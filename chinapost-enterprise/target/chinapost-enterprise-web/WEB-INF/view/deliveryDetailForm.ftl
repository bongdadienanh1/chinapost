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
    <title>供应商发货汇总表</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        /*.accountSearch{*/
            /*width: 1300px!important;*/
            /*height: 80px!important;*/
            /*transform: scale(0.95,0.95)!important;*/
            /*transform-origin: left top!important;*/
            /*-webkit-transform: scale(0.95,0.95)!important;*/
            /*-webkit-transform-origin: left top!important;*/
            /*-ms-transform: scale(0.95,0.95)!important;*/
            /*-ms-transform-origin: left top!important;*/
            /*-moz-transform: scale(0.95,0.95)!important;*/
            /*-moz-transform-origin: left top!important;*/

        /*}*/
        .accountSearch .allseachButton{
            width: 120px!important;
        }
        /*.table_listBaseByItem,.table_listBaseByInvent{*/
            /*transform: scale(0.75,0.75)!important;*/
            /*transform-origin: left top!important;*/
            /*-webkit-transform: scale(0.75,0.75)!important;*/
            /*-webkit-transform-origin: left top!important;*/
            /*-ms-transform: scale(0.75,0.75)!important;*/
            /*-ms-transform-origin: left top!important;*/
            /*-moz-transform: scale(0.75,0.75)!important;*/
            /*-moz-transform-origin: left top!important;*/
        /*}*/
    }
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
        width: 1600px;;
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

    #table_listBaseByInvent th:first-child, #table_listBaseByInvent td:first-child{
        width: 300px;
    }
    .table_listBaseByInvent span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBaseByInvent th, .table_listBaseByInvent td{
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
    .InventName{
        border-right: 1px solid #e5e5e5;
        border-left: 1px solid #e5e5e5;
    }
    #chooseGoodsType{
        position: absolute;
        left: 140px;
        top: 0px;
        background: #fff;
        cursor: pointer;
    }
    #chooseGoodsType dt, #chooseGoodsType dd{
        width: 100px;
        height: 20px;
        line-height: 20px;
        text-align: center;

    }
    #chooseGoodsType dd{
        border: 1px solid #e5e5e5;
        margin-top: -1px;
        display: none;
    }
    #chooseGoodsType dt:hover, #chooseGoodsType dd:hover{
        background: #54a6de;
        color: #fff;
    }


    #chooseBillStatus{
        position: absolute;
        left: 100px;
        top: 0px;
        background: #fff;
        cursor: pointer;
    }
    #chooseBillStatus dt{
        white-space: nowrap;
        overflow: hidden;
    }
    #chooseBillStatus dt, #chooseBillStatus dd{
        width: 115px;
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
        #accSearchbyInvent{
            margin-right: 20px;
        }
