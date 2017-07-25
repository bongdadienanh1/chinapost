<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css" />
    <link rel="stylesheet" href="${bath}/static/css/jPages.css" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>无标题文档</title>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            .accountTable,.detailThree,.detailFour{
                transform: scale(0.7,0.7)!important;
                transform-origin: left top!important;
                -webkit-transform: scale(0.7,0.7)!important;
                -webkit-transform-origin: left top!important;
                -ms-transform: scale(0.7,0.7)!important;
                -ms-transform-origin: left top!important;
                -moz-transform: scale(0.7,0.7)!important;
                -moz-transform-origin: left top!important;
            }
            /*.CustomerReturn{*/
                /*transform: scale(0.8,0.8)!important;*/
                /*transform-origin: left top!important;*/
                /*-webkit-transform: scale(0.8,0.8)!important;*/
                /*-webkit-transform-origin: left top!important;*/
                /*-ms-transform: scale(0.8,0.8)!important;*/
                /*-ms-transform-origin: left top!important;*/
                /*-moz-transform: scale(0.8,0.8)!important;*/
                /*-moz-transform-origin: left top!important;*/
                /*font-size: 16px!important;*/
            /*}*/
            .accountTable section{
                margin-top: 0px!important;
            }
            input[name='accountTel'], input[name='accountCust'], input[name='accountUserName']{
                width: 170px!important;
            }
            .allcpageTurnButton a,.allcpageTurnButton span{
                font-size: 24px!important;
                color: #999;
            }
            /*.accountSearch{*/
                /*transform: scale(0.9,0.9);*/
                /*transform-origin: left top;*/
                /*-webkit-transform: scale(0.9,0.9);*/
                /*-webkit-transform-origin: left top;*/
                /*-ms-transform: scale(0.9,0.9);*/
                /*-ms-transform-origin: left top;*/
                /*-moz-transform: scale(0.9,0.9);*/
                /*-moz-transform-origin: left top;*/
                /*height: 80px!important;*/
            /*}*/
        }
        .accountTable{
            transform: scale(0.8,0.8);
            transform-origin: left top;
            -webkit-transform: scale(0.8,0.8);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.8,0.8);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.8,0.8);
            -moz-transform-origin: left top;
        }
        .sum{
            width:100%;
            height:50px;
            line-height:50px;
            background:#54a6de;
            margin-left:20px;
            margin-top:20px;
            padding-left:30px;
            color: #fff;
        }
        .accountSearch{
            width:1400px;
            height:100px;
            margin-left:20px;
            margin-top:20px;
            font-size: 12px;
        }
        .accountSearch li{
            width:210px;

            height:32px;
            float:left;
            margin:10px 0px;
        }
        .accountSearch li input{
            width:150px;
        }
        .accountSearch li select{
            width:80px;
            background:#FFF;
            margin-left: 5px;
            margin-top: -3px;
            padding-left: 10px;
            border: none;
        }

        #accSearch{
            width:120px;
        }

        #accExport{
            width:120px;
            margin-left:30px;
        }

        #accChooseExport{
            width:120px;
            margin-left:30px;
        }

        .accountTable{
            width:1600px;
            height:auto;
            margin-left:20px;
            margin-top: 30px;
        }
        .accountTable ul{
            width:1000px;
            height:30px;
        }
        .accountTable ul li{
            float:left;
            width:80px;
            height:28px;
            color: #999;
            line-height:30px;
            border-bottom:2px solid transparent;
            text-align:center;
            margin-right:-1px;
            cursor:pointer;
        }
        .accountTable ul li.accOn{
            border-bottom:2px solid #54a6de;
            color: #54a6de;
        }
        .account_Table{

            width:1600px;
        }
        .account_Table th{
            font-weight: normal!important;
        }
        .account_Table abbr{
            display:inline-block;
            width:60px;
            height:20px;
            line-height:20px;
            text-align:center;
            background:#ff3300;
            color:#FFF;
            border-radius:5px;
            margin-top:30px;
            border:1px solid transparent;

        }
        .account_Table a,i{
            color:#54a6de;
            padding:0px 5px;
            cursor:pointer;

        }


        /*网点自提代客*/
        .detailFour{
            width:800px;
            height:auto;
            position:fixed;
            left:15%;
            top:5%;
            border:1px solid #ccc;
            border-radius:5px;
            background:#FFF;
            z-index:2;
            display:none;
        }
        .detailImg li{
            float:left;
            width:200px;
            height:150px;
            text-align:center;
            line-height:60px;
            margin-right:-1px
        }
        .detailFour ul.checkDetailFour{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailFour dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#333;
        }
        .checkDetailFour dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }
        .Purchase_table{
            margin-bottom:50px;
            border: 1px solid #e5e5e5;
        }
        .Purchase_table th{
            border-bottom: 1px solid #e5e5e5;
            background: #edf3f8;
        }
        #xxFour{
            display:inline-block;
            width:16px;
            height:16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            position:relative;
            cursor:pointerl;
        }

        /*网点自提用户自主下单*/


        .CustomerReturn{
            width: 880px;
            height: auto;
            overflow: hidden;
            background: #fff;
            position: absolute;
            left: 15%;
            top: 5%;
            z-index: 2;
            display: none;
        }
        .CustomerReturn ul{
            margin-left: 20px;
        }
        .CustomerReturn i{
            color: #ff3300;
        }
        input[name='reason']{
            width: 500px;
        }

        .CustomerReturn h2{
            color: #000;
            display: inline-block;
            margin-left: 20px;
            margin-right: 30px;
        }
        .CustomerReturn h1 i{
            display: inline-block;
            vertical-align: middle;
            width: 16px;
            height: 16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        .itemNum{
            width: 50px;
            height: 30px;
            border:none;
            margin: 0px 10px;
            background: #fff;
            text-align: center;
            margin-top: 40px;
        }
        .itemNumReduce,.itemNumAdd{
            width: 40px;
            height: 30px;
            border: 1px solid #cccccc;
            background: #fff;
            margin-top: 40px;
        }
        .CustomerReturn li{
            margin-top: 20px;
        }
        .CustomerReturn li span{
            display: inline-block;
            vertical-align: middle;
            overflow: hidden;
        }
        .CustomerReturn ul.returnlist{
            font-size: 14px;
            margin-left: 100px;
        }
        .CustomerReturn ul.returnlist select{
            width: 200px;
        }
        .CustomerReturn ul.returnlist input[type='radio']{
            margin-right: 20px;
        }
        .CustomerReturn ul.returnlist span{
            width: 120px;
        }
        .CustomerReturn ul.returnlist abbr{
            display: inline-block;
            vertical-align: middle;
            width: 300px;
        }
        .CustomerReturn li dl{
            width: 749px;
            margin-left: 20px;
            margin-top: 20px;
            border:1px solid #dedede;
        }
        .CustomerReturn li dl dt{
            width: 749px;
            height: 35px;
            border-bottom: 1px solid #e5e5e5;
            background: #edf3f8;
            white-space: nowrap;
        }
        .CustomerReturn li dl dt abbr{
            display: inline-block;
            vertical-align: middle;
            height: 35px;
            line-height: 35px;
            border-right: 1px solid #dedede;
            margin-right: -1px;
            text-align: center;
        }
        .CustomerReturn li dl dt abbr:first-child{
            width: 30px;
        }
        .CustomerReturn li dl dt abbr:nth-child(2){
            width: 220px;
        }
        .CustomerReturn li dl dt abbr:nth-child(3){
            width: 160px;
        }
        .CustomerReturn li dl dt abbr:nth-child(4){
            width: 58px;
        }
        .CustomerReturn li dl dt abbr:nth-child(5){
            width: 80px;
        }
        .CustomerReturn li dl dt abbr:nth-child(6){
            width: 80px;
        }
        .CustomerReturn li dl dd{
            width: 749px;
            height: 106px;
            white-space: nowrap;
            overflow: hidden;
        }
        .CustomerReturn li dl dd abbr{
            display: inline-block;
            vertical-align: middle;
            height: 106px;
            line-height: 106px;
            border-right: 1px solid #dedede;
            margin-right: -1px;
            text-align: center;
            overflow: hidden;
        }
        .CustomerReturn li dl dd abbr:first-child{
            width: 30px;
        }
        .CustomerReturn li dl dd abbr:first-child input{
            margin: 50px 0px 0px 10px;
        }
        .CustomerReturn li dl dd abbr:nth-child(2){
            width: 220px;
        }
        .CustomerReturn li dl dd abbr:nth-child(2) span{
            font-size: 12px;
            margin-top: 35px;
            margin-left: 10px;
            display: inline-block;
            vertical-align: middle;
            width: 120px;
            height: 80px;
            line-height: normal;
        }
        .CustomerReturn li dl dd abbr:nth-child(3){
            width: 160px;
        }
        .CustomerReturn li dl dd abbr:nth-child(4){
            width: 58px;
        }
        .CustomerReturn li dl dd abbr:nth-child(5){
            width: 80px;
        }
        .CustomerReturn li dl dd abbr:nth-child(6){
            width: 80px;
        }
        #CustomerReturnCancel{
            margin-left: 180px;
            margin-top: 20px;
            margin-bottom: 30px;
        }
        #CustomerReturnConfrim{
            margin-left: 100px;
            margin-top: 20px;
            margin-bottom: 30px;
        }

        /*网点自提用户自主下单*/
        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            height:24px;
            background-color: #f2f2f2;
            margin-left: -45px;
            background: url("${bath}/static/img/date_img.png") no-repeat center;

        }

        /*日历控件样式*/
        .xdsoft_datetimepicker{
            width:310px!important;
        }
        td section{
            margin-left: 20px!important;
        }
        td img{
            margin-top: 20px;
        }
        .detailCheck,.custReturn{
            color: #54a6de;
        }
        #holder_All_previous,#holder_second_next,#holder_first_next,#holder_third_next,#holder_forth_next,#holder_fifth_next,#holder_sixth_previous{
            width:80px!important;
        }

        #holder_All_next,#holder_second_previous,#holder_first_previous,#holder_third_previous,#holder_forth_previous,#holder_fifth_previous,#holder_sixth_next{
            width:80px!important;
        }
        .detailThree{
            width:800px;
            height:auto;
            position:fixed;
            left:15%;
            top:5%;
            border-radius:5px;
            background:#FFF;
            z-index:2;
            display:none;
        }
        .detailImg li{
            float:left;
            width:200px;
            height:150px;
            text-align:center;
            line-height:60px;
            margin-right:-1px
        }
        .detailThree ul.checkDetailThree{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailThree dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#333;
        }
        .checkDetailThree dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }

        #xxThree{
            display:inline-block;
            width:16px;
            height:16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }
        .holder{
            margin-top: 20px;
        }
        #userPay, #subPay,#detailShow,#payDetail{
            display: inline-block;
        }
        #userPay span, #subPay span{
            width: auto!important;
        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var isEnd = ( ${isEnd} == 1 ) ? true : false;
        var isTop = false;

        var eid = ${enterpriseInfo.enterpriseId};
        var ename = "${enterpriseInfo.enterpriseName}";
        sessionStorage.setItem("ShoppingCart",'');
        $(document).ready(function(e) {
            $( ".CustomerReturn" ).draggable();
            $( ".detailThree" ).draggable();
            $( ".detailFour" ).draggable();

            //仓库列表
            inventoryList(eid,ename);

            $(document).on("click",".itemNumAdd",function(){
                var num=parseInt($(this).siblings(".itemNum").val());
                var accountNum=parseInt($(this).parent().siblings(".returnCount").html());
                if(num>=accountNum){
                    response_ensure_alert("error","数量不能大于货品数量",function(){
                    })
                }else{
                    $(this).siblings(".itemNum").val(num+1);
                }
                var returnPrice="0";
                $(".eachPrice").each(function(){
                    returnPrice = parseInt(returnPrice) + parseInt($(this).html().replace(",","").replace(",","").replace(",","")*$(this).parent().siblings().children(".itemNum").val());
                });
                $("#returnPrice").html(addCommas(returnPrice,2));
            });
            $(document).on("click",".itemNumReduce",function(){
                var num=parseInt($(this).siblings(".itemNum").val());
                if(num>0){
                    $(this).siblings(".itemNum").val(num-1);
                }
                var eachNum="0"
                $(".itemNum").each(function(){
                    eachNum = parseInt(eachNum) + parseInt($(this).val())
                });
                if(eachNum ==0){
                    data_type_alert("至少选择一件物品","error")
                    $(this).siblings(".itemNum").val(1);
                }
                var returnPrice="0";
                $(".eachPrice").each(function(){
                    returnPrice = parseInt(returnPrice) + parseInt($(this).html().replace(",","").replace(",","").replace(",","")*$(this).parent().siblings().children(".itemNum").val());
                });
                $("#returnPrice").html(addCommas(returnPrice,2));
            });
            $("#accReturn").click(function(){
                window.location.href = "accountReturn";
            })


            $(".accountTable li").click(function(){
                $(this).addClass("accOn").siblings("li").removeClass("accOn");
            })
            $("#xxThree").click(function(){
                discoverHtml();
                $(".detailThree").fadeOut(500);
            })

            $("#xxFour").click(function(){
                discoverHtml();
                $(".detailFour").fadeOut(500);
            })
            $(document).on("click",".detailCheck",function(){
                coverHtml();
                var returnNum=$(this).parent().siblings(".returnNum").html()
                var returnMoneyNum=$(this).parent().parent().siblings().children().children(".returnMoneyNum").html()
                var returnCount=$(this).parent().parent().siblings().children().children(".returnCount").html()
                var returnAccTime=$(this).parent().parent().siblings().children(".returnAccTime").html()
                var check=$(this).parent().parent().siblings().children("abbr").html();
                $("#returnNum2").html(returnNum);
                $("#returnMoneyNum2").html(returnMoneyNum);
                $("#returnCount2").html(returnCount);
                $("#returnAccTime2").html(returnAccTime);
                $("#check2").html(check);
                console.log(check);
                if(check=="待付款"){
                    $(".Img1").css({"background":"#54a6de"})
                    $(".Img11").css({"color":"#54a6de"})
                    $(".Img2,.Img3,.Img4").css({"background":"#e6e6e6"})
                    $(".Img22,.Img33,.Img44").css({"color":"#e6e6e6"})
                }else if(check=="已付款"){
                    $(".Img1,.Img2").css({"background":"#54a6de"})
                    $(".Img11,.Img22").css({"color":"#54a6de"})
                    $(".Img3,.Img4").css({"background":"#e6e6e6"})
                    $(".Img33,.Img44").css({"color":"#e6e6e6"})
                }else if(check=="已发货"){
                    $(".Img1,.Img2,.Img3").css({"background":"#54a6de"})
                    $(".Img11,.Img22,.Img33").css({"color":"#54a6de"})
                    $(".Img4").css({"background":"#e6e6e6"})
                    $(".Img44").css({"color":"#e6e6e6"})
                }else if(check=="已完成"){
                    $(".Img1,.Img2,.Img3,.Img4").css({"background":"#54a6de"})
                    $(".Img11,.Img22,.Img33,.Img44").css({"color":"#54a6de"})
                }
                var checkstate=$(this).parent().parent().siblings().children(".checkstate").html()
                if(checkstate=="物流单详情网点代客下单"){
                    $(".detailFour").fadeIn(500);
                }
            })

        });


    </script>
