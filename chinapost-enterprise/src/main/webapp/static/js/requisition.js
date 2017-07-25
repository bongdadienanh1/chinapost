$(document).ready(function(){
    var array = [];
    ajaxPages('../web/api/requisition/myReq','itemContainer','holder',4,5,'','',array);
    ajaxPages('../web/api/requisition/myNotHandleReq','item_container_request_todo','request_todo',5,5,'','',array);
    ajaxPages('../web/api/requisition/myHandledReq','item_container_request_done','holder_done',6,5,'','',array);
    $.datetimepicker.setLocale('ch')


    var reNo = '';
    var start = '';
    var end = '';
    var status = '';
    var array = [];

    $("#reqsearch").click(function(){
        reNo = $("input[name='reNo']").val();
        start = $("#datetimepicker_start").val();
        end = $("#datetimepicker_end").val();
        status = $(".reqbutton select option:selected").val();
        console.log(start,end);
        array[8] = start;
        array[9] = end;
        array[10] = status;
        array[11] = reNo;
        if(start<=end){
            var type = $(".requestTable ul .reqOn").val();
            if( type == 0 ){
                ajaxPages('../web/api/requisition/myReq','itemContainer','holder',4,5,'','',array);
            }
            else if ( type == 1 ){
                ajaxPages('../web/api/requisition/myNotHandleReq','item_container_request_todo','request_todo',5,5,'','',array);
            }
            else if ( type == 2 ){
                ajaxPages('../web/api/requisition/myHandledReq','item_container_request_done','holder_done',6,5,'','',array);
            }
        }else{
            data_type_alert("结束时间不能小于开始时间","error")
        }
    });



    $("#date_start").click(function(e){
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_start").trigger("focus");
    })
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").val( $("#datetimepicker_start").val() );
        try{
            $("#datetimepicker_start").datetimepicker('destroy');
        }
        catch(e){

        }
    });

    $("#date_end").click(function(e){
        var dateStr = $("#datetimepicker_start").val();
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y',
            startDate:new Date(dateStr)
        });
        $("#datetimepicker_end").trigger("focus");
    })
    $("#datetimepicker_end").blur(function(){
        try{
            $("#datetimepicker_end").datetimepicker('destroy');
        }
        catch(e){

        }
    });


    //我的待办 ----
    var id = '';
    $(document).on("change",".myRequestTodoSelect",function(){
        var type = $(this).val();
        id = $(this).parent().parent().find("input[type='hidden']").val();
        if( type == 1 ){
            $(".agreeRequest").fadeIn(500);
        }
        else if ( type == 2 ){
            $(".disAgreeRequest").fadeIn(500);
        }
        $(this).val(0);
    });
    $("#agreeRequestConfirm").click(function(){
        $.post("../web/api/requisition/passReq",{
            id:id
        },function(data){
            if(data.response == 'success'){
                $(".AgreeRequestSure").fadeIn(500);
                $(".agreeRequest").fadeOut(500);
            }
            else{
                data_type_alert(data.data.text,"error");
            }
        });
    });

    $("#payRequestConfirm").click(function(){
        var paykey = $(".payRequest input[type='password']").val();
        var payMoney = $("#payMoney").val();
        $.post("../web/api/requisition/payReq",{
            id:id,
            payFee:payMoney,
            paykey:paykey
        },function(data){
            if( data.response == 'success' ){
                data_type_alert("支付成功","success");
                discoverHtml();
                response_ensure_alert("success","支付成功",function(){
                   window.location.href = "Requisition";
                });
                $(".payRequest").fadeOut(500);
            }
            else{
                data_type_alert(data.data.text,"error");
            }
        });
    });

    $("#disAgreeRequestConfirm").click(function(){
        $.post("../web/api/requisition/denyReq",{
            id:id
        },function(data){
            if(data.response == 'success'){
                data_type_alert("已拒绝","success");
                discoverHtml();
                window.location.href = "Requisition";
            }
            else{
                data_type_alert(data.data.text,"success");
                discoverHtml();
                $(".disAgreeRequest").fadeOut(500);
            }
        });
    });

});
