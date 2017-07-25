$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var goodsInfoAdded = '';
    $(document).on("click","#addinvet",function(){
        coverHtml();
        $("#addIvent").show();
        goodsInfoAdded="1";
        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[3] = goodsInfoAdded;
        ajaxPages1("../web/api/inventory/getGoodsInventory","","itemContainer","holder",10,array);
    });
    $(document).on("click","#xx",function(){
        discoverHtml();
        $("#addIvent").hide();
    });
    $( "#addIvent" ).draggable();
    $(document).on("click","input[name='addinventcheck']",function(){
        if($(this).prop("checked")){
            $("#count").append($(this).parent().parent()[0].outerHTML);
        }
        else{
            $("#count").find("input[value='" + $(this).val() + "']").parent().parent().remove();
        }
    });
    $(document).on("blur",".change",function(){
        if(!/^[0-9]*[1-9][0-9]*$/.test($(this).val()) && $(this).val()!=""){
            response_ensure_alert("error","请输入正整数")
            $(this).val("")
        }
    })
    $("#searchGoods").click(function(){
        var goodsInfoName = $("input[name='item_name']").val();
        var goodsInfoItemNo = $("input[name='item_no']").val();
        var enterpriseId = $("#enterpriseId").val();
        ////清空count
        //$("#count").empty();
        var array = [];
        goodsInfoAdded = "1";
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[3] = goodsInfoAdded;
        ajaxPages1("../web/api/inventory/getGoodsInventory","","itemContainer","holder",10,array);
        //$.post("../web/api/inventory/getGoodsInventory",{
        //    goodsInfoName:goodsInfoName,
        //    goodsInfoItemNo:goodsInfoItemNo,
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
        //        jPagesGet("holder","itemContainer",5);
        //        //处理多余页
        //        var showElement = ( $("#holder a").length - 2 ) * 5 ;
        //        console.log(showElement)
        //        if( showElement >= ( data.data.totalElements + 5 ) ){
        //            $( $("#holder a")[$("#holder a").length - 2]).remove();
        //        }
        //        $("#holder").find("a").css("display","inline-block");
        //        $("#holder").find("span").css("display","inline-block");
        //        $("#holder").find(".jp-hidden").css("display","none");
        //        $("#itemContainer").empty();
        //        $("#holder a").click(function(){
        //            var page = '';
        //            if( $(this).html() == '＜' ){
        //                page = parseInt($(".jp-current").html()) - 1;
        //            }
        //            else if( $(this).html() == '＞' ){
        //                page = parseInt($(".jp-current").html()) + 1;
        //            }
        //            else{
        //                page = $(this).html();
        //            }
        //            $.post("../web/api/inventory/getGoodsInventory",{
        //                goodsInfoName:goodsInfoName,
        //                goodsInfoItemNo:goodsInfoItemNo,
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
        //                    if( length < 5 ){
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

    //申请数量判断
    $(document).on("keyup",".applyCount",function(){
        var availableInventoryCount = parseFloat($(this).parent().prev().html());
        var applyCount = $(this).val();
        var _this = this;
        if( /^[0-9]*$/.test(applyCount) ){
            $(_this).next().val("true");
        }
        else{
            var length = $(".xcConfirm").length;
            if( length < 1 ){
                response_ensure_alert("error","请输入正确的数字",function(){
                    console.log("输入非法" + consoleNowTime());
                    $(_this).next().val("false");
                });
            }
        }
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
            html += '<abbr>' + '<b class="goodsInfoPack">' + $(this).find("abbr:nth-child(6)").data("pack") + '</b>' + $(this).find("abbr:nth-child(6)").data("unit") + '/箱' + '</abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(6)").data("available") + '</abbr>';
            html += '<abbr><input type="text" class="applyCount"><input type="hidden"></abbr>';
            html += '</dd>';
        });
        $(".bodySecond dl dd").remove();
        $(".bodySecond dl").append(html);
        $("#addIvent").fadeOut(500);
        discoverHtml();
    });

    $("#commit").click(function(){
        coverHtml()
        var reason = $(".reasonForApply").val();
        var skuJson = getSkuJson();
        var countFlag = true;
        $(".applyCount").each(function(){
           if( $(this).val() == "0" ){
                countFlag = false;
           }
        });
        if( skuJson != false && countFlag == true  && reason != ''){
            $.post("../web/api/inventory/createBill",{
                type:'REPLENISHMENT',
                outId:'',
                inId:'',
                reason:reason,
                skuJson:skuJson
            },function(data){
                if( data.response == 'success' ){
                    response_ensure_alert("success","操作成功",function(){
                        localStorage['systemAction'] = 'refresh';
                        discoverHtml()
                        window.close();
                    });
                }else{
                    response_ensure_alert("error", data.data.text ,function(){
                        discoverHtml()
                    })

                }
            },'json');
        }
        else if( countFlag == false ){
            response_ensure_alert("error","补货数量不正确",function(){
               console.log("补货数量不正确" + new Date().toString());
                discoverHtml()
            });
        }
        else if( skuJson == false ){
            response_ensure_alert("error","补货商品不能为空",function(){
                console.log("补货商品不能为空" + new Date().toString())
                discoverHtml();
            });
        }
        else if( reason == '' ){
            response_ensure_alert("error","申请原因不能为空",function(){
                console.log("申请原因不能为空" + new Date().toString());
                discoverHtml()
            });
        }
    });

    //发货
    $("#repleSend").click(function(){
        coverHtml()
        var billId = $("#billId").val();
        $.post("../web/api/inventory/finishBill",{
            billId:billId
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","发货成功",function(){
                    window.location.href = 'allocation';
                    discoverHtml()
                })
            }else{
                data_type_alert( data.data.text ,"error")
                discoverHtml()
            }
        },'json');
    });
    //审核
    $(".approve").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        var countFlag = true;
        $(".change").each(function(){
            if( $(this).val() == "" ){
                countFlag = false;
            }
        });
        if(!countFlag){
            data_type_alert("审核数量不能为空","error")
        }
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".change").val() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/approveBill",{
            billId:billId,
            comment:comment,
            amountsJson:skuJson
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","已审核",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    //同意

    $("#createPurchase").click(function(){
        coverHtml()
        var billId = $("#billId").val();
        $.post("../web/api/inventory/createPurchaseBillByBillid",{
            billId:billId
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","采购订单已生成，您可在单据管理-采购订单中查看",function(){
                    window.close();
                    localStorage['systemAction'] = 'refresh';
                })
            }else{
                response_ensure_alert("error",data.data.text)
                discoverHtml()
            }
        },'json');
    });

