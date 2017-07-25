<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>会员中心 - 支付密码</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=20162231534"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css">
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
</head>
<body>
<input type="hidden" id="isExist" value="${isExist!''}"/>
<#if isExist="1">
<div class="member_info">
    <dl>
        <dt>修改支付密码</dt>
        <dd>
            <a href="${basePath}/paypassword?flag=1">
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>
    <dl>
        <dt>忘记支付密码</dt>
        <dd>
            <a href="javascript:;" class="dialog">
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>
</div>
<#else>
<div class="member_info">
    <dl>
        <dt>设置支付密码</dt>
        <dd>
            <a href="${basePath}/paypassword?flag=0">
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>
</div>
</#if>
<script type="text/javascript">
    $(".dialog").click(function(){
        var forget = dialog({
            width: 260,
            title:"提示",
            content:'<p>请去附近的邮储银行网点，找工作人员重置您的支付密码</p>',
            cancelValue: '知道了',
            cancel:function(){
                return true;
            }
        });
        forget.showModal();
    })
</script>
</body>
</html>