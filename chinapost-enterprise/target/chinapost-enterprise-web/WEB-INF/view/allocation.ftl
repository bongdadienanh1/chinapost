<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery-ui.css">
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${bath}/static/js/allocation.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <title>调拨流程</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){

    }
    .reTop{
        width: 100%;
        height: 50px;
        line-height: 50px;
        background: #fff;
    }
    .topButton{
        margin: 0px 10px;
    }
    .bodyFirst{
        width: 100%;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul{
        width: 1110px;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul li{
        width: 350px;
        height: 40px;
        line-height: 40px;
        border: 1px solid #54a6de;
        margin-left: 15px;
        margin-bottom: 15px;
        float: left;
        white-space: nowrap;
    }
    .bodyFirst ul li span{
        display: inline-block;
        height: 100%;
        width:110px;
        text-align: center;
        background: #54a6de;
        color: #fff;
        border-right:1px solid #54a6de;
    }
    .bodyFirst ul li abbr{
        display: inline-block;
        height: 100%;
        width: 65%;
        padding-left: 5px;
        vertical-align: middle;
        overflow: hidden;
    }
    .bodyFirst ul li abbr input{
        width: 100%;
        height: 80%;
    }
    .bodySecond{
        width:100%;
        height: auto;
        margin: auto;
    }
    .bodySecond h1{
        width: 100%;
        height: 50px;
        line-height: 50px;
        padding-left: 5px;
        font-size: 24px;
    }
    .bodySecond dl{
        width: 1100px;
        height: auto;
        /*overflow: hidden;*/
        margin: auto;

    }
    .bodySecond dt abbr{
        background: #edf3f8;
    }
    .bodySecond dt,.bodySecond dd{
        width: 100%;
        height: 50px;
        line-height: 50px;

        white-space: nowrap;
    }
    .bodySecond dd{
        margin-top: -1px;
    }
    .bodySecond dd:nth-child(2n-1){
        background: #fefefe;
    }
    .bodySecond dt abbr:first-child{
        width:70px ;
    }
    .bodySecond dt abbr:nth-child(2){
        width:140px ;
    }
    .bodySecond dd abbr:first-child{
        width:70px ;
    }
    .bodySecond dd abbr:nth-child(2){
        width:140px ;
        overflow: hidden;
    }
    .bodySecond dd:last-child abbr{
        border-bottom: 1px solid #54a6de;
    }
    .bodySecond dd :last-child {
        /*margin-bottom: 20px;*/
    }
    .bodySecond dt abbr{
        border-bottom: 1px solid #54a6de;
    }
    .bodySecond abbr{
        display: inline-block;
        vertical-align: middle;
        width: 145px;
        height: 48px;
        text-align: center;
        border: 1px solid #54a6de;
        border-bottom: none;
        padding: 0px 1.8px;
        overflow: hidden;
    }
    .bodySecond abbr:not(:first-child){
        margin-left: -1px;
    }
    .bodySecond abbr input[type="text"]{
        width: 96%;
        height: 56%;
        margin-top: 10px;
    }
    #addIvent{
        width: 1000px;
        height: auto;
        position: absolute;
        left: 10%;
        top: 15%;
        background: #fff;
        display: none;
        z-index: 3;
    }
    #addIvent h1{
        width: 100%;
        height: 50px;
        font-size: 24px;
        line-height: 50px;
        border-bottom: 1px solid #bbb;
    }
    #xx{
        display:inline-block;
        vertical-align:middle;
        width:25px;
        height:25px;
        margin-left:800px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        cursor:pointer;
    }
    #addIvent li:first-child{
        width: 800px;
        margin: auto;
        height: 80px;
        line-height: 80px;
    }
    #addIvent li:first-child abbr{
        display: inline-block;
        vertical-align: middle;
        width: 200px;
    }
    #addIvent li:first-child abbr span{
        display: inline-block;
        vertical-align: middle;
        width: 80px;
        background: #f1f1f1;
        border-right: 1px solid #bbb;
        text-align: center;
    }
    #addIvent li:first-child abbr input[type='text']{
        width: 160px;
        height: 90%;
    }
    #addIvent li:first-child abbr input[type='button']{
        width: 80px;
        height:36px;
        background: #54a6de;
        color: #fff;
        margin:0px 10px;
    }
    #addIvent li:nth-child(2){
        width: 800px;
        margin: auto;
        height: auto;
        overflow: hidden;
    }
    #addIvent li:nth-child(2) dl{
        width: 800px;
        height: auto;
        overflow: hidden;
    }
    #addIvent li:nth-child(2) dl dt{
        width: 798px;
        height: 30px;
        line-height: 30px;
        border: 1px solid #bbbbbb;
        background: #f3f3f3;
    }
    #addIvent li:nth-child(2) dl dd{
        width: 798px;
        height: 30px;
        line-height: 30px;
        border: 1px solid #bbbbbb;
        border-top: none;
    }
    #addIvent li:nth-child(2) dl dt abbr,#addIvent li:nth-child(2) dl dd abbr{
        display: inline-block;
        vertical-align: middle;
        width: 128px;
        height: 30px;
        line-height: 30px;
        border-right:1px solid #bbbbbb ;
        text-align: center;
        overflow: hidden;
        padding: 0px 1.8px;
    }
    #addIvent li:nth-child(2) dl abbr input{
        margin-top: 10px;
    }
    #addIvent li dl dt abbr:nth-last-child(1),#addIvent li dl dd abbr:nth-last-child(1){
        border:none;
    }
    #addIvent li dl dt abbr:first-child,#addIvent li dl dd abbr:first-child{
        width: 58px;
    }
    #addIvent li dl dt abbr:nth-child(2),#addIvent li dl dd abbr:nth-child(2){
        width: 198px;
    }

    .suggest{
        width: 90%;
        height: 150px;
        margin:auto;
        margin-top: 20px;
    }
    .suggest textarea{
        width: 100%;
        height: 100%;
        color: #666;
    }
    .circulationSuggest{
        width: 90%;
        height: auto;
        overflow: hidden;
        margin:auto;
        margin-top: 20px;
    }
    .circulationSuggest h1{
        width: 100%;
        height: 50px;
        line-height: 50px;
        font-size: 18px;
    }
    .circulationSuggest ul{
        width: 100%;
        height: auto;
        overflow: hidden;
    }
    .circulationSuggest ul li{
        width: 99%;
        height: 80px;
        line-height: 80px;
        border-bottom: 1px solid #e5e5e5;
        margin-bottom: -1px;
    }
    .circulationSuggest ul li:nth-last-child(1){
        margin-bottom: 5px;
    }
    .circulationSuggest ul li dd:first-child{
        width: 250px;
        text-align: center;
    }
    .circulationSuggest ul li dd{
        display: inline-block;
        vertical-align: middle;
        float: left;
        font-size: 14px;
    }
    .circulationSuggest ul li dd:nth-child(3){
        float: right;
        line-height: 40px;
        margin-right: 10px;
    }
    #repleExamine input,#repleAgree input{
        margin-left: -1px;
    }
    .chooeseDepot input[type="button"]{
        width: 100px;
        height: 40px;
        color: #fff;
        border: none;
        border-radius: 3px;
        margin-top: 30px;
        margin-bottom: 30px;
    }
    .chooeseDepot input[type="button"]:first-child{
        margin-left: 180px;
    }
    .chooeseDepot input[type="button"]:nth-child(2){
        background: #54a6de;
        margin-left: 20px;
    }
    .chooeseDepot{
        width: 600px;
        height: auto;
        overflow: hidden;
        position: absolute;
        left: 10%;
        top: 15%;
        border: 1px solid #bbb;
        z-index: 3;
        background: #fff;
        display: none;
    }
    .chooeseDepot div:first-child{
        height: 60px;
        line-height: 60px;
        font-size: 24px;
        text-indent: 20px;
        border-bottom: 1px solid #bbb;
    }
    .chooeseDepot div:first-child i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        position:relative;
        left:440px;
        top:5px;
        cursor:pointer;
    }
    .chooeseDepot div:nth-child(2){
        height: 500px;
        overflow-y:scroll ;
    }
    .chooeseDepot div:nth-child(2) ul{
        height: auto;
        overflow: hidden;
        padding-left: 20px;
        margin-left: 20px;
    }
    .chooeseDepot div:nth-child(2) ul li{
        padding: 5px 0px;
    }
    .chooeseDepot div:nth-child(2) ul li a{
        display: inline-block;
        text-align: center;
        width: 15px;
        height: 15px;
        line-height: 15px;
        border-radius: 15px;
        border: 1px solid #bbbbbb;
        margin-right: 10px;

    }
    #count dd{
        display: none;
    }



    .chooeseDepotOut input[type="button"]{
        width: 100px;
        height: 40px;
        color: #fff;
        border: none;
        border-radius: 3px;
        margin-top: 30px;
        margin-bottom: 30px;
    }
    .chooeseDepotOut input[type="button"]:first-child{
        margin-left: 180px;
    }
    .chooeseDepotOut input[type="button"]:nth-child(2){
        background: #54a6de;
        margin-left: 20px;
    }
    .chooeseDepotOut{
        width: 600px;
        height: auto;
        overflow: hidden;
        position: absolute;
        left: 10%;
        top: 15%;
        border: 1px solid #bbb;
        z-index: 3;
        background: #fff;
        display: none;
    }
    .chooeseDepotOut div:first-child{
        height: 60px;
        line-height: 60px;
        font-size: 24px;
        text-indent: 20px;
        border-bottom: 1px solid #bbb;
    }
    .chooeseDepotOut div:first-child i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        position:relative;
        left:440px;
        top:5px;
        cursor:pointer;
    }
    .chooeseDepotOut div:nth-child(2){
        height: 500px;
        overflow-y:scroll ;
    }
    .chooeseDepotOut div:nth-child(2) ul{
        height: auto;
        overflow: hidden;
        padding-left: 20px;
        margin-left: 20px;
    }
    .chooeseDepotOut div:nth-child(2) ul li{
        padding: 5px 0px;
    }
    .chooeseDepotOut div:nth-child(2) ul li a{
        display: inline-block;
        text-align: center;
        width: 15px;
        height: 15px;
        line-height: 15px;
        border-radius: 15px;
        border: 1px solid #bbbbbb;
        margin-right: 10px;

    }
    #count{
        display: none;
    }
    #holder{
        margin-top: 30px;
        margin-bottom: 30px;
    }
    .importLog2{
        width:680px;
        height:680px;
        position:fixed;
        left:15%;
        top:15%;
        background:#FFF;
        box-shadow: 5px 5px 5px 5px #999;
        z-index:99;
        display:none;
    }
    .importLog2 ul{
        width: 650px;
        margin-left: 15px;
        border-top:1px solid #e0e0e0;
        padding-top:50px;
        overflow-x: hidden;
        overflow-y: scroll;
    }
    .importLog2 li{
        height:40px;
        margin-top: 14px;
    }
    .importLog2 li div{
        font-size: 14px;
        text-align:left;
        margin-left: 80px;
    }
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        height:24px;
        background-color: #f2f2f2;
        margin-left: -25px;
        margin-top: 10px;
        width: 114px;
        background: url("/static/img/date_img.png") no-repeat center;
    }
    /*.getInlist abbr:not(;first-child){*/
        /*margin-left:-6px*/
    /*}*/
