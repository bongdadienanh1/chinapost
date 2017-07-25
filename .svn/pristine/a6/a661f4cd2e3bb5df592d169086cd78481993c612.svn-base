<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery-ui.css">
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>无标题文档</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        body{
            zoom:62.5%;
            font-size:10px!important;
        }

    }
    .billButton{
        width:1450px;
        height:130px;
        margin-left:20px;
    }
    .billButton li{
        height:35px;
        float:left;
        margin-left:20px;
        margin-top:20px;
    }

    .billButton li button{
        width:60px;
        height:35px;
        border:1px solid #CCC;
        background:#fff;
        margin-right:10px;
        color: #666;
        cursor: pointer;
    }

    .billButton li button:active{
        background: #00cc88;
    }
    .billButton li input{
        width:135px;
        height:35px;
        border:none;
        border: 1px solid #ccc;
        background:#FFF;
        margin-left: -1px;
        font-size:16px;
    }

    .billButton li:first-child{
        width:250px;
    }
    .billButton li:nth-child(2){
        width:315px;
    }
    .billButton li:nth-child(3){
        width:315px;
    }
    #billsearch{
        width:75px;
        height:35px;
        margin-left:20px;
        margin-top:19px;
        color:#FFF;
        background:#54a6de;
        border:1px solid transparent;
    }

    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        height:24px;
        background-color: #f2f2f2;
        position: relative;
        margin-left: -45px;
        background-image: url("${bath}/static/img/date_img.png");
    }
    .xdsoft_datetimepicker{
        width:310px!important;
    }
    .select{
        display: inline-block;
        vertical-align: middle;
        width:150px;
        height:28px;
        background:#f8f8f8;
        text-align: center;
        position: relative;
        margin-left: -4px;
    }
    .arrow{
        display: inline-block;
        position: absolute;
        right: -5px;
        top: 0px;
        width: 36px;
        height: 35px;
        background:url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat;
    }

    .select dd,.select dt{
        width:150px;
        height:28px;
        line-height: 28px;
        background:#fff;
        margin-left:-1px;
        cursor: pointer;
    }
    .select dd{
        display: none;
        overflow: hidden;
    }

    #busChangeOut{
        width:145px;
        height:35px;
        margin-left:140px;
        margin-top:19px;
        color:#FFF;
        background:#54a6de;
        border:1px solid transparent;
    }
    #busChangeOut:hover{
        background:#00cc55;
    }
    #busChangeOut:active{
        background:#008738;
    }
    #holder{
        margin-top: 20px;
    }
    .table_list thead tr{
        height: 50px!important;
        color: #666666;
    }
    .table_list th,.table_list td{
        padding-left: 20px;
        border-bottom: 1px solid #e5e5e5;
    }
    .table_list tbody tr{
        height: 30px!important;
    }

</style>
<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>系统设置</span><abbr></abbr>
    </div>

<div class="billButton">
    <ul>
        <li class="allinputButton"><span>业务类型</span>
            <dl class="select"> <i value="0" class="arrow"></i>
                <dt name="active"><abbr>全部</abbr><input type="hidden" value="" id="typeId"> </dt>
                <dd data-id="">全部</dd>
            <#if !businessTypes?exists>
                <dd>无业务类型</dd>
            <#else>
                <#list businessTypes as object>
                    <dd data-id="${object.typeId}" class="import">${object.typeName}</dd>
                </#list>
            </#if>
            </dl>

        </li>
        <li>
           <input class="allinputButton" placeholder="开始时间" type="text" style="width:257px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
        </li>
        <li>
            <input class="allinputButton" placeholder="结束时间"  type="text" style="width:257px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
        </li>
        <input class="allseachButton" type="button" id="billsearch" value="搜索" />
        <input class="allclickButton" type="button" id="busChangeOut" value="导出变动记录" />
    </ul>
</div>

<div style="width: 100%;height: auto;overflow: hidden;">
    <table class="table_list" cellpadding="0" cellspacing="0" align="left" style="width: 1450px;margin-left: 20px;">
        <thead align="left">
        <tr>
            <th>时间</th>
            <th>业务类型</th>
            <th>变动前计算公式</th>
            <th>变动后计算公式</th>
            <th>变动说明</th>
            <th>备注</th>
            <th>操作者账号</th>
        </tr>
        </thead>

        <tbody align="left" id="itemContainer"></tbody>
    </table>

</div>
<div class="allcpageTurnButton" style="display: block;" id="holder"></div>
    </div>
</body>

<script type="text/javascript" src="${bath}/static/js/businessChangeHis.js?version=${VERSION}"></script>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>