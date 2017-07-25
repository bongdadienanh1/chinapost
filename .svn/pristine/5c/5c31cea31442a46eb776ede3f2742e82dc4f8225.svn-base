function BusinessType(name,id){
    var businessType = new BusinessObject(name,id);
    var ucoinValue = new BusinessObject();
    var parameterArray = [];
    var setParameterArray = function(arr){
        this.parameterArray = arr;
    };
    return {
        businessType:businessType,
        ucoinValue:ucoinValue,
        parameterArray:parameterArray,
        setParameterArray:setParameterArray
    };
}
function BusinessObject(name,id){
    var name = name;
    var id = id;
    var setObject = function(name,id){
        this.name = name;
        this.id = id;
    };
    var del = function(){
        //删除方法
    };
    var modify = function(){
        //修改方法
    };
    return {
        name:name,
        id:id,
        setObject:setObject,
        del:del,
        modify:modify
    };
}
function ParameterObject(pA,pB){
    var paramName = pA;
    var paramValue = pB;
    return {
        paramName:paramName,
        paramValue:paramValue
    };
}


var createCard = new BusinessType('createCard','01');
var ob1 = new BusinessObject('存期','01');
var ob2 = new BusinessObject('一个月','01');
var param1 = new ParameterObject(ob1,ob2);

var obA = new BusinessObject('金额','02');
var obB = new BusinessObject('浮点数字','02');
var param2 = new ParameterObject(obA,obB);

var arr = [];
arr.push(param1,param2);
createCard.setParameterArray(arr);
//拖拽启动
//$(document).on("mouseover",".busDeleteButton",function(){
//    $( "#sortable" ).sortable({
//        revert: true,
//        connectWith: '.busDeleteButton'
//    });
//});
//拖拽销毁
//$(document).on("mouseup",".busDeleteButton",function(){
//    $( "#sortable" ).sortable('destroy');
//});


