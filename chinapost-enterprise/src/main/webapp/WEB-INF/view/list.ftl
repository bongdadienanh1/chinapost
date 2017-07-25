<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css">
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css?version=${VERSION}">

    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jquery.pagination.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
</head>
<style>
    @media screen and ( max-width: 1400px){
        .table_list{
            transform: scale(0.99,0.99);
            transform-origin: left top;
            -webkit-transform: scale(0.99,0.99);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.99,0.99);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.99,0.99);
            -moz-transform-origin: left top;
        }
    }
    .showError{
        color:#ff3300!important;
        position:relative;
    }
    #autoWarning{
        width: auto;
        height: auto;
        z-index: 2;
        position: fixed;
        display: none;
    }
    #arrow{
        width: 16px;
        height: 16px;
        z-index: 3;
        position: relative;
        left: 20px;
        top: -2.5px;
        background:url(${bath}/static/img/warningArrow.png) center no-repeat;
    }
    #massageShow{
        padding: 10px;
        border: 2px solid #666;
        z-index: 2;
        background: #fff;
    }
    .importLog{
        width:680px;
        height:680px;
        position:fixed;
        left:15%;
        top:15%;
        background:#FFF;
        box-shadow: 5px 5px 5px 5px #999;
        z-index:99;
        display:none;
    }
    .importLog ul{
        width: 650px;
        margin-left: 15px;
        border-top:1px solid #e0e0e0;
        padding-top:50px;
        overflow-x: hidden;
        overflow-y: scroll;
    }
    .importLog li{
        height:40px;
        margin-top: 14px;
    }
    .importLog li div{
        font-size: 14px;
        text-align:left;
        margin-left: 80px;
    }
    .importLog2{
        width:680px;
        height:auto;
        position:fixed;
        left:15%;
        top:15%;
        background:#FFF;
        box-shadow: 5px 5px 5px 5px #999;
        z-index:99;
        display:none;
    }
    .importLog2 ul{
        width: 650px;
        height: 400px;
        margin-left: 15px;
        border-top:1px solid #e0e0e0;
        padding-top:50px;
        overflow-x: hidden;
        overflow-y: scroll;
    }
    .importLog2 li{
        height:40px;
        margin-top: 14px;
    }
    .importLog2 li div{
        font-size: 14px;
        text-align:left;
        margin-left: 80px;
    }

    #xx{
        display:inline-block;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        width:25px;
        height:25px;
        cursor:pointer;
    }
    .leftshanow{
        width: 100%;
        height: 100%;
        z-index: 1;
        color:#54b3e3!important;
        background-color: #fff;
        border:none!important;
        box-shadow: none!important;
        margin-left: 20px;
        font:  20px '黑体';
    }
    .table_list th:nth-child(3){
        width: 150px!important;
    }
    .disabled{

    }
    #Pagination{
        margin: 20px 0px;
    }
</style

<body style="">
<div style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:1000px;width: 100%;overflow-x: scroll;">
    <div style="width:100%; height:70px;line-height:70px;color:#333333;font-size:16px;">
        <#--<a style="box-shadow:-3px -2px 3px 0px #dedede inset;font:20px '黑体'; background: #f2f2f2; color:#b2b2b2; display:inline-block; width: 10%;line-height: 70px;text-indent: 30px;" href="UbaoSend">单个转账</a>-->

        <#--<span style="font:20px '黑体';text-indent: 30px; color:#2c97de; display:inline-block; line-height: 70px; ">批量转账</span>-->
        <a class="leftshanow" href="piliangdaoru" style="line-height: 70px;">批量导入</a>
    </div>




<div class="containe">
    <button class="re_import allclickButton">重新导入</button>
    <div id="notice">通过Excel导入，实现批量发放。具体发放到用户身份证号对应的账户中，会员来网点激活账户，使用邮豆消费。一次最多只能导入两万条</div>

    <div class="row">
        <div style="text-align:left;font-size:20px;margin-top:2%;font-weight:bold;margin-left:40px;"><abbr id="listTitle"></abbr>发放列表(<span id="num"></span>)</div>
    </div>
</div>
<hr style="width:98%;border-bottom:2px solid #dcdcdc;margin-left:22px;margin-top:40px;"/>
<input type="hidden" id="typeId" value="${typeId}" />
<table class="table_list" style="width:98%;margin-left:22px;font-size:15px;font-weight:550;border:0;">
    <thead>
    <tr style="background-color:#d6d6d6;" class="thead">
        <script type="text/javascript">
