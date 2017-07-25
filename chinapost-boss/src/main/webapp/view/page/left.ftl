
<style>
    .menu_item .menu_body ul li{
        padding-left:15px;
    }
    .sidebar{min-height:427px;}
    .menu_item .menu_title h4 a{background:#fff;}
    .menu_item .menu_title h4 a.active{background:#DCE6F0;}
</style>
<div class="col-lg-4 col-md-5 col-sm-6 sidebar">

    <div class="menu">
        <#if menuParentId ==0>
            <div class="menu_item">
                <div class="menu_title">
                    <h4><a href="javascript:;" <#if menuParentId == '0'>class="active"</#if>><span class="glyphicon glyphicon-th"></span> 模板设置</a></h4>
                </div>
                <div class="menu_body">
                    <ul>
                        <li <#if menuId == '0'>class="active"</#if>><a href="queryMobHomePage?menuParentId=0&menuId=0">首页模板</a></li>
                        <li <#if menuId == '1'>class="active"</#if>><a href="queryMobSubject?menuParentId=0&menuId=1&menuId=1">专题模板</a></li>
                    </ul>
                </div>
            </div>
        <#elseif menuParentId == 1>
            <div class="menu_item">
                <div class="menu_title">
                    <h4><a href="javascript:;" <#if menuParentId == '0'>class="active"</#if>><span class="glyphicon glyphicon-th"></span> 平台管理</a></h4>
                </div>
                <div class="menu_body">
                    <ul>
                        <li <#if menuId == '0'>class="active"</#if>><a href="${bath}/ThirdPlatformAdmin/ListPage?menuParentId=1&menuId=0">平台账号管理</a></li>
                        <li <#if menuId == '1'>class="active"</#if>><a href="${bath}/GoodsInfoAdmin/ListPage?menuParentId=1&menuId=1">平台货品管理</a></li>
                        <li <#if menuId == '2'>class="active"</#if>><a href="${bath}/OrderAdmin/ListPage?menuParentId=1&menuId=2">平台订单管理</a></li>
                        <li <#if menuId == '3'>class="active"</#if>><a href="${bath}/BackOrderAdmin/ListPage?menuParentId=1&menuId=3">平台退单管理</a></li>
                    </ul>
                </div>
            </div>
            <div class="menu_item">
                    <div class="menu_title">
                        <h4><a href="javascript:;" <#if menuParentId == '0'>class="active"</#if>><span class="glyphicon glyphicon-th"></span> 货品管理</a></h4>
                    </div>
                    <div class="menu_body">
                        <ul>
                            <li <#if menuId == '4'>class="active"</#if>><a href="${bath}/GoodsCategoryAdmin/ListPage?menuParentId=1&menuId=4">货品分类</a></li>
                            <li <#if menuId == '5'>class="active"</#if>><a href="${bath}/GoodsSpecAdmin/ListPage?menuParentId=1&menuId=5">货品规格</a></li>
                            <li <#if menuId == '6'>class="active"</#if>><a href="${bath}/GoodsBrand/getGoodsBrand?menuParentId=1&menuId=6">货品品牌</a></li>
                        </ul>
                    </div>
                </div>
        <#else>
            <div class="menu_item">
                <div class="menu_title">
                    <h4><a href="javascript:;" <#if menuParentId == '2'>class="active"</#if>><span class="glyphicon glyphicon-th"></span>统计信息</a></h4>
                </div>
                <div class="menu_body">
                    <ul>
                        <li <#if menuId == '0'>class="active"</#if>><a href="${bath}/PayOrderAdmin/reconciliationList?menuParentId=2&menuId=0">对账表</a></li>
                    </ul>
                </div>
            </div>
        </#if>
    </div>
        <#--<div class="menu_item">-->
            <#--<div class="menu_body">-->
            <#--<ul>-->
                <#--<li class="active"><a href="queryMobHomePage.htm">首页模板</a></li>-->
                <#--<li <#if menuId == '0'>class="active"</#if>><a href="">专题页模板</a></li>-->
            <#--</ul>-->
        <#--</div>-->
    <#--</div>-->

</div>
