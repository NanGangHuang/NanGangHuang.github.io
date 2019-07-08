## Redis是什么

*  开源

*  基于键值的存储服务系统

*  支持多种数据结构

*  高性能，功能丰富

## Redis安装

*  `wget http://download.redis.io/releases/redis-3.0.7.tar.gz`

*  `tar -xzf redis-3.0.7.tar.gz`

*  `ln -s redis-3.0.7 redis`

*  `cd redis`

*  `make&&make install`

## Redis可执行文件说明

   `redis-server` --->  Redis服务器  
   `redis-cli`    --->  Redis命令行客户端  
   `redis-benchmark`   --->  Redis性能测试工具  
   `redis-check-aof`   --->  AOF文件修复工具  
   `redis-check-dump`  --->  RDB文件检查工具  
   `redis-sentinel`    --->  Sentinel服务器  

## redis启动

*  `redis-server`

*  验证
   `ps -ef | grep redis` 查看redis进程和端口号

*  动态参数启动Redis
   
   `redis-server --port 6380` redis默认端口为6379

*  配置启动Redis

   `redis-server configPath`，如`redis-server redis.conf`

*  生产环境选着配置启动

*  单机多实例配置文件可以用端口区分开

## Redis客户端连接

*  `redis-cli -h 10.10.79.150 -p 6384`

## Redis常用配置

   `daemonize`  --->  是否是守护进程（no|yes）    
   `port`       --->  Redis对为端口号  
   `logfile`    --->  Redis系统日志  
   `dir`        --->  Redis工作目录  


