## 静态资源服务场景-CDN

*  配置语法-文件读取
   
   ```py
   Syntax:sendfile on | off
   Default:sendfile off;
   Context:http,server,location,if in location   
   ```

   引读: -with-file-aio 异步文件读取

*  配置语法-tcp_nopush

   ```py
   Syntax:tcp_nopush on | off;
   Default:tcp_nopush off;
   Context:http,server,location
   ```

   作用：sendfile开启的情况下，提高网络包的传输效率

*  配置语法-tcp-nodelay
   
   ```py
   Syntax: tcp_nodelay on | off;
   Default:tcp_nodelay on;
   Context:http,server,location
   ```

   作用:keepalive连接下，提高网络包的传输实时性

*  配置语法-压缩
   
   ```py
   Syntax:gzip on |off;
   Default:gzip off;
   Context:http,server,location,if in location
   ```

   ```py
   #压缩比
   Syntax:gzip_comp_level level;
   Default:gzip_comp_level 1;
   Context:http,server,location
   ```

   ```py
   # 控制gzip http协议的版本
   Syntax: gzip_http_version 1.0|1.1;
   Default:gzip_http_version 1.1;
   Context:http,server,location   
   ```

   作用：压缩数据

*  扩展Nginx压缩模块
   
   `http_gzip_static_module` - 预读gzip功能
   `http_gunzip_module` - 应用支持gunzip的压缩方式

*  实战配置

   ```py

   #请求路径以这些后缀结束
   location ~ .*\.(jpg|gif|png)$ {
        #gizp on;
        #gizp_http_version 1.1;
        #gizp_comp_level 2;
        #gizp_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript 
    application/x-httpd-php image/jpeg image/gif image/png;
        root /opt/app/code/images;
    }
    
    location ~ .*\.(txt|xml)$ {
        #gizp on;
        #gizp_http_version 1.1;
        #gizp_comp_level 1;
        #gizp_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript 
     application/x-httpd-php image/jpeg image/gif image/png;

     }
     
     #目录以download开头
     location ~ ^/download {
        gzip_static on;
        tcp_nopush on;
        root /opt/app/code;
     }

   ```

## 浏览器缓存

   HTTP协议定义的缓存机制（如：Expires;Cache-control等）  
   
