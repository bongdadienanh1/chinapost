

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
    <script type="text/javascript" src="${basePath}/static/js/goods/goods_vali.js?version=${VERSION}"></script>
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
    <input type="hidden" value="${isThirdAuditUsed!''}" id="isThirdAuditUsed"/>
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
    <div class="app-container">
        <div class="app-content">
            <div class="order-num">退单号：11111111111</div>
            <div class="order-det">
                <div class="od-tit"><h3>退单详情</h3></div>
                <div class="od-cont">
                    <table>
                        <tbody>
                        <tr>
                            <td style="width: 30%"><em>原订单号：</em>${backorder.orderCode }</td>
                            <td style="width: 30%"><em>收货人地址：</em>${backorder.order.shippingProvince!''}  ${backorder.order.shippingCity!''}   ${backorder.order.shippingCounty!''}</td>
                            <td ><em>退货原因：</em>
                            <#if backorder.backReason??>
                                <#if backorder.backReason=='1'>不想买了</#if>
                                <#if backorder.backReason=='2'>收货人信息有误</#if>
                                <#if backorder.backReason=='3'>货品损坏</#if>
                                <#if backorder.backReason=='4'>其他</#if>
                            </#if>
                            </td>
                        </tr>
                        <tr>
                            <td><em>退单时间：</em><#if backorder.backTime??>${backorder.backTime?string("yyyy-MM-dd HH:mm:ss") }<#else></#if></td>
                            <td><em>详细地址：</em><#if backorder.order.shippingAddress??>${backorder.order.shippingAddress!''}<#else></#if></td>
                            <td><em>上传凭证：</em>
                            <#if backorder.applyCredentials??>
                                <#if backorder.applyCredentials=='1'>
                                    没有任何凭证
                                <#else>
                                    <#if imglist??>
                                        <#list imglist as imgs>
                                            <img src="${imgs}" alt="" style="width:30px;height:30px;"/>
                                        </#list>
                                    </#if>
                                </#if>
                            </#if>
                            </td>
                        </tr>
                        <tr>
                            <td><em>退单状态：</em>
                            <#if backorder.backCheck??&&backorder.backCheck=="0">
                                退货审核
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="1">
                                待客户填写物流地址
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="2">
                                拒绝退货
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="3">
                                待收货
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="4">
                                订单结束
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="6">
                                审核退款
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="7">
                                拒绝退款
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="8">
                                收货失败
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="9">
                                待客户填写物流地址
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="10">
                                退款成功
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="11">
                                退货待退款
                            </#if>
                            <#if backorder.backCheck??&&backorder.backCheck=="12">
                                退款待退款
                            </#if>
                            </td>
                            <td><em>收货人姓名：</em>${backorder.order.shippingPerson}</td>
                        <#--<td><em>退单说明：</em>-->
                        <#if backorder.backRemark??>${backorder.backRemark}</#if>
                            </td>
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
                <#--<th>原始价格</th>
                <th>交易价格</th>-->
                    <th>退单总价</th>
                </tr>
                </thead>
                <tbody>
                <#--<#if backorder.orderGoodslistVo??>-->
                <#--<#list backorder.orderGoodslistVo as orderGoods>-->
                <tr>
                    <td>
                        <a href="javascript:;"><img alt="" width="30px" height="30px" src="${orderGoods.goodsInfoImgId!'' }" /></a>
                    </td>
                    <td>111件</td>
                    <td>¥100</td>
                </tr>
                <#--</#list>-->
                <#--</#if>-->
                </tbody>
                <tfoot></tfoot>
            </table>


        <#if backorder.orderMarketingList??>
            <table class="table">
                <tr>
                    <td class="tc fb" width="30%">订单促销信息</td>
                    <td claas="tl"></td>
                </tr>
                <#if backorder.order.orderMarketingList??>
                    <#list backorder.order.orderMarketingList as orderMarketing>
                        <#if orderMarketing.marketing??>
                            <tr>
                                <td class="tc" width="30%"><#if orderMarketing.marketing.marketingName??>${orderMarketing.marketing.marketingName }</#if></td>
                                <td claas="tl">
                                    <#if orderMarketing.marketing.codexType=="1">
                                        直降金额 ${orderMarketing.marketing.priceOffMarketing.offValue}
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="2">
                                        赠送货品
                                        <#if orderMarketing.marketing.giftList??>
                                            <#list orderMarketing.marketing.giftList as gift>
                                            ${gift.giftName }
                                            </#list>
                                        </#if>
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="3">
                                        赠送优惠券
                                        <#if orderMarketing.marketing.couponList??>
                                            <#list orderMarketing.marketing.couponList as coupon>
                                            ${coupon.couponName }
                                            </#list>
                                        </#if>
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="4">
                                        折扣${orderMarketing.marketing.discountMarketing.discountValue }
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="5">
                                        满${orderMarketing.marketing.fullbuyReduceMarketing.fullPrice }
                                        减${orderMarketing.marketing.fullbuyReduceMarketing.reducePrice }
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="6">
                                        满${orderMarketing.marketing.fullbuyPresentMarketing.fullPrice }
                                        赠
                                        <#if orderMarketing.marketing.giftList??>
                                            <#list orderMarketing.marketing.giftList as gift>
                                            ${gift.giftName }
                                            </#list>
                                        </#if>
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="7">
                                        满${orderMarketing.marketing.fullbuyCouponMarketing.fullPrice }
                                        赠
                                        <#if orderMarketing.marketing.couponList??>
                                            <#list orderMarketing.marketing.couponList as coupon>
                                            ${coupon.couponName }
                                            </#list>
                                        </#if>
                                    </#if>
                                    <#if orderMarketing.marketing.codexType=="8">
                                        满${orderMarketing.marketing.fullbuyDiscountMarketing.fullPrice }
                                        折 ${orderMarketing.marketing.fullbuyDiscountMarketing.fullbuyDiscount }
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                    </#list>
                </#if>
                <#if backorder.order.orderMarketingList??>
                    <tr>
                        <td class="tc fb" width="30%">订单促销赠送赠品</td>
                        <td claas="tl"></td>
                    </tr>
                    <#list backorder.order.orderMarketingList as orderMarketing>
                        <#if orderMarketing.orderGiftList??>
                            <#list orderMarketing.orderGiftList as giftlist >
                                <tr>
                                    <td class="tc" width="30%">赠品编号:${giftlist.gift.giftCode }</td>
                                    <td claas="tl">赠品名称:${giftlist.gift.giftName }</td>
                                </tr>
                            </#list>
                        <#else>
                            <td class="tc fb" width="30%"></td>
                            <td claas="tl">无赠品信息</td>
                        </#if>
                    </#list>

                    <tr>
                        <td class="tc fb" width="30%">订单促销送优惠券</td>
                        <td claas="tl"></td>
                    </tr>
                    <#list backorder.order.orderMarketingList as orderMarketing>
                        <#if orderMarketing.orderCouponList??>
                            <#list orderMarketing.orderCouponList as couponlist >
                                <tr>
                                    <td class="tc" width="30%">优惠券卷码：${couponlist.couponNo }</td>
                                    <td claas="tl">优惠劵名称:${couponlist.coupon.couponName }</td>
                                </tr>
                            </#list>
                        <#else>
                            <td class="tc fb" width="30%"></td>
                            <td claas="tl">无优惠劵信息</td>
                        </#if>
                    </#list>
                </#if>
            </table>
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
        $(".chosen-select").chosen();
        $(".brandsChosen").chosen();
        /* /!* 富文本编辑框 *!/
         $('.summernotedesc').summernote({
             height: 300,
             tabsize: 2,
             lang: 'zh-CN',
             onImageUpload: function(files, editor, $editable) {
                 sendFile(files,editor,$editable);
             }
         });


         /!* 富文本编辑框 *!/
         $('.summernotemobile').summernote({
             height: 300,
             tabsize: 2,
             lang: 'zh-CN',
             onImageUpload: function(files, editor, $editable) {
                 sendFile(files,editor,$editable);
             }
         });*/
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

    function getInputVal(obj){
        $("#basic-proPack").html($(obj).val()+"/箱");
    }
</script>
<#import "common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />

</body>
</html>

