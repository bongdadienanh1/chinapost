$(document).ready(function(){
    $(document).on("click",".DepotDetail dd a",function(){
        var _this = this;
        if( $(this).html() == '+' && $(this).parent().find("dl").length == 0){
            var enterpriseId = $(this).data("id");
            $.post("../web/api/inventory/getSonEnterpriseInfo",{
                enterpriseId:enterpriseId
            },function(data){
                if( data.response == 'success' ){
                    var html = '<dl>';
                    data.data.map(function(object){
                        html += '<dd>'
                        if( object.end  ){
                            html += '<a data-id="' + object.id + '">-</a>';
                        }
                        else{
                            html += '<a data-id="' + object.id + '">+</a>';
                        }

                        html += '<input type="radio" name="DepotRadio"/><span>' + object.accountName + '</span>'
                        html += '</dd>';
                    });
                    html += '</dl>';
                    $(_this).parent().append(html);
                }
                else{

                }
            },'json');
        }
    });
    var enter_lock = true;
    $("input[name='userId']").keydown(function(event){
        if(event.keyCode==13){
            if( enter_lock == true ){
                enter_lock = false;
                $("#sub").trigger("click");
            }
        }
    });

    $(document).on("click",'.DepotDetail a',function(){
        if( $(this).html( ) == "+" ){
            $(this).siblings("dl").slideDown();
            $(this).html("-");
        }else if( $(this).html( ) == "-" ){
            $(this).siblings("dl").slideUp();
            $(this).html("+");
        }
    });
    $(document).on("click","input[name='DepotRadio']",function(){
        var rangeId=$(this).siblings("a").data("id");
        var rangeName=$(this).siblings("span").html();
        $("#range").val(rangeName);
        $("#rangeId").val(rangeId);
    })
    $('#sub').click(function(){
        $("#updateUserName,#newMemberRoadDetail,#updateUserPhoneNo").val("");
        $("#newMemAddressP option[value!='0'],#newMemAddressC option[value!='0'],#newMemAddressD option[value!='0']").remove();
        $.get("../web/api/common/provincies",function(data){
            if( data.response == 'success' ){
                data.data.map(function( object ){
                    var html = '';
                    html += '<option value="' + object.provinceId +'">';
                    html += object.provinceName;
                    html += '</option>';
                    $("#newMemAddressP").append(html);
                });
            }
        },'json');
        var checkNum = /\d{6}[12][7890][0-9]{2}[01][0-9][0-3][0-9]{4}[xX0-9]/;
        var val = $('input[name="userId"]').val();
        var pass = IdentityCodeValid(val) ;
        if ( pass ){
            $.get("../web/api/ucoingrand/isNew",{"idCard":val},function(data){
                if( data == true ){
                    coverHtml()
                    $("#updateUserInfo").show()
                }
                else{
                    window.location.href = "oldUser?idCard=" + val;
                }
            });
        }else{
            response_ensure_alert("warning","身份证未通过校验");
        }
    });

    $("#outPutUcoin").click(function(){

    })
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
    $("#outputDetailCancel").click(function(){
        discoverHtml();
        $(".outputDetail").hide();
    });
    $("#outPutUcoin").click(function(){
        coverHtml();
        $(".outputDetail").show();
    })
    $( ".outputDetail" ).draggable();
    //导出邮豆发放记录
    $("#outputDetailConfirm").click(function(){
        var enterpriseId = $("#rangeId").val();
        var typeId = $("input[name='businessTypeCheck']:checked").val();
        var start = $("#datetimepicker_start").val();
        var end = $("#datetimepicker_end").val();
        if(start!="" && end!="" ){
        if(end >= start){
            if( typeId != '' && typeId != undefined ){
                if(enterpriseId != "" && enterpriseId != undefined){
                    $("body").append('<img id="loadingModule" style="position: fixed;top:30%;left:30%;z-index: 999999;" class="loading" src="../static/img/loading.gif"/>');
                    window.location.href = "../web/api/exportExcel/grandHistory?typeId=" + typeId + "&start=" + start + "&end=" + end + "&enterpriseId=" + enterpriseId;
                }else{
                    data_type_alert("导出范围不能为空","error")
                }
            }else{
                response_ensure_alert("error","请选择业务类型",function(){
                    console.log("导出失败" + consoleNowTime());
                });
            }
        }else{
                data_type_alert("时间必填且结束时间必须大于开始时间","error")
        }
        }else{
            data_type_alert("请选择要导出的邮豆发放起止时间段","error")
        }

        setTimeout(function(){
            $("#loadingModule").remove();
        },500);
    });
    $("#updateUserInfoCancel").click(function(){
        discoverHtml();
        $("#updateUserInfo").hide()
    });
    $("#updateUserInfo").draggable()
    $.get("../web/api/common/provincies",function(data){
        if( data.response == 'success' ){
            data.data.map(function( object ){
                var html = '';
                html += '<option value="' + object.provinceId +'">';
                html += object.provinceName;
                html += '</option>';
                $("#newMemAddressP").append(html);
            });
        }
    },'json');

    $("#newMemAddressP").change(function(){
        $("#newMemAddressC option[value!='0'],#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/cities",{
            "provinceId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.cityId +'">';
                    html += object.cityName;
                    html += '</option>';

                    $("#newMemAddressC").append(html);
                });
            }
        },'json');
    });
    $("#newMemAddressC").change(function(){
        $("#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/districts",{
            "cityId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.districtId +'">';
                    html += object.districtName;
                    html += '</option>';
                    $("#newMemAddressD").append(html);
                });
            }
        },'json');
    });


    $("#updateUserInfoConfirm").click(function() {
        var customerId=$("input[name='userId']").val();
        var userName = $("#updateUserName").val();
        var userPhone = $("#updateUserPhoneNo").val();
        var addPV = $("#newMemAddressP").val();
        if (addPV == "0") {
            addPV = "";
        }
        var addCV = $("#newMemAddressC").val();
        if (addCV == "0") {
            addCV = "";
        }
        var addDV = $("#newMemAddressD").val();
        if (addDV == "0") {
            addDV = "";
        }
        var addDetail = $("#newMemberRoadDetail").val();
        if (userName && userPhone.length == 11 && /^[1-9]*[1-9][0-9]*$/.test(userPhone) && addPV && addCV && addDV && addDetail) {
            $.post("../web/api/customer/newCustomer", {
                'idCard': customerId,
                'name': userName,
                'linkPhone': userPhone,
                'provinceId': addPV,
                'cityId': addCV,
                'districtId': addDV,
                'addr': addDetail,
                imgUrl:"",
                managerNo:"",
                tags:"",
                remark:""
            }, function (data) {
                if (data.response == 'success') {
                    discoverHtml();
                    $("#sub").trigger("click")
                }
                else {
                    data_type_alert(data.data.text, "error");
                }
            });
        } else {
            data_type_alert("请完善信息并检查手机号是否正确", "error")
        }
    });
});

