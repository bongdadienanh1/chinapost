var custId = $("#customerId").val();
var basePath=$("#basePath").val();
$(function(){
    //更换促销
    $(".fav-list .check-box").click(function(){
        if($(this).parent().parent().prop("class")=='selected'){
            $(this).parents(".fav-list").find(".selected").removeClass("selected");
        }else{
            $(this).parents(".fav-list").find(".selected").removeClass("selected");
            $(this).parents("li").addClass("selected");

        }
    })
})
//全选
function checkAll(){
    var selected = $(".select-all .selected");
    if (selected.length == 0) {
        $(".select-all .select-box").addClass("selected");
        $(".list-item .select-box").each(function(){
            $(this).removeClass("selected");
            checkone(this);
        })
    } else {
        $(".select-all .select-box").removeClass("selected")
        $(".list-item .select-box").each(function(){
            $(this).addClass("selected");
            checkone(this);
        })
    }

}
//购物车商品的选中
function checkone(ojb) {
    var selected = $(ojb).parent().find(".selected");
    if (selected.length == 0) {
        $(ojb).addClass("selected");
    } else {
        $(ojb).removeClass("selected");
    }
    //获取外部的DIV 是否是促销的商品
    //var codexType = $(ojb).parents("li").find(".fav-list").attr("attr-codextype");
    //if (codexType == '1') {
    //    var youhui = 0;
    //    var xiaoji = 0;
    //    $(ojb).parents("li").find(".list-item").each(function () {
    //        if ($(this).find(".selected").length == 1) {
    //            var num = $(this).find(".goodsNum").val();
    //            var price = $(this).find(".goodsPrice").val();
    //            var zhijiang = $(this).parents("li").find(".zhijiang_offValue").val();
    //            youhui = accAdd(youhui, accMul(num, zhijiang));
    //            xiaoji = accAdd(accMul(num, price), xiaoji);
    //        }
    //    });
    //    $(ojb).parents("li").find(".youhui").val(youhui);
    //
    //    $(ojb).parents("li").find(".oneprice").val(xiaoji);
    //
    //} else if (codexType == '5') {
    //    var mj_sumprice = 0;
    //    var mj_end = 0;
    //    //满减
    //    $(ojb).parents("li").find(".list-item").each(function () {
    //        //判断是否选中
    //        if ($(this).find(".selected").length == 1) {
    //            var num = $(this).find(".goodsNum").val();
    //            var price = $(this).find(".goodsPrice").val();
    //            mj_sumprice = accAdd(accMul(num, price), mj_sumprice);
    //        }
    //    });
    //
    //
    //    $(ojb).parents("li").find(".manjian_reducePrice").each(function () {
    //        var man = $(this).val().split(",")[0];
    //        var jian = $(this).val().split(",")[1];
    //        if (Subtr(mj_sumprice, man) >= 0) {
    //            mj_end = jian;
    //        }
    //
    //    });
    //    $(ojb).parents("li").find(".oneprice").val(mj_sumprice);
    //    $(ojb).parents("li").find(".youhui").val(mj_end);
    //
    //} else if (codexType == '8') {
    //    //满折
    //    var mz_sumprice = 0;
    //    var mz_end = 0;
    //    $(ojb).parents("li").find(".list-item").each(function () {
    //        //判断是否选中
    //        if ($(this).find(".selected").length == 1) {
    //            var num = $(this).find(".goodsNum").val();
    //            var price = $(this).find(".goodsPrice").val();
    //            mz_sumprice = accAdd(accMul(num, price), mz_sumprice);
    //        }
    //    });
    //
    //    $(ojb).parents("li").find(".manzhe_fullbuyDiscount").each(function () {
    //        var man = $(this).val().split(",")[0];
    //        var zhe = $(this).val().split(",")[1];
    //        if (Subtr(mz_sumprice, man) >= 0) {
    //            mz_end = accMul(mz_sumprice, Subtr(1, zhe));
    //        }
    //
    //    });
    //    $(ojb).parents("li").find(".oneprice").val(mz_sumprice);
    //    $(ojb).parents("li").find(".youhui").val(mz_end)
    //
    //} else if (codexType == '-1') {
    //    //-1代表是套装
    //    var num = $(obj).parents(".cart_item").find(".decrement").next().val();
    //    var xiaoji = 0;
    //    $(obj).parents(".marketgroup").find(".cart_item_goods").each(function () {
    //        var isprice = accMul($(ojb).find(".oneprice").val(), num);
    //        xiaoji = accAdd(xiaoji, isprice);
    //    });
    //    var groupPreferamount = $(obj).parents(".cart_item").find(".groupPreferamount").val();
    //    var youhui = accMul(num, groupPreferamount);
    //    $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);
    //    $(obj).parents(".marketgroup").find(".youhui").val(youhui);
    //} else {
        var xiaoji = 0;
        $(ojb).parents("li").find(".list-item").each(function () {
            //判断是否选中
            if ($(this).find(".selected").length == 1) {
                var num = $(this).find(".goodsNum").val();
                var price = $(this).find(".goodsPrice").val();
                xiaoji = accAdd(accMul(num, price), xiaoji);
            }
        });
        $(ojb).parents("li").find(".oneprice").val(xiaoji);
    //}
    lastsum();
}

