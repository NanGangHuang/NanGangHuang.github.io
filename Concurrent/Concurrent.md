#### 并发编程体验

#### 并发基本概念

并发：多个线程操作相同的资源，保证线程安全，合理使用资源。  
高并发:服务器能同时处理很多请求，提高程序性能。    

## CPU多级缓存

![](https://nanganghuang.github.io/Concurrent/img/4.jpg)

为什么需要CPU cache : CPU的频率太快了，快到主存跟不上，这样在处理器时钟周期内，CPU常常需要等待主存，浪费资源。  
所以cache的出现，是为了缓解CPU和内存之间速度的不匹配问题（结构：CPU->cache->memory）

#### `CPU`多级缓存-缓存一致性（`MESI`）

+ 用于保证多个CPU cache之间缓存共享数据的一致

## `Java`内存模型（`Java Memory Model,JMM`）  

![](https://nanganghuang.github.io/Concurrent/img/5.jpg)

![](https://nanganghuang.github.io/Concurrent/img/6.jpg)

![](https://nanganghuang.github.io/Concurrent/img/7.jpg)

![](https://nanganghuang.github.io/Concurrent/img/8.jpg)

#### Java内存模型 - 同步操作与规则

![](https://nanganghuang.github.io/Concurrent/img/9.jpg)

#### 并发的优势与风险

![](https://nanganghuang.github.io/Concurrent/img/10.jpg)

#### 并发模拟 

+ CountDownLatch
+ Semaphore

#### 线程安全性  

**原子性：**提供了互斥访问，同一时刻只能有一个线程来对它进行操作。  
**可见性：**一个线程对主内存的修改可以及时的被其他线程观察到  
**有序性：**一个线程观察其他线程中指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序。  


#### 原子性-`Atomic`包

![](https://nanganghuang.github.io/Concurrent/img/Snipaste_2019-09-07_14-38-19.png)

#### 原子性 - 锁

1.`synchronized:`依赖JVM  
2.`Lock：`依赖特殊的`CPU`指令，代码实现，`ReentrantLock ` 

#### 原子性 - `synchronized ` 

1.修饰代码块：大括号括起来的代码，作用于**调用的对象 **   
2.修饰方法：整个方法，作用于**调用的对象**  
3.修饰静态方法：整个静态方法，作用于**所有对象**  
4.修饰类：括号括起来的部分，作用于**所有对象 **   

#### 原子性 - 对比

1.`synchronized:`不可中断锁，适合竞争不激烈，可读性好  
2.`Lock:`可中断锁，多样化同步，竞争激烈是能维持常态  
3.`Atomic：`竞争激烈是能维持常态，比`Lock`性能好；只能同步一个值  

#### 可见性

导致共享变量在线程间不可见的原因  
1.线程交叉执行  
2.重排序结合线程交叉执行  
3.共享变量更新后的值没有在工作内存与主存间及时更新  

#### 可见性 - `synchronized`
`JMM`关于`synchronized`的两条规定  
1.线程解锁前，必须把共享变量的最新值刷新到主内存  
2.线程加锁是，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值**（注意，加锁与解锁是同一把锁）**  

#### 可见性 - `volatile`
通过加入**内存屏障**和**禁止重排序**优化来实现  
1.对`volatile`变量写操作时，会在写操作后加入一条`store`屏障指令，将本地内存中的共享变量刷新到主内存  
2.对`volatile`变量读操作时，会在读操作前加入一条`load`屏障指令，从主内存中读取共享变量  

#### 有序性

1.`java`内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影响到多线程并发执行的正确性。  

#### 有序性 - `happens-before`原则

1.程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作  
2.锁定规则：一个`unLock`操作先行发生于后面对同一个锁的`lock`操作  
3.`volatile`变量规则：对于一个变量的写操作先行发生于后面对这个变量的读操作。  
4.传递规则：如果操作`A`先行发生于操作`B`，而操作`B`又先行发生于操作`C`,则可以得出操作A先行发生于操作`C` 
5.线程启动规则：`Thread`对象的`start()`方法先行发生于此线程的每一个动作  
6.线程中断规则：对线程`interrupt()`方法的调用先行发生于被中断线程的代码检测到中断事件的发生。  
7.线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过`Thread.join()`方法结束、`Thread.isAlive()`的返回值手段检测到线程已经终止执行。  
8.对象终结规则：一个对象的初始化完成先行发生于他的`finalize()`方法的开始。 


#### 线程安全性 - 总结

1.原子性：`Atomic`包、`CAS`算法、`synchronized、Lock`  
2.可见性：`synchronized、volatile`  
3.有序性：`happens-before` 

## 发布对象

1.发布对象：使一个对象能够被当前范围之外的代码所使用
2.对象谕(yu)出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见

#### 安全发布对象

1.在静态初始化函数中初始化一个对象引用  
2.将对象的引用保存到volatile类型域或者AtomicReference对象中  
3.将对象的引用保存到某个正确构造对象的final类型域中  
4.将对象的引用保存到一个由锁保护的域中。 

#### 不可变对象

1.不可变对象需要满足的条件
	1.1 对象创建以后其状态就不能修改    
	1.2 对象所有域都是final类型的    
	1.3 对象是正确创建的(在对象创建期间，this引用没有逾出)   
	
+ final关键字：类、方法、变量  
    1.1 修饰类：不能被继承  
    1.2 修饰方法：1.锁定方法不被继承类修改；2、效率  
    1.3 修饰变量：基本数据类型变量、引用类型变量       

## 线程封闭

+ Ad-hoc 线程封闭：程序控制实现，最糟糕，忽略
+ 堆栈封闭：局部变量，无并发问题
+ ThreadLocal线程封闭：特别好的封闭方法



#### 线程不安全类与写法

1.StringBuilder -> StringBuffer  
2.SimpleDateFormat -> JodaTime  
3.ArrayList,HashSet,HashMap等Collections  

#### 现场安全 - 同步容器

+ ArrayList -> Vector,Stack
+ HashMap -> HashTable(key,value不能为null)
+ Collections.synchronizedXXX(List,Set,Map)

#### 线程安全 - 并发容器 J.U.C

+ ArrayList -> CopyOnWriteArrayList
+ HashSet,TreeSet -> CopyOnWriteArraySet,ConcurrentSkipListSet
+ HashMap,TreeMap -> ConcurrentHashMap,ConcurrentSkipListMap

#### AQS 同步主键 (AbstractQueuedSynchronizer)

![](https://nanganghuang.github.io/Concurrent/img/11.jpg)

+ CountDownLatch
+ Semaphore
+ CyclicBarrier
+ ReentrantLock
+ Condition
+ FutureTask

#### CyclicBarrier

#### ReentrantLock 与 锁

+ ReentrantLock(可重入锁)和synchronized区别


#### FutureTask

+ Callable与Runnable接口对比
+ Future接口
+ FutureTask类


#### Fork/Join框架

【9-1】


## 线程池

+ new Thread弊端

    1.1 每次new Thread 新建对象，性能差    
    1.2 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过多系统资源导致死机或者OOM  
    1.3 缺少更多功能，如更多执行，定期执行，线程中断
    
+ 线程池的好处

    2.1 重用存在的线程，减少对象创建，消亡的开销，性能佳  
    2.2 可有效控制最大并发线程数，提高系统支援利用率，同时可以避免过多资源竞争，避免阻塞  
    2.3 提供定时执行、定期执行、单线程、并发数控制等功能。  

#### 线程池 --- ThreadPoolExecutor

+ corePoolSize:核心线程数量
+ maximumPoolSize:线程最大线程数
+ workQueue:阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程中产生重大影响    
+ keepAliveTime:线程没有任务时最多保持多久时间终止  
+ unit:keepAliveTime的时间单位  
+ threadFactory:线程工厂，用来创建线程  
+ rejectHandler：当拒绝处理任务时的策略 

+ execute() : 提交任务，交给线程池执行
+ submit() : 提交任务，能够返回执行结果 execute+Future 
+ shutdown() : 关闭线程池，等待任务都执行完
+ shutdownNow() ； 关闭线程池，不等待任务执行完  
+ getTaskCount() : 线程池一致性和未执行的任务总数
+ getCompletedTaskCount() : 已完成的任务数量
+ getPoolSize() : 线程池当前的线程数量  
+ getActiveCount() : 当前线程池中正在执行任务的线程数量  

#### 线程池 --- Executor框架接口

+ Executors.newCachedThreadPool
+ Executors.newFixedThreadPool
+ Executors.newScheduledThreadPool
+ Executors.newSingleThreadExecutor

#### 线程池 --- 合理配置

+ CPU密集型任务，就需要尽量压榨CPU,参考值可以设为NCPU+1
+ IO密集型任务，参考值可以设置为2*NCPU  

## 死锁

####  死锁 --- 必要条件

+ 互斥条件
+ 请求和保持条件
+ 不剥夺条件
+ 环路等待条件