*  校验过期机制

   |校验是否过期|Expires,Cache-Control(max-age)|
   |------|------|
   |协议中Etag头信息校验|Etag|
   |Last-Modified头信息校验|Last-Modified|

   如图：

   ![](https://nanganghuang.github.io/image/2019-03-25.png)

*  配置语法 - expires
   
   添加Cache-Control,Expires头

   ```py
   Syntax: expires [modified] time;
           expires epoch | max | off;
   Default: expires off;
   Context: http,server,location,if in location
   ```
   
   ```py
   # 示例
   location ~ .*\.(htm|html)$ {
        expires 24h; # 缓存24小时之后过期
        root /opt/app/code;
     }
   ```
   

## Nginx跨域访问
 
*  Nginx如何允许跨域访问

   ```py
   Syntax: add_header name value [always];
   Default: ---
   Context:http,server,location,if in location

   # Access-Control-Allow-Origin 打开跨站访问
   ```
   
   ```py
   # 示例
   location ~ .*\.(htm|html)$ {
        # add_header Access-Control-Allow-Origin http://www.jesonc.com # 跨域访问
        # add_header Access-Control-Allow-Methods GET,POST,PUT,DELETE,OPTIONS;
        root /opt/app/code;
     }
   ```

   ```html
   <!-- 该页面会产生跨域访问，通过ajax访问http://jeson.imoocc.com/1.htm -->
   <html>
       <meta charset="utf-8">
       <title>测试ajax和跨域访问</title>
       <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <script type="text/javascript">
      $(document).ready(function(){
          $.ajax({
             type:"GET",
             url:"http://jeson.imoocc.com/1.html",
             success: function(data){
                alert("sucess!!!");
             },
             error: function(){
                 alert("fail!!!,请刷新再试");  
             }
          });
       });
      <body style="background-color:red;">
        <h1>测试跨域访问</h1>
      </body>
   </html>
   ```

## 防盗链 
   
   目的：防止资源被盗用

*  基于http_refer防盗链配置模块
   
   ```py
   Syntax: valid_referers none | blocked | server_names | string ...;
   Default: ---
   Context: server,location
   ```

   ```py
   location ~ .*\.(jpg|gif|png)$ {
        valid_referers none blocked 116.62.103.228 ~/google\./;
        if($invalid_referer){
           return 403;
        }
        root /opt/app/code/images;
    }
   ```

## 代理服务
   
   正向代理与反向代理

   区别：区别在于代理的对象不一样

   正向代理代理的对象是客户端
   反向代理代理的对象是服务端
       
*  配置语法

   ```py
   Syntax: proxy_pass URL;
   Default: ---
   Context:location,if in location,limit_except

   #http://localhost:8000/uri
   #https://192.168.1.1:8000/uri
   ```

   ```py
   # 监听80端口，反向代理到8080端口
   listen 80;   

   location ~ /test_proxy.htmls {
       proxy_pass http://127.0.0.1:8080;
   }
   ```

*  其他配置语法 - 缓冲区

   ```py
   Syntax: proxy_buffering on | off;
   Default: proxy_buffering on;
   Context: http,server,location
   ```
   扩展：`proxy_buffer_size`,`proxy_buffers`,`proxy_busy_buffers_size`
   
*  其他配置语法 - 跳转重定向

   ```py
   Syntax: proxy_redirect default;
   proxy_redirect off;proxy_redirect redirect replacement;
   Default:proxy_redirect default;
   Context:http,server,location
   ```

*  其他配置语法 - 头信息
   
   ```py
   Syntax:proxy_set_header field value;
   Default:proxy_set_header Host $proxy_host;
           proxy_set_header Connection close;
   Context:http,server,location
   ```

   扩展：`proxy_hide_header`,'proxy_set_body'

*  其他配置语法 - 超时
   
   ```py
   Syntax:proxy_connect_timeout time;
   Default:proxy_connect_timeout 60s;
   Context:http,server,location   
   ```
   
   扩展：`proxy_read_timeout`,`proxy_send_timeout`

*  案例

   ```py
   location / {
       proxy_pass http://127.0.0.1:8080;
       proxy_redirect default;
       
       proxy_set_header Host $http_host;
       proxy_set_header X-Real-IP $remote_addr;
      
       proxy_connect_timeout 30;
       proxy_send_timeout 60;
       proxy_read_timeout 60;
       
       proxy_buffer_size 32k;
       proxy_buffering on;
       proxy_buffers 4 128k;
       proxy_busy_buffers_size 256k;
       proxy_max_temp_file_size 256k;
   }

   ```

## Nginx负载均衡

*  配置语法
   
   ```py
   Syntax: upstream name {...}
   Default: ---
   Context:http
   ```

   ```py
   # http下简单配置负载均衡项
   upstream imooc {
      server 192.169.50.135:8001 down;
      server 192.168.50.135:8002 backup;
      server 192.168.50.135:8003 max_fails=1 fail_timeout=10s;

   # 把8003这个端口请求dport掉linux命令
   # iptables -I INPUT -p tcp --dport 8003 -j DROP
   # 清理iptanles规则
   # iptables -F
   }

   # location下配置
   location / {
       proxy_pass http://imooc;
       include proxy_params;
   }
   ```

*  upstream举例

   ```py
   upstream backend {
       #ip_push; 
       server backend1.example.com   weigth=5;#加权轮询
       server backend2.example.com:8080;
       server unix:/tmp/backend3;
 
       server backup1.example.com:8080 backup;
       server backup2.example.com:8080 backup;
   }
   ```

*  后端服务器在负载均衡调度中的状态

   |down|当前的server暂时不参与负载均衡|
   |------|------|
   |backup|预留的备份服务器|
   |max_fails|允许请求失败的次数|
   |fail_timeout|经过max_fails失败后，服务暂停的时间|
   |max_conns|限制最大的接收的连接数|
      
*  nginx调度算法

   |轮询|按时间顺序逐一分配到不同的后端服务器|
   |------|------|
   |加权轮询|weight值越大，分配到的访问几率越高|
   |ip_push|每个请求按访问IP的hash结果分配，这样来自同一个IP的固定访问一个后端服务器|
   |url_hash|按照访问的URL的hash结果来分配请求，是每个URL定向到同一个后端服务器|
   |least_conn|最小链接数，那个机器连接数就少分发|
   |hash关键数值|hash自定义的key|
   
*  url_hash

   ```py
   Syntax: hash key [consistent];
   Default: ---
   Context:upstream
   # this directive appeared in version 1.7.2
   ```

   ```py
   upstream imooc {
      hash $request_uri;
      server 192.169.50.135:8001 down;
      server 192.168.50.135:8002 backup;
      server 192.168.50.135:8003 max_fails=1 fail_timeout=10s;
   }
   ```

## 缓存服务

*  代理缓存

   ![](https://nanganghuang.github.io/image/Snipaste_2019-03-29_14-54-58.png)

*  proxy_cache配置语法

   ```py
   Syntax: proxy_cache_path path [levels=levels] [use_temp_path=on|off] 
           keys_zone=name:size [inactive=time] [max_size=size] [manager_files=number]
           [manager_sleep=time] [manager_threshold=time] [loader_files=number]
           [loader_sleep=time] [loader_threshold=time] [purger=on|off] 
           [purger_files=number] [purger_sleep=time] [purger_threshold=time];
   Default: ---
   Context: http
   ```
   
   ```py
   Syntax: proxy_cache zone | off
   Default: proxy_cache off;
   Context: http,server,location
   ```

*  配置语法 - 缓存过期周期

   ```py
   Syntax: proxy_cache_valid [code ...] time;
   Default: ---
   Context: http,server,location
   ```

*  配置语法 - 缓存的维度

   ```
   Syntax: proxy_cache_key string;
   Default: proxy_cache_key $scheme$proxy_host$request_uri;
   Context:http,server,location
   ```
   
   ```py
   #案例
   upstream imooc {
      hash $request_uri;
      server 192.169.50.135:8001 down;
      server 192.168.50.135:8002 backup;
      server 192.168.50.135:8003 max_fails=1 fail_timeout=10s;
   }
   
   proxy_cache_path /opt/app/cache levels=1:2 keys_zone=imooc_cache:10m max_size=10g 
                    inactive=60m use_tmp_path=off;

   # server下
   if($request_uri ~ ^/(url3|login|register|password\/reset)){
      set $cookie_nocache 1;
   }

   location / {
       proxy_cache imooc_cache;
       proxy_pass http://imooc;
       proxy_cache_valid 200 304 12h;
       proxy_cache_valid any 10m;
       proxy_cache_key $host$uri$is_args$args;

       #让页面不缓存
       proxy_no_cache $cookie_nocache $arg_nocache $arg_comment;
       proxy_no_cache $http_pragma $http_authorization;
       add_header Nginx-Cache "$upstream_cache_status";

      proxy_next_upstream error timeout invalid_header http_500 http_502 http_503 
                            http_504;
       include proxy_params;
   }
    
   ```

*  如何清理指定缓存？
    
   方式一、rm -rf 缓存目录内容
   方式二、第三方扩展模块ngx_cache_purge

*  如何让部分页面不缓存？
 
   ```py
   Syntax: proxy_no_cache string ...,
   Default: ---
   Context:http,server,location
   ```

*  大文件分片请求

   ```py
   Syntax: slice size;
   Default: slice 0;
   Context:http,server,location
   ```

   优势：
   每个子请求收到的数据会形成一个独立文件，一个请求断了，其他请求不受影响。

   缺点：
   当文件很大或者slice很小的时候，可能会导致文件描述符耗尽等情况
