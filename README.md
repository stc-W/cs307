# 项目信息

## 使用的技术栈

​		本次项目主要采取了Mybatis和servlet，主要利用了Mybatis进行和数据库的交互，利用servlet处理来自前端发送来的请求并接收相关参数，进行业务逻辑的处理，进行页面的转发，以及进行数据库的访问。

## 项目架构

​		本次项目在代码主要分为五个层面，分别是mapper层，pojo层，service层，util层和web层。

### mapper层

​		对于每个单独的数据库中的表都要有其对应的mapper，这里主要负责编写对应的sql语句，来进行对数据库的增删改查操作。对于mapper代码的编写主要有两种方式，第一种是基于注释的编写，第二种是基于xml文件的编写，第二种主要是针对一些较为复杂的sql语句，本次项目中所设计到的增删改查并不复杂因此我们采取第一种即用注释编写的方式，下面是一段实例代码。

```java
public interface ImageMapper {
  @Select("select * from images where postid = #{id}")
  Image selectByID(@Param("id") int id);
  
  @Insert("insert into images (postid, imagedata) values (#{postid},#{imagedata})")
  void add(Image image);
  
  @Update("update images set imagedata = #{imagedata} where postid = #{postid}")
  void updatePostID(Image image);
}
```

### pojo层

​		这一层主要编写一些基础的java类，主要的作用是用来存储表映射的对象(包含set和get方法)，下面是一段示例代码。

```java
package pojo;

public class Follow {
  private String authorName;
  private String authorFollowedBy;
  
  public Follow(String authorName, String s) {
    this.authorName = authorName;
    this.authorFollowedBy = s;
  }
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  @Override
  public String toString() {
    return "Follow{" +
      "AuthorID='" + authorName + '\'' +
      ", authorFollowedBy='" + authorFollowedBy + '\'' +
      '}';
  }
  public String getAuthorName() {
    return authorName;
  }
  public void setAuthorID(String authorID) {
    this.authorName = authorID;
  }
  public String getAuthorFollowedBy() {
    return authorFollowedBy;
  }
  public void setAuthorFollowedBy(String authorFollowedBy) {
    this.authorFollowedBy = authorFollowedBy;
  }
}

```

### util层

​		这一层主要存放一些工具类，例如为了提升效率我们构造了一个static的`SqlSessionFactoryUtils`类，防止每次访问都重复创造。

```java
package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
  private static SqlSessionFactory sqlSessionFactory;
   static {
     //静态代码块会随着类的加载而自动执行，且只执行一次
     try {
      String resource = "mybatis-config.xml";
       InputStream inputStream = Resources.getResourceAsStream(resource);
       sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
       } catch (IOException e) {
       e.printStackTrace();
       }
     }
   public static SqlSessionFactory getSqlSessionFactory(){
     return sqlSessionFactory;
     }
}

```

### service层

​		这一层主要用来申请`sqlsession`并且执行mapper接口中的方法以供web层调用进行对数据库的访问。下面是一段示例代码。

```java
public class ShareService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public void add(Share share){
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ShareMapper shareMapper = sqlSession.getMapper(ShareMapper.class);
    //2.4 调用方法
    shareMapper.add(share);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<Share> selectByAuthor(String name) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ShareMapper shareMapper = sqlSession.getMapper(ShareMapper.class);
    //2.4 调用方法
    List<Share> Shares = shareMapper.selectByAuthor(name);
    //2.5 释放资源
    sqlSession.close();
    return Shares;
  }
}

```

### web层

​		这里存放的是编写好的`servlet`，用来处理业务逻辑，接收数据，转发页面，调用service层方法，下面是一段示例代码。

