<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单-<#if type?? && type='1'>待付款<#elseif  type?? && type='3'>待收货<#elseif  type?? && type='4'>
        待评价<#elseif  type?? && type='6'>待退款/退货<#else>全部</#if>订单</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey!''}">
    <meta name="description" content="${seo.meteDes!''}">
<#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/pageAction.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
</head>

<body>
<input type="hidden" id="basePath"  value="${basePath}"/>
<input type="hidden" id="type" value="${type!''}">
<div class="order content-order-all" id="items">
<#if (pb.list?? && pb.list?size>0)>
    <#list pb.list as order>
        <div class="order-item">
            <div class="order-number">
                <div class="list-item">
                    <h3 class="item-head"><label for="">订单号：</label><span>${order.orderNo}</span>
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
                            <#elseif order.orderStatus=="RECIEVED">
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
                    </span>
                    </h3>
                </div>
            </div>
            <div class="order-info">
                <#if (order.goods?size>2)>
                    <div class="list-body-box">
                        <div class="list-item">
                            <div class="box-body">
                                <ul>
                                    <#list order.goods as good>
                                        <li>
                                            <a href="${basePath}/orderdetails?orderId=${order.orderId}">
                                                <img width="78" height="78" title="${(good.goodsName)!''}"
                                                     alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}"/>
                                            </a>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </div>
                    </div>
                <#else>
                    <#list order.goods as good>
                        <div class="list-body-line">
                            <div class="list-item">
                                <a href="${basePath}/orderdetails?orderId=${order.orderId}">
                                    <div class="pro-item" style="border-bottom: none;">
                                        <div class="propic">
                                            <img width="78" height="78" title="${(good.goodsName)!''}"
                                                 alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}"/>
                                        </div>
                                        <div class="prodesc" style="color: #333;">
                                            <h3 class="title">${good.goodsName!''}</h3>
                                            <p class="price" style="color: #333;"><span class="num">
                                                <#if good.goodsPrice??>
										   	${good.goodsPrice!''}
										</#if>
                                    </span>邮豆</p>
                                            <span class="pro-num">×${good.goodsNum}</span>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
            <div class="order-bottom">
                <div class="list-item">
                    <h3 class="item-head">
                        <label for="" style="vertical-align: middle;">实付款：</label><span class="pay-money" style="color: #333;">
                        <#if order.moneyPaid??>
                        ${order.moneyPaid!''}
                        </#if>邮豆</span>
                    </h3>
                    <div class="too-btn">
                        <#if order.orderStatus??>
                            <#if (order.orderStatus=="SUBMITED")>
                                <#--<#if order.payId==1>-->
                                    <a href="javascript:void(0);" class="btn pay-btn"
                                       onclick="payOrder('${order.orderNo}')">立即支付</a>
                                <#--</#if>-->
                                <a href="javascript:void(0);" class="btn-grey"
                                   onClick="cancelOrder('${order.orderNo}')">取消订单</a>
                            <#--<#elseif (order.orderStatus=="2")>-->
                                <#--<#if order.orderExpressType != 1>-->

                                <#--<#else>-->
                                    <#--<a href="javascript:void(0)" class="btn-grey">上门自提</a>-->
                            <#elseif order.orderStatus=="DELIVERED">
                                <#list order.expressno as expressno>
                                    <#if expressno_index==0>
                                        <#if expressno.expressName?? && expressno.expressName == '邮政小包'>
                                            <a href="http://m.kuaidi100.com/index_all.html?type=邮政国内&postid=${expressno.expressNo!}&callbackurl=${basePath}/myorder?type=3"
                                               class="btn">查看物流</a>
                                        <#else>
                                            <a href="http://m.kuaidi100.com/index_all.html?type=${expressno.expressName!}&postid=${expressno.expressNo!}&callbackurl=${basePath}/myorder?type=3"
                                               class="btn">查看物流</a>
                                        </#if>
                                    </#if>
                                </#list>
                                <a href="javascript:void(0);" class="btn" onClick="comfirmgoods('${order.orderNo}')">确认收货</a>
                            <#elseif order.orderStatus=="RECIEVED">
                                <#--<a href="${basePath}/comment-${order.orderId}.html" class="<#if cFlag gt 0 || sFlag gt 0>btn<#else>btn-grey</#if>">评价晒单</a>-->
                                <a class="btn-grey" href="${basePath}/applyBackGoods?orderCode=${order.orderNo}">申请退货</a>
                            <#elseif order.orderStatus=="PAYED">
                                <#if order.selfPick?string('true','false')=='false'>
                                    <a class="btn-grey" href="${basePath}/applyBackMoney?orderCode=${order.orderNo}">申请退款</a><br/>
                                    <#else >
                                        <a class="btn-grey" href="${basePath}/applyBackMoney?orderCode=${order.orderNo}">申请退款</a>
                                        <a class="btn" href="javascript:void(0)" onclick="selfPick(${order.orderNo})">查看提货码</a>
                                </#if>
                            <#elseif order.orderStatus=="RETURN_WAIT_DELIVER_INFO" && order.selfPick?string('true','false')=='false'>
                                <#--<a class="btn fill-flow" href="javascript:void(0)" onclick="expressInfo('${(backorder.order.orderCode)!''}')" style="width:100px">填写物流信息</a>-->
                                <#--<a class="btn" href="${basePath}/backorderpricedetail?backOrderCode=${order.orderNo}">退货详情</a>-->
                            </#if>
                            </#if>
                            <#--<#if isBackOrder==1>-->
                                <#--<#if (order.orderStatus=="RECIEVED")>-->
                                    <#--<a href="${basePath}/comment-${order.orderId}.html"-->
                                       <#--class="<#if cFlag gt 0 || sFlag gt 0>btn<#else>btn-grey</#if>">评价晒单</a>-->
                                <#--&lt;#&ndash; <a href="javascript:void(0)" class="btn" onclick="addCar('${order.orderId}')">再次购买</a>&ndash;&gt;-->
                                    <#--<a class="btn"-->
                                       <#--href="${basePath}/customer/applybackgoods-${order.orderId}.html">申请退货</a>-->
                                <#--<#elseif order.orderStatus=="1"|| order.orderStatus=="4" || order.orderStatus=="5" || order.orderStatus=="6">-->
                                <#--&lt;#&ndash; <a href="javascript:void(0)" class="btn" onclick="addCar('${order.orderId}')">再次购买</a>&ndash;&gt;-->
                                <#--<#elseif (order.orderStatus=="13" ||order.orderStatus=="15" ||order.orderStatus=="18") >-->
                                    <#--<a class="btn"-->
                                       <#--href="${basePath}/customer/backorderpricedetail-${order.orderId}.html">退款详情</a><br/>-->
                                <#--<#elseif order.orderStatus=="8">-->
                                    <#--<a class="btn fill-flow" href="javascript:void(0)"-->
                                       <#--onclick="expressInfo('${(order.orderNo)!''}')" style="width:100px">填写物流信息</a>-->
                                <#--<#elseif  (order.orderStatus=="7" ||-->
                                <#--order.orderStatus=="8" ||-->
                                <#--order.orderStatus=="9" ||-->
                                <#--order.orderStatus=="10" ||-->
                                <#--order.orderStatus=="11" ||-->
                                <#--order.orderStatus=="14" ||-->
                                <#--order.orderStatus=="16" ||-->
                                <#--order.orderStatus=="17" ||-->
                                <#--order.orderStatus=="12")>-->
                                    <#--<#if order.orderStatus=="8">-->
                                        <#--<a class="btn fill-flow" href="javascript:void(0)"-->
                                           <#--onclick="expressInfo('${(order.orderNo)!''}')">物流信息</a>-->
                                    <#--</#if>-->
                                    <#--<a class="btn"-->
                                       <#--href="${basePath}/customer/backordergoodsdetail-${order.orderId}.html">退货详情</a><br/>-->
                                <#--</#if>-->
                                <#--<#if ( order.orderStatus=="RECIEVED"|| order.orderStatus=="DELIVERED") >-->
                                    <#--<a href="${basePath}/customer/applybackgoods-${order.orderId}.html">申请退货</a><br/>-->
                                <#--</#if>-->
                                <#--<#if order.orderStatus=="PAYED">-->
                                    <#--<a class="btn" href="${basePath}/customer/mapplybackmoney-${order.orderId}.html">申请退款</a><br/>-->
                                <#--</#if>-->
                            <#--<#elseif  isBackOrder==2>-->
                                <#--<#if (order.orderStatus=="3")>-->
                                    <#--<a href="${basePath}/comment-${order.orderId}.html"-->
                                       <#--class="<#if cFlag gt 0 || sFlag gt 0>btn<#else>btn-grey</#if>">评价晒单</a>-->
                                <#--&lt;#&ndash; <a href="javascript:void(0)" class="btn" onclick="addCar('${order.orderId}')">再次购买</a>&ndash;&gt;-->
                                <#--<#elseif order.orderStatus=="1"|| order.orderStatus=="4" || order.orderStatus=="5" || order.orderStatus=="6">-->
                                <#--&lt;#&ndash;  <a href="javascript:void(0)" class="btn" onclick="addCar('${order.orderId}')">再次购买</a>&ndash;&gt;-->
                                <#--</#if>-->
                            <#--</#if>-->
                        <#--</#if>-->
                    </div>
                </div>
            </div>
        </div>
    </#list>
