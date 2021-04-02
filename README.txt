# sandbox-module
运行时动态修改方法执行逻辑，在不重启hotSwap进程的前提下完成了对目标程序方法的执行逻辑的改变
在不重启hotSwap进程的前提下完成了对目标程序方法的执行逻辑的改变
在不重启hotSwap进程的前提下完成了对目标程序方法的执行逻辑的改变

初体验
1. linux安装sandbox环境，安装过程可以百度。
2. 将本程序打成jar包(含有sandbox-api依赖)，放到sandbox/module目录下。
3. 部署目标程序hotSwap(github上我的另一个作品)，查看hotSwap进程号
4. 发送 post 请求：curl -H "Content-Type:application/json" -X POST -d 'ext-1.0-SNAPSHOT.jar父文件夹路径' https://127.0.0.1/refresh
5. 发送 post 请求：curl -H "Content-Type:application/json" -X POST -d '{"java.lang.Integer":666}' http://127.0.0.1:8081/bankServiceImpl/getBank 
	显示的是数据库的数据：[{"id":666,"money":666,"name":"xxx"}]
6. 到sandbox/bin目录下执行 sh ./sandbox.sh -p 22391 -d 'my-sandbox-module/changeMethodLogic'  
 其中22391是目标程序hotSwap进程号，my-sandbox-module/changeMethodLogic可定位到使能的区域
7. 发送 post 请求：curl -H "Content-Type:application/json" -X POST -d '{"java.lang.Integer":666}' http://127.0.0.1:8081/bankServiceImpl/getBank 
	显示sandbox 切入成功！！！
	挂载模块的代码逻辑将覆盖目标进程方法逻辑！！！
	[{"sandbox流程控制":"改变返回值666"}]
	
以上，在不重启hotSwap进程的前提下完成了对目标程序方法的执行逻辑的改变

注意：部署目标程序hotSwap需要把外部依赖（包含com.example.service.impl.BankServiceImpl）刷到spring容器中之后
		才能对目标方法com.example.service.impl.BankServiceImpl.getBank进行动态切面