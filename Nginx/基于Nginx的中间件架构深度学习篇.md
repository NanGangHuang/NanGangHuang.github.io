## 动静分离

   通过中间件将动态请求和静态请求分离。

## Nginx的rewrite规则


   实现url重写以及重定向

*  配置语法
   
   ```py
   Syntax: rewrite regex replacement [flag];
   Default: ---
   Context:server,location,if

   # rewrite ^(.*)$ /pages/mantain.html break;
   ```

*  正则表达式
 
   |.|匹配除换行符以外的任意字符|
   |------|------|
   |?|重复0次或1次|
   |+|重复1次或更多次|
   |*|最少链接数，那个机器连接数少就分发|
   |/d|匹配数字|
   |^|匹配字符串的开始|
   |$|匹配字符串的结尾|
   |{n}|重复n次|
   |{n,}|重复n次或者更多次|
   |[c]|匹配单个字符|
   |[a-z]|匹配a-z小写字母的任意一个|
   |\|转义字符|
   |()|用于匹配括号之间的内容，通过$1,$2调用|
   
*  flag

   |last|停止rewrite检测|
   |------|------|
   |break|停止rewrite检测|
   |redirect|返回302临时重定向，地址栏会显示跳转后的地址|
   |permanent|返回301永久重定向，地址栏会显示跳转后的地址|   

   ```py
   # 案例 
   root /opt/app/code
   location ~ ^/break {
       rewrite ^/break /test/ break;
   }   

   location ~ ^/last {
       rewrite ^/last /test/ last;
   }

   location /test/ {
       default_type application/json;
       return 200 '{"status":"success"}';
   }

   ```

*  Rewrite规则优先级

   执行server块的rewrite指令  
   执行location匹配  
   执行选定的location中的rewrite  

## Nginx高级模块

