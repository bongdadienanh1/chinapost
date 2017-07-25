<#assign bath = request.contextPath>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jPages.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>

    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script>
        //wealth_do   已分配
        //wealth_undo 未分配
        var wealth_do = ${wealth_do};
        var wealth_undo = ${wealth_undo};
        sessionStorage.setItem("ShoppingCart",'');
    </script>
    <title>财富管理</title>
    <style type="text/css">
        @media screen and ( max-width: 1400px) {
            .ucoinspare a{
                line-height: 24px!important;
            }
            .CashAssign,.detailList,#downtable{
                transform: scale(0.8,0.8);
                transform-origin: left top;
                -webkit-transform: scale(0.8,0.8);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.8,0.8);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.8,0.8);
                -moz-transform-origin: left top;
            }
            .requestTable{
                transform: scale(0.81,0.81);
                transform-origin: left top;
                -webkit-transform: scale(0.81,0.81);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.81,0.81);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.81,0.81);
                -moz-transform-origin: left top;
            }

        }
        body{
            height:90%!important;
        }
        .Ucoin{
            width:700px;
            height:300px;
            border:1px solid #e5e5e5;
            margin-left:20px;
            margin-top: 20px;
            background:#FFF;
            position:relative;
        }
        .Ucoin #my_container{
            position:absolute;
            top:0px;
            right:0px;
        }
        .Ucoin #my_container abbr{
            z-index: 2;
            position: absolute;
            right: 10px;
            top: 5px;
            font-size: 10px;
        }
        .Ucoin #my_container abbr:first-child{

        }
        .Ucoin #my_container abbr:nth-child(2){
            top: 25px;
        }
        .Ucoin .UcoinContent{
            width:400px;
            height:300px;
            position:absolute;
            left:0px;
            top:0px;
        }
        .ucoinspare a{
            display:inline-block;
            width:120px;
            line-height: 34px;
            text-align:center;
            margin-right:20px;
            cursor:pointer;
        }
        .ucoinspare a:hover{
            color: #fff;
        }


        /*.UcoinUnder{*/
            /*width:700px;*/
            /*height:300px;*/
            /*border:1px solid #e5e5e5;*/
            /*margin-left:20px;*/
            /*background:#FFF;*/
            /*position:absolute;*/
            /*left:750px;*/
            /*top:92px;*/
        /*}*/
        .UcoinUnder li{
            width:100%;
            height:50px;
            margin:40px 10px;
        }
        .Budget{
            width:600px;
            height:300px;
            border:1px solid #999;
            background:#FFF;
                        position:fixed;
            left:15%;
            top:30%;
            display:none;
            z-index: 3;
        }
        .Budget ul{
            margin-top: 50px;
        }
        .Budget li{
            width:600px;
            height:50px;
            margin:5px 10px;
        }
        .Budget li input[type='text']{
            margin-left:50px;
            width:350px;
            height:30px;
            border-style:none;
            border-bottom:1px solid #999;
            background:#FFF;
        }
        #Ucoinsub{
            width:120px;
            margin-left:200px;
            margin-top:20px;
            cursor:pointer;
        }

        .Budget h1 i{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        .CashAssign{
            width:800px;
            height:auto;
            background:#FFF;
            position:absolute;
            left:15%;
            top:15%;
            display:none;
            z-index: 3;
        }
        .CashAssign li{
            width:600px;
            height:auto;
            margin:25px 10px;
        }
        .CashAssign h1{
            width:600px;
            height:50px;
        }
        .CashAssign li ul{
            margin:20px 0px;
            position:relative;
            top:-40px;
            left:100px;
            width:600px;
            height: 500px;
            overflow-y: scroll;
            overflow-x: hidden;
        }
        .CashAssign li ul li{
            width:700px;
            margin:10px 0px;
        }
        .CashAssign li ul li abbr{
            display: inline-block;
            vertical-align: middle;
            width: 350px;
        }
        .CashAssign li ul li input.CashChkbox{
            margin:0px 10px;
        }
        .CashAssign li ul li input.Cashtext{
            width:150px;
            height:30px;
            border-style:none;
            border:1px solid #999;
            background:#FFF;
        }
        #checkval{
            border-style:none;
            background:#FFF;
        }
        #Cashsub{
            width:120px;
            border-style:none;
            margin-left:200px;
            margin-bottom:30px;
            cursor:pointer;
        }
        .CashAssign h1 i{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        .CashPwd{
            width:600px;
            height:250px;
            border:1px solid #999;
            border-radius:5px;
            background:#FFF;
                        position:fixed;
            left:15%;
            top:30%;
            display:none;
            z-index: 4;
        }
        .CashPwd li{
            width:550px;
            height:65px;
            padding-left:20px;
        }
        #CashPwd{
            width:150px;
            height:30px;
            margin-left:30px;
        }
        #CashPwdSub{
            width:120px;
        }
        #CashCancel{
            width:80px;
            margin-left:30px;
        }
        .table_list th,.table_list td{
            font-weight: normal!important;
        }
        .table_list thead tr{
            height: 50px!important;
            color: #666666;

        }
        .table_list th,.table_list td{
            text-align:left ;
            padding-left: 20px;
        }
        .table_list th:nth-child(2),.table_list th:nth-child(3),.table_list td:nth-child(3),.table_list td:nth-child(2){
            text-align:right ;
            padding-right: 50px;
        }
        .table_list tbody tr{
            height: 30px!important;
        }
        .holder{
            margin-top: 20px;
        }

        .billManager,.requisition{
            width:1600px;
            height:700px;
            margin-left:20px;
            margin-top: 20px;
        }
        .requestTable ul{
            width:1600px;
            height:35px;
            margin-top: 30px;
        }
        .requestTable ul li{
            width:155px;
            height:34px;
            border-bottom: 1px solid transparent;
            text-align:center;
            line-height:34px;
            margin-left:20px;
            float:left;
            cursor:pointer;
        }
        .requestTable ul li.reqOn{
            color:#54a6de;
            border-bottom: 2px solid #54a6de;
        }
        .billManager dt,.requisition dt{
            width:1600px;
            height:40px;
            border-bottom: 1px solid #e5e5e5;
        }
        .billManager dd,.requisition dd{
            width:1600px;
            height:45px;
            background:#FFF;
            border-bottom: 1px solid #e5e5e5;
        }


        .billManager dt abbr,.requisition dt abbr{
            display:inline-block;
            width:250px;
            height:40px;
            text-align:center;
            color:#666666;
            line-height:40px;
        }
        .billManager dt abbr:nth-child(5),.requisition dt abbr:nth-child(4),.billManager dd abbr:nth-child(8),.requisition dd abbr:nth-child(5),.requisition dt abbr:nth-child(6),.requisition dd abbr:nth-child(7){
            width: 180px !important;;
            padding-right: 100px;
            text-align: right;
        }
        .billManager dt abbr{
            width:200px!important;
        }

        .billManager dd abbr{
            display:inline-block;
            vertical-align: middle;
            width:200px;
            height:45px;
            text-align:center;
            line-height:45px;
            color:#333;
            vertical-align: middle;
            overflow: hidden;
        }
        .requisition dd abbr{
            display:inline-block;
            vertical-align: middle;
            width:250px;
            height:45px;
            text-align:center;
            line-height:45px;
            color:#333;
            vertical-align: middle;
            overflow: hidden;
        }
        .detail{
            height:35px;
            border-style:none;
            margin-top:5px;
            background-color:#fff;
            color: #54a6de;
        }
        .detailList{
            width:880px;
            height:auto;
            overflow:hidden;
            background:#FFF;
            z-index:2;
            position:absolute;
            left:15%;
            top:15%;
            display:none;
        }
        .header{
            height:70px;
            line-height:70px;
            padding-left:15px;
            font-size:22px;
            color:#333;
            border-bottom: 1px solid #e5e5e5;
        }
        #xx{
            display:inline-block;
            vertical-align:middle;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        .maindetail{
            height:auto;
            overflow:hidden;
        }
        .maindetail h2{
            width:835px;
            margin-left:15px;
            height:40px;
            line-height:40px;
            background:#edf3f8;
            padding-left:15px;
            font-size:16px;
            margin-top: 15px;

        }
        .maindetail	ul{
            width:835px;
            height:230px;
            margin-left:20px;
            margin-top:20px;
        }
        .maindetail	ul li{
            float:left;
            width:350px;
            margin:5px 5px 5px 0px;
            height:40px;
        }
        .maindetail	ul li span {
            display: inline-block;
            vertical-align: middle;
            width: 100px;
            overflow: hidden;
        }
        .maindetail	ul li input{
            border-style:none;
            background:#FFF;
            margin-left:20px;
        }
        .firstTable{
            width:835px;
            height:auto;
            overflow:hidden;
            margin-left:20px;
            display:block;
        }
        .firstTable dl{
            width:835px;
        }
        .firstTable dl dt{
            width:835px;
            height:35px;
            text-align:center;
            color:#666666;
            background:#edf3f8;
            line-height:35px;
        }
        .firstTable dl dt abbr{
            display:inline-block;
            vertical-align:middle;
            width:400px;
            height:35px;
        }
        .firstTable dl dd{
            width:835px;
            height:50px;
            text-align:center;
            background:#fff;
            border-bottom:1px solid #ccc;
            line-height:50px;
        }
        .firstTable dl dd abbr{
            display:inline-block;
            vertical-align:middle;
            width:400px;
            height:50px;
        }

        #holder_first{
            margin:20px 0px;
        }

        .moreDetail{
            color:#54a6de;
            float: right!important;
            cursor: pointer;
            margin-right: 10%;
            margin-top: 20px;
        }
        .secondTable{
            width:835px;
            height:auto;
            overflow:hidden;
            margin-left:20px;
            display:none;
        }
        .secondTable dl{
            width:835px;
        }
        .secondTable dl dt{
            width:835px;
            height:35px;

            color:#666666;
            background:#edf3f8;
            line-height:35px;
        }
        .secondTable dl dt abbr{
            display:inline-block;
            vertical-align:middle;
            text-align:center;
            width:260px;
            height:35px;
        }
        .secondTable dl dd{
            width:835px;
            height:50px;

            background:#fff;
            border-bottom:1px solid #ccc;
            line-height:50px;
        }
        .secondTable dl dd abbr{
            display:inline-block;
            vertical-align:middle;
            text-align:center;
            width:260px;
            height:50px;
            overflow: hidden;
        }
        #holder{
            margin-top: 20px;
        }
        .bigInt{
            margin-left: 20px;
            font-size: 15px;
            color: #ff3300;
        }

        #holder_first,#holder_second{
            margin:20px 0px;
        }
    </style>
    <#--<script src="${bath}/static/js/excel.js"></script>-->
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        $(document).ready(function(){
            $( ".CashAssign" ).draggable();
            $( ".CashPwd" ).draggable();
            $( ".Budget" ).draggable();

            if(${flag}){
                $("#BudGo").hide()
            }

            //tab切换
            $(".requestTable li").click(function(){
                var lival=$(this).val();
                $(this).addClass("reqOn").siblings("li").removeClass("reqOn");

                if(lival == 0){

                }
                else if(lival == 1){

                }
            });
        });

    </script>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div style="width:100%; font-size: 16px; height:70px; line-height: 70px;margin-left:20px;">
        <span style="font:24px '黑体'; color:#2c97de; display:inline-block; line-height: 70px;" >财富管理</span>
    </div>
    <span style="margin-left: 20px">公司账号余额和所有下级公司的财富总量 :<h4 class="addCommas"> ${(wealth_do + wealth_undo)?string("#.##")}</h4>,</span>
    <span>价值<h4 class="addCommas">${((wealth_do + wealth_undo)/1250)?string("#.##")}</h4>元</span>
    <div class="ucoinspare" style="position:relative;width:100%;height:auto;padding: 20px 20px 20px 20px;color:#333">
        <p id="lastMoney" style="font:22px '黑体'">余额<#if isEnd && allUcoin < 0 ><b style="color: #ff3300;margin-left: 30px;font-size: 16px">（余额不足，请联系上级发放）</b></#if></p>
        <p style="margin-top:20px;">
            <span style="font:16px '黑体'">
                <#if allUcoin < 0 >
                    <h4 style="color: #ff3300;" class="addCommas">${allUcoin}</h4>邮豆</span><span style="font:16px '黑体'">(价值 :<h4 style="color: #ff3300" class="addCommas">${(allUcoin/1250)?string("#.##")}</h4>元)</span>
                <#else>
                    <h4 class="addCommas">${allUcoin}</h4>邮豆</span><span style="font:16px '黑体'">(价值 :<h4 class="addCommas">${(allUcoin/1250)?string("#.##")}</h4>元)</span>
                </#if>
            <div style="position: absolute;bottom:10px;right:90px;">
                <a class="allclickButton" id="BudGo">预算申请</a><#if !isEnd><a class="allclickButton" id="CashGo">财富分配</a></#if>
            </div>
        </p>
    </div>

