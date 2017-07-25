$(document).ready(function(){
    var deliveryCode =""
   $("#payCheck").click(function(){
       coverHtml()
       console.log(11111111111)
       var customerId = $("#customerId").val();
       var goodsId = $("#itemId").val();
       var subsidyPrice = $("#subsidyNum").val();
       var subsidyReason = $("#subsidyReason").val();
       var PayKey = $("#PayKey").val();
       var text = $("#subsidyNum").val().split(".");
       var goodsInfo = sessionStorage.getItem("goodsInfo");
       var algin = text[1]
       if( algin == "" ){
           $("#subsidyNum").prop("readonly","readonly")
           response_ensure_alert("error","补贴金额输入不正确",function(){
               $("#subsidyNum").val("").focus().prop("readonly","");
               $("#shouldPayNum").html($("#payNum").html())
               $("#tixing").html("")
               discoverHtml()
           },function(){
               $("#subsidyNum").val("").focus().prop("readonly","");
               $("#shouldPayNum").html($("#payNum").html())
               $("#tixing").html("")
               discoverHtml()
           })
       }else{
           if( !$("input[id='subsidy']").is(":checked")){
               $.post("../web/api/valet/payValetOrder",{
                   customerId:customerId,
                   addressId:"",
                   goodsId:goodsId,
                   goodsInfo:goodsInfo,
                   subsidyPrice:"",
                   subsidyRemark:"",
                   payKey:""
               },function(data){
                   if( data.response == 'success' ){
                       coverHtml();
                       deliveryCode = data.data.orderCode;
                       sessionStorage.setItem("ShoppingCart",'');
                       $(".success").fadeIn();
                       sessionStorage.setItem("goodsInfo","");
                   }
                   else{
                       response_ensure_alert("error",data.data.text,function(){
                           discoverHtml()
                       })
                   }
               });
           }else{
               if(PayKey.length == 6){
                   if( subsidyPrice!="" && subsidyReason!="" && PayKey!=""){
                       $.post("../web/api/valet/payValetOrder",{
                           customerId:customerId,
                           addressId:"",
                           goodsId:goodsId,
                           goodsInfo:goodsInfo,
                           subsidyPrice:subsidyPrice,
                           subsidyRemark:subsidyReason,
                           payKey:PayKey
                       },function(data){
                           if( data.response == 'success' ){
                               coverHtml();
                               sessionStorage.setItem("ShoppingCart",'');
                               deliveryCode = data.data.orderCode;
                               $(".success").fadeIn();
                           }
                           else{
                               response_ensure_alert("error",data.data.text,function(){
                                   discoverHtml()
                               })
                           }
                       });
                   }else{
                       data_type_alert("勾选之后金额，留言，密码必填","error")
                       discoverHtml()
                   }
               }else{
                   data_type_alert("支付密码位数不正确","error")
                   discoverHtml()
               }
           }
       }



   });
    $("#paydetail").click(function(){
        discoverHtml()
        window.location.href="UserGet"
    })
    $("#paycomplete").click(function(){
        discoverHtml()
        window.location.href = 'UserGetListDetail?code=' + deliveryCode;
    })
});