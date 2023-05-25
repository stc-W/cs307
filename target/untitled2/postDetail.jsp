<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/21
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>postDetail</title>
    <link rel="stylesheet" type="text/css" href="css/postdetail.css">
</head>
<body>
<%HttpSession httpSession = request.getSession();
    String username = (String) httpSession.getAttribute("username");%>
<a href="homepageServlet?name=${username}">返回个人主页</a>
<hr>
<div class="container">
    <h1>帖子内容</h1>
    <div class="post-content">

        <div class="content-box">
            ${posts.contents}
            </div>


        </div>

    </div>
    <hr>
    <c:if test="${image != null}">
        <img src="data:image/jpeg;base64, ${image}" alt="Image">
    </c:if>
    <h3>回复</h3>
    <table class="reply-table">
        <tr>
            <th>序号</th>
            <th>点赞数</th>
            <th>内容</th>
            <th>作者姓名</th>
            <th>回复</th>
        </tr>
        <c:forEach items="${firstReplyList}" var="FirstReply" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${FirstReply.stars}</td>
                <td>${FirstReply.contents}</td>
                <td><a href="/project/homepageServlet?name=${FirstReply.firstReplyID}">${FirstReply.firstReplyID}</a></td>
                <td><a href="/project/addSecondReplyServlet?fatherid=${FirstReply.id}">回复</a></td>
            </tr>
        </c:forEach>
    </table>
    <form class="reply-form" action="addFirstReplyServlet" method="post">
        <input type="hidden" name="postID" value="${posts.postID}">
        <div>
            <label for="content">内容</label>
            <textarea id="content" name="content" required></textarea>
        </div>
        <div>
            <input type="submit" value="回复">
        </div>
    </form>
</div>
</body>
</html>
