$(document).ready(function(){
    $.datetimepicker.setLocale('ch')
    var orderNo = '';
    var status = '';
    var username = '';
    var receiver = '';
    var contactPhone = '';
    var createStart = '';
    var createEnd = '';
    var payStart = '';
    var payEnd = '';
    var selfPick="";
    var enterpriseId = eid;
    var array = [];
    var checked_array = {
        length:0
    };
    var outport_array = {};
    var itemIds = '[';

    array[0] = orderNo;
    array[1] = status;
    array[2] = username;
    array[3] = receiver;
    array[4] = contactPhone;
    array[5] = createStart;
    array[6] = createEnd;
    array[7] = payStart;
    array[8] = payEnd;
    array[9] = selfPick;
    array[10] = enterpriseId;
    jPages("../web/api/ordermanage/getOrders","itemContainer_All","holder_All",1,5,'','',array,function(pageId){
        if( checked_array[pageId] != undefined ) {
            $(".accountNumber").next().each(function () {
                var _this = this;
                checked_array[pageId].map(function(orderCode){
                    if($(_this).html() == orderCode){
                        $(_this).prev().prop("checked","checked");
                    }
                });
            });
        }
        return isTop;
    });

    $(document).on("click",".accountNumber",function(){
        var pageIndex=$(".jp-current").html();
        var orderCode=$(this).next().html();
        if( checked_array[pageIndex] == undefined ){
            checked_array[pageIndex] = new Array();
            checked_array.length++;
            outport_array[checked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            checked_array[pageIndex].push(orderCode);
            outport_array[checked_array.length-1].push(orderCode);
        }
        else{
            checked_array[pageIndex].map(function(code){
                if( code == orderCode ){
                    checked_array[pageIndex].remove(code);
                    outport_array[checked_array.length-1].remove(orderCode);
                }
            })
        }
    });


    $("#accSearch").click(function(){
        checked_array = {};
        outport_array = {};
        checked_array.length = 0;

        orderNo = $("input[name='accountNum']").val();
        receiver = $("input[name='accountCust']").val();
        username = $("input[name='accountUserName']").val();
        contactPhone = $("input[name='accountTel']").val();
        createStart = $("#datetimepicker_start").val();
        createEnd = $("#datetimepicker_end").val();
        payStart = $("#pay_datetimepicker_start").val();
        payEnd = $("#pay_datetimepicker_end").val();
        selfPick=$("#selfPick option:selected").val();
        enterpriseId = $(".enterpriseIdChoosen").val();
        if(selfPick=="1"){
            selfPick="true"
        }else if(selfPick=="2"){
            selfPick="false"
        }else if(selfPick="0"){
            selfPick=""
        }
        array[0] = orderNo;
        array[2] = username;
        array[3] = receiver;
        array[4] = contactPhone;
        array[5] = createStart;
        array[6] = createEnd;
        array[7] = payStart;
        array[8] = payEnd;
        array[9] = selfPick;
        array[10] = enterpriseId;
        var type = $(".accountTable ul li.accOn").html();
        if(createEnd>=createStart&&payEnd>=payStart){
            if( type == '全部订单' ){
                status = '';
                array[1] = status;

                jPages("../web/api/ordermanage/getOrders","itemContainer_All","holder_All",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                })
            }
            if( type == '待付款' ){
                status = 'SUBMITED';
                array[1] = status;
                $("#itemContainer_first").empty();
                jPages("../web/api/ordermanage/getOrders","itemContainer_first","holder_first",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                });
            }
            if( type == '待发货' ){
                status = 'WAIT_DELIVER';
                array[1] = status;
                $("#itemContainer_second").empty();
                jPages("../web/api/ordermanage/getOrders","itemContainer_second","holder_second",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                });
            }
            if( type == '待收货' ){
                status = 'DELIVERED';
                array[1] = status;
                $("#itemContainer_third").empty();
                jPages("../web/api/ordermanage/getOrders","itemContainer_third","holder_third",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                });
            }
            if( type == '待提货' ){
                status = 'WAIT_PICKUP';
                array[1] = status;
                $("#itemContainer_forth").empty();
                jPages("../web/api/ordermanage/getOrders","itemContainer_forth","holder_forth",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                });
            }
            if( type == '已完成' ){
                status = 'RECIEVED';
                array[1] = status;
                $("#itemContainer_fifth").empty();
                jPages("../web/api/ordermanage/getOrders","itemContainer_fifth","holder_fith",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                });
            }
            if( type == '已取消' ){
                status = 'CANCELED';
                array[1] = status;
                $("#itemContainer_sixth").empty();
                jPages("../web/api/ordermanage/getOrders","itemContainer_sixth","holder_sixth",1,5,'','',array,function(pageId){
                    if( checked_array[pageId] != undefined ) {
                        $(".accountNumber").next().each(function () {
                            var _this = this;
                            checked_array[pageId].map(function(orderCode){
                                if($(_this).html() == orderCode){
                                    $(_this).prev().prop("checked","checked");
                                }
                            });
                        });
                    }
                    return isTop;
                });
            }
        }else{
            data_type_alert("结束时间不能小于开始时间","error")
        }

    });

    //日历控件
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

    $("#pay_date_start").click(function(e){
        $("#pay_datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#pay_datetimepicker_start").trigger("focus");
    })
    $("#pay_datetimepicker_start").blur(function(){
        $("#pay_datetimepicker_start").datetimepicker('destroy');
    });

    $("#pay_date_end").click(function(e){
        $("#pay_datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#pay_datetimepicker_end").trigger("focus");
    })
    $("#pay_datetimepicker_end").blur(function(){
        $("#pay_datetimepicker_end").datetimepicker('destroy');
    });


    //修改订单金额
    $("#setAccountMoneyWarningConfrim").click(function(){
        $(".setAccountMoneyWarning").fadeOut(500);
        $(".setAccountMoney").fadeIn(500);
    });
    $("#setAccountMoneyWarningCancel").click(function(){
        discoverHtml();
        $(".setAccountMoneyWarning").fadeOut(500);

    });
    $("#setAccountMoneyConfrim").click(function(){
        var orderCode = $("#orderCode").val();
        var price = $("#priceForsetAccountMoney").val();
        if( isNaN(price) ){
            window.wxc.xcConfirm("请输入正确金额", window.wxc.xcConfirm.typeEnum.error);
        }
        else{
            $.post("../web/api/ordermanage/editPrice",{
                orderCode:orderCode,
                price:price
            },function(data){
                if( data.response == 'success' ){
                    ensure_alert("accountManager");
                }
                else{
                    window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
                }
            },'json');
        }

    });
    $("#setAccountMoneyCancel").click(function(){
        discoverHtml();
        $(".setAccountMoney").fadeOut(500);
    });

    //取消订单
    $(document).on("click",".cancelorder",function(){
        coverHtml();
        $("#orderCode").val($(this).parent().parent().parent().find("span").html());
        $(".CancelAccountStateWarning").fadeIn(500);
    });
    $("#CancelAccountStateWarningConfrim").click(function(){
        $(".CancelAccountStateWarning").fadeOut(500);
        $(".updateAccountState").fadeIn(500);
    });
    $("#CancelAccountStateWarningCancel").click(function(){
        discoverHtml();
        $(".CancelAccountStateWarning").fadeOut(500);
    });
    $("#updateAccountStateConfrim").click(function(){
        var orderCode = $("#orderCode").val();
        var reason =$(".updateAccountState textarea").val();
        $.post('../web/api/ordermanage/cancelOrder',{
            orderCode:orderCode,
            reason:reason
        },function(data){
            if(data.response == 'success'){
                ensure_alert('accountManager');
            }
        },'json');
    });
    $("#updateAccountStateCancel").click(function(){
        $(".updateAccountState").fadeOut(500);
        discoverHtml();
    });

    $(".accountTable li").click(function(){
        $("#itemContainer_All").empty();
        $("#holder_All").empty();
        var str = $(this).html();
        if ( str == '全部订单' ){
            status = '';
            array[1] = status;
            console.log('fuck');
            jPages("../web/api/ordermanage/getOrders","itemContainer_All","holder_All",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[0]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[0]).sibling().css("display","none");
        }
        else if ( str == '待付款' ){
            status = 'SUBMITED';
            array[1] = status;

            jPages("../web/api/ordermanage/getOrders","itemContainer_first","holder_first",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[1]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[1]).sibling().css("display","none");
        }
        else if ( str == '待发货' ){
            status = 'WAIT_DELIVER';
            array[1] = status;
            jPages("../web/api/ordermanage/getOrders","itemContainer_second","holder_second",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[2]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[2]).sibling().css("display","none");
        }
        else if ( str == '待收货' ){
            status = 'DELIVERED';
            array[1] = status;
            jPages("../web/api/ordermanage/getOrders","itemContainer_third","holder_third",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[3]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[3]).sibling().css("display","none");
        }
        else if ( str == '待提货' ){
            status = 'WAIT_PICKUP';
            array[1] = status;
            jPages("../web/api/ordermanage/getOrders","itemContainer_forth","holder_forth",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[4]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[4]).sibling().css("display","none");
        }
        else if ( str == '已完成' ){
            status = 'RECIEVED';
            array[1] = status;
            jPages("../web/api/ordermanage/getOrders","itemContainer_fifth","holder_fifth",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[5]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[5]).sibling().css("display","none");
        }
        else if ( str == '已取消' ){
            status = 'CANCELED';
            array[1] = status;
            jPages("../web/api/ordermanage/getOrders","itemContainer_sixth","holder_sixth",1,5,'','',array,function(pageId){
                checked_array = {};
                outport_array = {};
                checked_array.length = 0;
                if( checked_array[pageId] != undefined ) {
                    $(".accountNumber").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(orderCode){
                            if($(_this).html() == orderCode){
                                $(_this).prev().prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
            $( $(".jpages_Table")[6]).css("display","block").siblings(".jpages_Table").css("display","none");
            //$( $(".jpages_Table")[6]).siblings().css("display","none");
        }
    });


    //从全部退单位置返回时的处理
    var local_url = document.location.href;
    var type = local_url.split("?");
    if( type[1] != undefined ){
        type = type[1].split("=");
        getData( type );
    }



    //查看详情操作
    $(document).on("click",".detailCheck",function(){
        coverHtml();
        var accountMUN=$(this).parent().parent().siblings("#accNum").html();
        var data = $(this).parent().parent().parent().parent().find(".data").val();
        data = JSON.parse(data);
        var check=$(this).parent().parent().parent().siblings().children(".orderStatus").html();
        $(".accountNUM").html(accountMUN);
        if(check == "待付款"){
            $(".Img1").css({"background":"#54a6de"})
            $(".Img11").css({"color":"#54a6de"})
            $(".Img2,.Img3,.Img4").css({"background":"#b2b2b2"})
            $(".Img22,.Img33,.Img44").css({"color":"#b2b2b2"})
            $(".Img1,.Img2,.Img3,.Img4,.Img11,.Img22,.Img33,.Img44").show();
            $(".Img5,.Img55").hide()
            if(data.createTime!=undefined){
                $(".orderTime").html(handleDate_prev(new Date( data.createTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.createTime )));
            }else{
                $(".orderTime").html("")
            }
            $(".PayedTime").html("");
            $(".sendExpressTime").html("");
            $(".finishTime").html("");
            //赋值

        }else if(check=="待发货"||check=="待提货"||check=="审核退款"||check=="拒绝退款"||check=="退款待退款"||check=="退款成功"){
            $(".Img1,.Img2").css({"background":"#54a6de"});
            $(".Img11,.Img22").css({"color":"#54a6de"});
            $(".Img3,.Img4").css({"background":"#b2b2b2"});
            $(".Img33,.Img44").css({"color":"#b2b2b2"});
            $(".Img1,.Img2,.Img3,.Img4,.Img11,.Img22,.Img33,.Img44").show();
            $(".Img5,.Img55").hide()
            if(data.createTime!=undefined){
                $(".orderTime").html(handleDate_prev(new Date( data.createTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.createTime )));
            }else{
                $(".orderTime").html("")
            }

            if(data.ucoinPayTime != undefined){
                $(".PayedTime").html(handleDate_prev(new Date( data.ucoinPayTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.ucoinPayTime )));
            }else{
                $(".PayedTime").html("")
            }
            $(".sendExpressTime").html("");
            $(".finishTime").html("");
            //赋值

        }
        else if(check=="已发货"){
            $(".Img1,.Img2,.Img3").css({"background":"#54a6de"})
            $(".Img11,.Img22,.Img33").css({"color":"#54a6de"})
            $(".Img4").css({"background":"#b2b2b2"})
            $(".Img44").css({"color":"#b2b2b2"})
            $(".Img1,.Img2,.Img3,.Img4,.Img11,.Img22,.Img33,.Img44").show();
            $(".Img5,.Img55").hide()
            if(data.createTime!=undefined){
                $(".orderTime").html(handleDate_prev(new Date( data.createTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.createTime )));
            }else{
                $(".orderTime").html("")
            }

            if(data.ucoinPayTime != undefined){
                $(".PayedTime").html(handleDate_prev(new Date( data.ucoinPayTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.ucoinPayTime )));
            }else{
                $(".PayedTime").html("")
            }

            if(data.sendExpressTime!=undefined ){
                $(".sendExpressTime").html(handleDate_prev(new Date( data.sendExpressTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.sendExpressTime )));
            }else{
                $(".sendExpressTime").html("")
            }
            $(".finishTime").html("");
            //赋值

        }else if(check=="已完成"||check=="退货申请"||check=="同意退货"||check=="拒绝退货"||check=="待收货"||check=="退单结束"||check=="收货失败"||check=="待客户填写物流地址"||check=="退货待退款"){
            $(".Img1,.Img2,.Img3,.Img4").css({"background":"#54a6de"});
            $(".Img11,.Img22,.Img33,.Img44").css({"color":"#54a6de"});
            $(".Img1,.Img2,.Img3,.Img4,.Img11,.Img22,.Img33,.Img44").show();
            $(".Img5,.Img55").hide()
            if(data.createTime!=undefined){
                $(".orderTime").html(handleDate_prev(new Date( data.createTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.createTime )));
            }else{
                $(".orderTime").html("")
            }

            if(data.ucoinPayTime != undefined){
                $(".PayedTime").html(handleDate_prev(new Date( data.ucoinPayTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.ucoinPayTime )));
            }else{
                $(".PayedTime").html("")
            }

            if(data.sendExpressTime!=undefined ){
                $(".sendExpressTime").html(handleDate_prev(new Date( data.sendExpressTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.sendExpressTime )));
            }else{
                $(".sendExpressTime").html("")
            }
            var finishTime = data.getGoodsTime;
            if( finishTime != undefined ){
                $(".finishTime").html(handleDate_prev(new Date( finishTime )) + ' ' + handleDate_fuck_jiangtao(new Date( finishTime )));
            }else{
                $(".finishTime").html('');
            }


            //赋值

        }
        else if(check=="已取消"){
            $(".Img1,.Img2,.Img3,.Img4,.Img5").css({"background":"#54a6de"});
            $(".Img11,.Img22,.Img33,.Img44,.Img55").css({"color":"#54a6de"});
            $(".Img2,.Img3,.Img4,.Img22,.Img33,.Img44").hide();
            $(".Img5,.Img55").show();
            if(data.createTime!=undefined){
                $(".orderTime").html(handleDate_prev(new Date( data.createTime )) + ' ' + handleDate_fuck_jiangtao(new Date( data.createTime )));
            }else{
                $(".orderTime").html("")
            }
            $(".PayedTime").html("")
            $(".sendExpressTime").html("")
            var cancelTime = data.getGoodsTime;
            if( cancelTime != undefined ){
                $(".cancelTime").html(handleDate_prev(new Date( cancelTime )) + ' ' + handleDate_fuck_jiangtao(new Date( cancelTime )));
            }else{
                $(".cancelTime").html('');
            }



        }
        var checkstate=$(this).parent().parent().parent().siblings(".order_type").val();
        if(checkstate == 'false,true'){//物流单详情网点代客下单
            if( isTop ){
                $(".detailOne").fadeIn(500);
                $(".detailTwo,.detailThree,.detailFour").fadeOut(500);
                //-------订单概况
                //订单编号
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[1] ).find("span").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[2] ).find("span").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[5] ).find("span").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[6] ).find("span").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[7] ).find("span").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[8] ).find("span").html(addCommas(data.orderPrice,2));
                //网点补贴
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                //金钱
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[11] ).find("span").html(0);
                if( data.payTime != undefined ){
                    //邮豆支付时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[12] ).find("span").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    //支付完成时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[13] ).find("span").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }
                //快递信息
                //-------||||||||||------------
                var post_html = '';
                var post_count = 0;
                data.orderContainerRelations.map(function(object){
                    post_count++;
                    post_html += '<dd class="deliveryInfoPart"style="width:780px; height:50px;">包裹<a>';
                    post_html += post_count + '</a><br>';
                    post_html += '<div style="padding:5px 0px 5px 0px; width:360px; float:left;">物流公司：<span>';
                    post_html += object.expressName + '</span></div><div style="padding:5px 0px 5px 10px; width:360px; float:left;">';
                    post_html += '物流单号：<span>' + object.expressNo + '</span></div></dd>';
                });
                $(".deliveryInfoPart").remove();
                $(".deliveryInfo").after(post_html);

                //---------
                //配送方式
                //-------||||||||||------------
                if( data.selfPick ){
                    $( $( $(".detailOne .checkDetailOne dl:nth-child(1)")[2]).children()[1] ).find("span").html("网点自提");
                }
                else{
                    $( $( $(".detailOne .checkDetailOne dl:nth-child(1)")[2]).children()[1] ).find("span").html("快递配送");
                }
                $( $( $(".detailOne .checkDetailOne dl:nth-child(1)")[2]).children()[9] ).find("span").html(data.customerRemark);
                $(".deliveryPrice").html(data.expressPrice);//运费
                $(".deliveryAddress").html(data.shippingProvince + data.shippingCity + data.shippingCounty);//收货地址
                $(".deliveryDetailAddress").html( data.shippingAddress );//详细地址
                $(".personGet").html( data.shippingPerson );//收货人
                $(".contactPhone").html( data.shippingPhone );//联系电话
                $(".cellPhone").html( data.shippingMobile );//手机
                $(".postCode").html("");//邮编
                $(".customerRemark").html( data.customerRemark );//客户留言
                $(".billType").html("网点代客下单");
                //---------
                //下单类型


                    try{
                        $(".operator").html(handleUndefined(data.pickInfo.enterpriseName)+'( '+ handleUndefinedOptar(data.operator) + ' )');
                    }
                    catch(e){

                    }
                  //console.log(1,data.pickInfo.enterpriseName,$(".detailFour .checkDetailFour dl:nth-child(3)").children()[2]).find("span").html();
                   //$( $(".detailFour .checkDetailFour li:nth-child(3)").children()[2]).find("span").html(data.pickInfo.enterpriseName);
                //--------||||||||||----------------
                //卖家备注
                 $(".selfRemark").html(data.sellerRemark);
                //--------||||||||||----------------
                $(".payWay").html("授权免验证");

                //商品列表
                $(".detailOne .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailOne .Purchase_table tbody").append(html)
                });
            }
            else{
                $(".detailFour").fadeIn(500);
                //-------订单概况
                //订单编号
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[1] ).find("span").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[2] ).find("span").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[5] ).find("span").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[6] ).find("span").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[7] ).find("span").html(addCommas(data.orderPrice,2));
                //网点补贴
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                //金钱
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[11] ).find("span").html(0);
                if( data.payTime != undefined ){
                    //邮豆支付时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[12] ).find("span").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    //支付完成时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[13] ).find("span").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }
                //快递信息
                //-------||||||||||------------
                var post_html = '';
                var post_count = 0;
                data.orderContainerRelations.map(function(object){
                    post_count++;
                    post_html += '<dd class="deliveryInfoPart"style="width:780px; height:50px;">包裹<a>';
                    post_html += post_count + '</a><br>';
                    post_html += '<div style="padding:5px 0px 5px 0px; width:360px; float:left;">物流公司：<span>';
                    post_html += object.expressName + '</span></div><div style="padding:5px 0px 5px 10px; width:360px; float:left;">';
                    post_html += '物流单号：<span>' + object.expressNo + '</span></div></dd>';
                });
                $(".deliveryInfoPart").remove();
                $(".deliveryInfo").after(post_html);
                //---------
                //配送方式
                //-------||||||||||------------
                $(".deliveryType").html("快递配送");
                $( $( $(".detailFour .checkDetailFour dl:nth-child(1)")[2]).children()[9] ).find("span").html(data.customerRemark);
                $(".deliveryPrice").html(data.expressPrice);//运费
                $(".deliveryAddress").html(data.shippingProvince + data.shippingCity + data.shippingCounty);//收货地址
                $(".deliveryDetailAddress").html( data.shippingAddress );//详细地址
                $(".personGet").html( data.shippingPerson );//收货人
                $(".contactPhone").html( data.shippingPhone );//联系电话
                $(".cellPhone").html( data.shippingMobile );//手机
                $(".postCode").html();//邮编
                $(".customerRemark").html( data.customerRemark );//客户留言
                $(".billType").html("网点代客下单");
                //---------
                //下单类型


                try{
                    $(".operator").html(handleUndefined(data.pickInfo.enterpriseName)+'( '+ handleUndefinedOptar(data.operator) + ' )');
                }
                catch(e){

                }
                //console.log(1,data.pickInfo.enterpriseName,$(".detailFour .checkDetailFour dl:nth-child(3)").children()[2]).find("span").html();
                //$( $(".detailFour .checkDetailFour li:nth-child(3)").children()[2]).find("span").html(data.pickInfo.enterpriseName);
                //--------||||||||||----------------
                //卖家备注
                $(".selfRemark").html(data.sellerRemark);
                //--------||||||||||----------------
                $(".payWay").html("授权免验证");
                //商品列表
                $(".detailFour .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailFour .Purchase_table tbody").append(html)
                });
            }




        }
        else if(checkstate == 'false,false'){//物流单详情用户自主下单
            if( isTop ){
                console.log("fuck")
                $(".detailTwo").fadeIn(500);
                $(".detailOne,.detailThree,.detailFour").fadeOut(500);
                //-------订单概况
                //订单编号
                $( $(".detailTwo .checkDetailTwo dl:nth-child(1)").children()[1] ).find("span").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $( $(".detailTwo .checkDetailTwo dl:nth-child(1)").children()[2] ).find("span").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $( $(".detailTwo .checkDetailTwo dl:nth-child(1)").children()[5] ).find("span").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $( $(".detailTwo .checkDetailTwo dl:nth-child(1)").children()[6] ).find("span").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $( $(".detailTwo .checkDetailTwo dl:nth-child(1)").children()[7] ).find("span").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $( $(".detailTwo .checkDetailTwo dl:nth-child(1)").children()[8] ).find("span").html(addCommas(data.orderPrice,2));
                //网点补贴
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                //金钱
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[11] ).find("span").html(0);
                if( data.payTime != undefined ){
                    //邮豆支付时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[12] ).find("span").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    //支付完成时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[13] ).find("span").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }
                //快递信息
                var post_html = '';
                var post_count = 0;
                data.orderContainerRelations.map(function(object){
                    post_count++;
                    post_html += '<dd class="deliveryInfoPart"style="width:780px; height:50px;">包裹<a>';
                    post_html += post_count + '</a><br>';
                    post_html += '<div style="padding:5px 0px 5px 0px; width:360px; float:left;">物流公司：<span>';
                    post_html += object.expressName + '</span></div><div style="padding:5px 0px 5px 10px; width:360px; float:left;">';
                    post_html += '物流单号：<span>' + object.expressNo + '</span></div></dd>';
                });
                $(".deliveryInfoPart").remove();
                $(".deliveryInfo").after(post_html);

                //配送方式
                //-------||||||||||------------
                if( data.selfPick ){
                    $( $( $(".detailTwo .checkDetailTwo dl:nth-child(1)")[2]).children()[1] ).find("span").html("网点自提");
                }
                else{
                    $( $( $(".detailTwo .checkDetailTwo dl:nth-child(1)")[2]).children()[1] ).find("span").html("快递配送");
                }
                $( $( $(".detailTwo .checkDetailTwo dl:nth-child(1)")[2]).children()[9] ).find("span").html(data.customerRemark);
                $(".deliveryPrice").html(data.expressPrice);//运费
                $(".deliveryAddress").html(data.shippingProvince + data.shippingCity + data.shippingCounty);//收货地址
                $(".deliveryDetailAddress").html( data.shippingAddress );//详细地址
                $(".personGet").html( data.shippingPerson );//收货人
                $(".contactPhone").html( data.shippingPhone );//联系电话
                $(".cellPhone").html( data.shippingMobile );//手机
                $(".postCode").html();//邮编
                $(".customerRemark").html( data.customerRemark );//客户留言
                //---------
                //下单类型
                $(".billType").html("用户自主下单");
                $(".operator").html(handleUndefined( data.customerInfo.fullName  + "(" +data.customerInfo.username) + ")");
                //$( $(".detailFour .checkDetailFour li:nth-child(3)").children()[2]).find("span").html(data.customerInfo.username);
                //console.log(1,data.pickInfo.enterpriseName,$(".detailFour .checkDetailFour dl:nth-child(3)").children()[2]).find("span").html();
                //$( $(".detailFour .checkDetailFour li:nth-child(3)").children()[2]).find("span").html(data.pickInfo.enterpriseName);
                //--------||||||||||----------------
                //卖家备注
                $( ".selfRemark").html(data.sellerRemark);
                //$( $(".detailTwo .checkDetailTwo dl:nth-child(4)").children()[0]).find("span").html(data.sellerRemark);
                //--------||||||||||----------------

                //商品列表
                $(".detailTwo .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';


                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' +addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailTwo .Purchase_table tbody").append(html)
                });
            }
            else{
                $(".detailFour").fadeIn(500);
                //-------订单概况
                //订单编号
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[1] ).find("span").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[2] ).find("span").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[5] ).find("span").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[6] ).find("span").html(addCommas(data.orderPrePrice));
                //交易金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[7] ).find("span").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[8] ).find("span").html(addCommas(data.orderPrice,2));
                //网点补贴
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                //金钱
                $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[11] ).find("span").html(0);
                if( data.payTime != undefined ){
                    //邮豆支付时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[12] ).find("span").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    //支付完成时间
                    $( $(".detailOne .checkDetailOne dl:nth-child(1)").children()[13] ).find("span").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }
                //快递信息
                var post_html = '';
                var post_count = 0;
                data.orderContainerRelations.map(function(object){
                    post_count++;
                    post_html += '<dd class="deliveryInfoPart" style="width:780px; height:50px;">包裹<a>';
                    post_html += post_count + '</a><br>';
                    post_html += '<div style="padding:5px 0px 5px 0px; width:360px; float:left;">物流公司：<span>';
                    post_html += object.expressName + '</span></div><div style="padding:5px 0px 5px 10px; width:360px; float:left;">';
                    post_html += '物流单号：<span>' + object.expressNo + '</span></div></dd>';
                });
                $(".deliveryInfoPart").remove();
                $(".deliveryInfo").after(post_html);

                //配送方式
                //-------||||||||||------------
                $( $( $(".detailFour .checkDetailFour dl:nth-child(1)")[2]).children()[1] ).find("span").html("快递配送");
                $( $( $(".detailFour .checkDetailFour dl:nth-child(1)")[2]).children()[9] ).find("span").html(data.customerRemark);
                $(".deliveryPrice").html(data.expressPrice);//运费
                $(".deliveryAddress").html(data.shippingProvince + data.shippingCity + data.shippingCounty);//收货地址
                $(".deliveryDetailAddress").html( data.shippingAddress );//详细地址
                $(".personGet").html( data.shippingPerson );//收货人
                $(".contactPhone").html( data.shippingPhone );//联系电话
                $(".cellPhone").html( data.shippingMobile );//手机
                $(".postCode").html();//邮编
                $(".customerRemark").html( data.customerRemark );//客户留言

                //---------
                //下单类型
                $(".billType").html("用户自主下单");
                $(".operator").html(handleUndefined( data.customerInfo.fullName  + "(" +data.customerInfo.username) + ")");
                //$( $(".detailFour .checkDetailFour li:nth-child(3)").children()[2]).find("span").html(data.customerInfo.username);
                //console.log(1,data.pickInfo.enterpriseName,$(".detailFour .checkDetailFour dl:nth-child(3)").children()[2]).find("span").html();
                //$( $(".detailFour .checkDetailFour li:nth-child(3)").children()[2]).find("span").html(data.pickInfo.enterpriseName);
                //--------||||||||||----------------
                //卖家备注
                $( ".selfRemark").html(data.sellerRemark);
                //商品列表
                $(".detailFour .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailFour .Purchase_table tbody").append(html)
                });
            }




        }
        else if(checkstate == 'true,true'){//网点自提单详情网点代客下单
            if( isTop ){
                $(".detailThree").fadeIn(500);
                $(".detailTwo,.detailOne,.detailFour").fadeOut(500);
                //-------订单概况
                //订单编号
                $(".detailThree .checkDetailThree .orderCode").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                $(".detailThree .checkDetailThree .createTime").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                var pickName=$(this).parent().parent().parent().siblings().children().children(".pickName").html();
                //收件人
                $(".pickName2").html(pickName);
                var pickPhone=$(this).parent().parent().parent().siblings().children().children(".pickPhone").html();
                //收货人电话
                $(".pickPhone2").html(pickPhone);

                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $(".detailThree .checkDetailThree .oldPrice").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $(".detailThree .checkDetailThree .accountPrice").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $(".detailThree .checkDetailThree .orderPrice").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $(".detailThree .checkDetailThree .ucoinPrice").html(addCommas(data.orderPrice,2));
                //金钱支付金额
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                $(".detailThree .checkDetailThree .moneyPayTime").html(0);
                if( data.payTime != undefined ){
                    //邮豆支付时间
                    $(".detailThree .checkDetailThree .ucoinPayTime").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    //支付完成时间
                    $(".detailThree .checkDetailThree .finishTime").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }
                //配送方式
                //-------||||||||||------------
                $(".deliveryType").html("网点自提");
                $(".enterpriseAddress").html(data.pickInfo.enterpriseName +'( ' + handleUndefinedAddress(data.pickInfo.pickAddress) + ' )' );
                $(".customerRemark").html(data.customerRemark);
                //---------
                //下单类型
                $(".billType").html("网点代客下单");
                try{
                    $(".operator").html(handleUndefined(data.pickInfo.enterpriseName)+'( '+ handleUndefinedOptar(data.operator) + ' )');
                }
                catch(e){

                }
                $(".payWay").html("授权免验证");
                //--------||||||||||----------------
                //卖家备注
                $(".selfRemark").html(data.sellerRemark);
                //--------||||||||||----------------

                //商品列表
                $(".detailThree .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailThree .Purchase_table tbody").append(html)
                });
            }
            else{
                $(".detailThree").fadeIn(500);
                //-------订单概况
                //订单编号
                $(".orderCode").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $(".createTime").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                var pickName=$(this).parent().parent().parent().siblings().children().children(".pickName").html();
                //收件人
                $(".pickName2").html(pickName);
                var pickPhone=$(this).parent().parent().parent().siblings().children().children(".pickPhone").html();
                //收货人电话
                $(".pickPhone2").html(pickPhone);

                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $(".oldPrice").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $(".accountPrice").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $(".orderPrice").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $(".ucoinPrice").html(addCommas(data.orderPrice,2));
                //金钱支付金额
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                $(".moneyPayTime").html(0);
                //邮豆支付时间
                if(data.payTime !=undefined){
                    $(".ucoinPayTime").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    $(".finishTime").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }else{
                    $(".ucoinPayTime").html("")
                    $(".finishTime").html("")
                }
                //配送方式
                //-------||||||||||------------
                $(".deliveryType").html("网点自提")
                $(".enterpriseAddress").html(data.pickInfo.enterpriseName +'( ' + handleUndefinedAddress(data.pickInfo.pickAddress) + ' )' );
                $(".customerRemark").html(data.customerRemark);

                //---------
                //下单类型
                $(".billType").html("网点代客下单");
                try{
                    $(".operator").html(handleUndefined(data.pickInfo.enterpriseName)+'( '+ handleUndefinedOptar(data.operator) + ' )');
                }
                catch(e){

                }
                $(".payWay").html("授权免验证");
                //--------||||||||||----------------
                //卖家备注
                $(".selfRemark").html(data.sellerRemark);
                //--------||||||||||----------------

                //商品列表
                $(".detailThree .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailThree .Purchase_table tbody").append(html)
                });
            }





        }
        else if(checkstate == 'true,false'){//网点自提单详情用户自主下单
            if(isTop){
                $(".detailFour").fadeIn(500);
                $(".detailTwo,.detailThree,.detailOne").fadeOut(500);
                //-------订单概况
                //订单编号
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[1] ).find("span").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[2] ).find("span").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                var pickName=$(this).parent().parent().parent().siblings().children().children(".pickName").html();
                //收件人
                $(".pickName2").html(pickName);
                var pickPhone=$(this).parent().parent().parent().siblings().children().children(".pickPhone").html();
                //收货人电话
                $(".pickPhone2").html(pickPhone);

                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[5] ).find("span").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[6] ).find("span").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[7] ).find("span").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[8] ).find("span").html(addCommas(data.orderPrice,2));
                //金钱支付金额
                //网点补贴
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[11] ).find("span").html(0);
                //邮豆支付时间
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[12] ).find("span").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                //支付完成时间
                $( $(".detailFour .checkDetailFour dl:nth-child(1)").children()[13] ).find("span").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));

                //配送方式
                //-------||||||||||------------
                $(".deliveryType").html("网点自提");
                $(".enterpriseAddress").html(data.pickInfo.enterpriseName +'( ' + handleUndefinedAddress(data.pickInfo.pickAddress) + ' )' );
                $(".customerRemark").html(data.customerRemark);

                //---------
                //下单类型
                $(".billType").html("用户自主下单");
                $(".operator").html(handleUndefined("("+ data.customerInfo.fullName + ")" + data.customerInfo.username));
                //--------||||||||||----------------
                //卖家备注
                $(".selfRemark").html(data.sellerRemark);
                //--------||||||||||----------------

                //商品列表
                $(".detailFour .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailFour .Purchase_table tbody").append(html)
                });
            }
            else{
                $(".detailThree").fadeIn(500);
                //-------订单概况
                //订单编号
                $(".orderCode").html(data.orderCode);
                //下单时间
                var createTime = $(this).parent().parent().parent().parent().find(".hidden_create_time").val();
                //var createDate = handleFullTime( createTime,10,4 );
                $(".createTime").html(createTime);
                //订单状态
                var orderStatus = $(this).parent().parent().parent().parent().find(".orderStatus").html();
                var pickName=$(this).parent().parent().parent().siblings().children().children(".pickName").html();
                //收件人
                $(".pickName2").html(pickName);
                var pickPhone=$(this).parent().parent().parent().siblings().children().children(".pickPhone").html();
                //收货人电话
                $(".pickPhone2").html(pickPhone);

                $(".orderStatusDetail").html(orderStatus);
                //原始金额
                $(".oldPrice").html(addCommas(data.orderOldPrice,2));
                //优惠金额
                $(".accountPrice").html(addCommas(data.orderPrePrice,2));
                //交易金额
                $(".orderPrice").html(addCommas(data.orderPrice,2));
                //邮豆支付金额
                $(".ucoinPrice").html(addCommas(data.orderPrice,2));
                //金钱支付金额
                $(".subsidyPrice").html(addCommas(data.subsidyPrice,2));
                //用户支付
                $(".UserPayPrice").html(addCommas((parseFloat(data.orderPrice) - parseFloat(handleUndefinedZero(data.subsidyPrice,2))),2));
                $(".moneyPayTime").html(0);
                //邮豆支付时间
                if(data.payTime !=undefined){
                $(".ucoinPayTime").html( handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                    $(".finishTime").html(handleDate_prev( new Date(data.payTime) ) + " " + handleDate_fuck_jiangtao( new Date(data.payTime) ));
                }else{
                    $(".ucoinPayTime").html("")
                    $(".finishTime").html("")
                }
                //支付完成时间


                //配送方式
                //-------||||||||||------------
                $(".deliveryType").html("网点自提")
                $(".enterpriseAddress").html(data.pickInfo.enterpriseName +'( ' + handleUndefinedAddress(data.pickInfo.pickAddress) + ' )' );
                $(".customerRemark").html(data.customerRemark);

                //---------
                //下单类型
                $(".billType").html("用户自主下单");
                $(".operator").html(handleUndefined("("+ data.customerInfo.fullName + ")" + data.customerInfo.username));
                //--------||||||||||----------------
                //卖家备注
                $(".selfRemark").html(data.sellerRemark);
                //--------||||||||||----------------

                //商品列表
                $(".detailThree .Purchase_table tbody").empty();
                data.orderGoodsList.map(function(good){
                    var html = '';
                    html += '<tr bgcolor="#FFFFFF">';
                    html += '<td><img src="' + good.goodsImg + '" width="50" height="50" /><span>' + good.goodsInfoName + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</td>';
                    html += '<td>' + good.goodsInfoNum + '</td>';
                    //规格
                    try{
                        var specStr = '';
                        good.goodsProductReleSpecVoList.map(function(object){
                            specStr += object.spec.specName + ':' + object.goodsSpecDetail.specDetailName + '<br/>';
                        });
                    }
                    catch(e){
                        console.log(e);
                    }
                    html += '<td><span style="overflow: hidden;text-overflow: ellipsis;">' + handleUndefined(specStr) + '</span></td>';
                    html += '<td><span>' + addCommas(good.goodsInfoPrice,2) + '</span>邮豆</td>';
                    html += '<td><span>' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</td>';
                    html += '</tr>';
                    $(".detailThree .Purchase_table tbody").append(html)
                });
            }
        }
    });

    $(document).on("click",".changestatus",function(){
        coverHtml();
        $(".setAccountState").fadeIn(500);
        $(".setAccountState").css("z-index","9999");
        $("#orderCode").val($(this).parent().parent().parent().find("span").html());
    });

    //设置订单状态
    $("#setAccountStateConfrim").click(function(){
        var orderCode = $("#orderCode").val();
        $.post("../web/api/ordermanage/editStatus",{
            orderCode:orderCode
        },function(data){
            if( data.response == 'success' ){
                ensure_alert("accountManager");
            }
            else{
                response_ensure_alert('error',data.data.text,new function(){

                });
            }
        },'json');
    });
    $("#setAccountStateCancel").click(function(){
        discoverHtml();
        $(".setAccountState").fadeOut(500);
        $(".setAccountState").css("z-index","0");
    });

    $(document).on("click",".changeprice",function(){
        coverHtml();
        $(".setAccountMoneyWarning").fadeIn(500);
        $(".setAccountMoneyWarning").css("z-index","9999");
        $("#orderCode").val($(this).parent().parent().parent().find("span").html());
    });
    $(document).on("click",".calcelorder",function(){
        coverHtml();
        $(".CancelAccountStateWarning").fadeIn(500);
        $(".CancelAccountStateWarning").css("z-index","9999");
        $("#orderCode").val($(this).parent().parent().parent().find("span").html());
    });
    $(document).on("click",".custReturn",function(){
        coverHtml();
        $("#subPay").show()
        $("#userPay").show()
        $("#subPay").siblings("b").show()

        var accountNum = $(this).parent().parent().prev().prev().html();
        var sumNum = $("#sumNum").html();
        $("#returnNum").html(accountNum);
        //$("#returnMoneyNum").html(sumNum);
        var data = $(this).parent().parent().parent().parent().find(".data").val();
        data = JSON.parse(data);
        $("#subPay span").html(addCommas(data.subsidyPrice,2));
        if( data.subsidyPrice != undefined ){
            $("#userPay span").html(addCommas((data.orderPrice - data.subsidyPrice),2));
        }else{
            $("#userPay span").html(addCommas(data.orderPrice,2))
        }

        $(".CustomerReturn").fadeIn(500);
        $(".returnNum").html( data.orderCode );//订单编号
        $("#returnMoneyNum").html(addCommas(data.orderPrice,2) + '邮豆');//订单金额
        if( data.subsidyPrice == undefined ){
            $("#subPay").hide()
            $("#subPay").siblings("b").hide()
        }else if((data.orderPrice - data.subsidyPrice) == "0"){
            $("#userPay").hide()
            $("#subPay").siblings("b").hide()
        }


        //商品列表
        var goodsListhtml = '';
        data.orderGoodsList.map(function(good){
            goodsListhtml += '<dd>';
            goodsListhtml += '<abbr style="display: none;"><input type="hidden" checked="checked" class="checkboxForReturn" value="' + good.goodsInfoId + '"> </abbr>';
            goodsListhtml += '<abbr><img src="' + good.goodsImg + '" width="70" height="70" ><span>' + handleUndefined(good.goodsInfoName) + '</span></abbr>';
            goodsListhtml += '<abbr>' + good.goodsInfoItemNo + '</abbr>';
            goodsListhtml += '<abbr class="returnCount">' + good.goodsInfoNum + '</abbr>';
            goodsListhtml += '<abbr><span class="eachPrice">' + addCommas(good.goodsInfoOldPrice,2) + '</span>邮豆</abbr>';
            goodsListhtml += '<abbr><span class="sumPrice">' + addCommas(good.goodsInfoSumPrice,2) + '</span>邮豆</abbr>';
            goodsListhtml += '<abbr style="width: 150px"><input class="itemNumReduce" type="hidden" value="-"><input maxlength="2" readonly="readonly" class="itemNum" value="1" type="text"><input class="itemNumAdd" type="hidden" value="+" /></abbr>';
            goodsListhtml += '</dd>';

        });
        $("#returnGoodsList").empty().append(goodsListhtml);
        $(".itemNum").each(function(){
            $(this).val($(this).parent().siblings(".returnCount").html())
        })
        var  html = $("#payDetail").html()
        $("#detailShow").empty().append(html)
        //var returnPrice="0";
        //$(".sumPrice").each(function(){
        //    returnPrice = parseInt(returnPrice) + parseInt($(this).html().replace(",","").replace(",","").replace(",",""));
        //});
        //$("#returnPrice").html(addCommas(returnPrice,2));
    });
    $(document).on("click","#XX1,#CustomerReturnCancel",function(){
        discoverHtml();
        $(".CustomerReturn").fadeOut(500);
    });
    $(document).on("click",".checkboxForReturn",function(){
        if($(this).prop("checked")){
            var thisVal = $(this).parent().find(".sumPrice").html();
            var sumVal = $("#returnPrice").val();
            $("#returnPrice").val( parseInt( sumVal ) + parseInt( thisVal ) );
        }
        else{

        }
    });


    $("#CustomerReturnConfrim").click(function(){

        var orderNo = $("#returnNum").html();
        var inputCheck = true;
        var str = /^[1-9]*[1-9][0-9]*$/;
        $(".itemNum").each(function(){
            var value = $(this).val();
            if( !str.test( value ) ){
                inputCheck = false;
            }
        });
        var backGoodsId = '{';
        var backGoodsIdFlag = true;
        $("#returnGoodsList dd").each(function(){
            if( $(this).find(".itemNum").val() != '' ){
                backGoodsId += '"' + $(this).find(".checkboxForReturn").val() + '":' + $(this).find(".itemNum").val() + ',';
            }
            else{
                backGoodsIdFlag = false;
            }
        });
        if(backGoodsId.length > 3){
            backGoodsId = backGoodsId.substring(0,backGoodsId.length-1)
        };
        backGoodsId += '}';
        var reason = $("#customerReturnReason option:selected").val();
        var credential = $("input[name='proof']:checked").val();
        var remark = $(".CustomerReturn textarea").val();
        if( backGoodsIdFlag != false && remark != '' && inputCheck != false){
            $.post("../web/api/ordermanage/valetBackOrder",{
                orderNo:orderNo,
                backGoodsId:backGoodsId,
                reason:reason,
                credential:credential,
                remark:remark
            },function( data ){
                if( data.response == 'success' ){
                    response_ensure_alert("success","申请退单成功",function(){
                        $(".CustomerReturn").fadeOut();
                        discoverHtml();
                        window.location.reload()

                    });
                }
            },'json');
        }
        else if( remark == '' ){
            response_ensure_alert("error","申请原因不能为空，请输入",function(){
                console.log("退货失败" + consoleNowTime());
            });
        }
        else if( backGoodsIdFlag == false ){
            response_ensure_alert("error","退货数量不能为空，请重新输入",function(){
                console.log("退货失败" + consoleNowTime());
            });
        }
        else if( inputCheck == false ){
            response_ensure_alert("error","退货数量不正确，请重新输入",function(){
                console.log("退货失败" + consoleNowTime());
            });
        }

    });

    //代客退货 退款金额
    $(".itemNum").on('input',function(){

    });

    //导出全部订单
    //$("#accExport").click(function(){
    //    var type = '1';
    //    window.location.href = '../web/api/exportExcel/orderDown?'+'&orderNoString='+ orderNo + '&status=' + status + "&username=" + username + "&contactPhone=" + contactPhone + "&createStart=" + createStart + "&createEnd=" + createEnd + "&payStart=" + payStart + "&payEnd=" + payEnd + "&enterpriseId=" + enterpriseId+ '&type=' + type;
    //    //window.location.href = '../web/api/exportExcel/orderDown?' + 'status=' + status + "&username=" + accountCust + "&contactPhone=" + accountTel + "&createStart=" + createTimeStart + "&createEnd=" + createTimeEnd + "&payStart=" + payTimeStart + "&payEnd=" + payTimeEnd;
    //});

    $("#accExport").click(function(){
        if(orderNo == ''){
            if(typeof(receiver) == 'undefined'){
                receiver="";
            }
            window.location.href = '../web/api/exportExcel/orderDown?'+'&orderNoString='+ orderNo + '&status=' + status + "&username=" + username + "&contactPhone=" + contactPhone + "&createStart=" + createStart + "&createEnd=" + createEnd + "&payStart=" + payStart + "&payEnd=" + payEnd + "&enterpriseId=" + enterpriseId +'&receiver=' + receiver;

        }
        else{
            if(typeof(receiver) == 'undefined'){
                receiver="";
            }
        window.location.href = '../web/api/exportExcel/orderDown?'+'&orderNoString='+ '["'+ orderNo +'"]' + '&status=' + status + "&username=" + username + "&contactPhone=" + contactPhone + "&createStart=" + createStart + "&createEnd=" + createEnd + "&payStart=" + payStart + "&payEnd=" + payEnd + "&enterpriseId=" + enterpriseId +'&receiver=' + receiver;
        //window.location.href = '../web/api/exportExcel/orderDown?' + 'status=' + status + "&username=" + accountCust + "&contactPhone=" + accountTel + "&createStart=" + createTimeStart + "&createEnd=" + createTimeEnd + "&payStart=" + payTimeStart + "&payEnd=" + payTimeEnd;
        }
    });
    //导出选中
    $("#accChooseExport").click(function(){
        var orderInfoNumber = '[';
        for(var i = 0;i < checked_array.length;i++){
            for(var j = 0;j < outport_array[i].length;j++){
                orderInfoNumber += '"' + outport_array[i][j] + '",';
            }
        }
        orderInfoNumber = orderInfoNumber.substring(0,orderInfoNumber.length - 1);
        orderInfoNumber += ']';

        if(orderInfoNumber.length > 2){
            window.location.href = '../web/api/exportExcel/orderDown?orderNoString=' + orderInfoNumber;
        }
    });


})


