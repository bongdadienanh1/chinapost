
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商城第三方后台-货品管理</title>
<#assign basePath=request.contextPath />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/third.css?version=${VERSION}"/>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css?v=20151016234"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/material.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/ripples.css?v=20151016234" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/css/style.css?version=${VERSION}"/>
    <link rel="stylesheet" href="${basePath}/static/css/select2.min.css"/>
    <link rel="stylesheet" href="${basePath}/static/css/bootstrap-datetimepicker.min.css" >
    <script type="text/javascript" src="${basePath}/static/js/jquery.min.js?v=20151016234"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/ripples.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/material.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/third.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/goods_vali.js?v=20151016234"></script>
<#--<script type="text/javascript" src="${basePath}/static/js/goods/thirdgoods.js?v=20170702"></script>-->
    <script type="text/javascript" src="${basePath}/static/js/goods/thirdOrderList.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/newupload.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/plugins/iframeTools.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/jquery.slimscroll.min.js?v=20151016234"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/summernote.css?v=20151016234">
    <script type="text/javascript" src="${basePath}/static/js/summernote.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/summernote-zh-CN.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/jquery.validate.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/common/common_alert.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/common/common_date.js?version=${VERSION}"></script>
    <script src="${basePath}/static/js/app.js?v=20151016234"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/goodsAdd.css?version=${VERSION}"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css?v=20151016234"/>
    <link rel="stylesheet" href="${basePath}/static/css/bootstrap-chosen.css?v=20151016234"/>
    <script src="${basePath}/static/js/chosen.jquery.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/angular.min.js?v=20151016234"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/kindeditor.js"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/plugins/code/prettify.js"></script>
    <script src="${basePath}/static/js/select2.min.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<#--引入头部-->
<#include "index/indextop.ftl">


