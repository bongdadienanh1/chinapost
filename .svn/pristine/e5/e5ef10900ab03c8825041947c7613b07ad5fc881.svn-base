$(document).ready(function(e) {
    var keyUpLock = true;
    $("div.holder").jPages({
        containerID : "itemContainer"
    });

    $(".detailList").draggable();

    $("#BudGo").click(function(){
        coverHtml();
        $(".Budget").fadeIn(500);
        $(".CashAssign").fadeOut(500);
        $(".CashPwd").fadeOut(500);
    })
    $(".Budget h1 i").click(function(){
        discoverHtml()
        $(".Budget").fadeOut(500);
    })
    $("#CashGo").click(function(){
        coverHtml()
        $(".CashAssign").fadeIn(500);
        $(".Budget").fadeOut(500);
        $(".CashPwd").fadeOut(500);
    })
    $(".CashAssign h1 i").click(function(){
        discoverHtml()
        $(".CashAssign").fadeOut(500);
    })
    $("#Cashsub").click(function(){
        var checkNum1 =/^\d+(\.\d+)?$/;
        val1=$("#checkval").val()
        var flag = true;
        var flag2 = true;

        $(".CashChkbox:checked").each(function(){
            var args = $(this).siblings(".Cashtext").val().split(".");
            if(args[1] == "" ){
                $(this).siblings(".Cashtext").val("");
                flag = false;
                flag2 = false;
            }else if(parseInt( $(this).siblings(".Cashtext").val()) < 0){
                $(this).siblings(".Cashtext").val("");
                flag = false;
                flag2 = false;
            }
        })
        if(flag2){
            if(flag) {
                if (checkNum1.test(val1)&&val1!=0) {
                    $(".CashAssign").fadeOut(500);
                    $("#CashPwd").val("");
                    $(".CashPwd").fadeIn(500);
                } else {
                    data_type_alert("金额不正确", "error")
                }
            }
        }else{
            data_type_alert("金额输入不正确","error")
        }

    })
    $("#CashCancel").click(function(){
        discoverHtml();
        $(".CashPwd").fadeOut(500);
    })
    $(".CashChkbox").click(function() {
        var temp = 0;
        $(".CashChkbox").each(function(){

            if($(this).is(':checked')) {
                temp = temp + Number($(this).siblings(".Cashtext").val());
            }
            $("#checkval").attr("value",temp);
        });
    })
    $(document).on("input",".Cashtext",function(){

    })
    $(".Cashtext").on("input",function(){
        var temp = 0;
        var _this = this;
        var checkNum =/^\+?(\d*\.\d{3})$/
        var val=$(this).val()
        var bigInt = changeNum(val)
        var html = '<div class="bigInt">'+ bigInt +'</div>'
        if( val == ""){
            $(this).siblings(".bigInt").remove()
        }else{
            $(this).siblings(".bigInt").remove()
            $(this).after(html)
        }
        if(checkNum.test(val)){
            var s = $(_this).val();
            s= s.substring(0, s.length-1);
            $(_this).val(s);
            if( keyUpLock == true ){
                response_ensure_alert("error","请输入正确的数字",function(){
                    keyUpLock = true;
                    $(_this).removeAttr("disabled");

                });
                $(this).prop("disabled","disabled");
                keyUpLock = false;
            }
        }else{
            var checkval=$("#checkval").val();
            $(".CashChkbox").each(function(){

                if($(this).is(':checked')) {
                    temp = temp + Number($(this).siblings(".Cashtext").val());
                }
                $("#checkval").attr("value",temp);
            });
        }
    })
});

