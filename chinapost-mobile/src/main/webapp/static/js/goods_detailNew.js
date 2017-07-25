
/*
 *商品详情页通用JS
 * author YuanKangKang
 */
/*商品详情页点击规格值*/
var allProductList = new Array();
var tempList = new Array();
/**
 * 加载商品的促销信息
 */
//function loadGoodsMark(){
//    $.get("../gmarket/"+$("#productId").val()+"-"+$("#brandId").val()+"-"+$("#catId").val()+".html",function(data){
//        if(null==data || data.length<=0){
//            $(".promotion-value").append("<span class=\"fav gift\">无</span>此商品暂无促销信息");
//            return;
//        }else{
//            if(data.length > 1){
//                /* 促销选择 */
//                $('.promotion-choose .list-value').click(function(){
//                    $('.promotions-list').slideToggle('fast');
//                    $(this).find('i.arrow-right').toggleClass('arrow-down');
//                });
//            }
//
//            var marketingOne = data[0];
//            //直降促销
//            if(marketingOne.codexType==1){
//                $(".promotion-value").append("<span class=\"fav gift\">直降</span>"+marketingOne.marketingName);
//                $("#marketingId").val(marketingOne.marketingId);
//            }else if(marketingOne.codexType==15){
//                $(".promotion-value").append("<span class=\"fav gift\">折扣</span>"+marketingOne.marketingName);
//                $("#marketingId").val(marketingOne.marketingId);
//
//                //满减促销
//            }else if(marketingOne.codexType==5){
//                $(".promotion-value").append("<span class=\"fav gift\">满减</span>"+marketingOne.marketingName);
//                $("#marketingId").val(marketingOne.marketingId);
//                //满折促销
//            }else if(marketingOne.codexType==8) {
//                $(".promotion-value").append("<span class=\"fav gift\">满折</span>" + marketingOne.marketingName);
//                $("#marketingId").val(marketingOne.marketingId);
//            }else if(marketingOne.codexType==12){
//                $(".promotion-value").append("请选择促销");
//                if(data.length > 1){
//                    $("#marketingId").val(data[1].marketingId);
//                }
//            }
//
//
//            var count=0;
//            for(var i=0;i<data.length;i++){
//                var marketing=data[i];
//                //直降促销
//                if(marketing.codexType==1){
//
//                    $(".promotions").append("<li><a href=\"javascript:void(0);\" onclick='showMarket(this,1,"+marketing.marketingId+")'><span class=\"fav gift\">直降</span>"+marketing.marketingName+" <i class=\"arrow-right\"></i></a></li>");
//                }else if(marketing.codexType==15){
//                    //如果是折扣促销
//                    if(marketing.preDiscountMarketings!=null&&marketing.preDiscountMarketings.length!=0){
//                        for(var j=0;j<marketing.preDiscountMarketings.length;j++){
//                            if($("#productId").val()==marketing.preDiscountMarketings[j].goodsId){
//                                $(".price").attr("style","display:none");
//                                $(".oldprice").text($("#oldPrice").val()).parent().show();
//                                $(".mark_price").text(marketing.preDiscountMarketings[j].discountPrice.toFixed(2)).parent().show();
//                            }
//                        }
//
//                    }
//                    //满减促销
//                }else if(marketing.codexType==5){
//
//                    if(marketing.fullbuyReduceMarketings!=null&&marketing.fullbuyReduceMarketings.length!=0){
//                        for(var j=0;j<marketing.fullbuyReduceMarketings.length;j++){
//                            $(".promotions").append("<li><a href=\"javascript:void(0);\" onclick='showMarket(this,2,"+marketing.marketingId+")'><span class=\"fav gift\">满减</span>满"+marketing.fullbuyReduceMarketings[j].fullPrice+"减"+marketing.fullbuyReduceMarketings[j].reducePrice+" <i class=\"arrow-right\"></i></a></li>");
//                        }
//
//                    }
//                    //满折促销
//                }else if(marketing.codexType==8){
//
//                    if(marketing.fullbuyDiscountMarketings!=null&&marketing.fullbuyDiscountMarketings.length!=0){
//                        for(var j=0;j<marketing.fullbuyDiscountMarketings.length;j++){
//                            $(".promotions").append("<li><a href=\"javascript:void(0);\" onclick='showMarket(this,3,"+marketing.marketingId+")'><span class=\"fav gift\">满折</span>满"+marketing.fullbuyDiscountMarketings[j].fullPrice+"打"+(marketing.fullbuyDiscountMarketings[j].fullbuyDiscount*10)+"折 <i class=\"arrow-right\"></i></a></li>");
//                        }
//
//                    }
//                }else if(marketing.codexType==12){
//                    $("#yunfei").append("<span class=\"fav gift\">包邮</span>支持"+marketing.shippingMoney+"免运费");
//                }
//
//            }
//        }
//    });
//}

