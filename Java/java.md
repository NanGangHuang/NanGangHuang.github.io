# 一、Java基础

## 1、什么是 JDK？什么是 JRE？什么是 JVM？三者之间的联系与区别

**JVM:**java虚拟机，加载.class并运行.class。

**JRE:**java运行环境， 除了包含JVM以外还包含了运行java程序所必须的环境，JRE=JVM+java系统类库(小零件)。

**JDK:**java开发工具包，除了包含JRE以外还包含了开发java程序所必须的命令工具,JDK=JRE+编译、运行等命令工具。

**区别与联系：**

1. JDK 用于开发，JRE 用于运行java程序 ；
2. JDK 和 JRE 中都包含 JVM ；
3. JVM 是 java 编程语言的核心并且具有平台独立性。

## 2、请简述java编译运行过程

Java编译运行过程包含编译期和运行期

**编译期:**.java源文件，经过编译，生成.class字节码文件

**运行期:**JVM加载.class并运行.class

## 3、重载和重写的区别

**重载：** 发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同，发生在编译时。 　　

**重写：**   发生在父子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类；如果父类方法访问修饰符为 private 则子类就不能重写该方法。

## 4、Java 面向对象编程三大特性:封装、继承、多态

### 1.1封装

**1)类:**封装的是对象的属性和行为

**2)方法:**封装的是一段特定的业务逻辑功能

**3)访问控制修饰符:**封装的是具体访问的权限

### 1.2继承

 **1)作用:**代码复用

 **2)超类:**所有派生类所共有的属性和行为

​    **接口:**部分派生类所共有的行为

​    **派生类:**派生类所特有的属性和行为

**3)**传递性，单一继承、多接口实现

### 1.3多态

  **1)意义:**行为的多态(所有抽象方法都是多态的)

​             对象的多态(所有对象都是多态的)

  **2)**向上造型、强制类型转换、instanceof判断

  **3)多态的表现形式:**

​    3.1)重写:根据对象的不同来实现多态

​    3.2)重载:根据参数的不同来实现多态

## 5、阐述静态变量和实例变量的区别。

静态变量是被static修饰符修饰的变量，也称为类变量，它属于类，不属于类的任何一个对象，一个类不管创建多少个对象，静态变量在内存中有且仅有一个拷贝；实例变量必须依存于某一实例，需要先创建对象然后通过对象才能访问到它。静态变量可以实现让多个对象共享内存。

补充：在Java开发中，上下文类和工具类中通常会有大量的静态成员。

## 6、接口和抽象类的区别是什么？

1. 接口的方法默认是 public，所有方法在接口中不能有实现，抽象类可以有非抽象的方法
2. 接口中的实例变量默认是 final 类型的，而抽象类中则不一定 
3. 一个类可以实现多个接口，但最多只能实现一个抽象类 
4. 一个类实现接口的话要实现接口的所有方法，而抽象类不一定 
5. 接口不能用 new 实例化，但可以声明，但是必须引用一个实现该接口的对象 从设计层面来说，抽象是对类的抽象，是一种模板设计，接口是行为的抽象，是一种行为的规范。

## 7、成员变量与局部变量的区别有那些？

## 8、内存管理

**java方法参数传递规则：**Java只有一种参数传递规则，只按照值传递参数，也就是将变量的值复制一份。

```java
public static void main(String[] args){
    int n = 5;
    int[] arr = {5};
    add(5);
    add(arr);
    System.out.println(n);
    System.out.println(arr[0]);
}
public static void add(int n){
    n++;
}
public static void add(int[] n){
    n[0]++;
}
```

## 9、super与this的区别

**super的用法:**

1)super.成员变量名-------------访问超类的成员变量。

2)super.方法名()-----------------调用超类的方法。

3)super()---------------------------调用超类的构造方法。

**this:**指代当前对象，哪个对象调用方法它指的就是哪个对象只能用在方法中，方法中访问成员变量之前默认有个this.

**this的用法:**

1)this.成员变量名--------------访问成员变量

2)this.方法名()------------------调用方法(一般不用)

3)this()----------------------------调用构造方法

# 二、Java Core

## 1、String、StringBuffer、StringBuilder 的区别是什么？String 为什么是不可变的

**可变性**

String 对象是不可变的,StringBuffer、StringBuilder是可变的。

**线程安全性**

String 中的对象是不可变的，也就可以理解为常量，线程安全。StringBuffer 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。

**性能**

