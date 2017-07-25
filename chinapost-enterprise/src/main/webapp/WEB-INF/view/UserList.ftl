<#assign bath = request.contextPath>
<head>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->

    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
<style>
    .accountSearch{
        width:1600px;
        height:150px;
        margin-left:20px;
        margin-top:20px;
    }
    .accountSearch li{
        width:250px;
        height:36px;
        line-height:40px;
        float:left;
    }
    .accountSearch li input,select{
        width:120px;
        height:38px;
        margin-left: -3px;;
        border:1px solid #ccc;
        background:#FFF;
        margin-top: -3px;;
    }
    option{
        font-size: 15px;
    }
    .accountSearch li span{
        display:inline-block;
        width:90px;
        height:36px;
        text-align:center;
        font-size:15px;
        background:#e4e4e4;
        line-height:36px;
        border:1px solid #ccc;

    }
    #accSearch{
        width:70px;
        height:37px;
        background:#428bca;
        color:#FFF;
        border:1px solid transparent;
        font-size:15px;
        margin-left: 10px;
    }
    #accSearch:hover{
        border:1px solid #f0aa39;
        color:#f0aa39;
    }
    #accExport{
        width:110px;
        height:37px;
        background:#33cccc;
        color:#FFF;
        font-size: 15px;
        border:1px solid transparent;
        margin-left:30px;
    }
    #accExport:hover{
        border:1px solid #f0aa39;
        color:#f0aa39;
    }
    table {
        margin-left: 21px;
        width:1600px;
    }
    table td{
        text-align: center;
        font-size: 15px;
        border-bottom:1px solid #999
    }
    td .td_img{
        border-radius: 30px;
        background-color: pink;
        width:45px;
        height:45px;
        margin-left: 20px;
    }
    .content td:nth-child(n){
        padding-top: 20px!important;
    }
    .content td:nth-child(1){
        padding-top: 8px!important;
    }
    .content td select{
        background-color: #f2f2f2;
        padding-left: 5px;
    }
    #detail{
        position:absolute;
        top:100px;
        left:200px;
        width:882px;
        height:704px;
        background-color: white;
        position: relative;
    }
    #detail .header{
        width:100%;
        height:70px;
        border-bottom: 1px solid #ccc;
        line-height: 70px;
        padding-left: 25px;
        font-size: 20px;
        font-weight: 100;
    }
    #detail .content {
        margin: 0 auto;
    }
    #detail .content .basic_info{
        width:90%;
        height:180px;
        margin: 30px auto auto auto;
        font-weight: 200;
        position: relative;
    }
    #detail .content .basic_info header{
        border-bottom: 1px solid #f2f2f2;
        padding-left: 10px;
        font-size: 15px;
        height:40px;
        line-height: 40px;
    }
    #detail .content .basic_info .edit_button{
        position: absolute;
        top:0px;
        right:15px;
        width:80px;
        height:30px;
        color:white;
        background-color: #0099ff;
        font-size: 18px;
        border:none;
    }
    #detail .content .basic_info_content{
        margin-top: 15px;
    }
    #detail .content .basic_info_content .head{
        background-color: hotpink;
        width:150px;
        height:150px;
        margin-left: 15px;
        display: inline-block;
    }
    #detail .content .basic_info_content .content{
        width:610px;
        height:150px;
        border:1px solid;
        display: inline-block;
    }
    #detail .content .ucon_info{
        margin-top: 50px;
    }
</style>
</head>
<body>

<div style="width:1600px; height:100px; border-bottom:2px solid #dcdcdc; margin-left:20px;">
	<span style="font:24px '黑体'; color:#000; display:inline-block; margin-top:15px;margin-bottom:20px;">会员管理
    </span>
    <br  />
    会员列表
    |
    <a style="display:inline-block; margin:0px 10px 0px 10px;" href="piliangdaoru">会员消费统计</a>
</div>
<div class="accountSearch">
    <ul>
        <li>
            <span>身份证号</span>
            <input type="text" name="accountNum" />
        </li>
        <li>
            <span>姓名</span>
            <input type="text" name="accountCust" />
        </li>
        <li>
            <span>联系电话</span>
            <input type="text" name="accountCust" />
        </li>
        <li>
            <span>是否激活</span>
            <select>
                <option selected>全部</option>
                <option>是</option>
                <option>否</option>
            </select>
        </li>
        <li>
            <span>客户经理号</span>
            <input type="text" name="accountUserName" />
        </li>
        <li>
            <span>手机绑定</span>
            <select>
                <option selected>全部</option>
                <option>是</option>
                <option>否</option>
            </select>
        </li>
        <li style="margin-top: 10px;">
            <span>标签</span>
            <select>

            </select>
        </li>
        <li style="width:300px;margin-top: 10px;">
            <input type="button" value="搜索" id="accSearch" />
            <input type="button" id="accExport" value="开通新会员" />
        </li>
    </ul>
</div>
<table class="table" style="width:1600px;">
    <tr style="background-color: #949494;color:white;font-weight: bold;">
        <td width="100">头像</td>
        <td width="210">身份证号</td>
        <td width="100">姓名</td>
        <td width="100">性别</td>
        <td width="140">联系电话</td>
        <td width="180">账户邮豆</td>
        <td width="100">是否激活</td>
        <td width="100">手机绑定</td>
        <td width="180">客户经理号</td>
        <td width="100">标签</td>
        <td width="160">最后登录时间</td>
        <td>操作</td>
    </tr>
    <tr class="content">
        <td>
            <div class="td_img">

            </div>
        </td>
        <td>320583199302190019</td>
        <td>周仁杰</td>
        <td>男</td>
        <td>12345677854</td>
        <td>200.00</td>
        <td>否</td>
        <td>否</td>
        <td>11234</td>
        <td></td>
        <td>2016-04-18<br/>12:24:05</td>
        <td>
            <select>
                <option>查看详情</option>
                <option>12345678</option>
            </select>
        </td>
    </tr>
</table>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>