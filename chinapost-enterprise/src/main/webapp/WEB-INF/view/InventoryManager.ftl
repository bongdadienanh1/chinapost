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
    <script src="${bath}/static/js/inventoryManager.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/enterpriseInfo.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
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
            #invenAdd{
                width: 120px!important;
            }
            #addgoods,#allInvent{
                width: 120px!important;
            }
        }
        .memberSearch{
            width: 98%;
            height:80px;
            margin-left:20px;
            margin-top:20px;
            z-index: 1;
            background: #fff;
            transition: all 1s ;
        }
        .memberSearch ul{
            margin-top: 20px;
            margin-left: 20px;
        }
        .memberSearch li{
            width:250px;
            height:36px;
            line-height:40px;
            float:left;
        }
        .memberSearch li input[type='text']{
            width:200px;
            height:32px;
            margin-left: -6px;
            border:1px solid #ccc;
            background:#FFF;
            margin-top: -1px;
        }
        .memberSearch li select{

        }
        .memberSearch li select{
            margin-top: -5px;
            border: none;
            color:#999;
            margin-left: 5px;
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
            width:1600px;
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
            width:110px;
            height:50px;
            text-align:left;
            padding:15px 0px;
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
            width:110px;
            height:80px;
            vertical-align:middle;
            padding:20px 0px;
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
            background:#f8f8f8;
            text-align: center;
            position: relative;
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
    <#--<#if isTop == 'false'>-->
        <#--.changeInventory{-->
            <#--display:none;-->
        <#--}-->
    <#--</#if>-->
        /*.allOpenShow{*/
            /*position: absolute;*/
            /*bottom: 10px;*/
            /*left: 45%;*/
            /*color: #54a6de;*/
            /*cursor: pointer;*/
        /*}*/
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

            $( ".deleteWarn" ).draggable();
            $( ".warningNum" ).draggable();
            $( ".updateInvenNum" ).draggable();
            $( ".deleteCom" ).draggable();
            enterSearch( $("input[name='memberNum'],input[name='memberCust']"),$("#search"))
            $(document).on("click",".invenList dt input[name='invenListCheck']",function(){
                if(!$(this).is(':checked')){
                    $("#itemContainer input[name='invenListCheck']:checked").trigger("click");
                }else{
                    $("#itemContainer input[name='invenListCheck']:not(:checked)").trigger("click")
                }

            })

            $(document).on("click",".invenList dt input[name='invenListCheckWarning']",function(){
                if(!$(this).is(':checked')){
                    $("#itemContainer_warning input[name='invenListCheck']:checked").trigger("click");
                }else{
                    $("#itemContainer_warning input[name='invenListCheck']:not(:checked)").trigger("click")
                }

            })



            $("#updateInvenNumCancel,#xx").click(function(){
                discoverHtml()
                $(".updateInvenNum").fadeOut(500);
            })
            $("#warningNumCancel,#xx1").click(function(){
                discoverHtml()
                $(".warningNum").fadeOut(500);
            })
            $("#deleteComCancel,#xx4").click(function(){
                discoverHtml()
                $(".deleteCom").fadeOut(500);
            })
            $("#xx5").click(function(){
                discoverHtml()
                $(".chooseGoodsType").fadeOut(500);
            })
            $("#deleteWarnConfirm").click(function(){
                discoverHtml()
                $(".deleteWarn").fadeOut(500);
            })


            $(document).on("change",".invenListSelect",function(){
                $("#goodsId").val($(this).parent().parent().children()[0].value);
                if($(this).val()==1){
                    coverHtml()
                    $(this).val(0);
                    $(".updateInvenNum").fadeIn(500);

                }
                else if($(this).val()==2){
                    $(this).val(0)
                    var num1=$(this).parent().siblings(".invenNum").html();
                    var num2=$(this).parent().siblings(".invenNumUsed").html();
                    if(num1>0&&num2>0||num1>0&&num2>=0){
                        coverHtml()
                        $(".deleteWarn").fadeIn(500);
                    }else if(num1==0&&num2==0){
                        coverHtml()
                        $(".deleteCom").fadeIn(500);
                    }
                }
                else if($(this).val()==3){
                    $(this).val(0)
                    coverHtml()
                    $(".warningNum").fadeIn(500);
                    var goodsInfoId = $(this).parent().siblings(".goodsInfoId").val();
                    $("#setgoodInfo").val(goodsInfoId)
                    $.post("../web/api/inventory/getWarning",{
                        goodsInfoId:goodsInfoId
                    },function( data ){
                        if( data.response == 'success' ){
                            $("input[name='setWarningNumber']").val(data.data)
                        }
                        else{
                        }
                    },'json')

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
            $(".accountTable li").click(function(){
                $(this).addClass("accOn").siblings("li").removeClass("accOn");
                if( $(this).val() == 0 ){
                    $("dl[name='first']").css("display","block");
                    $("dl[name='second']").css("display","none");

//                    checked_array = {};
//                    outport_array = {};
//                    checked_array.length = 0;
                    var array = [];
                    array[0] =  $("input[name='memberNum']").val();
                    array[1] = $("input[name='memberCust']").val();
                    ajaxPages('../web/api/inventory/getInventoryGoods','itemContainer','holder',8,5,'','',array,function(pageId){
                        var goodsche=$(this).attr("data-type");
                        var thisCheckedArray = goodsCheckedMap.get(goodsche);
                        if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                            $(".accountNumber").each(function () {
                                var _this = this;
                                thisCheckedArray[pageId].map(function(orderCode){
                                    if($(_this).parent().prev().val() == orderCode){
                                        $(_this).prop("checked","checked");
                                    }
                                });
                            });
                        }
                    });
                    $("#search").show()
                    $("#search2").hide()
                }
                else if( $(this).val() == 1 ){
                    $("dl[name='first']").css("display","none");
                    $("dl[name='second']").css("display","block");

//                    checked_array.length = 0;
                    var array = [];
                    array[0] =  $("input[name='memberNum']").val();
                    array[1] = $("input[name='memberCust']").val();
                    ajaxPages('../web/api/inventory/getWarningGoods','itemContainer_warning','holder_warning',8,5,'','',array,function(pageId){
                        var goodsche=$(this).attr("data-type");
                        var thisCheckedArray = goodsCheckedMap.get(goodsche);
                        if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                            $(".accountNumber").each(function () {
                                var _this = this;
                                thisCheckedArray[pageId].map(function(orderCode){
                                    if($(_this).parent().prev().val() == orderCode){
                                        $(_this).prop("checked","checked");
                                    }
                                });
                            });
                        }
                    });
                    $("#search2").show()
                    $("#search").hide()
                }
            })
        });


    </script>
    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<input type="hidden" value="${isTop}" id="isTop"/>
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">

    <#if isEnd == true>
        <span>仓库管理</span>
    </#if>
    <#if isTop == true>
        <span>仓库管理</span>
    </#if>
    <#if isEnd != true>
        <a class="leftshanow" href="inventoryInquiry">库存查询</a>
    </#if>
        <a class="leftshanow" href="voucherManager">单据管理</a>
        <a class="leftshanow" href="maturityWarning">到期预警</a>
    </div>

