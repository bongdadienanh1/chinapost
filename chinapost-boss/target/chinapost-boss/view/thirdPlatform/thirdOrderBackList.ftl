

<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>平台退单列表</title>

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
    <style>
        .track-info {
            color: #afafaf;
        }
        .track-info i{background:url(${bath}/static/images/track-bg.png) no-repeat left bottom;width:14px;height:40px;margin:0 20px;display: inline-block;vertical-align: bottom;}
    </style>
</head>
<body>
<!-- 引用头 -->
<#include "page/header.ftl"/>
<input type="hidden" id="basePath" value="${bath}">

<div class="page_body container-fluid">
    <div class="row">
    <#include "page/left.ftl"/>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>平台管理</li>
                    <li>平台退单列表</li>
                </ol>
                <h2 class="main_title">平台退单列表<small>(共${pageBean.totalElements!'0'}条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline" id="thirdBackOrderForm" action="ListPage">
                            <input type="hidden" name="flag" value="${flag}">
                            <input type="hidden" name="page" id="page"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">退单号</span>
                                    <input type="text" class="form-control" id="backOrderNo" value="${backOrderNo}" name="backOrderNo">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">手机号</span>
                                    <input type="text" class="form-control" id="shippingMobile" name="shippingMobile" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${shippingMobile }">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">订单号</span>
                                    <input type="text" class="form-control" id="orderCode" name="orderCode" value="${orderCode}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">退单状态</span>
                                    <select class="form-control" id="up_backCheck" name="status">
                                        <option value="">请选择</option>
                                        <option value="REFUND_APPLIED" <#if status=='审核退款'>selected="selected"</#if>>审核退款</option>
                                        <option value="REFUND_DENIED" <#if status=='拒绝退款'>selected="selected"</#if>>拒绝退款</option>
                                        <option value="REFUND_FINISHED" <#if status=='退款成功'>selected="selected"</#if>>退款成功</option>
                                        <option value="RETURN_APPLIED" <#if status=='退货申请'>selected="selected"</#if>>退货申请</option>
                                        <option value="RETURN_DENIED" <#if status=='拒绝退货'>selected="selected"</#if>>拒绝退货</option>
                                        <option value="RETURN_WAIT_DELIVER_INFO" <#if status=='待客户填写物流信息'>selected="selected"</#if>>待客户填写物流信息</option>
                                        <option value="RETURN_DELIVERING" <#if status=='待商家收货'>selected="selected"</#if>>待商家收货</option>
                                        <option value="RETURN_DELIVER_FAILURE" <#if status=='收货失败'>selected="selected"</#if>>收货失败</option>
                                        <option value="RETURN_FINISHED" <#if status=='退货成功'>selected="selected"</#if>>退货成功</option>
                                        <option value="RETURN_WAIT_REFUND" <#if status=='退货待退款'>selected="selected"</#if>>退货待退款</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商家名称</span>
                                    <select   class="cate_selector w150"  name="thirdId" data-live-search="true">
                                        <option value="">--请选择--</option>
                                        <#list platformList as store>
                                            <option value="${store.storeId}" <#if thirdId == store.storeId >selected ="selected"</#if>>${store.storeName}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">退单开始时间</span>
                                    <input class="form-control" type="text" id="startTime" value="${start}" name="start"
                                           readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">退单结束时间</span>
                                    <input class="form-control" type="text" value="${end}" id="endTime" name="end"
                                           readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="submitBcakOrder();">搜索</button>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="exportBackOrderExcel();">退单导出</button>
                            </div>
                        </form>
                    </div>

                    <table class="table order_table">
                        <thead>
                        <tr>
                            <th width="25%">货品</th>
                            <th class="text-center">订单号</th>
                            <th class="text-center">总价/数量</th>
                            <th class="text-center">收货人</th>
                            <th class="text-center">退单时间</th>
                            <th class="text-center">退单状态</th>
                            <th class="text-center">退单金额</th>
                            <th class="text-center">商家</th>
                        </tr>
                        </thead>
                        <tbody id="ordergoods">

                        <#list pageBean.content as bkOrder>
                            <#assign goodsInfoSettlePrice = 0/>
                            <#assign goodsInfoNum = 0/>
                            <#list bkOrder.backOrderGoods as ordergoods>
                                <#assign goodsInfoNum = goodsInfoNum+ordergoods.goodsNum/>
                            <#--计算结算价-->
                                <#--<#assign goodsInfoSettlePrice=goodsInfoSettlePrice+ordergoods.goodsInfoSettlePrice*ordergoods.goodsInfoNum/>-->
                            </#list>
                        <tr class="table_blank data1 status">
                            <td colspan="8" style="padding:0px;"></td>
                        </tr>
                        <tr class="order_head data1 status">
                            <td colspan="8">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" class="order_select" name="orderId" value="">退单号：${bkOrder.backOrderCode}
                                    </label>
                                </div>

                                <div class="btns" style="top:20px;">
                                    <a href="javascript:;" onclick="fnOpen('${bkOrder.backOrderCode }')">查看详情</a>
                                </div>
                            </td>
                        </tr>
                        <tr class="data1 status">
                            <td>
                                <div class="mini_goods" style="text-align:left;">
                                    <#list bkOrder.backOrderGoods as orderGoods>
                                        <img alt="" title="${orderGoods.goodsInfoName}" src="${orderGoods.goodsInfoImage}" height="50">
                                    </#list>
                                </div>
                            </td>
                            <td align="center">${bkOrder.order.orderCode}</td>
                            <td align="center">
                                <p>${bkOrder.backPrice}.00邮豆</p>
                                <#--<p>(${bkOrder.backOrderGoods?size}件)</p>-->
                                <p>(${goodsInfoNum!''}件)</p>
                            </td>
                            <td>
                                <p>${bkOrder.backCollectPerson }</p>
                                <p>${bkOrder.backCollectPhone }</p>
                            </td>
                            <td align="center">
                                <p>
                                     ${bkOrder.backTime?string("yyyy-MM-dd HH:mm:ss")}
                                </p>
                            </td>
                            <td align="center">
                               <span class="">${bkOrder.backCheck}</span>
                            </td>
                            <td align="center">${bkOrder.backPrice}.00邮豆</td>
                            <td align="center"><#if bkOrder.business??>${bkOrder.business.businessName}</#if></td>
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
<#--Model 退单详情-->
<div class="modal fade" id="orderDetails" role="dialog">
    <div class="modal-dialog modal-lg">
        <!--startprint1-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeDetail()"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="detailorderCodeNo">退单号：</h4>
            </div>

            <div class="modal-body">
                <table class="table  table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">退货原因</th>
                            <th class="text-center">申请凭据</th>
                            <th class="text-center">退款金额</th>
                            <th class="text-center">问题说明</th>
                            <th class="text-center">上传凭证</th>
                            <th class="text-center">货品返回方式</th>
                            <#--<th class="text-center">退货货品</th>-->
                            <#--<th class="text-center">货品个数</th>-->
                            <#--<th class="text-center">退单状态</th>-->
                        </tr>
                    <tbody id="">
                        <tr>
                            <td class="text-center" id="backGoodsReason"></td>
                            <td class="text-center" id="requestReason"></td>
                            <td class="text-center" id="backPrice"></td>
                            <td class="text-center" id="problemExplain"></td>
                            <td class="text-center" id="uploadReason"></td>
                            <td class="text-center" id="goodsBackWay"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="table  table-bordered">
                    <thead>
                    <tr>
                        <th class="text-center">退货货品</th>
                        <th class="text-center">货品货号</th>
                        <th class="text-center">销售单价</th>
                        <th class="text-center">数量</th>
                        <th class="text-center">实付金额</th>
                    </tr>
                    </thead>
                    <tbody id="goodsInfoAlert">
                        <#--<tr>-->
                            <#--<td class="text-center"></td>-->
                            <#--<td class="text-center"></td>-->
                            <#--<td class="text-center"></td>-->
                            <#--<td class="text-center"></td>-->
                            <#--<td class="text-center"></td>-->
                        <#--</tr>-->
                    </tbody>
                </table>


                <div class="sf-tit"><b>操作日志</b></div>
                <div class="item-cont" style="margin-top: 20px;margin-bottom: 50px;">
                    <div class="track-info" style="padding-left: 130px;">
                        <p>
                            <span class="track-date">退单时间：</span>
                            <i></i>
                            <span class="track-cont"></span>
                        </p>
                    </div>
                </div>
                <#--<div class="sf-tit"><b>操作（重要）</b></div>-->
                <#--<div class="item-cont" style="margin-top: 20px;margin-bottom: 50px;">-->
                    <#--<div class="track-info" style="padding-left: 130px;">-->
                        <#--&lt;#&ndash;<p>&ndash;&gt;-->
                            <#--&lt;#&ndash;<span class="track-cont">退单时间：</span>&ndash;&gt;-->
                            <#--&lt;#&ndash;<i></i>&ndash;&gt;-->
                            <#--&lt;#&ndash;<span class="track-date" id="orderBackTime"></span>&ndash;&gt;-->
                        <#--&lt;#&ndash;</p>&ndash;&gt;-->
                    <#--</div>-->
                <#--</div>-->
                <!--endprint1-->
                <div class="order_info">
                    <h4>物流信息</h4>
                    <div class="container-fluid">
                        <div class="row logistics">
                            <div class="col-sm-12">
                                <p id="detailshippingaddress">收货地址：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingPerson">收货人：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailaddress">详细地址：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingMobile">手机： </p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailshippingPhone">联系电话：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="detailcustomerRemark">客户留言：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="backDeliveryCode">退货快递单号：</p>
                            </div>
                            <div class="col-sm-12">
                                <p id="backDeliveryCompany">退货快递公司：</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div style="text-align:center;">
                    <button type="button" class="btn btn-primary Noprint" onclick="closeDetail();">确定</button>
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
<script src="${bath}/static/js/ThirdPlantManage/thirdOrderBackList.js?version=${VERSION}"></script>
<script src="${bath}/static/js/bootstrap-datetimepicker.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/bootstrap-datetimepicker.zh-CN.js?version=${VERSION}"></script>
<script type="application/javascript">
    $(function(){
        $('select[data-live-search="true"]').select2();
    });
</script>
</body>
</html>













