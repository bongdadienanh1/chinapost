var pageNo=1;
var iIntervalId = null;
var basePath = $("#basePath").val();
var type = $("#type").val();
$(function(){
    window.onload=function(){
        $("#status").val(0);
    }
});
function show(){
    if($("#status").val()==1){
        return;
    }
	 pageNo++;
  $.ajax({
    url: basePath+'/allmyorder?pageNo='+pageNo+'&type='+type,
    type: 'GET',
    dataType: 'text',
    async:'false',
	beforeSend: showLoadingImg,
    error: showFailure,
    success:showResponse
  });
}

function showLoadingImg() {
	 $('#showmore').html(' <img src='+basePath+'/static/img/loading.gif /><span>加载中……</span>');
	 $('#showmore').show();
}

function showFailure() {
	 $('#showmore').html('<font color=red> 获取查询数据出错 </font>');
}
//根据ajax取出来的json数据转换成html
function showResponse(responseData) {
		 var returnjson = eval("("+responseData+")");
		 var nextpagehtml = ''; 
		 if(returnjson.data.pb.list !=null && returnjson.data.pb.list.length >0){
			 for(var i=0; i<returnjson.data.pb.list.length; i++) {
				 var order =  returnjson.data.pb.list[i];
				 nextpagehtml +='<div class="order-item">';
				 nextpagehtml +='<div class="order-number">';
				 nextpagehtml +='<div class="list-item">';
				 nextpagehtml +='<h3 class="item-head"><label for="">订单号：</label><span>';
				 nextpagehtml +=''+order.orderNo+'';
				 nextpagehtml +='</span>';
				 var cFlag = 0;
				 for(var j=0;j<order.goods.length;j++){
					 var good = order.goods[j];
					 if(good.evaluateFlag=="0"){
						 cFlag = cFlag +1;
					 }
				 }
				 nextpagehtml +='<span class="curValue text-them">';
				 if(order.orderStatus != null && order.orderStatus != ''){
					 if(order.orderStatus == "SUBMITED"){
						 if(order.orderLinePay=="0"){
							 nextpagehtml +='待发货';
						 }else{
							   nextpagehtml +='待付款';
						 }
					 }else if(order.orderStatus=="PAYED"){
                         if(order.selfPick){
                             nextpagehtml +='待提货';
                         }else{
                             nextpagehtml +='待发货';
                         }
					 }else if(order.orderStatus=="DELIVERED") {
                         nextpagehtml += '待收货';
                     }else if(order.orderStatus=="RECIEVED"){
						 nextpagehtml +='已完成';
					 //}else if(order.orderStatus=="3" ){
						// if(cFlag > 0){
						//	 nextpagehtml +='等待评价';
						// }else if(cFlag == 0){
						//	 nextpagehtml +='交易完成';
						// }
					 }else if(order.orderStatus=="CANCELED"){
						 nextpagehtml +='已取消';
					 }else if(order.orderStatus=="RETURN_APPLIED"){
						 nextpagehtml +='退货审核';
					 }else if(order.orderStatus=="RETURN_WAIT_DELIVER_INFO"){
						 nextpagehtml +='待填写物流信息';
					 }else if(order.orderStatus=="RETURN_DENIED"){
						 nextpagehtml +='拒绝退货';
					 }else if(order.orderStatus=="RETURN_WAIT_RECEIVE"){
						 nextpagehtml +='待商家收货';
					 }else if(order.orderStatus=="RETURN_DELIVER_FAILURE"){
						 nextpagehtml +='商家拒绝收货';
					 }else if(order.orderStatus=="RETURN_WAIT_REFUND"){
						 nextpagehtml +='待退款';
					 }else if(order.orderStatus=="REFUND_DENIED"){
						 nextpagehtml +='拒绝退款';
					 }else if(order.orderStatus=="REFUND_APPLIED"){
						 nextpagehtml +='退款审核';
					 }else if(order.orderStatus=="REFUND_WAIT_REFUND"){
						 nextpagehtml +=' 待退款';
					 }else if(order.orderStatus=="REFUND_FINISHED"){
						 nextpagehtml +='退款成功';
					 }else if(order.orderStatus=="RETURN_FINISHED"){
                         nextpagehtml +='退货成功';
                     }
				 }
				 nextpagehtml +='</span></h3>';
				 nextpagehtml +='</div></div>';
				 nextpagehtml +='<div class="order-info">';
                 if(order.goods.length > 2){
                    nextpagehtml +='<div class="list-body-box">';
					 nextpagehtml +='<div class="list-item">';
					 nextpagehtml +='<div class="box-body">';
					 nextpagehtml +='<ul>';
					 for(var n=0;n<order.goods.length;n++){
						 var good = order.goods[n];
						 nextpagehtml +='<li>';
						 nextpagehtml +='<a href="'+basePath+'/orderdetails?orderId='+order.orderId+'">';
						 nextpagehtml +='<img width="78" height="78" title="'+good.goodsName+'" alt="'+good.goodsName+'" src="'+good.goodsImg+'" />';
						 nextpagehtml +='</a>';
						 nextpagehtml +='</li>';
					 }
					 nextpagehtml +='</ul>';
					 nextpagehtml +='</div>';
					 nextpagehtml +='</div>';
					 nextpagehtml +='</div>';
				 }else{
					 for(var m=0;m<order.goods.length;m++){
						 var good = order.goods[m];
                         nextpagehtml +='<div class="list-body-line">';
						 nextpagehtml +='<div class="list-item">';
						 nextpagehtml +='<a href="'+basePath+'/orderdetails?orderId='+order.orderId+'">';
						 nextpagehtml +='<div class="pro-item" style="border-bottom: none;">';
						 nextpagehtml +='<div class="propic">';
						 nextpagehtml +='<img width="78" height="78" title="'+good.goodsName+'" alt="'+good.goodsName+'" src="'+good.goodsImg+'" />';
						 nextpagehtml +='</div>';
						 nextpagehtml +='<div class="prodesc">';
						 nextpagehtml +=' <h3 class="title">';
						 nextpagehtml +=''+good.goodsName+'';
						 nextpagehtml +='</h3>';
						 nextpagehtml +='<p class="price" style="color: #333;"><span class="num">';
						 nextpagehtml +=''+good.goodsPrice+'';
						 nextpagehtml +='</span>邮豆</p>';
						 nextpagehtml +='<span class="pro-num">x';
						 nextpagehtml +=''+good.goodsNum+'';
						 nextpagehtml +='</span>';
						 nextpagehtml +=' </div>';
						 nextpagehtml +=' </div>';
						 nextpagehtml +='</a>';
						 nextpagehtml +='</div>';
						 nextpagehtml +='</div>';
					 }
				 }
				 nextpagehtml +='</div>';
				 nextpagehtml +='<div class="order-bottom">';
				 nextpagehtml +='<div class="list-item">';
				 nextpagehtml +='<h3 class="item-head">';
				 nextpagehtml +='<label for="" style="vertical-align: middle;">实付款：</label><span class="pay-money" style="color: #333;">';
				 var moneyPaid = 0;
				 if(order.moneyPaid != null && order.moneyPaid != ''){
					 moneyPaid = order.moneyPaid;
				 }
				 nextpagehtml +=''+moneyPaid+'';
				 nextpagehtml +='邮豆</span>';
				 nextpagehtml +='</h3>';
				 nextpagehtml +='<div class="too-btn">';
				 if(order.orderStatus != null && order.orderStatus !=''){
					 if(order.orderStatus == "SUBMITED"){
						 //if(order.payId==1){
							 nextpagehtml +='<a href="javascript:void(0)" class="btn pay-btn" onclick="payOrder('+order.orderNo+')">立即支付</a>';
						 //}
						 nextpagehtml +='<a href="javascript:;" class="btn-grey" onClick="cancelOrder('+order.orderNo+')">取消订单</a>';
					 }else if(order.orderStatus=="DELIVERED"){
                         if(order.expressno!=null && order.expressno.length>0){
                             if(order.expressno[0].expressName == '邮政小包'){
                                 nextpagehtml += '<a href="http://m.kuaidi100.com/index_all.html?type=邮政国内&postid='+order.expressno[0].expressNo +'&callbackurl='+basePath+'/myorder?type=3" class="btn">查看物流</a>';
                             }else{
                                 nextpagehtml += '<a href="http://m.kuaidi100.com/index_all.html?type='+order.expressno[0].expressName+'&postid='+order.expressno[0].expressNo +'&callbackurl='+basePath+'/myorder?type=3" class="btn">查看物流</a>';
                             }
                         }
						 nextpagehtml +='<a href="javascript:;" class="btn" onClick="comfirmgoods('+order.orderNo+')">确认收货</a>';
					 }else if(order.orderStatus=="RECIEVED"){
						 nextpagehtml +='<a class="btn-grey"  href="'+basePath+'/applyBackGoods?orderCode='+order.orderNo+'">申请退货</a>';
					 }else if(order.orderStatus=="PAYED"){
						 if(order.selfPick){
							 nextpagehtml +='<a class="btn-grey" href="'+basePath+'/applyBackMoney?orderCode='+order.orderNo+'">申请退款</a>';
							 nextpagehtml +='<a class="btn" href="javascript:void(0)" onclick="selfPick('+order.orderNo+')">查看提货码</a>';
						 }else {
							 nextpagehtml +='<a class="btn-grey" href="'+basePath+'/applyBackMoney?orderCode='+order.orderNo+'">申请退款</a><br/>';
						 }
					 }
                     //if(returnjson.isBackOrder == '1'){
                     //    if(order.orderStatus=="RECIEVED"){
                     //        nextpagehtml +='<a href="'+basePath+'/comment-'+order.orderId+'.html" class=';
                     //        if( cFlag > 0){
                     //            nextpagehtml +='"btn">评价</a>';
                     //        }else if(cFlag == 0){
                     //            nextpagehtml +='"btn-grey">评价</a>';
                     //        }
                     //      /*  nextpagehtml +='<a href="javascript:void(0)" class="btn" onclick="addCar('+order.orderId+')">再次购买</a>';*/
                     //        nextpagehtml +='<a class="btn"  href="'+basePath+'/customer/applybackgoods-'+order.orderId+'.html">申请退货</a>';
                     //    }else if(order.orderStatus=="1" || order.orderStatus=="4" || order.orderStatus=="5" || order.orderStatus=="6"){
                     //       /* nextpagehtml +='<a href="javascript:void(0)" class="btn" onclick="addCar('+order.orderId+')">再次购买</a>';*/
                     //    }else if(order.orderStatus=="13" ||order.orderStatus=="15" ||order.orderStatus=="18"){
                     //        nextpagehtml +='<a class="btn" href="'+basePath+'/customer/backorderpricedetail-'+order.orderId+'.html">退款详情</a><br/>';
                     //    }else if(order.orderStatus=="8"){
                     //        nextpagehtml +='<a class="btn" href="javascript:void(0)" onclick="expressInfo('+order.orderNo+')" style="width:100px">填写物流信息</a><br/>';
                     //    }
                         //else if(order.orderStatus=="7" ||
                         //    order.orderStatus=="8" ||
                         //    order.orderStatus=="9" ||
                         //    order.orderStatus=="10" ||
                         //    order.orderStatus=="11" ||
                         //    order.orderStatus=="14" ||
                         //    order.orderStatus=="16" ||
                         //    order.orderStatus=="17" ||
                         //    order.orderStatus=="12"){
                         //    nextpagehtml +='<a class="btn" href="'+basePath+'/customer/backordergoodsdetail-'+order.orderId+'.html">退货详情</a><br/>';
                         //}
                         //if(order.orderStatus=="5" || order.orderStatus=="6"){
                         //    nextpagehtml +='<a class="btn" href="'+basePath+'/customer/applybackgoods-'+order.orderId+'.html">申请退货</a><br/>';
                         //}
                         //if(order.orderStatus=="1"){
                         //    nextpagehtml +='<a class="btn" href="'+basePath+'/customer/mapplybackmoney-'+order.orderId+'.html">申请退款</a><br/>';
                         //}
                     //}else if(returnjson.isBackOrder == '2'){
                     //    if(order.orderStatus=="RECIEVED"){
                     //        nextpagehtml +='<a href="'+basePath+'/comment-'+order.orderId+'.html" class=';
                     //        if( cFlag > 0){
                     //            nextpagehtml +='"btn">评价</a>';
                     //        }else if(cFlag == 0){
                     //            nextpagehtml +='"btn-grey">评价</a>';
                     //        }
                     //     /*   nextpagehtml +='<a href="javascript:void(0)" class="btn" onclick="addCar('+order.orderId+')">再次购买</a>';*/
                     //    }else if(order.orderStatus=="1" || order.orderStatus=="4" || order.orderStatus=="5" || order.orderStatus=="6") {
                     //      /*  nextpagehtml += '<a href="javascript:void(0)" class="btn" onclick="addCar(' + order.orderId + ')">再次购买</a>';*/
                     //    }
                     //}
                     }

				 nextpagehtml +='</div>';
				 nextpagehtml +='</div>';
				 nextpagehtml +='</div>';
				 nextpagehtml +='</div>';
			 } 
			 if(nextpagehtml != null && nextpagehtml != ""){
				 $('#items').append(nextpagehtml ); 
				 $newElems =$("#items .order-item");
				 // 渐显新的内容
				 $newElems.animate({ opacity: 1 });
				 if(returnjson.nextPageNo==pageNo){
					 clearInterval(iIntervalId);
					 $('#showmore').hide();
				 }else{
				/*	 $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
					 $('#showmore').show();*/
                     $('#showmore').html(' <img src='+basePath+'/static/img/loading.gif /><span>加载中……</span>');
                     $('#showmore').show();
				 }
			 }else{
				 $('#showmore').html('<a class="handle" href="javascript:show()">已无更多结果</a>');
			 }
		 }else{
             $("#status").val(1);
			 $('#showmore').html('<a class="handle" href="javascript:show()">已无更多结果</a>');
		 }
		 

 // 当前页码数小于3页时继续显示更多提示框
/* if(page < 2) {
   $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
   clearInterval(iIntervalId);
   $('#showmore').hide();
 }*/
//bdShare.fn.init();
}