<div class="memberSearch">
    <ul>
        <li>
            <input class="allinputButton" placeholder="货品名称" type="text" name="memberNum" />
        </li>
        <li>
            <input class="allinputButton" placeholder="货品编号" type="text" name="memberCust" />
        </li>
        <#if isEnd>
        <li >
            <div class="allinputButton" style="margin-top: 8px; color: #999; width: 250px;height: 24px!important;line-height:24px;border-radius: 20px!important;">
            <span>商品类型</span>
            <select class="goodsInfoType">
                <option selected="selected">全部</option>
                <option value="0">集采商品</option>
                <option value="1">自购商品</option>
            </select>
            </div>
        </li>
        </#if>
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
        <li style="width:1200px; margin-top:20px;">

            <#if isTop == true>
            <input class="allclickButton"  type="button" value="集采商品入库" id="invenAdd" />
            <#elseif isEnd == true>
                <input class="allclickButton"  style="margin-left:20px;" type="button" value="自购商品入库" class="invenBut" id="addgoods" />
            </#if>
            <#if isTop == true>
                <input class="allclickButton"  style="margin-left:20px;" type="button" value="库存调拨" class="invenBut" id="invenLocation" />
                <input class="allclickButton"  style="margin-left:20px;" type="button" value="采购订单" class="invenBut" id="purchaseOrder" />
            <#elseif isEnd == true>
                <input class="allclickButton"  style="margin-left:20px;" type="button" value="库存调拨" class="invenBut" id="invenLocation" />
            </#if>
            <#if isEnd == true>
                <input class="allclickButton"  style="margin-left: 20px;" type="button" value="补货申请" id="invenAsk" />
                <input class="allclickButton"  style="margin-left: 20px;" type="button" value="报损，报溢" id="invenWarnning" />
            </#if>
                <input class="allclickButton"  style="margin-left: 20px;" type="button" value="导出所有" id="outputAll" />
                <input class="allclickButton"  style="margin-left: 20px;" type="button" value="导出选中" id="outputPart" />
            <#if isEnd == true>
                <input class="allclickButton" type="button" id="allInvent" value="导出库存盘点" />
            </#if>
        </li>
    </ul>
</div>


<div class="accountTable">
    <ul>
        <li class="accOn" value="0">全部货品</li>
        <li value="1">预警货品</li>
    </ul>
    <dl class="invenList" name="first">
        <dt><abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>商品类型</abbr><abbr>库存</abbr><abbr>可用库存</abbr><abbr>预警值</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>
        </dt>
        <div id="itemContainer" data-type="0"></div>
        <div class="allcpageTurnButton" id="holder"></div>
    </dl>
    <dl class="invenList" name="second" style="display: none;">
        <dt><abbr style="width:50px;"><input name="invenListCheckWarning" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>商品类型</abbr><abbr>库存</abbr><abbr>可用库存</abbr><abbr>预警值</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr>操作</abbr>
        </dt>
        <div id="itemContainer_warning" data-type="1"></div>
        <div class="allcpageTurnButton" id="holder_warning"></div>
    </dl>

</div>


<div class="updateInvenNum allpop">
    <h1>修改库存<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;"><i style="color:#ff3300;">*</i>库存:<input style="width:220px; height:40px; margin-left:40px;" type="text" name="editInventory" /></span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input type="hidden" id="goodsId"/>
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

<div class="deleteWarn allpop">
    <h1>提示</h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">货品库存大于0，无法删除！</span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" onclick="discoverHtml()" type="button" id="deleteWarnConfirm" value="确定" />
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

        $(".arrow").click(function(){
            var arr=$(this).val();
            $(this).siblings("dd").slideToggle()
            if(arr==0){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_white_up.png) center no-repeat")
                $(this).val("1")
            }else if(arr==1){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_white_down.png) center no-repeat")
                $(this).val("0")
            }

        });
        $(document).on("click","#invenLocation",function(){
            window.open("allocation");
        });
        $(document).on("click","#purchaseOrder",function(){
            window.open("purchaseOrder?purchaseId=");
        });
        $(document).on("click","#addgoods",function(){
            window.location.href = "Addgoods"
        });
    })
</script>

</html>