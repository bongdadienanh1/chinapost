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
    <title>库存详情表</title>
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
        .accountSearch .allseachButton{
            width: 120px!important;
        }
    }
    .accountSearch{
        width:1600px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        width:300px;
        height:32px;
        line-height:32px;
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
    .table_listBaseByItem{
        margin-left: 20px;
        width: 1600px;
        display: none;
    }
    .table_listBaseByItem span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBaseByItem th, .table_listBaseByItem td{
        height: 40px;
        text-align: left;
        word-break: break-all;
        border-bottom: 1px solid #e5e5e5;
    }
    .table_listBaseByItem th b{
        margin-left: 10px;
        display: inline-block;
        width: 15px;
        height: 15px;
        line-height: 13px;
        text-align: center;
        border: 1px solid #e5e5e5;
        cursor: pointer;
        border-radius: 15px;
        color: #54a6de;
    }
    .table_listBaseByInvent{
        margin-left: 20px;
        width: 1600px;
        table-layout: fixed;
        display: none;
    }
    .table_listBaseByInventDetail{
        table-layout: fixed;
        width: 100%;
    }
    .table_listBaseByInventDetail th{
        height: 40px;
        text-align: left;
        border-bottom: none!important;
    }
    .table_listBaseByInvent th:first-child, .table_listBaseByInvent td:first-child{
        width: 100px;
    }
    .table_listBaseByInventDetail th:first-child, .table_listBaseByInventDetail td:first-child{
        width: auto;
    }
    table_listBaseByInventDetail
    .table_listBaseByInvent span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBaseByInvent th, .table_listBaseByInvent td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
        word-break: break-all;
    }
    .table_listBaseByInvent th b{
        margin-left: 10px;
        display: inline-block;
        width: 15px;
        height: 15px;
        line-height: 13px;
        text-align: center;
        border: 1px solid #e5e5e5;
        cursor: pointer;
        border-radius: 15px;
        color: #54a6de;
    }
    #holderByItem{
        margin: 30px 0px;
    }
    #holderByInvent{
        margin: 30px 0px;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
    }
    .monthAdd,.monthReduce,.reimburse,.monthAddByInvent,.monthReduceByInvent,.reimburseByInvent{
        display: none;
    }
    #year,#mouth{
        display: none;
        background: #fff;
        border: 1px solid #e5e5e5;
    }
    #year{
        width: 250px;
        height: 30px;
        text-align: center;
        position: absolute;
        top: 40px;
        z-index: 2;
    }
    #mouth{
        width: 250px;
        height: 200px;
        position: absolute;
        top: 69px;
    }
    #mouth a{
        display: inline-block;
        width: 60px;
        margin: 5px;
    }
    #year a{
        display: block;
        width: 50px;
    }
    #chooseYear{
        width: 50px;
        height: 30px;
        overflow: hidden;
        margin-left:100px;
        transition: all 0.5s;
        background: #fff;
    }
    #year a, #mouth a{

        height: 30px;
        text-align: center;
        line-height: 30px;
    }
    #year a:hover, #mouth a:hover{
        color: #fff;
        background: #54a6de;
    }
    #year a.yearOn, #mouth a.mouthOn{
        color: #fff;
        background: #54a6de;
    }
    #accSearchByInvent{
        margin-left: 20px;
    }
