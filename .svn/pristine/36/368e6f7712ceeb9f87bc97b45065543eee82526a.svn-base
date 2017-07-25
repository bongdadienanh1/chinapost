<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <meta name="format-detection" content="telephone=no" />
   <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/pageAction.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
</head>

<body>
<#if order??>
<div class="order content-order-detail mb50">
    <div class="order-number">
        <div class="list-item">
            <h3 class="item-head"><label for="">订单号：</label><span>${(order.orderNo)!''}</span>
            <#assign cFlag=0 />
      		<#list order.goods as good>
	        	<#if good.evaluateFlag== '0'>
	                 <#assign cFlag=cFlag+1 />
	            </#if>
	         </#list>
            <span class="curValue text-them">
            <#if order.orderStatus??>
                <#if order.orderStatus=="SUBMITED">
                    <#if order.orderLinePay=="0">
                        待发货
                    <#else>
                        待付款
                    </#if>
                <#elseif (order.orderStatus=="PAYED") >
                    <#if order.selfPick?string('true','false')=='false'>
                        待发货
                    <#else >
                        待提货
                    </#if>
                <#elseif order.orderStatus=="DELIVERED">
                    待收货
                <#--<#elseif (order.orderStatus=="3" && cFlag>0) >-->
                <#--等待评价-->
                <#--<#elseif order.orderStatus=="3" && cFlag==0 && sFlag!=0>-->
                <#--等待晒单-->
                <#elseif order.orderStatus=="RECIEVED" && cFlag==0 && sFlag==0>
                    已完成
                <#elseif (order.orderStatus=="CANCELED") >
                    已取消
                <#elseif order.orderStatus=="RETURN_APPLIED">
                    退货审核
                <#elseif order.orderStatus=="RETURN_WAIT_DELIVER_INFO">
                    待填写物流信息
                <#elseif order.orderStatus=="RETURN_DENIED">
                    拒绝退货
                <#elseif order.orderStatus=="RETURN_WAIT_RECEIVE">
                    待商家收货
                <#elseif order.orderStatus=="RETURN_DELIVER_FAILURE">
                    商家拒绝收货
                <#elseif order.orderStatus=="RETURN_WAIT_REFUND">
                    待退款
                <#elseif order.orderStatus=="REFUND_APPLIED">
                    退款审核
                <#elseif order.orderStatus=="REFUND_DENIED">
                    拒绝退款
                <#elseif order.orderStatus=="REFUND_WAIT_REFUND">
                    待退款
                <#elseif order.orderStatus=="REFUND_FINISHED">
                    退款成功
                <#elseif order.orderStatus=="RETURN_FINISHED">
                    退货成功
                </#if>
			</#if>
            </span></h3>
            <#if order.deliveryCode??>
                <p class="deliveryCode" style="color: #3c3c3c">提货码：${order.deliveryCode!''}</p>
            </#if>
        </div>
        <div class="list-item">
            <h3><#if order.orderStatus??>
                <#if order.orderStatus=="SUBMITED">
                    <#if order.orderLinePay=="0">
                        等待发货
                    <#else>
                    <#--<#if order.otherAmount??&& order.ucoinPrise??>-->
                    <#--<#if (order.otherAmount!'0.00')?string('0.00')!='0.00'&&(order.ucoinPrise)?string('0.00')!='0.00'>-->
                    <#--待继续付款-->
                    <#--<#else >-->
                    <#--等待付款-->
                    <#--</#if>-->
                        剩余72小时自动关闭交易
                    <#--</#if>-->
                    </#if>
                <#elseif (order.orderStatus=="PAYED") >
                    <#if order.selfPick?string('true','false')=='true'>
                        请去您选择的自提点，提货
                    <#else >
                        订单会在24小时内发货，请耐心等待。
                    </#if>
                <#elseif order.orderStatus=="DELIVERED">
                    还剩6天12时自动确认收货
                <#--<#elseif (order.orderStatus=="3" && cFlag>0) >-->
                <#--等待评价-->
                <#elseif order.orderStatus=="RECIEVED">
                    您的订单已签收。
                <#elseif (order.orderStatus=="CANCELED") >
                    感谢您的购买，欢迎再次光临！
                </#if>
            </#if>
            </h3>
        </div>
    </div>
    <div class="receive-info mt10">
        <div class="list-item">
            <#if order.selfPick?string('true','false')=='true' && enterpriseInfo??>
                <p class="dress-info">${enterpriseInfo.enterpriseName!''}</p>
                <p class="dress-info">${enterpriseInfo.address!''}</p>
            <#else>
                <h3><span class="name">
                    <#--<i class="user"></i>-->
                     <#if order.shippingPerson??>
                                ${order.shippingPerson}
                            </#if></span>
                    <span class="phoneNum">
                        <#--<i class="phone"></i>-->
                    <#if order.shippingMobile??>
                        ${order.shippingMobile?default('')}
                    </#if></span></h3>
                <p class="dress-info" style="color: #333;margin-top: .6em;">
                    <#--<i class="dress"></i>-->
                  <#if order.shippingProvince??>${order.shippingProvince?default('')}</#if>
                  <#if order.shippingCity??>${order.shippingCity?default('')}</#if>
                  <#if order.shippingCounty??>${order.shippingCounty?default('')}</#if>
                  <#if order.shippingAddress??>${order.shippingAddress?default('')}</#if>
                </p>
                </#if>
            <#--<i class="arrow-right"></i>-->
        </div>
    </div>
    <div class="order-info mt10">
        <div class="list-body-line">
            <#if (order.goods)?size gt 0>
              <#list order.goods as good>
               <#if good_index??&&good_index lt 2>

                   <#if good.subjectId??&& good.subjectId!=0>
               <a class="order_good_link"  href="${basePath}/productdetail?productId=${good.goodsId}">
                   <div class="list-item order-pro-item">
                    <div class="profrom-way">
                        <img class="logoImg" src="${good.subjectImg}"/>
                        <span class="logoName">${good.subjectName}</span>
                    </div>
                   <#else>
                   <a class="order_good_link"  href="${basePath}/productdetail?productId=${good.goodsId}">
                   <div class="list-item order-pro-item">
                   </#if>
	                <div class="pro-item" style="border-bottom: none;">
                        <#--<h3 class="title">${(good.subjectName)!''}</h3>-->
	                    <div class="propic">
	                        <img width="78" height="78" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" />
	                    </div>
	                    <div class="prodesc">
	                        <h3 class="title">${(good.goodsName)!''}</h3>
	
	                        <p class="price" style="color: #333;"><span class="num"><#if good.goodsPrice??>
												${good.goodsPrice!''}
											</#if> </span>邮豆</p>
	                        <span class="pro-num" style="font-family: "华文细黑">×${good.goodsNum}</span>
	                    </div>
	                </div>
	            </div>
	            </a>
	            <#else>
                    <#--<#list order.goods as goodtwo>-->
                    <#if good.subjectId??&& good.subjectId!=0>
	                    <a class="order_good_link"  href="${basePath}/productdetail?productId=${good.goodsId}">
                    <div class="list-item order-pro-item" style="display:none">
                        <div class="profrom-way">
                            <img class="logoImg" src="${good.subjectImg}"/>
                            <span class="logoName">${good.subjectName}</span>
                        </div>
                    <#else >
                        <a class="order_good_link"  href="${basePath}/productdetail?productId=${good.goodsId}">
                    <div class="list-item order-pro-item" style="display:none">
                    </#if>

	                <div class="pro-item">
                        <#--<h3 class="title">${(good.subjectName)!''}</h3>-->
	                    <div class="propic">
	                        <img width="78" height="78" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" />
	                    </div>
	                    <div class="prodesc">
	                        <h3 class="title">${(good.goodsName)!''}</h3>
	                        <p class="price" style="color: #333"><span class="num"><#if good.subjectGoodsPrice??>
								${good.subjectGoodsPrice!''}
							</#if> </span>邮豆</p>
	                        <span class="pro-num">×${good.goodsNum}</span>
	                    </div>
	                </div>
	            </div>
	           </a>
                    <#--</#list>-->
            </#if>
          </#list>
       </#if>
      </div>
      <#if (order.goods)?size gt 2>
         <div class="list-item see-all">
            — — — — 显示其他${order.goods?size-2}件商品 — — — —
        </div>
      </#if>
    </div>
    <#--<div class="paid-way mt10">-->
        <#--<div class="list-item">-->
            <#--<h3 class="item-head">支付方式及发票信息</h3>-->

            <#--<div class="list-value">-->
                <#--<p>支付方式：-->
	                <#--<#if order.payId??>-->
	                      <#--<#if order.payId==2>-->
								<#--货到付款-->
	                      <#--<#else>-->
	                         	<#--在线支付-->
	                      <#--</#if>-->
	                <#--</#if>-->
                <#--</p>-->
               <#--<ul>-->
                    <#--<li><label for="">户名：</label>234</li>-->
                    <#--<li><label for="">开户行：</label>中国银行</li>-->
                    <#--<li><label for="">账号：</label>6223384773829847857</li>-->
                    <#--<li><label for="">汇款识别码：</label>123456</li>-->
                <#--</ul>-->
                <#--<#if order.invoiceType??&&order.invoiceType!='0'>-->
                 <#--<p><label for="">发票类型：</label>-->
                   <#--<#if order.invoiceType=='1'>-->
                                    <#--普通发票-->
                   <#--<#elseif order.invoiceType=='2'>-->
                                     <#--增值发票-->
                  <#--</#if>-->
                <#--</p>-->
                <#--<p class="light">发票抬头：-->
                        <#--<#if order.invoiceTitle??>-->
                             <#--${order.invoiceTitle?default('')}-->
                        <#--</#if>-->
                <#--</p>-->
               <#--<p class="light">发票内容：-->
                        <#--<#if order.invoiceContent??>-->
                             <#--${order.invoiceContent?default('')}-->
                        <#--</#if>-->
                <#--</p>-->
             <#--</#if>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <div class="all-info mt10">
        <div class="list-item">
            <ul class="price-total">
                <li><label for="">商品金额</label><span class="value">${order.oldPrice!''}邮豆</span></li>
                <li><label for="">运费</label><span class="value"><#if order.shippingFee??> ${order.shippingFee!''} <#else>0</#if>邮豆</span></li>
                <#--<li><p class="textDetailed">订单总价及支付明细</p></li>-->
                <#--<li><label for="">其它支付</label><span class="value text-them" id="otherPayFee">￥<#if order.otherAmount??> ${(order.otherAmount)?string('0.00')}<#else>0.00</#if></span></li>-->
                <li><label for="">订单总价（含运费和邮宝）</label><span class="value text-them" id="sPrice"><#if order.moneyPaid??> ${order.moneyPaid!''}<#else>0</#if></span>邮豆</li>
            </ul>
        </div>
    </div>
   <#if order.isValet?string('true','false')=='true'>
       <div class="all-info mt10">
           <div class="list-item">
               <ul class="price-total">
                   <li><label for="">下单类型</label><span class="value">网点自提点</span></li>
                   <li><label for="">操作者</label><span class="value">${enterpriseInfo.accountName!''}</span></li>
               </ul>
           </div>
        </div>
   </#if>
