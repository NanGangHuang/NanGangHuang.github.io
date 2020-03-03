## 慢查询

* 生命周期

   ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-05-11_15-09-26.png)

   

 > 两点说明：   

   1.慢查询发生在第三阶段  
   2.客户端超时不一定慢查询，但慢查询是客户端超时的一个可能因素

*  慢查询两个配置 - `slowlog-max-len`
   
   1.先进先出队列
   
   2.固定长度
   
   3.保存在内存中
   
   
   
*  慢查询两个配置 - `slowlog-log-slower-than`
   
   1.慢查询阈值（单位：微秒）
   
   2.`showlog-log-slower-than`=0,记录所有命令
   
   3.`showlog-log-slower-than`<0,不记录所有命令
   
 > 配置方法：

   1.默认值

   ```xml
   config get slowlog-max-len = 128
   config get slowlog-log-slower-than = 10000
   ```

   2.修改配置文件重启

   3.动态配置

   ```xml
   config get slowlog-max-len 1000
   config get slowlog-log-slower-than 1000
   ```

 > 慢查询命令：

   1.`showlog get [n]` : 获取慢查询队列(n代表获取的条数)

   2.`showlog len` : 获取慢查询队列长度

   3.`showlog reset` : 清空慢查询队列

## pipeline

+ `pipeline-Jedis`实现（`java`实现`pipeline`）

   1.引入`jedis maven`依赖

    ```xml
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.0</version>
    </dependency>
    ```

   2.没有`pipeline`

    ```java
    Jedis jedis = new Jedis("127.0.0.1",6379);
    for(int i=0;i<10000;i++){
        jedis.hset("hashkey:"+i,"field"+i,"value"+i);
    }
    ```

		消耗60s

   3.使用`pipeline`

    ```java
    Jedis jedis = new Jedis("127.0.0.1",6379);
    for(int i=0;i<100;i++){
        Pineline pipeline = jedis.pipelined();
        for(int j=i*100;j<(i+1)*100;j++){
            pipeline.hset("hashkey:"+j,"field"+j,"value"+j);
        }
        pipeline.syncAndReturnAll();
    }
    ```

​				消耗0.7秒

+  使用建议

   1.注意每次`pineline`携带数据量

   2.`pipeline`每次只能作用在一个`Redis`节点上

   3.`M`操作与`pipeline`区别

  

  

## 发布订阅

   角色

   1.发布者（`publisher`）

   2.订阅者（`subscriber`）

   3.频道（`channel`）

+  模型

  ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-06_16-49-04.png)

+  `API`

   1.`publish`(发布命令)

      ```java
      publish channel message
      ```

      ```python
      # 演示
      redis> publish sohu:tv "hello world"
      (inteer) 3 #订阅者个数
      ```

   2.`subscribe`(订阅)

    ```python
    subscribe [channel] #一个或多个
    ```

    ```python
    # 演示
    redis> subscribe sohu:tv
    1)"subscribe"
    2)"sohu:tv"
    3)(integer)1
    1)"message"
    2)"sohu:tv"
    3)"hello world"
    ```

   3.`unsubcribe`(取消订阅)

    ```python
    unsubscribe [channel] #一个或多个
    ```

    ```python
    #演示
    redis>unsubscribe sohu:tv
    1)"unsubscribe"
    2)"sohu:tv"
    3)(integer)0
    ```

   4.其他`API`

    ```python
    1 psubscribe [pattern...] #订阅模式。
    2 punsubscribe [pattern...] #退出指定的模式
    3 pubsub channels #列出至少有一个订阅者的频道
    4 pubsub numsub [channel...] #列出给定频道的订阅者数量
    ```

+  消息队列

  ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-06_17-06-18.png)

  注：消息队列是只有一个人能够接受到消息

+  发布订阅总结

   1.发布订阅模式中的角色

   2.重要`API`

   3.消息队列和发布订阅

## Bitmap

+  位图

   1.`setbit`

    ```python
    setbit key offset value
    #给位图指定索引设置值
    ```

    ```python
    127.0.0.1:6379 > setbit unique:users:2016-04-05 0 1
    (integer) 0
    127.0.0.1:6379 > setbit unique:users:2016-04-05 5 1
    (integer) 0
    127.0.0.1:6379 > setbit unique:users:2016-04-05 11 1
    ```

   2.`getbit`

    ```python
    getbit key offset
    #获取位图指定索引的值
    ```

    ```python
    127.0.0.1:6379 > getbit unique:users:2016-04-05 8
    (integer) 0
    127.0.0.1:6379 > getbit unique:users:2016-04-05 19
    (integer) 1
    ```

   3.`bitcount`

    ```python
    bitcount key [start end]
    #获取位图指定范围（start到end,单位为字节，如果不指定就是获取全部）     位值为1的个数
    ```

    ```python
    127.0.0.1:6379>bitcount unique:users:and:2016_04_05
    (integer) 5
    127.0.0.1:6379>bitcount unique:users:and:2016_04_05 1 3
    (integer) 3
    ```

   4.`bitop`

    ```python
    bitop op destkey key [key...]
    #做多个Bitmap的and(交集)、or(并集)、not(非)、xor(异或)操作并将     结果保存在destkey中
    ```

    ```python
    #求两个位图的并集
    127.0.0.1:6379>bitop and unique:users:and:2016_04_04-   2016_04_05 unique:users:2016-04-05 unique:users:2016-04-04
    (integer) 3
    127.0.0.1:6379>bitcount unique:users:and:2016_04_04-   2016_04_05
    ```

   5.`bitpos`

    ```python
    bitpos key targetBit [start] [end]
    #计算位图指定范围（start到end,单位为字节，如果不指定就是获取全部） 第一个偏移量对应的值等于targetBit的位置
    ```

    ```python
    127.0.0.1:6379>bitops unique:users:and:2016_04_04 1
    (integer) 1
    127.0.0.1:6379>bitop unique:users:and:2016_04_04 0 1 2
    (integer) 8
    ```

  

  > 独立用户统计

  ​	(1) 使用`set`和`Bitmap`

  ​	(2) 1亿用户，5千万独立