每次对 String 类型进行改变的时候，都会生成一个新的 String 对象，然后将指针指向新的 String 对象。StringBuffer 每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 StirngBuilder 相比使用 StringBuffer 仅能获得 10%~15% 左右的性能提升，但却要冒多线程不安全的风险

**对于三者使用的总结：** 
如果要操作少量的数据用 = String
单线程操作字符串缓冲区 下操作大量数据 = StringBuilder
多线程操作字符串缓冲区 下操作大量数据 = StringBuffer

## 2、说说反射的用途及实现

反射机制是Java语言中一个非常重要的特性，它允许程序在运行时进行自我检查，同时也允许对其内部成员进行操作。

反射机制提供的功能主要有：得到一个对象所属的类；获取一个类的所有成员变量和方法；在运行时创建对象；在运行时调用对象的方法；

## 3、Collection和Collections的区别？

Collection是一个接口，它是Set、List等容器的父接口；Collections是个一个工具类，提供了一系列的静态方法来辅助容器操作，这些方法包括对容器的搜索、排序、线程安全化等等。

## 4、<font face="楷体">comparable 和 comparator的区别</font>

- comparable接口实际上是出自java.lang包 它有一个 compareTo(Object obj)方法用来排序
- comparator接口实际上是出自 java.util 包它有一个compare(Object obj1, Object obj2)方法用来排序

一般我们需要对一个集合使用自定义排序时，我们就要重写compareTo方法或compare方法，当我们需要对某一个集合实现两种排序方式，比如一个song对象中的歌名和歌手名分别采用一种排序方法的话，我们可以重写compareTo方法和使用自制的Comparator方法或者以两个Comparator来实现歌名排序和歌星名排序，第二种代表我们只能使用两个参数版的Collections.sort().

## 5、Arraylist 与 LinkedList 异同

- **1. 是否保证线程安全：** ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；

- **2. 底层数据结构：** Arraylist 底层使用的是Object数组；LinkedList 底层使用的是双向循环链表数据结构；

- **3. 插入和删除是否受元素位置的影响：** ① **ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。** 比如：执行`add(E e)  `方法的时候， ArrayList 会默认在将指定的元素追加到此列表的末尾，这种情况时间复杂度就是O(1)。但是如果要在指定位置 i 插入和删除元素的话（`add(int index, E element) `）时间复杂度就为 O(n-i)。因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的(n-i)个元素都要执行向后位/向前移一位的操作。 ② **LinkedList 采用链表存储，所以插入，删除元素时间复杂度不受元素位置的影响，都是近似 O（1）而数组为近似 O（n）。**

- **4. 是否支持快速随机访问：** LinkedList 不支持高效的随机元素访问，而ArrayList 实现了RandmoAccess 接口，所以有随机访问功能。快速随机访问就是通过元素的序号快速获取元素对象(对应于`get(int index) `方法)。

- **5. 内存空间占用：** ArrayList的空 间浪费主要体现在在list列表的结尾会预留一定的容量空间，而LinkedList的空间花费则体现在它的每一个元素都需要消耗比ArrayList更多的空间（因为要存放直接后继和直接前驱以及数据）。 

  ## 6、synchronized关键字的用法？

  synchronized关键字可以将对象或者方法标记为同步，以实现对对象和方法的互斥访问，可以用synchronized(对象) { … }定义同步代码块，或者在声明方法时将synchronized作为方法的修饰符。

  ## 7、线程的基本状态以及状态之间的关系

  ![](1.png)

**说明：**其中Running表示运行状态，Runnable表示就绪状态（万事俱备，只欠CPU），Blocked表示阻塞状态，阻塞状态又有多种情况，可能是因为调用wait()方法进入**等待池**，也可能是执行同步方法或同步代码块进入**等锁池**，或者是调用了sleep()方法或join()方法等待休眠或其他线程结束，或是因为发生了I/O中断。

**interrupt()**

​    线程的thread.interrupt()方法是中断线程，将会设置该线程的中断状态位，即设置为true。

中断的结果线程是死亡、还是等待新的任务或是继续运行至下一步，就取决于这个程序本身。

**Thread.yield( )**方法

使当前线程从执行状态（运行状态）变为可执行态（就绪状态）,,

##  8、sleep()方法和wait()方法简单对比

- 两者最主要的区别在于：**sleep方法没有释放锁，而wait方法释放了锁** 。 
- 两者都可以暂停线程的执行。
- Wait通常被用于线程间交互/通信，sleep通常被用于暂停执行。
- wait()方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的notify()或者notifyAll()方法。sleep()方法执行完成后，线程会自动苏醒。

