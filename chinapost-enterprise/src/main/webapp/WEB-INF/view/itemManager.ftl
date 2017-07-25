<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css" />
    <script src="${bath}/static/js/jquery.pagination.js"></script>
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/enterpriseInfo.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px) {
            .itemsTable{
                transform: scale(0.85,0.85);
                transform-origin: left top;
                -webkit-transform: scale(0.85,0.85);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.85,0.85);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.85,0.85);
                -moz-transform-origin: left top;
            }
            .itemsButton{
                transform: scale(0.9,0.9);
                transform-origin: left top;
                -webkit-transform: scale(0.9,0.9);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.9,0.9);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.9,0.9);
                -moz-transform-origin: left top;
            }
            .myitemsDetail,.update{
                line-height: 24px!important;
            }
            .itemsButton .allinputButton{
                height: 32px!important;
            }
            .itemsButton .allinputButton, .itemsButton .allclickButton, .itemsButton .allseachButton{
                font-size: 18px!important;
            }
            #selfPickItem{
                width: 120px!important;
            }
        }
        .itemsButton{
            width:1400px;
            height:140px;
            margin-left:20px;
        }
        .itemsButton li{
            width:200px;
            height:32px;
            float:left;
            margin-left:20px;
            margin-top:20px;
        }
        .itemsButton li span{
            display:inline-block;
            vertical-align:middle;
            width:120px;
            height:32px;
            line-height:32px;
            text-align:center;
        }
        .itemsButton li input{
            width:120px;
        }
        .itemsButton li select{
            width:100px;
            height:28px;
            margin-top:-2px;
            border:none;
            background:#FFF;
            color: #999;
            font-size: 16px;
        }
        #itemsSearch{
            width:120px;

        }

        #itemsOut{
            width:130px;
            height:40px;
            border:none;
            text-align:center;
            margin-left:20px;
            color:#FFF;
        }
        .itemsTable{
            width:1600px;
            height:auto;
            margin-left:20px;
        }
        .itemsTable ul{
            position: relative;
            width: 1600px;
            height: 33px;
            border-bottom: 1px solid #e5e5e5;
        }
        .itemsTable ul li{
           width: 120px;
            height: 32px;
            line-height: 32px;
            float: left;
            text-align: center;
            cursor: pointer;
        }
        .itemsTable ul li.on{
            border-bottom: 2px solid #54a6de;
        }
        .itemsList,.selfItemsList,.myItemsList{
            width:1600px;
            height:800px;
            overflow-y:auto;
            overflow-x: hidden;
            position: absolute;
            left: 0px;
            top: 30px;
            text-align: left;
        }
        .itemsList dt, .selfItemsList dt, .myItemsList dt{
            width:1600px;
            height:60px;
            color:#666666;
            border-bottom:1px solid #e5e5e5;
        }
        .itemsList dt abbr,.selfItemsList dt abbr,.myItemsList dt abbr{
            display:inline-block;
            width:100px;
            height:60px;
            text-align:left;
            padding:20px 0px;
            font-size: 16px;
        }
        .itemsList dd,.selfItemsList dd,.myItemsList dd{
            width:1600px;
            height:80px;
            background:#fff;
            border-bottom:1px solid #e5e5e5;
        }
        .itemsList dd abbr,.selfItemsList dd abbr,.myItemsList dd abbr{
            display:inline-block;
            text-align:left;
            width:100px;
            height:80px;
            vertical-align:middle;
            padding:30px 0px;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;

        }
        .myItemsList dd abbr:nth-last-child(1){
            width: 120px;
        }
        .itemsList dd abbr:nth-child(3), .selfItemsList dd abbr:nth-child(3), .myItemsList dd abbr:nth-child(3){
            vertical-align:top!important;
            font-size: 16px;
            overflow: hidden;
        }
        .itemsDetail{
            color: #54a6de;
            cursor: pointer;
        }
        .inOut{
            width:25px;
            height:25px;
            margin-left: 30px;
            text-align:center;
            border:none;
            background:#ff3300;
            color: #fff;
        }
        .onlineinOut{
            width:25px;
            height:25px;
            margin-left: 30px;
            text-align:center;
            border:none;
            background:#ff3300;
            color: #fff;
        }
        .userinOut{
            width:25px;
            height:25px;
            margin-left: 30px;
            text-align:center;
            border:none;
            background:#ff3300;
            color: #fff;
        }
        #holder{
            margin-top: 20px;
        }

        .select{
            display: inline-block;
            vertical-align: middle;
            width:130px;
            height:32px;
            margin-left:20px;
            border:1px solid #ccc;
            border-radius: 32px;
            background:#f8f8f8;
            text-align: center;
            position: relative;
        }
        .select dt{
            border-radius: 32px;
        }
        .select dt,.select dd {
            width:130px;
            height:32px;
            border:1px solid #ccc!important;
            background:#f8f8f8;
            margin-top: -1px;
            margin-left:-2px;
            cursor: pointer;
            line-height: 32px;
        }
        .arrow{
            display: inline-block;
            position: absolute;
            right: 0px;
            top: -3px;
            width: 36px;
            height: 38px;
            background:url(${bath}/static/img/com_btn_arrow.png) center no-repeat;
        }

        .select dd{
            display: none;
        }
        .select dd{
            margin-left: 5px!important;
        }
        .myitemsDetail,.update{
            color: #54a6de;
            line-height: 32px;
            text-align: center;
            cursor: pointer;
        }
        .myitemsDetail:hover,.update:hover{
            background: #54a6de;
            color: #fff;
        }
        .update{
            display: none;
        }
        .arrow2{
            display: inline-block;
            position: absolute;
            width: 20px;
            height: 30px;
            background:url(${bath}/static/img/com_btn_arrow_bg_down.png) center no-repeat;
            cursor: pointer;
        }
        #selfPickItem{
            margin-left: 20px;
        }
        .addMyItem{
            padding: 5px;
            color: #fff;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;

    </script>
    <script type="text/javascript">
        var enterpriseInfo = getCurrentEnterpriseInfo();
        var isTop = enterpriseInfo.getIsTop();
        var isEnd = enterpriseInfo.getIsEnd();
        $(document).ready(function(e) {
            var eid = ${enterprise.enterpriseId};
            var ename = "${enterprise.enterpriseName}";
            inventoryList(eid,ename);
            var isEnd=${isEnd}
            if(isEnd){
                $(".checkplace").hide()
            }
            $(document).on("click",".itemsTable ul li",function(){
                var value = $(this).val()
                if(value == "1"){
                    if(!isEnd){
                        $(".checkplace").show()
                    }
                }
                $(".allinputButton").each(function(){
                    $(this).val("");
                });
            })
            $(document).on("click",".itemsDetail",function(){
                var goodsInfoId = $(this).siblings().children(".itemsInfoId").val()
                window.open("itemListDetail?id=" + goodsInfoId + '&topShow=' + false)
            })
            $(document).on("click",".myitemsDetail",function(){
                var goodsInfoId = $(this).parent().siblings().children(".itemsInfoId").val()
                window.open("itemListDetail?id=" + goodsInfoId + '&topShow=' + false)
            })
            $(document).on("click",".update",function(){
                var goodsInfoId = $(this).parent().siblings().children(".itemsInfoId").val()
                window.location.href = 'updateSelfItems?id=' + goodsInfoId
            })
            $(document).on("mouseover",".myitemsDetail",function(){
                $(this).siblings(".update").show()
            })
            $(document).on("mouseover",".update",function(){
                $(this).show()
            })
            $(document).on("mouseout",".showmyitemsDetail",function(){
                $(this).children(".update").hide()
            })
            $(document).on("click","#selfPickItem",function(){
                var goodsInfoId = $(this).siblings().children(".itemsInfoId").val()
                window.location.href = "selfPickItmUpload"
            })
            $(document).on("click",".addMyItem",function(){
                var goodsInfoId = $(this).siblings(".itemsInfoId").val()
                var _this = this
                if( $(this).html() == '否' ){
                    console.log("!!!!!!!")
                    $.post("../web/api/goodsManager/shelves",{
                        goodsInfoId:goodsInfoId
                    },function(data){
                        if ( data.response == 'success' ){
                            response_ensure_alert('success','操作成功',function(){
                                $(_this).html("是").css("background","#54a6de")
                            });
                        }
                        else{
                            response_ensure_alert('error','操作失败',function(){
                                window.location.href = "itemManager"
                            });
                        }
                    });
                }
                else if( $(this).html() == '是' ){
                    $.post("../web/api/goodsManager/unshelves",{
                        goodsInfoId:goodsInfoId
                    },function(data){
                        if ( data.response == 'success' ){
                            response_ensure_alert('success','操作成功',function(){
                                $(_this).html("否").css("background","#ff3300")
                            });
                        }
                        else{
                            response_ensure_alert('error','操作失败',function(){
                                window.location.href = "itemManager"
                            });
                        }
                    });
                }
            })
        });

    </script>

    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
<div class="allheadstyle">
	<span>商品管理</span><abbr></abbr>
</div>

<div class="itemsButton">
    <ul>
        <li class="checkplace" style="display:none;width: 350px;"><input class="allinputButton" placeholder="${enterprise.enterpriseName}" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}"  type="hidden" class="enterpriseIdChoosen"> <input type="hidden" id="NewEnterpriseIdChoosen"  value="${enterprise.enterpriseId}"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
        <li style="width: 250px"><input class="allinputButton" placeholder="货品名称" style="width: 208px" type="text" name="itemManager_Name" /></li>
        <li style="width: 250px"><input class="allinputButton" placeholder="货品编号" style="width: 218px" type="text" name="itemManager_Number" /></li>
        <#--<li><span>品牌</span>-->
            <#--<select name="itemManager_Brand">-->
                <#--<option selected="selected">全部</option>-->
                <#--<#list brandResult as object>-->
                    <#--<option value="${object.brandId}">${object.goodsBrand}</option>-->
                <#--</#list>-->
            <#--</select>-->
        <#--</li>-->
        <li style=" color: #999; width: 250px;height: 32px!important;border-radius: 20px!important;" class="allinputButton"><span>是否上架</span>
            <select name="itemManager_Shelves">
                <option>全部</option>
                <option selected="selected" value="1">是</option>
                <option value="0">否</option>
            </select>
        </li>
        <li style=" color: #999; width: 250px;height: 32px!important;border-radius: 20px!important;" class="allinputButton checkplace2"><span>线上商城显示</span>
            <select name="itemManager_onlineShow">
                <option selected="selected">全部</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </li>
        <li style=" color: #999; width: 250px;height: 32px!important;border-radius: 20px!important;" class="allinputButton checkplace2"><span>代客商城显示</span>
            <select name="itemManager_valetShow">
                <option selected="selected">全部</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </li>
        <#--<li><span>分类</span>-->
            <#--<select name="itemManager_Type">-->
                <#--<option selected="selected">全部</option>-->
                <#--<#list typeResult as object>-->
                    <#--<option value="${object.typeId}">${object.goodsInfoTypeName}</option>-->
                <#--</#list>-->
            <#--</select>-->
        <#--</li>-->
        <#--<li><span>商家</span>-->
            <#--<select name="itemManager_ThirdName">-->
                <#--<option selected="selected">全部</option>-->
                <#--<#list thirdNameResult as object>-->
                    <#--<option value="${object.thirdId}">${object.thirdName}</option>-->
                <#--</#list>-->
            <#--</select>-->
        <#--</li>-->
        <li style="width:800px; border:none;">
            <input class="allseachButton" type="button" id="itemsSearch" value="搜索" />
            <input style="margin-left: 20px" class="allclickButton" type="button" id="outputAll" value="导出所有" />
            <input style="margin-left: 20px" class="allclickButton" type="button" id="outputPart" value="导出选中" />
            <#if isTop != "true">
                <input style="display: none" class="allclickButton" type="button" id="selfPickItem" value="上传自购商品" />
            </#if>
        </li>
    </ul>


