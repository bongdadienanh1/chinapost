
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
<#--<script type="text/javascript" src="${basePath}/static/js/goods/thirdOrderList.js"></script>-->
    <script type="text/javascript" src="${basePath}/static/js/goods/thirdBackOrderList.js?version=${VERSION}"></script>
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
    <style>
        .select2-container--default .select2-selection--single{background-color: rgba(0, 0, 0, 0)!important;}
    </style>
</head>
<body>
<#--引入头部-->
<#include "index/indextop.ftl">


<div class="wp">
    <input type="hidden" value="${isThirdAuditUsed!''}" id="isThirdAuditUsed"/>
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text show_text_detail" style="display: none;">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>订单管理</li>
                <li class="active" style="color: #07d;">退单列表</li>
            </ol>
            <div class="group_wp">
                <form action="" class="searchThirdOrderList" method="post">
                    <input type="hidden" id="page" name="page" value="${pageBean.number}">
                    <div class="search-block">
                        <div class="filter-groups">
                            <div class="control-group">
                                <label class="control-label">退单编号：</label>
                                <div class="controls">
                                    <input class="form-control" value="${backOrderNo!''}" name="backOrderNo" onkeyup="value=value.replace(/[^\w\/]/ig,'')" type="text" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">手机号码：</label>
                                <div class="controls">
                                    <input class="form-control" name="phoneNo" value="${phoneNo!''}" type="text"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">订单编号：</label>
                                <div class="controls">
                                    <input class="form-control" value="${orderNo!''}" name="orderNo" onkeyup="value=value.replace(/[^\w\/]/ig,'')" type="text" />
                                </div>
                            </div>

                        <#--<div class="control-group">-->
                        <#--<label class="control-label">收货人姓名：</label>-->
                        <#--<div class="controls">-->
                        <#--<input class="form-control" name="username" value="${username!''}" type="text" />-->
                        <#--</div>-->
                        <#--</div>-->

                            <div class="control-group">
                                <label class="control-label">退单状态：</label>
                                <div class="controls">
                                    <select class="form-control" name="status">
                                        <option value="">全部</option>
                                        <option value="REFUND_APPLIED" <#if status=="审核退款">selected="selected"</#if>>审核退款</option>
                                        <option value="REFUND_FINISHED" <#if status=="退款成功">selected="selected"</#if>>退款成功</option>
                                        <option value="REFUND_DENIED" <#if status=="拒绝退款">selected="selected"</#if>>拒绝退款</option>
                                        <option value="RETURN_APPLIED" <#if status=="退货申请">selected="selected"</#if>>退货申请</option>
                                        <option value="RETURN_WAIT_DELIVER_INFO" <#if status=="待客户填写物流信息">selected="selected"</#if>>待客户填写物流信息</option>
                                        <option value="RETURN_DELIVERING" <#if status=="待商家收货">selected="selected"</#if>>待商家收货</option>
                                        <option value="RETURN_WAIT_REFUND" <#if status=="退货待退款">selected="selected"</#if>>退货待退款</option>
                                        <option value="RETURN_FINISHED" <#if status=="退货成功">selected="selected"</#if>>退货成功</option>
                                        <option value="RETURN_DENIED" <#if status=="拒绝退货">selected="selected"</#if>>拒绝退货</option>
                                        <option value="RETURN_DELIVER_FAILURE" <#if status=="收货失败">selected="selected"</#if>>收货失败</option>
                                    </select>
                                </div>
                            </div>

                            <div class="control-group lg-group">
                                <label class="control-label">退单时间：</label>
                                <div class="controls">
                                    <input type="text" name="start" id="startTime" class="form-control sm-input datepicker" value="${start!''}" data-provide="datepicker"/>
                                    ~
                                    <input type="text" name="end" id="endTime" class="form-control sm-input datepicker" value="${end!''}" data-provide="datepicker"/>
                                </div>
                            </div>
                        </div>

                        <div class="search-operation">
                            <button class="btn btn-primary btn-sm"  onclick="searchOrder()" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                        <#--<button class="btn btn-default btn-sm"  onclick="resetThirdOrder()"  type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>-->
                        </div>
                    </div>
                </form>
                <button class="btn btn-primary btn-sm"  onclick="exportThirdBackOrder()" type="button">导出退单</button>
                <ul class="nav nav-tabs">
                    <li class="active tb_backOrderJj" ><a href="javascript:;">全部退单</a></li>
                </ul>
                <div class="cfg-content">
                    <div class="ops-bar"></div>
                    <form class="high_search" action="" method="post">
                        <table class="table table-bordered orders-table">
                            <thead>
                            <tr>
                                <th width="20%">货品</th>
                                <th>数量</th>
                                <th>买家</th>
                                <th width="12%">退单时间</th>
                                <th>退单状态</th>
                                <th>退单金额</th>
                                <th>结算价</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if (pageBean.content?size) &gt; 0 >
                                <#list pageBean.content as backOrder>
                                <tr class="separation-row">
                                    <td colspan="9"></td>
                                </tr>
                                <tr class="header-row">
                                    <td colspan="7">
                                        <p>退单编号：${backOrder.backOrderCode!''}</p>
                                    </td>
                                    <td class="row-ops" colspan="4">
                                        <a class="modi-order" href="javascript:;" onclick="modiOrder(${backOrder.backOrderCode})">查看详情</a>
                                    </td>
                                </tr>
                                <tr class="content-row">
                                    <td class="order-pros">
                                        <#assign goodsCont = 0/>
                                    <#--订单结算价-->
                                        <#assign goodsInfoSettlePrice=0/>
                                    <#--遍历该订单下面的所有货品的信息-->
                                        <#list backOrder.backOrderGoods as goods>
                                            <a href="javascript:;" title="${goods.goodsInfoName}"><img src="${goods.goodsInfoImage!''}" alt="${goods.goodsInfoName}" width="60" height="60"/></a>
                                            <#assign goodsCont = goodsCont+goods.goodsNum/>
                                        <#--计算结算价-->
                                            <#assign goodsInfoSettlePrice=goodsInfoSettlePrice+goods.goodsInfoSettlePrice*goods.goodsNum/>
                                        </#list>
                                    </td>
                                    <td>
                                        （${goodsCont!'0'}件）
                                    </td>
                                    <td style="line-height: 20px;">${backOrder.backCollectPerson!''}<br/>${backOrder.backCollectPhone!''}</td>
                                    <td>${backOrder.backTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>${backOrder.backCheck!''}</td>
                                <#--判断是店铺入驻商城还是供货商入驻商城的订单-->
                                    <td>${backOrder.backPrice?string("0.00")}邮豆</td>
                                    <td>￥ ${goodsInfoSettlePrice?string("0.00")}</td>
                                    <td>

                                        <#if backOrder.backCheck != "审核退款" && backOrder.backCheck != "退货申请" && backOrder.backCheck != "待商家收货" &&
                                        backOrder.backCheck != "退货待退款">
                                            <div class="btn-group">
                                                <a class="btn btn-primary btn-sm modi-order disabled" href="javascript:;">暂无操作</a>
                                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle disabled" data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu disabled" role="menu">
                                                </ul>
                                            </div>
                                        <#else >
                                            <div class="btn-group">
                                                <a class="btn btn-primary btn-sm modi-order" href="javascript:;">操作</a>
                                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <#if backOrder.backCheck == "审核退款">
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'1')" data-target="#Order_Money">同意退款</a></li>
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'2')" data-target="#Order_Money">拒绝退款</a></li>

                                                    </#if>
                                                    <#if backOrder.backCheck == "退货申请">
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'1')" data-target="#Order_Money">同意退货</a></li>
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'2')" data-target="#Order_Money">拒接退货</a></li>
                                                    </#if>
                                                    <#if backOrder.backCheck == "待商家收货">
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'3')" data-target="#Order_Money">同意收货</a></li>
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'4')" data-target="#Order_Money">拒接收货</a></li>
                                                    </#if>
                                                    <#if backOrder.backCheck == "退货待退款">
                                                        <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'5')" data-target="#Order_Money">同意退款</a></li>
                                                    <#--<li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(this,${backOrder.backOrderCode},'2')" data-target="#Order_Money">拒接退款</a></li>-->
                                                    </#if>
                                                <#--<li><a href="" data-toggle="modal" onclick="fnModifyBackOrder(<#if order.backOrderId??>${order.backOrderId}</#if>)" data-target="#modifyState">订单审核</a></li>-->
                                                </ul>
                                            </div>
                                        </#if>
                                    </td>

                                </tr>
                                </#list>
                            <#else >
                            <td colspan="12">
                                <center>暂无相关退单信息</center>
                            </td>
                            </#if>
                            </tbody>
                        </table>
                    </form>
                    <div class="footer-operation">
                        <#include "index/searchPag.ftl">
                    </div>
                </div>
            </div>
            <div class="app-content backOrderDeal" style="display: none">
                <div class="order-num" data-order-type="backOn">退单号：<span data-order-type="backOrderCode"></span></em></div>
                <div class="order-det">
                    <div class="od-tit"><h3>退单详情</h3></div>
                    <div class="od-cont">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>原订单号：</em><span id="orderCode"></span></td>
                                <td><em>收货地址信息：</em><span id="orderAddress"></span></td>
                                <td><em>退货原因：<span data-order-type="backReason"></span></em></td>
                            </tr>
                            <tr>
                                <td><em>退单时间：</em><span data-order-type="backTime"></span></td>
                                <td><em>详细地址：</em><span data-order-type="backCollectAddress"></span></td>
                                <td><em>上传凭证：</em><span data-order-type="uploadDocuments" ></span></td>
                            </tr>
                            <tr>
                                <td><em>退单状态：</em><span data-order-type="backCheck"></span></td>
                                <td><em>收货人姓名：</em><span data-order-type="backCollectPerson"></span></td>
                                <td><em>退单说明：</em><span data-order-type="backRemark"></span></td>
                            </tr>
                            <tr>
                                <td><em>退单快递公司：</em><span id="npLogisticsName"></span></td>
                                <td><em>退单快递单号：</em><span id="npLogisticsNo"></span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th width="300">退单货品</th>
                        <th>货品个数</th>
                        <th>退单总价</th>
                    </tr>
                    </thead>
                    <tbody class="m_tbody">
                    </tbody>
                    <tfoot></tfoot>
                </table>
                <div class="add-sorts-operation">
                    <a class="btn btn-primary" onclick="updateCancel()">关闭</a>
                </div>
            </div>
        </div>
    </div>
