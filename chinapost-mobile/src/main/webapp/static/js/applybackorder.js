var basePath = $("#basePath").val();
var backPrice=$("#backorderprice").attr("placeholder");
var content;

$(function(){
    var flagBool = true;
	/*退款*/
    $("#submitButtonprice").click(function() {
        if(flagBool){
            if($(".flag_saved").val()=="1"){
                return false;
            }else {
                $(".backReasonContent").text("");
                $(".applyCredentialsContent").text("");
                $(".backPriceContent").text("");
                $(".backRemarkContent").text("");
                $(".uploadDocumentContent").text("");
                var backReason = $("#backReasons").text();
                if (backReason == '请选择') {
                    content = '请选择退单原因！';
                    promptPopup(content);
                    $(".backReasonContent").text(content);
                    return;
                }

                var backRemark = $("#backRemark").val();
                if (backRemark == '') {
                    content = '问题说明不能为空！';
                    promptPopup(content);
                    $(".backRemarkContent").text(content);
                    return;
                }
                //$(".upload_pics").attr("order-id")
                var showPics = $(".share" + $(".upload_pics").attr("order-id"));
                var imgs = '';
                for (var i = 0; i < showPics.length; i++) {
                    imgs += $(showPics[i]).val() + ",";
                }

                if ($("#backPrice").val() == '') {
                    $("#backPrice").attr("value", $(".back_price").val());
                }
                $(".flag_saved").val("1");
                $(".upload_documents").attr("value", imgs);
                $("#upload-form").removeAttr("target");
                $("#upload-form").attr("action", basePath+"/back/keepBackOrderInfoprice.htm");
                flagBool = false;
                var reason="";
                switch(backReason){
                    case"不想买了":
                        reason = "NOT_WANT_BUY";
                        break;
                    case "收货人信息有误":
                        reason = "WRONG_INFORMATION";
                        break;
                    case "未按指定时间发货":
                        reason = "NOT_DELIVERY_TIMED";
                        break;
                    case "其他":
                        reason = "OTHER"
                        break;
                }

                $.post('submitApplyBackMoney',{orderNo:$("#orderCode").val(),credentialDoc:"",reason:reason,remark:$("#backRemark").val()},function(data){
                    if(data.response == "success"){
                        window.location.href = basePath+"/myorder?type=6";
                    }else {
                        //错误信息
                        alertStr(data.data.text);
                    }
                });
            }
        }
    });
    //退货
    $("#submitButtongoods").click(function() {
        if(flagBool){
            if($(".flag_saved").val()=="1"){
                return false;
            }else {
                checkNum();
                if(flag >0){
                    return ;
                }
                $(".backReasonContent").text("");
                $(".applyCredentialsContent").text("");
                $(".backPriceContent").text("");
                $(".backRemarkContent").text("");
                $(".uploadDocumentContent").text("");
                var backReason = $("#backReasons").text();
                if (backReason == '请选择' ||backReason == '' ) {
                    content = '请选择退单原因！';
                    promptPopup(content);
                    $(".backReasonContent").text(content);
                    return;
                }

                var backRemark = $("#backRemark").val();
                if (backRemark == '') {
                    content = '问题说明不能为空！';
                    promptPopup(content);
                    $(".backRemarkContent").text(content);
                    return;
                }
                var showPics = $(".share" + $(".upload_pics").attr("order-id"));
                var imgs = '';
                for (var i = 0; i < showPics.length; i++) {
                    imgs += $(showPics[i]).val() + ",";
                }
                //退货商品
                var goods = $(".back_goods_id");
                var hasgoods = 0;
                //保存要退的商品Id
                var backGoodsId_sum = '';
                if(goods != null && goods.length > 0){
                    for(var i = 0;i<goods.length;i++){
                        if($(goods[i]).val() !=0){
                            hasgoods = 1;
                            backGoodsId_sum += $(goods[i]).attr("attr-goods")+","+$(goods[i]).val()+"-";
                            $("#upload-form").append("<input type='hidden' name='goodsInfoIds' value=" + $(goods[i]).attr("attr-goods") + ">");
                        }
                    }
                }
                $("#backGoodsId").val(backGoodsId_sum);
                if(hasgoods == 0){
                    content = '请选择退货商品！';
                    promptPopup(content);
                    $(".backGoodsContent").text(content);
                    return;
                }


                if ($("#backPrice").val() == '') {
                    $("#backPrice").attr("value", $(".back_price").val());
                }

                var backway = $("div.returen-way").children(".selected").attr("backway");

                $(".flag_saved").val("1");
                $(".upload_documents").attr("value", imgs);
                $("#upload-form").removeAttr("target");
                flagBool = false;
                var reason="";
                switch(backReason){
                    case"不想买了":
                        reason = "NOT_WANT_BUY";
                        break;
                    case "商品质量问题":
                        reason = "GOODS_DAMAGE";
                        break;
                    case "收到商品与描述不符":
                        reason = "GOODS_DESC_ERROR";
                        break;
                    case "其他":
                        reason = "OTHER";
                        break;
                }
                //申请凭据
                var credential = "";
                switch($("#applyCredentials").val()){
                    case"1":
                        credential = "INVOICE";
                        break;
                    case "2":
                        credential = "QUALITY_REPORT";
                        break;
                    case "3":
                        credential = "NO_CREDENTIAL";
                        break;
                }
                var goodsinfoId=$(".list-item").find(".goodsId");
                var back_goods_id = $(".back_goods_id");
                var backGoods="{";
                var num = 0;
                for(var i=0;i<goodsinfoId.length;i++){
                    num++;
                    if(back_goods_id.eq(i)==0){
                        promptPopup(请选择退货数量);
                    }else{
                        if(num == goodsinfoId.length){
                            backGoods+="'"+goodsinfoId.eq(i).val()+"':"+back_goods_id.eq(i).val();
                        }else {
                            backGoods+="'"+goodsinfoId.eq(i).val()+"':"+back_goods_id.eq(i).val()+",";
                        }
                    }
                }
                backGoods += '}';
                $.post('submitApplyBackGoods',{
                    backGoodsId:backGoods,
                    orderNo:$("#orderCode").val(),
                    credentialDoc:$(".upload_documents").val(),
                    reason:reason,
                    credential:credential,
                    remark:$("#backRemark").val(),
                    backWay:backway
                },
                    function(data){
                        if(data.response == "success"){
                            window.location.href = basePath+"/myorder?type=6";
                        }else {
                            //错误信息
                            alertStr(data.data.text);
                        }
                },'json');
            }
        }
    });
    
    $(".noevidence").click(function(){
    	$(this).addClass("checked");
    	$("#applyCredentials").val(3);
    	$(".bill").attr("disabled", "disabled");
    	$(".report").attr("disabled", "disabled");
    	$(".bill").removeClass("checked");
    	$(".report").removeClass("checked");
    	$("#pubevidence").hide();
    });
    $(".bill").click(function(){
    	$(this).addClass("checked");
    	$("#applyCredentials").val(1);
    	$(".noevidence").attr("disabled", "disabled");
    	$(".report").attr("disabled", "disabled");
    	$(".noevidence").removeClass("checked");
    	$(".report").removeClass("checked");
    	$("#pubevidence").show();
    });
    $(".report").click(function(){
    	$(this).addClass("checked");
    	$("#applyCredentials").val(2);
    	$(".bill").attr("disabled", "disabled");
    	$(".noevidence").attr("disabled", "disabled");
    	$(".bill").removeClass("checked");
    	$(".noevidence").removeClass("checked");
    	$("#pubevidence").show();
    });
    
    //上传凭证
    $(".upload_pics").change(function() {
        if($(this).val()!=""){
            var orderId = $(this).attr("order-id");
            var shareImg = $(".share" + orderId);
            var waitImg = $("#wait_img"+orderId);
            gid = orderId;
            if(waitImg.length>0) return;
            if(shareImg.length==3) {
                $("#err_img_"+orderId).show();
                setTimeout(function(){
                    $("#err_img_"+orderId).hide();
                },3000);
                return;
            }
            $("#upload-form").attr("action",basePath+"/back/upload.htm");
            $("#upload-form").submit();
            $("#show_pics"+orderId).find("li:last").before('<li id="wait_img'+orderId+'"><a href="javascript:void(0)"><image src="'+basePath+'/static/img/load.jpg"/></a></li>');
        }
    });
});

