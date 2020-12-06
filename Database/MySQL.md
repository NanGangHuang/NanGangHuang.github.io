## MySQL的主配置文件

+ `linux`系統：文件夹名及文件路径为`/etc/my.cmf`
+ `windows`上主配置文件路径及文件名为`c:\windows/my.ini`或`C:\my.cn`

#### 用户及其数据库服务器的地址列表
`select USER,HOST from mysql.user`
#### 修改密码
`set PASSWORD FOR 'root'@'%'=PASSWORD('1');`  
[解决修改密码报错的问题](https://blog.csdn.net/kuluzs/article/details/51924374)

## mysql 设置基本属性
```sql
# 查看变量
show variables like 'char%';
#设置数据库编码
set character_set_database = utf8;
#设置表的编码
ALTER TABLE sys_log CHARACTER SET = utf8 ;
#设置字段编码
alter table sys_log modify OPERATION varchar(100) charset utf8 null;
```