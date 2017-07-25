<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <title>无标题文档</title>
    <style>
        @media screen and ( max-width: 1360px){

        }
        a{
            color:#54a6de;
        }
        .aList{
            width:100%;
            height:100%;
            margin-left:20px;
            margin-top: 50px;
        }
        .aList li{
            width:100%;
            padding:1px 0px;
            margin-top:20px;
        }
        .aList li abbr{
            display:inline-block;
            text-align:right;
            font-size:14px;
            color:#333;
            width:120px;
        }
        .aList li span{
            display:inline-block;
            font-size:14px;
            color:#333;
            width:400px;
            margin-left:30px;
        }
        #aloadPwd{
            width:400px;
            height:30px;
            border-style:none;
            border:1px solid #999;
            border-radius:3px;
            background:#fff;
            margin:0px 80px 0px 30px;
        }
        #apayPwd{
            width:400px;
            height:30px;
            border-style:none;
            border:1px solid #999;
            border-radius:3px;
            background:#fff;
            margin:0px 80px 0px 30px;

        }
        .update{
            display:inline-block;
            margin:0px 20px;
        }

        .inputval{
            border-style:none;
            margin-left:30px;
            width:550px;
            height: 32px;
            margin-top:-5px;
        }
        .confirm{
            margin:0px 10px;
            display:none;
        }
        .cancel{
            margin:0px 10px;
            display:none;
        }


        .adddressChange{
            position: relative;
            z-index: 2;
            width:540px;
            height: 200px;
            left: 500px;
            top: -80px;
            border: 1px solid #ccc;
            background: #fff;
        }
        .adddressChange select{
            width: 100px;
            height: 30px;
            margin-top: 20px;
            margin-right: 20px;
        }
        .adddressChange input{
            width: 480px;
            height: 30px;
            margin-top: 20px;
            margin-left: 40px;
        }
        .adddressChange a{
            margin-left: 40px;
            margin-top: 20px;
        }
        .createPaykey{
            width:600px;
            height:350px;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index: 2;
            display: none;
        }
        #xx4{
            display:inline-block;
            width:16px;
            height:16px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #createPaykeyConfirm{
            position:relative;
            left:180px;
            top:40px;
        }
        #createPaykeyCancel{
            position:relative;
            left:210px;
            top:40px;
        }


        .updatePaykey{
            width:600px;
            height:350px;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index: 2;
            display: none;
        }
        .updatePaykey input[type='password'],.updateLoginpassword input[type='password']{
            margin-left: 15px;
            border-radius: 3px;
        }
        #updatePaykeyConfirm{
            position:relative;
            left:180px;
            top:40px;
        }
        #updatePaykeyCancel{
            position:relative;
            left:210px;
            top:40px;
        }
        .updateLoginpassword{
            width:600px;
            height:350px;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index: 2;
            display: none;
        }
        #updateLoginpasswordConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#54a6de;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #updateLoginpasswordCancel{
            width:80px;
            height:30px;
            border-style:none;
            color: #999;
            border:1px solid #999;
            background:#f2f2f2;
            position:relative;
            left:210px;
            top:40px;
        }
        #accountNum{
            display: none;
            width: 400px;
            position: fixed;
            left: 10%;
            top:20%;
            z-index: 3;
            background: #fff;
        }
        #accountNum li{
            width: 100%;
            margin: 20px 0px;
        }

    </style>



    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        sessionStorage.setItem("ShoppingCart",'');
        $(document).ready(function(e) {
            $(".cancel").click(function(){
                location.reload();
            })
            var accountName="${enterpriseInfo.accountName}"
            var conpanyAddress="${enterpriseInfo.address}"
            var contactMan="${enterpriseInfo.linkman}"
            var contactTel="${enterpriseInfo.linkMobile}"
            $(".inputval").focus(function(){
                $("input[name='accountName']").val(accountName);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='contactTel']").val(contactTel);
                $("input[name='accountName'],input[name='account'],input[name='conpanyAddress'],input[name='contactMan'],input[name='contactTel']").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide();
                })
            })
            $("input[name='accountName']").focus(function(){
                $("input[name='accountName']").val(accountName);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='contactTel']").val(contactTel);
                $("input[name='accountName'],input[name='account'],input[name='conpanyAddress'],input[name='contactMan'],input[name='contactTel']").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide();
                })
                accountName=$(this).val();
                $(this).css({"border":"1px solid #54a6de"}).prop("readonly",null).siblings("a").show();
            });

            $("input[name='accountName']").siblings(".confirm").click(function(){
                var accN=$("input[name='accountName']").val();
                if(accN!==accountName){
                    $.post("${bath}/web/api/accountcenter/editAccountInfo", {
                        accountName: accN
                    }, function (data) {
                        if(data.response=="success"){
                            var txt=  "修改成功";
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                onOk:function(){
                                    location.reload()
                                }
                            });
                        }else{
                            response_ensure_alert("error",data.data.text,function(){})
                        }

                    }, "json")

                }
            });
            $("input[name='conpanyAddress']").siblings(".imgshow").click(function(){
                $("input[name='conpanyAddress']").trigger("focus");
            })
            $("input[name='conpanyAddress']").focus(function(){
                var pid = $("#provinceId").val();
                var cid = $("#cityId").val();
                var did = $("#districtId").val();
                $("input[name='accountName']").val(accountName);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='contactTel']").val(contactTel);
                console.log(pid,cid,did)
                conpanyAddress=$(this).val();
                console.log(conpanyAddress)
                $("#newMemberRoadDetail").val($("#addrDetail").val());
                $.get("${bath}/web/api/common/provincies",function(data){
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


                $("input[name='accountName'],input[name='account'],input[name='conpanyAddress'],input[name='contactMan'],input[name='contactTel']").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
                $(this).parent("div").show().siblings("input").css({"border":"none"});
                $(this).css({"border":"1px solid #54a6de"}).siblings().children("a").show();
                $(this).css({"border":"1px solid #54a6de"}).siblings("div").show().siblings(".imgshow").hide();

            });


            $("input[name='conpanyAddress']").siblings().children(".confirm").click(function(){
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
                var addDetail=$("#newMemberRoadDetail").val()
                if( addPV && addCV && addDV && addDetail){
                    $.post("${bath}/web/api/accountcenter/editAccountInfo", {
                        provinceId: addPV,
                        cityId:addCV,
                        districtId:addDV,
                        addrDetail:addDetail
                    }, function (data) {
                        if(data.response=="success"){
                            var txt=  "修改成功";
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                onOk:function(){
                                    location.reload()
                                }
                            });
                        }else{
                            response_ensure_alert("error",data.data.text,function(){})
                        }

                    }, "json")
                }else{
                    response_ensure_alert("error","请完善地址",function(){})
                }
            });
            $("input[name='contactMan']").siblings(".imgshow").click(function(){
                $("input[name='contactMan']").trigger("focus");
            })
            $("input[name='contactMan']").focus(function(){
                $("input[name='accountName']").val(accountName);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='contactTel']").val(contactTel);
                $("input[name='accountName'],input[name='account'],input[name='conpanyAddress'],input[name='contactMan'],input[name='contactTel']").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
                 contactMan=$(this).val();
                $(this).css({"border":"1px solid #54a6de"}).prop("readonly",null).siblings("a").show().siblings(".imgshow").hide();
            });

            $("input[name='contactMan']").siblings(".confirm").click(function(){
                var linkman= $("input[name='contactMan']").val()
                if(contactMan!=linkman){
                    $.post("${bath}/web/api/accountcenter/editAccountInfo", {
                        linkman: linkman
                    }, function (data) {
                        if(data.response=="success"){
                            var txt=  "修改成功";
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                onOk:function(){
                                    location.reload()
                                }
                            });
                        }else{
                            response_ensure_alert("error",data.data.text,function(){})
                        }


                    }, "json")
                }

            });
            $("input[name='contactTel']").siblings(".imgshow").click(function(){
                $("input[name='contactTel']").trigger("focus");
            })
            $("input[name='contactTel']").focus(function(){
                $("input[name='accountName']").val(accountName);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='contactTel']").val(contactTel);
                $("input[name='accountName'],input[name='account'],input[name='conpanyAddress'],input[name='contactMan'],input[name='contactTel']").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
               contactTel=$(this).val();
                $(this).css({"border":"1px solid #54a6de"}).prop("readonly",null).siblings("a").show().siblings(".imgshow").hide();
            });
            $(".allpop h1 i").click(function(){
                discoverHtml();
                $(this).parent().parent(".allpop").hide()
            })
            $("#createPaykeyCancel").click(function(){
                discoverHtml();
                $(".createPaykey").hide()
            })
            $("#updatePaykeyCancel").click(function(){
                discoverHtml();
                $(".updatePaykey").hide()
            })
            $("#updateLoginpasswordCancel").click(function(){
                discoverHtml();
                $(".updateLoginpassword").hide()
            })
            $(".forgetPassword").click(function(){
                data_type_alert("请联系上级账号修改","error")
            });
            $(".forgetPasswordTop").click(function(){
                data_type_alert("请联系优生活客服修改，客服电话 400-966-1900","error")
            });

            $("input[name='contactTel']").siblings(".confirm").click(function(){
                var linkMobile=  $("input[name='contactTel']").val()
                if(/^1[3|4|5|7|8]\d{9}$/.test(linkMobile)){
                    if(contactTel!=linkMobile){
                        $.post("${bath}/web/api/accountcenter/editAccountInfo", {
                            linkMobile: linkMobile
                        }, function (data) {
                            if(data.response=="success"){
                                var txt=  "修改成功";
                                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                    onOk:function(){
                                        location.reload()
                                    }
                                });
                            }else{
                                response_ensure_alert("error",data.data.text,function(){})
                            }


                        }, "json")
                    }
                }else{
                    data_type_alert("手机号号码不正确","error")
                }
            });

            $.get("${bath}/web/api/common/provincies",function(data){
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
            $("#newMemAddressD").change(function(){
                $("#newMemAddressR option[value!='0']").remove()
                $.post("../web/api/common/streets",{
                    "districtId":$(this).val()
                },function(data){
                    if( data.response == 'success' ){
                        data.data.map(function(object){
                            var html = '';
                            html += '<option value="' + object.streetId +'">';
                            html += object.streetName;
                            html += '</option>';
                            $("#newMemAddressR").append(html);
                        });
                    }
                },'json');
            });

            $("#updatePaykeyConfirm").click(function(){
                var prePwd =$("input[name='oldPayPassword']").val();
                var password = $("input[name='payPassword']").val();
                var passwordConfirm = $("input[name='payPasswordConfirm']").val();
                if(password == passwordConfirm){
                    var count = passwordConfirm.length
                    console.log(count)
                    if(count == 6 ){
                        $.post("${bath}/web/api/accountcenter/editpaykey", {
                            prePK:prePwd,
                            paykey: passwordConfirm
                        }, function (data) {
                            if(data.response=="success"){
                                var txt=  "修改成功";
                                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                    onOk:function(){
                                        discoverHtml()
                                        location.reload()
                                    }
                                });
                            }else{
                                data_type_alert(data.data.text,"error");
                            }

                        }, "json")
                    }else{
                        data_type_alert("密码为8-20位的数字和字母组合","error");
                    }
                }else{
                    data_type_alert("两次密码输入不一致","error");
                }
            })
            $("#updateLoginpasswordConfirm").click(function(){
                var prePwd =$("input[name='oldLoginPassword']").val();
                var password = $("input[name='loginPassword']").val();
                var passwordConfirm = $("input[name='loginPasswordConfirm']").val();
                if(password == passwordConfirm){
                    var count = passwordConfirm.length
                    var check = checkPassword(passwordConfirm)
                if(count > 7 && count < 21 && check){
                    $.post("${bath}/web/api/accountcenter/editpassword", {
                        prePwd:prePwd,
                        password: passwordConfirm
                    }, function (data) {
                        if(data.response=="success"){
                            var txt=  "修改成功";
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                onOk:function(){
                                    $(parent.document.getElementById("logout")).trigger("click")
                                }
                            });
                        }else{
                            data_type_alert(data.data.text,"error");
                        }

                    }, "json")
                }else{
                    data_type_alert("密码为8-20位的数字和字母组合","error");
                }
                }else{
                    data_type_alert("两次密码输入不一致","error");
                }
            })
            $("#createPaykeyConfirm").click(function(){
                var password = $("input[name='creatPayPassword']").val();
                var passwordConfirm = $("input[name='creatPayPasswordConfirm']").val();
                if( password == passwordConfirm ){
                var count=passwordConfirm.length
                if(count == 6){
                    $.post("${bath}/web/api/accountcenter/editpaykey", {
                        paykey: passwordConfirm
                    }, function (data) {
                        if(data.response=="success"){
                            var txt=  "修改成功";
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                onOk:function(){
                                    discoverHtml()
                                    location.reload()
                                }
                            });
                        }else{
                            data_type_alert(data.data.text,"error");
                        }

                    }, "json")
                }else{
                    data_type_alert("支付密码必须6位","error");
                }
                }else{
                    data_type_alert("两次密码输入不一致","error");
                }
            })
            $(".updateLoginpassword").draggable();
            $(".createPaykey").draggable();
            $(".updatePaykey").draggable();
            $(".loginPasswordupdate").click(function(){
                coverHtml();
                $(".updateLoginpassword").show()
            })
            $(".payPasswordCreate").click(function(){
                coverHtml();
                $(".createPaykey").show()
            })
            $(".payPasswordupdate").click(function(){
                coverHtml();
                $(".updatePaykey").show()
            })
            $("#setIdentification").click(function(){
                coverHtml()
                $("#accountNum").show()
            })
            $(document).on("click","#accountNumCancel",function(){
                discoverHtml()
                $("#accountNum").hide()
            })
            $(document).on("click","#accountNumConfirm",function(){
                var accountNum = $("#accountNumVal").val()
                if(accountNum != ""){
                    $.post("../web/api/accountcenter/editIdentification",{
                        identification:accountNum
                    },function(data){
                        if( data.response == 'success' ){
                            discoverHtml()
                            response_ensure_alert("success","修改成功",function(){
                                window.location.reload()
                            })
                        }
                        else{
                            response_ensure_alert('error',data.data.text,function(){

                            });
                        }
                    },'json');

                }else{
                    response_ensure_alert("error","请输入机构标示")
                }
            })
        });



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
    </script>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
