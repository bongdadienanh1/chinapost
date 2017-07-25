<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/enterpriseInfo.js"></script>
    <script src="${bath}/static/js/maturityWarning.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            .accountTable{
                transform: scale(0.75,0.75);
                transform-origin: left top;
                -webkit-transform: scale(0.75,0.75);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.75,0.75);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.75,0.75);
                -moz-transform-origin: left top;
            }
            /*#invenAdd{*/
                /*width: 120px!important;*/
            /*}*/
            /*#addgoods,#allInvent{*/
                /*width: 120px!important;*/
            /*}*/
            .date_button{
                margin-top: 1px\0 !important;
            }
            .accountTable{
                width: 132% !important;
            }
            .allOutShow{
                width: 132% !important;
            }
            .billButton li dl{
                margin-top: 3px\0 !important;
            }
            .overinput{
                top:1px !important;
            }
            .change_date_button{
                right: 205px!important;
            }
        }
        @media screen and ( max-width: 1360px){
            .xdsoft_datetimepicker{
                zoom: 100%!important;
            }
        }
        .vocherCode{
            color: #54a6de!important;
            cursor: pointer!important;
        }
        .memberSearch{
            width: 1200px;
            height:80px;
            margin-left:20px;
            margin-top:20px;
            z-index: 1;
            background: #fff;
            transition: all 1s ;
        }
        .memberSearch ul li{
            margin-top: 20px;
        }
        .memberSearch li{
            height:32px;
            line-height:40px;
            float:left;
            position: relative;
            margin-left: 20px;
        }
        .memberSearch li input[type='text']{
            width:215px;
            height:32px;
            margin-left: -6px;
            border:1px solid #ccc;
            background:#FFF;
            margin-top: -1px;
        }
        .memberSearch li select{
            margin-top: -5px;
            border: none;
            color:#999;
            margin-top: 8px\0;
        }
        option{
            font-size: 15px;
        }
        .memberSearch li span{
            display:inline-block;
            text-align:center;
            font-size:15px;

        }

        #invenAdd{
            width:110px;
            height:37px;
            color:#FFF;
            border:1px solid transparent;
            font-size:15px;
        }
        #warningSet{
            width:110px;
            height:37px;
            color:#FFF;
            font-size: 15px;
            border:1px solid transparent;
            margin-left:30px;
        }
        #inverExplor{
            width:110px;
            height:37px;
            color:#FFF;
            font-size: 15px;
            border:1px solid transparent;
            margin-left:30px;
            text-align:center;
        }
        #inverExplor option{
            background:#f2f2f2;
            color:#333;
        }
        .accountTable{
            width: 100%;
            height:auto;
            margin-left:20px;
            margin-top: 80px;
        }
        .accountTable ul{
            width:1000px;
            height:30px;
        }
        .accountTable ul li{
            float:left;
            width:100px;
            height:28px;
            line-height:30px;
            border-bottom:2px solid transparent;
            text-align:center;
            margin-right:-1px;
            cursor:pointer;
            color: #666666;
        }
        .accountTable ul li.accOn{
            color: #54a6de;;
            border-bottom:2px solid #54a6de;

        }
        .invenList{
            width:1950px;
            height:800px;
            overflow-y:auto;
        }
        .invenList dt{
            width:1950px;
            height:50px;
            border-bottom:1px solid #e5e5e5;
            border-top:1px solid #e5e5e5;
            color:#666666;
        }
        .invenList dt abbr{
            display:inline-block;
            width:100px;
            height:50px;
            text-align:left;
            padding:0px 0px;
            margin-left: 20px;
        }
        .invenList dd{
            width:1950px;
            height:80px;
            background:#fff;
            border-bottom:1px solid #CCC;
        }
        .invenList dd abbr{
            display:inline-block;
            text-align:left;
            width:100px;
            height:80px;
            vertical-align:middle;
            padding:30px 0px;
            margin-left: 20px;
            overflow: hidden;
        }
        .invenList dd abbr.invenName{
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .invenListSelect{
            height: 32px;
            width: 80px;
            padding-left: 10px;
            text-align:center;
            font-size: 18px;
        }
        .invenListSelect:hover{
            box-shadow: 0px 0px 3px 3px #e5e5e5;
        }

        .updateInvenNum{
            width:600px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #updateInvenNumConfirm{
            position:relative;
            left:180px;
            top:40px;
        }
        #updateInvenNumCancel{
            position:relative;
            left:210px;
            top:40px;
        }



        .warningNum{
            width:600px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx1{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #warningNumConfirm{
            position:relative;
            left:180px;
            top:40px;
        }
        #warningNumCancel{
            position:relative;
            left:210px;
            top:40px;
        }


        .deleteWarn{
            width:600px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;
            border:1px solid #CCC;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #deleteWarnConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            position:relative;
            left:220px;
            top:40px;
            color:#FFF;
        }
        .deleteCom{
            width:600px;
            height:350px;
            position:absolute;
            left:15%;
            top:30%;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx4{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #deleteComConfirm{
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #deleteComCancel{
            position:relative;
            left:210px;
            top:40px;
        }

        .chooseGoodsType{
            width:600px;
            position:absolute;
            left:15%;
            top:30%;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx5{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #chooseGoodsTypeConfirm{
            position:relative;
            left:160px;
            top:20px;
            color:#FFF;
        }
        #chooseGoodsTypeCancel{
            position:relative;
            left:210px;
            top:20px;
        }

        #holder_warning{
            margin-top: 20px;
        }
        #holder{
            margin-top: 20px;
        }

        #search{
            margin-left: 20px;
        }

        #search2{
            margin-left: 20px;
        }

        .select{
            display: inline-block;
            vertical-align: middle;
            width:120px;
            height:38px;
            margin-left:20px;
            background:#fff;
            text-align: center;
            position: relative;
        }
        .typeSelect{
            margin-left: -10px !important;
        }
        .select dt,.select dd {
            width:135px;
            height:38px;
            border:1px solid #ccc;
            background:#fff;
            margin-top: -1px;
            margin-left:-1px;
            cursor: pointer;
            line-height: 38px;
            color: #666666;
        }
        .arrow{
            display: inline-block;
            position: absolute;
            right: -10px;
            top: 0px;
            width: 36px;
            height: 38px;
            background:url(${bath}/static/img/com_btn_arrow_white_down.png) center no-repeat;
        }
        .select dd{
            display: none;
        }
        #invenLocation,#invenAsk,#invenWarnning{
            height: 38px;
        }
        #allInvent{
            margin-left: 20px;
        }
        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            margin-left: 0!important;
            height:24px;
            background-image: url("${bath}/static/img/date_img.png");
            position: absolute;
            right: 36px;
            margin-top: 10px;
            margin-top: 6px\0;
        }
        .change_date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            margin-left: 0!important;
            height:24px;
            background-image: url("${bath}/static/img/date_img.png");
            right: 230px;
            position: absolute;
        }
        .groupName{
            position: absolute;
            left: 40px;
        }
        .overinput{
            width: 50px;
            height: 20px;
            border:1px solid #ccc;
            position: absolute;
            right: 55px;
            top: 8px;
        }
        .overspan{
            position:absolute;
            right:10px;
        }
        .list_over_time{
            color: red;
            font-size: 14px;
        }
        .billButton li dl{
            display: inline-block;
            vertical-align: middle;
            margin-top: -3px;
            width:88px;
            height:20px;
            margin-left:85px;
            font-size: 12px;
            text-align: center;
            position: relative;
            z-index: 1;
            margin-top: 8px\0;
        }
        .arrow{
            display: inline-block;
            position: absolute;
            right: -10px;
            top: -8px;
            width: 36px;
            height: 35px;
            background:url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat;
        }
        .billButton li dl dd,.billButton li dl dt{
            width:68px;
            height:20px;
            background:#ffffff;
            margin-top: -1px;
            margin-left:10px;
            cursor: pointer;
            line-height: 20px;
            border:none;
        }
        .billButton li dl dd{
            display: none;
        }
        .memberSearch .timeInput{
            width: 237px !important;
        }
        .billButton li:nth-child(4){

            width:217px;
        }

    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        sessionStorage.setItem("ShoppingCart",'');
        //        var checked_array = {
        //            length:0
        //        };
        //        var outport_array = {};
        var goodsCheckedMap = new Map();

        $(document).ready(function(e) {
            var isEnd = getCurrentEnterpriseInfo().getIsEnd();

            $(".deleteWarn").draggable();
            $(".warningNum").draggable();
            $(".updateInvenNum").draggable();
            $(".deleteCom").draggable();
            enterSearch($("input[name='memberNum'],input[name='memberCust']"), $("#search"))
            $(document).on("click", ".invenList dt input[name='invenListCheck']", function () {
                if (!$(this).is(':checked')) {
                    $("#itemContainer input[name='invenListCheck']:checked").trigger("click");
                } else {
                    $("#itemContainer input[name='invenListCheck']:not(:checked)").trigger("click")
                }

            })

            $(document).on("click", ".invenList dt input[name='invenListCheckWarning']", function () {
                if (!$(this).is(':checked')) {
                    $("#itemContainer_warning input[name='invenListCheck']:checked").trigger("click");
                } else {
                    $("#itemContainer_warning input[name='invenListCheck']:not(:checked)").trigger("click")
                }

            })


            $("#updateInvenNumCancel,#xx").click(function () {
                discoverHtml()
                $(".updateInvenNum").fadeOut(500);
            })
            $("#warningNumCancel,#xx1").click(function () {
                discoverHtml()
                $(".warningNum").fadeOut(500);
            })
            $("#deleteComCancel,#xx4").click(function () {
                discoverHtml()
                $(".deleteCom").fadeOut(500);
            })
            $("#xx5").click(function () {
                discoverHtml()
                $(".chooseGoodsType").fadeOut(500);
            })
            $("#deleteWarnConfirm").click(function () {
                discoverHtml()
                $(".deleteWarn").fadeOut(500);
            })


//            $(document).on("change", ".invenListSelect", function () {
//                $("#goodsId").val($(this).parent().parent().children()[0].value);
//                if ($(this).val() == 1) {
//                    coverHtml()
//                    $(this).val(0);
//                    $(".updateInvenNum").fadeIn(500);
//                    $('#change_datetimepicker_start').val($(this).parent().parent().children('.list_start_time').text());
//                    $('#change_datetimepicker_end').val($(this).parent().parent().children('.list_end_time').text());
//                    $('#change_warning_day').val($(this).parent().parent().children('.list_warn_time').text());
//                }
//                if ($(this).val() == 2) {
//                    $(this).val(0)
//                    coverHtml()
//                    $(".deleteCom").fadeIn(500);
//                }
//            });

            $(document).on("change",".invenListSelect",function(){
                $("#goodsId").val($(this).parent().parent().find("input[name='id']").val());
                if($(this).val()==2){
                    coverHtml()
                    $(this).val(0);
                    $(".updateInvenNum").fadeIn(500);
                    $(".errorEnd").remove();
                    $('#change_datetimepicker_start').val($(this).parent().parent().children('.list_start_time').text());
                    $('#change_datetimepicker_end').val($(this).parent().parent().children('.list_end_time').text());
                    $('#oldEndTime').val($(this).parent().parent().children('.list_end_time').text());
                    $('#oldWarning').val($(this).parent().parent().children('.list_warn_time').text());
                    $('#oldsTartTime').val($(this).parent().parent().children('.list_start_time').text());
                    $('#change_warning_day').val($(this).parent().parent().children('.list_warn_time').text());
                }
                if($(this).val()==1){
                    $(this).val(0)
                        coverHtml()
                        $(".deleteCom").fadeIn(500);
                }
            });

            $("#warningNumConfirm").click(function(){
                var warningNumber = $("input[name='setWarningNumber']").val();
                var goodsInfoId =   $("#setgoodInfo").val()
                if(/^\+?[1-9][0-9]*$/.test(warningNumber)||warningNumber==0){
                    $.post("../web/api/inventory/setWarning",{
                        warning:warningNumber,
                        goodsInfoId:goodsInfoId
                    },function( data ){
                        if( data.response == 'success' ){
                            discoverHtml()
                            $(".goodsInfoId").each(function(){
                                if( $(this).val() == goodsInfoId){
                                    $(this).siblings(".warningVal").html(warningNumber)
                                }
                            })
                            response_ensure_alert("success","设置预警值成功",function(){
                                $(".warningNum").fadeOut(500)
                            })

                        }else{
                            data_type_alert(data.data.text,"error")
                        }
                    },'json');
                }else{
                    coverHtml()
                    data_type_alert("预警值必须为正整数","error")
                }
            });
            $("#invenAsk").click(function(){
                window.open("replenishment");
            });
            $("#invenAdd").click(function(){
                window.open("inStorage")
            });
            $(document).on("click","#invenWarnning",function(){
                coverHtml()
                $(".chooseGoodsType").show()

            })
            $("#chooseGoodsTypeConfirm").click(function(){
                discoverHtml()
                $(".chooseGoodsType").hide()
                window.open("reimburse?goodsInfoType=0");
            })
            $("#chooseGoodsTypeCancel").click(function(){
                discoverHtml()
                $(".chooseGoodsType").hide()
                window.open("reimburse?goodsInfoType=1");
            })
            $("#allInvent").click(function(){
                window.location.href = '../web/api/exportExcel/exportInventoryCheck';
            })
//            $(".accountTable li").click(function(){
//                $(this).addClass("accOn").siblings("li").removeClass("accOn");
//                if( $(this).val() == 0 ){
//                    $("dl[name='first']").css("display","block");
//                    $("dl[name='second']").css("display","none");
//
////                    checked_array = {};
////                    outport_array = {};
////                    checked_array.length = 0;
//                    var array = [];
//                    array[0] =  $("input[name='memberNum']").val();
//                    array[1] = $("input[name='memberCust']").val();
//                    ajaxPages('../web/api/inventory/getInventoryGoods','itemContainer','holder',8,5,'','',array,function(pageId){
//                        var goodsche=$(this).attr("data-type");
//                        var thisCheckedArray = goodsCheckedMap.get(goodsche);
//                        if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
//                            $(".accountNumber").each(function () {
//                                var _this = this;
//                                thisCheckedArray[pageId].map(function(orderCode){
//                                    if($(_this).parent().prev().val() == orderCode){
//                                        $(_this).prop("checked","checked");
//                                    }
//                                });
//                            });
//                        }
//                    });
//                    $("#search").show()
//                    $("#search2").hide()
//                }
//                else if( $(this).val() == 1 ){
//                    $("dl[name='first']").css("display","none");
//                    $("dl[name='second']").css("display","block");
//
////                    checked_array.length = 0;
//                    var array = [];
//                    array[0] =  $("input[name='memberNum']").val();
//                    array[1] = $("input[name='memberCust']").val();
//                    ajaxPages('../web/api/inventory/getWarningGoods','itemContainer_warning','holder_warning',8,5,'','',array,function(pageId){
//                        var goodsche=$(this).attr("data-type");
//                        var thisCheckedArray = goodsCheckedMap.get(goodsche);
//                        if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
//                            $(".accountNumber").each(function () {
//                                var _this = this;
//                                thisCheckedArray[pageId].map(function(orderCode){
//                                    if($(_this).parent().prev().val() == orderCode){
//                                        $(_this).prop("checked","checked");
//                                    }
//                                });
//                            });
//                        }
//                    });
//                    $("#search2").show()
//                    $("#search").hide()
//                }
//            })
        });


    </script>
    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<input type="hidden" value="${isTop}" id="isTop"/>