</style>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        $(document).on("mouseenter","#itemBrand",function(){
            $(this).children("dd").show()
        })
        $(document).on("mouseleave","#itemBrand",function(){
            $(this).children("dd").hide()
        })
        $(document).on("click","#itemBrand dd:not(#brandSearch)",function(){
            var id=$(this).data("id")
            var name = $(this).html()
            console.log(id,name)
            $(this).siblings("dt").attr("data-id",id)
            $(this).siblings("dt").html(name)
            $("#itemBrand dd").slideUp()
            $(this).parent().siblings("b").remove()
        })
        $.post("../web/api/goodsManager/getThirdName",{
            thirdName:""
        },function(data){
            if( data.response == 'success' ){
                $("#itemBrand dd:not(#brandSearch)").remove()
                var html = ""
                data.data.map(function(object){
                    html += '<dd title="'+ object.thirdName  +'" data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
                })
                $("#itemBrand").append(html)
                var id=$("#brandSearch").next().data("id")
                var name = $("#brandSearch").next().html()
                $("#brandSearch").siblings("dt").attr("data-id",id)
                $("#brandSearch").siblings("dt").html(name)
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');


        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
        $("#chooseGoodsType").mouseover(function(){
            $("#chooseGoodsType dd").show()
        })
        $("#chooseGoodsType").mouseout(function(){
            $("#chooseGoodsType dd").hide()
        })
        $("#chooseGoodsType dd").click(function(){
            var html = $(this).html()
            var dataId = $(this).data("id")
            $("#chooseGoodsType dt").attr("data-id",dataId);
            $("#chooseGoodsType dt").html(html);
            $("#chooseGoodsType").trigger("mouseout");
            $("#brandSearch input").val("")
            if(dataId == "0"){
                $.post("../web/api/goodsManager/getThirdName",{
                    thirdName:""
                },function(data){
                    if( data.response == 'success' ){
                        $("#itemBrand dd:not(#brandSearch)").remove()
                        var html = ""
                        data.data.map(function(object){
                            html += '<dd title="'+ object.thirdName  +'"  data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
                        })
                        $("#itemBrand").append(html)
                        var id=$("#brandSearch").next().data("id")
                        var name = $("#brandSearch").next().html()
                        $("#brandSearch").siblings("dt").attr("data-id",id)
                        $("#brandSearch").siblings("dt").html(name)
                    }
                    else{
                        response_ensure_alert('error',data.data.text,function(){

                        });
                    }
                },'json');

            }else{
                $.post("../web/api/goodsManager/getThirdEnterpriseName",{
                    thirdName:""
                },function(data){
                    if( data.response == 'success' ){
                        $("#itemBrand dd:not(#brandSearch)").remove()
                        var html = ""
                        data.data.map(function(object){
                            html += '<dd title="'+ object.enterpriseName  +'"  data-id="'+ object.enterpriseId +'">'+ object.enterpriseName +'</dd>'
                        })
                        $("#itemBrand").append(html)
                        var id=$("#brandSearch").next().data("id")
                        var name = $("#brandSearch").next().html()
                        $("#brandSearch").siblings("dt").attr("data-id",id)
                        $("#brandSearch").siblings("dt").html(name)
                    }
                    else{
                        response_ensure_alert('error',data.data.text,function(){

                        });
                    }
                },'json');
            }
        })

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
            if(  $("input[name='billStatesCheckbox']:checked").length == 5 ){
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
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  供应商发货汇总表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 290px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 250px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><div style="text-align: left!important; position: relative;margin-top: 0px;margin-left: -30px" class="allinputButton"><span style="display:inline-block;margin-left: 40px;color: #999">商品类型</span>
                <dl style="margin-top: 3px"  id="chooseGoodsType">
                    <dt data-id="0">集采商品</dt>
                    <dd data-id="0">集采商品</dd>
                    <dd data-id="1">自购商品</dd>
                </dl>
            </div> </li>
            <li style="width: 295px;"><div style="margin-left;20px;text-align: left!important; position: relative;margin-top: 0px;margin-left: -10px" class="allinputButton"><span style="display:inline-block;margin-left: 40px;color: #999">供应商</span>
                <dl id="itemBrand">
                    <dt data-id="0"id="brandAppend">请选择</dt>
                    <dd id="brandSearch"><input style="width: 100px!important;height: 18px!important;" class="allinputButton" placeholder="输入文字搜索" type="text"> </dd>
                </dl>
            </div> </li>

            <li>
                <div style="margin-left:20px;text-align: left!important; position: relative;margin-top: 0px" class="allinputButton"><span style="display:inline-block;margin-left: 20px;color: #999">单据状态</span>
                    <dl style="margin-top: 3px" id="chooseBillStatus">
                        <dt data-name="ALL_PICK">全部</dt>
                        <dd style="text-align: left;margin-left: 10px" data-name="ALL_PICK"><input type="checkbox" name="billStatesCheckboxAll" checked="checked"/>全部</dd>
                        <dd data-name="WAIT_DELIVERY"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待发货</span></dd>
                        <dd data-name="WAIT_RECEIVER"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待收货</span></dd>
                        <dd data-name="WAIT_EDIT"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待修改</span></dd>
                        <dd data-name="FINISHED"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>已完成</span></dd>
                        <dd data-name="PARTIAL_RECEIPT"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>部分收货</span></dd>
                    </dl>
            </li>
            <li><input class="allinputButton" placeholder="定货开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="定货结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="备货开始日期"   type="text" style="width:250px;" id="supply_datetimepicker_start"/><a  id="supply_date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="备货结束日期"   type="text" style="width:250px;" id="supply_datetimepicker_end"/><a  id="supply_date_end" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="入库开始日期"   type="text" style="width:250px;" id="instock_datetimepicker_start"/><a  id="instock_date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="入库结束日期"   type="text" style="width:250px;" id="instock_datetimepicker_end"/><a  id="instock_date_end" href="#"class="date_button"/></a></li>

            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索(按货品)" id="accSearchbyInvent" /><input class="allseachButton" type="button" value="搜索(按仓库)" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>


    <div style="width: 1600px;">
        <table id="table_listBaseByEnter" class="table_listBaseByInvent" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>网点名称</th>
                <th>货品名称</th>
                <th>货品编号</th>
                <th>货品类型</th>
                <th>发货数量</th>
                <th>货品结算价（元）</th>
                <th>金额（元）</th>
                <th>收货信息</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
            <#--<tr>-->
            <#--<td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="1300" height="600"></td>-->
            <#--</tr>-->
            <tr>
                <td style="text-align: center" colspan="9"><img src="${bath}/static/img/nodata.png" width="1600px;" height="600"></td>
            </tr>
            </tbody>
        </table>

        <table style="display: none" id="table_listBaseByInvent" class="table_listBaseByInvent" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>货品名称</th>
                <th>货品编号</th>
                <th>货品类型</th>
                <th>发货数量</th>
                <th>货品结算价（元）</th>
                <th>金额（元）</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer2">
            <#--<tr>-->
            <#--<td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="1300" height="600"></td>-->
            <#--</tr>-->
            <tr>
                <td style="text-align: center" colspan="9"><img src="${bath}/static/img/nodata.png" width="1300" height="600"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>
    <div id="holder2" class="allcpageTurnButton"></div>










    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>

<script type="text/javascript">
    var array = [];
    array[23] = ""
    array[24] = ""
    array[25] = ""
    array[26] = ""
    array[27] = ""
    array[28] = ""
    array[29] = ""
    array[5] = ""
    array[30] = ""
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
    $("#supply_date_start").click(function(e){
        $("#supply_datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#supply_datetimepicker_start").trigger("focus");
    });
    $("#supply_datetimepicker_start").blur(function(){
        $("#supply_datetimepicker_start").datetimepicker('destroy');
    });

    $("#supply_date_end").click(function(e){
        $("#supply_datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#supply_datetimepicker_end").trigger("focus");
    })
    $("#supply_datetimepicker_end").blur(function(){
        $("#supply_datetimepicker_end").datetimepicker('destroy');
    });

    $("#instock_date_start").click(function(e){
        $("#instock_datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#instock_datetimepicker_start").trigger("focus");
    });
    $("#instock_datetimepicker_start").blur(function(){
        $("#instock_datetimepicker_start").datetimepicker('destroy');
    });

    $("#instock_date_end").click(function(e){
        $("#instock_datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#instock_datetimepicker_end").trigger("focus");
    })
    $("#instock_datetimepicker_end").blur(function(){
        $("#instock_datetimepicker_end").datetimepicker('destroy');
    });




    $(document).ready(function(){
        $(document).on("input","#brandSearch input[type='text']",function(){
            var goodsInfoType = $("#chooseGoodsType dt").attr("data-id")
            var brandName = $(this).val()
            if( goodsInfoType == "0"){
                $.post("../web/api/goodsManager/getThirdName",{
                    thirdName:brandName
                },function(data){
                    if( data.response == 'success' ){
                        $("#brandSearch").siblings("dd").remove()
                        var html = ""
                        data.data.map(function(object){
                            html += '<dd style="display:block" data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
                        })
                        $("#brandSearch").after(html)
                    }
                    else{
                        response_ensure_alert('error',data.data.text,function(){

                        });
                    }
                },'json');
            }else{
                $.post("../web/api/goodsManager/getThirdEnterpriseName",{
                    thirdName:brandName
                },function(data){
                    if( data.response == 'success' ){
                        $("#brandSearch").siblings("dd").remove()
                        var html = ""
                        data.data.map(function(object){
                            html += '<dd style="display:block" data-id="'+ object.enterpriseId +'">'+ object.enterpriseName +'</dd>'
                        })
                        $("#brandSearch").after(html)
                    }
                    else{
                        response_ensure_alert('error',data.data.text,function(){

                        });
                    }
                },'json');
            }

        })

        var  excelType = ""
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false
        $("#accSearch").click(function(){
            excelType = 1
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  && $("#instock_datetimepicker_end").val() >= $("#instock_datetimepicker_start").val() && $("#supply_datetimepicker_end").val() >= $("#supply_datetimepicker_start").val() ){
                lock=true
                $("#accSearch").css("font-weight","bold").css("background","#0584c5")
                $("#accSearchbyInvent").removeAttr("style")
                array[23] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[24] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[25] =$("#supply_datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[26] =$("#supply_datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[27] =$("#instock_datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[28] =$("#instock_datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                var goodsInfoType = $("#chooseGoodsType dt").attr("data-id");
                console.log(array[21])
                if (array[21] == "3"){
                    array[21] = ""
                }
                array[29] = ""
                $("input[name='billStatesCheckbox']:checked").each(function(){
                    array[29]  +=  $(this).parent("dd").data("name") + ','
                })
                array[29]  =  array[29].substring(0, array[29].length-1);
                if(array[29] == ""){
                    response_ensure_alert("error","请先选择单据状态")
                }else{
                    if( goodsInfoType == "0"){
                        $("#table_listBaseByEnter").show()
                        $("#holder").show()
                        $("#table_listBaseByInvent").hide()
                        $("#holder2").hide()
                        array[5] = $("#brandAppend").attr("data-id")
                        array[30] = ""
                        ajaxPages("../web/api/report/getSupplyByThirdAndEnterprise","itemContainer","holder","deliveryDetail",10,'','',array,function(){

                        });
                    }else{
                        $("#table_listBaseByEnter").show()
                        $("#holder").show()
                        $("#table_listBaseByInvent").hide()
                        $("#holder2").hide()
                        array[30] = $("#brandAppend").attr("data-id")
                        array[5] = ""
                        ajaxPages("../web/api/report/getSupplyByThirdAndEnterprise","itemContainer","holder","deliveryDetail",10,'','',array,function(){

                        });
                    }

                }

            }else{
                data_type_alert("结束时间必须大于开始时间且必填","error")
            }

        });

        $("#accSearchbyInvent").click(function(){
            excelType =0
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  && $("#instock_datetimepicker_end").val() >= $("#instock_datetimepicker_start").val() && $("#supply_datetimepicker_end").val() >= $("#supply_datetimepicker_start").val() ){
                lock=true
                $("#accSearchbyInvent").css("font-weight","bold").css("background","#0584c5")
                $("#accSearch").removeAttr("style")
                array[23] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[24] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[25] =$("#supply_datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[26] =$("#supply_datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[27] =$("#instock_datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[28] =$("#instock_datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                var goodsInfoType = $("#chooseGoodsType dt").attr("data-id");
                console.log(array[21])
                if (array[21] == "3"){
                    array[21] = ""
                }
                array[29] = ""
                $("input[name='billStatesCheckbox']:checked").each(function(){
                    array[29]  +=  $(this).parent("dd").data("name") + ','
                })
                array[29]  =  array[29].substring(0, array[29].length-1);
                if(array[29] == ""){
                    response_ensure_alert("error","请先选择单据状态")
                }else{
                    if( goodsInfoType == "0"){
                        $("#table_listBaseByEnter").hide()
                        $("#holder").hide()
                        $("#table_listBaseByInvent").show()
                        $("#holder2").show()
                        array[5] = $("#brandAppend").attr("data-id")
                        array[30] = ""
                        ajaxPages("../web/api/report/getSupplyByThirdAndGoods","itemContainer2","holder2","deliveryDetail2",10,'','',array,function(){

                        });
                    }else{
                        $("#table_listBaseByEnter").hide()
                        $("#holder").hide()
                        $("#table_listBaseByInvent").show()
                        $("#holder2").show()
                        array[30] = $("#brandAppend").attr("data-id")
                        array[5] = ""
                        ajaxPages("../web/api/report/getSupplyByThirdAndGoods","itemContainer2","holder2","deliveryDetail2",10,'','',array,function(){

                        });
                    }

                }

            }else{
                data_type_alert("结束时间必须大于开始时间且必填","error")
            }

        });

        $("#accExport").click(function(){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
            }
            if( array[23] != undefined ){
                outputStr += '&orderStartTime=' + array[23];
            }
            if( array[24] != undefined ){
                outputStr += '&orderEndTime=' + array[24];
            }
            if( array[25] != undefined ){
                outputStr += '&readyStartTime=' + array[25];
            }
            if( array[26] != undefined ){
                outputStr += '&readyEndTime=' + array[26];
            }
            if( array[27] != undefined ){
                outputStr += '&warehouseStartTime=' + array[27];
            }
            if( array[28] != undefined ){
                outputStr += '&warehouseEndTime=' + array[28];
            }
            if( array[29] != undefined ){
                outputStr += '&billStatuss=' + array[29];
            }
            var goodsInfoType = $("#chooseGoodsType dt").attr("data-id");
            var thirdName = $("#itemBrand dt").html();
            if( excelType == "0"){
                if(goodsInfoType == "0"){
                    outputStr += '&thirdId=' + array[5];
                    outputStr += '&thirdName=' + thirdName;
                }else{
                    outputStr += '&thirdEnterpriseId=' + array[30];
                    outputStr += '&thirdName=' + thirdName;
                }
                window.location.href = '../web/api/exportExcel/exportSupplyByThirdAndGoods?' + outputStr;
            }else if(excelType == "1"){
                if(goodsInfoType == "0"){
                    outputStr += '&thirdId=' + array[5];
                    outputStr += '&thirdName=' + thirdName;
                }else{
                    outputStr += '&thirdEnterpriseId=' + array[30];
                    outputStr += '&thirdName=' + thirdName;
                }
                window.location.href = '../web/api/exportExcel/exportSupplyByThirdAndEnterprise?' + outputStr;
            }else{
                data_type_alert("请先搜索后再导出","error")
            }


        });
    });
</script>