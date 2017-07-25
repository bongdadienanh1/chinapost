<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>订单-退款/退货</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
     <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/pageAction.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
    <#--<script src="${basePath}/static/js/wxforward.js"></script>-->
</head>
<body>
<input id="status" value="0" type="hidden">
<input type="hidden" id="type" value="${type!''}">
<input type="hidden" id="basePath" value="${basePath}">
<div class="order content-order-all" id="items">
 <#if (pb.list)?? &&  (pb.list?size!=0)>
        <#list pb.list as backorder>
		    <div class="order-item">
		        <div class="order-number">
		            <div class="list-item">
		                <h3 class="item-head"><label for="">退单号：</label><span>${(backorder.backOrderCode)!''}</span>
		                    <span class="curValue text-them">
		                      <#if backorder.backCheck??>
	                                <#if backorder.backCheck.name()=="RETURN_APPLIED">
	                                   	 退货审核
	                                <#elseif backorder.backCheck.name()=="RETURN_AGREED">
										同意退货
	                                <#elseif backorder.backCheck.name()=="RETURN_DENIED">
	                                  	  拒绝退货
	                                <#elseif backorder.backCheck.name()=="RETURN_DELIVERING">
	                                   	 待商家收货
	                                <#elseif backorder.backCheck.name()=="RETURN_DELIVER_FAILURE">
                                        商家拒绝收货
	                                <#elseif backorder.backCheck.name()=="RETURN_WAIT_REFUND">
                                        待退款
	                                <#elseif backorder.backCheck.name()=="RETURN_WAIT_DELIVER_INFO">
										待填写物流信息
	                                <#elseif backorder.backCheck.name()=="RETURN_FINISHED">
                                        退货成功
									<#elseif backorder.backCheck.name()=="REFUND_APPLIED">
                                        退款审核
									<#elseif backorder.backCheck.name()=="REFUND_DENIED">
                                        拒绝退款
								  	<#elseif backorder.backCheck.name()=="REFUND_AGREED">
                                        待退款
							  		<#elseif backorder.backCheck.name()=="REFUND_FINISHED">
                                 		 退款成功
									</#if>
	                            </#if>
		                    </span></h3>
		            </div>
		        </div>
		        <div class="order-info">
		            <#if backorder.products?? && backorder.products?size gt 2>
		                <div class="list-body-box">
			             <div class="list-item">
		                    <div class="box-body">
		                        <ul>
		             			  <#list backorder.products as backgoods>
		                            <li>
		                            <a  title="${backgoods.goodsInfoName!''}" href="javascript:void(0)">
                              	    <img width="78" height="78" title="${(backgoods.goodsInfoName)!''}" alt="${(backgoods.goodsInfoName)!''}" src="${(backgoods.goodsInfoImage)!''}" />
		                            </a>
		                            </li>
	                            </#list>
		                        </ul>
		                    </div>
		                   </div>
                        <#else>
		            	<div class="list-body-line">
                            <#list backorder.products as backgoods>
                                <a title="${backgoods.goodsInfoName!''}" href="javascript:void(0)">
					                <div class="list-item">
					                    <div class="pro-item" style="border-bottom: none;">
					                        <div class="propic">
	                              			  <img width="78" height="78" title="${(backgoods.goodsInfoName)!''}" alt="${(backgoods.goodsInfoName)!''}" src="${(backgoods.goodsInfoImage)!''}" />
					                        </div>
					                        <div class="prodesc">
					                            <h3 class="title">${(backgoods.goodsInfoName)!''}</h3>
					
					                            <p class="price" style="color: #333"><span class="num">
					                               <#if (backgoods.goodsInfoPrice)??>
                                                        ${backgoods.goodsInfoPrice!''}
                                                    </#if></span>邮豆</p>
					                            <span class="pro-num">×${(backgoods.goodsInfoNum)!'0'}</span>
					                        </div>
					                    </div>
					                </div>
				                </a>
                            </#list>
                        </#if>
		            </div>
		        </div>
		        <div class="order-bottom">
		            <div class="list-item">
		                <h3 class="item-head">
		                    <label for="">退款金额：</label><span class="pay-money" style="color: #333;">
		                    <#if backorder.backPrice??>
                                ${backorder.backPrice!''}
                            </#if>邮豆</span>
		                </h3>
		                 <div class="too-btn">
		                <#if (backorder.backCheck)??>
	                       <#if backorder.backCheck.name()=="RETURN_WAIT_DELIVER_INFO" && (backorder.order.selfPick?string('true','false')=='false')>
	                          <a class="btn fill-flow" href="javascript:void(0)" onclick="expressInfo('${(backorder.order.orderCode)!''}')" style="width:100px">填写物流信息</a>
		                  </#if>
		               </#if>
		                 <#if (backorder.isBack)??>
		                  <#if backorder.isBack=="2">
	                            <a class="btn" href="${basePath}/backorderpricedetail?backOrderCode=${backorder.backOrderCode}">退款详情</a><br/>
	                        <#else>
	                            <a class="btn" href="${basePath}/backorderpricedetail?backOrderCode=${backorder.backOrderCode}">退货详情</a><br/>
	                      </#if>
		                </#if>
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

                <a class="btn btn-full" href="${basePath}/main">去逛逛</a>
 </div>
    </div>
  </#if>       
</div>
	<div class="list-loading" id="showmore"  style="display:none" >
        <img alt="" src="${basePath}/static/img/loading.gif">
        <span>加载中……</span>
    </div>
</body>

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
                <input type="text" class="text" placeholder="请填写准确的物流单号"  name="wlno" id="wlno" onBlur="wuliudanhao()">
                 <label id="yanzhengno"></label>
            </div>
        </div>
    </div>
</form>
 <script src="${basePath}/static/js/backorder.js"></script>
 <script>
        $(function(){
            if($('.content-order-all .list-body-box ul li')[0]){
                var $li=$('.content-order-all .list-body-box ul li');
                $('.content-order-all .list-body-box ul').width($li[0].offsetWidth*$li.length+($li.length-1)*10);
            }
        });
	  $(window).scroll(function(){
		    if($(this).scrollTop() >= ($('body').height() - $(window).height())){
		     show();
		    }
	    });
	    
 </script>
</html>