## Jenkins的安装 
     
1. 安装网址(下载.war包)
	https://www.jenkins.io/download/  
2. 放入centos服务器某一目录，在该目录下使用命令 java -jar jenkins.war --httpPort=8081 启动jenkins.
	浏览器打开网址http://106.54.183.217:8081/ 设置 user : Admin ，password：Admin  
3. 生成的jenkins 初始password 为 ca1a523453fe46dd8a009691c1c3fae0。
4. 添加自己的插件：Manage Jenkins -> Manage Plugins -> 可选插件  -> [rebuilder,safe restart]
<div align="center"><img src="https://nanganghuang.github.io/Jenkins/img/1.png" ></img></div>
	

## Jenkins的基础配置
#### 1.配置全局安全性  
Manage Jenkins -> Configure Global Security ->  授权策略下选择安全矩阵 -> add user并赋予管理员权限
<div align="center"><img src="https://nanganghuang.github.io/Jenkins/img/2.png" ></img></div>

#### 2.新建用户并赋予权限
Manage Jenkins -> Manage Users -> 新建用户
创建好用户之后在上述第二步添加用户并赋予权限（取消第一个Adminster权限）。