<#else>
    <div class="no-orders">
        <div class="list-item">
            <i class="dingdan"></i>
            <h3>您还没有相关的订单</h3>
            <p>再去逛逛吧~</p>
        </div>
        <div class="list-item">
            <a class="btn btn-full" href="${basePath}/main.html">去逛逛</a>
        </div>
    </div>
</#if>
</div>
<div class="list-loading" id="showmore" style="display:none">
    <img alt="" src="${basePath}/static/img/loading.gif">
    <span>加载中……</span>
</div>


<div class="opacity-bg-1" style="display:none"></div>
<input id="payOrderId" value="" type="hidden">
<input id="status" value="0" type="hidden">

<div class="tip-box" id="confirm_order" style="display:none">
    <div class="tip-body2">
        <h3 id="tiptitle">是否确认收货？</h3>
        <div class="btn-group">
            <a class="btn btn-grey" href="javascript:;" onclick="cancelComfirmGoods()">取&nbsp;消</a>
            <a class="btn btn-them" href="javascript:;" onclick="comfirmGoodsSucc()">确&nbsp;定</a>
        </div>
    </div>
</div>

<form class="fill-flow-form" style="display:none;" id="fillForm">
    <input type="hidden" id="orderNo" name="orderNo" value=""/>
    <div class="list-item">
        <h3 class="item-head pr0"><label for="">物流公司</label>
        </h3>
        <div class="list-value">
            <div class="shuoming">
                <input type="text" class="text" placeholder="请填写正确的物流公司" name="wlname" id="wlname" onBlur="wuliuname()">
                <label id="yanzhengname"></label>
            </div>
        </div>
    </div>
    <div class="list-item">
        <h3 class="item-head pr0"><label for="">物流单号</label>
        </h3>
        <div class="list-value">
            <div class="shuoming">
                <input type="text" class="text" placeholder="请填写准确的物流单号" name="wlno" id="wlno" onBlur="wuliudanhao()">
                <label id="yanzhengno"></label>
            </div>
        </div>
    </div>