<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>
 <#--订单列表页---------------------------------------------------------------------------------------------->
    <div class="app show_text" style="display: none;">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>订单管理</li>
                <li class="active" style="color: #07d;">订单列表</li>
            </ol>
        </div>
        <div class="search-block">
            <form action="${basePath}/orderManage/getOrders" class="searchThirdOrderList" method="post">
                <input type="hidden" id="page" name="page" value="${pageBean.number}">
                <input type="hidden" name="status" id="status">
                <div class="filter-groups">
                    <div class="control-group">
                        <label class="control-label">订单编号：</label>
                        <div class="controls">
                            <input class="form-control" name="orderNo" id="orderCode"
                                   onkeyup="value=value.replace(/[^\w\/]/ig,'')"
                                   value="${orderNo!''}" type="text"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">手机号码：</label>
                        <div class="controls">
                            <input class="form-control" name="contactPhone" id="shippingMobile" value="<#if contactPhone??>${contactPhone!''}</#if>" type="text"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">收货人姓名：</label>
                        <div class="controls">
                            <input class="form-control" name="receiver" value="${receiver!''}" type="text" />
                        </div>
                    </div>
                    <div class="control-group lg-group">
                        <label class="control-label">下单时间：</label>
                        <div class="controls">
                            <input type="text" name="createStart" id="createStart" value="${createStart!''}" class="form-control sm-input datepicker"/>
                            ~
                            <input type="text" name="createEnd" id="createEnd" value="${createEnd!''}" class="form-control sm-input datepicker"/>
                        </div>
                    </div>
                    <div class="control-group lg-group">
                        <label class="control-label">付款时间：</label>
                        <div class="controls">
                            <input type="text" name="payTimeStart" id="payStart" value="${payStart!''}" class="form-control sm-input datepicker"/>
                            ~
                            <input type="text" name="payTimeEnd" id="payEnd" value="${payEnd!''}" class="form-control sm-input datepicker"/>
                        </div>
                    </div>
                </div>
                <div class="search-operation">
                    <button class="btn btn-primary btn-sm"  onclick="searchOrder()" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                    <#--<button class="btn btn-default btn-sm"  onclick="resetThirdOrder()"  type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>-->
                </div>
            </form>
        </div>
        <button class="btn btn-primary btn-sm"  onclick="exportThirdOrder()" type="button">导出订单</button>
        <ul class="nav nav-tabs">
            <li class="tb_barterOrder <#if status==''>active</#if>" onclick="changTbl('')"><a href="javascript:;">全部订单</a></li>
            <li class="tb_backOrderSH  <#if status=='待付款'>active</#if>" data-type="SUBMITED" onclick="changTbl('SUBMITED')"><a href="javascript:;">待付款</a></li>
            <li class="tb_backOrderTg  <#if status=='待发货'>active</#if>" data-type="PAYED" onclick="changTbl('PAYED')"><a href="javascript:;">待发货</a></li>
            <li class="tb_backOrderJj  <#if status=='已发货'>active</#if>" data-type="DELIVERED" onclick="changTbl('DELIVERED')"><a href="javascript:;">已发货</a></li>
            <li class="tb_backOrderYt  <#if status=='已完成'>active</#if>" data-type="RECIEVED" onclick="changTbl('RECIEVED')"><a href="javascript:;">已完成</a></li>
            <li class="tb_backOrderover <#if status=='已取消'>active</#if>" data-type="CANCELED" onclick="changTbl('CANCELED')"><a href="javascript:;">已取消</a></li>
        </ul>
        <div class="cfg-content">
            <form class="high_search" action="<#if map.pb.url??&&map.pb??>${map.pb.url}<#else> </#if>" method="post">
            <div class="ops-bar"></div>
            <table class="table table-bordered orders-table">
                <thead>
                <tr>
                    <th width="30%">货品</th>
                    <th>货品数量（总金额）</th>
                    <th>收货人信息</th>
                    <th width="10%">下单时间</th>
                    <th>订单状态</th>
                    <th>结算价</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#if (pageBean.content?size) &gt; 0 >
                    <#list pageBean.content as order>
                        <tr class="separation-row">
                            <td colspan="8"></td>
                        </tr>
                        <tr class="header-row">
                            <td colspan="6">
                                <p>订单号：${order.orderCode!''}</p>
                            </td>
                            <td class="row-ops" colspan="2">

                            </td>
                        </tr>
                        <tr class="content-row">
                            <td class="order-pros">
                                <#--货品数量-->
                                <#assign goodsCont = 0/>
                                <#--订单结算价-->
                                <#assign goodsInfoSettlePrice=0/>
                            <#--遍历该订单下面的所有货品的信息-->
                                <#list order.orderGoodsList as goods>
                                    <a href="javascript:;" title="${goods.goodsInfoName}"><img src="${goods.goodsImg!''}" alt="${goods.goodsInfoName}" width="60" height="60"/></a>
                                    <#assign goodsCont = goodsCont+goods.goodsInfoNum/>
                                    <#--计算结算价-->
                                    <#assign goodsInfoSettlePrice=goodsInfoSettlePrice+goods.goodsInfoSettlePrice*goods.goodsInfoNum/>
                                </#list>
                            </td>
                            <td>
                                ${goodsCont!'0'}件<br/>
                                ${order.orderPrice?string("0.00")}邮豆
                            </td>
                            <td style="line-height: 20px;">${order.shippingPerson!''}<br/>${order.shippingMobile!''}</td>
                            <td>${order.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>
                                ${order.orderStatus!''}
                            </td>
                        <#--判断是店铺入驻商城还是供货商入驻商城的订单-->
                            <td>￥ ${goodsInfoSettlePrice?string("0.00")}</td>
                            <td>
                                <div class="btn-group" style="width: 112px;">

                                    <#--<button type="button" class="btn btn-primary btn-sm"  onclick="javascript:window.location.href='${basePath}/orderManage/getOrderGoods?orderId=${order.orderId}'">查看详情</button>-->
                                    <button type="button" class="btn btn-primary btn-sm" order-price ='${goodsInfoSettlePrice?string("0.00")}'  onclick="toOrderListDetail(${order.orderId},this)">查看详情</button>
                                    <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <span class="caret"></span>
                                    </button>
                                    <#--<#if status=='待付款'>-->
                                        <#--<ul class="dropdown-menu" role="menu">-->
                                            <#--<li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(${order.backOrderId},${order.mallId})" data-target="#Order_Money">修改金额</a></li>-->
                                        <#--</ul>-->
                                    <#--</#if>-->
                                </div>
                            </td>

                        </tr>
                    </#list>
                <#else >
                    <td colspan="12">
                        <center>暂无相关订单信息</center>
                    </td>
                </#if>
                </tbody>
            </table>
            <div class="footer-operation">
                <#include "index/searchPag.ftl">
            </div>
        </form>
        </div>
    </div>
 <#--订单详情页----------------------------------------------------------------------------------------------->
    <div class="app show_text_detail" style="display: none;">
        <div class="app-container">
            <div class="app-content">
            <div class="order-num">订单号：<span data-type="orderCode"></span></div>

            <div class="order-ex-info" style="margin-bottom:20px;">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#ex01" aria-controls="ex01" data-toggle="tab">订单信息</a>
                    </li>
                    <li>
                        <a href="#ex02" aria-controls="ex02" data-toggle="tab">物流信息</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="ex01">
                        <div class="order-det">
                            <div class="od-cont">
                                <table>
                                    <tbody>
                                    <tr>
                                        <td><em>订单状态：</em>
                                        <span data-type="orderStatus"></span>
                                        </td>
                                        <td><em>运费：</em>
                                        <span data-type="expressPrice"></span>邮豆
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><em>下单时间：</em><span data-type="createTime"></span></td>
                                        <td><em>付款时间：</em><span data-type="payTime"></span></td>
                                    </tr>
                                    <tr>
                                        <td><em>订单交易金额：</em><span data-type="orderPrice"></span>邮豆</td>
                                        <td><em>订单结算金额：</em><span id="orderPrice"></span>元</td>
                                    </tr>
                                    <tr>
                                        <td><em>客户留言：</em><span data-type="customerRemark"></span></td>
                                        <td colspan="4"><em>卖家备注：</em><span data-type="sellerRemark"></span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="ex02">
                        <div class="order-det">
                            <div class="od-cont">
                                <table>
                                    <tbody>
                                    <tr class="express">
                                        <td><em>快递名称：</em><span data-list="expressName"></span></td>
                                        <td><em>快递单号：</em><span data-list="expressNo"></span></td>
                                    </tr>
                                    <tr>
                                        <td><em>收货地址：</em><span data-type="shippingProvince"></span><span data-type="shippingCity"></span><span data-type="shippingCounty"></span></td>
                                        <td><em>详细地址：</em><span data-type="shippingAddress"></span></span></td>
                                    </tr>
                                    <tr>
                                        <td><em>收货人姓名：</em><span data-type="shippingPerson"></td>
                                        <td><em>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</em><span data-type="shippingMobile"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th width="400">货品名称</th>
                                <th>货品个数</th>
                                <th class="goodsSinglePrice">货品单价</th>
                                <th class="bussinessPrice">交易总额</th>
                            </tr>
                            </thead>
                            <tbody class="m_tbody">
                            </tbody>
                        </table>
        </div>
        </div>
        <div class="add-sorts-operation">
            <a class="btn btn-primary" onclick="closeDetail()">关闭
                <div class="ripple-wrapper">
                    <div class="ripple ripple-on ripple-out" style="left: 41.5px; top: 5px; background-color: rgba(255, 255, 255, 0.84); transform: scale(11);">

                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<!-- Modal 添加规格 -->
<#--<div class="modal fade" id="goodSpecModal" role="dialog">-->
    <#--<div class="modal-dialog">-->
        <#--<div class="modal-content">-->
            <#--<div class="modal-header">-->
                <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                <#--<h4 class="modal-title">添加规格</h4>-->
            <#--</div>-->
            <#--<div class="modal-body m-goods-body">-->
                <#--<div class="form-group" style="overflow: hidden">-->
                    <#--<label class="control-label col-xs-3"><b>*</b>规格名称：</label>-->
                    <#--<div class="controls col-xs-7">-->
                       <#--<input>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="goodsSpeaInfo type_spec"></div>-->
            <#--</div>-->
            <#--<div class="modal-footer">-->
                <#--<button type="button" class="btn btn-primary updateExpress2" onclick="addsGoodsSpecInfo()">保存</button>-->
                <#--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--</form>-->
<#include "common/leftfooter.ftl">

<script>
    $(function(){
        $.material.init();
        $(".chosen-select").chosen();
        $(".brandsChosen").chosen();
        $('.datepicker').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            weekStart : 1,
            autoclose : true,
            language : 'zh-CN',
            todayBtn : true,
            viewSelect : 'hour'
        })
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

    function getInputVal(obj){
        $("#basic-proPack").html($(obj).val()+"/箱");
    }
    //搜索
    function searchOrder(){
        $("#status").val($(".nav-tabs .active").attr("data-type"));
        if(checkEndTime($("#createStart").val(),$("#createEnd").val())){
            $(".searchThirdOrderList").submit();
        }else {
            showTipAlert("开始时间不能大于结束时间！");
        }
    }
    //分页
    function searchData(pageNumber){
        $("#page").val(pageNumber);
        searchOrder();
    }
    //tab切换
    function changTbl(status){
        $(".searchThirdOrderList").attr('action','${basePath}/orderManage/getOrders');
        $("#status").val(status);
        $("#page").val(1);
        $(".searchThirdOrderList").submit();
    }
    //查看详情点击显示详情
    function toOrderListDetail(_id,_this){
        $('.m_tbody').html('');
        $('.show_text').hide();
        $("#orderPrice").html('');
        $.post('getOrderGoods?orderId='+_id,function(data){
            console.log(data);
            if(data.response == "success"){
                var orderData = data.data;
                var dataExpress = data.data.orderContainerRelations[0];
                var ele1 = $(".show_text_detail").find('[data-type]');
                var ele2 = $(".show_text_detail").find('[data-list]');
                ele1.each(function(){
                    if($(this).attr('data-type').indexOf("Status")>=0){
                        $(this).html(orderStatus_handle(eval('orderData.'+$(this).attr('data-type'))));
                    }else if($(this).attr('data-type').indexOf("Time")>=0){
                        $(this).html(formatDateTime(eval('orderData.'+$(this).attr('data-type'))));
                    }else if($(this).attr('data-type').indexOf("Price")>=0){
                        $(this).html(getDecimal(eval('orderData.'+$(this).attr('data-type'))));
                    }else{
                        $(this).html(eval('orderData.'+$(this).attr('data-type')));
                    }
                });
                if(data.data.orderStatus != 'PAYED'){
                    ele2.html("");
                    ele2.each(function(){
                        $(this).html(eval('dataExpress.'+$(this).attr('data-list')));
                    });
                    $(".express").show();
                }else {
                    $(".express").hide();
                }
                for(var i = 0; i < orderData.orderGoodsList.length; i++){
                    var ordergoods = orderData.orderGoodsList[i];
                    var html = '';
                    html += '<tr><td> <div class="goods-intro"> <img alt="" class="gds-img" data-list="goodsImg" src="'+ ordergoods.goodsImg +'" /> <div class="gds-info"> <p class="gds-name"><span data-list="goodsInfoName">'+ ordergoods.goodsInfoName +'</span></p> </div> </div> </td> <td><span data-list="goodsInfoNum">'+ ordergoods.goodsInfoNum +'</span>(件)</td> <td><span data-list="goodsInfoPrice">'+ getDecimal(ordergoods.goodsInfoPrice) +'</span>邮豆</td> <td><span data-list="goodsInfoSumPrice">'+ getDecimal(ordergoods.goodsInfoSumPrice) +'</span>邮豆</td> </tr>'
                    $('.m_tbody').append(html);
                }
                if(orderStatus_handle(data.data.orderStatus)!="待付款" || orderStatus_handle(data.data.orderStatus)!="待发货" || orderStatus_handle(data.data.orderStatus)!="待发货"){
                    $.each(data.data.orderContainerRelations,function(){
                        if(this.expressNo){
                            $(".expressNo").html('<em>物流单号：</em>'+this.expressNo);
                        }
                    });
                }
                $("#orderPrice").html($(_this).attr("order-price"));
            }
        })
        $('.show_text_detail').show();
    }
//    关闭详情页
function closeDetail(){
    $('.show_text').show();
    $('.show_text_detail').hide();
}

</script>
<#--<#import "common/selectindex.ftl" as selectindex>-->
<#--<@selectindex.selectindex "${n!''}" "${l!''}" />-->

</body>
</html>