<#--<#assign bath = request.contextPath>-->
<#--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<#--<html xmlns="http://www.w3.org/1999/xhtml">-->
<#--<head>-->
    <#--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />-->
    <#--<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css"/>-->
    <#--<!--[if !IE]><!&ndash;&gt; <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]&ndash;&gt;-->
    <#--<!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]&ndash;&gt;-->
    <#--<script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>-->
    <#--<style type="text/css">-->
        <#--a{-->
            <#--color: #24b35f;-->
        <#--}-->
        <#--.memberSearch{-->
            <#--width:1600px;-->
            <#--height:150px;-->
            <#--margin-left:20px;-->
            <#--margin-top:20px;-->
        <#--}-->
        <#--.memberSearch li{-->
            <#--width:250px;-->
            <#--height:36px;-->
            <#--line-height:40px;-->
            <#--float:left;-->
        <#--}-->
        <#--.memberSearch li input,select{-->
            <#--width:120px;-->
            <#--height:36px;-->
            <#--margin-left: -6px;-->
            <#--border:1px solid #ccc;-->
            <#--background:#FFF;-->
            <#--margin-top: -1px;-->
        <#--}-->
        <#--.memberSearch li input:hover{-->
            <#--box-shadow: 2px 0px 3px 0px #ccc;-->
        <#--}-->
        <#--option{-->
            <#--font-size: 15px;-->
        <#--}-->
        <#--.memberSearch li span{-->
            <#--display:inline-block;-->
            <#--width:90px;-->
            <#--height:36px;-->
            <#--text-align:center;-->
            <#--font-size:15px;-->
            <#--background:#f1f1f1;-->
            <#--line-height:36px;-->
            <#--border:1px solid #ccc;-->

        <#--}-->
        <#--#accSearch{-->
            <#--width:70px;-->
            <#--height:37px;-->
            <#--background:#24b35f;-->
            <#--color:#FFF;-->
            <#--border:1px solid transparent;-->
            <#--box-shadow: 0px 3px 3px 0px #ccc;-->
            <#--font-size:15px;-->
            <#--margin-left: 10px;-->
        <#--}-->
        <#--#accSearch:hover{-->
            <#--background: #00cc55;-->
        <#--}-->
        <#--#accSearch:active{-->
            <#--background: #008738;-->

        <#--}-->
        <#--#accExport{-->
            <#--width:110px;-->
            <#--height:37px;-->
            <#--background:#33cccc;-->
            <#--color:#FFF;-->
            <#--font-size: 15px;-->
            <#--border:1px solid transparent;-->
            <#--margin-left:30px;-->
        <#--}-->
        <#--#accExport:hover{-->
            <#--border:1px solid #f0aa39;-->
            <#--color:#f0aa39;-->
        <#--}-->



        <#--.memberList{-->
            <#--width:1600px;-->
            <#--height:800px;-->
            <#--overflow-y:auto;-->
            <#--margin-left:20px;-->
            <#--margin-top:20px;-->
        <#--}-->
        <#--.memberList dt{-->
            <#--width:1600px;-->
            <#--height:50px;-->
            <#--background:url(${bath}/static/img/com_pic_backgroundcolour.png) center no-repeat;-->
            <#--color:#FFF;-->
        <#--}-->
        <#--.memberList dt abbr{-->
            <#--display:inline-block;-->
            <#--width:150px;-->
            <#--height:50px;-->
            <#--text-align:center;-->
            <#--padding:15px 0px;-->
        <#--}-->
        <#--.memberList dd{-->
            <#--width:1600px;-->
            <#--height:80px;-->
            <#--background:#fff;-->
            <#--border-bottom:1px solid #CCC;-->
        <#--}-->
        <#--.memberList dd abbr{-->
            <#--display:inline-block;-->
            <#--text-align:center;-->
            <#--width:150px;-->
            <#--height:80px;-->
            <#--vertical-align:middle;-->
            <#--padding:30px 0px;-->

        <#--}-->
        <#--.spendDetail{-->
            <#--width:120px;-->
            <#--height:30px;-->
            <#--background:#24b383;-->
            <#--margin-top:-5px;-->
            <#--border:none;-->
            <#--font-size: 14px;-->
            <#--color: #fff;-->
        <#--}-->
        <#--.spendDetail:hover{-->
            <#--box-shadow: 0px 3px 3px 0px #999;-->
        <#--}-->
        <#--.spendDetail:active{-->
             <#--background: #009966;-->
         <#--}-->
        <#--.spendingDetail{-->
            <#--width:850px;-->
            <#--height:600px;-->
            <#--position:absolute;-->
            <#--left:15%;-->
            <#--top:15%;-->
            <#--border:1px solid #CCC;-->
            <#--box-shadow:0px 0px 3px 0px #CCCCCC;-->
            <#--background:#FFF;-->
            <#--display:none;-->
            <#--z-index: 2;-->
        <#--}-->
        <#--#xx{-->
            <#--display:inline-block;-->
            <#--width:25px;-->
            <#--height:25px;-->
            <#--background:url(${bath}/static/img/XX.png) center no-repeat;-->
            <#--position:relative;-->
            <#--left:650px;-->
            <#--top:10px;-->
            <#--cursor:pointer;-->
        <#--}-->
        <#--.spendingList{-->
            <#--margin:20px 0px 0px 20px;-->
            <#--width:810px;-->
            <#--height:420px;-->
        <#--}-->
        <#--.spendingList dt{-->
            <#--width:810px;-->
            <#--height:40px;-->
            <#--color: #fff;-->
            <#--background:url(${bath}/static/img/com_pic_backgroundcolour.png) center no-repeat;-->
        <#--}-->
        <#--.spendingList dt abbr{-->
            <#--display:inline-block;-->
            <#--width:180px;-->
            <#--text-align:center;-->
            <#--height:40px;-->
            <#--line-height:40px;-->
        <#--}-->
        <#--.spendingList dd{-->
            <#--width:810px;-->
            <#--height:70px;-->
            <#--background:#fff;-->
            <#--border-bottom:1px solid #CCC;-->
        <#--}-->
        <#--.spendingList dd abbr{-->
            <#--display:inline-block;-->
            <#--width:180px;-->
            <#--text-align:center;-->
            <#--height:70px;-->
            <#--line-height:70px;-->
        <#--}-->

    <#--</style>-->
    <#--<script type="text/javascript">-->
        <#--$(document).ready(function(e) {-->
            <#--$(".memberSearch li input").focus(function(){-->
                <#--$(this).css("border","1px solid #008738");-->

            <#--})-->
            <#--$(".memberSearch li input").blur(function(){-->
                <#--$(this).css("border","1px solid #ccc");-->

            <#--})-->
            <#--$("#xx").click(function(){-->
                <#--$(".spendingDetail").fadeOut(500);-->
            <#--})-->
            <#--$(".spendDetail").click(function(){-->
                <#--$(".spendingDetail").fadeIn(500);-->
            <#--})-->
        <#--});-->


    <#--</script>-->

    <#--<title>无标题文档</title>-->
