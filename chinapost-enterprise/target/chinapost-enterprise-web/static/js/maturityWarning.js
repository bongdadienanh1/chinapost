//function ajaxPages(total,url,contentStr,target,type,perpage){
//    for(var i = 0;i < total;i++){
//        var html = get_html(type);
//        $("#"+contentStr).append(html);
//    }
//    jPagesGet(target,contentStr);
//    $("#"+contentStr).empty();
//    $("a").click(function(){
//        var page = $(this).html();
//        $.post(url,{
//            "page":page,
//            "size":perpage
//        },function(data){
//            if(data.response == 'success'){
//                appendHtml(contentStr,page,total,data.data.content,perpage);
//            }
//        },'json');
//    });
//    $("#holder").children()[1].click();
//}
//
//function get_html(type){
//    var html = '';
//    html = "<dd>";
//    html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
//    html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
//    html += '<abbr class="invenName"></abbr>';
//    html += '<abbr class="invenType"></abbr>';
//    html += '<abbr class="invenId"></abbr>';
//    html += '<abbr class="invenNum"></abbr>';
//    html += '<abbr class="invenNumUsed"></abbr>';
//    html += '<abbr class="invenBrand"></abbr>';
//    html += '<abbr class="invenSeller"></abbr>';
//    html += '<abbr class="invenName"></abbr>';
//    html += '<abbr class="invenId"></abbr>';
//    html += '<abbr style="width:300px;">';
//    html += '<select class="invenListSelect">';
//    html += '<option value="0" selected="selected">可选</option>';
//    html += '<option value="1">修改库存</option>';
//    html += '<option value="2">删除</option>';
//    html += '</select></abbr></dd>';
//    return html;
//}
//function appendHtml(str,pageIndex,totalPage,data,perpage){
//    $("#"+ str).empty();
//    for(var i=0;i<perpage;i++){
//        var html = "<dd>";
//        html += '<input type="hidden" value="' + data[i].goodsId + '">'
//        html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
//        html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
//        html += '<abbr class="invenName">' + data[i].goodsName + '</abbr>';
//
//        var content = '';
//        var h = '';
//        var array = data[i].specString.split(",");
//        for ( var j = 0; j < array.length;j++){
//            var text= /(?:null)/;
//            var flag = text.test(array[j]);
//            if ( !flag ){
//                h = array[j];
//                if( array.length > 1 ){
//                    h += '...';
//                }
//                break;
//            }
//
//        }
//        for(var a = 0;a < array.length;a++){
//            var text= /(?:null)/;
//            var flag = text.test(array[a]);
//            if ( !flag ){
//                content += array[a] + '\n';
//            }
//        }
//        html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
//        html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
//        html += '<abbr class="invenNum">' + data[i].inventory + '</abbr>';
//        html += '<abbr class="invenNumUsed">' + data[i].available + '</abbr>';
//        html += '<abbr class="invenBrand">' + data[i].goodsBrand + '</abbr>';
//        html += '<abbr class="invenSeller">' + data[i].goodsMerchants + '</abbr>';
//        html += '<abbr style="width:300px;">';
//        html += '<select class="invenListSelect">';
//        html += '<option value="0" selected="selected">可选</option>';
//        html += '<option value="1">修改库存</option>';
//        html += '<option value="2">删除</option>';
//        html += '</select></abbr></dd>';
//        $("#"+ str).append(html);
//    }
//
//}
//function checkUndefined(str){
//    if( str == 'undefined' ){
//        str = '';
//    }
//    return str;
//}
//
//
//function jPagesGet(content,target){
//    $("#"+content).jPages({
//        containerID : target
//    });
//
//}


