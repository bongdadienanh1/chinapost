<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>会员中心 - 个人资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201606231503"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/idangerous.swiper.js"></script>
    <script src="${basePath}/static/js/myacountaddress.js"></script>
    <script src="${basePath}/static/js/customer/exif.js"></script>
    <script src="${basePath}/static/js/customer/lrz.js"></script>
    <script src="${basePath}/static/js/customer/customer_share.js"></script>
</head>
<body>
<input type="hidden" value="${basePath}" id="basePath">
<div class="member_info">
    <#--<dl>-->
        <#--<dt>用户名</dt>-->
        <#--<dd>-->
            <#--<a href="javascript:void(0)">-->
                <#--<span>${cust.customerUsername!''}</span>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
    <dl class="ordImgBox">
        <dt>头像</dt>
        <dd>
            <a href="javascript:void(0)" class="positionIcon">
                <#if imgUrl??>
                    <#assign imgSrc = imgUrl?substring(0,imgUrl?last_index_of("."))/>
                    <img class="myOrdImg" src="${imgSrc+"_160x160"+imgUrl?substring(imgUrl?last_index_of("."))}" width="100%"/>
                <#else >
                    <img class="myOrdImg" src="${basePath}/static/img/avatar.png" class="myOrdImg" width="100%"/>
                </#if>
                <#--<i class="ion-ios-arrow-right"></i>-->
            </a>
        </dd>
    </dl>
    <#--<dl>-->
        <#--<dt>会员级别</dt>-->
        <#--<dd>-->
            <#--<a href="javascript:void(0)">-->
                <#--<span class="label">${cust.pointLevelName!''}</span>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
    <dl>
        <dt>姓名</dt>
        <dd>
            <a href="javascript:;">
                <span>
                    <#if fullname?? && fullname != ''>
                        ${fullname!''}
                    </#if>
                </span>
            </a>
        </dd>
    </dl>
    <#--<dl>-->
        <#--<dt>姓名</dt>-->
        <#--<dd>-->
            <#--<a href="${basePath}/changename.html">-->
                <#--<span>${cust.infoRealname!''}</span>-->
                <#--<i class="ion-ios-arrow-right"></i>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
    <dl>
        <dt>性别</dt>
        <dd>
            <a href="javascript:;" class="gender_set">
        <span>
        <#--<#if male??>-->
            <#--<#switch cust.infoGender>-->
                <#--<#case '0'>-->
                    <#--保密-->
                    <#--<#break>-->
                <#--<#case '1'>-->
                    <#--男-->
                    <#--<#break>-->
                <#--<#case '2'>-->
                    <#--女-->
                    <#--<#break>-->
                <#--<#default>-->
            <#--</#switch>-->
        <#--</#if>-->
            <#if male?? && male!=''>
                <#if male == "true">
                    男
                <#else >
                     女
                </#if>
            <#else>
                保密
            </#if>
        </span>
            </a>
        </dd>
    </dl>
    <dl>
        <dt>手机号码</dt>
        <dd>
            <#if phoneNo??&& phoneNo!=''>
                <a href="javascript:;">
                    <span>${phoneNo!''}</span>
                </a>
            <#else >
                <a href="javascript:;">
                    <span>未绑定</span>
                    <i class="ion-ios-arrow-right"></i>
                </a>
            </#if>
        </dd>
    </dl>
    <dl>
        <dt>身份证号</dt>
        <dd>
            <a href="javascript:;">
                <span><#if idcardNo?? && idcardNo!=''>${idcardNo!''}<#else>未绑定</#if></span>
            </a>
        </dd>
    </dl>
    <dl>
        <dt>联系地址</dt>
        <dd>
            <a href="${basePath}/contactAddress">
                <span><#if contactAddr?? && contactAddr != ''>${contactAddr!''}<#else >未设置地址</#if></span>
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>
    <dl>
        <dt>联系电话</dt>
        <dd>
            <a href="javascript:;">
                <span class="local"><#if contactPhone??&&contactPhone!=''>${contactPhone!''}<#else>未填写</#if></span>
                <i class="ion-ios-arrow-right"></i>
            </a>
        </dd>
    </dl>

    <#--<dl>-->
        <#--<dt>QQ号码</dt>-->
        <#--<dd>-->
            <#--<a href="${basePath}/changeqq.htm">-->
                <#--<span>${cust.infoQQ!''}</span>-->
                <#--<i class="ion-ios-arrow-right"></i>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
</div>