function jPages(url,contentStr,target,type,perpage,reason,batchId,array,callbackChecked){
    $("#" + contentStr).empty();
    /*
     * url               api地址
     * postOrget         ajax类型/0Get,1Post
     * contentDom        内容区DomId
     * targetDom         页码区DomId
     * type              插入内容类型
     * size              每页显示数目
     * array             可选参数列表
     * */
    var orderNo = '';
    var status = '';
    var username = '';
    var receiver = '';
    var contactPhone = '';
    var createStart = '';
    var createEnd = '';
    var payStart = '';
    var payEnd = '';
    var selfPick = '';
    var enterpriseId = '';

    if( array != undefined ){
        orderNo = array[0];
        status = array[1];
        username = array[2];
        receiver = array[3];
        contactPhone = array[4];
        createStart = array[5];
        createEnd = array[6];
        payStart = array[7];
        payEnd = array[8];
        selfPick = array[9];
        enterpriseId = array[10];
        console.log(enterpriseId);
    }

    $.post(url,{
        orderNo:orderNo,
        status:status,
        username:username,
        receiver:receiver,
        contactPhone:contactPhone,
        createStart:createStart,
        createEnd:createEnd,
        payStart:payStart,
        payEnd:payEnd,
        selfPick:selfPick,
        enterpriseId:enterpriseId,
        page:1,
        size:perpage
    },function( data ){
        if( data.response == 'success' ){


            appendHtml(contentStr,type,data.data.content);

            $("#" + target).pagination(data.data.totalElements, {
                prev_text: "＜",
                next_text: "＞",
                num_edge_entries: 2,
                num_display_entries: 8,
                items_per_page:perpage,
                //回调
                callback: function(page){
                    $.post(url,{
                        orderNo:array[0],
                        status:array[1],
                        username:array[2],
                        receiver:array[3],
                        contactPhone:array[4],
                        createStart:array[5],
                        createEnd:array[6],
                        payStart:array[7],
                        payEnd:array[8],
                        selfPick:array[9],
                        enterpriseId:array[10],
                        page:page+1,
                        size:perpage
                    },function(data){
                        if(data.response == 'success'){
                            appendHtml(contentStr,type,data.data.content);
                            if( typeof callbackChecked == 'function' ){
                                callbackChecked(page);
                            }
                        }
                    },'json');
                }
            });
        }
        else{

        }
    });
}