</style>
<script type="text/javascript">
    var isTop = "${isTop}";
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        var status = $("#status").val()
        $(".invet").hide();
        var isTop = "${isTop}";
         var isEnd = "${isEnd}";
        var state =  "${inventorybill.billStatus.getName()}";
        var aStatus = "${status}";
        if(state == "部分收货"){
            $(".bodySecond h1 b").html("待收货清单")
        }
        else{
            $(".bodySecond h1 b").html("调拨清单")
        }
            if( state == "" || state == "待修改" ){
                $(".invet").show();
                $(".reasonForApply").prop("readonly","");
                $(".reasonForApply").css("border","none");
            }else if(state == "已完成"||state == "待发货"||state == "已终止"){
                if(state == "已完成"){
                    if(aStatus == "done"|| aStatus=="myrequest"){
                        $(".jsLogBill").css("width","1652px");
                        $(".clearWidth").css("width","1100px");
                        $(".clearWidth").prev().css("width","1100px")
                        $(".jsLogBill").prev().css("width","1652px")
                    }else {
                        $(".clearWidth").css("width","1652px");
                        $(".jsLogBill").css("width","1652px");
                        $(".jsLogBill").prev().css("width","1652px")
                        $(".clearWidth").prev().css("width","1652px");
//                    $(".bodySecond").css("width","1100px");
                    }
                }
                <#--else if(state == "已终止" && ${inventorybill.billType.getname()} == 'INVENTORY_TRANSFER'){-->
                    <#--$-->
                <#--}-->
                $(".getSettlePrice").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                $(".sumNumber").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                var allNum = 0
                $(".outNum").each(function(){
                    allNum = allNum + parseInt($(this).html())
                });
                if(state == "已完成" && $(".jsLogBill").find(".outNum").length != 0) {
                    allNum = allNum / 2;
                }
                $("#allNum").html(allNum)
                var sumNum = 0
                $(".sumNumber").each(function(){
                    sumNum = sumNum + parseFloat($(this).html())
                })
                $("#sumNum").html(sumNum)
            }
            else if(state == "待收货" && status != "nothandled"){
                if(aStatus == "done"||aStatus=="myrequest"){
                    $(".jsLogBill").css("width","1652px");
                    $(".clearWidth").css("width","1100px");
                    $(".clearWidth").prev().css("width","1100px")
                    $(".jsLogBill").prev().css("width","1652px")
                }else {
                    $(".clearWidth").css("width","1652px");
                    $(".jsLogBill").css("width","1652px");
                    $(".jsLogBill").prev().css("width","1652px")
                    $(".clearWidth").prev().css("width","1652px");
//                    $(".bodySecond").css("width","1652px");
                }
                $(".getSettlePrice").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                $(".sumNumber").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                var allNum = 0
                $(".outNum").each(function(){
                    allNum = allNum + parseInt($(this).html())
                })
                $("#allNum").html(allNum)
                var sumNum = 0
                $(".sumNumber").each(function(){
                    sumNum = sumNum + parseFloat($(this).html())
                })
                $("#sumNum").html(sumNum)
            }
            else if(state == "待收货" && status == "nothandled"||state == "部分收货" && status == "nothandled"){
                if(aStatus == "done" || aStatus=="myrequest"){
                    $(".jsLogBill").css("width","1652px");
                    $(".clearWidth").css("width","1100px");
                    $(".clearWidth").prev().css("width","1100px")
                    $(".jsLogBill").prev().css("width","1652px")
                }else {
                    $(".clearWidth").css("width","1652px");
                    $(".jsLogBill").css("width","1652px");
                    $(".jsLogBill").prev().css("width","1652px")
                    $(".clearWidth").prev().css("width","1652px");
//                    $(".bodySecond").css("width","1652px");
                }
                $(".getSettlePrice").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                $(".sumNumber").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                var allNum = 0
                $(".outNum").each(function(){
                    allNum = allNum + parseInt($(this).html())
                })
                $("#allNum").html(allNum)
                var sumNum = 0
                $(".sumNumber").each(function(){
                    sumNum = sumNum + parseFloat($(this).html())
                })
                $("#sumNum").html(sumNum)
            }else if((state == "部分收货" && status != "nothandled")){
                if(aStatus == "done" || aStatus=="myrequest"){
                    $(".jsLogBill").css("width","1652px");
                    $(".clearWidth").css("width","1100px");
                    $(".clearWidth").prev().css("width","1100px");
                    $(".jsLogBill").prev().css("width","1652px")
                }else {
                    $(".jsLogBill").css("width","1652px");
                    $(".clearWidth").css("width","1652px");
                    $(".clearWidth").prev().css("width","1652px");
                    $(".jsLogBill").prev().css("width","1652px")
                }
                $(".getSettlePrice").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                $(".sumNumber").each(function(){
                    if($(this).html()==""){
                        $(this).html(0)
                    }
                })
                var allNum = 0
                $(".outNum").each(function(){
                    allNum = allNum + parseInt($(this).html())
                })
                $("#allNum").html(allNum)
                var sumNum = 0
                $(".sumNumber").each(function(){
                    sumNum = sumNum + parseFloat($(this).html())
                })
                $("#sumNum").html(sumNum)
                $(".checkInput").hide()
            }

    });

