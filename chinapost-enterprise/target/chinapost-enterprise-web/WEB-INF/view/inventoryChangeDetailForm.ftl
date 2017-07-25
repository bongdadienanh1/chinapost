<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/formCenter.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>库存记录变动表</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        /*.accountSearch{*/
            /*height: 80px!important;*/
            /*transform: scale(0.95,0.95);*/
            /*transform-origin: left top;*/
            /*-webkit-transform: scale(0.95,0.95);*/
            /*-webkit-transform-origin: left top;*/
            /*-ms-transform: scale(0.95,0.95);*/
            /*-ms-transform-origin: left top;*/
            /*-moz-transform: scale(0.95,0.95);*/
            /*-moz-transform-origin: left top;*/
        /*}*/

    }
    .accountSearch{
        width:1800px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        width:250px;
        height:40px;
        line-height:40px;
        float:left;
        margin:10px 0px;
    }
    .accountSearch li input[type='text']{
        background:#FFF;
    }
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        height:24px;
        background-color: #f2f2f2;
        margin-left: -45px;
        background: url("${bath}/static/img/date_img.png") no-repeat center;

    }
    .table_listBaseByInvent{
        margin-left: 20px;
        width: 1600px;;
        table-layout: fixed;
    }
    .table_listBaseByInventDetail{
        table-layout: fixed;
        width: 90%;
    }
    .table_listBaseByInventDetail th{
        height: 40px;
        text-align: left;
        border-bottom: none!important;
    }
    .table_listBaseByInvent th:first-child, .table_listBaseByInvent td:first-child{
        width: 100px;
    }
    .table_listBaseByInvent span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBaseByInvent th, .table_listBaseByInvent td{
        height: 40px;
        text-align: left;
        word-break: break-all;
        border-bottom: 1px solid #e5e5e5;
    }
    #holder{
        margin: 30px 0px;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
    }
    .InventName{
        border-right: 1px solid #e5e5e5;
    }
</style>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
    })
</script>
<body style="background: #fff">
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  库存变动记录表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:220px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:220px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="货品名称"   type="text" style="width:220px;" id="ItemName"/></li>
            <li><input class="allinputButton" placeholder="货品编号"   type="text" style="width:220px;" id="ItemNumber"/></li>
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>


    <div style="width: 1600px;">
        <table class="table_listBaseByInvent" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>网点名称</th>
                <th>时间</th>
                <th>货品名称</th>
                <th>货品规格</th>
                <th>货品编号</th>
                <th>变动原因</th>
                <th>单据编号</th>
                <th>库存变动数量</th>
                <th>库存余量</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
            <#--<tr>-->
            <#--<td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="1600" height="600"></td>-->
            <#--</tr>-->
            <tr>
                <td style="text-align: center" colspan="9"><img src="${bath}/static/img/nodata.png" width="1600px;" height="600"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>











    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>

<script type="text/javascript">
    var array = [];
    array[0] = '';//goodsInfoName
    array[1] = "" ;//goodsInfoItemNo
    array[8] = '';//start
    array[9] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
//    var todayDate = new Date();//获取今天日期
//    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
//    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
//    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");

//    var i = 0;
//    while (i<$(".InventName").length) {
//        var j = i + 1
//        var num = 1
//        while (j <($(".InventName").length + 1)) {
//            if (j < $(".InventName").length && $(".InventName").eq(i).html() == $(".InventName").eq(j).html()) {
//                console.log("remove:" + j);
//                $(".InventName").eq(j).remove()
//                num++;
//                i--;
//            } else {
//                $(".InventName").eq(i).prop("rowspan",num)
//
//                break;
//            }
//        }
//        i = j;
//    }
//    $(".removeInvent").remove()







    $(document).ready(function(){
        var year  =new Date().getFullYear();
        var month =new Date().getMonth();
        month = month + 1
        if(month<10){
            month = '0'+month
        }
        if( month == 4 || month == 6  || month == 9 || month == 11 ){
            $("#datetimepicker_start").val( year + '-' + month + '-' + "01")
            $("#datetimepicker_end").val( year + '-' + month + '-' + "30")
        }else if(month == 2 ){
            if( year%4 == 0 ){
                $("#datetimepicker_start").val( year + '-' + month + '-' + "01")
                $("#datetimepicker_end").val( year + '-' + month + '-' + "29")
            }else{
                $("#datetimepicker_start").val( year + '-' + month + '-' + "01")
                $("#datetimepicker_end").val( year + '-' + month + '-' + "28")
            }
        }else{
            $("#datetimepicker_start").val( year + '-' + month + '-' + "01")
            $("#datetimepicker_end").val( year + '-' + month + '-' + "31")
        }
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  &&  $("#datetimepicker_start").val() != "" &&   $("#datetimepicker_end").val() != ""){
                lock=true
                array[0] = $("#ItemName").val();//idCard
                array[1] = $("#ItemNumber").val();//idCard
                array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                ajaxPages("../web/api/report/getInventoryChangeByEnterprise","itemContainer","holder","changeHistory",10,'','',array,function(){

                });
            }else{
                data_type_alert("结束时间必须大于开始时间且必填","error")
            }

        });


        $("#accExport").click(function(){
            if(lock){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
            }
            if( array[8] != undefined ){
                outputStr += '&start=' + array[8];
            }
            if( array[9] != undefined ){
                outputStr += '&end=' + array[9];
            }
            if( array[0] != undefined ){
                outputStr += '&goodsInfoName=' + array[0];
            }
            if( array[1] != undefined ){
                outputStr += '&goodsInfoItemNo=' + array[1];
            }
            if( array[8] == undefined || array[8] == '' || array[9] == undefined || array[9] == ''){
                response_ensure_alert("warning","搜索时间不能为空");
            }
            else{
                window.location.href = '../web/api/exportExcel/exportInventoryChangeByEnterprise?' + outputStr;
            }
            }else{
                data_type_alert("请先搜索后再导出","error")
            }
        });
    });
</script>