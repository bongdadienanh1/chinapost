<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>会员中心 - 首页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=20160560100000"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css">
  <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
  <script src="${basePath}/static/js/idangerous.swiper.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
</head>
<body style="width: 100%;overflow: hidden">
<div class="content-home" style="padding-top:0px;padding-bottom: 0px;">
<#--<div class="member_brief member_box">-->
  <#--<div class="cover">-->
      <#--<a href="${basePath}/account">-->
      <#--<div class="avatar">-->
          <#--<img alt="" src="<#if cust.customerImg?? && cust.customerImg!=''>${cust.customerImg}<#else>${basePath}/static/img/avatar.png</#if>">-->
      <#--</div>-->
      <#--</a>-->
    <#--<h2>${cust.customerNickname!''}</h2>-->
    <#--<div class="message">-->
      <#--<a href="${basePath}/adminnotice">-->
        <#--<i class="iconfont icon-icxiaoxi"></i>-->
        <#--<#if xiaoxiCount==0>-->

        <#--<#else>-->
            <#--<span class="badge">${xiaoxiCount!''}</span>-->
        <#--</#if>-->
      <#--</a>-->
    <#--</div>-->
  <#--</div>-->
  <#--<div class="data row">-->
    <#--<div class="col-12">-->
      <#--<p>-->
        <#--<a href="${basePath}/customer/mycollections">-->
          <#--<span>${(mycollectcount)!'0'}</span>-->
          <#--我的收藏-->
        <#--</a>-->
      <#--</p>-->
    <#--</div>-->
    <#--<div class="col-12">-->
      <#--<p>-->
        <#--<a href="${basePath}/customer/mybrowerecord">-->
          <#--<span>${browereCount!'0'}</span>-->
          <#--浏览过的-->
        <#--</a>-->
      <#--</p>-->
    <#--</div>-->
  <#--</div>-->
<#--</div>-->
<div class="order_area member_box">
    <a href="${basePath}/account">
        <div class="common_line row avatarBox">
            <div class="avatar">
                <#if cust.imgUrl?? && cust.imgUrl!=''>
                    <#assign imgSrc = cust.imgUrl?substring(0,cust.imgUrl?last_index_of("."))/>
                    <img alt="" src="${imgSrc+"_160x160"+cust.imgUrl?substring(cust.imgUrl?last_index_of("."))}">
                <#else>
                    <img alt="" src="${basePath}/static/img/avatar.png">
                </#if>
            </div>
            <h4 class="avatarText">${cust.fullname!''}</h4>
            <i class="ion-chevron-right avatarIcon"></i>
        </div>
    </a>
</div>
<div class="order_area member_box">
    <a href="${basePath}/myUcoin">
        <div class="common_line row">
            <em class="point_icon"></em>
            <h4 style="width: 83%;">我的邮豆<span class="myUcoin" style="text-align: right;color: #c71818;display: inline-block;width: 60%;"></span></h4>
            <i class="ion-chevron-right"></i>
        </div>
    </a>
</div>
<div class="order_area member_box">
    <a href="${basePath}/myorder">
        <div class="common_line row orderBox">
            <em class="order_icon"></em>
            <h4>全部订单</h4>
            <i class="ion-chevron-right"></i>
        </div>
    </a>
  <div class="top row">
    <div class="col-25">
      <a href="${basePath}/myorder?type=1">
        <p class="order_state_0">
          <i class="iconfont icon-daifukuan"></i>
          待付款
        <#if noMoneyCount!=0> <span class="badge">${noMoneyCount!''}</span></#if>
        </p>
      </a>
    </div>
      <div class="col-25">
          <a href="${basePath}/myorder?type=2">
              <p class="order_state_1">
                  <i class="iconfont icon-tuikuanshouhou"></i>
                  待发货
              <#if unfilledOrders!=0> <span class="badge">${unfilledOrders!''}</span></#if>
              </p>
          </a>
      </div>
    <div class="col-25">
      <a href="${basePath}/myorder?type=3">
        <p class="order_state_2">
          <i class="iconfont icon-daishouhuo"></i>
          待收货
        <#if noFaHuoCount!=0> <span class="badge">${noFaHuoCount!''}</span></#if>
        </p>
      </a>
    </div>

    <div class="col-25">
      <a href="${basePath}/myorder?type=4">
        <p class="order_state_1">
          <i class="iconfont icon-klmpingjia"></i>
          待提货
        <#if unDeliveryCount!=0> <span class="badge">${unDeliveryCount!''}</span></#if>
        </p>
      </a>
    </div>
    <div class="col-25">
      <a href="${basePath}/myorder?type=6">
        <p class="order_state_4">
          <i class="iconfont icon-daituihuo"></i>
          退款/退货
        <#if cancelCount!=0> <span class="badge">${cancelCount!''}</span></#if>
        </p>
      </a>
    </div>
  </div>
