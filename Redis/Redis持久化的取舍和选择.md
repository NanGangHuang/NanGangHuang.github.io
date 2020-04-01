## 持久化的作用
> `redis`所有数据保存在内存中，对数据的更新将异步地保存到磁盘上。

## `RDB`  
  ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_15-37-57.png)

+  触发机制-主要三种方式  
   1.`save`(同步)  
   2.`bgsave`(异步)  
   3.自动     
   
    
   
   |   命令   |  `save`  |  `bgsave`  |
   | ---- | ---- | ---- |
   |   `IO`类型   |   同步   |  异步    |
   |   阻塞？   |   是   |    是  |
   |  复杂度    |   `O(n)`   |   `O(n)`   |
   |   优点   |    不会消耗额外内存  |  不阻塞客户端命令    |
   |   缺点   |   阻塞客户端命令   |   需要fork,消耗内存   |
   
 ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_15-51-27.png)
   
+  `RDB`总结  
   1.`RDB`是`Redis`内存到硬盘的快照，用于持久化。  
   2.`save`通常会阻塞`Redis`。  
   3.`bgsave`不会阻塞`Redis`,但是会`fork`新进程    
## `AOF`

   ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_16-20-17.png)
    
   ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_16-21-38.png)

+  `AOF`三种策略  
   1.`always`  
    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_16-25-31.png)  
   2.`everysec`  
    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_16-26-39.png)  
   3.`no`  
    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_16-28-16.png)      
   
   |   命令   |  `always`    |  `everysec`    |   `no`   |
   | ---- | ---- | ---- | ---- |
   |   优点   |  不丢失数据    |   每秒一次`fsync`丢一秒数据   | 不用管     |
   |  缺点    |  `IO`开销较大，一般的`sata`盘只有几百`TPS`    |    丢一秒数据  |   不可控   |
   
+  `AOF`重写
    1.`AOF`重写作用  
      1.1 减少硬盘占用量  
      2.2 加速恢复速度    
    
+  `AOF`重写实现两种方式  
   1.`bgrewriteaof`  
    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-07_17-05-55.png) 
   
   2.`AOF`重写配置    
   
    2.1 配置      
   
   |   配置名   |   含义   |
   | ---- | ---- |
   |   `auto-aof-rewrite-min-size`   |   `AOF`文件重写需要的尺寸   |
   |   `auto-aof-rewrite-percentage`   |   `AOF`文件增长率   |
   
    2.2 统计    
   
   |   统计名   |   含义   |
   | ---- | ---- |
   |   `aof_current_size`   |   `AOF`当前尺寸(单位：字节)   |
   |   `aof_base_size`   |   `AOF`上次启动和重写的尺寸(单位：字节)   |
   
   自动触发时机（同时满足）  
   (1)`aof_current_size>auto-aof-rewrite-min-size  `  
   (2)`aof_current_size-aof_base_size/aof_base_size>auto-aof-rewrite=percentage`    

    2.3配置  
    ```python
    appendonly yes
    appendfilename "appendonly-${port}.aof"
    appendfsync everysec
    dir /bigdiskpath
    no-appendfsync-on-rewrite yes
    auto-aof-rewrite-percentage 100
    autp-aof-rewrite-min-size 64mb
    ```
   
## `RDB`和`AOF`

|命令|`RDB`|`AOF`|
|----|----|----|
|启动优先级|低|高|
|体积|小|大|
|恢复速度|快|慢|
|数据安全性|丢数据|根据策略决定|
|轻重|重|轻|

+  `RDB`最佳策略  
   1."关"  
   2.集中管理  
   3.主从，从开？  
   
+  `AOF`最佳策略  
   1."开"：缓存和存储  
   2.`AOF`重写集中管理  
   3.`everysec ` 
   
+  最佳策略  
   1.小分片  
   2.缓存或者存储  
   3.监控(硬盘、内存、负载、网络)  
   4.足够的内存    

