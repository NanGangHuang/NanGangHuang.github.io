#### `SQL`标准中`Join`的类型

1.内连接（`INNER`） 
`SELECT <select_list> FROM TableA A INNER JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/1.png)

2.全外连接（`FULL OUTER`）
3.左外连接（`LEFT OUTER`）
`SELECT <select_list> FROM TableA A LEFT JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/1.png)

`SELECT <select_list> FROM TableA A INNER JOIN TableB B ON A.key = B.Key`

![](https://nanganghuang.github.io/Database/img/1.png)
4.右外连接（`RIGHT OUTER`）
5.交叉连接（`CROSS`）