## jsp内置对象

jsp内置对象是由web容器来管理，不需要创建对象，可以直接对象调用其方法；

request：用于处理客户端发送过来的请求(java后台获取到页面传递过来的数据)

response:用于响应客户端请求并向客户端输出信息

session:一个会话就是在一段时间内，一个客户端与Web服务器的一连串相关的交互过程；数据是保存在服务器的内存里；

out:想客户端输出信息

application:(应用程序)，作用域整个应用程序；多个用户都可以共享到application里面的数据；application直到tomcat web服务器关闭才会销毁；

page:

pageContext: pageContext的作用域是在当前页面，其他页面获取不到

exception:

config:



### request常用方法

| 方法名称                                     | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| String getParameter(String name)         | 根据表单组件名称获取提交数据                           |
| String [] getParameterValues(String name) | 获取表单组件对应多个值时的请求数据                        |
| void setCharacterEncoding(String charset) | 指定每个请求的编码                                |
| RequestDispatcher getRequestDispatcher(String path) | 返回一个RequestDispatcher对象，该对象的forward()方法用于转发请求 |

##### 中文乱码：

##### 1.post提交方式

```javascript
   //  如果传递过来的是中文，会乱码(因为web容器默认编码格式是8859-1格式，你需要先把它转换成utf-8格式再去获取到)
	request.setCharacterEncoding("utf-8"); 
	String userName= request.getParameter("userName");
	String userPwd= request.getParameter("userPwd");
	
```

##### 2.get提交方式

######    a:

```javascript
       // 读取用户名和密码
        String name = request.getParameter("name");
        // 对请求数据进行字符编码
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");

```



######   b:

```javascript
在Tomcat目录结构\conf\server.xml中设置字符集
<Connector  port="8080"  protocol="HTTP/1.1"
connectionTimeout="20000"
redirectPort="8443"  URIEncoding="UTF-8"

```

 



### 转发和重定向区别：

转发：forward:

**是在服务器端发挥作用**，将同一个请求地址在服务器端资源之间进行传递；A页面转发到B页面,B页面能够获取到A页面的数据；

重定向:redirect 

**在客户端发挥作用**，重新向客户端发送一个新的URL请求跳转页面,A页面跳转到B页面，B页面获取不到A页面的数据；



### **session**会话机制：

一个会话就是在一段时间内，一个客户端与Web服务器的一连串相关的交互过程；包括浏览器多次给服务器发送请求；第一次访问请求，会讲session保存在服务器端，sessionID保存在你的客户端，当后面的请求去访问数据的时候先根据你客户端sessionID去服务器端找到session对象并且找到session里面存储的数据；当你的浏览器多个窗口访问session是共享数据，当浏览器关闭，session就被销毁；session是给一个用户的；APPlication是整个web服务器共享的；



##### session常用方法：

![](images\session.jpg)



##### session失效：

###### 方式1 

 session.invalidate(); 设置立即失效

###### 方式2：

```javascript
//设置session的失效时间 10分钟
session.setMaxInactiveInterval(10*60);
```

###### 方式3:

在项目的web.xml中配置或者在tomcat容器的conf/web.xml中配置

```javascript
<!-- 设置该项目的session会话有效时间10分钟 -->
  <session-config>
  	<session-timeout>10</session-timeout>
  </session-config>
```





### 转发和重定向区别详解

        作为一名java web开发的程序员，在使用servlet/jsp的时候，我们必须要知道实现页面跳转的两种方式的区别和联系：即转发和重定向的区别。

      1、request.getRequestDispatcher().forward()方法,只能将请求转发给同一个WEB应用中的组件；而response.sendRedirect() 方法不仅可以重定向到当前应用程序中的其他资源，还可以重定向到同一个站点上的其他应用程序中的资源，甚至是使用绝对URL重定向到其他站点的资源。