</head>


<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>订单管理</span><abbr></abbr>
    </div>

<#--<div class="sum">-->
    <#--订单总金额：<span>13000</span>邮豆，其中现金：<span>3000</span>元，邮豆：<span>7000</span>邮豆-->
<#--</div>-->

<div class="accountSearch">
    <ul>
        <#if isEnd =="0">
        <li style="width: 300px"><input class="allinputButton" placeholder="查看范围" value="${enterpriseInfo.enterpriseName}" style="width: 250px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterpriseInfo.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"   style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
        <#else >
            <input value="${enterpriseInfo.enterpriseId}" type="hidden" class="enterpriseIdChoosen">
        </#if>
            <li style="width: 200px"><input style="width: 180px" class="allinputButton" placeholder="订单号"  type="text" name="accountNum" /></li>
            <li style="width: 180px"><input class="allinputButton" placeholder="用户名"   type="text" name="accountUserName" /></li>
            <li style="width: 180px"><input class="allinputButton" placeholder="联系电话"  type="text" name="accountTel" /></li>
            <li style="height: 30px" class="allinputButton"><span>本网点提货</span>
                <select id="selfPick">
                    <option value="0" selected="selected">全部</option>
                    <option value="1">是</option>
                    <option value="2">否</option>
                </select>
            </li>
            <li style="width: 280px"></li>
            <#if isEnd =="1">
                <li style="width: 280px"></li>
            </#if>
            <li><input class="allinputButton" placeholder="下单开始时间"   type="text" style="width:200px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="下单结束时间"   type="text" style="width:200px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="付款开始时间"   type="text" style="width:200px;" id="pay_datetimepicker_start"/><a  id="pay_date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="付款结束时间"   type="text" style="width:200px;" id="pay_datetimepicker_end"/><a  id="pay_date_end" href="#"class="date_button"/></a></li>
            <li style="width:500px;margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input class="allclickButton" type="button" id="accExport" value="导出所有" /><input class="allclickButton" type="button" id="accChooseExport" value="导出选中的" /></li>
    </ul>