<#if !isEnd>
    <div id="downtable" style="width:1600px;border: 1px solid #e5e5e5;margin-top: 20px;margin-left: 20px;margin-bottom: 50px;">
        <div style="margin:40px 0px 20px 20px; font-size:18px; color:#666;">
            下级账号(财富总量<span><h4 class="addCommas">${SonTotalWealth}</h4>邮豆</span>,价值<h4 class="addCommas">${(SonTotalWealth/1250)?string("#.##")}</h4>元)
        </div>

        <div>
            <table class="table_list" style="width:1450px;margin-left:20px;font-size:16px;border:0;">
                <thead align="left">
                <tr>
                    <th>账号名称</th>
                    <th>财富总量</th>
                    <th>价值人民币</th>
                    <#--<th>占比</th>-->
                </tr>
                </thead>
                <tbody align="left" id="itemContainer">
                    <#list SonWealthResult as son>
                    <tr style="background-color:#fff;">
                        <th>
                            <#if son.wealth < 0 >${son.name}<div title="该网点余额不足，请尽快联系发放" style="color: #ff3300;margin-left: 10px;display: inline-block; width: 100px; text-align:center;height: 30px;line-height: 30px;">余额不足</div><#else>${son.name}
                            </#if>

                        </th>
                        <th>
                            <#if son.wealth < 0 >
                                    <abbr style="color: #ff3300" class="ucoinAll addCommas">${son.wealth}</abbr> 邮豆</th>
                                <#else>
                                    <abbr class="ucoinAll addCommas">${son.wealth}</abbr> 邮豆</th>
                            </#if>

                        <th>
                            <#if (son.wealth/6250 > 0.01)>
                            <abbr class="addCommas">
                            ${(son.wealth/6250)?string("0.00")}
                            <#elseif (son.wealth/6250 = 0 )>
                            <abbr class="addCommas">
                                0.00
                            <#elseif (son.wealth/6250 < 0 )>
                            <abbr style="color: #ff3300;" class="addCommas">
                            ${(son.wealth/6250)?string("0.00")}
                                <#else>
                            <abbr class="addCommas">
                                0.01
                            </#if>
                        </abbr>元</th>
                        <#--<th class="percent" data-total="${SonTotalWealth}" data-son="${son.wealth}">-->
                            <#--<#if son.wealth !="0">-->
                            <#--${(son.wealth / SonTotalWealth)}-->
                            <#--<#else>-->
                                <#--0%-->
                            <#--</#if>-->
                        <#--</th>-->
                    </tr>
                    </#list>
                </tbody>
                <div id="id_list">
                    <#list SonWealthResult as son>
                        <input type="hidden" value="${son.id}" />
                    </#list>
                </div>
            </table>
            <div class="holder allcpageTurnButton" style="margin-left: 22px;"></div>
        </div>
    </div>
