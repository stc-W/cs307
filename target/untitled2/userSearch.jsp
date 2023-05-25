<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/8
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>作者列表</title>
</head>
<body>
<a href="homepageServlet?name=${username}">返回个人主页</a>
<link rel="stylesheet" type="text/css" href="css/userSearch.css">
<hr>
<table>
    <th>搜索结果(点击姓名可进入其主页)</th>
<c:forEach items="${usernames}" var="String" varStatus="status">
    <tr align="center">
    <td><a href="/project/homepageServlet?name=${String}">${String}</a> </td>
        <td><a href="/project/followServlet?authorname=${String}&page=userSearch&S=${username}">关注</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
