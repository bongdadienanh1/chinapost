<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css" />
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css?version=${VERSION}" />
    <!-- dateTimePicker控件css -->
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>

    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>


    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">


    <!-- jPage分页 -->
    <script src="${bath}/static/js/jPages.js"></script>


    <style>
        body{
            width:1600px;
        }
        th,td{
            border-radius: 0!important;
        }
        tbody tr:nth-child(2n-1){
            background-color: #f6f6f6!important;
        }
        tbody tr:nth-child(2n){
            background-color: white!important;
        }
        .sum{
            width:100%;
            height:50px;
            line-height:50px;
            background:#fcfce1;
            margin-left:20px;
            margin-top:20px;
            padding-left:30px;
        }
        .button_group{
            margin-top: 25px;
            width:1600px;
        }
        .button_group label{
            width:90px;
            height:35px;
            background-color: #f2f2f2;
            margin-left: 22px;
            line-height: 35px;
            text-align: center;
            border:1px solid #e0e0e0;
        }
        .button_group input{
            height:35px;
            border:1px solid #e0e0e0;
            margin-top: -4px;
            padding-left: 5px;
        }
        .button_group > input[type="button"]{
            background-color: #fcfcfc;
            width:75px;
            padding-left: 0;
            margin-left: 7px;
        }
        .button_group .search_button{
            border:none;
        }
        .button_group .date_button{
            display: inline-block;
            vertical-align: middle;
            margin-top: -4px;
            width:37px!important;
            margin-left: 0!important;
            height:35px;
            background-color: #f2f2f2;
            background-image: url("${bath}/static/img/date_img.png");
        }
        table{
            width:98%;margin-left:22px;font-size:15px;font-weight:550;border:0;margin-top: 25px;
        }
        .xdsoft_datetimepicker{
            width:310px!important;
        }
    </style>
</head>
<body>

<div class="sum">15000邮豆</div>
<div class="button_group">
    <label>选择账号</label><input type="text" style="width:130px;" placeholder="全部"/>
    <input type="button" value="今天" />
    <input type="button" value="本周" />
    <input type="button" value="上周" />
    <input type="button" value="本月" />
    <input type="button" value="上月" />
    <input type="button" value="今年" />
    <label>开始时间</label><input type="text" style="width:130px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
    <label>结束时间</label><input type="text" style="width:130px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
    <input type="button" value="搜索"  style="background-color: #428bca;color:white;margin-left: 25px;" class="search_button"/>
</div>

<table class="table_list">
    <thead>
    <tr style="background-color:#d6d6d6;">
        <th style="width:500px;padding-left: 50px;">所属账号</th>
        <th>邮豆发放总额</th>
        <th>占比</th>
    </tr>
    </thead>
    <tbody id="itemContainer">
    <script type="text/javascript">
    </script>
        <tr>
            <td style="padding-left: 50px;">南京邮政江宁局</td>
            <td>2000</td>
            <td>25%</td>
        </tr>
        <tr>
            <td style="padding-left: 50px;">南京邮政江宁局</td>
            <td>2000</td>
            <td>25%</td>
        </tr>
        <tr>
            <td style="padding-left: 50px;">南京邮政江宁局</td>
            <td>2000</td>
            <td>25%</td>
        </tr>
        <tr>
            <td style="padding-left: 50px;">南京邮政江宁局</td>
            <td>2000</td>
            <td>25%</td>
        </tr>
        <tr>
            <td style="padding-left: 50px;">南京邮政江宁局</td>
            <td>2000</td>
            <td>25%</td>
        </tr>
        <tr style="background-color: #ddffff!important;border-bottom: 1px solid #ccc">
            <td style="padding-left: 50px;">总计</td>
            <td>8000</td>
            <td>100%</td>
        </tr>
    </tbody>
</table>
  <span class="footer" style="margin-top:60px;">
  </span>

<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/UCoinCount.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>