</div>
<#--</form>-->
<#include "common/leftfooter.ftl">


<script type="text/javascript" src="${basePath}/static/js/navmenu.js?v=20151016234"></script>
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
        if(checkEndTime($("#startTime").val(),$("#endTime").val())){
            searchData(1);
        }else {
            showTipAlert("开始时间不能大于结束时间！");
        }
    }
    //分页
    function searchData(pageNumber){
        $(".searchThirdOrderList").attr('action','${basePath}/orderManage/getBackOrders');
        $("#page").val(pageNumber);
        $(".searchThirdOrderList").submit();
    }

//    $(".modi-order").click(function () {
//
//    });

    function modiOrder(backOrderCode){
        $('.m_tbody').html('');
        $("#npLogisticsName").html("");
        $("#npLogisticsNo").html("");
        $("#orderCode").html("");
        $("#orderAddress").html("");
        $(".backOrderDeal").find("data-order-type").html('');
        $.post("getBackOrderGoods?backOrderCode="+backOrderCode,function(data){
            if(data.response == 'success'){
                console.log(data);
//                var demoData = data.data;
//                $(".backOrderDeal").find("data-order-type").each(function(){
//                    $(this).html(eval('demoData.'+$(this).attr("data-order-type")));
//                })
                var orderData = data.data;
                var ele1 = $(".show_text_detail").find('[data-order-type]');
                var ele2 = $(".show_text_detail").find('[data-list]');
                ele1.each(function(){
                    if($(this).attr('data-order-type').indexOf("Check")>=0){
                        $(this).html(backOrderStatus_handle(eval('orderData.'+$(this).attr('data-order-type'))));
                    }else if($(this).attr('data-order-type').indexOf("Time")>=0){
                        $(this).html(formatDateTime(eval('orderData.'+$(this).attr('data-order-type'))));
                    }else if($(this).attr('data-order-type').indexOf("Reason")>=0){
                        $(this).html(backReson_handle(eval('orderData.'+$(this).attr('data-order-type'))));
                    }else{
                        $(this).html(eval('orderData.'+$(this).attr('data-order-type')));
                    }
                });
                for(var i = 0; i < orderData.backOrderGoods.length; i++){
                    var ordergoods = orderData.backOrderGoods[i];
                        var html = '';
                        html += '<tr><td> <a href="javascript:;"><img data-list="goodsInfoImage" alt="" width="30px" height="30px" src="'+ ordergoods.goodsInfoImage +'" /></a> <span>'+ ordergoods.goodsInfoName +'</span></td> <td><span data-list="goodsNum">'+ ordergoods.goodsNum +'</span>件</td> <td><span data-list="goodsPrice">'+ getDecimal(ordergoods.goodsPrice) +'</span>邮豆</td> </tr>'
                        $('.m_tbody').append(html);
                }
                $("#orderCode").html(data.data.order.orderCode);
                if(data.data.logisticses[0]){
                    $("#npLogisticsName").html(data.data.logisticses[0].npLogisticsName);
                    $("#npLogisticsNo").html(data.data.logisticses[0].npLogisticsNo);
                }
                $("#orderAddress").html(data.data.order.shippingProvince+data.data.order.shippingCity+" "+data.data.order.shippingCounty);

            }
        });
        $(".group_wp").hide();
        $(".app-content").show();
    }

    function updateCancel(){
        $(".app-content").hide();
        $(".group_wp").show();
    }

    function fnModifyBackOrderMoney(_this,backOrderCode,type){
        var url = 'orderStateChange?backOrderCode='+backOrderCode+'&whetherType='+type;
        var tips = '你确定要'+$(_this).text()+'吗?';
        showDeleteOneConfirmAlert(url,tips);
    }

    function exportThirdBackOrder(){
        $(".searchThirdOrderList").attr('action','backOrderDown');
        $(".searchThirdOrderList").submit();
    }
</script>
<#--<#import "common/selectindex.ftl" as selectindex>-->
<#--<@selectindex.selectindex "${n!''}" "${l!''}" />-->

</body>
</html>
