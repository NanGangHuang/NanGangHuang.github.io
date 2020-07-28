## oracle 自带账户

System :系统账户
sys : 超级用户

oracle11g 和oracle 10g 自带一个普通scott,默认密码为：tiger,并给该用户自带了四张二位表

## oracle 新建用户

```sql
--创建用户
--create user 用户名 identified by 密码
    create user huanggang identified by 123456
--给用户赋予权限
    --赋予用户数据库连接权限
    grant connect to huanggang
    --赋予资源操纵权限
    grant resource to huanggang
```
