---
layout: post
title: spring-boot学习
date: 2019-03-04 10:44:00
tags: java
categories: java
excerpt: 记录spring boot的学习笔记
---

## Spring Boot初级大纲



![SpringBoot初级大纲](\img\SpringBoot初级大纲.png)



## 利用Spring Boot搭建一个简单的项目

* 创建一个maven项目

* 注入spring启动器坐标

  ```
  <!-- springBoot的启动器 -->
  <dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```

* 编写返回HelloWorld的Controller

  ```
  /**
  * SpringBoot HelloWorld
  * @author Administrator
  *
  */
  @Controller
  public class HelloWorld {
      @RequestMapping("/hello")
      @ResponseBody
      public Map<String, Object> showHelloWorld(){
          Map<String, Object> map = new HashMap<>();
          map.put("msg", "HelloWorld");
          return map;
      }
  }
  ```

  

* 编写Spring Boot的启动类

  ```
  /**
  * SpringBoot 启动类
  * @author Administrator
  *
  */
  @SpringBootApplication
  public class App {
      public static void main(String[] args) {
      	SpringApplication.run(App.class, args);
      }
  }
  ```

* 编写启动器类需要注意的问题

  启动器存放的位置。启动器可以和 controller 位于同一个包下，或者位于 controller 的上一级
  包中，但是不能放到 controller 的平级以及子包下。

