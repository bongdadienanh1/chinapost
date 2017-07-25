<#assign bath = request.contextPath>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>代客下单</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css">
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css?version=${VERSION}">
    <!-- 分页样式 -->
    <link rel="stylesheet" href="${bath}/static/css/jPages.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>

    <style type="text/css">
        @media screen and ( max-width: 1400px) {
            .userItem,.list,#subsidyPop,.textVal,.success{
                transform: scale(0.8,0.8);
                transform-origin: left top;
                -webkit-transform: scale(0.8,0.8);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.8,0.8);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.8,0.8);
                -moz-transform-origin: left top;

            }
            .list{
                padding-top:0px!important;
                margin-top: 0px!important;

            }
            td,th{
                font-size: 16px!important;
            }
            #subsidyPop{
                margin-top: 0px!important;
            }
            .textVal{
                margin-top: 0px!important;
            }
            #paySubmit{
                right: 200px!important;
            }
        }
        ::-webkit-input-placeholder{color:#666;opacity:.7}:-moz-placeholder{color:#666;opacity:.7}::-moz-placeholder{color:#666;opacity:.7}:-ms-input-placeholder{color:#666;opacity:.7}
        body{
            background-color: #f7f7f7;
        }
        .hidden_address{
            display: none;;
        }
        .newAddress{
            z-index:55;
            width:700px;
            height:auto;
            background:#FFF;
            position:fixed;
            left:10%;
            top:30%;
            border:1px solid #e5e5e5;
            border-radius:2px;

            display: none;
        }
        .newAddress span{
            display:inline-block;
            font-size:14px;
            color:#000;
            padding:5px 0px 0px 20px;
            width:698px;
            height:50px;
            background:#f7f7f7;
        }
        .newAddress ul{
            margin-top:20px;
        }

        .newAddress ul li{
            padding:5px 0px;
            font-size:12px;
        }
        .newAddress ul li abbr{
            display:inline-block;
            font-size:12px;
            width:100px;
            color:#666;
            text-align:right;
            padding:5px;
        }

        .newAddress ul li i{
            display:inline-block;
            margin:0px 3px;
            color:#ff3300;
        }
        .newAddress ul li input{
            width:180px;
            height:25px;
            border-style:none;
            border:1px solid #CCC;
            border-radius:3px;
            background:#fff;
        }
        .newAddress ul li select{
            width:100px;
            height:45px;
            padding:5px;
            border-style:none;
            border:1px solid #CCC;
            border-radius:3px;
            background:#FFF;

        }


        .inputval{
            border-style:none;
            background:transparent;
            margin-left:30px;
            width:400px;
            margin-top:-5px;
        }
        .confirm,.cancel{
            margin:0px 10px;
            display:none;
        }
        #saveAdd{
            width: 100px;
            height: 30px;
            background: #54a6de;
            border-radius: 3px;
            border:1px solid transparent;
            color: #fff;
            margin-left: 100px;
        }
        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            position:relative;

            cursor:pointer;
        }
        .hidden_address{
            width:100%;
            padding: 5px 0px;
            border-bottom: 1px solid #bbbbbb;
            font-size: 14px;
        }
        .hidden_address:hover{
            cursor:pointer;
            border-bottom:2px solid #54a6de;
        }
        .xcConfirm .popBox .ttBox{
            height:60px!important;
        }
        .hidden_address:hover .deleteAdd{
            display: inline-block;
        }
        .deleteAdd{
            display:none;
            z-index: 2;
        }
        .sendWay{
            width: 980px;
            border: 1px solid #e5e5e5;
        }
        .sendWay .tabs{
            margin-left: 10px;
            margin-top: 10px;
            margin-right: 10px;
            color: #e1e1e1;
            height: 50px;
            border-bottom: 1px solid #e5e5e5;
        }
        .sendWay .tabs .tab1{
        }
        .sendWay .tabs .on{
            color: #54a6de;
        }
        #subsidyPop{
            width: 970px;
            margin-left: 50px;
            margin-top: 50px;
            transition: all 0.5s;
        }
        #subsidyPop h1{
            width: 1000px;
            height: 30px;
            line-height: 30px;
            color: #333;
            font-size: 14px;
        }
        #subsidyPop h1 span:first-child{
            float: left;
            margin-left: 20px;
        }
        #subsidyPop h1 span:nth-child(2){
            float: right;
            margin-right: 20px;
        }
        #subsidyPop h1 span:nth-child(2) input{
            margin-top: 8px;
        }
        #subsidyShow{
            font-size: 12px;
            padding: 20px;
            margin-top: 15px;
            border: 1px solid #e5e5e5;
        }
        #subsidyShow li{
            margin: 15px 0px;
            font-size: 18px;
            color: #e5e5e5;
        }
        #subsidyShow li input{
            border:1px solid #e5e5e5;
        }
        #subsidyShow li:first-child i{
            color: #e5e5e5;
        }
        #subsidyLabel{
            display:inline-block;
            width: 46px;
            height: 24px;
            position:relative;
            background: #dddddd;
            border-radius: 20px;
            top: 3px;
            right: 30px;
        }
        #subsidyLabelCover{
            width: 20px;
            height: 20px;
            border-radius:25px;
            position: absolute;
            left: 2px;
            top: 2px;
            background: #fff;
            transition: all 0.5s;
        }
        .success{
            width:600px;
            height:450px;
            background:#FFF;
            border:1px solid #ccc;
            position:fixed;
            left:15%;
            top:30%;
            z-index:3;
            display:none;

        }

        .success span{
            display:inline-block;
            height:40px;
            position:relative;
            left:150px;
            top:150px;
            color:#000;
            font-size:20px;
        }
        .success span img{
            margin-right:30px;
        }
        #paydetail{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:24px;
            color:#FFF;
            font-size:14px;
            border-radius:3px;
            text-align:center;
            background:#2087fc;
            position:relative;
            top:270px;
            left:-120px;
            cursor:pointer;
        }
        #paycomplete{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:24px;
            font-size:14px;
            color:#FFF;
            border-radius:3px;
            text-align:center;
            background:#2087fc;
            position:relative;
            top:270px;
            left:-60px;
            cursor:pointer;

        }
        .textVal{
            width:970px;
            height:200px;
            margin-left:50px;
            margin-top:30px;
            position:relative;
            border: 1px solid #e5e5e5;
            padding: 10px;
        }
        .textVal ul li{
            float:left;
            width:120px;
            height:40px;
            text-align:center;
            line-height:40px;
            color:#666666;
            font-size:12px;
            cursor:pointer;
            padding: 0px 10px;
        }
        .textVal ul li:first-child{
            border-right:1px solid #e5e5e5 ;
        }
        .textVal ul li.on{
            background:#FFF;
            color: #54a6de;
        }
        .textVal ul li div.on1{
            background:#FFF;
            display:block;
            border-top: 1px solid #e5e5e5;
            color: #666;
        }

        .textVal ul li div{
            width:1000px;
            height:150px;
            position:absolute;
            left:0px;
            top:60px;
        }
        .textVal ul li div dt{
            float:none;
            background:#FFF;
            width:80%;
            text-align:left;
            padding:5px 0px;
            margin-left:20px;
        }
        .textVal ul li div dl{
            margin-top:20px;
        }
        #check{
            margin-left: 20px;
        }
        #IdInput{
            display: none;
            width: 550px;
            position: fixed;
            left: 15%;
            top: 30%;
            background: #fff;
            z-index: 3;
        }
        #checkUser,#searchUser{
            margin: 15px 10px;
        }
        #IdInputConfirm{
            margin-left: 140px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        #IdComfirm{
           background: #fff;
            font-size: 22px;
        }
        #IdComfirm:hover{
            box-shadow: 0px 0px 5px 0px #ddd;
            font-weight: bold;
        }
        #memberShow{
            margin-left:10px;
            margin-top: 10px;
            min-height: 50px;
            max-height: 200px;
            overflow-x: hidden;
            overflow-y: scroll;
        }
        #memberShow abbr,#memberShow span{
            display: inline-block;
            width: 220px;
            margin: 10px;
        }
        .bigInt{
            margin-left: 20px;
            font-size: 14px;
            color: #ff3300;
        }
    </style>
