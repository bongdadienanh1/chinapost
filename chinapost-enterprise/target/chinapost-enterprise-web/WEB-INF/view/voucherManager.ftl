<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/voucherManager.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
            .voucherTable{
                transform: scale(0.8,0.8);
                transform-origin: left top;
                -webkit-transform: scale(0.8,0.8);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.8,0.8);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.8,0.8);
                -moz-transform-origin: left top;
            }
    }
    .billButton{
        width:1400px;
        height:130px;
        margin-left:20px;
    }
    .billButton li{
        float:left;
        margin-left:10px;
        margin-top:20px;
        color: #999;
    }

    .billButton li button{
        width:60px;
        height:35px;
        border:1px solid #CCC;
        background:#f4f4f4;
        margin-right:10px;
        color: #999;
        cursor: pointer;
    }

    .billButton li input{
        width:215px;
        height:26px;
        font-size:16px;
    }
    .billButton li input:hover{
    }
    .billButton li:nth-child(6){

        width:217px;
    }
    .billButton li:nth-child(7){

        width:215px;
    }
    .billButton li:nth-child(2){
            display: none;
            width: 215px;;
    }
    /*.billButtonul li:nth-child(2){*/
        /*width:215px;*/
    /*}*/
    /*.billButtonul li:nth-child(5){*/
        /*width: 400px;*/
        /*float: left;*/
    /*}*/
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        margin-left: 0!important;
        height:24px;
        background-image: url("${bath}/static/img/date_img.png");
        position: relative;
        left: -45px;
    }
    .xdsoft_datetimepicker{
        width:310px!important;
    }
    #voucherSearch{
        width:90px;
        height:26px;
        margin-left:20px;
        color:#FFF;
        border:1px solid transparent;
    }

    .voucherTable{
        width:1600px;
        height:800px;
        margin-left:20px;
    }
    .voucherTable ul{
        width:1600px;
        height:35px;
    }
    .voucherTable ul li{
        width:85px;
        height:33px;
        text-align:center;
        line-height:33px;
        margin-left:20px;
        float:left;
        cursor:pointer;
        border-bottom: 2px solid transparent;
    }
    .voucherTable ul li.reqOn{
        color:#54a6de;
        border-bottom: 2px solid #54a6de;
    }

    .myVoucherTodo{
        width:1600px;
        height:700px;
        margin-left:20px;
    }
    .myVoucherTodo dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myVoucherTodo dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }

    #mergeReplenishment{
        width: 90px;
        margin-left: 20px;
    }
    .myVoucherTodo dt abbr{
        display:inline-block;
        width:220px;
        height:40px;
        text-align:left;
        color:#666666;
        line-height:40px;
        margin-left: 5px;
    }
    .myVoucherTodo dd abbr{
        display:inline-block;
        width:220px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
        margin-left: 5px;
    }

    .myVoucherDone {
        width:1600px;
        height:700px;
        margin-left:20px;
        display: none;
    }
    .myVoucherDone dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myVoucherDone dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }


    .myVoucherDone dt abbr{
        display:inline-block;
        width:220px;
        height:40px;
        text-align:left;
        color:#666666;
        line-height:40px;
        margin-left: 5px;
    }
    .myVoucherDone dd abbr{
        display:inline-block;
        width:220px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
        margin-left: 5px;
    }

    .myVoucherRequest{
        width:1600px;
        height:700px;
        margin-left:20px;
        display: none;
    }
    .myVoucherRequest dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myVoucherRequest dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }


    .myVoucherRequest dt abbr{
        display:inline-block;
        width:220px;
        height:40px;
        text-align:left;
        color:#666666;
        margin-left: 5px;
        line-height:40px;
    }
    .myVoucherRequest dd abbr{
        display:inline-block;
        width:220px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
        margin-left: 5px;
    }
    .vocherCode{
        color: #54a6de!important;
        cursor: pointer!important;
    }

    .myPurchaseOrder{
        width:1600px;
        height:700px;
        margin-left:20px;
        display: none;
    }
    .myPurchaseOrder dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myPurchaseOrder dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }


    .myPurchaseOrder dt abbr{
        display:inline-block;
        width:220px;
        height:40px;
        text-align:left;
        color:#666666;
        line-height:40px;
        margin-left: 5px;
    }
    .myPurchaseOrder dd abbr{
        display:inline-block;
        width:220px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
        margin-left: 5px;
    }

    .billButton li dl{
        display: inline-block;
        vertical-align: middle;
        margin-top: 2px;
        width:120px;
        height:20px;
        margin-left:-5px;
        font-size: 12px;
        text-align: center;
        position: relative;
        z-index: 1;
    }
    .arrow{
        display: inline-block;
        position: absolute;
        right: -10px;
        top: -8px;
        width: 36px;
        height: 35px;
        background:url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat;
    }
    .billButton li dl dd,.billButton li dl dt{
        width:120px;
        height:20px;
        background:#ffffff;
        margin-top: -1px;
        margin-left:10px;
        cursor: pointer;
        line-height: 20px;
    }
    .billButton li dl dd{
        display: none;
    }
    .allSelectdata:hover {
        background: #2c97de;
        color: #ffffff;
    }
    /*#itemBrand:not(:first-child) dd:hover{*/
        /*background: #2c97de;*/
        /*color: #ffffff;*/
    /*}*/
    /*#itemBrand dd {*/
        /*border: 1px solid #e5e5e5;*/
        /*cursor: pointer;*/
    /*}*/
    /*#itemBrand{*/
        /*width:160px;*/
        /*min-height: 21px;*/
        /*max-height: 300px;*/
        /*overflow-y: scroll;*/
        /*overflow-x: hidden;*/
        /*background: #fff;*/
        /*z-index: 1;*/
        /*position: absolute;*/
        /*top: 3px;*/
        /*left: 110px;*/
    /*}*/
    /*#itemBrand dt, #itemBrand dd {*/
        /*height: 20px;*/
        /*line-height: 20px;*/
        /*margin-top: -1px;*/
        /*padding-left: 20px;*/
        /*background: #fff;*/
        /*overflow: hidden;*/
    /*}*/
    #itemBrand{
        width:160px;
        min-height: 21px;
        max-height: 300px;
        overflow-y: scroll;
        overflow-x: hidden;
        background: #fff;
        z-index: 1;
        position: absolute;
        top: 3px;
        left: 110px;
    }
    #itemBrand dt, #itemBrand dd{
        height: 20px;
        line-height: 20px;
        margin-top: -1px;
        padding-left: 20px;
        background: #fff;
        overflow: hidden;
    }
    #itemBrand dd{
        border: 1px solid #e5e5e5;
        cursor: pointer;
    }
    #itemBrand dt:hover,#itemBrand dd:not(#brandSearch):hover{
        color: #fff;
        background: #54a6de;
    }
    #itemBrand dd{
        display: none;
    }
