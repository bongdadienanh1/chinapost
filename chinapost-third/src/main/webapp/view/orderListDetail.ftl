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

    <div class="app show_text" style="display: none;"">
    <div class="app-container">
        <div class="app-content">
            <div class="order-num">订单号：${orderGoods.orderCode}</div>

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
                                            ${orderGoods.orderStatus}
                                        </td>
                                        <td><em>运费：</em>
                                            ${orderGoods.expressPrice?string("0.00")}邮豆
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><em>下单时间：</em><#if orderGoods.createTime??>${orderGoods.createTime?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                        <td><em>付款时间：</em><#if orderGoods.payTime??>${orderGoods.payTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                                    </tr>
                                    <tr>
                                        <td><em>订单交易金额：</em>${orderGoods.orderPrice?string("0.00")}邮豆</td>
                                        <td><em>邮豆支付金额：</em>${orderGoods.ucoinPrise?string("0.00")}邮豆</td>
                                    </tr>
                                    <tr>
                                        <td><em>客户留言：</em><#if orderGoods.customerRemark??>${orderGoods.customerRemark }</#if></td>
                                        <td colspan="4"><em>卖家备注：</em><#if orderGoods.sellerRemark??>${orderGoods.sellerRemark }</#if></td>
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
                                    <tr>
                                        <td><em>收货地址：</em>${orderGoods.shippingProvince !''}  ${orderGoods.shippingCity!'' }   ${orderGoods.shippingCounty !''}</td>
                                        <td><em>详细地址：</em><#if orderGoods.shippingAddress??>${orderGoods.shippingAddress }</#if></td>
                                    </tr>
                                    <tr>
                                        <td><em>收货人姓名：</em>${orderGoods.shippingPerson!'' }</td>
                                        <td><em>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</em><#if orderGoods.shippingMobile??>${orderGoods.shippingMobile }</#if></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <#if orderGoods.orderGoodsList??>
            <#if orderGoods.mallId==0>
                <#list orderGoods.orderGoodsList as orderGoodsPurdct>
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="400">货品名称</th>
                            <th>货品个数</th>
                            <th>货品单价</th>
                            <th>交易总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                                <span class="">
                                </span>
                        </tr>
                        <tr>
                            <td>
                                <div class="goods-intro">
                                    <img alt="" class="gds-img" src="<#if orderGoodsPurdct.goodsImg??>${orderGoodsPurdct.goodsImg}<#else></#if>" />
                                    <div class="gds-info">
                                        <p class="gds-name"><#if orderGoodsPurdct.goodsInfoName??>${orderGoodsPurdct.goodsInfoName }<#else></#if></p>
                                    </div>
                                </div>
                            </td>
                            <td>${orderGoodsPurdct.goodsInfoNum}(件)</td>
                            <td>${orderGoodsPurdct.goodsInfoPrice?string("0.00")}邮豆</td>
                            <td>${orderGoodsPurdct.goodsInfoSumPrice?string("0.00")}邮豆</td>
                        </tr>
                        </tbody>
                    </table>
                </#list>
            <#else >
                <#list orderGoods.orderGoodsList as orderGoodsPurdct>
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="400">货品名称</th>
                            <th>货品个数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                                <span class="">
                                </span>
                        </tr>
                        <tr>
                            <td>
                                <div class="goods-intro">
                                    <img alt="" class="gds-img" src="<#if orderGoodsPurdct.goodsProductVo??>${orderGoodsPurdct.goodsProductVo.goodsInfoImgId}<#else></#if>" />
                                    <div class="gds-info">
                                        <p class="gds-name"><#if orderGoodsPurdct.goodsProductVo??>${orderGoodsPurdct.goodsProductVo.goodsInfoName }<#else></#if></p>
                                    </div>
                                </div>
                            </td>
                            <td>${orderGoodsPurdct.goodsInfoNum}</td>
                        </tr>
                        </tbody>
                    </table>
                </#list>
            </#if>
        </#if>
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
    });
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
<#--<#import "common/selectindex.ftl" as selectindex>-->
<#--<@selectindex.selectindex "${n!''}" "${l!''}" />-->

</body>
</html>

