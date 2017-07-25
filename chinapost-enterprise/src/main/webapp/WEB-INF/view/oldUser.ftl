<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <title>无标题文档</title>
    <style>
        body{
            background:#f7f7f7;
        }

        .oldUser{
            width:800px;
            height:auto;
            overflow: hidden;
            background:#FFF;
            border:1px solid #e5e5e5;
            margin-top:50px;
            margin-left:150px;
        }
        .oldUser ul{
            width:500px;
            margin:auto;
            margin-top:30px;
            position:relative;

        }
        .oldUser ul input{
            margin:15px 0px 15px 20px;
            width:300px;
            height:40px;
            border:1px solid #CCC;
            border-radius:5px;
            background:#FFF;
        }
        .oldUser ul select{
            margin:15px 0px 15px 20px;
            width:300px;
            height:40px;
            border:1px solid #CCC;
            border-radius:5px;
            background:#FFF;
            font-size:18px;
            padding-left:20px;
        }

        .oldUser ul li{
            color:#000;
            font-size:20px;
        }
        .oldUser ul li abbr {
            display: inline-block;
            vertical-align: middle;
            width: 130px;
            text-align: right;
        }
        .oldUser ul li i{
            color:#ff3300;
            position:relative;
            top:0px;
        }
        #cancel{
            width:120px;
            height:40px;
            background:#bbbbbb;
            border-radius:5px;
            color:#FFF;
            position:absolute;
            left:109px;
            cursor:pointer;

        }
        #userSub{
            width:120px;
            height:40px;
            background:#54a6de;
            border-radius:5px;
            color:#FFF;
            position:absolute;
            left:259px;
            cursor:pointer;

        }

        .success{
            width:600px;
            height:450px;
            background:#FFF;
            border:1px solid #ccc;
                        position:fixed;
            left:15%;
            top:30%;
            z-index:3;
            display:none;

        }
        .success span{
            display:inline-block;
            height:40px;
            position:relative;
            left:200px;
            top:150px;
            color:#000;
            font-size:20px;
        }
        .success span img{
            margin-right:30px;
        }
        #detail{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:35px;
            color:#FFF;
            font-size:14px;
            border-radius:3px;
            text-align:center;
            background:#bbbbbb;
            position:relative;
            top:300px;
            cursor:pointer;
        }
        #complete{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:35px;
            font-size:14px;
            color:#FFF;
            border-radius:3px;
            text-align:center;
            background:#009966;
            position:relative;
            top:300px;
            left:50px;
            cursor:pointer;

        }

        .oldUser ul li.detail{
            height:150px;
        }
        .oldUser ul li.detail img{
            position:relative;
            top:35px;
            left:18px;
        }

        .oldUserDetail{
            position:relative;
            left:250px;
            top:-95px;
        }
        .oldUser ul li.detail ul.oldUserDetail li{
            font-size:12px;
            margin:5px 0px 0px 5px;
            padding:0px;
        }
        .oldUser ul li.detail ul.oldUserDetail li:first-child{
            font-size:18px;
        }
        #updateUserInfo{
            width: 500px;
            height: 400px;
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

</head>
<body style="background: #edf3f8">
<div class="allOutShow" style="height: auto;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span style="font:20px '黑体'; background: #ffffff; color:#2c97de; display:inline-block; width:10%;line-height: 70px;text-indent: 30px;">单个转账</span><a class="leftshanow" href="piliangdaoru">批量转账</a><abbr></abbr>
    </div>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    var enterpriseId = "${enterpriseId}";
    $(".success").draggable()
</script>

<input type="hidden" id="customerId" value="${customer.getId()}">
<input type="hidden" id="provinceId" value="${customer.provinceId}">
<input type="hidden" id="cityId" value="${customer.cityId}">
<input type="hidden" id="districtId" value="${customer.districtId}">
<input type="hidden" id="addrDetail" value="${customer.addr}">
<div id="updateUserInfo">
    <h1>修改用户信息</h1>
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
<div class="oldUser">
    <ul>
        <li class="detail"><abbr>收款人：</abbr><img src="${imgUrl}" width="100" height="99" />
            <ul class="oldUserDetail">
                <input class="allseachButton" id="updateButton" style=" color:#fff; background:#54a6de;position: absolute; right: 150px; top: -20px; width: 80px; height: 30px" value="编辑" type="button">
                <li id="idCard">${idCardNo}</li>
                <li><abbr style="width: 80px; text-align: left;white-space: nowrap; overflow: hidden;margin-right: 10px;" id="userName">
                <#if FullName=="">暂无姓名<#else>
                ${FullName}
                </#if>
               </abbr><span id="userPhoneNo">
                <#if PhoneNo=="">暂无联系电话<#else> ${PhoneNo}</#if>
               </span></li>
                <li style="width: 300px;">本网点邮豆余额：<#if EnterpriseUcoin>${EnterpriseUcoin}<#else >0</#if> (总余额:${UCoin})</li>
                <li>联系地址：<span style="display: inline-block; vertical-align: middle; width: 150px; overflow: hidden; white-space: nowrap" id="olduseradddetail">
                <#if ContactAddr=="">
                暂无联系地址
                <#else>
                ${ContactAddr}
                </#if>
            </span></li>
            </ul>
        </li>
    <#if ChinapostTag?exists >
        <li><abbr>标签：</abbr>
            <div style="margin-left:15px;display: inline-block;vertical-align: top; width: 300px; text-indent: 0px;">
                <#list ChinapostTag as ChinapostTag>
                    <abbr title="${ChinapostTag.tagDesc}" style="text-indent:0px;display: inline-block; vertical-align: middle;padding: 0px 5px; height: 20px; text-align:center; line-height:20px;font-size: 8px; color:#fff;background: #ff3300;margin-right: 20px;">${ChinapostTag.tagName}</abbr>
                </#list>
            </div>
        </li>
    </#if>
    <#--<li><abbr><i>*</i>金额：</abbr><input type="text" name="UbaoName" id="UCoin"/></li>-->
        <li><abbr><i>*</i>业务类型：</abbr>
            <select id="businessType">
                <option selected="selected" >可选择</option>
            <#list businessType as businessType>
                <option value="${businessType.typeId}">${businessType.typeName}</option>
            </#list>
            </select>
        </li>


        <li><abbr>营销邮豆金额:</abbr><input style="width: 300px;border: none;" readonly="readonly" type="text"  id="ucoinMarketing" /></li>
        <li><abbr>促销邮豆金额:</abbr><input style="width: 100px;" type="text"  id="ucoinSale" maxlength="12"/></li>
        <li><abbr>备注：</abbr><input maxlength="45" type="text" name="Note" id="remark"/></li>
        <li><abbr><i>*</i>支付密码：</abbr><input type="password" name="userPassword" id="Password" /></li>
        <li style="height: 80px"><input class="allcancelButton" type="button" id="cancel" value="取消" />
            <input class="allseachButton" type="button" id="userSub" value="确定发放" />
        </li>
    </ul>

</div>


<div class="success">
    <span><img src="${bath}/static/img/ok.png" width="40" height="40" />发放成功</span>
    <a class="allcancelButton" id="detail">查看发放详情</a>
    <a class="allseachButton" id="complete">完成</a>
</div>
</div>
</body>

<script src="${bath}/static/js/oldUser.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>

</html>
