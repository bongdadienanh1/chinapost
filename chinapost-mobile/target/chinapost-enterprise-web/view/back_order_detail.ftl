<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单-退款/退货详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/pageAction.js"></script>
</head>
<body>
<div class="order content-order-return">
    <div class="order-number">
        <div class="list-item">
            <h3 class="item-head text-them">
                <#if bOrder.backCheck??>
                    <#if bOrder.backCheck.name()=="RETURN_APPLIED">
                        退货审核
                    <#elseif bOrder.backCheck.name()=="RETURN_AGREED">
                        同意退货
                    <#elseif bOrder.backCheck.name()=="RETURN_DENIED">
                        拒绝退货
                    <#elseif bOrder.backCheck.name()=="RETURN_DELIVERING">
                        待商家收货
                    <#elseif bOrder.backCheck.name()=="RETURN_DELIVER_FAILURE">
                        商家拒绝收货
                    <#elseif bOrder.backCheck.name()=="RETURN_WAIT_REFUND">
                        待退款
                    <#elseif bOrder.backCheck.name()=="RETURN_WAIT_DELIVER_INFO">
                        待填写物流信息
                    <#elseif bOrder.backCheck.name()=="RETURN_FINISHED">
                        退货成功
                    <#elseif bOrder.backCheck.name()=="REFUND_APPLIED">
                        退款审核
                    <#elseif bOrder.backCheck.name()=="REFUND_DENIED">
                        拒绝退款
                    <#elseif bOrder.backCheck.name()=="REFUND_AGREED">
                        待退款
                    <#elseif bOrder.backCheck.name()=="REFUND_FINISHED">
                        退款成功
                    </#if>
                </#if>
            </h3>

            <h3 class="item-head"><label for="">订单号：</label><span>${(bOrder.order.orderCode)!''}</span></h3>

            <h3 class="item-head"><label for="">退单号：</label><span>${(bOrder.backOrderCode)!''}</span></h3>
        </div>
    </div>
    <div class="mt10">
        <div class="order-info">
            <div class="list-body-line">
                <#if bOrder.backOrderGoods?? && bOrder.backOrderGoods?size gt 0>
                    <#list bOrder.backOrderGoods as list>
	                    <#if list_index lt 2>
	                        <a title="${list.goodsInfoName!''}" href="${basePath}/productdetail?productId=${list.goodsInfoId}">
	                        <div class="list-item">
			                    <div class="pro-item">
			                        <div class="propic">
			                            <img alt="" src="${list.goodsInfoImage}" style="width:78px;height:78px;"/>
			                        </div>
			                        <div class="prodesc">
			                            <h3 class="title">${(list.goodsInfoName)!''}</h3>
			
			                            <p class="price"><span class="num">${list.goodsPrice!''}</span>邮豆</p>
			                            <span class="pro-num">×${list.goodsNum}</span>
			                        </div>
			                    </div>
	                	  </div>
	                	  </a>
	                	  <#else>
	                        <a title="${list.goodsInfoName!''}" href="${basePath}/productdetail?productId=${list.goodsInfoId}">
	                        <div class="list-item" style="display:none">
			                    <div class="pro-item">
			                        <div class="propic">
			                            <img alt="" src="${list.goodsInfoImage}" style="width:78px;height:78px;"/>
			                        </div>
			                        <div class="prodesc">
			                            <h3 class="title">${(list.goodsInfoName)!''}</h3>
			
			                            <p class="price"><span class="num">${list.goodsPrice!''}</span>邮豆</p>
			                            <span class="pro-num">×${list.goodsNum}</span>
			                        </div>
			                    </div>
	                	  </div>
	                	  </a>
	                	 </#if>
                    </#list>
                </#if>
            </div>
            <#if (bOrder.backOrderGoods)?size gt 2>
	         <div class="list-item see-all">
	            — — — — 显示其他${bOrder.backOrderGoods?size-2}件商品 — — — —
	        </div>
     	 </#if>
        </div>
    </div>
    <div class="mt10">
        <div class="list-item">
            <ul class="tep-strip">
               <#if bOrder.logs??>
	             <#list bOrder.logs as  backOrderLog>
                    <li>
                        <div class="body">
                             <span class="vertical-line"></span>
                             <span class="point"><b><i></i></b></span>
                            <h4>${backOrderLog.logStr}</h4>
                            <p>${backOrderLog.createTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                        </div>
                   </li>
	            </#list>
	           </#if>
            </ul>
        </div>
    </div>
    <div class="all-info mt10">
        <div class="list-item">
            <ul class="price-total">
                <li><label for="">退款金额</label><span class="value text-them">${bOrder.backPrice!''}邮豆</span></li>
                <li><label for=""><#if bOrder.isBack=='1'>退货原因<#else>退款原因</#if></label>
                	<span class="value">
                         ${bOrder.backReason!''}
                	</span>
                </li>
                <li><label for="">问题说明</label><span class="value">${(bOrder.backRemark)!''}</span></li>
                <#if bOrder.isBack=='1'>
                <li><label for="">返回方式</label><span class="value"><#if bOrder.order.selfPick?string('true','false')=='false'>快递<#else>退回自提点</#if></span></li>
                <li><label for="">申请凭据</label>
	                <span class="value">
	                 <#if bOrder.applyCredentials??>
                        <#if bOrder.applyCredentials=='QUALITY_REPORT'>
                           	 有质检报告
                        <#elseif bOrder.applyCredentials=='NO_CREDENTIAL'>
                            没有任何凭证
                        <#elseif bOrder.applyCredentials=='INVOICE'>
                            有发票
                        <#else>
                            	有发票、有质检报告
                        </#if>
                    </#if>
	                </span>
                </li>
             </#if>
            </ul>
        </div>
    </div>
    <#if bOrder.logs??>
	    <div class="mt10">
	        <div class="list-item">
	            <h3 class="item-head">卖家留言</h3>
	            <ul class="message">
	                <#assign notice = 0>
		             <#list bOrder.logs as  backOrderLog>
		                <#if (backOrderLog.backRemark)?? && backOrderLog.backRemark !=''>
	                <#assign notice = 1>
		                <li>
		                    <p class="time">${backOrderLog.createTime?string("yyyy-MM-dd HH:mm:ss")}</p>
		                     ${(backOrderLog.backRemark)!''}
		                </li>
		                </#if>
	                </#list>
	                <#if notice ==0>
	                   <li> 暂无留言<li>
	               </#if>
	            </ul>
	        </div>
	    </div>
    </#if>
    <#assign showflag =0 />
<#if bOrder.imageList?? && bOrder.imageList?size gt 0>
    <#list bOrder.imageList as imgs>
        <#if imgs?? && imgs !=''>
            <#assign showflag =1 />
        </#if>
    </#list>
</#if>
<#if showflag==1>
<div class="mt10">
    <div class="list-item">
        <div class="recommend-list">
              <ul>
                <#list bOrder.imageList as imgs>
                    <#if imgs?? && imgs !=''>
                    <li><img src="${imgs}" alt="" style="width:70px;height:70px;"/></li>
                    </#if>
                </#list>
                </ul>
        </div>
    </div>
</div>
</#if>
    <!-- <div class="cancel">
         <div class="list-item">
             <a class="btn btn-full" href="javascript:;"><i></i>取消申请</a>
         </div>
     </div>-->
</div>

</body>
<script>
    $(function(){
        $('.recommend-list ul').width($('.recommend-list ul li').width()*$('.recommend-list ul li').length+($('.recommend-list ul li').length-1)*10);
      /* 显示隐藏的商品 */
            $('.see-all').click(function(){
                $(this).prev('.list-body-line').find('.list-item').show();
                $(this).remove();
            });
    })
</script>
</html>