</style>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
        $(document).on("click","#monthAdd",function(){
            if($(this).html() == "+"){
                $(".monthAdd").show()
                $(this).html("-")
            }else if($(this).html() == "-"){
                $(".monthAdd").hide()
                $(this).html("+")
            }
        })
        $(document).on("click","#monthReduce",function(){
            if($(this).html() == "+"){
                $(".monthReduce").show()
                $(this).html("-")
            }else if($(this).html() == "-"){
                $(".monthReduce").hide()
                $(this).html("+")
            }
        })
        $(document).on("click","#reimburse",function(){
            if($(this).html() == "+"){
                $(".reimburse").show()
                $(this).html("-")
            }else if($(this).html() == "-"){
                $(".reimburse").hide()
                $(this).html("+")
            }
        })
        $(document).on("click","#monthAddByInvent",function(){
            if($(this).html() == "+"){
                $(".monthAddByInvent").show()
                $(this).html("-")
            }else if($(this).html() == "-"){
                $(".monthAddByInvent").hide()
                $(this).html("+")
            }
        })
        $(document).on("click","#monthReduceByInvent",function(){
            if($(this).html() == "+"){
                $(".monthReduceByInvent").show()
                $(this).html("-")
            }else if($(this).html() == "-"){
                $(".monthReduceByInvent").hide()
                $(this).html("+")
            }
        })
        $(document).on("click","#reimburseByInvent",function(){
            if($(this).html() == "+"){
                $(".reimburseByInvent").show()
                $(this).html("-")
            }else if($(this).html() == "-"){
                $(".reimburseByInvent").hide()
                $(this).html("+")
            }
        })
        $(document).on("mouseover","#year a",function(){
            $(this).addClass("yearOn").siblings().removeClass("yearOn")
            var allyear = $("#datetimepicker_start").val().split("-")
            var year = $(this).html()
            $("#yearShow").html(year)
            $("#datetimepicker_start").val(year + '-' + allyear[1])
        })
        $(document).on("blur","#datetimepicker_start",function(){
            $("#year").hide()
            $("#mouth").hide()
        })
        $(document).on("mouseover","#mouth a",function(){
            $(this).addClass("mouthOn").siblings().removeClass("mouthOn")
            var allyear = $("#datetimepicker_start").val().split("-")
            var month = $(this).data("month")
            $("#datetimepicker_start").val(allyear[0] + '-' + month)

        })
//        $("#staticByTime").click(function(){
//            var staticByTime = $("#staticInput").val()
//            $.post("../web/api/report/staticByTime",{
//                date:staticByTime
//            },function(data){
//                if( data.response == 'success' ){
//                  alert("成功")
//                }
//            },'json');
//        })
    })
