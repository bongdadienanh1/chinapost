<#assign bath = request.contextPath>
<link rel="stylesheet" href="${bath}/static/css/font-awesome.min.css?version=${VERSION}">
<link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
<style>
    .ctrl_nav li>a>i {
        margin:0 auto 10px;
    }
    .modal-dialog{
        margin:90px auto;
    }
    .modal-article {padding: 0 10px; height: 400px; overflow-y: scroll;}
    .modal-article p {line-height: 180%; font-size: 16px; position: relative; padding-left: 50px;}
    .modal-article p em {font-style: normal; position: absolute; top: 0; left: 0;}
    .modal-article img {display: block; width: 100%; margin: 10px auto 20px;}
    h1 {
        font-size: 18px;
        font-weight: normal;
    }
    .shop-guide-wrapper {
        width: 780px;
        margin: 0 auto;
    }
    .shop-guide-wrapper .mtitle, .shop-guide-wrapper .mbody {
        border: 1px solid #ECECEC;
    }
    .shop-guide-wrapper .mtitle {
        height: 85px;
        background-color: #F8F8F8;
        text-align: center;
        border-radius: 5px 5px 0 0;
        box-shadow: inset 0 0 10px #ECECEC;
    }
    .shop-guide-wrapper .mbody {
        height: 332px;
        border-top: 0;
        border-radius: 0 0 5px 5px;
        position: relative;
    }

    .shop-guide-wrapper .stepInsts {
        width: 770px;
        padding-top: 18px;
        margin: 0 auto;
    }
    .shop-guide-wrapper .stepInsts .stepShow {
        height: 25px;
        background-image: url(../images/guide-step-sprite.png);
        background-repeat: no-repeat;
    }
    .shop-guide-wrapper .stepInsts ul {
        *zoom: 1;
        margin-left:97px;
    }
    .shop-guide-wrapper .stepInsts ul:before, .shop-guide-wrapper .stepInsts ul:after {
        display: table;
        content: "";
        line-height: 0;
    }
    .shop-guide-wrapper .stepInsts ul:after {
        clear: both;
    }
    .shop-guide-wrapper .stepInsts ul li {
        width: 96px;
        height: 18px;
        padding: 9px 0;
        font-size: 14px;
        color: #68676C;
        line-height: 18px;
        text-align: center;
        float: left;
    }
    .shop-guide-wrapper .stepInsts ul li.past {
        color: #333;
    }
    .shop-guide-wrapper .stepInsts ul li.finish {
        color: #C00;
    }
    .shop-guide-wrapper .step-0 .stepShow {
        background-position: center 0;
    }
    .shop-guide-wrapper .step-1 .stepShow {
        background-position: center -25px;
    }
    .shop-guide-wrapper .step-2 .stepShow {
        background-position: center -50px;
    }
    .shop-guide-wrapper .step-3 .stepShow {
        background-position: center -75px;
    }
    .shop-guide-wrapper .step-4 .stepShow {
        background-position: center -100px;
    }
    .shop-guide-wrapper .step-5 .stepShow {
        background-position: center -125px;
    }
    .shop-guide-wrapper .step-6 .stepShow {
        background-position: center -150px;
    }
    .shop-guide-wrapper .step-7 .stepShow {
        background-position: center -175px;
    }
    .shop-guide-wrapper .step-8 .stepShow {
        background-position: center -200px;
    }
    .shop-guide-wrapper .center-btn-wrapper {
        text-align: center;
        margin-top: 50px;
    }
    .shop-guide-wrapper .corner-btn-wrapper {
        position: absolute;
        right: 45px;
        bottom: 20px;
    }
    .shop-guide-wrapper .inner-content {
        padding: 15px 45px 0;
    }
    .shop-guide-wrapper .inner-content .content {
        padding-left: 70px;
        height: 178px;
        overflow-y: auto;
    }
    .shop-guide-wrapper .centered-content {
        padding: 60px 0 0;
        text-align: center;
    }
    .shop-guide-wrapper .mtitle h1 {
        text-align: center;
        margin: 30px 0 0;
    }
    .shop-guide-wrapper .mbody .stepInsts {
        padding-top: 44px;
    }

</style>

