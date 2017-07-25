    <#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            .invenList{
                transform: scale(0.9,0.9);
                transform-origin: left top;
                -webkit-transform: scale(0.9,0.9);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.9,0.9);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.9,0.9);
                -moz-transform-origin: left top;
            }
        }
        @media screen and ( max-width: 1360px){
            .xdsoft_datetimepicker{
                zoom: 100%!important;
            }
        }
        .choose{
            width:300px;
            height:100px;
            margin-left:20px;
        }
        #chooseInven{
            width:110px;
            height:40px;
            color:#FFF;
            border:1px solid transparent;
            margin-top:30px;
        }
        .invenList{
            width:1600px;
            height:600px;
            overflow-y:auto;
        }
        .invenList dt{
            width:2000px;
            height:50px;
            color:#666666;
            border-bottom:1px solid #e5e5e5 ;
        }
        .invenList dt abbr{
            display:inline-block;
            width:170px;
            height:50px;
            text-align:center;
            padding:15px 0px;
        }
        .invenList dd{
            width:2000px;
            height:80px;
            background:#fff;
            border-bottom:1px solid #CCC;
        }
        .invenList dd abbr{
            overflow: hidden;
            display:inline-block;
            text-align:center;
            width:170px;
            height:80px;
            vertical-align:middle;
            padding:30px 0px;
            white-space: nowrap;
        }
        .invenListSelect{
            margin-top:-10px;
            text-align:center;
        }
        #foot{
            width:100%;
            height:100px;
            border-top:1px solid #ccc;
        }
        #addInvenConfirm{
            margin-left:500px;
            margin-top:20px;
            cursor:pointer;
        }
        #addInvenCancel{
            margin-left:20px;
            margin-top:20px;
            cursor:pointer;
        }


        .chooseInvenList{
            width:1010px;
            height:530px;
            margin-left:20px;
        }
        .chooseInvenList dt{
            width:1010px;
            height:50px;
            color:#666666;
            border-bottom: 1px solid #e5e5e5;
        }
        .chooseInvenList dt abbr{
            display:inline-block;
            width:150px;
            height:50px;
            margin-left: 10px;
            text-align:center;
            padding:15px 0px;
        }
        .chooseInvenList dd{
            width:1010px;
            height:80px;
            background:#fff;
            border-bottom:1px solid #CCC;
        }
        .chooseInvenList dd abbr{
            display:inline-block;
            text-align:center;
            width:150px;
            height:80px;
            margin-left: 10px;
            vertical-align:middle;
            padding:30px 0px;
            overflow: hidden;
        }
        .chooseInvenList dd abbr:nth-child(3){
            height:50px;
            overflow: hidden;
            padding:10px 0px!important;
            margin-top: -60px;
        }
        .addInvenList{
            width:1050px;
            height:770px;
            position:absolute;
            left:15%;
            top:10%;

            background:#FFF;
            z-index:2;
            display:none;
        }
        .addButton{
            width:850px;
            height:110px;
        }
        .addButton abbr{
            display:inline-block;
            width:210px;
            height:35px;
            margin-left:20px;
            margin-top:10px;
        }
        .addButton abbr span{
            vertical-align:middle;
            display:inline-block;
            width:85px;
            height:35px;
            background:#f4f4f4;
            text-align:center;
            line-height:35px;
        }
        .addButton abbr input{
            width:110px;
            height:35px;
            background:#FFF;
        }
        .addButton abbr select{
            width:110px;
            height:35px;
            border:none;
            background:#FFF;
        }
        #addInvenListSearch{
            width:75px;
            height:35px;
            margin-left:20px;
            color:#fff;
            border-style:none;
        }
        #fenye{
            width:850px;
            height:100px;
            line-height:100px;
            text-align:right;
            padding-right:50px;
        }
        #addInvenListConfirm{
            margin-left:350px;
            margin-top:20px;
            cursor:pointer;
        }
        #addInvenListCancel{
            margin-left:20px;
            margin-top:20px;
            cursor:pointer;
        }

        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #holder{
            margin-top: 15px;
        }

        .inventory{
            width:150px!important;
        }
        input[name='remove']{
            width: 80px;
            height: 30px;
            margin-top: -5px;
            background: #fff;
            border: 1px solid #bbbbbb;
        }
        .invenName{
            /*margin-top: -40px;*/
        }
        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            height:24px;
            background-color: #f2f2f2;
            margin-left: -45px;
            background: url("/static/img/date_img.png") no-repeat center;

        }
        .start-time{
            width: 140px;
        }
        .end-time{
            width: 140px;
        }
        .warning-time{
            width: 140px;
        }
    </style>
    <script type="text/javascript">
        var totalElements = ${totalElements};
        $(document).ready(function(e) {
            //禁止后退键 作用于Firefox、Opera
            document.onkeypress = forbidBackSpace;
            //禁止后退键  作用于IE、Chrome
            document.onkeydown = forbidBackSpace;
            $( ".addInvenList" ).draggable();
            sessionStorage.setItem("ShoppingCart",'');
            $("#addInvenListCancel,#xx").click(function(){
                discoverHtml()
                $(".addInvenList").fadeOut(500);
            })
            $("#chooseInven").click(function(){
                coverHtml()
                $(".addInvenList").fadeIn(500);
            })
            $("#addInvenCancel").click(function(){
                window.location.href="InventoryManager"
            })

            $(document).on("click",".chooseInvenList dt input[name='invenListCheck']",function(){
                if($(".chooseInvenList dt input[name='invenListCheck']").is(':checked')) {
                    $("#itemContainer input:not(:checked)").each(function () {
                        $(this).trigger("click")
                    });
                }else if(!$(".chooseInvenList dt input[name='invenListCheck']").is(':checked')){
                    $("#itemContainer input:checked").each(function () {
                        $(this).trigger("click")
                    });
                }


            })


        });
    </script>


    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 103%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>自购商品入库</span><abbr></abbr>
    </div>


