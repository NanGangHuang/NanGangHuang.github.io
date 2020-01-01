## 简介

+ Spring Session是Spring的项目之一

+ Spring Session提供了一套创建和管理Servlet HttpSession的方案

+ Spring Session提供了集群Session（Clustered Session）功能

+ 默认采用外置的Redis来存储Session数据，以此来解决Session共享的问题

 
 ## Spring Session 项目集成
 
 + 引入Spring Session pom 
 
 + 配置JedisConnectionFactory
 
 + 配置DelegatingFilterProxy
 
 + 配置RedisHttpSessionConfiguration
 
 + 配置DefaultCookieSerializer
 
 + 配置JedisPoolConfig
 
 ## Spring Session源码解析
 
 + DelegatingFilterProxy
 
 + DefaultCookieSerializer
 
 + SessionRepositoryFilter
 
 + RedisOperationsSessionRepository
 
 + AbstractHttpSessionApplicationInitializer
 
 + SessionRepositoryRequestWrapper
 
 + SessionRepositoryResponseWrapper
 
 + CookieHttpSessionStrategy
 
 