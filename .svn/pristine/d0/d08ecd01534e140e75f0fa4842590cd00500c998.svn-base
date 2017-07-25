<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/formCenter.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
</head>

<style type="text/css">
    @media screen and ( max-width: 1400px){
        #listDetailBottom{
            transform: scale(0.8,0.8);
            transform-origin: left top;
            -webkit-transform: scale(0.8,0.8);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.8,0.8);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.8,0.8);
            -moz-transform-origin: left top;
        }
    }
    #listDetail{
        margin-top: 20px;
        margin-left: 20px;
    }
    #listDetail dl{
        height: 600px;
        overflow-x: hidden;
        overflow-y: scroll;
    }
    #listDetail dt{
        width: 1200px;
        background: #edf3f8;
        height: 60px;
        line-height: 60px;
    }
    #listDetail dt abbr, #listDetail dd abbr{
        display: inline-block;
        vertical-align: middle;
        width: 200px;
    }
    #listDetail dt abbr:first-child,#listDetail dd abbr:first-child{
        width: 549px;
        margin-left: 20px;
    }

    #listDetail dd{
        width: 1200px;
        border-bottom: 1px solid #e5e5e5;
        height: 100px;
        line-height: 100px;
    }
    .checkItemNum input{
        width: 25px;
        height: 25px;
        background: #edf3f8;
        border: 1px solid #e5e5e5;
    }
    .checkItemNum input[type='text']{
        margin: 0px -4px;
        background: #fff;
        width: 40px;
        text-align: center;
    }
    #listDetailBottom{
        width: 1200px;
        height: 80px;
        line-height: 80px;
        background: #edf3f8;
    }
    #allMoney{
        margin: 0px 20px;
    }
    .itemDetail{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $(document).on("click",".itemAdd",function(){
            var num = parseInt($(this).siblings(".itemNumber").val())
            if(num<9999) {
                $(this).siblings(".itemNumber").val(num + 1);
                var perPrice = parseFloat($(this).parent().siblings(".itemPrice").children("span").html().replace(",","").replace(",","").replace(",",""))
                $(this).parent().siblings(".sumNumber").children("span").html(addCommas(perPrice *  $(this).siblings(".itemNumber").val(),2))
            }

            var num1 = 0
            var price = 0
            $("input[name='chooseList']:checked").parent().siblings(".checkItemNum").children(".itemNumber").each(function(){
                num1 = parseInt(num1) + parseInt($(this).val().replace(",","").replace(",","").replace(",",""))
                $("#allNumber span").html(num1)
            });
            $("input[name='chooseList']:checked").parent().siblings(".sumNumber").children("span").each(function(){
                price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
                $("#allMoney span").html(addCommas(price,2))
            });
        })
        $(document).on("click",".itemReduce",function(){
            var num=parseInt($(this).siblings(".itemNumber").val())
            if(num>1){
                $(this).siblings(".itemNumber").val(num-1);
                var perPrice = parseFloat($(this).parent().siblings(".itemPrice").children("span").html().replace(",","").replace(",","").replace(",",""))
                $(this).parent().siblings(".sumNumber").children("span").html(addCommas(perPrice *  $(this).siblings(".itemNumber").val(),2))
            }
            var num1 = 0
            var price = 0
            $("input[name='chooseList']:checked").parent().siblings(".checkItemNum").children(".itemNumber").each(function(){
                num1 = parseInt(num1) + parseInt($(this).val().replace(",","").replace(",","").replace(",",""))
                $("#allNumber span").html(num1)
            });
            $("input[name='chooseList']:checked").parent().siblings(".sumNumber").children("span").each(function(){
                price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
                $("#allMoney span").html(addCommas(price,2))
            });
        })
        $(document).on("input",".itemNumber",function(){
            var str = /^[1-9]*[1-9][0-9]*$/;
            var count = $(this).val();
            var _this=this
            console.log(count)
            if( str.test(count) && count!=0) {
                var perPrice = parseFloat($(this).parent().siblings(".itemPrice").children("span").html().replace(",","").replace(",","").replace(",",""))
                $(this).parent().siblings(".sumNumber").children("span").html(addCommas(perPrice * count,2))
                var num1 = 0
                var price = 0
                $("input[name='chooseList']:checked").parent().siblings(".checkItemNum").children(".itemNumber").each(function(){
                    num1 = parseInt(num1) + parseInt($(this).val().replace(",","").replace(",","").replace(",",""))
                    $("#allNumber span").html(num1)
                });
                $("input[name='chooseList']:checked").parent().siblings(".sumNumber").children("span").each(function(){
                    price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
                    $("#allMoney span").html(addCommas(price,2))
                });
            }else{
                $(this).prop("readonly","readonly")
                response_ensure_alert("error","请输入正确的购买数量",function(){
                    $(_this).val(1)
                    $(_this).prop("readonly","")
                })
            }
        })
        $(document).on("click","input[name='chooseList']",function(){
            if($("input[name='chooseList']:checked").length == 0){
                $("#allNumber span").html("0")
                $("#allMoney span").html("0.00")
                $("#itemClear").css("background","#999999")
            }else{
                var num1 = 0
                var price = 0
                $("#itemClear").css("background","#f56c73")
                $("input[name='chooseList']:checked").parent().siblings(".checkItemNum").children(".itemNumber").each(function(){
                    num1 = parseInt(num1) + parseInt($(this).val().replace(",","").replace(",","").replace(",",""))
                    $("#allNumber span").html(num1)
                });
                $("input[name='chooseList']:checked").parent().siblings(".sumNumber").children("span").each(function(){
                    price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
                    $("#allMoney span").html(addCommas(price,2))
                });
            }

        })
        $("input[name='chooseListAll']").click(function(){
            if(!$(this).is(":checked")){
                $("#itemClear").css("background","#999999")
                $("input[name='chooseList']:checked").trigger("click")
                $("#allNumber span").html("0")
                $("#allMoney span").html("0.00")
            }else{
                $("input[name='chooseList']:not(:checked)").trigger("click")
                var num1 = 0
                var price = 0
                $("input[name='chooseList']:checked").parent().siblings(".checkItemNum").children(".itemNumber").each(function(){
                    num1 = parseInt(num1) + parseInt($(this).val().replace(",","").replace(",","").replace(",",""))
                    $("#allNumber span").html(num1)
                });
                $("input[name='chooseList']:checked").parent().siblings(".sumNumber").children("span").each(function(){
                    price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
                    $("#allMoney span").html(addCommas(price,2))
                });
                $("#itemClear").css("background","#f56c73")
            }
        })
    })
