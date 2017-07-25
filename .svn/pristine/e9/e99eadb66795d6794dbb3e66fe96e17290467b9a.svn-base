<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<#assign basePath=request.contextPath>
    <title>购物车</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
<#if (sys.bsetName)??>
    <title>购物车-${(sys.bsetName)!''}</title>
    <input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
<#else>
    <title>购物车-${(seo.mete)!''}</title>
    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
</#if>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css">
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
    <style>
        .logoImg{display: inline-block!important;width: 17px!important;height: 17px!important;}
    </style>
</head>
<body>
<div class="content-cart">
    <div class="cart-head">
        <h1>我的购物车</h1>
        <#if customerId??>
            <#if shopMap.shoplist??&&shopMap.shoplist?size!=0>
                <a href="javascript:;" class="cart-edit">编辑</a>
            </#if>
        </#if>
    </div>
    <input type="hidden" value="${customerId!''}" id="customerId">
    <input type="hidden" value="${basePath!''}" id="basePath">
<#if customerId??>
<#else>
    <div class="list-item notlogin">
        <h3><a class="btn" href="${basePath}/login?url=myshoppingmcart">登&nbsp;录</a>登录后可同步购物车里的商品哦~</h3>
    </div>
</#if>
    <#assign goodsNum=0/>
    <#assign sumPrice=0/>
    <#assign youhui=0/>
<#if shopMap.shoplist??&&shopMap.shoplist?size!=0>
    <ul class="cart-list">
        <#list shopMap.stores as stores>
                <li id="${stores.thirdId}">
                    <div class="profrom-way logoBox">
                        <i class="select-box selected positionTop" onclick="checkArea(this)"></i>
                        <img class="logoImg" src="${basePath}/static/img/icon_order_details.png"/>
                        <span class="logoName">${stores.thirdName}</span>
                    </div>
                </li>
            <#list shopMap.shoplist as shop>
                <#if shop.goodsDetailBean??>
                    <#if stores.thirdId== shop.goodsDetailBean.productVo.thirdId>
                        <li class="subjectGoods ${stores.thirdId}">
                            <div class="list-item cart-pro-item">
                            <input type="hidden" class="goodInfoId" value="${shop.goodsDetailBean.productVo.goodsInfoId}"/>
                            <input type="hidden" class="shoppingCartId" value="${shop.shoppingCartId}"/>
                            <input type="hidden" class="distinctId" value="${shop.distinctId!''}"/>
                        <#--<#if (shop.goodsDetailBean.productVo.goodsInfoStock?number-shop.goodsNum?number<0)||shop.goodsDetailBean.productVo.goodsInfoAdded=='0'>-->
                            <#--<input type="hidden" value="1" class="noexit"/>-->
                        <#--</#if>-->
                            <i class="select-box selected" onclick="checkone(this)"></i>

                            <div class="propic">
                                <a href="${basePath}/productdetail?productId=${shop.goodsDetailBean.productVo.goodsInfoId}"  title="${shop.goodsDetailBean.productVo.productName}">
                                     <img src="${shop.goodsDetailBean.productVo.goodsInfoImgId}" alt="产品图">
                                    <#--<#if (shop.goodsDetailBean.productVo.goodsInfoStock?number-shop.goodsNum?number<0)>-->
                                        <#--<span class="sell-out">无货</span>-->
                                    <#--<#elseif shop.goodsDetailBean.productVo.goodsInfoAdded=='0'>-->
                                        <#--<span class="pull-down">下架</span>-->
                                    <#--</#if>-->
                                </a>
                            </div>
                            <div class="prodesc">
                                <h3 class="title" style="font-size: 1.1em;">
                                    <a href="${basePath}/productdetail?productId=${shop.goodsDetailBean.productVo.goodsInfoId}"  title="${shop.goodsDetailBean.productVo.productName}">
                                        ${shop.goodsDetailBean.productVo.productName!''}
                                    </a>
                                </h3>
                                <p class="price" style="font-size: 1.1em;vertical-align: middle"><span class="num">${shop.goodsDetailBean.productVo.goodsInfoPreferPrice!''}</span>邮豆</p>
                                <input type="hidden" class="goodsPrice" value="${shop.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                <div class="buy-num">
                                    <span class="reduce" onclick="mit(this,${shop.shoppingCartId});">&#8722</span>
                                    <input type="text" class="goodsNum"  attr-maxnum="${shop.goodsDetailBean.productVo.goodsInfoStock}" onblur="opblur(this,${shop.shoppingCartId});" value="${shop.goodsNum}"/>
                                    <span class="add" onclick="add(this,${shop.shoppingCartId});">&#43</span>
                                </div>
                                <#assign goodsNum=goodsNum+shop.goodsNum?number/>
                                <#assign oneprice=shop.goodsNum?number*shop.goodsDetailBean.productVo.goodsInfoPreferPrice?number/>
                                <#assign sumPrice=sumPrice+oneprice/>
                                <input  class="oneprice" type="hidden" value="${oneprice}">
                            </div>
                            <#--<div class="tool-btn">-->
                                <#--<a class="in-collection collection" href="javascript:;" >移入<br>收藏</a>-->
                                <#--<a class="delete" href="javascript:;"> 删除</a>-->
                            <#--</div>-->
                        </div>
                        </li>
                    </#if>
                </#if>
            </#list>
        </#list>
    </ul>

    <div  class="pay-box">
        <div class="select-all">
            <i class="select-box selected" onclick="checkAll()"></i><label>全选</label>
        </div>
        <div class="cart-money">
            <h3>合计：<span class="money-all"><span>${sumPrice!''}</span>邮豆</h3>
            <#--<p>优惠：￥<span class="fanxian">${youhui}</span></p>-->
        </div>
        <a class="btn cart-pay" onclick="onpay()">结算(<span id="goodsNum">${goodsNum}</span>)</a>
        <div class="goods-ctrl">
            <#--<a href="javascript:;" class="btn btn-grey">移入收藏</a>-->
            <a href="javascript:;" class="btn btn-them">删除</a>
        </div>
    </div>
