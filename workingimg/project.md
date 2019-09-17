#### 世贸

###### SVN
`https://source.skyland.net.cn/svn/project2017/nova2017-shimao`

###### 正式系统地址
`http://shimao.skyland.net.cn:8081/bpm/portal/login.jsp`


#### 系统相关

###### 修改管理员密码
```sql
update SysUserAccount set Password = 'E10ADC3949BA59ABBE56E057F20F883E' where LoginID = 'Admin'
````
###### PC端下拉选从Map获值
```json
{"$GETMAP(class,com.skyland.equipment.service.EquipmentConstant.getRepairStatusMap)":"$GETMAP()"}
```



