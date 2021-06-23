书籍： maven 实战

###### 基础技术
1.Spring 模式注解    
2.Spring 应用上下文  
3.Spring 工厂加载机制  
4.Spring 应用上下文初始器  
5.Spring Environment 抽象  
6.Spring 应用事件/监听器 


###### 衍生技术
1.SpringApplication  
2.SpringApplication Builder API  
3.SpringApplication 运行监听器  
4.SpringApplication 参数  
5.SpringApplication 故障分析  
6.Spring Boot 应用事件/监听器  

###### SpringApplication 准备阶段
1.配置：Spring Bean 来源  
2.推断：Web 应用类型和主引导类（Main Class）  
3.加载：应用上下文初始器和应用事件监听器  


#### property 里面的值
E:\repository\org\springframework\boot\spring-boot-autoconfigure\2.3.0.RELEASE\spring-boot-autoconfigure-2.3.0.RELEASE.jar!\META-INF\spring-configuration-metadata.json

## SpringBoot启动流程

+ 框架初始化

1.配置资源加载器  
2.配置primarySources  
3.应用环境检测  
4.配置初始化器  
5.配置应用监听器  
6.配置main方法所在类  

+ 框架启动

1.计时器开始计时  
2.Headless模式赋值  
3.发送ApplicationStartingEvent  
4.配置环境模块  
5.发送ApplicationEnvironmentPrepareEvent  
6.打印banner  
7.创建应用上下文  
8.初始化失败分析器  
9.关联springboot组件与应用上下文对象  
10.发送ApplicationContextInitializedEvent  
11.加载sources到context  
12.发送ApplicationPreparedEvent  
13.刷新上下文  
14.计时器停止计时  
15.发送ApplicationStartedEvent  
16.调用框架启动扩展类  
17发送ApplicationReadyEvent  

+ 自动化装配

1.收集配置文件中的配置工厂类  
2.加载组件工厂  
3.注册组件内定义bean  


## 初始化器解析

#### 系统初始化器介绍

+ 类名 : `ApplicationContextInitializer`

+ 介绍 : Spring容器刷新之前执行的一个回调函数

+ 作用 : 向SpringBoot容器中注册属性

+ 使用 : 继承接口自定义实现

#### SpringFactoriesLoader介绍
+ 框架内部使用的通用工厂加载机制
+ 从classpath下多个jar包特定的位置读取文件并初始化类
+ 文件内容必须是kv形式，即properties类型
+ key是全限定名(抽象类|接口)，value是实现，多个实现用，分隔

#### 实现原理

+ 定义在Spring.factories文件中被SpringFactoriesLoader发现注册

+ SpringApplication初始化完毕后手动添加

+ 定义成环境变量被DelegatingApplicationContestInitializer发现注册

#### 面试题

+ 介绍下SpringFactoriesLoader？

+ SpringFactoriesLoader如何加载工厂类？

+ 系统初始化器的作用？

+ 系统初始化器调用时机？

+ 如何自定义实现系统初始化器？

+ 自定义实现系统初始化器有哪些注意事项？


## 监听器

#### 监听器模式要素

+ 事件
+ 监听器
+ 广播器
+ 触发机制


#### SpringBoot监听器实现

#### 面试题


+ 介绍下监听器模式

+ SpringBoot 关于监听器相关实现类有哪些

+ SpringBoot框架有哪些框架事件以及他们的顺序

+ 介绍下监听事件触发机制？

+ 如何自定义实现系统监听器及注意事项？

+ 实现ApplicationListener接口与SmartApplicationListener的区别
