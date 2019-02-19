---
layout: post
title: linux下安装jdk,tomcat,eclipse，mysql及相关linux指令
date: 2019-02-19 11:55:00
tags: linux tomcat mysql eclipse
categories: linux
excerpt: linux mint系统安装jdk,eclipse,tomcat,mysql等基本开发工具,以及linux下如何安装使用git，如何打开eclipse
---

# Linux常用命令

**打印工作目录，当前所路径:**

```
pwd	
/home/soft01 
```

**改变工作目录 :**

```
cd /etc
```

 **创建文件夹命令:**

```
mkdir 文件夹名         新建空文件夹
```

**显示目录内容:**

```java
ls       平铺  
ll       详细列表
```

**编辑文件：**

```java
vi 与 vim 都是编辑文件，如果文件不存在，带有创建文件功能
vi 普通编辑  vim高级编辑（带有颜色）
进入到编辑器后非可编辑状态，点击“i” 或 “insert”启用编辑状态
编辑后点击“esc”退出编辑状态 ，输入
	:wq  保存并退出
	:q   不保存退出（适用于没有编辑时）
	:q!   强制退出
```

**创建空文件：**

```java
touch 文件名     创建空文件
```

```java
cat 文件名       查看文件全部内容
```

```java
head [-n] 文件名      查看文件的前n行，默认前10行
```

```java
tail [-n] 文件名      查看文件的后n行，默认是后10行
tailf 文件名          动态显示文件后n行。常用在tomcat显示日志文件功能。
```

```java
echo '内容' >>文件名   向文件中添加一些内容。
```

```java
ifconfig   打印网卡信息.
```

```java
reboot     重启。
```

**打包命令：**

```
tar -czvf 包文件名.tar.gz 文件夹 文件夹 ...

-c 选项 创建包
-z 选择 创建包以后采用gzip进行压缩, 建议文件后缀是 tar.gz, 是告诉使用者这个包是压缩过的!
-v 选项 查看打包过程, 可以显示哪些文件被打到包中, 这个选项会响应打包速度.
-f 选项 用于指定打包以后的文件名

参数文件夹是被打包文件夹!

tar -czvf maven.tar.gz apache-maven-3.3.9
```

**释放包:**

```
tar -xzvf 包文件名.tar.gz

-x 选项 释放包

mkdir test
cd test
tar -xzvf ../maven.tar.gz
```

**复制文件命令：**

```java
cp  [-r] 源文件 新文件路径   复制文件,加入参数[-r]表示复制文件夹

cp 源文件 目标文件           将源文件改名复制为目标文件

cp 源文件 目标文件夹         将源文件复制到目标文件夹中,文件名不变

cp -r 源文件夹  新文件夹     将源文件夹改名复制为新文件夹

cp -r 源文件夹  已有文件夹   将源文件夹复制到已有文件夹中,文件名不变
```

```java
ctrl + c   中断
```

**文件夹/文件改名命令：**

```java
mv 原文件  新文件    剪切，具备重命名的功能，重命名目录也是一样的功能
```

**删除命令(remove)：**

```java
rm [-rf] 文件名     删除 [-r]表示删除目录 [-f]表示强制删除
```

```java
clear   清屏
```

# Linux下安装jdk

#### 1.确保jdk安装包是无损的

#### 2.删除原来的jdk

```java
rpm -qa | grep java  或 rpm -qa | grep jdk  //命令来查询出系统自带的jdk
```

```java
rpm -e --nodeps   后面跟系统自带的jdk名    //这个命令来删除系统自带的jdk
```

```java
rpm -qa | grep java  或 rpm -qa | grep jdk //命令来查询出是否删除掉
```

#### 3.创建/usr/java文件

```java
使用touch命令创建/usr/java文件，将jdk压缩包解压到该文件夹中
```

#### 4.在/etc/profile文件中配置环境变量

```java
//使用vim /etc/profile打开该文件
```

```java
//在末尾添加如下配置
export JAVA_HOME=/usr/java
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
```

#### 5.解析文件（或重启系统）

```java
source /etc/profile
```

#### 6.验证配置是否成功

```java
java -version
```



# Linux下部署tomcat

#### 1.上传tomcat压缩包到服务器并解压

```java
//注：解压之后的文件夹放在一个文件夹下 如：/usr/local/tomcat
```

#### 2.配置环境变量

```java
export TOMCAT_HOME=/usr/local/tomcat
export CATALINA_HOME=/usr/local/tomcat
```

#### 3.打开8080端口

```java
//我们可以输入命令查看防火墙的状态；
firewall-cmd --state；
```

```java
//如果上一步处于关闭状态，输入命令：
systemctl start firewalld.service；
```

```java
//开启8080端口，输入命令：
firewall-cmd --zone=public --add-port=8080/tcp --permanent；

--zone=public  //表示作用域为公共的；
--add-port=8080/tcp  //添加tcp协议的端口8080；
--permanent  //永久生效，如果没有此参数，则只能维持当前服务生命周期内，重新启动后失效；
```

```java
//输入命令重启防火墙；
systemctl restart firewalld.service；
```

```java
//输入命令重新载入配置；
firewall-cmd --reload；
```

#### 4.启动tomcat,进入到tomcat/bin文件夹

```java
//直接启动
./startup.sh
```

```java
//启动并动态打印启动信息
./startup.sh  &  /usr/local/tomcat/logs/catalina.out
```

#### 5.关闭tomcat

```java
./shutdown.sh
```

# linux mint 的MySql的安装

- 打开终端

- 在终端中输入 **sudo apt-get install mysql-server** ，回车后，系统要求输入当前用户的密码，输入密码后回车。

- 读取和分析完毕后，系统会要求确认，然后输入 y 确认后开始下载并安装mysql。

- 安装过程中，会提示设置MySQL数据库管理员root的密码，输入密码后回车。

- 再次输入密码进行确认，然后回车。

- 安装完毕。

- 输入 **mysql -u root -p** ，用root账号登陆。

  回车后要求输入密码，就是在安装过程中设置的密码。

- 输入`exit`退出`mysql`

# ubuntu使用git

安装git

> 使用命令：

```java
apt-get install git 
git --version //查看git安装的版本
```

#### 初始化目录

> 在本地当前目录初始化git仓库,cd 到某目录下，

```
git inti
```

#### 配置并连接GitHub

> 配置用户名密码

```
git config --global user.name "你的github帐号名"
git config --global user.email "你的github邮箱"
```

#### GitHub帐号添加SSH Keys

> 生成keys

```
ssh -keygen -t rsa -C "你的github邮箱"
```

​	系统会提示key的保存位置（一般是~/.ssh目录）和指定口令，保持默认，连续三次回车即可

> 复制SSH Key到GitHub

​	使用vim打开生成的id_rsa.pub文件，将里面的内容复制粘接到GitHub中的SSH Key界面

Setting -> SSH and GPG Keys -> New SSH key 添加

#### 测试连接是否成功

```java
ssh -T git@github.com  //时间会略长
```

# linux下打开eclipse

cd到你的安装目录，如：

```java
cd /root/eclipse/jee-2018-09/eclipse
```

运行：

```java
./eclipse //启动eclipse
```