</head>
<body style="background: #edf3f8">
<div class="allOutShow" style="height: auto;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>代客下单</span><abbr></abbr>
    </div>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;

$(document).ready(function(){
    $("#IdInput").draggable()
    var enterprisePId = $("#provinceId").val();
    var enterpriseCId = $("#cityId").val();
    var enterpriseDId = $("#districtId").val();
    var checklist = false
    $.get("${bath}/web/api/common/provincies", function (data) {
        if (data.response == 'success') {
            data.data.map(function (object) {
                var html = '';
                html += '<option value="' + object.provinceId + '">';
                html += object.provinceName;
                html += '</option>';
                $(".newAddressP").append(html);
            });
            if( window.localStorage ){
                var PID = localStorage['addDeliverInfo_provinceId'];
                var CID = localStorage['addDeliverInfo_cityId'];
                var DID = localStorage['addDeliverInfo_districtId'];
                if( PID == '' || PID == undefined){
                    PID = enterprisePId;
                }
                if( CID == '' || CID == undefined){
                    CID = enterpriseCId;
                }
                if( DID == '' || PID == undefined){
                    DID = enterpriseDId;
                }
            }
            else{
                var PID = enterprisePId;
                var CID = enterpriseCId;
                var DID = enterpriseDId;
            }
            $(".newAddressP option[value='" + PID + "']").prop("selected","selected");
            if(PID){
                UpdateMemAddressP(function(){
                    $(".newAddressC option[value='" + CID + "']").prop("selected","selected");
                },PID);
            }
            if(CID){
                UpdateMemAddressC(function(){
                    $(".newAddressD option[value='" + DID + "']").prop("selected","selected");
                },CID);
            }
        }
    }, 'json');

    $(".newAddressP").change(function () {
        $(".newAddressC option[value!='0'],.newAddressD option[value!='0'],.newAddressR option[value!='0']").remove();
        $.post("../web/api/common/cities", {
            "provinceId": $(this).val()
        }, function (data) {
            if (data.response == 'success') {
                data.data.map(function (object) {
                    var html = '';
                    html += '<option value="' + object.cityId + '">';
                    html += object.cityName;
                    html += '</option>';

                    $(".newAddressC").append(html);
                });
            }
        }, 'json');
    });
    $(".newAddressC").change(function () {
        $(".newAddressD option[value!='0'],.newAddressR option[value!='0']").remove()
        $.post("../web/api/common/districts", {
            "cityId": $(this).val()
        }, function (data) {
            if (data.response == 'success') {
                data.data.map(function (object) {
                    var html = '';
                    html += '<option value="' + object.districtId + '">';
                    html += object.districtName;
                    html += '</option>';
                    $(".newAddressD").append(html);
                });
            }
        }, 'json');
    });
    $(".newAddressD").change(function () {
        $(".newAddressR option[value!='0']").remove()
        $.post("../web/api/common/streets", {
            "districtId": $(this).val()
        }, function (data) {
            if (data.response == 'success') {
                data.data.map(function (object) {
                    var html = '';
                    html += '<option value="' + object.streetId + '">';
                    html += object.streetName;
                    html += '</option>';
                    $(".newAddressR").append(html);
                });
            }
        }, 'json');
    });
})
</script>
<input type="hidden" id="provinceId" value="${enterpriseInfo.provinceId}">
<input type="hidden" id="cityId" value="${enterpriseInfo.cityId}">
<input type="hidden" id="districtId" value="${enterpriseInfo.districtId}">

