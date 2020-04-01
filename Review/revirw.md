## 你对HashMap有什么认识

1.HashMap底层数据结构是数组+链表  
2.HashMap是无序的  
3.HashMap是线程不安全的，多线程情况下需要使用ConCurrentHashMap或者Collections.synchronizedMap();
4.HashMap初始值大小是16,当达到阈值的时候，就是原来的0.75,容量就加倍
5.ConCurrentHashMap采用分段锁技术，每次只锁一小部分数据，提高了并发性

## 你对ArrayList有什么认识

1.ArrayList底层是数组，初始大小是10，
2.ArrayList是有序的
3.ArrayList是线程不安全的，多线程是使用CopyOnWriteArrayList或者Collections.synchronizedList();
4.CopyOnWriteArrayList采用读写分离的技术，每次写入数据时，复制出一个新的数组，旧的数组可以保证正常
的读，复制完成再将原来的引用指向新的数组。



## 锁的种类有多少

1.公平锁和非非公平锁  
2.自旋锁  
3.可重入锁  
4.读锁和写锁  

## 你对JVM有什么认识

1.jvm内存模型分为堆，方法区，本地方法栈，虚拟机栈，程序计数器，其中堆，和方法去是线程共享的，
本地方法栈，虚拟机栈和程序计数器是线程私有的。
2.其中堆分为新生代与老年代，新生代又分为Eden区，与两个Survivor区，
3.java程序中新创建的对象会放到Eden区，大对象会直接放入老年代中    



## 你对多线程有什么认识

## 你对Redis有什么认识

## 你对Nginx有什么认识

## 你对mysql有什么认识

## Tomcat如何调优

## 请自我介绍一下

## 介绍一下你的项目


