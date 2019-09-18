## Cache-Control

#### 可缓存性
1.`public`  
2.`private`  
3.`no-cache`  

#### 到期
`max-age=<seconds>`  
`s-maxage=<seconds>` 代理服务器生效  
`max-stale=<seconds>`   

#### 重新验证
`must-revalidate`  
`proxy-revalidate`  

## 其他
`no-store`  
`no-transform`  

## 验证头
`Last-Modified`:上次修改时间  

`Etag`:数据签名  
配合`if-Match`或者`if-Non-Match`使用  
对比资源的签名判断是否使用缓存  

## Cookie和Session
通过`Set-Cookie`设置  

#### Cookie属性
`max-age`和`expires`设置过期时间  
`Secure`只在`https`的时候发送   
`HttpOnly`无法通过`document.cookie`访问   



