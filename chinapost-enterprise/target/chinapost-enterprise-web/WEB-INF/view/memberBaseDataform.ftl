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
    <title>会员基础数据表</title>
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
        #changeType{
            width: 140px!important;
        }
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
    .table_listBase{
        margin-left: 20px;
        width: 2200px;
        display: none;
    }
    .table_listBase span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBase th, .table_listBase td{
        height: 40px;
        text-align: left;
        padding-left: 10px;
        border-bottom: 1px solid #e5e5e5;
    }
    .table_listBase2{
        border-collapse: collapse;
        margin-left: 20px;
        width: 2200px;
        table-layout: fixed;
    }
    .table_listBase2 span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBase2 th, .table_listBase2 td{
        height: 40px;
        text-align: left;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .table_listBase2Detail{
       table-layout: fixed;
    }
    .table_listBase2 tr{
        border-bottom: 1px solid #e5e5e5;
    }

    #holder{
        margin: 30px 0px;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
    }
    .InventName,.InventAddress{
        border-right: 1px solid #e5e5e5;
    }
    .openDetail{
        display: inline-block;
        width: 16px;
        height: 16px;
        line-height: 16px;
        border: 1px solid #54a6de;
        color: #54a6de;
        border-radius: 16px;
        text-align: center;
        margin: 0px 5px;
    }
    .openDetail:hover{
        background: #54a6de;
        color: #fff;
    }
    .table_listBase2Detail tr:nth-last-child(1){
        border-bottom: none;
    }
    .secondClass{
        padding-left: 10px;
    }
    .thirdClass{
        padding-left: 20px;
    }
    .lastClass{
        padding-left:60px;
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
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  会员基础数据表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="display:none;width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /><input data-id="0" style="margin-left: 20px" class="allclickButton" type="button" id="changeType" value="切换到明细表" /></li>
        </ul>
    </div>


    <div style="width: 2200px;">
        <table class="table_listBase" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th style="text-align: center;border-right: 1px solid #e5e5e5;" colspan="7">用户基本情况</th>
                <th style="text-align: center;border-right: 1px solid #e5e5e5;" colspan="2">发放情况</th>
                <th style="text-align: center;border-right: 1px solid #e5e5e5;" colspan="2">扣减情况</th>
                <th style="text-align: center;border-right: 1px solid #e5e5e5;" colspan="3">线下订单</th>
                <th style="text-align: center;border-right: 1px solid #e5e5e5;" colspan="2">线上订单</th>
                <th style="text-align: center;border-right: 1px solid #e5e5e5;" rowspan="2">兑换结算金额（元）</th>
                <th style="text-align: center" colspan="2">退单</th>
            </tr>
            <tr>
                <th>上级名称</th>
                <th>网点名称</th>
                <th>姓名</th>
                <th>身份证号</th>
                <th>用户属性</th>
                <th>上期结存</th>
                <th style="border-right: 1px solid #e5e5e5;">当前结余</th>
                <th>发放次数</th>
                <th style="border-right: 1px solid #e5e5e5;">发放邮豆</th>
                <th>扣减次数</th>
                <th style="border-right: 1px solid #e5e5e5;">扣减邮豆</th>
                <th>兑换次数</th>
                <th>兑换邮豆</th>
                <th style="border-right: 1px solid #e5e5e5;">补贴邮豆</th>
                <th>兑换次数</th>
                <th style="border-right: 1px solid #e5e5e5;">兑换邮豆</th>
                <th>退单次数</th>
                <th>退单结算金额（元）</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
              <tr>
                 <td style="text-align: center" colspan="19"><img src="${bath}/static/img/nodata.png" width="2200px;" height="600"></td>
              </tr>
            </tbody>
        </table>

        <table class="table_listBase2" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>机构名称</th>
                <th>用户属性</th>
                <th>用户数</th>
                <th>发放用户数</th>
                <th>发放邮豆</th>
                <th>扣减用户数</th>
                <th>扣减邮豆</th>
                <th>兑换用户数</th>
                <th>兑换邮豆</th>
                <th>补贴用户数</th>
                <th>补贴邮豆</th>
                <th>兑换结算金额(元)</th>
                <th>退单结算金额(元)</th>
                <th>会员邮豆余额</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer2">
            <tr>
                <td style="text-align: center" colspan="19"><img src="${bath}/static/img/nodata.png" width="2200px;" height="600"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>
    <div class="loading">
        <h1>数据量大正在搜索中，请耐心等待.......</h1>
        <img width="250" height="150" src="${bath}/static/img/loading3.gif">
    </div>










    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>

</body>

<script type="text/javascript">
    var array = [];
    array[33] = '';//start
    array[34] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
//    var todayDate = new Date();//获取今天日期
//    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
//    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
//    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");



    $(document).ready(function(){
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false
        $(document).on("click","#changeType",function(){
            if( $(this).val() =="切换到明细表"){
                $("#holder").show()
                if(!isEnd) {
                    $(".checkplace").show()
                }

                $(this).attr("data-id","1")
                $(this).val("切换到统计表")
                $(".table_listBase").show()
                $(".table_listBase2").hide()
                $("#accSearch").trigger("click")
            }
            else{
                $("#holder").hide()
                if(!isEnd){
                    $(".checkplace").hide()
                }
                $(this).attr("data-id","0")
                $(this).val("切换到明细表")
                $(".table_listBase2").show()
                $(".table_listBase").hide()
                $("#accSearch").trigger("click")
            }
        })
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  ){
                $(".loading").show()
                coverHtml()
                lock=true
                array[33] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[34] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                if( $("#changeType").attr("data-id") == "1"){

                    ajaxPages("../web/api/report/getCustomerFormDetail","itemContainer","holder","memberBaseDataform",10,'','',array);
                }else{
                    var startTime = $("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                    var endTime = $("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                    $.post("../web/api/report/getCustomerFormStatistic",{
                        startTime:startTime,
                        endTime:endTime
                    },function(data){
                        if(data.response == "success"){
                            discoverHtml()
                            $(".loading").hide()
                            var html = ""
                            html += '<tr>'
                            if( data.data[0].end == "1" ){
                                html += '<td data-top="0" data-id="'+ data.data[0].enterpriseId +'">' + data.data[0].enterpriseName +  '</td>'
                            }else{
                                html += '<td data-top="0" data-id="'+ data.data[0].enterpriseId +'">' + '<a class="openDetail">+</a>' + data.data[0].enterpriseName +  '</td>'
                            }
                            html += '<td colspan="13">'
                            html += '<table class="table_listBase2Detail" style="" width="100%">'
                            html += '<tr>'
                            html += '<td>' + handleNewOldCus(data.data[0].customerRef)+  '</td>'
                            html += '<td>' + handleUndefinedToZero(data.data[0].customerAmount)+  '</td>'
                            html += '<td>' + handleUndefinedToZero(data.data[0].grandCustomerAmount)+  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].grandPrice,2) +'">' + addCommas(data.data[0].grandPrice,2) +  '</td>'
                            html += '<td>' + handleUndefinedToZero(data.data[0].decutCustomerAmount)+  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].decutPrice,2) +'">' + addCommas(data.data[0].decutPrice,2) +  '</td>'
                            html += '<td>' + handleUndefinedToZero(data.data[0].consumeCustomerAmount)+  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].consumePrice,2) +'">' + addCommas(data.data[0].consumePrice,2) +  '</td>'
                            html += '<td>' + handleUndefinedToZero(data.data[0].consumeSubsidyCustomerAmount)+  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].consumeSubsidyPrice,2) +'">' + addCommas(data.data[0].consumeSubsidyPrice,2) +  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].consumeSettlePrice,2) +'">' + addCommas(data.data[0].consumeSettlePrice,2) +  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].refundSettlePrice,2) +'">' + addCommas(data.data[0].refundSettlePrice,2) +  '</td>'
                            html += '<td title="'+ addCommas(data.data[0].currentPrice,2) +'">' + addCommas(data.data[0].currentPrice,2) +  '</td>'
                            html += '</tr>'
                            html += '<tr>'
                            if(data.data[1] != undefined){
                                html += '<td>' + handleNewOldCus(data.data[1].customerRef)+  '</td>'
                                html += '<td>' + handleUndefinedToZero(data.data[1].customerAmount)+  '</td>'
                                html += '<td>' + handleUndefinedToZero(data.data[1].grandCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].grandPrice,2) +'">' + addCommas(data.data[1].grandPrice,2) +  '</td>'
                                html += '<td>' + handleUndefinedToZero(data.data[1].decutCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].decutPrice,2) +'">' + addCommas(data.data[1].decutPrice,2) +  '</td>'
                                html += '<td>' + handleUndefinedToZero(data.data[1].consumeCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].consumePrice,2) +'">' + addCommas(data.data[1].consumePrice,2) +  '</td>'
                                html += '<td>' + handleUndefinedToZero(data.data[1].consumeSubsidyCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].consumeSubsidyPrice,2) +'">' + addCommas(data.data[1].consumeSubsidyPrice,2) +  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].consumeSettlePrice,2) +'">' + addCommas(data.data[1].consumeSettlePrice,2) +  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].refundSettlePrice,2) +'">' + addCommas(data.data[1].refundSettlePrice,2) +  '</td>'
                                html += '<td title="'+ addCommas(data.data[1].currentPrice,2) +'">' + addCommas(data.data[1].currentPrice,2) +  '</td>'
                            }else{
                                html += '<td>老用户</td>'
                                html += '<td>0</td>'
                                html += '<td>0</td>'
                                html += '<td>0.00</td>'
                                html += '<td>0</td>'
                                html += '<td>0.00</td>'
                                html += '<td>0</td>'
                                html += '<td>0.00</td>'
                                html += '<td>0</td>'
                                html += '<td>0.00</td>'
                                html += '<td>0.00</td>'
                                html += '<td>0.00</td>'
                                html += '<td>0.00</td>'
                            }

                            html += '</tr>'
                            html += '</td>'
                            html += '</table>'
                            html += '</tr>'

                            $("#itemContainer2").empty().append(html)
                        }else{
                            response_ensure_alert("error",data.data.text)
                            discoverHtml()
                            $(".loading").hide()
                        }
                    },"json")

                }

            }else{
                data_type_alert("结束时间必须大于开始时间","error")
            }

        });


        $(document).on('click',".openDetail",function(){

            var enterpriseId = $(this).parent().data("id")
            var upenterpriseId = $(this).parent().attr("data-upenterpriseid")
            var upupenterpriseId = $(this).parent().attr("data-upupEnterpriseId")
            var istop = false
            var _this = this
            var startTime = $("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
            var endTime = $("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
            if( $(this).parent().data("top") == "0" ){
                istop = true
            }
            if($(this).html() == "+" ){
                $(".loading").show()
                coverHtml()
                $(this).html("-")
                $.post("../web/api/report/getSonCustomerFormStatistic",{
                    enterpriseId:enterpriseId,
                    startTime:startTime,
                    endTime:endTime
                },function(data){
                    if(data.response == "success"){
                        discoverHtml()
                        $(".loading").hide()
                        var html = ""
                        data.data.map(function(o){
                            html += '<tr>'
                            if(o[0]!=undefined){
                                if(istop && o[0].end != true){
                                    html += '<td class="allFirst secondClass" data-upEnterpriseId="'+ enterpriseId +'" data-top="1" data-id="'+ o[0].enterpriseId +'">' + '<a class="openDetail">+</a>' + o[0].enterpriseName +  '</td>'
                                }else if( o[0].end == true ){
                                    html += '<td class="allFirst lastClass" data-upupupEnterpriseId="'+ upupenterpriseId +'"  data-upupEnterpriseId="'+ upenterpriseId +'"  data-upEnterpriseId="'+ enterpriseId +'" data-top="1" data-id="'+ o[0].enterpriseId +'">' + o[0].enterpriseName +  '</td>'
                                }else{
                                    html += '<td class="allFirst thirdClass" data-upupEnterpriseId="'+ upenterpriseId +'" data-upEnterpriseId="'+ enterpriseId +'" data-top="1" data-id="'+ o[0].enterpriseId +'">' + '<a class="openDetail">+</a>' + o[0].enterpriseName +  '</td>'
                                }
                                html += '<td colspan="13">'
                                html += '<table class="table_listBase2Detail" style="" width="100%">'
                                html += '<tr>'
                                html += '<td>' + handleNewOldCus(o[0].customerRef)+  '</td>'
                                html += '<td>' + handleUndefinedToZero(o[0].customerAmount)+  '</td>'
                                html += '<td>' + handleUndefinedToZero(o[0].grandCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(o[0].grandPrice,2) +'">' + addCommas(o[0].grandPrice,2) +  '</td>'
                                html += '<td>' + handleUndefinedToZero(o[0].decutCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(o[0].decutPrice,2) +'">' + addCommas(o[0].decutPrice,2) +  '</td>'
                                html += '<td>' + handleUndefinedToZero(o[0].consumeCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(o[0].consumePrice,2) +'">' + addCommas(o[0].consumePrice,2) +  '</td>'
                                html += '<td>' + handleUndefinedToZero(o[0].consumeSubsidyCustomerAmount)+  '</td>'
                                html += '<td title="'+ addCommas(o[0].consumeSubsidyPrice,2) +'">' + addCommas(o[0].consumeSubsidyPrice,2) +  '</td>'
                                html += '<td title="'+ addCommas(o[0].consumeSettlePrice,2) +'">' + addCommas(o[0].consumeSettlePrice,2) +  '</td>'
                                html += '<td title="'+ addCommas(o[0].refundSettlePrice,2) +'">' + addCommas(o[0].refundSettlePrice,2) +  '</td>'
                                html += '<td title="'+ addCommas(o[0].currentPrice,2) +'">' + addCommas(o[0].currentPrice,2) +  '</td>'
                                html += '</tr>'
                                html += '<tr>'
                                if(o[1] != undefined){
                                    html += '<td>' + handleNewOldCus(o[1].customerRef)+  '</td>'
                                    html += '<td>' + handleUndefinedToZero(o[1].customerAmount)+  '</td>'
                                    html += '<td>' + handleUndefinedToZero(o[1].grandCustomerAmount)+  '</td>'
                                    html += '<td title="'+ addCommas(o[1].grandPrice,2) +'">' + addCommas(o[1].grandPrice,2) +  '</td>'
                                    html += '<td>' + handleUndefinedToZero(o[1].decutCustomerAmount)+  '</td>'
                                    html += '<td title="'+ addCommas(o[1].decutPrice,2) +'">' + addCommas(o[1].decutPrice,2) +  '</td>'
                                    html += '<td>' + handleUndefinedToZero(o[1].consumeCustomerAmount)+  '</td>'
                                    html += '<td title="'+ addCommas(o[1].consumePrice,2) +'">' + addCommas(o[1].consumePrice,2) +  '</td>'
                                    html += '<td>' + handleUndefinedToZero(o[1].consumeSubsidyCustomerAmount)+  '</td>'
                                    html += '<td title="'+ addCommas(o[1].consumeSubsidyPrice,2) +'">' + addCommas(o[1].consumeSubsidyPrice,2) +  '</td>'
                                    html += '<td title="'+ addCommas(o[1].consumeSettlePrice,2) +'">' + addCommas(o[1].consumeSettlePrice,2) +  '</td>'
                                    html += '<td title="'+ addCommas(o[1].refundSettlePrice,2) +'">' + addCommas(o[1].refundSettlePrice,2) +  '</td>'
                                    html += '<td title="'+ addCommas(o[1].currentPrice,2) +'">' + addCommas(o[1].currentPrice,2) +  '</td>'
                                }else{
                                    html += '<td>老用户</td>'
                                    html += '<td>0</td>'
                                    html += '<td>0</td>'
                                    html += '<td>0.00</td>'
                                    html += '<td>0</td>'
                                    html += '<td>0.00</td>'
                                    html += '<td>0</td>'
                                    html += '<td>0.00</td>'
                                    html += '<td>0</td>'
                                    html += '<td>0.00</td>'
                                    html += '<td>0.00</td>'
                                    html += '<td>0.00</td>'
                                    html += '<td>0.00</td>'
                                }

                                html += '</tr>'
                                html += '</td>'
                                html += '</table>'
                            }

                            html += '</tr>'
                        })
                        $(_this).parent().parent().after(html)
                    }else{
                        discoverHtml()
                        $(".loading").hide()
                        response_ensure_alert("error",data.data.text)
                    }
                },"json")
            }else{
                $(this).html("+")
                $(".allFirst").each(function(){
                    if( $(this).attr("data-upEnterpriseId") == enterpriseId ||$(this).attr("data-upupenterpriseid") == enterpriseId||$(this).attr("data-upupupEnterpriseId") == enterpriseId){
                        $(this).parent().remove()
                    }
                })
            }

        })


        $("#accExport").click(function(){
            if(lock){
            var outputStr = '';
            if( array[33] != undefined ){
                outputStr += '&startTime=' + array[33];
            }
            if( array[34] != undefined ){
                outputStr += '&endTime=' + array[34];
            }
            if( array[33] == undefined || array[33] == '' || array[34] == undefined || array[34] == ''){
                response_ensure_alert("warning","搜索时间不能为空");
            }
            else{
                if( $("#changeType").attr("data-id") == "1"){
                    if( array[16] != undefined ){
                        outputStr += '&enterpriseId=' + array[16];
                    }
                    window.location.href = '../web/api/exportExcel/bigExportCustomerFormDetail?' + outputStr;
                }else{
                    window.location.href = '../web/api/exportExcel/exportCustomerFormStatistic?' + outputStr;
                }


            }
            }else{
                data_type_alert("请先搜索后再导出","error")
            }
        });
    });
</script>