<div class="choose">
    <input class="allclickButton" type="button" id="chooseInven" value="选择货品" />
</div>

<div style="margin-left:20px;">
    <dl class="invenList">
        <dt><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>库存</abbr><abbr>生产日期</abbr><abbr>到期日期</abbr><abbr>保质期</abbr></abbr><abbr style="width:90px;">操作</abbr>
        </dt>
        <div class="add_invenList" style="width: 100%"></div>
    </dl>
    <div id="foot">
        <input class="allseachButton" type="button" value="确定" id="addInvenConfirm" />
        <input class="allcancelButton" type="button" value="取消" id="addInvenCancel" />
    </div>
</div>

<div class="addInvenList allpop">
    <h1>    选择货品
        <i onclick="discoverHtml()" id="xx"></i></h1>
    <ul>

        <li style="height:630px;">
            <div class="addButton">
                <abbr><input style="width: 200px!important;" class="allinputButton" placeholder="货品名称" type="text" name="item_name"/></abbr>
                <abbr><input style="width: 200px!important;" class="allinputButton" placeholder="货品编号" type="text" name="item_no"/></abbr>
                <#--<abbr><span>品牌</span>-->
                    <#--<select id="brand">-->
                        <#--<option selected="selected" name="itemManager_Brand">全部</option>-->
                        <#--<#list brandResult as object>-->
                            <#--<option value="${object.brandId}">${object.goodsBrand}</option>-->
                        <#--</#list>-->
                    <#--</select>-->
                <#--</abbr>-->
                <#--<abbr><span>商家</span>-->
                    <#--<select id="thirdname">-->
                        <#--<option selected="selected" name="itemManager_ThirdName">全部</option>-->
                        <#--<#list thirdNameResult as object>-->
                            <#--<option value="${object.thirdId}">${object.thirdName}</option>-->
                        <#--</#list>-->
                    <#--</select>-->
                <#--</abbr>-->
                <input class="allseachButton" type="button" id="addInvenListSearch" value="搜索" />
            </div>
            <dl class="chooseInvenList">
                <dt><abbr style="width:30px;"><input name="invenListCheck" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>品牌</abbr><abbr>所属商家</abbr>
                </dt>
                <div id="itemContainer"></div>
                <div class="allcpageTurnButton" id="holder"></div>
                <div style="display: none" id="count"></div>
            </dl>
        </li>
        <li style="height:100px;">
            <input class="allseachButton" onclick="discoverHtml()" type="button" value="确定" id="addInvenListConfirm" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" value="取消" id="addInvenListCancel" />
        </li>
    </ul>
</div>

<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</div>
</body>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/addgoods.js?version=${VERSION}"></script>
</html>