function appendHtml(str,type,data){
    $("#" + str).empty();
    var html = '';
    data.map(function(o){
//获取商品总数
        var count = 0;
        var prev_time = handleDate_prev(new Date(o.createTime));
        var next_time = handleDate_next(new Date(o.createTime));

//获取订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废
        var orderStatus = orderStatus_handle( o.orderStatus );
        if( orderStatus == '待发货' ){
            if( o.selfPick ){
                orderStatus = '待提货';
            }
            else{
                orderStatus = '待发货';
            }
        }
        html += '<tr style="display:inline-block;vertical-align: middle; width:1600px; height:120px; margin-bottom:10px;">';
        html += "<td><input class='data' type='hidden' value='" + JSON.stringify(o) + "' /></td>";
        html += '<input type="hidden" class="order_type" value="' + o.selfPick + ',' + o.isValet + '" />';
        try{
            html += '<td style="display:inline-block; width:1550px; text-align:left; height:30px;background: #edf3f8; line-height:30px; padding-left:50px; position:relative;"><input name="accountNumberSelect" class="accountNumber" type="checkbox" />订单号：<span style="margin-right: 50px;" id="accNum">' + o.orderCode + '</span>所属网点：<span>' + handleUndefined(o.pickInfo.enterpriseName) + '</span>';
        }catch(e){
            html += '<td style="display:inline-block; width:1550px;text-align:left; height:30px; background: #edf3f8; line-height:30px; padding-left:50px; position:relative;"><input name="accountNumberSelect" class="accountNumber" type="checkbox" />订单号：<span style="margin-right: 50px;" id="accNum">' + o.orderCode + '</span>所属网点：<span>暂无网点</span>';
        }
        if( isTop ){
            if( orderStatus == '待付款' ){
                html += '<div style="position:absolute; right:80px; top:0px;"><span><a class="detailCheck">查看详情</a>  <i>- </i> <a class="changestatus">修改状态</a>   <i>-</i>  <a class="changeprice">修改金额</a>   <i>- </i> <a class="cancelorder">取消订单</a> </span></div></td>';
            }
            else{
                html += '<div style="position:absolute; right:80px; top:0px;"><span><a class="detailCheck">查看详情</a></span></div></td>';
            }
        }
        else{
            if( orderStatus == '已完成' && isEnd ){
                html += '<div style="position:absolute; right:80px; top:0px;"><span><a class="detailCheck">查看详情</a>  <i>- </i> <a class="custReturn">代客退单</a></div></td>';
            }
            else{
                html += '<div style="position:absolute; right:80px; top:0px;"><span><a class="detailCheck">查看详情</a></div></td>';
            }
        }
        var flag = 0;
        html += '<td style="display:inline-block; width:400px; height:100px; line-height:100px; text-align:center; ">' ;
        o.orderGoodsList.map(function(object){
            flag=flag + 1;
            if( flag < 3){
                html += '<img src="' + handleUndefined(object.goodsImg) + '" width="50" height="50" />';
            }
        });
        html += '</td>';
        html += '<td style="display:inline-block; width:125px; height:100px;"><section style="margin-top:20px;margin-left: 0px!important;">' + addCommas(o.orderOldPrice,2) + '邮豆<br />(&nbsp;<span>'
        var goodsInfoNum = 0;
        o.orderGoodsList.map(function(object){
            goodsInfoNum +=   parseInt(object.goodsInfoNum);
        });
        html += goodsInfoNum + '</span>件&nbsp;)</section></td>';
        if( o.selfPick == true ){
            html += '<td style="display:inline-block; width:280px; height:100px;"><section style="margin-top:10px;">用户名：' + handleUndefined(o.customerInfo.username) + '<span></span><br />收件人：<span class="pickName">' + handleUndefined(o.customerInfo.fullName) + '</span><br />';
            html += '联系电话：<span class="pickPhone">' + handleUndefined(o.customerInfo.contactPhone) + '</span><br /></section></td>';
        }
        else if( o.selfPick == false ){
            html += '<td style="display:inline-block; width:280px; height:100px;"><section style="margin-top:10px;">用户名：' + handleUndefined(o.customerInfo.username) + '<span></span><br />收件人：<span class="pickName">' + handleUndefined(o.shippingPerson) + '</span><br />';
            html += '联系电话：<span class="pickPhone">' + handleUndefined(o.shippingMobile) + '</span><br /></section></td>';
        }



        html += '<td style="display:inline-block; width:250px; height:100px; padding:10px 0px;"><section style="margin-top:20px;">' + prev_time + '<br />' + next_time + '<input type="hidden" class="hidden_create_time" value="' + prev_time + ' ' + next_time +  '"></section></td>';
        if( orderStatus == '已完成' ){
            html += '<td style="display:inline-block; width:200px; height:100px;"><abbr style="display: inline-block;color: #54b3e3;margin-top: 30px;padding: 2px;border-radius: 2px;" class="orderStatus" data-orderStatus="' + o.orderStatus + '">' + orderStatus + '</abbr></td>';
        }else{
            html += '<td style="display:inline-block; width:200px; height:100px;"><abbr style="color: #ff3300;display: inline-block; margin-top: 30px;padding: 2px;border-radius: 2px;" class="orderStatus" data-orderStatus="' + o.orderStatus + '">' + orderStatus + '</abbr></td>';
        }
        html += '<td style="display:inline-block; width:180px; height:100px; line-height:80px;"><span id="sumNum">' + addCommas(o.orderPrice,2) + '邮豆</span></td>';
        try {
            html += '<td style="display:inline-block; width:120px; text-align: center; height:100px; line-height:80px;overflow: hidden"><span class="checkstate" data-storeId="' + o.business.businessId + '">' + handleUndefined(o.business.businessName) + '</span></td></tr>';
        }
        catch(e){
            html += '<td style="display:inline-block; width:120px; text-align: center; height:100px; line-height:80px;overflow: hidden"><span class="checkstate">' + handleUndefined(o.pickInfo.enterpriseName) + '</span></td></tr>';
        }
    });
    $("#"+ str).append(html);
}