</script>

<body>
<input type="hidden" value="${status}" id="status">
<input type="hidden" value="${billId}" id="billId2">
<div class="reTop">
    <input type="hidden" value="${enterpriseInfo.enterpriseId}" id="enterpriseId">
    <ul>
        <li style="float: left;margin-left: 1%; color: #54a6de;font-weight: bold">
            <span style="margin-right: 10px;">调拨流程</span>-<span style="margin-left: 10px;">
        <#if inventorybill == ''>
            创建
        <#else>
        ${inventorybill.billStatus.getName()}
        </#if></span>
        </li>
        <li style="float: right;margin-right: 1%;">
        <#if inventorybill.billStatus.name() == 'WAIT_RECEIVER'||inventorybill.billStatus.name() == 'WAIT_DELIVERY'||inventorybill.billStatus.name() == 'FINISHED'||inventorybill.billStatus.name() == 'TERMINATED'>
            <input type="button" class="topButton allclickButton" value="导出表格" id="outExcel">
        </#if>
        <#if status != 'done' && status != 'myrequest'>
            <#if inventorybill == ''>
                <div style="display: inline-block;" id="repleCreate"> <input type="button" class="topButton allclickButton" value="提交" id="commit"> </div>
            <#else>

                <input type="hidden" value="${inventorybill.billId}" id="billId">
                <#if inventorybill.billStatus.name() == 'WAIT_DELIVERY'>
                    <div style="display: inline-block;"> <input style="width: 120px;" type="button" id="repleSend" class="topButton allclickButton" value="确认发货">
                        <input type="button" class="topButton stop allclickButton" value="终止">
                    </div>
                <#elseif inventorybill.billStatus.name() == 'WAIT_RECEIVER'>
                    <div style="display: inline-block;"> <input style="width: 120px; margin-right: 20px;"  id="repleReceive" type="button" class="topButton allclickButton" value="确认收货">
                        <input id="repleBack" type="button" class="topButton allclickButton" value="退回">
                    </div>
                <#elseif inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'>
                    <div style="display: inline-block;"> <input style="width: 120px; margin-right: 20px;"  id="repleReceive" type="button" class="topButton allclickButton" value="确认收货">
                    </div>

                <#elseif inventorybill.billStatus.name() == 'WAIT_EDIT'>
                    <div style="display: inline-block;"> <input style="width: 120px; margin-right: 20px;"  id="repleSend2" type="button" class="topButton allclickButton" value="确认发货">
                        <input type="button" class="topButton stop allclickButton" value="终止">
                    </div>
                </#if>
            </#if>
        </#if>
        <#if status == 'done' && isTop && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
            <input type="hidden" value="${inventorybill.billId}" id="billId">
            <input type="button" class="topButton stop allclickButton" value="终止">
        </#if>
        <#if status == 'nothandled' && isTop && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
            <input type="hidden" value="${inventorybill.billId}" id="billId">
            <input type="button" class="topButton stop allclickButton" value="终止">
        </#if>
        <#if status == 'myrequest' && !isTop && !isEnd && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
            <input type="hidden" value="${inventorybill.billId}" id="billId">
            <input type="button" class="topButton stop allclickButton" value="终止">
        </#if>



        </li>
    </ul>