<#else>
    <div style="font:23px '黑体';margin-left: 20px;margin-top: 20px;color:#333">最近账单/请款单</div>
    <div class="requestTable">
        <ul>
            <li value="0" class="reqOn">最近账单</li>
            <li value="1">最近请款单</li>
            <span class="moreDetail">查看更多 >></span>
        </ul>
        <dl class="billManager">
            <dt>
                <abbr>支付时间</abbr><abbr>交易单号</abbr><abbr>账单类型</abbr><abbr>账单状态</abbr><abbr>总金额</abbr><abbr>总笔数</abbr><abbr>操作</abbr>
            </dt>
        </dl>
        <dl class="requisition" style="display: none;">
            <dt>
                <abbr>创建时间</abbr><abbr>单据编号</abbr><abbr>单据状态</abbr><abbr>申请金额</abbr><abbr>申请原因</abbr><abbr>支付金额</abbr>
            </dt>
        </dl>

    </div>
</#if>

    <div class="Budget allpop">
        <h1>预算申请 <i></i></h1>
        <ul>
            <li style="margin:-10px 0px 0px 50px;">申请金额<input type="text" maxlength="9" id="BudAmout" name="BudAmout" placeholder="请输入充值金额" /></li>
            <li style="margin-left:50px;">申请原因<input maxlength="50" type="text" id="BudReason" name="BudReason" placeholder="请填写申请原因"  /></li>
            <li><input class="allseachButton" type="button" id="Ucoinsub" value="确定" /></li>
        </ul>
    </div>



