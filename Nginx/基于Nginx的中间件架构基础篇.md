## Centos 防火墙

*  关闭firewall

   > firewall-cmd --state #查看默认防火墙状态（关闭后显示notrunning，开启后显示running）  
   > systemctl stop firewalld.service #停止firewall  
   > systemctl disable firewalld.service #禁止firewall开机启动  

## 环境调式确认

*  两项安装
   > yum -y install gcc gcc-c++ autoconf pcre pcre-devel make automake  
   > yum -y install wget httpd-tools vim  

*  一次初始化
   > cd /opt;mkdir app download logs work backup

## yum安装nginx
 
*  添加nginx到yum源
   
   > sudo rpm -Uvh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm

*  安装nginx
  
   > sudo yum install -y nginx

*  启动nginx

   > sudo systemctl start nginx.service

*  强制停止nginx

   > pkill -9 nginx

*  验证nginx是否启动成功
  
   > 在浏览器输入本机IP地址，出现nginx默认页面

## 编译安装nginx

*  官网上下载源码包

*  使用命令解压源码包，如：
   
   > tar -zxvf nginx-1.0.14.tar.gz

*  编译安装nginx
   
   进入目录nginx-1.0.14，执行以下命令
   > ./configure  
   > make  
   > make install  



## Nginx简述
   Nginx是一个开源且高性能、可靠的HTTP中间件、代理服务  

## 基本参数使用

*  安装目录

   > rpm -ql nginx  

   |路径|类型|作用|
   |------|------|------|
   |/etc/logrotate.d/nginx|配置文件|Nginx日志轮转，用于logrotate服务的日志切割|
   |/etc/nginx <br> etc/nginx/nginx.conf <br>  /etc/nginx/conf.d <br>/etc/nginx/conf.d/default.conf | 目录、配置文件 |Nginx主配置文件|
   |/etc/nginx/fastcgi_params <br> /etc/nginx/scgi_params <br>/etc/nginx/uwsgi_params |配置文件|cgi配置相关，fastcgi配置|
   |/etc/nginx/koi-utf <br> /etc/nginx/koi-win <br> /etc/nginx/win-utf|配置文件|编码转换映射转化文件|
   |/etc/nginx/mime.types |配置文件|设置http协议的Content-Type与扩展名对应的关系|
   |/etc/sysconfig/nginx <br> /etc/sysconfig/nginx-debug <br> /usr/lib/systemd/system/nginx-debug.service <br> /usr/lib/systemd/system/nginx.service | 配置文件|用于配置出系统守护进程管理器管理方式|
   |/usr/lib64/nginx/modules <br> /etc/nginx/modules |目录|Nginx模块目录|
   |/usr/sbin/nginx <br> /usr/sbin/nginx-debug |命令|Nginx服务的启动管理的终端命令|
   |/usr/share/doc/nginx-1.14.2 <br> /usr/share/doc/nginx-1.14.2/COPYRIGHT <br> /usr/share/man/man8/nginx.8.gz |文件、目录|Nginx手册和帮助文件|
   |/var/cache/nginx |目录|Nginx缓存目录|
   |/var/log/nginx |目录 |Nginx日志目录|


*  编译参数

   > nginx -V

   |编译选项|作用|
   |------|------|
   |--prefix=/etc/nginx <br>--sbin-path=/usr/sbin/nginx <br> --modules-path=/usr/lib64/nginx/modules <br>--conf-path=/etc/nginx/nginx.conf <br> --error-log-path=/var/log/nginx/error.log <br> --http-log-path=/var/log/nginx/access.log  <br>--pid-path=/var/run/nginx.pid <br> --lock-path=/var/run/nginx.lock |安装目的目录或路径|
   |--http-client-body-temp-path=/var/cache/nginx/client_temp <br> --http-proxy-temp-path=/var/cache/nginx/proxy_temp <br>--http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp <br>--http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp <br>--http-scgi-temp-path=/var/cache/nginx/scgi_temp |执行对应模块时，nginx所保存的临时文件|
   |--user=nginx --group=nginx | 设定Nginx进程启动的用户和组用户|
   | --with-cc-opt=parameters|设置额外的参数将被添加到CFLAGS变量|
   |--with-ld-opt=parameters|设置附加的参数，链接系统库|



*  Nginx默认语法配置

   |user|设置nginx服务的系统使用用户|
   |------|------|
   |worker_processes|工作进程数|
   |error_log|nginx的错误日志|
   |pid|nginx服务启动时候的pid|

   |events|worker_connections|每个进程允许最大连接数|
   |------|------|------|
   ||use|工作进程数|

*  重启nginx

   > systemctl restart nginx.service   

## HTTP请求

   客户端  <==============> 服务端  
   request - 包括请求行、请求头、请求数据  
   response - 包括状态行、消息报头、响应正文  

## Nginx日志类型

   包括：error.log access_log

## Nginx变量

   HTTP请求变量 - arg_PARAMETER、http_HEADER、sent_http_HEADER

   内置变量 - Nginx内置的

   自定义变量 - 自己定义
      
## Nginx模块讲解