<#--</head>-->

<#--<body>-->

<#--<div style="width:100%; height:70; line-height: 70px; border-bottom:2px solid #dcdcdc; margin-left:20px;">-->

    <#--<a style="color: #24b35f;" href="memberManager.html">会员列表</a>-->
    <#--|-->
    <#--<span style="color: #333;">会员消费统计</span>-->
<#--</div>-->

<#--<div class="memberSearch">-->
    <#--<ul>-->
        <#--<li>-->
            <#--<span>身份证号</span>-->
            <#--<input type="text" name="memberNum" />-->
        <#--</li>-->
        <#--<li>-->
            <#--<span>姓名</span>-->
            <#--<input type="text" name="memberCust" />-->
        <#--</li>-->
        <#--<li>-->
            <#--<span>联系电话</span>-->
            <#--<input type="text" name="memberCust" />-->
        <#--</li>-->
        <#--<li>-->
            <#--<span>开始时间</span>-->
            <#--<input type="text" name="memberUserName" />-->
        <#--</li>-->
        <#--<li>-->
            <#--<span>结束时间</span>-->
            <#--<input type="text" name="memberUserName" />-->
        <#--</li>-->
        <#--<li>-->
            <#--<input type="button" value="搜索" id="accSearch" />-->
        <#--</li>-->
    <#--</ul>-->