/**
 * 显示促销
 * */
//function showMarket(obj,num,marketingId){
//    var market = $(obj).text().length;
//    var market1 = $(obj).text().substring(2,market);
//    $("#marketingId").val(marketingId);
//    if(num == 1){
//        $(".promotion-value").html("");
//        $(".promotion-value").append("<span class=\"fav gift\">直降</span>"+market1);
//    }else if(num == 2){
//        $(".promotion-value").html("");
//        $(".promotion-value").append("<span class=\"fav gift\">满减</span>"+market1);
//    }else if(num == 8){
//        $(".promotion-value").html("");
//        $(".promotion-value").append("<span class=\"fav gift\">满折</span>"+market1);
//    }else if(num == 12){
//        $(".promotion-value").html("");
//        $(".promotion-value").append("<span class=\"fav gift\">包邮</span>"+market1);
//    }
//
//}

/**
 * 加载优惠券列表
 * */
//function loadCoupon(){
//    $.ajax({
//        url:"../queryCouponList.htm?productId="+$("#productId").val()+"&cateId="+$("#catId").val()+"&brandId="+$("#brandId").val()+"&thirdId="+$("#thirdId").val(),
//        type:"post",
//        success:function(data){
//            if(null == data || data.length <= 0){
//                $("#coupon").append("<span class=\"manjian\">无</span>");
//            }else{
//                for(var i = 0;i<data.length;i++){
//                    $("#coupon").append("<span class=\"manjian\" onClick=\"goCoupon();\">"+data[i].couponName+"</span>");
//                }
//            }
//        }
//    });
//}

/**
 * 跳转至优惠券领取页面
 * */
//function goCoupon(){
//    location.href="../goCoupon-"+$("#productId").val()+"-"+$("#catId").val()+"-"+$("#brandId").val()+"-"+$("#thirdId").val()+".html";
//}

/*加载商品评论*/
function loadComment(pageNo,type){
    /*清空相关的div*/
    $("#commentBody").html("");
    var productId=$("#productId").val();
    var params="&productId="+productId;
    params+="&pageNo="+pageNo+"&pageSize=3";
    /*var haoCount=0;
    var zhongCount=0;
    var chaCount=0;*/
    //获取所有评论总数
    //var count=0;
    /*AJAX查询商品好评*/

    $.ajax({
        url:"../queryProducCommentForDetailHyc.htm?type=0"+params,
        type:"post",
        async:false,
        success:function(data){
            /*设置所有的行数*/
            //count=data.rows;
            if(type==0){
                putPageComment(type,data);
            }
            //$("#haoping").text("好评（"+haoCount+"）");
        }
    });

    /*AJAX查询商品中评*/
    /*$.ajax({
        url:"../queryProducCommentForDetailHyc.htm?type=1"+params,
        type:"post",
        async:false,
        success:function(data){
            *//*设置所有的行数*//*
            zhongCount=data.rows;
            if(type==1){
                putPageComment(type,data);
            }
            $("#zhongping").text("中评（"+zhongCount+"）");
        }
    });*/

    /*AJAX查询商品差评*/
    /*$.ajax({
        url:"../queryProducCommentForDetailHyc.htm?type=2"+params,
        type:"post",
        async:false,
        success:function(data){
            *//*设置所有的行数*//*
            chaCount=data.rows;
            if(type==2){
                putPageComment(type,data);
            }
            $("#chaping").text("差评（"+chaCount+"）");
        }

    });*/
    /*count = haoCount+zhongCount+chaCount;*/
    //多少人评论
    //$("#commentCount").text(count);
    /*if(count == 0){
        $("#haoPingLv").text("100%");
    }else{
        var haoPingLv=parseInt((haoCount/count) * 100);
        $("#haoPingLv").text(haoPingLv+"%");
    }*/
}

