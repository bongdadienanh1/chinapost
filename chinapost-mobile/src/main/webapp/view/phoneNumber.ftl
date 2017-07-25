
<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>个人资料 - 编辑手机号码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="${basePath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
    <link rel="stylesheet" href="${basePath}/static/css/myUb.css"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/idangerous.swiper.js"></script>
    <script src="${basePath}/static/js/customer/customer_share.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
    <style>
        .passContainer{
            border:none;
        }
    </style>
</head>
<body>
<input type="hidden" value="${basePath}" id="basePath">

<form action="${basePath}/updateAddress" id="updateAddress" method="post">
    <div class="member_form">
        <input type='hidden' id="provinceCh" name="provinceId" value="${customer.provinceId!''}"/>
        <input type='hidden' id="cityCh" name="cityId" value="${customer.cityId!''}"/>
        <input type='hidden' id="countyCh" name="districtId" value="${customer.districtId!''}"/>

        <dl>
            <dt>手机号码</dt>
            <dd>
                <#if customer.phoneNo != ''>
                    <textarea rows="4" class="text" name="addr" id="phoneInput" style="height: 3.0em; background-color: #ffffff;" placeholder="${customer.phoneNo}"  disabled="disabled">${customer.phoneNo!''}</textarea>
                <#else>
                    <textarea rows="4" class="text" name="addr" id="phoneInput" style="height: 3.0em;padding: 5px;" placeholder="${phoneNo}">${phoneNo!''}</textarea>
                </#if>

            </dd>
        </dl>
    </div>
</form>
<form id="phonenoform" action="${basePath}/updateMaleAndPhone">
    <input type="hidden" value="" name="phoneNo" id="phonenoinput">
</form>
<div class="p10">
<#--<div class="col-12">-->
<#--<div class="pr15">-->
<#--<a href="javascript:;" class="btn btn-full-grey" onClick="javascript :history.back(-1);">取消</a>-->
<#--</div>-->
<#--</div>-->
<#if customer.phoneNo == ''>
    <div class="col-24">
        <div class="pl15">
        <#--<a class="btn btn-full" onclick="checkForm1()">确定</a>-->
        <#--<button class="btn btn-full" type="button" onclick="checkForm1()">确定</button>-->
            <a href="javascript:void(0)" class="btn btn-full" onclick="checkForm1()">确定</a>
        </div>
    </div>
<#else>
    <div class="col-24">
        <div class="pl15">
            <a href="javascript:void(0)" class="btn btn-full" onclick="checkForm2()">更换手机号</a>
        </div>
    </div>
</#if>

</div>

<div class="foot" style="height: 100px;">
</div><!-- /foot -->

