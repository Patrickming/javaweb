# jQuery


## 1、入门程序
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--首先这里要导入jQuery文件--><!--..表示上一层目录也就是Demo层-->
<script type="text/javascript" src="../script/jquery-1.7.2.js"></script>
<script type="text/javascript">

	//使用$()代替window.onload
	$(function(){
		//使用选择器获取按钮对象，随后绑定单击响应函数
        //这里的"#btnId"取代下面的第1步
		$("#btnId").click(function(){
			//弹出Hello
			alert('Hello');
		});
	});
    /*这里对比没用jQuery的事件*/
	// 动态注册onclick事件
	window.onload = function () {
		// 1 获取标签对象
		var btnObj = document.getElementById("btn01");
		// 2 通过标签对象.事件名 = function(){}
		btnObj.onclick = function () {
			alert("Hello");
		}
	}
</script>
</head>
<body>
	<button id="btnId">SayHello</button>
</body>
</html>
```
以上网页，点击绑定的按钮后会alert一个`hello`
```text
常见问题？
1、使用 jQuery 一定要引入 jQuery 库吗？ 答案： 是，必须
2、jQuery 中的$到底是什么？ 答案： 它是一个函数
3、怎么为按钮添加点击响应函数的？ 答案：
1、使用 jQuery 查询到标签对象
2、使用标签对象.click( function(){} );
```
## 2、jQuery的核心函数
$ 是 jQuery 的核心函数，能完成 jQuery 的很多功能。$()就是调用$这个函数
用法：
```text
1、传入参数为 [ 函数 ] 时：
表示页面加载完成之后。相当于 window.onload = function(){}
2、传入参数为 [ HTML 字符串 ] 时：
会对我们创建这个 html 标签对象
3、传入参数为 [ 选择器字符串 ] 时：
$(“#id 属性值”); id 选择器，根据 id 查询标签对象
$(“标签名”); 标签名选择器，根据指定的标签名查询标签对象
$(“.class 属性值”); 类型选择器，可以根据 class 属性查询标签对象
4、传入参数为 [ DOM 对象 ] 时：
会把这个 dom 对象转换为 jQuery 对象
```
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="../script/jquery-1.7.2.js"></script>
    <script type="text/javascript">

        //核心函数的4个作用
        //作用1、传入参数为[函数]时：在文档加载完成后执行这个函数
        $(function () {
            // alert("页面加载完成之后，自动调用");

            //作用2、传入参数为[HTML的字符串]时：根据这个字符串创建元素节点对象
            //这里就直接在body里创建了<div>
            $("    <div>" +
                    "        <span>div-span1</span>" +
                    "        <span>div-span2</span>" +
                    "    </div>").appendTo("body");

            
            //传入参数为[选择器字符串]时：根据这个字符串查找元素节点对象
            var btnObj = document.getElementById("btn01");
            //1.查询到id为btn01的节点对象，也就是<button id="btn01">按钮1</button>这个按钮
            var $btnObj = $("#btn01")
            //2.查询所有<button>这个标签对象.length1就是返回找到了多少个
             alert($("button").length);
            
            //作用4、传入参数为[DOM对象]时：将DOM对象包装为jQuery对象返回
             alert( $(btnObj) );

            // alert( $("<h1></h1>") );
            alert($("button"));

        });


      
        

    </script>
</head>
<body>
<button id="btn01">按钮1</button>
<button>按钮2</button>
<button>按钮3</button>
</body>
</html>
```
## 3、jQuery 对象和 dom 对象区分

### 1、什么是 jQuery 对象，什么是 dom 对象
```text
Dom 对象
1.通过 getElementById()查询出来的标签对象是 Dom 对象
2.通过 getElementsByName()查询出来的标签对象是 Dom 对象
3.通过 getElementsByTagName()查询出来的标签对象是 Dom 对象
4.通过 createElement() 方法创建的对象，是 Dom 对象
DOM 对象 Alert 出来的效果是：[object HTML 标签名 Element]
jQuery 对象
5.通过 JQuery 提供的 API 创建的对象，是 JQuery 对象
6.通过 JQuery 包装的 Dom 对象，也是 JQuery 对象
7.通过 JQuery 提供的 API 查询到的对象，是 JQuery 对象
jQuery 对象 Alert 出来的效果是：[object Object]
```

### 2、问题：jQuery 对象的本质是什么？
```text
jQuery 对象是 dom 对象的数组 + jQuery 提供的一系列功能函数。
```
### 3、jQuery 对象和 Dom 对象使用区别
```text
jQuery 对象不能使用 DOM 对象的属性和方法
DOM 对象也不能使用 jQuery对象的属性和方法
```
### 4、Dom 对象和 jQuery 对象互转
转换例子：
```html
<script>
    //这里是document.getElementById("testDiv")查询到的DOM对象转化成jQuery对象
    alert( $(document.getElementById("testDiv")) );
    
    //这里是把刚刚转化成的jQuery对象根据下标转化成DOM对象
    alert( $(document.getElementById("testDiv"))[0] );
    
</script>

```
#### 1、dom 对象转化为 jQuery 对象（*重点）
1、先有 DOM 对象
2、$( DOM 对象 ) 就可以转换成为 jQuery 对象
#### 2、jQuery 对象转为 dom 对象（*重点）
1、先有 jQuery 对象
2、jQuery 对象[下标]取出相应的 DOM 对象





## 4、选择器
在文件01选择器，在笔记里只展示jQuery的内容
这里面包含1~5题，然后根据我们写的五道题就绑定到了相应按钮上
点哪个就是哪个功能
**其实这里的所有选择器用法都可以查官方文档**
**这里只展示基本选择器里的代码，其他的自己看下源文件**
**可以在源文件里把网页打开测试一下就很好懂了**
```html
<script type="text/javascript" src="../script/jquery-1.7.2.js"></script>
		<script type="text/javascript">
			
				$(function () {
					//1.选择 id 为 one 的元素 "background-color","#bbffaa"
                    //这里设置id 为 btn1 的元素（这里是按钮）点击为什么什么功能
					$("#btn1").click(function () {
						// css() 方法 可以设置和获取样式
                        //这里设置id为one的元素背景颜色变为#bbffaa
						$("#one").css("background-color","#bbffaa");
					});


					//2.选择 class 为 mini 的所有元素
					$("#btn2").click(function () {
						$(".mini").css("background-color","#bbffaa");
					});

					//3.选择 元素名是 div 的所有元素
					$("#btn3").click(function () {
                        //元素名是 div 的所有元素
						$("div").css("background-color","#bbffaa");
					});

					//4.选择所有的元素
					$("#btn4").click(function () {
						$("*").css("background-color","#bbffaa");
					});

					//5.选择所有的 span 元素和id为two的元素
					$("#btn5").click(function () {
                        //复合一下，两个条件
						$("span,#two").css("background-color","#bbffaa");
					});

				});

		</script>
```

## 5、jQuery 事件操作
### 1、$( function(){} );和window.onload = function(){}的区别？
#### 1、他们分别是在什么时候触发？
1、jQuery 的页面加载完成之后是浏览器的内核解析完页面的标签创建好 DOM 对象之后就会马上执行。
2、原生 js 的页面加载完成之后，除了要等浏览器内核解析完标签创建好 DOM 对象，还要等标签显示时需要的内容加载
完成。
举个例子：
假设你要去打架
jQuery在知道你打架需要一根木棍的时候就冲上去干了
而原生在知道你要一根木棍后，还要再把木棍找出来，才去干架
所以jQuery比原生js快
#### 2、他们触发的顺序？
1、jQuery 页面加载完成之后先执行
2、原生 js 的页面加载完成之后
#### 3、他们执行的次数？
1、原生 js 的页面加载完成之后，只会执行最后一次的赋值函数。
2、jQuery 的页面加载完成之后是全部把注册的 function 函数，依次顺序全部执行。
```html
<script>
    window.onload = function(){
		//会在整个页面加载完毕之后调用
		alert("abc1")
	}
    window.onload = function(){
		//会在整个页面加载完毕之后调用
		alert("abc2")
	}

	$(function(){
		alert("edf1")
	}
    $(function(){
		alert("edf2")
	})
</script>
```
像是这里只会弹出一次abc2，而edf1和edf2都会弹出

### 2、jQuery 中其他的事件处理方法：
```text
click() 它可以绑定单击事件，以及触发单击事件
mouseover() 鼠标移入事件
mouseout() 鼠标移出事件
bind() 可以给元素一次性绑定一个或多个事件。
one() 使用上跟 bind 一样。但是 one 方法绑定的事件只会响应一次。
unbind() 跟 bind 方法相反的操作，解除事件的绑定
live() 也是用来绑定事件。它可以用来绑定选择器匹配的所有元素的事件。哪怕这个元素是后面动态创建出
来的也有效
```
看源文件复习

### 3、事件的冒泡
也一样，复习看源文件+网页测试复习
#### 1什么是事件的冒泡？
事件的冒泡是指，父子元素同时监听同一个事件。当触发子元素的事件的时候，同一个事件也被传递到了父元素的事件里去
响应。
#### 2那么如何阻止事件冒泡呢？
在子元素事件函数体内，return false; 可以阻止事件的冒泡传递。

### 4、javaScript 事件对象
事件对象，是封装有触发的事件信息的一个 javascript 对象。
我们重点关心的是怎么拿到这个 javascript 的事件对象。以及使用。
如何获取呢 javascript 事件对象呢？
在给元素绑定事件的时候，在事件的 function( event ) 参数列表中添加一个参数，这个参数名，我们习惯取名为 event。
这个 event 就是 javascript 传递参事件处理函数的事件对象
```html
<script>
    //1.原生 javascript 获取 事件对象
    window.onload = function () {
        document.getElementById("areaDiv").onclick = function (event) {
            console.log(event);
        }
    }
    //2.jQuery 代码获取 事件对象
    $(function () {
        $("#areaDiv").click(function (event) {
            console.log(event);
        });
    });
    //3.使用 bind 同时对多个事件绑定同一个函数。怎么获取当前操作是什么事件。
    $("#areaDiv").bind("mouseover mouseout", function (event) {
        if (event.type == "mouseover") {
            console.log("鼠标移入");
        } else if (event.type == "mouseout") {
            console.log("鼠标移出");
        }
    });
</script>
```