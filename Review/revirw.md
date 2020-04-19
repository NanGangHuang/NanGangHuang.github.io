## 你对HashMap有什么认识

1.HashMap底层数据结构是数组+链表  
2.HashMap是无序的  
3.HashMap是线程不安全的，多线程情况下需要使用ConCurrentHashMap或者Collections.synchronizedMap();
4.HashMap初始值大小是16,当达到阈值的时候，就是原来的0.75,容量就加倍
5.ConCurrentHashMap采用分段锁技术，每次只锁一小部分数据，提高了并发性

## 你对ArrayList有什么认识

1.ArrayList底层是数组，初始大小是10，每次扩容为当前容量的一半
2.ArrayList是有序的
3.ArrayList是线程不安全的，多线程是使用CopyOnWriteArrayList或者Collections.synchronizedList();
4.CopyOnWriteArrayList采用读写分离的技术，每次写入数据时，复制出一个新的数组，旧的数组可以保证正常
的读，复制完成再将原来的引用指向新的数组。

## 你对list与set有什么认识


## 锁的种类有多少

1.公平锁和非非公平锁  
2.自旋锁  
3.可重入锁  
4.读锁和写锁  

## 你对JVM有什么认识

1.jvm内存模型分为堆，方法区，本地方法栈，虚拟机栈，程序计数器，其中堆，和方法去是线程共享的，
本地方法栈，虚拟机栈和程序计数器是线程私有的。
2.其中堆分为新生代与老年代，新生代又分为Eden区，与两个Survivor区，Eden区满，即触发Minor GC，老年区满
触发Full GC,或者是直接调用System.gc();
当Survivor From 中的对象存活了15次Minor GC，那么它将被放入老年代（OLD）
3.FullGC为导致什么问题？？？
4.java程序中新创建的对象会放到Eden区，大对象会直接放入老年代中    
5.新生代GC是使用复制算法，老年代GC是使用标记-整理算法



+ 那些对象是需要被清除的，
强引用，弱引用，虚引用，软引用 

+ 什么是复制算法，标记整理算法？


## 你对多线程有什么认识

1.线程有六种状态，初始化，运行，死亡，阻塞，等待，超时等待

初始化===》运行：调用线程的start方法
运行 ===》等待：调用wait/join方法
等待 ===》运行：notify()/notifyAll()

2.线程池有四种创建方式，固定数量，无线数量，一定规则，单个线程池
具体？
newCachedThreadPool,newFixedThreadPool,newScheduledThreadPool,newSingleThreadExecutor
具体的调用,创建线程，放入线程池
```java

```
3.线程创建的方式，继承Thread类，实现Runnable接口，实现Callable接口，并实现其call()方法


## 线程池的核心原理

线程池收到一个新的任务，先判断当前运行的线程是否少于核心线程数量，如果少于，则创建新的线程来执行任务
如果多于，则将任务加入阻塞队列，如果阻塞队列已经满了，并且当前运行的线程少于最大线程数量，则新建一个线程
执行任务，如果大于，则使用拒绝策略。

拒绝策略：直接抛弃，抛异常，还可以自定义拒绝策略

## valatile关键字

1.禁止指令重排序
2.不具备原子性
3.保证内存可见性


## 你对Redis有什么认识

1.五种基本类性及其常用的方法
set:
String:
zset:
list:
hash:

2.Redis很快，主要是由于他存储在内存中

## 如何通过Redis实现分布式锁

SETNX key value :如果key不存在，则创建并赋值

时间复杂度：O(1)
返回值：设置成功，返回1;设置失败，返回0

## 如何使用Redis做异步队列
使用List做队列，RPUSH生产消息，LPOP消费消息
pub/sub:主题订阅者模式
+ 发送至发送消息，订阅者接收消息
+ 订阅者可以订阅任意数量的频道

pub/sub缺点
消息的发布时无状态的，无法保证可达