<#--<article class="delivery" style="height:auto;">-->
    <#--<header>配送方式</header>-->
    <#--<section class="sendWay">-->
        <#--<div class="tabs">-->
            <#--<span class="tab1 on">网点自提</span>-->
            <#--&lt;#&ndash;<span class="tab2">物流配送</span>&ndash;&gt;-->
            <#--&lt;#&ndash;<a id="addAddress" style="position:absolute;display:inline-block;right:43px;color:#0099ff;text-decoration:none;cursor:pointer;font-weight:normal;">新增收货地址</a>&ndash;&gt;-->
        <#--</div>-->
        <#--<div class="tabs_content" >-->
            <#--<header>提货网点</header>-->
            <#--<div>-->
                <#--<span id="enterpriseName"></span>&nbsp;&nbsp;&nbsp;<span  id="enterpriseAddress"></span>-->
                <#--<#if hasValet == false>-->
                    <#--<br /><span style="display:inline-block;margin-top:20px;color:red;">! 本网点对于该商品的库存不足，请及时补充</span>-->
                <#--</#if>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="tabs_content post" style="position:absolute;top:50px;display: none;height:auto;min-height:150px;z-index:1;">-->
            <#--<header style="position:relative;">收货人信息</header>-->
            <#--<div id="container">-->
                <#--<span id="defaultAddress">-->
                    <#--<input type="hidden" id="defaultAddressId" />-->
                    <#--&lt;#&ndash;<span style="display:inline-block;border:1px solid #0099ff;width:130px;height:40px;line-height:40px;font-weight:bold;" id="defaultAddressSquare">&ndash;&gt;-->
                    <#--&lt;#&ndash;<span style="margin-left:35px;" class="address_fullname" id="defaultFullName"></span>&ndash;&gt;-->
                <#--</span>-->
                <#--<span style="margin-left:30px;" class="address_fullname" id="defaultUserName">-->
                    <#--&lt;#&ndash;&ndash;&gt;-->
                <#--</span><span style="margin-left:30px;" id="address_address"></span><span style="margin-left:30px;" id="address_mobile"></span>-->
          <#--</span>-->
                <#--<div style="margin-left:23px;" id="moreAddress">更多地址<img src="${bath}/static/img/blue_down_arrow.png" style="margin-left:10px;"></div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</section>-->

