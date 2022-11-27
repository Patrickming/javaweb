# JSON_Ajax

## 1、JSON
介绍
```text
JSON (JavaScript Object Notation) 是一种轻量级的数据交换格式。易于人阅读和编写。同时也易于机器解析和生成。JSON
采用完全独立于语言的文本格式，而且很多语言都提供了对 json 的支持（包括 C, C++, C#, Java, JavaScript, Perl, Python
等）。 这样就使得 JSON 成为理想的数据交换格式。
json 是一种轻量级的数据交换格式。
轻量级指的是跟 xml 做比较。
数据交换指的是客户端和服务器之间业务数据的传递格式。
```
### 1、JSON 在 JavaScript 中的使用。(客户端使用)
#### 1、json 的定义 和访问
json 是由键值对组成，并且由花括号（大括号）包围。每个键由引号引起来，键和值之间使用冒号进行分隔，
多组键值对之间进行逗号进行分隔。
json 本身是一个对象。
json 中的 key 我们可以理解为是对象中的一个属性。
json 中的 key 访问就跟访问对象的属性一样： json 对象.key
```html
<script>
// json的定义
			var jsonObj = {
				"key1":12,
				"key2":"abc",
				"key3":true,
				"key4":[11,"arr",false],
				//值也可以是一个json对象
				"key5":{
					"key5_1" : 551,
					"key5_2" : "key5_2_value"
				},
				//值也可以是一个json数组
				"key6":[{
					"key6_1_1":6611,
					"key6_1_2":"key6_1_2_value"
				},{
					"key6_2_1":6621,
					"key6_2_2":"key6_2_2_value"
				}]
			};

            alert(typeof(jsonObj));// object  json就是一个对象
            alert(jsonObj.key1); //12
            alert(jsonObj.key2); // abc
            alert(jsonObj.key3); // true
            alert(jsonObj.key4);// 得到数组[11,"arr",false]
             // json 中 数组值的遍历
            for(var i = 0; i < jsonObj.key4.length; i++) {
                alert(jsonObj.key4[i]);
            }
            alert(jsonObj.key5.key5_1);//551
            alert(jsonObj.key5.key5_2);//key5_2_value
            alert( jsonObj.key6 );// 得到json数组
</script>
```

#### 2、json 的两个常用方法
json 的存在有两种形式。
1：对象的形式存在，我们叫它 json 对象。
2：字符串的形式存在，我们叫它 json 字符串。
一般我们要操作 json 中的数据的时候，需要 json 对象的格式。
一般我们要在客户端和服务器之间进行数据交换的时候，使用 json 字符串。
JSON.stringify() 把 json 对象转换成为 json 字符串
JSON.parse() 把 json 字符串转换成为 json 对象
示例代码：
```html
<script>
// 把 json 对象转换成为 json 字符串
var jsonObjString = JSON.stringify(jsonObj); // 特别像 Java 中对象的 toString
alert(jsonObjString)
// 把 json 字符串。转换成为 json 对象
var jsonObj2 = JSON.parse(jsonObjString);
alert(jsonObj2.key1);// 12
alert(jsonObj2.key2);// abc
</script>
```

### 2、JSON 在 java 中的使用(服务器端使用)
#### 1、javaBean、List、Map 和 json 的互转
1. 建包pojo，建一个javabean`Person`
2. 建包json，建一个JsonTest.java
3. 在json包下建PersonListType.java和PersonMapType.java
```java
package com.xrm.json;
import com.google.gson.reflect.TypeToken;
import com.xrm.pojo.Person;

import java.util.ArrayList;

public class PersonListType extends TypeToken<ArrayList<Person>> {
}
```
```java
package com.xrm.json;
import com.google.gson.reflect.TypeToken;
import com.xrm.pojo.Person;

import java.util.HashMap;

public class PersonMapType extends TypeToken<HashMap<Integer, Person>> {
}
```
```java
package com.xrm.json;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xrm.pojo.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class JsonTest {

    /**
     *  1、javaBean和json的互转
     */
    @Test
    public void test1(){
        Person person = new Person(1,"国哥好帅!");
        // 创建Gson对象实例，这里Gson就是JSON，只不过是谷歌提供的所以叫Gson
        Gson gson = new Gson();
        // toJson方法可以把java对象转换成为json字符串
        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);
        // fromJson把json字符串转换回Java对象
        // 第一个参数是json字符串
        // 第二个参数是转换回去的Java对象类型
        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);
    }

    /**
     *  2、List和json的互转
     */
    @Test
    public void test2() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person(1, "国哥"));
        personList.add(new Person(2, "康师傅"));

        Gson gson = new Gson();

        // 把List转换为json字符串
        String personListJsonString = gson.toJson(personList);
        System.out.println(personListJsonString);

        List<Person> list = gson.fromJson(personListJsonString, new PersonListType().getType());
        System.out.println(list);
        Person person = list.get(0);
        System.out.println(person);
    }

    /**
     *  3、Map和json的互转
     */
    @Test
    public void test3(){
        Map<Integer,Person> personMap = new HashMap<>();

        personMap.put(1, new Person(1, "国哥好帅"));
        personMap.put(2, new Person(2, "康师傅也好帅"));

        Gson gson = new Gson();
        // 把 map 集合转换成为 json字符串
        String personMapJsonString = gson.toJson(personMap);
        System.out.println(personMapJsonString);

//        Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());
        Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new TypeToken<HashMap<Integer,Person>>(){}.getType());

        System.out.println(personMap2);
        Person p = personMap2.get(1);
        System.out.println(p);

    }


}

```


