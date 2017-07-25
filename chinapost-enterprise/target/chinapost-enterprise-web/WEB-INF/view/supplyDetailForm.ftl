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
    <title>入库明细表</title>
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
        width: 2200px;
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
    #chooseGoodsType{
        position: absolute;
        left: 140px;
        top: 3px;
        background: #fff;
        cursor: pointer;
    }
    #chooseGoodsType dt, #chooseGoodsType dd{
        width: 100px;
        height: 20px;
        line-height: 20px;
        background: #fff;
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
    .InventAddress{
        border-right: 1px solid #e5e5e5;
        border-left: 1px solid #e5e5e5;
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
                var html = "<dd data-id=''>请选择</dd>"
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
                        var html = "<dd data-id=''>请选择</dd>"
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
                        var html = "<dd data-id=''>请选择</dd>"
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
            if(  $("input[name='billStatesCheckbox']:checked").length == 3 ){
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
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  入库明细表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 290px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 250px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="创建入库单开始时间"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="创建入库单结束时间"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li><div style="margin-top:0px;text-align: left!important; position: relative;" class="allinputButton"><span style="display:inline-block;margin-left: 40px;color: #999">商品类型</span>
                <dl id="chooseGoodsType">
                    <dt data-id="0">集采商品</dt>
                    <dd data-id="0">集采商品</dd>
                    <dd data-id="1">自购商品</dd>
                </dl>
            </div> </li>

            <li style="width: 295px;"><div style="margin-left;20px;text-align: left!important; position: relative;margin-top: 0px;margin-left: -10px" class="allinputButton"><span style="display:inline-block;margin-left: 40px;color: #999">供应商</span>
                <dl id="itemBrand">
                    <dt data-id="0"id="brandAppend">请选择</dt>
                    <dd data-id="">请选择</dd>
                    <dd id="brandSearch"><input style="width: 100px!important;height: 18px!important;" class="allinputButton" placeholder="输入文字搜索" type="text"> </dd>
                </dl>
            </div> </li>
            <li>
                <div style="margin-left:20px;margin-top:0px;text-align: left!important; position: relative;" class="allinputButton"><span style="display:inline-block;margin-left: 20px;color: #999">单据状态</span>
                    <dl id="chooseBillStatus">
                        <dt data-name="ALL_PICK">全部</dt>
                        <dd style="text-align: left;margin-left: 10px" data-name="ALL_PICK"><input type="checkbox" name="billStatesCheckboxAll" checked="checked"/>全部</dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="STAY_IN"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待入库</span></dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="FINISHED"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>已完成</span></dd>
                        <dd style="text-align: left;margin-left: 22px" data-name="PARTIAL_RECEIPT"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>部分入库</span></dd>
                    </dl>
            </li>

            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>


    <div style="width: 2300px;">
        <table class="table_listBaseByInvent" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>网点名称</th>
                <th>供应商名称</th>
                <th>单据编号</th>
                <th>发货日期</th>
                <th>货品名称</th>
                <th>货品编号</th>
                <th>货品类型</th>
                <th>单价</th>
                <th>采购数量</th>
                <th>入库数量</th>
                <th>入库金额（元）</th>
                <th>状态</th>
                <th>联系人</th>
                <th>联系电话</th>
                <th>地址</th>
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
    var array = [];
    array[21] = "";
    array[8] = "";
    array[9] = "";
    array[29] = "";
    array[5] = "";
    array[30] = "";
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
//    $("#supply_date_start").click(function(e){
//        $("#supply_datetimepicker_start").datetimepicker({
//            step:5,
//            lang:'ch',
//            timepicker:false,
//            format:'Y-m-d',
//            formatDate:'Y-m-d'
//        });
//        $("#supply_datetimepicker_start").trigger("focus");
//    });
//    $("#supply_datetimepicker_start").blur(function(){
//        $("#supply_datetimepicker_start").datetimepicker('destroy');
//    });
//
//    $("#supply_date_end").click(function(e){
//        $("#supply_datetimepicker_end").datetimepicker({
//            step:5,
//            lang:'ch',
//            timepicker:false,
//            format:'Y-m-d',
//            formatDate:'Y-m-d'
//        });
//        $("#supply_datetimepicker_end").trigger("focus");
//    })
//    $("#supply_datetimepicker_end").blur(function(){
//        $("#supply_datetimepicker_end").datetimepicker('destroy');
//    });
//
//    $("#instock_date_start").click(function(e){
//        $("#instock_datetimepicker_start").datetimepicker({
//            step:5,
//            lang:'ch',
//            timepicker:false,
//            format:'Y-m-d',
//            formatDate:'Y-m-d'
//        });
//        $("#instock_datetimepicker_start").trigger("focus");
//    });
//    $("#instock_datetimepicker_start").blur(function(){
//        $("#instock_datetimepicker_start").datetimepicker('destroy');
//    });
//
//    $("#instock_date_end").click(function(e){
//        $("#instock_datetimepicker_end").datetimepicker({
//            step:5,
//            lang:'ch',
//            timepicker:false,
//            format:'Y-m-d',
//            formatDate:'Y-m-d'
//        });
//        $("#instock_datetimepicker_end").trigger("focus");
//    })
//    $("#instock_datetimepicker_end").blur(function(){
//        $("#instock_datetimepicker_end").datetimepicker('destroy');
//    });




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

        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()){
                lock=true
                array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                var goodsInfoType = $("#chooseGoodsType dt").attr("data-id");
                array[21]=goodsInfoType;
                console.log(array[21])
                if (array[21] == "3"){
                    array[21] = ""
                }
                array[29] = "";
                $("input[name='billStatesCheckbox']:checked").each(function(){
                    array[29]  +=  $(this).parent("dd").data("name") + ','
                })
                array[29]  =  array[29].substring(0, array[29].length-1);
                if(array[29] == ""){
                    response_ensure_alert("error","请先选择单据状态")
                }else{
                    if(goodsInfoType == "0"){
                        array[5] = $("#brandAppend").attr("data-id");
                        array[30] = "";
                        ajaxPages("../web/api/report/getSupplyDetails","itemContainer","holder","supplyDetail",10,'','',array,function(){

                        });
                    }else {
                        array[30] = $("#brandAppend").attr("data-id")
                        array[5] = ""
                        ajaxPages("../web/api/report/getSupplyDetails","itemContainer","holder","supplyDetail",10,'','',array,function(){

                        });
                    }
                }

            }else{
                data_type_alert("结束时间必须大于开始时间且必填","error")
            }

        });


        $("#accExport").click(function(){
            var outputStr = '';
            if(lock) {
                if (array[16] != undefined) {
                    outputStr += 'enterpriseId=' + array[16];
                }
                if (array[8] != undefined) {
                    outputStr += '&start=' + array[8];
                }
                if (array[9] != undefined) {
                    outputStr += '&end=' + array[9];
                }
                if (array[29] != undefined) {
                    outputStr += '&billStatuss=' + array[29];
                }
                if (array[21] != undefined) {
                    outputStr += '&goodsInfoType=' + array[21];
                }
                if(array[5] != undefined){
                    outputStr+='&thirdId='+array[5]
                }
                window.location.href = '../web/api/exportExcel/exportSupplyDetails?' + outputStr;
            }else{
                data_type_alert("请先搜索后再导出","error")
            }

        });
    });
</script>