</div>
        <#if order.orderStatus??>
        <#if order.orderStatus!="CANCELED" &&order.orderStatus!="RETURN_APPLIED">
    <div class="bar-bottom">
<div class="order content-order-all" id="items">
    <div class="order-item">
        <div class="order-bottom">
            <div class="list-item">

                <div class="too-btn">

                        <#if order.orderStatus=="SUBMITED">
                            <#--<#if order.otherAmount??&& order.ucoinPrise??>-->
                                <#--<#if (order.otherAmount!'0.00')?string('0.00')!='0.00'&&(order.ucoinPrise)?string('0.00')!='0.00'>-->
                                    <#--<p class="allPrice"><label for="">待支付:</label><span style="color: red" >￥<#if order.otherAmount??> ${(order.otherAmount)?string('0.00')}<#else>0.00</#if></span></p>-->
                                    <#--<a href="javascript:void(0);" class="btn" onClick="cancelOrder('${order.orderId}')">取消订单</a>-->
                                    <#--<#if order.payId==1>-->
                                        <#--<a href="javascript:void(0);" class="btn pay-btn" onclick="payOrder('${order.orderId}','${order.otherAmount}')">继续支付</a>-->
                                    <#--</#if>-->
                                    <#--<#else>-->
                                        <p class="allPrice"><label for="">待支付:</label><span style="color: red" ><#if order.moneyPaid??> ${(order.moneyPaid)?string('0.00')}<#else>0.00</#if>邮豆</span></p>
                                        <a href="javascript:void(0);" class="btn" onClick="cancelOrder('${order.orderNo}')">取消订单</a>
                                        <#if order.payId==1>
                                            <a href="javascript:void(0);" class="btn pay-btn" onclick="payOrder('${order.orderNo}')">立即支付</a>
                                        </#if>
                                <#--</#if>-->
                            <#--</#if>-->
                        <#elseif order.orderStatus=="PAYED">
                            <a  class="btn"  href="${basePath}/applyBackMoney?orderCode=${order.orderNo}">申请退款</a>
                        <#elseif order.orderStatus=="DELIVERED">
                            <#list order.expressno as expressno>
                                <#if expressno_index==0>
                                    <#if expressno.expressName?? && expressno.expressName == '邮政小包'>
                                        <a href="http://m.kuaidi100.com/index_all.html?type=邮政国内&postid=${expressno.expressNo!}&callbackurl=${sys.bsetAddress!}/mobile/myorder?type=3"
                                           class="btn">查看物流</a>
                                    <#else>
                                        <a href="http://m.kuaidi100.com/index_all.html?type=${expressno.expressName!}&postid=${expressno.expressNo!}&callbackurl=${sys.bsetAddress!}/mobile/myorder?type=3"
                                           class="btn">查看物流</a>
                                    </#if>
                                </#if>
                            </#list>
                            <#--<a  class="btn"  href="${basePath}/applyBackMoney?orderCode=${order.orderNo}">申请退款</a>-->
                            <a href="javascript:;" class="btn btn-warning" onClick="comfirmgoods('${order.orderNo}')">确认收货</a>
                        <#elseif (order.orderStatus=="RECIEVED")>
                        <#--<a href="javascript:;" class="btn btn-default">查看物流</a>-->
                            <a class="btn"  href="${basePath}/applyBackGoods?orderCode=${order.orderNo}">申请退货</a>
                            <#--<a  href="${basePath}/comment-${order.orderId}.html" class="btn btn-default">评价晒单</a>-->
                            <#--<a  href="${basePath}/comment-${order.orderId}.html" class="btn btn-default">删除订单</a>-->
                        </#if>

                </div>
            </div>
        <#--</div>-->
    </div>
    </div>
    </div>
        </#if>
        </#if>
