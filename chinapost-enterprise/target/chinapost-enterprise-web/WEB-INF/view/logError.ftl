<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>

    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        .container{
            position: absolute;
            top:20%;
            left:25%;
            width:50%;
            height:500px;
            margin: 0 auto;
            background:url(${bath}/static/img/com_pic_login_failure.png) center no-repeat;
            background-size: 100%;
        }
        .content{
            position: relative;
            top:34%;
            text-align: center;
            font-size: 24px;
        }
        .content a{
            display: block;
            margin-top: 15px;
            font-size:20px;
            color:#b4acac;
        }
        .content a:hover{
            text-decoration: underline;
            color:#999;
        }
        #time{
            margin-left: 8px;
            font-size: 40px;
            color:#008738;
            margin-right: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="content">
            <div>登陆失效,请重新登录,<abbr id="time">3</abbr>秒钟后跳回首页</div>
            <a>若浏览器未自动跳转，请点击此链接</a>
        </div>
    </div>
    <script type="text/javascript">
        var time = $("#time").html();
        console.log(time);
        var timeOut = setInterval(function(){
            console.log(time);
            if( time > 0 ){
                time = time - 1;
                $("#time").html(time);
            }
            else{
                clearInterval(timeOut);
                window.parent.window.location.href = 'login.htm';
            }
        },1000);
        $("a").click(function(){
            clearInterval(timeOut);
            window.parent.window.location.href = 'login.htm';
        })
    </script>
</body>