<#--</div>-->

<#--<div class="memberList">-->
    <#--<dl>-->
        <#--<dt><abbr>头像</abbr><abbr style="width:180px;">身份证号</abbr><abbr>姓名</abbr><abbr>性别</abbr><abbr>联系电话</abbr><abbr>消费总额</abbr><abbr>消费邮豆</abbr><abbr>消费现金</abbr><abbr style="width:300px;">操作</abbr>-->
        <#--</dt>-->
        <#--<dd><abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr><abbr style="width:180px;" class="memId">320583198811111111</abbr><abbr class="memName">张三</abbr><abbr class="memSex">男</abbr><abbr class="memTel">12312312312</abbr><abbr class="memSpendTotal"><span>500.00</span>邮豆</abbr><abbr class="memSpendUcoin"><span>300.00</span>邮豆</abbr><abbr class="memSpendCash"><span>200.00</span>元</abbr><abbr style="width:300px;">-->
                <#--<input onclick="coverHtml()" class="spendDetail" type="button" value="查看详情" />-->
            <#--</abbr>-->
        <#--</dd>-->
    <#--</dl>-->
<#--</div>-->

<#--<div class="spendingDetail">-->
    <#--<ul>-->
        <#--<li style="height:80px; line-height:80px;  padding-left:20px; border-bottom:1px solid #ccc; font-size:20px; color:#333;">会员消费详情<i onclick="discoverHtml()" id="xx"></i></li>-->
        <#--<li>-->
            <#--<dl class="spendingList">-->
                <#--<dt><abbr>订单号</abbr><abbr>消费总额</abbr><abbr>支付方式</abbr><abbr>消费时间</abbr>-->
                <#--</dt>-->
                <#--<dd>-->
                    <#--<abbr>2016032114374039</abbr>-->
                    <#--<abbr>30.00邮豆</abbr>-->
                    <#--<abbr>30.00邮豆+0.00元</abbr>-->
                    <#--<abbr>2016-04-01 13:38:20</abbr>-->
                <#--</dd>-->
                <#--<dd>-->
                    <#--<abbr>2016032114374039</abbr>-->
                    <#--<abbr>30.00邮豆</abbr>-->
                    <#--<abbr>30.00邮豆+0.00元</abbr>-->
                    <#--<abbr>2016-04-01 13:38:20</abbr>-->
                <#--</dd>-->
                <#--<dd>-->
                    <#--<abbr>2016032114374039</abbr>-->
                    <#--<abbr>30.00邮豆</abbr>-->
                    <#--<abbr>30.00邮豆+0.00元</abbr>-->
                    <#--<abbr>2016-04-01 13:38:20</abbr>-->
                <#--</dd>-->
                <#--<dd>-->
                    <#--<abbr>2016032114374039</abbr>-->
                    <#--<abbr>30.00邮豆</abbr>-->
                    <#--<abbr>30.00邮豆+0.00元</abbr>-->
                    <#--<abbr>2016-04-01 13:38:20</abbr>-->
                <#--</dd>-->
            <#--</dl>-->
        <#--</li>-->
        <#--<li style="height:80px; line-height:80px; position:relative;">-->
            <#--<div style=" width:600px; height:60px; background:#666;position:absolute; right:50px;"></div>-->
        <#--</li>-->
    <#--</ul>-->
<#--</div>-->
<#--<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>-->
<#--</body>-->
<#--</html>-->
<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Cache-Control" CONTENT="no-cache" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css"/>

    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>

    <style>
        .container{
            position: absolute;
            top:20%;
            left:25%;
            width:50%;
            height:500px;
            margin: 0 auto;
            background:url(${bath}/static/img/com_pic_outlets-please.png) center no-repeat;
            background-size: 100%;
        }
        .content{
            position: relative;
            top:54%;
            text-align: center;
            font-size: 24px;
        }
        .content a{
            display: block;
            margin-top: 15px;
            font-size:40px;
            color:#24b35f;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="content">
        <div>抱歉 工程师正在紧急开发中 </div>
        <a>敬请期待！</a>
    </div>
</div>
</body>