</script>
<body style="background: #edf3f8">
<div class="allOutShow" style="height: 1000px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>购物车</span><abbr></abbr>
    </div>

<div id="listDetail">
    <dl>
        <dt>
            <abbr>商品</abbr>
            <abbr>单价</abbr>
            <abbr>数量</abbr>
            <abbr>金额</abbr>
        </dt>

    </dl>
    <div id="listDetailBottom">
        <ul>
            <li style="float: left"><input type="checkbox" checked="checked" name="chooseListAll">全选
            <a href="itemList" style="color:#54a6de;"><img style="margin-left: 20px" width="16" height="16" src="${bath}/static/img/back.png">返回继续添加商品</a>
            </li>
            <li style="float: right">
                <abbr id="allNumber">已选择<span style="color: #ff3300">0</span>件商品</abbr>
                <abbr id="allMoney">合计(不含运费)：<span style="color: #ff3300">0.00</span>邮豆</abbr>
                <input style="margin-right: 20px;height:32px;width:140px;border-radius: 16px;color: #fff;border: none;" type="button" class="" value="结算" id="itemClear">
            </li>
        </ul>

    </div>
</div>







</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script src="../static/js/shoppingcart.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var num1 = 0
        var price = 0
        $(".itemNumber").each(function(){
            num1 = parseInt(num1) + parseInt($(this).val())
            $("#allNumber span").html(num1)
        });
        $(".sumNumber span").each(function(){
            price = parseFloat(price) + parseFloat($(this).html().replace(",","").replace(",","").replace(",",""))
            $("#allMoney span").html(addCommas(price,2))
        });
        if($("input[name='chooseList']:checked").length == 0){
            $("#itemClear").css("background","#999999")
        }else{
            $("#itemClear").css("background","#f56c73")
        }
    })
</script>