# 三、设计模式

## 1、设计模式:23种

模式：解决特定问题的固定编程套路。使用时候只需要按照模式进行套用即可。

单例模式：解决在一个软件中特定对象的实例永远只有一个固定编程套路。

单例：一个软件中对象的实例永远只有一个。

饿汉式 和 懒汉式

饿汉式：尽早立即创建对象，类一旦加载就创建对象

​	-使用对象时候没有延迟

懒汉式：

#### 单例模式

```java
//饿汉式
private static Girl instance = new Girl();
public static Girl getInstance(){
    return insatnce;
}
private Girl(){
    
}
```

```java
//懒汉式
private static Boy instance;
private Boy(){
    
}
public synchronized static Boy getInstance(){
    if(instance == null){
        instance = new Boy();
    }
    return instance;
}
```



## 2、简述一下你了解的设计模式。

所谓设计模式，就是一套被反复使用的代码设计经验的总结（情境中一个问题经过证实的一个解决方案）。使用设计模式是为了可重用代码、让代码更容易被他人理解、保证代码可靠性。设计模式使人们可以更加简单方便的复用成功的设计和体系结构。将已证实的技术表述成设计模式也会使新系统开发者更加容易理解其设计思路。

在GoF的《Design Patterns: Elements of Reusable Object-Oriented Software》中给出了三类:

- 创建型[对类的实例化过程的抽象化]
- 结构型[描述如何将类或对象结合在一起形成更大的结构]
- 行为型[对在不同的对象之间划分责任和算法的抽象化]

共23种设计模式，包括：

- Abstract Factory（抽象工厂模式）
- Builder（建造者模式）
- Factory Method（工厂方法模式）
- Prototype（原始模型模式）
- Singleton（单例模式）
- Facade（门面模式）
- Adapter（适配器模式）
- Bridge（桥梁模式）
- Composite（合成模式）
- Decorator（装饰模式）
- Flyweight（享元模式）
- Proxy（代理模式）
- Command（命令模式）
- Interpreter（解释器模式）
- Visitor（访问者模式）
- Iterator（迭代子模式）
- Mediator（调停者模式）
- Memento（备忘录模式）
- Observer（观察者模式）
- State（状态模式）
- Strategy（策略模式）
- Template Method（模板方法模式）
- Chain Of Responsibility（责任链模式）

面试被问到关于设计模式的知识时，可以拣最常用的作答，例如：

- 工厂模式：工厂类可以根据条件生成不同的子类实例，这些子类有一个公共的抽象父类并且实现了相同的方法，但是这些方法针对不同的数据进行了不同的操作（多态方法）。当得到子类的实例后，开发人员可以调用基类中的方法而不必考虑到底返回的是哪一个子类的实例。
- 代理模式：给一个对象提供一个代理对象，并由代理对象控制原对象的引用。实际开发中，按照使用目的的不同，代理可以分为：远程代理、虚拟代理、保护代理、Cache代理、防火墙代理、同步化代理、智能引用代理。
- 适配器模式：把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起使用的类能够一起工作。
- 模板方法模式：提供一个抽象类，将部分逻辑以具体方法或构造器的形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。不同的子类可以以不同的方式实现这些抽象方法（多态实现），从而实现不同的业务逻辑。

# 四、Web前端

## 1、如何在前端每三秒刷新一次

使用定时器，var id = setInterval(函数,3000);//每隔3秒执行函数。

​			setTimeout(函数,2000);//两秒后执行函数。

注：clearInterval(id);//停止定时器

## 2、jQuery的常用选择器？

ID选择器、Class选择器、标签选择器、任意选择器、属性选择器，组合选择器，层次选择器

## 3、jQuery的页面加载完毕事件？

第一种：

```java
$(document).ready(function(){
});
//$(document)把原生的document这个dom对象转换为jQuery对象，转换完成之后才能调用readey方法
//ready(fn),表示的是页面结构被加载完毕后执行传入函数fn。
```

第二种：

```java
$(function(){
});
//当页面加载完毕后执行里面的函数
```

window.onload的区别

1、jQuery中的页面加载完毕事件，表示的是页面结构被加载完毕。

2、window.onload 表示的是页面被加载完毕。

```tex
<img src ="http://baidu.com/1.jpg" />   onload必须等等页面中的图片、声音、图像等远程资源被加载完毕后才调用，而jQuery中只需页面结构被加载完毕。
```