//            //禁止后退键 作用于Firefox、Opera
//            document.onkeypress = forbidBackSpace;
//            //禁止后退键  作用于IE、Chrome
//            document.onkeydown = forbidBackSpace;
            var key =${key};

            $.post("../web/api/ucoingrand/getExcelData",{
                key:key,
                page:1,
                size:5
            },function(data){
                if(data.response == "success"){
                    $('.thead').append('<th>行数</th>');
                    data.data.head.map(function(object){
                        //填充数据
                        var html = '<th>' + object + '</th>';
                        $('.thead').append(html);
                    });
                    for(var i = 0;i < data.data.content.content.length;i++){
                        var html = '<tr>';
                        html += '<td>' + (i+1) + '</td>';
                        data.data.content.content[i].map(function(object){
                            //填充数据
                            if(object == null){
                                html += '<td class="">'+ ''+'</td>';
                            }else{
                                html += '<td class="">' + object + '</td>';
                            }
                        });
                        html += '</tr>';
                        $('#itemContainer').append(html);
                    }
                    $("#num").html(data.data.content.totalElements);
                    //定位并加入错误信息
                    var trCount = 0;
                    var errLogHtml = '';
                    data.data.error.map(function(arr){
                        if(arr.length != 0){
                            for( var i = 0;i < arr.length;i++ ){
                                var args = arr[i].split("^_^");
                                var index = handleAZ(args[1]);
                                var errorContent = args[1] + args[2];

                                //显示错误日志
                                errLogHtml += '<li>';
                                errLogHtml += '<div class="errorContent">' + '第' + ( args[0] ) + '行错误:' + errorContent + '</div>';
                                errLogHtml += '</li>';
                            }
                        }
                        trCount++;
                    });

                    $("#Pagination").pagination(data.data.content.totalElements,{
                        prev_text: "＜",
                        next_text: "＞",
                        num_edge_entries: 2,
                        num_display_entries: 8,
                        items_per_page:5,
                        callback: function(page){
                            $.post("../web/api/ucoingrand/getExcelData",{
                                key:key,
                                page:page+1,
                                size:5
                            },function(data){
                                if(data.response == 'success'){
                                    $("#itemContainer").empty();
                                    for(var i = 0;i < data.data.content.content.length;i++){
                                        var html = '<tr>';
                                        html += '<td>' + ((parseInt(page)) * 5 + i + 1) + '</td>';
                                        data.data.content.content[i].map(function(object){
                                            //填充数据
//                                            html += '<td class="">' + object + '</td>';
                                            if(object==null){
                                                html += '<td class="">'+ ''+'</td>';
                                            }else{
                                                html += '<td class="">' + object + '</td>';
                                            }
                                        });
                                        html += '</tr>';
                                        $('#itemContainer').append(html);
                                    }
                                    //定位并加入错误信息
                                    var trCount = 0;
                                    var errLogHtml = '';
                                    data.data.error.map(function(arr){
                                        if(arr.length != 0){
                                            for( var i = 0;i < arr.length;i++ ){
                                                var args = arr[i].split("^_^");
                                                var errorContent = args[1] + args[2];

                                                //显示错误日志
                                                errLogHtml += '<li>';
                                                errLogHtml += '<div class="errorContent">' + '第XXX' + args[0] + '行错误:' + errorContent + '</div>';
                                                errLogHtml += '</li>';
                                            }
                                        }
                                        trCount++;
                                    });
                                }
                            },'json');
                        }
                    });
                    if( errLogHtml == '' ){
                        $(".importLog").hide()
                    }else{
                        $(".importLog").show()
                    }
                    $(".importLog ul").append(errLogHtml);

                    //列表总数
                    var num="0";

                    $(".showError").each(function(){
                        if($(this).html()== ""){
                            $(this).html("- -")
                        }
                    });
                    $(".showError").mouseover(function(){
                        $("#autoWarning").show()
                        var left = window.event.clientX
                        var top = window.event.clientY
                        var text = $(this).attr("value");
                        $("#massageShow").html(text);
                        $("#autoWarning").css({left:left,top:top-50});
//                    }
                    });
                    $(".showError").mouseout(function(){
                        $("#autoWarning").hide()
                        $("#myCanvas").remove();
                    });
                }else{

                }



            },'json')


        </script>
    </tr>
    </thead>
    <tbody id="itemContainer">
        <script type="text/javascript">
            sessionStorage.setItem("ShoppingCart",'');
            $(document).ready(function(){
                //移动窗口
                $("#backP").click(function(){
                    window.location.href = "piliangdaoru"
                })
                $( ".importLog" ).draggable();
                $( ".importLog2" ).draggable();
                $("#xx").click(function(){
                    discoverHtml()
                   $(".importLog").css("display","none");
                });
                $(".importLog2 h1 i").click(function(){
                    console.log(1111111)
                    $(".pwd").hide()
                    $(".importLog2").hide()
                })
                //填充信息
//                含业务类型
//                for(var i = 1;i < data.data.excel[1].length;i++){
//                    var html = '<tr>';
//                    data.data.excel[1][i].map(function(object){
//                        //填充数据
//                        html += '<td class="">' + object + '</td>';
//                    });
//                    html += '</tr>';
//                    $('#itemContainer').append(html);
//                }
//                //定位并加入错误信息
//                var trCount = 0;
//                var errLogHtml = '';
//                data.data.excel[2].map(function(arr){
//                    if(arr.length != 0){
//                        for( var i = 0;i < arr.length;i++ ){
//                            var args = arr[i].split("^_^");
//                            var index = args[0];
//                            var errorContent = args[1];
//                            $($($("tbody tr")[trCount]).children()[index]).addClass("showError");
//                            $($($("tbody tr")[trCount]).children()[index]).attr("value",errorContent);
//
//                            //显示错误日志
//                            errLogHtml += '<li>';
//                            errLogHtml += '<div class="errorContent">' + '第' + ( parseInt(trCount) + 1 ) + '行错误:' + errorContent + '</div>';
//                            errLogHtml += '</li>';
//                        }
//                    }
//                    trCount++;
//                });



//              不含业务类型

                //绘制canvas

            });
        </script>
    </tbody>
