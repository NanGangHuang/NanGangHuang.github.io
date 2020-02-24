> 学至慕课网《RabbitMQ消息中间件技术精讲》

## 主流消息中间件介绍

###### ActiveMQ

+ ActiveMQ是Apache出品，最流行的，能力最强劲的开源消息总线，并且它一个完全支持JMS规范的消息中间件。   
+ 其丰富的API，多种集群构建模式使得他成为业界老牌消息中间件，在中小型企业应用广泛！  
+ MQ衡量指标：服务性能，数据存储，集群架构

###### KAFKA

+ Kafka是Linkedln开源的放不是发布-订阅消息系统，目前归属于Apache顶级项目。
Kafka主要特点是基于Pull的模式来处理消息消费，追求高吞吐量，一开始的目的就是用于日志收集和传输。
0.8版本开始支持复制，不支持事务，对消息的重复、丢失、错误没有严格要求，
适合产生大量数据的互联网服务的数据收集业务


###### RocketMQ

+ RocketMQ是阿里开源的消息中间件，目前也已孵化为Apache顶级项目，他是纯Java开发，
具有高吞吐量、高可用性、适合大规模分布式系统应用的特点。RocketMQ思路起源于Kafka,
他对消息的可靠传输及事务性做了优化，目前在阿里集团被广泛应用于交易，充值，流计算，
消息推送，日志流式处理，binglog分发等场景

###### RabbitMQ

+ RabbitMQ是使用Erlang语言开发的开源消息队列系统，基于AMQP协议来实现。
AMQP的主要特征是面向消息、队列、路由（包括点对点和发布/订阅）、可靠性、安全。
AMQP协议更多用在企业系统内，对数据一致性、稳定性和可靠性要求很高的场景，
对性能和吞吐量的要求还在其次


## RabbitMQ核心概念及AMQP协议

###### 初识RabbitMQ

+ RabbitMQ是一个开源的消息代理和队列服务器，用来通过普通协议在完全不同的应用之间共享数据，
RabbitMQ是使用Erlang语言来编写的，并且RabbitMQ是基于AMQP协议的。

+ Erlang语言最初在于交换机领域的架构模式，这样使得RabbitMQ在Broker之间进行数据交互的性能是非常优秀的

+ Erlang的优点：Erlang有着和原生Socket一样的延迟

###### 什么是AMQP高级消息队列协议

+ AMQP全称：Advanced Message Queuing Protocol 

+ AMQP翻译：高级消息队列协议 

+ AMQP定义：是具有现代特征的二进制协议。是一个提供统一消息服务的应用层标准高级消息队列协议，
是应用层协议的一个开放标准，为面向消息的中间件设计。

