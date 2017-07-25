<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>会员中心 - 修改登录密码</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
  <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
  <script src="${basePath}/static/js/idangerous.swiper.js"></script>
    <style>
        .member_form2 dl dt{color: #333;}
    </style>
</head>
<body>
<input type="hidden" value="${flag!''}" id="flag">
<div class="member_form2">
    <input type="hidden" id="basePath" value="${basePath}"/>
    <dl>
        <dt>原登录密码：</dt>
        <dd>
                <input type="password" class="text pad15" id="oldLoginPassword" maxlength="20"  placeholder="原登录密码"/>
        </dd>
    </dl>
  <dl>
    <dt>登录密码：</dt>
    <dd>
        <input type="password" class="text pad15" id="loginPassword" maxlength="20"  placeholder="登录密码"/>
    </dd>
  </dl>
  <dl>
    <dt>确认登录密码：</dt>
    <dd>
        <input type="password" id="aginLoginPassword" maxlength="20" placeholder="确认登录密码" class="text pad15">
    </dd>
  </dl>
</div>
<div class="p10 mb50">
  <a href="javascript:void(0)" class="btn btn-full">提交</a>
</div>
<script>
    $(".btn-full").click(function(){
        var password = $("#loginPassword").val();
        var aginpassword = $("#aginLoginPassword").val();
        var oldPassword =$("#oldLoginPassword").val();
        if(checkInput()){
            $.ajax({
                type: 'post',
                url: "updatePassword?Password="+password+"&newpassword="+aginpassword+"&oldPassword="+oldPassword,
                success: function(data){
                    if(data.response == "success"){
                        alertStr("修改成功");
                        window.location.href = $("#basePath").val() + "/customercenter";
                    }else {
                        if(data.data.errorcode != ""){
                            if(data.data.errorcode == "1"){
                                alertStr(data.data.text);
                                window.location.href = $("#basePath").val() + "/login";
                            }
                            alertStr(data.data.text);
                        };
                    }
                }
            });
        }
    });

    function checkInput(){
        var password = $("#loginPassword").val();
        var newpassword = $("#aginLoginPassword").val();
        var repeat = /^[0-9a-zA-Z]*$/g;
        if(password == null || password == ''){
            alertStr("请输入登录密码");
            return false;
        }
        if(!repeat.test(password)){
            alertStr("登录密码格式错误");
            return false;
        }
        if(password != newpassword){
            alertStr("两次输入的登录密码不一致");
            return false;
        }
        if(newpassword == null || newpassword == ''){
            alertStr("请确认登录密码");
            return false;
        }
        return true;
    }


    $("#loginPassword").focus(function(){
        $(this).attr("placeholder","");
    });
    $("#loginPassword").blur(function(){
        if ($(this).val()==null || $(this).val()=='') {
            $(this).attr("placeholder","登录密码");
        };
        if($(this).val()<6){
            alertStr("登录密码不小于6位");
            $(this).val("");
            $(this).attr("placeholder","登录密码6-20位");
        }
    });
    $("#aginLoginPassword").focus(function(){
        $(this).attr("placeholder","");
    });
    $("#aginLoginPassword").blur(function(){
        if ($(this).val()==null || $(this).val()=='') {
            $(this).attr("placeholder","登录密码6-20位");
        };
        if($(this).val()<6){
            alertStr("登录密码不小于6位");
            $(this).val("");
            $(this).attr("placeholder","登录密码6-20位");
        }
    });
    $("#oldLoginPassword").focus(function(){
        $(this).attr("placeholder","");
    });
    $("#oldLoginPassword").blur(function(){
        if ($(this).val()==null || $(this).val()=='') {
            $(this).attr("placeholder","登录密码6-20位");
        };
        if($(this).val()<6){
            alertStr("登录密码不小于6位");
            $(this).val("");
            $(this).attr("placeholder","登录密码6-20位");
        }
    });

    $(".bar-bottom a:eq(4)").addClass("selected");
</script>
<script src="${basePath}/static/js/common.js"></script>
</body>
</html>