</div>

<div class="bodyFirst">
    <h1 style=" width: 200px;margin: auto;margin-top: 50px;margin-bottom: 50px; font-size: 28px; font-weight: border;">调拨流程</h1>
    <ul>
        <#if inventorybill == ''>
            <li><span>单据编号</span><abbr>新申请单</abbr></li>
            <li><span>申请账号</span><abbr>${enterpriseInfo.enterpriseName}</abbr></li>
            <li><span>申请日期</span><abbr id="time">${.now?string("yyyy-MM-dd")}</abbr></li>
        <#else>
            <li><span>单据编号</span><abbr>${inventorybill.code}</abbr></li>
            <li><span>申请账号</span><abbr>${inventorybill.getTansferInfo().getCreatorName()}</abbr></li>
            <li><span>申请日期</span><abbr id="time">${inventorybill.getCreateTime()?string("yyyy-MM-dd")}</abbr></li>
        </#if>
        <#if inventorybill == ''>
            <li><span>当前状态</span><abbr>创建</abbr></li>
        <#else>
            <li><span>当前状态</span><abbr data-status="${inventorybill.billStatus.name()}">${inventorybill.billStatus.getName()}</abbr></li>
        </#if>
        <#if inventorybill == ''>
            <#if isTop>
                <li><span>*调出仓库</span><abbr class="choosenIntOut" data-id="${enterprise.enterpriseId}" style="cursor: pointer;">${enterprise.enterpriseName}</abbr></li>
                <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            <#elseif isEnd>
                <li><span>*调出仓库</span><abbr class="choosenIntOut" data-id="${enterpriseInfo.enterpriseId}" style="cursor: pointer;">${enterpriseInfo.enterpriseName}</abbr></li>
                <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            <#else>
                <li><span>*调出仓库</span><abbr class="choosenIntOut" style="cursor: pointer;" id="choosenIntOut">请选择</abbr></li>
                <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            </#if>
        <#elseif inventorybill.billStatus.name() == 'FINISHED'||inventorybill.billStatus.name() == 'WAIT_RECEIVER'||inventorybill.billStatus.name() == 'WAIT_DELIVERY'||inventorybill.billStatus.name() == 'TERMINATED'>
            <li><span>*调出仓库</span><abbr class="choosenIntOut"  data-id="${inventorybill.getInId()}" style="cursor: pointer;" >${inventorybill.getTansferInfo().getOutName()}</abbr></li>
            <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" data-id="${inventorybill.getOutId()}" style="cursor:pointer;">${inventorybill.getTansferInfo().getInName()}</abbr></li>
            <li><span>收货地址</span><abbr  style="cursor: pointer;" >${inventorybill.getTansferInfo().getConsignAddress()}</abbr></li>
            <li><span>收货人</span><abbr  style="cursor: pointer;" >${inventorybill.getTansferInfo().getConsignMan()}</abbr></li>
            <li><span>收货电话</span><abbr  style="cursor: pointer;" >${inventorybill.getTansferInfo().getConsignMobile()}</abbr></li>
            <li><span>合计数量</span><abbr id="allNum" style="cursor: pointer;" >${inventorybill.getTansferInfo().getConsignMobile()}</abbr></li>
            <li><span>合计金额(元)</span><abbr id="sumNum"  style="cursor: pointer;" >${inventorybill.getTansferInfo().getConsignMobile()}</abbr></li>
        <#else>
            <li><span>*调出仓库</span><abbr class="choosenIntOut"  data-id="${inventorybill.getInId()}" style="cursor: pointer;" >${inventorybill.getTansferInfo().getOutName()}</abbr></li>
            <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" data-id="${inventorybill.getOutId()}" style="cursor:pointer;">${inventorybill.getTansferInfo().getInName()}</abbr></li>
        </#if>
        <li style="width: 717px;"><span>*调拨原因</span><abbr><input readonly="readonly" style="border:none;" type="text" class="reasonForApply" value="${inventorybill.reason}"/> </abbr></li>
    </ul>