</div>

<div class="accountTable">
    <ul>
        <li class="accOn" style="width:auto;">全部订单</li>
        <li>待付款</li>
        <li>待发货</li>
        <li>待收货</li>
        <li>待提货</li>
        <li>已完成</li>
        <li>已取消</li>
        <li id="accReturn" style="margin-left:80px; width: 150px;">全部退单</li>
    </ul>
    <table role="none" class="account_Table" border="0" cellspacing="0" cellpadding="0">
        <thead style="display:inline-block; width:1604px; height:40px; color:#555555;border-top:1px solid #e5e5e5;" >
        <tr><th style="display:inline-block; width:0px; height:40px; overflow:hidden;">订单号</th><th style="display:inline-block; width:400px; height:40px; line-height:40px; border-style:none;">商品</th><th style="display:inline-block; width:80px; height:40px; line-height:40px; border-style:none;">总价/数量</th><th style="display:inline-block; width:320px; height:40px; line-height:40px; border-style:none;">买家信息</th><th style="display:inline-block; width:230px; height:40px; line-height:40px; text-align:left; border-style:none;">下单时间</th><th style="display:inline-block; width:200px; height:40px; line-height:40px; text-align:left; border-style:none;">订单状态</th><th style="display:inline-block; width:170px; height:40px; line-height:40px; text-align:left; border-style:none;">订单金额</th><th style="display:inline-block; width:150px; height:40px; line-height:40px; text-align:left; border-style:none;">商家</th>
        </tr>
        </thead>
        <tbody >

        </tbody>
    </table>
    <#--全部订单-->
        <div class="jpages_Table">
            <div id="itemContainer_All" class="itemContainer"></div>
            <div id="holder_All" class="holder allcpageTurnButton"></div>
        </div>

    <#--待付款-->
        <div class="jpages_Table">
            <div id="itemContainer_first" class="itemContainer"></div>
            <div id="holder_first" class="holder allcpageTurnButton"></div>
        </div>

    <#--待发货-->
        <div class="jpages_Table">
            <div id="itemContainer_second" class="itemContainer"></div>
            <div id="holder_second" class="holder allcpageTurnButton"></div>
        </div>

    <#--待收货-->
        <div class="jpages_Table">
            <div id="itemContainer_third" class="itemContainer"></div>
            <div id="holder_third" class="holder allcpageTurnButton"></div>
        </div>

    <#--待提货-->
        <div class="jpages_Table">
            <div id="itemContainer_forth" class="itemContainer"></div>
            <div id="holder_forth"class="holder allcpageTurnButton"></div>
        </div>

    <#--已完成-->
        <div class="jpages_Table">
            <div id="itemContainer_fifth" class="itemContainer"></div>
            <div id="holder_fifth" class="holder allcpageTurnButton"></div>
        </div>

    <#--已取消-->
        <div class="jpages_Table">
            <div id="itemContainer_sixth" class="itemContainer"></div>
            <div id="holder_sixth" class="holder allcpageTurnButton"></div>
        </div>
