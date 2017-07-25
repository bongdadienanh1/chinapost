<#assign bath = request.contextPath>
<head>
    <meta charset="utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- 主要样式 -->
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css">
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <link rel="stylesheet" href="${bath}/static/css/CheckSystem.css?version=${VERSION}">
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script>
<script type="text/javascript">alert("您的浏览器版本过低，有些功能无法使用，建议您使用IE9及以上版本浏览器，体验更加")</script>
    <![endif]-->
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script src="${bath}/static/js/login.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/CheckSystem.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>

    <style>@media screen and (max-width:1400px){
        .mainform,.mainhead li.right,.mainhead li.left{
            transform: scale(0.8,0.8);
            transform-origin:  top;
            -webkit-transform: scale(0.8,0.8);
            -webkit-transform-origin:  top;
            -ms-transform: scale(0.8,0.8);
            -ms-transform-origin:  top;
            -moz-transform: scale(0.8,0.8);
            -moz-transform-origin:  top;
        }
        .mainhead{
            height: 60px!important;
        }
        #loginTop{
            top: 30px!important;
            height: 30px!important;
        }
    }input,button,select,textarea{outline:0;autocomplete="off"}.mainform{margin:auto;width:560px;height:380px;position:relative;top:15%;border-radius:5px;background:#fff;background-size:100%}::-webkit-input-placeholder{color:#666;opacity:.7}:-moz-placeholder{color:#666;opacity:.7}::-moz-placeholder{color:#666;opacity:.7}:-ms-input-placeholder{color:#666;opacity:.7}.mainform ul{width:100%;height:36px}.mainform ul li{width:560px;height:50px;line-height:50px;font-size:24px;color:#999;font-family:宋体;float:left;text-align:center;background:#f6f6f6;font-weight:bold;box-shadow:0 -1px 3px 0 #666 inset;cursor:pointer}.mainform ul li.clickOn{color:#54a6de;box-shadow:none;background:#fff;height:53px;border-radius:3px}.mainform div.welcomelogin{width:480px;height:220px;margin-left:40px;margin-top:30px}.mainform div.welcomelogin dd{width:480px;height:48px;border:1px solid #e1e1e1;border-radius:6px;margin-bottom:24px;background:url(${bath}/static/img/login_pic_input-box_bg.png)}.mainform div.welcomelogin dd img{margin:10px 24px 10px 16px}.mainform div.welcomelogin dd input{width:395px;height:46px;border-style:none;color:#666;background:transparent;font-size:20px}#login_button{width:480px;height:48px;color:#fff;background:#54a6de;border-style:none;border-radius:6px;font-size:24px;font-family:宋体}.mainform div.ucoinSearch{width:480px;height:220px;margin-left:40px;margin-top:30px;display:none}.mainform div.ucoinSearch dd{width:480px;height:48px;border:1px solid #e1e1e1;border-radius:6px;margin:80px 0;background:url(${bath}/static/img/login_pic_input-box_bg.png);background-size:100%}.mainform div.ucoinSearch dd img{margin:10px 24px 10px 16px}.mainform div.ucoinSearch dd input{width:360px;height:46px;border-style:none;background:transparent;color:#666;font-size:20px}.UbaoSearch{display:block;width:480px;height:48px;color:#fff;background:#999;border-style:none;border-radius:6px;font-size:24px;font-family:宋体;line-height:48px;text-align:center}.ubaosearch_alert{float:right;margin-top:15px;font-size:24px;color:#666;white-space:nowrap}.mainhead{width:100%;height:80px;position:fixed;top:0;left:0;right:0;background:#1a7dc2}.mainhead li img{margin-right:24px;margin-top:-10px}.mainhead li.left{height:80px;line-height:80px;position:absolute;left:10%;font-size:40px;color:#fff}.mainhead li.right{height:80px;line-height:80px;position:absolute;right:10%;font-size:20px;color:#fff}.mainhead li.right span{position:relative;top:13%;line-height:30px}.mainfoot{width:95%;text-align:center;height:40px;text-align:center;line-height:40px;position:fixed;bottom:16px;font-size:20px;color:#fff}.backgroundbody{width:1024px;height:768px;margin:auto;margin-top:50px;background:url(${bath}/static/img/login_pic_big_bg.png) center no-repeat}</style>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){

    }
    #UbaoLogin_button{
        display: inline-block;
        margin-top: 15px;
        color: #999;
        width: 480px;
        height: 48px;
        line-height: 48px;
        border: 1px solid #999;
        border-radius: 6px;
        text-align: center;
    }
    #UbaoLogin_button:hover{
        color: #fff;
        background: #54a6de;
        border: 1px solid #54a6de;
    }
</style>
<body style="background:#1d8bd8;">
<div class="backgroundbody">
    <div id="loginTop" style="width:500px; height:150px; color:#fff; font-size:36px; text-align:center;margin: auto;position: relative; top:110px">欢迎使用邮豆系统</div>
    <div class="mainform">
        <ul>
            <li value="0" class="clickOn">欢迎登录</li>
        <#--<li value="1">邮豆查询</li>-->
        </ul>
        <div class="welcomelogin">
            <dl>
                <dd><img src="${bath}/static/img/login_icon_account.png">
                    <input class="lg_username" type="text" placeholder="账号">
                    <input class="val_username" type="hidden" readonly />
                </dd>
                <dd><img src="${bath}/static/img/login_icon_password.png">
                    <input maxlength="16" class="lg_password" onfocus="this.type='password'" autocomplete="off" placeholder="密码">
                    <input class="val_password" type="hidden" readonly />
                </dd>
                <dd class="form_captcha" style="width: 240px; margin-bottom: -20px;display: none;">
                    <img src="${bath}/static/img/login_icon_verification.png">
                    <input maxlength="5" name="capt" style="width: 150px" type="text" placeholder="验证码">
                </dd>
                <img style="position: relative; left: 280px; top: -30px;display: none;" class="captcha" src="../web/captcha"/>
            </dl>
            <div>
                <input type="hidden" value="${bath}" id="url_head" />
                <input type="button" value="登录" id="login_button"/>
                <input type="hidden" value="${bath}" id="url_head" />
                <a id="UbaoLogin_button">会员邮豆查询登录</a>
            </div>
        </div>


        <div class="ucoinSearch">
            <dl>
                <dd><img src="${bath}/static/img/login_icon_id.png">
                    <input maxlength="18" id="idCard" type="text" placeholder="身份证号">
                    <div>
                        <div class="ubaosearch_alert"></div>
                    </div>
                </dd>
            </dl>
            <div>
                <a class="UbaoSearch" href="javascript:void(0)" onclick="disabled()">查询</a>
            </div>
        </div>
    </div>


</div>

<div class="mainhead">
    <ul>
        <li class="left"><img src="${bath}/static/img/login_icon_youbao.png">邮豆系统</li>

        <li class="right"><img src="${bath}/static/img/login_icon_tel.png"><span style="display: inline-block;">客服电话<abbr style="font-size: 22px; display: block">400-966-1900</abbr></span></li>
    </ul>
</div>

<div class="mainfoot">
    Copyright YLife Technology —version ${VERSION}
</div>
<input type="hidden" id="loginUrl" value="login">
</body>