<#--</article>-->
    <article class="userItem">
        <header>下单用户身份证号</header>
        <section>
             <input id="IdComfirm" value="点击选择下单用户" type="button">
            <div id="userInfo" style="display: none;">
                <img id="userImg"  width="110" height="110"/>
                <input type="hidden" id="customerId" />
                <span style="font-size:20px;" id="line_1"></span>
                <span id="line_2">&nbsp;&nbsp;&nbsp;<abbr id="line_4"></abbr> </span>
                <span id="line_3">本网点邮豆余额:<b></b>邮豆</span>
            </div>
        </section>
    </article>
<article class="list" style="padding-top:40px;">
    <header>商品清单</header>
    <section style="padding-top:15px;">
        <table style="border:1px solid #e5e5e5;width:970px;font-size:14px;">
            <tr style="color:#666666;border-bottom: 1px solid #e5e5e5; height: 40px;">
                <td style="padding-left:20px; border-style:none;">商品</td>
                <td>单价</td>
                <td>数量</td>
                <td>金额</td>
            </tr>
            <#list goodsInfos as info>
                <tr class="itemInfo">
                    <td style="padding:20px 0 20px 28px;position:relative; height:120px;">
                        <img src="${info["goodsInfoImgId"]}" style="width:90px;height:90px;vertical-align:top;"/><span style="position:absolute;top:30px;left:130px;">${info["goodsInfoName"]}</span>
                    </td>
                    <td class="itemPrice"><abbr class="addCommas">${info["goodsInfoPreferPrice"]?string("#.##")}</abbr>邮豆</td>
                    <td class="goodCount" data-gid="${info["goodsInfoId"]}">${info["count"]}</td>
                    <td class="goodSum"><abbr class="addCommas">${(info["count"] * info["goodsInfoPreferPrice"])?string("#.##")}</abbr>邮豆</td>
                </tr>
                <#if info["getAvailable"] == "">
                    <tr style="color:#ff3300;border-right: 1px solid #e5e5e5"><td align="right" colspan="4">该商品不属于本网点</td></tr>
                <#elseif info["getAvailable"] < info["count"]>
                <script type="text/javascript">
                    var checklist =true
                </script>
                    <tr style="color:#ff3300;border-right: 1px solid #e5e5e5"><td align="right" colspan="4">该商品的网点可用库存不足，可用库存为${info["getAvailable"]}件</td></tr>
                </#if>
            </#list>
            <tr style="border: 1px solid #e5e5e5;">
                <td style="padding: 10px" align="left" colspan="2"><span style="color: #ff3300" class="sumCount">0</span>件商品，订单总金额</td>
                <td style="padding: 10px" align="right" colspan="2"><span style="color: #ff3300" class="sumGoodsMoney addCommas">0</span>邮豆</td>
            </tr>
        </table>
    </section>