<script>
    $(function(){

    });


    //    验证手机号码格式
    function checkMobile(str) {
        if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|177)[0-9]{8}$/.test(str.trim()))) {
            alertStr("手机号码格式错误，请重新输入！");
            return false;
        }
        else {
            return true;
        }
    }
    //新增绑定的手机号
    function checkForm1(){
        checkMobile($('#phoneInput').val());
        if(checkMobile($('#phoneInput').val()) == true){
            $.post('${basePath}/checkExistsPayPassWord?phoneNo='+$('#phoneInput').val(), {}, function (tt) {
                if (tt.response == "success") {
                    var zhifumima = dialog({
                        width: 260,
                        content: '<p class="tc">输入支付密码</p><p class="tc"><div class="passContainer"> <div class="input-box"> <input type="number" value="" class="passwordInput" maxlength="6"/> </div> <div class="passItem-box"> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div></div> </div></p> ',
                        okValue: '确定',
                        cancelValue: '取消',
                        ok: function () {
                            var password = $(".passwordInput").val();
                            $.ajax({
                                type: "POST",
                                url: "checkPayPassword",
                                data: {
                                    payPassword:password
                                },
                                dataType: "json",
                                traditional: true,
                                success: function(data){
                                    if(data.response == "success"){
                                        var phone = $('#phoneInput').val();
                                        $('#phonenoinput').val(phone);
                                        $('#phonenoform').attr("action","${basePath}/updateMaleAndPhone");
                                        $('#phonenoform').submit();
                                        return true;
                                        <#--$.post('updateMaleAndPhone',{-->
                                                    <#--phoneNo:$("#phoneInput").val()-->
                                                <#--},-->
                                                <#--function(data){-->
                                                    <#--if(data.response == "success"){-->
<#--//                                                        window.reload();-->
                                                        <#--window.location.href = "${basePath}/account";-->
                                                    <#--}else {-->
                                                        <#--alertStr(data.data.text);-->
                                                    <#--}-->
                                                <#--},'json');-->
                                    }else {
                                        var text = data.data.text;
                                        alertStr(text);
                                        <#--setTimeout(function(){-->
                                            <#--window.location.href = "${basePath}/account";-->
                                        <#--},1000);-->

                                        <#--window.location.href = "${basePath}/account"-->
                                    }
                                }
                            });
                        },
                        cancel: function () {
                            //停留在当前页面
                            return true;
                        }
                    });

                    zhifumima.showModal();

                    $('.passContainer').click(function () {
                        $('.passwordInput').focus();
                    });

                    $('.passwordInput').bind("input propertychange change", function (event) {
                        if (event.type != "propertychange" || event.originalEvent.propertyName == "value") {
                            if ($('.passwordInput').val().length > 6) {
                                $('.passwordInput').val($('.passwordInput').val().substr(0, 6));
                            }

                            $('.passContainer .passItem-box .passItem').html("");
                            for (var i = 0; i < $('.passwordInput').val().length; i++) {
                                $('.passContainer .passItem-box .passItem').eq(i).html("*");
                            }
                        }
                    });


                    $('.passwordInput').focus();

                } else {
                    var shezhimima = dialog({
                        width: 260,
                        content: '<p class="tc">去设置支付密码</p> ',
                        okValue: '去设置支付密码',
                        cancelValue: '取消',
                        ok: function () {
                            window.location.href = '${basePath}/paypassword?flag=2';
                        },
                        cancel: function () {
                            //停留在当前页面
                            return true;
                        }
                    });
                    shezhimima.showModal();
                }
            });
        }
    }

    //重新绑定的手机号
    function checkForm2(){
        checkMobile($('#phoneInput').val());
        if(checkMobile($('#phoneInput').val()) == true){
            $.post('${basePath}/checkExistsPayPassWord', {}, function (tt) {
                if (tt.response == "success") {
                    var zhifumima = dialog({
                        width: 260,
                        content: '<p class="tc">输入支付密码</p><p class="tc"><div class="passContainer"> <div class="input-box"> <input type="number" value="" class="passwordInput" maxlength="6"/> </div> <div class="passItem-box"> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div></div> </div></p> ',
                        okValue: '确定',
                        cancelValue: '取消',
                        ok: function () {
                            $.ajax({
                                type: "POST",
                                url: "checkPayPassword",
                                data: {
                                    payPassword:$(".passwordInput").val(),
//                                    phoneNo:$("#phoneInput").val()
                                },
                                dataType: "json",
                                traditional: true,
                                success: function(data){
                                    if(data.response == "success"){
                                    <#--window.location.href = "${basePath}/account";-->
                                        var reasonBox = dialog({
                                            width : 260,
                                            title : '请输入需要绑定的手机号',
                                            content : '<form id="upcellphone"><input type="text" placeholder="请输入手机号码..." style="width: 60%; margin-left: 20%;" id="cellphoneinput"><input type="hidden" value="" id="cellphonehidden" name="phoneNo"></form>',
                                            okValue : '确定',
                                            cancelValue : '取消',
                                            ok : function(){
                                                var cellphone = $('#cellphoneinput').val();
                                                if(checkMobile(cellphone)){
                                                    $('#cellphonehidden').val(cellphone);
                                                    $("#upcellphone").attr("action","${basePath}/updateMaleAndPhone");
                                                    $("#upcellphone").submit();
                                                    return true;
                                                }
                                            },
                                            cancel : function(){
                                                return true;
                                            }
                                        });
                                        reasonBox.showModal();
                                    }else {
                                        var text = data.data.text;
                                        alertStr(text);
                                        <#--setTimeout(function(){-->
                                            <#--window.location.href = "${basePath}/account";-->
                                        <#--},1000);-->

                                    }
                                }
                            });
                        },
                        cancel: function () {
                            //停留在当前页面
                            return true;
                        }
                    });

                    zhifumima.showModal();

                    $('.passContainer').click(function () {
                        $('.passwordInput').focus();
                    });

                    $('.passwordInput').bind("input propertychange change", function (event) {
                        if (event.type != "propertychange" || event.originalEvent.propertyName == "value") {
                            if ($('.passwordInput').val().length > 6) {
                                $('.passwordInput').val($('.passwordInput').val().substr(0, 6));
                            }

                            $('.passContainer .passItem-box .passItem').html("");
                            for (var i = 0; i < $('.passwordInput').val().length; i++) {
                                $('.passContainer .passItem-box .passItem').eq(i).html("*");
                            }
                        }
                    });


                    $('.passwordInput').focus();

                } else {
                    var shezhimima = dialog({
                        width: 260,
                        content: '<p class="tc">去设置支付密码</p> ',
                        okValue: '去设置支付密码',
                        cancelValue: '取消',
                        ok: function () {
                            window.location.href = '${basePath}/paypassword?flag=2';
                        },
                        cancel: function () {
                            //停留在当前页面
                            return true;
                        }
                    });
                    shezhimima.showModal();
                }
            });
        }
    }


</script>
</body>
<script src="${basePath}/static/js/myacountaddress.js"></script>
</html>