function mit(obj,cartId){
    //if($(obj).parents(".marketgroup").attr("attr-codextype")!="-1"){
        //获取 可用的最大库存和最小
        var minNum = 1;
        var nowNum = $(obj).next().val();
        var setNum = 0;
        if(nowNum>minNum){
            setNum=Subtr(nowNum,1);
        }else{
            setNum=nowNum;
        }
        $(obj).next().val(setNum);
        changeNum(cartId,setNum);
        jisuan(obj);
    //}else{
    //    //套装
    //    var nowNum = $(obj).next().val();
    //    var flag = 0;
    //    if(nowNum=='1'){
    //        flag ++;
    //    }
    //
    //    if(flag == 0 ){
    //        var zongji = 0 ;
    //        $(obj).next().val(SubtrInt(nowNum,1));
    //        nowNum --;
    //        $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
    //            $(this).find(".buyNum").text(nowNum);
    //            var onePrice = $(this).find(".oneprice").val();
    //            var xiaoji = accMul(onePrice,nowNum);
    //            zongji = accAdd(xiaoji,zongji);
    //            $(this).find(".smprice").val(xiaoji);
    //            $(this).find(".pv_smprice").text(xiaoji);
    //        });
    //        if($(obj).parents(".cart_item").find(".mjchecked")[0].checked){
    //            $(obj).parents(".marketgroup").find(".xiaoji").val(zongji);
    //            var oneyh = $(obj).parents(".cart_item").find(".groupPreferamount").val();
    //            $(obj).parents(".marketgroup").find(".youhui").val(accMul(oneyh,nowNum));
    //        }
    //        changeNum(cartId,nowNum);
    //        lastsum();
    //    }
    //
    //}

}