![](https://nanganghuang.github.io/RabbitMQ/img/Snipaste_2020-02-22_22-40-39.png)

## AMQP核心概念

+ Server:又称Broker,接受客户端的连接，实现AMQP实体服务

+ Connection:连接，应用程序与Broker的网络连接

+ Channel:网络信道，几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。
客户端可建立多个Channel,每个Channel代表一个会话任务。

+ Message:消息，服务器与应用程序之间传送的数据，有Properties和Body组成。Properties可以对消息
进行修饰，比如消息的优先级、延迟等高级特性；Body则就是消息体内容。

+ Virtual host:虚拟地址，用于进行逻辑隔离，最上层的消息路由。一个Virtual Host里面可以有诺干个
Exchange和Queue,同一个Virtual Host里面不能有相同名称的Exchange或Queue

+ Exchange:交换机，接受消息，根据路由键转发消息到绑定的队列

+ Binding :Exchange和Queue之间的虚拟连接，binging中可以包含routing key 

+ Routing key : 一个路由规则，虚拟机可用来他来确定如何路由一个特定消息 

+ Queue:也称为Message Queue，消息队列，保存消息并将它们转发给消费者

## RabbitMQ整体架构是什么样子的？

## RabbitMQ安装与使用

[RabbitMQ安装与使用](https://juejin.im/post/5c454daf518825265c2fedb9)

+ 服务的启动： rabbitmq-server start &

+ 服务的停止：rabbitmqctl stop_app

+ 管理插件： rabbitmq-plugins enable rabbitmq_management

+ 访问地址：[http://192.168.11.76:15672/](http://192.168.11.76:15672/)

## 命令行与管控台-基础操作

+ rabbitmqctl stop_app:关闭应用

+ rabbitmqctl start_app:启动应用

+ rabbitmqctl status:节点状态

+ rabbitmqctl add_user username password:添加用户

+ rabbitmqctl list_users:列出所有用户

+ rabbitmqctl delete_user username :删除用户

+ rabbitmqctl clear_permissions -p vhostpath username :清除用户权限

+ rabbitmqctl list_user_permissions username : 列出用户权限

+ rabbitmqctl change_password username newpassword : 修改密码

+ rabbitmqctl set_permissions -p vhostpath username ".*" ".*" ".*" : 设置用户权限

+ rabbitmqctl add_vhost vhostpath :创建虚拟主机

+ rabbitmqctl list_vhosts : 列出所有虚拟主机

+ rabbitmqctl list_permissions -p vhostpath : 列出虚拟主机上所有权限 

+ rabbitmqctl delete_vhost vhostpath : 删除虚拟主机  

+ rabbitmqctl list_queues: 查看所有队列信息

+ rabbitmqctl -p vhostpath purge_queue blue :清除队列里的消息

+ rabbitmqctl reset: 移除所有数据，要在rabbitmqctl stop_app 之后使用

+ rabbitmqctl join_cluster <clusternode> [ --ram ] : 组成集群命令

+ rabbitmqctl cluster_status : 查看集群状态 

+ rabbitmqctl change_cluster_node_type disc | ram : 修改集群节点的存储形式

+ rabbitmqctl forget_cluster_node [--offline] 忘记节点（摘除节点）

+ rabbitmqctl rename_cluster_node oldnode1 newnode1 [oldnode2] [newnode2...] (修改节点名称)

#### 查看RabbitMQ是否启动

```
[root@iZbp1fdluqx20hcz9r7aejZ ~]# lsof -i:5672
COMMAND   PID     USER   FD   TYPE   DEVICE SIZE/OFF NODE NAME
beam    22530 rabbitmq   48u  IPv6 27530710      0t0  TCP *:amqp (LISTEN)
```

## 急速入门-消息生产与消费

+ ConnectionFactory:获取连接工厂

+ Connection:一个连接

+ Channel:数据通信信道，可发送和接受消息

+ Queue:具体的消息存储队列

+ Producer & Consumer 生产和消费者

## Exchange 交换机

+ Exchange:接受消息，并根据路由键转发消息所绑定的队列

![](https://nanganghuang.github.io/RabbitMQ/img/Snipaste_2020-02-24_15-37-20.png)

#### 交换机属性

+ Name:交换机名称

+ Type:交换机类型direct,topic,fanout,headers

+ Durability:是否需要持久化，true为持久化

+ Auto Delete:当最后一个绑定到Exchange上的队列删除后，自动删除该Exchange

+ Internal:当前Exchange是否用于RabbitMQ内部使用，默认为False 

+ Arguments:扩展参数，用于扩展AMQP协议自制定化使用

#### Direct Exchange

+ 所有发送到Direct Exchange的消息被转发到RouteKey中指定的Queue

注意：Direct模式可以使用RabbitMQ自带的Exchange:default Exchange,所以不需要将Exchange
进行任何绑定(binding)操作，消息传递时，RouteKey必须完全匹配才会被队列接受，否则该消息会被
抛弃。

![](https://nanganghuang.github.io/RabbitMQ/img/Snipaste_2020-02-24_15-49-01.png)

#### Topic Exchange

+ 所有发送到Topic Exchange的消息被转发到所有关心Routekey中指定Topic的Queue上

+ Exchange 将RouteKey和某Topic进行模糊匹配，此时队列需要绑定一个Topic

+ 注意：可以使用通配符进行模糊匹配

``` 
符号 "#" 匹配一个或多个词
符号 "*" 匹配不多不少一个词
例如： "log.#" 能够匹配到"log.info.oa"
       "log.*" 只会匹配到"log.erro"
```

![](https://nanganghuang.github.io/RabbitMQ/img/Snipaste_2020-02-24_16-12-02.png)

