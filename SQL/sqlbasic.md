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