function opblur(obj,cartId){

    //if($(obj).parents(".marketgroup").attr("attr-codextype")!="-1"){
        var reg = new RegExp("^[0-9]*$");
        var nowNum = $(obj).val();
        //var maxNum = $(obj).attr("attr-maxnum");
        var minNum = 1;
        var setNum = 0 ;
        if(nowNum=='0' || nowNum==''){
            setNum = minNum ;
            nowNum =  minNum;
        }
        if(reg.test(nowNum)){
            //if(Subtr(nowNum,maxNum)>0){
            //    setNum = maxNum ;
            //}else{
                setNum = nowNum;
            //}
        }else{
            setNum = minNum ;
        }
        changeNum(cartId,setNum);
        $(obj).val(setNum);
        jisuan(obj);
        //lastsum();
    //}else{
    //    var flag= 0 ;
    //    //套装
    //    var reg = new RegExp("^[0-9]*$");
    //    var nowNum = $(obj).val();
    //    if(!reg.test(nowNum)){
    //        nowNum = 1;
    //        $(obj).val(nowNum);
    //    }
    //    var maxNum = $(obj).attr("attr-maxnum");
    //    if(SubtrInt(maxNum,nowNum)<0){
    //        nowNum = maxNum;
    //    }
    //    $(obj).val(nowNum);
    //    var zongji = 0 ;
    //    $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
    //        $(this).find(".buyNum").text(nowNum);
    //        var onePrice = $(this).find(".oneprice").val();
    //        var xiaoji = accMul(onePrice,nowNum);
    //        zongji = accAdd(xiaoji,zongji);
    //        $(this).find(".smprice").val(xiaoji);
    //        $(this).find(".pv_smprice").text(xiaoji);
    //    });
    //
    //    if($(obj).parents(".cart_item").find(".mjchecked")[0].checked){
    //        $(obj).parents(".marketgroup").find(".xiaoji").val(zongji);
    //        var oneyh = $(obj).parents(".cart_item").find(".groupPreferamount").val();
    //        $(obj).parents(".marketgroup").find(".youhui").val(accMul(oneyh,nowNum));
    //    }
    //
    //    changeNum(cartId,nowNum);
    //    lastsum();
    //
    //}
}


function add(obj,cartId){
    //不等于-1 为套装
    //if($(obj).parents(".marketgroup").attr("attr-codextype")!="-1"){
    //获取 可用的最大库存和最小
    //var maxNum = $(obj).prev().attr("attr-maxnum");
    var nowNum = $(obj).prev().val();
    var setNum;
    //if(Subtr(maxNum,nowNum)<=0){
    //    setNum=nowNum;
    //}else{
        setNum=accAddInt(nowNum,1);
        changeNum(cartId,setNum);
    //};
    $(obj).prev().val(setNum);
    jisuan(obj);
    //}else{
    //    //套装
    //    var nowNum = $(obj).prev().val();
    //    var flag = 0;
    //    var num = $(obj).prev().attr("attr-maxnum");
    //    if(Subtr(num,nowNum)<=0){
    //        flag ++ ;
    //    }
    //
    //
    //    if(flag == 0 ){
    //        var zongji = 0 ;
    //        $(obj).prev().val(accAddInt(nowNum,1));
    //        nowNum ++;
    //        $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
    //            $(this).find(".buyNum").text(nowNum);
    //            var onePrice = $(this).find(".oneprice").val();
    //            var xiaoji = accMul(onePrice,nowNum);
    //            zongji = accAdd(xiaoji,zongji);
    //            $(this).find(".smprice").val(xiaoji);
    //            $(this).find(".pv_smprice").text(xiaoji);
    //        });
    //        if($(obj).parents(".cart_item").find(".mjchecked")[0].checked){
    //            $(obj).parents(".marketgroup").find(".xiaoji").val(zongji);
    //            var oneyh = $(obj).parents(".cart_item").find(".groupPreferamount").val();
    //            $(obj).parents(".marketgroup").find(".youhui").val(accMul(oneyh,nowNum));
    //        }
    //        changeNum(cartId,nowNum);
    //        lastsum();
    //    }
    //
    //}


}
//<!--未登录修改数量-->
function changeNumCoo(id,num){
    //$.post(basePath+"/addProductToShopmCar.htm", { productId: id, goodsNum: num },
    //    function(data){
    //    });
}
//<!--已登录修改数量-->
function changeNum(id,num){
    $.post(basePath+"/changeshoppingmcartbyid?shoppingCartId="+id+"&num="+num, function (data)
    {
        if (data.response=="success")
        {
            return;
        }
    });
}
function jisuan(obj){
    //计算价格 总计和优惠
    //var codexType =  $(obj).parents(".list-item").parent().find(".fav-list").attr("attr-codextype");

    var xiaoji = 0 ;
    var youhui = 0 ;
    $(obj).parents("li").find(".list-item .selected").each(function(){
        //一件商品的价格
        var oneprice=$(this).parents(".list-item").find(".goodsPrice").val();
        var num=$(this).parents(".list-item").find(".goodsNum").val();
        var thisPrice=accMul(oneprice,num);
        //总计+单品小计
        xiaoji = accAdd(thisPrice,xiaoji);

    });

    //
    //if(codexType=='1'){
    //    $(obj).parents("li").find(".list-item").each(function() {
    //        if($(this).find(".selected").length==1){
    //            var num = $(this).find(".goodsNum").val();
    //            var zhijiang = $(this).parents("li").find(".zhijiang_offValue").val();
    //            youhui = accAdd(youhui, accMul(num, zhijiang));
    //        }
    //    });
    //}else if(codexType=='5'){
    //    $(obj).parents("li").find(".manjian_reducePrice").each(function(){
    //        var man = $(this).val().split(",")[0];
    //        var jian = $(this).val().split(",")[1];
    //        if(Subtr(xiaoji,man)>=0){
    //            youhui = jian;
    //        }
    //
    //    });
    //
    //}else if(codexType=='8'){
    //    $(obj).parents("li").find(".manzhe_fullbuyDiscount").each(function(){
    //        var man = $(this).val().split(",")[0];
    //        var zhe = $(this).val().split(",")[1];
    //        if(Subtr(xiaoji,man)>=0){
    //            youhui = accMul(xiaoji,Subtr(1,zhe));
    //        }
    //
    //    });
    //
    //}

    $(obj).parents("li").find(".oneprice").val(xiaoji);
    $(obj).parents("li").find(".youhui").val(youhui);

    lastsum();
}

