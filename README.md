[toc]

# 基于 Spring Boot 的账户认证系统
# 目录

本项目基于 Spring Boot 框架，搭建了一个简单的账户认证微服务。  
Spring Boot相对于传统的SSM(Spring MVC + Mybatis + Spring)框架用起来更加简单，不需要进行复杂的配置，方便灵活。  

Spring Boot让我们的Spring应用变的更轻量化。比如：你可以仅仅依靠一个Java类来运行一个Spring应用。你也可以打包你的应用为jar并通过使用java -jar来运行你的Spring Web应用。  

使用Spring Boot可以很方便的建立微服务。  


## 应用技术
Spring Boot + bootstrap + thymeleaf

## 项目搭建

使用Intellij中的Spring Initializr来快速构建Spring Boot  
菜单栏中选择File=>New=>Project..  
联网自动从网站上下载Spring Boot的模板，稍作等待框架就搭好啦。  

## 项目目录结构  

### JAVA 源码目录
src/main/java/ 为代码文件  
src/main/resources/ 为资源文件  

为了保证项目资源结构的清晰，我们把 src/main/java/ 再进一步进行划分：  
bean 目录存放的是要用到的实体类  
controller 目录存放的是控制层类  

src/main/resources/template/ 为静态页面的模板文件，这里用了thymeleaf模板渲染引擎框架（据说Spring Boot推荐）  
src/main/resources/application.properties 为Spring Boot的配置文件  

### maven配置 ( pom.xml ) 
- **Java web 项目配置**

我们做的是Java web项目，在其默认生成的maven配置文件中添加web和thymeleaf依赖。  
  
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>43
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
- **MySQL 数据库配置**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.11</version> <!-- 加上版本号-->
</dependency>
```
### application.properties配置  

- **thymeleaf 模板渲染引擎配置**  
```
# Enable template caching.
spring.thymeleaf.cache=true
# Check that the templates location exists.
spring.thymeleaf.check-template-location=true
# Content-Type value.
spring.thymeleaf.servlet.content-type=text/html
# Enable MVC Thymeleaf view resolution.
spring.thymeleaf.enabled=true
# Template encoding.
spring.thymeleaf.encoding=UTF-8
# Comma-separated list of view names that should be excluded from resolution.
spring.thymeleaf.excluded-view-names=
# Template mode to be applied to templates. See also StandardTemplateModeHandlers.
spring.thymeleaf.mode=HTML
# Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.prefix=classpath:/templates/
# Suffix that gets appended to view names when building a URL.
spring.thymeleaf.suffix=.html
```
- **MySQL 配置**
```
# 服务器地址、端口、数据库名称、编码、时区、是否认证ssl
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/runoob?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=FALSE
# 数据库账户和密码
spring.datasource.username=root
spring.datasource.password=123456
# mysql的驱动
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.max-idle=10
spring.datasource.max-wait=10000
spring.datasource.min-idle=5
spring.datasource.initial-size=5
```
- **MySQL 数据库表（t_user）结构字段**

id | username | password | phonenum | email  | regtime |
---|---|---|---|---|---|
1 | SpringBoot | 123 | 13519089021 | abc@foxmail.com | 2018-02-23 18:56:21 |




### Controller 类注解说明  
web项目的控制器写在这里，处理页面的请求，前后台交互  

@Controller：修饰class，用来创建处理http请求的对象  
@RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。  
@RequestMapping：配置url映射  

## 项目打包运行  
- **使用maven进行打包。**  
```
mvn install
```
- **Spring Boot里面嵌入了Tomcat，直接运行** 
```
java -jar xxx.jar
```
- **浏览器验证输入: http://localhost:8080**


## 参考来源  
[Spring Boot基础教程](http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/)  
[基于Bootstrap的简洁登录界面设计效果](http://www.htmleaf.com/css3/ui-design/201610114094.html)  
[Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)  
