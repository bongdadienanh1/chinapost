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
            background:url(${bath}/static/img/com_pic_outlets-please.png) center no-repeat;
            background-size: 100%;
        }
        .content{
            position: relative;
            top:54%;
            text-align: center;
            font-size: 24px;
        }
        .content a{
            display: block;
            margin-top: 15px;
            font-size:40px;
            color:#24b35f;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="content">
        <div>抱歉 工程师正在紧急开发中 </div>
        <a>敬请期待！</a>
    </div>
</div>
</body>
