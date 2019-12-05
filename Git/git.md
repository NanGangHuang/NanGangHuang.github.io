> https://www.bootcss.com/p/git-guide/  

> https://www.cnblogs.com/Sungeek/p/6928125.html

## 一、在centos7下搭建git服务器

#### 1、安装 Git

`git --version`  
`yum install -y git`  

#### 2、服务器端创建 git 用户，用来管理 Git 服务，并为 git 用户设置密码

```
[root@localhost home]# id git
id: git：无此用户
[root@localhost home]# useradd git
[root@localhost home]# passwd git
```
#### 3、服务器端创建 Git 仓库

```
[root@localhost home]# mkdir -p data/git/gittest.git
[root@localhost home]# git init --bare data/git/gittest.git
初始化空的 Git 版本库于 /home/data/git/gittest.git/
[root@localhost home]# cd data/git/
[root@localhost git]# chown -R git:git gittest.git/
```
#### 4、客户端 clone 远程仓库

```
$ git clone git@47.110.127.54:/home/git/file.git
```
#### 5、客户端创建 SSH 公钥和私钥

```
$ ssh-keygen -t rsa -C "1570438765@qq.com"
```
#### 6、服务器端 Git 打开 RSA 认证
进入 /etc/ssh 目录，编辑 sshd_config，打开以下三个配置的注释：  
```
[root@localhost git]# cd /etc/ssh/


[root@localhost ssh]# vim sshd_config

RSAAuthentication yes

PubkeyAuthentication yes

AuthorizedKeysFile .ssh/authorized_keys
```
:wq 保存  

保存并重启 sshd 服务：  
```
[root@localhost ssh]# systemctl restart sshd
```

在 /home/git/ 下创建目录 .ssh
```
[root@localhost git]# pwd
/home/git
[root@localhost git]# mkdir .ssh
[root@localhost git]# ls -a 
. .. .bash_logout .bash_profile .bashrc .gnome2 .mozilla .ssh
```
然后把 .ssh 文件夹的 owner 修改为 git
```
[root@localhost git]# chown -R git:git .ssh
```

#### 7、将客户端公钥导入服务器端 /home/git/.ssh/authorized_keys 文件

回到 Git Bash 下，导入文件：  
 ```
$ ssh git@192.168.20.101 'cat >> .ssh/authorized_keys' < ~/.ssh/id_rsa.pub
```
回到服务器端，查看 .ssh 下是否存在 authorized_keys 文件：  
```
[root@localhost git]# cd .ssh
[root@localhost .ssh]# ll
总用量 4
-rw-rw-r--. 1 git git 398 8月  28 20:08 authorized_keys
```


## 二、git基本使用

#### 1、`git init` : 创建新仓库。     

创建新文件夹，打开，然后执行`git init`以创建新的git仓库  

#### 2、检出仓库 

执行如下命令已创建一个本地仓库的克隆版本：    
`git clone /path/to/repository`  
如果是远端服务器上的仓库，你的命令会是这个样子的：    
`git clone username@host:/path/to/repository`    
如：`git clone git@47.110.127.54:/home/git/file.git`  

如果是克隆远程分支：  
 `git clone -b <branch name> username@host:/path/to/repository`  
如远程仓库除了master还有mymaster分支  
则`git clone -b mymaster git@47.110.127.54:/home/git/file.git`  

#### 3、添加与提交

可以计划改动（把它们添加到缓存区），使用如下命令：    
`git add <filename>`  
`git add *`  
这是 git 基本工作流程的第一步；使用如下命令以实际提交改动：  
`git commit -m "代码提交信息"`  
现在，你的改动已经提交到了 HEAD，但是还没到你的远端仓库。  

#### 4、推送改动

你的改动现在已经在本地仓库的 HEAD 中了。执行如下命令以将这些改动提交到远端仓库：  
`git push origin master`   
可以把 master 换成你想要推送的任何分支。    

如果你还没有克隆现有仓库，并欲将你的仓库连接到某个远程服务器，你可以使用如下命令添加：  
`git remote add origin <server>`  
如此你就能够将你的改动推送到所添加的服务器上去了。  
 

####5、分支

分支是用来将特性开发绝缘开来的。在你创建仓库的时候，master 是“默认的”。在其他分支上进行开发，完成后再将它们合并到主分支上。  


创建一个叫做“feature_x”的分支，并切换过去：  
`git checkout -b feature_x`  
切换回主分支：  
`git checkout master`  
再把新建的分支删掉：  
`git branch -d feature_x`  
除非你将分支推送到远端仓库，不然该分支就是 不为他人所见的：  
`git push origin <branch>`  

#### 6、更新与合并

要更新你的本地仓库至最新改动，执行：  
`git pull`  
更新特定分支  
`git pull origin mmaster`  

以在你的工作目录中 获取（fetch） 并 合并（merge） 远端的改动。  
要合并其他分支到你的当前分支（例如 master），执行：  
`git merge <branch>`  
两种情况下，git 都会尝试去自动合并改动。不幸的是，自动合并并非次次都能成功，并可能导致 冲突（conflicts）。 这时候就需要你修改这些文件来人肉合并这些 冲突（conflicts） 了。改完之后，你需要执行如下命令以将它们标记为合并成功：  
`git add <filename>`  
在合并改动之前，也可以使用如下命令查看：  
`git diff <source_branch> <target_branch>`  