$(document).ready(function(){
    var typeId = '';
    var paramId = '';

    $( ".busPopUp" ).draggable();
    $( "#busFormulaSet" ).draggable();
    //新增业务类型
    $(document).on("click",".addNew",function(){
        var name = $("#busTypeName").val();
        var serialNo = $(".tableBody").length;
        if(name!=""){
        $.post("../web/api/sysmanage/addBussinessType",{
            name:name,
            seriaNo:serialNo,
            enabled:true
        },function(data){
            if( data.response == 'success' ){
                response_ensure_alert('success','添加成功',function(){
                    discoverHtml();
                    $(".busPopUp").hide();
                    var html = addHtml(1,name,data.data.typeId);
                    $(".tableBody:nth-last-child(1)").before(html);
                    window.location.href = 'businessType?action=click';
                })

            }
            else{
                response_ensure_alert('error',data.data.text,function(){
                    console.log("添加失败" + consoleNowTime());
                });
            }
        },'json');
        }else{
            data_type_alert("请输入业务类型名字","error")
        }
    });
    //编辑业务类型
    $(document).on("click",".editBusinessType",function(){
        var name = $("#busTypeName").val();
        var _this = this;
        var enabled = $("input[name='busTypeActive']:checked").val()
        if(enabled=="1"){
            enabled=true;
        }else if(enabled == "0"){
            enabled=false;
        }
        $.post("../web/api/sysmanage/editBussinessType",{
            name:name,
            typeId:typeId,
            enabled:enabled
        },function(data){
            if( data.response == 'success' ){
                response_ensure_alert('success','修改成功',function(){
                    discoverHtml();
                    window.location.href = 'businessType?action=click';
                });

            }
            else{
                response_ensure_alert('error',data.data.text,function(){
                    console.log("添加失败" + consoleNowTime());
                });
            }
        },'json');
    });

    //删除业务类型
    $(document).on("click",".busName .busDeleteButton",function(){
        var _this = this;
        var typeId = $(this).parent().parent().prev().val();
        var busNamedeleteAble = $(this).parent().siblings(".busNamedeleteAble").val()
        response_OkCancel_alert("warning","是否确认删除",function(){
            if(busNamedeleteAble == "true"){
                $.post("../web/api/sysmanage/deleteBussinessType",{
                    typeId:typeId
                },function(data){
                    if( data.response == 'success' ){
                        response_ensure_alert('success','删除成功',function(){
                            $(_this).parent().parent().parent().remove();
                        });
                    }
                    else{
                        data_type_alert(data.data.text,"error")
                        console.log("修改失败" + consoleNowTime());
                    }
                },'json');
            }else{
                data_type_alert("改参数已经被使用，无法删除","error")
            }
        },function(){})

    });

    //新增参数
    $(document).on("click","#busParameterValAddChange .confirm",function(){
        var flag2 = true;
        var radio = $("input[name='busParameterValRid']:checked").val();
        if( radio == 0 ){
            $(".xishushuru").each(function(){
                var args = $(this).val().split(".");
                var temp = ( args[1] == undefined ) ? '' : args[1];
                var value=$(this).val()
                if(  args[1] != ""  &&  !/^([1-9]\d*|[0]{1,1})$/.test(value) ){
                    $(this).val("");
                    flag2 = false;
                }
            });
            $(".pV_tr td:first-child input").each(function(){
                if( $(this).val()=="" ){
                    flag2 = false;
                }
            });
            if($(".firstTrInput").val()==""){
                flag2 = false;
            }
        }
        if(flag2){
            var paramType = handleParameter( $("input[name='busParameterValRid']:checked").val() );
            var required = '';
            var valuesJson = '';
            if( paramType != 'TYPE_ENUM' ){
                required = handleParameterRequired($("input[name='busParameterPrimary']:checked").val());
            }
            else{
                required = true;
                valuesJson += '[';
                $(".newAdd tr").each(function(){
                    var paramValueName = $(this).find("td:nth-child(1)").children("input").val();
                    var paramValue = $(this).find("td:nth-child(2)").children("input").val();
                    if(paramValue != '' && paramValueName != '' && paramValue != undefined && paramValueName != undefined){
                        valuesJson += '{"paramValueName":"' + paramValueName + '","paramValue":"' + paramValue + '"},';
                    }
                });
                valuesJson = valuesJson.substring(0,valuesJson.length - 1);
                valuesJson += ']';
            }
            var name = $("#busParameterName").val();

            $.post("../web/api/sysmanage/addParam",{
                typeId:typeId,
                paramType:paramType,
                name:name,
                required:required,
                valuesJson:valuesJson
            },function(data){
                if( data.response == 'success' ){
                    response_ensure_alert('success','添加成功',function(){
                        ////插入参数
                        //var html  = '';
                        //html += '<dd class="busParameterDD">';
                        //html += '<input type="hidden" value="' + data.data.paramId + ',' + data.data.paramType + '">'
                        //html += '<abbr>' + data.data.paramName + '</abbr>';
                        //html += '<span class="buttonAppendPlace"><i class="busChangeButton"></i><i class="busDeleteButton"> </i> <i class="busMoveButtonPar"> </i> </span>';
                        //html += '</dd>';
                        //$('input[value=' + data.data.typeId + ']').parent().find(".busParameter dl div").append(html);
                        //
                        ////插入参数值
                        //var htmlPV = '';
                        //if( data.data.paramType == 'TYPE_ENUM' ){
                        //    htmlPV += '<dd class="busParameterValDD" data-id="' + data.data.paramId + '">';
                        //    htmlPV += '<input type="hidden" value="' + data.data.required + '" class="required">';
                        //    data.data.paramValues.map(function(object){
                        //        htmlPV += '<abbr data-value="' + object.paramId + ',' + object.paramValue + '">' + object.paramValueName + '</abbr>';
                        //    });
                        //    htmlPV += '<span class="buttonAppendPlace"><i class="busChangeButton"></i></span></dd>';
                        //}
                        //else if( data.data.paramType == 'TYPE_FLOAT' ){
                        //    htmlPV += '<dd class="busParameterValDD" data-id="' + data.data.paramId + '"><input type="hidden" value="' + data.data.required + '" class="required"><abbr>浮点类型</abbr> <span class="buttonAppendPlace"><i class="busChangeButton"></i></span></dd>';
                        //}
                        //else if( data.data.paramType == 'TYPE_TXT' ){
                        //    htmlPV += '<dd class="busParameterValDD" data-id="' + data.data.paramId + '"><input type="hidden" value="' + data.data.required + '" class="required"><abbr>文本类型</abbr> <span class="buttonAppendPlace"><i class="busChangeButton"></i></span></dd>';
                        //}
                        //$('input[value=' + data.data.typeId + ']').parent().find(".busParameterVal dl dd:nth-last-child(1)").before(htmlPV);
                        ////等高
                        //$(".tableBody").each(function(){
                        //    var ulhei = $(this).height();
                        //    $(this).children(".busName,.busFormula").height(parseInt(ulhei)-1).css("line-height",ulhei + 'px');
                        //});
                        ////关闭弹窗
                        discoverHtml();
                        //$("#busParameterValAddChange").hide();
                        window.location.href = 'businessType?action=click';
                    });
                }
                else{
                    response_ensure_alert("error",data.data.text,function(){
                        console.log("添加参数失败" + consoleNowTime());
                    });
                }
            },'json');
        }else{
            data_type_alert("参数名必填且系数应为大于0的正数，如果是小数，不能超过小数点后2位","error")
        }


    });
    //修改参数
    $(document).on("click","#busParameterValAddChange .edit",function(){
        var name = $("#busParameterName").val();
        var enabled = $("input[name='busParameterPrimaryActive']:checked").val()
        var required = $("input[name='busParameterPrimary']:checked").val()
        if(required=="1"){
            required=true;
        }else if(required == "0"){
            required=false;
        }
        if(enabled=="1"){
            enabled=true;
        }else if(enabled == "0"){
            enabled=false;
        }
        $.post("../web/api/sysmanage/editParam",{
            paramId:paramId.split(",")[0],
            name:name,
            enabled:enabled,
            required:required
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert('success','修改成功',function(){
                    discoverHtml()
                    $('input[value="' + paramId + '"]').next().html(name);
                    $("#busParameterValAddChange").hide();
                    window.location.href = 'businessType?action=click';
                });
            }
            else{
                response_ensure_alert("error",data.data.text,function(){
                   console.log("修改参数失败" + consoleNowTime());
                });
            }
        },'json');
    });

    //新增公式
    $("#busFormulaConfirm").click(function(){
        var expression = '';
        var expression_str = '';
        var note = $("#expressionNote").val();
        var doms = $("#calculatorVal").children();
        doms.each(function(){
            if( $(this).prop('class') == 'busParmBut' ){
                expression += '{' + $(this).data('id') + '}';
            }
            else if( $(this).prop('class') == 'calculatorParam' ){
                expression += $(this).html();
            }
            expression_str += $(this).html();
        });
        $.post("../web/api/sysmanage/editExpression",{
            typeId:typeId,
            expression:expression,
            note:note
        },function(data){
            if( data.response == 'success' ){
                response_ensure_alert('success','添加成功',function(){
                    discoverHtml();
                    $('input[value=' + typeId + ']').parent().find(".busFormula TextField").empty().append(expression_str);
                    window.location.href = 'businessType?action=click';
                });
            }
            else{
                response_ensure_alert("error",data.data.text,function(){
                    console.log(new Date().toString() + "修改失败");
                });
            }
        },'json');
    });
    $("#busFormulaCancel").click(function(){
        discoverHtml();
        $("#busFormulaSet").hide();
    })
    $("#complete").click(function(){
        window.location.href ="businessType"
    });
    $( "#sortableBusinessType" ).sortable({
        revert: true,
        handle: '.busMoveButton',
        stop:function(){
            var serialJson = '{';
            var i = 1;
            $(".tableBody").each(function(){
                if( $(this).children().first().val() != '' ){
                    serialJson += '"' + $(this).children().first().val() + '":' + i + ',';
                    i++;
                }
            });
            serialJson = serialJson.substring(0,serialJson.length-1) + '}';
            $.post("../web/api/sysmanage/updateTypeSerial",{
                serialJson:serialJson
            },function(data){
                if( data.response != 'success' ){
                    response_ensure_alert('error','更新顺序失败,即将重载页面',function(){
                       window.location.href = 'businessType';
                    });
                }
            },'json');
        }
    });

    $( ".sortableBusinessPam" ).sortable({
        revert: true,
        handle: '.busMoveButtonPar',
        stop:function(){
            var serialJson = '{';
            var i = 1;
            $(this).children().each(function(){
                var str = $(this).find("input[name='softBusParam']").val();
                if( str != '' ){
                    serialJson += '"' + str.split(",")[0] + '":' + i + ',';
                    i++;
                }
            });
            serialJson = serialJson.substring(0,serialJson.length-1) + '}';
            $.post("../web/api/sysmanage/updateParamSerial",{
                serialJson:serialJson
            },function(data){
                if( data.response != 'success' ){
                    response_ensure_alert('error','更新顺序失败,即将重载页面',function(){
                        window.location.href = 'businessType';
                    });
                }
                else{
                    window.location.href = 'businessType?action=click';
                }
            },'json');
        }
    });

    $(".tableBody").each(function(){
        var ulhei = $(this).height();
        $(this).children(".busName,.busFormula").height(parseInt(ulhei)-1).css("line-height",ulhei + 'px');
    });
    $("#addChange").click(function(){
        var html1=""
        var html2=""
        var html3=""
        var html4=""
        var html5=""
        var html6=""
        $(this).hide();
        $("#changeHistory").hide();
        $("#complete").show();
        html1 += '<ul class="tableBody">'
        html1 += '<li class="busName">'+'<b class="AllAdd" style="color: #54a6de;">'+'+添加'+ '</b>' +'</li>'
        html1 += '<li class="busParameter">'+'<dl>'+'<dd>'+'</dd>'+'</dl>'+'</li>'
        html1 += '<li class="busParameterVal">'+'<dl>'+'<dd>'+'</dd>'+'</dl>'+'</li>'
        html1 += '<li class="busFormula">'+'</li>'
        html1 += '</ul>'

        html4 += '<dd>'+'<b class="AllAdd" style="color: #54a6de;">'+'+添加'+ '</b>' +'</dd>'
        html5 += '<dd>'+ '</dd>';

        $(".busParameterDD abbr").each(function(){
            if($(this).html() == '- -'){
                $(this).parent().remove();
            }
        });
        $(".busParameterValDD abbr").each(function(){
            if($(this).html() == '- -'){
                $(this).parent().remove();
            }
        });
        $(".busParameter dl").append(html4);
        $(".busParameterVal dl").append(html5);
        $("#sortableBusinessType").append(html1);
        $(".tableBody").each(function(){
            var ulhei = $(this).height()
            $(this).children(".busName,.busFormula").height(parseInt(ulhei)-1).css("line-height",ulhei + 'px')
        })


        html2 += '<i class="busChangeButton">' +'</i>'+ '<i class="busDeleteButton">' + ' </i> ' + '<i class="busMoveButton">' + ' </i> ';
        html6 += '<i class="busChangeButton">' +'</i>'+ '<i class="busDeleteButton">' + ' </i> ' + '<i class="busMoveButtonPar">' + ' </i> ';
        html3 += '<i class="busChangeButton">' +'</i>';
        $(".busName").each(function(){
            $(this).children(".buttonAppendPlace").append(html2)
        });
        $(".busParameter dl dd").each(function(){
            $(this).children(".buttonAppendPlace").append(html6)
        });
        $(".busParameterVal dl dd").each(function(){
            $(this).children(".buttonAppendPlace").append(html3)
        });
        $(".busFormula").each(function(){
            $(this).children(".buttonAppendPlace").append(html3)
        });


        $(".busName .busNamedeleteAble").each(function(){
            if($(this).val() == "false"){
                $(this).siblings(".buttonAppendPlace").children(".busDeleteButton").remove()
            }
        })
        $(".busParameterDD .busParameterdeleteAble").each(function(){
            if($(this).val() == "false"){
                $(this).siblings(".buttonAppendPlace").children(".busDeleteButton").remove()
            }
        })
        $(".busParameterValDD abbr").each(function(){
            if($(this).html() == "文本类型"||$(this).html() == "浮点类型"){
                $(this).siblings(".buttonAppendPlace").children(".busChangeButton").remove()
            }
        })
    })

    $(".cancel").click(function(){
        discoverHtml();
        $(".busParameterValPri").hide()
        $(".busPopUp").hide();
    });
    $("#changeHistory").click(function(){
        window.location.href="businessChangeHis";
    });
    $(document).on("click",".busName .AllAdd",function(){
        coverHtml();
        $("#busTypeAddChange").show();
        $(".busTypeActive").hide();
        $("#busTypeName,#busTypeSort").val("");
        $("#busTypeName").prop("readonly","").css("border","1px solid #999")
        $("#businessType").attr("class","addNew");
    });
    $(document).on("click",".busParameter .AllAdd",function(){
        coverHtml();
        $(".paramValueEdit").show();
        $(".bussinessParNameEMUE").hide();
        $("#busParameterValAddChange").show();
        $(".busParameterValActive").hide()

        $("#busParameterName").prop("readOnly","").css("border","1px solid #999");

        $(".pV_tr td:nth-child(1) input[type='text'],.pV_tr td:nth-child(2) input[type='text']").val("").prop("readonly","");
        $(".hasbusParameter,.busParameterValType,.busParameterValChoose").show();
        $("#busParameterName,#busParameterNameTurn").val("");
        typeId = $(this).parent().parent().parent().parent().children().first().val();
        $("#busParameterValAddChange li:nth-child(3) input:nth-child(2)").prop("class","confirm");
        busCreateParamInit();
        $(".pV_tr td:nth-child(3) input[type='text']").val("已启用").prop("readonly","readonly");
        $("#NewAddButton2").prop("id","NewAddButton")
        $(".updatBtn").hide()
    });
    $(document).on("click",".busName .busChangeButton",function(){
        coverHtml();
        var deleteAble = $(this).parent().siblings(".busNamedeleteAble").val();
        var enabled = $(this).parent().siblings(".busNameEnabled").val();
        if(deleteAble=="false"){
            $("#busTypeName").prop("readonly","readonly").css("border","none");
        }else{
            $("#busTypeName").prop("readonly","").css("border","1px solid #999")
        }
        if(enabled=="true"){
            $("input[name='busTypeActive'][value='1']").prop("checked","checked");
        }else{
            $("input[name='busTypeActive'][value='0']").prop("checked","checked");
        }
        $("#busTypeAddChange").show();
        $(".busTypeActive").show();
        $("#busTypeName").val($(this).parent().siblings("abbr").html());
        $("#businessType").attr("class","editBusinessType");
        typeId = $(this).parent().parent().prev().val();
    });
    $(document).on("click",".busParameter .busChangeButton",function(){
        coverHtml();
        if($(this).parent().prev().prev().val().split(",")[1] == "TYPE_ENUM") {
            $(".paramValueEdit").show();
            $(".bussinessParNameEMUE").hide();
            var deleteAble = $(this).parent().siblings(".busParameterdeleteAble").val();
            var enabled = $(this).parent().siblings(".busParameterEnabled").val();

            if (deleteAble == "false") {
                //不可删除
                $("#busParameterName").prop("readonly", "readonly").css("border", "none");
            } else {
                //可删除
                $("#busParameterName").prop("readonly", "").css("border", "1px solid #999")
            }
            if (enabled == "true") {
                $("input[name='busParameterPrimaryActive'][value='1']").prop("checked", "checked");
            } else {
                $("input[name='busParameterPrimaryActive'][value='0']").prop("checked", "checked");
            }
            $(".busParameterValActive").show();
            $("#busParameterValAddChange").show();
            $(".busParameterValType,.busParameterValPri,.busParameterValChoose").hide();
            $(".hasbusParameter").show();
            var str = $(this).parent().prev().prev("input[type='hidden']").val();
            paramId = str;
            $("#busParameterValAddChange input[type='button']:nth-child(2)").prop("class", "edit");
            $("#busParameterName").val($(this).parent().siblings("abbr").html());
            $(".updatBtn").show()
        }else{
            $(".paramValueEdit,.busParameterValPri").show();
            $(".bussinessParNameEMUE").hide();
            var deleteAble = $(this).parent().siblings(".busParameterdeleteAble").val();
            var enabled = $(this).parent().siblings(".busParameterEnabled").val();
            $("#busParameterName").prop("readonly", "readonly").css("border", "none");

            if (enabled == "true") {
                $("input[name='busParameterPrimaryActive'][value='1']").prop("checked", "checked");
            } else {
                $("input[name='busParameterPrimaryActive'][value='0']").prop("checked", "checked");
            }

            if (deleteAble == "false") {
                //不可删除
                $("#busParameterName").prop("readonly", "readonly").css("border", "none");
            } else {
                //可删除
                $("#busParameterName").prop("readonly", "").css("border", "1px solid #999")
            }


            if($(this).parent().prev().prev().val().split(",")[2]){
                $("input[name='busParameterPrimary'][value='1']").prop("checked","checked")
            }else{
                $("input[name='busParameterPrimary'][value='0']").prop("checked","checked")
            }
            $(".busParameterValActive").show();
            $("#busParameterValAddChange").show();
            $(".busParameterValType,.busParameterValChoose").hide();
            $(".hasbusParameter").show();
            var str = $(this).parent().prev().prev("input[type='hidden']").val();
            paramId = str;
            $("#busParameterValAddChange input[type='button']:nth-child(2)").prop("class", "edit");
            $("#busParameterName").val($(this).parent().siblings("abbr").html());
            $(".updatBtn").show()
        }
    });
    //删除参数
    $(document).on("click",".busParameter .busDeleteButton",function(){

        var paramStr = $(this).parent().prev().prev("input[type='hidden']").val();
        var paramId = paramStr.split(",")[0];
        var _this = this;
        response_ensure_alert('warning','是否确认删除',function(){
            $.post("../web/api/sysmanage/deleteParam",{
                paramId:paramId
            },function(data){
                if(data.response == 'success'){
                    $(_this).parent().parent().parent().parent().parent().next().find(".busParameterValDD").each(function(){
                        if( $(this).data("id") == paramId ){
                            $(this).remove();
                        }
                    });
                    $(_this).parent().parent().remove();
                    //等高
                    $(".tableBody").each(function(){
                        var ulhei1 = $(this).children(".busParameter").height();
                        $(this).children(".busName,.busFormula").height(parseInt(ulhei1)+1).css("line-height",ulhei1 + 'px')
                    });
                }
                else{
                    console.log("删除失败" + consoleNowTime());
                }
            },'json');
        });
        $("#busParameterName").val($(this).parent().siblings("abbr").html());
    });



    $(document).on("click",".busParameterVal .busChangeButton",function(){
        coverHtml();
        $("#busParameterValAddChange").show();
        paramId = $(this).parent().parent().data('id');
        $(".busParameterValType,.busParameterValPri,.busParameterValChoose").show();
        $(".hasbusParameter").hide();
        $("#busParameterValAddChange input[type='button']:nth-child(2)").prop("class","paramValueEdit");
        //填充数据
        var paramType = $(this).parent().prev().html();
        var required = $(this).parent().parent().find(".required").val();
        if( paramType == '浮点类型' ){
            $(".paramValueEdit").show();
            $("input[name='busParameterValRid'][value='1']").prop("checked","checked");
            if( required == 'true' ){
                $("input[name='busParameterPrimary'][value='1']").prop("checked","checked");
            }
            else{
                $("input[name='busParameterPrimary'][value='0']").prop("checked","checked");
            }
            $(".busParameterValChoose").hide();
        }
        else if( paramType == '文本类型' ){
            $(".paramValueEdit").show();
            $("input[name='busParameterValRid'][value='2']").prop("checked","checked");
            if(required){
                $("input[name='busParameterPrimary'][value='1']").prop("checked","checked");
            }
            else{
                $("input[name='busParameterPrimary'][value='0']").prop("checked","checked");
            }
            $(".busParameterValChoose").hide();
        }
        else{
            $(".busParameterValPri,.busParameterValActive,.busParameterValType,.paramValueEdit").hide();
            $(".bussinessParNameEMUE").show();
            $("input[name='busParameterValRid'][value='0']").prop("checked","checked");
            $("tbody.newAdd tr:nth-child(1)").remove();
            $(".pV_tr").remove();
            var html = '';
            $(this).parent().siblings("abbr").each(function(){
                html += '<tr class="pV_tr"><td><input type="text" value="' + $(this).html() + '"></td>';
                html += '<td><input class="xishushuru" maxlength="4" type="text" value="' + $(this).data("value").split(",")[1] + '"></td>';
                html += '<td><input style="text-align: center;height: 100%;" readonly="readonly" value="'
                if( $(this).data("value").split(",")[3]=="true"){
                    html+="已启用"
                }else{
                    html+="未启用"
                }
                html += '" type="text" class="valActive"><input type="hidden" class="valId" value="'+$(this).data("value").split(",")[0] + '"><input type="hidden" class="deleable" value="'+ $(this).data("value").split(",")[2] +'"><input type="hidden" class="bussinessParamId" value="'+$(this).data("value").split(",")[4] +'"></td>'
                html += '<td><input style="width: 100px;display:inline;" type="button" class="updatBtn allclickButton" value="编辑">'
                if( $(this).data("value").split(",")[2]=="true"){
                    html+='<input style="width: 100px;display:inline;" type="button" class="deleteBtn2 allclickButton" value="删除">'
                }else{
                }
                html += '</td></tr>';
            });

            $(".newAdd tr").before(html);
            $(".newAdd input[type='text']").prop("readonly","readonly");
            $("#NewAddButton").prop("id","NewAddButton2")
        }

    });

    //编辑参数值
    $(document).on("click","#busParameterValAddChange .paramValueEdit",function(){
        var paramType = handleParameter($("input[name='busParameterValRid']:checked").val());

        var valuesJson = '';
        if( paramType != 'TYPE_ENUM' ){
            required = handleParameterRequired($("input[name='busParameterPrimary']:checked").val());
        }
        else{
            required = true;
            valuesJson += '[';
            $(".newAdd tr").each(function(){
                var paramValueName = $(this).find("td:nth-child(1)").children("input").val();
                var paramValue = $(this).find("td:nth-child(2)").children("input").val();
                if(paramValue != '' && paramValueName != '' && paramValue != undefined && paramValueName != undefined){
                    valuesJson += '{"paramValueName":"' + paramValueName + '","paramValue":"' + paramValue + '"},';
                }
            });
            valuesJson = valuesJson.substring(0,valuesJson.length - 1);
            valuesJson += ']';
        }

        $.post("../web/api/sysmanage/addParam",{
            paramId:paramId,
            required:required,
            paramType:paramType,
            valuesJson:valuesJson
        },function(data){
            if( data.response == 'success'){
                response_ensure_alert('success','修改成功',function(){
                    var htmlPV = '';
                    if( data.data.paramType == 'TYPE_ENUM' ){
                        htmlPV += '<dd class="busParameterValDD" data-id="' + data.data.paramId + '">';
                        htmlPV += '<input type="hidden" value="' + data.data.required + '" class="required">';
                        data.data.paramValues.map(function(object){
                            htmlPV += '<abbr class="busParameterValAbbr" data-value="' + object.paramId + ',' + object.paramValue + '">' + object.paramValueName + '</abbr>';
                        });
                        htmlPV += '<span class="buttonAppendPlace"><i class="busChangeButton"></i></span></dd>';
                    }
                    else if( data.data.paramType == 'TYPE_FLOAT' ){
                        htmlPV += '<dd class="busParameterValDD" data-id="' + data.data.paramId + '"><input type="hidden" value="' + data.data.required + '" class="required"><abbr>浮点类型</abbr> <span class="buttonAppendPlace"><i class="busChangeButton"></i></span></dd>';
                    }
                    else if( data.data.paramType == 'TYPE_TXT' ){
                        htmlPV += '<dd class="busParameterValDD" data-id="' + data.data.paramId + '"><input type="hidden" value="' + data.data.required + '" class="required"><abbr>文本类型</abbr> <span class="buttonAppendPlace"><i class="busChangeButton"></i></span></dd>';
                    }
                    $('input[value=' + data.data.typeId + ']').parent().find("dd[data-id='" + data.data.paramId + "']").before(htmlPV).remove();
                    discoverHtml();
                    $("#busParameterValAddChange").hide();
                });
            }
            else{
               data_type_alert(data.data.text,"error")
            }
        },'json');
    });
    $(".updateenumValcancel").click(function(){
        $("#updateenumVal").hide();
    })
    $(".updateenumValcancel2").click(function(){
        $("#updateenumVal2").hide();
    })
    $(document).on("click",".deleteBtn2",function(){
        var _this=this;
        if( $(".newAdd .pV_tr").length > 1 ){
        response_OkCancel_alert("error","确定删除该参数值吗?",function(){
            var valueId=$(_this).parent().prev().children(".valId").val();
            coverHtml()
            $.post("../web/api/sysmanage/deleteParamValue",{
                valueId:valueId
            },function(data){
                if( data.response == 'success'){
                    response_ensure_alert("success","操作成功",function(){
                        $(".pV_tr input[value='"+valueId+"']").each(function(){
                           $(_this).parent().parent(".pV_tr").remove();
                        });
                    })
                }else{
                    data_type_alert(data.data.text,"error")
                }
            },'json')
        },function(){})
        }else{
            data_type_alert("最少保留一个选项","error")
        }
    })
    $(document).on("click","#NewAddButton2",function(){
        $("#updateenumVal2").show();
        var updateParamId=$(this).parent().siblings(".pV_tr:nth-child(1)").children("td:nth-child(3)").children(".bussinessParamId").val();
        $("#bussinessParamId2").val(updateParamId);
        $("#updateenumValName2").val("");
        $("#updateenumValNum2").val("");
        $("input[name='updateenumValActive2'][value='1']").prop("checked","checked")

    })
    $(document).on("click","#updateenumValconfirm2",function(){
        var paramId =  $("#bussinessParamId2").val();
        var name =  $("#updateenumValName2").val();
        var value =  $("#updateenumValNum2").val();
        var enabled = $("input[name='updateenumValActive2']:checked").val();
        var serialNo="";
        $(".pV_tr").each(function(){
            serialNo= parseInt($(this).length)+parseInt(1);
        })
        if(enabled == "1"){
            enabled = "true"
        }else{
            enabled = "false"
        }
        var ages=value.split(".")
        var temp = ( ages[1] == undefined ) ? '' : ages[1];
        if(temp.length<3 && ages[1] !=""&&/^([1-9]\d*|[0]{1,1})$/.test(value)) {
            $.post("../web/api/sysmanage/addParamValue", {
                paramId: paramId,
                name: name,
                value: value,
                enabled: enabled,
                serialNo: serialNo
            }, function (data) {
                if (data.response == 'success') {
                    $("#updateenumVal2").hide();
                    if (enabled == "1") {
                        enabled = "已启用"
                    } else {
                        enabled = "未启用"
                    }
                    response_ensure_alert("success", "操作成功", function () {
                        var html9 = ""
                        html9 += '<tr class="pV_tr"><td><input readonly="readonly" type="text" value="' + data.data.paramValueName + '"></td>';
                        html9 += '<td><input readonly="readonly" class="xishushuru" maxlength="4" type="text" value="' + data.data.paramValue + '"></td>';
                        html9 += '<td><input style="text-align: center;height: 100%;" readonly="readonly" value="'
                        if (data.data.enabled == "true") {
                            html9 += "已启用"
                        } else {
                            html9 += "未启用"
                        }
                        html9 += '" type="text" class="valActive"><input type="hidden" class="valId" value="' + data.data.paramValueId + '"><input type="hidden" class="deleable" value="' + data.data.deletable + '"><input type="hidden" class="bussinessParamId" value="' + data.data.paramId + '"></td>'
                        html9 += '<td><input style="width: 100px;display:inline;" type="button" class="updatBtn allclickButton" value="编辑">'
                        if (data.data.deletable) {
                            html9 += '<input style="width: 100px;display:inline;" type="button" class="deleteBtn2 allclickButton" value="删除">'
                        } else {
                        }
                        html9 += '</td></tr>';
                        $("#busParameterValAddChange .cancel").addClass("cancel2")
                        $(".newAdd").last().prev().after(html9)
                    })
                } else {
                    data_type_alert(data.data.text, "error")
                }
            }, 'json')
        }else{
            data_type_alert("系数为大于0的正数，如果是小数，不能超过小数点后2位","error")
        }
    });
    $(document).on("click",".updatBtn",function(){
        $("#updateenumVal").show();
        var updateName=$(this).parent().siblings("td:nth-child(1)").children("input").val();
        var updateNum=$(this).parent().siblings("td:nth-child(2)").children("input").val();
        var updateId=$(this).parent().siblings("td:nth-child(3)").children(".valId").val();
        var updateactive=$(this).parent().siblings("td:nth-child(3)").children(".valActive").val();
        var updatedeleable=$(this).parent().siblings("td:nth-child(3)").children(".deleable").val();
        var updateParamId=$(this).parent().siblings("td:nth-child(3)").children(".bussinessParamId").val();
        $("#updateenumValId").val(updateId);
        $("#updateenumValName").val(updateName);
        $("#updateenumValNum").val(updateNum);
        $("#updateenumenable").val(updateactive);
        $("#bussinessParamId").val(updateParamId);
        if(updateactive=="已启用"){
            $("input[name='updateenumValActive'][value='1']").prop("checked","checked")
        }else{
            $("input[name='updateenumValActive'][value='0']").prop("checked","checked")
        }
        if(updatedeleable=="false"){
            $("#updateenumValName").prop("readonly","readonly").css("border","none")
        }else{
            $("#updateenumValName").prop("readonly","").css("border","1px solid #999")
        }
    });
    $(document).on("click",".cancel2",function(){
        $("#busParameterValAddChange .cancel").remove("cancel2")
        window.location.href = 'businessType?action=click';
    })
    $(document).on("click","#updateenumValconfirm",function(){
        var paramId2 =  $("#updateenumValId").val();
        var name =  $("#updateenumValName").val();
        var value =  $("#updateenumValNum").val();
        var enabled = $("input[name='updateenumValActive']:checked").val();
        if(enabled == "1"){
            enabled = "true"
        }else{
            enabled = "false"
        }
        var ages=value.split(".")
        var temp = ( ages[1] == undefined ) ? '' : ages[1];
        if(temp.length<3 && ages[1] !="" && /^([1-9]\d*|[0]{1,1})$/.test(value)){
        $.post("../web/api/sysmanage/editParamValue",{
            paramId:paramId2,
            name:name,
            value:value,
            enabled:enabled
        },function(data){
            if( data.response == 'success'){
                $("#updateenumVal").hide();
                if(enabled == "true"){
                    enabled="已启用"
                }else{
                    enabled="未启用"
                }
                response_ensure_alert("success","操作成功",function(){
                    $(".pV_tr input[value='"+paramId2+"']").each(function(){
                       $(this).siblings(".valActive").val(enabled);
                        $(this).parent().siblings("td:nth-child(1)").children("input").val(name);
                        $(this).parent().siblings("td:nth-child(2)").children("input").val(value);
                        $("#busParameterValAddChange .cancel").addClass("cancel2")
                    });
                })
            }else{
                data_type_alert(data.data.text,"error")
            }
        },'json')
        }else{
            data_type_alert("系数为大于0的正数，如果是小数，不能超过小数点后2位","error")
        }
    });
    $(document).on("click",".busFormula .busChangeButton",function(){
        coverHtml();
        var busFormula=$(this).parent().siblings("textfield").html();
        $("#calculatorBefore").html("邮豆金额 ="+busFormula);

        $("#busFormulaSet").show();
        //$("#calculatorVal").html($(this).parent().siblings("abbr").html());
        typeId = $(this).parent().parent().prev().prev().prev().prev().val();
        //插入具体参数
        $.post("../web/api/sysmanage/getComputableParams",{
            typeId:typeId
        },function(data){
            if(data.response == 'success'){
                var html = '';
                data.data.map(function(object){
                    html += '<div data-id="' + object.paramId + '">' + object.paramName + '</div>';
                });
                $("#busFormulaContent ul li:nth-child(1) div:nth-child(2)").empty().append(html);
            }
            else{
                console.log("插入参数失败" + consoleNowTime());
            }
        },'json');

        //$(this).parent().parent().siblings(".busParameter").find(".busParameterDD").each(function(){
        //    var str = $(this).children("input").val();
        //    str = str.split(",")[0];
        //
        //});
        //console.log(html);
        //$("#busFormulaContent ul li:nth-child(1) div:nth-child(2)").empty().append(html);
    });
    $(document).on("click",".calculator",function(){
        var html = '<div class="calculatorParam">' + $(this).html() + '</div>';
        $("#calculatorVal").append(html);
    });
    $(document).on("click","#calculatorBack",function(){
        var s = $("#calculatorVal").children();
        s.last().remove();
    });


//        $("input[name='busParameterRid']").click(function(){
//            if($(this).val() == "0"){
//                $(".hasbusParameter").show();
//            }else if($(this).val() == "1"){
//                $(".hasbusParameter").hide();
//            }
//        })
    $("input[name='busParameterValRid']").click(function(){
        if($(this).val() == "0"){
            $(".busParameterValChoose").show();
            $(".busParameterValPri").hide();
        }else if($(this).val() == "1"||$(this).val() == "2"){
            $(".busParameterValChoose").hide();
            $(".busParameterValPri").show();
        }
    })
    $(document).on("input",".xishushuru",function(){
        if(/^\+?(\d*\.\d{3})$/.test($(this).val())){
            data_type_alert("最多小数点后2位","error")
            var s = $(this).val();
            s= s.substring(0, s.length-1);
            $(this).val(s);
        }
    });
    $(document).on("click","#NewAddButton",function(){
        var html=""
        html +=  '<tr class="pV_tr"> '+ '<td>' + ' <input maxlength="8" type="text">' + '</td>' ;
        html += ' <td>' + '<input class="xishushuru" type="text"> ' +  ' </td> ';
        html += ' <td>'+'<input style="text-align: center;height: 100%;" readonly="readonly" value="已启用" type="text" class="valActive">'+'</td> ';
        html += ' <td> ' + '<input style="width: 100px" type="button" class="deleteBtn allclickButton" value="删除">' + '</td>' +'</tr>';
        $(".newAdd tr:nth-last-child(2)").after(html)
    })

    $(document).on("click",".deleteBtn",function(){
        if($(".newAdd tr").length != 2 ){
         $(this).parent().parent().remove();
        }
        else{
          data_type_alert("至少必须有一个参数","error")
        }
    });
    $(document).on("click","#busParmPlace div",function(){
        var parHtml = '<div class="busParmBut" data-id="' + $(this).data('id') + '">'+$(this).html()+'</div>'
        $("#calculatorVal").append(parHtml)
    })
});