<#--<div class="member_info">-->
    <#--<dl>-->
        <#--<dt>收货地址管理</dt>-->
        <#--<dd>-->
            <#--<a href="${basePath}/addresslist.html">-->
                <#--<i class="ion-ios-arrow-right"></i>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
    <#--<dl>-->
        <#--<dt>支付密码</dt>-->
        <#--<dd>-->
            <#--<a href="${basePath}/toChangeCustomerPayPassword.htm?url=${url!''}">-->
                <#--<i class="ion-ios-arrow-right"></i>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
    <#--<dl>-->
        <#--<dt>账号安全</dt>-->
        <#--<dd>-->
            <#--<a href="${basePath}/accountsafe.html">-->
                <#--<i class="ion-ios-arrow-right"></i>-->
            <#--</a>-->
        <#--</dd>-->
    <#--</dl>-->
<#--
<dl>
  <dt>账号绑定</dt>
  <dd>
    <a href="${basePath}/phonevalidate4.htm">
      <i class="ion-ios-arrow-right"></i>
    </a>
  </dd>
</dl>
-->
<#--</div>-->

<div class="p10 mb50">
    <a href="javascript:void(0)" class="btn btn-full" id="loginout" style="font-size: 1.2em;font-weight: 500">退出当前登录</a>
</div>

<#--选择上传头像-->
<div class="choose_photo" style="display:none;">
    <a href="javascript:uploadImg()" class="btn btn-full-grey">
        <i class="iconfont icon-unie041"></i> 上传头像
        <input type="file" class="uploadImg" id="uploadFile">
    </a>
    <a href="javascript:;" class="btn btn-full gender_close">取消</a>
    <form id="upload_form" name="upload_form" method="post" enctype="multipart/form-data" action="${basePath}/updateCustomerImg" target="hidden_frame">
        <input type="hidden" id="CustomerImg" name="CustomerImg"/>
    </form>
</div>
<iframe name="hidden_frame" style="display:none"></iframe>
<#--&lt;#&ndash;选择省&ndash;&gt;-->
<#--<div class="screen area-box-lv1" style="display:none;">-->
    <#--<div class="screen-header">-->
        <#--<a class="back" href="javascript:;" id="back">取消</a>-->
        <#--请选择省-->
    <#--</div>-->
    <#--<div class="screen-cont">-->
        <#--<div class="lists province">-->
            <#--<dl id="prodl">-->
            <#--</dl>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="screen area-box-lv2" style="display:none;">-->
    <#--<div class="screen-header">-->
        <#--<a class="back" href="javascript:;" onclick="back1();"><i class="back-icon"></i></a>-->
        <#--请选择市-->
    <#--</div>-->
    <#--<div class="current-area">-->
        <#--已选择：-->
        <#--<span id="readypro"></span>-->
    <#--</div>-->
    <#--<div class="screen-cont">-->
        <#--<div class="lists city">-->
            <#--<dl id="citydl">-->
            <#--</dl>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="screen area-box-lv3" style="display:none;">-->
    <#--<div class="screen-header">-->
        <#--<a class="back" href="javascript:;" onclick="back2();"><i class="back-icon"></i></a>-->
        <#--请选择地区-->
    <#--</div>-->
    <#--<div class="current-area">-->
        <#--已选择：-->
        <#--<span id="readycity"></span>-->
    <#--</div>-->
    <#--<div class="screen-cont">-->
        <#--<div class="lists district">-->
            <#--<dl id="areadl">-->
            <#--</dl>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
<script>
    $(function(){
        /* 选择地区 */
        $('.choose_area').click(function(){
            $('body').append('<div class="opacity-bg-3"></div>');
            $('.area-box-lv1').show();
        });
        $('#back').click(function(){
            $('.opacity-bg-3').remove();
            $('.screen').hide();
            $("#provinceCh").val("");
        });


        $('a.gender_close').click(function(){
            $('.opacity-bg-3').remove();
            $('.gender_choose').hide();
            $('.choose_photo').hide();
        });
        $(".positionIcon").click(function(){
            if($('.opacity-bg-3').length == 0){
                $('body').append('<div class="opacity-bg-3"></div>');
                $('.choose_photo').show();
            }
        });
    });
    $("#loginout").click(function(){
        window.location.href="${basePath}/logout";
    });

    function uploadImg(){
        $(".uploadImg").click();
    }
    $(".bar-bottom a:eq(4)").addClass("selected");
</script>
</body>
</html>