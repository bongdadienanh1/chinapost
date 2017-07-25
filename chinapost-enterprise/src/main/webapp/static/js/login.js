$(document).ready(function(){
    var count = 1;
    if( localStorage.loginErrorCode == 13 ){
        $(".form_captcha").show();
        $(".mainform").css("height","430px")
        $(".captcha").show();
    }
    else if( localStorage.loginErrorCode == 0 ){
        $(".form_captcha").hide();
        $(".captcha").hide();
    }

    $(".mainform ul li").click(function(){
        $(this).addClass("clickOn").siblings().removeClass("clickOn")
        var liVal=$(this).val();
        if(liVal=="0"){
            $(".welcomelogin").show();
            $(".ucoinSearch").hide();
        }else if(liVal=="1"){
            $(".welcomelogin").hide();
            $(".ucoinSearch").show();
        }

    });
    $(" .mainform div.welcomelogin dd input,.mainform div.ucoinSearch dd input").focus(function(){
        $(this).parent("dd").css("border","1px solid #54a6de");
        $(this).parent("dd").css("box-shadow","0px 0px 0px 0px #fff");
    });
    $(" .mainform div.welcomelogin dd input,.mainform div.ucoinSearch dd input").blur(function(){
        $(this).parent("dd").css("border","1px solid #e1e1e1");
        $(this).parent("dd").css("box-shadow","none");

    });
    $("#UbaoSearch").click(function(){
        var idCard = $("#idCard").val();
        var url_head = $("#login_button").prev().val();
        if(IdentityCodeValid(idCard)){
            $.get(url_head + "/web/api/ucoingrand/isNew",{"idCard":idCard},function(data){
                if( data == true ){
                    response_ensure_alert("error","新用户暂时无法查询",function(){
                        enter_lock2 = true;
                    },function(){
                        enter_lock2 = true;
                    })
                }
                else{
                    $(".hiddenHref").trigger("click");
                }
            });
        }else{
            response_ensure_alert("error","请输入正确的身份证号",function(){
                enter_lock2 = true;
            },function(){
                enter_lock2 = true;
            })
        }
    });
    var enter_lock = true;
    $(".lg_username,.lg_password,input[name='capt']").keydown(function(event){
            if(event.keyCode==13){
                if( enter_lock == true ){
                    enter_lock = false;
                    $("#login_button").trigger("click");
                }
            }
    });
    var enter_lock2 = true;
    $("#idCard").keydown(function(event){
        if(event.keyCode==13){
            if( enter_lock2 == true ) {
                enter_lock2 = false;
                $(".UbaoSearch").trigger("click");
            }
        }
    });
    $(".lg_username").on("input",function(){
        $(".val_username").val($(this).val());
    });
    $(".lg_password").on("input",function(){
        $(".val_password").val($(this).val());
    });
    $("#login_button").click(function(){
        //针对某些傻逼浏览器的表单自动保存
        $(".lg_username").prop("readonly","readonly");
        $(".lg_password").prop("readonly","readonly");

        var username = $(".val_username").val();
        var password = $(".val_password").val();
        var captcha = $("input[name='capt']").val();
        var url_head = $(this).prev().val();
        var url = url_head + '/web/login';
        $.post(url,{
            username:username ,
            password:password,
            k_captcha:captcha
        },function(data){
            if( data.response == 'success' ){
                count = 1;
                localStorage.loginErrorCode = 0;
                sessionStorage["logStatus"] = 'Using';
                window.location.href = url_head + "/web/main";
            }
            else{
                count++;
                //针对某些傻逼浏览器的表单自动保存
                $(".lg_username").prop("readonly","");
                $(".lg_password").prop("readonly","");
                localStorage.loginErrorCode = data.data.errorcode;
                if( count == 3 ){
                    $(".form_captcha").show();
                    $(".mainform").css("height","430px")
                    $(".captcha").show();
                }
                if( data.data.errorcode == 13 ){
                    if( count != 1 ){
                        response_ensure_alert("error","验证码输入有误，请重新输入",function(){
                            enter_lock = true;
                        },function(){
                            enter_lock = true;
                        });
                    }
                    else{
                        count = 0;
                        response_ensure_alert("error",data.data.text,function(){
                            enter_lock = true;
                        },function(){
                            enter_lock = true;
                        });
                    }
                    $(".form_captcha").show();
                    $(".mainform").css("height","430px")
                    $(".captcha").show();
                    $(".captcha").prop("src",url_head + "/web/captcha?t=" + new Date());
                }
                if( data.data.errorcode == 10 ){
                    response_ensure_alert("error",data.data.text,function(){
                        enter_lock = true;
                    },function(){
                        enter_lock = true;
                    });
                    $(".captcha").prop("src",url_head + "/web/captcha?t=" + new Date());
                }
            }
        });
    });

    $("#UbaoLogin_button").click(function(){
        //针对某些傻逼浏览器的表单自动保存
        $(".lg_username").prop("readonly","readonly");
        $(".lg_password").prop("readonly","readonly");

        var username = $(".val_username").val();
        var password = $(".val_password").val();
        var captcha = $("input[name='capt']").val();
        var url_head = $(this).prev().val();
        var url = url_head + '/web/login';
        $.post(url,{
            username:username ,
            password:password,
            k_captcha:captcha
        },function(data){
            if( data.response == 'success' ){
                $.post("../web/api/current/isEnd",{

                },function(data){
                    if(data.response=="success"){
                        if(data.data){
                            count = 1;
                            localStorage.loginErrorCode = 0;
                            sessionStorage["logStatus"] = 'Using';
                            window.location.href = url_head + "/web/memberCheck";
                        }else{
                            $(".lg_username").prop("readonly","");
                            $(".lg_password").prop("readonly","");
                            data_type_alert("请登入网点账号查询","error")
                        }
                    }else{

                    }
                },'json')

            }
            else{
                count++;
                //针对某些傻逼浏览器的表单自动保存
                $(".lg_username").prop("readonly","");
                $(".lg_password").prop("readonly","");
                localStorage.loginErrorCode = data.data.errorcode;
                if( count == 3 ){
                    $(".form_captcha").show();
                    $(".mainform").css("height","430px")
                    $(".captcha").show();
                }
                if( data.data.errorcode == 13 ){
                    if( count != 1 ){
                        response_ensure_alert("error","验证码输入有误，请重新输入",function(){
                            enter_lock = true;
                        },function(){
                            enter_lock = true;
                        });
                    }
                    else{
                        count = 0;
                        response_ensure_alert("error",data.data.text,function(){
                            enter_lock = true;
                        },function(){
                            enter_lock = true;
                        });
                    }
                    $(".form_captcha").show();
                    $(".mainform").css("height","430px")
                    $(".captcha").show();
                    $(".captcha").prop("src",url_head + "/web/captcha?t=" + new Date());
                }
                if( data.data.errorcode == 10 ){
                    response_ensure_alert("error",data.data.text,function(){
                        enter_lock = true;
                    },function(){
                        enter_lock = true;
                    });
                    $(".captcha").prop("src",url_head + "/web/captcha?t=" + new Date());
                }
            }
        });
    });

    $(".captcha").click(function(){
        $(".captcha").prop("src","../web/captcha?t=" + new Date());
    });
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    function disabled(){
        return 0;
    }
    function goSearch() {
        var idCard = $("#idCard").val();
        var a = window.open("_blank");
        var url_head = $("#url_head").val();
        a.location = url_head + '/web/Ubao_Search?idCard=' + idCard;
    }
    sessionStorage.setItem("ShoppingCart",'');
    $("#idCard").on("input",function(){
        if($(this).val().length == 18){
            var idCard = $("#idCard").val();
            var url_head = $("#login_button").prev().val();
            if(IdentityCodeValid(idCard)){
                $.get(url_head + "/web/api/ucoingrand/isNew",{"idCard":idCard},function(data){
                    if( data == true ){
                        $(".ubaosearch_alert").html("该用户为新用户无法查询");
                        $(".UbaoSearch").attr("onclick","disabled()");
                    }
                    else{
                        $(".ubaosearch_alert").html("");
                        $(".UbaoSearch").css("background-color","#54a6de");
                        $(".UbaoSearch").attr("onclick","goSearch()");
                    }
                });
            }else{
                $(".ubaosearch_alert").html("请输入正确的身份证号");
                $(".UbaoSearch").attr("onclick","disabled()");
            }
        }
        else{
            $(".UbaoSearch").css("background-color","#999");
            $(".UbaoSearch").attr("onclick","disabled()");
        }
    });
});