如果传递给response.sendRedirect()方法的相对URL以“/”开头，它是相对于整个WEB站点的根目录；如果创建request.getRequestDispatcher()对象时指定的相对URL以“/”开头，它是相对于当前WEB应用程序的根目录。

      2、重定向访问过程结束后，浏览器地址栏中显示的URL会发生改变，由初始的URL地址变成重定向的目标URL；请求转发过程结束后，浏览器地址栏保持初始的URL地址不变。

      3、HttpServletResponse.sendRedirect方法对浏览器的请求直接作出响应，响应的结果就是告诉浏览器去重新发出对另外一个URL的访问请求，这个过程好比有个绰号叫“浏览器”的人写信找张三借钱，张三回信说没有钱，让“浏览器”去找李四借，并将李四现在的通信地址告诉给了“浏览器”。于是，“浏览器”又按张三提供通信地址给李四写信借钱，李四收到信后就把钱汇给了“浏览器”。

由此可见，重定向的时候，“浏览器”一共发出了两封信和收到了两次回复，“浏览器”也知道他借到的钱出自李四之手。

request.getRequestDispatcher().forward()方法在服务器端内部将请求转发给另外一个资源，浏览器只知道发出了请求并得到了响应结果，并不知道在服务器程序内部发生了转发行为。这个过程好比绰号叫“浏览器”的人写信找张三借钱，张三没有钱，于是张三找李四借了一些钱，甚至还可以加上自己的一些钱，然后再将这些钱汇给了“浏览器”。

由此可见，转发的时候，“浏览器”只发 出了一封信和收到了一次回复，他只知道从张三那里借到了钱，并不知道有一部分钱出自李四之手。

       4、request.getRequestDispatcher().forward()方法的调用者与被调用者之间共享相同的request对象和response对象，它们属于同一个访问请求和响应过程；

而response.sendRedirect()方法调用者与被调用者使用各自的request对象和response对象，它们属于两个独立的访问请求和响应过程。对于同一个WEB应用程序的内部资源之间的跳转，特别是跳转之前要对请求进行一些前期预处理，并要使用HttpServletRequest.setAttribute方法传递预处理结果，那就应该使用request.getRequestDispatcher().forward()方法。不同WEB应用程序之间的重定向，特别是要重定向到另外一个WEB站点上的资源的情况，都应该使用response.sendRedirect()方法。

        5、无论是request.getRequestDispatcher().forward()方法，还是response.sendRedirect()方法，在调用它们之前，都不能有内容已经被实际输出到了客户端。如果缓冲区中已经有了一些内容，这些内容将被从缓冲区中。



转发和重定向的图解

![](images\1.png)

两种跳转获得对象的方式
//获得转发对象getRequestDispatcher()
HttpServletRequest(httpServletRequest).getRequestDispatcher
ServletContext.getRequestDispatcher();

//获得重定向对象sendRedirect()
HttpServletResponse(httpServletResponse).sendRedirect();
转发和跳转的小结
      1、转发使用的是getRequestDispatcher()方法;重定向使用的是sendRedirect();

      2、转发：浏览器URL的地址栏不变。重定向：浏览器URL的地址栏改变；

      3、转发是服务器行为，重定向是客户端行为；

      4、转发是浏览器只做了一次访问请求。重定向是浏览器做了至少两次的访问请求；

      5、转发2次跳转之间传输的信息不会丢失，重定向2次跳转之间传输的信息会丢失（request范围）。

转发和重定向的选择
      1、重定向的速度比转发慢，因为浏览器还得发出一个新的请求，如果在使用转发和重定向都无所谓的时候建议使用转发。

      2、因为转发只能访问当前WEB的应用程序，所以不同WEB应用程序之间的访问，特别是要访问到另外一个WEB站点上的资源的情况，这个时候就只能使用重定向了。