</div>
<div class="bodySecond">
    <h1 class="hhead" style="margin: 0 auto;width: 1100px;"><b style="font-weight: normal;font-family: '黑体';">调拨清单</b><span style="float: right;margin-right: 1%;">
    <#if inventorybill == ''>
        <input style="margin-right: 20px;" class="topButton allseachButton" id="addinvet" type="button" value="添加"/><input class="topButton delete allseachButton" type="button" value="删除"/>
        <#elseif inventorybill.billStatus.name() == 'WAIT_EDIT' && status != 'done'>
            <input style="margin-right: 20px;" class="topButton allseachButton" id="addinvet" type="button" value="添加"/><input class="topButton delete allseachButton" type="button" value="删除"/>
    </#if>
    </span></h1>
    <dl class="clearWidth">
        <#if inventorybill == ''>
            <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>*调拨数量</abbr> </dt>        </#if>
        <#if inventorybill != "" && inventorybill.billStatus.name() == 'FINISHED'|| inventorybill != ""  && inventorybill.billStatus.name() == 'TERMINATED'>
            <dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>金额(元)</abbr>    </dt>
        <#elseif inventorybill != "" && inventorybill.billStatus.name() == 'WAIT_DELIVERY'>
            <dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>金额(元)</abbr>   </dt>
        <#elseif inventorybill.billStatus.name() == 'WAIT_RECEIVER'&& inventorybill != ""  >
            <dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>金额(元)</abbr><abbr class="checkInput" style="width: 100px">本次收货数量</abbr>
            <#if status != 'done' && status != 'myrequest'>
                <abbr style="width: 130px;margin-left: -6px">生产日期</abbr><abbr style="width: 130px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>
            </#if>
            </dt>
        <#elseif inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'&& inventorybill != ""  >
            <dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>已收货数量</abbr><abbr class="checkInput" style="width: 100px">本次收货数量</abbr>
                <#if status != 'done' && status != 'myrequest'>
                    <abbr style="width: 130px;margin-left: -6px">生产日期</abbr><abbr style="width: 130px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>
                </#if>
            </dt>
        <#else>
            <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>*调拨数量</abbr> </dt>
        </#if>
            <#assign x = 0>
        <#if  inventorybill.billStatus.name() == 'WAIT_EDIT'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getInfo().getGoodsInfoId()}"><abbr><input type="checkbox" name="invetcheck"><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr>${item.getInfo().getAvailable()}</abbr><abbr class="outNum"><input value="${item.getAmount()}" class="change" type="text"></abbr>
                </dd>
            </#list>
        <#elseif inventorybill.billStatus.name() == 'FINISHED'||inventorybill.billStatus.name() == 'TERMINATED'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getThirdName()}">${item.getInfo().getThirdName()}</abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="getSettlePrice">${item.getInfo().getSettlePrice()}</abbr><abbr class="outNum">${item.getAmount()}</abbr><abbr class="sumNumber">${item.getInfo().getSettlePrice() * item.getAmount()}</abbr>

                </dd>
            </#list>
        <#elseif inventorybill.billStatus.name() == 'WAIT_DELIVERY'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getThirdName()}">${item.getInfo().getThirdName()}</abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="getSettlePrice">${item.getInfo().getSettlePrice()}</abbr><abbr class="outNum">${item.getAmount()}</abbr><abbr class="sumNumber">${item.getInfo().getSettlePrice() * item.getAmount()}</abbr>
                </dd>
            </#list>
        <#elseif inventorybill.billStatus.name() == 'WAIT_RECEIVER'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getThirdName()}">${item.getInfo().getThirdName()}</abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="getSettlePrice">${item.getInfo().getSettlePrice()}</abbr><abbr class="outNum">${item.getAmount()}</abbr><abbr class="sumNumber">${item.getInfo().getSettlePrice() * item.getAmount()}</abbr><abbr class="checkInput" style="width: 100px"><input class="thisCheck" type="text" value=""><input class="thisGoodsInfoId" type="hidden" value="${item.getGoodsInfoId()}"> </abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="position: relative;margin-left: -6px; width: 130px"><input type="text" class="start-time datetimepicker_start" placeholder="生产时间" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_start"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 130px"><input type="text" class="end-time datetimepicker_end" placeholder="到期时间" id="" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_end"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" value="" class="warning-time" onkeyup="onlyNum(this)" /></abbr>
                    </#if>
                </dd>
            </#list>
        <#elseif inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'>
            <#list inventorybill.items as item>
            <#if item.getReceiptAmount() != item.getAmount()>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getThirdName()}">${item.getInfo().getThirdName()}</abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="getSettlePrice">${item.getInfo().getSettlePrice()}</abbr><abbr class="outNum">${item.getAmount()}</abbr><abbr class="receiptAmount">${item.getReceiptAmount()}</abbr><abbr class="checkInput" style="width: 100px"><input class="thisCheck" type="text" value=""><input class="thisGoodsInfoId" type="hidden" value="${item.getGoodsInfoId()}"> </abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="position: relative;margin-left: -6px; width: 130px"><input type="text" class="start-time datetimepicker_start" placeholder="生产时间" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_start"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 130px"><input type="text" class="end-time datetimepicker_end" placeholder="到期时间" id="" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_end"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" value="" class="warning-time" onkeyup="onlyNum(this)" /></abbr>
                    </#if>
                </dd>
            </#if>
            </#list>
        <#else>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="outNum">${item.getAmount()}</abbr>

                </dd>
            </#list>
        </#if>

    </dl>