<input type="hidden" id="provinceId" value="${enterpriseInfo.provinceId}">
<input type="hidden" id="cityId" value="${enterpriseInfo.cityId}">
<input type="hidden" id="districtId" value="${enterpriseInfo.districtId}">
<input type="hidden" id="addrDetail" value="${enterpriseInfo.addrDetail}">
<div class="allheadstyle">
    <a class="rightshanow" href="companyAccount">公司信息</a><span>账号信息</span><abbr class="leftshanow"></abbr>
</div>

<div class="aList">
    <ul>
        <#if isEmp == "true">
            <li><abbr>账号:</abbr><input name="account" class="inputval" type="text" value="${enterpriseManager.username}" readonly="readonly">
            </li>
            <li><abbr>登录密码:</abbr><input style="border: none;" readonly="readonly" class="inputval" maxlength="20" type="password" name="aloadPwd" id="aloadPwd" placeholder="登录账户时需要输入的密码" /><span><a class="loginPasswordupdate update">修改</a><a class="forgetPasswordTop">忘记</a></span>
            </li>
            <li><abbr>支付密码:</abbr><input style="border: none;" readonly="readonly" class="inputval" maxlength="6" type="password" name="apayPwd" id="apayPwd" placeholder="在账户财富(邮豆)变动时需要输入的密码" /><span><#if hasPaykey><a class="payPasswordupdate update">修改</a><a class="forgetPasswordTop">忘记</a><#else><a class="payPasswordCreate update">设置</a></#if></span>
            </li>
        <#elseif isEnd=="true">
            <li><abbr>网点名称:</abbr><input name="conpanyName" class="inputval" type="text" value="${enterpriseInfo.enterpriseName}" readonly="readonly">
            <li><abbr>机构编号:</abbr><input name="organizationName" class="inputval" type="text" value="${enterpriseInfo.accountName}" readonly="readonly">
            <li><abbr>机构标识:</abbr><input name="organizationName" class="inputval" type="text" value="${enterpriseInfo.identification}" readonly="readonly"><a id="setIdentification">设置/修改机构标识</a>
            </li>
            <li style="height: 30px;"><abbr>网点联系地址:</abbr><input name="conpanyAddress" class="inputval" type="text" value="${enterpriseInfo.address}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i>
                <div style="display:none ;" class="adddressChange allpop">
                    <h1>修改地址</h1>
                    <select style="margin-left:40px;" name="newMemAddressP" id="newMemAddressP">
                        <option value="0" selected="selected">选择省份</option>
                    </select>
                    <select name="newMemAddressC" id="newMemAddressC">
                        <option value="0" selected="selected">选择城市</option>
                    </select>
                    <select name="newMemAddressD" id="newMemAddressD">
                        <option value="0" selected="selected">选择区县</option>
                    </select>
                <#--<select name="newMemAddressR" id="newMemAddressR">-->
                <#--<option value="0" selected="selected">选择街道</option>-->
                <#--</select>-->
                    <input type="text" name="newMemberRoadDetail" id="newMemberRoadDetail" placeholder="详细地址" />
                   <a class="confirm">修改</a><a class="cancel">放弃</a>
                </div>
            </li>
            <li><abbr>网点联系人:</abbr><input name="contactMan" maxlength="8" class="inputval" type="text" value="${enterpriseInfo.linkman}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i><a class="confirm">修改</a><a class="cancel">放弃</a>
            </li>
            <li><abbr>网点联系方式:</abbr><input maxlength="11" name="contactTel" class="inputval" type="text" value="${enterpriseInfo.linkMobile}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i><a class="confirm">修改</a><a class="cancel">放弃</a>
            </li>
            <li><abbr>网点折扣系数:</abbr><input name="DiscountRate" class="inputval" type="text" value="${enterprisefunction.discountPct}" readonly="readonly"><a class="confirm">修改</a><a class="cancel">放弃</a>
            </li>
            <li><abbr>账号:</abbr><input name="account" class="inputval" type="text" value="${enterpriseManager.username}" readonly="readonly">
            </li>
            <li><abbr>登录密码:</abbr><input style="border: none;" readonly="readonly" class="inputval" maxlength="20" type="password" name="aloadPwd" id="aloadPwd" placeholder="登录账户时需要输入的密码" /><span><a class="loginPasswordupdate update">修改</a><a class="forgetPassword">忘记</a></span>
            </li>
            <li><abbr>支付密码:</abbr><input style="border: none;" readonly="readonly" class="inputval" maxlength="6" type="password" name="apayPwd" id="apayPwd" placeholder="在账户财富(邮豆)变动时需要输入的密码" /><span><#if hasPaykey><a class="payPasswordupdate update">修改</a><a class="forgetPassword">忘记</a><#else><a class="payPasswordCreate update">设置</a></#if></span>
            </li>
        <#else>
            <li><abbr>部门名称:</abbr><input name="conpanyName" class="inputval" type="text" value="${enterpriseInfo.enterpriseName}" readonly="readonly">
            </li>
            <li style="height: 30px;"><abbr>部门联系地址:</abbr><input name="conpanyAddress" class="inputval" type="text" value="${enterpriseInfo.address}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i>
                <div style="display:none ;" class="adddressChange allpop">
                    <h1>修改地址</h1>
                    <select style="margin-left:40px;" name="newMemAddressP" id="newMemAddressP">
                        <option value="0" selected="selected">选择省份</option>
                    </select>
                    <select name="newMemAddressC" id="newMemAddressC">
                        <option value="0" selected="selected">选择城市</option>
                    </select>
                    <select name="newMemAddressD" id="newMemAddressD">
                        <option value="0" selected="selected">选择区县</option>
                    </select>
                <#--<select name="newMemAddressR" id="newMemAddressR">-->
                <#--<option value="0" selected="selected">选择街道</option>-->
                <#--</select>-->
                    <input type="text" name="newMemberRoadDetail" id="newMemberRoadDetail" placeholder="详细地址" />
                    <a class="confirm">修改</a><a class="cancel">放弃</a>
                </div>
            </li>
            <li><abbr>部门联系人:</abbr><input name="contactMan" maxlength="8" class="inputval" type="text" value="${enterpriseInfo.linkman}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i><a class="confirm">修改</a><a class="cancel">放弃</a>
            </li>
            <li><abbr>部门联系方式:</abbr><input maxlength="11" name="contactTel" class="inputval" type="text" value="${enterpriseInfo.linkMobile}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i><a class="confirm">修改</a><a class="cancel">放弃</a>
            </li>
            <li><abbr>账号:</abbr><input name="account" class="inputval" type="text" value="${enterpriseManager.username}" readonly="readonly">
            </li>
            <li><abbr>登录密码:</abbr><input style="border: none;" readonly="readonly" class="inputval" maxlength="20" type="password" name="aloadPwd" id="aloadPwd" placeholder="登录账户时需要输入的密码" /><span><a class="loginPasswordupdate update">修改</a><a class="forgetPassword">忘记</a></span>
            </li>

            <li><abbr>支付密码:</abbr><input style="border: none;" readonly="readonly" class="inputval" maxlength="6" type="password" name="apayPwd" id="apayPwd" placeholder="在账户财富(邮豆)变动时需要输入的密码" /><span><#if hasPaykey><a class="payPasswordupdate update">修改</a><a class="forgetPassword">忘记</a><#else><a class="payPasswordCreate update">设置</a></#if></span>
            </li>
        </#if>


    </ul>
