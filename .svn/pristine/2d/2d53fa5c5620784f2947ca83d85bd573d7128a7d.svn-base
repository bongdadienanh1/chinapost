$(document).ready(function(){
    $("#cancel").click(function(){
        window.location.href="UbaoSend"

    });

    $(document).on("change",".TYPE select",function(){
        $(".addParam").val("");
        $("#ucoinMarketing").val("");
    });

    $(document).on("change","#businessType",function(){
        //清空数据
        $("#ucoinMarketing").val("");
        $("#ucoinSale").val("");


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
                            var html = '<li class="TYPE" data-pId="' + object.paramId + '">' + '<abbr>'+ '<span class="typeName">' + object.paramName+'</span>' + ':' + '</abbr>' + '<select>'
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
                            var html = '<li class="TYPE" data-pId="' + object.paramId + '">' + '<abbr>' +'<i>*</i>'+ '<span class="typeName">'+ object.paramName+'</span>' + ':' + '</abbr>' + '<select>'
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
                            var html2 = '<li data-pid="' + object.paramId + '" class="TYPE">' + '<abbr>' + '<i>*</i>'+ '<span class="typeName">' + object.paramName +'</span>' + ':' + '</abbr>' + '<input id="' + object.paramId + '" type="text" class="addParam"/>' + '</li>';
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
        var saleAmount = $("#ucoinSale").val();
        if( saleAmount == '' ){
            saleAmount = 0;
        }
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

    $("#ucoinSale").change(function(){
        var saleAmount = $("#ucoinSale").val();
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
        var name = $("#userName").val();
        var saleAmount = $("#ucoinSale").val();
        var remark = $("#remark").val();
        var paykey = $("#Password").val();
        var valueMap = '';
        var typeId = '';
        var provinceId = $(".newAddressP option:selected").val();
        var cityId = $(".newAddressC option:selected").val();
        var districtId = $(".newAddressD option:selected").val();
        var detailAddr = $("#newAddressDetail").val();
        var check = /^\d+(\.\d+)?$/;
        var contactPhone = $("#contactPhone").val();
        if( saleAmount == '' ){
            saleAmount = 0;
        }
        if( $("#businessType option:selected").val() == '可选择' ){

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
            else{

            }
            valueMap += '"身份证号":' + idCard + ',';
            if( name != '' ){
                valueMap += '"姓名":' + name + ',';
            }
            if( remark != '' ){
                valueMap += '"促销邮豆金额":' + saleAmount + ',';
                valueMap += '"备注":' + remark + '}';
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
           if(contactPhone!="" && /^\+?[1-9][0-9]*$/.test(contactPhone) && contactPhone.length == 11){
               if(check_flag){
                   if( idCard && name && valueMap && paykey ){
                       var checkNum = /^[0-9]*[1-9][0-9]*$/;
                       if( checkNum.test(saleAmount) || saleAmount == 0){
                           $.post("../web/api/ucoingrand/singleGrand",{
                               "typeId":typeId,
                               "valuesJson":valueMap,
                               "paykey":paykey,
                               "provinceId":provinceId,
                               "cityId":cityId,
                               "districtId":districtId,
                               contactPhone:contactPhone,
                               "addr":detailAddr
                           },function(data){
                               //返回信息判断提交是否成功
                               if(data.response == 'success'){
                                   console.log('success');
                                   if(window.localStorage){
                                       localStorage['UbaoSend_provinceId'] = provinceId;
                                       localStorage['UbaoSend_cityId'] = cityId;
                                       localStorage['UbaoSend_districtId'] = districtId;
                                   }else{
                                       response_ensure_alert("error","浏览器版本太低，不支持保存地址功能",function(){
                                           console.log(consoleNowTime());
                                       });
                                   }
                                   ensure_alert("UbaoSend");
                               }
                               else{
                                   data_type_alert(data.data.text,"error");
                               }
                           },'json');
                       }
                       else{
                           response_ensure_alert("error","请输入正确的数字",function(){
                               $("#ucoinSale").val("").focus();
                           });

                       }
                   }else if( paykey == '' ){
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
           }else{
               data_type_alert("手机号码不正确","error")
           }
        }

    });

    $("#ucoinSale").change(function(){
        var typeId = $("#businessType option:selected").val();
        var enterpriseId = $("#ucoinSale").val();
        var infoJson = '{"typeId":';
        if( $("#businessType option:selected").val() == '可选择' ){

        }
        else{
            infoJson += '"' + $("#businessType option:selected").val() + '","valueMap":{';
            if( $(".TYPE").length > 0 ){
                $(".TYPE").each(function(){
                    if( $(this).find("option:selected").length > 0 ){
                        infoJson += '"' + $(this).data("pid") + '":"' + $(this).find("option:selected").val() + '",';
                    }
                    if( $(this).find("input").length > 0 ){
                        infoJson += '"' + $(this).find("input").prop("id") + '":"' + $(this).find("input").val() + '",';
                    }
                });
                infoJson = infoJson.substring(0,infoJson.length-1);
            }
            infoJson += '}}';
            ucoinCalculator(typeId,infoJson,enterpriseId);
        }

    });

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

function UpdateMemAddressP(callback,pid){
    $(".newAddressC option[value!='0'],.newAddressD option[value!='0'],.newAddressR option[value!='0']").remove();
    $.post("../web/api/common/cities",{
        "provinceId":pid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.cityId +'">';
                html += object.cityName;
                html += '</option>';
                $(".newAddressC").append(html);
            });
            callback();
        }
    },'json');
}

function UpdateMemAddressC(callback,cid){
    $(".newAddressD option[value!='0'],.newAddressR option[value!='0']").remove();
    $.post("../web/api/common/districts",{
        "cityId":cid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.districtId +'">';
                html += object.districtName;
                html += '</option>';
                $(".newAddressD").append(html);
            });
            callback();
        }
    },'json');
}

/*
*  @RequestParam
 String idCardNo,
 @RequestParam(required = false)
 String name,
 @RequestParam
 String price,
 @RequestParam
 String businessType,
 @RequestParam(required = false)
 String remark,
 @RequestParam
 String paykey
* */