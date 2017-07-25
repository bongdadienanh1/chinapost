$(document).ready(function() {
    var purchaseId = $("#purchaseId").val()
    $(".nodoupt").click(function(){
        var  marks = $(".reasonForApply").val()
        $.post('../web/api/inventory/confirmPurchaseBill',{
            purchaseId:purchaseId,
            marks:marks
        },function(data){
            if(data.response == "success"){
                localStorage['systemAction'] = 'refresh';
                response_ensure_alert("success","已确认",function(){
                    window.close()
                })
            }else{
                response_ensure_alert("error",data.data.text)
            }
        },'json')
    })
    $(".stop").click(function(){
        $.post('../web/api/inventory/terminatePurchaseBill',{
            purchaseId:purchaseId
        },function(data){
            if(data.response == "success"){
                localStorage['systemAction'] = 'refresh';
                response_ensure_alert("success","终止成功",function(){
                    window.close()
                })
            }else{
                response_ensure_alert("error",data.data.text)
            }
        },'json')
    })
    $(".creatBill").click(function(){
        $(".creatBill").unbind();
        $(this).css("background-color","#f4f4f4")
        $.post('../web/api/inventory/createStorageBill',{
            purchaseId:purchaseId
        },function(data){
            if(data.response == "success"){
                localStorage['systemAction'] = 'refresh';
                response_ensure_alert("success","生成入库单成功",function(){
                    window.close()
                })
            }else{
                response_ensure_alert("error",data.data.text)
            }
        },'json')
    })
    $(".check").click(function(){
        var reason = $("#remark").html()
        $.post('../web/api/inventory/settlementPurchase',{
            purchaseId:purchaseId,
            comment:reason
        },function(data){
            if(data.response == "success"){
                localStorage['systemAction'] = 'refresh';
                response_ensure_alert("success","结算成功",function(){
                    window.close()
                })
            }
            else if(data.data.errorcode == 14){

                response_ensure_alert("warning","当前采购商品未完全入库,是否强制结算",function(){
                    $.post('../web/api/inventory/forceFinishBill',{
                        purchaseId:purchaseId
                    },function(data){
                        if(data.response == "success"){
                            localStorage['systemAction'] = 'refresh';
                            response_ensure_alert("success","结算成功",function(){
                                window.close()
                            })
                        }else{
                            response_ensure_alert("error",data.data.text)
                        }
                    },'json')
                },function(){

                })
            }else{
                response_ensure_alert("error",data.data.text)
            }
        },'json')
    })


})