</div>



<div class="itemsTable">
    <ul>
        <li value="0" class="on">集采商品

        </li>
        <dl class="itemsList">
            <dt><abbr style="width:30px;"><input name="itemsListCheck" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr style="margin-left: 15px;">货品规格</abbr><abbr>货品编号</abbr><abbr>商城价(邮豆)</abbr><abbr>结算价(RMB)</abbr><abbr>是否上架</abbr><abbr>线上商城显示</abbr><abbr>代客商城显示</abbr><abbr>分类</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>
            </dt>
            <div id="itemContainer"></div>
            <div style="text-align: left" class="allcpageTurnButton" id="holder"></div>
        </dl>
        <li value="1">自购商品

        </li>
        <dl style="display: none" class="selfItemsList">
            <dt><abbr style="width:30px;"><input name="selfItemsList" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr style="margin-left: 15px;">货品规格</abbr><abbr>货品编号</abbr><abbr style="margin-left: 20px;">商城价(邮豆)</abbr><abbr>结算价(RMB)</abbr><abbr>是否上架</abbr><abbr>分类</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>
            </dt>
            <div id="selfItemContainer"></div>
            <div style="text-align: left" class="allcpageTurnButton" id="selfHolder"></div>
        </dl>
        <#if isTop != "true">
        <li value="2">我的商品

        </li>
            <dl style="display: none" class="myItemsList">
                <dt><abbr style="width:30px;"><input name="myItemsList" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr style="margin-left: 15px;">货品规格</abbr><abbr>货品编号</abbr><abbr style="margin-left: 20px;">商城价(邮豆)</abbr><abbr>结算价(RMB)</abbr><abbr>是否上架</abbr><abbr>分类</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>
                </dt>
                <div id="myItemContainer"></div>
                <div style="text-align: left" class="allcpageTurnButton" id="myHolder"></div>
            </dl>
        </#if>
    </ul>