</div>



<div class="detailFour allpop">
    <h1>订单号:<span class="accountNUM"></span><i id="xxFour"></i></h1>
    <ul>
        <li style="width:800px; height:150px;">
            <ul class="detailImg" style="width:800px; height:150px;">
                <li class="Img11" style="text-align:center; position:relative; color:#e6e6e6;">已下单<br /><abbr class="Img1" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:92px; font-size:9px">1</abbr><abbr class="Img1" style="display:inline-block; width:90px; height:5px; background:#e6e6e6; position:absolute; left:109px; top:68px;"></abbr><span class="orderTime" style="display:inline-block; margin-top:10px; color:#999;"></span></li>
                <li class="Img22" style="text-align:center; position:relative; color:#e6e6e6;">已付款<br /><abbr class="Img2" style="display:inline-block; width:92px; height:5px; background:#e6e6e6; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img2" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:92px; font-size:9px">2</abbr><abbr class="Img2" style="display:inline-block; width:90px; height:5px; background:#e6e6e6; position:absolute; left:109px; top:68px;"></abbr><span style="display:inline-block; margin-top:10px; color:#999;"class="PayedTime"></span></li>
                <li class="Img44" style="text-align:center; position:relative; color:#e6e6e6;">已完成<br /><abbr class="Img4" style="display:inline-block; width:92px; height:5px; background:#e6e6e6; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img4" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:90px; font-size:9px">4</abbr><span style="display:inline-block; margin-top:10px; color:#999;" class="finishTime"></span></li>
                <li class="Img55" style="text-align:center; position:relative; color:#e6e6e6;">已取消<br /><abbr class="Img5" style="display:inline-block; width:92px; height:5px; background:#e6e6e6; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img5" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:90px; font-size:9px">2</abbr><span class="cancelTime"  style="display:inline-block; margin-top:10px; color:#e6e6e6;"></span></li>
            </ul>
        </li>
        <li>
            <ul class="checkDetailFour">
                <li>
                    <dl>
                        <dd class="dt">订单概况</dd>
                        <dd>订单编号：<span class="orderCode" id="returnNum2">2015032209010048</span></dd>
                        <dd>下单时间：<span class="createTime" id="returnAccTime2">2016-03-22 09:01:00</span></dd>
                        <dd>订单状态：<span style="color: #ff3300;" class="orderStatusDetail" id="check2">未付款</span></dd>
                        <dd>使用优惠券：<span></span></dd>
                        <dd>订单原始金额：<span class="oldPrice">48.00</span>邮豆</dd>
                        <dd>订单优惠金额：<span class="accountPrice"></span></dd>
                        <dd>订单交易金额：<span class="orderPrice">39.00</span>邮豆</dd>
                        <dd>邮豆支付金额：<span class="ucoinPrice">10.00</span>邮豆</dd>
                        <dd>网点补贴金额：<span class="subsidyPrice"></span>邮豆</dd>
                        <dd>用户支付金额：<span class="UserPayPrice"></span>邮豆</dd>
                        <dd>金钱支付金额：<span class="moneyPayTime">9.00</span>元</dd>
                        <dd>邮豆支付时间：<span class="ucoinPayTime"></span></dd>
                        <dd>支付完成时间：<span class="finishTime"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt deliveryInfo">快递信息</dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">物流信息</dd>
                        <dd>配送方式：<span class="deliveryType"></span></dd>
                        <dd>运费：<span class="deliveryPrice">0.00</span>邮豆</dd>
                        <dd>收货地址：<span class="deliveryAddress"></span></dd>
                        <dd>详细地址：<span class="deliveryDetailAddress"></span></dd>
                        <dd>收货人：<span class="personGet"></span></dd>
                        <dd>联系电话：<span class="contactPhone"></span></dd>
                        <dd>手机：<span class="cellPhone"></span></dd>
                        <dd>邮编：<span class="postCode"></span></dd>
                        <dd>客户留言：<span class="customerRemark"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">下单类型</dd>
                        <dd>下单类型：<span class="billType"></span></dd>
                        <dd>操作者：<span class="operator"></span></dd>
                        <dd>扣款验证方式：<span class="payWay"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">发票信息</dd>
                        <dd>发票类型：<span>不需要发票</span></dd>
                        <dd>支付类型：<span>在线支付</span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">卖家备注</dd>
                        <dd style="width:740px;">卖家备注：<span></span></dd>
                    </dl>

                </li>

            </ul>
        </li>
        <li>
            <table class="Purchase_table" style="text-align:center;width:760px; margin-left:20px; margin-top:20px;" cellspacing="0" cellpadding="0" align="center" border="0">
                <thead style="height:40px; background:#a4aeb9;">
                <tr>
                    <th>商品名称</th>
                    <th>原始价格</th>
                    <th>数量</th>
                    <th style="width: 200px;">商品规格</th>
                    <th>交易价格</th>
                    <th>商品总价</th>
                </tr>
                </thead>
                <tbody>
                <tr bgcolor="#FFFFFF">
                    <td><img src="${bath}/static/img/look.png" width="50" height="50" /><span>道具两件套</span></td>
                    <td><span>48.00</span>邮豆</td>
                    <td class="returnCount">1</td>
                    <td>个数：两件套</td>
                    <td><span class="returnMoney">48.00</span>邮豆</td>
                    <td><span class="returnMoneyNum2">48.00</span>邮豆</td>

                </tr>
                </tbody>
            </table>

        </li>

    </ul>