</style>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    sessionStorage.setItem("ShoppingCart",'');
    enterSearch( $("#voucherOrder,#voucherName"),$("#voucherSearch"))
</script>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
    <#if isEnd == true>
        <a href="InventoryManager">仓库管理</a>
    </#if>
    <#if isTop == true>
        <a href="InventoryManager">仓库管理</a>
    </#if>
    <#if isEnd != true>
        <a href="inventoryInquiry">库存查询</a>
    </#if>

        <span>单据管理</span>

        <a class="leftshanow" href="maturityWarning">到期预警</a>
        <abbr></abbr>
    </div>

<div class="billButton">
    <input type="hidden" value="${isTop}" id="isTop"/>
    <input type="hidden" value="${isEnd}" id="isEnd"/>
    <ul>
        <li><input class="allinputButton" placeholder="单据编号" type="text" id="voucherOrder"/></li>
        <li class="Status allinputButton" id="billsta2"><span class="groupName" style="line-height: 28px;font-size: 16px">单据状态</span>
            <dl class="select"> <i value="0" class="arrow arrowStatus"></i>
                    <dt data-typestr="" id="status2"><input id="statusInput2" type="hidden" value=""/><abbr>全部</abbr></dt>
                    <dd class="now All allSelectButton">全部</dd>
                    <dd data-typestr="TO_CONFIRM" class="now billStatus allSelectButton">待确认</dd>
                    <dd data-typestr="TERMINATED" class="now billStatus allSelectButton">已终止</dd>
                    <dd data-typestr="FINISHED" class="now billStatus allSelectButton">已确认</dd>
                    <dd data-typestr="COMMODITY_STORAGE" class="now billStatus allSelectButton">待商品入库</dd>
                    <dd data-typestr="FOR_SETTLEMENT" class="now billStatus allSelectButton">待结算</dd>
                    <dd data-typestr="COMPLETED" class="now billStatus allSelectButton">已完成</dd>
            </dl>
        </li>

        <li id="createN"><input class="allinputButton" placeholder="创建人"  type="text" id="voucherName"/></li>
        <li>
            <input class="allinputButton" placeholder="开始时间"  type="text" style="width:257px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
        </li>
        <li>
            <input class="allinputButton" placeholder="结束时间"  type="text" style="width:257px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
        </li>
        <li class="Type allinputButton"><span class="groupName" style="line-height: 28px;font-size: 16px;">单据类型</span>
            <dl class="select"> <i value="0" class="arrow arrowType"></i>
                <dt data-typesta-typestr="" id="type"><input id="typeInput" type="hidden" value=""/><abbr>全部</abbr></dt>
                <dd class="allSelectButton" >全部</dd>
                <#list billType as object>
                    <dd class="allSelectdata" data-typesta-typestr="${object.name()}">${object.getName()}单</dd>
                </#list>
            </dl>
        </li>
        <li class="Status allinputButton" id="billsta1"><span class="groupName" style="font-size: 16px;line-height: 28px">单据状态</span>
            <dl class="select"> <i value="0" class="arrow arrowStatus"></i>
                <dt data-typestr="" id="status"><input id="statusInput" type="hidden" value=""/><abbr>全部</abbr></dt>
                <dd class="now All allSelectButton">全部</dd>
                <#list billStatus as object>
                    <dd data-typestr="${object.name()}" class="now billStatus allSelectButton">${object.getName()}</dd>
                </#list>
                <dd data-typestr="PARTIAL_RECEIPT2" class="now billStatus allSelectButton">部分入库</dd>
            </dl>
        </li>
        <#--<li class="Supplier allinputButton" id="supplier" style="display: none;"><span class="groupName">供应商</span>-->
            <#--<dl class="select"> <i value="0" class="arrow arrowSupplier"></i>-->
                <#--<dt data-supplier="" id=""><input id="supplierInput" type="hidden" value=""/><abbr>全部</abbr></dt>-->

                <#--<dd data-supplier="" class="now2 billSupplier allSelectButton">供应商1</dd>-->
                <#--<dd data-supplier="" class="now2 billSupplier allSelectButton">供应商2</dd>-->
            <#--</dl>-->
        <#--</li>-->
        <li style="width: 295px;display: none" id="supplier">
            <div style="margin-left;20px;text-align: left!important; position: relative;margin-top: 0px;margin-left: -10px;font-size: 16px;line-height: 28px " class="allinputButton"><span style="display:inline-block;margin-left: 40px;color: #999">供应商</span>
                <dl id="itemBrand" style="height: auto">
                    <dt data-id="0"id="brandAppend">请选择</dt>
                    <dd data-id="">请选择</dd>
                    <dd id="brandSearch"><input style="width: 100px!important;height: 18px!important;font-size: 12px" class="allinputButton" placeholder="输入文字搜索" type="text"> </dd>
                </dl>
            </div>
        </li>

        <li> <input class="allseachButton" type="button" id="voucherSearch" value="搜索" />
            <#if isTop == true>
                <a href="megeReplishment"><input class="allseachButton" type="button" id="mergeReplenishment" value="合并补货单" /></a>
            </#if>
            <input style="width: 100px;display: none;" class="allseachButton" type="button" id="voucherSearchByCondition" value="按条件导出" />
        </li>

    </ul>

