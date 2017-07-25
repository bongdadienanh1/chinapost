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
    <link rel="stylesheet" href="${bath}/static/css/jPages.css" />
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script src="${bath}/static/js/jPages.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1360px){
        #userDetailListReduce{
            transform: scale(0.8,0.8);
            transform-origin: left top;
            -webkit-transform: scale(0.8,0.8);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.8,0.8);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.8,0.8);
            -moz-transform-origin: left top;
            font-size: 16px!important;
        }
        .accountSearch{
            width: 1400px!important;
            height: 80px!important;
            transform: scale(0.95,0.95);
            transform-origin: left top;
            -webkit-transform: scale(0.95,0.95);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.95,0.95);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.95,0.95);
            -moz-transform-origin: left top;
        }
    }
     .checkplace{
         _margin-top: -15px;
     }
    .accountSearch{
        width:1800px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        width:300px;
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


    .table_listReduce{
        width: 98%;
        margin-left: 20px;
    }
    .table_listReduce span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listReduce th, .table_listReduce td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
    }
    #holder{
        margin: 30px 0px;
    }
    #holder2{
        margin: 30px 0px;
        margin-left: 100px;
    }
    #userDetailListReduce{
        font-size: 12px;
        width:1200px;
        position:fixed;
        left:15%;
        top:15%;
        z-index:2;
        background:#FFF;
        display: none;
    }
    #userDetailListReduce h1 i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
    }
    .holder2{
        margin-left: 100px;
        margin-bottom: 20px;
        width: 1000px;
    }
    .table_listReduceDetail{
        margin-left: 100px;
        margin-bottom: 20px;
        width: 1000px;
    }
    .table_listReduceDetail th, .table_listReduceDetail td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
    }
</style>

<body style="background: #edf3f8">
<div class="allOutShow" style="height: 1000px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle"><a href="formCenter">首页</a><a href="memberUbaoSendForm">会员邮豆发放统计报表</a><span>会员邮豆消耗统计报表</span><a class="leftshanow" href="BaseDataform">网点基础数据统计报表</a><a class="leftshanow" href="ubaoSendForm">邮豆发放记录表</a><a class="leftshanow" href="inventoryDetailForm">库存详情表</a><a class="leftshanow" href="inventoryChangeDetailForm">库存变动记录表</a><a class="leftshanow" href="supplyDetailForm">网点供货明细表</a><a class="leftshanow" href="supplyCountForm">网点供货统计表</a><a class="leftshanow" href="deliveryDetailForm">供应商发货汇总表</a><abbr></abbr>
    </div>
    <input type="hidden" id="newDatetimepicker_start"/>
    <input type="hidden" id="newDatetimepicker_end"/>
    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"> <input type="hidden" id="NewEnterpriseIdChoosen"  value="${enterprise.enterpriseId}"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li style="width: 250px"><input style="width: 250px" class="allinputButton" placeholder="身份证号"   type="text" name="accountUserName" /></li>
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>


    <div style="width: 98%">
        <table class="table_listReduce" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>身份证号</th>
                <th>姓名</th>
                <th>线下订单数</th>
                <th>线下消耗邮豆</th>
                <th>线上订单数</th>
                <th>线上消耗邮豆</th>
                <th>线下退单数</th>
                <th>线下退款邮豆</th>
                <th>线上退单数</th>
                <th>线上退款邮豆</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
            <tr>
                <td style="text-align: center" colspan="11"><img src="${bath}/static/img/nodata.png" width="98%" height="600"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>


    <div class="allpop" id="userDetailListReduce">
        <h1>会员邮豆消耗明细<i></i></h1>
        <div style="margin-left: 20px; margin-top: 20px;height: 50px"><input style="float: right;margin-right: 70px"  type="button" id="outExcel" value="导出表格" class="allclickButton"> </div>
        <table class="table_listReduceDetail" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>身份证号</th>
                <th>姓名</th>
                <th>单据类型</th>
                <th>时间</th>
                <th>订单/退单号</th>
                <th>消耗/退款邮豆</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer2">
            <tr>
                <td>身份证号</td>
                <td>姓名</td>
                <td>单据类型</td>
                <td>时间</td>
                <td>单据编号</td>
                <td>订单金额</td>
            </tr>
            </tbody>
        </table>
        <div id="holder2" class="allcpageTurnButton"></div>
    </div>


<script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>

</body>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    var array = [];
    array[15] = '';//idCard
    array[8] = '';//start
    array[9] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();
    var array1 = [];
    array1[15] = '';//idCard
    array1[8] = '';//start
    array1[9] = '';//end
    array1[16] = $(".enterpriseIdChoosen").val();
//    var todayDate = new Date();//获取今天日期
//    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
//    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
//    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");
//    $("#newDatetimepicker_start").val(array[8]);
//    $("#newDatetimepicker_end").val(array[9]);
    enterSearch( $("input[name='accountUserName']"),$("#accSearch"))
    $(document).on("click",".checkDetailReduce",function(){
        coverHtml()
        var start =  $("#newDatetimepicker_start").val();
        var end =  $("#newDatetimepicker_end").val();
        var idCard = $(this).parent().siblings(".idCard").html();
        var enterpriseId = $("#NewEnterpriseIdChoosen").val();
        array1[15] = idCard;
        array1[8] = start;
        array1[9] = end;
        array1[16] =enterpriseId
        excel_idCard = idCard;
        ajaxPages("../web/api/report/getDetailConsumeNew","itemContainer2","holder2","memberUbaoReduceFormDetailNew",10,'','',array1);
        $("#userDetailListReduce").fadeIn(500);
    });

    $(document).ready(function(){
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
        var lock=false
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val() ){
                lock=true
                array[15] = $("input[name='accountUserName']").val();//idCard
                array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                $("#NewEnterpriseIdChoosen").val( array[16]);
                $("#newDatetimepicker_start").val(array[8]);
                $("#newDatetimepicker_end").val(array[9]);
                ajaxPages("../web/api/report/getCustomerConsumeNew","itemContainer","holder","memberUbaoReduceFormNew",10,'','',array);
            }else{
                data_type_alert("结束时间必须大于开始时间","error")
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
            if( array[15] != undefined ){
                outputStr += '&idCard=' + array[15];
            }
            if( array[8] == undefined || array[8] == '' || array[9] == undefined || array[9] == ''){
                response_ensure_alert("warning","搜索时间不能为空");
            }
            else{
                window.location.href = '../web/api/exportExcel/customerConsumeDownNew?' + outputStr;
            }

            }else{
                data_type_alert("请先搜索后再导出","error")
            }
        });

        $("#outExcel").click(function(){
            var outputStr = '';
            if( array1[16] != undefined ){
                outputStr += 'enterpriseId=' + array1[16];
            }
            if( array1[8] != undefined ){
                outputStr += '&start=' + array1[8];
            }
            if( array1[9] != undefined ){
                outputStr += '&end=' + array1[9];
            }
            if( excel_idCard != undefined ){
                outputStr += '&idCard=' + excel_idCard;
            }
//            if( array[8] == undefined || array[8] == '' || array[9] == undefined || array[9] == ''){
//                response_ensure_alert("warning","搜索时间不能为空");
//            }
//            else{
                window.location.href = '../web/api/exportExcel/consumeDetailDownNew?' + outputStr;
//            }
        });
    });
</script>