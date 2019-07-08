## `Spring`框架中如何使用`Redis`

查看`https://spring.io/projects/spring-data-redis#overview`

## `Redis`五种基本数据类型

   1.字符串(String)  
   2.列表(List)  
   3.集合(Set)  
   4.散列表(Hash)  
   5.有序集合(ZSet)  


## 通用命令

> 完整的`redis`命令列表：`http://redis.io/commands`

*  通用命令

   1.`keys`
   
    ```python
    keys * 
    #遍历所有key
    
    keys [pattern]
    #便历所有key
    ```
    **keys命令一般不再生产环境使用**

   2.`dbsize`

    ```python
    dbsize
    #计算key的总数
    ```

   3.`exists key`
   
    ```python
    exists key
    #检查key是否存在
    ```

   4.del key [key ...]
   
    ```python
    del key 
    #删除指定key-value
    ```

   5.expire key seconds

    ```python
    expire key seconds
    #key在seconds秒后过期

    ttl key
    #查看key剩余的过期时间
    ```

   6.type key 
    ```python
    type key
    #返回key的类型
    #string,hash,list,set,zset
    ```

   | 命令   | 时间复杂度 |
   | ------ | ---------- |
   | keys   | O(n)       |
   | dbsize | O(1)       |
   | del    | O(1)       |
   | exists | O(1)       |
   | expire | O(1)       |
   | type   | O(1)       |

*  数据结构和内部编码

*  单线程架构

##  string


​    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_14-26-40.png)

   ```python
    get key
    #获取key对应的value
      
    set key value
    #设置key-value
      
    del key
    #删除key-value
    
   ```

   ```python
    incr key
    #key自增1，如果key不存在，自增后get(key)=1
    
    decr key
    #key自减1，如果key不存在，自减后get(key)=-1
       
    incrby key k 
    #key自增k,如果key不存在，自增后get(key)=k
    
    decr key k
    #key自减k,如果key不存在，自减后get(key)=-k  
    
   ```


   ```python
    set key value
    #不管key是否存在，都设置
       
    setnx key value
    #key不存在，才设置
       
    set key value xx
    #key存在，才设置
   ```

   ```python
    mget key1 key2 key3 ...
    #批量获取key,原子操作
    
    mset key1 value1 key2 value2 key3 value3
    #批量设置key-value   
   ```

   ```python
    getset key newvalue
    #set key newvalue并返回旧的value
       
    append key value
    #将value追加到旧的value
    
    strlrn key
    #返回字符串的长度（注意中文）
   ```

   ```python
    incrbyfloat key 3.5
    #增加key对应的值3.5
       
    getrange key start end
    #获取字符串指定下标所有值
    
    setrange key index value
    #设置指定下标所有对应的值
   ```

> 字符串总结




| 命令          | 含义                         | 复杂度 |
| ------------- | ---------------------------- | ------ |
| set key value | 设置key-value                | o(1)   |
| get key       | 获取key-value                | o(1)   |
| del key       | 删除key-value                | o(1)   |
| setnx setxx   | 根据key是否存在设置key-value | o(1)   |
| Incx decr     | 计数                         | o(1)   |
| mget mset     | 批量操作key-value            | o(n)   |

## hash

*  哈希健值结构
    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_14-53-00.png)

   field不能相同，value可以相同

    ```python
    hget key field
    #获取hash key对应的field的value

    hset key field value 
    #设置hash key对应field的value

    hdel key field
    #删除hash key对应field的value
    ```

    ```python
    hexists key field
    #判断hash key是否有field

    hlen key
    #获取hash key field的数量
    ```

    ```python
    hmget key field1 field2...fieldN
    #批量获取hash key的一批field对应的值

    hmset key field1 value1 field2 value2.. fieldN valueN
    #批量设置hash key的一批field value
    ```

    ```python
    hgetall key
    #返回hash key对应所有的field和value

    hvals key
    #返回hash key对应所有field的value

    hkeys key
    #返回hash key对应所有field
    ```

## 列表

*  列表结构
   ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_14-26-04.png)
   
   
   
*  特点

   1.有序    
   2.可以重复  
   3.左右两边插入弹出    