</#if>
    <#if goodsNum==0>
        <div class="cart-empty">
            <i></i>
            <h3>您的购物车空空如也，快去装满它！</h3>

                <a class="btn btn-full" href="${basePath}/main.html">去逛逛</a>
        </div>

        <#--<div class="cart-recommend">-->
            <#--<h3 class="recommend-top"><span class="splitline">— — — — </span>猜你喜欢<span class="splitline">— — — — </span></h3>-->
            <#--<div class="pro-recommend">-->
                <#--<div class="pro-recommend-body">-->
                    <#--<#if goods??&&goods?size!=0>-->
                    <#--<#list goods as good>-->
                        <#--<a href="${basePath}/item/${good.goodsInfoId}.html">-->
                            <#--<div class="list-item">-->
                                <#--<div class="propic">-->
                                    <#--<img src="${good.goodsInfoImgId!''}" alt="产品图">-->
                                <#--</div>-->
                                <#--<div class="prodesc">-->
                                    <#--<h5 class="title">${good.goodsInfoName!''}</h5>-->
                                    <#--<p class="price">¥&nbsp;<span class="num">${good.goodsInfoPreferPrice!'0.00'}</span>-->
                                    <#--</p>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</a>-->
                    <#--</#list>-->
                    <#--</#if>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
        </#if>
</div>
<#include "common/smart_menu.ftl"/>
<form method="post" action="${basePath}/suborder" class="subForm">

