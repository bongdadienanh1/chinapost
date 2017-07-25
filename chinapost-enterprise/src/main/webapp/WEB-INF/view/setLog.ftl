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
            body{
                zoom:62.5%;
                font-size:10px!important;
            }
        }
        #logbody{
            margin-left: 20px;
            margin-top: 50px;
        }
        #logbody li{
            margin-top: 20px;
        }
        #logbody li span{
            display: inline-block;
            width: 80px;
        }
        #logbody li abbr{
            display: inline-block;
            width: 20px;
        }
        #logbody li input[type='text']{
            width: 400px;
            margin-right: 30px;
        }
    </style>



    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        $(document).ready(function(){
            $("#addLog").click(function(){
                var html = add()
                console.log(11111111)
                $("#logbody").append(html)
                for(i = 1 ; i <= $(".num").length ; i ++ ){
                    $(".num").eq(i-1).html(i+':')
                }
            })
            $(document).on("click","#del",function(){
                $(this).parent().remove()
                for(i = 1 ; i <= $(".num").length ; i ++ ){
                    $(".num").eq(i-1).html(i+":")
                }
            })
            $("#confirm").click(function(){
                var deliveryVersion = $("#logName").val();
                var deliveryInform = ""
                $(".logVal").each(function(){
                    deliveryInform += $(this).val() + "^_^"
                })
                deliveryInform = deliveryInform.substring(0,deliveryInform.length - 3)

                $.post("${bath}/web/deliveryInform/addDeliveryInform", {
                    deliveryVersion:deliveryVersion,
                    deliveryInform: deliveryInform
                }, function (data) {
                    if(data.response=="success"){
                        response_ensure_alert("success","成功")
                    }else{
                        data_type_alert(data.data.text,"error");
                    }

                }, "json")
            })
        })


        function add(){
            var html = ""
            html +=  '<li><span>更新内容:</span><abbr class="num">1</abbr><input class="logVal" type="text" id="logName"><input class="allclickButton" type="button" value="删除" id="del"> </li>'
            return html
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
    <span>更新日志</span><abbr class="leftshanow"></abbr>
</div>
<div>
    <ul id="logbody">
        <li><span>版本号:</span><input type="text" id="logName"> </li>
        <li><span>更新内容:</span><abbr class="num">1:</abbr><input class="logVal" type="text" id="logName"><input class="allseachButton" type="button" value="添加" id="addLog"> </li>
    </ul>
    <input class="allseachButton" style="margin-left: 20px; margin-top: 50px" type="button" value="确定" id="confirm">
</div>

<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
</html>
