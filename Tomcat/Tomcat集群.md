## Tomcat单机部署多应用/多机部署多应用

## Tomcat集群能带来什么

+ 提高服务的性能，并发能力，以及高可用性  
+ 提供项目架构横向扩展能力

## Tomcat集群带来了什么问题

+ Session登录信息存储及读取的问题
+ 服务器定时任务并发的问题

## Tomcat单机部署多应用

+ 修改/etc/profile 增加tomcat环境变量

```
export CATALINA_BASE=/home/install_software/tomcat1
export CATALINA_HOME=/home/install_software/tomcat1
export TOMCAT_HOME=/home/install_software/tomcat1
export CATALINA_2_BASE=/home/install_software/tomcat2
export CATALINA_2_HOME=/home/install_software/tomcat2
export CATALINA_2_HOME=/home/install_software/tomcat2


```