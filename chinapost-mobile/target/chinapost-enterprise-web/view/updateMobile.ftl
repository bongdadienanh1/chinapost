<!DOCTYPE html>
<html>
<head>
    <#assign basePath=request.contextPath>  
  <meta charset="UTF-8">
  <title>个人资料 - 编辑联系电话</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
  <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
  <script src="${basePath}/static/js/idangerous.swiper.js"></script>
</head>
<body>
<form action="${basePath}/orderupdateaddress" id="addForm" method="post">
<div class="mui-appbar">
    <h2 class="mui-text-center">编辑联系地址</h2>
    <a href="javascript:history.go(-1);" class="back-btn">
        <i class="glyphicon glyphicon-chevron-left"></i>
    </a>
</div>
<div class="member_info">
    <dl>
        <dt>联系电话</dt>
        <dd>
            <input type="text">
        </dd>
    </dl>
</div>
</form>
<div class="p10">
  <div class="col-12">
    <div class="pl15">
      <a href="javascript:void(0)" class="btn btn-full" onclick="checkForm()">保存</a>
    </div>
  </div>
</div>

<div class="foot" style="height: 100px;">
</div><!-- /foot -->

<script>

</script>
</body>
</html>