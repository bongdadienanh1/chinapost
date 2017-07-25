<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
     String basePath = path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>意外错误</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="<%=basePath%>static/css/style.min.css?201512311847"/>
  <script src="<%=basePath%>static/js/jquery-1.10.1.js"></script>
</head>
<body>

<div class="content" style="padding:44px 0px 0px 0px;">
  <div class="backImg">
    <img src="<%=basePath%>static/img/pic_wrong500_page.png" width="100%"/>
  </div>
  <p style="color: #333;font-size: 1.2em;text-align: center;margin-top: 1.0em;">抱歉，您访问的页面没有安全着陆...</p>
  <div class="p20" style="text-align: center;">
    <a href="<%=basePath%>main.html" class="btn errorBtn-full">返回首页</a>
  </div>
</div>


</body>
</html>