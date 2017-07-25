/**
 * Created by suibian on 2016/5/7.
 */
/*$(function(){
    var productId = "4320";
    $.get("productdetail.htm",{
        productId:productId
    },function(data){
        if(data.response == "success"){
            goods_data(data);
        }else{
            return;
        }
    },"json");
})
function goods_data(data){
    //获取商品图片
    for(var i=0;i<data.data.productVo.imageList.length;i++){
        var imgSrc = data.data.productVo.imageList[i].imageBigName;
        if(i == 0){
            $(".swiper-wrapper").append('<div class="swiper-slide swiper-slide-active" style="width: 375px; height: 2646px;"><a href="#"><img alt="" src="'+imgSrc+'" style="height: 375px;"></a></div>');
        }else{
            $(".swiper-wrapper").append('<div class="swiper-slide" style="width: 375px; height: 2646px;"><a href="#"><img alt="" src="'+imgSrc+'" style="height: 375px;"></a></div>');
        }
    }
    //设置商品图片轮播
    var mySwiper = new Swiper('.swiper-container',{
         pagination: '.swiper-pagination',
         loop:true,
         grabCursor: true,
         autoplay : 3000
     });
    //获取商品名称
    var goodsName = data.data.productVo.productName;
    $("#goodsName").html(goodsName);

    var goodsPrice = data.data.productVo.goodsInfoPreferPrice;
    $("#goodsPrice").find("span").html(goodsPrice+".00");
}
*/
$(function() {
    /*点击加按钮*/
    $(".num_plus").click(function () {
        var count = parseFloat($(".product_count").val());
        $(".product_count").val(parseFloat(count + parseFloat(1)));
    });

    /*点击减按钮*/
    $(".num_minus").click(function () {
        var count = parseFloat($(".product_count").val());
        if (count > 1) {
            $(".product_count").val(parseFloat(count - parseFloat(1)));
        }
    });

    var buyNum = 0;
    /*立即购买时间*/
    $(".toBuy").click(function () {
        /*如果选中的规格小于所有的规格,表示规格选择不完全,不允许加入购物车*/
        if ($(".selected").length < parseFloat($("#allSpecLength").val())) {
            setTimeout(function () {
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>请选择规格！</h3></div></div>');
            }, 1000);
            setTimeout(function () {
                window.location.reload();
            }, 2000)
            return;
        }

        /*如果购买数量大于剩余库存不允许购买*/
        var count = parseFloat($(".product_count").val());
        var stock = parseFloat($("#productStock").val());
        var goodsInfoAdded = parseFloat($("#goodsInfoAdded").val());
        if (goodsInfoAdded != 1) {
            setTimeout(function () {
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>货品已下架！</h3></div></div>');
            }, 1000);
            setTimeout(function () {
                window.location.reload();
            }, 2000);
            return;
        }

        if (count <= stock) {
            buyNum = buyNum + 1;
            location.href = "addproducttoshopmcarl?goodsNum=" + count + "&productId=" + $("#productId").val() + "&distinctId=1103&subjectId=0";
        } else {
            setTimeout(function () {
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>库存不足！</h3></div></div>');
            }, 1000);
            setTimeout(function () {
                window.location.reload();
            }, 2000)
            return;
        }
    });
});

//加载商品规格参数
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
/**
 * 加载购物车数量
 * */
function loadShopCartNum(){
    var goodsNum = 0;
    $.ajax({
        url:"../myshoppingmcartNum",
        type:"post",
        success:function(data){
            var cartgoods = data.shopCart.miniGoodsList;
            if(cartgoods != null && cartgoods.length>0){
                for(var i = 0 ; i < cartgoods.length ; i++) {
                    goodsNum += cartgoods[i].buNum;
                }
                $("#shopCartNum").text(goodsNum);
            }else {
                $("#shopCartNum").text(0);
            }
        }
    });
}

/**
 * 加入购物车
 * */
$(function(){
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
        var stock = parseFloat($("#productStock").val());
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
        if(count<=stock){
            addCartNum=addCartNum+1;
            //ajax加入购物
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
        }else{
            setTimeout(function(){
                $('.tip-box').remove();
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><i class="failed"></i><h3>库存不足！</h3></div></div>');
            },1000);
            setTimeout(function(){
                window.location.reload();
            },2000)
            return;
        }
    });
})
var allProductList = new Array();
var tempList = new Array();
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