$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';//货品编号
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    var code = '';//单据编号
    var overday = "4";//到期提醒类型
    var day = "100";//到期提醒天数
    var startDate = "";//入库开始时间
    var endDate = "";//入库结束时间
    var endStartDate = "";//到期开始时间
    var endEndDate = "";//到期结束时间
    var typeName = "";
    if($("#isTop").val()=='true' || $("#isEnd").val()=='true'){
        typeName = "1";
    }else {
        typeName = "2";
    }
    var checked_array = {};
    var outport_array = {};
    var goodsCheckedMap = new Map();

    checked_array.length = 0;
    var array = [];
    array[0] = goodsInfoName;
    array[1] = goodsInfoItemNo;
    array[2] = brandId;
    array[3] = goodsInfoAdded;
    array[4] = typreId;
    array[5] = thirdId;
    array[17] = typeName;
    array[11] = code;
    array[35] = overday;
    array[36] = day;
    array[37] = startDate;
    array[38] = endDate;
    array[39] = endStartDate;
    array[40] = endEndDate;

    $.datetimepicker.setLocale('ch');

    $(".overinput").show();
    $(".overspan").css("display",'inline-block');
    //ajaxPages(total_elements,'../web/api/inventory/getInventoryGoods','itemContainer','holder',0,5);
    ajaxPages('../web/api/inventory/warn','itemContainer','holder',11,5,'','',array,function(pageId){
        var thisType = $(this).parent().parent().parent().attr("data-type");
        var thisCheckedArray = goodsCheckedMap.get(thisType);
        if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
            $(".accountNumber").each(function () {
                var _this = this;
                thisCheckedArray[pageId].map(function(orderCode){
                    if($(_this).parent().prev().val() == orderCode){
                        $(_this).prop("checked","checked");
                    }
                });
            });
        }
    });
    var time =  setInterval(show,200);
    function show(){
        $(".goodsInfoId").each(function(){
            var pageIndex=$("#holder span.current").html();
            var _this = this;
            var thisType = $(this).parent().parent().attr("data-type");
            var thisCheckedArray = goodsCheckedMap.get(thisType);
            if( typeof (thisCheckedArray) !="undefined" && thisCheckedArray[pageIndex] != undefined ) {
                thisCheckedArray[pageIndex].map(function (itId) {
                    if ($(_this).val() == itId) {
                        $(_this).siblings().children(".accountNumber").prop("checked", "checked");
                    }
                });
            }
        })
    }
    $(document).on("click",".accountNumber",function(){
        var pageIndex=$("#holder span.current").html();
        var orderCode=$(this).parent().prev().val();
        var goodsche=$(this).parent().parent().parent().attr("data-type");
        var newChecked = [];
        var checkedIndex = goodsCheckedMap.get(goodsche);
        if(typeof (checkedIndex) == "undefined"){
            newChecked[pageIndex] = new Array();
            if($(this).is(":checked")){
                newChecked[pageIndex].push(orderCode);
                goodsCheckedMap.put(goodsche,newChecked);
                console.log(goodsCheckedMap.get(goodsche)[pageIndex]);
            }
            else{
                checkedIndex[pageIndex].map(function(code){
                    if( code == orderCode ){
                        checkedIndex[pageIndex] = checkedIndex[pageIndex].remove(code);
                    }
                })
            }
        }else {
            if(checkedIndex[pageIndex] == undefined){
                newChecked[pageIndex] = new Array();
                if($(this).is(":checked")){
                    newChecked[pageIndex].push(orderCode);
                    checkedIndex.push(newChecked[pageIndex]);
                    goodsCheckedMap.put(goodsche,checkedIndex);
                    console.log(goodsCheckedMap.get(goodsche)[pageIndex]);
                }
                else{
                    newChecked[pageIndex].map(function(code){
                        if( code == orderCode ){
                            newChecked[pageIndex] = newChecked[pageIndex].remove(code);
                        }
                    })
                }
            }else {
                if($(this).is(":checked")){
                    checkedIndex[pageIndex].push(orderCode);
                    goodsCheckedMap.put(goodsche,checkedIndex);
                    console.log(goodsCheckedMap.get(goodsche)[pageIndex]);
                }
                else{
                    checkedIndex[pageIndex].map(function(code){
                        if( code == orderCode ){
                            checkedIndex[pageIndex] = checkedIndex[pageIndex].remove(code);
                        }
                    })
                }
            }

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
        var vocherCode=$(this).siblings("input[name='billId']").val();
        var vocherState=$(this).siblings(".vocherState").val();
        if(vocherState == "报损单" || vocherState == "报溢单" ){
            window.open("reimburse?id=" + vocherCode + "&status=done");
        }
        else if(vocherState == "补货单"  ){
            window.open("replenishment?id=" + vocherCode + "&status=done") ;
        }
        else if(vocherState == "补货单(合并单)" ){
            window.open("replenishmentMega2?parentId=" + vocherCode + "&status=done" +  "&mega=" + 1) ;
        }
        else if(vocherState == "调拨单"  ){
            window.open("allocation?id=" + vocherCode + "&status=done") ;
        }
        else if(vocherState == "入库单"  ){
            window.open("storage?id=" + vocherCode + "&status=done") ;
        }
        else if(vocherState == "采购订单" ){
            window.open("purchaseOrder?purchaseId=" + vocherCode) ;
        }
        else if(vocherState == "采购入库单" ){
            window.open("inStorage?purchaseId=" + vocherCode + "&status=done") ;
        }
    });

    $("#updateInvenNumConfirm").click(function(){
        var str = {};

        var stime = '';
        var etime = '';
        var wtime = $('#change_warning_day').val();
        var goodsId = $("#goodsId").val();
        var stringDateStart = $('#change_datetimepicker_start').val();
        var stringDateEnt = $('#change_datetimepicker_end').val();

        if($("#oldWarning").val()!='' && stringDateStart=="" && stringDateEnt != ''){
            //wtime=$("#oldWarning").val();
            stringDateStart = $("#oldsTartTime").val();
        }
        if(stringDateStart=='' && stringDateEnt!='' && wtime!=''){
            stringDateStart = getNewDay(stringDateEnt, Number(wtime) - 1,'start');
        }
        if(stringDateEnt == '' && stringDateStart !='' && wtime!=''){
            stringDateEnt = getNewDay(stringDateStart, Number(wtime) - 1);
        }
        if(wtime == '' && stringDateStart!='' && stringDateEnt != ''){
            wtime = DateDiff(stringDateStart, stringDateEnt) + 1;
        }
        if(stringDateStart != ''){
            stime = new Date(stringDateStart);
        }
        if(stringDateEnt != ''){
            etime = new Date(stringDateEnt);
        }
        if(stringDateStart != '' && stringDateEnt != '' && stringDateStart > stringDateEnt){
            data_type_alert("生产日期应该小于到期日期","error")
        }
        else if((DateDiff(stringDateStart,stringDateEnt) + 1) != parseInt(wtime) && stringDateStart != '' &&stringDateEnt != '' && wtime != ''){
            data_type_alert("保质期与填写的实际有效期不符，请核对修改！","error")
        }
        else if(stringDateStart == '' && stringDateEnt == '' && wtime != ''){
            data_type_alert("请填写生产日期或者到期日期！","error")
        }else if(stringDateStart!='' && (stringDateStart > new Date().Format("yyyy-MM-dd"))){
            data_type_alert("生产日期不能大于当前时间！","error")
        }
        else{
            str.id = goodsId;
            if(stringDateStart != ''){
                str.productTime = stime;
            }
            if(stringDateEnt != ''){
                str.endTime = etime;
            }
            if(wtime != ''){
                str.endWarnDay = wtime;
            }


            $.post("../web/api/inventory/updateWarnDay", str,function(data){

                if( data.response == 'success' ){
                    //dj_readInputStream();
                    $('.updateInvenNum ').fadeOut(100);
                    $("#search").click();
                    discoverHtml();
                    data_type_alert("修改成功","success")

                    }
                else{
                    window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
                }
            },'json');
        }


    });

    $("#deleteComConfirm").click(function(){
        var goodsId = $("#goodsId").val();
        $.post("../web/api/inventory/delectWarn ",{
            id:goodsId
        },function(data){
            if( data.response == 'success' ){
                discoverHtml()
                $('.deleteCom').hide();
                $("#search").click();
                //ensure_alert('maturityWarning');
                //ajaxPages("../web/api/inventory/warn", "itemContainer", "holder", 11, 5, '', '', array,function(pageId){
                //    var thisType = $(this).attr("data-type");
                //    var thisCheckedArray = goodsCheckedMap.get(thisType);
                //    if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                //        $(".accountNumber").each(function () {
                //            var _this = this;
                //            thisCheckedArray[pageId].map(function(orderCode){
                //                if($(_this).parent().prev().val() == orderCode){
                //                    $(_this).prop("checked","checked");
                //                }
                //            });
                //        });
                //    }
                //});
            }
            else{
                window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
            }
        },'json');
    });

    $("#itemContainer").click(function(){
        if( $(".allOpenShow").children("span").html() == "关闭搜索项"){
            $(".allOpenShow").children("span").html("展开搜索项")
            $(".allOpenShow").children("b").html("∨")
            $(".allOpenShow").parent("div").css("height","50px")
            $(".allOpenShow").parent("div").css("box-shadow","none")
            $(".allOpenShow").siblings().fadeOut(800)
        }
    })

    //$("#search").click(function(){
    //    var code = $("input[name='memberNum']").val();//单据编号
    //    var goodsInfoItemNo = $("input[name='memberCust']").val();//货品编号
    //    var overday = $(".chosetime option:selected").val();//到期提醒类型
    //    var day = $('.overinput').val();//到期提醒天数
    //    var startDate = $("#in_datetimepicker_start").val();//入库开始时间
    //    var endDate = $('#in_datetimepicker_end').val();//入库结束时间
    //    var endStartDate = $('#over_datetimepicker_start').val();//到期开始时间
    //    var endEndDate = $('#over_datetimepicker_end').val();//到期结束时间
    //    var page = 1;
    //    var size = 10;
    //    var str = '';
    //    $.post("../web/api/inventory/warn",{
    //        code:code,
    //        goodsInfoItemNo:goodsInfoItemNo,
    //        day:day,
    //        startDate:startDate,
    //        endDate:endDate,
    //        endStartDate:endStartDate,
    //        endEndDate:endEndDate,
    //        page:page,
    //        size:size
    //    },function(data){
    //        if( data.response == 'success' ){
    //        }
    //        else{
    //            response_ensure_alert('error',data.data.text,function(){
    //            });
    //        }
    //    },'json');
    //    $(this).children("b").remove()
    //})
    $("#search").click(function(){

        goodsInfoName =$("input[name='memberNum']").val();
        code = $("input[name='memberNum']").val();//单据编号
        goodsInfoItemNo = $("input[name='memberCust']").val();//货品编号
        //overday = $(".chosetime option:selected").val();//到期提醒类型
        //day = $('.overinput').val();//到期提醒天数
        if($("#isTop").val()=='true'){
            typeName = $("#statusInput2").val();
        }
        day = '';
        startDate = $("#in_datetimepicker_start").val();//入库开始时间
        endDate = $('#in_datetimepicker_end').val();//入库结束时间
        endStartDate = $('#over_datetimepicker_start').val();//到期开始时间
        endEndDate = $('#over_datetimepicker_end').val();//到期结束时间

        var goodsInfoType = $(".goodsInfoType").val()
        $("#select_id").find("option:selected").text();
        overday = $('#statusInput').val();

        if(overday == '1' || overday == '2'){
            day = '';//到期提醒天数
        }else {
            day = $('.overinput').val();//到期提醒天数
        }
        if(goodsInfoType == "全部"){
            goodsInfoType =""
        }
        //判断入库时间搜索条件
        if(startDate > endDate && startDate != '' && endDate != ''){
            response_ensure_alert("error","入库结束时间不得小于入库开始时间");
        }
        //判断到期时间搜索条件
        if(endStartDate > endEndDate && endStartDate != '' && endEndDate != ''){
            response_ensure_alert("error","到期结束时间不得小于到期开始时间");
        }
        var array = [];
        array[1] = goodsInfoItemNo;
        array[11] = code;
        array[35] = overday;
        array[36] = day;
        array[37] = startDate;
        array[38] = endDate;
        array[39] = endStartDate;
        array[40] = endEndDate;
        array[17] = typeName;

        //array[21] = goodsInfoType
        ajaxPages("../web/api/inventory/warn", "itemContainer", "holder", 11, 5, '', '', array,function(pageId){
            var thisType = $(this).attr("data-type");
            var thisCheckedArray = goodsCheckedMap.get(thisType);
            if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                $(".accountNumber").each(function () {
                    var _this = this;
                    thisCheckedArray[pageId].map(function(orderCode){
                        if($(_this).parent().prev().val() == orderCode){
                            $(_this).prop("checked","checked");
                        }
                    });
                });
            }
        });


    });

    $(".select dd").click(function(){
        var secval=$(this).html();
        var secStr=$(this).data("typestr");
        var type = $(this).parent().prev().html();
        $(this).hide().siblings("dd").hide();
        $(this).siblings("dt").children("abbr").html(secval);
        $(this).siblings("dt").children("input").val(secStr);
        $(this).siblings(".arrow").css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");
        if($(this).parent().find('#status').length != 0){
            if(secStr == '1' || secStr == '2'){
                $(".overinput").hide();
                $(".overspan").hide();
            }else {
                $(".overinput").show();
                $(".overspan").css("display",'inline-block');
            }
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

    $("#search2").click(function(){
        checked_array = {};
        outport_array = {};
        checked_array.length = 0;
        var goodsInfoType = $(".goodsInfoType").val()
        if(goodsInfoType == "全部"){
            goodsInfoType =""
        }
        goodsInfoName = $("input[name='memberNum']").val();
        goodsInfoItemNo = $("input[name='memberCust']").val();
        if ($("#brand_select option:selected").val() == '全部') {
            brandId = '';
        }
        else {
            brandId = $("#brand_select option:selected").val();
        }
        if ($("#thirdname_select option:selected").val() == '全部') {
            thirdId = '';
        }
        else {
            thirdId = $("#thirdname_select option:selected").val();
        }


        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[2] = brandId;
        array[3] = goodsInfoAdded;
        array[4] = typreId;
        array[5] = thirdId;
        array[21] = goodsInfoType
        ajaxPages("../web/api/inventory/getWarningGoods", "itemContainer_warning", "holder_warning", 8, 5, '', '', array,function(pageId){
            var thisType = $(this).attr("data-type");
            var thisCheckedArray = goodsCheckedMap.get(thisType);
            if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                $(".accountNumber").each(function () {
                    var _this = this;
                    thisCheckedArray[pageId].map(function(orderCode){
                        if($(_this).parent().prev().val() == orderCode){
                            $(_this).prop("checked","checked");
                        }
                    });
                });
            }
        });

    });

    $("#outputAll").click(function(){
        var goodsInfoName = $("input[name='memberNum']").val();
        var goodsNumber = $("input[name='memberCust']").val();
        var goodsInfoType = $(".goodsInfoType").val()
        if(goodsInfoType == "全部"){
            goodsInfoType =""
        }
        var flag = $(".accOn").html();

        if( flag == '全部货品' ){
            window.location.href = '../web/api/exportExcel/inventoryGoodsDown?goodsInfoId=all' + '&goodsNumber=' + goodsNumber + '&goodsInfoName=' + goodsInfoName + '&goodsInfoType=' + goodsInfoType;
        }
        else{
            window.location.href = '../web/api/exportExcel/inventoryWarningGoodsDown?goodsInfoId=all' + '&goodsNumber=' + goodsNumber + '&goodsInfoName=' + goodsInfoName + '&goodsInfoType=' + goodsInfoType;
        }
    });

    $("#outputPart").click(function(){
        var flag = $(".accOn").html();
        var outType = $(".accOn").attr("value");

        var outArr = goodsCheckedMap.get(outType);
        var itemIds = '[';
        //for(var i = 0;i < outArr.length;i++){
        //    for(var j = 0;j < outport_array[i].length;j++){
        //        itemIds += '"' + outport_array[i][j] + '",';
        //    }
        //}
        for(var i=1;i<outArr.length;i++){
            for(var j=0; j < outArr[i].length;j++){
                itemIds += '"' + outArr[i][j] + '",';
            }
        }
        itemIds = itemIds.substring(0,itemIds.length - 1);
        itemIds += ']';

        console.log(itemIds);

        if(itemIds.length > 2){
            if( flag == '全部货品' ){
                window.location.href = '../web/api/exportExcel/inventoryGoodsDown?goodsInfoId=' + itemIds;
            }
            else{
                window.location.href = '../web/api/exportExcel/inventoryWarningGoodsDown?goodsInfoId=' + itemIds;
            }

        }
    });



    //日历初始化
    $("#in_date_start").click(function(e){
        $("#in_datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#in_datetimepicker_start").trigger("focus");
    });



    $("#in_datetimepicker_start").blur(function(){
        $("#in_datetimepicker_start").datetimepicker('destroy');
    });
    $("#in_date_end").click(function(e){
        $("#in_datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#in_datetimepicker_end").trigger("focus");
    });
    $("#in_datetimepicker_end").blur(function(){
        $("#in_datetimepicker_end").datetimepicker('destroy');
    });



    $("#over_date_start").click(function(e){
        $("#over_datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#over_datetimepicker_start").trigger("focus");
    });
    $("#over_datetimepicker_start").blur(function(){
        $("#over_datetimepicker_start").datetimepicker('destroy');
    });
    $("#over_date_end").click(function(e){
        $("#over_datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#over_datetimepicker_end").trigger("focus");
    });
    $("#over_datetimepicker_end").blur(function(){
        $("#over_datetimepicker_end").datetimepicker('destroy');
    });


    $("#change_date_start").click(function(e){
        $("#change_datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#change_datetimepicker_start").trigger("focus");
    });
    $("#change_datetimepicker_start").blur(function(){
        $("#change_datetimepicker_start").datetimepicker('destroy');
    });
    $("#change_date_end").click(function(e){
        $("#change_datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#change_datetimepicker_end").trigger("focus");
    });
    $("#change_datetimepicker_end").blur(function(){
        $("#change_datetimepicker_end").datetimepicker('destroy');
    });
});
function Map(){
    this.elements = new Array();

    //获取Map元素个数
    this.size = function() {
        return this.elements.length;
    },

        //判断Map是否为空
        this.isEmpty = function() {
            return (this.elements.length < 1);
        },

        //删除Map所有元素
        this.clear = function() {
            this.elements = new Array();
        },

        //向Map中增加元素（key, value)
        this.put = function(_key, _value) {
            if (this.containsKey(_key) == true) {
                if(this.containsValue(_value)){
                    if(this.remove(_key) == true){
                        this.elements.push( {
                            key : _key,
                            value : _value
                        });
                    }
                }else{
                    this.elements.push( {
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push( {
                    key : _key,
                    value : _value
                });
            }
        },

        //删除指定key的元素，成功返回true，失败返回false
        this.remove = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key){
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            }catch(e){
                bln = false;
            }
            return bln;
        },

        //获取指定key的元素值value，失败返回null
        this.get = function(_key) {
            try{
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        return this.elements[i].value;
                    }
                }
            }catch(e) {
                return null;
            }
        },

        //获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null
        this.element = function(_index) {
            if (_index < 0 || _index >= this.elements.length){
                return null;
            }
            return this.elements[_index];
        },

        //判断Map中是否含有指定key的元素
        this.containsKey = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key){
                        bln = true;
                    }
                }
            }catch(e) {
                bln = false;
            }
            return bln;
        },

        //判断Map中是否含有指定value的元素
        this.containsValue = function(_value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].value == _value){
                        bln = true;
                    }
                }
            }catch(e) {
                bln = false;
            }
            return bln;
        },

        //获取Map中所有key的数组（array）
        this.keys = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].key);
            }
            return arr;
        },

        //获取Map中所有value的数组（array）
        this.values = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].value);
            }
            return arr;
        };
};
//将时间格式yyyy/mm/dd转换为yyyy-mm-dd 00:00:00
function mydate(str)
{
    if(str != ''){
        var n = str;
        n += ' 00:00:00';
        return n;
    }
     if(str == ''){
        return str;
    }
}
//计算两天只差
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006/12/18格式
    var  aDate,  oDate1,  oDate2,  iDays;
    aDate  =  sDate1.split("-");
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);   //转换为12-18-2006格式
    aDate  =  sDate2.split("-");
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数
    return  iDays;
}

//日期加上天数得到新的日期
//dateTemp 需要参加计算的日期，days要添加的天数，返回新的日期，日期格式：YYYY-MM-DD
function getNewDay(dateTemp, days,type) {
    var dateTemp = dateTemp.split("/");
    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式
    var millSeconds;
    if(type == 'start'){
        millSeconds = Math.abs(nDate) - (days * 24 * 60 * 60 * 1000);
    }else {
        millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
    }
    var rDate = new Date(millSeconds);
    var year = rDate.getFullYear();
    var month = rDate.getMonth() + 1;
    if (month < 10) month = "0" + month;
    var date = rDate.getDate();
    if (date < 10) date = "0" + date;
    return (year + "-" + month + "-" + date);
}

//将时间格式yyyy/mm/dd转换为yyyy-mm-dd
function mydate2(str)
{
    var n = str.replace(/\//g,'-');
    return n;
}

//只允许输入大于零的正整数的正则函数
function onlyNum(obj){
    //var str = $(obj).val().replace(/[^1-9]/g,'');
    //$(obj).val(str);

    if($(obj).val().length == 1){
        $(obj).val($(obj).val().replace(/[^1-9]/g,''));
    }
    else{
        $(obj).val($(obj).val().replace(/\D/g,''))
    }
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