</table>
    <div id="Pagination" class="allcpageTurnButton"></div>
  <span class="buttonGroup" style="display:inline-block;">
    <button class="ensure allseachButton" id="ensure">确定转账</button>
    <button class="cancel allcancelButton" id="cancel">取消</button>
  </span>
<div class="pwd allpop">
    <h1>输入支付密码<i style="width: 25px; height: 25px; background:url(${bath}/static/img/XX.png) center no-repeat;"></i></h1>
    <input type="password" name="pwd" placeholder="支付密码" id="list_password"/><br />
    <span style="" class="allseachButton" id="confirm">确认支付</span>
</div>

<div class="success">
    <span style="left:110px;" ><img src="${bath}/static/img/ok.png" width="40" height="40" />支付成功，<b>3</b>秒后返回邮豆发放页面</span>
    <a id="complete" class="allseachButton">完成</a>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<div id="autoWarning">
    <div id="massageShow"></div>
    <div id="arrow"></div>
</div>
<div class="importLog allpop">
    <h1>导入错误信息汇总<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul style="height:90%;"></ul>
</div>
    <div class="loading">
        <h1>邮豆正在发放中，请耐心等待.......</h1>
        <img width="250" height="150" src="${bath}/static/img/loading3.gif">
    </div>

    <div class="importLog2 allpop">
        <h1>错误<i onclick="discoverHtml()" id="xx"></i></h1>
        <ul></ul>
        <div style="height: 80px">
            <input id="backP" class="allseachButton" style="background:#e9e9e9!important; margin-left: 200px;color: #999!important;" type="button" value="返回重新发放">
            <input id="continue" style="margin-left: 30px" class="allseachButton" type="button" value="继续发放">
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        var business_type = ${business_type}
        $("#cancel").click(function(){
            window.location.href = 'piliangdaoru';
        })
        $("#confirm").click(function(){
            $(".pwd").fadeOut(200);
            var pwd = $("#list_password").val();
            //var infoJson = JSON.stringify(data.data);
            //含业务类型
            //var typeId = $("#typeId").val();
            $("#cover1").show();
            $(".loading").show();
            //$.post("../web/api/ucoingrand/batchGrand",{
            $.post("../web/api/ucoingrand/newBatchGrand",{
                //含业务类型
                //typeId:typeId,
                paykey:pwd,
                key:key,
                type:business_type
            },function(data){
                if( data.response == 'success' ){
                    $(".success").fadeIn(400);
                    $("#cover1").hide();
                    $(".loading").hide();
                    var i = 3;
                    setInterval(function(){
                        i--;
                        if( i >= 0 ){
                            $(".success").find("span").find("b").html(i)
                        }
                        else{
                            clearInterval(this);
                            discoverHtml();
                            window.location.href = "piliangdaoru";
                        }
                    },1000);
                } else{
                    $(".pwd").fadeIn(200);
                    $("#cover1").hide();
                    $(".loading").hide();
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');
        });
        $("#ensure").click(function(){
            var length = $(".importLog ul li").length;
            if( length == 0 ){
                $.post("../web/api/ucoingrand/checkBalance",{
                    key:key,
                    type:business_type
                },function(data){
                    if( data.response == 'success' ){
                        $(".pwd").fadeIn(200);
                    }
                    else if( data.data.errorcode == 19 ){
                        $(".importLog2").show();
                        $("#cover1").hide();
                        $(".loading").hide();
                        var errLogHtml2 =""
                        var count =1
                        var arg = data.data.text
                        console.log(arg)
                        var index = arg.split(",")
                        index.map(function(arr){
                            var args = arr.split("=")
                            errLogHtml2 += '<li>';
                            errLogHtml2 += '<div>'+ '<b>' + count + '</b>' + ":" + args[1].replace("}","") + '</div>';
                            errLogHtml2 += '</li>';
                            count++
                        })
                        $(".importLog2 ul").empty().append(errLogHtml2);
                        $("#continue").click(function(){
                            $(".importLog2").hide();
                            $(".pwd").fadeIn(200);
                        })
                    }
                },'json');
            }
            else{
                coverHtml();
                $(".importLog").fadeIn();
            }
        });
    })
</script>
<script src="${bath}/static/js/list.js?version=${VERSION}"></script>