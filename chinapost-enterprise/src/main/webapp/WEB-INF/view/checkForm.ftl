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
    <title>区域对账汇总表</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        /*.accountSearch{*/
            /*width: 1300px!important;*/
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
        /*.table_listBaseByItem,.table_listBaseByInvent{*/
        /*transform: scale(0.55,0.55)!important;*/
        /*transform-origin: left top!important;*/
        /*-webkit-transform: scale(0.55,0.55)!important;*/
        /*-webkit-transform-origin: left top!important;*/
        /*-ms-transform: scale(0.55,0.55)!important;*/
        /*-ms-transform-origin: left top!important;*/
        /*-moz-transform: scale(0.55,0.55)!important;*/
        /*-moz-transform-origin: left top!important;*/
        /*}*/
    }
    /*.table_listBaseByItem,.table_listBaseByInvent{*/
    /*transform: scale(0.95,0.95);*/
    /*transform-origin: left top;*/
    /*-webkit-transform: scale(0.95,0.95);*/
    /*-webkit-transform-origin: left top;*/
    /*-ms-transform: scale(0.95,0.95);*/
    /*-ms-transform-origin: left top;*/
    /*-moz-transform: scale(0.95,0.95);*/
    /*-moz-transform-origin: left top;*/
    /*}*/
    .accountSearch{
        width:1400px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        width:255px;
        height:32px;
        line-height:32px;
        float:left;
        margin: 10px;
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
        width: 2000px;
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
        width: 150px;
    }
    .table_listBaseByInvent span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBaseByInvent th, .table_listBaseByInvent td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
        padding-left: 10px;
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
        border-left: 1px solid #e5e5e5;
    }
    .last{
        border-right: 1px solid #e5e5e5;
    }







    #chooseBillStatus{
        position: absolute;
        left: 100px;
        top: 3px;
        background: #fff;
        z-index: 1;
        cursor: pointer;
    }
    #chooseBillStatus dt{
        white-space: nowrap;
        overflow: hidden;
    }
    #chooseBillStatus dt, #chooseBillStatus dd{
        width: 125px;
        height: 20px;
        line-height: 20px;

        text-align: center;
    }
    #chooseBillStatus dd{
        margin-top: -1px;
        display: none;
    }
    #chooseBillStatus dt:hover,#chooseBillStatus dd:not(:first-child):hover{
        background: #54a6de;
        color: #fff;
    }


    .InventAddress{
        border-right: 1px solid #e5e5e5;
        border-left: 1px solid #e5e5e5;
    }

</style>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){

        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        var notEnd = true;

        inventoryList(eid,ename,notEnd);




        $("#chooseBillStatus").mouseover(function(){
            $("#chooseBillStatus dd").show()
        })
        $("#chooseBillStatus").mouseout(function(){
            $("#chooseBillStatus dd").hide()
        })
        $("input[name='billStatesCheckboxAll']").click(function(){
            if( $(this).is(":checked") ){
                $("input[name='billStatesCheckbox']:not(':checked')").trigger("click")
            }else{
                $("input[name='billStatesCheckbox']:checked").trigger("click")
            }
        })
        $("input[name='billStatesCheckbox']").click(function(){
            if(  $("input[name='billStatesCheckbox']:checked").length == 4 ){
                $("#chooseBillStatus dt").html("全部")
                $("input[name='billStatesCheckboxAll']").prop("checked","checked")
            }else{
                $("input[name='billStatesCheckboxAll']").prop("checked","")
                var html = ""
                $("input[name='billStatesCheckbox']:checked").each(function(){
                    html += $(this).next("span").html() + ','
                })
                html = html.substring(0,html.length-1)
                $("#chooseBillStatus dt").html(html)
            }

        })
    })
