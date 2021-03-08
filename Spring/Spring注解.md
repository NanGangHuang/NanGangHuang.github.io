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





> [慕课网_《2小时学会SpringBoot》学习总结](https://segmentfault.com/a/1190000008398892)

```
@Controller：处理http请求
@RestController:Spring4之后新的注解，返回json需要@ResponseBody与@Controller
@RequestMapping:配置url映射
```
处理请求中的参数
```
@PathVariable:获取url中的数据
@RequestParam:获取请求参数的值
@GetMapping:组合注解
```

```
@RequestMapping("/test")
@RequestBody
//property 文件中配置,如何在java代码中获取
//name:zhangsan
@Value("${name}")
private String name;
```

```
@ConfigurationProperties
通过注解@ConfigurationPPProperties(prefix=“配置文件的前缀”)可以将配置文件中的配置自动与实体进行映射。
```
