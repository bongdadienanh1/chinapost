<#assign bath = request.contextPath>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css">
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css?version=${VERSION}">

    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <style>
        .container{
            background:#fff;
            background-size:100% ;
            color: #666666;
            width:800px;
            height:570px;
            padding: 15px;
        }
        iframe{
            width:700px;
            height:400px;
            border:none;
        }
        .header ul li ul li{
            margin-top: 10px;;
        }
        .mainhead{
            width: 100%;
            height: 80px;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            background:#1a7dc2;
        }
        .mainhead li img{
            margin-right: 24px;
            margin-top: -10px;
        }
        .mainhead li.left{
            height: 80px;
            line-height: 80px;
            position: absolute;
            left: 10%;
            font-size: 40px;
            color: #ffffff;
        }
        .mainhead li.right{
            height: 80px;
            line-height: 80px;
            position: absolute;
            right: 10%;
            font-size: 20px;
            color: #ffffff;
        }
        .mainhead li.right span{
            position: relative;
            top:13%;
            line-height: 30px;
        }

    </style>
</head>
<body style="background: #1d8bd8">

<div class="mainhead">
    <ul>
        <li class="left"><img src="${bath}/static/img/login_icon_youbao.png">邮豆系统</li>

        <li class="right"><img src="${bath}/static/img/login_icon_tel.png"><span style="display: inline-block;">客服电话<abbr style="font-size: 22px; display: block">400-966-1900</abbr></span></li>
    </ul>
</div>
<div style="width: 800px;height: 600px;margin:auto;background:url(${bath}/static/img/login_pic_big_bg.png) center no-repeat;">
<div class="container">
    <input type="hidden" value="${userId}" id="userId"/>
    <div class="header" style="position: relative;height:95px;">
        <ul>
            <li>
                <img src="${imgUrl}" style="width:75px;height:75px;position: absolute;top: 0px;left:50px;"/>
                <ul style="text-align:left;padding-left: 170px;font-size:15px;margin-top: 20px;">
                    <li>${idCard}</li>
                    <li>${customer.getFullname()}    ${customer.getContactPhone()}
                        <span style="float:right;margin-right: 60px;">邮豆余额</span>
                    </li>
                    <li>客户经理号：${customer.getManagerNo()}
                        <span style="float:right;margin-right: 60px;">
                            <span style="color:#ff3300;font-size:25px;">${customer.getTotalUcoin()?string("0.00")}</span>邮豆
                        </span>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <hr style="width:88%;"/>
    <iframe src="UbaoList?userId=${userId}"></iframe>
</div>
</div>
<script src="${bath}/static/js/login.js?version=${VERSION}"></script>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
</script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>