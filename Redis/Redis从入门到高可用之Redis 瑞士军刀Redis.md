## 慢查询

* 生命周期

   ![](https://nanganghuang.github.io/image/Snipaste_2019-05-11_15-09-26.png)

   

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



