## Nginx常见问题

*  相同server_name多个虚拟主机优先级访问
   
   按照文件的顺序

*  location匹配优先级
   
   > =     进行普通字符精确匹配，也就是完全匹配   
   > \~\\\~*  表示执行一个正则匹配（），\~*区分大小写   
   > ^\~    表示普通字符匹配，使用前缀匹配  
   
   

*  try_files使用

   按顺序检查文件是否存在

   ```py
   location / {
      try_files $uri $uri/ /index.php;
   }
   ```

*  Nginx的alias和root区别

   ```py
   #root
   location /request_path/image/ {
       root /local_path/image/;
   }

   #浏览器访问地址：http://www.imooc.com/request_path/image/cat.png
   #本地文件存储位置：/local_path/image/request_path/image/cat.png
   ```

   ```py
   #alials
   location /request_path/image/ {
       root /local_path/image/;
   }

   #浏览器访问地址：http://www.imooc.com/request_path/image/cat.png
   #本地文件存储位置：/local_path/image/cat.png
   ```

*  用什么方法传递用户的真实IP

   ![](https://nanganghuang.github.io/Nginx/img/Snipaste_2019-04-03_10-35-38.png)

*  Nginx常见错误码
   
   `Nginx: 413 Request Entity Too Large`

   1、用户上传文件限制 `client_max_body_size`

   `502 bad gatway`

   2、后端服务无响应

   `504 Gateway Time-out`

   3、后端服务执行超时

   `403` ： 访问被拒绝

   `404` ： 文件没找到

   `400` ： 请求参数错误

## Nginx性能优化

*  性能优化考虑点
   
*  压测工具ab

   + 安装

     `yum install httpd-tools`

   + 使用

     ab -n 2000 -c 2 http://127.0.0.1/
     -n 总的请求数
     -c 并发数
     -k 是否开启长连接

*  系统与Nginx性能优化
   
   网络、系统、服务  程序、数据库，底层服务
   
   + 文件句柄

     linux\Unix 一切皆文件，文件句柄就是一个索引

   + 设置方式 

     系统全局性修改、用户局部性修改、进程局部性修改

   + CPU的亲和

     把进程通常不会在处理器之间频繁迁移进程迁移的频率小，减少性能损耗。

     ```py
     #查看cpu数
     cat /proc/cpuinfo | grep "physical id"|sort|uniq|wc -l
     #查看cpu核心数
     cat /proc/cpuinfo | grep "cpu cores" | uniq

     #nginx.conf配置文件下配置
     worker_processes 16; # 16为总的cpu核心数
     worker_cpu_affinity 0000000000000001 000000000000010 ...... 1000000000000000;
     #worker_cpu_affinity 101010101010101 010101010101010
     #worker_cpu_affinity auto
     ```
## 基于Nginx架构的安全篇
   
*  常见的恶意行为

   爬虫行为和恶意抓取、资源盗用

   基础防盗链功能 - 目的不让恶意用户能轻易的爬取网站对外数据 

   `secure_link_module` - 对数据安全性提高加密验证和失效性，适合如核心重要的数据  

   `access_module` - 对后台、部分用户服务的数据提供IP防控

*  常见的应用层攻击手段

   后台密码撞库 - 通过猜测密码字典不断对后台系统登录性尝试，获取后台登录密码
   
   方法一、后台登录密码复杂度

   方法二、`access_module` - 对后台提供IP防控

   方法三、预警机制

   + 文件上传漏洞
 
   > http://www.imooc.com/upload/1.jpg/1.php
   
   Nginx将1.jpg作为php代码执行

   ```py
   location ^~ /upload{
       root /opt/app/images;
       if($request_filename ~* (.*)\.php{
           return 403;
       }
   }
   ```

   SQL注入 - 利用未过滤/未审核用户输入的攻击方法，让应用运行本不应该运行的SQL代码
   
   
   

*  Nginx防攻击策略

*  场景：Nginx+LUA的安全waf防火墙