<input type="hidden" value="${isEnd}" id="isEnd"/>
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 120%;overflow-x: scroll;">
    <div class="allheadstyle">
    <#if isEnd == true>
        <a href="InventoryManager">仓库管理</a>
    </#if>
    <#if isTop == true>
        <a href="InventoryManager">仓库管理</a>
    </#if>
    <#if isEnd != true>
        <a href="inventoryInquiry">库存查询</a>
    </#if>

        <a href="voucherManager">单据管理</a>

        <span>到期预警</span>
        <abbr></abbr>
    </div>

    <div class="memberSearch billButton">
        <ul>
            <li>
                <input class="allinputButton" placeholder="单据编号" type="text" name="memberNum" />
            </li>
            <li>
                <input class="allinputButton" placeholder="货品编号" type="text" name="memberCust" />
            </li>

            <li class="Type allinputButton" style="width: 340px;margin-top: 26px;"><span class="groupName">到期提醒</span>
                <dl class="select typeSelect">
                    <i value="1" class="arrow arrowType"></i>
                    <dt data-typestr="" id="status"><input id="statusInput" type="hidden" value="4"/><abbr>未到期</abbr></dt>
                    <dd data-typestr="1" class="now billStatus allSelectButton">全部</dd>
                    <dd data-typestr="2" class="now billStatus allSelectButton">永久有效</dd>
                    <dd data-typestr="3" class="now billStatus allSelectButton">过期</dd>
                    <dd data-typestr="4" class="now billStatus allSelectButton">未到期</dd>
                </dl>
                <#--<select class="chosetime">-->
                    <#--<option selected="selected">未到期</option>-->
                    <#--<option>全部</option>-->
                    <#--<option>永久有效</option>-->
                    <#--<option>过期</option>-->
                <#--</select>-->
            <input class="overinput" value="100"/><span class="overspan">天之内</span>
            </li>
        <#if isTop == true>
            <li class="Status allinputButton" style="margin-top: 26px;">
                <span class="groupName">查看范围</span>
                <dl class="select">
                    <i value="0" class="arrow arrowType"></i>
                    <dt data-typestr="1" id="status2"><input id="statusInput2" type="hidden" value="1"/><abbr>南京区</abbr></dt>
                    <dd data-typestr="2" class="now billStatus allSelectButton">其他网点</dd>
                    <dd data-typestr="1" class="now billStatus allSelectButton">南京区</dd>
                </dl>
            </li>
        <#else >
            <li class="Status allinputButton" style="border-color: #fff;margin-top: 26px;">

            </li>
        </#if>
            <#--<div style="width: 800px;display: inline-block;height: 60px"></div>-->
            <li style="position:relative;">
                <input class="allinputButton timeInput" placeholder="入库开始时间"  type="text" id="in_datetimepicker_start" onkeyup="$(this).val('')"/><a  id="in_date_start" href="#"class="date_button"/></a>
            </li>
            <li style="position: relative">
                <input class="allinputButton timeInput" placeholder="入库结束时间"  type="text" id="in_datetimepicker_end" onkeyup="$(this).val('')"/><a  id="in_date_end" href="#"class="date_button"/></a>
            </li>
            <li style="position:relative;">
                <input class="allinputButton timeInput" placeholder="到期开始时间"  type="text" id="over_datetimepicker_start" onkeyup="$(this).val('')"/><a  id="over_date_start" href="#"class="date_button"/></a>
            </li>
            <li style="position: relative">
                <input class="allinputButton timeInput" placeholder="到期结束时间"  type="text" id="over_datetimepicker_end" onkeyup="$(this).val('')"/><a  id="over_date_end" href="#"class="date_button"/></a>
            </li>

        <#--<li>-->
        <#--<span>品牌</span>-->
        <#--<select id="brand_select">-->
        <#--<option selected="selected">全部</option>-->
        <#--<#list brandResult as object>-->
        <#--<option value="${object.brandId}">${object.goodsBrand}</option>-->
        <#--</#list>-->
        <#--</select>-->
        <#--</li>-->
        <#--<li>-->
        <#--<span>商家</span>-->
        <#--<select id="thirdname_select">-->
        <#--<option selected="selected">全部</option>-->
        <#--<#list thirdNameResult as object>-->
        <#--<option value="${object.thirdId}">${object.thirdName}</option>-->
        <#--</#list>-->
        <#--</select>-->
        <#--</li>-->
            <li>
                <input class="allseachButton" type="button" value="搜索" data-type="0" id="search"/>
                <input class="allseachButton"  style="display: none;" type="button" data-type="1" value="搜索" id="search2"/>
            </li>
            <#--<li style="width:1200px; margin-top:20px;">-->

            <#--<#if isTop == true>-->
                <#--<input class="allclickButton"  type="button" value="集采商品入库" id="invenAdd" />-->
            <#--<#elseif isEnd == true>-->
                <#--<input class="allclickButton"  style="margin-left:20px;" type="button" value="自购商品入库" class="invenBut" id="addgoods" />-->
            <#--</#if>-->
            <#--<#if isTop == true>-->
                <#--<input class="allclickButton"  style="margin-left:20px;" type="button" value="库存调拨" class="invenBut" id="invenLocation" />-->
                <#--<input class="allclickButton"  style="margin-left:20px;" type="button" value="采购订单" class="invenBut" id="purchaseOrder" />-->
            <#--<#elseif isEnd == true>-->
                <#--<input class="allclickButton"  style="margin-left:20px;" type="button" value="库存调拨" class="invenBut" id="invenLocation" />-->
            <#--</#if>-->
            <#--<#if isEnd == true>-->
                <#--<input class="allclickButton"  style="margin-left: 20px;" type="button" value="补货申请" id="invenAsk" />-->
                <#--<input class="allclickButton"  style="margin-left: 20px;" type="button" value="报损，报溢" id="invenWarnning" />-->
            <#--</#if>-->
                <#--&lt;#&ndash;<input class="allclickButton"  style="margin-left: 20px;" type="button" value="导出所有" id="outputAll" />&ndash;&gt;-->
                <#--&lt;#&ndash;<input class="allclickButton"  style="margin-left: 20px;" type="button" value="导出选中" id="outputPart" />&ndash;&gt;-->
            <#--<#if isEnd == true>-->
                <#--<input class="allclickButton" type="button" id="allInvent" value="导出库存盘点" />-->
            <#--</#if>-->
            <#--</li>-->
        </ul>
    </div>


    <div class="accountTable">
        <ul>
            <li class="" value="0">到期预警</li>
            <#--<li value="1">预警货品</li>-->
        </ul>
        <#--<dl class="invenList" name="first">-->
            <#--<dt><abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>商品类型</abbr><abbr>库存</abbr><abbr>可用库存</abbr><abbr>预警值</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>-->
            <#--</dt>-->
            <#--<div id="itemContainer" data-type="0"></div>-->
            <#--<div class="allcpageTurnButton" id="holder"></div>-->
        <#--</dl>-->
        <dl class="invenList" name="first">
            <dt><abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr><abbr style="width: 150px">入库单据</abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>规格</abbr><abbr>货品编号</abbr><abbr>入库库存</abbr><abbr>入库时间</abbr><abbr>生产日期</abbr><abbr>到期日期</abbr><abbr>保质期（天）</abbr><abbr>到期提醒</abbr><abbr>品牌</abbr><abbr>所属商家</abbr>
                <abbr id="operation">操作</abbr>
            </dt>
            <div id="itemContainer" data-type="0"></div>
            <div class="allcpageTurnButton" id="holder"></div>
        </dl>
        <#--<dl class="invenList" name="second" style="display: none;">-->
            <#--<dt><abbr style="width:50px;"><input name="invenListCheckWarning" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>商品类型</abbr><abbr>库存</abbr><abbr>可用库存</abbr><abbr>预警值</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>-->
            <#--</dt>-->
            <#--<div id="itemContainer_warning" data-type="1"></div>-->
            <#--<div class="allcpageTurnButton" id="holder_warning"></div>-->
        <#--</dl>-->

    </div>


    <div class="updateInvenNum allpop">
        <h1>保质期修改<i onclick="discoverHtml()" id="xx"></i></h1>
        <ul>
            <#--<li style="height:150px; line-height:150px;">-->
                <#--<span style="padding-left:100px; font-size:22px;"><i style="color:#ff3300;">*</i>库存:<input style="width:220px; height:40px; margin-left:40px;" type="text" name="editInventory" /></span>-->
            <#--</li>-->
                <li style="margin-left: 130px; margin-top: 20px">
                    <span>生产日期：  </span><input style="border-radius: 0px" class="allinputButton" placeholder="生产日期"  type="text" style="width:257px;" id="change_datetimepicker_start" onkeyup="$(this).val('')"/><a  id="change_date_start" href="javascript:void(0)"class="change_date_button" /></a>
                </li>
                <li style="margin-left: 130px; margin-top: 20px">
                    <span>到期日期：  </span><input style="border-radius: 0px" class="allinputButton" placeholder="到期日期"  type="text" style="width:257px;" id="change_datetimepicker_end" onkeyup="$(this).val('')"/><a  id="change_date_end" href="javascript:void(0)"class="change_date_button" /></a>
                </li>
                <li style="margin-left: 130px; margin-top: 20px; margin-bottom: 20px">
                    <span>保质期：  </span><input style="border-radius: 0px; margin-left: 18px" class="allinputButton" placeholder="保质期"  type="text" style="width:257px;" id="change_warning_day"  onkeyup ="onlyNum(this)"/>
                </li>
            <li style="height:120px; border-top:1px solid #CCC;">
                <input type="hidden" id="goodsId"/>
                <input type="hidden" id="oldEndTime"/>
                <input type="hidden" id="oldWarning"/>
                <input type="hidden" id="oldsTartTime"/>
                <input class="allseachButton" type="button" id="updateInvenNumConfirm" value="确定" />
                <input class="allcancelButton" onclick="discoverHtml()" type="button" id="updateInvenNumCancel" value="取消" />
            </li>
        </ul>
    </div>


    <div class="warningNum allpop">
        <h1>预警设置<i onclick="discoverHtml()" id="xx1"></i></h1>
        <ul>
            <li style="height:150px; line-height:150px;">
                <input style="width:220px; height:40px; margin-left:40px;" type="hidden" id="setgoodInfo" />
                <span style="padding-left:100px; font-size:22px;">设置预警值:<input style="width:220px; height:40px; margin-left:40px;" type="text" name="setWarningNumber" /></span>
            </li>
            <li style="height:120px; border-top:1px solid #CCC;">
                <input class="allseachButton" onclick="discoverHtml()" type="button" id="warningNumConfirm" value="确定" />
                <input class="allcancelButton" onclick="discoverHtml()" type="button" id="warningNumCancel" value="取消" />
            </li>
        </ul>
    </div>


    <div class="deleteCom allpop">
        <h1>确认提示<i onclick="discoverHtml()" id="xx4"></i></h1>
        <ul>
            <li style="height:150px; line-height:150px;">
                <span style="padding-left:100px; font-size:22px;">您确定删除该货品吗？</span>
            </li>
            <li style="height:120px; border-top:1px solid #CCC;">
                <input class="allseachButton" type="button" id="deleteComConfirm" value="确定" />
                <input class="allcancelButton" onclick="discoverHtml()" type="button" id="deleteComCancel" value="取消" />
            </li>
        </ul>
    </div>

    <div class="chooseGoodsType allpop">
        <h1>确认提示<i onclick="discoverHtml()" id="xx5"></i></h1>
        <ul>
            <li style="height:80px; line-height:80px;">
                <span style="padding-left:180px; font-size:22px;">请选择报损报溢商品类型</span>
            </li>
            <li style="height:80px; border-top:1px solid #CCC;">
                <input class="allseachButton" onclick="discoverHtml()" type="button" id="chooseGoodsTypeConfirm" value="集采商品" />
                <input class="allseachButton" onclick="discoverHtml()" type="button" id="chooseGoodsTypeCancel" value="自购商品" />
            </li>
        </ul>
    </div>


    <div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</div>
