## 缓存更新

> [缓存更新的套路](https://coolshell.cn/articles/17416.html)

**错误操作：**先删缓存，在更新数据库，而后再把数据装载到缓存中。

## 更新缓存的Design Pattern

###### Cache Aside Pattern
1.失效：应用程序先从`cache`取数据，没有得到，则从数据库中取数据，成功后，防到缓存中。
2.命中：应用程序从`cache`取数据，取到后返回。
3.更新：先把数据存到数据库中，成功后，再让缓存失效。

###### Read/Write Through Pattern

###### Write Behind Caching Pattern

