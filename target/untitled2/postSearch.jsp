<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/8
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>搜索结果</title>
</head>
<body>
<a href="homepageServlet?name=${username}">返回个人主页</a>
<link rel="stylesheet" type="text/css" href="css/psotSearch.css">
<hr>
<h1>搜索结果</h1>
<table  border="1" cellspacing="0" width="80%">
    <tr>
        <th>序号</th>
        <th>标题</th>
        <th>类别</th>
        <th>发布时间</th>
        <th>发布国家</th>
        <th>发布城市</th>
        <th>作者姓名</th>
    </tr>
    <c:forEach items="${postsList}" var="Posts" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
            <td>${Posts.title}</td>
            <td>${Posts.category}</td>
            <td>${Posts.postingTime}</td>
            <td>${Posts.postingCity}</td>
            <td>${Posts.postingCountry}</td>
            <td>${Posts.authorName}</td>
            <td><a href="/project/likeServlet?postID=${Posts.postID}&page=postSearch&S=${S}">点赞</a></td>
            <td><a href="/project/shareServlet?postID=${Posts.postID}&page=postSearch&S=${S}">转发</a></td>
            <td><a href="/project/favoriteServlet?postID=${Posts.postID}&page=postSearch&S=${S}">收藏</a></td>
            <td><a href="/project/postDetailServlet?postID=${Posts.postID}">详情</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