转发和重定向的应用场景
       在上面我已经提到了，转发是要比重定向快，因为重定向需要经过客户端，而转发没有。有时候，采用重定向会更好，若需要重定向到另外一个外部网站，则无法使用转发。另外，重定向还有一个应用场景：避免在用户重新加载页面时两次调用相同的动作。

       例如，当提交产品表单的时候，执行保存的方法将会被调用，并执行相应的动作；这在一个真实的应用程序中，很有可能将表单中的所有产品信息加入到数据库中。但是如果在提交表单后，重新加载页面，执行保存的方法就很有可能再次被调用。同样的产品信息就将可能再次被添加，为了避免这种情况，提交表单后，你可以将用户重定向到一个不同的页面，这样的话，这个网页任意重新加载都没有副作用；

       但是，使用重定向不太方便的地方是，使用它无法将值轻松地传递给目标页面。而采用转发，则可以简单地将属性添加到Model,使得目标视图可以轻松访问。由于重定向经过客户端，所以Model中的一切都会在重定向时丢失。但幸运的是，在Spring3.1版本以后，我们可以通过Flash属性，解决重定向时传值丢失的问题。

       要使用Flash属性，必须在Spring MVC的配置文件中添加一个<annotation-driven/>。然后，还必须再方法上添加一个新的参数类型：org.springframework.web.servlet.mvc.support.RedirectAttributes。

       如下所示：

