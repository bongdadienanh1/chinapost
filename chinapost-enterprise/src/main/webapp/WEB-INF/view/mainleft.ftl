<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/mainleft.css?version=${VERSION}"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/enterpriseInfo.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".list ul li a").click(function(){
		    $(this).parent("li").addClass("clickOn").siblings("li").removeClass("clickOn");
    });

});


</script>
<title>左边导航</title>
</head>

<body>
<#list SonWealthResult as son>
    <div style="display: none;" class="ucoinAll">${son.wealth}</div>
</#list>
<div class="list">
	<ul>
        <#list currentPages as page>
            <#if isEnd == "true">
                <#if page.getDesignation() != '请款单据' && page.getDesignation() != '账单管理'>
                    <li class="edit"><i></i><a href="..${page.getUrl()}" class="a_click" target="mainright">${page.getDesignation()}</a>
                    </li>
                </#if>
            <#else>
                <li class="edit"><i></i><a href="..${page.getUrl()}" class="a_click" target="mainright">${page.getDesignation()}</a>
                </li>
            </#if>

        </#list>
	</ul>
    <input type="button" style="display: none;" id="jump">
</div>
</body>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        $("#jump").bind('click', function(){
            $("#itemlist").trigger("click")
        });
       $(".edit").each(function(){
            var href = $(this).find("a").prop("href").split("web/")[1].toLocaleLowerCase();
            $(this).attr("id",href);
       });
        $(".list li").click(function(){
            $(".list ul li#account i").css("background","url(${bath}/static/img/com_btn_account_nor.png) center no-repeat");
            $(".list ul li#hiemanager i").css("background","url(${bath}/static/img/com_btn_level_nor.png) center no-repeat");
            $(".list ul li#rolemanager i").css("background","url(${bath}/static/img/com_btn_role_nor.png) center no-repeat");
            $(".list ul li#membermanager i").css("background","url(${bath}/static/img/com_btn_member_nor.png) center no-repeat");
            $(".list ul li#itemmanager i").css("background","url(${bath}/static/img/com_btn_goods_nor.png) center no-repeat");
            $(".list ul li#ucoinmanager i").css("background","url(${bath}/static/img/com_btn_wealth_nor.png) center no-repeat");
            $(".list ul li#ubaosend i").css("background","url(${bath}/static/img/com_btn_youbao_nor.png) center no-repeat");
            $(".list ul li#itemlist i").css("background","url(${bath}/static/img/com_btn_user_nor.png) center no-repeat");
            $(".list ul li#accountmanager i").css("background","url(${bath}/static/img/com_btn_order_nor.png) center no-repeat");
            $(".list ul li#requisition i").css("background","url(${bath}/static/img/com_btn_request_nor.png) center no-repeat");
            $(".list ul li#billmanager i").css("background","url(${bath}/static/img/com_btn_bill_nor.png) center no-repeat");
            $(".list ul li#inventorymanager i").css("background","url(${bath}/static/img/com_btn_stock_nor.png) center no-repeat");
            $(".list ul li#businesstype i").css("background","url(${bath}/static/img/com_btn_businessType_nor.png) center no-repeat");
            $(".list ul li#formcenter i").css("background","url(${bath}/static/img/com_btn_formCenter_nor.png) center no-repeat");
            $(".list ul li#logmanager i").css("background","url(${bath}/static/img/com_btn_logManager_nor.png) center no-repeat");
            var lion=$(".clickOn a").html();
            if(lion=="账号中心"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_account_hover.png) center no-repeat")
            }else if(lion=="层级管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_level_hover.png) center no-repeat")
            }
            else if(lion=="角色管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_role_hover.png) center no-repeat")
            }
            else if(lion=="会员管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_member_hover.png) center no-repeat")
            }
            else if(lion=="商品管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_goods_hover.png) center no-repeat")
            }
            else if(lion=="财富管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_wealth_hover.png) center no-repeat")
            }
            else if(lion=="邮豆发放"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_youbao_hover.png) center no-repeat")
            }
            else if(lion=="用户提货"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_user_hover.png) center no-repeat")
            }
            else if(lion=="订单管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_order_hover.png) center no-repeat")
            }
            else if(lion=="请款单据"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_request_hover.png) center no-repeat")
            }
            else if(lion=="账单管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_bill_hover.png) center no-repeat")
            }
            else if(lion=="库存管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_stock_hover.png) center no-repeat")
            }
            else if(lion=="系统设置"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_businessType_hover.png) center no-repeat")
            }
            else if(lion=="报表中心"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_formCenter_hover_nor.png) center no-repeat")
            }
            else if(lion=="日志管理"){
                $(".clickOn i").css("background","url(${bath}/static/img/com_btn_logManager_hover_nor.png) center no-repeat")
            }

        })
        var wealth_undo = ${wealth_undo}
        var myUCoin = ${myUCoin}
       if((parseInt(wealth_undo)+parseInt(myUCoin)) < 0 ){
           var html = ""
           html += '<img id="yuebuzu"  style="position:absolute;right:5%;top:15px;" src="${bath}/static/img/yuebuzu.png" >'
           $("#ucoinmanager").append(html);
       }
        var lockMoney = false
        $(".ucoinAll").each(function(){
            if( parseInt($(this).html()) < 0 ){
                lockMoney = true
            }
        })
        if (lockMoney){
            var html = '<img id="yuebuzu" style="position:absolute;right:5%;top:15px;" src="${bath}/static/img/yuebuzu.png" >'
            $("#ucoinmanager").append(html);
        }
    });

</script>

</html>
