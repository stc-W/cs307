<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/21
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>二级回复</title>
  <link rel="stylesheet" type="text/css" href="css/secondreply.css">
</head>
<body>
<%HttpSession httpSession = request.getSession();
  String username = (String) httpSession.getAttribute("username");%>
<a href="homepageServlet?name=${username}">返回个人主页</a>
<hr>
<div class="container">



<h3>一级回复内容</h3>
<table border="1" cellspacing="0" width="80%">
  <tr>
    <th>点赞数</th>
    <th>内容</th>
    <th>作者姓名</th>
    <th>帖子id</th>
  </tr>
    <tr align="center">
      <td>${firstReply.stars}</td>
      <td>${firstReply.contents}</td>
      <td><a href="/project/homepageServlet?name=${firstReply.firstReplyID}">${firstReply.firstReplyID}</a></td>
      <td><a href="/project/postDetailServlet?postID=${firstReply.postID}">${firstReply.postID}</a> </td>
    </tr>
</table>
<hr>
<table border="1" cellspacing="0" width="80%">
  <tr>
    <th>序号</th>
    <th>点赞数</th>
    <th>内容</th>
    <th>作者姓名</th>
  </tr>
  <c:forEach items="${secondReplyList}" var="SecondReply" varStatus="status">
    <tr align="center">
      <td>${status.count}</td>
      <td>${SecondReply.stars}</td>
      <td>${SecondReply.contents}</td>
      <td><a href="/project/homepageServlet?name=${SecondReply.secondReplyID}">${SecondReply.secondReplyID}</a></td>
    </tr>
  </c:forEach>
</table>
<form action="addSecondServlet" method="post">
  <input type="hidden" name="fatherid" value="${id}">
  <tr>
    <td>内容</td>
    <td><textarea name="content" cols="30" rows="10" required></textarea></td>
  </tr>
  <tr>
    <td><input type="submit" value="回复"></td>
  </tr>

  </table>
</form>

  </div>

</body>
</html>
