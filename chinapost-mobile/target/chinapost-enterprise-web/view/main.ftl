<html lang="zh-cn">
<head>
    <#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="UDD商城">
    <meta name="description" content="UDD商城">
    <title>UDD商城</title>

    <link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/css/style.css" rel="stylesheet"/>
    <!-- Bootstrap -->
    <link href="${basePath}/static/css/idangerous.swiper.css" rel="stylesheet">

    <link href="${basePath}/static/css/style.min.css?v=201601091628" rel="stylesheet">
    <link href="${basePath}/static/css/ui-dialog.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/css/index.min.css"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/idangerous.swiper.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
    <![endif]-->
    <style>
        .curtain_wp {background:url(${basePath}/static/img/curtain_word.png) #2589c9 no-repeat center 30%; background-size:50% auto;}
        .curtain_wp p {position:absolute; width:100%; left:0; bottom:10%; text-align:center; color:#fff; font-family:Arial; font-size:16px;}
    </style>
    <script type="text/javascript">
        //basePath值为''时，重新静态化
        //if($("#basePath").val()==""){
        //	location = "toStaticizeIndex.htm";
        //}

        function browserRedirect() {
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) == "android";
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
            if(bIsIpad){
                location = "http://www.ylife.cn";
            }
            if (!(bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
                //跳转到pc版
                //location = "http://www.ylife.cn";
            }
        }
        //根据设备跳转
        browserRedirect();
        //显示主体
        function showmain(){
            $(".ps-head").removeAttr("style");
            $("#main").fadeIn(function(){
                //设置全屏轮播
                var fullRolls = $(".fullRoll");
                if(fullRolls.length>0){
                    $(".more_goods").hide();
                    $(".foot").hide();
                    $(".smart_menu").hide();

                    var item = $(".app_item");
                    for(var i=0;i<item.length;i++){
                        var n = $(item[i]).find(".fullRoll").length;
                        if(!n){
                            $(item[i]).hide();
                        }
                    }

                    for(var i=0;i<fullRolls.length;i++){
                        var sd = $(fullRolls[i]).find(".fullRollSD").val();
                        if(sd == "sh"){
                            mySwiper = new Swiper('.full-swiper-container',{
                                mode:'horizontal',
                                loop:true,
                                pagination: '.full-pagination',
                                paginationClickable: true,
                            });
                            $(fullRolls[i]).find(".full-pagination").show();
                            $(fullRolls[i]).find(".vtc-img").hide();
                        } else {
                            mySwiper = new Swiper('.full-swiper-container',{
                                mode:'vertical',
                                loop:true,
                                pagination: '.full-pagination',
                                paginationClickable: true,
                            });
                            $(fullRolls[i]).find(".full-pagination").hide();
                            $(fullRolls[i]).find(".vtc-img").show().delay(3000).fadeOut(500);
                        };

                        //控制音乐播放
                        $(".bgd_music a").click(function(){
                            var obj = $(this),
                                    audio = document.getElementById('bg_music');
                            if(audio.paused){
                                audio.play();
                                obj.html('暂停');
                                obj.removeClass("music_pause");
                                return;
                            }
                            audio.pause();
                            obj.html('播放');
                            obj.addClass('music_pause');
                        });
                    }
                }
                //轮播
                var rolls = $(".roll_adv");
                for(var i=0;i<rolls.length;i++){
                    var rollId = "#"+$(rolls[i]).prop("id");
                    $(rollId +' .roll_banner,'+rollId+' .roll_banner img').css('height',parseInt(($(window).width() * 35) / 72) + 'px');
                    var mySwiper = new Swiper(rollId + ' .swiper-container',{
                        pagination: '.swiper-pagination',
                        loop:true,
                        grabCursor: true,
                        autoplay : 3000
                    });
                }
                //文字
                var texts = $(".text_app");
                for(var i=0;i<texts.length;i++){
                    var text = $(texts[i]);
                    var textid = text.prop("id");
                    var textCont = $("#textCont"+textid.substring(8)).text();
                    text.html(textCont);
                }
            });
        }


        //站点设置信息不显示缓冲页
        //不显示缓冲页
        $(function(){
            showmain();
        });


    </script>
</head>
<body>
<style>
    .ps-head{padding:0.5em;background: url(http://st.360buyimg.com/m/images/index/header-bg.png?v=2) repeat-x #efefef;  background-size: 100% 44px;}
    .ps-back{width: 15%; float: left; text-align: center;}
    .ps-back a{ display: block; background: url(images/arrow_left.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em;  text-indent: -99999px;}

    .ps-search{border:1px solid #d6d6d6; border-radius: 4px; height: 2.3em; margin-top: 0.2em; width:100%;}
    .ps-search input{ width: 85%;height: 2.1em;
        background:#fff; border:none;margin-top:0;float:left;padding-left:0.5em}
    .ps-search button{float:right;width:15%;background: url(../images/search.png) no-repeat center center; text-indent:1.8em;
        background-size: auto 50%; border:none; cursor: pointer; height: 2.1em; border-left:1px solid #d6d6d6;text-indent: -99999px;}
    .ps-clk{ width: 15%; float: left; text-align: center; height: 2.8em;}
    .ps-clk a{display: block; background: url(imagesst.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em; text-indent: -99999px;}
    .more_goods{border-top:1px solid #f5f5f5;background:#fff;}
    .more_goods a{display:block;padding:0.8em 0; text-align:center;font-size:1.2em;}
</style>

<#--<div id="seckilling"  class="app_item app_cube" style="display: none;">-->
    <#--<div class="app_itemTitle">-->
        <#--<h1>&nbsp;&nbsp;限时秒杀</h1>-->

        <#--<div class="app_right1" id="countdown">-->
            <#--<span class="text">距开始还剩</span>-->
            <#--<span class="num hour">03</span>-->
            <#--<span class="dot"></span>-->
            <#--<span class="num min">16</span>-->
            <#--<span class="dot"></span>-->
            <#--<span class="num second">20</span>-->
        <#--</div>-->
    <#--</div>-->
    <#--<ul class="app-goodTime clearfix gd-02">-->
        <#--<li class="magin-left">-->
            <#--<a href="/mobile/item/4328.html"><img src="http://img01.ningpai.com/1447471027568.jpg!160"></a>-->
            <#--<div class="goods-info">-->
                <#--<h3>大润窑 汝窑8头茶具</h3>-->
                <#--<span  goodsproductPrice="￥199" class="good-price goodsPromotionPrice">￥89</span>-->
                <#--<span  class="price-text goodsproductPrice">原价</span>-->
                <#--<s  class="original-price goodsproductPrice">￥:199</s>-->
            <#--</div>-->
        <#--</li>-->
        <#--<li class="magin-left">-->
            <#--<a href="/mobile/item/4329.html"><img src="http://img01.ningpai.com/1447470585711.jpg!160"></a>-->
            <#--<div class="goods-info">-->
                <#--<h3>大润窑 哥窑浮雕8头茶具</h3>-->
                <#--<span  goodsproductPrice="￥199" class="good-price goodsPromotionPrice">￥99</span>-->
                <#--<span  class="price-text goodsproductPrice">原价</span>-->
                <#--<s  class="original-price goodsproductPrice">￥:199</s>-->
            <#--</div>-->
        <#--</li>-->
    <#--</ul>-->
<#--</div>-->

<!--<div class="search">
    <div class="search_bg"></div>
    <div class="search_box">
        <div class="s_left">
            <img alt="" src="/mobile/images/qianmi_logo.png">
            <a href="javascript:;" class="backto_page">×</a>
        </div>
        <form action="/mobile/searchProduct.htm" method="get" id="searchProductForm" >
            <div class="search_form">
                <i class="ion-ios-search-strong"></i>
                <input id="searchInput"   name="keyWords" type="text"  placeholder="搜索商品">
            </div>
            <div class="s_right">

                <a href="member/notice1.html"><i class="iconfont icon-xiaoxi2"></i></a>
                <a href="pro-list.html" class="search_btn">搜索</a>
            </div>
        </form>
    </div>-->

<#--<div class="search">-->
    <#--<div class="search_bg"></div>-->
    <#--<form action="/mobile/searchProduct.htm" method="get" id="searchProductForm" >-->
        <#--<div class="search_box">-->
            <#--<div class="s_left">-->
                <#--<img alt="" onclick="toCates()" src="/mobile/images/qianmi_logo.png">-->
                <#--<a href="javascript:;" class="backto_page">×</a>-->
            <#--</div>-->
            <#--<div class="search_form">-->
                <#--<i class="ion-ios-search-strong"></i>-->
                <#--<input id="searchInput"   name="keyWords" type="text"  placeholder="搜索商品">-->
            <#--</div>-->
            <#--<div class="s_right">-->

                <#--<a href="/mobile/adminnotice.html" onclick="toAdminnotice()"><i  class="iconfont icon-xiaoxi2"></i></a>-->
                <#--<a href="javascript:" id="search_btn" class="search_btn">搜索</a>-->
            <#--</div>-->
        <#--</div>-->
    <#--</form>-->
    <#--<div class="search_page">-->
        <#--<div class="search_history">-->
            <#--<h4>历史搜索</h4>-->
            <#--<ul id="historySearch">-->

            <#--</ul>-->
            <#--<div class="clear_history">-->
                <#--<button class="btn btn-full-grey" onclick="clearCookie('searchTitle')">清空搜索历史记录</button>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--</div>-->
<script type="text/javascript">
    $(function(){
        getList();

        $('#searchInput').on("keydown", function(event) {
            var key = event.which;
            if (key == 13) {
                $("#search_btn").click();
            }
        });
        $("#search_btn").click(function(){
            $("#storeId").val($(".storeId").val());
            checkCookie( $("#searchInput").val());
            $("#searchProductForm").submit();
        })
    })
    function getList(){
        var title=getCookie("searchTitle");
        var list=spiltCookie(title);
        var str="";
        var count=0;
        for(var i =0;i<list.length;i++){

            if(count <9&&list[i]!=""){
                count++;
                str+='<li><a onclick="subForm(this)" attr-value="'+decodeURIComponent(list[i])+'" href="javascript:;">'+decodeURIComponent(list[i])+'</a></li>';
            }
        }
        $("#historySearch").html(str);
    }

    function toCates()
    {
        window.location.href="/mobile/cates.html";
    }

    function toAdminnotice()
    {
        window.location.href="/mobile/adminnotice.html";
    }

    function subForm(obj){
        $("#searchInput").val($(obj).attr("attr-value"));
        $("#searchProductForm").submit();
    }
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }
    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    }
    //清除cookie
    function clearCookie(name) {
        setCookie(name, "", -1);
        getList();
    }
    function checkCookie(obj) {
        var title=getCookie("searchTitle");
        var list=spiltCookie(title);
        var str="";

        var count=0;
        for(var i =0;i<list.length;i++){
            if(count<8){
                str+=list[i]+'==='
                if(list[i]==encodeURI(obj)) {
                    obj="";
                }
                count++;
            }

        }
        str=encodeURI(obj)+"==="+str;
        setCookie("searchTitle",str,1);
    }

    function spiltCookie(dValue){
        var strs= new Array(); //定义一数组
        strs=dValue.split("===");
        var list= new Array(); //定义一数组
        for(var i =strs.length-1;i>=0;i--){
            if(strs[i]!=""){
                list.push(strs[i]);
            }
        }
        return strs;
    }

</script>


<script>
    $(function(){



    });

    /* roll_banner ad */
    $('#roll_banner1').height($(window).width()/(720/396));
    var rollAd1 = new Swiper('#roll_banner1 .swiper-container',{
        pagination: '#roll_banner1 .pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true
    });

    var rollAd2 = new Swiper('#roll_banner2 .swiper-container',{
        pagination: '#roll_banner2 .pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true
    });

    var rollAd3 = new Swiper('#roll_banner3 .swiper-container',{
        pagination: '#roll_banner3 .pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true
    });

    var rollAd4 = new Swiper('#roll_banner4 .swiper-container',{
        pagination: '#roll_banner4 .pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true
    });

    var rollAd5 = new Swiper('#roll_banner5 .swiper-container',{
        pagination: '#roll_banner5 .pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true
    });

    /* search */
    $('#searchInput').focus(function(){
        $("#ip_cont").hide();
        $('.search').addClass('complete_search');
        $(".search").css('position','absolute');
        $('.search_box').css('backgroundColor','#dfdfdf');
        $('.search_page').height($(window).height()-46);
        $('.backto_page').click(function(){
            $("#ip_cont").show();
            $(".search").css('position','fixed');
            $('.search').removeClass('complete_search');
            $('#searchInput').blur();
            if($(window).scrollTop()<500){
                $('.search_box').css('backgroundColor','rgba(255,76,100,'+ $(window).scrollTop()*0.002 +')');
            }
            else{
                $('.search_box').css('backgroundColor','rgba(255,76,100,1)');
            }
        });
    });

    /* search end */

</script>
<script>
    $(function(){
        $(".bar-bottom a:eq(0)").addClass("selected");
    })
</script>
<div class="curtain_wp">
</div><!--/curtain_wp-->
<div id="main" style="display: none;">

    <input class="storeId" type="hidden" value="0">
    <!--引用静态页面-->
    <div id="ip_cont" class="ip_cont">

        <div class="app_item app_cube" style="display: block;">
            <div class="roll_adv item" id="roll_adv1">
                <div name="roll_banner" class="roll_banner">
                    <div class="swiper-container">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121717099.jpg" width="100%" widthB="100%" height="50%" heightB="50%" class="" style=""></a>
                            </div>
                            <div class="swiper-slide">
                                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121748906.jpg" width="100%" widthB="100%" height="50%" heightB="50%" class="" style=""></a>
                            </div>
                            <div class="swiper-slide">
                                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121759313.jpg" width="100%" widthB="100%" height="50%" heightB="50%" class="" style=""></a>
                            </div>
                            <div class="swiper-slide">
                                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121780647.jpg" width="100%" widthB="100%" height="50%" heightB="50%" class="" style=""></a>
                            </div>
                            <div class="swiper-slide">
                                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470214605719.jpg" width="100%" widthB="100%" height="50%" heightB="50%" class="" style=""></a>
                            </div>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_1">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470214673650.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative;" id="app_cont31">
                <a href="${basePath}/subject?subjectId=72"><img id="img1_1_1_1" src="http://img01.ylife.cn/1470121563741.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=88"><img id="img1_2_1_1" src="http://img01.ylife.cn/1470121573650.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:25.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=91"><img id="img1_3_1_1" src="http://img01.ylife.cn/1470121585830.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=71"><img id="img1_4_1_1" src="http://img01.ylife.cn/1470121593978.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:75.0%;top:0.0%;"></a><a href=""><img id="img2_1_1_1" src="http://img01.ylife.cn/1470121605799.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:0.0%;top:25.0%;"></a><a href="${basePath}/subject?subjectId=80"><img id="img2_1_1_1" src="http://img01.ylife.cn/1470121616878.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:0.0%;top:25.0%;"></a><a href="${basePath}/subject?subjectId=93"><img id="img2_2_1_1" src="http://img01.ylife.cn/1470121626297.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:25.0%;top:25.0%;"></a><a href="${basePath}/subject?subjectId=75"><img id="img2_3_1_1" src="http://img01.ylife.cn/1470121634577.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:50.0%;top:25.0%;"></a><a href="${basePath}/subject?subjectId=92"><img id="img2_4_1_1" src="http://img01.ylife.cn/1470121642395.jpg" width="25.0%" widthB="25.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:75.0%;top:25.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_2">
                <a href="${basePath}/subject?subjectId=65"><img id="" src="http://img01.ylife.cn/1470121836775.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_3">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121847208.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont_3" style="height: 330px; position: relative" id="app_cont1">
                <a href="${basePath}/subject?subjectId=87"><img id="imgS1_1_1_1" src="http://img01.ylife.cn/1470121860664.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=84"><img id="imgS1_2_1_1" src="http://img01.ylife.cn/1470121871208.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:33.33333333333333%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=89"><img id="imgS1_3_1_1" src="http://img01.ylife.cn/1470121879728.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:66.66666666666666%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_4">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121888686.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_5">
                <a href="${basePath}/subject?subjectId=92"><img id="" src="http://img01.ylife.cn/1470121898998.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_6">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121921175.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative" id="app_cont32">
                <a href="${basePath}/subject?subjectId=77"><img id="img1_1_1_2" src="http://img01.ylife.cn/1470121931895.jpg" width="50.0%" widthB="50.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=70"><img id="img1_3_1_2" src="http://img01.ylife.cn/1470121939918.jpg" width="50.0%" widthB="50.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_7">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470121975292.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont_3" style="height: 330px;position: relative;" id="app_cont2">
                <a href="${basePath}/subject?subjectId=79"><img id="imgS1_1_1_1" src="http://img01.ylife.cn/1470134183401.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=71"><img id="imgS1_2_1_1" src="http://img01.ylife.cn/1470134198328.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:33.33333333333333%;top:0.0%;"></a><a href="/mobile/subject?subjectId=90"><img id="imgS1_3_1_1" src="http://img01.ylife.cn/1470134209938.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:66.66666666666666%;top:0.0%;"></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont_3" style="height: 106.667px;position: relative;" id="app_cont7">
                <a href="${basePath}/subject?subjectId=66"><img id="imgS1_1_1_1" src="http://img01.ylife.cn/1470134305966.jpg" width="33.33333333333333%" widthb="33.33333333333333%" height="106.66666666666667" heightb="33.33333333333333%" class="imgapp" style="position: absolute; left: 0%; top: 0%; height: 106.667px;"></a><a href="${basePath}/subject?subjectId=67"><img id="imgS1_2_1_1" src="http://img01.ylife.cn/1470134319530.jpg" width="33.33333333333333%" widthb="33.33333333333333%" height="106.66666666666667" heightb="33.33333333333333%" class="imgapp" style="position: absolute; left: 33.3333%; top: 0%; height: 106.667px;"></a><a href="${basePath}/subject?subjectId=69"><img id="imgS1_3_1_1" src="http://img01.ylife.cn/1470204648718.jpg" width="33.33333333333333%" widthb="33.33333333333333%" height="106.66666666666667" heightb="33.33333333333333%" class="imgapp" style="position: absolute; left: 66.6667%; top: 0%; height: 106.667px;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_23">
                <a href="${basePath}/subject?subjectId=77"><img id="" src="http://img01.ylife.cn/1470142904196.jpg" width="100%" widthb="100%" height="" heightb="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_9">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122379645.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_10">
                <a href="${basePath}/subject?subjectId=79"><img id="" src="http://img01.ylife.cn/1470122395464.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_11">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122415407.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative" id="app_cont33">
                <a href="${basePath}/subject?subjectId=85"><img id="img1_1_1_2" src="http://img01.ylife.cn/1470122437790.jpg" width="50.0%" widthB="50.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=91"><img id="img1_3_1_2" src="http://img01.ylife.cn/1470122456532.jpg" width="50.0%" widthB="50.0%" height="25.0%" heightB="25.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_12">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122470067.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont_3" style="height: 330px;position: relative" id="app_cont4">
                <a href="${basePath}/subject?subjectId=81"><img id="imgS1_1_1_1" src="http://img01.ylife.cn/1470122487380.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=73"><img id="imgS1_2_1_1" src="http://img01.ylife.cn/1470122500206.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:33.33333333333333%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=88"><img id="imgS1_3_1_1" src="http://img01.ylife.cn/1470122515023.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:66.66666666666666%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_13">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122533431.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont_3" style="height: 330px;position: relative" id="app_cont5">
                <a href="${basePath}/subject?subjectId=74"><img id="imgS1_1_1_1" src="http://img01.ylife.cn/1470122547307.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=83"><img id="imgS1_2_1_1" src="http://img01.ylife.cn/1470122558721.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:33.33333333333333%;top:0.0%;"></a><a href="${basePath}/subject?subjectId=86"><img id="imgS1_3_1_1" src="http://img01.ylife.cn/1470122572257.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:66.66666666666666%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_14">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122593594.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_24">
                <a href="${basePath}/subject?subjectId=83"><img id="" src="http://img01.ylife.cn/1470142971306.jpg" width="100%" widthb="100%" height="" heightb="" class="" style=""></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_15">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122727132.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_16">
                <a href="${basePath}/subject?subjectId=78"><img id="" src="http://img01.ylife.cn/1470122745775.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_17">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122760211.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont_3" style="height: 330px;position: relative" id="app_cont6">
                <a href="${basePath}/productdetail?productId=5399"><img id="imgS1_1_1_1" src="http://img01.ylife.cn/1470122788698.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/productdetail?productId=5447"><img id="imgS1_2_1_1" src="http://img01.ylife.cn/1470122802473.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:33.33333333333333%;top:0.0%;"></a><a href="${basePath}/productdetail?productId=5467"><img id="imgS1_3_1_1" src="http://img01.ylife.cn/1470122816886.jpg" width="33.33333333333333%" widthB="33.33333333333333%" height="33.33333333333333%" heightB="33.33333333333333%" class="imgapp" style="position:absolute;left:66.66666666666666%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_22">
                <a href="${basePath}/subject?subjectId=78"><img id="" src="http://img01.ylife.cn/1470142590845.jpg" width="100%" widthb="100%" height="" heightb="" class="" style=""></a>
            </div>
        </div>


        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_18">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122832790.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_19">
                <a href="${basePath}/subject?subjectId=76"><img id="" src="http://img01.ylife.cn/1470122845063.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative" id="app_cont34">
                <a href="${basePath}/productdetail?productId=5289"><img id="img1_1_3_2" src="http://img01.ylife.cn/1470122887457.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/productdetail?productId=5288"><img id="img1_3_3_2" src="http://img01.ylife.cn/1470122903442.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a>
            </div>
        </div>
        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_20">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470122921902.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

    </div>

    <div class="foot">
    </div><!-- /foot -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${basePath}/static/js/fastclick.min.js"></script>
    <script src="${basePath}/static/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/static/js/jquery.keleyi.js"></script>
    <script src="${basePath}/static/js/jquery.lazyload.js"></script>
    <#--<script src="${basePath}/static/js/wxforward.js"></script>-->
    <script src="${basePath}/static/js/flushMenu.js"></script>
    <#--<script src="${basePath}/static/js/touchmove.js"></script>-->
    <script src="${basePath}/static/js/countdown.js?v=201511271451"></script>
    <script src="${basePath}/static/js/common.js?v=20151110234"></script>




    <#--<div class="bar-bottom">-->
        <#--<a class="bar-item" href="${basePath}/main"><i class="bar-bottom-i home"></i>首页</a>-->
        <#--&lt;#&ndash;<a class="bar-item" href="/mobile/cates.html"><i class="bar-bottom-i class"></i>分类</a>&ndash;&gt;-->
        <#--&lt;#&ndash;<a class="bar-item" href="${basePath}/static/customer/addCoupon.htm"><i class="bar-bottom-coupon"></i></a>&ndash;&gt;-->
        <#--<a class="bar-item" href="${basePath}/myshoppingmcart"><i class="bar-bottom-i cart"></i>购物车</a>-->
        <#--<a class="bar-item" href="${basePath}/customercenter"><i class="bar-bottom-i mine"></i>我的</a>-->
    <#--</div>-->
    <#include "common/smart_menu.ftl"/>

    <#--<div style="display: none;">-->
        <#--<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1257386524'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1257386524%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>-->
    <#--</div>-->

    <script>
        $(".text_app").parent().each(function () {
            $(this).html(HTMLDecode($(this).html()));
        });
    </script>

    <!--处理限时抢购-->
    <script>
        var html=$("#seckilling").html();
        $("#h_seckilling").parent().html(html);
        $("#seckilling").html("");
        var startTime = new Date("2016/01/26 13:07:00").getTime();
        var endTime = new Date("2016/02/01 23:55:00").getTime();
        seckilling();
    </script>

    <script>
        $(function(){

            //处理魔方高度
            Array.ExistsSameValues = function(a1, a2) {
                var exists = false;
                if(a1 instanceof Array && a2 instanceof Array){
                    for (var i=0,iLen=a1.length; i<iLen; i++){
                        for (var j=0,jLen=a2.length; j<jLen; j++){
                            if (a1[i]===a2[j]){
                                return true;
                            };
                        };
                    };
                };
                return exists;
            };
            $(".app_cont").each(function(){
                var _this = $(this),
                        _hg = "",
                        arr1 = new Array(),
                        arr2 = new Array(),
                        _th = "",
                        _tp = "",
                        _c = $(window).width()/4;
                _this.find("img").each(function(){
                    _th = $(window).width()*(parseInt($(this).attr("heightb"))/100);
                    var styleval = $(this).attr("style");
                    var topval = styleval.substring((styleval.indexOf("top:")+4),styleval.lastIndexOf("%"));
                    _tp = $(window).width()*(parseInt(topval)/100);
                    $(this).height(_th);
                    $(this).css("top",_tp+"px");
                    _hg = _tp + _th;
                    arr1.push(_hg);
                    arr2.push(_tp);
                });
                if(Array.ExistsSameValues(arr1, arr2) == true) {
                    var _top = Math.max.apply(null, arr1);
                    _this.height(_top);
                } else {
                    _this.find("img").each(function(){
                        var _t = $(this),
                                _th = parseInt($(this).attr("heightb"))/100*$(window).width(),
                                _tp = parseInt($(this).css("top")),
                                _tr = _tp/_c+1,
                                _rowspan = _th/_c;
                        for(var r=_tr;r<_rowspan+_tr;r++) {
                            _t.addClass("tr"+r+" ");
                        };
                    });
                    var arr3 = new Array();
                    for(var n=1;n<5;n++) {
                        if(_this.find(".tr"+n).length == 0) {
                            arr3.push(n);
                        };
                    };
                    _this.height($(window).width()-_c*arr3.length);
                    for(x in arr3) {
                        _this.find("img").each(function(){
                            var _t = $(this),
                                    _n = _t.prop("class").substring(9,10),
                                    _top = parseInt(_t.css("top"));
                            if(_n > arr3[x]) {
                                _t.css("top",_top-_c+"px");
                            };
                        });
                    };
                };
            });

            $(".app_cont_3").each(function(){

                var _this = $(this);
                var _c = $(window).width()/3;
                var cur_rows=1;  //得到图片行数
                var cur_cols=1;  //得到图片列数

                _this.find("img").each(function(){


                    cur_cols=$(this).attr("id").substr($(this).attr("id").length-3,1);

                    $(this).css("height",_c*cur_cols);
                    $(this).attr("height",_c*cur_cols);


                    if($(this).attr("id").indexOf("imgS2")>=0)
                    {
                        cur_rows=2;
                        $(this).css("top",_c);
                    }

                    if($(this).attr("id").indexOf("imgS3")>=0)
                    {
                        cur_rows=3;
                        $(this).css("top",_c*2);
                    }




                });
                $(this).css("height",_c*cur_rows);

            });


        });
        var app_cont = $(".app_cont*");
        for(var i=0;i<app_cont.length;i++){
            $(app_cont[i]).height($(window).width());
        }

        $(".app_itemTitle").parent().each(function(){
            $(this).addClass("bottom_line_v1");
        });

        $(".blank-box").each(function(){
            $(this).addClass("parting_box");
            $(this).css("height",15);
        });

        $(function(){
            $(".gd-02 li").css("width",$(window).width()/2 - 10);
        });
    </script>


</div><!--/main-->
</body>

</html>