## 问答

> #### 1.1 Tomcat服务器软件的目录结构说明， 如何配置Tomcat的环境变量？

```markdown
bin：目录里存放tomcat服务器软件相关的命令（.bat,.sh,.exe）
conf:
	context.xml--->用来配置tomcat上下文的共享通用信息， 帮助我们在多网站（Web应用）之间使用相同的资	源（数据源，共享文件等等）
	server.xml--->用来设置tomcat服务器运行时的行为（端口，处理请求是的设置）
	web.xml--->配置tomcat服务器托管的网站（Web应用）的行为，
	项目中的web.xml文件要比tomcat下的web.xml文件优先级高。
lib: 
	存放的是通用的Jar， tomcat服务器在运行时会将项目下WEB-INF/lib中的所有Jar
    统一打成一个包。
logs:
	存放tomcat运行时的日志信息， 查看logs中的文件可以监控tomcat服务器运行的状态
temp:
	存放tomcat服务器运行时， 生成的一些临时文件， 不需我们进行维护
webapps：
	Web应用发布的地方
work: 
	Web应用项目编译生成的字节码文件存放的目录，包括java文件
```

> #### 1.2 如何使用Eclipse创建Web项目？

```markdown
first：
	File-->new-->Dynamic Web project
than-->：
	填写项目名&选择tomcat版本&选Dynamic web module version 版本&Configuration使用tomcat默认
next-->:
	Default output folder改成：WebContent/WEB-INF/classes
next-->:
	勾选Generate web.xml deployment descriptor也就是创建web.xml配置文件
finish
```

> #### 1.3 Java Server Pages的运行原理？

```markdown
首先jsp被编译成纯java文件，然后java文件编译成class字节码文件，由被编译好的java程序动态的生成html
```

> #### 1.4 什么是无状态请求， 应用程序的类型？

```markdown
无状态请求：
	就是每次请求和之前没有任何关系,比如http,之前一次请求目的就是为了获取目标资源，当请求完成了这次连接就失效了，并不会一直保持连接.
应用程序类型:
	web应用程序
状态化请求:
	在cookie和session出现后，才开始趋向于状态化，在请求的时候向客户端返回保存着某些信息的cookie和session，这些信息可以被页面之前共用，在后面发请求的时候可以根据这些信息去判断某些关系，比如用session或者cookie判断用户是否已经登陆等。
```

> #### 1.5 request和response对象的作用和常用方法？

```markdown
request:
	request代表请求对象,通过request可以获取http请求中的信息，可以用来处理请求里面包含的信息
```

#### **request常用方法**

| 方法名称                                                 | 说明                                                         |
| -------------------------------------------------------- | ------------------------------------------------------------ |
| String getParameter(String name)                         | 根据表单组件名称获取提交数据                                 |
| String[ ] getParameterValues(String name)                | 获取表单组件对应多个值时的请求数据                           |
| void setCharacterEncoding(String charset)                | 指定每个请求的编码                                           |
| RequestDispatcher<br />getRequestDispatcher(String path) | 返回一个RequestDispatcher对象，<br />该对象的forward( )方法用于转发请求 |

------



```markdown
response:
	response代表响应对象，通过response可以设置响应信息，动态的响应客户端的请示，比如设置响应头
```

#### **response常用方法**

| 方法名称                         | 说明                                            |
| -------------------------------- | ----------------------------------------------- |
| void addCookie(Cookie cookie)    | 服务端向客户端增加- -个cookie对象               |
| void sndedirect(String location) | 页面跳转的一 种方式叫重定向                     |
| void setContentype(String type)  | 设置服务端响应的编码(设置服务端contentType类型) |

> #### 1.6 处理中文乱码的方法有几种，如何实现？

```markdown
1、
	在<%%>标签里写new String(request.getParameter(“key”).getBytes(“ISO-8859-1”), “UTF-8”);
2、
	在tomcat的server.xml里面找到标签名为Connector的添加： URIEncoding=”UTF-8”
3、
	在<%%>标签里写request.setCharacterEncoding("utf-8");
```

> #### 1.7 针对GET和POST请求如何处理中文乱码？

```
GET方法使用1.6的第一种或者第二种，推荐第二种
POST方法使用1.6的第三种
```

> #### 1.8 转发与重定向的区别和应用场合是什么？