</body>
<script>
    $(document).ready(function(){

        $(document).on("click","#invenLocation",function(){
            window.open("allocation");
        });
        $(document).on("click","#purchaseOrder",function(){
            window.open("purchaseOrder?purchaseId=");
        });
        $(document).on("click","#addgoods",function(){
            window.location.href = "Addgoods"
        });
//        $(document).on('change','.chosetime',function(){
//            $('.overinput').remove();
//            $('.overspan').remove();
//            var ss = $(this).children('option:selected').val();
//            var addday = '<input class="overinput"  value="100"/><span class="overspan">天之内</span>';
//            if (ss == '全部' || ss == '永久有效') {
//                console.log(111);
//               $('.overinput').remove();
//                $('.overspan').remove();
//            } else if (ss == '过期' || ss == '未到期') {
//                console.log(222);
//                $('.Type').append(addday);
//            }
//        })
        //当填了生产日期和到期日期，自动填写保质期
        $(document).on('change', '#change_datetimepicker_end', function () {
            var stime = $(this).parent().prev().children('#change_datetimepicker_start').val();
            var etime = $(this).val();
            var wtime = $(this).parent().next().children('#change_warning_day');
            if($("#oldEndTime").val()!='' && etime=='' && wtime.val()==''){
                $(this).parent().append("<p class='errorEnd' style='color: red;font-size: 14px;'>注意！删除到期日期该货品将变成永久有效<p>")
            }
            if(stime!='' &&etime !=''){
                wtime.val(DateDiff(stime, etime) + 1);
            }
//            if(stime != '' && wtime.val() != ''){
//                etime = getNewDay(stime.val(), Number(wtime.val()) - 1);
//            }
        })
        $(document).on('change', '#change_datetimepicker_start', function () {
            var etime = $(this).parent().next().children('#change_datetimepicker_end').val();
            var stime = $(this).val();
            var wtime = $(this).parent().next().next().children('#change_warning_day');
            if($("#oldEndTime").val()!='' && etime == '' && wtime==''){
                $('#change_datetimepicker_end').parent().append("<p class='errorEnd' style='color: red;font-size: 14px;'>注意！删除到期日期该货品将变成永久有效<p>")
            }
            if(etime!='' &&stime!=''){
                wtime.val(DateDiff(stime, etime) + 1);
            }
        })
        $(document).on('change', '#change_warning_day', function () {
            var etime = $(this).parent().prev().children('#change_datetimepicker_end');
            var wtime = $(this);
            var stime = $(this).parent().prev().prev().children('#change_datetimepicker_start');
            if($("#oldEndTime").val()!='' && wtime.val()=='' && etime.val()==''){
                $('#change_datetimepicker_end').parent().append("<p class='errorEnd' style='color: red;font-size: 14px;'>注意！删除到期日期该货品将变成永久有效<p>")
            }
        })

    })
    //计算两天只差
    function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006/12/18格式
        var  aDate,  oDate1,  oDate2,  iDays;
        aDate  =  sDate1.split("-");
        oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);   //转换为12-18-2006格式
        aDate  =  sDate2.split("-");
        oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);
        iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数
        return  iDays;
    }
</script>

</html>