//退单金额的价格
var back_price = 0;
//保存要退的商品Id
var backGoodsId_sum = 0;

function mit(obj){
    if( !$(obj).hasClass("disabled")) {
        var minNum = 0;
        var num = 0;
        var nowNum = $(obj).next().val();
        var goodsprice = $(obj).next().attr("attr-price");
        var setNum = 0;
        if (nowNum > minNum) {
            setNum = Subtr(nowNum, 1);
            $(obj).parent().find(".add").removeClass("disabled");
        } else {
            setNum = nowNum;
        }
        $(obj).next().val(setNum);
        if (setNum == 0) {
            $(obj).addClass("disabled");
        }
        num = Subtr(nowNum, setNum);
        var subprice = accMul(goodsprice, num);
        var newprice = Subtr(backPrice, subprice);
        $("#backorderprice").attr("placeholder", newprice);
        backPrice = newprice;
        $("#backPrice").val(backPrice);
    }
}


function add(obj){
    if( !$(obj).hasClass("disabled")) {
        //获取 可用的最大数量
        var maxNum = $(obj).prev().attr("attr-maxnum");
        var goodsprice = $(obj).prev().attr("attr-price");
        var nowNum = $(obj).prev().val();
       var setNum = 0;
        if (Subtr(maxNum, nowNum) <= 0) {
            setNum = nowNum;
        } else {
            setNum = accAddInt(nowNum, 1);
            $(obj).parent().find(".reduce").removeClass("disabled");
        }newprice
        $(obj).prev().val(setNum);
        if (setNum == maxNum) {
            $(obj).addClass("disabled");
        }
        var addprice = accMul(goodsprice, 1);
        var newprice = accAdd(backPrice, addprice);
        $("#backorderprice").attr("placeholder", newprice);
        backPrice = newprice;
        $("#backPrice").val(backPrice);
    }
}