</article>
    <input type="hidden" value="${goodInfo.goodsInfoId}" id="goodsId"><div id="subsidyPop">
        <h1><span>网点补贴</span><span><input style="display: none;" type="checkbox" id="subsidy"> <label id="subsidyLabel" for="subsidy"><div id="subsidyLabelCover"></div></label> </span></h1>
        <ul id="subsidyShow">
            <li><i>*</i>补贴金额：<input readonly="readonly" style="width: 300px;height: 40px;border-radius: 1px;" class="" placeholder="请输入补贴金额" type="text" id="subsidyNum"><b style="margin-left: 20px;font-weight: normal">邮豆</b> <abbr style="margin-left: 20px;margin-top: 20px;width: 1000px; color: #ff3300" id="tixing2"></abbr></li>
            <li><i style="color: #fff!important;">*</i>补贴备注：<input readonly="readonly" maxlength="50" style="border-radius: 1px;height: 40px; width: 800px;" class="" placeholder="请输入补贴的原因" type="text" id="subsidyReason"></li>
            <#--<li><i>*</i>支付密码：<input style="border-radius: 1px!important;height: 24px!important;" maxlength="6" class="allinputButton" placeholder="请输入网点支付密码" type="password" id="PayKey"></li>-->
        </ul>
    </div>
    <div class="textVal">
        <h1 style="font-size: 14px!important;margin-left: 10px;padding-bottom: 15px;border-bottom:1px solid #e5e5e5;width: 930px;">用户支付金额<span style="float: right;margin-right: 20px;"><b class="sumPrice" style="font-size: 20px;font-weight:normal;color: #ff3300">${sumNum}</b>邮豆</span></h1>
        <ul>
        <#--<li class="on" value="msg">短信验证码验证-->
        <#--<div class="on1" >-->
        <#--<dl>-->
        <#--<dt>短信验证码已发送到手机号：<span>${contactPhone}</span>-->
        <#--</dt>-->
        <#--<dt><input type="text" id="textName" placeholder="短信验证码"/><input type="button" id="textGet" value="获取验证码" />-->
        <#--</dt>-->
        <#--</dl>-->
        <#--</div>-->
        <#--</li>-->
        <#--<li style="display:${judge};" class="judge on" value="${judge}">-->
            <div class="on1" >
                <div style="color: #54a6de;margin-left: 20px;margin-top: 10px">免验证</div>
                <dl style="margin-left:20px;margin-top:50px; color: #666">
                    <dt>
                    <#if enterprisefunction.getHasPermit()>
                        <i><img src="${bath}/static/img/ok.png" width="40" height="40" /></i><span id="check" style="font-size: 16px">账号已经被授权，代客下单免验证</span>
                    <#else>
                        <i><img src="${bath}/static/img/error.png" width="40" height="40" /></i><span id="check" style="font-size: 16px">该账号无代客下单权限，请联系上级修改</span>
                    </#if>
                    </dt>
                </dl>
            </div>
        <#--</li>-->
        </ul>
    </div>
<#--<article class="bill" style="width:970px;">-->
    <#--<section style="text-align:right;font-size:17px;font-weight:bold;">-->
        <#--<div><span style=""><span class="totalCount"></span>件商品，总商品金额：</span><span style="display: inline-block;width: 150px;margin-left: 20px"><b id="itemPrice"></b>邮豆</span></div>-->
        <#--<div><span style="">运费：</span><span style="display: inline-block;width: 150px;margin-left: 20px"><b id="yunfei">0</b>邮豆</span></div>-->
        <#--<div><span style="">应付金额：</span><span style="display: inline-block;width: 150px;margin-left: 20px"><b class="sumPrice"></b>邮豆</span></div>-->
    <#--</section>-->
<#--</article>-->


