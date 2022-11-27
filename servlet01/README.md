## servlet01
### 1、HelloServlet程序
#### 1、在web.xml中配置的就是tomcat（浏览器）访问我们写的程序的地址
```xml
<!--步骤1-->
 <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.xrm.servlet.HelloServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <!--这里是Tomcat配置里面的URL+你写的,
        比如这里就是URL+/hello = http://localhost:8080/servlet01/hello
        你启动Tomcat的时候会在网页访问你配置的URL：http://localhost:8080/servlet01
        -->
        <!--这里的意思就是访问URL+/hello的时候会去找<servlet-name>：HelloServlet
        然后HelloServlet再找到<servlet-class>：com.xrm.servlet.HelloServlet
        然后这里面的就是你写的代码程序
        -->
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
<!--在URL其中，我们设置的名字servlet01就是工程路径，而hello就是资源路径-->
```
#### 2、设置页面访问
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--这里的表单，action里写的就是a.html这个页面的这个表单访问的网站（servlet）也就是提交地址，我们设置成刚刚的hello
所以这里通过hello又去....就到了步骤一的过程
-->
<form action="http://localhost:8080/servlet01/hello" method="get">
    <input type="submit">
    <!--然后我在http://localhost:8080/servlet01/a.html这个网页点击一次按钮即相当于访问一次hello这个servlet，
     就访问一次web.xml中配置的hello对应的程序中的service程序
     -->
</form>
</body>
</html>
```
**其实在这里我们就能知道，servlet就像是一个接口，我们把它配置在我们的web.xml中，通过servlet里的
各种标签来匹配我们的网页和写的后端java程序在这就是HelloServlet来处理我们写的网页里的各种请求**

### 2、HelloServletHttp程序
一般在实际项目开发中，都是使用继承 HttpServlet 类的方式去实现 Servlet 程序。
1、编写一个类去继承 HttpServlet 类
2、根据业务需要重写 doGet 或 doPost 方法
3、到 web.xml 中的配置 Servlet 程序的访问地址

#### 1、编写servlet程序HelloServletHttp
这里简单设置两个对应响应方法
```java
public class HelloServletHttp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的doGet方法");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的doPost方法");
    }
}
```
#### 2、配置web.xml里的servlet
```xml
 <servlet>
        <servlet-name>HelloServletHttp</servlet-name>
        <servlet-class>com.xrm.servlet.HelloServletHttp</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServletHttp</servlet-name>
        <url-pattern>/helloHttp</url-pattern>
    </servlet-mapping>
```
#### 3、在a.html里增加对应HelloServletHttp的servlet测试按钮
```html
<!--别忘记把hello改成helloHttp-->
<form action="http://localhost:8080/servlet01/helloHttp" method="get">
    <input type="submit" value="我是hello的GET提交">
</form>
<form action="http://localhost:8080/servlet01/helloHttp" method="post">
    <input type="submit" value="我是helloHttp的POST提交">
</form>
<!--这里在网页点击相应按钮会对应到相应响应的get或post方法上-->

```
然后这里就是点击哪个按钮就会对应我们在HelloServletHttp设置重写的哪个响应方法

### 3、ServletConfig 类
介绍
```text
1.ServletConfig 类从类名上来看，就知道是 Servlet 程序的配置信息类。
2.Servlet 程序和 ServletConfig 对象都是由 Tomcat 负责创建，我们负责使用。
3.Servlet 程序默认是第一次访问的时候创建，ServletConfig 是每个 Servlet 程序创建时，
就创建一个对应的 ServletConfig 对象。所以每个servlet的这个是不同的，也获取不到别人servlet里面的信息
```
#### ServletConfig 类的三大作用
```text
1、可以获取 Servlet 程序的别名 servlet-name 的值
2、获取初始化参数 init-param
3、获取 ServletContext 对象
```
在这里根据上面那几个作用先配置一下测试信息
##### 1、根据作用2先在servlet里加一下信息
我们在HelloServlet里演示
```xml
<servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.xrm.servlet.HelloServlet</servlet-class>
        <init-param>
            <!--是参数名-->
            <param-name>username</param-name>
            <!--是参数值-->
            <param-value>root</param-value>
        </init-param>
        <!--init-param是初始化参数-->
        <init-param>
            <!--是参数名-->
            <param-name>url</param-name>
            <!--是参数值-->
            <param-value>jdbc:mysql://localhost:3306/test</param-value>
        </init-param>
    </servlet>
<!--这里记得<init-param>可以加多对信息-->
```

##### 2、我们在HelloServlet里的init方法里加上使用servletconfig的使用方法测试
```java
@Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");

//        1、可以获取Servlet程序的别名servlet-name的值
        System.out.println("HelloServlet程序的别名是:" + servletConfig.getServletName());
