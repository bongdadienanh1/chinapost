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
		 var returnjson = eval("(" +responseData+")");	
		 var nextpagehtml = ''; 
		 if(returnjson.data.pb.list!=null && returnjson.data.pb.list.length >0 ){
			 for(var i=0; i<returnjson.data.pb.list.length; i++) {
				 var backorder =  returnjson.data.pb.list[i];
				 nextpagehtml +='<div class="order-item">';
				 nextpagehtml +='<div class="order-number">';
				 nextpagehtml +='<div class="list-item">';
				 nextpagehtml +='<h3 class="item-head"><label for="">退单号：</label><span>';
				 nextpagehtml +=''+backorder.backOrderCode+'';
				 nextpagehtml +='</span>';
				 
				 nextpagehtml +='<span class="curValue text-them">';
				 if(backorder.backCheck != null && backorder.backCheck != ''){
					 if(backorder.backCheck == "RETURN_APPLIED"){
						 nextpagehtml +='退货审核';
					 }else if(backorder.backCheck =="RETURN_AGREED"){
						 nextpagehtml +='同意退货';
					 }else if(backorder.backCheck=="RETURN_DENIED"){
						 nextpagehtml +='拒绝退货';
					 }else if(backorder.backCheck=="RETURN_DELIVERING" ){
						 nextpagehtml +='待商家收货';
					 }else if(backorder.backCheck=="RETURN_FINISHED"){
						 nextpagehtml +='退货成功';
					 }else if(backorder.backCheck=="REFUND_APPLIED"){
						 nextpagehtml +='退款审核';
					 }else if(backorder.backCheck=="REFUND_DENIED"){
						 nextpagehtml +='拒绝退款';
					 }else if(backorder.backCheck=="RETURN_DELIVER_FAILURE"){
						 nextpagehtml +='商家拒绝收货';
					 }else if(backorder.backCheck=="RETURN_WAIT_DELIVER_INFO"){
						 nextpagehtml +='待填写物流信息';
					 }else if(backorder.backCheck=="REFUND_AGREED"){
                         nextpagehtml +='待退款';
                     }else if(backorder.backCheck=="RETURN_WAIT_REFUND"){
                         nextpagehtml +='待退款';
                     }else if(backorder.backCheck=="REFUND_FINISHED"){
						 nextpagehtml +='退款成功';
					 }
				 }
				 nextpagehtml +='</span></h3>';
				 nextpagehtml +='</div></div>';
				 nextpagehtml +='<div class="order-info">';
				 nextpagehtml +='<div class="list-body-box">';
                 if(backorder.products !=null){

                     if(backorder.products.length > 2){
                         nextpagehtml +='<div class="list-item">';
                         nextpagehtml +='<div class="box-body">';
                         nextpagehtml +='<ul>';
                         for(var n=0;n<backorder.products.length;n++){
                             var backgoods = backorder.products[n];
                             nextpagehtml +='<li>';
                             nextpagehtml +='<a title="'+backgoods.goodsInfoName+'" href="'+basePath+'/orderdetails?orderId='+backgoods.goodsInfoId+'">';
                             nextpagehtml +='<img width="78" height="78" title="'+backgoods.goodsInfoName+'" alt="'+backgoods.goodsInfoName+'" src="'+backgoods.goodsInfoImgId+'" />';
                             nextpagehtml +='</a>';
                             nextpagehtml +='</li>';
                         }
                         nextpagehtml +='</ul>';
                         nextpagehtml +='</div>';
                         nextpagehtml +='</div>';
                     }else{
                         for(var m=0;m<backorder.products.length;m++){
                             var backgoods = backorder.products[m];
                             nextpagehtml +='<div class="list-item">';
                             nextpagehtml +='<a title="'+backgoods.goodsInfoName+'" href="'+basePath+'/orderdetails?orderId='+backgoods.goodsInfoId+'">';
                             nextpagehtml +='<div class="pro-item" style="border-bottom: none;">';
                             nextpagehtml +='<div class="propic">';
                             nextpagehtml +='<img width="78" height="78" title="'+backgoods.goodsInfoName+'" alt="'+backgoods.goodsInfoName+'" src="'+backgoods.goodsInfoImage+'" />';
                             nextpagehtml +='</div>';
                             nextpagehtml +='<div class="prodesc">';
                             nextpagehtml +=' <h3 class="title">';
                             nextpagehtml +=''+backgoods.goodsInfoName+'';
                             nextpagehtml +='</h3>';
                             nextpagehtml +='<p class="price" style="color: #333;"><span class="num">';
                             nextpagehtml +=''+backgoods.goodsInfoPrice.toFixed(2)+'';
                             nextpagehtml +='</span>邮豆</p>';
                             nextpagehtml +='<span class="pro-num">x';
                             var num = 0;
                             if(backgoods.goodsInfoNum != null && backgoods.goodsInfoNum !=''){
                                 num = backgoods.goodsInfoNum;
                             }
                             nextpagehtml +=''+num+'';
                             nextpagehtml +='</span>';
                             nextpagehtml +=' </div>';
                             nextpagehtml +=' </div>';
                             nextpagehtml +='</a>';
                             nextpagehtml +='</div>';
                         }
                     }
                 }
				 nextpagehtml +='</div>';
				 nextpagehtml +='</div>';
				 nextpagehtml +='<div class="order-bottom">';
				 nextpagehtml +='<div class="list-item">';
				 nextpagehtml +='<h3 class="item-head" style="color: #333;">';
				 nextpagehtml +='<label for="" style="vertical-align: middle;">退款金额：</label><span class="pay-money" style="color: #333;">邮豆';
				 var moneyPaid = 0;
				 if(backorder.backPrice != null && backorder.backPrice != ''){
					 moneyPaid = backorder.backPrice;
				 }
				 
				 nextpagehtml +=''+moneyPaid+'';
				 nextpagehtml +='</span>';
				 nextpagehtml +='</h3>';
				 nextpagehtml +='<div class="too-btn">';
				 if(backorder.backCheck !=null && backorder.backCheck!=''){
					 if(backorder.backCheck=="9"){
						 nextpagehtml +='<a class="btn fill-flow" href="javascript:void(0)" onclick="expressInfo('+backorder.order.orderCode+')" style="width:100px">填写物流信息</a>';
					 }
				 }
				 if(backorder.isBack != null && backorder.isBack !=''){
					 if(backorder.isBack == "2"){
						 nextpagehtml +='<a class="btn" href="'+basePath+'/backorderpricedetail?backOrderCode='+backorder.backOrderCode+'">退款详情</a><br/>';
					 }else{
						 nextpagehtml +='<a class="btn" href="'+basePath+'/backorderpricedetail?backOrderCode='+backorder.backOrderCode+'">退货详情</a><br/>';
					 }
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
					 $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
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

//物流名称失去焦点
function wuliuname(){
	var value = $("#wlname").val();//物流名称
	if(value==''||value.length<0){
		$("#yanzhengname").addClass("red");
		$("#yanzhengname").html("&nbsp;<img src='"+basePath+"/static/img/red_cha.png'/>物流公司名称不能为空！");
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
		$("#yanzhengno").html("&nbsp;<img src='"+basePath+"/static/img/red_cha.png'/>物流公司单号不能为空！");

	}else{
        if(!reg.test(no)){
            $("#yanzhengno").addClass("red");
            $("#yanzhengno").html("&nbsp;<img src='"+basePath+"/static/img/red_cha.png'/>只能为数字或字母！");
        }else{
            $("#yanzhengno").html("");
        }

	}
}

//验证物流信息
function checkValue(value,no){
    var reg = /^[0-9]|[a-z]|[A-Z]+$/;
	var result = 0;
	if(value==''||value.length<0){
		$("#yanzhengname").addClass("red");
		$("#yanzhengname").html("&nbsp;<img src='"+basePath+"/static/img/red_cha.png'/>物流公司名称不能为空！");
		result=1;
	}
	if(no==''||no.length<0){
		$("#yanzhengno").addClass("red");
		$("#yanzhengno").html("&nbsp;<img src='"+basePath+"/static/img/red_cha.png'/>物流公司单号不能为空！");
		result=1;
	}else{
        if(!reg.test(no)){
            $("#yanzhengno").addClass("red");
            $("#yanzhengno").html("&nbsp;<img src='"+basePath+"/static/img/red_cha.png'/>只能为数字或字符！");
        }else{
            $("#yanzhengno").html("");
        }
    }
	return result;
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
					url:basePath+"/saveMBackOrderGeneral",
					//提交的数据
					data:{wlname:wlname,wlno:wlno,orderNo:orderNo},
					async:false,
					//返回数据的格式
					datatype: "post",
					//成功返回之后调用的函数             
					success:function(data){
						subflag = 1;
						if(data.response=="success"){
							window.location.href =basePath+'/myorder?type=6';
						}else if(data==0){
							window.location.href =basePath+'/login';
						}
					}
				});
		}
	}else{
		$("#fillForm").showModal();
	}
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