<article style="width:970px;height:80px;border-radius:2px;position:relative;"><!--2087fc-->
    <#if hasValet == false>
        <button class="allseachButton" id="" style="background:#dddddd!important;width:130px;position:absolute;top:20px;right:0px;border-radius:18px;color:#fff;line-height:36px!important;font-size:18px;font-weight:bold; border-style:none;">提交订单</button>
    <#elseif !enterprisefunction.getHasPermit()>
        <button class="allseachButton" id="" style="background:#dddddd!important;width:130px;height:36px!important;position:absolute;top:20px;right:0px;border-radius:18px;color:#fff;line-height:36px!important;font-size:18px;font-weight:bold; border-style:none;">提交订单</button>
    <#else>
        <#--<button class="allseachButton" id="order_submit2" style="width:130px;height:36px!important;position:absolute;top:20px;right:30px;border-radius:18px;color:#fff;line-height:36px!important;font-size:18px;font-weight:bold; border-style:none;">提交订单</button>-->
        <button class="allseachButton" id="paySubmit" style="width:130px;height:32px!important;position:absolute;top:20px;right:0px;border-radius:18px;color:#fff;line-height:32px!important;font-size:18px;font-weight:bold; border-style:none;">提交订单</button>
    </#if>
    <script type="text/javascript">
        <#if hasValet == false>
            $("#order_submit").css("disabled","disabled");
            $("#order_submit").css("background-color","#bbbbbb");
        <#else>
            $("#order_submit").css("background-color","#54a6de");
        </#if>


    </script>
</article>
<form>
        <div class="newAddress allpop">
            <h1>新增收货人信息<i id="xx"></i></h1>
        <ul>
            <li><abbr><i>*</i>收货人：</abbr><input maxlength="8" type="text" name="adName"  />
            </li>
            <li><abbr><i>*</i>所在地区：</abbr><select class="newAddressP">
                <option value="0" selected="selected">请选择省份</option>
            </select>
                <select class="newAddressC">
                    <option value="0" selected="selected">请选择城市</option>
                </select>
                <select class="newAddressD">
                    <option value="0" selected="selected">请选择区县</option>
                </select>
                <select class="newAddressR">
                    <option value="0" selected="selected">请选择街道</option>
                </select>
            </li>
            <li><abbr><i>*</i>详细地址：</abbr><input type="text" name="adAddress"  />
            </li>
            <li><abbr><i>*</i>手机号码：</abbr><input maxlength="11" type="text" name="adCell"  /><abbr>固定电话：</abbr><input maxlength="13" type="text" name="adTel"  />
            </li>
            <li><abbr>邮政编码：</abbr><input maxlength="6" type="text" name="adCell"  />
            </li>
            <li style="height: 50px;"><input id="saveAdd" type="button" value="保存收货人信息" />
            </li>
        </ul>
    </div>