<div class="page_top container-fluid Noprint">
    <div class="row">
        <div class="col-lg-4 col-md-5 col-sm-6">
            <h1 class="logo"><img alt="" width="75%" src="http://img01.ningpai.com/1426758561857.jpg" id="logo"></h1>
        </div>
        <div class="col-lg-20 col-md-19 col-sm-18">
            <div class="top_banner">
                <div class="ctrl_banner">
                    <ul class="ctrl_nav pull-right">
                        <li>
                            <a href="javascript:void(0);" onclick="goOut();">
                                <i class="icon icon-white icon-off"></i>&nbsp;退出
                            </a>
                        </li>

                    </ul>
                    <p class="version" style="color: #ffffff">标准版（2.0.5-RELEASE） </p>
                </div>

                <div class="nav_area">
                    <div class="user_data pull-right">
                        <ul>
                            <li>
                                <a href="javascript:modifyInfo();">
                                    <img class="vm mr10" id="m_img" alt="" src="${bath}/static/images/avatar.jpg"
                                             height="25" style="margin-top:-3px;"/>
                                    <font size="4">&nbsp;&nbsp;<small>${name }</small></font>

                                </a>
                            </li>
                        </ul>
                    </div>
                    <nav class="nav pull-left">
                        <ul>
                            <li <#if menuParentId == '0'>class="active"</#if>>
                                <a href="${bath}/queryMobHomePage?menuParentId=0&menuId=0">
                                    模板配置
                                </a>
                            </li>
                            <div style="display:none;">
                                    <div class="popover-content">
                                        <div class="nav_sub">
                                            <h4>模板配置</h4>
                                            <ul>
                                                <li><a href="${bath}/queryMobHomePage?menuParentId=0&menuId=0">首页模板</a></li>
                                                <li><a href="${bath}/queryMobSubject?menuParentId=0&menuId=1">专题页模板</a></li>
                                            </ul>

                                        </div>
                                    </div>
                            </div>
                            <li <#if menuParentId == '1'>class="active"</#if>>
                                <a href="${bath}/ThirdPlatformAdmin/ListPage?menuParentId=1&menuId=0">
                                    第三方管理
                                </a>
                            </li>
                            <div style="display:none;">
                                <div class="popover-content">
                                    <div class="nav_sub">
                                        <h4>平台管理</h4>
                                        <ul>
                                            <li><a href="${bath}/ThirdPlatformAdmin/ListPage?menuParentId=1&menuId=0">平台账号管理</a></li>
                                            <li><a href="${bath}/GoodsInfoAdmin/ListPage?menuParentId=1&menuId=1">平台货品管理</a></li>
                                            <li><a href="${bath}/OrderAdmin/ListPage?menuParentId=1&menuId=2">平台订单管理</a></li>
                                            <li><a href="${bath}/BackOrderAdmin/ListPage?menuParentId=1&menuId=3">平台退单管理</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="popover-content">
                                    <div class="nav_sub">
                                        <h4>货品管理</h4>
                                        <ul>
                                            <li><a href="${bath}/GoodsCategoryAdmin/ListPage?menuParentId=1&menuId=4">货品分类</a></li>
                                            <li><a href="${bath}/GoodsSpecAdmin/ListPage?menuParentId=1&menuId=5">货品规格</a></li>
                                            <li><a href="${bath}/GoodsBrand/getGoodsBrand?menuParentId=1&menuId=6">货品品牌</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <li <#if menuParentId == '2'>class="active"</#if>>
                                <a href="${bath}/PayOrderAdmin/reconciliationList?menuParentId=2&menuId=0">
                                    统计信息
                                </a>
                            </li>
                            <div style="display:none;">
                                <div class="popover-content">
                                    <div class="nav_sub">
                                        <h4>统计信息</h4>
                                        <ul>
                                            <li><a href="${bath}/PayOrderAdmin/reconciliationList?menuParentId=2&menuId=0">对账表</a></li>
                                          </ul>
                                    </div>
                                </div>
                            </div>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>




<div id="goout" class="modal fade Noprint" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">退出系统</h4>
            </div>
            <form class="form-horizontal" method="post" action="${bath}/login.htm">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-7">
                            <p class="text-muted">您确定要退出系统吗？</p>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="${bath}/static/js/jquery.min.js?version=${VERSION}"></script>
<script type="text/javascript" src="${bath}/static/js/jquery.qrcode.min.js?version=${VERSION}"></script>
<script type="text/javascript" src="${bath}/static/js/jquery.validate.js?version=${VERSION}"></script>
<script type="text/javascript" src="${bath}/static/js/artDialog4.1.7/artDialog.source.js?version=${VERSION}"></script>
<script type="text/javascript" src="${bath}/static/js/artDialog4.1.7/plugins/iframeTools.js?version=${VERSION}"></script>
<script type="text/javascript" src="${bath}/static/js/jquery.cookie.js?version=${VERSION}"></script>


<script type="text/javascript">
    $(function(){
        /* 导航弹出菜单 */
        $('.nav ul li').each(function(){
            $(this).mouseenter(function(e){
                var positionX =$(this).width()/2 - 200;
                $(this).append('<div class="menu_down popover bottom" style="position:absolute;top: 32px; left: ' + positionX + 'px; display: block;"><div class="arrow" style="left: 44%;"></div><div class="popover-content">' + $(this).next().html() + '</div></div>');
                $(this).mouseleave(function(){
                    $(this).find(".menu_down").remove();
                });
            });

        });
    })
    function goOut(){
        $('#goout').modal('show');
    };

</script>
    