<#if inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' || inventorybill.billStatus.name()=='FINISHED' >
    <div style="margin-top: 30px;margin-left:auto;margin-right:auto;font-size: 24px;font-family: '黑体'">已收货清单</div>
    <dl class="jsLogBill" style="margin-top: 30px; margin-bottom: 20px">
    <dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>金额(元)</abbr><abbr>已收货数量</abbr><abbr style="width: 112px">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>    </dt>
        <#--<dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>金额(元)</abbr><abbr>已收货数量</abbr>    </dt>-->
        <#if inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' || inventorybill.billStatus.name()=='FINISHED'>
            <#assign x = 0>
            <#list inventorybill.items as item>
                <#list item.warnDay as data>
                    <#if data.currentAmount??>
                        <#assign x=x+1>
                        <dd data-id="${item.getItemId()}" class="">
                            <abbr><b>${x}</b></abbr>
                            <abbr style="margin-left: -6px" title="${data.thirdName}">${data.thirdName}</abbr>
                            <abbr style="margin-left: -6px" title="${data.goodINfoName}">${data.goodINfoName}</abbr>
                            <abbr style="margin-left: -6px">${data.remark}</abbr>
                            <abbr style="margin-left: -6px">${data.goodInfoItemNo}</abbr>
                            <abbr style="margin-left: -6px" class="getSettlePrice">${item.settlePrice}</abbr>
                            <abbr style="margin-left: -6px" class="receiptAmount"> ${item.amount}</abbr>
                            <abbr style="margin-left: -6px">${item.getInfo().getSettlePrice() * item.getAmount()}</abbr>
                            <abbr style="margin-left: -6px" class="outNum">${data.currentAmount}</abbr>
                            <abbr style="position: relative;margin-left: -6px; width: 112px">
                                <#if data.productTime??>
                                ${(data.productTime?string("yyyy/MM/dd"))}
                            </#if>
                            </abbr>
                            <abbr style="position: relative;margin-left: -6px; width: 112px">
                                <#if data.endTime??>
                                ${(data.endTime?string("yyyy/MM/dd"))}
                            </#if>
                            </abbr>
                            <#if data.endWarnDay != '0'>
                                <abbr style="position: relative;margin-left: -6px; width: 112px">${(data.endWarnDay)}</abbr>
                            <#else >
                                <abbr style="position: relative;margin-left: -6px; width: 112px"></abbr>
                            </#if>
                        </dd>
                    </#if>
                </#list>
            </#list>
        </#if>
    </dl>
</#if>
<#if inventorybill.billStatus.name() == 'TERMINATED'&& inventorybill.billType.name() == 'INVENTORY_TRANSFER' >
    <div style="margin-top: 30px;margin-left:150px;margin-right:auto;font-size: 24px;font-family: '黑体'">已收货清单</div>
    <dl class="jsLogBill" style="margin-top: 30px; margin-bottom: 20px; margin-left:150px">
        <dt><abbr>序号</abbr><abbr>供应商名称</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>结算单价(元)</abbr><abbr>*调拨数量</abbr><abbr>金额(元)</abbr><abbr>已收货数量</abbr><abbr style="width: 112px">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>    </dt>
        <#if inventorybill.billStatus.name() == 'TERMINATED'&& inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
            <#assign x = 0>
            <#list inventorybill.items as item>
                <#list item.warnDay as data>
                    <#if data.currentAmount??>
                        <#assign x=x+1>
                        <dd data-id="${item.getItemId()}" class="">
                            <abbr><b>${x}</b></abbr>
                            <abbr style="margin-left: -6px" title="${data.thirdName}">${data.thirdName}</abbr>
                            <abbr style="margin-left: -6px" title="${data.goodINfoName}">${data.goodINfoName}</abbr>
                            <abbr style="margin-left: -6px">${data.remark}</abbr>
                            <abbr style="margin-left: -6px">${data.goodInfoItemNo}</abbr>
                            <abbr style="margin-left: -6px" class="getSettlePrice">${item.settlePrice}</abbr>
                            <abbr style="margin-left: -6px" class="receiptAmount"> ${item.amount}</abbr>
                            <abbr style="margin-left: -6px">${item.getInfo().getSettlePrice() * item.getAmount()}</abbr>
                            <abbr style="margin-left: -6px" class="">${data.currentAmount}</abbr>
                            <abbr style="position: relative;margin-left: -6px; width: 112px">
                                <#if data.productTime??>
                                ${(data.productTime?string("yyyy/MM/dd"))}
                            </#if>
                            </abbr>
                            <abbr style="position: relative;margin-left: -6px; width: 112px">
                                <#if data.endTime??>
                                ${(data.endTime?string("yyyy/MM/dd"))}
                            </#if>
                            </abbr>
                            <#if data.endWarnDay != '0'>
                                <abbr style="position: relative;margin-left: -6px; width: 112px">${(data.endWarnDay)}</abbr>
                            <#else >
                                <abbr style="position: relative;margin-left: -6px; width: 112px"></abbr>
                            </#if>
                        </dd>
                    </#if>
                </#list>
            </#list>
        </#if>
    </dl>
</#if>

</div>


