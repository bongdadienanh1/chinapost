<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/main.css"/>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
<!-- 主要样式 -->
<link rel="stylesheet" href="${bath}/static/css/style.css">
<style type="text/css">
    @media screen and ( max-width: 1400px){
        #cardBody{
            transform: scale(0.8,0.8);
            transform-origin:  top;
            -webkit-transform: scale(0.8,0.8);
            -webkit-transform-origin:  top;
            -ms-transform: scale(0.8,0.8);
            -ms-transform-origin:  top;
            -moz-transform: scale(0.8,0.8);
            -moz-transform-origin:  top;
        }
    }
    #countDetail{
        width: 300px;
        height: 200px;
        background: #fff;
        position: absolute;
        right: 0px;
        top: 30px;
        box-shadow: 0px 1px 3px 0px #ccc;
        display: none;

    }
    body{
        width: 100%;
        background: #edf3f8;
    }
    #countDetail li{
        margin-top: 20px;
    }
    #countDetail li abbr{
        display:inline-block;
        vertical-align: top;
        margin-top: 10px;
        width: 150px;
        color: #333;
        height: 20px;
    }
    #countDetail li abbr:nth-child(3){
        position: relative;
        left: 125px;
        top: -60px;
    }

    #countDetail li img{
        margin-left: 20px;
        box-shadow: 0px 0px 3px 0px #ccc;
    }
    #logout{
        width: 120px;
        height: 35px;
        background: #54a6de;
        color: #fff;
        margin-left: 90px;
        margin-top: -10px;
        border:1px solid transparent;
    }
    #cardBody{
        width:97%;
        height: 1200px;
        margin: auto;
        margin-top: 20px;
        background: #fff;
    }
    #cardBody2{
        width: 600px;
        margin: auto;
        padding-top: 15%;
    }
    #idCard{
        width: 350px;
        height: 50px;
        font-size: 24px;
        border-radius: 3px;
        border: 1px solid #e5e5e5;
        color: #999;
    }
    #confirm{
        width: 120px;
        height: 50px;
        border-radius: 3px;
        border: 1px solid #54a6de;
        color: #fff;
        background: #54a6de;
        margin-left: 20px;
        display: inline-block;
        vertical-align: top;
        text-align: center;
        line-height: 50px;
    }
    ::-webkit-input-placeholder { /* WebKit browsers */
        color: #bbb;
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color: #bbb;
    }
    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color: #bbb;
    }
    :-ms-input-placeholder { /* Internet Explorer 10+ */
        color: #bbb;
    }
</style>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/pouchdb.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".users").mouseover(function(){
               $("#countDetail").show()
            })
            $(".users").mouseout(function(){
                $("#countDetail").hide()
            })
            $(".news img").mouseover(function(){
                $(this).prop("src","${bath}/static/img/com_btn_news_hover.png")

            })
            $(".news img").mouseout(function(){
                $(this).prop("src","${bath}/static/img/ring.png")

            })
            $(".news img").mousedown(function(){
                $(this).prop("src","${bath}/static/img/com_btn_news_pressed.png")
            })
            $(".news img").mouseup(function(){
                $(this).prop("src","${bath}/static/img/com_btn_news_hover.png")
            })
            var height=document.documentElement.clientHeight;
            $(".mainleft").css("height",height-50);
            $(".mainright").css("height",height-50);
            $(window).resize(function(){
                var height=document.documentElement.clientHeight;
                $(".mainleft").css("height",height-50);
                $(".mainright").css("height",height-50);
            })
        })

    </script>

<title>邮豆</title>
</head>

<body style="overflow-y: hidden;">
<div id="cover"  style="width: 12%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<div id="cover2"  style="width: 100%; height: 54px; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 12%; top: 0px;"></div>
<div class="topNews">
    <span style="display: inline-block; vertical-align: middle;width: 200px; height: 54px; line-height: 54px; font-size: 24px;margin-left: 50px;">邮豆系统 V ${VERSION}
</span>
    <div class="spanright">

    <#--消息模式-->
	<#--<span class="news"><img style="margin-right:10px; width:25px; height:25px;" src="${bath}/static/img/ring.png" width="20" height="20" />消息<i>1</i>-->
    <#--</span>-->

    <span class="users"><img style="margin-right:10px; width:25px; height:25px;" src="${enterprise.imgUrl}" width="20" height="20" />${enterpriseInfo.enterpriseName}<i></i>
        <div id="countDetail">
            <ul>
                <li><img src="${enterprise.imgUrl}" width="100" height="100" />
                    <abbr>${enterpriseInfo.enterpriseName}</abbr>
                    <abbr>${enterpriseManager.username}</abbr>
                </li>
                  <form action="${bath}/web/logout" method="post">
                  <input id="logout" type="submit" value="退出账号">
              </form>
            </ul>

        </div>
    </span>
    </div>
    <div id="cardBody">
        <div id="cardBody2">
        <input maxlength="18" type="text" id="idCard" placeholder="用户身份证号"> <input type="hidden" value="${bath}" id="url_head" /><a id="confirm" target="_blank">邮豆查询</a>
        </div>

    </div>
</div>
</body>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#idCard").blur(function(){
            var idCard = $("#idCard").val();
            $.post("../web/api/common/isNew",{
                idCard:idCard
            },function(data){
                if(data.response == 'success'){
                    if(data.data == false){
                      $("#confirm").attr("href","memberCheckDetail?idCard=" + idCard)
                    }else{
                        $("#confirm").removeAttr("href")
                        response_ensure_alert("error","该用户不存在",function(){
                            $("#idCard").val("")
                        },function(){
                            $("#idCard").val("")
                        })
                    }
                }
            },'json')

        });
        $("#confirm").click(function(){
            $("#idCard").val("")
            window.location.reload()
        })
    })
</script>
</html>
