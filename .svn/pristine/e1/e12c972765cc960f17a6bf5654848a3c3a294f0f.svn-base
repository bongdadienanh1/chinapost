<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jPages.css" />
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        #tableForm{
            transform: scale(0.8,0.8);
            transform-origin: left top;
            -webkit-transform: scale(0.8,0.8);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.8,0.8);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.8,0.8);
            -moz-transform-origin: left top;
        }
        #userDetailListReduce{
            font-size: 16px!important;
        }
    }
    .accountSearch{
        width:1800px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        width:300px;
        line-height:40px;
        float:left;
        margin:10px 0px;
    }

    .accountSearch li select{
        margin-top: -2px;
        border: none;
        margin-left: 10px;
    }
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        height:24px;
        background-color: #f2f2f2;
        margin-left: -45px;
        background: url("${bath}/static/img/date_img.png") no-repeat center;

    }

    .table_listReduce{
        width: 1600px;
        margin-left: 20px;
    }
    .table_listReduce span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listReduce th, .table_listReduce td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
    }
    #holder{
        margin: 30px 0px;
    }
    #holder2{
        margin: 30px 0px;
        margin-left: 100px;
    }
    #userDetailListReduce{
        font-size: 12px;
        width:1200px;
        position:fixed;
        left:15%;
        top:15%;
        z-index:2;
        background:#FFF;
        display: none;
    }
    #userDetailListReduce h1 i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
    }
    .holder2{
        margin-left: 100px;
        margin-bottom: 20px;
        width: 1000px;
    }
    .table_listReduceDetail{
        margin-left: 100px;
        margin-bottom: 20px;
        width: 1000px;
    }
    .table_listReduceDetail th, .table_listReduceDetail td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
    }
    .sendReduceDetail{
        color: #54a6de;
        cursor: pointer;
    }
    #record{
        margin-left: 100px;
        margin-top:20px;
    }
    #record li{
        float: left;
        width: 500px;
        height: 36px;
        font-size: 18px;
    }
</style>
<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:800px;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
    <#--<span>单个转账</span><a class="leftshanow" href="piliangdaoru">批量导入</a><abbr></abbr>-->
        <a href="piliangdaoru">批量导入</a><span>发放扣减记录</span><abbr></abbr>
    </div>
    <div class="accountSearch">
        <ul>
            <#--<li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"> <input type="hidden" id="NewEnterpriseIdChoosen"  value="${enterprise.enterpriseId}"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>-->
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li class="allinputButton" style="width: 250px; line-height: 32px;margin-top: 17px">
                <span style="margin-top: -10px">操作类型</span>
                <select class="typeChoose">
                    <option selected="selected" value="0">全部</option>
                    <option value="1">批量发放</option>
                    <option value="2">批量扣减</option>
                </select>
            </li>
            <li style="width:500px; margin-left: 20px">
                <input class="allseachButton" type="button" value="搜索" id="accSearch" />
                <#--<input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" />-->
            </li>
        </ul>
    </div>

    <div id="tableForm" style="width: 1600px">
        <table class="table_listReduce" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>完成时间</th>
                <th>文件名</th>
                <th>操作类型</th>
                <th>总笔数</th>
                <th>总金额</th>
                <th>操作者账号</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
            <tr>
                <td style="text-align: center" colspan="11"><img src="${bath}/static/img/nodata.png" width="1600" height="600"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>


    <div class="allpop" id="userDetailListReduce">
        <h1>记录详情<i></i></h1>
        <div>
        <ul id="record">
            <li><abbr>完成时间：<span id="recordData"></span></abbr></li>
            <li><abbr>操作类型：<span id="recordType"></span></abbr></li>
            <li><abbr>总笔数：<span id="recordCount"></span></abbr></li>
            <li><abbr>发放总金额：<span id="recordMoney"></span></abbr></li>
            <li><abbr>操作者账号：<span id="recordAccount"></span></abbr></li>
        </ul>
        </div>
        <table class="table_listReduceDetail" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>机构编号</th>
                <th>身份证号</th>
                <th>姓名</th>
                <th>电话</th>
                <th>营销邮豆金额</th>
                <th>促销邮豆金额</th>
                <th>业务日期</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer2">
            <tr>
                <th>机构编号</th>
                <th>身份证号</th>
                <th>姓名</th>
                <th>电话</th>
                <th>营销邮豆金额</th>
                <th>促销邮豆金额</th>
                <th>业务日期</th>
            </tr>
            </tbody>
        </table>
        <div id="holder2" class="allcpageTurnButton"></div>
    </div>
    <div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>




<div>
</body>
<script src="${bath}/static/js/sendReduceRecode.js?version=${VERSION}"></script>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>