</script>
<body style="background: #fff">
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  区域对账汇总表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 290px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 250px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>


            <li>
                <div style="margin-left:20px;margin-top:0px;text-align: left!important; position: relative;" class="allinputButton"><span style="display:inline-block;margin-left: 20px;color: #999">单据状态</span>
                    <dl id="chooseBillStatus">
                        <dt data-name="ALL_PICK">全部</dt>
                        <dd style="text-align: left;margin-left: 10px" data-name="ALL_PICK"><input type="checkbox" name="billStatesCheckboxAll" checked="checked"/>全部</dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="FINISHED"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>已确认</span></dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="COMMODITY_STORAGE"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待商品入库</span></dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="FOR_SETTLEMENT"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待结算</span></dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="COMPLETED"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>已完成</span></dd>
                    </dl>
            </li>

            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>


    <div style="width: 2100px;">
        <table class="table_listBaseByInvent" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>账号名称</th>
                <th>采购日期</th>
                <th>boss</th>
                <th>南邮</th>
                <th>福临门</th>
                <th>金龙鱼</th>
                <th>铁好</th>
                <th>乐维</th>
                <th>远恒</th>
                <th>优生活</th>
                <th>合计</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
            <#--<tr>-->
            <#--<td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="1300" height="600"></td>-->
            <#--</tr>-->
            <tr>
                <td style="text-align: center" colspan="14"><img src="${bath}/static/img/nodata.png" width="2000px;" height="600"></td>
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


    $(document).ready(function(){
        var statuss="";
        function Isempty(str){
            if(typeof (str) == 'undefined'){
                return '';
            }else{
                return str;
            }
        }

        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false;
        $("#accSearch").click(function(){
            if($("#datetimepicker_end").val() >= $("#datetimepicker_start").val()){
                lock = true;
                $("input[name='billStatesCheckbox']:checked").each(function(){
                    statuss  +=  $(this).parent("dd").data("name") + ','
                });
                var status  =  statuss.substring(0, statuss.length-1);
                if(status == ""){
                    lock = false;
                    response_ensure_alert("error","请先选择单据状态")
                }else{
                    $.post("../web/api/report/getSupplyAccountStatistic",{
                        enterpriseId:$(".enterpriseIdChoosen").val(),
                        startTime:$("#datetimepicker_start").val().replace("/","-").replace("/","-"),
                        endTime:$("#datetimepicker_end").val().replace("/","-").replace("/","-"),
                        statuss:status
                    },function(data){
                        var html='';
                        if(data.response == "success"){
                            var dataMap = data.data;
                            dataMap.map(function(ob){
                                html += '<tr>'
                                            +'<td>'+Isempty(ob.enterpriseName)+'</td>';
                                            if(ob.enterpriseName=="合计"){
                                                if(typeof (ob.purchaseDate)!="undefined"){
                                                    html +=  '<td>'+ob.purchaseDate+'</td>';
                                                }else{
                                                    html +=  '<td></td>';
                                                }
                                            }else{
                                                html +=  '<td>'+ob.purchaseDate+'</td>';
                                            }
                                            html +='<td>'+addCommas(ob.bossFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.nyFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.flmFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.jlyFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.thFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.lwFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.yhFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.yshFee,2)+'</td>'
                                            +'<td>'+addCommas(ob.totalFee,2)+'</td>'
                                        +'</tr>'
                            })
                                $('#itemContainer').empty().append(html)

                        }
                    })
                }
            }else{
                data_type_alert("结束时间必须大于开始时间且必填","error")
            }
        });


        $("#accExport").click(function(){
            var outputStr = '';
//            var statu='';
            if(lock) {
                outputStr += 'enterpriseId='+$(".enterpriseIdChoosen").val()+'&';
                outputStr += 'star='+$("#datetimepicker_start").val().replace("/","-").replace("/","-")+'&'
                outputStr += 'end='+$("#datetimepicker_end").val().replace("/","-").replace("/","-")+'&'
//                $("input[name='billStatesCheckbox']:checked").each(function(){
//                       statu += $(this).parent("dd").data("name")+','
//                });
//                if(statu != ""){
//                    outputStr += 'statuss='+ statu.substring(0, statu.length-1);
//                }
                outputStr += 'statuss='+ statuss;
                window.location.href = '../web/api/exportExcel/exportSupplyAccount?' + outputStr;
            }
            else{
                data_type_alert("请先搜索后再导出","error")
            }


        });
    });
</script>