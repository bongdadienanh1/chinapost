<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        @media screen and ( max-width: 1400px){
            body{
                zoom:62.5%;
                font-size:10px!important;
            }
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
        #outPutUcoin{
            width: 180px;
            height: 40px;
            border: none;
            background: #54a6de;
            float: right;
            color: #fff;
            margin-top: 15px;
            margin-right: 50px;
        }

        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            margin-left: 0!important;
            height:24px;
            border-radius: 16px;
            background-color: #f2f2f2;
            background-image: url("${bath}/static/img/date_img.png");
            position: relative;
            left: -45px;
        }
        .outputDetail{
            width: 1000px;
            height: auto;
            background: #fff;
            position:absolute;
            left: 10%;
            top: 15%;
            z-index: 3;
            display: none;
        }

        .outputDetail li:nth-child(2){
            width: 900px;
            height: auto;
            line-height: 80px;
            padding-left: 20px;
            padding-top: 20px;
        }
        .outputDetail li abbr span{
            display: inline-block;
            vertical-align: middle;
            width: 80px;

        }

        .outputDetail li abbr input{
            height: 35px;
        }
        .outputDetail li abbr b{
            font-weight: normal;
            display: inline-block;
            width: 120px;
            height: 37px;
            background: #bbb;
            line-height: 37px;
            text-align: center;
            color: #fff;
            vertical-align: middle;
            margin-left: 20px;
        }
        #outputDetailCancel,#outputDetailConfirm{
            width: 100px;
            height: 35px;
            border: none;
            color: #fff;
        }
        #outputDetailCancel{
            margin-left: 380px;
        }
        #outputDetailConfirm{
            margin-left: 30px;
            background: #54a6de;
        }

        .DepotDetail{
            height: 200px;
            overflow-y:scroll ;
        }
        .DepotDetail dl{
            overflow: hidden;
            padding-left: 20px;
            margin-left: 20px;
        }
        .DepotDetail dl dd{
            padding: 5px 0px;
            font-size: 16px!important;
            line-height: 20px;
        }
        .DepotDetail dl dd a{
            display: inline-block;
            text-align: center;
            width: 15px;
            height: 15px;
            line-height: 15px;
            border-radius: 15px;
            border: 1px solid #bbbbbb;
            margin-right: 10px;

        }
        .DepotDetail dd span{
            cursor: pointer;
        }
        .DepotDetail dd span:hover{
            background: #f1f1f1;

        }
        #updateUserInfo{
            width: 500px;
            height: auto;
            position: fixed;
            left: 25%;
            top: 25%;
            z-index: 3;
            background: #fff;
            display: none;
            border-radius: 5px;
        }
        #updateUserInfo h1{
            width: 100%;
            height: 50px;
            line-height: 50px;
            text-align: center;
            font-size: 24px;
            text-indent: 0px!important;
        }
        #updateUserInfo ul{
            height: 100%;
        }
        #updateUserInfo ul li{
            width: 100%;
            height: 60px;
            line-height: 60px;
            font-size: 16px;
        }
        #updateUserInfo ul li select{
            width: 100px;
            height: 40px;
        }
        #updateUserInfo ul li i{
            color: #ff3300;
        }
        #updateUserInfo ul li input[type='text']{
            width: 70%;
            height: 70%;
            border-radius: 3px;
        }
        #updateUserInfo ul li input[type='button']{
            width: 80px;
            height: 40px;
            border: none;
        }
        #updateUserInfo ul li input[type='button']:hover{
        }
        #updateUserInfo ul li input[type='button']:nth-child(1){
            margin-left:120px ;
        }
        #updateUserInfo ul li input[type='button']:nth-child(2){
            background: #54a6de;
            color: #fff;
            margin-left:50px ;
        }
    </style>
    <title>无标题文档</title>
</head>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    sessionStorage.setItem("ShoppingCart",'');
</script>

<body style="background: #edf3f8">
<div class="allOutShow" style="height: 800px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>单个转账</span><a class="leftshanow" href="piliangdaoru">批量导入</a><abbr></abbr>
    </div>
    <input class="allclickButton" type="button" value="导出邮豆发放记录" id="outPutUcoin"/>
<div id="updateUserInfo" class="allpop">
    <h1>创建新会员
        <h6 style="width: 100%;text-align: center; color: #666">该用户为新用户，请先完善用户信息，然后再对该用户发放邮豆</h6>
    </h1>
    <ul>
        <li style="text-indent: 32px;"><i>*</i>姓名:<input maxlength="12" id="updateUserName" style="margin-left: 2px" type="text" value=""></li>
        <li>
            <i>*</i>联系地址:
            <select style="margin-right:20px;" name="newMemAddressP" id="newMemAddressP">
                <option value="0" selected="selected">选择省份</option>
            </select>
            <select style="margin-right:20px;" name="newMemAddressC" id="newMemAddressC">
                <option value="0" selected="selected">选择城市</option>
            </select>
            <select name="newMemAddressD" id="newMemAddressD">
                <option value="0" selected="selected">选择区县</option>
            </select>
            <input style="margin-left:76px;" type="text" name="newMemberRoadDetail" id="newMemberRoadDetail" placeholder="详细地址" />
        </li>
        <li style="margin-top: 80px;"><i>*</i>联系电话:<input maxlength="11" id="updateUserPhoneNo" type="text" value=""></li>
        <li>
            <input class="allcancelButton" id="updateUserInfoCancel" value="取消" type="button" />
            <input class="allseachButton" id="updateUserInfoConfirm" value="确定" type="button" />
        </li>

    </ul>

</div>



<div class="inPut">
    <input type="text" name="userId" maxlength="18" placeholder="用户身份证号" />
    <input type="button" id="sub" value="下一步"/>
</div>

<div class="outputDetail allpop">
    <h1>导出邮豆发放记录</h1>
    <ul>
        <li></li>
        <li>
            <abbr><span><i style="color: #ff3300">*</i>时间段:</span>
                <input class="allinputButton" placeholder="发放开始时间" type="text" style="width:326px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
                <input class="allinputButton" placeholder="发放结束时间" type="text" style="width:326px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
            </abbr>
            <br>
            <abbr><span>业务类型:</span>
            <#list businessType as object>
               <div style="display: inline-block;margin-left: 20px;"><input type="radio" name="businessTypeCheck" value="${object.typeId}"> ${object.typeName}</div>
            </#list>
            </abbr>
            <br/>
            <#if isEnd>
                <abbr>
                    <span><input value="${enterpriseId}" id="rangeId" type="hidden">
                </abbr>
                <#else>
                    <abbr>
                        <span><i style="color: #ff3300">*</i>导出范围</span><input readonly="readonly" id="range" type="text" style="width: 300px;"><input id="rangeId" type="hidden">
                    </abbr>
                    <div class="DepotDetail">
                        <dl>
                            <dd>
                                <a data-id="${enterprise.id}">+</a>
                                <input type="radio" name="DepotRadio"/>
                                <span>${enterprise.enterpriseName}</span>
                            </dd>
                        </dl>
                    </div>
            </#if>
        </li>
        <li style="margin: 20px 0px;"><input class="allcancelButton" id="outputDetailCancel" type="button" value="取消" /><input class="allseachButton" id="outputDetailConfirm" value="确定" type="button"/> </li>
    </ul>
</div>

<script src="${bath}/static/js/UbaoSend.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
</html>
