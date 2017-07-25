<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/main.css"/>
<!-- 主要样式 -->
<link rel="stylesheet" href="${bath}/static/css/style.css">
<style type="text/css">
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
    .mainleft{
        transition: all 1s;
    }
    .mainright{
        transition: all 1s;
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
            $(".mainleft").mouseenter(function(){
                $(this).css("width","12%")
                $(".mainright").css("width","88%")

            })
            $(".mainleft").mouseout(function(){
                $(this).css("width","2%")
                $(".mainright").css("width","98%")

            })
        })

    </script>

<title>邮豆</title>
</head>

<body style="overflow-y: hidden;">
<div id="cover"  style="width: 2%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<div id="cover2"  style="width: 100%; height: 54px; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 2%; top: 0px;"></div>
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
</div>
<iframe width="2%" class="mainleft" scrolling="no" name="mainleft" frameborder="0" src="mainleft.ftl">
</iframe>
<iframe width="98%"th="98%" class="mainright" style="overflow-x: hidden;overflow-y: scroll" name="mainright" frameborder="0" src="mainright.ftl">
</iframe>
</body>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>

</html>