</div>


<div class="voucherTable">
    <ul>
        <li value="0" class="reqOn">待办事宜</li>
        <li value="1">已办事宜</li>
        <li value="2">我的请求</li>
        <#if isTop == true><li value="3">采购订单</li></#if>
    </ul>
    <dl class="myVoucherTodo">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>申请账号</abbr><abbr>创建日期</abbr><abbr>单据状态</abbr><abbr>当前操作者</abbr><abbr>申请原因</abbr>
        </dt>
        <div id="itemContainer_notHandled"></div>
        <div class="allcpageTurnButton" id="holder_notHandled"></div>

    </dl>
    <dl class="myVoucherDone">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>申请账号</abbr><abbr>创建日期</abbr><abbr>单据状态</abbr><abbr>当前操作者</abbr><abbr>申请原因</abbr>
        </dt>
        <div id="itemContainer_done"></div>
        <div class="allcpageTurnButton" id="holder_done"></div>
    </dl>



    <dl class="myVoucherRequest">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>申请账号</abbr><abbr>创建日期</abbr><abbr>单据状态</abbr><abbr>当前操作者</abbr><abbr>申请原因</abbr>
        </dt>

        <div id="itemContainer_myRequest"></div>
        <div class="allcpageTurnButton" id="holder_myRequest"></div>
    </dl>

    <dl class="myPurchaseOrder">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>供应商</abbr><abbr>单据状态</abbr><abbr>申请日期</abbr><abbr>备注</abbr>
        </dt>

        <div id="itemContainer_myPurchaseOrder"></div>
        <div class="allcpageTurnButton" id="holder_purchase"></div>
    </dl>

</div>
</div>
</body>