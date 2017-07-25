<#macro leftmenu basePath>
<ul class="menu_list menu_box">
    <#if firstNav == 2>
        <li class='menu_cont <#if twoNav=='1'>active</#if>''><a href='${basePath}/orderManage/getOrders'>订单列表</a></li>
        <li class='menu_cont <#if twoNav=='2'>active</#if>''><a href='${basePath}/orderManage/getBackOrders'>退单列表</a></li>
        <li class='menu_cont <#if twoNav=='3'>active</#if>''><a href='${basePath}/orderManage/getDeliveryOrders'>出库列表</a></li>
    <#elseif firstNav == 1>
        <li class='menu_cont <#if twoNav=='1'>active</#if>'><a href='${basePath}/gotoAddGoods'>上传货品</a></li>
        <li class='menu_cont <#if twoNav=='2'>active</#if>'><a href='${basePath}/goodsManager/getGoodsInfo?auditStatus=1'>货品审核列表</a></li>
        <li class='menu_cont <#if twoNav=='3'>active</#if>'><a href='${basePath}/goodsManager/getGoodsInfo?type=0&goodsInfoAdded=1'>在售货品列表</a></li>
    </#if>
</ul>
<div class="ui-footer"><a href="javascript:;"><#if (sysBasic.temp1)??>${sysBasic.temp1}</#if></a></div>
</#macro>


<#--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>店铺</title>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css?v=20151016234"/>
    <link rel="stylesheet" href="${basePath}/css/style.css?v=20151210234"/>
</head>
<body>
<div class="ui-sidebar">
    <div class="sidebar-nav">
        <ul>
            <li class="active"><a href="shop01.html">店铺管理</a></li>
            <li><a href="shop02.html">功能设置</a></li>
            <li><a href="shop03.html">店铺模板</a></li>
            <li><a href="shop04.html">银行账号验证</a></li>
            <li><a href="shop05.html">信息接收设置</a></li>
            <li><a href="shop06.html">职位管理</a></li>
            <li><a href="shop07.html">员工管理</a></li>
            <li><a href="shop08.html">品牌授权管理</a></li>
            <li><a href="shop09.html">物流管理</a></li>
            <li><a href="shop10.html">运费模板</a></li>
            <li><a href="shop11.html">图片管理</a></li>
            <li><a href="shop12.html">处罚记录</a></li>
            <li><a href="shop13.html">物流单管理</a></li>
        </ul>
    </div>
    <div class="ui-footer"><a href="javascript:;">© qianmi.com</a></div>
</div>
</body>
</html>
-->
