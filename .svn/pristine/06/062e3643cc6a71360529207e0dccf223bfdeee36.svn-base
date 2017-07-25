

<div class="bar-bottom">
    <a class="bar-item" href="${basePath}/main"><i class="bar-bottom-i home"></i>首页</a>
    <#--<a class="bar-item" href="${basePath}/cates.html"><i class="bar-bottom-i class"></i>分类</a>-->
    <#--<a class="bar-item" href="${basePath}/customer/addCoupon.htm"><i class="bar-bottom-coupon"></i></a>-->
    <a class="bar-item" href="${basePath}/myshoppingmcart"><i class="bar-bottom-i cart"></i>购物车</a>
    <a class="bar-item" href="${basePath}/customercenter"><i class="bar-bottom-i mine"></i>我的</a>
</div>

<script>
    $(function(){
        if(window.location.href.indexOf("myshoppingmcart")>=0){
            var item = $(".bar-bottom").find(".bar-item").eq(1);
            item.addClass("selected");
        }else if(window.location.href.indexOf("customercenter")>=0){
            var item = $(".bar-bottom").find(".bar-item").eq(2);
            item.addClass("selected");
        }
    })
</script>