</div>
<div class="opacity-bg-1" style="display:none"></div>
<input id="payOrderId" value="" type="hidden">
<input id="status" value="0" type="hidden">
<input type="hidden" id="type" value="${type!''}">
<input type="hidden" id="basePath" value="${basePath}">
<div class="order content-order-all" id="items">
<div class="tip-box" id="confirm_order" style="display:none">
    <div class="tip-body2">
        <h3 id="tiptitle">是否确认收货？</h3>
        <div class="btn-group">
            <a class="btn btn-grey" href="javascript:;" onclick="cancelComfirmGoods()">取&nbsp;消</a>
            <a class="btn btn-them" href="javascript:;" onclick="comfirmGoodsSucc()">确&nbsp;定</a>
        </div>
    </div>
</div>

</div>
</#if>
<#--<#include "../common/smart_menu.ftl">-->
</body>
<script src="${basePath}/static/js/myorder.js"></script>
<script src="${basePath}/static/js/common.js"></script>
    <script>
        $(function(){
            $(".bar-bottom a:eq(4)").addClass("selected");
            /* 显示隐藏的商品 */
            $('.see-all').click(function(){
                $(this).prev('.list-body-line').find('.list-item').show();
                $(this).remove();
            });
        });
        function payOrder(orderNo) {
            var zhifumima = dialog({
                width: 260,
                content: '<p class="tc">输入支付密码</p><p class="tc"><div class="passContainer"> <div class="input-box"> <input type="number" value="" class="passwordInput" maxlength="6"/> </div> <div class="passItem-box"> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div></div> </div></p> ',
                okValue: '确定',
                cancelValue: '取消',
                ok: function () {
                    //验证支付密码
                    $.post("${basePath}/payOrder",{
                        orderCode:orderNo,
                        paykey:$(".passwordInput").val()
                    },function(data){
                        if(data.response == "success"){
                            window.location.href = "${basePath}/paysuccess";
                        }else {
//                                var text = data.data.text;
//                                var errorMsg = dialog({
//                                    width: 260,
//                                    title:"提示",
//                                    content:text
//                                });
//                                errorMsg.showModal();
                            window.location.href = "${basePath}/myorder"
                        }
                    });
                <#--$('#payPassword').val($('.passwordInput').val());-->
                <#--$.post('${basePath}/checkCustomerPayPassword.htm', {payPassword: $('.passwordInput').val()}, function (data) {-->
                <#--if (data.codeNo == 1) {-->
                <#--&lt;#&ndash;$("#subForm").attr("action", "${basePath}/submitorder.htm");&ndash;&gt;-->
                <#--&lt;#&ndash;$("#subForm").submit();&ndash;&gt;-->
                <#--return true;-->
                <#--} else {-->
                <#--alertStr('支付密码错误');-->
                <#--}-->
                <#--})-->
                },
                cancel: function () {
                    //停留在当前页面
                    return true;
                }
            });

            zhifumima.showModal();

            $('.passContainer').click(function () {
                $('.passwordInput').focus();
            });

            $('.passwordInput').bind("input propertychange change", function (event) {
                if (event.type != "propertychange" || event.originalEvent.propertyName == "value") {
                    if ($('.passwordInput').val().length > 6) {
                        $('.passwordInput').val($('.passwordInput').val().substr(0, 6));
                    }

                    $('.passContainer .passItem-box .passItem').html("");
                    for (var i = 0; i < $('.passwordInput').val().length; i++) {
                        $('.passContainer .passItem-box .passItem').eq(i).html("*");
                    }
                }
            });


            $('.passwordInput').focus();
        };

