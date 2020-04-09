> ### 了解Cookie

**Cookie是小段的文本信息，在网络服务器上生成，并发送给浏览器，通过使用cookie可以标识用户身份，记录用户名和密码，跟踪重复等。**

------

> ### Cookie有什么作用？

今天就说一下在用户登陆中的作用

**Cookie有效期限未到时，Cookie能使用户在不键入密码和用户名的情况下进入曾经浏览过的一些站点**

> ### 如何创建Cookie

```java
//newCookie的对象时候会自动导入这个包
import="javax.servlet.http.Cookie"
//Parameter即类似于map集合中的Key，Value即map中的value，即键值对的形式
Cookie cookie_name =new Cookie("Parameter","Value");
```

> ### 设置Cookie的存活时长

```java
cookie.setMaxAge(60);//建议使用1*60这样的，就是1*60秒，这样看得更清晰
```

> ### 以本文的形式存储客户端

```java
//在服务器端response.addCookie(cookie)把cookie以本文的形式存储在客户端
response.addCookie(cookie);
```

> ### 遍历Cookie中的信息

```java
Cookie[] cookieUname = request.getCookies();
    if (cookieUname != null) {
        for (Cookie cookie : cookieUname) {
            if ("uname".equals(cookie.getName())) {
                out.print("<h1>欢迎:" + cookie.getValue() + "</h1>");
            }
        }
    }
```

- **获取键**

  ```java
  cookie.getName();
  ```

- **获取值**

  ```java
  cookie.getValue();
  ```

- **获取存活时长**

  ```java
  cookie.getMaxAge();
  ```

> ### JSESESSIONID

![](I:\作业\jsp\img\QQ截图20200402170025.png)

从上图我们可以看到，sessionID和JSESSIONID是一摸一样的，那这说明session可能和cookie存在某种关联，而这两者是一一对应的关系

------

**下面是我做的案例**

![](I:\作业\jsp\img\QQ截图20200402170309.png)

此时我们并未登陆，也能看到通过session获取uname的值是null，证明我们并没有去保存uname去session里。

但是，sessionID和JSESSIONID依然有有ID值，而且是一摸一样的。

------

现在我们登陆看看

![](I:\作业\jsp\img\QQ截图20200402170735.png)

登陆成功了，而欢迎zhangsan这个zhangsan是我从Cookie里面获取的用户名

登陆页面的jsp代码

```jsp
<%
    Cookie[] cookieUname = request.getCookies();
    out.print("<h1>session：</h1><h1>"+session.getAttribute("uname")+"</h1><br><h1>sessionID：</h1>"+session.getId());
    if (cookieUname != null) {
        for (Cookie cookie : cookieUname) {
            out.print("<h1>保存在Cookie的信息：</h1><h1>"+cookie.getName()+"</h1><br>"+cookie.getValue());
            if ("uname".equals(cookie.getName())) {
//                response.sendRedirect("index.jsp");
            }
        }
    }
%>
```

主页的jsp代码

```java
<%
    Cookie[] cookieUname = request.getCookies();
    Boolean falg = true;
    if (cookieUname != null) {
        for (Cookie cookie : cookieUname) {
            if ("uname".equals(cookie.getName())) {
                //向本页面打印元素
                out.print("<h1>欢迎:" + cookie.getValue() + "</h1>");
                falg = false;
            }
        }
    }
    if (falg) {
        response.sendRedirect("login.jsp");
    }
%>
```

然后我们回到登陆页面看看session的uname值

![](I:\作业\jsp\img\QQ截图20200402171326.png)

此时回到登陆页面刷新一下，看到了session的uname值是zhangsan，并且Cookie里也存在uname的值。两个id都变了但还是。一摸一样，那究竟存在什么联系呢？

我之前设定了Cookie的存活时间为60秒，到时间了就会自动销毁。现在差不多了，我们去刷新一下

![](I:\作业\jsp\img\QQ截图20200402171725.png)

结果很显然，session的uname的值依然存在，难道在cookie销毁的uname的时候和session并没有什么联系吗？session并不会随着cookie的销毁而销毁？

那么为什么两个id却还在呢？难道cookie并没有真正的销毁吗？

我们在来做个试验：去浏览器设置把cookie清理一下，你猜会发生什么？

![](I:\作业\jsp\img\QQ截图20200402172244.png)

好，清理完了我们刷新了一下页面。这下Cookie才是真正的销毁了吧，而且session的uname值也跟着没了，但是sessionid却还依然在。那么为什么我之前setAttribute("uame",uame)的值会没了呢？

**然后我再次刷新一下页面：**

![](I:\作业\jsp\img\QQ截图20200402172911.png)

