> 学至《剑指Java面试-offer直通车》

## 谈谈你对java的理解

+ 平台无关性

![](https://nanganghuang.github.io/JVM/img/Snipaste_2020-02-25_10-05-44.png)

+ GC
+ 面向对象
+ 语言特性
+ 类库
+ 异常处理

## JVM如何加载.class文件

![](https://nanganghuang.github.io/JVM/img/Snipaste_2020-02-25_10-21-00.png)

+ Class Loader:依据特定格式，加载class文件到内存

+ Execution Engine：对命令进行解析

+ Native Interface :融合不同开发语言的原生库为java所用

+ Runtime Data Area:JVM内存空间结构模型


## 什么是反射

Java反射机制是在运行状态中，对任意一个类，都能够知道这个类的所有属性和方法；
对任意一个对象，都能够调用他的任意方法和属性；这种动态获取信息以及
动态调用对象的方法的功能称为java语言的反射机制。

## 类从编译到执行的过程

+ 编译器将Robot.java源文件编译为Robot.class字节码文件

+ ClassLoader将字节码转换为JVM中的Class<Robot>对象

+ JVM利用Class<Robot>对象实例化为Robot对象

## 谈谈ClassLoader

ClassLoader在java中有着非常重要的作用，他主要工作在Class装载的加载阶段，其主要作用是
从系统外部获得CLass二进制数据流。它是Java的核心组件，所有的Class都是有ClassLoader进行加载的
ClassLoader负责通过将Class文件里的二进制数据流装载进系统，然后交给Java虚拟机进行连接、
初始化等操作。

## 谈谈类加载器的双亲委派机制

## 为什么使用双亲委派机制去加载类

+ 避免多份同样字节码的加载

## 类的加载方式

+ 隐式加载：new

+ 显示加载：loadClass,forName等

## loadClass和forName的区别

类的装载过程

+ 加载：通过ClassLoader加载class文件字节码，生成Class对象

+ 链接：
    1. 校验：检查加载的class的正确性和安全性
    2. 准备：为类变量分配存储空间并设置类变量初始值
    3. 解析：JVM将常量池内的符号引用转换为直接引用

+ 初始化：执行类变量赋值和静态代码块

#### loadClass和forName的区别

+ Class.forName得到的class是已经初始化完成的

+ Classloder.loadClass得到的class是还没有链接的

## JVM三大性能调优参数 -Xms -Xmx -Xss的含义

+ -Xss:规定了每个线程虚拟机栈（堆栈）的大小

+ -Xms:堆的初始值

+ -Xmx:堆能达到的最大值

## Java内存模型中堆和栈的区别

+ 管理方式：栈自动释放，堆需要GC

+ 空间大小：栈比堆小

+ 碎片相关：栈产生的碎片远小于堆

+ 分配方式：栈支持静态和动态分配，而堆仅支持动态分配








