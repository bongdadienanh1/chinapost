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
    <script type="text/javascript" src="${bath}/static/js/inStorage.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>入库单</title>
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
        width: 1290px;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul li{
        width: 300px;
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
        background-color: #fcfcfc;
    }
    .bodySecond{
        width: 1324px;
        height: auto;
        overflow: hidden;
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
        width: 100%;
        height: auto;
        /*overflow: hidden;*/
        margin-bottom: 10px;
        /*margin: auto;*/

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
        width:128.5px ;
    }
    .bodySecond dt abbr:nth-child(2){
        width:228.5px ;
    }
    .bodySecond dd abbr:first-child{
        width:128.5px ;
    }
    .bodySecond dd abbr:nth-child(2){
        width:228.5px ;
        overflow: hidden;
    }
    .bodySecond dd:last-child abbr{
        border-bottom: 1px solid #54a6de;
    }
    .bodySecond dt abbr{
        border-bottom: 1px solid #54a6de;
    }
    .bodySecond abbr{
        display: inline-block;
        vertical-align: middle;
        width: 178.5px;
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
    /*.bodySecond abbr input[type="text"]{*/
        /*border-radius: 10px;*/
        /*width: 100%;*/
        /*height: 43%;*/
        /*margin-top: 10%;*/
        /*!*margin-left: -30%;*!*/
    /*}*/
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
    .checkNum{
        width: 100px;
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
    .bodySecond abbr input[type="text"]{
        width: 96%;
        height: 56%;
        margin-top: 10px;
    }
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
        if(state == "部分收货"){
            $(".bodySecond h1 b").html("待入库清单")
        }else{
            $(".bodySecond h1 b").html("入库清单")
        }
            if( state == "" ){
                $(".invet").show();
                $(".reasonForApply").prop("readonly","");
                $(".reasonForApply").css("border","none");
                $(".bodySecond").css("width","1128px");
            }else if(state == "部分收货"){
                $(".bodySecond").css("width","1480px");
            }


    });

</script>

<body>
<input type="hidden" value="${status}" id="status">
<input type="hidden" value="${purchaseId}" id="billId2">
<div class="reTop">
    <input type="hidden" value="${enterpriseInfo.enterpriseId}" id="enterpriseId">
    <ul>
        <li style="float: left;margin-left: 1%; color: #54a6de;font-weight: bold">
            <span style="margin-right: 10px;">集采商品入库</span>-<span id="statue" style="margin-left: 10px;"><#if inventorybill == ''>创建<#else><#if inventorybill.billStatus.getName() == "部分收货">部分入库<#else>${inventorybill.billStatus.getName()}</#if></#if></span>
        </li>
        <li style="float: right;margin-right: 1%;">
        <#if status != 'done' && status != 'myrequest'>
            <#if inventorybill == ''>
                <div style="display: inline-block;" id="repleCreate"> <input type="button" class="topButton allclickButton" value="提交" id="commit"> </div>
            <#else>
                <#if inventorybill.billStatus.name() == 'STAY_IN' ||inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'>
                    <div style="display: inline-block;"> <input style="width: 120px;" type="button" id="repleSend" class="topButton allclickButton" value="确认入库"> </div>
                </#if>
            </#if>
        </#if>



        </li>
    </ul>
</div>

<div class="bodyFirst">
    <h1 style=" width: 200px;margin: auto;margin-top: 50px;margin-bottom: 50px; font-size: 28px; font-weight: border;">集采商品入库</h1>
    <ul>
        <#if inventorybill == ''>
            <li><span>单据编号</span><abbr>新入库单</abbr></li>
            <li><span>申请账号</span><abbr>${enterpriseInfo.enterpriseName}</abbr></li>
            <li><span>申请日期</span><abbr id="time">${.now?string("yyyy-MM-dd")}</abbr></li>
        <#else>
            <li><span>单据编号</span><abbr>${inventorybill.code}</abbr></li>
            <li><span>申请账号</span><abbr>${inventorybill.getTansferInfo().getCreatorName()}</abbr></li>
            <li><span>申请日期</span><abbr id="time">${inventorybill.getCreateTime()?string("yyyy-MM-dd")}</abbr></li>
        </#if>

        <#if inventorybill == ''>
            <#if isTop>
                <li><span>仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            <#elseif isEnd>
                <li><span>仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            <#else>
                <li><span>仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            </#if>
        <#else>
            <li><span>仓库</span><abbr class="choosenInt inventoryNumber" data-id="${inventorybill.getOutId()}" style="cursor:pointer;">${inventorybill.getTansferInfo().getInName()}</abbr></li>
        </#if>
        <#if inventorybill == ''>
            <li><span>当前状态</span><abbr>创建</abbr></li>
        <#else>
            <li><span>当前状态</span><abbr data-status="${inventorybill.billStatus.name()}"><#if inventorybill.billStatus.getName() == "部分收货">部分入库<#else>${inventorybill.billStatus.getName()}</#if></abbr></li>
        </#if>
        <#if inventorybill == ''>
            <li style="width: 935px;"><span>备注</span><abbr><input maxlength="20" style="border:none;" type="text" class="reasonForApply" value=""/> </abbr></li>
        <#else>
            <li style="width: 935px;"><span>备注</span><abbr><input maxlength="20" readonly="readonly" style="border:none;" type="text" class="reasonForApply" value="${inventorybill.reason}"/> </abbr></li>
        </#if>
        <#assign amout=0>
            <#list inventorybill.items as item>
                <#assign amout=amout+item.amount>
            </#list>
        <li>
            <span>入库总数</span>
            <abbr <#if inventorybill == ''>id="allamout"</#if>>
             ${amout}
            </abbr>
        </li>
        <#if inventorybill != ''>
            <li><span>已入库总数</span><abbr id="allready">&nbsp;</abbr></li>
        </#if>
    </ul>

