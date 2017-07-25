<!DOCTYPE html>
<html lang="en">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>快捷登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201606201528"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
</head>
<body>
<div class="content-login mb50">
    <input type="hidden"  id="basePath" value="${basePath}"/>
    <input type="hidden" id="urlhide" value="${url!''}" />
    <div class="login-banner">
        <img src="${basePath}/static/img/pic_banner_login.png" alt=""/>
        <#--<div class="user">-->
            <#--<div class="user-pic"><img src="${basePath}/static/img/user-pic.png" alt=""/></div>-->
        <#--</div>-->
        <#--<div class="tab-menu">-->
            <#--<a class="cur" href="javascript:;">账号密码登录</a>-->

        <#--</div>-->
    </div>
    <form method="post" id="form_register" action="#">
        <div class="in-box">
            <div class="list-item">
                <label for=""><img class="login_user" src="${basePath}/static/img/icon_account_login.png"></label>
                <input type="text" name="username" maxlength="18" id="username" placeholder="输入身份证号或绑定的手机号"/>
                <#--<a id="get-confirmcode" class="get-confirmcode" href="javascript:void(0)" onClick="codeNock();">获取验证码</a>-->
            </div>
            <div class="list-item">
                <label for=""><img class="login_user" src="${basePath}/static/img/icon_password_login.png"></label>
                <input type="password" name="password" id="password" placeholder="请输入密码"/>
            </div>
            <#--<div class="list-item" id="box_vcode" style="display: none;">-->
                <#--<label for="">图形验证码</label>-->
                <#--<input type="text" name="vcode" id="vcode" placeholder="输入图形验证码"/>-->
                <#--<img id="checkCodeImg" class="get-confirmcode" src="${basePath}/patchca.htm?${.now?string("HHmmss")}" onclick="this.src=this.src+'?'+Math.random(); ">-->
            <#--</div>-->
        </div>
    </form>
    <div class="list-item regist">
        <a class="btn btn-full" href="javascript:;" onClick="login();"><i>登&nbsp;录</i></a>
    </div>
</div>
<script type="text/javascript">
    function login(){
        var userName = $("#username").val();
        var password = $("#password").val();
        if(userName == "" || password == ""){
            alertStr("账号密码不能为空！");
            return;
        }else {
            $.post("checkLogin",{
                username:userName,
                password:password
            },function(data){
                if(data.response == 'success'){
                    //跳转首页
                    window.location.href = $('#urlhide').val();
                }else {
                    alertStr("账号或密码输入错误！！");
                    return;
                }
            },'json');
        }
    }
    function alertStr(str){
        $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>'+str+'</h3></div></div>');
        setTimeout(function(){
            $('.tip-box').remove();
        },1000);
    }
</script>
</body>
</html>