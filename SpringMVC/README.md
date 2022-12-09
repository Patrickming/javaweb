# SpringMVC

## 1、SpringMVC_helloworld
浏览器（写的html文件）直接访问不到WEB-INF里面的东西，
而需要我们服务器来访问，也就是只能通过转发来访问


1、thymeleaf的配置配置在springmvc.xml文件中


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--扫描控制层组件-->
    <context:component-scan base-package="com.atguigu.controller"></context:component-scan>

    <!-- 配置Thymeleaf视图解析器 -->
    <bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="characterEncoding" value="UTF-8"/>
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <property name="templateResolver">
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!-- 视图前缀 -->
                        <property name="prefix" value="/WEB-INF/templates/"/>
                        <!-- 视图后缀 -->
                        <property name="suffix" value=".html"/>
                        <property name="templateMode" value="HTML5"/>
                        <property name="characterEncoding" value="UTF-8" />
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

</beans>
```
2、在web.xml中配置dispatchservlet、和导入springmvc.xml文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--
        配置SpringMVC的前端控制器DispatcherServlet
        SpringMVC的配置文件默认的位置和名称：
        位置：WEB-INF下
        名称：<servlet-name>-servlet.xml，当前配置下的配置文件名为SpringMVC-servlet.xml
        url-pattern中/和/*的区别：
        /：匹配浏览器向服务器发送的所有请求（不包括.jsp）
        /*：匹配浏览器向服务器发送的所有请求（包括.jsp）
    -->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--设置SpringMVC配置文件的位置和名称-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
            <!--这里idclasspath就说明是  类路径：比如我们这的java包、resource包、-->
        </init-param>
        <!--将DispatcherServlet的初始化时间提前到服务器启动时-->
        <!--如果不设置这个的话，所以我们第一次
        访问的时候DispatcherServlet才会初始化
        因为DispatcherServlet的初始化做的事情很多，就很花时间-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

如果不在controller里设置处理dispatchServlet收到请求的方法，我们访问的所有网页都是404

```java
@Controller
public class HelloController {

    @RequestMapping("/")  //请求映射，一个斜线在服务器里解析的（java程序里的）就是TomCat配置的URL：http://localhost:8080/SpringMVC/
    //也就是我们的首页
    public String protal() {
        //将逻辑视图返回//去掉物理视图的前缀后缀就是
        //这里我们返回index.html，联系我们的  @RequestMapping("/") 映射中的这一/就说明把index.html当成首页
        return "index";
    }

    @RequestMapping("/hello")
    /*
    *<a th:href="@{/hello}">测试SpringMVC</a>   点击html文件里的这个超链接,
    *因为@RequestMapping("/hello")  会跳到URL+/hello这个网页,而这个网页我们设置的就是 
     * return "success";也就是URL+/hello这个网页就是我们写的success.html文件
     */
    public String hello() {
        return "success";
    }
}
```
**对照下面html文件来看**
index：
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>index.html</h1>     <!--这里就是语法-->
<a th:href="@{/hello}">测试SpringMVC</a><!--这样写thymeleaf会帮助我们渲染然后在/hello这个绝对路径上加上我们缺少的配置好的路径-->
<a href="/hello">测试绝对路径</a>//错误的，浏览器直接访问不到
</body>
</html> 9
```
success：
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>成功</title>
</head>
<body>
<h1>success.html</h1>
</body>
</html>
```


其实在我们开发的时候就可以：先配置好web.xml文件(dispatcherServlet设置),
然后配置springMVC文件(配置thymeleaf),然后在web.xml文件里匹配springMVC文件
然后写网页,然后在controller里配置服务器请求映射



## 2、SpringMVC_demo
//讲的就是有关@RequesMapping注解和里面的东西
重新看了里面的@RequesMapping注解的ant风格路径

## 3、SpringMVC_rest
```text
这一个文件，讲的是restful的东西，在这个文件里有笔记
```