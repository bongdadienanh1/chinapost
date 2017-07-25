$(document).ready(function(e) {
    //分页
    $("div.holder").jPages({
        containerID : "itemContainer",
        perPage:5
    });

    $("#newAccout").click(function(){
        $(".creatAccout").fadeIn(500);
        $(".updateAcount,.deleteAccout,.accessControl,.deleteError").fadeOut(500);
    });
    $("#xx,#creatAccoutCancel").click(function(){
        $(".creatAccout").fadeOut(500);
    });
    $("#xx1,#updateAcountCancel").click(function(){
        $( $(".hieselect").children()[0]).prop("selected","selected");
        $(".updateAcount").fadeOut(500);
        $(".hieselect").val(3);
    });
    $("#xx2,#deleteAcountCancel").click(function(){
        $(".deleteAccout").fadeOut(500);
        $(".hieselect").val(3);
    });
    $("#xx3,#accessControlCancel").click(function(){
        $(".accessControl").fadeOut(500);
        $(".hieselect").val(3);
    });



    $(".hieSearch abbr input").focus(function(){
        $(this).css("border","1px solid #008738");
    })
    $(".hieSearch abbr input").blur(function(){
        $(this).css("border","1px solid #ccc");
    })
    $(document).on("click",".update",function(){
        coverHtml();
        var AcountName=$(this).parent().parent().siblings(".updateName").html();
        var updateId=$(this).parent().parent().siblings(".updateId").html();
        var updatehasPermit=$(this).parent().parent().siblings(".updatehasPermit").html();
        var updatediscountPct=$(this).parent().parent().siblings(".updatediscountPct").html();
        var organizationName=$(this).parent().parent().siblings(".organizationName").html();
        var updateEnd=$(this).parent().parent().siblings(".updateEnd").html();
        $("#updateAcountName").val(AcountName);
        $("#enterpriseId").val(updateId);
        $("#updateAcountSale").val(updatediscountPct);
        $("#updateOrganizationName").val(organizationName);
        $("#ifisEND").val(updateEnd);

        if(updatehasPermit=="是"){
            $("input[name='updateHasPermit'][value='0']").prop("checked","checked");
        }else if(updatehasPermit=="否"){
            $("input[name='updateHasPermit'][value='1']").prop("checked","checked");
        }
        if(updateEnd=="否"){
            $(".ifisEnd2").hide();
        }else if(updateEnd=="是"){
            $(".ifisEnd2").show();
        }
        $(".updateAcount").fadeIn(500);
    });
    $("input[name='unPrimary']").click(function(){
        if($(this).val()=="0"){
            $(".ifisEnd").show();
        }else{
            $(".ifisEnd").hide();
        }
    })
    $(document).on("click",".reset",function(){
        coverHtml()
        $(".resetPassword").show();
        var updateId=$(this).parent().parent().siblings(".updateId").html();
        $("#resetEnterpriceId").val(updateId);
        $("input[name='password']").val("")
        $("input[name='passwordConfirm']").val("")
    })
    $(document).on("click","#resetPasswordConfirm",function(){
        var enterpriceId= $("#resetEnterpriceId").val();
        var checkpass=$(".passwordResetChooes:checked").val();
        console.log(checkpass)
        if(checkpass=="0"){
            if($("input[name='password']").val()==$("input[name='passwordConfirm']").val()){
                var check = checkPassword($("input[name='passwordConfirm']").val())
               if($("input[name='passwordConfirm']").val().length > 7 && $("input[name='passwordConfirm']").val().length < 21 && check ){
                   $.post("../web/api/hierarchy/editPassword",{
                       'enterpriseId':enterpriceId,
                       'password':$("input[name='passwordConfirm']").val()
                   },function(data){
                       if( data.response == 'success' ){
                           response_ensure_alert("success","修改密码成功",function(){
                               discoverHtml()
                               $(".resetPassword").hide();
                           })
                       }
                       else{
                           response_ensure_alert("error",data.data.text,function(){

                           });
                       }
                   },'json')
               }else{
                   data_type_alert("密码为8-20位的数字和字母组合","error")
               }
            }else{
                data_type_alert("两次密码输入不正确","error")
            }
        }else if(checkpass=="1"){
            if($("input[name='password']").val()==$("input[name='passwordConfirm']").val()){
               if($("input[name='passwordConfirm']").val().length == 6 ){
                   $.post("../web/api/hierarchy/editPayKey",{
                       'enterpriseId':enterpriceId,
                       'payKey':$("input[name='passwordConfirm']").val()
                   },function(data){
                       if( data.response == 'success' ){
                           response_ensure_alert("success","修改密码成功",function(){
                               discoverHtml()
                               $(".resetPassword").hide();
                           })
                       }
                       else{
                           response_ensure_alert("error",data.data.text,function(){

                           });
                       }
                   },'json')
               }else{
                   data_type_alert("支付密码必须为6位","error")
               }
            }else{
                data_type_alert("两次密码输入不正确","error")
            }
        }
    });
    //网点折扣比例
    var lock = true;
    $("#updateAcountSale").on("input",function(){
        var val = $(this).val();
        var arr = val.split(".");
        if( arr[1] != undefined ){
            if( arr[1].length > 2 && lock == true){
                lock = false;
                $(this).val(val.substring(0,val.length-1));
                response_ensure_alert("error","网点折扣比例小数点后不得超过两位",function(){
                    lock = true;
                    console.log("网折比例输入失败" + consoleNowTime());
                });
            }
        }
    });
    $(document).on("click",".delete",function(){
        $(parent.document.getElementById("cover")).show();
        $(parent.document.getElementById("cover2")).show();
        $("#cover1").show();
        $("#deleteAccout_enterpriseId").val($(this).parent().parent().siblings(".updateId").html());
        $(".deleteAccout").fadeIn(500);
        $(".creatAccout,.updateAcount,.accessControl,.deleteError").fadeOut(500);
    })
    $(document).on("click",".control",function(){
        $(parent.document.getElementById("cover")).show();
        $(parent.document.getElementById("cover2")).show();
        $("#cover1").show();
        $("#permission_enterpriseId").val($(this).parent().parent().children()[6].innerHTML);
        $(".accessControl").fadeIn(500);
        $("#permission_enterpriseId").val($(this).parent().parent().children()[6].innerHTML);
        var getHasPermit = $(this).parent("td").siblings(".getHasPermit").html();
        if(getHasPermit == "0"){
            $(".accessControl input[name='laissez_passer'][value='0']").prop("checked","checked");
        }else if( getHasPermit == "1"){
            $(".accessControl input[name='laissez_passer'][value='1']").prop("checked","checked");
        }
        $(".creatAccout,.updateAcount,.deleteAccout,.deleteError").fadeOut(500);
    })


    $("#deleteAcountSure").click(function(){
        $(".updateAcount,.deleteAccout,.creatAccout,.accessControl").fadeOut(500);
    });
    $("#deleteAcountError").click(function(){
        $(".deleteError").fadeOut(500);
    });

    //搜索
    $("#conSearch").click(function(){
        var keywords = $.trim($("#keywords").val());
        var length = $("#itemContainerHidden").find(".updateName").length;
        var html = '';
        $("#itemContainer").children().css("display","none");
        for(var i = 0;i < length; i++){
            var name = $.trim($("#itemContainerHidden").find(".updateName")[i].innerHTML);
            if( name.match(keywords) ){
                html += $("#itemContainerHidden").find(".updateName")[i].parentNode.outerHTML;
            }
        }
        $("#itemContainer").empty().append(html);
        $("div.holder").jPages({
            containerID : "itemContainer",
            perPage:5
        });
    });
    //新建下级账号
    //新建验证
    //下级公司名称
    var password = '';
    var ensure_password = '';

    //确认密码

    //提交新建
    $("#creatAccoutSure").click(function(){
        var name = $("#creatAccoutName").val()
        var username = $("#creatAccoutloginName").val()
        var discount = $("#creatAccoutDiscountPct").val()
        var organizationName = $("#createOrganizationName").val()
        var password = $("#creatAccoutPassword").val()
        var ensure_password = $("#creatAccoutNamePassConfirm").val()
        var isPar = $(".creatAccout ul li input[name='unPrimary']:checked").val()
        var hasPermit = $(".creatAccout ul li input[name='hasPermit']:checked").val()
        if(isPar == 0){
            isPar = "true";
        }else if(isPar == 1){
            isPar  = "false";
        }
        if(hasPermit == 0){
            hasPermit = "true";
        }else if(hasPermit == 1){
            hasPermit  = "false";
        }
        var args = discount.split(".");
        var temp = ( args[1] == undefined ) ? '' : args[1];

        if( name != "" && username != "" && password != "" ){
                if( password == ensure_password ){
                    var check = checkPassword(ensure_password)
                    if(ensure_password.length> 7 && ensure_password.length < 21 && check){
                        if(isPar=="true"){
                            if(discount!="" && organizationName != ""){
                                if(  temp.length < 3 && args[1] != "" && /^[0-9]+.?[0-9]*$/.test(discount)){
                                    $.post("../web/api/hierarchy/addSon",{
                                        'name':name,
                                        'username':username,
                                        "discountPct":discount,
                                        'password':ensure_password,
                                        'end':isPar,
                                        'hasPermit':hasPermit,
                                        'organizationName':organizationName
                                    },function(data){
                                        if( data.response == 'success' ){
                                            $(parent.document.getElementById("cover")).hide();
                                            $(parent.document.getElementById("cover2")).hide();
                                            $("#cover1").hide();
                                            ensure_alert('hiemanager');
                                        }
                                        else{
                                            response_ensure_alert("error",data.data.text,function(){

                                            });
                                        }
                                    },'json')
                                }else{
                                    data_type_alert("网折比例不正确,必须是正整数或者2位小数","error")
                                }
                            }else{
                                response_ensure_alert("error","网点折扣系数和机构编号不能为空",function(){})
                            }

                        }else{
                            $.post("../web/api/hierarchy/addSon",{
                                'name':name,
                                'username':username,
                                "discountPct":"",
                                'password':ensure_password,
                                'end':isPar,
                                'hasPermit':false,
                                'organizationName':""
                            },function(data){
                                if( data.response == 'success' ){
                                    $(parent.document.getElementById("cover")).hide();
                                    $(parent.document.getElementById("cover2")).hide();
                                    $("#cover1").hide();
                                    ensure_alert('hiemanager');
                                }
                                else{
                                    response_ensure_alert("error",data.data.text,function(){

                                    });
                                }
                            },'json')
                        }
                }else{
                    //密码不一致
                    data_type_alert("密码为8-20位的数字和字母组合","error")
                }
                }else{
                    data_type_alert("两次密码不一致","error")
                }

        }else{
            data_type_alert("参数不能为空","error")
        }
        $(".hieselect").val(3);
    });

    //编辑账号
    $("#updateAcountSure").click(function(){
        var enterpriseId = $("#enterpriseId").val()
        var name =$("#updateAcountName").val()
        var discount = $("#updateAcountSale").val()
        var isPar = $("#ifisEND").val();
        var organizationName = $("#updateOrganizationName").val()
        var hasPer = $("input[name='updateHasPermit']:checked").val();
        var args = discount.split(".");
        var temp = ( args[1] == undefined ) ? '' : args[1];
        if(isPar == "是"){
            isPar = "true";
        }else if(isPar == "否"){
            isPar  = "false";
        }
        if(hasPer == "0"){
            hasPer = "true";
        }else if(hasPer == "1"){
            hasPer  = "false";
        }
        if(isPar=="true"){
            if( organizationName !="" && discount != ""){
                    if( temp.length < 3 && args[1] != "" && /^[0-9]+.?[0-9]*$/.test(discount)){
                        $.post("../web/api/hierarchy/editSon",{
                            'enterpriseId':enterpriseId,
                            'name':name,
                            'discountPct':discount,
                            'end':isPar,
                            'hasPermit':hasPer,
                            'organizationName':organizationName
                        },function(data){
                            if( data.response == 'success' ){
                                $(parent.document.getElementById("cover")).hide();
                                $(parent.document.getElementById("cover2")).hide();
                                $("#cover1").hide();
                                ensure_alert('hiemanager');
                            }
                            else{
                                response_ensure_alert("error",data.data.text,function(){})
                            }
                        },'json')
                    }else{
                        data_type_alert("网折比例不正确,必须是正整数或者2位小数","error")
                    }
                }else{
                    response_ensure_alert("error","网点折扣比例和机构编号不能为空",function(){})
                }

        }else if(isPar =="false"){
            $.post("../web/api/hierarchy/editSon",{
                'enterpriseId':enterpriseId,
                'name':name,
                'discountPct':"",
                'end':isPar,
                'hasPermit':hasPer,
                'organizationName':""
            },function(data){
                if( data.response == 'success' ){
                    $(parent.document.getElementById("cover")).hide();
                    $(parent.document.getElementById("cover2")).hide();
                    $("#cover1").hide();
                    ensure_alert('hiemanager');
                }
                else{
                    response_ensure_alert("error",data.data.text,function(){})
                }
            },'json')

        }



        $(".hieselect").val(3);
    });

    //删除
    $("#deleteAcountSure").click(function(){
        var enterpriseId = $("#deleteAccout_enterpriseId").val();
        $.post("../web/api/hierarchy/deleteSon",{"enterpriseId":enterpriseId},function(data){
            if( data.response == 'success' ){
                $(parent.document.getElementById("cover")).hide();
                $(parent.document.getElementById("cover2")).hide();
                $("#cover1").hide();
                ensure_alert('hiemanager');

            }
            else{
                if(data.data.errorcode == '14'){
                    response_ensure_alert("error",data.data.text,function(){
                        console.log("删除失败" + new Date().toString());
                        discoverHtml();
                    });
                }
            }
            $(".hieselect").val(3);
        },'json');
    });



    //编辑权限
    $("#accessControlSure").click(function(){
        var hasPermit = '';
        if ( $( $("input[name='laissez_passer']")[0] ).prop("checked") ){
            hasPermit = true;
        }
        else if( $( $("input[name='laissez_passer']")[1] ).prop("checked") ){
            hasPermit = false;
        }
        var enterpriseId = $("#permission_enterpriseId").val();
        $.post("../web/api/hierarchy/setPermit",{
            "enterpriseId":enterpriseId,
            "hasPermit":hasPermit
        },function(data){
            if( data.response == 'success' ){
                $(parent.document.getElementById("cover")).hide();
                $(parent.document.getElementById("cover2")).hide();
                $("#cover1").hide();
                ensure_alert('hiemanager');
            }
            else{

            }
        },'json');
    });
});