```java
@WebServlet("/followServlet")
public class FollowServlet extends HttpServlet {
  service.FollowService followService = new service.FollowService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String authorname = req.getParameter("authorname");
      HttpSession session = req.getSession();
      String authorfollowername = (String) session.getAttribute("username");
      String page = req.getParameter("page");
      pojo.Follow follow = new pojo.Follow(authorname, authorfollowername);
      followService.add(follow);
      if (page.equals("homepage")) {
        req.getRequestDispatcher("/homepageServlet?name=" + authorname).forward(req, resp);
      } else if (page.equals("userSearch")) {
        String S = req.getParameter("S");
        req.getRequestDispatcher("/userSearchServlet?searchname="+S).forward(req, resp);
      }
  }
```

## 支持的基础接口

​		本项目主要基于HTTP协议的一个web项目，通过前端的GUI与用户进行交互，支持以下功能。注意下面的功能仅仅是相对基础功能来写的，具体更多功能可以参考bonus部分。

### 注册以及登录

​		用户通过表单分别向`project/loginCheckServlet`和`project/registerservlet`

发送数据和对应请求，对于登录来说，如果核对成功则会出现提示页面，提示登录成功并跳转到用户主页，如果失败则会提示登录失败并且跳转回登陆界面，对于注册功能，对注册的用户名以及密码有格式限制，如果不符合限制则不许提交，并且如果用户名发生重复也会对应出现注册失败的提示，并返回，如果注册成功则会返回登录界面。

### 用户点赞，收藏，转发帖子并查看

​		在用户主页，帖子广场，以及搜索界面显示的帖子信息后面都有对应的点赞收藏和转发的快捷键，用户只需要点击对应位置，即可向对应的`servlet`发送请求，在对应的数据表中提交一条数据，需要注意的是我们在数据库添加层面对重复点赞收藏和转发进行了屏蔽。即使点击多次也只会有一条数据。并且用户可以在他们的主页观测到自己点赞收藏转发的帖子信息并进行查看内容。

### 用户关注/取关作者

​		用户可以通过搜索功能或者直接通过帖子的作者信息进入对应作者的主页，在对应作者的主页有关注按钮，点击关注即可向`project/followServlet`发送请求，如果此前未关注便可在对应表中添加一条数据，同时在自己的用户主页可以查看自己当前关注的作者，对应的对方也能在自己的关注着列表中看见你，在自己的关注列表作者用户名的后面还有取消关注的快捷键，点击即可向`project/deleteFollowServlet`发送请求，删除对应数据，同时这个作者会消失在你的关注列表里，你也不存在与该作者的追随者列表里。

### 用户发布帖子

​		用户在自己的用户主页下可以点击发布按钮，随后会跳转到一个表单页面，在该表单页面输入对应的信息并点击提交就可以向`project/addPostServlet`发送请求，该servlet会向post表中写入对应信息，并且发帖时间是由后端直接生成的。

### 用户回复帖子或回复回复

​		用户在帖子的详情页面可以查看到当前帖子的所有回复，并且能在该页面提交自己的回复，同时在每一条一级回复后面都有回复快捷键点击即可进入二级回复界面，该界面会显示所有的针对该条一级回复的二级回复，并且你能在此页面提交自己的二级回复。

### 用户查看自己的一级回复以及二级回复

​		用户在自己的主页可以查看自己的一级回复列表以及二级回复列表，并且在对应的一级回复后面插入了快捷键支持一键查询该一级回复对应的帖子信息，同时二级回复列表也支持一键查询对应的一级回复信息。

## Bonus

​		除了基础部分之外我们额外添加了许多bonus部分以增强用户体验。

### 使用高斯数据库

​		本次项目全部都是基于华为的开源高斯数据库实现的，我们利用VMWare软件在自己的电脑上搭建了虚拟机，配置了华为的**OpenEuler**系统,并在该系统上配置了高斯数据库，同时成功进行了远程连接，我们可以通过DataGrip软件对数据库进行方便的配置同时根据官网的说明，我们可以直接使用Postgres的驱动jar包对该数据库进行操作。可见该数据库对于postgres用户来说十分友好，语法几乎一致。

### 封装一个后端

