$(document).ready(function(){
    $("input").blur(function(){
        var flag = 0;
        $("input").map(function(dom){
            if(dom){
                //变色 激活按钮
            }
        });
    });
    $("#cancel").click(function(){
        window.location.href="UbaoSend"

    });
    $(document).on("change","#businessType",function(){
        //清空数据
        $("#ucoinMarketing").val(0);
        $("#ucoinSale").val();
        var businessTypeID = $(this).val();
        $(".TYPE").remove();
        $.post("../web/api/ucoingrand/getParam",{
            typeId:businessTypeID
        },function(data){
            if(data.response == "success"){
                var valueMap = '';
                var Mapcount = 0;
                data.data.map(function(object){
                    Mapcount++;
                    if(object.paramType == "TYPE_ENUM"){
                        if(object.required == false) {
                            var html = '<li data-pId="' + object.paramId + '" class="TYPE" >' + '<abbr>'+ '<span class="typeName">' + object.paramName+'</span>' + ':' + '</abbr>' + '<select>'
                            $.post("../web/api/ucoingrand/getParamValue", {
                                paramId: object.paramId
                            }, function (data2) {
                                data2.data.map(function (object2) {
                                    html += '<option value=" ' + object2.paramValueId + '">' + object2.paramValueName + '</option>'
                                });
                                html += '</select>' + '</li>';
                                $("#businessType").after(html);

                            }, "json");
                        }else{
                            var html = '<li data-pId="' + object.paramId + '" class="TYPE CHANGE">' + '<abbr>' +'<i>*</i>'+ '<span class="typeName">'+ object.paramName+'</span>' + ':' + '</abbr>' + '<select>'
                            $.post("../web/api/ucoingrand/getParamValue", {
                                paramId: object.paramId
                            }, function (data2) {
                                data2.data.map(function (object2) {
                                    html += '<option value=" ' + object2.paramValueId + '">' + object2.paramValueName + '</option>'
                                });
                                html += '</select>' + '</li>';
                                $("#businessType").after(html);

                            }, "json");
                        }
                    }else if( object.paramType == "TYPE_TXT" ) {
                            if(object.required == false) {
                                var html2 = '<li data-pid="' + object.paramId + '" class="TYPE">' + '<abbr>' + '<span class="typeName">' + object.paramName +'</span>' +  ':' + '</abbr>' + '<input id="' + object.paramId + '" type="text" />' + '</li>';
                                $("#businessType").after(html2);
                            }else{
                                var html2 = '<li data-pid="' + object.paramId + '" class="TYPE">' + '<abbr>' + '<i>*</i>'  + '<span class="typeName">' + object.paramName +'</span>' + ':' + '</abbr>' + '<input id="' + object.paramId + '" type="text" />' + '</li>';
                                $("#businessType").after(html2);
                            }
                    }else if(object.paramType == "TYPE_FLOAT"){
                        if(object.required == false) {
                            var html2 = '<li data-pid="' + object.paramId + '" class="TYPE">' + '<abbr>'+ '<span class="typeName">' + object.paramName +'</span>' + ':' + '</abbr>' + '<input id="' + object.paramId + '" type="text" />' + '</li>';
                            $("#businessType").after(html2);
                        }else{
                            var html2 = '<li data-pid="' + object.paramId + '" class="TYPE REQUIRED">' + '<abbr>' + '<i>*</i>'+ '<span class="typeName">' + object.paramName +'</span>' + ':' + '</abbr>' + '<input id="' + object.paramId + '" maxlength="13" type="text" class="addParam"/>' + '</li>';
                            $("#businessType").after(html2);
                        }
                    }

                });
                var typeId = $("#businessType option:selected").val();
                if( $("#businessType option:selected").val() == '可选择' ){

                }
                else{
                    if( Mapcount != 0 ){
                        var _this = setInterval(function(){
                            if( $(".TYPE").length == Mapcount ){
                                valueMap += '{';
                                $(".TYPE").each(function(){
                                    if( $(this).find("option:selected").length > 0 ){
                                        valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("option:selected").html() + '",';
                                    }
                                    if( $(this).find("input").length > 0 ){
                                        valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("input").val() + '",';
                                    }
                                });
                                valueMap = valueMap.substring(0,valueMap.length-1);
                                valueMap += '}';
                                var computeFlag = true;
                                $(".addParam").each(function(){
                                    console.log($(this).val());
                                    if( $(this).val() == '' ){
                                        computeFlag = false;
                                    }
                                });
                                if( computeFlag == true ){
                                    ucoinCalculator(typeId,valueMap,enterpriseId);
                                }
                                clearInterval(_this);
                            }
                        },500);
                    }
                    else{
                        ucoinCalculator(typeId,'{}',enterpriseId);
                    }
                }
            }
            else{
                $("#ucoinSale").val(0);
                //$.post()
            }
        },"json")
    });
    $(document).on("change",".addParam",function(){
        var typeId = $("#businessType option:selected").val();
        var valueMap = '';
        if( $("#businessType option:selected").val() == '可选择' ){

        }
        else{
            valueMap += '{';
            $(".TYPE").each(function(){
                if( $(this).find("option:selected").length > 0 ){
                    valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("option:selected").html() + '",';
                }
                if( $(this).find("input").length > 0 ){
                    var v = ( $(this).find("input").val() == '' ) ? 0 : $(this).find("input").val();
                    valueMap += '"' + $(this).find(".typeName").html() + '":"' + v + '",';
                }
            });
            valueMap = valueMap.substring(0,valueMap.length-1);
            if( valueMap != '' ){
                valueMap += '}';
            }
            ucoinCalculator(typeId,valueMap,enterpriseId);
        }

    });
    //$(document).on("change",".TYPE select",function(){
    //    $(".addParam").val("");
    //    $("#ucoinMarketing").val("");
    //});
    $(document).on("change",".CHANGE",function(){
        var typeId = $("#businessType option:selected").val();
        var valueMap = '';
        var lock = true;
        valueMap += '{';
        $(".TYPE").each(function(){
            if( $(this).find("option:selected").length > 0 ){
                valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("option:selected").html() + '",';
            }
            if( $(this).find("input").length > 0 ){
                var floatValue = ( $(this).find("input").val() != '' ) ? $(this).find("input").val() : 0;
                valueMap += '"' + $(this).find(".typeName").html() + '":"' + floatValue + '",';
                $(this).find("i").each(function(){
                    if( $(this).parent().next().val() == '' ){
                        lock = false;
                    }
                });
            }
        });
        valueMap = valueMap.substring(0,valueMap.length-1);
        if( valueMap != '' ){
            valueMap += '}';
        }
        if( lock == true ){
            ucoinCalculator(typeId,valueMap,enterpriseId);
        }
    });


    $("#ucoinSale").change(function(){
        var typeId = $("#businessType option:selected").val();
        var valueMap = '';
        if( $("#businessType option:selected").val() == '可选择' ){

        }
        else{
            valueMap += '{';
            $(".TYPE").each(function(){
                if( $(this).find("option:selected").length > 0 ){
                    valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("option:selected").html() + '",';
                }
                if( $(this).find("input").length > 0 ){
                    valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("input").val() + '",';
                }
            });
            valueMap = valueMap.substring(0,valueMap.length-1);
            if( valueMap != '' ){
                valueMap += '}';
            }
            ucoinCalculator(typeId,valueMap,enterpriseId);
        }

    });

    $("#userSub").click(function(){
        var idCard = $("#idCard").html();
        var name = $("#userName").html().trim();
        var saleAmount = $("#ucoinSale").val();
        var remark = $("#remark").val().trim();
        var paykey = $("#Password").val();
        var valueMap = '';
        var typeId = '';
        var check = /^\d+(\.\d+)?$/;
        if(saleAmount == ''){
            saleAmount = 0;
        }
        if( $("#businessType option:selected").val() == '可选择' ){
                data_type_alert("请选择业务类型","error")
        }
        else{
            typeId = $("#businessType option:selected").val();
            valueMap += '{';
            if( $(".TYPE").length > 0 ){
                $(".TYPE").each(function(){
                    if( $(this).find("option:selected").length > 0 ){
                        valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("option:selected").html() + '",';
                    }
                    if( $(this).find("input").length > 0 ){
                        valueMap += '"' + $(this).find(".typeName").html() + '":"' + $(this).find("input").val() + '",';
                    }
                });
            }
            valueMap += '"身份证号":' + idCard + ',';
            if( name != '' ){
                valueMap += '"姓名":' + name + ',';
            }
            if( remark != '' ){
                valueMap += '"促销邮豆金额":' + saleAmount + ',';
                valueMap += '"备注":' + '"' + remark +'"'+ '}';
            }
            else{
                valueMap += '"促销邮豆金额":' + saleAmount + '}';
            }
            var check_flag = true;
            $(".addParam").each(function(){
                var num =$(this).val();
                if(!check.test(num)){
                    check_flag = false;
                }
            });
            if(check_flag){
                if( idCard && name && businessType  && paykey ){
                    var checkNum = /^[0-9]*[1-9.][0-9]*$/;
                    if( checkNum.test(saleAmount) || saleAmount == 0 ){
                        $.post("../web/api/ucoingrand/singleGrand",{
                            "typeId":typeId,
                            "valuesJson":valueMap,
                            "paykey":paykey
                        },function(data){
                            //返回信息判断提交是否成功
                            if(data.response == 'success'){
                                coverHtml();
                                $(".success").fadeIn()
                            }
                            else{
                                data_error_alert(data.data.text,"error");
                            }
                        },'json');
                    }
                    else{
                        response_ensure_alert("error","请输入正确的数字",function(){
                            $("#ucoinSale").val("").focus();
                        });
                    }
                }
                else if( paykey == ''){
                    response_ensure_alert("error","请输入密码",function(){
                        console.log("发放失败" + consoleNowTime());
                    });
                }
            }else{
                data_type_alert("请输入正确的数字","error")
                $(".addParam").each(function(){
                    $(this).val("");
                });
                $("#ucoinMarketing").val("")
            }

        }

    });
    $("#complete").click(function(){
        discoverHtml()
        window.location.href="UbaoSend"
    })
    $("#detail").click(function(){
        discoverHtml();
        //frame操作
        $(window.parent.frames['mainleft'].document).find(".clickOn").removeClass("clickOn");
        $(window.parent.frames['mainleft'].document).find("#bil").addClass("clickOn");
        window.location.href="BillManager?action=2";
    });
    $("#updateUserInfoConfirm").click(function(){
        var userName=$("#updateUserName").val();
        var customerId=$("#customerId").val();
        var userPhone=$("#updateUserPhoneNo").val();
        var addPV=$("#newMemAddressP").val();
        if( addPV=="0"){
            addPV="";
        }
        var addCV=$("#newMemAddressC").val();
        if( addCV=="0"){
            addCV="";
        }
        var addDV=$("#newMemAddressD").val();
        if( addDV=="0"){
            addDV="";
        }
        var addDetail=$("#newMemberRoadDetail").val();
        if(userName && userPhone.length==11 && /^[1-9]*[1-9][0-9]*$/.test(userPhone) && addPV && addCV && addDV && addDetail){
            $.post("../web/api/customer/editCustomer",{
                'id':customerId,
                'name':userName ,
                'linkPhone':userPhone ,
                'provinceId':addPV ,
                'cityId':addCV ,
                'districtId':addDV ,
                'addr':addDetail
            },function(data){
                if(data.response == 'success'){
                    discoverHtml();
                    response_ensure_alert("success","修改成功",function(){
                        $("#updateUserInfo").hide();
                        $("#provinceId").val(addPV);
                        $("#cityId").val(addCV);
                        $("#districtId").val(addDV);
                        $("#addrDetail").val(addDetail);
                        $("#olduseradddetail").html($("#newMemAddressP option:selected").html()+$("#newMemAddressC option:selected").html()+$("#newMemAddressD option:selected").html()+addDetail);
                        $("#userName").html(userName);
                        $("#userPhoneNo").html(userPhone);
                    })

                }
                else{
                    data_type_alert(data.data.text,"error");
                }
            });
        }else{
            data_type_alert("请完善信息并检查手机号是否正确","error")
        }
    });

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
   $("#updateButton").click(function(){
       coverHtml()
       $("#updateUserInfo").show();
       var pid = $("#provinceId").val();
       var cid = $("#cityId").val();
       var did = $("#districtId").val();
       $("#updateUserName").val($("#userName").html().trim());
       $("#updateUserPhoneNo").val($("#userPhoneNo").html().trim());
       $("#newMemberRoadDetail").val($("#addrDetail").val().trim());
       $.get("../web/api/common/provincies",function(data){
           if( data.response == 'success' ){
               data.data.map(function( object ){
                   var html = '';
                   html += '<option value="' + object.provinceId +'">';
                   html += object.provinceName;
                   html += '</option>';
                   $("#newMemAddressP").append(html);
               });

               $("#newMemAddressP option[value='" + pid + "']").prop("selected","selected");

               if(pid){
                   newMemAddressP(function(){
                       $("#newMemAddressC option[value='" + cid + "']").prop("selected","selected");
                   },pid);
               }
               if(cid){
                   newMemAddressC(function(){
                       $("#newMemAddressD option[value='" + did + "']").prop("selected","selected");
                   },cid);
               }

           }
       },'json');
   });
    $("#updateUserInfoCancel").click(function(){
        discoverHtml();
        $("#updateUserInfo").hide()
    });
    $("#updateUserInfo").draggable()
});