<div class="CashAssign allpop">
    <h1 style="width:100%;font-size:18px;">可分配余额:<span class="addCommas">${wealth_undo}</span>邮豆 <i></i></h1>
    <ul>
        <li style=" margin-left:40px;"><#if flag == "true">下级公司:<#else>部门:</#if>
            <ul id="CashAssign">
                    <#--<li><input type="checkbox" value="${enterpriseId}" class="CashChkbox" /><abbr>${enterpriseName}:</abbr><input maxlength="8" type="text" name="CashName" class="Cashtext" placeholder="分配金额" /></li>-->
                <#list SonWealthResult as son>
                    <li><input type="checkbox" value="${son.id}" class="CashChkbox" /><abbr>${son.name}:</abbr><input maxlength="12" type="text" name="CashName" class="Cashtext" placeholder="分配金额" /></li>
                </#list>
            </ul>
        </li>
        <li style="margin-top:-20px;margin-left:40px;">分配总额：<input type="text" id="checkval" readonly="readonly" />邮豆</li>
        <li><input class="allseachButton" type="button" id="Cashsub" value="确定"  /> </li>
    </ul>
</div>



    <div class="CashPwd allpop">
        <h1>支付密码</h1>
        <ul>
            <li style="height:100px; line-height:100px; padding-left:50px; border-bottom:1px solid #ccc; border-top:1px solid #CCC;"><span style="color:#ff3300;">*</span>支付密码：<input type="password" id="CashPwd"/></li>
            <li style="padding:20px 0px 0px 200px;"><input class="allseachButton" type="button" id="CashPwdSub" value="确定" /><input class="allcancelButton" type="button" id="CashCancel" value="取消" /></li>
        </ul>
    </div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</div>