</form>
    <div id="IdInput" class="allpop">
        <h1>请选择下单用户<i style=" background:url(${bath}/static/img/XX.png) center no-repeat;"></i></h1>
        <div> <input maxlength="18" class="allinputButton" placeholder="请输入身份证号" type="text" pattern="^\d{17}[\w\d]$" id="checkUser"/><input class="allinputButton" type="text" placeholder="请输入姓名" id="userName"><input type="button" class="allclickButton" id="searchUser" value="搜索"> </br><b style="margin-left: 10px;font-size: 16px; color: #ff3300;" id="tixing"></b> </div>

        <div id="memberShow">
            <ul>




            </ul>
        </div>

        <input class="allseachButton" value="确认" type="button" id="IdInputConfirm">
    </div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script src="${bath}/static/js/order_sure.js?version=${VERSION}"></script>
    <div class="success">
        <span><img src="${bath}/static/img/ok.png" width="40" height="40" />付款成功，订单已支付</span>
        <a class="allseachButton" id="paydetail">完成</a>
        <a  class="allcancelButton" id="paycomplete">查看订单</a>
    </div>
    </div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        $("#IdComfirm").click(function(){
            coverHtml()
            $("#IdInput").show()
        })
        $("#IdInput i").click(function(){
            discoverHtml()
            $("#IdInput").hide()
        })
        $("#subsidyLabel").click(function(){
            if( !$("input[id='subsidy']").is(":checked") ){
                $("#subsidyLabelCover").css("left","22px")
                $("#subsidyLabel").css("background","#54a6de");
            }else{
                $("#subsidyLabelCover").css("left","2px")
                $("#subsidyLabel").css("background","#bbbbbb")
            }
        })
        $("#subsidy").click(function(){
            $("#subsidyNum,#subsidyReason,#PayKey").val("")
            $(".sumPrice").html($(".sumGoodsMoney").html());
            $("#tixing2").html("");
            if($("#subsidy").is(":checked")){
                $("#subsidyShow li:first-child i").css("color","#ff3300")
                $("#subsidyShow li").css("color","#333")
                $("#subsidyNum").stop().prop("readonly","").css("border","1px solid #999")
                $("#subsidyReason").stop().prop("readonly","").css("border","1px solid #999")
            }else{
                $("#subsidyShow li:first-child i").css("color","#e5e5e5")
                $("#subsidyShow li").css("color","#e5e5e5")
                $("#subsidyNum").stop().prop("readonly","readonly").css("border","1px solid #e5e5e5")
                $("#subsidyReason").stop().prop("readonly","readonly").css("border","1px solid #e5e5e5")
            }
        })
        var num = 0
        var price = 0
        $(".goodCount").each(function(){
            num = parseInt(num) + parseInt($(this).html())
            $(".sumCount").html(num)
        });
        $(".goodSum .addCommas").each(function(){
            price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
            $(".sumGoodsMoney").html(addCommas(price,2))
        });
        $("#subsidyNum").blur(function(){
            var _this=this
            if( $(this).val() == "0"){
                $("#subsidyNum").attr("disabled","disabled")
                response_ensure_alert("error","补贴数量不能为0",function(){
                    $(_this).val("").focus()
                    $(_this).removeAttr("disabled");
                })
            }
        })
        $(document).on("input","#subsidyNum",function(event){
            var val = $(this).val()
            var bigInt = changeNum(val)
            var html = '<div class="bigInt">'+ bigInt +'</div>'
            if( val == ""){
                $(this).siblings(".bigInt").remove()
            }else{
                $(this).siblings(".bigInt").remove()
                $(this).next().next().after(html)
            }
            var subsidyNum=parseFloat($(this).val());
            var payNum = parseFloat($(".sumGoodsMoney").html().replace(",","").replace(",","").replace(",",""));
            var text = $(this).val().split(".")
            var algin = text[1]
            if( algin == undefined ){algin = ""}
            if( algin.length > 2 ){
                $("#subsidyNum").attr("disabled","disabled")
                response_ensure_alert("error","不能超过小数点后2位",function(){
                    $("#subsidyNum").val("").focus().removeAttr("disabled");
                    $(".sumPrice").html(addCommas(payNum,2))
                    $("#tixing2").html("")
                    $(".bigInt").remove()
                },function(){
                    $("#subsidyNum").val("").focus().removeAttr("disabled");
                    $(".sumPrice").html(addCommas(payNum,2))
                    $("#tixing2").html("")
                    $(".bigInt").remove()
                })
            }
            if($(this).val()!=""){
            if( !/^[0-9]+.?[0-9]*$/.test($(this).val()) ){
                $("#subsidyNum").attr("disabled","disabled")
                response_ensure_alert("error","请输入数字",function(){
                    $("#subsidyNum").val("").focus().removeAttr("disabled");
                    $(".sumPrice").html(addCommas(payNum,2))
                    $("#tixing2").html("")
                    $(".bigInt").remove()
                },function(){
                    $("#subsidyNum").val("").focus().removeAttr("disabled");
                    $(".sumPrice").html(addCommas(payNum,2))
                    $("#tixing2").html("")
                    $(".bigInt").remove()
                })
            }

            if(subsidyNum > payNum * 0.75){
                $("#tixing2").html("补贴金额过高，请谨慎操作")
            }else{
                $("#tixing2").html("");
            }
            if( subsidyNum > payNum ){
                $("#subsidyNum").attr("disabled","disabled")
                response_ensure_alert("error","补贴金额不能超过应付金额",function(){
                    $("#subsidyNum").val("").focus().removeAttr("disabled");
                    $(".sumPrice").html(addCommas(payNum,2))
                    $("#tixing2").html("")
                    $(".bigInt").remove()
                },function(){
                    $("#subsidyNum").val("").focus().removeAttr("disabled");
                    $(".sumPrice").html(addCommas(payNum,2))
                    $("#tixing2").html("")
                    $(".bigInt").remove()
                })
            }else{
                $(".sumPrice").html(addCommas(payNum-subsidyNum,2))
            }
            }
        })
        $(".sumPrice").html(addCommas($(".sumGoodsMoney").html().replace(",","").replace(",","").replace(",",""),2))
        console.log(  $(".sumPrice").html())
        try{
            if(checklist){
                $("#paySubmit").unbind("click")
            }
        }catch(e){

        }

    })
</script>
</html>
