/**
 * 列表全选
 * @param obj 多选元素this
 * @param name 字符串
 */ 
function selectAll(obj,name){
	 var a = $("input[name='"+name+"']");
	 if(obj.checked==true){
		  for(var i = 0;i<a.length;i++){
		       a[i].checked = true; 
		   }
	 }else{
		 for(var i = 0;i<a.length;i++){
		     a[i].checked = false;
		    }
	 }
   
  }

/**
 * 检查是否选中行
 * @param obj
 * @returns {boolean}
 */
function checkSelect(obj){
	//判断是否有按钮选中
	var bool = false;
	var brandIds = document.getElementsByName(obj);
	//var checkedBrand = new Array();
	for (var i = 0; i < brandIds.length; i++) {
		var e = brandIds[i];
		if (e.checked == true) {
			bool = true;
			//checkedBrand.push(e.value);
			break;
		}
	}
	return bool;
}

//根据传过来的对象验证是否为数字
function checkNumAndDialog(inputobj){
	if (isNaN($("#"+inputobj).val() )) {
		$("#"+inputobj).next().html("请填写正确格式的数字");
        $("#"+inputobj).next().addClass("error");
		$("#"+inputobj).focus();
		return false;
	}
	else {
		$("#"+inputobj).next().html("");
		return true;
	}
}

//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj){
	var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
	if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).next().addClass("error");
		$("#"+inputobj).next().html("输入的内容包含特殊字符!");
		$("#"+inputobj).focus();
		return false;
	}
	else {
		$("#"+inputobj).next().html("");
		return true;
	}
}
//将订单状态转换为中文
function orderStatus_handle(str){
    if( str == 'REFUND_APPLIED' ){
        return '申请退款';
    }
    if( str == 'SUBMITED'){
        return '待付款';
    }
    if( str == 'PAYED'){
        return '待发货';
    }
    if( str == 'DELIVERED'){
        return '已发货';
    }
    if( str == 'RECIEVED'){
        return '已完成';
    }
    if( str == 'CANCELED'){
        return '已取消';
    }
    if( str == 'WAIT_DELIVER'){
        return '待发货';
    }
    if( str == 'WAIT_PICKUP'){
        return '待提货';
    }
    if( str == 'RETURN_APPLIED'){
        return '退货申请';
    }
    if( str == 'RETURN_AGREED'){
        return '同意退货';
    }
    if( str == 'RETURN_DENIED'){
        return '已拒绝';
    }
    if( str == 'RETURN_DELIVERING'){
        return '待商家收货';
    }
    if( str == 'RETURN_FINISHED'){
        return '退货成功';
    }
    if( str == 'REFUND_APPLIED'){
        return '申请退款';
    }
    if( str == 'REFUND_DENIED'){
        return '拒绝退款';
    }
    if( str == 'RETURN_DELIVER_FAILURE'){
        return '收货失败';
    }
    if( str == 'RETURN_WAIT_DELIVER_INFO'){
        return '待客户填写物流信息';
    }
    if( str == 'RETURN_WAIT_REFUND' ){
        return '退货待退款';
    }
    if( str == 'REFUND_FINISHED' ){
        return '退款完成';
    }
    if( str == 'REFUND_AGREED' ){
        return '同意退款';
    }
    if( str == 'RETURN_WAIT_RECEIVE'){
        return '待商家收货';
    }
    if( str == 'REFUND_WAIT_REFUND'){
        return '待退款';
    }
}

//将退单订单状态转换为中文
function backOrderStatus_handle(str){
    if( str == 'REFUND_APPLIED' ){
        return '审核退款';
    }
    if( str == 'SUBMITED'){
        return '待付款';
    }
    if( str == 'PAYED'){
        return '待发货';
    }
    if( str == 'DELIVERED'){
        return '已发货';
    }
    if( str == 'RECIEVED'){
        return '已完成';
    }
    if( str == 'CANCELED'){
        return '已取消';
    }
    if( str == 'WAIT_DELIVER'){
        return '待发货';
    }
    if( str == 'WAIT_PICKUP'){
        return '待提货';
    }
    if( str == 'RETURN_APPLIED'){
        return '退货申请';
    }
    if( str == 'RETURN_AGREED'){
        return '同意退货';
    }
    if( str == 'RETURN_DENIED'){
        return '拒绝退货';
    }
    if( str == 'RETURN_DELIVERING'){
        return '待商家收货';
    }
    if( str == 'RETURN_FINISHED'){
        return '退货成功';
    }
    if( str == 'REFUND_APPLIED'){
        return '审核退款';
    }
    if( str == 'REFUND_DENIED'){
        return '拒绝退款';
    }
    if( str == 'RETURN_DELIVER_FAILURE'){
        return '收货失败';
    }
    if( str == 'RETURN_WAIT_DELIVER_INFO'){
        return '待客户填写物流信息';
    }
    if( str == 'RETURN_WAIT_REFUND' ){
        return '退货待退款';
    }
    if( str == 'REFUND_FINISHED' ){
        return '退款成功';
    }
    if( str == 'REFUND_AGREED' ){
        return '退款待退款';
    }
    if( str == 'RETURN_WAIT_RECEIVE'){
        return '待商家收货';
    }
    if( str == 'REFUND_WAIT_REFUND'){
        return '退款待退款';
    }
}


//    将标准时间转换为时间戳
function formatDateTime(str) {
    if(str){
        var date = new Date(str);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        var minute = date.getMinutes();
        minute = minute < 10 ? ('0' + minute) : minute;
        var second = date.getSeconds();
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    }else {
        return '';
    }
};
//将退货原因的枚举转换成中文
function backReson_handle(str){
    if( str == 'NOT_WANT_BUY'){
        return '不想买了';
    }
    if( str == 'WRONG_INFORMATION'){
        return '收货人信息有误';
    }
    if( str == 'GOODS_DAMAGE'){
        return '商品损坏';
    }
    if( str == 'GOODS_DESC_ERROR'){
        return '收到商品与描述不符';
    }
    if( str == 'NOT_DELIVERY_TIMED'){
        return '未按指定时间发货';
    }
    if( str == 'OTHER'){
        return '其他';
    }
}

function getDecimal(num){
    if(num){
        return num.toFixed(2);
    }else {
        return "0.00"
    }
}

//
function checkEndTime(startTime,endTime){
    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
    if(end<start){
        return false;
    }
    return true;
}