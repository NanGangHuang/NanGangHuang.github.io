---
layout: post
title: 设计模式的一些理解
date: 2019-02-15 13:56:00
tags: design java
categories: java
excerpt: 主要学习23种设计模式时的笔记
---

# 创建型模式

**创建型模式(`Creational Pattern`)理解：**

> 将软件模块中对象的创建和对象的使用分离。

​	创建型模式隐藏了类实例的创建细节，将一个复杂对象的构建与他的表示分离，使得同样的构建过程可以创建不同的表示。

# 单例模式

**定义:**

> 确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例

**单例模式有两种构建方式：**

> 饿汉式：指全局的单例实例在类装载时构建
>
> 懒汉式：指全局的单例实例在第一次被使用时构建

**饿汉式（线程安全）源码**

```java
public class Singleton {
    //在静态初始化器中创建单例实例，这段代码保证了线程安全
    private static Singleton uniqueInstance = new Singleton();
    //Singleton类只有一个构造方法并且被private修饰的，所以用户无法通过new方法创建
    private Singleton () {};
    public static Singleton getInstance() {
        return uniqueInstance;
    }
```

**懒汉式（非线程安全和`synchronized`关键字修饰的线程安全）版本**

```java
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton () {};
    //没有加入synchronized关键字的版本是线程不安全的
    public static Singleton getInstance() {
        //判断当前单例是否已经存在，若存在则返回，若不存在则再建立单例
        if (unqiueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```

* 线程安全版本（加`synchronized`修饰）

```java
	public static synchronized getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
	}
```

由于每次使用`getInstance()`都要经过`synchronized`加锁这一层，这会增加`getInstance()`的方法的时间消耗，而且还有可能会发生阻塞，下面的双重检查加锁版是为了解决这个问题存在的。

* 双重检查加锁版本

```java
public class Singleton {
    //volatile保证，当uniqueInstance变量被初始化成Singleton实例时，多线程可以正确处理uniqueInstance变量
    private volatitle static Singleton uniqueInstance;
    private Singleton () {};
    public static Singleton getInstance() {
    	//检查实例，如果不存在，就进入同步代码块
        if （uniqueInstance == null）{
            //只有第一次才彻底执行这里的代码
            synchronized(Singleton.class){
                //进入同步代码块后，再检查一次，如果仍是null,才创建实例
                if (uniqueInstance == null){
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```



注：具体参考	`https://github.com/Snailclimb/JavaGuide` 设计模式板块