*  secure_link_module模块

   一、制定并允许检查请求的链接的真实性以及保护资源免遭受未经授权的访问  
   二、限制链接生效周期  
   
   ```py
   Syntax: secure_link expression;
   Default: ---
   Context:http,server,location
   ```

   ```py
   Syntax: secure_link_md5 expression;
   Default: ---
   Context:http,server,location
   ```

   ![](https://nanganghuang.github.io/image/Snipaste_2019-03-29_18-00-01.png)
   ![](https://nanganghuang.github.io/image/Snipaste_2019-03-29_18-02-01.png)

   ```py
   root   /opt/app/code;

    location / {
        secure_link $arg_md5,$arg_expires; #上图中参数md5与参数expires
        secure_link_md5 "$secure_link_expires$uri imooc";#imooc为加密串，‘盐’

        if ($secure_link = "" ){
            return 403;
        }

        if ($secure_link = "0" ){
            return 410;
        }
    }

   ```

   ```sh
   #生成访问链接的脚本 
   #!/bin/sh
   #
   #Auth:jeson@imooc.com
   servername="192.168.50.135"
   download_file="/download/wei.jpg"
   time_num=$(date -d "2019-04-02 00:00:00" +%s)
   secret_num="imooc"

   res=$(echo -n "${time_num}${download_file}${secret_num}"|openssl md5 -binary |
      openssl base64 | tr +/ -_ | tr -d = )

   echo "http://${servername}${download_file}?md5=${res}&expires=${time_num}"

   ```

*  geoip_module模块

   基于ip地址匹配MaxMind GeoIP二进制文件，读取IP所在地域信息。
   
   安装:`yum install nginx-module-geoip`

   `http-geoip_module`使用场景
   
    + 区别国内外做HTTP访问规则   
    + 区别国内城市地域做HTTP访问规则  

*  HTTPS服务

   + 对称加密

   ![](https://nanganghuang.github.io/image/Snipaste_2019-04-01_16-00-32.png)

   + 非对称加密

   ![](https://nanganghuang.github.io/image/Snipaste_2019-04-01_16-03-59.png)

   + HTTPS加密原理

   ![](https://nanganghuang.github.io/image/Snipaste_2019-04-01_16-04-53.png)

   + 中间人伪造客户端和服务端

   ![](https://nanganghuang.github.io/image/Snipaste_2019-04-01_16-10-15.png)

*  生成秘钥和CA证书

   ```py
   在linux终端下输入一下命令确保openssl
   #openssl version 

   #nginx -V
   --with-http_ssl_module
   ```
   
   步骤一、生成key秘钥  `openssl genrsa -idea -out jeson.key 1024`    
   步骤二、生成证书签名请求文件(csr文件)  `openssl req -new -key jeson.key -out jeson.csr`
   步骤三、生成证书签名文件（CA文件） `openssl x509 -req -days 3650 -in jeson.csr -signkey jeson.key -out jeson.crt` 

*  Nginx的HTTPS语法配置

   ```py
   Syntax: ssl on | off ;
   Default: ssl off;
   Context:http,server
   ```

   ```py
   Syntax: ssl_certificate file;
   Default: ---
   Context:http,server
   ```

   ```py
   Syntax: ssl_certificate_key file;
   Default:---
   Context:http,server
   ```

   ```py
   #配置文件中
    listen       443;#443为https默认端口
    server_name  192.168.50.135;

    ssl on;
    ssl_certificate /etc/nginx/ssl_key/jeson.crt;
    ssl_certificate_key /etc/nginx/ssl_key/jeson.key;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

    root   /opt/app/code;

    location / {
        index index.html index.htm;
    }
   ```

   重启nginx服务
   `nginx -s stop -c /etc/nginx/nginx.conf` 停止  
   `nginx -c /etc/nginx/nginx.conf` 启动  
   `netstat -luntp | grep 443` 查看443端口是否启动  

*  配置苹果要求的证书

   a、服务器所有的链接使用TLS1.2以上版本（OpenSSL 1.0.2）  
   b、HTTPS证书必须使用SHA256以上哈希算法签名  
   c、HTTPS证书必须使用RSA 2048位或ECC 256位以上公钥算法  
   d、使用前向加密技术 
 
*  HTTPS服务优化

   方法一、激活keepalive长连接  
   方法二、设置ssl session缓存  
   
   ```py
   #server下
   keepalive_timeout 100;
   ssl_session_cache shared:SSL:10m;
   ssl_session_timeout 10m;
   ```

## Nginx与Lua开发

   > Lua：是一个简洁，轻量，可扩展的脚本语言

   Nginx与Lua的优势：  
   充分结合Nginx的并发处理epoll优势和lua轻量实现简单的功能切换高并发的场景

*  Lua的基础语法

   + 安装

     `yum insatll lua`

   + 运行

     ```py
     [root@localhost ~]# lua
     Lua 5.1.4  Copyright (C) 1994-2008 Lua.org, PUC-Rio
     > print("hello,wrold")
     hello,wrold

     [root@localhost work]# vim test.lua
     [root@localhost work]# chmod a+rx ./test.lua 
     [root@localhost work]# ./test.lua 
     i'm Jeson
     ```

   + 注释

     —— 行注释

     --[[
         块注释
     --]]

   + while循环语句

     ```py
     sum = 0
     num = 1
     while num <= 100 do
       sum = sum + num
       num = num + 1
     end
     print("sum=",sum)
     #Lua没有++或是+=这样的操作
     ```

   + if-else判断语句

     ```py
     if age == 40 and sex == "Male" then
       print("大于40男人")
     elseif age > 60 and sex ~= "Female" then
       print("非女人而且大于60")
     else
       local age = io.read()
       print("Your age is"..age)
     end
     #"~=" 是不等于
     #字符串的拼接操作符 ".."
     #io库的分别从stdin和stdout读写的read和write函数
     ```

*  Nginx+Lua环境

   [Nginx编译安装Lua模块](https://www.imooc.com/article/19597)

*  Nginx调用lua模块指令

   Nginx的可插拔模块加载执行，共11个阶段处理
   
   |set_by_lua<br>set_by_lua_file|设置nginx变量，可实现复杂的赋值逻辑|
   |------|------|
   |access_by_lua<br>access_by_lua_file|请求访问阶段处理，用于访问控制|
   |content_by_lua<br>content_by_lua_file|内容处理器，接受请求处理并输出响应|

*  实战场景 - 灰度发布

   按照一定的关系区别，分部分的代码进行上线，使代码的发布能平滑过渡上线。
   
   + 用户的信息cookie等信息区别
   
   + 根据用户的ip地址

   ![](https://nanganghuang.github.io/image/Snipaste_2019-04-02_14-56-06.png)

   

