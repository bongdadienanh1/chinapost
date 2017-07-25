$(document).ready(function(){
    $.datetimepicker.setLocale('ch');
    var inId = "";
    var outId = $(".choosenIntOut").data("id");
    $(document).on("click","#addinvet",function(){
        $("#cover1").show();
        $("#addIvent").show();
        var enterpriseId = $(".choosenIntOut").data("id");
        ajaxPages("../web/api/inventory/getOutInventoryGoods",enterpriseId,'itemContainer','holder',10);
    });
    $(document).on("click","#xx",function(){
        $("#cover1").hide();
        $("#addIvent").hide();
    });
    $( "#addIvent" ).draggable();
    $(".DepotDetail a").each(function(){
        if($(this).html()=="+"){
            $(this).siblings("ul").hide();
        }
    });
    $(".chooeseDepot div:first-child i,#chooseCancel").click(function(){
        $("#cover1").hide();
        $(".chooeseDepot").hide();
    });

    $(document).on("click",".chooeseDepotOut li a.chooseAvailable",function(){
        var _this = this;
        if( $(this).html() == '+' && $(this).parent().find("ul").length == 0){
            var enterpriseId = $(this).data("id");
            $.post("../web/api/inventory/getSonEnterpriseInfo",{
                enterpriseId:enterpriseId
            },function(data){
                if( data.response == 'success' ){
                    var html = '<ul>';
                    data.data.map(function(object){
                        html += '<li>';
                        html += '<a data-id="' + object.id + '" style="display:none;"></a>';
                        if( object.end == false ){
                            html += '<span>' + object.enterpriseName + '</span>';
                        }
                        else{
                            html += '<input type="radio" name="chooeseDepotOut"/><span>' + object.enterpriseName + '</span>';
                        }

                        html += '</li>';
                    });
                    html += '</ul>';
                    $(_this).parent().append(html);
                }
                else{

                }
            },'json');
        }
    });




    $(document).on("click",".chooeseDepot li a",function(){
        console.log(1);
        var _this = this;
        if( $(this).html() == '+' ){
            var enterpriseId = $(this).data("id");
            $.post("../web/api/inventory/getSonEnterpriseInfo",{
                enterpriseId:enterpriseId
            },function(data){
                if( data.response == 'success' ){
                    var html = '<ul>';
                    data.data.map(function(object){
                        html += '<li>'
                        if( object.end == true ){
                            html += '<a data-id="' + object.id + '">-</a>';
                        }
                        else{
                            html += '<a data-id="' + object.id + '">+</a>';
                        }
                        if( object.end == false ){
                            html += '<span>' + object.enterpriseName + '</span>';
                        }
                        else{
                            html += '<input type="radio" name="chooeseDepotOut"/><span>' + object.enterpriseName + '</span>';
                        }
                        html += '</li>';
                    });
                    html += '</ul>';
                    $(_this).siblings("ul").remove();
                    $(_this).parent().append(html);
                }
                else{

                }
            },'json');
        }
    });

    $(document).on("click",'.DepotDetail a',function(){
        if( $(this).html( ) == "+" ){
            $(this).siblings("ul").slideDown();
            $(this).html("-");
        }else if( $(this).html( ) == "-" ){
            $(this).siblings("ul").slideUp();
            $(this).html("+");
        }
    });
    $("#choosenInt").click(function(){
        $("#cover1").show();
        $(".chooeseDepot").show();
    });
    $("#chooseConfirm").click(function(){
        $("#cover1").hide();
        var dataid =  $("input[name='chooeseDepotOut']:checked").siblings("a").data("id");
        $("#choosenInt").html($("input[name='chooeseDepotOut']:checked").siblings("span").html());
        inId = dataid;
        $("#choosenInt").data("id",dataid)
        $(".chooeseDepot").hide();
    });
    $(".chooeseDepotOut div:first-child i,#chooseCancelOut").click(function(){
        $("#cover1").hide();
        $(".chooeseDepotOut").hide();
    });
    $("#choosenIntOut").click(function(){
        $.post('../web/api/inventory/');
        $("#cover1").show();
        $(".chooeseDepotOut").show();
    });
    $("#chooseConfirmOut").click(function(){
        $("#cover1").hide();
        var dataid =  $("input[name='chooeseDepotOut']:checked").siblings("a").data("id");
        $("#choosenIntOut").html($("input[name='chooeseDepotOut']:checked").siblings("span").html());
        outId = dataid;
        $(".chooeseDepotOut").hide();
        $("#choosenIntOut").data("id",dataid);
        var goodsJson = '['
        $(".bodySecond dd").each(function(){
            goodsJson += $(this).data("id") + ','
        })
        goodsJson = goodsJson.substring(0,goodsJson.length -1)
        goodsJson += ']'
        $.post("../web/api/inventory/getGoodsAndInventory",{
            enterpriseId:dataid,
            goodsJson:goodsJson
        },function(data){
            if(data.response == 'success'){
                  for(var i = 0 ; i <=  $(".available").length - 1 ; i++){
                      $(".available").eq(i).html( data.data[i].available)
                  }
                for(var i = 0 ; i <=  $(".oldAvailable").length - 1 ; i++){
                    $(".oldAvailable").eq(i).attr("data-available",data.data[i].available)
                }

            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $(document).on("click","input[name='addinventcheck']",function(){
        if($(this).prop("checked")){
            $("#count").append($(this).parent().parent()[0].outerHTML);
        }
        else{
            $("#count").find("input[value='" + $(this).val() + "']").parent().parent().remove();
        }
    });

    $("#searchGoods").click(function(){
        var goodsInfoName = $("input[name='item_name']").val();
        var goodsInfoItemNo = $("input[name='item_no']").val();
        var enterpriseId = $(".choosenIntOut").data("id");
        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        ajaxPages("../web/api/inventory/getOutInventoryGoods",enterpriseId,"itemContainer","holder",10,array);
        ////清空count
        //$("#count").empty();
        //
        //$.post("../web/api/inventory/getOutInventoryGoods",{
        //    goodsInfoName:goodsInfoName,
        //    goodsInfoItemNo:goodsInfoItemNo,
        //    enterpriseId:enterpriseId,
        //    brandId:'',
        //    thirdId:'',
        //    page:1,
        //    size:10
        //},function(data){
        //    if( data.response == 'success' ){
        //        var total = data.data.totalElements;
        //        for(var i = 0;i < total;i++){
        //            var html = get_html(1);
        //            $("#itemContainer").append(html);
        //        }
        //        jPagesGet("holder","itemContainer",10);
        //        //处理多余页
        //        var showElement = ( $("#holder a").length - 2 ) * 5 ;
        //        console.log(showElement,data.data.totalElements + 5);
        //        if( showElement >= ( data.data.totalElements + 5 ) ){
        //            $( $("#holder a")[$("#holder a").length - 2]).remove();
        //        }
        //        $("#holder").find("a").css("display","inline-block");
        //        $("#holder").find("span").css("display","inline-block");
        //        $("#holder").find(".jp-hidden").css("display","none");
        //        $("#itemContainer").empty();
        //        $("#holder a").click(function(){
        //            var enterpriseId = $(".choosenIntOut").data("id");
        //            var page = '';
        //            if( $(this).html() == '上一页' ){
        //                page = parseInt($(".jp-current").html()) - 1;
        //            }
        //            else if( $(this).html() == '下一页' ){
        //                page = parseInt($(".jp-current").html()) + 1;
        //            }
        //            else{
        //                console.log(1);
        //                page = $(this).html();
        //            }
        //            $.post("../web/api/inventory/getOutInventoryGoods",{
        //                goodsInfoName:goodsInfoName,
        //                goodsInfoItemNo:goodsInfoItemNo,
        //                enterpriseId:enterpriseId,
        //                brandId:'',
        //                thirdId:'',
        //                page:page,
        //                size:10
        //            },function(data){
        //                if( data.response == 'success' ){
        //                    var length = data.data.content.length;
        //                    $("#itemContainer").empty();
        //                    for(var i = 0;i < length;i++){
        //                        var html = get_html(1);
        //                        $("#itemContainer").append(html);
        //                    }
        //                    //jPagesGet("holder","itemContainer",5);
        //                    if( length < 10 ){
        //                        console.log(length);
        //                        goodsSearch_appendHtml("itemContainer",page,length,data.data.content,10);
        //                    }
        //                    else{
        //                        goodsSearch_appendHtml("itemContainer",page,data.data.size,data.data.content,10);
        //                    }
        //                }
        //            },'json');
        //        });
        //        $("#holder").children()[1].click();
        //    }
        //},'json');
    });

    //删除
    $(".delete").click(function(){
        $(".bodySecond input[type='checkbox']:checked").each(function(){
            var id = $(this).parent().parent().data("id");
            $(this).parent().parent().remove();
            $("#count dd").each(function(){
                if( $(this).find("input[name='addinventcheck']").val() == id ){
                    $(this).remove();
                }
            });
        });

        var count = 1;
        $(".bodySecond input[type='checkbox']").each(function(){
            $(this).next().html(count);
            count++;
        });
    });

    $("#commitGoods").click(function(){
        var length = 0;
        var html = '';
        $("#count dd").each(function(){
            length++;
            html += '<dd data-id="' + $(this).find("input[type='checkbox']").val() + '">';
            html += '<abbr><input type="checkbox" name="invetcheck"/><b>' + length + '</b></abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(2)").html() + '</abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(3)").html() + '</abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(4)").html() + '</abbr>';
            html += '<abbr class="available">' + $(this).find("abbr:nth-child(6)").data("available") + '</abbr>';
            if($(this).data("checkedamount") != undefined){
                html += '<abbr><input type="text" value="'+  $(this).data("checkedamount") +'"> </abbr>';
            }else{
                html += '<abbr><input type="text" value=""> </abbr>';
            }
            html += '</dd>';
        });
            $(".bodySecond dl dd").remove();
            $(".bodySecond dl").append(html);

        var abbrLength = $(".clearWidth dt").find("abbr");
        var allAbbr = $(".clearWidth").find("abbr");
        if(abbrLength.length == 6){
            allAbbr.each(function(){
                $(this).css("width","176px");
            })
        }

        $("#addIvent").fadeOut(500);
        discoverHtml();
    });

    $("#commit").click(function(){
        coverHtml()
        var billId = $("#billId2").val()
        var reason = $(".reasonForApply").val();
        var flag = true;
        var inId = $("#choosenInt").data("id");
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                console.log(this);
                if( $(this).find("input[type='text']").val() != ''){
                    skuJson += '"' + $(this).data("id") + '":' + $(this).find("input[type='text']").val() + ',';
                    console.log( $(this).find("input[type='text']").val() );
                }
                else{
                    flag = false;
                }
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
            skuJson += '}';
            console.log(flag);
            if(inId != '' && outId != inId && flag == true && reason != '') {
                $.post("../web/api/inventory/createBill", {
                    type: 'INVENTORY_TRANSFER',
                    outId: outId,
                    inId: inId,
                    reason: reason,
                    skuJson: skuJson,
                    billId:billId
                }, function (data) {
                    if (data.response == 'success') {
                        discoverHtml()
                        response_ensure_alert("success", "提交成功", function () {
                            localStorage['systemAction'] = 'refresh';
                            window.close();
                        });
                    } else {
                        discoverHtml()
                        response_ensure_alert('error',data.data.text,function(){
                            console.log("创建失败" + new Date().toString());
                        });
                    }
                }, 'json');
            }
            else if( inId == '' ){
                discoverHtml()
                response_ensure_alert("error","调入仓库不能为空",function(){
                    console.log("调入仓库为空" + new Date().toString());
                });
            }
            else if( outId == inId ){
                discoverHtml()
                data_type_alert("调出仓库和调入仓库不能为同一个", "error");
            }
            else if( flag == false ){
                discoverHtml()
                response_ensure_alert("error","调拨数量不能为空",function(){
                   console.log("调拨数量为空" + new Date().toString());
                });
            }
            else if( reason == '' ){
                discoverHtml()
                response_ensure_alert("error","调拨原因不能为空",function(){
                    console.log("调拨原因不能为空" + new Date().toString());
                });
            }
        }else{
            discoverHtml()
            data_type_alert("没有选择货品","error")
        }


    });
    $( ".importLog2" ).draggable();
    $(".importLog2 h1 i").click(function(){
        console.log(1111111)
        $(".pwd").hide()
        $(".importLog2").hide()
    })
    //发货
    $("#repleSend").click(function(){
        coverHtml()
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".outNum").html() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/deliveryBill",{
            billId:billId,
            comment:comment,
            skuJson:skuJson
        },function(data){
            if(data.response == 'success'){
                discoverHtml()
                response_ensure_alert("success","发货成功",function(){
                    window.close();
                });
            }
            else if( data.data.errorcode == 14 ){
                discoverHtml()
                $(".importLog2").show();
                var errLogHtml2 =""
                var arg = data.data.extendMsg
                console.log(arg)
                for(var i in arg ){
                    errLogHtml2 += '<li>';
                    errLogHtml2 += '<div>'+ i + "  :  " + "<b style='color: #ff3300;'>" + arg[i] + "</b>" + '</div>';
                    errLogHtml2 += '</li>';
                    console.log(i,arg[i]);
                }

                $(".importLog2 ul").empty().append(errLogHtml2);

            }else{
                discoverHtml()
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $("#repleSend2").click(function(){
        coverHtml()
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".change").val() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/deliveryBill",{
            billId:billId,
            comment:comment,
            skuJson:skuJson
        },function(data){
            if(data.response == 'success'){
                discoverHtml()
                response_ensure_alert("success","发货成功",function(){
                    window.close();
                });
            }else{
                discoverHtml()
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $(document).on("input",".thisCheck",function(){
        var val = $(this).val()
        var _this = this
        if(val != ""){
            if( !/^[0-9]*[1-9][0-9]*$/.test(val)){
                response_ensure_alert("error","请输入数字",function(){
                    $(_this).focus()
                },function(){
                    $(_this).focus()
                })
                $(this).blur()
                $(this).val("")
            }

            if( $(this).parent().siblings(".receiptAmount").html() == undefined){
                var checkVal = parseInt($(this).parent().siblings(".outNum").html())
            }else{
                var checkVal = parseInt($(this).parent().siblings(".outNum").html()) -  parseInt($(this).parent().siblings(".receiptAmount").html())
            }
            console.log(checkVal)
            if( parseInt(val) > checkVal){
                response_ensure_alert("error","本次收货数量不能大于调拨数量",function(){
                    $(_this).focus()
                },function(){
                    $(_this).focus()
                })
                $(this).blur()
                $(this).val("")
            }
        }
    })
    //收货
    $("#repleReceive").click(function(){
        $('.warn-info').remove();
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = "[";
        var type1 = true;
        var type2 = true;
        var type3 = true;
        var type4 = true;



        $(".thisGoodsInfoId").each(function(){
            var stime = $(this).parent().next().find('.start-time');
            var etime = $(this).parent().next().next().find('.end-time');
            var wtime = $(this).parent().next().next().next().find('.warning-time');


            //生产日期小于到期日期报错
            if(stime.val() != '' && etime.val() != '' && stime.val() > etime.val() ){
                //var htm = '';
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 16px;left: 8px">生产日期应该小于到期日期</span>';
                //stime.parent().append(htm);
                stime.css('border-color','#f56b6b');
                type1 = false;
                return false;
            }
            //只填写了保质期报错
            if(stime.val() == '' && etime.val() == '' && wtime.val() != ''){
                //var htm = '';
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 16px;left: 8px">请填写生产日期或者到期日期</span>';
                //stime.parent().append(htm);
                stime.css('border-color','#f56b6b');
                type2 = false;
                return false;
            }
            //开始日期不能大于当前时间
            if(stime.val()!='' && (stime.val() > new Date().Format("yyyy/MM/dd"))){
                //var htm = '';
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 8px;left: 16px">生产日期不能大于当前时间</span>';
                //stime.parent().append(htm);
                stime.css('border-color','#f56b6b');
                type3 = false;
                return false;
            }
            if(stime.val() != '' && etime.val() != '' && (DateDiff(stime.val(), etime.val()) + 1) != parseInt(wtime.val())){
                //var htm = '';
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 8px;left: 16px">生产日期不能大于当前时间</span>';
                //stime.parent().append(htm);
                wtime.css('border-color','#f56b6b');
                type4 = false;
                return false;
            }
            if( $(this).siblings(".thisCheck").val() != ""){
                skuJson +=  '{';
                skuJson += '"goodsInfoId":' + $(this).val() + ','+ '"amount":' + $(this).siblings(".thisCheck").val()  + ',';

                if( stime.val() != ''){
                    skuJson +=  '"productTime":'+'"'+ mydate(stime.val()) + '"' + ',';
                }
                if( etime.val() != ''){
                    skuJson += '"endTime":' + '"' + mydate(etime.val()) + '"' + ',';
                }
                if(wtime.val() != ''){
                    skuJson += '"endWarnDay":' + wtime.val() + ',';
                }
                skuJson = skuJson.substring(0,skuJson.length - 1);//删除数据最后的一个逗号
                skuJson +=  '},';
            }

        })
        //if(type1){
        //    discoverHtml();
        //    data_type_alert("生产日期应该小于到期日期",'error');
        //}
        //if(type2){
        //    discoverHtml();
        //    data_type_alert("请填写生产日期或者到期日期",'error');
        //}
        skuJson = skuJson.substring(0,skuJson.length - 1);

        skuJson += ']';
        if(skuJson == ']' ){
            skuJson = skuJson.substring(0,skuJson.length - 1);
        }
        if(!type1){
            discoverHtml();
            data_type_alert("生产日期应该小于到期日期",'error');
        }else if(!type2){
            discoverHtml();
            data_type_alert("请填写生产日期或者到期日期",'error');
        }else if(!type3){
            discoverHtml();
            data_type_alert("生产日期不能大于当前时间",'error');
        }else if(!type4){
            discoverHtml();
            data_type_alert("保质期不合法",'error');
        }else if(skuJson ==''){
            //没有填写收货数量报错
            discoverHtml();
            data_type_alert("请填写本次收货数量",'error');
        }else {
            $.post("../web/api/inventory/receiptBill",{
                billId:billId,
                comment:comment,
                skuJson:skuJson
            },function(data){
                if(data.response == 'success'){
                    response_ensure_alert("success","收货成功",function(){
                        window.close();
                    });
                }else{
                    data_type_alert( data.data.text ,"error")
                }
            },'json');
        }



    });
    //退回
    $("#repleBack").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        $.post("../web/api/inventory/backBill",{
            billId:billId,
            comment:comment
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","退回",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    //终止
    $(".stop").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        $.post("../web/api/inventory/terminateBill",{
            billId:billId,
            comment:comment
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","终止成功",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $(".topButton").click(function(){
        localStorage['systemAction'] = 'refresh';
    });
});

function ajaxPages(url,enterpriseId,contentStr,target,perpage,array){
    if( array != undefined ){
        var goodsInfoName = array[0];
        var goodsInfoItemNo = array[1];
    }
    $.post(url,{
        "page":1,
        "size":perpage,
        "enterpriseId":enterpriseId,
        "goodsInfoName":goodsInfoName,
        "goodsInfoItemNo":goodsInfoItemNo
    },function(data){
        if(data.response == 'success'){
            var total = data.data.totalElements;
            for(var i = 0;i < total;i++){
                var html = get_html();
                $("#"+contentStr).append(html);
            }
            jPagesGet(target,contentStr);
            if( total < perpage ){
                goodsChoose_appendHtml(contentStr,1,total,data.data.content,total);
            }
            else{
                goodsChoose_appendHtml(contentStr,1,total,data.data.content,perpage);
            }
            //处理多余页
            var calPage = 1;
            if( $("#" + target + " a").length - 2 < 0 ){
                calPage = 1;
            }
            else{
                calPage = $("#" + target + " a").length - 2;
            }
            var showElement = calPage * perpage ;
            if( showElement >= ( data.data.totalElements + perpage) ){
                $( $("#" + target + " a")[$("#" + target + " a").length - 2]).html($( $("#" + target + " a")[$("#" + target + " a").length - 3]).html());
                $( $("#" + target + " a")[$("#" + target + " a").length - 3]).remove();
            }
            $("a").unbind("click");
            $("a").click(function(){
                var page = '';
                console.log($(this).html());
                if( $(this).html() == '＜' ){
                    page = parseInt($(".jp-current").html()) - 1;
                    if( page ==  $("#" + target + " a").length - 3 ){
                        $($("#" + target + " a")[$("#" + target + " a").length - 3]).addClass("jp-current");
                        $($("#" + target + " a")[$("#" + target + " a").length - 2]).attr("class","");
                    }
                }
                else if( $(this).html() == '＞' ){
                    page = parseInt($(".jp-current").html()) + 1;
                    if( page ==  $("#" + target + " a").length - 2){
                        $(this).prev().addClass("jp-current");
                        $($("#" + target + " a")[$("#" + target + " a").length - 3]).attr("class","");
                    }

                }
                else{
                    page = $(this).html();
                }
                if( page > $("#" + target + " a").length - 2 || page < 1){

                }
                else{
                    if( !isNaN(page) ){
                        $.post(url,{
                            "page":page,
                            "size":perpage,
                            "enterpriseId":enterpriseId,
                            "goodsInfoName":goodsInfoName,
                            "goodsInfoItemNo":goodsInfoItemNo
                        },function(data){
                            if(data.response == 'success'){
                                goodsChoose_appendHtml(contentStr,page,total,data.data.content,10);
                            }
                        },'json');
                    }
                }

            });
        }
    },'json');

}
function get_html(){
    var html = '';
    html = "<dd>";
    html += '<abbr><input type="checkbox" name="addinventcheck"> </abbr>';
    html += '<abbr>非尼膜属高透iPhone5</abbr>';
    html += '<abbr>颜色：红色</abbr>';
    html += '<abbr>0270301010007</abbr>';
    html += '<abbr>ma</abbr>';
    html += '<abbr>邮政</abbr>';
    html += "</dd>";
    return html;
}

function goodsChoose_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    var allCheckedCount = 0;
    for(var i=0;i<perpage;i++){
        var flag = 0;
        var html = "<dd>";
        var length = $("#count input[type='checkbox']").length;
        if( length > 0 ){
            for(var j = 0;j < length; j++){
                var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
                if( data[i].goodsInfoId == id ){
                    flag = 1;
                }
            }
        }
        try{
            if( flag ){
                allCheckedCount++;
                html += '<abbr><input name="addinventcheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            else{
                html += '<abbr><input name="addinventcheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            html += '<abbr title="'+data[i].goodsInfoName+'">' + data[i].goodsInfoName + '</abbr>';
            //html += '<abbr class="invenName" style="padding-top: 0!important;">' + data[i].goodsInfoName + '</abbr>';

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for ( var j = 0; j < array.length;j++){
                var text= /(?:null)/;
                var flag = text.test(array[j]);
                if ( !flag ){
                    h = array[j];
                    if( array.length > 1 ){
                        h += '...';
                    }
                    break;
                }

            }
            for(var a = 0;a < array.length;a++){
                var text= /(?:null)/;
                var flag = text.test(array[a]);
                if ( !flag ){
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';

            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller" data-available="' + data[i].available + '">' + data[i].goodsMerchants + '</abbr>' ;
            html += '</abbr>';
            html += "</dd>";

            $("#"+ str).append(html);
            if( allCheckedCount == perpage ){
                $("input[name='addinventcheckDT']").prop("checked","checked");
            }
            else{
                $("input[name='addinventcheckDT']").prop("checked","");
            }
        }
        catch(e){

        }

    }

}
function goodsSearch_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i<perpage;i++){
        var flag = 0;
        var html = "<dd>";
        var length = $("#count input[type='checkbox']").length;
        if( length > 0 ){
            for(var j = 0;j < length; j++){
                var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
                if( data[i].goodsInfoId == id ){
                    flag = 1;
                }
            }
        }

        try{
            if( flag ){
                html += '<abbr><input name="addinventcheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            else{
                html += '<abbr><input name="addinventcheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }

            html += '<abbr title="'+data[i].goodsInfoName+'">' + data[i].goodsInfoName + '</abbr>';

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for ( var j = 0; j < array.length;j++){
                var text= /(?:null)/;
                var flag = text.test(array[j]);
                if ( !flag ){
                    h = array[j];
                    if( array.length > 1 ){
                        h += '...';
                    }
                    break;
                }

            }
            for(var a = 0;a < array.length;a++){
                var text= /(?:null)/;
                var flag = text.test(array[a]);
                if ( !flag ){
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller" data-available="' + data[i].available + '">' + data[i].goodsMerchants + '</abbr>' ;
            html += '</abbr>';
            html += "</dd>";
            $("#"+ str).append(html);
        }
        catch(e){

        }
    }

}

function jPagesGet(content,target){
    $("#"+content).jPages({
        containerID : target,
        perPage:10
    });

}

//将时间格式yyyy/mm/dd转换为yyyy-mm-dd 00:00:00
function mydate(str)
{
    if(str != ''){
        var n = str.replace(/\//g,'-');
        n += ' 00:00:00';
        return n;
    }
    else{
        return str;
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

//计算两天只差
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006/12/18格式
    var  aDate,  oDate1,  oDate2,  iDays
    aDate  =  sDate1.split("/")
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
    aDate  =  sDate2.split("/")
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
    return  iDays;
}