*  Nginx官方模块

   + 安装编译参数讲解 
   
     |编译选项|作用|
     |------|------|
     | --with-http_stub_status_module |Nginx的客户端状态 |
     | --with-http_random_index_module |目录中选择一个随机主页 |
     |--with-http_sub_module |HTTP内容替换 |
   

   + http_stub_status_module 配置 

     > Syntax ： stub_status      
     > Default ： -      
     > Context : server,location     
 
     具体配置default.conf配置如下选项  

     ```py
     location /mystatus {
       stub_status;
     }
     ```
   + random_index_module

     > Syntax:random_index on | off  
     > Deault:random_index off  
     > Context:location  

     例如：nginx主页配置  

     ```py
     location / {
        root   /opt/app/code;
        random_index on;
        #index  index.html index.htm;
     }
     ```

   + http_sub_module  
    
     > Syntax:sub_filter string replacement    
     > Default : -    
     > Context:http,server,location    
     
     > Syntax:sub_filter_last_modified on | off  
     > Default:sub_filter_last_modified off  
     > Context:http,server,location  
     注：判断服务端的内容是否有更新  
     
     > Syntax:sub_filter_once on | off;  
     > Default:sub_filter_once on;  
     > Context:http,server,location  
     
     ```py
      location / {
        root   /opt/app/code;
        index  submodel.html;
        sub_filter 'www.imook.com' '<a>imooc</a>';
        sub_filter_once off;
        # 强制更新所有
     }

     ```
*  Nginx的请求限制

   连接频率限制 --- `limit_conn_module`  
   请求频率限制 --- `limit_conn_module`  

   + limit_conn_module 连接限制
     
     > Syntax: limit_conn_zone key zone=name:size;  
     > Default: ---  
     > Context:http  
     
     > Syntax: limit_conn zone number;  
     > Default: ---  
     > Context:http,server,location  

   + limit_conn_module 请求限制

     > Syntax: limit_req_zone key zone=name:size rate=rate;  
     > Default: ---  
     > Context:http  

     > Syntax: limit_req zone=name [burst=number][nodelay];  
     > Default: ---  
     > Context:http,server,location  
     
     ```py
     #http模块下
     limit_conn_zone $binary_remote_addr zone=conn_zone:1m;
     limit_req_zone $binary_remote_addr zone=req_zone:1m rate=1r/s;
     
     #service模块下
     location / {
        root   /opt/app/code;
        limit_conn conn_zone 1;
        #limit_req zone=req_zone burst=3 nodelay;
        #limit_req zone=req_zone burst=3;
        #limit_req zone=req_zone;

        index  submodel.html;
     }

     ```

   + 压力测试工具ab(需要安装httpd工具)
     
     ```py
     # 40为连接数量
     # 20为请求数量
     ab -n 40 -c 20 http://192.168.0.102/submodel.html
     ```
*  Nginx的访问控制

   基于IP的访问控制 --- `http_access_module`  
   基于用户的信任登陆 --- `http_auth_basic_module`  

   + http_access_module
     
     > Syntax: allow address | CIDR |unix: | all;  
     > Default: ---  
     > Context:http,server,location,limit_except  

     > Syntax: deny address | CIDR | unix:| all;  
     > Default: ---  
     > Context:http,server,location,limit_except      

       ```py
       # location ~  ^/admin.html {
       #   root   /opt/app/code;
       #   deny 192.168.50.135;
       #   allow all;
       #   index  admin.html;
       # }

       location ~  ^/admin.html {
          root   /opt/app/code;
          allow  192.168.50.135;
          deny all;
          index  admin.html;
       }
    
       ```
   + http_auth_basic_module
     
     > Syntax: auth_basic string | off;  
     > Default: auth_basic off;  
     > Context: http,server,location,limit_except  

     > Syntax: auth_basic_user_file file;  
     > Default: ---  
     > Context: http,server,location,limit_except;  
    
     ```py
     # 安装工具httpd
     # 使用 yum install httpd-tools -y 命令安装httpd工具
     # 在/etc/nginx目录下使用命令 htpasswd -c ./auth_conf jeson 生成登录验证的用户名和密码，用户名为 jeson 
     # 在conf.d目录下的配置文件添加如下location

     location ~  ^/admin.html {
        root   /opt/app/code;
        auth_basic "Auth access test! input your password!";
        auth_basic_user_file /etc/nginx/auth_conf;
        index  admin.html;
     }
     
     # 访问admin.html页面时将出现登录验证

     ```

   + http_auth_basic_module 局限性
     
     > 用户信息依赖文件方式

     > 操作管理机械，效率低下

     解决方案
       ```py
       # Nginx结合LUA实现高效验证
       # Nginx和LDPA打通，利用ngnx-auth-ldap模块
       ```


*  第三方模块


## Nginx中linux命令  

   修改配置文件之后，使用命令检查配置文件是否正确 
   
   > nginx -t -c /etc/nginx/nginx.conf  
 
   出现successful表示成功。  
   修改配置文件之后，使用命令重新加载配置文件  

   > nginx -s reload -c /etc/nginx/nginx.conf  
   > ps -aux | grep nginx 

## 通过IP138来查询ip地址
   
   > www.ip138.com