</div>

<div class="detailThree allpop">
    <h1>订单号:<span class="accountNUM"></span><i id="xxThree"></i></h1>
    <ul>
        <li style="width:800px; height:150px;">
            <ul class="detailImg" style="width:800px; height:150px;">
                <li class="Img11" style="text-align:center; position:relative; color:#e6e6e6;">已下单<br /><abbr class="Img1" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:92px; font-size:9px">1</abbr><abbr class="Img1" style="display:inline-block; width:90px; height:5px; background:#e6e6e6; position:absolute; left:109px; top:68px;"></abbr><span class="orderTime" style="display:inline-block; margin-top:10px; color:#e6e6e6;"></span></li>
                <li class="Img22" style="text-align:center; position:relative; color:#e6e6e6;">已付款<br /><abbr class="Img2" style="display:inline-block; width:92px; height:5px; background:#e6e6e6; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img2" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:92px; font-size:9px">2</abbr><abbr class="Img2" style="display:inline-block; width:90px; height:5px; background:#e6e6e6; position:absolute; left:109px; top:68px;"></abbr><span class="PayedTime" style="display:inline-block; margin-top:10px; color:#e6e6e6;"></span></li>
                <li class="Img44" style="text-align:center; position:relative; color:#e6e6e6;">已完成<br /><abbr class="Img4" style="display:inline-block; width:92px; height:5px; background:#e6e6e6; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img4" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:90px; font-size:9px">4</abbr><span class="finishTime"  style="display:inline-block; margin-top:10px; color:#e6e6e6;"></span></li>
                <li class="Img55" style="text-align:center; position:relative; color:#e6e6e6;">已取消<br /><abbr class="Img5" style="display:inline-block; width:92px; height:5px; background:#e6e6e6; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img5" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#e6e6e6; border-radius:10px; position:absolute; left:90px; font-size:9px">2</abbr><span class="cancelTime"  style="display:inline-block; margin-top:10px; color:#e6e6e6;"></span></li>
            </ul>
        </li>
        <li>
            <ul class="checkDetailThree">
                <li>
                    <dl>
                        <dd class="dt">订单概况</dd>
                        <dd>订单编号：<span class="orderCode"></span></dd>
                        <dd>下单时间：<span class="createTime"></span></dd>
                        <dd>订单状态：<span class="orderStatusDetail"></span></dd>
                        <dd>使用优惠券：<span></span></dd>
                        <dd>订单原始金额：<span class="oldPrice"></span>邮豆</dd>
                        <dd>订单优惠金额：<span class="accountPrice"></span></dd>
                        <dd>订单交易金额：<span class="orderPrice"></span>邮豆</dd>
                        <dd>邮豆支付金额：<span class="ucoinPrice"></span>邮豆</dd>
                        <dd>网点补贴金额：<span class="subsidyPrice"></span>邮豆</dd>
                        <dd>用户支付金额：<span class="UserPayPrice"></span>邮豆</dd>
                        <dd>金钱支付金额：<span class="moneyPayTime"></span>元</dd>
                        <dd>邮豆支付时间：<span class="ucoinPayTime"></span></dd>
                        <dd>支付完成时间：<span class="finishTime"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">配送方式</dd>
                        <dd>配送方式：<span class="deliveryType"></span></dd>
                        <dd>提货网点：<span class="enterpriseAddress"></span></dd>
                        <dd>提货人：<span class="pickName2"></span></dd>
                        <dd>提货人电话：<span class="pickPhone2"></span></dd>
                        <dd>客户留言：<span class="customerRemark"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">下单类型</dd>
                        <dd>下单类型：<span class="billType"></span></dd>
                        <dd>操作者：<span class="operator"></span></dd>
                        <dd>扣款验证方式：<span class="payWay"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">卖家备注</dd>
                        <dd style="width:740px;">卖家备注：<span></span></dd>
                    </dl>

                </li>

            </ul>
        </li>
        <li>
            <table class="Purchase_table" style="text-align:center;width:760px; margin-left:20px; margin-top:20px;" cellspacing="0" cellpadding="0" align="center" border="0">
                <thead style="height:40px; background:#a4aeb9;">
                <tr>
                    <th>商品名称</th>
                    <th>原始价格</th>
                    <th>数量</th>
                    <th style="width: 200px;">商品规格</th>
                    <th>交易价格</th>
                    <th>商品总价</th>
                </tr>
                </thead>
                <tbody>
                <tr bgcolor="#FFFFFF">
                    <td><img src="${bath}/static/img/look.png" width="50" height="50" /><span>道具两件套</span></td>
                    <td><span>48.00</span>邮豆</td>
                    <td></td>
                    <td>个数：两件套</td>
                    <td><span></span>邮豆</td>
                    <td><span></span>邮豆</td>

                </tr>
                </tbody>
            </table>

        </li>

    </ul>