function ucoinCalculator(typeId,valuesJson,enterpriseId){
    $.post("../web/api/ucoingrand/ucoinCompute",{
        typeId:typeId,
        valuesJson:valuesJson,
        enterpriseId:enterpriseId
    },function(data){
        if( data.response == 'success' ){
            $("#ucoinMarketing").val(data.data);
        }
        else{
            response_ensure_alert("error",data.data.text,function(){
               console.log(new Date().toString() + '计算失败');
            });
        }
    },'json');
}



function newMemAddressP(callback,pid){
    $("#newMemAddressC option[value!='0'],#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
    $.post("../web/api/common/cities",{
        "provinceId":pid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.cityId +'">';
                html += object.cityName;
                html += '</option>';
                $("#newMemAddressC").append(html);
            });
            callback();
        }
    },'json');
}

function newMemAddressC(callback,cid){
    $("#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
    $.post("../web/api/common/districts",{
        "cityId":cid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.districtId +'">';
                html += object.districtName;
                html += '</option>';
                $("#newMemAddressD").append(html);
            });
            callback();
        }
    },'json');
}

function newMemAddressD(callback,did){
    $("#newMemAddressR option[value!='0']").remove();
    $.post("../web/api/common/streets",{
        "districtId":did
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.streetId +'">';
                html += object.streetName;
                html += '</option>';
                $("#newMemAddressR").append(html);
            });
            callback();
        }
    },'json');
}