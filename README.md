###### PS
> ps -ef ;

注：`-e`参数指定显示所有运行在系统上的进程；`-f`参数则扩展了输出  
`UID` ：启动这些进程的用户。  
`PID` ：进程的进程ID。  
`PPID` ：父进程的进程号（如果该进程是由另一个进程启动的）。  
`C` ：进程生命周期中的CPU利用率。  
`STIME` ：进程启动时的系统时间。  
`TTY` ：进程启动时的终端设备。  
`TIME` ：运行进程需要的累计CPU时间。  
`CMD` ：启动的程序名称。  
ps命令显示某个特定时间点的信息。  

```
UID         PID   PPID  C STIME TTY          TIME CMD
root          1      0  0 09:12 ?        00:00:03 /sbin/init splash
root          2      0  0 09:12 ?        00:00:00 [kthreadd]

```

###### top
`top`命令显示实时进程信息  

`q`退出`top`命令  

###### 结束进程
> kill PID(进程ID)

> kill -s HUP 3940 强制终止

> killall 进程名（不是PID）

`killall http*`:结束了所有以`http`开头的进程，慎用。最好不要在`root`用户里使用.

###### 挂载存储媒体
> Linux文件系统将所有的磁盘都并入一个虚拟目录下。在使用新的存储媒体之前，需要把它放到虚拟目录下。这项工作称为挂载（mounting）。

图形桌面环境里，大多数能自动挂载特定类型的可移动存储媒体。  

管理可移动存储设备的Linux命令行命令：  
1.`mount`命令
要手动在虚拟目录中挂载设备，需要以root用户身份登录  
> mount -t type device directory

type 参数指定了磁盘被格式化的文件系统类型                                                                                                                                                     