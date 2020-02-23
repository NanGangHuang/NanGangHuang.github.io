# 一、javaEE

## 1、介绍Ajax

**什么是Ajax?**

异步的javascript和xml

使用场景：登陆失败时不跳转页面，注册时提示用户名是否存在，二级联动等等使用场景。

## 2、list集合遍历相关

不要再foreach循环里进行元素的remove/add操作。remove元素请使用Iterator方式，如果是并发操作，需要对Iterator对象加锁。

```java
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
Iterator<String> iterator = list.iterator();
while(iterator.hasNext()){
	String item = iterator.next();
	if(删除元素的条件){
		iterator.remove();
	}
}
```

错误的做法：在遍历集合的时候，没有用迭代器进行增删，会出现错误。

```java
for(String item :list){
	if("1".equals(item)){
        list.remove(item);
	}
}
```

如果把1换成2，将会有什么结果?

## 3、Comparator注意事项

在JDK版本及以上，Comparator实现类要满足如下三个条件，不然Arrays.sort,Collections.sort会报IllegalArgumentException异常。

​	1）x,y的比较结果和y,x的比较结果相反。

​	2）x>y ,y>x,则x>z。

​	3）x=y,则x,y比较结果和y,z比较结果相同。

下列中没有处理相等的情况，实际使用中可能会出现异常；

```java
new Comparator<Student>(){
	@Override
    public int compare(String o1,Studnet 02){
		return o1.getId()>o2.getId()?1:-1;
    }
}
```

## 4、集合初始值问题

HashMap使用HashMap(int initialCapacity)初始化。

initialCapacity = (需要存储的元素个数/负载因子)+1。负载因子（即loaderfactor为0.75）；

## 5、Lambda表达式

使用Lambda表达式可以避免庞大的匿名内部内实现。

我们可以使用匿名类来实例化*Consumer*接口的实现，然后将其作为参数应用于*forEach*方法：

```java
Consumer<String> printConsumer= new Consumer<String>() {
    public void accept(String name) {
        System.out.println(name);
    }
};
names.forEach(printConsumer);
```

使用Lambda表达式实现：

```java
names.forEach(name -> System.out.println(name));
```

也可以使用方法引用来实现遍历：

```java
names.forEach(System.out::println);
```

迭代集合：**任何类型Collection都可迭代  - 列表，集合，队列 等都具有使用forEach的相同语法**

列表：

```java
List<String> names = Arrays.asList("Larry", "Steve", "James");
names.forEach(System.out::println);
```

Set:

```java
Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));
uniqueNames.forEach(System.out::println);
```

queue:

```java
Queue<String> namesQueue = new ArrayDeque<>(Arrays.asList("Larry", "Steve", "James"));
namesQueue.forEach(System.out::println)
```

对于Map遍历：虽然不能进行迭代器迭代，但是他提供了forEach变体，如：

```java
Map<Integer, String> namesMap = new HashMap<>();
namesMap.put(1, "Larry");
namesMap.put(2, "Steve");
namesMap.put(3, "James");
namesMap.forEach((key, value) -> System.out.println(key + " " + value));
```

## 6、利用Set进行元素去重

利用Set元素的唯一特性，可以快速对一个集合进行去重操作，避免使用List的contains方法进行遍历、对比、去重操作。

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
list.add("c");
list.add("a");
Set<String> set = new HashSet<>(list);
System.out.println("list的个数为：" + list.size() + "个");
list.forEach(System.out::println);
System.out.println("set的个数为：" + set.size() + "个");
set.forEach(System.out::println);
```

## 7、使用 ThreadPoolExecutor 创建线程池

```
public ThreadPoolExecutor(int corePoolSize,
						  int maxinumPoolSize,
						  long keepAliveTime,
						  TimeUnit unit,
						  BlockingQueue<Runnable> workQueue,
						  ThreadFactory threadFactory,
						  RejectedExecutionHandler handler){
	 if(corePoolSize<0||
	 		maximumPoolSize<=0 ||
	 		maximumPoolSize<corePoolSize||
	 		keepAliveTime<0)
	 	throw new IllegalArguementException();
if(workQueue == null || threadFactory == null || handler == null)
	throw new NullPointerException();
	this.maximumPoolSize = corePoolSize;
	this.keepAliveTime = unit.toNanos(keepAliveTime);
	this.threadFactory = threadFactory;
	this.handler = handler;
} 
```

### 构造函数参数：

1、corePoolSize 核心线程数大小，当线程数<corePoolSize,会创建线程执行runnable

2、maximumPoolSize最大线程数，当线程数>=corePoolSize的时候，会把runnable放入workQueue中

3、keepAliveTime保持存活时间，当线程数大于corePoolSize的空闲线程能保持的最大时间。

4、unit 时间单位

5、workQueue保存任务的阻塞队列

6、threadFactory 创建线程的工厂

7、handler 拒接策略

### JDK7提供了7个阻塞队列。（也属于并发容器）

1. ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
2. LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
3. PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
4. DelayQueue：一个使用优先级队列实现的无界阻塞队列。
5. SynchronousQueue：一个不存储元素的阻塞队列。
6. LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
7. LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

### 阻塞队列的应用场景

阻塞队列常用于生产者和消费者的场景，生产者是向队列里添加元素的线程，消费者是从队列里取元素的线程。简而言之，阻塞队列是生产者用来存放元素、消费者获取元素的容器。

### 使用 ThreadPoolExecutor 创建线程池的具体代码

```java
package io.ymq.thread.TestThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author yanpenglei
 * @create 2017-10-12 15:39
 **/
public class TestThreadPoolExecutor{
    public static void main(String[] args){
        long currentTimeMillis = System.currentTimeMillis();
        
        //构造一个线程池
        ThreadPoolExecutor threadPool = 
            new ThreadPoolExecutor(5,6,3,TimeUnit.SECONDS,
                         new ArrayBlockingQueue<Runnable>(3));
        for(int i=1;i<=10;i++){
            try{
                String task = "task="+i;
                System.out.println("创建任务并提交到线程池中："+task);
                threadPool.execute(new ThreadPoolTask(task));
                Thread.sleep(100);
            }catch(Exception e){
                e.printStacjTrace();
            }
        }
        try{
            //等待所有线程执行完毕当前任务
            threadPool.shutdown();
            boolean loop = true;
            do{
               loop = !threadPool.awaitTermination(2, 		                          TimeUnit.SECONDS);//等待2秒
            } while (loop);
            if (loop != true) {
                System.out.println("所有线程执行完毕");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("耗时：" + (System.currentTimeMillis() - 	                                                   currentTimeMillis));
        }
    }
}
```

