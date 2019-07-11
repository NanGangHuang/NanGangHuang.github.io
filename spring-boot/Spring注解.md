## Spring 注解

1.`@SpringBootApplication`

   1.1 包含了`@ComponentScan`、`@SpringBootConfiguration`和`@EnableAutoConfiguration`注解

   1.2 `@ComponentScan`让`spring Boot`扫描到`Configuration`类并把它加入到程序上下文



2.`@Component；@Controller；@Service；@Repository `

   2.1  在`annotaion`配置注解中用`@Component`来表示一个通用注释用于说明一个类是一个`spring`容器管理的类。即就是该类已经拉入到`spring`的管理中了。

   2.2 `@Controller, @Service, @Repository`是`@Component`的细化，这三个注解比`@Component`带有更多的语义，它们分别对应了控制层、服务层、持久层的类。

   2.3 作用

​       (1)`@controller `控制器（注入服务）

​       (2)`@service `服务（注入`dao`）

​       (3)`@repository dao`（实现`dao`访问）

​       (4)`@component`（把普通`pojo`实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>