<div id="addIvent" class="allpop">
    <h1>
      选择货品 <i id="xx"></i>
    </h1>
    <ul>
        <li>
            <abbr><input class="allinputButton" placeholder="货品名称" type="text" name="item_name"/></abbr>
            <abbr><input class="allinputButton" placeholder="货品编号" type="text" name="item_no"/></abbr>
            <abbr style="border: none; width: 300px">
                <input type="button" class="topButton allseachButton" value="搜索" id="searchGoods"/>
                <input type="button" class="topButton allseachButton" value="确定" id="commitGoods"/>
            </abbr>
        </li>
        <li>
            <dl>
                <dt><abbr><input type="checkbox" name="addinventcheckDT"> </abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>品牌</abbr><abbr>所属商家</abbr>
                </dt>
                <div id="itemContainer"></div>
                <div class="allcpageTurnButton" id="holder"></div>
                <div id="count"></div>
            </dl>
        </li>
    </ul>
</div>
<#if status == 'nothandled'>
    <#if  inventorybill.billStatus.name() == 'WAIT_DELIVERY' || inventorybill.billStatus.name() == 'WAIT_RECEIVER' || inventorybill.billStatus.name() == 'WAIT_EDIT'>
    <div class="suggest">
        <textarea rows="10" cols="185" placeholder="流转意见"></textarea>
    </div>
    </#if>
</#if>

<#--<#if status == 'done'>-->
    <#--<#if isTop && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'&& inventorybill.billType.name() == 'INVENTORY_TRANSFER'>-->
<#if status == 'done' && isTop && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
    <div class="suggest">
        <textarea rows="10" cols="185" placeholder="流转意见"></textarea>
    </div>
</#if>
<#if status == 'nothandled' && isTop && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
<div class="suggest">
    <textarea rows="10" cols="185" placeholder="流转意见"></textarea>
</div>
</#if>
<#--<#if status == 'myrequest'>-->
    <#--<#if !isTop && !isEnd && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>-->
<#if status == 'myrequest' && !isTop && !isEnd && inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' && inventorybill.billType.name() == 'INVENTORY_TRANSFER'>
        <div class="suggest">
            <textarea rows="10" cols="185" placeholder="流转意见"></textarea>
        </div>
</#if>


<#if inventorybill != ''>
<div class="circulationSuggest">
    <h1>流转意见</h1>
    <ul>
        <#list inventoryBillLog as object>
            <li>
                <dl>
                    <dd>${object.enterpriseName}</dd>
                    <#if object.getLogAction()=="[创建]">
                        <dd></dd>
                        <#else>
                            <#if object.getLogMsg()=="">
                                <dd></dd>
                            <#else>
                                <dd>审批意见：${object.getLogMsg()}</dd>
                            </#if>
                    </#if>
                    <dd>
                        <span>${object.getLogAction()}</span>
                        <br/>
                        <span>${object.getCreateTime()?string("yyyy-MM-dd HH:mm:ss")}</span>
                    </dd>
                </dl>
            </li>
        </#list>
    </ul>
</div>
</#if>

<div class="importLog2 allpop">
    <h1>错误<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul style="height:80%;"></ul>
</div>

<div class="chooeseDepot">
    <div>选择仓库<i></i></div>
    <div class="DepotDetail">
        <ul>
            <li>
                <a data-id ="${enterprise.enterpriseId}">+</a>
                <input type="radio" name="chooeseDepotOut"/>
                <span>${enterprise.enterpriseName}</span>
            </li>
        </ul>
    </div>
    <div><input type="button" value="取消" id="chooseCancel"/> <input type="button" value="确定" id="chooseConfirm"/></div>

</div>

<div class="chooeseDepotOut">
    <div>选择仓库<i></i></div>
    <div class="DepotDetail">
        <ul>
            <li>
                <a class="chooseAvailable" data-id ="${enterpriseInfo.enterpriseId}">+</a>
                <span>${enterpriseInfo.enterpriseName}</span>
            </li>
        </ul>
    </div>
    <div><input type="button" value="取消" id="chooseCancelOut"/> <input type="button" value="确定" id="chooseConfirmOut"/></div>