/*将查询到的评论加载到页面中*/
function putPageComment(type,data){
    var commentHtml="";
    if(data.list!=null && data.list.length>0){
        for(var l=0;l<data.list.length;l++){
            var comment = data.list[l];
            // 晒单集合对象
            var shareImgList = comment.imageList;
            var customerImg;

            // 晒单图片对象
            var shareImg = "";
            if(shareImgList != null){
                shareImg = '<a href="../queryShareImgsByShareId.htm?shareId='+comment.shareId+'"><ul class="img_sun">';
                // 遍历晒单图片
                for(var i = 0;i<4;i++){
                    if(shareImgList[i] != null){
                        if(i == 0){
                            shareImg +='<li><img src="'+shareImgList[i].imageName+'"/></li>'
                        }else{
                            shareImg +='<li class="margL10"><img src="'+shareImgList[i].imageName+'"/></li>'
                        }
                    }else{ // 图片不够时候的占位
                        shareImg +='<li class="margL10"></li>'
                    }
                }
                shareImg +='</ul></a>';
            }

            // 会员头像 为空时 默认显示优生活图标
            if(comment.customerImg == "" || comment.customerImg == null){
                customerImg = "../images/defaultImg.png";
            }else{
                customerImg = comment.customerImg;
            }


            var star = "star"+comment.commentScore;
            if(comment.isAnonymous=='1'){
                commentHtml+="<div class='list-item'><div class='star'>" +
                "<img class='list-itemImg' src='"+customerImg+"' width='100%'/>"+
                "<div class='"+star+" cur ml34'></div>" +
                "</div> <div class='user-item'>&nbsp;<span class='userName'>****&nbsp;&nbsp;&nbsp;</span>" +
                "<span>"+timeStamp2String(comment.publishTime)+"</span></div><p class='text'>"+comment.commentContent+"</p>"+shareImg+"</div>";
            }else{
                commentHtml+="<div class='list-item'><div class='star'>" +
                "<img class='list-itemImg' src='"+customerImg+"' width='100%'/>"+
                "<div class='"+star+" cur ml34'></div>" +
                "</div> <div class='user-item'>&nbsp;<span class='userName'>"+ SubString(comment.customerNickname)+"&nbsp;&nbsp;&nbsp;</span>" +
                "<span>"+timeStamp2String(comment.publishTime)+"</span></div><p class='text'>"+comment.commentContent+"</p>"+shareImg+"</div>";
            }
        }
    }else{
        commentHtml+="<div class='list-item'><div class='user-item'><span></span></div><p class='text'>暂无商品评论</p></div>";
    }
    $("#commentBody").append(commentHtml);
    $(".curValue").html(data.rows+"人评论");

    // 设置晒单图片的高度
    $(".img_sun li").each(function(){
        $(this).css("height",$(this).width());
    });
}

/**
 * 点击好评时调用
 * */
//$("#haoping").click(function(){
//    $(this).addClass('selected').siblings().removeClass('selected');
//    loadComment(1,0);
//});

/**
 * 点击中评时调用
 * */
