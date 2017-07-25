/**
 * Created by Raby Lee on 2015/1/9.
 */
$(function(){

 
    /* 导航弹出菜单 */

    /* 页面功能简单提示说明 */
    $('.jfsz').popover({
        html : true,
        placement : 'auto top',
        content : '系统管理中包含对系统的整体设置',
        trigger : 'hover'
    });
    /* 页面功能简单提示说明 END */

    /*******************************************
     *
     * 以下是为配合云销系统修改的新版增加内容
     *
     * *****************************************/

    /* 顶部右侧导航鼠标悬停显示更多内容 */
    $('.ctrl_nav li').click(function(){
        var $that = $(this);
        var $outbox = $(this).find('.popover');
        $outbox.show().css({
            'left' : -($outbox.width()/2) + ($that.width()/2) + 'px',
            'top' : '14px'
        });
    });
    $('.ctrl_nav li').mouseleave(function(){
        var $outbox = $(this).find('.popover');
        setTimeout(function(){
            $outbox.hide();
        },200);
    });

    /* 侧边栏手风琴效果 */
    $('.menu_title').click(function(){
        $(this).next().slideToggle('fast');
    });
    /* 侧边栏手风琴效果 END */

    /* 为了自适应页面，协调相关内容的宽高 */
    $('.main_cont').css('height',$(window).height() - 84 + 'px');
    $('.sidebar').css('height',($(window).height() - 84) + 'px');
    $(window).resize(function(){
        $('.main_cont').css('hight',$(window).height() - 84 + 'px');
        $('.sidebar').css('height',($(window).height() - 84) + 'px');
    });

    /* 为了自适应页面，协调相关内容的宽高 END */

    /*******************************************
     *
     * 以上是为配合云销系统修改的新版增加内容
     *
     * *****************************************/



});
function getBrowserInfo(){//判断浏览器及版本
    var agent = navigator.userAgent.toLowerCase() ;
    var regStr_ie = /msie [\d.]+;/gi ;
    var regStr_ff = /firefox\/[\d.]+/gi;
    var regStr_chrome = /chrome\/[\d.]+/gi ;
    var regStr_saf = /safari\/[\d.]+/gi ;

    if(agent.indexOf("msie") > 0){//IE
        return agent.match(regStr_ie) ;
    }
    if(agent.indexOf("firefox") > 0){//firefox
        return agent.match(regStr_ff) ;
    }
    if(agent.indexOf("chrome") > 0){//Chrome
        return agent.match(regStr_chrome) ;
    }
    if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0){//Safari
        return agent.match(regStr_saf) ;
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
        return '待收货';
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
    var date = new Date(str);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    return y + '-' + m + '-' + d+' '+h+':'+minute;
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

//比较时间大小
function checkEndTime(startTime,endTime){
    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
    if(end<start){
        return false;
    }
    return true;
}