</div>
<div class="bodySecond">
    <h1><b style="font-weight: normal;font-family: '黑体'">入库清单</b><span style="float: right;margin-right: 1%;">
    <#if inventorybill == ''>
        <input style="margin-right: 20px;" class="topButton allseachButton" id="addinvet" type="button" value="添加"/><input class="topButton delete allseachButton" type="button" value="删除"/>
        <#elseif inventorybill.billStatus.name() == 'WAIT_EDIT' && status != 'done'>
            <input style="margin-right: 20px;" class="topButton allseachButton" id="addinvet" type="button" value="添加"/><input class="topButton delete allseachButton" type="button" value="删除"/>
    </#if>
    </span></h1>
    <dl class="bodySecond2">
        <#if inventorybill == ''>
            <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr> </dt>
        <#else>
            <#if inventorybill.billStatus.name() == 'FINISHED'>
                <#--<dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr><abbr style="width: 112px">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr> </dt>-->
                <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr> </dt>
            <#elseif inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'>
                <#if status != 'done' && status != 'myrequest'>
                <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr><abbr>本次入库数量</abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="width: 112px;margin-left: -6px;">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>
                    </#if>
                </dt>
                <#else>
                    <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr><abbr>本次入库数量</abbr>  </dt>
                </#if>
            <#elseif inventorybill.billStatus.name() == 'STAY_IN'><#--待入库-->
                <#if status != 'done' && status != 'myrequest'>
                <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>本次入库数量</abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="width: 112px;margin-left: -6px;">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>
                    </#if>
                </dt>
                <#else>
                    <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>本次入库数量</abbr> </dt>
                </#if>
            <#else>
                <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="width: 112px;margin-left: -6px;">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>
                    </#if>
                </dt>
            </#if>
        </#if>
            <#assign x = 0>
        <#if  inventorybill.billStatus.name() == 'FINISHED'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
            <#--已经入库完成，根据后台数据信息陈列-->
                <dd data-id="${item.getInfo().getGoodsInfoId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr>${item.getPurchasePrice()}</abbr><abbr class="sendAmount">${item.getAmount()}</abbr><abbr class="receiptAmount"><#if item.getReceiptAmount() == "">0<#else>${item.getReceiptAmount()}</#if></abbr>

                </dd>
            </#list>
        <#elseif inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'>
            <#list inventorybill.items as item>
                <#if item.getReceiptAmount() != item.getAmount()>
                <#assign x=x+1>
                <#--部分入库-->
                <dd data-id="${item.getInfo().getGoodsInfoId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr>${item.getPurchasePrice()}</abbr><abbr class="sendAmount">${item.getAmount()}</abbr><abbr class="allreadyReceiptAmount"><#if item.getReceiptAmount() == "">0<#else>${item.getReceiptAmount()}</#if></abbr><abbr class="receiptAmount"><input style="margin-top: 10px" value="${item.getAmount() - item.getReceiptAmount()}" class="checkNum"> </abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="start-time datetimepicker_start" placeholder="生产时间" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_start"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="end-time datetimepicker_end" placeholder="到期时间" id="" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_end"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" value="" class="warning-time" onkeyup="onlyNum(this)" /></abbr>
                    </#if>
                </dd>
                </#if>
            </#list>
        <#elseif inventorybill.billStatus.name() == 'STAY_IN'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
            <#--待入库-->
                <dd data-id="${item.getInfo().getGoodsInfoId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr>${item.getPurchasePrice()}</abbr><abbr class="sendAmount">${item.getAmount()}</abbr><abbr class="receiptAmount"><input style="margin-top: 10px" value="${item.getAmount()}" class="checkNum"> </abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="start-time datetimepicker_start" placeholder="生产时间" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_start"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="end-time datetimepicker_end" placeholder="到期时间" id="" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_end"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="warning-time" onkeyup="onlyNum(this)" /></abbr>
                    </#if>
                </dd>
            </#list>
        <#else>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <#--待入库-->
                <dd data-id="${item.getInfo().getGoodsInfoId()}"><abbr><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr>${item.getPurchasePrice()}</abbr><abbr class="sendAmount">${item.getAmount()}</abbr><abbr class="receiptAmount"><input style="margin-top: 10px" value="${item.getAmount()}" class="checkNum"> </abbr>
                    <#if status != 'done' && status != 'myrequest'>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="start-time datetimepicker_start" placeholder="生产时间" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_start"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" class="end-time datetimepicker_end" placeholder="到期时间" id="" onkeyup="$(this).val('')"/><a href="javascript:void(0)"class="date_button date_end"/></a></abbr>
                        <abbr style="position: relative;margin-left: -6px; width: 112px"><input type="text" value="" class="warning-time" onkeyup="onlyNum(this)" /></abbr>
                    </#if>
                </dd>
            </#list>
        </#if>

    </dl>


    <#if inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' || inventorybill.billStatus.name()=='FINISHED'>
    <div style="margin-top: 30px;font-size: 24px;font-family: '黑体'">已入库清单</div>
    <dl style="margin-top: 30px;" class="jsDateWeith">
        <#--<dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr><abbr style="width: 112px">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr> </dt>-->
            <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库单价</abbr><abbr>入库数量</abbr><abbr>已入库数量</abbr><abbr style="width: 112px">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>  </dt>

            <#assign x = 0>
        <#if inventorybill.billStatus.name() == 'PARTIAL_RECEIPT' || inventorybill.billStatus.name()=='FINISHED'>
            <#assign x = 0>
        <#list inventorybill.items as item>
            <#assign PurchasePrice = item.purchasePrice>
            <#assign amount = item.amount>
            <#list item.warnDay as data>
            <#if data.currentAmount??>
                <#assign x=x+1>
                <dd class="allre">
                    <abbr><b>${x}</b></abbr>
                    <abbr style="margin-left: -6px" title="${data.goodINfoName}">${data.goodINfoName!''}</abbr>
                    <abbr style="margin-left: -6px">${data.remark!''}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr>
                    <abbr style="margin-left: -6px" class="getSettlePrice">${PurchasePrice!''}</abbr>
                    <abbr style="margin-left: -6px" class="sendAmount">${amount!''}</abbr>
                    <abbr style="margin-left: -6px" class="allreadyReceiptAmount">${data.currentAmount}</abbr>
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
    <#if  inventorybill.billStatus.name() == 'STAY_IN' || inventorybill.billStatus.name() == 'PARTIAL_RECEIPT'>
    <div class="suggest">
        <textarea rows="10" cols="185" placeholder="流转意见"></textarea>
    </div>
    </#if>
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
                        <span><#if object.getLogAction() == "[部分收货]">[部分入库]<#elseif object.getLogAction() == "[收货]">[入库]<#else>${object.getLogAction()}</#if></span>
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
        var abbrHead = $(".bodySecond2 dt").find('abbr');
        var jsDateWeith = $(".jsDateWeith dt").find('abbr');
        if(abbrHead.length>8){
            $('.bodySecond2').find('abbr').each(function(){
                $(this).css('width','127.5px');
            })
        }
        if(jsDateWeith.length>8){
            $('.jsDateWeith').find('abbr').each(function(){
                $(this).css('width','123.5px');
            });
        }
        var statue = $("#statue").html();
        if( statue == "待入库"){
            var Num = 0;
            $(".sendAmount").each(function(){
                Num = Num + parseInt($(this).html())
            })
            $("#allamout").html(Num)
            $("#allready").html(0)
        }else if(statue == "部分入库"){
            var Num = 0;
            $(".bodySecond2").find(".sendAmount").each(function(){
                Num = Num + parseInt($(this).html())
            })
            var rep = 0;
            if($(".jsDateWeith").find(".sendAmount").length == 0){
                $(".bodySecond2").find(".allreadyReceiptAmount").each(function(){
                    rep = rep + parseInt($(this).html())
                })
            }else {
                $(".jsDateWeith").find(".allreadyReceiptAmount").each(function(){
                    rep = rep + parseInt($(this).html())
                })
            }
            $("#allamout").html(Num)
            $("#allready").html(rep)
        }
        else if(statue == "已完成"){
            var Num = 0;
            $(".sendAmount").each(function(){
                Num = Num + parseInt($(this).html());
            });
            if($(".jsDateWeith").find(".sendAmount").length != 0){
                Num = Num/2;
            }
            var rep = 0;
            $(".receiptAmount").each(function(){
                rep = rep + parseInt($(this).html())
            })
            $("#allamout").html(Num)
            $("#allready").html(rep)
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

        $(document).on("blur",".checkedamount",function(){
            if($(".xcConfirm").length<=0){
                var val = $(this).val()
                if(/^[0-9]*[1-9][0-9]*$/.test(val)){
                    var num = 0
                    $(".checkedamount").each(function(){
                        var num1 = $(this).val()
                        if(num1 ==""){
                            num1 = 0
                        }
                        num = parseInt(num) + parseInt(num1)
                    })

                    $("#allamout").html(num)
                }else{
                    response_ensure_alert("error","请输入正整数")
                    $(this).val("")
                }
            }
        })
        $(document).on("blur",".settlePrice",function(){
            if($(".xcConfirm").length<=0){
                var val = $(this).val()
                var check = checkNumTofix(val)
                if(check){

                }else{
                    response_ensure_alert("error","请输入正数")
                    $(this).val("")
                }
            }
        })
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
            }else if(stime !='' && wtime.val()!=''&& etime==''){
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
</script>
</#if>