| 数据类型 | 每个`userid`占用空间 | 需要存储的用户量 | 全部内存量 |
| -------- | -------------------- | ---------------- | ---------- |
| `set`    | 32位                 | 50,000,000       | 200`MB`    |
| `Bitmap` | 1位                  | 100,000,000      | 12.5`MB`   |

|          | 一天    | 一个月 | 一年   |
| -------- | ------- | ------ | ------ |
| set      | 200`M`  | 6`G`   | 72`G`  |
| `Bitmap` | 12.5`M` | 375`M` | 4.5`G` |

  ​		(3)只有10万独立用户

| 数据类型 | 每个`userid`占用空间 | 需要存储的用户量 | 全部内存量 |
| -------- | -------------------- | ---------------- | ---------- |
| `set`    | 32位                 | 1,000,000        | 4`MB`      |
| `Bitmap` | 1位                  | 100,000,000      | 12.5`MB`   |

+  使用经验

   1.`type` = `string`,最大512`MB`

   2.注意`setbit`时的偏移量，可能有较大耗时

   3.位图不是绝对好

## `HyperLogLog`

> 基于`HyperLogLog`算法：极小空间完成独立数量统计
> 本质还是字符串

    ```python
    127.0.0.1:6379> type hyperloglog_key
    string
    ```
+  三个命令
   1.`pfadd key element [element ...]`: 向`hyperloglog`添加元素
   2.`pfcount key [key ...]`: 计算`hyperloglog`的独立总数
   3.`pfmerge destkey sourcekey [sourcekey ...]`:合并多个`hyperloglog`
    ```python
    redis> pfadd 2017_03_06:unique:ids "uuid-1" "uuid-2" "uuid-3" "uuid-4"
    (integer) 1
    redis> pfcount 2017_03_06:unique:ids
    (integer) 4
    redis> pfadd 2017_03_06:unique:ids "uuid-1" "uuid-2" "uuid-3" "uuid-90"
    (integer) 1
    redis> pfcount 2017_03_06:unique:ids
    (integer) 5
    #合并多个
    redis> pfadd 2016_03_06:unique:ids "uuid-1" "uuid-2" "uuid-3" "uuid-4"
    (integer) 1
    redis> pfcount 2016_03_06:unique:ids
    (integer) 4
    redis> pfadd 2016_03_05:unique:ids "uuid-4" "uuid-5" "uuid-6" "uuid-7"
    (integer) 1
    redis> pfcount 2016_03_05:unique:ids
    (integer) 4
    redis> pfmerge 2016_03_06:unique:ids 2016_03_05:unique:ids
    ok
    redis> pfcount 2016_03_06:unique:ids
    (integer) 7
    
    ```
+  内存消耗（百万独立用户）
    ```python
    elements=""
    key="2016_05_01:unique:ids"
    for i in `seq 1 1000000`
    do
      elements = "${elements}" uuis-"${i}"
      if[[$((i%1000)) == 0]]
      then
        redis-cli pfadd ${key} ${elements}
        elements = ""
      fi
    done
    ```
|       | 内存消耗         |
| ----- | ---------------- |
| 1天   | 15`KB`           |
| 1个月 | 450`KB`          |
| 1年   | 15`KB`*365=`5MB` |

+  使用经验
   1.是否能容忍错误？（错误率：0.81%）
    ```python
    127.0.0.1:6379> pfcunt 2016_05_01:inoque:ids(integer)
    1009838
    ```
   2.是否需要单条数据？
   

## `GEO`

> GEO(地理信息定位)：存储经纬度，计算两地距离，范围计算等

+  `API`
   1.`geoadd`
    ```python
    geo key longitude latitude member
    [longitude latitude member...]
    #增加地理位置信息
    ```
    ```python
    127.0.0.1:6379> geoadd cities:locations 116.28 39.55 beijin
    (integer) 1
    127.0.0.1:6379> geoadd cities:locations 116.28 39.55 tianjin 114.29 38.02 shijiazhuang 118.01 39.38 tanshan 115.29 38.51 baoding
    (integer) 4
    ```
   2.`geopos`
    ```python
    geopos key member [member ...]
    #获取地理位置信息
    ```
    ```python
    127.0.0.1:6379> geopos cities:locations tianjin
    1) 1)"117.12000042200088501"
       2)"29.0800000535766543"
    ```
   3.`geodist`
    ```python
    geodist key member1 member2 [unit]
    #获取两个地理位置的距离
    #unit:m(米)、km(千米)、mi(英里)、ft(尺)
    ```
    ```python
    127.0.0.1:6379> geolist cities:locations tianjin beijing km
    "89.2061"
    ```
   4.`georadius`
+  相关说明
   1.since 3.2+
   2.type geoKey = zset
   3.没有删除API:zrem key member