</form>
<script src="${basePath}/static/js/myorder.js?v=201606040917"></script>
<script src="${basePath}/static/js/common.js"></script>
<#--<script src="${basePath}/js/customer/backorder.js"></script>-->
<script>

    $(window).scroll(function () {
        if ($(this).scrollTop() >= ($('body').height() - $(window).height())) {
            show();
        }
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
                        var text = data.data.text;
                        var errorMsg = dialog({
                            width: 260,
                            title:"提示",
                            content:text
                        });
                        errorMsg.showModal();
                    <#--window.location.href = "${basePath}/pay_error"-->
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


    <#--function callpay(appId, timeStamp, nonceStr, package, sign) {-->
        <#--WeixinJSBridge.invoke('getBrandWCPayRequest', {-->
            <#--"appId": appId,-->
            <#--"timeStamp": timeStamp,-->
            <#--"nonceStr": nonceStr,-->
            <#--"package": package,-->
            <#--"signType": "MD5",-->
            <#--"paySign": sign-->
        <#--}, function (res) {-->
            <#--WeixinJSBridge.log(res.err_msg);-->
            <#--if (res.err_msg == "get_brand_wcpay_request:ok") {-->
                <#--alertStr("支付成功！");-->
                <#--location.href = "${basePath}/paysucccesswx.htm";-->
            <#--} else if (res.err_msg == "get_brand_wcpay_request:cancel") {-->
                <#--alertStr("用户取消支付！");-->
                <#--location.href = "${basePath}/customer/detail-" + $("#payOrderId").val() + ".html";-->
            <#--} else {-->
                <#--alertStr(res.err_msg + "支付失败！");-->
                <#--location.href = "${basePath}/customer/detail-" + $("#payOrderId").val() + ".html";-->
            <#--}-->
        <#--})-->
    <#--}-->
    function selfPick(orderCode){
        $.post("getDeliveryCode",{orderCode:orderCode},function (data){
            if(data.response == "success"){
                var zitima = dialog({
                    width: 260,
                    content:'<p style="padding: 1.4em;line-height: 1.8em"><span style="display:block;text-align: center;font-size: 1.4em;line-height: 1.4em;color: red;margin-bottom: .8em;">提货码：'+data.data.deliveryCode+'</span>请前往【'+data.data.pickInfo.enterpriseName+'：'+data.data.pickInfo.pickAddress+'】提货。</p>',
                    cancelValue: '知道了',
                    cancel:function(){
                        return true;
                    }
                })
                zitima.showModal();
            }
        })
    }
</script>
</body>

</html>