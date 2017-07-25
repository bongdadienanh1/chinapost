<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>商品详情-查看图片</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}css/style.min.css?201512311847"/>

    <script type="text/javascript" src="${basePath}js/jquery-1.10.1.js"></script>
    <script type="text/javascript" src="${basePath}js/jquery.event.drag-1.5.min.js"></script>
    <script type="text/javascript" src="${basePath}js/jquery.touchSlider.js"></script>
</head>
<body>
<div class="content-detail-pic">
    <div class="main_visual">
        <div class="main_image">
            <ul style="width: 494px; overflow: visible;">
                <#if detailBean?? && detailBean.productVo?? && detailBean.productVo.imageList??>
                    <#list detailBean.productVo.imageList as image>
                        <li><img src="${image.imageBigName!''}" alt=""/></li>
                    </#list>
                </#if>
            </ul>
            <a href="javascript:;" class="btn_prev"></a>
            <a href="javascript:;" class="btn_next"></a>
        </div>
    </div>
    <div class="slide-nav">
        <a href="javascript:;" class="cur"></a>
        <a href="javascript:;" class=""></a>
        <a href="javascript:;" class=""></a>
        <a href="javascript:;" class=""></a>
        <a href="javascript:;" class=""></a>
    </div>
</div>
<#--站长统计-->
<#--<#include "../common/statistics.ftl"/>-->
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $(".main_visual").hover(function () {
            $("#btn_prev,#btn_next").fadeIn()
        }, function () {
            $("#btn_prev,#btn_next").fadeOut()
        });

        $dragBln = false;

        $(".main_image").touchSlider({
            flexible: true,
            speed: 200,
            btn_prev: $("#btn_prev"),
            btn_next: $("#btn_next"),
            paging: $(".flicking_con a"),
            counter: function (e) {
                $(".slide-nav a").removeClass("cur").eq(e.current - 1).addClass("cur");
            }
        });

        $(".main_image").bind("mousedown", function () {
            $dragBln = false;
        });

        $(".main_image").bind("dragstart", function () {
            $dragBln = true;
        });

        $(".main_image a").click(function () {
            if ($dragBln) {
                return false;
            }
        });

        timer = setInterval(function () {
            $("#btn_next").click();
        }, 5000);

        $(".main_visual").hover(function () {
            clearInterval(timer);
        }, function () {
            timer = setInterval(function () {
                $("#btn_next").click();
            }, 5000);
        });

        $(".main_image").bind("touchstart", function () {
            clearInterval(timer);
        }).bind("touchend", function () {
            timer = setInterval(function () {
                $("#btn_next").click();
            }, 5000);
        });

    });
</script>
</html>