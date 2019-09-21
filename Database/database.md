#### `SQL`标准中`Join`的类型

1.内连接（`INNER`） 
`SELECT <select_list> FROM TableA A INNER JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/1.png)

2.全外连接（`FULL OUTER`）

`SELECT <select_list> FROM TableA A FULL JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/6.png)

`SELECT <select_list> FROM TableA A FULL JOIN TableB B ON A.key = B.Key WHERE A.Key IS NULL and B.key IS NULL`

![](https://nanganghuang.github.io/Database/img/7.png)

3.左外连接（`LEFT OUTER`）
`SELECT <select_list> FROM TableA A LEFT JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/2.png)

`SELECT <select_list> FROM TableA A INNER JOIN TableB B ON A.key = B.Key WHERE B.Key IS NULL`

![](https://nanganghuang.github.io/Database/img/3.png)

4.右外连接（`RIGHT OUTER`）

`SELECT <select_list> FROM TableA A RIGHT JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/4.png)

`SELECT <select_list> FROM TableA A RIGHT JOIN TableB B ON A.key = B.Key WHERE A.Key IS NULL`

![](https://nanganghuang.github.io/Database/img/5.png)

5.交叉连接（`CROSS`）:笛卡尔积

`SELECT <select_list> FROM TableA A CROSS JOIN TableB B`

#### 如何进行行列转换

`SUM WHEN 条件 then 答案1 else 答案2 end`

#### 如何进行列转行

使用Union  

使用序列号的方式   
```sql
select user_name,
case when c.id = 1 then 'arms'
     when c.id = 2 then 'clothing'
     when c.id = 3 then 'shoe'
end as equipment
,coalesce(case when c.id = 1 then arms end
,case when c.id = 2 then clothing end
,case when c.id = 3 then show end) as eq_name
from user1 a
join user1_equipment b on a.id = b.user_id
cross join tb_sequence c where c.id <= 3 order by 
user_name 
```