var comUrl="";
//确认收货
function comfirmgoods(orderCode) {
	/*$('#confirm_order').show();
	$('.opacity-bg-1').show();
	$("#tiptitle").html("是否确认收货？");*/
    var sureGet = dialog({
        width : 260,
        content : '<p class="tc">是否确认收货？</p>',
        okValue : '确定',
        cancelValue : '取消',
        ok : function(){
            comfirmGoodsSucc();
        },
        cancel : function(){
            cancelComfirmGoods();
        }
    });
    sureGet.showModal();
//	$("#go_pay_01").prop("href", url);
	comUrl=basePath+'/comfirmofgoods?orderCode='+orderCode;
}

//取消订单
function cancelOrder(orderNo) {
    var url =basePath+'/cancelOrder?orderCode='+orderNo;
	/*$('#confirm_order').show();
	$('.opacity-bg-1').show();
	$("#tiptitle").html("是否确认取消？");*/
    var sureGet = dialog({
        width : 260,
        content : '<p class="tc">是否确认取消？</p>',
        okValue : '确定',
        cancelValue : '取消',
        ok : function(){
            comfirmGoodsSucc();
        },
        cancel : function(){
            cancelComfirmGoods();
        }
    });
    sureGet.showModal();
	comUrl = url;
}
function comfirmGoodsSucc(){
	window.location.href=comUrl;
	$('#confirm_order').hide();
	$('.opacity-bg-1').hide();
}


