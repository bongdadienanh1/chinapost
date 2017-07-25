

<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>平台订单列表</title>

    <!-- Bootstrap -->
    <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link rel="stylesheet" href="${bath}/static/css/font-awesome.min.css?version=${VERSION}">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/summernote.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/bootstrap/css/bootstrap-select.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/bootstrap-datetimepicker.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/select2.min.css?version=${VERSION}" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<#include "page/header.ftl"/>
<!-- Modal -->
<div class="modal fade" id="orderDetails" role="dialog">
    <div class="modal-dialog modal-lg">
        <!--startprint1-->
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="detailorderCodeNo">订单详情</h4>
            </div>

            <div class="modal-body">

                <div class="order_flow">
                    <ul id="status">
                        <li class="active">
                            <p class="name">已下单</p>
                            <p class="bar"><i>1</i></p>
                            <p class="time">2015-04-27 14:15:13</p>
                        </li>
                        <li class="active">
                            <p class="name">已付款</p>
                            <p class="bar"><i>2</i></p>
                            <p class="time">2015-04-27 14:15:13</p>
                        </li>
                        <li>
                            <p class="name">已发货</p>
                            <p class="bar"><i>3</i></p>
                            <p class="time">2015-04-27 14:15:13</p>
                        </li>
                        <li>
                            <p class="name">已完成</p>
                            <p class="bar"><i>4</i></p>
                            <p class="time">2015-04-27 14:15:13</p>
                        </li>
                    </ul>
                </div>

                <div class="order_info">
                    <h4>订单概况</h4>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-12">
                                <p id="detailorderCode">订单编号：20150228143717</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailcreateTime">下单时间：2015-02-28 14:37:17</p>
                            </div>
                            <div class="col-sm-12">
                                <p>订单状态：<span class="text-danger" id="detailorderStatus">已付款未发货</span></p>
                            </div>
                            <div class="col-sm-12">
                                <p>订单结算价：<span id="goodsInfoSettlePriceSpan" class="text-danger">172.01</span></p>
                            </div>
                            <div class="col-sm-12">
                                <p>订单交易金额：<span id="detailorderPrice" class="text-danger">172.01</span></p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailorederRemark">取消订单原因：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailorederRetime">取消订单时间：</p>
                            </div>
                        </div>
                    </div>

                    <h4>物流信息</h4>
                    <div class="container-fluid">
                        <div class="row" id="relations">

                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <p id="detailorderExpress">配送方式：快递配送</p>
                            </div>
                            <#--<div class="col-sm-12">-->
                                <#--<p id="detailexpressPrice">运费： 12.00</p>-->
                            <#--</div>-->
                            <div class="col-sm-12">
                                <p id="detailshippingaddress">收货地址：安徽 安庆 安庆市</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailaddress">详细地址：dsdsd</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingPerson">收货人：dsdsd</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingPhone">联系电话：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingMobile">手机： 13913021285</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingPostcode">邮编：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailcustomerRemark">客户留言：</p>
                            </div>
                        </div>
                    </div>

                    <#--<h4>发票信息</h4>-->
                    <#--<div class="container-fluid">-->
                        <#--<div class="row" id="invoiceType">-->

                        <#--</div>-->
                    <#--</div>-->

                    <#--<h4>卖家备注</h4>-->
                    <#--<div class="container-fluid">-->
                        <#--<div class="row">-->
                            <#--<div class="col-sm-12">-->
                                <#--<p id="detailsellerRemark">卖家备注：</p>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                </div>

                <table class="table  table-bordered">
                    <thead>
                    <tr>
                        <th width="40%">货品名称</th>
                        <th class="text-center">单价</th>
                        <th class="text-center">数量</th>
                        <th class="text-center">货品规格</th>
                        <th class="text-center">结算价</th>
                        <th class="text-center">货品总价</th>
                        <th class="text-center">货品总结算价</th>
                    </tr>
                    </thead>
                    <tbody id="ordergoods">
                    <tr>
                        <td>
                            <div class="data_item">
                                <img alt="" src="">
                                <p>贝亲（Pigeon）婴儿柔湿巾80片装（3包）PL135</p>
                                <p class="text-muted">80片*3包</p>
                            </div>
                        </td>
                        <td class="text-center">39.80</td>
                        <td class="text-center">1</td>
                        <td class="text-center"></td>
                        <td class="text-center">39.80</td>
                        <td rowspan="2" class="text-center">5.00</td>
                    </tr>
                    <tr>
                        <td>
                            <div class="data_item">
                                <img alt="" src="images/good_2_small.jpg">
                                <p>贝亲（Pigeon）婴儿柔湿巾80片装（3包）PL135</p>
                                <p class="text-muted">80片*3包</p>
                            </div>
                        </td>
                        <td class="text-center">39.80</td>
                        <td class="text-center">1</td>
                        <td class="text-center"></td>
                        <td class="text-center">39.80</td>
                    </tr>
                    </tbody>
                </table>
                <!--endprint1-->
                <#--<div style="text-align:center;">
                    <button type="button" class="btn btn-primary Noprint" onclick="return doPrint();">打印</button>
                </div>-->
            </div>
        </div>

    </div>
