<!DOCTYPE html>
<html lang="zh-cn">

<head>
<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
<#if (sys.bsetName)??>
    <title><#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>列表页--${sys.bsetName}</title>
<#else>
    <title><#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>列表页--${seo.mete}</title>
</#if>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201706151657"/>
</head>
<body>
<script src="${basePath}/static/js/jquery.js"></script>
<input type="hidden" id="show_status" value="${showStatus!""}">
<script>
    $(function() {
        //列表条形块形样式切换
        $('.style-trans').click(function(){
            if($("#showStatus").val()==0){
                $("#showStatus").val(1);
            }else{
                $("#showStatus").val(0);
            }
            if($('.list-line').length > 0){
                $('.list-line').addClass('list-box').removeClass('list-line');
                $(this).find('i.list-icon').addClass('list-2').removeClass('list-1');
            }
            else{
                $('.list-box').addClass('list-line').removeClass('list-box');
                $(this).find('i.list-icon').addClass('list-1').removeClass('list-2');
            }
        });
        if ($("#show_status").val() == 1) {
            $('.style-trans').click();
        }
    });
</script>

<div class="bar-top" style="position:static">
    <div class="search complete_search" style="position:static">
        <div class="search_box">
            <div class="s_left">
                <a href="javascript:;" onClick="javascript:history.back(-1);" class="backto_page">
                    <i class="ion-ios-arrow-left"></i>
                </a>
            </div>
            <div class="search_form">
                <i class="ion-ios-search-strong"></i>
                <form action="${basePath}/getInventorySearchProductList.htm" method="get" id="searchProductForm" >
                    <input id="searchInput"  value="${goodsInfoName!''}" placeholder="请输入商品关键字搜索"  name="goodsInfoName"  type="text">
                    <#--<input type="hidden" value="${storeId!""}" name="storeId" id="storeId">-->
                </form>
            </div>
            <div class="s_right">
                <a href="javascript:void(0);" id="searchBtn" class="search_btn">搜索</a>
            </div>
        </div>
    </div>

</div>
<#--<div class="bar-top" style="position:static">-->
    <#--<a val="5D" href="javascript:void(0);" class="bar-item filter-item change_sort <#if sort='' || sort='5D'>active</#if>"  filter_default ">默认</a>-->
    <#--<a val="2D" href="javascript:void(0);" class="bar-item filter-item change_sort <#if sort!='' && sort='22D' || sort='2D' >active</#if>"  filter-sales">销量<#if sort!='' && sort='22D' ><i class="sharp-up"></i></#if><#if sort!='' && sort='2D' ><i class="sharp-down"></i></#if></a>-->
    <#--<a val="1D" href="javascript:void(0);" class="bar-item filter-item change_sort <#if sort!='' && sort='11D' || sort='1D'>active</#if>"  filter-price">价格<#if sort!='' && sort='11D' ><i class="sharp-up"></i></#if><#if sort!='' && sort='1D' ><i class="sharp-down"></i></#if></a>-->
    <#--<a href="javascript:void(0);" class="bar-item style-trans"><i class="list-icon list-1"></i></a>-->
<#--</div>-->

<#--<input type="hidden" value="<#if keyWords??>${keyWords}</#if>" id="keywords_v">-->
<div class="content"  style="padding-top:0;" >
    <div class="prolist list-line">
    <#if pageBean.content??>
        <#if pageBean.content?size!=0>
        <#list pageBean.content as product>

              <div class="list-item">
                <a href="${basePath}/productdetail?productId=${product.goodsInfoId}">
                    <div class="propic">

                        <img src="${product.goodsInfoImgId!''}" alt="${product.goodsInfoName!''}"/>
                    </div>



                    <div class="prodesc">
                        <#if product.goodsInfoName?? && product.goodsInfoName?length gt 15>
                            <h3 class="title">${product.goodsInfoName?substring(0,15)}...</h3>
                        <#else >
                            <h3 class="title">${product.goodsInfoName!''}</h3>
                        </#if>
                        <p style="bottom: 0px;" class="price"><span>${product.goodsInfoPreferPrice?string('0.00')}</span>邮豆</p>
                    </div>
                </a>
            </div>
        </#list>
        <#else>
            <div class="no_tips">
            <p>木有搜索到商品哦</p>
        </div>
        </#if>
    </#if>
        <div class="slideBar">
            <div class="slideBar-item sTop"><i></i></div>
        </div>
    </div>
    <div id="showmore" class="list-loading"></div>

</div>