​		我们所有的请求都是基于HTTP协议，我们的服务器采用了Tomcat8的服务器，由该服务器实现对前端数据的接收以及后端回应的发送，以及对**JSP**文件进行渲染并在前端进行展示。

### 匿名发言

​		我们在数据库中提前申请好了一个**!anonymous**用户，因为我们前端对用户名的限制，用户无法申请和这个相同的用户名。并且该匿名用户在自己的主页查看不到任何自己的发帖以及点赞帖子关注等信息，只会显示搜索发帖以及帖子广场等页面，匿名用户无法关注和点赞分享，但是可以回复他人的信息。

### 修改和删除帖子

​		我们在基础功能的基础上增加了修改和删除帖子的快捷键，他们就在帖子基本信息的后面，并且只有自己才能修改和删除自己的帖子。每次点击快捷键其实就是相当于对对于的servlet发送了一次请求，在数据库进行修改或删除工作。

### 支持插入图片

​		我们这里实现插入和展示图片的原理是，后端接收完前端用户传递过来的图片后会将其变为一个字节码数组存入数据库中，为了防止图片过大使得post表负担过重，我们单独建了一张images表用来存储图片和对应的postid，我们暂时只支持用户在每一个帖子传递一张图片，在对应展示的时候，后端首先取得服务器中的字节信息，通过编码器将其转换为base64的字符串信息将其传输给前端jsp页面，交由服务器渲染之后即可展示图片。

###  点赞榜单

​		我们实现了一个实时更新的TOP10点赞榜单，为了保证该榜单的事实更新的效率，我们选择采取维护一个大小为10的一个小顶堆，并且为了保证修改内部值的效率我们构建了一个哈希表用以维护帖子在小顶堆底层数组的下标，这样我们可以O(1)的找到这个下标，我们每次对一个帖子进行点赞都需要维护这个堆和哈希表，维护堆的时间复杂度是O（logk），这里k = 10，所以其实就是O（1）的时间复杂度，同时维护哈希表的时间也是O（1）的，所以我们可以每次操作以O（1）的时间复杂度维护这个榜单，这是十分高效的，之所以使用小顶堆来实现，我们每次在有新点赞的时候仅仅需要和堆顶元素进行比较就可以知道这个新点赞数是否能够变成前10，如果该帖子已经在堆中存在了，在收到新的点赞时要更新这个小顶堆，由于时点赞数目的增加，需要堆该节点进行下沉操作。并且需要注意的是由于我们维护的是一个小顶堆，所以输出结果是从小到大的前十名，输出时候可以做一个反向输出。

### 多功能搜索

​		我们这里主要支持两种搜索，第一种是搜索帖子，我们支持的是模糊搜索，只要在帖子的内容分类或者标题中出现过对应的关键字就会显示该帖子，这里实现的底层原理其实就是数据库中的通配符搜索，同时用户也支持用户名模糊搜索，原理类似，模糊搜索帖子的示例代码如下。

```java
 @Select("select * from post where title like '%' || #{title} || '%' or content like '%' || #{title} || '%' or category like '%' || #{title} || '%' or author_Name like '%' || #{title} || '%'")
    @ResultMap("postsResultMap")
    List<Posts> selectMoHu(@Param("title") String title);
```

### 使用数据库连接池

​		我们使用的是Mybatis框架进行与数据库的交互，在Mybatis的配置文件中有对数据库连接池的配置。

```xml
 <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="org.postgresql.Driver"/>
                <property name="url" value="jdbc:postgresql://192.168.86.101:26000/proj2?useSSL=false&amp;useServerPrepStmts=true"/>
                <property name="username" value="hailong"/>
                <property name="password" value="~~0907chusheng"/>
            </dataSource>
        </environment>
    </environments>
```

​		在` <dataSource type="POOLED">`这里，我们选择了数据库资源的类型是pooled，也就是选择启用数据库连接池，如果这里选择unpooled则是不使用数据库连接池，Mybatis实现了自己的数据库连接池技术相关类，具体实现在`org.apache.ibatis.datasource`包下面，有`PooledDataSource`实现了`DataSource`接口。

