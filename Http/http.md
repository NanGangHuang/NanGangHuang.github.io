## Tcp三次握手，四次挥手



#### Tcp三次握手
> 抓包工具：`wireshark`

在TCP/IP协议中，TCP协议提供可靠的连接服务，采用三次握手建立一个连接  

第一次握手：建立连接时，客户端发送SYN包（syn=j）到服务器，并进入SYN_SEND状态，等待服务器确认；  
第二次握手：服务器收到SYN包，必须确认客户的SYN(ack=j+1),同时自己也发送一个SYN包（syn=k）,即SYN+ACK包，此时服务器进入SYN_RECV状态；  
第三次握手：客户端收到服务器的SYN+ACK包，向服务器发送确认包ACK(ack=k+1),此包发送完毕，客户端和服务器进入ESTABLISHED状态，完成三次握手。  

#### 为什么需要三次握手才能建立连接

为了初始化Sequence Number的初始值

#### TCP Flags

+ URG : 紧急指针标志  
+ ACK : 确认序号标志   
+ PSH : push标志
+ RST : 重置连接标志 
+ SYN : 同步序号，用于建立连接过程  
+ FIN : finish标志，用于释放连接 

#### TCP四次挥手

TCP采用四次挥手来释放连接

第一次挥手：Client发送一个FIN,用来关闭Client到Server的数据传送，Client进入FIN_WAIT_1状态；  
第二次挥手：Server收到FIN后，发送一个ACK到Client,确认序号为收到序号+1(与SYN相同,一个FIN占用一个序号)，Server进入CLOSE_WAIT状态；  
第三次挥手：Server发送一个FIN,用来关闭Server到Client的数据传送，Server进入LAST_ACK状态；  
第四次挥手：Client收到FIN后，Client进入TIME_WAIT状态，接着发送一个ACK给Server,确认序号为收到序号+1，Server进入CLOSED状态，完成四次挥手。  

## 网络协议分层

#### 经典五层模型


#### TCP/IP四层协议

![](https://nanganghuang.github.io/Http/img/6.png)

#### ISO七层协议模型

![](https://nanganghuang.github.io/Http/img/5.png)

#### 传输层
1.向用户提供可靠的端到端的服务  
2.传输层向高层屏蔽了下层数据通信的细节  

#### 应用层
1.为应用软件提供了很多服务  
2.构建于TCP协议之上  
3.屏蔽网络传输相关细节  

## URI,URL,URN

#### URI(Uniform Resource Identifier)统一资源标识符

#### URL(Uniform Resource Locator)统一资源定位器

`http://user:pass@host.com:80/path?query=strin#hash`

#### URN 永久统一资源定位符

#### 三者的区别

## HTTP请求

#### 请求报文格式

![](https://nanganghuang.github.io/Http/img/1.png)

![](https://nanganghuang.github.io/Http/img/4.png)

#### 响应报文格式

![](https://nanganghuang.github.io/Http/img/2.png)

![](https://nanganghuang.github.io/Http/img/3.png)

#### 首部字段

1.通用首部字段

| 首部字段名        | 说明                       |
| ----------------- | -------------------------- |
| Cache-Control     | 控制缓存的行为             |
| Connection        | 逐跳首部、连接的管理       |
| Date              | 创建报文的日期时间         |
| Pragma            | 报文指令                   |
| Trailer           | 报文末端的首部一览         |
| Transfer-Encoding | 指定报文主体的传输编码方式 |
| Upgrade           | 升级为其他协议             |
| Via               | 代理服务器的相关信息       |
| Warning           | 错误通知                   |



2.请求首部字段

| 首部字段名      | 说明                     |
| --------------- | ------------------------ |
| Accept          | 用户代理可处理的媒体类型 |
| Accept-Charset  | 优先的字符集             |
| Accept-Encoding | 优先的内容编码           |
| Accept-Language | 优先的语言（自然语言）   |
| Authorization   | Web认证信息              |
| Expect          | 期待服务器的特定行为     |
| From            | 用户的电子邮箱地址       |
| Host            | 请求资源所在服务器       |
| If-Match        | 比较                     |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |
|                 |                          |



#### GET

#### POST

#### PUT

#### DELETE

#### HTTP CODE

## 状态码

|      | 类别                           | 原因状语                   |
| ---- | ------------------------------ | -------------------------- |
| 1XX  | Informational(信息性状态码)    | 接收的请求正在请求         |
| 2XX  | Success(成功状态码)            | 请求正常处理完毕           |
| 3XX  | Redirection(重定向状态码)      | 需要进行附加操作以完成请求 |
| 4XX  | Client Error(客户端错误状态码) | 服务端无法处理请求         |
| 5XX  | Server Error(服务端错误状态码) | 服务器处理请求出错         |



## TCP与UDP的区别

#### TCP

#### UDP
1.UDP报文结结构  
![](https://nanganghuang.github.io/Http/img/Snipaste_2019-08-17_15-34-53.png)

#### TCP与UDP区别
1.面向连接和无连接  
2.可靠性（强 弱）  
3.有序性（有 无）  
4.速度（快 慢）  
5.量级（重 轻）  

#### TCP





## HTTPS
#### 加密
公钥：在客户端加密
私钥：在服务端解密



【图解HTTP】3-5
【慕课网】 9-5