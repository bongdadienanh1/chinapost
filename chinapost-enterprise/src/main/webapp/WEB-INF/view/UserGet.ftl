<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        @media screen and ( max-width: 1360px) {

        }
        body{
            background:#f7f7f7;
        }

        a{
            color:#20abf6;
        }
        .inPut{
            width:500px;
            height:200px;
            margin-left:20%;
            margin-top:10%
        }
        .inPut input{
            width:400px;
            height:50px;
            padding:10px;
            font-size:24px;
            border-radius:5px;
            background:#FFF;
            border:1px solid #cccccc;
            color:#000;
        }
        .inPut input#sub{
            width:150px;
            height:74px;
            position:relative;
            top:-74px;
            left:450px;
            border-style:none;
            color:#FFF;
            background:#54a6de;
            border-radius:5px;

        }

        input[placeholder]{color:#999;}


    </style>
    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:800px;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <a href="itemList">代客下单</a><span>网点提货</span><abbr></abbr>
    </div>


<div class="inPut">
    <input type="text" name="billNumber" placeholder="自提单提货码" />
    <input type="button" id="sub" value="下一步"/>
</div>



<script src="${bath}/static/js/UserGet.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
</html>
