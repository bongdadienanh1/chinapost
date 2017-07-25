<!DOCTYPE html>
<!-- saved from url=(0052)http://192.168.2.222:8101/mobile/customercenter.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>我的邮豆 - 账单详情</title>
    <#assign basePath=request.contextPath>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="${basePath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/myUb.css?v=201601091628">
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628">
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
</head>
<body>
<div class="content-home" style="padding-top:0px;padding-bottom: 0px;">
    <div class="member_brief member_box">
        <div class="coverTitle">
            <h2 class="title">交易成功</h2>
            <#if customerUcoinHistory[0].type=='UCOIN_CONSUME' >
                <p class="text">-
                    <#assign x = 0>
                    <#list customerUcoinHistory as list>
                        <#assign x = x+list.price >
                    </#list>
                    ${x}
                </p>
            <#elseif customerUcoinHistory[0].type=='UCOIN_DEDUCT'>
                    <p class="text">
                        <#assign x = 0>
                        <#list customerUcoinHistory as list>
                            <#assign x = x+list.price >
                        </#list>
                        ${x}
                    </p>
            <#else>
                <p class="text">+${customerUcoinHistory[0].price!''}</p>
            </#if>
        </div>
        <div class="logoBox">
            <img src=" <#if customerUcoinHistory[0].type=='UCOIN_CONSUME'>${basePath}/static/img/icon_logo_billing_details.png<#else >${customerUcoinHistory[0].enterpriseLogo!''}</#if>">
            <span style="font-weight: 300;font-size: 1.1em;font-family:'微软雅黑';margin-left: .6em;">
            <#if customerUcoinHistory[0].type=='UCOIN_CONSUME'>UDD商城<#else >${customerUcoinHistory[0].accountName!''}</#if></span>
        </div>
    </div>
    <div class="member_box">
        <div class="common_line row">
            <p>交易理由:
            <#if customerUcoinHistory[0].type=='UCOIN_CONSUME'>
                <span style="color: #666;float:none;">购物消费</span>
            <#elseif customerUcoinHistory[0].type=='UCOIN_REFUND'>
               <span style="color: #666;float:none;">邮豆退款</span>
            <#elseif customerUcoinHistory[0].type=='ENTERPRISE_GRAND'>
                <span style="color: #666;float:none;">邮豆发放</span>
            <#elseif customerUcoinHistory[0].type=='UCOIN_DEDUCT'>
                 <span style="color: #666;float:none;">邮豆扣减</span>
            </#if>
            </p>
        </div>
        <div class="common_line row">

            <p>
                <#if customerUcoinHistory[0].type=='UCOIN_CONSUME'>
                    付款商品：<span style="color: #666;float:none;">${customerUcoinHistory[0].goodsInfoNames!''}</span>
                <#else >
                    交易备注: <span style="color: #666;float:none;">${customerUcoinHistory[0].remark!''}</span>
                </#if>
            </p>
        </div>
        <#--<div class="common_line row">-->
            <#--<span class="payDetail">&nbsp;&nbsp;付款时间:</span>-->
            <#--<#list subjectList as subject>-->
                <#--<#if (subject.couponSubjectName)??>-->
                    <#--<p class="subjectName">-->
                        <#--${subject.couponSubjectName!''} U宝-->
                        <#--<span class="price">-->
                        <#--${(subject.ucoinPrice!'0')?string('0.00')}-->
                        <#--</span>-->
                    <#--</p>-->
                <#--</#if>-->
            <#--</#list>-->
        <#--</div>-->
        <div class="common_line row">
            <p>付款时间:
                <span style="color: #666;float:none;">
                    <#if (customerUcoinHistory[0].createTime)??>
                        ${(customerUcoinHistory[0].createTime)?string('yyyy-MM-dd HH:mm:ss')}
                    </#if>
                </span>
            </p>
        </div>
        <#--<div class="common_line row">-->
            <#--<p>订单号:${ucoinBillDetail.orderCode!''}</p>-->
        <#--</div>-->
    </div>
</div>
</body>
</html>