//$("#zhongping").click(function(){
//    $(this).addClass('selected').siblings().removeClass('selected');
//    loadComment(1,1);
//});

/**
 * 点击差评时调用
 * */
//$("#chaping").click(function(){
//    $(this).addClass('selected').siblings().removeClass('selected');
//    loadComment(1,2);
//});

var allProductList = new Array();
/**
 * 加载所属商品下的所有的货品并初始化规格值按钮
 * @param productList 货品列表
 */
function loadAllProduct(productList)
{
    allProductList = productList;
    /*如果货品信息为空或者长度为0，设置所有的规格值为空*/
    if (allProductList == null || allProductList.length == 0) {
        $("._sku").addClass("disabled");
    }
    else {
        //控制规格值得按钮
        controlSpecBtn(allProductList,null,true,false);
    }
}


/*控制规格值按钮*/
function controlSpecBtn(tempProductList,obj,bool,checkRunAgain)
{
    var specList = $("._sku");
    var canSelSpec = new Array();
    /*已经获取到匹配的货品取出所有的关联的规格值*/
    for (var i = 0; i < tempProductList.length; i++)
    {
        //循环可选择的货品的集合获取所有的可选择的规格按钮
        var goodsSpec = tempProductList[i].specVo;
        for (var k = 0; k < goodsSpec.length; k++) {
            canSelSpec.push(goodsSpec[k].goodsSpecDetail.specDetailId);
        }
    }
    /*控制页面中的按钮*/
    for (var i = 0; i < specList.length; i++)
    {
        var count = 0;
        //循环去可选按钮中匹配是否存在
        for (var k = 0; k < canSelSpec.length; k++) {
            if (canSelSpec[k] == $(specList[i]).attr("data-value")) {
                count = count - 1 + 2;
            }
        }
        //如果循环到的按钮匹配可选按钮的数量为0，则标记为不可选中，否则就是可选中
        if (count == 0) {
            $(specList[i]).remove();;
            /*删除保存的以选中记录,并添加新的*/
        }
        else {
            $(specList[i]).removeClass("disabled");
        }
    }
    //如果传递过来的对象不为空并且，再次执行计算按钮的标记为真，并且方法标记为false时执行以下方法，重新计算可选择的按钮
    if(!bool && obj!=null && checkRunAgain){
        $(obj).removeClass("disabled");
        clickSpecDetail(obj,false);
    }
}

/*点击规格值的时候*/
function clickSpecDetail(obj,bool)
{
    var self = $(obj);
    if (self.hasClass('disabled')) {
        //return false;
        self.removeClass('disabled');
    }else{
        //选中自己，兄弟节点取消选中
        self.addClass('selected').siblings().removeClass('selected');
    }
    /*删除保存的以选中记录,并添加新的*/
    $(".sel_spec_"+$(obj).attr("data-parent")).html("“"+$(obj).attr("title")+"”");

    self.append("<i></i>");
    var specList = $("._sku");
    var selSpecList = new Array();
    /*获取已经选中的规格值*/
    for (var i = 0; i < specList.length; i++) {
        if ($(specList[i]).hasClass("selected")) {
            selSpecList.push(specList[i]);
        }
    }
    var tempProductList = new Array();
    /*根据已经选中的规格值循环所有的货品筛选出可以被选中的货品*/
    var allRunCount=0;
    for (var j = 0; j < allProductList.length; j++)
    {
        //获取到货品的关联的规格
        var goodsSpec = allProductList[j].specVo;
        var count = 0;
        //循环货品的规格去选中的规格中匹配
        for (var k = 0; k < goodsSpec.length; k++)
        {
            for (var i = 0; i < selSpecList.length; i++)
            {
                //如果当前循环的规格和循环到的货品的规格相匹配就给标记+1
                if (goodsSpec[k].goodsSpecDetail.specDetailId == $(selSpecList[i]).attr("data-value")) {
                    count = count - 1 + 2;
                    if (count == 0) {
                        $(specList[i]).addClass("disabled");
                        /*删除保存的以选中记录,并添加新的*/
                    }
                    else {
                        $(specList[i]).removeClass("disabled");
                    }
                };
            };
        };
        /*如果匹配的数量大于等于已经选中的数量则说明完全匹配，跳转到货品详情页*/
        if (count >= selSpecList.length) {
            if($("#allSpecLength").val()==count){
                $("#productId").val(allProductList[j].goodsInfoId);
                $("#isAjax").val("1");
                $("#paramGoodsForm").attr("action","../item/"+allProductList[j].goodsInfoId+".html");
                $("#paramGoodsForm").submit();
            }
            tempProductList.push(allProductList[j]);
        }else{
            //如果匹配的数量小于已经选中的数量则给标记+1
            allRunCount+=1;
        };
    }
    //如果标记等于所有的货品的长度说明没有匹配的货品，就保留当前选中的按钮，重新计算其他可选的按钮
    if(allRunCount==allProductList.length){
        //选中自己，其他的全部取消选中
        $("._sku").removeClass('selected');
        /*self.removeClass("disable");*/
        //取消完所有的选中之后选中当前传递的对象
        self.addClass('selected');
        self.append("<i></i>");
        //再次调用当前的方法进行计算
        clickSpecDetail(self,false);
        //设置控制按钮的方法循环执行计算可选按钮的标记为true
        bool=true;
    }
}

