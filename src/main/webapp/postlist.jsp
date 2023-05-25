<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/8
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/postlist.css" rel="stylesheet">
</head>


<body>
<%HttpSession httpSession = request.getSession();
String username = (String) httpSession.getAttribute("username");%>
<a href="homepageServlet?name=${username}">返回个人主页</a>
<hr>
<hr>
<form action="/project/postSearchServlet" id="searchpost" method="post">
    模糊搜索帖子(包含相关标题内容以及分类)<input type="text" name="search" placeholder="宁愿少输，不要输错">
    <input type="submit" value="搜索">
</form>


<hr>
<form action="/project/userSearchServlet" id="searchname" method="post">
    模糊搜索用户<input type="text" name="searchname" placeholder="宁愿少输，不要输错">
    <input type="submit" value="搜索">
</form>


<h1>帖子广场</h1>
<table border="1" cellspacing="0" width="80%">
    <tr>
        <th>序号</th>
        <th>标题</th>
        <th>类别</th>
        <%--      <th>内容</th>--%>
        <th>发布时间</th>
        <th>作者姓名</th>
        <th>发布国家</th>
        <th>发布城市</th>
    </tr>
    <c:forEach items="${postsList}" var="Posts" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
            <td>${Posts.title}</td>
            <td>${Posts.category}</td>
                <%--    <td>${Posts.contents}</td>--%>
            <td>${Posts.postingTime}</td>
            <td><a href="/project/homepageServlet?name=${Posts.authorName}">${Posts.authorName}</a></td>
            <td>${Posts.postingCity}</td>
            <td>${Posts.postingCountry}</td>
            <td><a href="/project/postDetailServlet?postID=${Posts.postID}">详情</a></td>
            <c:if test= "${username != \"!anonymous\"}">
                <td><a href="/project/likeServlet?postID=${Posts.postID}&page=postlist">点赞</a></td>
                <td><a href="/project/shareServlet?postID=${Posts.postID}&page=postlist">转发</a></td>
                <td><a href="/project/favoriteServlet?postID=${Posts.postID}&page=postlist">收藏</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