### 页面显示设计

​		在Web开发中，HTML和JSP（JavaServer Pages）是两种常用的前端技术。HTML用于定义网页的结构和内容，而JSP是一种动态网页技术，允许在HTML中嵌入Java代码。CSS（层叠样式表）用于定义网页的样式和布局。我们使用的是CSS外联方式进行连接。

CSS外联设计的优势

1. 可维护性：将CSS代码与HTML和JSP代码分离，使样式定义集中管理，易于修改和维护。

2. 可重用性：通过外联设计，可以在多个HTML和JSP页面之间共享相同的样式，提高代码的重用性。

3. 可扩展性：通过外联设计，可以轻松添加和修改样式，而无需修改HTML和JSP代码。

4. 分工合作：通过将样式和内容分离，可以实现前端和后端开发人员的分工合作，提高开发效率。

在HTML中使用CSS外联设计非常简单，以下是一些步骤：

1. 创建一个独立的CSS文件，例如styles.css。

2. 在HTML文件的<head>标签中使用<link>元素将CSS文件与HTML文件关联起来，示例如下：

  Html：

```
 <link rel="stylesheet" type="text/css" href="styles.css">
```

3. 在CSS文件中编写所需的样式规则，例如：

Css：

  body {

   background-color: #f2f2f2;

   font-family: Arial, sans-serif;

  }

4. 在HTML文件的标签中使用CSS类或ID来应用样式，示例如下：

 Html：

```html
  <h1 class="heading">Hello, World!</h1>
```

 Css：

```css
 .heading {

   color: blue;

  }
```

​	在JSP中使用CSS外联设计与HTML类似，只是需要注意一些特殊的语法：

1. 创建一个独立的CSS文件，例如`styles.css`。

2. 在JSP文件的`<head>`标签中使用<link>元素将CSS文件与JSP文件关联起来，示例如下：

  Jsp：

```jsp
 <link rel="stylesheet" type="text/css" href="styles.css">
```

3. 在CSS文件中编写所需的样式规则，与HTML中的CSS编写方式相同。

4. 在JSP文件中使用JSP标签将CSS类或ID应用到相应的HTML元素，示例如下：

  Jsp：

```jsp
  <h1 class="<%= cssClass %>">Hello, World!</h1>
```

  其中，`cssClass`是在JSP中定义的一个变量，用于动态设置样式类。

​	通过使用CSS外联设计，我们可以有效地将样式与HTML和JSP代码分离，提高代码的可维护性和可重用性。无论是在HTML还是JSP中，通过创建独立的CSS文件，并使用<link>元素将其与HTML或JSP文件关联起来，我们可以轻松地定义和应用样式规则，从而实现更好的代码组织和开发效率。

### 合理使用用户权限

​		我们对用户进行了权限的限制，这主要实在前端实现的，在非自己的主页界面，用户无法发帖修改和删除帖子，并且用户只能关注别人不能关注自己，也就是只在他人用户界面有关注按钮，并且对匿名用户不能关注分享和点赞他人的帖子，我们通过在前端的jsp文件中使用**EL(Expression Language)**, 对用户名进行判断，如果当前主页用户与登录用户不符合就不显示对应的快捷键，用户也就无法完成对应的操作。

# 总结和不足

​		本次项目主要是基于JAVA WEB基础做的，并没有使用Springboot等高级框架，这是由于学期时间有点紧张没有来得及学习的缘故，但是在这次开放过程中我们使用了一些底层的javaweb基础技术实现和数据库进行交互，为我们后续接触更高级的框架打下了坚实的基础，本次设计不足的地方有我们的后端架构还不够清晰，处理业务逻辑的时候有一定的代码冗余，前端设计经验不足等等，总的来说还有很长一段路要走还得继续学习。

​		
