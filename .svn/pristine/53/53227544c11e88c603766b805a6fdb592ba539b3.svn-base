<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css" />
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
            .accountTable,.detailThree,.detailFour,.detailOne,.detailTwo{
                transform: scale(0.7,0.7)!important;
                transform-origin: left top!important;
                -webkit-transform: scale(0.7,0.7)!important;
                -webkit-transform-origin: left top!important;
                -ms-transform: scale(0.7,0.7)!important;
                -ms-transform-origin: left top!important;
                -moz-transform: scale(0.7,0.7)!important;
                -moz-transform-origin: left top!important;
            }
            .accountTable section{
                margin-top: 0px!important;
            }
            input[name='accountTel'], input[name='accountCust'], input[name='accountUserName']{
                width: 170px!important;
            }
            .allcpageTurnButton a,.allcpageTurnButton span{
                font-size: 24px!important;
            }
            /*.accountSearch{*/
                /*transform: scale(0.8,0.8);*/
                /*transform-origin: left top;*/
                /*-webkit-transform: scale(0.8,0.8);*/
                /*-webkit-transform-origin: left top;*/
                /*-ms-transform: scale(0.8,0.8);*/
                /*-ms-transform-origin: left top;*/
                /*-moz-transform: scale(0.8,0.8);*/
                /*-moz-transform-origin: left top;*/
                /*height: 80px!important;*/
            /*}*/

        }
        .accountTable{
            transform: scale(0.9,0.9);
            transform-origin: left top;
            -webkit-transform: scale(0.9,0.9);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.9,0.9);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.9,0.9);
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
            height:130px;
            margin-left:20px;
            font-size: 12px;
        }
        .accountSearch li{
            width:220px;
            height:40px;
            line-height:40px;
            float:left;
            margin:10px 0px;
        }
        .accountSearch li input{

        }

        #accSearch{
            width:80px;
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
        .account_Table abbr:hover{
            color:#f2b95d;
            border:1px solid #f2b95d;
        }
        .itemContainer td abbr{
            display:inline-block;
            height:20px;
            line-height:20px;
            text-align:center;
            border-radius:5px;
            margin-top:30px;
            border:1px solid transparent;

        }
        .itemContainer a,i{
            color:#54a6de;
            padding:0px;
            cursor:pointer;

        }
        .detailOne{
            width:800px;
            height:auto;
            position:absolute;
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
        .detailOne ul.checkDetailOne{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailOne dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#666666;
        }
        .checkDetailOne dd{
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
        #xxOne{
            display:inline-block;
            width:16px;
            height:16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }
        /*网点代客下单*/
        .detailTwo{
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
        .detailTwo ul.checkDetailTwo{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailTwo dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#666666;
        }
        .checkDetailTwo dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }


        #xxTwo{
            display:inline-block;
            width:16px;
            height:16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }


        /*用户自己下单*/
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
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#666666;
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

        /*网点自提代客*/
        .detailFour{
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
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#666666;
        }
        .checkDetailFour dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }

        #xxFour{
            display:inline-block;
            width:16px;
            height:16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }
        .holder{
            margin-top: 30px;
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





        .setAccountState{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:100;
            display: none;
        }
        .setAccountState li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #setAccountStateCancel{
            width:200px;
            height:50px;
            border:1px solid #ccc;
            background:#fff;
            color:#666666
            cursor:pointer;

        }
        #setAccountStateCancel:hover{
        }
        #setAccountStateCancel:active{
            background: #008738;
            color: #fff;

        }
        #setAccountStateConfrim{
            width:200px;
            height:50px;
            border:1px solid transparent;
            background:#54a6de;
            color:#fff;
            cursor:pointer;

        }
        #setAccountStateConfrim:hover{
        }
        #setAccountStateConfrim:active{
            background: #008738;

        }

        .setAccountMoneyWarning{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:100;
            display: none;
        }
        .setAccountMoneyWarning li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #setAccountMoneyWarningCancel{
            width:200px;
            height:50px;
            border:1px solid #ccc;
            background:#fff;
            color:#666666
        cursor:pointer;

        }
        #setAccountMoneyWarningCancel:hover{
        }
        #setAccountMoneyWarningCancel:active{
            background: #008738;
            color: #fff;

        }
        #setAccountMoneyWarningConfrim{
            width:200px;
            height:50px;
            border:1px solid transparent;
            background:#54a6de;
            color:#fff;
            cursor:pointer;

        }
        #setAccountMoneyWarningConfrim:hover{
        }
        #setAccountMoneyWarningConfrim:active{
            background: #008738;

        }




        .setAccountMoney{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:100;
            display: none;
        }
        .setAccountMoney li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #setAccountMoneyCancel{
            width:200px;
            height:50px;
            border:1px solid #ccc;
            background:#fff;
            color:#666666
        cursor:pointer;

        }
        #setAccountMoneyCancel:hover{
        }
        #setAccountMoneyCancel:active{
            background: #008738;
            color: #fff;

        }
        #setAccountMoneyConfrim{
            width:200px;
            height:50px;
            border:1px solid transparent;
            background:#54a6de;
            color:#fff;
            cursor:pointer;

        }
        #setAccountMoneyConfrim:hover{
        }
        #setAccountMoneyConfrim:active{
            background: #008738;

        }













        .CancelAccountStateWarning{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:100;
            display: none;
        }
        .CancelAccountStateWarning li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #CancelAccountStateWarningCancel{
            width:200px;
            height:50px;
            border:1px solid #ccc;
            background:#fff;
            color:#666666
        cursor:pointer;

        }
        #CancelAccountStateWarningCancel:hover{
        }
        #CancelAccountStateWarningCancel:active{
            background: #008738;
            color: #fff;

        }
        #CancelAccountStateWarningConfrim{
            width:200px;
            height:50px;
            border:1px solid transparent;
            background:#54a6de;
            color:#fff;
            cursor:pointer;

        }
        #CancelAccountStateWarningConfrim:hover{
        }
        #CancelAccountStateWarningConfrim:active{
            background: #008738;

        }






        .updateAccountState{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:100;
            display: none;
        }
        .updateAccountState li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #updateAccountStateCancel{
            width:200px;
            height:50px;
            border:1px solid #ccc;
            background:#fff;
            color:#666666
        cursor:pointer;

        }
        #updateAccountStateCancel:hover{
        }
        #updateAccountStateCancel:active{
            background: #008738;
            color: #fff;

        }
        #updateAccountStateConfrim{
            width:200px;
            height:50px;
            border:1px solid transparent;
            background:#54a6de;
            color:#fff;
            cursor:pointer;

        }
        #updateAccountStateConfrim:hover{
        }
        #updateAccountStateConfrim:active{
            background: #008738;

        }

        #holder_All_previous,#holder_second_next,#holder_first_next,#holder_third_next,#holder_forth_next,#holder_fifth_next,#holder_sixth_previous{
            width:80px!important;
        }

        #holder_All_next,#holder_second_previous,#holder_first_previous,#holder_third_previous,#holder_forth_previous,#holder_fifth_previous,#holder_sixth_next{
            width:80px!important;
        }
        td section{
            margin-left: 20px!important;
        }
        td img{
            margin-top: 20px;
        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;

        var isEnd = false;
        var isTop = true;
        var eid = ${enterpriseInfo.enterpriseId};
        var ename = "${enterpriseInfo.enterpriseName}";

        $(document).ready(function(e) {
            $( ".detailOne" ).draggable();
            $( ".detailTwo" ).draggable();
            $( ".detailThree" ).draggable();
            $( ".detailFour" ).draggable();
            $( ".setAccountState" ).draggable();
            $( ".setAccountMoneyWarning" ).draggable();
            $( ".setAccountMoney" ).draggable();
            $( ".CancelAccountStateWarning" ).draggable();
            $( ".updateAccountState" ).draggable();

            //仓库列表
            inventoryList(eid,ename);
            sessionStorage.setItem("ShoppingCart",'');
            $("#accReturn").click(function(){
                window.location.href = "accountReturn";
            })
            $(".accountTable li").click(function(){
                $(this).addClass("accOn").siblings("li").removeClass("accOn");
            })
            $("#xxOne").click(function(){
                discoverHtml();
                $(".detailOne").fadeOut(500);
            })
            $("#xxTwo").click(function(){
                discoverHtml();
                $(".detailTwo").fadeOut(500);
            })
            $("#xxThree").click(function(){
                discoverHtml();
                $(".detailThree").fadeOut(500);
            })
            $("#xxFour").click(function(){
                discoverHtml();
                $(".detailFour").fadeOut(500);
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
        <li style="width: 300px;"><input class="allinputButton" placeholder="查看范围" value="${enterpriseInfo.enterpriseName}" style="width: 250px;color: #999!important;" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterpriseInfo.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
        <li><input style="width: 200px" class="allinputButton" placeholder="订单号"  type="text" name="accountNum" /></li>
        <li style="width:180px;"><input class="allinputButton" placeholder="收货人"  type="text" name="accountCust" /></li>
        <li style="width: 180px"><input class="allinputButton" placeholder="联系电话"  type="text" name="accountTel" /></li>
        <li style="width: 180px"><input class="allinputButton" placeholder="用户名"   type="text" name="accountUserName" /></li>
        <li style="width: 280px"></li>
        <li><input class="allinputButton" placeholder="下单开始时间"   type="text" style="width:200px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
        <li><input class="allinputButton" placeholder="下单结束时间"   type="text" style="width:200px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>

        <li><input class="allinputButton" placeholder="付款开始时间"   type="text" style="width:200px;" id="pay_datetimepicker_start"/><a  id="pay_date_start" href="#"class="date_button"/></a></li>
        <li><input class="allinputButton" placeholder="付款结束时间"   type="text" style="width:200px;" id="pay_datetimepicker_end"/><a  id="pay_date_end" href="#"class="date_button"/></a></li>
        <li style="width:500px;"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input class="allclickButton" type="button" id="accExport" value="导出所有" /><input class="allclickButton" type="button" id="accChooseExport" value="导出选中的" /></li>
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
        <thead style="display:inline-block; width:1604px; height:40px; color:#666666;border-top:1px solid #e5e5e5;" >
        <tr>
            <th style="display:inline-block; width:0px; height:40px; overflow:hidden;">订单号</th>
            <th style="display:inline-block; width:400px; height:40px; line-height:40px; border-style:none;">商品</th>
            <th style="display:inline-block; width:80px; height:40px; line-height:40px; border-style:none;">总价/数量</th>
            <th style="display:inline-block; width:340px; height:40px; line-height:40px; border-style:none;">买家信息</th>
            <th style="display:inline-block; width:240px; height:40px; line-height:40px; text-align:left; border-style:none;">下单时间</th>
            <th style="display:inline-block; width:200px; height:40px; line-height:40px; text-align:left; border-style:none;">订单状态</th>
            <th style="display:inline-block; width:170px; height:40px; line-height:40px; text-align:left; border-style:none;">订单金额</th>
            <th style="display:inline-block; width:110px; height:40px; line-height:40px; text-align:left; border-style:none;">商家</th>
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


<div class="detailOne allpop">
    <h1>订单号:<span class="accountNUM"></span><i id="xxOne"></i></h1>
    <ul>
        <li style="width:800px; height:150px;">
            <ul class="detailImg" style="width:800px; height:150px;">
                <li class="Img11" style="text-align:center; position:relative; color:#b2b2b2;">已下单<br /><abbr class="Img1" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">1</abbr><abbr class="Img1" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="orderTime" style="display:inline-block; margin-top:10px; color:#999;"></span></li>
                <li class="Img22" style="text-align:center; position:relative; color:#b2b2b2;">已付款<br /><abbr class="Img2" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img2" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">2</abbr><abbr class="Img2" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="PayedTime" style="display:inline-block; margin-top:10px; color:#999;"></span></li>
                <li class="Img33" style="text-align:center; position:relative; color:#b2b2b2;">已发货<br /><abbr class="Img3" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img3" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">3</abbr><abbr class="Img3" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="sendExpressTime" style="display:inline-block; margin-top:10px; color:#999;"></span></li>
                <li class="Img44" style="text-align:center; position:relative; color:#b2b2b2;">已完成<br /><abbr class="Img4" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img4" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">4</abbr><span class="finishTime" style="display:inline-block; margin-top:10px; color:#999;"></span></li>
                <li class="Img55" style="text-align:center; position:relative; color:#b2b2b2;">已取消<br /><abbr class="Img5" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img5" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">2</abbr><span class="cancelTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
            </ul>
        </li>
        <li>
            <ul class="checkDetailOne">
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
                        <dd class="dt deliveryInfo">快递信息</dt>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">配送方式</dt>
                        <dd>配送方式：<span class="deliveryType"></span></dd>
                        <dd>运费：<span class="deliveryPrice">0.00</span></dd>
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
                        <dd class="dt">下单类型</dt>
                        <dd>下单类型：<span class="billType"></span></dd>
                        <dd>操作者：<span class="operator"></span></dd>
                        <dd>扣款验证方式：<span class="payWay"></span></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">卖家备注</dt>
                        <dd style="width:740px;">卖家备注：<span class="selfRemark"></span></dd>
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
                    <td><span></span>邮豆</td>
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




<div class="detailTwo allpop">
    <h1>订单号:<span class="accountNUM"></span><i id="xxTwo"></i></h1>
    <ul>

        <li style="width:800px; height:150px;">
            <ul class="detailImg" style="width:800px; height:150px;">
                <li class="Img11" style="text-align:center; position:relative; color:#b2b2b2;">已下单<br /><abbr class="Img1" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">1</abbr><abbr class="Img1" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="orderTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img22" style="text-align:center; position:relative; color:#b2b2b2;">已付款<br /><abbr class="Img2" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img2" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">2</abbr><abbr class="Img2" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="PayedTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img33" style="text-align:center; position:relative; color:#b2b2b2;">已发货<br /><abbr class="Img3" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img3" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">3</abbr><abbr class="Img3" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="sendExpressTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img44" style="text-align:center; position:relative; color:#b2b2b2;">已完成<br /><abbr class="Img4" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img4" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">4</abbr><span class="finishTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img55" style="text-align:center; position:relative; color:#b2b2b2;">已取消<br /><abbr class="Img5" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img5" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">2</abbr><span class="cancelTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
            </ul>
        </li>
        <li>
            <ul class="checkDetailTwo">
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
                        <dd class="dt deliveryInfo">快递信息</dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">配送方式</dd>
                        <dd>配送方式：<span class="deliveryType"></span></dd>
                        <dd>运费：<span class="deliveryPrice">0.00</span></dd>
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
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">卖家备注</dd>
                        <dd style="width:740px;">卖家备注：<span class="selfRemark"></span></dd>
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
                    <td><span></span>邮豆</td>
                    <td>1</td>
                    <td>个数：两件套</td>
                    <td><span></span>邮豆</td>
                    <td><span></span>邮豆</td>

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
                <li class="Img11" style="text-align:center; position:relative; color:#b2b2b2;">已下单<br /><abbr class="Img1" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">1</abbr><abbr class="Img1" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="orderTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img22" style="text-align:center; position:relative; color:#b2b2b2;">已付款<br /><abbr class="Img2" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img2" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">2</abbr><abbr class="Img2" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="PayedTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img44" style="text-align:center; position:relative; color:#b2b2b2;">已完成<br /><abbr class="Img4" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img4" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">4</abbr><span class="finishTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img55" style="text-align:center; position:relative; color:#b2b2b2;">已取消<br /><abbr class="Img5" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img5" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">2</abbr><span class="cancelTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
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
                        <dd style="width:740px;">卖家备注：<span class="selfRemark"></span></dd>
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

<div class="detailFour allpop">
    <h1>订单号:<span class="accountNUM"></span><i id="xxFour"></i></h1>
    <ul>
        <li style="width:800px; height:150px;">
            <ul class="detailImg" style="width:800px; height:150px;">
                <li class="Img11" style="text-align:center; position:relative; color:#b2b2b2;">已下单<br /><abbr class="Img1" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">1</abbr><abbr class="Img1" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="orderTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;">2016-03-22 09:01:00</span></li>
                <li class="Img22" style="text-align:center; position:relative; color:#b2b2b2;">已付款<br /><abbr class="Img2" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img2" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:92px; font-size:9px">2</abbr><abbr class="Img2" style="display:inline-block; width:90px; height:5px; background:#b2b2b2; position:absolute; left:109px; top:68px;"></abbr><span class="PayedTime" style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img44" style="text-align:center; position:relative; color:#b2b2b2;">已完成<br /><abbr class="Img4" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img4" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">4</abbr><span class="finishTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
                <li class="Img55" style="text-align:center; position:relative; color:#b2b2b2;">已取消<br /><abbr class="Img5" style="display:inline-block; width:92px; height:5px; background:#b2b2b2; position:absolute; left:0px; top:68px;"></abbr><abbr class="Img5" style="display:inline-block;  width:20px; height:20px; line-height:20px; color:#FFF; background:#b2b2b2; border-radius:10px; position:absolute; left:90px; font-size:9px">2</abbr><span class="cancelTime"  style="display:inline-block; margin-top:10px; color:#b2b2b2;"></span></li>
            </ul>
        </li>
        <li>
            <ul class="checkDetailFour">
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
                    </dl>
                </li>
                <li>
                    <dl>
                        <dd class="dt">卖家备注</dd>
                        <dd style="width:740px;">卖家备注：<span class="selfRemark"></span></dd>
                    </dl>

                </li>

            </ul>
        </li>
        <li>
            <table class="Purchase_table" style="text-align:center;width:760px; margin-left:20px; margin-top:20px;" cellspacing="0" cellpadding="0" align="center" border="0">
                <thead style="height:40px;">
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
                </tbody>
            </table>

        </li>

    </ul>
</div>



<div class="setAccountState allpop">
    <h1>设置订单状态</h1>
    <ul>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">
            你确定将订单状态修改为已付款吗
        </li>
        <li style="text-align: center;">
            <input type="button" id="setAccountStateConfrim" value="确定" />
            <input type="button" id="setAccountStateCancel" value="取消" />
        </li>
    </ul>
</div>

<div class="setAccountMoneyWarning allpop">
    <h1>操作提示</h1>
    <ul>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">
         订单金额不能随意修改，你确定要修改吗？
        </li>
        <li style="text-align: center;">
            <input class="allseachButton" type="button" id="setAccountMoneyWarningConfrim" value="确定" />
            <input class="allcancelButton" type="button" id="setAccountMoneyWarningCancel" value="取消" />
        </li>
    </ul>
</div>


<div class="setAccountMoney allpop">
    <h1>修改订单金额</h1>
    <ul>
        <input type="hidden" id="orderCode" />
        <li style="height:100px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:100px; text-align: center;">
          <i style="color: #ff3300;">*</i>优惠的金额：<input style="width: 350px; height: 35px;" type="text" id="priceForsetAccountMoney"/>
        </li>
        <li style="height:150px; width: 640px; margin-left: 20px; line-height:150px; text-align: center;">
            <span style="position:relative; top: -70px;"><i style="color: #ff3300;">*</i>修改金额原因：</span><textarea rows="4" cols="40"></textarea>
        </li>
        <li style="text-align: center;">
            <input class="allseachButton" type="button" id="setAccountMoneyConfrim" value="确定" />
            <input class="allcancelButton" type="button" id="setAccountMoneyCancel" value="取消" />
        </li>
    </ul>
</div>


<div class="CancelAccountStateWarning allpop">
    <h1>操作提示</h1>
    <ul>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">
            订单不能随意中断，你确定要修改吗？
        </li>
        <li style="text-align: center;">
            <input class="allseachButton" type="button" id="CancelAccountStateWarningConfrim" value="确定" />
            <input class="allcancelButton" type="button" id="CancelAccountStateWarningCancel" value="取消" />
        </li>
    </ul>
</div>

<div class="updateAccountState allpop">
    <h1>修改订单状态</h1>
    <ul>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">
            <span style="position:relative; top: -70px;"><i style="color: #ff3300;">*</i>申请原因：</span><textarea rows="4" cols="50"></textarea>
        </li>
        <li style="text-align: center;">
            <input class="allseachButton" type="button" id="updateAccountStateConfrim" value="确定" />
            <input class="allcancelButton" type="button" id="updateAccountStateCancel" value="取消" />
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