function cancelComfirmGoods(){
	$('#confirm_order').hide();
	$('.opacity-bg-1').hide();
}

function addCar(id){
	$.ajax({
		url:basePath+'/addproducttoshopcarbyorderid.htm?orderId='+id,
        type:'post',
        success:function(data){
         if(data>0){
          window.location.href = basePath+"/myshoppingmcart.html";
         }
        }
	});
}

function expressInfo(orderNo){
	$("#orderNo").val(orderNo);
	var fillFlow = dialog({
		width : 300,
		title : '填写物流信息',
		content : $('.fill-flow-form'),
		okValue : '保存',
		cancelValue : '取消',
		ok : function(){
			quedingwl('00');
		},
		cancel : function(){
			return true;
		}
	});
	fillFlow.showModal();
}

//物流名称失去焦点
function wuliuname(){
	var value = $("#wlname").val();//物流名称
	if(value==''||value.length<0){
		$("#yanzhengname").addClass("red");
		$("#yanzhengname").html("&nbsp;<img src='../images/red_cha.png'/>物流公司名称不能为空！");
	}else{
		$("#yanzhengname").html("");
	}
}

//物流单号失去焦点
function wuliudanhao(){
	var reg = /^[0-9a-zA-Z]+$/;
	var no = $("#wlno").val(); //物流单号

	if(no==''||no.length<0){
		$("#yanzhengno").addClass("red");
		$("#yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>物流公司单号不能为空！");

	}else{
		if(!reg.test(no)){
			$("#yanzhengno").addClass("red");
			$("#yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>只能为数字或字母！");
		}else{
			$("#yanzhengno").html("");
		}

	}
}

