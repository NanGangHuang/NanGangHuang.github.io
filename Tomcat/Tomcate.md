###### Guns


###### Tomcat优化思路

//todo 2.1 


###### 线程池优化
1.`maxConnections`最大连接数
最大连接数受系统内核的影响
	1.1 `ulimit -a` : 查看系统支持的最大连接数    
	     open files 1024 当前系统支持最大的连接数   
    1.2 修改 `vim /etc/security/limits.conf`
        添加 `*      soft     nofiles        65535`  
            `*      hard     nofiles         65535`  

2.`maxThreads`最大线程数


3.`acceptCount`最大排队等待数

配置结果 `server.xml`
```
<Connector port="8080" protocol="HTTP/1.1" maxConnections="3000"
maxThreads="500" acceptCount="500" connectionTimeout="20000" redirectPort="8443" />
```

###### Tomcat内存优化

1.`JVM`优化建议

| 参数                 | 参数作用       | 优化建议            |
| -------------------- | -------------- | ------------------- |
| -server              | 启用Server     | 服务端建议开启      |
| -Xms                 | 最小内存       | 建议与-Xmx相同      |
| -Xmx                 | 最大内存       | 建议到可用内存的80% |
| -XX:MetaspaceSize    | 元空间的初始值 |                     |
| -XX:MaxMetaspaceSize | 元空间最大内存 | 默认无限            |
| -XX:MaxNewSize       | 新生代最大内存 | 默认16M             |


2.配置
tomcat `bin/catalina.sh`

`JAVA_OPTS="-server -Xms128m -Xmx128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -XX:MaxNewSize=32m"`

###### `Gzip`相关设置
1.`compression`-设置开启Gzip压缩
2.`compressableMineType`-压缩类型
3.`compressMinSize`-压缩后输出内容大小

4.配置 `server.xml`

```
<Connector port="8080" protocol="HTTP/1.1" maxConnections="3000"
maxThreads="500" acceptCount="500" connectionTimeout="20000"
compression="true" compressMinSize="2048"
redirectPort="8443" />
```

###### 	`Tomcat`三种模式

**`BIO:`**最稳定最老的一个连接器，使用阻塞形式处理`Request`请求  
**`NIO:`**使用`java`的异步`IO`技术，进行非阻塞形式处理`Request`请求  
**`APR:`**原生c语言编写的非堵塞I/O，目前性能最理想  

###### `APR`安装



###### `Memcached`入门
1.1`Memcached`是一款高性能、分布式的内存对象缓存系统
1.2`Memcached`可以有效分担数据库负载
1.3`Memcached`基于libevent事件实现无阻塞通信