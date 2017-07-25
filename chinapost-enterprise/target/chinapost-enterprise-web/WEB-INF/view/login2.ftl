<#assign bath = request.contextPath>
<head>
    <meta charset="utf-8" />
    <title>邮宝系统_登录界面</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css">
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css">
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script>
<script type="text/javascript">alert("您的浏览器版本过低，有些功能无法使用，建议您使用IE9及以上版本浏览器，体验更加")</script>
    <![endif]-->
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script src="${bath}/static/js/common.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        input,button,select,textarea{outline:none}
    </style>
</head>
<body style="background:url(${bath}/static/img/login_pic_big_bg.png) center no-repeat;background-size: 100%;">
<div id="index_head">
    <span class="left">
      <img src="${bath}/static/img/login_icon_youbao.png" />
      邮宝系统
    </span>
    <span class="right">
      <img src="${bath}/static/img/login_icon_tel.png" />
      <a>客服电话<br /><abbr style="font-size: 24px; opacity: 1;">400-966-1900000</abbr></a>
    </span>
</div>
<div class="container">
    <div id="diaoshen"></div>
    <form action="${bath}/web/login" class="loginForm" method="post" >
        <div class="form_head"><span class="button_login">欢迎登陆</span><span style="margin-left: 50px;" class="button_search">邮宝查询</span></div>
        <div class="Ubao_login" style="padding-bottom: 5%;">
            <div class="form_user">
                <img src="${bath}/static/img/login_icon_account.png" />
                <input type="text" placeholder="账号" name="username"/>
            </div>

            <div class="form_password">
                <img src="${bath}/static/img/login_icon_password.png" />
                <input type="password" placeholder="密码" name="password"/>
            </div>

            <div class="form_captcha" style="display: none;">
                <img src="${bath}/static/img/login_icon_verification.png"  />
                <input style="width: 120px;height: 60px;" type="text" placeholder="验证码" name="capt" />
                <img class="captcha" src="../web/captcha"/>
            </div>



            <div class="form_submit">
                <input type="hidden" value="${bath}" id="url_head" />
                <input style="margin-left: -72px; width:100%;height: 50px; box-shadow: 0px 3px 5px 0px #666; cursor: pointer;" type="button" value="登录" id="login_button"/>
            </div>
        </div>
        <div class="Ubao_Search" style="display: none;">
            <div class="form_user" style="margin-top: 80px;">
                <img style="width: 40px; height: 40px;" src="${bath}/static/img/login_icon_id2.png"  />
                <input style="width: 300px;" type="text" placeholder="身份证号" id="idCard"/>
            </div>
            <div class="form_submit">
                <input style="margin-left: -60px; width:500px;height: 60px; box-shadow: 0px 3px 5px 0px #666; cursor: pointer;" type="button" value="查询" id="UbaoSearch"/>
            </div>
        </div>
    </form>
</div>
<script src="${bath}/static/js/login.js"></script>
</body>
