### 增加搜索框列数

![1544499059264](C:\Users\hg\AppData\Roaming\Typora\typora-user-images\1544499059264.png)

### 在数据源文件中获取用户id

数据源类`extends BLProcDataSourceSupport`，如类`PlanLZJDiaryWorkDSBLService`

```java
String currentUserId = super.getActorid();
```

### `JSP`页面获取流程id的方法

```java
String pID = request.getParameter("processInstanceId")==null?"":request.getParameter("processInstanceId");
long proccessInstanceId = 0L;
if( pID.equals("") ){
	String tID = request.getParameter("taskInstanceId")==null?"":request.getParameter("taskInstanceId");
	if(!tID.equals("")){		
		BpmTaskInstance ti = null;
		long taskInstanceId=Long.valueOf(tID);
		ti=BpmContext.getInstance().loadTaskInstance(taskInstanceId);	
		proccessInstanceId = ti.getProcessInstanceId();
	}
}else{
	proccessInstanceId = Long.valueOf(pID);
}
```

### `JSP`页面获取当前节点的方法

```java
TaskExecutingContext taskContext=(TaskExecutingContext)request.getAttribute(TaskExecutingContext.TASK_EXECUTING_CONTEXT);
String nodename="";
if(taskContext!=null){
	nodename=taskContext.getCurrNode().getNodeName();
}
```

### `JSP`页面解析`JSON`数组

```java
1.  for (var i = 0; i < result.length; i++) {
        //result[i]表示获得第i个json对象即JSONObject
        //result[i]通过.字段名称即可获得指定字段的值
        result[i].userName;
    }
2.  for(var i in result){
        //表示遍历数组，而i表示的是数组的下标值，
        //result[i]表示获得第i个json对象即JSONObject
        //result[i]通过.字段名称即可获得指定字段的值
        result[i].userName;
    }

```

> 注：result为标准json数组

### 获取流程结束的时间

```sql
select wfpi.EndDate from StoreCloseInfomation sci 
		 left join WFProcessInstance wfpi on wfpi.ProcessInstanceId=sci.PROCESSINSTANCEID
```

### 日志的使用

```java
private static Log log=LogFactory.getLog(ServCustomerRepairService.class);
//ServCustomerRepairService类名
log.info("报修订单状态修改接口已启用。。。");
```

### 年份的使用

```java
{"$GETMAPALL(year)":"$GETMAPALL(year)"} // 前十年后十年
{"$GETMAPALL(month)":"$GETMAPALL(month)"} //12月
```

### 获取用户id

```xml
<%@page import="system.SysUserAccount"%>
<%@page import="system.Global"%>
<%
	Global global=new Global();  
	SysUserAccount user=global.getOnlineUser(request);
	String loginId = "";
	if(user!=null){
		loginId = user.getLoginID(); 
	}
%>
```