</div>

<div class="CustomerReturn allpop" style="display: none;">
    <h1>代客退货<i id="XX1"></i></h1>
    <ul>
        <li><h2>退货说明</h2></li>
        <li><h2>订单编号:</h2><span id="returnNum">12313213132123123</span><h2>订单总额:</h2><span id="returnMoneyNum">390</span><br><h2>支付明细:</h2><div id="payDetail"><div id="userPay"><span>390</span>邮豆(用户支付)</div><b style="margin: 0px 20px">+</b><div id="subPay"><span>390</span>邮豆(网点补贴)</div></div></li>
        <li>
            <h2>填写退货单</h2>
            <ul class="returnlist">
                <li><span><i>*</i>是否退货:</span><input type="radio" checked="checked"/>退货</li>
                <li><span><i>*</i>退单原因:</span>
                    <select id="customerReturnReason">
                        <#list backReason as object>
                            <option value="${object.name()}">${object.getTag()}</option>
                        </#list>
                    </select>
                </li>
                <li><span><i>*</i>申请凭据:</span>
                    <abbr id="customerReturnCredentials">
                    <#list credential as object>
                        <input type="radio" name="proof" checked="checked" value="${object.name()}" />${object.getTag()}<br/>
                    </#list>

                    </abbr>
                </li>
                <li><span><i>*</i>退款金额:</span>
                    <#--<span id="returnPrice"></span>邮豆-->
                    <div id="detailShow"></div>
                </li>
                <li><span><i>*</i>问题说明:</span>
                    <textarea rows="3" cols="30"></textarea>
                </li>
                <li><span><i>*</i>商品返回方式:</span><input type="radio" checked="checked"/>退回提货点</li>
            </ul>
        </li>
        <li>
            <h2>选择退货商品</h2>
            <dl>
                <dt><abbr style="display: none"></abbr><abbr>商品名称</abbr><abbr>商品编号</abbr><abbr>数量</abbr><abbr>销售单价</abbr><abbr>实付金额</abbr><abbr style="width: 150px">操作数量</abbr>
                </dt>
                <div id="returnGoodsList">

                </div>
            </dl>
        </li>
        <li>
            <input class="allcancelButton" value="取消" type="button" id="CustomerReturnCancel">
            <input class="allseachButton" value="确定退货" type="button" id="CustomerReturnConfrim">
        </li>
    </ul>
</div>
<div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/orderManager.js?version=${VERSION}"></script>
    </div>
</body>
</html>