/*把已经选择的属性置为选中状态*/
function loadChooseParam() {

    var params = $(".chooseParamId");
    var specList = $("._sku");
    if (params.length > 0) {
       for (var i = 0; i < params.length; i++) {
            for (var k = 0; k < specList.length; k++) {
                $(specList[k]).removeClass("disabled");
                if ($(specList[k]).attr("data-value") == $(params[i]).val()) {
                    $(specList[k]).addClass("selected");
                    $(specList[k]).addClass("disabled");


                }
            }
        }
    }
    var specNamesss = "";
    $(".specNames").each(function(){
        var s =  $(this).find("h3").text();
        var sss = $(this).find("div").find("a");
        if(sss.hasClass("selected")){
            specNamesss += s+":"+$(this).find("div").find("a.selected").text()+"&nbsp;&nbsp;";
        }
    });
    $("#yixuan").append(specNamesss);
}


/*处理时间格式化*/
function timeStamp2String(time){
    var date=new Date(parseFloat(time));
    var datetime = new Date();
    datetime.setTime(date);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    //return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
    return year + "-" + month + "-" + date;
}

/*初始化已经选择的城市*/
//function loadInit(){
//    $.ajax({
//        type: 'post',
//        url:"../getAllProvince.htm",
//        async:false,
//        success: function(data) {
//            var provinceHtml="";
//            for(var i=0;i<data.length;i++){
//                provinceHtml+="<dd onClick='loadCity("+data[i].provinceId+",this);'>"+data[i].provinceName+"</dd>";
//            }
//            $(".province").html(provinceHtml);
//        }
//    });
//}

/*根据点击的省份加载城市*/
//function loadCity(pid,pro){
//    $(".ch_province").val($(pro).text());
//    $("#okProvince").text($(pro).text());
//    $.ajax({
//        url:"../getAllCityByPid.htm?provinceId="+pid,
//        type:"post",
//        async:false,
//        success:function(data){
//            var cityHtml="";
//            for(var i=0;i<data.length;i++){
//                cityHtml+="<dd onClick='loadDistinct("+data[i].cityId+",this);'>"+data[i].cityName+"</dd>";
//            }
//            $(".city").html(cityHtml);
//        }
//    });
//    $('.area-box-lv1').hide();
//    $('.area-box-lv2').show();
//
//}

