## 如何设计一个关系型数据库

![](https://nanganghuang.github.io/SQL/img/8.png)

## 为什么要使用索引

避免全表扫描，提升查询效率

## 什么样的信息能够成为索引

+ 主键、唯一键以及普通键等

## 索引的数据结构

+ 生成索引，建立二叉查找树进行二分查找
+ 生成索引，建立B-Tree结构进行查找
+ 生成索引，建立B+-Tree结构进行查找
+ 生成索引，建立Hash结构进行查找

#### B-Tree
定义
+ 根节点至少包括两个孩子
+ 树中每个节点最多含有m个孩子（m>=2）
+ 除根节点和叶节点外，其他每个节点至少有ceil(m/2)个孩子
+ 所有叶子节点都位于同一层
+ 假设每个非终端节点中包含n个关键字的信息，其中  
    a)Ki(i=1...n)为关键字，且关键字按顺序升序排序K(i-1)<ki  
    b)关键字的个数n必须满足，[ceil(m/2)-1]<=n<=m-1  
    c)非叶子节点的指针：P[1],P[2],....P[M];其中P[1]指向关键字小于K[1]的子树<br>
      P[M]指向的关键字大于K[M-1]的子树，其他P[i]指向关键字属于（K[i-1],K[i]）的子树
     

## 密集索引和稀疏索引的区别

## 如何定位并优化慢查询SQL

+ 根据慢日志定位慢查询SQL

```mysql
show variables like '%quer%'; # 查询变量
# long_query_time  10s 超过以下时间是会被记录到慢查询日志中
# show_query_log OFF 是否打开慢查询日志 默认关闭
# show_query_log_file /use/local/mysql/data/XX.log 慢查询日志地址

show status like '%show_queries%'; # 查询慢查询的sql条数
# show_queries 0

set GLOBAL show_query_log = on;
# 打开慢查询日志
set long_query_time = 1; 
```

+ 使用explain等工具分析sql

```mysql
explain select name from person_info_large order by name desc;

# result 
```

**explain关键字段**  

(1).type
system>const>eq_ref>ref>fulltext>ref_or_null>index_merge>
unique_subquery>index_subquery>rang>index>all  

注：type为all时为全表扫描

(2).extra  

extra中出现以下2项意味着MYSQL根本不能使用索引，效率会受到重大影响。应尽可能对此进行优化

|extra项|说明|
|----|----|
|Using filesort|表示MYSQL会对结果使用一个外部索引排序,而不是从表里按索引次序读到相关内容。<br>可能在内存或者磁盘上进行排序。MYSQL中无法利用索引完成的排序操作称为"文件排序"|
|Using temporary|表示MySql在对查询结果排序时使用临时表。常见于排序order by和分组查询group by.|

+ 修改sql，尽量让sql走索引

## 联合索引的最左测匹配原则成因

## 索引是建立越多越好吗 

## MyISAM与InnoDB关于锁方面的区别是什么

+ MyISAM默认用的是表级锁，不支持行级锁
+ InnoDB默认是行级锁，也支持表级锁

#### MyISAM适合的场景

+ 频繁执行全表count语句
+ 对数据进行增删改的频率不高，查询非常频繁
+ 没有事务

#### InnoDB适合的场景

+ 数据增删改都相当频繁
+ 可靠性要求比较高，要求支持事务

#### 数据库锁的分类

+ 按锁的粒度划分，可分为表级锁，行级锁，页级锁
+ 按锁级别划分，可分为共享锁，排他锁
+ 按加锁方式分，可分为自动锁，显示锁
+ 按操作划分，可分为DML锁，DDL锁
+ 按使用方式分，可分为乐观锁，悲观锁

## 数据库事务的四大特性

ACID    
+ 原子性（Atomic）
+ 一致性（Consistency）
+ 隔离性（Isolation）
+ 持久性（Durability）

## 事务隔离级别以及各级别下的并发访问问题

**事务并发访问引起的问题以及如何避免**
+ 更新丢失 ——— mysql所有事务隔离级别在数据库层面均可避免
+ 脏读 ——— READ-COMMITTED事务隔离级别以上可避免
+ 不可重复读 ——— REPEATABLE-READ事务隔离级别以上可以避免
+ 幻读 ——— SERIALIZABLE事务隔离级别可避免

|事务隔离级别|更新丢失|脏读|不可重复读|幻读|
|---|---|---|---|---|
|未提交读|避免|发生|发生|发生|
|已提交读|避免|避免|发生|发生|
|可重复读|避免|避免|避免|发生|
|串行化|避免|避免|避免|避免|

## InnoDB可重复读隔离级别下如何避免幻读

## RC、RR级别下的InnoDB的非阻塞读如何实现 

## 语法部分