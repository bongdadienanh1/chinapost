
/*
 * 更改页数
 */
function changePageNo(obj){
    $(".searchThirdOrderList").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
}

function modifyThirdbkorderbyId(orderId,sta){
    $(".up_order_sta").val(orderId);
    var options = $(".old_order_sta").find("option");
    for(var i =0;i<options.length;i++){
        if($(options[i]).val()==sta){
            $(options[i]).prop("selected","selected");
            break;
        }
    }
    dia(4);
}

//查询状态改变
function changTbl(obj){
    resetThirdOrder();
    $(".tabStatus").prop("value",obj);
    $(".searchThirdOrderList").submit();
}
//根据条件查询订单信息
function queryOrderList(){
    $(".searchThirdOrderList").attr("action","queryThirdBackOrderList.html");
    $('.searchThirdOrderList').submit();
}

//提交表单
function subThirdOrderSta(){
    $(".up_third_back_order_sta").submit();
}

$(function(){
    //隐藏审核提示
    $('.backOrderOption').hide();
    if($(".tabStatus").val()==1){
        $(".tb_backOrderr").addClass("cur");
    }

    $("#up_backCheck").change(function(){
        var val = $(this).children('option:selected').val();
        if(val == '-1')
            $("#hid_backCheck").val("");
        else if(val == "")
            $("#hid_backCheck").val(backCheck);
        else
            $("#hid_backCheck").val($(this).children('option:selected').val());
    });
    var backCheck =  $("#hid_backCheck").val();
    $("#up_backCheck option").each(function(){
        if($(this).val() == backCheck){
            $(this).val("");
        }
    });
});

//取消订单
function delBackOrder(backOrderId){
    $('#quedingbackOrderId').val(backOrderId);
}


//取消订单 对话框 确定
function deleteOrder(){
    $('.up_delete_order_sta').submit();
}


//订单审核
function fnModifyBackOrder(backOrderId){
    $('#updateOrderCheck').val(backOrderId);
}

//退款审核
function fnModifyBackOrderMoney(backOrderId,mallId,backPrice){
    var selectStr = '';
    $('#returnMoneyId').val(backOrderId);
    if(mallId==0){
        selectStr+='<option value="10">同意退款</option>';
        selectStr+='<option value="7">拒绝退款</option>';
    }else{
        selectStr+='<option value="12">同意退款</option>';
        selectStr+='<option value="7">拒绝退款</option>';
    }
    $("#backCheckMoney").html(selectStr);
    if(typeof (backPrice) != "undefined"){
        $(".backPriceBox").css("display","inline-block");
        $(".backPrice").html('￥'+(backPrice!=""?backPrice:0));
    }else {
        $(".backPriceBox").css("display","none")
    }
}
//确认收货
function getGoods(backOrderId,mallId){
    $('#backOrderId').val(backOrderId);
    var mallRemark = '';
    if(mallId!=0){
        mallRemark+='<textarea style="width: 100%" class="required" name="mallRemark" placeholder="商家备注..." rows="5" maxlength="255"></textarea>'
    }
    $(".mallRemark").html(mallRemark);
}
//确认收货 确定按钮
function querenshouhuo(){
    if(!$(".up_queren_order_sta").valid()) return;
    $('.up_queren_order_sta').submit();
}

//审核退款确认按钮
function returnMoneySub(){
    $(".up_third_backOrder_money").submit();
}


//更改对话框确定按钮
function subBackOrder(){
    $(".up_third_order_sta").submit();
}




//更新订单状态信息
function upOrderSta(orderId,sta){
    $(".ordersta").removeClass("none");
    $(".up_order_sta").val(orderId);
    var options = $(".old_order_sta").find("option");
    for(var i =0;i<options.length;i++){
        if($(options[i]).val()==sta){
            $(options[i]).prop("selected","selected");
            break;
        }
    }
    //如果是未付款
    if(sta==0){
        $(".option1").addClass("none");
        $(".option2").addClass("none");
        $(".option0").addClass("none");
        $(".option3").addClass("none");
    }else if(sta==1){
        $(".option0").addClass("none");
        $(".option1").addClass("none");
        $(".option3").addClass("none");
        $(".option4").addClass("none");
        $(".option6").addClass("none");
    }else if(sta==2){
        $(".option0").addClass("none");
        $(".option1").addClass("none");
        $(".option4").addClass("none");
        $(".option6").addClass("none");
    }else if(sta==3){
        $(".option0").addClass("none");
        $(".option1").addClass("none");
        $(".option2").addClass("none");
        $(".option4").addClass("none");
        $(".option6").addClass("none");
    }else if(sta == 4){
        $(".option0").addClass("none");
        $(".option1").addClass("none");
        $(".option2").addClass("none");
        $(".option3").addClass("none");
        $(".option6").addClass("none");
    }
    else if(sta == 13){
        $(".option1").addClass("none");
        $(".option2").addClass("none");
        $(".option0").addClass("none");
        $(".option3").addClass("none");
    }
    dia(4);
}

//导出第三方订单
function exportBackOrder(){
    if($('#startTime').val() == "" && $('#endTime').val() == "" &&
        $("#backOrderCode").val() == "" && $('#orderCode').val() == "" && $('#shippingMobile').val() == ""){
        $('#hint').modal("show");
    }else{
        $(".searchThirdOrderList").attr("action","exportBackOrder.htm");
        $(".searchThirdOrderList").submit();
    }
}