/*根据点击的城市加载区县*/
//function loadDistinct(cid,city){
//    $(".ch_city").val($(city).text());
//    $("#okProvince1").text($("#okProvince").text());
//    $("#okCity").text($(city).text());
//    $.get("../getAllDistrictByCid.htm?cityId="+cid,function(data){
//        var distinctHtml="";
//        for(var i=0;i<data.length;i++){
//            distinctHtml+="<dd onClick='checkDistinct("+data[i].districtId+",this);'>"+data[i].districtName+"</dd>";
//        }
//        $(".district").html(distinctHtml);
//    });
//    $('.area-box-lv2').hide();
//    $('.area-box-lv3').show();
//}

/*点击区县的时候*/
//function checkDistinct(dId,dis){
//    $(".ch_distinct").val($(dis).text());
//    /*当点击最后一级区县的时候   把之前选择的省份,城市和区县信息拼装成一个字符串并提交到后台控制器*/
//    $(".ch_address").val($(".ch_province").val()+$(".ch_city").val()+$(".ch_distinct").val());
//    $(".ch_distinctId").val(dId);
//    $("#isAjax").val("0");
//    $("#paramGoodsForm").submit();
//}

/**
 * 加载购物车数量
 * */
//function loadShopCartNum(){
//    var goodsNum = 0;
//    $.ajax({
//        url:"../myshoppingmcartNum",
//        type:"post",
//        success:function(data){
//            var cartgoods = data.shopCart.miniGoodsList;
//            if(cartgoods != null && cartgoods.length>0){
//                for(var i = 0 ; i < cartgoods.length ; i++) {
//                    goodsNum += cartgoods[i].buNum;
//                }
//                $("#shopCartNum").text(goodsNum);
//            }else {
//                $("#shopCartNum").text(0);
//            }
//        }
//    });
//}

/**
 * 查询该用户是否收藏该商品
 * */
//function loadCollection(){
//    var productId = $("#productId").val();
//    $.ajax({
//        url:"../ajaxcollection.htm?productId="+productId,
//        type:"post",
//        success:function(data){
//            if(data >= 1){
//                $(".collection").addClass("selected");
//            }
//        }
//    });
//}

function showPingLun(){
    var productId = $("#productId").val();
    location.href="../showPingLun.htm?productId="+productId;
}

function opblur(){
    var r = /^\+?[1-9][0-9]*$/;
    if(!r.test($(".product_count").val())){
        $(".product_count").val(1);
        return ;
    }
}

