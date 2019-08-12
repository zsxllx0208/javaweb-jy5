<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/6
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>念咒</title>
</head>
<body>
<form action="">
    <input type="text" placeholder="请输入咒语" id="zy">
    <input type="submit" value="提交" id="tj" onclick="tijiao()">
</form>
</body>
<script>
    function tijiao() {
        var zy = document.getElementById("zy");
        if(zy.value==="芝麻开门"){
            alert("开始试炼")
        }else {
            alert("没有反应")
        }
    }
</script>
</html>