//        2、获取初始化参数init-param
        System.out.println("初始化参数username的值是;" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是;" + servletConfig.getInitParameter("url"));
//        3、获取ServletContext对象
        System.out.println(servletConfig.getServletContext());
    }
```
这里自己看一下上面的配置联系此处代码，就不多写了。
好吧我还是拿来了：
`HelloServlet程序的别名是:HelloServlet
初始化参数username的值是;root
初始化参数url的值是;jdbc:mysql://localhost:3306/test
org.apache.catalina.core.ApplicationContextFacade@4afa3fb9`

##### 3、我们在HelloServletHttp里的init方法里加上使用servletconfig的使用方法测试
```java
  @Override
    public void init(ServletConfig config) throws ServletException {
    /*这里注意一下,如果我们是在重写的是继承HttpServlet的类的init方法时，
     * 如果不带上super.init(config);这句话，就会覆盖掉HttpServlet继承的
     * GenericServlet里创建的servletconfig，相当于没有创建这个servletconfig对象
     * 从而导致调用servletconfig里的方法时发生空指针异常*/
        super.init(config);
        System.out.println("重写了init初始化方法,做了一些工作");
    }
```

### 4、ServletContext 类
介绍
```text
1、ServletContext 是一个接口，它表示 Servlet 上下文对象
2、一个 web 工程(不是工程，是web工程)，只有一个 ServletContext 对象实例。
3、ServletContext 对象是一个域对象。
什么是域对象?
域对象，是可以像 Map 一样存取数据的对象，叫域对象。
这里的域指的是存取数据的操作范围，整个 web 工程。
存数据 取数据 删除 数据
Map      put()         get()         remove()
域对象 setAttribute() getAttribute() removeAttribute();
也就是说，一看到什么什么Attribute就是域对象
4、ServletContext 是在 web 工程部署启动的时候创建。在 web
```
#### ServletContext 类的四个作用
```text
1、获取 web.xml 中配置的上下文参数 context-param
2、获取当前的工程路径，格式: /工程路径
3、获取工程部署后在服务器硬盘上的绝对路径
4、像 Map 一样存取数据
```
##### 1、根据作用1先在web.xml里加一下信息
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--记住这里是加在web.xml里的而不是某个servlet里，因为这是全局范围的参数-->
    <!--context-param 是上下文参数(它属于整个 web 工程)-->
    <context-param>
        <param-name>username</param-name>
        <param-value>context</param-value>
    </context-param>
    <!--context-param 是上下文参数(它属于整个 web 工程)-->
    <context-param>
        <param-name>password</param-name>
        <param-value>root</param-value>
    </context-param>
```
##### 2、我们在HelloServletHttp里的doGet方法里加上使用servletcontext的使用方法测试
```java
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、获取web.xml中配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext();
        //或者调用genericservlet里的方法 ServletContext context = getServletContext();

        String username = context.getInitParameter("username");
        System.out.println("context-param参数username的值是:" + username);
        System.out.println("context-param参数password的值是:" + context.getInitParameter("password"));
//        2、获取当前的工程路径，格式: /工程路径
        System.out.println( "当前工程路径:" + context.getContextPath() );
//        3、获取工程部署后在服务器硬盘上的绝对路径
        /**
         *  / 斜杠被服务器解析地址为:http://ip:port/工程名/  映射到IDEA代码的web目录<br/>
         */
        System.out.println("工程部署的路径是:" + context.getRealPath("/"));
        System.out.println("工程下css目录的绝对路径是:" + context.getRealPath("/css"));
        System.out.println("工程下imgs目录1.jpg的绝对路径是:" + context.getRealPath("/imgs/1.jpg"));
    }
```
如果我在a.html里点击了HelloServletHttp里的doGet按钮，上面的输出为
```text
context-param参数username的值是:context
context-param参数password的值是:root
当前工程路径:/servlet01
工程部署的路径是:D:\idea_java_projects\javaweb123\servlet01\target\ssm-1.0-SNAPSHOT\
工程下css目录的绝对路径是:D:\idea_java_projects\javaweb123\servlet01\target\ssm-1.0-SNAPSHOT\css
工程下imgs目录1.jpg的绝对路径是:D:\idea_java_projects\javaweb123\servlet01\target\ssm-1.0-SNAPSHOT\imgs\1.jpg
```

##### 3、像 Map 一样存取数据
在HelloServletHttp里的doGet加上以下代码
```java
 System.out.println("保存之前获取 key1的值是:"+ context.getAttribute("key1"));

        /*
         * 这里加的数据除了服务器关闭重启，数据都不会消失
         */
        context.setAttribute("key1", "value1");
        

        System.out.println("保存之后获取域数据key1的值是:"+ context.getAttribute("key1"));
```
这里其实在别的servlet程序里都能get获取到，我不演示了
下面是输出的结果
```text
保存之前获取 key1的值是:null
保存之后获取域数据key1的值是:value1
```