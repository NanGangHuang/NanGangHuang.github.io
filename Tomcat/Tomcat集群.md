
## Tomcat单机部署多应用/多机部署多应用

## Tomcat集群能带来什么

+ 提高服务的性能，并发能力，以及高可用性  
+ 提供项目架构横向扩展能力

## Tomcat集群带来了什么问题

+ Session登录信息存储及读取的问题
+ 服务器定时任务并发的问题

## Tomcat单机部署多应用

+ 修改/etc/profile 增加tomcat环境变量

    ```
    export CATALINA_BASE=/home/install_software/tomcat1
    export CATALINA_HOME=/home/install_software/tomcat1
    export TOMCAT_HOME=/home/install_software/tomcat1
    export CATALINA_2_BASE=/home/install_software/tomcat2
    export CATALINA_2_HOME=/home/install_software/tomcat2
    export TOMCAT_2_HOME=/home/install_software/tomcat2
    
    ```

+ 第一个tomcat不变

+ 打开第二个tomcat目录bin下catalina.sh

+ 在这行下面编辑，新增配置，保存退出

    ```
    # OS specific support.  $var _must_ be set to either true or false.
    export CATALINA_BASE=$CATALINA_2_BASE
    export CATALINA_HOME=$CATALINA_2_HOME
    ```

+ 打开第二个tomcat的conf目录下的server.xml
    既：${tomcat}/conf/server.xml
  > 修改三个端口！！！
    
+ Server port节点端口号修改

    `<Server port="8005" shutdown="SHUTDOWN">`修改为==>  
    `<Server port="9005" shutdown="SHUTDOWN">`

+ Connector port="8080"节点端口号修改

    ```    
    <Connector port="8080" protocol="HTTP/1.1"
                    connectionTimeout="20000"
                    redirectPort="8443" URIEncoding="UTF-8" />
    ```
    
    ```    
    <Connector port="9080" protocol="HTTP/1.1"
                    connectionTimeout="20000"
                    redirectPort="8443" URIEncoding="UTF-8" />
    ```
    
+ Connector port="8009" protocol=...节点端口号修改

    ```
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
    ```
    
    ```
    <Connector port="9009" protocol="AJP/1.3" redirectPort="8443" />
    ```
    
+ 分别进入两个tomcat的bin目录，启动tomcat

+ 配置nginx,并启动

    ```
    upstream 47.110.127.54{
            server 47.110.127.54:8080 weight=1;
            server 47.110.127.54:9080 weight=2;
    
    }
    
    
    server {
            listen 80;
            autoindex on;
            #server_name imooc.com www.imooc.com;
            access_log /var/log/nginx/host.access.log combined;
            index index.html index.htm index.jsp index.php;
    
    location / {
            proxy_pass http://47.110.127.54;
            add_header Access-Control-Allow-Origin *;
    
    
            }
    ```