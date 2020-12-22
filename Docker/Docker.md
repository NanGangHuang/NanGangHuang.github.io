> 系统学习Docker 践行DevOps理念

# 到底什么是Docker

## Centos上安装docker

https://docs.docker.com/engine/install/centos/



## 创建并运行docker容器

```
docker run --name bob_the_cantainer -i -t ubuntu /bin/bash
```

## 运行Docker容器

```
docker ps
docker attach dockerid

# 重启已经停止的docker
docker start bob_the_container 
or 
docker start aa3f3650f4e
```

## 删除docker容器

```
#删除单个容器
docker rm 80430f8d0921 
## 删除单个运行中的容器
docker rm -f 80430f8d0921

# 删除所有容器
docker rm `docker ps -a -q`
```

## 查找docker容器

```
#查找所有docker容器
docker ps -a
```

## Dockerfile语法梳理及最佳实践

#### FROM

FROM scrach   # 制作base image
FROM centos   # 使用base image
FROM ubuntu:14.04
尽量使用官方的image做base image

#### LABEL

LABEL maintainer="xiaoquwl@gmail.com"
LABEL version="1.0"
LABEL description="this is description"

Metadata不可少

#### RUN

RUN yum update && yum install -y vim \
    python-dev  # 反斜线换行
RUN apt-get update && apt-get install -y perl \
    pwgen --no-install-recommends && rm -rf \
    /var/lib/apt/lists/*  # 注意清理cache
RUN /bin/bash -c 'source $HOME/.bashrc; echo $HOME'    

#### WORKDIR 

WORKDIR /root

WORKDIR /test # 如果没有会自动创建test目录
WORKDIR demo
RUN pwd   #  输出结果应该是/test/demo

用WORKDIR，不要用RUN cd! 尽量使用绝对目录!

#### ADD and COPY

ADD hello /
ADD test.tar.gz / #添加到根目录并解压
WORKDIR /root
ADD hello test/ # /root/test/hello
WORKDIR /root
COPY hello test/
 
大部分情况，COPY优于ADD,ADD除了COPY还有额外功能（解压）！
添加远程文件/目录请使用CURL或者wget!

#### ENV

ENV MYSQL_VERSION 5.6  # 设置常量
RUN apt-get install -y mysql-server="${MYSQL_VERSION}" \
    && rm -rf /var/lib/apt/lists/*  #引用常量
    
尽量使用ENV增加可维护性！

## run vs cmd vs entrypoint

RUN : 执行命令并创建新的Image Layer
CMD : 设置容器启动后默认执行的命令和参数
ENTRYPOINT : 设置容器启动时运行的命令

#### Shell 和 Exec 格式

+ Shell 格式

```dockerfile
RUN apt-get install -y vim
CMD echo "helloo docker"
ENTRYPOINT echo "hello docker"
```

+ Exec 格式

```dockerfile
RUN ["apt-get" ,"install" , "-y" , "vim"]
CMD ["/bin/echo" , "hello docker"]
ENTRYPOINT["/bin/echo" , "hello docker"]
```

+ Dockerfile1

```dockerfile
FROM centos
ENV name Docker
ENTRYPOINT echo "hello $name"
```

+ Dockerfile2

```dockerfile
FROM centos
ENV name Docker
ENTRYPOINT [ "/bin/echo" , "hello $name" ]
```
 