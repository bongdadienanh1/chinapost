<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">

    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        table{
            width:700px;
            margin-top: 30px;
            opacity: 0.7;
        }
        table thead tr{
            height:40px;
        }
        table thead th{
            text-align: left;
            padding-left: 10px;
            background-color: #fff;
            border:none;
            height: 50px;
            color: #333;
            border-radius: 3px;
            font-size: 18px;
        }
        table tbody tr{
            height:60px;
        }
        table tbody tr td{
            height:60px;
            font-size:12px;
            font-weight: bold;
        }
        table td{
            padding-left: 10px;
        }
        table tbody tr td img{
            width:50px;
            height:50px;
            vertical-align: middle;
        }
        table tbody tr td abbr{
            margin-left: 20px;
        }

        .header{
            margin-top: 27px;
            color:#666666;
            font-size: 32px;
            font-weight: bold;
        }
        .loading{
            text-align: center;
            font-size:18px;
            font-weight: bold;
            width:700px;
            height:40px;
            line-height: 40px;
            background-color: #f2f9f9;
        }
        .table_list th,.table_list td{
            border-bottom: 1px solid #e5e5e5;
        }
        .table_list th{
            background: #edf3f8;
        }
    </style>
</head>

<body style="background: transparent!important;">
<div class="header">邮豆明细</div>
<input type="hidden" value="${userId}">
<table  cellpadding="0" cellspace="0" class="table_list" style="margin-top: 15px;">
    <thead>
        <th>交易时间</th>
        <th>交易对象</th>
        <th>交易理由</th>
        <th>交易金额</th>
    </thead>
    <tbody>

    </tbody>
</table>
<script src="${bath}/static/js/UbaoList.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
</script>
</body>
</html>
