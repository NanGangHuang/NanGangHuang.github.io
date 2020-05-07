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