@RequestMapping(value="saveProduct",method=RequestMethod.POST)
public String saveProduct(ProductForm productForm,RedirectAttributes redirectAttributes){

```
 //执行产品保存的业务逻辑等
```

```
 //传递参数
   redirectAttributes.addFlashAttribute("message","The product is saved successfully");
   
 //执行重定向
  return "redirect:/……";
```







### sessionid如何产生？由谁产生？保存在哪里？



```javascript
sessionid是一个会话的key，浏览器第一次访问服务器会在服务器端生成一个session，有一个sessionid和它对应。tomcat生成的sessionid叫做jsessionid。

session在访问tomcat 服务器HttpServletRequest的getSession(true)的时候 创建，tomcat的ManagerBase类提供创建sessionid的方法： 随机数+时间+jvmid；

存储在服务器的内存中，tomcat的StandardManager类将session 存储在内存中，也可以持久化到file，数据库，memcache，redis等。 客户端只保存sessionid到cookie中，而不会保存session，session销毁只能通过invalidate或超时，关掉浏览器并不会关闭session。

那么Session在何时创建呢？当然还是在服务器端程序运行的过程中创建的，不同语言实现的应用程序有不同创建Session的方法，而在Java中是通过调用HttpServletRequest的getSession方法（使用true作为参数）创建的。在创建了Session的同时，服务器会为该Session生成唯一的Session id，而这个Session id在随后的请求中会被用来重新获得已经创建的Session；在Session被创建之后，就可以调用Session相关的方法往Session中增加内容了，而这些内容只会保存在服务器中，发到客户端的只有Session id；当客户端再次发送请求的时候，会将这个Session id带上，服务器接受到请求之后就会依据Session id找到相应的Session，从而再次使用之。

创建：sessionid第一次产生是在直到某server端程序调用 HttpServletRequest.getSession(true)这样的语句时才被创建。

删除：超时；程序调用HttpSession.invalidate()；程序关闭；

session存放在哪里：服务器端的内存中。不过session可以通过特殊的方式做持久化管理（memcache，redis）。

session的id是从哪里来的，sessionID是如何使用的：当客户端第一次请求session对象时候，服务器会为客户端创建一个session，并将通过特殊算法算出一个session的ID，用来标识该session对象

session会因为浏览器的关闭而删除吗？

不会，session只会通过上面提到的方式去关闭。

 

下面是tomcat中session的创建：

ManagerBase是所有session管理工具类的基类，它是一个抽象类，所有具体实现session管理功能的类都要继承这个类，该类有一个受保护的方法，该方法就是创建sessionId值的方法：

（ tomcat的session的id值生成的机制是一个随机数加时间加上jvm的id值，jvm的id值会根据服务器的硬件信息计算得来，因此不同jvm的id值都是唯一的），

StandardManager类是tomcat容器里默认的session管理实现类，

它会将session的信息存储到web容器所在服务器的内存里。

PersistentManagerBase也是继承ManagerBase类，它是所有持久化存储session信息的基类，PersistentManager继承了PersistentManagerBase，但是这个类只是多了一个静态变量和一个getName方法，目前看来意义不大， 对于持久化存储session，tomcat还提供了StoreBase的抽象类，它是所有持久化存储session的基类，另外tomcat还给出了文件存储FileStore和数据存储JDBCStore两个实现。

浏览器禁用cookie后，怎么使用session，解决方案:

sessionid是存储在cookie中的，解决方案如下：

Session URL重写，保证在客户端禁用或不支持COOKIE时，仍然可以使用Session

session机制。session机制是一种服务器端的机制，服务器使用一种类似于散列表的结构（也可能就是使用散列表）来保存信息。

当程序需要为某个客户端的请求创建一个session时，服务器首先检查这个客户端的请求里是否已包含了一个session标识（称为session id），如果已包含则说明以前已经为此客户端创建过session，服务器就按照session id把这个session检索出来使用（检索不到，会新建一个），如果客户端请求不包含session id，则为此客户端创建一个session并且生成一个与此session相关联的session id，session id的值应该是一个既不会重复，又不容易被找到规律以仿造的字符串，这个session id将被在本次响应中返回给客户端保存。 保存这个session id的方式可以采用cookie，这样在交互过程中浏览器可以自动的按照规则把这个标识发挥给服务器。一般这个cookie的名字都是类似于 SEEESIONID。但cookie可以被人为的禁止，则必须有其他机制以便在cookie被禁止时仍然能够把session id传递回服务器。 经常被使用的一种技术叫做URL重写，就是把session id直接附加在URL路径的后面。还有一种技术叫做表单隐藏字段。就是服务器会自动修改表单，添加一个隐藏字段，以便在表单提交时能够把session id传递回服务器。比如：

<form name=”"testform”" action=”"/xxx”"> <input type=”"hidden”" name=”"jsessionid”" value=”"ByOK3vjFD75aPnrF7C2HmdnV6QZcEbzWoWiBYEnLerjQ99zWpBng!-145788764″”> <input type=”"text”"> </form>

URL重写：

http://www.test.com/test;jsessionid=ByOK3vjFD75aPnrF7C2HmdnV6QZcEbzWoWiBYEnLerjQ99zWpBng!-145788764

```



##### cookie：

cookie是Web服务器保存在客户端的一系列文本信息

```javascript
Cookie的应用举例
 1、保持用户的登录状态：不安全
  将用户的信息保存到Cookie中，并发送给浏览器，并且将有效时间设置为一个较长的时间，这样浏览器在以后访问网站时，都会带着该Cookie，服务器以此来辨识用户，用户就不再需要输入用户名和密码等信息。但是Cookie是纯文本的，很容易被截获，所以我们在使用Cookie保存敏感信息时一般都需要进行加密。
 2、广告的精确推送
  我们在使用百度搜索时，它会将我们搜索的关键字之类的信息保存到Cookie中，当我们访问带有百度广告的链接时，该Cookie信息会自动发送给Baidu，百度就可以根据Cookie的信息给你推送广告
 3、保存用户名：Taobao
  一旦用户登录成功以后，下次再登录时，直接将Cookie中的用户名读取并显示出来，这样用户就不需要再次输入用户名，只输入密码即可。
```

什么是cookie？

```javascript
Cookie是HTTP协议的规范之一，它是服务器和客户端之间传输的小数据
首先由服务器通过响应头把Cookie传输给客户端，客户端会将Cookie保存起来
当客户端再次请求同一服务器时，客户端会在请求头中添加该服务器保存的Cookie，发送给服务器
Cookie就是服务器保存在客户端的数据
Cookie就是一个键值对
```



![](images\cookie.png)

cookie的失效时间:

```javascript
如果不设置cookie过期时间，则表示这个cookie的生命期为浏览器会话期间，只要关闭浏览器窗口，cookie就消失了。这种生命期为浏览器会话期的 cookie被称为会话cookie。会话cookie一般不存储在硬盘上而是保存在内存里，当然这种行为并不是规范规定的。如果设置了过期时间，浏览器就会把cookie保存到硬盘上，关闭后再次打开浏览器，这些cookie仍然有效直到超过设定的过期时间。

存储在硬盘上的 cookie可以在不同的浏览器进程间共享，比如两个IE窗口。而对于保存在内存里的cookie，不同的浏览器有不同的处理方式。对于IE，在一个打开的窗口上按Ctrl-N（或者从文件菜单）打开的窗口可以与原窗口共享，而使用其他方式新开的IE进程则不能共享已经打开的窗口的内存cookie；对于 Mozilla Firefox、chrome、IE8，所有的进程和标签页都可以共享同样的cookie。一般来说是用javascript的window.open打开的窗口会与原窗口共享内存cookie
```



win7系统cookies默认保存地址：

```javascript
C:\Users\Administrator\AppData\Local\Microsoft\Windows\Temporary Internet Files
```



cookie文章：

https://blog.csdn.net/rubulai/article/details/91873075?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task



https://blog.csdn.net/qq_17452939/article/details/80901985

案例：

###### 案例1：

create.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>添加cookie</title>
  </head>
  
  <body>
<%
    session.setAttribute("test", "hello");
    response.sendRedirect("getCookie.jsp");
%>

  </body>
</html>


```

getCookie.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>读取cookie</title>
  </head>
  
  <body>
<%
    out.print("sessionid:" + session.getId());
    out.print("<br/>");
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (int i=0; i<cookies.length; i++) {
            out.print("cookie name:" + cookies[i].getName());
            out.print("<br/>");
            out.print("cookie value:" + cookies[i].getValue());
            out.print("<br/>");
        }
    }
%>

  </body>
</html>

```

###### 案例2：

info.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>添加cookie</title>
  </head>
  
  <body>
<%
    Cookie nc=new Cookie("info", "ok"); 
    nc.setMaxAge(10); //设置Cookie失效前时间为60秒
    response.addCookie(nc);
    response.sendRedirect("showCookie.jsp");
%>

  </body>
</html>

```

showCookie.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>读取cookie</title>
  </head>
  
  <body>
<%
    Cookie[] cookies = request.getCookies();
    boolean sign = false; //标识
    if (cookies != null) {
        for (int i=0; i<cookies.length; i++) {
            if (cookies[i].getName().equals("info")) {
                sign = true;
                out.print("读取Cookie的值:" + cookies[i].getValue());
            }
        }
    }
    if (!sign) {
        out.print("超过Cookie有效期,无法读取Cookie");
    }
%>
  </body>
</html>

```

###### 案例3：

login.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>登录</title>
  </head>
  <%
		Cookie[] cookies = request.getCookies();
		String user="";
		String pwd = "";
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("userName"))
					response.sendRedirect("welcome.jsp");
			}
		}

  	
   %>
  <body>
    <form name="loginForm" method="post" action="doLogin.jsp">
			用户名：<input type="text" name="userName" />
			密码：<input type="password" name="pwd" />
			<input type="submit" value="登录">
		</form>

  </body>
</html>

```

doLogin.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		if("sa".equals(name.trim())&& "123".equals(pwd.trim())){
			//以key/value的形式创建Cookie
			Cookie uname=new Cookie("userName", name.trim()); 
			 uname.setMaxAge(5*60); 
			response.addCookie(uname);
			response.sendRedirect("welcome.jsp");
		} 
	%>


```

welcome.jsp

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>欢迎页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    欢迎您的光临！！！
  </body>
</html>

```

###### 案例4：

要想在cookie中存储中文那么必须使用URLEncoder类里面的encode(String s, String enc)方法进行中文转码，例如：

```javascript
       String name = URLEncoder.encode("姓名", "UTF-8");
       String value = URLEncoder.encode("张三", "UTF-8");
       Cookie c = new Cookie(name, value);
       c.setMaxAge(3600);
       response.addCookie(c);

```

```javascript
       response.setContentType("text/html;charset=utf-8");
       Cookie[] cs = request.getCookies();
       if(cs != null) {
           for(Cookie c : cs) {
              String name = URLDecoder.decode(c.getName(), "UTF-8");
              String value = URLDecoder.decode(c.getValue(), "UTF-8");
               String s = name + ": " + value + "<br/>";
              response.getWriter().print(s);
           }
       }
```