**请求转发与重定向的区别**

|                             | 请求转发 | 重定向  |
| --------------------------- | -------- | ------- |
| 地址栏是否改变              | 不变     | 改变    |
| 是否保留第一-次请求时的数据 | 保留     | 不保留  |
| 请求次数                    | 1个来回  | 2个来回 |

**转发和重定向的应用场合**

```markdown
转发：
	转发在同一个web程序中其他页面需要共享同一次请求的数据信息时候使用，因为只有一个来回所以速度较快，并不会经过客户端，是在服务器端完成的。
重定向:
	转发只能在同一个web应用中进行，如果想要去其他的网站这时候就要用到重定向了
```

> #### 1.9 session，application对象的作用和常用方法是什么？

```markdown
session：
	一般用来保存用户登陆信息，判断用户是否登陆
application:
	可以用来实现网站访问次数的统计，可以用来储存一些公开公共的信息
```

**session常用方法**

```java
//设置信息
	session.setAttribute("key", value);
//取出信息
	String username=(String)session.getAttribute("key");
```

**applicationn常用方法**

```java
//设置信息
	application.getAttribute("accCount");
//取出信息
	Integer count = (Integer) application.getAttribute("accCount");
```

> #### 2.0 cookie对象的作用和常用方法是什么？

**cookie对象的作用是保存用户信息，可以保存用户访问了哪些页面、次数，也可以用来自动填写表单的用户名**

**cookie的常用方法**

- 创建cookie对象

  ```java
  Cookie cookie_name =new Cookie("Parameter","Value");
  Cookie[] cookie_name =request.getCookies;
  ```

- **设置生命周期**

  ```java
  cookie.getMaxAge();
  ```

- **获取值**

  ```java
  cookie.getValue();
  ```

- **获取存活时长**

  ```java
  cookie.getMaxAge();
  ```

> #### 2.1 内置对象的作用域？

| 对象        | 说明                  | 作用域                 |
| ----------- | --------------------- | ---------------------- |
| pageContext | JSP页面容器(page对象) | 当前页面有效           |
| request     | 对象请求              | 同次请求有效           |
| session     | 会话请求              | 同次会话有效           |
| application | 全局对象              | 全局有效(整个项目有效) |

> #### 2.2 JavaBean?

JavaBean 是一种JAVA语言写成的可重用组件。为写成JavaBean，类必须是具体的和公共的，并且具有无参数的[构造器](https://baike.baidu.com/item/构造器/9844976)。JavaBean 通过提供符合一致性设计模式的公共方法将内部域暴露成员属性，set和get方法获取。众所周知，属性名称符合这种模式，其他Java 类可以通过自省机制(反射机制)发现和操作这些JavaBean 的属性。它可以被Applet、Servlet、SP等Java应用程序调用．也可以可视化地被Java开发工具使用。它包含属性(Properties)、方法(Methods)、事件(Events)等特性。

用户可以使用JavaBean将功能、处理、值、数据库访问和其他任何可以用java代码创造的对象进行打包，并

[![img](https://bkimg.cdn.bcebos.com/pic/42a98226cffc1e17b5140d9f4490f603728de9f1?x-bce-process=image/resize,m_lfit,w_220,h_220,limit_1)](https://baike.baidu.com/pic/javaBean/529577/0/42a98226cffc1e17b5140d9f4490f603728de9f1?fr=lemma&ct=single)且其他的开发者可以通过内部的JSP页面、Servlet、其他JavaBean、applet程序或者应用来使用这些对象。用户可以认为JavaBean提供了一种随时随地的复制和粘贴的功能，而不用关心任何改变。

> #### 2.3 3层架构？

三层架构即表现层，数据访问层，业务逻辑层

**表现层：**

​		主要是指与用户交互的界面，比如前端网页，app的图形界面，把从业务层经过处理的数据展现给用户。

**数据访问层:**

​		数据访问层顾名思义就是指用来访问数据的，通过这层来获取数据，主页是与数据库打交道，增删改查的方		法都是从中定义的。

**业务逻辑层**

​		用来写业务的，数据从数据访问层里拿到，要经过一些列逻辑处理，像登陆业务需要验证密码，或者根据获		取到的文件进行读取文件，最后才能给表现层展现给用户。