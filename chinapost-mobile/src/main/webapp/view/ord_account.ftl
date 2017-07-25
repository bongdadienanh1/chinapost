<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>会员中心 - 登录密码</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=20162231534"/>
</head>
<body>
<input type="hidden" id="mobile" value="${custInfo.infoMobile!''}"/>
<input type="hidden" id="isMobile" value="${custInfo.isMobile!''}"/>
<div class="member_info">
    <dl>
        <dt>登录密码</dt>
        <dd>
            <a href="${basePath}/passwordindex">
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>
    <dl>
        <dt>支付密码</dt>
        <dd>
            <a href="${basePath}/paypasswordindex">
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>
</div>

</body>
</html>