//顶级
    $(".nodoupt").click(function(){
        var enterpriseId = $("#enterpriseId").val()
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".topSee").html() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/agreeBill",{
            billId:billId,
            comment:comment,
            skuJson:skuJson
        },function(data){
            if(data.response == 'success'){
              if(enterpriseId == 1){
                  var okArr = [];
                  okArr[0] = '生成采购订单';
                  okArr[1] = function(){
                      discoverHtml();
                      $.post('../web/api/inventory/createPurchaseBillByBillid',{
                          billId:billId
                      },function(data){
                          if(data.response == "success"){
                              response_ensure_alert("success","采购订单已生成，您可在单据管理-采购订单中查看",function(){
                                  window.close();
                                  localStorage['systemAction'] = 'refresh';
                              })
                          }else{
                              response_ensure_alert("error",data.data.text)
                          }

                      },'json');

                  };
                  var cancelArr = [];
                  cancelArr[0] = '下次再创建';
                  cancelArr[1] = function(){
                     /* $.post('../web/api/inventory/createNotTransderBill',{
                          billId:billId
                      })*/
                      discoverHtml();
                      window.close();
                      localStorage['systemAction'] = 'refresh';
                  };
                  selfPick_alert('success',"您已审批同意，请及时进行采购操作",okArr,cancelArr);
              }else{
                  var okArr = [];
                  okArr[0] = '立即创建调拨单';
                  okArr[1] = function(){
                      discoverHtml();
                      window.open("allocation?isOld=true" + "&billId="+ billId) ;
                      window.close();
                  };
                  var cancelArr = [];
                  cancelArr[0] = '下次再创建调拨单';
                  cancelArr[1] = function(){
                     /* $.post('../web/api/inventory/createNotTransderBill',{
                          billId:billId
                      })*/
                      discoverHtml();
                      window.close();
                  };
                  selfPick_alert('success',"您已审批同意，请及时进行调拨操作",okArr,cancelArr);
              }

            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $('.creatOrderImmidently').click(function(){
        var billId = $("#billId").val();
        $.post('../web/api/inventory/createPurchaseBillByBillid',{
            billId:billId
        },function(data) {
            if (data.response == "success") {
                response_ensure_alert("success", "采购订单已生成，您可在单据管理-采购订单中查看", function () {
                    window.close();
                    localStorage['systemAction'] = 'refresh';
                })
            } else {
                response_ensure_alert("error", data.data.text)
            }
        })
    })
    $('.creatOrderImmidently2').click(function(){
        var billId = $("#billId").val();
        window.open("allocation?isOld=true" + "&billId="+ billId) ;
        window.close()
        localStorage['systemAction'] = 'refresh';
    })
    //同意2  中间级
    $(".nodoupt2").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        var countFlag = true;
        $(".change").each(function(){
            if( $(this).val() == "" ){
                countFlag = false;
            }
        });
        if(!countFlag){
            data_type_alert("审核数量不能为空","error")
        }
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".change").val() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/agreeBill",{
            billId:billId,
            comment:comment,
            skuJson:skuJson
        },function(data){
            if(data.response == 'success'){
                var okArr = [];
                okArr[0] = '立即创建调拨单';
                okArr[1] = function(){
                    discoverHtml();
                    window.open("allocation?isOld=true" + "&billId="+ billId) ;
                };
                var cancelArr = [];
                cancelArr[0] = '下次再创建调拨单';
                cancelArr[1] = function(){
                   /* $.post('../web/api/inventory/createNotTransderBill',{
                        billId:billId
                    })*/
                    discoverHtml();
                    window.close();
                };
                selfPick_alert('success',"您已审批同意，请及时进行调拨操作",okArr,cancelArr);
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $("#createAllocation").click(function(){
        var billId = $("#billId").val();
        $.post("../web/api/inventory/setBillRead",{
            billId:billId,
            readFlag:true
        },function(data){
            if( data.response == 'success' ){


            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
        window.open("allocation?isOld=true" + "&billId="+ billId) ;
        $(this).remove()
    })

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

    $(".topButton").click(function(){
        localStorage['systemAction'] = 'refresh';
    });
});
function ajaxPages(total,url,contentStr,target,perpage,enterpriseId){
    for(var i = 0;i < total;i++){
        var html = get_html();
        $("#"+contentStr).append(html);
    }
    jPagesGet(target,contentStr);
    //处理多余页
    var calPage = 1;
    if( $("#" + target + " a").length - 2 < 0 ){
        calPage = 1;
    }
    else{
        calPage = $("#" + target + " a").length - 2;
    }
    var showElement = calPage * perpage ;
    if( showElement >= ( total ) ){
        $( $("#" + target + " a")[$("#" + target + " a").length - 2]).html($( $("#" + target + " a")[$("#" + target + " a").length - 3]).html());
        $( $("#" + target + " a")[$("#" + target + " a").length - 3]).remove();
    }

    $("#"+contentStr).empty();
    $("a").click(function(){

        var page = '';
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
            $.post(url,{
                "page":page,
                "size":perpage,
                "enterpriseId":enterpriseId
            },function(data){
                if(data.response == 'success'){
                    goodsChoose_appendHtml(contentStr,page,total,data.data.content,10);
                }
            },'json');
        }
    });
    $("#holder").children()[1].click();
}
function ajaxPages1(url,enterpriseId,contentStr,target,perpage,array){
    if( array != undefined ){
        var goodsInfoName = array[0];
        var goodsInfoItemNo = array[1];
        var goodsInfoAdded = array[3];
    }
    $.post(url,{
        "page":1,
        "size":perpage,
        "enterpriseId":enterpriseId,
        "goodsInfoName":goodsInfoName,
        "goodsInfoType":0,
        "goodsInfoItemNo":goodsInfoItemNo,
        "goodsInfoAdded":goodsInfoAdded
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
                            "goodsInfoType":0,
                            "goodsInfoItemNo":goodsInfoItemNo,
                            "goodsInfoAdded":"1"
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
        html += '<abbr><input type="hidden"><input type="checkbox" name="addinventcheck"> </abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
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
            //可用库存
            if( flag ){
                allCheckedCount++;
                html += '<abbr><input type="hidden" value="' + data[i].available + '" class="available"><input name="addinventcheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            else{
                html += '<abbr><input name="addinventcheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            html += '<abbr title="'+ data[i].goodsInfoName +'">' + data[i].goodsInfoName + '</abbr>';
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
            html += '<abbr class="invenSeller" data-available="' + data[i].available + '" data-unit="' + handleUndefined(data[i].goodsInfoUnit) + '" data-pack="' + handleUndefined(data[i].goodsInfoPack) + '">' + data[i].goodsMerchants + '</abbr>' ;
            html += '</abbr>';
            html += "</dd>";
            $("#"+ str).append(html);
        }
        catch(e){

        }

        if( allCheckedCount == perpage ){
            $("input[name='addinventcheckDT']").prop("checked","checked");
        }
        else{
            $("input[name='addinventcheckDT']").prop("checked","");
        }
    }

}
function goodsSearch_appendHtml(str,pageIndex,totalPage,data,perpage){
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
            //可用库存
            if( flag ){
                allCheckedCount++;
                html += '<abbr><input type="hidden" value="' + handleUndefined(data[i].available) + '" class="available"><input name="addinventcheck" checked="checked" type="checkbox" value="' + handleUndefined(data[i].goodsInfoId) + '"/></abbr>';
            }
            else{
                html += '<abbr><input name="addinventcheck" type="checkbox" value="' + handleUndefined(data[i].goodsInfoId) + '"/></abbr>';
            }
            html += '<abbr title="'+ data[i].goodsInfoName +'">' + data[i].goodsInfoName + '</abbr>';
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
            html += '<abbr class="invenSeller" data-available="' + data[i].available + '" data-unit="' + handleUndefined(data[i].goodsInfoUnit) + '" data-pack="' + handleUndefined(data[i].goodsInfoPack) + '">' + data[i].goodsMerchants + '</abbr>' ;
            html += '</abbr>';
            html += "</dd>";
        }
        catch(e){

        }
        $("#"+ str).append(html);
        if( allCheckedCount == perpage ){
            $("input[name='addinventcheckDT']").prop("checked","checked");
        }
        else{
            $("input[name='addinventcheckDT']").prop("checked","");
        }
    }
}
function jPagesGet(content,target){
    $("#"+content).jPages({
        containerID : target,
        perPage:10
    });

}

function getSkuJson(){
    var skuJson = '{';
    console.log('123');
    if( $(".bodySecond dd").length > 0 ){
        $(".bodySecond dd").each(function(){
            if( $(this).find("input[type='text']").val() != '' ){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find("input[type='text']").val() + ',';
                console.log( $(this).find("input[type='text']").val() );
            }
            else{
                skuJson = false;
            }

        });
        if( skuJson != false ){
            skuJson = skuJson.substring(0,skuJson.length-1);
            skuJson += '}';
        }
        console.log(skuJson);
        return skuJson;

    }else{
        return false;
    }
}1