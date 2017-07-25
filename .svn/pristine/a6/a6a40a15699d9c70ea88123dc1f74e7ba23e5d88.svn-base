$(document).ready(function(){
    $("#date_start").click(function(e){
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#datetimepicker_start").trigger("focus");
    });
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").datetimepicker('destroy');
    });

    $("#date_end").click(function(e){
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#datetimepicker_end").trigger("focus");
    })
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });
    var lock=false
    var array = [];
    array[8] = '';//start
    array[9] = '';//end
    array[17] = ""
    var array1 = [];
    array1[18] = ""
    $("#accSearch").click(function(){
        if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val() ){
            lock=true
            if( $(".typeChoose").val() == "0" ){
                array[17] = ""
            }else if ( $(".typeChoose").val() == "1"){
                array[17] = "-100"
            }
            else if ( $(".typeChoose").val() == "2"){
                array[17] = "-200"
            }
            array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
            array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
            $("#newDatetimepicker_start").val(array[8]);
            $("#newDatetimepicker_end").val(array[9]);
            ajaxPages("../web/api/report/getUcoinGrandByBatchCode","itemContainer","holder","sendReduce",10,'','',array);
        }else{
            data_type_alert("结束时间必须大于开始时间","error")
        }

    });
    $("#userDetailListReduce").draggable();
    $("#userDetailListReduce h1 i").click(function(){
        discoverHtml();
        $("#userDetailListReduce").hide()
    });
    $(document).on("click",".sendReduceDetail",function(){
        coverHtml()
        var recordData = $(this).parent().siblings(".recordData").html()
        var recordType = $(this).parent().siblings(".recordType").html()
        var recordCount = $(this).parent().siblings(".recordCount").html()
        var recordMoney = $(this).parent().siblings(".recordMoney").html()
        var recordAccount = $(this).parent().siblings(".recordAccount").html()

        $("#recordData").html(recordData)
        $("#recordType").html(recordType)
        $("#recordCount").html(recordCount)
        $("#recordMoney").html(recordMoney)
        $("#recordAccount").html(recordAccount)
        array1[18] = $(this).siblings(".batchcode").val()
        ajaxPages("../web/api/report/getDetailUcoinGrandByBatchCode","itemContainer2","holder2","sendReduceDetail",10,'','',array1);
        $("#userDetailListReduce").fadeIn(500);
    });


})