**太神奇了！！！！！！**

竟然Cookie又出来了，而且JSESSIONID还是和上面那个sessionID是一样的。这说明了什么呢。我今天上课绕了又绕把我头给整懵了。

------



> ### **现在我懂了听我细讲：**

每一个session都自带id，而我认为session并不需要去创建，session一直都在，只是没有使用setAttribute去设定一个k/v，所以这就是大家认为的session没有登录就不存在，我认为这是不对的不管有没有登录，session都是存在的，而且一直存在一个id，上图就是最好的证明，那呢为什么我设置的Cookie到期了，Cookie的JSESSIONID却还在呢？其实我认为过期并不等于销毁，只是之前设定的k/v没了。过期后还是之前那个Cookie，id和之前一样就是最好的证明。

------

那么为什么当cookie过期之后，session的uname值仍然在呢？这并不是说session和cookie没有任何联系，因为sessionid和JSESSIONID始终一对一，所以这必然有联系，因为其实session是存在于服务器端，且id也是，而cookie存在于客户端，所以要想在客户端去获取session的Attribute，则必须通过一个唯一标识，而这个唯一标识就是一对一的sessionid和JSESSIONID，浏览器(客户端)需要通过Cookie的JSESSIONID去服务器端找到对应sessionid的session，所以这就是为什么两个id怎么都是一摸一样了。这就很好解释了，当在设置内清除浏览器Cookie的时候，是真正的把Cookie的本地存储给清掉了，所以这个时候JSESSIONID就是不存在的，所以浏览器没有了这个唯一标识，就找不到对应的session，所以就会为null了。这就是为什么清理Cookie之后session的uname值为null而且sessionid也变了的原因，因为找不到原来那个存储着那个uname值的session了，session与Cookie的唯一联系就是这两个一毛一样的id作为唯一标识，除此之外没有任何联系。

------

好了，既然这个问题得到了解释，那么为什么再第二次刷新页面的时候Cookie又出来了，这应该是开发session和Cookie的开发人员设定的一种机制，当Cookie被清除后，原来这个Cookie就真的被销毁了，但是为了让客户端能获取到session，于是分配了个新的空间产生了个新的cookie，而这个Cookie就是为这个session而生的，session会把它的id复制给Cookie的JSESSIONID，使得两个id一一对应，这样浏览器(客户端)又能顺利得通过Cookie的JSESSIONID去服务器端找到对应sessionid的session了。

Cookie销毁在的这段期间，session原来的那个session其实还存在，只是没有了意义，因为根本没有标识去找这个session了，所以之后又会产生一个新的Cookie和这个新的session一 一对应。看到这你懂了吗？

------



> ### Cookie的全部方法列举

| 类型    | 方法名                     | 方法解释                                                     |
| ------- | -------------------------- | ------------------------------------------------------------ |
| String  | getComment()               | 返回cookie中注释,如果没有注释的话将返回空值.                 |
| String  | getDomain()                | 返回cookie中Cookie适用的域名. 使用getDomain() 方法可以指示浏览器把Cookie返回给同 一域内的其他服务器,而通常Cookie只返回给与发送它的服务器名字完全相同的服务器。注意域名必须以点开始（例如.yesky.com） |
| int     | getMaxAge()                | 返回Cookie过期之前的最大时间，以秒计算。                     |
| String  | getName()                  | 返回Cookie的名字。名字和值是我们始终关心的两个部分，笔者会在后面详细介绍 getName/setName。 |
| String  | getPath()                  | 返回Cookie适用的路径。如果不指定路径，Cookie将返回给当前页面所在目录及其子目录下 的所有页面。 |
| boolean | getSecure()                | 如果浏览器通过安全协议发送cookies将返回true值，如果浏览器使用标准协议则返回false值。 |
| String  | getValue()                 | 返回Cookie的值。笔者也将在后面详细介绍getValue/setValue。    |
| int     | getVersion()               | 返回Cookie所遵从的协议版本。                                 |
| void    | setComment(String purpose) | 设置cookie中注释。                                           |
| void    | setDomain(String pattern)  | 设置cookie中Cookie适用的域名                                 |
| void    | setMaxAge(int expiry)      | 以秒计算，设置Cookie过期时间。                               |
| void    | setPath(String uri)        | 指定Cookie适用的路径。                                       |
| void    | setSecure(boolean flag)    | 指出浏览器使用的安全协议，例如HTTPS或SSL。                   |
| void    | setValue(String newValue)  | cookie创建后设置一个新的值。                                 |
| void    | setVersion(int v)          | 设置Cookie所遵从的协议版本。                                 |