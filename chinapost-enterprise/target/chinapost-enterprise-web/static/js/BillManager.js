$(document).ready(function(){
    $.datetimepicker.setLocale('ch')
    var array = [];
    $(".billTable li[value='0']").click(function(){
        if( isTop == 'true' ){
            ajaxPages('../web/api/billmanage/parentAllocat','itemContainer','holder',0,5,"充值列表","",array);
        }
        else{
            ajaxPages('../web/api/billmanage/parentAllocat','itemContainer','holder',0,5,"上级财富分配","",array);
        }

    });

    $(".billTable li[value='1']").click(function(){
        ajaxPages('../web/api/billmanage/myAllocat','itemContainer','holder',0,5,"财富分配","",array);
    });

    $(".billTable li[value='2']").click(function(){
        ajaxPages('../web/api/billmanage/getBatchGrands','itemContainer','holder',0,5,"邮豆发放","",array);
    });

    $(".billTable li[value='3']").click(function(){
        ajaxPages('../web/api/billmanage/getBatchDecut','itemContainer','holder',0,5,"邮豆扣减","",array);
    });

    $(".billTable li[value='4']").click(function(){
        ajaxPages('../web/api/billmanage/getEnterpriseIdOrderSubsidy','itemContainer','holder',0,5,"网点订单补贴","",array);
    });
    $(".billTable li[value='5']").click(function(){
        ajaxPages('../web/api/billmanage/getEnterpriseIdBackOrderSubsidy','itemContainer','holder',0,5,"订单退款返还","",array);
    });
    $(".billTable li[value='" + tabNumber + "']").trigger("click");




    $("#billsearch").click(function(){
        var array = [];
        array[11] = $("#billNo").val();
        array[8] = $("#datetimepicker_start").val();
        array[9] = $("#datetimepicker_end").val();
        var type = $(".reqOn").val();
        console.log(array);
        if( array[9]>=array[8]){
            if ( type == 0 ){
                if( isTop == 'true' ){
                    ajaxPages('../web/api/billmanage/parentAllocat','itemContainer','holder',0,5,"充值列表",'',array);
                }else{
                    ajaxPages('../web/api/billmanage/parentAllocat','itemContainer','holder',0,5,"上级财富分配",'',array);
                }

            }
            else if ( type == 1){
                ajaxPages('../web/api/billmanage/myAllocat','itemContainer','holder',0,5,"财富分配",'',array);
            }
            else if ( type == 2 ){
                ajaxPages('../web/api/billmanage/getBatchGrands','itemContainer','holder',0,5,"邮豆发放",'',array);
            }
            else if ( type == 3 ){
                ajaxPages('../web/api/billmanage/getBatchDecut','itemContainer','holder',0,5,"邮豆扣减",'',array);
            }
            else if ( type == 4 ){
                ajaxPages('../web/api/billmanage/getEnterpriseIdOrderSubsidy','itemContainer','holder',0,5,"网点订单补贴",'',array);
            }
            else if ( type == 5 ){
                ajaxPages('../web/api/billmanage/getEnterpriseIdBackOrderSubsidy','itemContainer','holder',0,5,"订单退款返还",'',array);
            }
        }else{
            data_type_alert("结束时间不能小于开始时间","error")
        }
    });

    $("#date_start").click(function(e){
        console.log(1);
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_start").trigger("focus");
    })
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").datetimepicker('destroy');
    });

    $("#date_end").click(function(e){
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_end").trigger("focus");
    })
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });
});