$(document).ready(function(){
    $("#Ucoinsub").click(function(){
        console.log(1);
        var value = $("#BudAmout").val();
        var reason = $("#BudReason").val();
        var args = value.split(".");
        var temp = ( args[1] == undefined ) ? '' : args[1];
        if( temp.length < 3 && args[1] !="" ){
            $.post("../web/api/wealth/requisition",{
                'amount': value,
                'reason': reason
            },function(data){
                if( data.response == 'success' ){
                    discoverHtml();
                    response_ensure_alert("success","申请预算成功",function(){
                        window.location.href = 'UCoinManager';
                    });
                }
                else{
                    data_type_alert(data.data.text,"error")
                }
            },'json');
        }else{
            data_type_alert("请输入正确的金额，必须是正整数或者2位小数","error")
        }
    });
    $("#CashPwdSub").click(function(){
        coverHtml()
        var paykey = $("#CashPwd").val();
        var str = '{';
        var length = $("#CashAssign li").length;
        var _this = this;
        for(var i = 0;i<length;i++){
            var id_dom = $("#CashAssign li input[type='checkbox']")[i];
            var value_dom = $("#CashAssign li input[type='text']")[i]
            if( id_dom.checked ){
                var id = id_dom.value;
                var value = value_dom.value;
                str += '"';
                str += id;
                str += '":';
                str += value;
                str += ',';
            }
        }
        str = str.substring(0,str.length-1);
        str += '}';
        console.log(str);
        $.post("../web/api/wealth/allocation",{
            'paykey':paykey ,
            'allocationJson':str
        },function( data ){
            if( data.response == 'success' ){
                $("#CashPwd").val("");
                $(".CashPwd").hide()
                discoverHtml();
                ensure_alert('UCoinManager');

            }
            else{
                $("#CashPwd").val("");
                data_type_alert(data.data.text,"error")
            }
        },'json');
    });

    //处理表格占比
    $(".percent").html(function(){
        var percent = '0%';
        var sonWealth = $(this).data("son");
        var totalWealth = $(this).data("total");
        var result =  (parseInt(sonWealth) / parseInt(totalWealth)).toFixed(9);
        result = result.substring(0,6);
        if( isNaN(result) ){
            return percent;
        }
        else{
            return (result*100).toFixed(2) + '%';
        }
    });

    //账单ajax账单
    $(".requestTable ul li").click(function(){
        var array = [];
        var value = $(".reqOn").attr("value");
        if( value == 0 ){
            $(".billManager").css("display","block");
            $(".requisition").css("display","none");
            $.post("../web/api/wealth/getCurrentBill",{

            },function(data){
                if( data.response == 'success' && data.data != '' ){
                    var html = '';
                    data.data.map(function(object){
                        try{
                            var date = new Date(object.createTime);
                            date = handleDate_prev(date) + '  ' + handleDate_next(date);
                        }
                        catch(e){
                            var date = '';
                        }
                        var count = object.count;
                        var persons = ( handleUndefined(count) == 0 ) ?  '' : ((handleUndefined(count) > 1) ? '多人' : '一人');
                        var fee = object.fee;
                        var type = object.type;
                        var code = object.code;
                        var id = object.id;
                        html += '<dd>';
                        html += '<input class="type" type="hidden" data-id="' + id + '" value="' + object.type + '">';
                        html += '<input class="businessType" type="hidden" data-id="' + id + '" value="' + object.sendType + '">';
                        html += '<abbr class="date">' + date + '</abbr>';//支付时间
                        html += '<abbr class="code">' + handleUndefined(code) + '</abbr>';//交易单号
                        html += '<abbr class="type">' + handleUndefined(type) + '</abbr>';//交易单号
                        html += '<abbr style="display: none" class="id">' + handleUndefined(id) + '</abbr>';//交易单号
                        html += '<abbr class="status">已完成</abbr>';//账单状态
                        html += '<abbr class="fee">' +  addCommas(fee,2).replace("-","") + '邮豆' + '</abbr>';//总金额
                        html += '<abbr class="count">' +  handleUndefined(count) + '</abbr>';//总笔数
                        html += '<abbr class="myBillListCheck"><input type="button" value="查看详情" class="detail" /></abbr>';//操作
                        html += '</dd>';
                    });
                    $(".billManager dd").remove();
                    $(".billManager").append(html);
                }
            });
        }
        else if( value == 1 ){
            $(".billManager").css("display","none");
            $(".requisition").css("display","block");
            $.post("../web/api/wealth/getCurrentReq",{},function(data){
                if( data.response == 'success' && data.data != '' ){
                    var html = '';
                    data.data.map(function(object){
                        try{
                            var date = new Date(object.createTime);
                            date = handleDate_prev(date) + '  ' + handleDate_next(date);
                        }
                        catch(e){
                            var date = '';
                        }
                        var count = object.count;
                        var persons = ( handleUndefined(count) == 0 ) ?  '' : ((handleUndefined(count) > 1) ? '多人' : '一人');
                        var status = object.status;
                        var fee = object.fee;
                        var type = object.type;
                        var code = object.code;
                        var remark = object.remark;
                        var payFee = object.payFee;
                        var id = object.id;
                        html += '<dd>';
                        html += '<input class="type" type="hidden" data-id="' + id + '" value="' + object.type + '">';
                        html += '<abbr class="date">' + date + '</abbr>';//创建时间
                        html += '<abbr class="code">' +  handleUndefined(code) + '</abbr>';//单据编号
                        html += '<abbr class="status">' +  handleReqStatus(status) + '</abbr>';//单据状态
                        html += '<abbr class="fee">' +  addCommas(fee,2) + '邮豆' + '</abbr>';//申请金额
                        html += '<abbr class="reamrk">' + handleUndefined(remark) + '</abbr>';//申请原因
                        html += '<abbr class="payFee">' + addCommas(payFee,2) + '</abbr>';//支付金额
                        html += '</dd>';
                    });
                    $(".requisition dd").remove();
                    $(".requisition").append(html);
                }
            });
        }
    });
    $(document).on("click",".detail",function(){
        coverHtml();
        $(".detailList").fadeIn(500);
        var val=$(this).parent().siblings(".type").val();
        var count = $(this).parent().siblings(".count").html();
        var target = ( handleUndefined(count) == 0 ) ?  '' : ((handleUndefined(count) > 1) ? '多人' : '一人');
        var type = $(this).parent().siblings(".type").val();
        var fee = $(this).parent().siblings(".fee").html();
        var date = $(this).parent().siblings(".date").html();
        var code = $(this).parent().siblings(".code").html();
        var remark = $(this).parent().siblings(".remark").html();
        var businessType = $(this).parent().siblings(".businessType").val();
        var batchId = $(this).parent().siblings(".type").data("id");

        if( val == '上级财富分配' ){
            $(".detailList li[value='businessType']").hide();
            $(".detailList li[value='note']").hide();
            $(".secondTable").hide();
            $(".detailList li[value='orderNumber']").hide();
            $("input[name='type']").val(type);
            $("input[name='sumMoney']").val(fee);
            $("input[name='sumNumber']").val(count);
            $("input[name='date']").val(date);
            $("input[name='code']").val(code);
            $.post("../web/api/billmanage/parentAllocat",{
                code:code,
                start:"",
                end:""
            },function(data){
                if(data.response == "success"){
                    $("input[name='target']").val( data.data.content[0].outName);
                }else{
                    data_type_alert(data.data.text,"error")
                }
            },"json")


        }else if(val=="邮豆发放"){
            $(".secondTable").show();
            $(".detailList li[value='businessType']").show();
            $(".detailList li[value='note']").show();
            $(".detailList li[value='orderNumber']").hide();
            $("input[name='target']").val(target);
            $("input[name='type']").val(type);
            $("input[name='sumMoney']").val(fee);
            $("input[name='sumNumber']").val(count);
            $("input[name='date']").val(date);
            $("input[name='code']").val(code);
            $("input[name='businessType']").val(businessType);
            $("input[name='remark']").val(remark);
            $(".sendTarget").html("发放对象")
            $(".sendTargetM").html("发放金额")
            ajaxPages("../web/api/billmanage/getGrandByBatch","itemContainer_second","holder_second",'bill_second',5,'',batchId,'',function(){
                if( count > 1 ){
                    $("input[name='target']").val('多人');
                }
                else{
                    $("input[name='target']").val('一人');
                }
            });
        }else if(val=="邮豆扣减"){
            $(".secondTable").show();
            $(".detailList li[value='businessType']").hide();
            $(".detailList li[value='note']").show();
            $(".detailList li[value='orderNumber']").hide();
            $("input[name='target']").val(target);
            $("input[name='type']").val(type);
            $("input[name='sumMoney']").val(fee);
            $("input[name='sumNumber']").val(count);
            $("input[name='date']").val(date);
            $("input[name='code']").val(code);
            $("input[name='businessType']").val(businessType);
            $("input[name='remark']").val(remark);
            $(".sendTarget").html("扣减对象")
            $(".sendTargetM").html("扣减金额")
            ajaxPages("../web/api/billmanage/getGrandByBatch","itemContainer_second","holder_second",'bill_second',5,'',batchId,'',function(){
                if( count > 1 ){
                    $("input[name='target']").val('多人');
                }
                else{
                    $("input[name='target']").val('一人');
                }
            });
        }else if(val=="网点订单补贴"){
            $(".detailList li[value='businessType']").hide();
            $(".detailList li[value='note']").hide();
            $(".detailList li[value='orderNumber']").show();
            $(".detailList li[value='orderNumber'] span").html("订单号:");
            $(".secondTable").hide();
            $.post("../web/api/billmanage/getOrderSubsidy",{
                orderId:batchId
            },function(data){
                if(data.response == "success"){
                    $("input[name='target']").val( data.data.fullname + '  (' + data.data.idCardNo + ')');
                    $("input[name='type']").val("网点订单补贴");
                    $("input[name='sumMoney']").val(data.data.subsidyPrice);
                    $("input[name='sumNumber']").val(1);
                    $("input[name='date']").val(handleDate_prev(new Date(data.data.payTime)) + "  " + handleDate_next(new Date(data.data.payTime)));
                    $("input[name='code']").val(data.data.orderCode);
                    $("input[name='orderNumber']").val(data.data.orderCode);
                }else{
                    data_type_alert(data.data.text,"error")
                }
            },"json")


        }else if(val=="订单退款返还"){
            $(".detailList li[value='businessType']").hide();
            $(".detailList li[value='note']").hide();
            $(".detailList li[value='orderNumber']").show();
            $(".detailList li[value='orderNumber'] span").html("退单号:");
            $(".secondTable").hide();
            $.post("../web/api/billmanage/getBackOrderSubsidy ",{
                backOrderId:batchId
            },function(data){
                if(data.response == "success"){
                    $("input[name='target']").val( data.data.fullname + '  (' + data.data.idCardNo + ')');
                    $("input[name='type']").val("订单退款返还");
                    $("input[name='sumMoney']").val(data.data.backSubsidyPrice);
                    $("input[name='sumNumber']").val(1);
                    $("input[name='date']").val(handleDate_prev(new Date(data.data.payTime)) + "  " + handleDate_next(new Date(data.data.payTime)));
                    $("input[name='code']").val(data.data.backOrderCode);
                    $("input[name='orderNumber']").val(data.data.backOrderCode);
                }else{
                    data_type_alert(data.data.text,"error")
                }
            },"json")


        }
    });
    $("#xx").click(function(){
        discoverHtml();
        $(".detailList").fadeOut(500)
    });

    $(".requestTable ul li:nth-child(1)").trigger('click');

    $(".moreDetail").click(function(){
        if($(".reqOn").val() == 0){
            $("#ucoinmanager",window.parent.frames['mainleft'].document).removeClass("clickOn");
            $("#billmanager",window.parent.frames['mainleft'].document).addClass("clickOn");
            window.location.href = 'BillManager';
        }
        else{
            $("#ucoinmanager",window.parent.frames['mainleft'].document).removeClass("clickOn");
            $("#requisition",window.parent.frames['mainleft'].document).addClass("clickOn");
            window.location.href = 'Requisition';
        }

    });
});


