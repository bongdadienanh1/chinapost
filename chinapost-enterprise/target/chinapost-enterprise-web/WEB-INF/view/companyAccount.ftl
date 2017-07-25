<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <title>无标题文档</title>
    <style>
        @media screen and ( max-width: 1400px){

        }
        body{
            background:#f7f7f7;
        }

        a{
            color:#54a6de;
        }
        .cList{
            width:100%;
            height:100%;
            margin-left:20px;
        }
        .cList li{
            width:100%;
            padding:1px 0px;
            margin-top:20px;
        }
        .cList li abbr{
            display:inline-block;
            text-align:right;
            font-size:14px;
            color:#333;
            width:120px;
        }
        .cList li span{
            display:inline-block;
            font-size:14px;
            color:#333;
            width:400px;
            margin-left:30px;
        }

        .inputval{
            border-style:none;
            background:transparent;
            margin-left:30px;
            width:580px;
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
        #upload{
            display: none;
            width: 400px;
            height: 350px;
            position: fixed;
            left: 10%;
            top:20%;
            z-index: 3;
            background: #fff;
        }
        #upload li{
            width: 100%;
            height: 20px;
        }
        #upload li:first-child{
            height: 200px;
        }

    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        sessionStorage.setItem("ShoppingCart",'');
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var conpanyName = '${enterprise.enterpriseName}';
        var conpanySName = '${enterprise.profile}';
        var conpanySAddress = '${enterprise.registerAddress}';
        var contactMan = '${enterprise.linkman}';
        var conpanyAddress = '${enterprise.address}';
        var contactTel = '${enterprise.mobile}';
        $(document).ready(function(e) {
            if(${flag}){
                $( "#upload" ).draggable();
                $(".cancel").click(function(){
                    location.reload();
                })
                $("#LogoUpdate").click(function(){
                    var updateInterval = false;
                    var domId = "LogoUpdate";
                    var _this = this;
                    var imgUploader = setInterval(function(){
                        if( updateInterval == false && $(_this).val() != '' ){
                            if( $("#LogoUpdate").val() ){
                                updateInterval = UpladFile(imgUploader,domId);
                            }
                        }
                    },500);
                });
                $("#companyLogoConfirm").click(function(){
                    var imgUrl=$("#companyLogo").prop("src");
                    if(imgUrl!=""){
                    $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
                        imgUrl: imgUrl
                    }, function (data) {
                        if(data.response=="success"){
                            var txt=  "修改成功";
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                onOk:function(){
                                    parent.location.reload();
                                }
                            });
                        }else{
                            response_ensure_alert("error",data.data.text,function(){
                                location.reload()
                            })
                        }
                    }, "json")
                    }else{
                        data_type_alert("error","请选择上传文件")
                    }
                });
                $("#conpanyLogoUpload").click(function(){
                    coverHtml();
                    var url=$(this).siblings("span").children("img").prop("src");
                    $("#companyLogo").prop("src",url)
                    $("#upload").show();
                });
                $("#companyLogoCancel").click(function(){
                    discoverHtml();
                    $("#upload").hide();
                });
                $("input[name='conpanyName']").siblings(".imgshow").click(function(){
                    $("input[name='conpanyName']").trigger("focus");
                })
            $("input[name='conpanyName']").focus(function () {
                $("input[name='conpanyName']").val(conpanyName);
                $("input[name='conpanySName']").val(conpanySName);
                $("input[name='conpanySAddress']").val(conpanySAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactTel']").val(contactTel);
                $(".cList input").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
                conpanyName = $(this).val();
                $(this).css({"border": "1px solid #ccc"}).prop("readonly", null).siblings("a").show().siblings(".imgshow").hide();
            });
            $("input[name='conpanyName']").siblings(".confirm").click(function () {
                    var enterpriseName=$("input[name='conpanyName']").val()
                    if(conpanyName !==enterpriseName) {
                        $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
                            enterpriseName: enterpriseName
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
                    $(this).hide().siblings("a").hide().siblings("input").css({"border": "none"}).prop("readonly", "readonly");
                });


                $("input[name='conpanySName']").siblings(".imgshow").click(function(){
                    $("input[name='conpanySName']").trigger("focus");
                })
                $("input[name='conpanySName']").focus(function () {
                $("input[name='conpanyName']").val(conpanyName);
                $("input[name='conpanySName']").val(conpanySName);
                $("input[name='conpanySAddress']").val(conpanySAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactTel']").val(contactTel);
                $(".cList input").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
                conpanySName = $(this).val();
                $(this).css({"border": "1px solid #ccc"}).prop("readonly", null).siblings("a").show().siblings(".imgshow").hide();
            });
                $("input[name='conpanySName']").siblings(".confirm").click(function () {
                    var profile=$("input[name='conpanySName']").val();
                    if(profile!==conpanySName){
                        $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
                            profile: profile
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
                $("input[name='conpanySAddress']").siblings(".imgshow").click(function(){
                    $("input[name='conpanySAddress']").trigger("focus");
                })
            $("input[name='conpanySAddress']").focus(function () {
                $("input[name='conpanyName']").val(conpanyName);
                $("input[name='conpanySName']").val(conpanySName);
                $("input[name='conpanySAddress']").val(conpanySAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactTel']").val(contactTel);
                $(".cList input").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                });
                $("#newMemberRoadDetail").css({"border": "1px solid #ccc"}).prop("readonly", "");
                conpanySAddress = $(this).val();
                $(this).parent("div").show().siblings("input").css({"border": "none"});
                $(this).css({"border": "1px solid #ccc"}).siblings().children("a").show();
                $(this).css({"border": "1px solid #ccc"}).siblings("div").show().siblings(".imgshow").hide();

            });
             $("input[name='conpanySAddress']").siblings().children(".confirm").click(function () {
                    var addPV = $("#newMemAddressP").val();
                    if (addPV == "0") {
                        var addP = ""
                    } else {
                        var addP = $("#newMemAddressP option:selected").html()

                    }
                    var addCV = $("#newMemAddressC").val();
                    if (addCV == "0") {
                        var addC = ""
                    } else {
                        var addC = $("#newMemAddressC option:selected").html()

                    }
                    var addDV = $("#newMemAddressD").val();
                    if (addDV == "0") {
                        var addD = ""
                    } else {
                        var addD = $("#newMemAddressD option:selected").html()

                    }

                    var addDetail = $("#newMemberRoadDetail").val()

                    $("input[name='conpanySAddress']").val(addP + addC + addD + addDetail)
                 var sAddress=addP + addC + addD + addDetail;
                 if(conpanySAddress!==sAddress){
                     $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
                         registerAddress: sAddress
                     }, function (data) {
                         if(data.response=="success"){
                             var txt=  "修改成功";
                             window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                                 onOk:function(){
                                     location.reload()
                                 }
                             });
                         }

                     }, "json")
                 }
                });
                $("input[name='contactMan']").siblings(".imgshow").click(function(){
                    $("input[name='contactMan']").trigger("focus");
                })
            $("input[name='contactMan']").focus(function () {
                $("input[name='conpanyName']").val(conpanyName);
                $("input[name='conpanySName']").val(conpanySName);
                $("input[name='conpanySAddress']").val(conpanySAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactTel']").val(contactTel);
                $(".cList input").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
                contactMan = $(this).val();
                $(this).css({"border": "1px solid #ccc"}).prop("readonly", null).siblings("a").show().siblings(".imgshow").hide();
            });
            $("input[name='contactMan']").siblings(".confirm").click(function () {
                var linkman=$("input[name='contactMan']").val();
                if(contactMan!==linkman){
                    $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
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
                $("input[name='conpanyAddress']").siblings(".imgshow").click(function(){
                    $("input[name='conpanyAddress']").trigger("focus");
                })
            $("input[name='conpanyAddress']").focus(function () {
                $("input[name='conpanyName']").val(conpanyName);
                $("input[name='conpanySName']").val(conpanySName);
                $("input[name='conpanySAddress']").val(conpanySAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactTel']").val(contactTel);
                var pid = $("#provinceId").val();
                var cid = $("#cityId").val();
                var did = $("#districtId").val();
                console.log(pid,cid,did)
                $("#updateRoadDetail").val($("#addrDetail").val());
                $.get("${bath}/web/api/common/provincies",function(data){
                    if( data.response == 'success' ){
                        data.data.map(function( object ){
                            var html = '';
                            html += '<option value="' + object.provinceId +'">';
                            html += object.provinceName;
                            html += '</option>';
                            $("#updateAddressP").append(html);
                        });

                        $("#updateAddressP option[value='" + pid + "']").prop("selected","selected");

                        if(pid){
                            updateAddressP(function(){
                                $("#updateAddressC option[value='" + cid + "']").prop("selected","selected");
                            },pid);
                        }
                        if(cid){
                            updateAddressC(function(){
                                $("#updateAddressD option[value='" + did + "']").prop("selected","selected");
                            },cid);
                        }

                    }
                },'json');



                $(".cList input").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide();
                });
                $("#updateRoadDetail").css({"border": "1px solid #ccc"}).prop("readonly", "");
                conpanyAddress = $(this).val();
                $(this).parent("div").show().siblings("input").css({"border": "none"});
                $(this).css({"border": "1px solid #ccc"}).siblings().children("a").show();
                $(this).css({"border": "1px solid #ccc"}).siblings("div").show().siblings(".imgshow").hide();
            });
            $("input[name='conpanyAddress']").siblings().children(".confirm").click(function () {
                    var addPV = $("#updateAddressP").val();
                        if( addPV=="0"){
                            addPV="";
                        }
                    var addCV = $("#updateAddressC").val();
                        if( addCV=="0"){
                            addCV="";
                        }
                    var addDV = $("#updateAddressD").val();
                        if( addDV=="0"){
                            addDV="";
                        }
                    var addDetail = $("#updateRoadDetail").val();
                if(addPV&&addCV&&addDV&&addDetail){
                        $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
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
                    response_ensure_alert("error","请完善联系地址",function(){

                    })
                }
                });


                $("input[name='contactTel']").siblings(".imgshow").click(function(){
                    $("input[name='contactTel']").trigger("focus");
                })
            $("input[name='contactTel']").focus(function () {
                $("input[name='conpanyName']").val(conpanyName);
                $("input[name='conpanySName']").val(conpanySName);
                $("input[name='conpanySAddress']").val(conpanySAddress);
                $("input[name='contactMan']").val(contactMan);
                $("input[name='conpanyAddress']").val(conpanyAddress);
                $("input[name='contactTel']").val(contactTel);
                $(".cList input").each(function(){
                    $(this).css({"border": "none"}).prop("readonly", "readonly").siblings("a,div").hide().siblings(".imgshow").show();
                })
                contactTel = $(this).val();
                $(this).css({"border": "1px solid #ccc"}).prop("readonly", null).siblings("a").show().siblings(".imgshow").hide();
            });
            $("input[name='contactTel']").siblings(".confirm").click(function () {
                var linkMobile=$("input[name='contactTel']").val();
                if(/^((0\d{2,3})-()\d{7,8})?$/.test(linkMobile)){
                if(linkMobile!==contactTel){
                    $.post("${bath}/web/api/accountcenter/editEnterPriseInfo", {
                        mobile: linkMobile
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
                    data_type_alert("座机号码不正确","error")
                }

                });

            $.get("${bath}/web/api/common/provincies", function (data) {
                if (data.response == 'success') {
                    data.data.map(function (object) {
                        var html = '';
                        html += '<option value="' + object.provinceId + '">';
                        html += object.provinceName;
                        html += '</option>';
                        $("#newMemAddressP").append(html);
                    });
                }
            }, 'json');

            $("#newMemAddressP").change(function () {
                $("#newMemAddressC option[value!='0'],#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
                $.post("../web/api/common/cities", {
                    "provinceId": $(this).val()
                }, function (data) {
                    if (data.response == 'success') {
                        data.data.map(function (object) {
                            var html = '';
                            html += '<option value="' + object.cityId + '">';
                            html += object.cityName;
                            html += '</option>';

                            $("#newMemAddressC").append(html);
                        });
                    }
                }, 'json');
            });
            $("#newMemAddressC").change(function () {
                $("#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
                $.post("../web/api/common/districts", {
                    "cityId": $(this).val()
                }, function (data) {
                    if (data.response == 'success') {
                        data.data.map(function (object) {
                            var html = '';
                            html += '<option value="' + object.districtId + '">';
                            html += object.districtName;
                            html += '</option>';
                            $("#newMemAddressD").append(html);
                        });
                    }
                }, 'json');
            });
            $("#newMemAddressD").change(function () {
                $("#newMemAddressR option[value!='0']").remove()
                $.post("../web/api/common/streets", {
                    "districtId": $(this).val()
                }, function (data) {
                    if (data.response == 'success') {
                        data.data.map(function (object) {
                            var html = '';
                            html += '<option value="' + object.streetId + '">';
                            html += object.streetName;
                            html += '</option>';
                            $("#newMemAddressR").append(html);
                        });
                    }
                }, 'json');
            });


            $.get("${bath}/web/api/common/provincies", function (data) {
                if (data.response == 'success') {
                    data.data.map(function (object) {
                        var html = '';
                        html += '<option value="' + object.provinceId + '">';
                        html += object.provinceName;
                        html += '</option>';
                        $("#updateAddressP").append(html);
                    });
                }
            }, 'json');

            $("#updateAddressP").change(function () {
                $("#updateAddressC option[value!='0'],#updateAddressD option[value!='0'],#updateAddressR option[value!='0']").remove()
                $.post("../web/api/common/cities", {
                    "provinceId": $(this).val()
                }, function (data) {
                    if (data.response == 'success') {
                        data.data.map(function (object) {
                            var html = '';
                            html += '<option value="' + object.cityId + '">';
                            html += object.cityName;
                            html += '</option>';

                            $("#updateAddressC").append(html);
                        });
                    }
                }, 'json');
            });
            $("#updateAddressC").change(function () {
                $("#updateAddressD option[value!='0'],#updateAddressR option[value!='0']").remove()
                $.post("../web/api/common/districts", {
                    "cityId": $(this).val()
                }, function (data) {
                    if (data.response == 'success') {
                        data.data.map(function (object) {
                            var html = '';
                            html += '<option value="' + object.districtId + '">';
                            html += object.districtName;
                            html += '</option>';
                            $("#updateAddressD").append(html);
                        });
                    }
                }, 'json');
            });
            $("#updateAddressD").change(function () {
                $("#updateAddressR option[value!='0']").remove()
                $.post("../web/api/common/streets", {
                    "districtId": $(this).val()
                }, function (data) {
                    if (data.response == 'success') {
                        data.data.map(function (object) {
                            var html = '';
                            html += '<option value="' + object.streetId + '">';
                            html += object.streetName;
                            html += '</option>';
                            $("#updateAddressR").append(html);
                        });
                    }
                }, 'json');
            });
            }


        })

        function updateAddressP(callback,pid){
            $("#updateAddressC option[value!='0'],#updateAddressD option[value!='0'],#updateAddressR option[value!='0']").remove()
            $.post("../web/api/common/cities",{
                "provinceId":pid
            },function(data){
                if( data.response == 'success' ){
                    data.data.map(function(object){
                        var html = '';
                        html += '<option value="' + object.cityId +'">';
                        html += object.cityName;
                        html += '</option>';
                        $("#updateAddressC").append(html);
                    });
                    callback();
                }
            },'json');
        }

        function updateAddressC(callback,cid){
            $("#updateAddressD option[value!='0'],#updateAddressR option[value!='0']").remove()
            $.post("../web/api/common/districts",{
                "cityId":cid
            },function(data){
                if( data.response == 'success' ){
                    data.data.map(function(object){
                        var html = '';
                        html += '<option value="' + object.districtId +'">';
                        html += object.districtName;
                        html += '</option>';
                        $("#updateAddressD").append(html);
                    });
                    callback();
                }
            },'json');
        }

        function updateAddressD(callback,did){
            $("#updateAddressR option[value!='0']").remove();
            $.post("../web/api/common/streets",{
                "districtId":did
            },function(data){
                if( data.response == 'success' ){
                    data.data.map(function(object){
                        var html = '';
                        html += '<option value="' + object.streetId +'">';
                        html += object.streetName;
                        html += '</option>';
                        $("#updateAddressR").append(html);
                    });
                    callback();
                }
            },'json');
        }


        function UpladFile(interval,domId) {
            var fileObj = document.getElementById(domId).files[0]; // 获取文件对象
            var FileController = "../web/uploadPicture";                    // 接收上传文件的后台地址
            var data = [];
            // FormData 对象
            var form = new FormData();// 可以增加表单数据
            form.append("file", fileObj);                           // 文件对象
            var xhr = new XMLHttpRequest();
            xhr.open("POST", FileController, true);
            // XMLHttpRequest 对象
            xhr.send(form);
            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4 && xhr.status == 200){
                    str = xhr.responseText;
                    data = JSON.parse(str);
                    if (data.response == 'success'){
                        $("#" + domId).prev().prop("src",data.data);
                        $("#" + domId).val("");
                        return true;
                    }
                    else{
                        response_ensure_alert('error',data.data.text,function(){
                            console.log("上传失败" + consoleNowTime());
                            $("#" + domId).val("");
                        });
                        return false;
                    }
                }
                else if( xhr.status == 400 ){
                    response_ensure_alert("error","上传失败",function(){
                        console.log("上传失败" + consoleNowTime());
                        $("#" + domId).val("");
                        return false;
                    });
                }
                clearInterval(interval);
            };
        }


    </script>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:800px;width: 100%;overflow-x: scroll;">
<input type="hidden" id="provinceId" value="${enterprise.provinceId}">
<input type="hidden" id="cityId" value="${enterprise.cityId}">
<input type="hidden" id="districtId" value="${enterprise.districtId}">
<input type="hidden" id="addrDetail" value="${enterprise.addrDetail}">
<div class="allheadstyle">
    <span>公司信息</span><a class="leftshanow" href="Account">账号信息</a><abbr></abbr>
</div>

<div class="cList">
    <ul>
        <li>
            <abbr>公司名称:</abbr>
            <input name="conpanyName" class="inputval" type="text" value="${enterprise.enterpriseName}" readonly="readonly">
            <i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i>
            <a class="confirm" >修改</a>
            <a class="cancel" >放弃</a>
        </li>
        <li>
            <abbr>公司简称:</abbr>
            <input name="conpanySName" class="inputval" type="text" value="${enterprise.profile}" readonly="readonly">
            <i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i>
            <a class="confirm">修改</a>
            <a class="cancel">放弃</a>
        </li>
        <li>
            <abbr id="img">公司logo:</abbr>
            <span>
                <img src="${enterprise.imgUrl}" width="128" height="128" />
                <#if flag == "true"><br />*Logo大小：128*128像素</#if>
            </span>
            <#if flag == "true">
                <br>
                <input class="allseachButton" id="conpanyLogoUpload" type="button" style="margin-left:153px; margin-top:10px;width: 80px; height: 30px;background:#54a6de;color: #fff" value="上传logo">
            </#if>

        </li>
        <li style="height: 30px;"><abbr>公司注册地址:</abbr><input name="conpanySAddress" class="inputval" type="text" value="${enterprise.registerAddress}" readonly="readonly">
            <i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i>
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
        <li><abbr>公司联系人:</abbr><input maxlength="10" name="contactMan" class="inputval" type="text" value="${enterprise.linkman}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i><a class="confirm">修改</a><a class="cancel">放弃</a>
        </li>
        <li style="height: 30px"><abbr>公司联系地址:</abbr><input name="conpanyAddress" class="inputval" type="text" value="${enterprise.address}" readonly="readonly">
            <i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i>
            <div style="display:none ;" class="adddressChange allpop">
                <h1>修改地址</h1>
                <select style="margin-left:40px;" name="newMemAddressP" id="updateAddressP">
                    <option value="0" selected="selected">选择省份</option>
                </select>
                <select name="newMemAddressC" id="updateAddressC">
                    <option value="0" selected="selected">选择城市</option>
                </select>
                <select name="newMemAddressD" id="updateAddressD">
                    <option value="0" selected="selected">选择区县</option>
                </select>
                <#--<select name="newMemAddressR" id="updateAddressR">-->
                    <#--<option value="0" selected="selected">选择街道</option>-->
                <#--</select>-->
                <input type="text" name="newMemberRoadDetail" id="updateRoadDetail" placeholder="详细地址" />
                <a class="confirm">修改</a><a class="cancel">放弃</a>
            </div>


        </li>
        <li><abbr>公司联系电话:</abbr><input maxlength="13" name="contactTel" class="inputval" type="text" value="${enterprise.mobile}" readonly="readonly"><i class="imgshow" style="display:inline-block; vertical-align:middle;height:24px; width: 80px;background:url(${bath}/static/img/edit_account.jpg) no-repeat right center;"></i><a class="confirm">修改</a><a class="cancel">放弃</a>
        </li>
    </ul>
</div>
<div class="allpop" id="upload">
    <h1>上传logo</h1>
    <ul>
        <li style="margin-top: 20px;margin-left: 20px"><img src="" width="150px" height="150px" id="companyLogo"><input id="LogoUpdate" type="file" style="margin-left:20px;width: 80px; height: 30px;"> </li>
        <li><input id="companyLogoCancel" style="margin-left: 80px;" class="button allcancelButton" type="button" value="取消" /><input id="companyLogoConfirm" style="margin-left: 20px" class="button allseachButton" value="确定" type="button"/></li>
    </ul>
</div>

<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
</html>