# 五、数据库

## 1、数据库范式

第一范式：列不可分，eg:【联系人】（姓名，性别，电话），一个联系人有家庭电话和公司电话，那么这种表结构设计就没有达到 1NF；

第二范式：有主键，保证完全依赖。eg:订单明细表【OrderDetail】（OrderID，ProductID，UnitPrice，Discount，Quantity，ProductName），Discount（折扣），Quantity（数量）完全依赖（取决）于主键（OderID，ProductID），而 UnitPrice，ProductName 只依赖于 ProductID，不符合2NF；

第三范式：无传递依赖(非主键列 A 依赖于非主键列 B，非主键列 B 依赖于主键的情况)，eg:订单表【Order】（OrderID，OrderDate，CustomerID，CustomerName，CustomerAddr，CustomerCity）主键是（OrderID），CustomerName，CustomerAddr，CustomerCity 直接依赖的是 CustomerID（非主键列），而不是直接依赖于主键，它是通过传递才依赖于主键，所以不符合 3NF。



## 2、事务机制

- **关系性数据库需要遵循ACID规则，具体内容如下：**

![事务的特性](https://user-gold-cdn.xitu.io/2018/5/20/1637b08b98619455?w=312&h=305&f=png&s=22430)

1. **原子性：** 事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用；
2. **一致性：** 执行事务前后，数据保持一致；
3. **隔离性：** 并发访问数据库时，一个用户的事物不被其他事物所干扰，各并发事务之间数据库是独立的；
4. **持久性:**  一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库 发生故障也不应该对其有任何影响。

## 3、事务并发带来的问题

- 脏读：一个事务读取了另一个事务未提交的数据；
- 不可重复读：不可重复读的重点是修改，同样条件下两次读取结果不同，也就是说，被读取的数据可以被其它事务修改；
- 幻读：幻读的重点在于新增或者删除，同样条件下两次读出来的记录数不一样。

## 4、隔离级别

　　隔离级别决定了一个session中的事务可能对另一个session中的事务的影响。ANSI标准定义了4个隔离级别，MySQL的InnoDB都支持，分别是：

**READ UNCOMMITTED（未提交读）：**最低级别的隔离，通常又称为dirty read，它允许一个事务读取另一个事务还没commit的数据，这样可能会提高性能，但是会导致脏读问题；

**READ COMMITTED（已提交读）：**在一个事务中只允许对其它事务已经commit的记录可见，该隔离级别不能避免不可重复读问题；

**REPEATABLE READ（可重复读）：**在一个事务开始后，其他事务对数据库的修改在本事务中不可见，直到本事务commit或rollback。但是，其他事务的insert/delete操作对该事务是可见的，也就是说，该隔离级别并不能避免幻读问题。在一个事务中重复select的结果一样，除非本事务中update数据库。

**SERIALIZABLE（可串行化）：**最高级别的隔离，只允许事务串行执行。

　　**MySQL默认的隔离级别是REPEATABLE READ。**

| 隔离级别        脏读（Dirty Read）   不可重复读（NonRepeatable Read） 幻读（Phantom Read）  =================================================================================== |      |      |      |
| :----------------------------------------------------------- | ---- | ---- | ---- |
| 未提交读（Read uncommitted）        可能                            可能                       可能 |      |      |      |
| 已提交读（Read committed）          不可能                          可能                        可能 |      |      |      |
| 可重复读（Repeatable read）          不可能                          不可能                     可能 |      |      |      |
| 可串行化（Serializable ）                不可能                          不可能                     不可能 |      |      |      |

## 5、说说你对MySql索引的理解

## 6、**索引的不足之处**  

   上面都在说使用索引的好处，但过多的使用索引将会造成滥用。因此索引也会有它的缺点：  

   1.虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件。  

   2.建立索引会占用磁盘空间的索引文件。一般情况这个问题不太严重，但如果你在一个大表上创建了多种组合索引，索引文件的会膨胀很快。  

   索引只是提高效率的一个因素，如果你的MySQL有大数据量的表，就需要花时间研究建立最优秀的索引，或优化查询语句。

 ##  7、SQL语句的优化

 优化insert语句：一次插入多值；

 应尽量避免在 where 子句中使用!=或<>操作符，否则将引擎放弃使用索引而进行全表扫描；

 应尽量避免在 where 子句中对字段进行null值判断，否则将导致引擎放弃使用索引而进行全表扫描；

 优化嵌套查询：子查询可以被更有效率的连接(Join)替代；

 **很多时候用 exists 代替 in 是一个好的选择。**


   ##  8、什么样的字段适合创建索引？

   - 经常作查询选择的字段
   - 经常作表连接的字段
   - 经常出现在order by, group by, distinct 后面的字段

## 9、存储过程

存储过程（Stored Procedure）是在大型数据库系统中，一组为了完成特定功能的SQL 语句集，存储在数据库中，经过第一次编译后再次调用不需要再次编译，用户通过指定存储过程的名字并给出参数（如果该存储过程带有参数）来执行它。存储过程是数据库中的一个重要对象。 

- **存储过程有什么优点？** 
  1.存储过程可以重复使用，大大减小开发人员的负担； 
  2.对于网络上的服务器，可以大大减小网络流量，因为只需要传递存储过程的名称即可； 
  3.可以防止对表的直接访问，只需要赋予用户存储过程的访问权限。

# 六、JavaEE

## 1、Servlet接口中有哪些方法及Servlet生命周期探秘

Servlet接口定义了5个方法，其中**前三个方法与Servlet生命周期相关**：

- **void init(ServletConfig config) throws ServletException**
- **void service(ServletRequest req, ServletResponse resp) throws ServletException, java.io.IOException**
- **void destory()**
- java.lang.String getServletInfo()
- ServletConfig getServletConfig()

**生命周期：** **Web容器加载Servlet并将其实例化后，Servlet生命周期开始**，容器运行其**init()方法**进行Servlet的初始化；请求到达时调用Servlet的**service()方法**，service()方法会根据需要调用与请求对应的**doGet或doPost**等方法；当服务器关闭或项目被卸载时服务器会将Servlet实例销毁，此时会调用Servlet的**destroy()方法**。**init方法和destory方法只会执行一次，service方法客户端每次请求Servlet都会执行**。Servlet中有时会用到一些需要初始化与销毁的资源，因此可以把初始化资源的代码放入init方法中，销毁资源的代码放入destroy方法中，这样就不需要每次处理客户端的请求都要初始化与销毁资源。

## 2、Servlet是如何运行的?

step1.浏览器依据ip、port建立与Servlet容器之间的连接。

step2.浏览器创建请求数据包并发送给容器。

step3.容器解析请求数据包中的数据，然后将解析到的数据添加到
request对象里面，同时，容器还会创建一个response对象。

step4.容器依据请求路径，创建对应的Servlet对象，然后调用该对象的
service方法。

```
注：
	容器会将request对象和response对象作为参数传递过来。
开发人员可以通过调用request对象提供的方法来获得请求数据包中
的数据（比如请求参数），然后将处理结果写到response对象即可。
```

step5.容器通过response对象获得处理结果，然后创建响应数据包并发送给浏览器。

step6.浏览器解析响应数据包中的数据，生成相应的页面。

## 3、转发与重定向的区别

### 3.1能否共享request?

```
转发可以、重定向不行。
注:
	a.容器收到请求之后，会立即创建request和response对象，一旦
响应发送完毕，会立即销毁这两个对象。也就是说，request和response
对象的生存时间是一次请求和响应期间存在。
	b.转发是一次请求，重定向是二次请求。
```

### 3.2浏览器地址栏的地址有无变化?

```
转发没有变化，重定向有变化。
```

### 3.3目的地有无限制?

```
转发有（要求属于同一个应用），重定向无任何限制。
```

//重定向是一个请求处理完了，继续发送另一个请求
转发是一个组件请求没处理完转发给另一个组件

## 4、JSP和Servlet是什么关系

其实这个问题在上面已经阐述过了，Servlet是一个特殊的Java程序，它运行于服务器的JVM中，能够依靠服务器的支持向浏览器提供显示内容。JSP本质上是Servlet的一种简易形式，JSP会被服务器处理成一个类似于Servlet的Java程序，可以简化页面内容的生成。Servlet和JSP最主要的不同点在于，Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML分离开来。而JSP的情况是Java和HTML可以组合成一个扩展名为.jsp的文件。有人说，Servlet就是在Java中写HTML，而JSP就是在HTML中写Java代码，当然这个说法是很片面且不够准确的。JSP侧重于视图，Servlet更侧重于控制逻辑，在MVC架构模式中，JSP适合充当视图（view）而Servlet适合充当控制器（controller）。

## 5、JSON与XML的区别

（1）可读性方面：基本相同，XML的可读性比较好；

（2）可扩展性方面：都具有良好的扩展性；

（3）编码难度方面：相对而言，JSON的编码比较容易；

（4）解码难度：JSON的解码难度基本为零，XML需要考虑子节点和父节点；

（5）数据体积方面：JSON相对于XML来讲，数据体积小，传递的速度比较快；

（6）数据交互方面：JSON与javascript的交互更加方便，更容易解析处理，更好的数据交互；

（7）数据描述方面：XML对数据描述性比较好；

（8）传输速度方面：JSON的速度远远快于XML。

## 6、JSP有哪些内置对象、作用分别是什么

JSP有9个内置对象：

- request：封装客户端的请求，其中包含来自GET或POST请求的参数；
- response：封装服务器对客户端的响应；
- pageContext：通过该对象可以获取其他对象；
- session：封装用户会话的对象；
- application：封装服务器运行环境的对象；
- out：输出服务器响应的输出流对象；
- config：Web应用的配置对象；
- page：JSP页面本身（相当于Java程序中的this）；
- exception：封装页面抛出异常的对象。

## 7、常见的HTTP状态码

​	RFC2616定义的状态码，由3位数字和原因短信组成。数字中的第一位指定了响应类别，后两位无分类。响应类别有以下5种：

​	类型触发动作说明1XXInformational信息性状态码，表示接受的请求正在处理。

​	2XXSuccess成功状态码，表示请求正常处理完毕。

​	3XXRedirection重定向状态码，表示需要客户端需要进行附加操作。

​	4XXClient Error客户端错误状态码，表示服务器无法处理请求。

​	5XXServer Error服务器错误状态码，表示服务器处理请求出错。

### 7.1、**2XX Success**

2xx 响应结果表示从客户端发来的请求在服务器端被正常处理了。

**200 OK**

请求被成功处理，服务器会根据不同的请求方法返回结果：

GET：请求的对应资源会作为响应返回。HEAD：请求的对应资源的响应头(entity-header)会作为响应返回,不包括响应体(message-body)。POST：返回处理对应请求的结果。	

### 7.2、3XX Redirection

3XX 响应结果表明浏览器需要执行某些特殊的处理以完成请求。

**301 Movied Permanently**

​	永久性重定向。该状态码表示请求的资源已经被分配了新的URI，并且以后使用资源现在所指的URI。并且根据请求的方法有不同的处理方式：

HEAD：必须在响应头部Location字段中指明新的永久性的URI。

GET：除了有Location字段以外，还需要在响应体中附上永久性URI的超链接文本。

POST：客户端在发送POST请求，受到301响应之后，不应该自动跳转URI，应当让用户确认跳转。

**302 Found**

临时性重定向。该状态码表示请求的资源已被分配了新的URI，希望用户本次能使用新的URI访问。

**304 Not Modified**

该状态码表示客户端发送附带条件请求时，服务器端允许请求访问资源，但未满足条件的情况。304状态码返回时，不包含任何响应的主题部分。附带条件的请求指的是采用GET方法的请求头中包含：If-Match、If-Modified-Since、If-None-Match、If-Range、If-Unmodified-Since中任一首部。

### 7.3、4XX Client Error

4XX 的响应结果表明客户端是发生错误的原因所在

**400 Bad Request**

表示该请求报文中存在语法错误，导致服务器无法理解该请求。客户端需要修改请求的内容后再次发送请求。

### 7.4、**5XX Server Error**

5XX 的响应结果表明服务器本身发生错误，或者没有足够的能力来处理请求

**500 Internal Server Error**

该状态码表明服务器端在执行请求时发生了错误。也有可能是Web应用存在的BUG或某些临时的故障。

# 七、框架部分

## 1、谈谈你对Spring的理解

Spring是一个轻量级框架，Spring整个系列的最最核心的概念当属IOC、AOP。AOP（Aspect Oriented Programming），即面向切面编程，可以说是OOP（Object Oriented Programming，面向对象编程）的补充和完善。AOP的核心功能的底层实现机制：动态代理的实现原理。AOP的拦截功能是由java中的动态代理来实现的。

### 1.1、AOP

### 1.2、IOC

## 2、Spring MVC 运行流程

第一步：客户端请求提交到DispatcherServlet

第二步：由DispatcherServlet控制器查询一个或多个HandlerMapping,找到处理请求的Controller

第三步：DispatcherServlet将请求提交到Controller

第四步：Controller调用业务逻辑处理之后，返回ModelAndView

第五步：DispactherServlet查询一个或多个ViewResoler视图解析器，找到ModelAndVIew指定的视图。

第六步：视图负责将结果显示到客户端。

## 3、Spring有哪些优点？

轻量级：Spring在大小和透明性方面绝对属于轻量级的，基础版本的Spring框架大约只有2MB。

控制反转(IOC)：Spring使用控制反转技术实现了松耦合。依赖被注入到对象，而不是创建或寻找依赖对象。

面向切面编程(AOP)： Spring支持面向切面编程，同时把应用的业务逻辑与系统的服务分离开来。

容器：Spring包含并管理应用程序对象的配置及生命周期。

MVC框架：Spring的web框架是一个设计优良的web MVC框架，很好的取代了一些web框架。

事务管理：Spring对下至本地业务上至全局业务(JAT)提供了统一的事务管理接口。

异常处理：Spring提供一个方便的API将特定技术的异常(由JDBC, Hibernate, 或JDO抛出)转化为一致的、Unchecked异常。

## 4、谈谈你对Spring MVC的理解

## 5、Mybatis的实现原理

Mybatis利用动态代理创建Mapper接口实例

## 6、#{}和${}这2种占位符的区别

回到使用JDBC中使用`PreparedStatement`场景中，凡可以使用`?`进行占位的，在MyBatis中，都应该使用`#{}`，实现预编译。

在MyBatis的实现原理中，可以理解为参数都是在一个Map中，MyBatis会将Map中的值取出，用于替换`?`。

关于`#{}`的表达方式，可以参考：**ONGL表达式**（也适用于Spring表达式）。

而`${}`的作用是替换SQL语句中的某个组成部分，由于是SQL语句的语法限制的部分，所以，无法通过预编译处理，则存在被注入的风险！

小结：需要预编译，且替换某个值，则使用`#{}`，如果是占位某个语法的组成部分，则使用`${}`，且无法预编译，存在注入风险！

> 所有的注入问题，都可以在接收到用户请求的第一时间对内容进行过滤来避免。

## 7、JDK10新特性

1、局部变量类型可以使用var定义

2、对垃圾收集器进行了优化。

3、在list、map、set接口下新增了一个静态方法CopyOf

# 八、编程题

## 1、统计每个英文大写字母的字符数量

```java
/**
 * 统计每个英文大写字符数 
 */
String str = "ADSJDKSIWJRURJKWLAPQWMNBVCXSSLKJHGF";
int[] counter = new int[26];
for(int i=0;i<str.length();i++){
    char c = str.charAt(i);
    counter[c-'A']++;
}
char c='A';
for(int i:counter){
    System.out.println(c++);
    System.out.println(":");
    System.out.println(i);
}
```

## 2、值改变问题

```java
	public static void main(String[] args) {
		int n = 5;
		int[] arr = {5};
		add(n);
		add(arr);
		System.out.println(n);
		System.out.println(arr[0]); 
	}
	public static void add(int n) {
		n++;
	}
	public static void add(int[] n) {
		n[0]++;
	}
```

```java
	public static void main(String[] args) {
		String s1 = "A";
		StringBuilder s2 = new StringBuilder("A");
		add(s1);
		add(s2);
		System.out.println(s1);
		System.out.println(s2);		
	}
	public static void add(String s) {
		s+="A";
	}
	public static void add(StringBuilder s) {
		s.append("A");
	}
```

## 3、++运算符问题

```java
public static void main(String[] args) {
		int a = 5;
		
		a = 
		a++;
		/*
		 * 包含两个运算符，分为两步计算
		 * 1. ++ 运算优先级高，需要先计算， 先执行后加加
		 *   1. 先取当前a的值5作为a++表达式整体的值5
		 *   2. 然后将a的值增加1，a为6
		 * 2. = 运算优先级低，需要后计算
		 *   将 a++ 整体的值5，赋值给a，为修改为5
		 */
		
		for(int i=0; i<500; i++) {
			a=a++;
		}
		System.out.println(a); 
	}
```



## 4、统计100G数据前100大的数据

```java
		long[] arr = new long[101];
		Random r = new Random();
		for(int i=0;i<1000000;i++){
		long num = r.nextLong();
		arr[0] = num;
		Arrays.sort(arr);
		}
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
```

## 5、二分查找

```java
public static void main(String[] args) {
		int[] arr1= {3, 8, 15, 22, 98, 201, 304, 891, 998, 1001};
		int[] arr2= {3, 8, 15, 998, 98, 201, 304, 891, 22, 1001};
		//使用2分查找算法在数组中查找一个元素的位置，
		//返回值如果是 >=0 表示找到，如果<0表示没有找到
		int i = Arrays.binarySearch(arr1, 998);
		System.out.println(i);
		i = Arrays.binarySearch(arr1, 100);
		System.out.println(i);
		//失败：
		i = Arrays.binarySearch(arr2, 998);
		System.out.println(i); 
	}

```

## 6、LinkedList 双向循环链表

```java
public class Demo08 {
	public static void main(String[] args) {
		LinkedList<Integer> list=
				new LinkedList<Integer>(); 
		list.add(8);
		list.add(5); 
		list.add(6);
		list.add(7);
		list.add(9);
		
		list.add(2, 3);
		list.add(4, 10);
 		System.out.println(list);
 		list.delete(5);
 		System.out.println(list);
	}
}
class LinkedList<E>{
	private Node head;
	private int size=0;
	class Node{
		E data;
		Node next;
		Node prev;
		public Node(E e) {
			data = e;
		}
	}
	public int size() {
		return size;
	}
	
	public void delete(int index) {
		Node node = find(index);
		Node nextNode = node.next;
		Node prevNode = node.prev;
		nextNode.prev = prevNode;
		prevNode.next = nextNode;
		size--;
	}
	
	public void add(int index, E e) {
		Node node = find(index);
		//创建新元素
		Node newNode = new Node(e);
		Node prevNode = node.prev;
		//连接结点
		prevNode.next=newNode;
		newNode.next = node;
		newNode.prev = prevNode;
		node.prev = newNode;
		size++;
	}
	
	private Node find(int index) {
		Node node;
		if(index<=size/2) {
			//正方向一半插入元素
			//查找插入元素当前位置的结点
			int n=0;
			node = head;
			while(n!=index) {
				node=node.next;
				n++;
			}
		}else {
			//负方向一半插入元素
			int n=size-1;
			node = head.prev;
			while(n!=index) {
				node=node.prev;
				n--;
			}
		}
		return node;
	}
	public void add(E e) {
		if(head==null) { 
			//第一次添加元素在情况
			head = new Node(e); 
			head.next = head;
			head.prev = head;
		}else {
			//第二次以后,添加元素
			Node last = head.prev;
			Node node = new Node(e);
			node.next = head;
			node.prev = last;
			head.prev = node;
			last.next = node;
		}
		size++;
	}
	
	/**
	 * "[8, 9, 6, 7]"
	 */
	public String toString() {
		if(head==null) {
			return "[]"; 
		}
		StringBuilder buf = new StringBuilder("[");
		buf.append(head.data);
		//node 代表当前正在遍历的结点
		Node node=head.next;
		//如果当前元素的下一个不是头结点，则继续循环。
		while(node!=head) {
			buf.append(", ").append(node.data);
			//向后跳一个结点
			node = node.next;
		}
		return buf.append("]").toString(); 
	}
}
```

## 7、ArrayList

```
if(size+1>elementData.length){
	
}
```

## 8、散列表

#### 1.1、关于散列表

​	1、散列表是查询速度最快的数据结构（集合）

​      	          --在编程中，尽量避免顺序查找，尽量利用散列表查找

​	2、散列表根据Key的HashCode，计算散列数组的下标位置，这样就可以不用顺序查找直接定位数据的位置。

​	3、散列查找过程：首先根据key的HashCode计算散列位置，然后在散列桶中利用key的equals逐一比较。找到key,返回Value,找不到返回value.

​	4、散列表数据添加过程：首先根据Key的HashCode计算出散列位置，然后在散列桶中利用key的equals逐一比较。找到key,则替换Value,没有找到Key将数据添加到散列桶，如果位置上已经有数据，这添加到链表的最后节点。

​	5、加载因子：根据统计结果，当散列表中元素数量和数组大小的比值小于75%时候，散列桶中链表长度一般不超过3个。此时是内存消耗和性能较好的合理值。一般不用修改。散列表在元素超过75%时候，会自动扩容并且重新散列元素，为了减少重新散列，可以在创建散列表时候指定容量。

​	6、建议作为散列表key类型，需要合理重写HashCode和equals，重写规则：两个对象相等时，

## 九、计算机网络



## 十、linux系统

## 1、linux有哪几种文件系统

![文件类型](https://user-gold-cdn.xitu.io/2018/7/3/1645f1a7d64def1a?w=901&h=547&f=png&s=72692)

