<%--
  Created by IntelliJ IDEA.
  User: hailong
  Date: 2023/5/24
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>修改</title>
    <link rel="stylesheet" type="text/css" href="css/addPosts.css">
</head>
<body>
<form action="changePostServlet" method="post" enctype="multipart/form-data">
    <table>
        <input type="hidden" name="id" value="${postID}">
        <tr>
            <td>标题</td>
            <td><input type="text" name="title" required></td>
        </tr>
        <tr>
            <td>分类</td>
            <td><input type="text" name="category"></td>
        </tr>
        <tr>
            <td>内容</td>
            <td><textarea name="content" cols="30" rows="10" required></textarea></td>
        </tr>
        <tr>
            <td>图片</td>
            <td><input type="file" name="imageFile"></td>
        </tr>

        <td>国家</td>
        <td>
            <select id="countrySelect" onchange="loadCities()" name="country">
                <option value="">选择国家</option>
                <option value="china">中国</option>
                <option value="usa">美国</option>
                <option value="uk">英国</option>
                <option value="japan">日本</option>
                <option value="German">德国</option>
            </select>
        </td>
        </tr>
        <tr>
            <td>城市</td>
            <td>
                <select id="citySelect" name="city">
                    <option value="">选择城市</option>
                </select>
            </td>
        </tr>

        <script>
            function loadCities() {
                var countrySelect = document.getElementById("countrySelect");
                var citySelect = document.getElementById("citySelect");
                var country = countrySelect.value;


                citySelect.innerHTML = "<option value=''>选择城市</option>";


                if (country === "china") {

                    var cities = ["北京", "上海", "广州", "深圳","石家庄","呼和浩特","太原","沈阳","长春","哈尔滨","南京","杭州","合肥","福州","南昌",
                        "济南","郑州","武汉","长沙","南宁","海口","成都","贵阳","昆明","拉萨","西安","兰州","西宁","银川","乌鲁木齐","台北","香港","澳门","重庆","天津"];
                    for (var i = 0; i < cities.length; i++) {
                        var option = document.createElement("option");
                        option.text = cities[i];
                        option.value = cities[i];
                        citySelect.add(option);
                    }
                } else if (country === "usa") {
                    // 添加美国的城市选项
                    var cities = ["纽约", "洛杉矶", "芝加哥", "休斯顿","费城","底特律","波士顿","匹兹堡","亚特兰大","华盛顿"];
                    for (var i = 0; i < cities.length; i++) {
                        var option = document.createElement("option");
                        option.text = cities[i];
                        option.value = cities[i];
                        citySelect.add(option);
                    }
                } else if (country === "uk") {
                    // 添加英国的城市选项
                    var cities = ["伦敦", "曼彻斯特", "伯明翰", "利物浦"];
                    for (var i = 0; i < cities.length; i++) {
                        var option = document.createElement("option");
                        option.text = cities[i];
                        option.value = cities[i];
                        citySelect.add(option);
                    }
                }
                else if (country==="japan"){
                    var cities = ["名古屋", "大阪", "北海道", "神户"];
                    for (var i = 0; i < cities.length; i++) {
                        var option = document.createElement("option");
                        option.text = cities[i];
                        option.value = cities[i];
                        citySelect.add(option);
                    }
                }
                else if (country==="German"){
                    var cities = ["柏林", "汉堡", "慕尼黑", "科隆","法兰克福"];
                    for (var i = 0; i < cities.length; i++) {
                        var option = document.createElement("option");
                        option.text = cities[i];
                        option.value = cities[i];
                        citySelect.add(option);
                    }
                }

            }
        </script>
        <%--想实现一个下拉可以选择所有国家，然后另外一个显示对应国家的所有城市--%>
        <tr>
            <td><input type="submit" value="修改"></td>
        </tr>
    </table>
</form>
</body>
</html>
