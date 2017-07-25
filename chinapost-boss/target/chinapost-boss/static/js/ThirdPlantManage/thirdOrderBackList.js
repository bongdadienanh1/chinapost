$(function(){

    /* 订单筛选交互演示用*/
    $('#order_tabs a').click(function () {
        $that = $(this);
        $that.parent().addClass('active');
        $that.parent().siblings().removeClass('active');
        //var _cla = $that.attr("data-type")
        //
        //$("#statusflag").val($(".flagli .active").find("a").attr("data-type"));
        //submitOrder();
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
/*查看详细*/
//function fnOpen(backOrderCode) {
//    $.ajax({
//        type : "POST",
//        url:"queryById",
//        data : {backOrderCode:backOrderCode},
//        dataType:'json',
//        success:function(data){
//            console.log(data)
//        }
//    });
//}
function fnOpen(backOrderCode) {
    $('#goodsInfoAlert').html('');
    $.ajax({
        type : "POST",
        url:"queryById",
        data : {backOrderCode:backOrderCode},
        success:function(data){
            console.log(data)
            //清空列表
            /$("#ordertk").html("");
            $('#detailorderCodeNo').html('');
            $('#backGoodsReason').html('')//退货原因
            $('#requestReason').html('')//申请原因
            $('#backPrice').html('');//退款金额
            $('#problemExplain').html('');//问题说明
            $('#uploadReason').html('')//上传凭证

            $(".logistics").find("p").html("");
            $(".track-info").html("");
            //退款日志
            $("#ordertk").html("");
            $('#detailorderCodeNo').html('退单号：'+ data.backOrderCode);
            $('#backGoodsReason').html(backReson_handle(data.backReason))//退货原因
            $('#requestReason').html()//申请原因
            $('#backPrice').html(data.backPrice+'.00邮豆');//退款金额
            $('#problemExplain').html(data.backRemark);//问题说明
            $('#uploadReason').html(data.uploadDocuments)//上传凭证
            $('#goodsBackWay').html("物流配送")//商品返回方式

            $('#detailshippingaddress').html()//快递单号
            $('#detailshippingaddress').html('收货地址：'+data.order.shippingProvince +data.order.shippingCity + data.order.shippingCounty)//收货地址
            $('#deliveryCompany').html()//快递公司
            $('#detailshippingPerson').html('收货人：'+data.backCollectPerson)//收货人
            $('#detailaddress').html('详细地址：'+data.backCollectAddress)//详细地址
            $('#detailshippingMobile').html('手机：'+data.backCollectPhone)//手机
            //$('#detailcustomerRemark').html('客户留言：'+data.backRemark)//客户留言
            if(typeof (data.logisticses[0]) != "undefined"){
                $('#backDeliveryCode').html('退货快递单号：'+data.logisticses[0].npLogisticsNo)//退货快递单号
                $('#backDeliveryCompany').html('退货快递公司：'+data.logisticses[0].npLogisticsName)//退货快递公司
            }
            for(var i = 0; i < data.backOrderGoods.length; i++){
                var html = '';
                html += '<tr>'
                +'<td class="text-center"><img style="width:30px;height:30px;" src="'+ data.backOrderGoods[i].goodsInfoImage +'"/></td>'
                +'<td class="text-center">'+ data.backOrderGoods[i].goodsInfoItemNo +'</td>'
                +'<td class="text-center">'+ data.backOrderGoods[i].goodsPrice+'.00邮豆</td>'
                +'<td class="text-center">'+ data.backOrderGoods[i].goodsNum +'</td>'
                +'<td class="text-center">'+ data.backOrderGoods[i].goodsPrice * data.backOrderGoods[i].goodsNum +'.00邮豆</td>'
                +'</tr>'
                $('#goodsInfoAlert').append(html);
            }
            //操作日志
            $.each(data.logs,function(){
                $(".track-info").append('<p><span class="track-date">操作时间：'+formatDateTime(this.createTime)+'</span><i></i><span class="track-cont">操作内容：'+this.logStr+'</span></p>');
            });
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


function submitBcakOrder(){
    if(checkEndTime($("#startTime").val(),$("#endTime").val())){
        $("#thirdBackOrderForm").submit();
    }else {
        showTipAlert("开始时间不能大于结束时间！");
    }
}

function exportBackOrderExcel() {
    $("#thirdBackOrderForm").attr("action","exportBackOrderExcel");
    submitBcakOrder();
    $("#thirdBackOrderForm").attr("action","ListPage");
}

function searchData(pageNumber){
    $("#page").val(pageNumber);
    $("#thirdBackOrderForm").submit();
}

function closeDetail(){
    $('#orderDetails').modal('hide');
}