function addHtml(num,txt,id){
    var html = '';
    if( num == 1 ){
        html += '<ul class="tableBody" draggable="false">';
        html += '<input type="hidden" value="' + id + '"><li class="busName" style="height: 42px; line-height: 42px;"><abbr>' + txt + '</abbr> <span class="buttonAppendPlace"><i class="busChangeButton"></i><i class="busDeleteButton"> </i> <i class="busMoveButton"> </i> </span> </li>';
        html += '<li class="busParameter"><dl><div class="sortableBusinessPam ui-sortable"></div><dd><b class="AllAdd" style="color: #54a6de;">+添加</b></dd></dl></li>';
        html += '<li class="busParameterVal"><dl><dd></dd></dl></li>';
        html += '<li class="busFormula" style="height: 42px; line-height: 42px;"><abbr></abbr> <span class="buttonAppendPlace"><i class="busChangeButton"></i></span></li>';
        html += '</ul>';
    }
    if( num == 3 ){
        html += '<i class="busChangeButton">' +'</i>';
    }
    if( num == 4 ){
        html += '<dd>'+'<b class="AllAdd" style="color: #54a6de;">'+ txt + '</b>' +'</dd>';
    }
    if( num == 5 ){
        html += '<dd>'+ '</dd>';
    }
    if( num == 6 ){
        html += '<i class="busChangeButton">' +'</i>'+ '<i class="busDeleteButton">' + ' </i> ' + '<i class="busMoveButtonPar">' + ' </i> ';
    }
    return html;
}

function busCreateParamInit(){
    $("#busParameterName").val();
    $("input[name='busParameterValRid'][value='0']").prop("checked","checked");
    $("input[name='busParameterPrimary'][value='1']").prop("checked","checked");
    var count = 1;
    $(".busParameterValChoose table tbody.newAdd tr").each(function(){
        if(count != 1){
            if($(this).find("td").length > 1){
                $(this).remove();
            }
        }
        else{
            count = 0;
            $(".firstTrInput").val('');
        }
    });


}