function lastsum(){
    var zongji = 0;
    var fanxian = 0;
    var allcount = 0;
    $(".oneprice").each(function(){
        if($(this).parent().parent('.list-item').find('i.selected').length != "0"){
            zongji = accAdd(zongji,$(this).val());
        }
    });

    $(".youhui").each(function(){
        fanxian = accAdd(fanxian,$(this).val());
    });
    $(".list-item .selected").each(function(){
        allcount = accAddInt($(this).parent().find(".goodsNum").val(),allcount);
    });

    $("#goodsNum").html(allcount);
    $(".money-all").html(zongji+"邮豆");
    $(".fanxian").html(fanxian);
    $(".payPrice").html(Subtr(zongji,fanxian)+"邮豆");
}
//去结算
function onpay(){
    var obj=$(".list-item .selected");
    if(obj.length!=0){
        var vHtm="";
        for(var i=0; i<obj.length; i++){
            //    if($(obj[i]).parent().find(".noexit").length!=0){
            //        var discountBox = dialog({
            //            width: 260,
            //            title: '操作提示',
            //            content: "包含无货或下架商品，请重新选择!",
            //            okValue: '确定',
            //            cancelValue: '取消',
            //            ok: function () {
            //                return true;
            //            },
            //            cancel: function () {
            //                return true;
            //            }
            //        })
            //        discountBox.showModal();
            //        return;
            //    }
            vHtm+="<input type='hidden' value="+$(obj[i]).parent().find(".shoppingCartId").val()+" name='box'/>";
        }
        $(".subForm").append(vHtm);
        $(".subForm").submit();
    }else{
        var discountBox = dialog({
            width: 260,
            title: '操作提示',
            content: "购物车没有选中商品，是否跳转到首页",
            okValue: '确定',
            cancelValue: '取消',
            ok: function () {
                location.href=basePath+"/main"
                return true;
            },
            cancel: function () {

                return true;
            }
        })
        discountBox.showModal();
    }

}
//专区选择
function checkArea(obj){
    var id = $(obj).parent().parent().attr("id");
    for(var i=0;i<$(".cart-list .subjectGoods").length;i++){
        var className = $(".cart-list .subjectGoods")[i].className;
        var arry = className.split(" ");
        if(arry[1]==id){
            if(obj.className.indexOf("selected")>=0){
                $(".cart-list .subjectGoods").eq(i).find(".select-box").removeClass("selected");
            }else{
                $(".cart-list .subjectGoods").eq(i).find(".select-box").addClass("selected");
            }
        }
    }
    lastPrice();
    if(obj.className.indexOf("selected")>=0){
        $(obj).removeClass("selected");
    }else{
        $(obj).addClass("selected");
    }
}