//                    var yuePay = dialog({
//                        width: 260,
//                        content: '<p class="tc">支付密码</p><p class="tc"><input type="text" class="p10"></p> ',
//                        okValue: '确定',
//                        cancelValue: '取消',
//                        ok: function () {
//                            //确认离开，跳转到下一页面
//                            return false;
//                        },
//                        cancel: function () {
//                            //停留在当前页面
//                            return true;
//                        }
//                    });
//                    $('a.weixin').click(function () {
//                        payBox.close().remove();
//                        weixinPay.showModal();
//                        $("#paymoney").text('￥' + orderPrice);
//                    });
//                    $('a.hebao').click(function () {
//                        payId="40";
//                    });
//
//                    $('a.yue').click(function () {
//                        payBox.close().remove();
//                        yuePay.showModal();
//                    });
//                }
//            });
//            $("#payOrderId").val(orderId);
//            payBox.showModal();
//        }
        <#--function callpay(appId,timeStamp,nonceStr,package,sign){-->
            <#--WeixinJSBridge.invoke('getBrandWCPayRequest',{-->
                <#--"appId" : appId,-->
                <#--"timeStamp" : timeStamp,-->
                <#--"nonceStr" : nonceStr,-->
                <#--"package" : package,-->
                <#--"signType" : "MD5",-->
                <#--"paySign" : sign-->
            <#--},function(res){-->
                <#--WeixinJSBridge.log(res.err_msg);-->
                <#--if(res.err_msg == "get_brand_wcpay_request:ok"){-->
                    <#--alertStr("支付成功！");-->
                    <#--location.href="${basePath}/paysucccesswx.htm";-->
                <#--}else if(res.err_msg == "get_brand_wcpay_request:cancel"){-->
                    <#--alertStr("用户取消支付！");-->
                    <#--location.href="${basePath}/customer/detail-"+$("#payOrderId").val()+".html";-->
                <#--}else {-->
                    <#--alert(res.err_msg);-->
                    <#--alertStr("支付失败！");-->
                    <#--location.href = "${basePath}/customer/detail-" + $("#payOrderId").val() + ".html";-->
                <#--}-->
            <#--})-->
        <#--}-->
  </script>
</html>