$(function(){
    /*点击加按钮*/
    $(".num_plus").click(function(){
        var count = parseFloat($(".product_count").val());
        $(".product_count").val(parseFloat(count + parseFloat(1)));
    });

    /*点击减按钮*/
    $(".num_minus").click(function(){
        var count = parseFloat($(".product_count").val());
        if(count >1){
            $(".product_count").val(parseFloat(count - parseFloat(1)));
        }
    });

    var buyNum=0;
    /*立即购买时间*/
    $(".toBuy").click(function(){
        /*如果选中的规格小于所有的规格,表示规格选择不完全,不允许加入购物车*/
        if($(".selected").length<parseFloat($("#allSpecLength").val())){
            setTimeout(function(){
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>请选择规格！</h3></div></div>');
            },1000);
            setTimeout(function(){
                window.location.reload();
            },2000)
            return ;
        }

        /*如果购买数量大于剩余库存不允许购买*/
        var count = parseFloat($(".product_count").val());
        //var stock = parseFloat($("#productStock").val());
        var goodsInfoAdded=parseFloat($("#goodsInfoAdded").val());
        if(goodsInfoAdded!=1){
            setTimeout(function(){
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>货品已下架！</h3></div></div>');
            },1000);
            setTimeout(function(){
                window.location.reload();
            },2000);
            return;
        }

        //if(count<=stock){
            buyNum=buyNum+1;
            location.href = "addproducttoshopmcarl?goodsNum=" + count + "&productId=" + $("#productId").val() + "&distinctId=1103&subjectId=0";
        //}else{
        //    setTimeout(function(){
        //        $('.tip-box').remove();
        //        $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>库存不足！</h3></div></div>');
        //    },1000);
        //    setTimeout(function(){
        //        window.location.reload();
        //    },2000)
        //    return;
        //}
    });

    /**
     * 加入购物车
     * */
    var addCartNum=0;
     $("#add_cart").click(function(){

         var   r   =/^\d+$/g;
         if(r.test($(".product_count").val())){

         }else{
             $("#numError").text("您输入的数量有误！");
             return;
         }

        /*如果选中的规格小于所有的规格,表示规格选择不完全,不允许加入购物车*/
        if($(".selected").length<parseFloat($("#allSpecLength").val())){
            setTimeout(function(){
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>请选择规格！</h3></div></div>');
            },1000);
            setTimeout(function(){
                window.location.reload();
            },2000)
            return ;
        }
        /*如果购买数量大于剩余库存不允许购买*/
        //$(".tip_text").html("库存不足!");
        //alert("库存不足！");

        var count = parseFloat($(".product_count").val());
        //var stock = parseFloat($("#productStock").val());
        var goodsInfoAdded=parseFloat($("#goodsInfoAdded").val());
        if(goodsInfoAdded!=1){
            setTimeout(function(){
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>货品已下架！</h3></div></div>');
            },1000);
            setTimeout(function(){
                window.location.reload();
            },2000)

            return;
        }
        //if(count<=stock){
            addCartNum=addCartNum+1;
            $.post("addproducttoshopcar",{
                goodsNum:$(".product_count").val(),
                productId:$("#productId").val(),
                distinctId:$(".ch_distinctId").val(),
                marketingId:$("#marketingId").val(),
                subjectId:"0"
            },function(data){
                if(data.response == "success"){
                    setTimeout(function(){
                        $('.tip-box').remove();
                        $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="success"></i><h3>成功加入购物车</h3></div></div>');
                    },1000);
                    setTimeout(function(){
                        window.location.reload();
                    },2000)
                }else {
                    setTimeout(function(){
                        $('.tip-box').remove();
                        $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>加入购物车失败</h3></div></div>');
                    },1000);
                    setTimeout(function(){
                        window.location.reload();
                    },2000)
                }
            });
            //location.href="../addproducttoshopcarnew.htm?goodsNum="+$(".product_count").val()+"&productId="+$("#productId").val()+"&distinctId=1103";
        //}else{
            //if(addCartNum==0){
            //$(".buy_now_tip").click();
            //}
        //    setTimeout(function(){
        //        $('.tip-box').remove();
        //        $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>库存不足！</h3></div></div>');
        //    },1000);
        //    setTimeout(function(){
        //        window.location.reload();
        //    },2000)
        //
        //
        //    return;
        //}
    });

    /**
     * 收藏商品
     * */
    //$(".collection").click(function(){
    //    var districtId = $(".ch_distinctId").val();
    //    var goodsprice = $("#followPrice").val();
    //    var productId = $("#productId").val();
    //    var _this=$(this);
    //    $.post("../saveAtte.htm",{productId:productId,districtId:districtId,goodsprice:goodsprice},function(data){
    //        if(data=="-2"){
    //            //返回结果为2代表用户未登录，跳转到登录页面
    //            location.href="../register.html?url=/customercenter.html";
    //        }
    //        else{
    //            if(_this.hasClass("selected")) {
    //                _this.removeClass("selected");
    //            } else {
    //                _this.addClass("selected");}
    //        }
    //    });
    //});

});

$(function(){
    for(var i=0;i<$('.userName').length;i++){
        var text = $('.userName').eq(i).text();
        var first = text.substr(0,1);
        var last = text.substr(text.length-1,text.length-1);
        $('.userName').eq(i).html(first+'***'+last);
    };
})

// 截取
function SubString(obj){
    var first = obj.substr(0,1);
    var last = obj.substr(obj.length-1,obj.length-1);
    return first+'***'+last;
}


