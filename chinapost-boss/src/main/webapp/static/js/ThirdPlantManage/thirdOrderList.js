
$(function(){

    /* 订单筛选交互演示用*/
    $('#order_tabs a').click(function () {
        $that = $(this);
        $that.parent().addClass('active');
        $that.parent().siblings().removeClass('active');

        submitOrder();
    });
    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        todayBtn : true,
        viewSelect : 'hour'
    });
    $('.form_date').datetimepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        minView : 2,
        todayBtn : true
    });
});

function submitOrder(){
    if(checkEndTime($("#startTime").val(),$("#endTime").val())){
        $("#statusFlag").val($(".flagli .active").find("a").attr("data-type"));
        $("#thirdOrderForm").submit();
    }else {
        showTipAlert("开始时间不能大于结束时间！");
    }
}

function exportOrderExcel(){
    $("#thirdOrderForm").attr("action","exportOrderExcel");
    submitOrder();
    $("#thirdOrderForm").attr("action","ListPage");
}

/*查看详细*/
function fnOpen(oId,orderSettlePrice) {
    $.ajax({
        type : "POST",
        url:"queryById",
        data : {orderId:oId},
        dataType:'json',
        success:function(data){
            //清空列表
            $("#ordergoods").html("");
            $("#pageorder").html("");
            $("#orderLog").html("");
            //退款日志
            $("#ordertk").html("");
            if(data!="" && data!= null && data != undefined){
                /*等的详情*/
                //订单详细信息
                var order = data;
                //var relations = data.relations;
                if(order!= null){
                    var status = addstatusstr('已下单',order.createTime,1,'active');
                    if(order.payTime != null){
                        status += addstatusstr('已付款',order.payTime,2,'active');
                    }else{
                        status += addstatusstr('已付款',null,2,'');
                    }
                    if(order.sendExpressTime != null || order.getGoodsTime != null){
                        status += addstatusstr('已发货',order.sendExpressTime,3,'active');
                    }else{
                        status += addstatusstr('已发货',null,3,'');
                    }
                    if(order.getGoodsTime != null){
                        status += addstatusstr('已完成',order.getGoodsTime,4,'active');
                    }else{
                        status += addstatusstr('已完成',null,4,'');
                    }
                    $("#detailorderCodeNo").html("订单号："+notNull(order.orderCode));
                    $("#detailorderCode").html("订单编号："+notNull(order.orderCode));
                    $("#detailcreateTime").html("下单时间："+timeObject(order.createTime));
                    if(order.orderCancelTime == null) {
                        $("#detailorederRetime").html('');
                    }else{
                        $("#detailorederRetime").html("订单取消时间："+timeObject(order.orderCancelTime));
                    }
                    //判断状态
                    $("#detailorderStatus").html(orderStatus_handle(order.orderStatus));

                    $("#status").html(status);
                    $("#goodsInfoSettlePriceSpan").html(orderSettlePrice+'元');
                    $("#detailorderPrePrice").html("订单交易金额："+order.orderPrePrice.toFixed(2)+'邮豆');
                    $("#detailorderPrice").html(order.orderPrice.toFixed(2)+'邮豆');
                    if(order.orderCancelRemark == null){
                        $("#detailorederRemark").html("");
                    }else{
                        $("#detailorederRemark").html("取消订单原因："+order.orderCancelRemark);
                    }

                    var relations = order.orderContainerRelations;

                    ////物流信息
                    var relation = "";
                    if(relations != undefined){
                       for(var i=0;i<relations.length;i++){
                           relation+="<div class='col-sm-12'><p>物流公司："+notNull(relations[i].expressName)+"</p></div>"
                           relation+="<div class='col-sm-12'><p>物流单号："+notNull(relations[i].expressNo)+"</p></div>"
                       }
                    }
                    $("#relations").html(relation);
                    //收货信息
                    $("#detailshippingaddress").html("收货地址："+notNull(order.shippingProvince)+notNull(order.shippingCity)+notNull(order.shippingCounty));
                    $("#detailaddress").html("详细地址："+notNull(order.shippingAddress));
                    $("#detailshippingPerson").html("收货人："+notNull(order.shippingPerson));
                    $("#detailshippingPhone").html("联系电话："+notNull(order.customerInfo.contactPhone));
                    $("#detailshippingMobile").html("手机："+notNull(order.shippingMobile));
                    $("#detailshippingPostcode").html("邮编："+notNull(order.shippingPostcode));
                    $("#detailcustomerRemark").html("客户留言："+notNull(order.customerRemark));

                    //卖家备注
                    $("#detailsellerRemark").html("卖家备注："+notNull(order.sellerRemark));
                    /*订单商品信息*/

                    var str ="";
                    $.each(order.orderGoodsList,function(idx,item){
                        str+='<tr id="goodsnum'+idx+'" class="">';
                        str +='<td><div class="data_item"><img alt="" src="'+notNull(item.goodsImg)+'" height="50" >';
                        str += '<p>'+notNull(item.goodsInfoName)+'</p></div>';
                        str += '</td>';
                        str+='<td>'+item.goodsInfoPrice.toFixed(2)+'邮豆</td>';
                        str+='<td>'+item.goodsInfoNum+'</td>';
                        if(item.goodsProductReleSpecVoList!=null) {
                            str += '<td>';
                            for(var s = 0;s<item.goodsProductReleSpecVoList.length;s++){
                                if(item.goodsProductReleSpecVoList[s].spec != undefined){
                                    str+=item.goodsProductReleSpecVoList[s].spec.specName;
                                }
                                str+=':';
                                if(item.goodsProductReleSpecVoList[s].goodsSpecDetail != undefined){
                                    str+=item.goodsProductReleSpecVoList[s].goodsSpecDetail.specDetailName;
                                }
                            }
                            str += '</td>';
                        }
                        if(item.goodsInfoSettlePrice){
                            str +='<td>'+item.goodsInfoSettlePrice.toFixed(2)+'元</td>';
                        }else {
                            str +='<td>0.00</td>';
                        }
                        str +='<td>'+(item.goodsInfoPrice*item.goodsInfoNum).toFixed(2)+'邮豆</td>';
                        if(item.goodsInfoSettlePrice){
                            str +='<td>'+(item.goodsInfoSettlePrice * item.goodsInfoNum).toFixed(2)+'元</td>';
                        }else {
                            str +='<td>0.00元';
                        }
                        str +='</td></tr>';
                    });
                    $("#ordergoods").html(str);
                }
            }
            $('#orderDetails').modal('show');
        }
    });
}
//拼接订单状态显示
function addstatusstr(str,time,num,cla){
    if(time != null){
        return '<li class="'+cla+'">'+
            '<p class="name">'+str+'</p>'+
            '<p class="bar"><i>'+num+'</i></p>'+
            '<p class="time">'+timeObject(time)+'</p>'+
            '</li>';
    }else{
        return '<li class="'+cla+'">'+
            '<p class="name">'+str+'</p>'+
            '<p class="bar"><i>'+num+'</i></p>'+
            '<p class="time"></p>'+
            '</li>';
    }

}

//转换时间格式
function timeObject(obj){
    var date = "/Date(" +notNull(obj)+")/";
    var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
    var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
    return result;
}

//判断数据是否为空为空返回“”
function notNull(obj){
    if(obj != null && obj != undefined){
        return obj;
    }else{
        return "";
    }
}

function searchData(pageNumber){
    $("#page").val(pageNumber);
    submitOrder();
}