# CMS系统说明 #

## 一、开发背景 ##
   

## 二、技术框架 ##



### 1. 开发环境 ###
    
- **JDK1.8**
- **Apache-Maven-3.5.0**  
- **InteliJ IDEA**
- **Apache-Tomcat-8.0**
- **Nginx-1.6.3**

### 2. 后端技术 ###

- **Spring**
- **Spring MVC**
- **Spring Boot**
- 密码找回 : **SpringBoot-Email**
- 分布式定时框架: **quartz**
- **MyBatis**
### 3. 权限框架 ###
- 权限： **Apache Shiro**
### 4. 数据库以及连接池 ###
- 数据库 ： **MySQL**
- 连接池 ： **Druid**
    
### 5. Json ###
- Json转换 ： **FastJson**
### 6. 前端技术 ###
- 前端页面 ： **FreeMarker 模块引擎**
- 前端样式 ： **BootStrap**
- 文件上传 ： **ajaxFileUpload**
- 文本编辑 ： **百度UEditor编辑器**
    
## 三、项目结构 ##


    trunk --------------------------------------------------------- 代码库
    |- sql -------------------------------------------------------- 数据库建表语句以及初始化
    |- src -------------------------------------------------------- 项目源码
        |- main --------------------------------------------------- 主代码 
            |- java ----------------------------------------------- 代码
            |   |-  com
            |       |- baidu -------------------------------------- 百度编辑器
            |       |- pengzu
            |           |— config --------------------------------- 系统配置
            |           |- controller ----------------------------- 控制层
            |           |   |- manage ----------------------------- 后台控制层
            |           |   |- view ------------------------------- 前台控制层
            |           |- dao ------------------------------------ 数据库dao 接口
            |           |- entity --------------------------------- 实体
            |           |   |-result ------------------------------ 响应及分页
            |           |   |- vo --------------------------------- 页面传输实体
            |           |- filter --------------------------------- 请求拦截器
            |           |- job ------------------------------------ quartz 定时执行类
            |           |- schedule ------------------------------- quartz 定时配置
            |           |- service -------------------------------- Service层接口
            |           |   |- impl ------------------------------- Service 接口实现层
            |           |- shiro ---------------------------------- shiro realm 配置
            |           |- utils ---------------------------------- 工具类
            |           |- ServletInitializer --------------------- 外置Tomcat运行必须类
            |           |- CmsApplication ------------------------- 系统启动类
            |- resources ------------------------------------------ 资源文件夹
                |- mybatis ---------------------------------------- mybatis 配置以及Mapper文件
                |- static ----------------------------------------- 系统静态资源文件夹
                |- templates -------------------------------------- 系统模板页面文件夹

                         
## 四、项目打包以及部署 ##


### （一）、项目打包

	1. 修改项目里 trunk > src > main > resources 里的配置文件:application-xxx.yml和quartz-xxx.properties 为不同环境配置文件
	application-xxx.yml:

>
>spring: <br>
>&nbsp;&nbsp;&nbsp;&nbsp;mail: #邮箱的发送配置<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;host: smtp.xxxx.com&nbsp;&nbsp;&nbsp;&nbsp;#邮件服务器SMTP地址<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;username: xxxxx@xxxx.com&nbsp;&nbsp;&nbsp;&nbsp;#邮件发件人配置<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: xxxxx&nbsp;&nbsp;&nbsp;&nbsp;#邮件发件人密码<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;properties:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mail:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;smtp:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;auth: true<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;starttls:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;enable: true<br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;required: true<br>
>&nbsp;&nbsp;&nbsp;&nbsp;datasource:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;name: test<br>
>&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true<br>
>&nbsp;&nbsp;&nbsp;&nbsp;username: root<br>
>&nbsp;&nbsp;&nbsp;&nbsp;password: xxxx&nbsp;&nbsp;&nbsp;&nbsp;#数据库密码<br>
>file:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;root: E:\filesystem&nbsp;&nbsp;&nbsp;&nbsp;#文件存放位置<br>
>email:<br>
>&nbsp;&nbsp;&nbsp;&nbsp;username: xxxxx@xxxx.com&nbsp;&nbsp;&nbsp;&nbsp;#发件人邮箱<br>
>

	quartz-xxx.properties:

>
>org.quartz.scheduler.instanceName = MyScheduler<br>
>
>org.quartz.threadPool.threadCount = 5<br>
>org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX<br>
>org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate<br>
>org.quartz.jobStore.tablePrefix = QRTZ_<br>
>org.quartz.jobStore.dataSource = myDS<br>
>
>org.quartz.dataSource.myDS.driver = com.mysql.jdbc.Driver<br>
>org.quartz.dataSource.myDS.URL = jdbc:mysql://localhost:3306/quartz?characterEncoding=utf-8&useSSL=false<br>
>org.quartz.dataSource.myDS.user = root<br>
>org.quartz.dataSource.myDS.password = xxxxx<br>
>org.quartz.dataSource.myDS.maxConnections = 5<br>
>



	2. 在工程下trunk目录里打开cmd命令窗口,输入:
> mvn clean package -Pxxx -Dmaven.test.skip=true&nbsp;&nbsp;&nbsp;&nbsp;#xxx：项目部署环境配置文件
 
	3. 打包文件在 target下..名字为 cms-1.0.0.war

#### （二）、项目部署

	1. 上传war包到服务器;
	2. 放在Tomcat的webapps目录下
	3. 配置Tomcat的server.xml,加入
	4. 加入 <Context path="" docBase="cms-1.0.0" reloadable="true"/> 到host节点下 
	5. 启动Tomcat
	6. 前台访问地址: ip：port
	7. 后台访问地址: ip:port/adminManager/login.html
	8. 正常情况下访问是没有图片资源的,需配置Nginx反向代理静态资源



 