</script>
<body style="background: #fff">
    <div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  库存详情表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="选择月份"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
                <div id="year"></div>
                <div id="mouth">
                    <a data-month="1">一月</a><a data-month="2">二月</a><a data-month="3">三月</a><a data-month="4">四月</a><a data-month="5">五月</a><a data-month="6">六月</a><a data-month="7">七月</a><a data-month="8">八月</a><a data-month="9">九月</a><a data-month="10">十月</a><a data-month="11">十一月</a><a data-month="12">十二月</a>
                </div>
            </li>
            <li><input class="allinputButton" placeholder="货品名称"   type="text" style="width:250px;" id="ItemName"/></li>
            <li><input class="allinputButton" placeholder="货品编号"   type="text" style="width:250px;" id="ItemNumber"/></li>
            <#--<li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>-->
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索(按货品)" id="accSearchByItem" /><input class="allseachButton" type="button" value="搜索(按仓库)" id="accSearchByInvent" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
            <#--<li><input type="text" id="staticInput"><input type="button" value="设置" id="staticByTime"> </li>-->
        </ul>
    </div>


    <div style="width: 1600px">
        <img id="imgSearch" src="${bath}/static/img/nodata.png" width="1600px" height="600">
        <table class="table_listBaseByInvent" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>仓库</th>
                <th>
                    <table class="table_listBaseByInventDetail" cellpadding="0" cellspacing="0" align="right">
                        <tr>
                            <th>货品名称</th>
                            <th>货品规格</th>
                            <th>货品编号</th>
                            <th>上月结存</th>
                            <th>本月新增<b id="monthAddByInvent">+</b></th>
                            <th class="monthAddByInvent">商品入库</th>
                            <th class="monthAddByInvent">调入</th>
                            <th class="monthAddByInvent">退货</th>
                            <th>本月消耗<b id="monthReduceByInvent">+</b></th>
                            <th class="monthReduceByInvent">调出</th>
                            <th class="monthReduceByInvent">兑换</th>
                            <th>损溢数量<b id="reimburseByInvent">+</b></th>
                            <th class="reimburseByInvent">报损</th>
                            <th class="reimburseByInvent">报溢</th>
                            <th>本月结余</th>
                        </tr>
                    </table>
                </th>

            </tr>
            </thead>
            <tbody align="center" id="itemContainerByInvent">
              <#--<tr>-->
                 <#--<td style="text-align: center" colspan="6"></td>-->
              <#--</tr>-->
              <tr>
                  <td>库存</td>
                  <td>
                      <table class="table_listBaseByInventDetail" cellpadding="0" cellspacing="0" align="right">
                          <tr>
                              <td>货品名称</td>
                              <td>货品规格</td>
                              <td>货品编号</td>
                              <td>上月结存</td>
                              <td>本月新增</td>
                              <td class="monthAddByInvent">商品入库</td>
                              <td class="monthAddByInvent">调入</td>
                              <td class="monthAddByInvent">退货</td>
                              <td>本月消耗</td>
                              <td class="monthReduceByInvent">调出</td>
                              <td class="monthReduceByInvent">兑换</td>
                              <td>损溢数量</td>
                              <td class="reimburseByInvent">报损</td>
                              <td class="reimburseByInvent">报溢</td>
                              <td>本月结余</td>
                          </tr>
                          <tr>
                              <td>货品名称</td>
                              <td>货品规格</td>
                              <td>货品编号</td>
                              <td>上月结存</td>
                              <td>本月新增</td>
                              <td class="monthAddByInvent">商品入库</td>
                              <td class="monthAddByInvent">调入</td>
                              <td class="monthAddByInvent">退货</td>
                              <td>本月消耗</td>
                              <td class="monthReduceByInvent">调出</td>
                              <td class="monthReduceByInvent">兑换</td>
                              <td>损溢数量</td>
                              <td class="reimburseByInvent">报损</td>
                              <td class="reimburseByInvent">报溢</td>
                              <td>本月结余</td>
                          </tr>
                          <tr>
                              <td>货品名称</td>
                              <td>货品规格</td>
                              <td>货品编号</td>
                              <td>上月结存</td>
                              <td>本月新增</td>
                              <td class="monthAddByInvent">商品入库</td>
                              <td class="monthAddByInvent">调入</td>
                              <td class="monthAddByInvent">退货</td>
                              <td>本月消耗</td>
                              <td class="monthReduceByInvent">调出</td>
                              <td class="monthReduceByInvent">兑换</td>
                              <td>损溢数量</td>
                              <td class="reimburseByInvent">报损</td>
                              <td class="reimburseByInvent">报溢</td>
                              <td>本月结余</td>
                          </tr>
                      </table>
                  </td>

              </tr>
            </tbody>
        </table>




        <table class="table_listBaseByItem" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>货品名称</th>
                <th>货品规格</th>
                <th>货品编号</th>
                <th>上月结存</th>
                <th>本月新增<b id="monthAdd">+</b></th>
                <th class="monthAdd">商品入库</th>
                <th class="monthAdd">调入</th>
                <th class="monthAdd">退货</th>
                <th>本月消耗<b id="monthReduce">+</b></th>
                <th class="monthReduce">调出</th>
                <th class="monthReduce">兑换</th>
                <th>损溢数量<b id="reimburse">+</b></th>
                <th class="reimburse">报损</th>
                <th class="reimburse">报溢</th>
                <th>本月结余</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainerByItem">
            <#--<tr>-->
            <#--<td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="1400" height="600"></td>-->
            <#--</tr>-->
            <tr>
                <td>货品名称</td>
                <td>货品规格</td>
                <td>货品编号</td>
                <td>上月结存</td>
                <td>本月新增</td>
                <td class="monthAdd">商品入库</td>
                <td class="monthAdd">调入</td>
                <td class="monthAdd">退货</td>
                <td>本月消耗</td>
                <td class="monthReduce">调出</td>
                <td class="monthReduce">兑换</td>
                <td>损溢数量</td>
                <td class="reimburse">报损</td>
                <td class="reimburse">报溢</td>
                <td>本月结余</td>
            </tr>
            <tr>
                <td>货品名称</td>
                <td>货品规格</td>
                <td>货品编号</td>
                <td>上月结存</td>
                <td>本月新增</td>
                <td class="monthAdd">商品入库</td>
                <td class="monthAdd">调入</td>
                <td class="monthAdd">退货</td>
                <td>本月消耗</td>
                <td class="monthReduce">调出</td>
                <td class="monthReduce">兑换</td>
                <td>损溢数量</td>
                <td class="reimburse">报损</td>
                <td class="reimburse">报溢</td>
                <td>本月结余</td>
            </tr>
            <tr>
                <td>货品名称</td>
                <td>货品规格</td>
                <td>货品编号</td>
                <td>上月结存</td>
                <td>本月新增</td>
                <td class="monthAdd">商品入库</td>
                <td class="monthAdd">调入</td>
                <td class="monthAdd">退货</td>
                <td>本月消耗</td>
                <td class="monthReduce">调出</td>
                <td class="monthReduce">兑换</td>
                <td>损溢数量</td>
                <td class="reimburse">报损</td>
                <td class="reimburse">报溢</td>
                <td>本月结余</td>
            </tr>
            </tbody>
        </table>


    </div>
    <div id="holderByInvent" class="allcpageTurnButton"></div>
    <div id="holderByItem" class="allcpageTurnButton"></div>











    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>

