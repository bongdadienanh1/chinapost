<html lang="zh-cn">
<head>
    <#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="UDD商城">
    <meta name="description" content="UDD商城">
    <title>UDD商城</title>

    <#--<link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>-->
    <link href="${basePath}/static/css/style.css" rel="stylesheet"/>
    <!-- Bootstrap -->
    <link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/static/css/idangerous.swiper.css" rel="stylesheet">

    <link href="${basePath}/static/css/style.min.css?v=201601091628" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
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

<#--<script>-->
    <#--$(function(){-->
        <#--$(".bar-bottom a:eq(0)").addClass("selected");-->
    <#--})-->
<#--</script>-->
<div class="curtain_wp">
</div><!--/curtain_wp-->
<div id="main" style="display: none;">

    <input class="storeId" type="hidden" value="0">
    <!--引用静态页面-->
    <div id="ip_cont" class="ip_cont">
        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_1">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470109288245.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_2">
                <a href="${basePath}/productdetail?productId=5286"><img id="" src="http://img01.ylife.cn/1470109304832.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_3">
                <a href="${basePath}/productdetail?productId=5284"><img id="" src="http://img01.ylife.cn/1470109312300.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_4">
                <a href="${basePath}/productdetail?productId=5282"><img id="" src="http://img01.ylife.cn/1470109320903.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_6">
                <a href="${basePath}/productdetail?productId=5287"><img id="" src="http://img01.ylife.cn/1470109347542.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_5">
                <a href="${basePath}/productdetail?productId=5285"><img id="" src="http://img01.ylife.cn/1470109334059.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_7">
                <a href="${basePath}/productdetail?productId=5283"><img id="" src="http://img01.ylife.cn/1470109357132.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative;" id="app_cont1"></div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative;" id="app_cont2">
                <a href="${basePath}/productdetail?productId=5281"><img id="img1_1_3_2" src="http://img01.ylife.cn/1470109464239.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/productdetail?productId=5293"><img id="img1_3_3_2" src="http://img01.ylife.cn/1470109474991.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative;" id="app_cont3">
                <a href="${basePath}/productdetail?productId=5292"><img id="img1_1_3_2" src="http://img01.ylife.cn/1470109490094.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/productdetail?productId=5291"><img id="img1_3_3_2" src="http://img01.ylife.cn/1470109503869.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="app_cont" style="height: 320px;position: relative;" id="app_cont4">
                <a href="${basePath}/productdetail?productId=5290"><img id="img1_1_3_2" src="http://img01.ylife.cn/1470109524565.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:0.0%;top:0.0%;"></a><a href="${basePath}/productdetail?productId=5446"><img id="img1_3_3_2" src="http://img01.ylife.cn/1470109536856.jpg" width="50.0%" widthB="50.0%" height="75.0%" heightB="75.0%" class="imgapp" style="position:absolute;left:50.0%;top:0.0%;"></a>
            </div>
        </div>

        <div class="app_item app_cube" style="display: block;">
            <div class="img_adv item" id="img_adv_8">
                <a href="javascript:;"><img id="" src="http://img01.ylife.cn/1470109551674.jpg" width="100%" widthB="100%" height="" heightB="" class="" style=""></a>
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




    <div class="bar-bottom">
        <a class="bar-item" href="${basePath}/main"><i class="bar-bottom-i home"></i>首页</a>
        <a class="bar-item" href="${basePath}/myshoppingmcart"><i class="bar-bottom-i cart"></i>购物车</a>
        <a class="bar-item" href="${basePath}/customercenter"><i class="bar-bottom-i mine"></i>我的</a>
    </div>

    <div style="display: none;">
        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1257386524'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1257386524%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
    <script>

        if(window.location.href.indexOf("uindex.htm")>=0)
        {
            var item = document.getElementById('bar-bottom-item1');
            item.setAttribute("class", "bar-item selected");
        }

        if(window.location.href.indexOf("addCoupon.htm")>=0)
        {
            var item = document.getElementById('bar-bottom-item2');
            item.setAttribute("class", "bar-item selected");
        }

        if(window.location.href.indexOf("customercenter.htm")>=0)
        {
            var item = document.getElementById('bar-bottom-item3');
            item.setAttribute("class", "bar-item selected");
        }

    </script>
    <script>
        $(".text_app").parent().each(function () {
            $(this).html(HTMLDecode($(this).html()));
        });
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

                _this.find("img").each(function(){
                    $(this).css("height",_c);
                    $(this).attr("height",_c);


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