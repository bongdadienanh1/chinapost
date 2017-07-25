$(document).ready(function(e) {
    var arr = [];
    arr[8] = '';
    arr[9] = '';
    arr[11] = '';
    arr[12] = '';
    arr[13] = '';
    arr[14] = '';
    $.datetimepicker.setLocale('ch')
    //轮询刷新
    setInterval(function(){
        var action = localStorage["systemAction"];
        if( action == 'refresh' ){
            localStorage['systemAction'] = 'active';
            $(".voucherTable li.reqOn").trigger("click")
        }
    },1000);
    $(document).on("mouseenter","#itemBrand",function(){
        $(this).children("dd").show()
    })
    $(document).on("mouseleave","#itemBrand",function(){
        $(this).children("dd").hide()
    })
    $(document).on("click","#itemBrand dd:not(#brandSearch)",function(){
        var id=$(this).data("id")
        var name = $(this).html()
        console.log(id,name)
        $(this).siblings("dt").attr("data-id",id)
        $(this).siblings("dt").html(name)
        $("#itemBrand dd").slideUp()
        $(this).parent().siblings("b").remove()
    })
    //$.post("../web/api/goodsManager/getThirdName",{
    //    thirdName:""
    //},function(data){
    //    if( data.response == 'success' ){
    //        var html = ''
    //        var html1 = "<dd data-id=''>请选择</dd>"
    //        $("#itemBrand dd:not(#brandSearch)").remove()
    //        $('#brandSearch').after(html1)
    //        data.data.map(function(object){
    //            html += '<dd title="'+ object.thirdName  +'" data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
    //        })
    //        $("#itemBrand").append(html)
    //        var id=$("#brandSearch").next().data("id")
    //        var name = $("#brandSearch").next().html()
    //        $("#brandSearch").siblings("dt").attr("data-id",id)
    //        $("#brandSearch").siblings("dt").html(name)
    //    }
    //    else{
    //        response_ensure_alert('error',data.data.text,function(){
    //
    //        });
    //    }
    //},'json');
    //$.post("../web/api/goodsManager/getThirdName",{
    //    thirdName:""
    //},function(data){
    //    if( data.response == 'success' ){
    //        $("#itemBrand dd:not(#brandSearch)").remove()
    //        var html = "<dd data-id=''>请选择</dd>"
    //        data.data.map(function(object){
    //            html += '<dd title="'+ object.thirdName  +'"  data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
    //        })
    //        $("#itemBrand").append(html)
    //        var id=$("#brandSearch").next().data("id")
    //        var name = $("#brandSearch").next().html()
    //        $("#brandSearch").siblings("dt").attr("data-id",id)
    //        $("#brandSearch").siblings("dt").html(name)
    //    }
    //    else{
    //        response_ensure_alert('error',data.data.text,function(){
    //
    //        });
    //    }
    //},'json');

    //初始化供应商下拉搜索
    $.post("../web/api/goodsManager/getThirdName",{
        thirdName:""
    },function(data){
        if( data.response == 'success' ){
            $("#itemBrand dd:not(#brandSearch)").remove()
            var html = "<dd data-id=''>请选择</dd>"
            data.data.map(function(object){
                html += '<dd title="'+ object.thirdName  +'" data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
            })
            $("#itemBrand").append(html)
            var id=$("#brandSearch").next().data("id")
            var name = $("#brandSearch").next().html()
            $("#brandSearch").siblings("dt").attr("data-id",id)
            $("#brandSearch").siblings("dt").html(name)
        }
        else{
            response_ensure_alert('error',data.data.text,function(){

            });
        }
    },'json');
    //输入框改变时的供应商下拉变化
    $(document).on("input","#brandSearch input[type='text']",function(){
        var goodsInfoType = $("#chooseGoodsType dt").attr("data-id")
        var brandName = $(this).val()
            $.post("../web/api/goodsManager/getThirdName",{
                thirdName:brandName
            },function(data){
                if( data.response == 'success' ){
                    $("#brandSearch").siblings("dd").remove()
                    var html = ""
                    if($("#brandSearch input[type='text']").val() == ''){
                        html += "<dd data-id='' style='display: block'>请选择</dd>"
                    }else{

                    }
                    data.data.map(function(object){
                        html += '<dd style="display:block" data-id="'+ object.thirdId +'">'+ object.thirdName +'</dd>'
                    })
                    $("#brandSearch").after(html)
                    console.log(html)
                }
                else{
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');

    })
    $(".voucherTable ul li").click(function(){

        arr[14] = $("#statusInput").val()

        arr[14] = $("#statusInput").val()
        if(arr[14] == "PARTIAL_RECEIPT"){
            arr[13] = "INVENTORY_TRANSFER"
        }else{
            arr[13] = '';
            if( $("#status").children("abbr").html() =="全部" ){

            }else{
                arr[13] = $("#typeInput").val();
            }
        }


        if(arr[14] == "PARTIAL_RECEIPT2"){
            arr[14] = "PARTIAL_RECEIPT"
            arr[13] = "PURCHASE_INVENTORY_INSTOCK"
        }else{
            arr[13] = '';
            if( $("#status").children("abbr").html() =="全部" ){

            }else{
                arr[13] = $("#typeInput").val();
            }
        }

        if( $(this).html() == '待办事宜' ){
            ajaxPages('../web/api/inventory/getUnHandleBills','itemContainer_notHandled','holder_notHandled','inventory_notHandled',5,'','',arr);
        }
        if( $(this).html() == '已办事宜' ){
            ajaxPages('../web/api/inventory/getHandledBills','itemContainer_done','holder_done','inventory_done',5,'','',arr);
        }
        if( $(this).html() == '我的请求' ){
            ajaxPages('../web/api/inventory/getCreatedBills','itemContainer_myRequest','holder_myRequest','inventory_myRequest',5,'','',arr);
        }
        if( $(this).html() == '采购订单' ){
            arr[13] = ""
            arr[14] = $("#statusInput2").val()
            ajaxPages('../web/api/inventory/getPurchaseBillList','itemContainer_myPurchaseOrder','holder_purchase','purchaseOrder',5,'','',arr);
        }
    });
    //搜索
    $("#voucherSearch").click(function(){

        var checked = $(".reqOn").html();
        var code = $("#voucherOrder").val();
        var creator = $("#voucherName").val();
        var start = $("#datetimepicker_start").val();
        var end = $("#datetimepicker_end").val();
        var billType = '';
        if( $("#type").children("abbr").html() != '全部' ){
            billType = $("#typeInput").val();
        }
        var billStatus = '';
        if( $("#status").children("abbr").html() =="全部" ){

        }else{
            billStatus = $("#statusInput").val();
        }
        var billStatus2 = '';
        if( $("#status2").children("abbr").html() =="全部" ){

        }else{
            billStatus2 = $("#statusInput2").val();
        }
        if(billStatus == "PARTIAL_RECEIPT"){
            billType = "INVENTORY_TRANSFER"
        }


        if(billStatus == "PARTIAL_RECEIPT2"){
            billStatus = "PARTIAL_RECEIPT"
            billType = "PURCHASE_INVENTORY_INSTOCK"
        }
        var thirdId = '';
        if($('#brandAppend').attr('data-id') == ''){

        }else{
            thirdId = $('#brandAppend').attr('data-id');
        }
        arr[8] = start;
        arr[9] = end;
        arr[11] = code;
        arr[12] = creator;
        arr[13] = billType;
        arr[5] = thirdId;

        if( start > end ){
            response_ensure_alert("error","结束时间不得小于开始时间");
        }
        else{
            if( checked == '待办事宜' ){
                arr[14] = billStatus;
                ajaxPages('../web/api/inventory/getUnHandleBills','itemContainer_notHandled','holder_notHandled','inventory_notHandled',5,'','',arr);
            }
            else if( checked == '已办事宜' ){
                arr[14] = billStatus;
                ajaxPages('../web/api/inventory/getHandledBills','itemContainer_done','holder_done','inventory_done',5,'','',arr);
            }
            else if( checked ==  '我的请求'){
                arr[14] = billStatus;
                ajaxPages('../web/api/inventory/getCreatedBills','itemContainer_myRequest','holder_myRequest','inventory_myRequest',5,'','',arr);
            }
            else if( checked ==  '采购订单'){
                arr[14] = billStatus2;
                ajaxPages('../web/api/inventory/getPurchaseBillList','itemContainer_myPurchaseOrder','holder_purchase','purchaseOrder',5,'','',arr);
            }
        }

    });


    $(".voucherTable ul li[value='0']").trigger("click");


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
    });
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });

    $(".voucherTable li").click(function(){
        var lival=$(this).val();
        $(this).addClass("reqOn").siblings("li").removeClass("reqOn");
        if(lival==0){
            //待办事宜
            $(".myVoucherTodo").show().siblings("dl").hide();
            $(".billButton li:nth-child(2)").hide();
            $(".billButton li:nth-child(3)").show();
            $(".billButton li:nth-child(6)").show();
            $(".billButton li:nth-child(7)").show();
            $("#mergeReplenishment").show()
            $('#voucherSearchByCondition').hide();
            $('#supplier').hide();
        }else if(lival==1){
            //已办事宜
            $(".myVoucherDone").show().siblings("dl").hide();
            $(".billButton li:nth-child(2)").hide();
            $(".billButton li:nth-child(3)").show();
            $(".billButton li:nth-child(6)").show();
            $(".billButton li:nth-child(7)").show();
            $("#mergeReplenishment").hide()
            $('#voucherSearchByCondition').hide();
            $('#supplier').hide();
        }else if(lival==2){
            //我的请求
            $(".myVoucherRequest").show().siblings("dl").hide();
            $(".billButton li:nth-child(2)").hide();
            $(".billButton li:nth-child(3)").show();
            $(".billButton li:nth-child(6)").show();
            $(".billButton li:nth-child(7)").show();
            $("#mergeReplenishment").hide()
            $('#voucherSearchByCondition').hide();
            $('#supplier').hide();
        }
        else if(lival==3){
            //采购订单
            $(".myPurchaseOrder").show().siblings("dl").hide();
            $(".billButton li:nth-child(2)").show();
            $(".billButton li:nth-child(3)").hide();
            $(".billButton li:nth-child(6)").hide();
            $(".billButton li:nth-child(7)").hide();
            $("#mergeReplenishment").hide()
            $('#voucherSearchByCondition').show();
            $('#supplier').show();

        }
    });
    $(document).on("click",".vocherCode",function(){
        var vocherCode=$(this).siblings("input[type='hidden']").val();
        if($(".voucherTable li.reqOn").val()=="2"){
            $.post("../web/api/inventory/setBillRead",{
                billId:vocherCode,
                readFlag:true
            },function(data){
                if( data.response == 'success' ){


                }
                else{
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');
            $(this).children("b").remove()
        }
    })
    $(document).on("click",".vocherCode",function(){
        var status = $(".reqOn").html();
        status = handleStatus(status)
        var vocherCode=$(this).siblings("input[type='hidden']").val();
        var vocherState=$(this).siblings(".vocherState").html();
       if(vocherState == "报损单" || vocherState == "报溢单" ){
           window.open("reimburse?id=" + vocherCode + "&status=" + status) ;
       }
        else if(vocherState == "补货单"  ){
            window.open("replenishment?id=" + vocherCode + "&status=" + status) ;
        }
       else if(vocherState == "补货单(合并单)" ){
           window.open("replenishmentMega2?parentId=" + vocherCode + "&status=" + status +  "&mega=" + 1) ;
       }
       else if(vocherState == "调拨单"  ){
           window.open("allocation?id=" + vocherCode + "&status=" + status) ;
       }
       else if(vocherState == "入库单"  ){
           window.open("storage?id=" + vocherCode + "&status=" + status) ;
       }
       else if(vocherState == "采购订单" ){
           window.open("purchaseOrder?purchaseId=" + vocherCode) ;
       }
       else if(vocherState == "采购入库单" ){
           window.open("inStorage?purchaseId=" + vocherCode + "&status=" + status) ;
       }
    });
    $(".arrowType").click(function(){
        var arr=$(this).val();
        $(this).siblings("dd").slideToggle();
        if(arr==0){
            $(this).css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
            $(this).val("1")
        }else if(arr==1){
            $(this).css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");
            $(this).val("0")
        }

    });
    $(".arrowStatus").click(function(){
        var arr=$(this).val();

        if(arr==0){
            $(this).css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
            $(this).val("1")
            $(".now").slideDown();
        }else if(arr==1){
            $(this).css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");
            $(this).val("0")
            $(".now").slideUp();
        }
        //if($('.reqOn').val() != '3') {
        //    if ($('#typeInput').val() == 'INVENTORY_INSTOCK') {
        //        $(".billStatus").each(function () {
        //            $(this).hide();
        //        })
        //        $("dd[data-typestr='FINISHED']").show();
        //    }
        //    else if ($('#typeInput').val() == 'PURCHASE_INVENTORY_INSTOCK') {
        //        $(".billStatus").each(function () {
        //            $(this).hide();
        //        })
        //        $("dd[data-typestr='FINISHED']").show();
        //        $("dd[data-typestr='STAY_IN']").show();
        //        $("dd[data-typestr='PARTIAL_RECEIPT2']").show();
        //    }
        //    else {
        //        $(".billStatus").each(function () {
        //            $(this).show();
        //        })
        //    }
        //}
        //else  if($('.reqOn').val() == '3') {
        //    $(".billStatus").each(function(){
        //        $(this).show();
        //    })
        //}
    });
    $(".arrowSupplier").click(function() {
        var arr = $(this).val();

        if (arr == 0) {
            $(this).css("background", "url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
            $(this).val("1")
            $(".now2").slideDown();
        } else if (arr == 1) {
            $(this).css("background", "url(../static/img/com_btn_arrow_black_down.png) center no-repeat");
            $(this).val("0")
            $(".now2").slideUp();
        }
    })



    $(document).on('click','.select dd',function(){
        var secval=$(this).html();
        var secStr=$(this).data("typestr");
        var type = $(this).parent().prev().html();
        $(this).hide().siblings("dd").hide();
        $(this).siblings("dt").children("abbr").html(secval);
        $(this).siblings("dt").children("input").val(secStr);
        $(this).siblings(".arrow").css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");

        if( secStr == 'REPLENISHMENT' ){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'CHECKING' || $(this).data("typestr") == 'CHECKED' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' ){
                    $(this).show()
                    $(this).addClass("now");
                    $(".now").show()
                    $(".arrowStatus").css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
                    $(".arrowStatus").val("1")
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else if( secStr == 'INVENTORY_TRANSFER' ){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'WAIT_DELIVERY' || $(this).data("typestr") == 'WAIT_RECEIVER' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' || $(this).data("typestr") == 'WAIT_EDIT'){
                    $(this).show()
                    $(this).addClass("now");
                    $(".now").show()
                    $(".arrowStatus").css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
                    $(".arrowStatus").val("1")
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else if( secStr == 'MORE_REPORT' || secStr == 'LESS_REPORT'){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'CHECKING' || $(this).data("typestr") == 'WAIT_EDIT' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' ){
                    $(this).show()
                    $(this).addClass("now");
                    $(".now").show()
                    $(".arrowStatus").css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
                    $(".arrowStatus").val("1")
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else if( secStr == 'MERGE_REPLENISHMENT'){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'CHECKING' || $(this).data("typestr") == 'WAIT_EDIT' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' ){
                    $(this).show()
                    $(this).addClass("now");
                    $(".now").show()
                    $(".arrowStatus").css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
                    $(".arrowStatus").val("1")
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else{
            //单据状态全部
            if( type == '单据类型'){
                var btype = $(this).data("typesta-typestr");
                $('#typeInput').val($(this).data("typesta-typestr"));
                $(".All").css("display","block");
                $(".billStatus").each(function(){
                    $(this).addClass("now");
                    $(this).show()
                    $(".arrowStatus").css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
                    $(".arrowStatus").val("1")
                    $(".now").show()
                });
                //入库单
                if(btype == 'INVENTORY_INSTOCK'  && $('.reqOn').val() != 3){
                    //$("#statusInput").parent().parent().children('dd').each(function(){
                    //    $(this).hide();
                    //})
                    //$("dd[data-typestr='FINISHED']").show();
                    var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd>';
                    $('#billsta1').children('.select').children('dd').remove();
                    $('#billsta1').children('.select').append(html);
                    $(".arrowStatus").click();
                    $(".arrowStatus").click();

                }
                //采购入库单
                else if(btype == 'PURCHASE_INVENTORY_INSTOCK' && $('.reqOn').val() != 3){
                    //$("#statusInput").parent().parent().children('dd').each(function(){
                    //    $(this).hide();
                    //})
                    //$("dd[data-typestr='FINISHED']").show();
                    //$("dd[data-typestr='STAY_IN']").show();
                    //$("dd[data-typestr='PARTIAL_RECEIPT2']").show();
                    var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="STAY_IN" class="now billStatus allSelectButton">待入库</dd><dd data-typestr="PARTIAL_RECEIPT2" class="now billStatus allSelectButton">部分入库</dd>';
                    $('#billsta1').children('.select').children('dd').remove();
                    $('#billsta1').children('.select').append(html);
                    $(".arrowStatus").click();
                    $(".arrowStatus").click();
                }
                //补货单 合并的补货单
                else if(btype == 'REPLENISHMENT' || btype == 'MERGE_REPLENISHMENT'  && $('.reqOn').val() != 3){
                    if($("#isTop").val() =='true'){
                        var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="CHECKING" class="now billStatus allSelectButton">审核中</dd><dd data-typestr="CHECKED" class="now billStatus allSelectButton">已审核</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="CHECKED_NOT_PURCHASE" class="now billStatus allSelectButton">已同意未采购</dd>';
                        $('#billsta1').children('.select').children('dd').remove();
                        $('#billsta1').children('.select').append(html);
                        $(".arrowStatus").click();
                        $(".arrowStatus").click();
                    }
                    else if( $("#isTop").val() !='true' && $("#isEnd").val() !='true') {
                        var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="CHECKING" class="now billStatus allSelectButton">审核中</dd><dd data-typestr="CHECKED" class="now billStatus allSelectButton">已审核</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="CHECKED_NOT_TRANSFER" class="now billStatus allSelectButton">已同意未调拨</dd><dd data-typestr="CHECKED_NOT_PURCHASE" class="now billStatus allSelectButton">已同意未采购</dd>';
                        $('#billsta1').children('.select').children('dd').remove();
                        $('#billsta1').children('.select').append(html);
                        $(".arrowStatus").click();
                        $(".arrowStatus").click();
                    }else if( $("#isEnd").val() =='true'){
                        var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="CHECKING" class="now billStatus allSelectButton">审核中</dd><dd data-typestr="CHECKED" class="now billStatus allSelectButton">已审核</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="CHECKED_NOT_TRANSFER" class="now billStatus allSelectButton">已同意未调拨</dd><dd data-typestr="CHECKED_NOT_PURCHASE" class="now billStatus allSelectButton">已同意未采购</dd>';
                        $('#billsta1').children('.select').children('dd').remove();
                        $('#billsta1').children('.select').append(html);
                        $(".arrowStatus").click();
                        $(".arrowStatus").click();
                    }else{
                        var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="CHECKING" class="now billStatus allSelectButton">审核中</dd><dd data-typestr="CHECKED" class="now billStatus allSelectButton">已审核</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd>';
                        $('#billsta1').children('.select').children('dd').remove();
                        $('#billsta1').children('.select').append(html);
                        $(".arrowStatus").click();
                        $(".arrowStatus").click();
                    }
                }
                //调拨单
                else if(btype == 'INVENTORY_TRANSFER'  && $('.reqOn').val() != 3){
                    var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="PARTIAL_RECEIPT" class="now billStatus allSelectButton">部分收货</dd><dd data-typestr="WAIT_DELIVERY" class="now billStatus allSelectButton">待发货</dd><dd data-typestr="WAIT_RECEIVER" class="now billStatus allSelectButton">待收货</dd><dd data-typestr="WAIT_EDIT" class="now billStatus allSelectButton">待修改</dd>';
                    $('#billsta1').children('.select').children('dd').remove();
                    $('#billsta1').children('.select').append(html);
                    $(".arrowStatus").click();
                    $(".arrowStatus").click();
                }
                //报损单 报溢单
                else if(btype == 'LESS_REPORT' || btype == 'MORE_REPORT'  && $('.reqOn').val() != 3){
                    var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="CHECKING" class="now billStatus allSelectButton">审核中</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="WAIT_EDIT" class="now billStatus allSelectButton">待修改</dd>';
                    $('#billsta1').children('.select').children('dd').remove();
                    $('#billsta1').children('.select').append(html);
                    $(".arrowStatus").click();
                    $(".arrowStatus").click();
                }
                else{
                    var html = '<dd class="now All allSelectButton">全部</dd><dd data-typestr="CHECKING" class="now billStatus allSelectButton">审核中</dd><dd data-typestr="CHECKED" class="now billStatus allSelectButton">已审核</dd><dd data-typestr="TERMINATED" class="now billStatus allSelectButton" style="display: block;">已终止</dd><dd data-typestr="FINISHED" class="now billStatus allSelectButton">已完成</dd><dd data-typestr="PARTIAL_RECEIPT" class="now billStatus allSelectButton">部分收货</dd><dd data-typestr="WAIT_DELIVERY" class="now billStatus allSelectButton">待发货</dd><dd data-typestr="WAIT_RECEIVER" class="now billStatus allSelectButton">待收货</dd><dd data-typestr="WAIT_EDIT" class="now billStatus allSelectButton">待修改</dd><dd data-typestr="STAY_IN" class="now billStatus allSelectButton">待入库</dd><dd data-typestr="PARTIAL_RECEIPT2" class="now billStatus allSelectButton">部分入库</dd>';
                    $('#billsta1').children('.select').children('dd').remove();
                    $('#billsta1').children('.select').append(html);
                    $(".arrowStatus").click();
                    $(".arrowStatus").click();
                }
            }
        }

    });
    $(document).on('click','#voucherSearchByCondition',function(){
        if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()){
            var code = $("#voucherOrder").val();
            var billStatus2 = '';
            if( $("#status2").children("abbr").html() =="全部" ){

            }else{
                billStatus2 = $("#statusInput2").val();
            }
            var start = $("#datetimepicker_start").val();
            var end = $("#datetimepicker_end").val();
            var thirdId = '';
            if($('#brandAppend').attr('data-id') == ''){

            }else{
                thirdId = $('#brandAppend').attr('data-id');
            }
            window.location.href = '../web/api/exportExcel/exportPurchaseByCondition?code='+code+ '&'+'start='+start+ '&'+'end='+end+ '&'+'billStatus='+billStatus2+ '&'+'thirdId='+thirdId;
        }else{
            data_type_alert("结束时间必须大于开始时间且必填","error")
        }

    })

});

function handleStatus( str ){
    if( str == '待办事宜' ){
        return 'nothandled';
    }
    if( str == '已办事宜' ){
        return 'done';
    }
    if( str == '我的请求' ){
        return 'myrequest';
    }
}