</div>

<div class="page_body container-fluid">
    <div class="row">
    <#include "page/left.ftl"/>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>平台管理</li>
                    <li>平台订单列表</li>
                </ol>
                <h2 class="main_title">平台订单列表<small>(共${pageBean.totalElements!'0'}条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline" action="ListPage" id="thirdOrderForm" method="post">
                            <input type="hidden" name="orderStatus" id="statusFlag"/>
                            <input type="hidden" name="page" id="page"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">订单号</span>
                                    <input type="text" class="form-control" id="orderCode" name="orderCode" value="${order.orderCode}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">收货人</span>
                                    <input type="text" class="form-control" id="shippingPerson" name="shippingPerson" value="${order.shippingPerson }">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">联系电话</span>
                                    <input type="text" class="form-control" id="shippingMobile" name="shippingMobile" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${order.shippingMobile }">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商家名称</span>
                                    <select   class="cate_selector w150"  name="businessId" data-live-search="true">
                                        <option value="">--请选择--</option>
                                    <#list platformList as store>
                                        <option value="${store.storeId}" <#if order.businessId == store.storeId >selected ="selected"</#if>>${store.storeName}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">下单开始时间</span>
                                    <input class="form-control" type="text" id="startTime" value="${createStart}" name="createStart"
                                           readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">下单结束时间</span>
                                    <input class="form-control" type="text" value="${createEnd}" name="createEnd" id="endTime"
                                           readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>

                            <#--<div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">用户名</span>
                                    <input type="text" class="form-control" id="customerUsername" value="${order.customerUsername }">
                                </div>
                            </div>-->
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">付款开始时间</span>
                                    <input class="form-control" type="text" id="payTimeStart"
                                           value="${payStart}" name="payStart" readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">付款结束时间</span>
                                    <input class="form-control" type="text" value="${payEnd}" id="payTimeEnd" name="payEnd"
                                           readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="submitOrder();">搜索</button>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="exportOrderExcel();">订单导出</button>
                            </div>
                        </form>
                    </div>


                    <div class="table_tabs" id="order_tabs">
                        <ul class="flagli">
                            <li class="<#if order.orderStatus==null||order.orderStatus==''>active</#if>">
                            <a href="javascript:;" data-type="">全部订单</a>
                            </li>
                            <li class="<#if order.orderStatus=='待付款'>active</#if>">
                                <a href="javascript:;" data-type="SUBMITED">待付款</a>
                            </li>
                            <li class="<#if order.orderStatus=='待发货'>active</#if>">
                            <a href="javascript:;" data-type="PAYED">待发货</a>
                            </li>
                            <li class="<#if order.orderStatus=='已发货'>active</#if>">
                            <a href="javascript:;" data-type="DELIVERED">已发货</a>
                            </li>
                            <li class="<#if order.orderStatus=='已完成'>active</#if>">
                            <a href="javascript:;" data-type="RECIEVED">已完成</a>
                            </li>
                            <li class="<#if order.orderStatus=='已取消'>active</#if>">
                            <a href="javascript:;" data-type="CANCELED">已取消</a>
                            </li>
                        </ul>
                    </div>
                    <table class="table order_table">
                        <thead>
                        <tr>
                            <th width="25%">货品</th>
                            <th class="text-center">总价/数量</th>
                            <th class="text-center">买家信息</th>
                            <th class="text-center">下单时间</th>
                            <th class="text-center">订单状态</th>
                            <th class="text-center">订单交易金额</th>
                            <th class="text-center">订单结算金额</th>
                            <th class="text-center">商家</th>
                        </tr>
                        </thead>
                        <tbody id="ordergoods">
                        <#list pageBean.content as order>
                            <#assign goodsInfoSettlePrice = 0/>
                            <#assign goodsInfoNum = 0/>
                            <#list order.orderGoodsList as ordergoods>
                                <#assign goodsInfoNum = goodsInfoNum+ordergoods.goodsInfoNum/>
                            <#--计算结算价-->
                                <#assign goodsInfoSettlePrice=goodsInfoSettlePrice+ordergoods.goodsInfoSettlePrice*ordergoods.goodsInfoNum/>
                            </#list>
                            <tr class="table_blank data1 status">
                                <td colspan="8" style="padding:0px;"></td>
                            </tr>
                            <tr class="order_head data1 status">
                                <td colspan="8">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="order_select" name="orderId" value="">订单号：${order.orderCode }
                                        </label>
                                    </div>

                                    <div class="btns" style="top:20px;">
                                        <a href="javascript:;" onclick="fnOpen(${order.orderId},'${goodsInfoSettlePrice?string("0.00")}')">查看详情</a>
                                    <#--<b> - </b>-->
                                    <#--<a href="javascript:;" onclick="orderlog(${order.orderId })">操作日志</a>-->
                                    <#--<b> - </b>-->
                                    <#--<a href="javascript:;" onclick="addNotes(${order.orderId })">卖家备注</a>-->
                                    </div>
                                </td>
                            </tr>
                            <tr class="data1 status">
                                <td>
                                    <div class="mini_goods" style="text-align:left;">
                                        <#list order.orderGoodsList as ordergoods>
                                           <img alt="" title="${ordergoods.goodsInfoName}" src="${ordergoods.goodsImg}" height="50">
                                        </#list>
                                        <#--<input type="hidden" value="" id="goodsInfoSettlePrice">-->
                                    </div>
                                </td>
                                <td align="center"><p>${order.orderOldPrice?string("0.00")}邮豆</p>
                                    <p>(${goodsInfoNum}件)</p></td>
                                <td><#if order.customerInfo != '' > <p>用户名: ${order.customerInfo.username }</p></#if>
                                    <#if order.shippingPerson != '' ><p>收件人: ${order.shippingPerson }</p></#if>
                                    <#if order.shippingMobile != '' > <p>手机号:${order.shippingMobile }</p></#if></td>
                                <td>
                                    ${order.createTime?string("yyyy-MM-dd HH:mm:ss")}
                                </td>
                                <td align="center">
                                    ${order.orderStatus}
                                </td>
                                <td align="center">
                                    ${order.orderPrice}.00邮豆
                                </td>
                                <td align="center">
                                    ￥ ${goodsInfoSettlePrice?string("0.00")}
                                </td>
                                <td><#if order.business??>${order.business.businessName}</#if></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>


                    <div class="table_foot">
                        <#include "page/searchPag.ftl"/>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

</div>

<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/summernote.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/summernote-zh-CN.js?version=${VERSION}"></script>
<script src="${bath}/static/bootstrap/js/bootstrap-select.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_checked.js?version=${VERSION}"></script>
<script src="${bath}/static/js/select2.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/ThirdPlantManage/thirdOrderList.js?version=${VERSION}"></script>
<script src="${bath}/static/js/bootstrap-datetimepicker.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/bootstrap-datetimepicker.zh-CN.js?version=${VERSION}"></script>
<script type="application/javascript">
    $(function(){
        $('select[data-live-search="true"]').select2();
    });
</script>
</body>
</html>





