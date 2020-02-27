<%--
  Created by IntelliJ IDEA.
  User: lifubao
  Date: 2020/2/20
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="/req.do?_method=add">新增一个属性</a>
  <a href="/req.do?_method=remove">移除一个属性</a>
  <a href="/req.do?_method=replace">替换一个属性</a>
  <h1>当前在线人数为:${onlineUsers}</h1>
  <a href="/logout.do">安全退出</a>

  <hr>
  文件上传：
  <form action="/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="pic" value=""><br>
    <input type="text" name="username" value=""><br>
    <input type="submit" value="上传">
  </form>
<h1>文件下载</h1>
  <a href="/download.do?filename=account.png">下载</a>
  </body>

</html>
