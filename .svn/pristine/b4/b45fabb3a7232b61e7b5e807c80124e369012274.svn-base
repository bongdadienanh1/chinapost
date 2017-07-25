function seckilling(){
    var now = new Date().getTime();
    var sysSecond = (endTime - now)/1000;
    var timer = $('.colockbox');
    var day = setDig(Math.floor((sysSecond / 3600) / 24),2);
    var hour = setDig(Math.floor((sysSecond / 3600) % 24),2);
    var minute = setDig(Math.floor((sysSecond / 60) % 60),2);
    var second = setDig(Math.floor(sysSecond % 60),2);
    function setDig(num,n){
        var str = ""+num;
        while(str.length<n){
            str="0"+str
        }
        return str;
    }
    // 活动结束
    if(now>endTime){
        $(".goodsproductPrice").hide();
        $(".goodsPromotionPrice") .each(function() {
            var _this = $(this);
            _this.html(_this.attr("goodsproductPrice"));
        });

        $("#countdown").hide();
        clearTimeout(setTimeout(seckilling,1000));
    }
    // 活动开始
    if(now>=startTime && now<= endTime){
        $(".goodsproductPrice").show();
        $("#countdown .hour").html(hour);
        $("#countdown .min").html(minute);
        $("#countdown .second").html(second);
        $("#countdown .text").html("距结束还剩");
        setTimeout(seckilling,1000);
    }
    // 活动未开始
    if(now<startTime&&now<=endTime){
        $(".goodsproductPrice").hide();
            $(".goodsPromotionPrice") .each(function() {
            var _this = $(this);
            _this.html(_this.attr("goodsproductPrice"));
        });
        var differTime = (startTime-now)/1000;
        var day = setDig(Math.floor((differTime / 3600) / 24),2);
        var hour = setDig(Math.floor((differTime / 3600) % 24),2);
        var minute = setDig(Math.floor((differTime / 60) % 60),2);
        var second = setDig(Math.floor(differTime % 60),2);

        $("#countdown .hour").html(hour);
        $("#countdown .min").html(minute);
        $("#countdown .second").html(second);
        $("#countdown .text").html("距开始还剩");
        setTimeout(seckilling,1000);
    }
}


