![](authorid1/Mybatis-plus使用注解注入sql语句/logo.png)

> ###首先把我这次项目的数据库亮查出来

![](authorid1/Mybatis-plus使用注解注入sql语句/QQ截图20200403190946.png)

> ###然后是我用来使用多表联查的实体类

![](authorid1/Mybatis-plus使用注解注入sql语句/QQ截图20200403191902.png)

**这里为了方便我使用了Lombok工具**

**接下来，我们去dao层写一个接口，这里dao层接口我就直接写实现类了，不去搞什么interface接口了**

![](authorid1/Mybatis-plus使用注解注入sql语句/QQ截图20200403193655.png)

> ###接下来我们看看Service业务层

![](authorid1/Mybatis-plus使用注解注入sql语句/流程png)

> ### 画个图

![](authorid1/Mybatis-plus使用注解注入sql语句/无标题.png)
> 然后编写Controller

![](authorid1/Mybatis-plus使用注解注入sql语句/1585930945575)

就是用业务层父类去调用子类实现类的方法

> 测试一下看看能不能拿到数据

![](authorid1/Mybatis-plus使用注解注入sql语句/QQ截图20200404002732.png)

通过标题查找文章的确能拿到数据，而且作者名字也显示出来了，作者名字是作者信息表的，并不是在文章表，所以证明这种用注解注入SQL的查询方式是能拿到数据的

> 再来配合我今天做好的Markdown显示页面试下从服务器端获取的md文件内容，再渲染到前端页面

![](authorid1/Mybatis-plus使用注解注入sql语句/QQ截图20200404003209.png)

![](authorid1/Mybatis-plus使用注解注入sql语句/QQ截图20200404003225.png)

好了，都没问题哈。

> ###概括

**大概讲一下流程，dao层继承了BaseMapper类，而MP提供的ServiceImpl类里面有个getBaseMapper()方法返回值就是继承了BaseMapper类的泛型M，也就是说，进来ServiceImpl里面的第一个泛型只能是继承了BaseMapper类的Dao层对象，我们的dao层一开就继承了。然后我们的业务层又去继承了MP提供的ServiceImpl这个类，就能直接调用其getBaseMapper()方法，然后业务层就得到一个继承了BaseMapper类的M对象，其实就是dao层对象，然后业务层就能用这个dao层对象去调用BaseMapper以及dao层自己写的独有的方法了。
然后在做多表查询的时候，需要去建一个对应字段的实体类。因为泛型需要用到，而且也需要用来存取多表查询的数据**