*  重要API

   1.增
    ```python
    rpush key value1 value2 ...valueN
    #从列表右端插入值（1-N）个

    lpush key value1 value2 ...valueN
    #从列表左端插入值（1-N）个

    linsert key before | after value newValue
    #在list指定的值前后插入newValue
    ```
   
   2.删
    ```python
    lpop key
    #从列表左侧弹出一个item

    rpop key
    #从列表右侧弹出一个item

    lrem key count value
    #根据count值，从列表中删除所有value相等的项
    （1）count>0,从左到右，删除最多count个value相等的项
    （2）count<0,从右到左，删除最多Math.abs(count)个value相等的项
    （3）count=0,删除所有value相等的项
   

    ltrim key start end
    #按照索引范围修剪列表（删除）
    ```

   3.查
    ```python
    lrange key start end(包含end)
    #获取列表指定索引范围所有item  
   
    lindex key index
    #获取列表指定索引的item
    lindex listkey -1 获取最后一个元素

    llen key
    #获取列表长度
    ```

   4.改
    ```python
    lset key index newValue
    #设置列表指定索引值为newValue
    ```

    ```python
    blpop key timeout
    #lpop阻塞版本，timeout是阻塞超时时间，timeout=0为永不阻塞

    brpop key timeout
    #brpop阻塞版本，timeout是阻塞超时时间，timeout=0为永不阻塞
    ```

    `LRUSH + LPOP = Stack`  
   `LPUSH + RPOP = Queue`  
   ` LPUSH + LTRIM = Capped Collection`  
   ` LPUSH + BRPOP = Message Queue`  

## 集合

*  集合结构

   ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_14-46-12.png)

*  特点

   1.无序  
   2.无重复  
   3.集合间操作  

*  集合内API

    ```python
    sadd key element 
    #向集合key添加element（如果element已经存在，添加失败）

    srem key element
    #将集合key中的element移除掉
    ```

    ```python
    scard user:1:follow = 4 #计算集合大小
    sismember user:1:follow it = 1 (存在) #判断it是否在集合中
    srandmember user:1:follow count = his #从集合中随机挑count个元素
    spop user:1:follow = sports #从集合中随机弹出一个元素
    smembers user:1:follow = music his sports it #获取集合所有元素
    ```

   现有两个集合
   `user:1:follow = it music his sports`  
   `user:2:follow = it news ent sports`     
   
    ```python
    sdiff user:1:follow user:2:follow = music his #差集
    sinter user:1:follow user:2:follow = it sports #交集
    sunion user:1:follow user:2:follow = it music his sports news ent #并集
    sdiff|sinter|suion + store destkey ... #将差集、交集、并集结果保存在destkey中
    ```

   `SADD = Tagging`  
   `SPOP/SRANDMEMBER = Random item`  
   `SADD + SINTER = Social Graph`    

## 有序集合

​    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_15-00-14.png)

​    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_21-24-56)

​    ![](https://nanganghuang.github.io/Redis/img/Snipaste_2019-07-08_21-26-22)


+  “重要”`API`
   1.`zadd`
   
    ```python
    zadd key score element(可以是多对)
    #添加score和element
    ```
   
   2.`zrem`
    ```python
    zrem key element(可以是多个)
    #删除元素
    ```
   
   3.`zscore`
    ```python
    zscore key element
    #返回元素的分数
    ```
   
   4.`zincrby`
    ```python
    zincrby key increScore element
    #增加或减少元素的分数
    ```
   
   5.`zcard`
    ```python
    zcard key
    #返回元素的总个数
    ```
   6.`zrange`
    ```python
    zrang key start end [WITHSCORES]
    #返回指定索引范围内的升序元素【分值】
    ```
   
   7.`zrangebyscore`
    ```python
    zrangebyscore key minScore maxScore
    [WITHSCORES]
    #返回指定分数范围内的升序元素【分值】
    ```
   
   8.`zcount`
    ```python
    zcount key minScore maxScore
    #返回有序集合内在指定分数范围内的个数
    ```
   
   9.`zremrangebyrank`
    ```python
    zremrangbyrank key start end
    #删除指定排名内的升序元素
    ```
   
   10.`zremrangebyscore`
    ```python
    zremrangebyscore key minScore maxScore
    #删除指定分数内的升序元素
    ```
   
+  有序集合总结
   
    
   
   | 操作类型 | 命令                                        |
   | -------- | ------------------------------------------- |
   | 基本操作 | zadd zrem zcad zincrby zscore               |
   | 范围操作 | zrange zrangebyscore zcount zremrangebyrank |
   | 集合操作 | zunionstore zinterstore                     |
   
   