</div>

<div class="member_box">
<#--
  <div class="common_line row">
    <a href="my_balance.html">
      <em class="balance_icon"></em>
      <h4>我的余额</h4>
      <i class="ion-chevron-right"></i>
    </a>
  </div>
 -->
<input type="hidden" id="threePartIsband" value="${threePartIsband!''}">
    <input type="hidden" id="customerId" value="${customerId!''}">
  <a href="${basePath}/distribution?flag=1">
      <div class="common_line row">
          <em class="coupon_icon"></em>
          <h4>收货地址</h4>
          <i class="ion-chevron-right"></i>
      </div>
  </a>
    <a href="${basePath}/accountSecurity">
    <div class="common_line row">
        <em class="ucoin_icon"></em>
        <h4>账号安全</h4>
        <i class="ion-chevron-right"></i>
    </div>
    </a>
</div>

    <div class="member_box">
        <div class="common_line row">
            <a href="tel:400-966-1900">
                <em class="tel_icon"></em>
                <h4>客服电话：<span style="text-align: right;display: inline-block;width: 60%;">400-966-1900</span></h4>
                <i class="ion-chevron-right"></i>
            </a>
        </div>
    </div>
    <#--<div class="member_box">-->
        <#--<div class="common_line row">-->
            <#--<a href="javascript:;">-->
                <#--<em class="like_icon"></em>-->
                <#--<h4>猜你喜欢</h4>-->
            <#--</a>-->
        <#--</div>-->
        <#--<div class="slide_goods">-->
            <#--<div class="swiper-container">-->
                <#--<div class="swiper-wrapper">-->
                    <#--<#if guessLikes??>-->
                        <#--<#list guessLikes as like>-->
                            <#--<div class="swiper-slide">-->
                                <#--<div class="like_good">-->
                                    <#--<a href="${basePath}/item/${like.goodsInfoId}.html">-->
                                        <#--<div class="img">-->
                                            <#--<img alt="" src="${like.goodsInfoImgId!''}" height="110px;">-->
                                        <#--</div>-->
                                        <#--<p class="name">-->
                                            <#--<#if (like.goodsInfoName)?length gt 15>-->
                                            <#--${(like.goodsInfoName)?substring(0,15)}...-->
                                            <#--<#else> ${like.goodsInfoName!''}-->
                                            <#--</#if>-->
                                        <#--</p>-->
                                        <#--<p class="price">-->
                                            <#--￥${like.goodsInfoPreferPrice!'0'}-->
                                        <#--</p>-->
                                    <#--</a>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</#list>-->
                    <#--<#else>-->

                    <#--</#if>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <div class="foot">
    <#--        <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>-->
    </div>


</div>
<#include "common/smart_menu.ftl"/>
<#--
<div class="bar-bottom">
  <a class="bar-item" href="${basePath}/main.html"><i class="bar-bottom-i home"></i>首页</a>
  <a class="bar-item" href="${basePath}/cates.html"><i class="bar-bottom-i class"></i>分类</a>
  <a class="bar-item" href="${basePath}/myshoppingmcart.html"><i class="bar-bottom-i cart"></i>购物车</a>
  <a class="bar-item selected" href="${basePath}/customercenter.html"><i class="bar-bottom-i mine"></i>我的</a>
</div>
-->
<script>
  var mySwiper = new Swiper('.swiper-container',{
    slidesPerView: 'auto'
  });
  $(function(){
      $.post("${basePath}/myUcoinBalance",{},function(data){
          if(data.response =="success"){
              var fx = Math.round(data.data*100)/100;
              var sx = fx.toString();
              var pos_decimal = sx.indexOf(".");
              if(pos_decimal < 0){
                  pos_decimal = sx.length;
                  sx += '.';
              }
              while (sx.length <= pos_decimal + 2)
              {
                  sx += '0';
              }
              $(".myUcoin").html(sx);
          };
      })
  })

  $(".bar-bottom a:eq(4)").addClass("selected");
</script>
</body>
</html>