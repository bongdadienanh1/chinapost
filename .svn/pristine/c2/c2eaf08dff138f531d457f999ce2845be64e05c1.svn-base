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
    <title>财富统计报表</title>
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
        width: 1600px;;
    }
    .table_listBase span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBase th, .table_listBase td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
    }
    #holder{
        margin: 30px 0px;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
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
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  财富统计报表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <#--<li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>-->
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>


    <div style="width: 1600px;">
        <table class="table_listBase" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>机构名称</th>
                <th>累计预拨</th>
                <th>发放邮豆总额</th>
                <th>扣减邮豆总额</th>
                <th>补贴邮豆总额</th>
                <th>退还邮豆总额</th>
                <th>剩余邮豆总额</th>
                <th>剩余现金价值(元)</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
              <tr>
                 <td style="text-align: center" colspan="8"><img src="${bath}/static/img/nodata.png" width="1600px;" height="600"></td>
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
    array[15] = '';//idCard
    array[8] = '';//start
    array[9] = '';//end
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
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  ){
                lock=true
                var startTime = $("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                var endTime = $("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                $.post("../web/api/report/getWealth",{
                    startTime:startTime,
                    endTime:endTime
                },function(data){
                    if(data.response == "success"){
                        var html = ""
                        html += '<tr>'
                        if( data.data.end == true ){
                            html += '<td data-top="0" data-id="'+ data.data.enterpriseId +'">' + data.data.enterpriseName +  '</td>'
                        }else{
                            html += '<td data-top="0" data-id="'+ data.data.enterpriseId +'">' + '<a class="openDetail">+</a>' + data.data.enterpriseName +  '</td>'
                        }
                        html += '<td title="'+addCommas(data.data.appropriation,2)+'">' + addCommas(data.data.appropriation,2) +  '</td>'
                        html += '<td title="'+addCommas(data.data.grandUdou,2)+'">' + addCommas(data.data.grandUdou ,2)+  '</td>'
                        html += '<td title="'+addCommas(data.data.decutUdou,2)+'">' + addCommas(data.data.decutUdou,2) +  '</td>'
                        html += '<td title="'+addCommas(data.data.subsidyUdou,2)+'">' + addCommas(data.data.subsidyUdou,2) +  '</td>'
                        html += '<td title="'+addCommas(data.data.backUdou,2)+'">' + addCommas(data.data.backUdou,2) +  '</td>'
                        html += '<td title="'+addCommas(data.data.remainUdou,2)+'">' + addCommas(data.data.remainUdou,2) +  '</td>'
                        html += '<td title="'+addCommas(data.data.remainCash,2)+'">' + addCommas(data.data.remainCash,2) +  '</td>'
                        html += '</tr>'
                        $("#itemContainer").empty().append(html)
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },"json")
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
                $(this).html("-")
                $.post("../web/api/report/getSonWealth",{
                    enterpriseId:enterpriseId,
                    startTime:startTime,
                    endTime:endTime
                },function(data){
                    if(data.response == "success"){
                        var html = ""
                        data.data.map(function(o){
                            html += '<tr>'
                            if(istop && o.end != true){
                                html += '<td class="allFirst secondClass" data-upEnterpriseId="'+ enterpriseId +'" data-top="1" data-id="'+ o.enterpriseId +'">' + '<a class="openDetail">+</a>' + o.enterpriseName +  '</td>'
                            }else if( o.end == true ){
                                html += '<td class="allFirst lastClass" data-upupupEnterpriseId="'+ upupenterpriseId +'"  data-upupEnterpriseId="'+ upenterpriseId +'"  data-upEnterpriseId="'+ enterpriseId +'" data-top="1" data-id="'+ o.enterpriseId +'">' + o.enterpriseName +  '</td>'
                            }else{
                                html += '<td class="allFirst thirdClass" data-upupEnterpriseId="'+ upenterpriseId +'" data-upEnterpriseId="'+ enterpriseId +'" data-top="1" data-id="'+ o.enterpriseId +'">' + '<a class="openDetail">+</a>' + o.enterpriseName +  '</td>'
                            }
                            html += '<td title="'+addCommas(o.appropriation,2)+'">' + addCommas(o.appropriation,2) +  '</td>'
                            html += '<td title="'+addCommas(o.grandUdou,2)+'">' + addCommas(o.grandUdou ,2)+  '</td>'
                            html += '<td title="'+addCommas(o.decutUdou,2)+'">' + addCommas(o.decutUdou,2) +  '</td>'
                            html += '<td title="'+addCommas(o.subsidyUdou,2)+'">' + addCommas(o.subsidyUdou,2) +  '</td>'
                            html += '<td title="'+addCommas(o.backUdou,2)+'">' + addCommas(o.backUdou,2) +  '</td>'
                            html += '<td title="'+addCommas(o.remainUdou,2)+'">' + addCommas(o.remainUdou,2) +  '</td>'
                            html += '<td title="'+addCommas(o.remainCash,2)+'">' + addCommas(o.remainCash,2) +  '</td>'
                            html += '</tr>'
                        })
                        console.log(html)
                        $(_this).parent().parent().after(html)
                    }else{
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
            var startTime = $("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
            var endTime = $("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
            if( startTime != undefined ){
                outputStr += '&startTime=' + startTime;
            }
            if( endTime != undefined ){
                outputStr += '&endTime=' + endTime;
            }
            if( startTime == undefined || startTime == '' || endTime == undefined || endTime == ''){
                response_ensure_alert("warning","搜索时间不能为空");
            }
            else{
                window.location.href = '../web/api/exportExcel/exportWealthForm?' + outputStr;
            }
            }else{
                data_type_alert("请先搜索后再导出","error")
            }
        });
    });
</script>