/**
 * 上传文件成功后触发事件
 * @param msg
 */
function callback(msg) {
    //上传失败
    if(msg.split(",").length<2) {
        if(msg=='101') {
            $("#wait_img"+gid).remove();
            dia(5);
        } else if(msg=='102') {
            $("#wait_img"+gid).remove();
            dia(4);

        }
        return;
    }
    //上传成功
    var imageName = msg.split(",")[0],newImg ="";
    var orderId = msg.split(",")[1];
    $("#wait_img"+orderId).remove();
    if(imageName.indexOf("!")!= -1){
        newImg = imageName.substring(0,imageName.indexOf("!")+1)+56;
    }else{
        newImg = imageName;
    }
    $("#show_pics"+orderId).find("li:last").before('<li class="share_img"><input class="share'+orderId+'" type="hidden" value="'+imageName+'" ></input><span style="display:none;" class="del-img"><a href="javascript:;"  onclick="show_delPic('+orderId+');">x</a></span><img id="share'+orderId+'"  width="68px" height="68px" alt="" src="'+newImg+'" /></li>');
    $(".share_img").click(function() {
        $(".share_img").removeClass("selected");
        $(this).addClass("selected");
        $(this).find(".del-img").show();
    });
}

function show_delPic(orderId){
	var shareImg = $(".share"+orderId);
    $(".upload_pics").val("");
    for(var i=0; i<shareImg.length; i++) {
        if(shareImg[i].parentElement.className.indexOf("selected")>0) {
            shareImg[i].parentElement.remove();
        }
    }
}
var  flag =0;
function checkNum(){
    var reg = new RegExp("^[0-9]*$");
    var staCheck = $("#staCheck").val();
    backPrice =0;
    $('input[class="back_goods_id"]').each(function(){
        if(reg.test(parseInt($(this).val()))){
            $(this).parent().next(".numError").text("");
            if(parseInt($(this).val())>parseInt($(this).attr("attr-maxnum"))){
                $(this).parent().next(".numError").text("您输入的数量有误！");
                flag = 1;
            }else{
                var goodsprice = $(this).attr("attr-price");
                var num =parseInt($(this).val());
                var addprice = accMul(goodsprice, num);
                var newprice = accAdd(backPrice, addprice);
                if(staCheck =='' || staCheck !=1){
                    $("#backorderprice").attr("placeholder", newprice);
                    backPrice = newprice;
                    $("#backPrice").val(backPrice);
                }
            }
        }else{
            $(this).parent().next(".numError").text("您输入的数量有误！");
           flag = 1;
        }
    });
}

function promptPopup(content){
    var promptPopup = dialog({
        width : 260,
        content : '<p class="tc">提示</p><p class="tc"><span class="payPrice text-them">'+content+'</span></p> ',
        okValue : '确定',
        cancelValue : '返回',
        ok : function(){
            flagBool = true;
        }
    });
    promptPopup.showModal();
}

//弹出提示
function alertStr(str){
    $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>'+str+'</h3></div></div>');
    setTimeout(function(){
        $('.tip-box').remove();
    },1000);
}
