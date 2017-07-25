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
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>入库单</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1360px){

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
    }
    .bodyFirst ul li span{
        display: inline-block;
        height: 100%;
        width: 30%;
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
    }
    .bodyFirst ul li abbr select{
        width: 90%;
        height: 80%;
    }
    .bodyFirst ul li abbr input{
        width: 100%;
        height: 80%;
    }
    .bodySecond{
        width: 1267px;
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
        overflow: hidden;
        margin: auto;
        border-left: 1px solid #54a6de;
        border-top: 1px solid #54a6de;
    }
    .bodySecond dt{
        background: #edf3f8;
    }
    .bodySecond dt,.bodySecond dd{
        width: 100%;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #54a6de;

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
    .bodySecond abbr{
        display: inline-block;
        vertical-align: middle;
        width: 178.5px;
        height: 50px;
        text-align: center;
        border-right: 1px solid #54a6de;
        padding: 0px 1.8px;
        overflow: hidden;
    }

    .bodySecond abbr input[type="text"]{
        width: 80%;
        height: 80%;
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
        width: 220px;
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
        width: 200px;
        height: 90%;
    }
    #addIvent li:first-child abbr input[type='button']{
        width: 80px;
        height: 50px;
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
        border-bottom: 1px solid #bbbbbb;
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
    #count dd{
        display: none;
    }
    #holder{
        margin: 30px 0px;
    }

    #count{
        display: none;
    }
</style>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    sessionStorage.setItem("ShoppingCart",'');
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
</script>

<body>
<div class="reTop">
    <ul>
        <li style="float: left;margin-left: 1%; color: #54a6de;font-weight: bold">
            <span style="margin-right: 10px;">入库单</span>-<span style="margin-left: 10px;">
        已完成
        </span>
        </li>

    </ul>
</div>

<div class="bodyFirst">
    <h1 style=" width: 200px;margin: auto;margin-top: 50px;margin-bottom: 50px; font-size: 28px; font-weight: border;">自购商品入库</h1>
    <ul>
        <li><span>单据编号</span><abbr>${inventorybill.code}</abbr></li>
        <li><span>申请账号</span><abbr>${enterpriseInfo.enterpriseName}</abbr></li>
        <li><span>申请日期</span><abbr id="time">${inventorybill.getCreateTime()?string("yyyy-MM-dd")}</abbr></li>
        <li><span>单据状态</span><abbr>已完成</abbr></li>
    </ul>

</div>
<div class="bodySecond">
    <h1>货品清单
    </h1>
    <dl>
        <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>入库数量</abbr><abbr style="width: 112px">生产日期</abbr><abbr style="width: 112px">到期日期</abbr><abbr style="width: 112px">保质期</abbr>    </dt>

        <#assign x = 0>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><input type="hidden" class="goodsType" value="${item.getInfo().getGoodsInfoType()}"><b>${x}</b></abbr><abbr title="${item.getInfo().getGoodsInfoName()}">${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="outNum">${item.getAmount()}</abbr>

                        <abbr style="position: relative;margin-left: -5px; width: 112px">
                        <#if item.productTime??>
                            ${(item.productTime?string("yyyy-MM-dd"))}
                        </#if>
                        </abbr>
                        <abbr style="position: relative;margin-left: -5px; width: 112px">
                            <#if item.endTime??>
                            ${(item.endTime?string("yyyy-MM-dd"))}
                        </#if>
                        </abbr>
                        <abbr style="position: relative;margin-left: -5px; width: 112px">
                            <#if item.endWarnDay != '0'>
                                ${item.endWarnDay}
                            </#if>
                        </abbr>
                </dd></#list>
    </dl>
</div>

<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        var goodsType =""
        $(".goodsType").each(function(){
            goodsType = $(this).val()
        })
        if(goodsType == "0" ){
            $(".bodyFirst h1").html("集采商品入库")
        }
    })
</script>