function handleDate_prev(time){
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var day = time.getDate();
    if( month < 9 ){
        month = '0' + month;
    }
    if( day < 10 ){
        day = '0' + day;
    }
    return year + '-' + month + '-' + day;
}

function handleDate_next(time){
    var hour = time.getHours();
    var minute = time.getMinutes();
    if( hour < 10 ){
        hour = '0' + hour;
    }
    if( minute < 10 ){
        minute = '0' + minute;
    }
    return hour + ':' + minute;
}

function handleDate_fuck_jiangtao(time){
    var hour = time.getHours();
    var minute = time.getMinutes();
    var second = time.getSeconds();
    if(minute < 10){
        minute = '0' + minute;
    }
    if(second < 10){
        second = '0' + second;
    }
    return hour + ':' + minute + ':' + second;
}

function getData( type ){
    if ( type == '0' ){
        $(".accountTable li:nth-child(1)").trigger("click");
    }
    else if ( type == '1' ){
        $(".accountTable li:nth-child(2)").trigger("click");
    }
    else if ( type == '2' ){
        $(".accountTable li:nth-child(3)").trigger("click");
    }
    else if ( type == '3' ){
        $(".accountTable li:nth-child(4)").trigger("click");
    }
    else if ( type == '4' ){
        $(".accountTable li:nth-child(5)").trigger("click");
    }
    else if ( type == '5' ){
        $(".accountTable li:nth-child(6)").trigger("click");
    }
    else if ( type == '6' ){
        $(".accountTable li:nth-child(7)").trigger("click");
    }
}

function handleFullTime(time,x,y){
    var result = '';
    result = time.substring(0,x);
    result += " " + time.substring(x + y + 1);
    return result;
}
enterSearch( $("input[name='accountNum'],input[name='accountUserName'],input[name='accountTel'],input[name='accountCust']"),$("#accSearch"))