## 2、AJAX 请求
介绍
```text
AJAX 即“Asynchronous Javascript And XML”（异步 JavaScript 和 XML），是指一种创建交互式网页应用的网页开发
技术。
ajax 是一种浏览器通过 js 异步发起请求，局部更新页面的技术。
Ajax 请求的局部更新，浏览器地址栏不会发生变化
局部更新不会舍弃原来页面的内容
```

### jQuery 中的 AJAX 请求

#### 1、$.ajax 方法
url 表示请求的地址
type 表示请求的类型 GET 或 POST 请求
data 表示发送给服务器的数据
格式有两种：
一：name=value&name=value
二：{key:value}
success 请求成功，响应的回调函数
dataType 响应的数据类型
常用的数据类型有：
text 表示纯文本
xml 表示 xml 数据
json 表示 json 对象

```html
<script type="text/javascript">
    $(function(){
        // ajax请求
        $("#ajaxBtn").click(function(){
            $.ajax({
                url:"http://localhost:8080/JSON_Ajax/ajaxServlet",
                // 或者这么写data:"action=jQueryAjax",
                data:{action:"jQueryAjax"},
                type:"GET",
                //成功的回调函数  这里的data就是一个dataType为 "json"的对象
                success:function (data) {
                    // alert("服务器返回的数据是：" + data);
                    // var jsonObj = JSON.parse(data);
                    //body里有个<div id="msg">来显示我们服务器返回的消息
                    $("#msg").html(" ajax 编号：" + data.id + " , 姓名：" + data.name);
                },
                dataType : "json"
            });
        });
```
在JsonTest里的jQueryAjax回调函数
```java
 protected void jQueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  jQueryAjax == 方法调用了");
        Person person = new Person(1, "国哥");
        // json格式的字符串
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);

        resp.getWriter().write(personJsonString);
    }
```

#### 2、$.get 方法和$.post 方法
不说了，看源文件


#### 3、表单序列化 serialize()
serialize()可以把表单中所有表单项的内容都获取到，并以 name=value&name=value 的形式进行拼接。

```html
<script>
    // ajax请求, 这里设置提交表单按钮，然后发送的文件，这里不会改变和刷新跳转页面。
    //serialize()可以把表单中所有表单项的内容都获取到，并以 name=value&name=value 的形式进行拼接。
    $("#submit").click(function(){
        // 把参数序列化                                                       //这里注意要加上连字符&//这里是关键，把id是form01的表单序列化发送服务器
        $.getJSON("http://localhost:8080/JSON_Ajax/ajaxServlet","action=jQuerySerialize&" + $("#form01").serialize(),function (data) {
            $("#msg").html(" Serialize 编号：" + data.id + " , 姓名：" + data.name);
            //客户端（网页）输出:   Serialize 编号：1 , 姓名：国哥
        });
    });

```
```java
  protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  jQuerySerialize   == 方法调用了");

        System.out.println("用户名：" + req.getParameter("username"));
        System.out.println("密码：" + req.getParameter("password"));
        //服务器（java控制台）输出:     用户名：xrm
                                       密码：123

        Person person = new Person(1, "国哥");
        // json格式的字符串
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);

        resp.getWriter().write(personJsonString);
    }
```

然后我在网页上用户名输入xrm，密码输入123
然后：
```text
客户端（网页）输出:   Serialize 编号：1 , 姓名：国哥
服务器（java控制台）输出:     用户名：xrm
                            密码：123
```