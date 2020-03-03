## Spring IOC

![](https://nanganghuang.github.io/Spring/img/20200227101952.jpg)

#### Spring IOC支持的功能

+ 依赖注入

+ 依赖检查

+ 自动装配

+ 支持集合

+ 指定初始化方法和销毁方法

+ 支持回调方法

#### BeanFactory:Spring框架最核心的接口

+ 提供IOC的配置机制

+ 包含Bean的各种定义，便于实例化Bean

+ 建立Bean之间的依赖关系

+ Bean生命周期的控制

#### BeanFactory与ApplicationContext的比较

+ BeanFactory是Spring框架的基础设置，面向Spring

+ ApplicationContext面向使用Spring框架的开发者

#### ApplicationContext的功能（继承多个接口）

+ BeanFactory:能够管理，装备Bean

+ ResourcePatternResolver：能够加载资源文件

+ MessageSource:能够实现国际化等功能

+ ApplicationEventPublisher:能够注册监听器，实现监听机制

#### getBean方法的代码逻辑

+ 转换beanName

+ 从缓存中加载实例

+ 实例化Bean

+ 检测parentBeanFactory

+ 初始化依赖的Bean

+ 创建Bean

#### Spring Bean的作用域

+ singleton:Spring的默认作用域，容器里拥有唯一的Bean实例

+ prototype:针对每个getBean请求，容器都会创建一个Bean实例

+ request:会为每个Http请求创建一个Bean实例

+ session:会为每个session创建一个Bean实例

+ globalSession：会为每个全局Http Session创建一个Bean实例，该作用域仅对Portlet有效

#### Spring Bean的生命周期


## Spring AOP

关注点分离：不同的问题交给不同的部分去解决

+ 面向切面编程AOP正是此种技术的体现

+ 通用化功能代码的实现，对应的就是所谓的切面（Aspect）

+ 业务功能代码和切面代码分开后，架构将变得高内聚低耦合

+ 确保功能的完整性：切面最终需要被合并到业务中（Weave）

#### AOP的三种织入方式

+ 编译时织入：需要特殊的Java编译器，如AspectJ

+ 类加载时织入：需要特殊的Java编译器，如AspectJ和AspectWerkz

+ 运行时织入：Spring采用的方式，通过动态代理的方式，实现简单

#### AOP的主要名词概念

+ Aspect:通用功能的代码实现

+ Target：被织入Aspect的对象

+ Join Point:可以作为切入点的机会，所有的方法都可以作为切入点

+ Pointcut : Aspect实际被应用在的Join Point ，支持正则

+ Advice:类里的方法以及这个方法如何织入到目标方法的方式

+ Weaving: AOP的实现过程

#### Advice的种类

+ 前置通知（Before）

+ 后置通知（AfterReturning）

+ 异常通知（AfterThrowing）

+ 最终通知（After）

+ 环绕通知（Around）

#### AOP的实现：JdkProxy和Cglib

+ 由AopProxyFactory根据AdvisedSupport对象的配置来决定

+ 默认策略如果目标类是接口，则用JDKProxy来实现，否则用后者

+ JDKProxy的核心:InvocationHandler接口和Proxy类

+ Cglib：以继承的方式动态生成目标类的代理

+ JDKProxy：通过Java的内部反射机制实现

+ Cglib:借助ASM实现

+ 反射机制在生成类的过程中比较高效

+ ASM在生成类之后的执行过程比较高效

#### Spring里的代理模式的实现

+ 真实实现类的逻辑包含在了getBean方法里

+ getBean的方法返回的实际上是Proxy的实例

+ Proxy实例是Spring采用JDK Proxy或CGLIB动态生成的

#### Spring 事务的相关考点

+ ACID

+ 隔离级别

+ 事务传播 

## Spring Schedule Cron表达式快速入门

Cron表达式的格式：秒 分 时 日 月 周 年 （可选）
1. Seconds
2. Minutes
3. Hours
4. Day-of-Month
5. Month
6. Day-of-Week
7. Year(可选字段)

**Spring Schedule Cron 表达式快速入门**

|字段名|允许的值|允许的特殊字符|
|----|----|----|
|秒|0-59|, - * / |
|分|0-59|, - * /|
|小时|0-23|, - * / |
|月内日期|1-31|, - * ? / L C # |
|月|1-12 或者 JAN-DEC|, - * ? / L C #|
|周内日期|1-7 或者 SUN-SAT|, - * ? / L C #|
|年(可选)|留空，1979-2099|, - * /|

|特殊字符|意义|
|----|----|
|*|匹配所有的值，如：*在分钟的字段域里表示每分钟|
|?|只在日期域和星期域中使用，他被用来指定“非明确的值”|
|-|指定一个范围，如：“10-12”在小时域意味着“10点，11点，12点”|
|,|指定几个可选值，如：“MON,WED,FRI”表示“星期一、星期三,星期五”|
|/|指定增量，如：“0/15”在秒域意思是每分钟的0,15,30和45秒，“5/15”在分钟域表示每小时的5,20,35和50，<br>符号“\*”在“/”前面（如：\*/10）等价于0在“/”前面（如：0/10）|
|L|表示day-of-month和day-of-week域，但在两个字段中的意思不同，例如<br>day-of-month域中表示一个月的最后一天。如果在day-of-week域表示‘7’或者‘SAT’,<br>如果在day-of-week域中前面加上数字，他表示一个月的最后一个星期五|
|W|只允许日期域出现，这个字符用于指定日期的最近工作日。例如：如果你在日期域中写“15W”,表示：这个月15号最近的工作日。<br>所以，如果15号是周六，则任务会在14号触发,如果15号是周日，则任务会在16号触发。<br>如果是在日期域填写“1W”即使1号是周六，那么任务也只会在下周一，也就是3号触发，<br>“W”字符指定的最近工作日是不能够跨月份的，字符“W”只能配合一个单独的数值只用，不能够是一个数字端，如：1-15W是错误的|
|LW|L和W可以在日期域中联合使用，LW表示这个月最后一周的工作日|
|#|只允许在星期域中出现。这个字符用于指定本月的某某天。例如：“6#3”表示<br>本月第三周的星期五（6表示星期五，3表示第三周），“2#1”表示本月第一周的星期一，“4#5表示第五周的星期三”|
|C|允许在日期域和星期域出现。这个字符依靠一个指定的“日历”。也就是说这个表达式的值依赖于相关的“日历”的计算结果，如果没有“日历”关联，<br>则等价于所有包含的“日历”，如：日期域是“5C”表示关联“日历”中的第一天，<br>或者这个月开始的第一天的后五天，星期域是“1C”表示关联“日历”中第一天，或者星期的第一天的后一天，也就是周日的后一天（周一）|

**常用cron表达式介绍**  

0 0 0 * * ? 每天0点一次  
0 0 23 * * ? 每天23点一次  
0 */1 * * * ? 每1分钟（每个1分钟的整数倍）  
0 0 */6 * * ? 每6小时  
0 0 */1 * * ? 每1小时  

## Spring Schedule Cron配置

+ `<task:annotation-driven/>`

+ `xmlns:task= "http://www.springframework.org/schema/task" `

+ `http://www.springframework.org/schma/task`
  `http://www.springframework.org/schema/task/spring-task.xsd`
  
+ `@Componment`

+ `@Scheduled`

+ `@Scheduled(crom= "0 */1 * * * ?" )`

## MySQL行锁、表锁

+ SELECT ... FOR UPDATE

+ 使用InnoDB引擎

+ Row-Level Lock(明确的住建)

+ Table-Level Lock(无明确的主键)  







