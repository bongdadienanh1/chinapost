<#assign bath = request.contextPath>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>邮豆系统_登录界面</title>
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
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            body{
                transform: scale(0.8,0.8);
                transform-origin: left top;
                -webkit-transform: scale(0.8,0.8);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.8,0.8);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.8,0.8);
                -moz-transform-origin: left top;
            }
            .complete{
                top: 195px!important;
            }

        }
        body{
            background-color: #f7f7f7;
        }
        .UserGetList_content{
            border:1px solid #ccc;
                    width:1400px;
            margin-left: 22px;
            margin-top: 28px;
            background-color: white;
            position: relative;
            padding-bottom: 40px;
        }
        .goodsInfo{
            margin-left: 52px;
            margin-top: 55px;
            font-size: 16px;
            position: relative;
        }
        .goods_type{
            position: absolute;
            top: -5px;
            right:200px;
        }
        .goods_error{
            position: absolute;
            top:15px;
            right:120px;
            color:#ff3300;
        }
        .goods_error img{
            margin-top: -4px;
        }
        #ensure{
            background-color: #54a6de;
            font-size: 16px;
            border: none;
            color:white;
            border-radius: 3px;
            font-weight: bold;
            width:120px;
            height:35px;
            margin-top: 20px;
        }
        #ensure:hover{

        }
        #ensure:active{
            background: #009966;
        }
        .success{
            width:700px;
            height:450px;
            background:#FFF;
            border:1px solid #ccc;
                        position:fixed;
            left:15%;
            top:30%;
            z-index:2;
            display:none;
        }
        .success span{
            display:inline-block;
            height:40px;
            position:relative;
            left:200px;
            top:150px;
            color:#000;
            font-size:20px;
        }
        .success span img{
            margin-right:30px;
        }
        .warning{
            width:700px;
            height:300px;
            background:#FFF;
            border:1px solid #ccc;
                        position:fixed;
            left:15%;
            top:30%;
            z-index:2;
        }
        .warning div{
            position: absolute;
            top:150px;
            left:110px;
            display: inline-block;
            width:450px;
            font-size: 18px;
            text-align: center;
        }
        .warning img{
            position: absolute;
            top:148px;
            left:72px;
        }
        .warning span{
            display:inline-block;
            height:40px;
            position:relative;
            left:300px;
            top:80px;
            color:#000;
            font-size:20px;
        }
        .detail{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:35px;
            color:#FFF;
            font-size:14px;
            border-radius:3px;
            text-align:center;
            background:#bbbbbb;
            position:relative;
            top:300px;
        }
        .detail:hover{

        }
        .detail:active{
            background: #009966;
        }
        .complete:hover{

        }
        .complete:active{
            background: #009966;
        }
        .complete{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:35px;
            font-size:14px;
            color:#FFF;
            border-radius:3px;
            text-align:center;
            background:#54a6de;
            position:relative;
            top:300px;
            left:50px;

        }
        .xcConfirm .popBox .ttBox{
            height:60px!important;
        }
    </style>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="height: 800px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>网点提货</span><abbr></abbr>
    </div>
    <input type="hidden" value="0" />
<div class="UserGetList_content">
    <div class="goodsInfo">
        <div>
            <input type="hidden" value="${order.getDeliveryCode()}" id="dCode" />
            <div>订单号:<abbr id="orderNumber">${order.orderCode}</abbr></div>
            <div style="margin-top: 15px;">提货网点：<span>${order.getPickInfo().getEnterpriseName()}</span><span style="margin-left: 25px;">${order.getPickInfo().getPickAddress()}</span></div>
        </div>
        <div class="goods_type"></div>
        <#if flag>
            <#else>
                <div class="goods_error">提货点非本网点，暂不支持跨网点提货</div>
        </#if>

    </div>
    <hr width="85.2%" style="margin-left: 50px;"/>
    <article class="userItem">
        <section>
            <div>
                <img src="${imgUrl}" style="width:80px;height:80px;"/>
                <span style="font-size:20px;padding-left: 100px;">${customer.idcardNo}</span>
                <span style="padding-left: 100px;">${customer.fullname}&nbsp;&nbsp;&nbsp;${customer.contactPhone}</span>
            </div>
        </section>
    </article>
    <hr width="85.2%" style="margin-left: 50px;margin-top:-20px;"/>
    <article class="list" style="margin-top: -10px;">
        <section style="padding-top:15px;">
            <table style="border:1px solid #ccc;width:970px;font-size:14px;">
                <tr style="background-color:#ccc;color:white;">
                    <td style="padding-left:20px;">商品</td>
                    <td>单价</td>
                    <td>数量</td>
                </tr>
                <#assign goodsCount = 0>
                <#list order.orderGoodsList as orderGood>
                    <tr style="height:120px;">
                        <td style="padding:20px 0 20px 28px;position:relative;">
                            <img src="${orderGood.goodsImg}" style="width:100px;height:80px;background-color: red;"/>
                            <span style="position:absolute;top:30px;left:130px;">${orderGood.goodsInfoName}</span>
                        </td>
                        <td>${orderGood.goodsInfoPrice?string("#.##")}邮豆</td>
                        <td>${orderGood.goodsInfoNum}</td>
                    </tr>
                    <#assign goodsCount = goodsCount + orderGood.goodsInfoNum>
                </#list>

            </table>
        </section>
    </article>
    <article class="bill" style="width:970px;">
        <section style="text-align:right;font-size:17px;font-weight:bold;">
            <div><span style="">${goodsCount}件商品，总商品金额：</span><span style="margin-left:70px;">${order.orderOldPrice?string("#.##")}邮豆</span></div>
            <div><span style="">订单金额：</span><span style="margin-left:70px;">${order.orderPrice?string("#.##")}邮豆</span></div>
            <#if flag><input type="button" value="确定提货" id="ensure"></#if>

        </section>
    </article>
</div>
<div class="success">
    <span><img src="${bath}/static/img/ok.png" width="40" height="40" />自提单提货完成</span>
    <a class="detail">查看订单详情</a>
    <a class="complete">完成</a>
</div>
<#if flag>

<#else>
<div class="warning">
    <span>系统提示</span>
    <img src="${bath}/static/img/notok.png"/>
    <div>该自提单提货点非本网点，暂不支持跨网点提货，用户可前往提货订单中的提货网点提货</div>
    <a class="detail" style="top: 230px;left: 140px;">查看订单详情</a>
    <a class="complete" style="top: 230px;left: 180px;">确定</a>
</div>
</#if>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
<script src="${bath}/static/js/UserGet.js?version=${VERSION}"></script>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    $(".complete").click(function(){
        discoverHtml();
        window.location.href="UserGet";

    })
    $(document).ready(function(){
        var isEnd = sessionStorage.getItem("isEnd")
        if(isEnd == "1"){
            var intervalTime = 0;
            var intervalFlag = true;
            console.log(11111)
            setInterval(function(){
                if( intervalTime == 300000){
                    intervalTime = 0;
                    sessionStorage.setItem("ShoppingCart",'');
                    window.location.href = 'itemList'
                }
                else{
                    if( intervalFlag == false ){
                        intervalTime = 0;
                        intervalFlag = true;
                    }
                }
                intervalTime = intervalTime + 1000;
            },1000);
        }
    })
</script>
</html>