<div class="detailList allpop">
    <h1 class="header">账单详情<i id="xx"></i></h1>
    <div class="maindetail">
        <h2>交易成功</h2>
        <ul>
            <li><span>交易对象:</span><input style="width: 200px" type="text" readonly="readonly" name="target" /></li>
            <li><span>账单类型:</span><input type="text" readonly="readonly" name="type" /></li>
            <li><span>总金额:</span><input type="text" readonly="readonly" name="sumMoney" /></li>
            <li><span>总笔数:</span><input type="text" readonly="readonly" name="sumNumber" /></li>
            <li><span>支付时间:</span><input type="text" readonly="readonly" name="date" /></li>
            <li><span>交易单号:</span><input type="text" readonly="readonly" name="code" /></li>
            <li value="orderNumber"><span>订单号:</span><input type="text" readonly="readonly" name="orderNumber" /></li>
            <li value="businessType"><span>业务类型:</span><input type="text" readonly="readonly" name="businessType" /></li>
        </ul>
    </div>
    <div class="secondTable">
        <dl>
            <dt>
                <abbr class="sendTarget">发放对象</abbr>
                <abbr>姓名</abbr>
                <abbr class="sendTargetM">发放金额</abbr>
            </dt>
            <div id="itemContainer_second"></div>
            <div class="allcpageTurnButton" id="holder_second"></div>
        </dl>
    </div>

</div>
</body>

<script src="${bath}/static/js/UCoinManager.js?version=${VERSION}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var lockMoney = false
        $(".ucoinAll").each(function(){
            if( parseInt($(this).html()) < 0 ){
                lockMoney = true
            }
        })
        if (lockMoney){
            var  html = '<b style="color: #ff3300;margin-left: 30px;font-size: 16px">（有余额不足下级，请尽快为其分配财富）</b>'
            $("#lastMoney").append(html)
        }
    })

</script>
</html>
