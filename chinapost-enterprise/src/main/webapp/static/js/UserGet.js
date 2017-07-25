$(document).ready(function(){
    $("#sub").click(function(){
        var deliveryCode = $("input[name='billNumber']").val();
        $.post("../web/api/valet/getByDeliveryCode",{
            deliveryCode:deliveryCode
        },function(data){
            if( data.response == 'success' ){
                window.location.href = 'UserGetList?deliveryCode=' + deliveryCode;
            }else{
                response_ensure_alert('error',data.data.text,function(){

                },function(){

                });
            }
        },'json');
    });

    //for UserGetList
    if($("input[type='hidden']").val() == 0){
        $(".goods_type").html("待提货");
    }
    else if( $("input[type='hidden']").val() == 1 ){
        $(".goods_type").html("已完成");
    }
    else if( $("input[type='hidden']").val() == 2 ){
        $(".goods_type").html("已完成");
    }

    //查看发放详情
    $(".detail").click(function(){
        $(".warning").hide();
    });
    //完成


    //用户提货
    $("#ensure").click(function(){
        var deliveryCode = $("#dCode").val();
        $.post("../web/api/valet/pickUpOrder",{
            deliveryCode:deliveryCode
        },function(data){
            if( data.response == 'success' ){
                var okArr = [];
                okArr[0] = "查看详情";
                okArr[1] = function(){
                    $("#ensure").css("display","none");
                    $(".goods_type").html("已完成");
                };
                var cancelArr = [];
                cancelArr[0] = "确定";
                cancelArr[1] = function(){
                    window.location.href = 'UserGet';
                };
                selfPick_alert("success","提货成功",okArr,cancelArr);
            }
            else{
                response_ensure_alert("error",data.data.text,function(){
                    console.log("提货失败" + consoleNowTime());
                });
            }
        });
    });
    $(".clsBtn").click(function(){
        window.location.href = 'UserGet';
    });



});