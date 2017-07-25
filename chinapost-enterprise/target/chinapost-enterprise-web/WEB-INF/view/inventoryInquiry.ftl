    <#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/inventoryInquiry.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        .itemsButton li input.invenBut{
            width: 120px!important;
        }
        .seachTableDepot{
            transform: scale(0.7,0.7);
            transform-origin: left top;
            -webkit-transform: scale(0.7,0.7);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.7,0.7);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.7,0.7);
            -moz-transform-origin: left top;
        }
        .seachTableItem{
            transform: scale(0.9,0.9);
            transform-origin: left top;
            -webkit-transform: scale(0.9,0.9);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.9,0.9);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.9,0.9);
            -moz-transform-origin: left top;
        }
    }

    .itemsButton{
        width:1400px;
        height:140px;
        margin-left:20px;
    }
    .itemsButton li{
        width:240px;
        height:40px;
        float:left;
        margin-left:20px;
        margin-top:20px;
    }

    .itemsButton li input[type='text']{
        width:208px;
        background:#FFF;
    }

    .itemsButton li input.invenBut{
        width: 150px;
    }
    .seachTableDepot{
        margin-top: 20px;
        margin-left: 20px;
        display: none;
    }
    .insidetable td{
        width: 200px;
        margin-left: -1px;

    }
    .insidetable td:first-child{
        width: 300px;;
    }
    .insidetable{
        margin-left: -1px;
    }
    .insidetable2{
        border-collapse: collapse;
        margin-left: -1px;
    }
    .insidetable2 tbody tr:not(:last-child){
        border-bottom: 1px solid #e5e5e5;
    }
    .insidetable2 td{
        width: 200px;
        margin-left: -1px;
    }
    .insidetable2 td:first-child{
        width: 300px;;
    }
    .seachTableItem{
        margin-top: 20px;
        margin-left: 20px;
        display: none;
    }
    .seachTableItem tbody td,.seachTableItem th{
         border-bottom: 1px solid #e5e5e5;
        margin-left: -1px;
    }
    .seachItem th:first-child,.seachItem td:first-child{
        padding-left: 30px;
    }
    #invenSearchDepot{
        margin-left: 20px;
    }
</style>
<body>
<script type="text/javascript">
    var eid = ${enterpriseInfo.enterpriseId};
    var ename = "${enterpriseInfo.enterpriseName}";
    sessionStorage.setItem("ShoppingCart",'');
</script>

<body style="background: #edf3f8">
<div style="min-height:800px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
    <#if isEnd == true>
        <a href="InventoryManager">仓库管理</a>
    </#if>
    <#if isTop == true>
        <a href="InventoryManager">仓库管理</a>
    </#if>

    <#if isEnd != true>
        <span>库存查询</span>
    </#if>

        <a class="leftshanow" href="voucherManager">单据管理</a>



        <a class="leftshanow" href="maturityWarning">到期预警</a>
        <abbr></abbr>
    </div>

<div class="itemsButton">
    <ul>
        <li style="width: 356px;"><input class="allinputButton" placeholder="仓库" value="${enterpriseInfo.enterpriseName}" style="width: 280px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterpriseInfo.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose" style="background:url(${bath}/static/img/chooseinout.png) center no-repeat;  color:#fff;display:inline-block; vertical-align:middle; width: 58px; height:40px; line-height:40px; text-align:center;cursor: pointer;position: relative; left: -55px"></abbr></li>
        <li><input class="allinputButton" placeholder="货品编号"  type="text" name="itemManager_Number" /></li>
        <li><input class="allinputButton" placeholder="货品名称"  type="text" name="itemManager_Name" /></li>
        <li style="border: none; width: 1200px;">
            <input type="button" value="查询（按货品）" class="invenBut allseachButton" id="invenSearchItem" />
            <input type="button" class="invenBut allseachButton" value="查询（按仓库）" id="invenSearchDepot" />
            <input style="margin-left: 20px;" type="button" class="invenBut allclickButton" value="导出查询库存" id="invenOut" />
            <#if isEnd == true>
            <#elseif isTop == true>
            <#else>
                <input type="button" value="库存调拨" class="invenBut allclickButton" id="invenLocation" />
            </#if>
        </li>
    </ul>
</div>

<div class="seachTableDepot">
    <table style="border-collapse:collapse;table-layout:fixed;" width="100%" height="500px" border="0"  align="center" cellspacing="0" class="seachTableDepotContent">
        <thead align="center" style=" color: #666666;border-bottom: 1px solid #e5e5e5;">
        <tr style="height: 50px;       color: #333;">
            <td style="width: 120px;">仓库</td>
            <td>
                <table style="table-layout:fixed;" class="insidetable" width="100%" border="0"  align="center" cellspacing="0">
                    <tbody align="center">
                        <tr align="center">
                            <td>货品名称</td>
                            <td>货品规格</td>
                            <td>货品编号</td>
                            <td>商品类型</td>
                            <td>库存</td>
                            <td>可用库存</td>
                            <td>品牌</th>
                            <td>所属商家</td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        </thead>
        <tbody align="center" class="tbodyContent">

        </tbody>
    </table>
</div>



<div class="seachTableItem">
    <table class="seachItem" width="100%" align="left" border="0" cellspacing="0">
        <thead>
            <tr align="left" style="height: 50px; color: #666666;border-bottom: 1px solid #e5e5e5">
                <th>货品名称</th>
                <th>货品规格</th>
                <th>货品编号</th>
                <th>商品类型</th>
                <th>库存</th>
                <th>可用库存</th>
                <th>品牌</th>
                <th>所属商家</th>
            </tr>
        </thead>
        <tbody align="left" >

        </tbody>
    </table>
</div>


<div class="chooeseDepot allpop"></div>

<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>

<script type="text/javascript">
    $(document).ready(function(){
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;

        //仓库列表
        inventoryList(eid,ename);

        $(document).on("click","#invenLocation",function(){
              window.open("allocation");
        });

    })
</script>