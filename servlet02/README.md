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

## servlet02
### 1、HttpServletRequest 类
介绍
```text
每次只要有请求进入 Tomcat 服务器，Tomcat 服务器就会把请求过来的 HTTP 协议信息解析好封装到 Request 对象中。
然后传递到 service 方法（doGet 和 doPost）中给我们使用。我们可以通过 HttpServletRequest 对象，获取到所有请求的
信息。
```
#### 1、HttpServletRequest 类的常用方法
```java
i. getRequestURI() 获取请求的资源路径
ii. getRequestURL() 获取请求的统一资源定位符（绝对路径）
iii. getRemoteHost() 获取客户端的 ip 地址
iv. getHeader() 获取请求头
v. getParameter() 获取请求的参数
vi. getParameterValues() 获取请求的参数（多个值的时候使用）
vii. getMethod() 获取请求的方式 GET 或 POST
viii. setAttribute(key, value); 设置域数据
ix. getAttribute(key); 获取域数据
x. getRequestDispatcher() 获取请求转发对象
演示：
public class RequestAPIServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        i.getRequestURI()					获取请求的资源路径
        System.out.println("URI => " + req.getRequestURI());
//        ii.getRequestURL()					获取请求的统一资源定位符（绝对路径）
        System.out.println("URL => " + req.getRequestURL());
//        iii.getRemoteHost()				获取客户端的ip地址
        /**
         * 在IDEA中，使用localhost访问时，得到的客户端 ip 地址是 ===>>> 127.0.0.1<br/>
         * 在IDEA中，使用127.0.0.1访问时，得到的客户端 ip 地址是 ===>>> 127.0.0.1<br/>
         * 在IDEA中，使用 真实ip 访问时，得到的客户端 ip 地址是 ===>>> 真实的客户端 ip 地址<br/>
         */
        System.out.println("客户端 ip地址 => " + req.getRemoteHost());
//        iv.getHeader()						获取请求头
        System.out.println("请求头User-Agent ==>> " + req.getHeader("User-Agent"));
//        vii.getMethod()					获取请求的方式GET或POST
        System.out.println( "请求的方式 ==>> " + req.getMethod() );
    }
}

```
#### 2、获取请求参数

##### 1、第一步先建表单
建立form.html在webapp里
里面包含用户名密码和多选的兴趣爱好选项
```html
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/07_servlet/parameterServlet" method="post">
        用户名：<input type="text" name="username"><br/>
        密码：<input type="password" name="password"><br/>
        兴趣爱好：<input type="checkbox" name="hobby" value="cpp">C++
        <input type="checkbox" name="hobby" value="java">Java
        <input type="checkbox" name="hobby" value="js">JavaScript<br/>
        <input type="submit">
    </form>
</body>
</html>
```
##### 2、第二步，写java代码
```java
public class ParameterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-------------doGet------------");

        // 获取请求参数
        String username = req.getParameter("username");

        //这里处理中文乱码问题：1 先以iso8859-1进行编码 ,2 再以utf-8进行解码
        username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        // 获取请求参数
        String password = req.getParameter("password");
        //多项要用getParameterValues();这个方法
        String[] hobby = req.getParameterValues("hobby");

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        System.out.println("兴趣爱好：" + Arrays.asList(hobby));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符集为UTF-8，从而解决post请求的中文乱码问题
        // 也要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");

        System.out.println("-------------doPost------------");
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobby = req.getParameterValues("hobby");

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        System.out.println("兴趣爱好：" + Arrays.asList(hobby));
    }
}

```
#### 3、请求的转发
先配置两个servlet
```java
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数（办事的材料）查看
        String username = req.getParameter("username");
        System.out.println("在Servlet1（柜台1）中查看参数（材料）：" + username);

        // 给材料 盖一个章，并传递到Servlet2（柜台 2）去查看
        /*因为共享request域里的内容*/
        req.setAttribute("key1","柜台1的章");

        // 问路：Servlet2（柜台 2）怎么走
        /**
         * 请求转发必须要以斜杠打头，/ 斜杠表示地址为：http://ip:port/工程名/ , 映射到IDEA代码的web目录<br/>
         *
         */
        /*
                这里的requestDispatcher就是转发路径
         */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("http://www.baidu.com");

        // 走向Sevlet2（柜台 2）
           /*
                这里的forward就是转发了，然后把请求响应都传给上面的requestDispatcher输入的
                servlet，比如我们这就是servlet2
         */
        requestDispatcher.forward(req,resp);

    }
}

```
```java
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数（办事的材料）查看
        String username = req.getParameter("username");
        System.out.println("在Servlet2（柜台2）中查看参数（材料）：" + username);

        // 查看 柜台1 是否有盖章
        Object key1 = req.getAttribute("key1");
        System.out.println("柜台1是否有章：" + key1);

        // 处理自己的业务
        System.out.println("Servlet2 处理自己的业务 ");
    }
}
```
在form里加上访问servlet1资源按钮,然后通过servlet1转发到servlet2处理业务
```html
<br/>
<form action="http://localhost:8080/servlet02/servlet1" method="get"><!--因为我们是重写的doget所以改成doget-->
    在柜台1盖章的内容：<input type="text" name="username"><br/>
</form>
```
然后如果我们在表单里写的是`放行！`
那输出就是
```text
在Servlet1（柜台1）中查看参数（材料）：放行！
在Servlet2（柜台2）中查看参数（材料）：放行！
柜台1是否有章：柜台1的章
Servlet2 处理自己的业务 
```

### 2、web里/斜杠的不同意义
```text
web 中 / 斜杠的不同意义
在 web 中 / 斜杠 是一种绝对路径。
/ 斜杠 如果被浏览器解析，得到的地址是：http://ip:port/
在我们写的html里就是被浏览器解析的，如下代码
<a href="/">斜杠</a>
/ 斜杠 如果被服务器解析，得到的地址是：http://ip:port/工程路径
在我们写的java文件和web.xml文件里就是被服务器解析的，如下代码
1、<url-pattern>/servlet1</url-pattern>
2、servletContext.getRealPath(“/”);
3、request.getRequestDispatcher(“/”);
特殊情况： response.sendRediect(“/”); 把斜杠发送给浏览器解析。得到 http://ip:port/
```

### 3、.HttpServletResponse 类
介绍
```text
HttpServletResponse 类和 HttpServletRequest 类一样。每次请求进来，Tomcat 服务器都会创建一个 Response 对象传
递给 Servlet 程序去使用。HttpServletRequest 表示请求过来的信息，HttpServletResponse 表示所有响应的信息，
我们如果需要设置返回给客户端的信息，都可以通过 HttpServletResponse 对象来进行设置
字节流 getOutputStream(); 常用于下载（传递二进制数据）
字符流 getWriter(); 常用于回传字符串（常用）
两个流同时只能使用一个。
使用了字节流，就不能再使用字符流，反之亦然，否则就会报错。
```
#### 如何往客户端回传数据
```java
public class ResponseIOServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
IOException {
    
    //解决响应乱码
    // 它会同时设置服务器和客户端都使用 UTF-8 字符集，还设置了响应头
// 此方法一定要在获取流对象之前调用才有效
    resp.setContentType("text/html; charset=UTF-8");
// 要求 ： 往客户端回传 字符串 数据
PrintWriter writer = resp.getWriter();
writer.write("response's content!!!耶耶耶");
/*
        会在浏览器显示`response's content!!!耶耶耶`
 */
//设置重定向，1、不共享request域数据，访问不了WEB-INF里的玩意
    resp.sendRedirect("http://localhost:8080");
}
}
```