function lastPrice(){
    var zongji = 0;
    var allcount = 0;
    var fanxian = 0;
    for(var i=0;i<$(".cart-list .subjectGoods").length;i++){
        if($(".cart-list .subjectGoods").eq(i).find(".selected").length>0){
            var num = $(".cart-list .subjectGoods").eq(i).find(".goodsNum").val();
            var price = $(".cart-list .subjectGoods").eq(i).find(".goodsPrice").val();
            zongji += num*price;
            allcount += Number(num);
            /*//获取外部的DIV 是否是促销的商品
             var codexType = $(".cart-list .subjectGoods").eq(i).find(".fav-list").attr("attr-codextype");
             if (codexType == '1') {
             var zhijiang = $(this).parents("li").find(".zhijiang_offValue").val();
             fanxian += Number(zhijiang)*Number(num);
             } else if (codexType == '5') {
             var mj_sumprice = 0;
             var mj_end = 0;
             //满减
             $(this).parents("li").find(".list-item").each(function () {
             //判断是否选中
             if ($(this).find(".selected").length == 1) {
             var num = $(this).find(".goodsNum").val();
             var price = $(this).find(".goodsPrice").val();
             mj_sumprice = accAdd(accMul(num, price), mj_sumprice);
             }
             });


             $(this).parents("li").find(".manjian_reducePrice").each(function () {
             var man = $(this).val().split(",")[0];
             var jian = $(this).val().split(",")[1];
             if (Subtr(mj_sumprice, man) >= 0) {
             mj_end = jian;
             }

             });
             $(this).parents("li").find(".oneprice").val(mj_sumprice);
             $(this).parents("li").find(".youhui").val(mj_end);

             } else if (codexType == '8') {
             //满折
             var mz_sumprice = 0;
             var mz_end = 0;
             $(this).parents("li").find(".list-item").each(function () {
             //判断是否选中
             if ($(this).find(".selected").length == 1) {
             var num = $(this).find(".goodsNum").val();
             var price = $(this).find(".goodsPrice").val();
             mz_sumprice = accAdd(accMul(num, price), mz_sumprice);
             }
             });

             $(this).parents("li").find(".manzhe_fullbuyDiscount").each(function () {
             var man = $(this).val().split(",")[0];
             var zhe = $(this).val().split(",")[1];
             if (Subtr(mz_sumprice, man) >= 0) {
             mz_end = accMul(mz_sumprice, Subtr(1, zhe));
             }

             });
             $(this).parents("li").find(".oneprice").val(mz_sumprice);
             $(this).parents("li").find(".youhui").val(mz_end)

             } else if (codexType == '-1') {
             //-1代表是套装
             var num = $(this).parents(".cart_item").find(".decrement").next().val();
             var xiaoji = 0;
             $(this).parents(".marketgroup").find(".cart_item_goods").each(function () {
             var isprice = accMul($(ojb).find(".oneprice").val(), num);
             xiaoji = accAdd(xiaoji, isprice);
             });
             var groupPreferamount = $(obj).parents(".cart_item").find(".groupPreferamount").val();
             var youhui = accMul(num, groupPreferamount);
             $(this).parents(".marketgroup").find(".xiaoji").val(xiaoji);
             $(this).parents(".marketgroup").find(".youhui").val(youhui);
             }*/
        }
    }
    $("#goodsNum").html(allcount);
    $(".money-all").html(zongji.toFixed(2)+"邮豆");
    //$(".fanxian").html(fanxian);
    //$(".payPrice").html("￥"+Subtr(zongji,fanxian));
}