## Redis如何做持久化

RDB快照（持久化）：保存某个时间点的全量数据快照
save 900 1
save 300 10

SAVE:阻塞Redis的服务进程，直到RDB文件被创建完毕
BGSAVE:Fork出一个子进程来创建RDB文件，不阻塞服务器进程


AOP持久化 备份指令
appendonly yes 


## 你对Nginx有什么认识

负载均衡的几中方式：轮训，hash,urlhash,ip地址

## 你对mysql有什么认识

sql调优？
索引？
存储过程？

1.左连接,右连接,全连接

## mysql索引失效的原因有哪些？

## 数据库根据什么条件去创建索引

## 数据库里面什么是乐观锁，怎么实现？

## mysql里面悲观锁是怎么实现的？

## 你对Spring有什么认识

1.轻量级的web框架
2.@Controller,@Service,@Component,@Autoware

## 你对SpringAop有什么认识

这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程。
1.Aop是面向切面编程，就是把一些系统功能给封装起来了，比如日志，事务等等，当业务代码需要用到日志或者事务的时候
直接切入就可以了。

## 你对SpringIOC有什么认识

Ioc—Inversion of Control，即“控制反转”，这是一种设计思想。
在Java开发中，我们将设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。

1.IOC是控制反转，目的是为了是解耦，降低对象之间的耦合性，你要修改底层对象的时候，尽量不影响其他对象，
Spring的做法是先把对象创建出来，然后通过set注入，注解注入，构造器注入，接口注入等等，你想要的改动某一对象的话，或者传值的话
影响的地方就很小

## 你对SpringMVC有什么认识

## 你对SpringBoot有什么认识

1.简化了大量的bean配置

## Spring,Spring MVC及Spring Boot区别

1.Spring是一种轻量级的开发框架，可以用来帮我们管理对象，包括对象的创建销毁等等。
2.SpringMVC 是一个基于MCV思想开发出来的一套框架，通过Dispatcher Servlet, ModelAndView 和 View Resolver，让应用开发变得很容易。
3.Spring Boot 实现自动配置，降低项目搭建的复杂度,大大减少了配置文件数量



## Tomcat如何调优


## 请自我介绍一下


## 介绍一下你的项目经验



## 项目中遇到的生产问题

## 写一个多线程下的单例模式

```java
package com.huanggang.concurrency;

public class SingletDemo {
    private static volatile SingletDemo singletDemo = null;
    private SingletDemo(){
    }
    public static SingletDemo getInstance(){
        if(singletDemo == null){
            synchronized (SingletDemo.class){
                if(singletDemo == null){
                    singletDemo = new SingletDemo();
                }
            }
        }
        return singletDemo;
    }
    public static void main(String[] args) {
    }
}

```

## 写一下冒泡排序

```java
public class Bubble {

    public static void main(String[] args) {
        int[] array = bubble(new int[]{3, 2, 1, 0});
        for(int i : array){
            System.out.println(i);
        }
    }
    private static int[] bubble( int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++){
                int temp = 0;
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }
}
```


## Session与Cookie的区别

1.Session是存储在服务端，Cookie存储在客户端
2.Cookie个数和大小都有限制，Session没有
3.Cookie由于存放在客户端，所以不安全，考虑到安全情况应该放到服务器中

## String类为什么是final类型的？

为了安全
实现字符串池，节省内存空间，提高效率

## Spring的事务管理简单说一下



## HTTP头里面的内容
 
Content-Length：响应体的长度
Expires：响应过期的日期和时间
Last-Modified ：请求资源的最后修改时间
Set-Cookie ： 设置Http Cookie
Cache-Control:告诉所有的缓存机制是否可以缓存及哪种类型

## 线程中断的方式有几种

interrupt()方法中断正在运行中的线程只会修改中断状态位
正常执行完了
调用线程的stop方法 


## hashCode() 和equals() 区别和作用
