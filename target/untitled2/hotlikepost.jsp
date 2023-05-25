<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/24
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/hotlikepost.css">
</head>
<body>
<h1>点赞榜</h1>
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
        <th>点赞数</th>
    </tr>
    <c:forEach items="${posts}" var="Posts" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
            <td>${Posts.title}</td>
            <td>${Posts.category}</td>
                <%--    <td>${Posts.contents}</td>--%>
            <td>${Posts.postingTime}</td>
            <td><a href="/project/homepageServlet?name=${Posts.authorName}">${Posts.authorName}</a></td>
            <td>${Posts.postingCity}</td>
            <td>${Posts.postingCountry}</td>
            <td>${hotLikePost.get(status.count - 1).num}</td>
            <td><a href="/project/postDetailServlet?postID=${Posts.postID}">详情</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
