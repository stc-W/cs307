<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/7
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${user.username}的个人主页</title>
    <link href="css/homepage.css" rel="stylesheet">
</head>
<body>
<a href="homepageServlet?name=${username}">返回个人主页</a>
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
<hr>
<c:if test= "${user.username != \"!anonymous\"}">
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>用户名</th>
            <th>电话</th>
            <th>注册时间</th>
        </tr>
        <tr align = "center">
            <td>${user.username}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.registrationTime}</td>
        </tr>
    </table>
    <hr>
    <h3>我发的帖子</h3>
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>序号</th>
            <th>标题</th>
            <th>类别</th>
                <%--        <th>内容</th>--%>
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
                <td>${Posts.postingTime}</td>
                <td>${Posts.authorName}</td>
                <td>${Posts.postingCity}</td>
                <td>${Posts.postingCountry}</td>
                <c:if test= "${username != \"!anonymous\"}">
                    <td><a href="/project/likeServlet?postID=${Posts.postID}&backname=${user.username}&page=homepage">点赞</a></td>
                    <td><a href="/project/shareServlet?postID=${Posts.postID}&backname=${user.username}&page=homepage">转发</a></td>
                    <td><a href="/project/favoriteServlet?postID=${Posts.postID}&backname=${user.username}&page=homepage">收藏</a></td>
                </c:if>
                <c:if test = "${user.username == username}">
                    <td><a href="/project/deletePostServlet?postID=${Posts.postID}">删除帖子</a></td>
                    <td><a href="/project/selectByIDChangeServlet?postID=${Posts.postID}">修改</a></td>
                </c:if>
                <td><a href="/project/postDetailServlet?postID=${Posts.postID}">详情</a></td>

            </tr>
        </c:forEach>
    </table>
    <hr>
    <h3>我喜欢的帖子</h3>
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
        <c:forEach items="${likerList}" var="Posts" varStatus="status">
            <tr align="center">
                <td>${status.count}</td>
                <td>${Posts.title}</td>
                <td>${Posts.category}</td>
                    <%--      <td>${Posts.contents}</td>--%>
                <td>${Posts.postingTime}</td>
                <td><a href="/project/homepageServlet?name=${Posts.authorName}">${Posts.authorName}</a></td>
                <td>${Posts.postingCity}</td>
                <td>${Posts.postingCountry}</td>
                <td><a href="/project/postDetailServlet?postID=${Posts.postID}">详情</a></td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <h3>我分享的帖子</h3>
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>序号</th>
            <th>标题</th>
            <th>类别</th>
                <%--  <th>内容</th>--%>
            <th>发布时间</th>
            <th>作者姓名</th>
            <th>发布国家</th>
            <th>发布城市</th>
        </tr>
        <c:forEach items="${shareList}" var="Posts" varStatus="status">
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
            </tr>
        </c:forEach>
    </table>
    <hr>
    <h3>我收藏的帖子</h3>
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
        <c:forEach items="${favoriteList}" var="Posts" varStatus="status">
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
            </tr>
        </c:forEach>
    </table>
    <hr>
    <h3>我的回复</h3>
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>序号</th>
            <th>点赞数</th>
            <th>内容</th>
            <th>帖子id</th>
        </tr>
        <c:forEach items="${firstReplyList}" var="FirstReply" varStatus="status">
            <tr align="center">
                <td>${status.count}</td>
                <td>${FirstReply.stars}</td>
                <td>${FirstReply.contents}</td>
                <td><a href="/project/postDetailServlet?postID=${FirstReply.postID}">${FirstReply.postID}</a> </td>
            </tr>
        </c:forEach>
    </table>
    <h3>我的二级回复</h3>
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>序号</th>
            <th>点赞数</th>
            <th>内容</th>
            <th>一级回复</th>
        </tr>
        <c:forEach items="${secondReplyList}" var="SecondReply" varStatus="status">
            <tr align="center">
                <td>${status.count}</td>
                <td>${SecondReply.stars}</td>
                <td>${SecondReply.contents}</td>
                <td><a href="/project/addSecondReplyServlet?fatherid=${SecondReply.fatherID}">${SecondReply.fatherID}</a></td>

            </tr>
        </c:forEach>
    </table>
    <hr>
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>我喜欢的作者</th>
        </tr>
        <c:forEach items="${IFollowList}" var="Follow" varStatus="status">
            <tr align="center">
                <td><a href="/project/homepageServlet?name=${Follow.authorName}">${Follow.authorName}</a></td>
                <c:if test="${user.username == username}">
                    <td><a href="/project/deleteFollowServlet?authorname=${Follow.authorName}">取消关注</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <table border="1" cellspacing="0" width="80%">
        <tr>
            <th>喜欢我的作者</th>
        </tr>
        <c:forEach items="${FollowMeList}" var="Follow" varStatus="status">
            <tr align="center">
                <td><a href="/project/homepageServlet?name=${Follow.authorFollowedBy}">${Follow.authorFollowedBy}</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<hr>
<input type="button" value="最受欢迎" id="like" class="centered-button postlist-button"><br>
<script>
    document.getElementById("like").onclick = function (){
        location.href = "/project/hotLikePostServlet";
    }
</script>
<hr>
<input type="button" value="帖子广场" id="postlist" class="centered-button postlist-button"><br>
<script>
    document.getElementById("postlist").onclick = function (){
        location.href = "/project/postSquareServlet";
    }
</script>
<hr>
<c:if test="${user.username == username}">
    <input type="button" value="发帖" id="add" class="centered-button postlist-button"><br>
    <script>
        document.getElementById("add").onclick = function (){
            location.href = "/project/addPosts.jsp";
        }
    </script>

    <style>
        .centered-button{
            display: block;
            margin: 0 auto;
            font-size:20px ;
        }
        .postlist-button{
            background-color: #3498db;
            color: #fff;
        }

    </style>
</c:if>
<c:if test= "${username != \"!anonymous\"}">
    <c:if test="${user.username != username}">
        <input type="button" value="关注" id="follow" class="centered-button postlist-button"><br>
        <script>
            document.getElementById("follow").onclick = function () {
                location.href = "/project/followServlet?page=homepage&authorname=${user.username}";
            }
        </script>


    </c:if>
</c:if>
</body>
</html>