</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        var billId=${inventorybill.billId}
        $("#outExcel").click(function(){
            window.location.href = '../web/api/exportExcel/exportBillByBillId?billId=' + billId;
        });
        var abbrLength = $(".clearWidth dt").find("abbr");
        var allAbbr = $(".clearWidth").find("abbr");
        if(abbrLength.length == 6){
            allAbbr.each(function(){
                $(this).css("width","176px");
            })
        }
        var creat = "${inventorybill}"
        if(creat = "") {
            var now = new Date();
            var innerHTML = "";
            innerHTML = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
            $("#time").html(innerHTML)
        }
        $(document).on("click","#addIvent ul li dt input[name='addinventcheckDT']",function(){
            if( $(this).is(":checked") ){
                $("#itemContainer").find("input[type='checkbox']").each(function(){
                    var flag = $(this).is(":checked");
                    if( flag ){

                    }
                    else{
                        $(this).trigger("click");
                    }
                });
            }
            else{
                $("#itemContainer").find("input[type='checkbox']").each(function(){
                    var flag = $(this).is(":checked");
                    if( flag ){
                        $(this).trigger("click");
                    }
                    else{

                    }
                });
            }

        });

        //日历控件
        $(document).on('click', '.date_start', function (e) {
            $(this).prev().datetimepicker({
                step: 5,
                lang: 'ch',
                format: 'Y/m/d',
                timepicker: false
            });
            $(this).prev().trigger("focus");
        })
        $(document).on('blur', '.repleSend', function () {
            $(this).datetimepicker('destroy');
        });

        $(document).on('click', '.date_end', function (e) {
            $(this).prev().datetimepicker({
                step: 5,
                lang: 'ch',
                format: 'Y/m/d',
                timepicker: false
            });
            $(this).prev().trigger("focus");
        })
        $(document).on('blur', '.datetimepicker_end', function () {
            $(this).datetimepicker('destroy');
        });

        //当填了生产日期和到期日期，自动填写保质期
        $(document).on('change', '.end-time', function () {
            var stime = $(this).parent().prev().children('.start-time').val();
            var etime = $(this).val();
            var wtime = $(this).parent().next().children('.warning-time');
            console.log(454545);
            console.log(stime);
            console.log(DateDiff(stime, etime) + 1);
            if (stime != '' && etime != '') {

                wtime.val(DateDiff(stime, etime) + 1);
            }else if(etime!='' && wtime.val()!='' && stime==''){
                $(this).parent().prev().children('.start-time').val((getNewDay(etime, Number(wtime.val()) - 1,'start')));
            }
        })
        //当填写了到期日期和保质期，自动填写生产日期
        $(document).on('change', '.start-time', function () {
            var etime = $(this).parent().next().children('.end-time').val();
            var stime = $(this).val();
            var wtime = $(this).parent().next().next().children('.warning-time');
            if (stime != '' && etime != '') {
                wtime.val(DateDiff(stime, etime) + 1);
            }else if(stime !='' && wtime.val()!='' && etime==''){
                $(this).parent().next().children('.end-time').val((getNewDay(stime, Number(wtime.val()) - 1)));
            }
        })


        $(document).on('change', '.warning-time', function () {
            var etime = $(this).parent().prev().children('.end-time');
            var wtime = $(this);
            var stime = $(this).parent().prev().prev().children('.start-time');
            if(wtime.val() != '' && etime.val() =='' && stime.val() != ''){
                etime.val(getNewDay(stime.val(), Number(wtime.val()) - 1));
            }else if(wtime.val() != '' && stime.val() ==''&& etime.val() !=''){
                stime.val(getNewDay(etime.val(), Number(wtime.val()) - 1,'start'));
            }
        })
        //计算两天只差
        function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006/12/18格式
            var  aDate,  oDate1,  oDate2,  iDays
            aDate  =  sDate1.split("/")
            oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
            aDate  =  sDate2.split("/")
            oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
            iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
            return  iDays
        }
        //将数据格式转换为yyyy-mm-dd 00:00:00
        function mydate(str)
        {
            var n = str.replace(/\//g,'-');
            n += ' 00:00:00';
            return n;
        }
        //日期加上天数得到新的日期
        //dateTemp 需要参加计算的日期，days要添加的天数，返回新的日期，日期格式：YYYY-MM-DD
        function getNewDay(dateTemp, days,type) {
            var dateTemp = dateTemp.split("/");
            var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式
            var millSeconds;
            if(type == 'start'){
                millSeconds = Math.abs(nDate) - (days * 24 * 60 * 60 * 1000);
            }else {
                millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
            }
            var rDate = new Date(millSeconds);
            var year = rDate.getFullYear();
            var month = rDate.getMonth() + 1;
            if (month < 10) month = "0" + month;
            var date = rDate.getDate();
            if (date < 10) date = "0" + date;
            return (year + "/" + month + "/" + date);
        }
        //将时间格式yyyy/mm/dd转换为yyyy-mm-dd 00:00:00
        function mydate(str)
        {
            var n = str.replace(/\//g,'-');
            n += ' 00:00:00';
            return n;
        }
        //将时间格式yyyy/mm/dd转换为yyyy-mm-dd
        function mydate2(str)
        {
            var n = str.replace(/\//g,'-');
            return n;
        }
    });
</script>
<#if isOld>
<script type="text/javascript">
    var billId=${billId}
    $.post("../web/api/inventory/getCreatBillDataFromReplementBill",{
        billId:billId
    },function(data){
        if(data.response == 'success'){
            var html =""
            var html2 =""
            var length = 0
            data.data.items.map(function(o){
                length++;
                html += '<dd data-id="' + o.goodsInfoId  + '">';
                html += '<abbr style="width: 176px"><input type="checkbox" name="invetcheck"/><b>' + length + '</b></abbr>';
                html += '<abbr style="width: 176px">' + o.info.goodsInfoName + '</abbr>';
                html += '<abbr style="width: 176px">' + o.info.specString + '</abbr>';
                html += '<abbr style="width: 176px">' + o.info.goodsInfoItemNo + '</abbr>';
                if(data.data.outId == "1"){
                    html += '<abbr style="width: 176px">' + o.info.available + '</abbr>';
                }else{
                    html += '<abbr style="width: 176px" class="available">' + "请选择调出仓" + '</abbr>';
                }
                html += '<abbr style="width: 176px"><input type="text" value="'+ o.checkedAmount +'"> </abbr>';
                html += '</dd>';
            });
            $(".bodySecond dl dd").remove();
            $(".bodySecond dl").append(html);
            data.data.items.map(function(o){
                html2 += '<dd data-checkedAmount="'+ o.checkedAmount +'">';
                html2 += '<abbr>' + '<input name="addinventcheck" type="checkbox" value="'+ o.goodsInfoId +'">' + '</abbr>'
                html2 += '<abbr>' + o.info.goodsInfoName + '</abbr>';
                html2 += '<abbr class="invenType">' + o.info.specString + '</abbr>';
                html2 += '<abbr>' + o.info.goodsInfoItemNo + '</abbr>';
                html2 += '<abbr>' + o.info.thirdName + '</abbr>';
                html2 += '<abbr class="oldAvailable" data-available="'+ o.info.available +'"></abbr>';
                html2 += '</dd>';
            });
            $("#count").append(html2)
            if(data.data.outId == "1"){
                $("#choosenIntOut").data("id",1)
                $("#choosenIntOut").html(data.data.tansferInfo.outName)
                $("#choosenIntOut").unbind("click")
            }
            $("#choosenInt").attr("data-id",data.data.inId)
            $("#choosenInt").html(data.data.tansferInfo.inName)
            $("#choosenInt").unbind("click")
        }else{
            data_type_alert( data.data.text ,"error")
        }
    },'json');

</script>
</#if>