</div>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
<script src="${bath}/static/js/itemManager.js?version=${VERSION}"></script>
</body>
<script>
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        enterSearch( $("input[name='itemManager_Name'],input[name='itemManager_Number']"),$("#itemsSearch"))
        $(document).on("mouseover",".select",function(){
            $(this).children("dd").show()
        })
        $(document).on("mouseout",".select",function(){
            $(this).children("dd").hide()
        })
        $(document).on("click",".itemsList dt input[name='itemsListCheck']",function(){
            if($(".itemsList dt input[name='itemsListCheck']").is(':checked')) {
                $(".itemsList dd input[name='itemsListCheck']:not(:checked)").each(function () {
                    $(this).trigger("click")
                });
            }else if(!$(".itemsList dt input[name='itemsListCheck']").is(':checked')){
                $(".itemsList dd input[name='itemsListCheck']:checked").each(function () {
                    $(this).trigger("click")
                });
            }

        })
        $(document).on("click",".selfItemsList dt input[name='selfItemsList']",function(){
            if($(".selfItemsList dt input[name='selfItemsList']").is(':checked')) {
                $(".selfItemsList dd input[name='selfItemsList']:not(:checked)").each(function () {
                    $(this).trigger("click")
                });
            }else if(!$(".selfItemsList dt input[name='selfItemsList']").is(':checked')){
                $(".selfItemsList dd input[name='selfItemsList']:checked").each(function () {
                    $(this).trigger("click")
                });
            }

        })
        $(document).on("click",".myItemsList dt input[name='myItemsList']",function(){
            if($(".myItemsList dt input[name='myItemsList']").is(':checked')) {
                $(".myItemsList dd input[name='myItemsList']:not(:checked)").each(function () {
                    $(this).trigger("click")
                });
            }else if(!$(".myItemsList dt input[name='myItemsList']").is(':checked')){
                $(".myItemsList dd input[name='myItemsList']:checked").each(function () {
                    $(this).trigger("click")
                });
            }

        })
    })
</script>
</html>
