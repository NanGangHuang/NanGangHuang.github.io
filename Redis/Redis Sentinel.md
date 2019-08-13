### 主从复制高可用

+ 主从复制问题

  1.手动故障转移

  2.写能力和存储能力受限

+ `master` 宕掉故障处理

  ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-18_22-38-34.png)
  
### Redis Sentinel架构

 ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-20_09-58-13.png)

### Redis Sentinel故障转移

​    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-20_10-00-35.png)

   ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-20_10-02-00.png)

### Redis Sentinel 安装与配置

​		![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-20_10-10-17.png)

配置：一个主节点（`master-7000`）和两个从节点（`slave-7001`与`slave-7002`）

三个`Sentinel`节点（sentinel-26379、`sentinel-26380`、`sentinel-26381`）

+ `Redis`主节点配置

> 启动：`redis-server redis-7000.conf`

```python
port 7000
daemonize yes
pidfile /var/run/redis-7000.pid
logfile "7000.log"
dir "/opt/soft/redis/data/"
```

+ `Redis`从节点

> 启动:`redis-server redis-7001.conf`
>
> ​        `redis-server redis-7002.conf`

```python
#slave-1
port 7001
daemonize yes
podfile /var/run/redis-7001.pid
logfile "7001.log"
dir "/opt/soft/redis/data"
slaveof 127.0.0.1 7000

#slave-2
port 7002
daemonize yes
pidfile /var/run/redis-7002.pid
logfile "7002.log"
dir "/opt/soft/redis/data"
slaveof 127.0.0.1 7000
```

+ `sentinel`主要配置

```python
port ${port}
dir "/opt/soft/redis/data/"
logfile "${port}.log"
sentinel monitor mymaster 127.0.0.1 7000 2
sentinel down-after-milliseconds mymaster 30000
sentinel failover-timeout mymaster 180000
```