</div>

    <div class="createPaykey allpop">
        <h1>创建支付密码<i onclick="discoverHtml()" id="xx4"></i></h1>
        <ul>
            <li style="height:150px; line-height:50px; padding-left:50px;margin-top: 30px">
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>输入密码:<input maxlength="6" type="password" name="creatPayPassword"/></span><br />
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>确认密码:<input maxlength="6" type="password" name="creatPayPasswordConfirm" /></span>
            </li>
            <li style="height:120px; border-top:1px solid #CCC;">
                <input class="allseachButton" type="button" id="createPaykeyConfirm" value="确定" />
                <input class="allcancelButton" onclick="discoverHtml()" type="button" id="createPaykeyCancel" value="取消" />
            </li>
        </ul>
    </div>

    <div class="updatePaykey allpop">
        <h1>修改支付密码<i onclick="discoverHtml()" id="xx4"></i></h1>
        <ul>
            <li style="height:150px; line-height:50px; padding-left:50px;margin-top: 30px">
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>初始密码:<input maxlength="6" type="password" name="oldPayPassword"/></span><br />
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>输入密码:<input maxlength="6" type="password" name="payPassword"/></span><br />
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>确认密码:<input maxlength="6" type="password" name="payPasswordConfirm" /></span>
            </li>
            <li style="height:120px; border-top:1px solid #CCC;">
                <input class="allseachButton" type="button" id="updatePaykeyConfirm" value="确定" />
                <input class="allcancelButton" onclick="discoverHtml()" type="button" id="updatePaykeyCancel" value="取消" />
            </li>
        </ul>
    </div>

    <div class="updateLoginpassword allpop">
        <h1>修改登录密码<i onclick="discoverHtml()" id="xx4"></i></h1>
        <ul>
            <li style="height:150px; line-height:50px; padding-left:50px;margin-top: 30px">
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>初始密码:<input maxlength="20" type="password" name="oldLoginPassword"/></span><br />
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>输入密码:<input maxlength="20" type="password" name="loginPassword"/></span><br />
                <span style="padding-left:100px;"><b style="color: #ff3300">*</b>确认密码:<input maxlength="20" type="password" name="loginPasswordConfirm" /></span>
            </li>
            <li style="height:120px; border-top:1px solid #CCC;">
                <input class="allseachButton" type="button" id="updateLoginpasswordConfirm" value="确定" />
                <input class="allcancelButton" onclick="discoverHtml()" type="button" id="updateLoginpasswordCancel" value="取消" />
            </li>
        </ul>
    </div>
    <div class="allpop" id="accountNum">
        <h1>设置机构标识</h1>
        <ul>
            <li style="margin-top: 20px;margin-left: 20px"><input value="${enterpriseInfo.identification}" type="text" id="accountNumVal"> </li>
            <li><input id="accountNumCancel" style="background: #bbbbbb; color: #333;margin-left: 80px;" class="button allcancelButton" type="button" value="取消" /><input id="accountNumConfirm" style="margin-left: 20px" class="button allseachButton" value="确定" type="button"/></li>
        </ul>
    </div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
</html>