</form>
<script src="${basePath}/static/js/shoppingCar/jsOperation.js"></script>
<script src="${basePath}/static/js/shoppingCar/shoppingcart.js"></script>
<script>

    $(function() {
        $(".bar-bottom a:eq(3)").addClass("selected");
        /* 显示/隐藏优惠 */
        $('.cart-promotions').click(function () {
            $(this).toggleClass('open');
            $(this).parent().next().find('.c-discount').slideToggle('fast');
        });


        /* 选择赠品 */
        $('.get-gift-btn').click(function () {
            $('body').append('<div class="opacity-bg-4"></div>');
            $('.getgift').slideDown('fast');
        });
        $('.getgift .close').click(function () {
            $('.opacity-bg-4').remove();
            $('.getgift').slideUp('fast');
        });
        /* 删除商品 */
        $('.btn-them').click(function () {
            if( $(".list-item .selected").length!=0){
                var discountBox = dialog({
                    width: 260,
                    title: '删除商品',
                    content: "确定从购物车中删除选择的商品？",
                    okValue: '确定',
                    cancelValue: '取消',
                    ok: function () {
                        $(".list-item .selected").each(function(){
                            var obj = $(this).parents(".list-item");
                            var goodsInfoId = $(obj).find(".goodInfoId").val();
                            var shoppingCartId = $(obj).find(".shoppingCartId").val();
                            $.post("${basePath}/delshoppingcartbyid?shoppingCartId=" + shoppingCartId + "&goodsInfoId=" + goodsInfoId, function (data) {
                            });
                        })
                        setTimeout(function () {
                            $('.tip-box').remove();
                            $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="success"></i><h3>删除商品成功！</h3></div></div>');
                        }, 10);
                        setTimeout(function () {
                            $(".tip-box").hide();
                            location.reload();
                        }, 1000)
                        return true;
                    },
                    cancel: function () {

                        return true;
                    }
                });
                discountBox.showModal();
            }else{
                setTimeout(function () {
                    $('.tip-box').remove();
                    $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>请选择商品！</h3></div></div>');
                }, 10);
                setTimeout(function () {
                    $(".tip-box").hide();
                }, 1000)
            }

        });

        /* 选择优惠 */
//        $('.change_discount').click(function () {
//            var sid = $(this).parent().parents("li").find(".shoppingCartId").val();
//            var discountBox = dialog({
//                width: 260,
//                title: '选择一个优惠活动',
//                content: $('.promotions-box' + sid),
//                okValue: '确定',
//                cancelValue: '取消',
//                ok: function () {
//                    var newMarId = $('.promotions-box' + sid).find(".selected").find(".marketingAId").val();
//                    if (newMarId != '') {
//                        $(".marketing_activity_id").val(newMarId)
//                        $(".shopping_cart_id").val(sid);
//                        $(".change_shopping").submit();
//                    }
//                    return true;
//                },
//                cancel: function () {
//
//                    return true;
//                }
//            });
//            discountBox.showModal();
//        });
        /* 点击编辑显示编辑按钮 */
        var cartEditable = false;
        $('.cart-edit').click(function () {
            if (cartEditable) {
                $('.pay-box').removeClass('editable');
                $(this).text('编辑');
                $(".select-all .select-box").removeClass("selected");
                cartEditable = !cartEditable;
            }
            else {
                $('.pay-box').addClass('editable');
                $(this).text('确定');
//                $(".select-all").addClass("selected");
                $(".select-all .select-box").addClass("selected");
                cartEditable = !cartEditable;
            }
            checkAll();
        });


        /**
         * 收藏商品
         * */
        <#--$(".btn-grey").click(function () {-->
            <#--if($(".list-item .selected").length!=0){-->
                <#--$(".list-item .selected").each(function(){-->
                    <#--var districtId = $(this).parents(".list-item").find(".distinctId").val();-->
                    <#--var goodsprice = $(this).parents(".list-item").find(".goodsPrice").val();-->
                    <#--var productId = $(this).parents(".list-item").find(".goodInfoId").val();-->
                    <#--var _this = $(this);-->
                    <#--$.post("${basePath}/saveAtte.htm", {-->
                        <#--productId: productId,-->
                        <#--districtId: districtId,-->
                        <#--goodsprice: goodsprice-->
                    <#--}, function (data) {-->
                        <#--if (data == "-2") {-->
                            <#--//返回结果为2代表用户未登录，跳转到登录页面4444-->
                            <#--location.href = "../register.html?url=/customercenter.html";-->
                        <#--}else if(data == "-1"){-->
                            <#--setTimeout(function () {-->
                                <#--$('.tip-box').remove();-->
                                <#--$('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="success"></i><h3>商品已经收藏！</h3></div></div>');-->
                            <#--}, 10);-->
                            <#--setTimeout(function () {-->
                                <#--$(".tip-box").hide();-->
                            <#--}, 1000)-->
                        <#--} else {-->
                            <#--setTimeout(function () {-->
                                <#--$('.tip-box').remove();-->
                                <#--$('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="success"></i><h3>加入收藏成功！</h3></div></div>');-->
                            <#--}, 10);-->
                            <#--setTimeout(function () {-->
                                <#--$(".tip-box").hide();-->
                            <#--}, 1000)-->

                        <#--}-->
                    <#--});-->
                <#--})-->
            <#--}else{-->
                <#--setTimeout(function () {-->
                    <#--$('.tip-box').remove();-->
                    <#--$('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>请选择商品！</h3></div></div>');-->
                <#--}, 10);-->
                <#--setTimeout(function () {-->
                    <#--$(".tip-box").hide();-->
                <#--}, 1000)-->
            <#--}-->

        <#--});-->
    });

</script>
</body>
</html>