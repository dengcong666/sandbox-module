# sandbox-module
运行时动态修改方法执行逻辑


初体验
1. linux安装sandbox环境，安装过程可以百度。
2. 将本程序打成带依赖的jar包，放到sandbox/module目录下。
3. 部署目标程序hotSwap(github上我的另一个作品)，查看hotSwap进程号
4. 到sandbox/bin目录下执行 sh ./sandbox.sh -p 22391 -d 'my-sandbox-module/changeMethodLogic'  
 其中22391是目标程序hotSwap进程号，my-sandbox-module/changeMethodLogic可定位到使能的区域



注意：部署目标程序hotSwap需要把外部依赖（包含com.example.service.impl.BankServiceImpl）刷到spring容器中之后
		才能对目标方法com.example.service.impl.BankServiceImpl.getBank进行动态切面