<#--<div class="hide">-->
    <#--<form action="searchProduct.htm" id="searchForm" method="post">-->
        <#--<div class="filterList">-->
            <#--<ul class="clearfix">-->
            <#--<#if storeId??&&storeId!=0>-->
                <#--<input type="hidden" name="storeId" value="${storeId}">-->
            <#--</#if>-->
                <#--<input type="hidden" name="keyWords" class="title" value="${keyWords!''}">-->
                <#--<input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">-->
                <#--<input type="hidden" name="sort" class="list_sort" value="${sort!''}">-->
                <#--<input type="hidden" name="showStock" class="show_stock" value="${showStock!''}">-->
                <#--<input type="hidden" name="showStatus" id="showStatus" value="0">-->
            <#--</ul>-->
        <#--</div>-->
        <#--<!--/filterList&ndash;&gt;-->
    <#--</form>-->
<#--</div>-->
<#--<input class="storeId" type="hidden" value="${storeId!""}">-->
<#--<input type="hidden" value="${wareId!""}" id="wareId_v">-->

<#include "../common/smart_menu.ftl" />
<input type="hidden" id="basePath" value="${basePath}">
<input type="hidden" value="${pageBean.number!""}" id="pageNum">
<input type="hidden" value="0" id="status">
<script>
    $(function(){
        //回到顶部
        $('.sTop>i').click(function(){
            $('html,body').animate({scrollTop: 0}, 800);
        });
        /* 显示隐藏搜索 */
        $('.search-pro').click(function(){
            $('.pro-search-box').show();

        });

        $('.search_btn').click(function(){
            checkCookie($("#searchInput").val());
            $("#storeId").val($(".storeId").val());
            $("#searchProductForm").submit();
        });
        $('.search-cancel').click(function(){
            $('.pro-search-box').hide();
        });
        /* 商品列表排序 */

        //默认排序
        $('.filter_default').click(function(){
            $('.filter-item').removeClass('active').find('i').remove();
            $(this).addClass('active');
        });

        //销量排序
        $('.filter-sales').click(function(){
            $('.filter-item').removeClass('active').find('i').remove();
            $(this).addClass('active');
            $(this).append('<i class="sharp-down"></i>');
        });

        //价格排序
        $('.filter-price').click(function(){
            if($(this).find('i').length == 0){
                $('.filter-item').removeClass('active').find('i').remove();
                $(this).addClass('active');
                $(this).append('<i class="sharp-up"></i>');
            }
            else if($(this).find('i').attr('class') == 'sharp-up'){
                $(this).find('i').attr('class','sharp-down');
            }
            else{
                $(this).find('i').attr('class','sharp-up');
            }
        });







    });

    window.onload=function()//用window的onload事件，窗体加载完毕的时候
    {
        $("#status").val(0);
        $("#pageNum").val(1);
        //do something
    }

    $(window).scroll(function(){
        if($("#status").val()==1){
            return null;
        }
        if($(this).scrollTop() >= ($('body').height() - $(window).height())) {
            var pageNum = $("#pageNum").val();
            pageNum++;
            $("#pageNum").val(pageNum);
            $.ajax({
                type: "POST",
                url: "${basePath}/getInventorySearchProductListAjax",
                beforeSend: showLoadingImg,
                error: showFailure,
                data: {
                    page: pageNum,
                    goodsInfoName: $('#searchInput').val()
                },
                success: function (msg) {
                    if (msg.data.content.length == 0) {
                        $("#status").val(1);
                        $('#showmore').html('已无更多结果');
                    }
                    else {
                        var str = '';
                        var goodsName = "";
                        msg.data.content.map(function (data) {
                            str += '<div class="list-item">'
                            + '<a href="${basePath}/productdetail?productId=' + data.goodsInfoId + '">'
                            + '<div class="propic">'
                            + '<img src="' + data.goodsInfoImgId + '" alt="' + data.goodsInfoName + '"/>'
                            + '</div>'
                            + '<div class="prodesc">';
                            if (data.goodsInfoName.length > 15) {
                                goodsName = data.goodsInfoName.substring(0, 15) + "...";
                            } else {
                                goodsName = data.goodsInfoName;
                            }
                            str += '<h3 class="title">' + goodsName + '</h3> ';
                            str += '<p style="bottom: 0px;" class="price">'
                            + '<span>' + data.goodsInfoPreferPrice.toFixed(2) + '</span>邮豆'
                            + '</p></div></a> </div>';
                        })
                        $(".prolist").append(str);
                    }
                }
            });
        }
    });

    function showLoadingImg() {
        $('#showmore').html(' <img alt="" src="${basePath}/static/img/loading.gif"> <span>加载中……</span>');
    }

    function showFailure() {
        $('#showmore').html('<font color=red> 获取查询数据出错 </font>');
    }


</script>
</body>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath}/static/js/bootstrap.min.js"></script>
<script src="${basePath}/static/js/fastclick.min.js"></script>
<script src="${basePath}/static/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/static/js/jquery.keleyi.js"></script>
<script src="${basePath}/static/js/jquery.lazyload.js"></script>
<script src="${basePath}/static/js/goods/goods_list.js"></script>
<script src="${basePath}/static/js/goods/goods_pro.js"></script>
</html>