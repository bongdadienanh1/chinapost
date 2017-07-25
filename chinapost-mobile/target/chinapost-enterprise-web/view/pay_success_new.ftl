<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>支付成功</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628">
  <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <style>
        .code_box{overflow: hidden;width: 100%;border-bottom: 1px solid #ececec;}
        .code_box .back_img{width: 46%;display: inline-block;vertical-align: middle;}
        .code_box .success_text{width: 51%;display: inline-block;margin-top: -1.0em;vertical-align: middle;}
        .code_box .success_text .text{text-align: center;}
        .code_box img{display:block; width:75%; margin:0 auto;}
        .code_box span{ text-align:center; width:100%; display:inline-block; font-size:1.2em;}
        .btnBox{margin: 0 auto;padding-top: 1.0em;overflow: hidden;}
        .deliveryCode{width: 60%;background-color: #E63D59;border-radius: 6px;border:1px dashed #fff;color: #fff!important;margin-left: 20%;margin-top: 1.0em;padding: 4px;text-align: center;}
        /*.success_imgBox{margin-top:6px;width:100%;}*/
    </style>
</head>
<body>

<div class="container">

  <div class="success_info" style="padding: 20px 20px 16px;">
      <#if openid??>
          <div class="success_icon"></div>
       <#else>
           <div class="code_box">
               <div class="back_img">
                   <img src="${basePath}/static/img/pic_banner_payment_success.png" width="100%;">
               </div>
               <div class="success_text">
                <#if deliveryCode?? && deliveryCode !="undefined">
                   <p class="text"><span>支付成功</span>赶快到提货网点将宝贝带回家吧~~</p>
                    <#else >
                    <p class="text"><span>支付成功</span>您的包裹正马不停蹄的赶来~~</p>
                </#if>
               </div>
           </div>
      </#if>
      <#if deliveryCode?? && deliveryCode !="undefined">
          <p class="deliveryCode">提货码：<span style="color: #fff;">${deliveryCode!''}</span></p>
      </#if>
          <div class="btnBox">
              <a href="${basePath}/myorder" class="btn success_btn" style="color: #333333;border: 1px solid #333333;float: left;margin-left:3.0em;">查看订单</a>
              <a href="${basePath}/main" class="btn success_btn" style="float: right;margin-right:3.0em;">继续购物</a>
          </div>
  </div>
    <#--<div class="success_imgBox">
        <a href="http://www.ylife.cn/mobile/c/2004"><img src="http://img01.ylife.cn/1457060937555.jpg" width="100%"/></a>
    </div>-->

  <#--<div class="relate_orders">-->
      <#--<#list orderList as order>-->
          <#--<div class="relate_order">-->
              <#--<h4>订单号：${order.orderCode!''}</h4>-->
              <#--<#list order.orderGoodsList as goods>-->
                  <#--<div class="good">-->
                      <#--<a href="javascript:void(0)">-->
                          <#--<img class="img" alt="" src="${goods.goodsImg}">-->
                          <#--<p class="name">${goods.goodsInfoName}</p>-->
                      <#--</a>-->
                  <#--</div>-->
              <#--</#list>-->
          <#--</div>-->
      <#--</#list>-->

    <#--&lt;#&ndash;<div class="relate_order">&ndash;&gt;-->
      <#--&lt;#&ndash;<h4>订单号：T2015090812349078</h4>&ndash;&gt;-->
      <#--&lt;#&ndash;<div class="good">&ndash;&gt;-->
        <#--&lt;#&ndash;<a href="javascript:void(0)">&ndash;&gt;-->
          <#--&lt;#&ndash;<img class="img" alt="" src="images/good_img.jpg">&ndash;&gt;-->
          <#--&lt;#&ndash;<p class="name">米莱珠宝0.7克拉红宝石戒指18K金镶嵌10分……</p>&ndash;&gt;-->
        <#--&lt;#&ndash;</a>&ndash;&gt;-->
      <#--&lt;#&ndash;</div>&ndash;&gt;-->
    <#--&lt;#&ndash;</div>&ndash;&gt;-->
    <#--<div class="pay_total">-->
      <#--<p>实付款：￥120.00</p>-->
    <#--</div>-->
  <#--</div>-->

</div>


</body>
</html>