//提交物流信息
function quedingwl(flag){//判断是我的订单跳转还是会员中心首页跳转
	var subflag = 0;
	var wlname = $("#wlname").val();//物流名称
	var wlno = $("#wlno").val(); //物流单号
	var orderNo = $("#orderNo").val(); //订单号
	if(checkValue(wlname,wlno)==0){//验证物流信息
		//var index = url.substring(url.length-6,url.length-5);//截取当前的页数
		//var yueindex = url.substring(url.length-10,url.length-9);//截取一月前订单
		if(subflag == 0){
			$.ajax({
				//提交数据的类型 POST GET
				type:"POST",
				//提交的网址
				url:basePath+"/saveMBackOrderGeneral.htm",
				//提交的数据
				data:{wlname:wlname,wlno:wlno,orderNo:orderNo},
				async:false,
				//返回数据的格式
				datatype: "post",
				//成功返回之后调用的函数
				success:function(data){
					subflag = 1;
					if(data==1){
						window.location.href =basePath+'/customer/myorder-6-1.html';
					}else if(data==0){
						window.location.href =basePath+'/register.html';
					}
				}
			});
		}
	}else{
		$("#fillForm").showModal();
	}
}

//验证物流信息
function checkValue(value,no){
	var reg = /^[0-9]|[a-z]|[A-Z]+$/;
	var result = 0;
	if(value==''||value.length<0){
		$("#yanzhengname").addClass("red");
		$("#yanzhengname").html("&nbsp;<img src='../images/red_cha.png'/>物流公司名称不能为空！");
		result=1;
	}
	if(no==''||no.length<0){
		$("#yanzhengno").addClass("red");
		$("#yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>物流公司单号不能为空！");
		result=1;
	}else{
		if(!reg.test(no)){
			$("#yanzhengno").addClass("red");
			$("#yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>只能为数字或字符！");
		}else{
			$("#yanzhengno").html("");
		}
	}
	return result;
}