<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>会员中心 - 修改支付密码</title>
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
    <input type="hidden" id="flag" value="${flag!''}"/>
    <#if flag ="1">
    <dl>
        <dt>原支付密码：</dt>
        <dd>
            <input type="password" class="text pad15" id="oldPayPassword" maxlength="6"  placeholder="原支付密码6位数字"/>
        </dd>
    </dl>
    </#if>
  <dl>
    <dt>支付密码：</dt>
    <dd>
        <input type="password" class="text pad15" id="payPassword" maxlength="6"  placeholder="支付密码6位数字"/>
    </dd>
  </dl>
  <dl>
    <dt>确认支付密码：</dt>
    <dd>
        <input type="password" id="aginPayPassword" maxlength="6" placeholder="支付密码6位数字" class="text pad15">
    </dd>
  </dl>
</div>
<div class="p10 mb50">
  <a href="javascript:void(0)" class="btn btn-full">提交</a>
</div>
<script>
    $(".btn-full").click(function(){
        var password = $("#payPassword").val();
        var aginpassword = $("#aginPayPassword").val();
        var oldpayPassword =$("#oldPayPassword").val();
        var flag = $("#flag").val();
        if(checkInput()){
            $.ajax({
                type: 'post',
                url: "updatePayPassword?payPassword="+password+"&newpassword="+aginpassword+"&oldpayPassword="+oldpayPassword+"&flag="+flag,
                success: function(data){
                    if(data.response == "success"){
                        alertStr("修改成功");
                        if(data.data != "" && typeof(data.data) !='undefined'){
                            window.location.href = $("#basePath").val()+"/"+data.data;
                        }else {
                            window.location.href = $("#basePath").val() + "/customercenter";
                        }
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
        var password = $("#payPassword").val();
        var newpassword = $("#aginPayPassword").val();
        if(password == null || password == ''){
            alertStr("请输入支付密码");
            return false;
        }
        if(!(/^\d{6}$/).test(password)){
            alertStr("支付密码格式错误");
            return false;
        }
        if(password != newpassword){
            alertStr("两次输入的支付密码不一致");
            return false;
        }
        if(newpassword == null || newpassword == ''){
            alertStr("请确认支付密码");
            return false;
        }
        return true;
    }


    $("#payPassword").focus(function(){
        $(this).attr("placeholder","");
    });
    $("#payPassword").blur(function(){
        if ($(this).val()==null || $(this).val()=='') {
            $(this).attr("placeholder","支付密码6位数字");
        }
    });
    $("#aginPayPassword").focus(function(){
        $(this).attr("placeholder","");
    });
    $("#aginPayPassword").blur(function(){
        if ($(this).val()==null || $(this).val()=='') {
            $(this).attr("placeholder","支付密码6位数字");
        }
    });


    $(".bar-bottom a:eq(4)").addClass("selected");
</script>
<script src="${basePath}/static/js/common.js"></script>
</body>
</html>