<script type="text/javascript">
    var array = [];
    array[22] = '';//idCard
    array[16] =""
    array[0] = '';//goodsInfoName
    array[1] = "" ;//goodsInfoItemNo
//    var todayDate = new Date();//获取今天日期
//    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
//    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
//    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");
    var getYear = new Date().getFullYear();
    var getmonth = new Date().getMonth();
    if(getmonth == 0){
        $("#datetimepicker_start").val((getYear - 1) + '-12')
    }else{
        $("#datetimepicker_start").val(getYear + '-' + getmonth)
    }

    $(document).ready(function(){
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var excelType = ""
        $("#accSearchByInvent").click(function(){
            excelType =0
            $(".table_listBaseByInvent,#holderByInvent").show()
            $(".table_listBaseByItem,#holderByItem").hide()
            $("#imgSearch").hide()
            var enterpriseId = $(".enterpriseIdChoosen").val();
            var date  = $("#datetimepicker_start").val()
            array[16] = enterpriseId
            array[22] = date
            array[0] = ""
            array[1] = ""
            $("#accSearchByInvent").css("font-weight","bold").css("background","#0584c5")
            $("#accSearchByItem").removeAttr("style")
            if( $("#ItemNumber").val() == "" && $("#ItemName").val() == "" ){
                ajaxPages("../web/api/report/getInventoryHistoryByEnterprise","itemContainerByInvent","holderByInvent","accSearchByInvent",1,'','',array,function(data){

                });
            }else{
                $.post("../web/api/report/getHistoryGoodsByEnterprise",{
                    enterpriseId:enterpriseId,
                    date:date,
                    goodsInfoName:$("#ItemName").val(),
                    goodsInfoItemNo:$("#ItemNumber").val()
                },function(data){
                    if( data.response == 'success' ){
                        var trHtml = ""
                        data.data.map(function(o){
                            trHtml += '<tr>';
                            trHtml += '<td style="border-right: 1px solid #e5e5e5">'+ o.enterpriseName +'</td>'
                            trHtml += '<td>'
                            trHtml += '<table class="table_listBaseByInventDetail" cellpadding="0" cellspacing="0" align="right">'
                            o.formList.map(function(ob){
                                trHtml += '<tr>'
                                trHtml += '<td>'+ handleUndefined(ob.goodsInfoName) + '</td>'
                                trHtml += '<td>' + handleUndefined(ob.goodsSpecString) + '</td>'
                                trHtml += '<td>' + handleUndefined(ob.goodsInfoItemNo) + '</td>'
                                trHtml += '<td>' + addCommas(ob.lastMonthInventory,0) + '</td>'
                                trHtml += '<td>' + addCommas(ob.increaseAmount,0) + '</td>'
                                if($("#monthAddByInvent").html() == "-"){
                                    trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(ob.instockAmount,0) + '</td>'
                                    trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(ob.transferIn,0) + '</td>'
                                    trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(ob.orderBack,0) + '</td>'
                                }else{
                                    trHtml += '<td class="monthAddByInvent">' + addCommas(ob.instockAmount,0) + '</td>'
                                    trHtml += '<td class="monthAddByInvent">' + addCommas(ob.transferIn,0) + '</td>'
                                    trHtml += '<td class="monthAddByInvent">' + addCommas(ob.orderBack,0) + '</td>'
                                }

                                trHtml += '<td>' + addCommas(ob.consumeAmount,0) + '</td>'
                                if($("#monthReduceByInvent").html() == "-"){
                                    trHtml += '<td style="display: table-cell;"  class="monthReduceByInvent">' + addCommas(ob.transferOut,0) + '</td>'
                                    trHtml += '<td style="display: table-cell;"  class="monthReduceByInvent">' + addCommas(ob.orderConsume,0) + '</td>'
                                }else{
                                    trHtml += '<td class="monthReduceByInvent">' + addCommas(ob.transferOut,0) + '</td>'
                                    trHtml += '<td class="monthReduceByInvent">' + addCommas(ob.orderConsume,0) + '</td>'
                                }

                                trHtml += '<td>' + addCommas(ob.gainsAmount,0) + '</td>'

                                if($("#reimburseByInvent").html() == "-"){
                                    trHtml += '<td style="display: table-cell;"  class="reimburseByInvent">' + addCommas(ob.lessReport,0) + '</td>'
                                    trHtml += '<td style="display: table-cell;"  class="reimburseByInvent">' + addCommas(ob.moreReport,0) + '</td>'
                                }else{
                                    trHtml += '<td class="reimburseByInvent">' + addCommas(ob.lessReport,0) + '</td>'
                                    trHtml += '<td class="reimburseByInvent">' + addCommas(ob.moreReport,0) + '</td>'
                                }

                                trHtml += '<td>' + addCommas(ob.inventoryAmount,0) + '</td>'
                                trHtml += '</tr>'
                            })

                            trHtml += '</table>'
                            trHtml += '</td>'
                            trHtml += '</tr>'

                        })
                        $("#itemContainerByInvent").empty().append(trHtml)
                        $("#holderByItem").empty()
                        $("#holderByInvent").empty()
                    }
                    else{
                        response_ensure_alert('error',data.data.text,function(){

                        });
                    }
                },'json');
            }


        });
        $("#accSearchByItem").click(function(){
            excelType = 1
            $(".table_listBaseByInvent,#holderByInvent").hide()
            $(".table_listBaseByItem,#holderByItem").show()
            $("#imgSearch").hide()
            $("#accSearchByItem").css("font-weight","bold").css("background","#0584c5")
            $("#accSearchByInvent").removeAttr("style")
            var enterpriseId = $(".enterpriseIdChoosen").val();
            var date  = $("#datetimepicker_start").val()
            array[0] = $("#ItemName").val();//idCard
            array[1] = $("#ItemNumber").val();//idCard
            array[16] = enterpriseId
            array[22] = date
            ajaxPages("../web/api/report/getInventoryHistoryByGoodsInfo","itemContainerByItem","holderByItem","accSearchByItem",10,'','',array,function(){

            });
        });
        $(document).on("mouseover","#chooseYear",function(){
            $(this).stop().css("height","220px")

        })
        $(document).on("mouseout","#chooseYear",function(){
            $(this).stop().css("height","30px")
        })
        $("#date_start").click(function(){
            $("#year").show()
            $("#mouth").show()
            var year = new Date().getFullYear();
            var html = "<div id='chooseYear'><a id='yearShow'>" + year
            for(var i = year ; i >= year -5  ;i--){
                html += '<a>'+ i +'</a>'
            }
            html += '</a></div>'
            $("#datetimepicker_start").focus()
            $("#year").empty().append(html)
        })
        $("#accExport").click(function(){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
            }
            if( array[22] != undefined ){
                outputStr += '&date=' + array[22];
            }

            console.log(excelType)
            if( excelType == "0"){
                if($("#ItemName").val() == "" && $("#ItemNumber").val() == ""){
                    window.location.href = '../web/api/exportExcel/exportInventoryHistoryByEnterprise?' + outputStr;
                }else{
                    if( array[0] != undefined ){
                        array[0] = $("#ItemName").val();
                        outputStr += '&goodsInfoName=' + array[0];
                    }
                    if( array[1] != undefined ){
                        array[1] = $("#ItemNumber").val();
                        outputStr += '&goodsInfoItemNo=' + array[1];
                    }
                    window.location.href = '../web/api/exportExcel/exportHistoryGoodsByEnterprise?' + outputStr;
                }

            }else if(excelType == "1"){
                if( array[0] != undefined ){
                    array[0] = $("#ItemName").val();
                    outputStr += '&goodsInfoName=' + array[0];
                }
                if( array[1] != undefined ){
                    array[1] = $("#ItemNumber").val();
                    outputStr += '&goodsInfoItemNo=' + array[1];
                }
                window.location.href = '../web/api/exportExcel/exportInventoryHistoryByGoodsInfo?' + outputStr;
            }else{
                data_type_alert("请先搜索后再导出","error")
            }


        });
    });
</script>