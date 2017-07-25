<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px) {

        }
        .itemListHead{
            width: 1000px;
            height: auto;
            overflow: hidden;
            margin: auto;
            background: #fff;
            border: 1px solid #dedede;
        }
        .itemListHead div.HeadLeft{
            width: 399px;
            height: 440px;
            float: left;
        }
        .itemListHead div.HeadRight{
            width: 599px;
            height: auto;
            float: right;
        }
        .itemListHead div.HeadLeft abbr{
            display: block;
            vertical-align: middle;
        }
        .itemListHead div.HeadLeft abbr:first-child{
            width: 399px;
            height: 350px;
            margin-top: 20px;
        }
        .itemListHead div.HeadLeft abbr:first-child img{
            width: 350px;
            height: 350px;
            margin-left: 20px;
        }

        .itemName{
            width:auto;
            overflow: hidden;
        }

        .itemListHead div.HeadLeft abbr:nth-child(2){
             width: 399px;
             height: 50px;
             margin-top: 15px;
         }
        .itemListHead div.HeadLeft abbr:nth-child(2) s{
            display: inline-block;
            width: 359px;
            height: 50px;
            overflow: hidden;
        }
        .itemListHead div.HeadLeft abbr:nth-child(2) span{
            display: inline-block;
            width: 9999px;
            height: 50px;
            position: relative;;
            left: 0px;
        }
        .itemListHead div.HeadLeft abbr:nth-child(2) img{
            width: 48px;
            height: 48px;
            margin: 0px 10px;
            border: 1px solid transparent;
        }
        .itemListHead div.HeadLeft abbr:nth-child(2) img:hover{
            border: 1px solid #ff3300;
        }
        .itemListHead div.HeadLeft abbr:nth-child(2) i.changeLeft{
            display: inline-block;
            vertical-align: middle;
            width: 20px;
            height: 50px;
            line-height: 50px;
            float: left;
            background:url(${bath}/static/img/item_left.gif) center no-repeat;
        }
        .itemListHead div.HeadLeft abbr:nth-child(2) i.changeRight{
            display: inline-block;
            vertical-align: middle;
            width: 20px;
            height: 50px;
            line-height: 50px;
            float: right;
            background:url(${bath}/static/img/item_right.gif) center no-repeat;
        }
        .itemListHead div.HeadRight li{
            width: 450px;
            height: 55px;
            margin: 5px 0px;
            line-height: 55px;

        }
        .itemListHead div.HeadRight li:first-child{
            padding-top: 20px;
            font-weight: bold;
        }
        .itemListHead div.HeadRight li:nth-child(2){
             background: #edf3f8;
         }
        .itemListHead div.HeadRight li.goodsType span{
            display: inline-block;
            width: 70px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            margin:0px 10px;
            border: 1px solid #cccccc;

        }
        .itemListHead div.HeadRight li.goodsType span.itemColorOn{
            border: 3px solid #ff3300;
        }
        .itemListHead div.HeadRight li:nth-child(5){

        }
        #itemPrice{
            margin:0px 20px;
            color: #ff3300;
            font-size: 48px;
            font-weight: bold;
        }
        #itemNumReduce,#itemNumAdd{
            width: 40px;
            height: 30px;
            border: 1px solid #cccccc;
            background: #fff;
        }
        input[name='itemNum']{
            width: 50px;
            height: 30px;
            border: 1px solid #cccccc;
            margin: 0px 10px;
            background: #fff;
            text-align: center;
        }
        #itemBuy{
            width: 120px;
        }
        #itemColor{
            font-weight: bold;
        }
        .itemListbody{
            width: 1000px;
            height: auto;
            overflow: hidden;
            margin: auto;
            background: #fff;
            margin-top: -1px;
            border: 1px solid #dedede;

        }
        .itemListbody h1{
            width: 1000px;
            height: 25px;
            background: #f2f2f2;
        }
        .itemListbodyHead{
            width: 880px;
            height: 50px;
            position: relative;


        }
        .itemListbodyHead li{
            float: left;
            width: 120px;
            height: 45px;
            line-height: 45px;
            text-align: center;
            border: 2px solid transparent;
            z-index: 2;
        }
        .itemListbodyHead li.listOn{
            border: 2px solid #ff3300;
            border-bottom: 2px solid #fff;
        }
        .itemListIntorduce{
            width: 880px;
            height: auto;
            overflow: hidden;

        }
        .itemListIntorduce dt{
            width: 880px;
            height: auto;
            overflow: hidden;
            border-top:1px solid #ccc ;
            border-bottom:1px solid #ccc ;
            margin-bottom: 20px;

        }
        .itemListIntorduce dt abbr{
            display: inline-block;
            width: 250px;
            margin-right: 20px;
            padding-left: 10px;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .itemListIntorduce dd li:first-child{
            height: 50px;
            margin-left: 20px;
            margin-bottom: 10px;
        }


        .itemListdetail{
            width: 1000px;
            height: auto;
            overflow: hidden;
            margin: auto;
            background: #fff;
            margin-top: -1px;
            display: none;

        }
        .itemListdetail dt{
            width: 1000px;
            height: 30px;
            line-height: 30px;
            padding-left: 20px;
            background: #fafafa;
            border: 1px solid #cccccc;
            font-weight: bold;
            font-size: 14px;
        }
        .itemListdetail dd{
            font-size: 12px;
            color: #999;
            width: 1000px;
            height:auto;
            overflow: hidden;
            background: #fff;
        }
        .itemListdetail dd abbr{
             display: inline-block;
             width: 180px;
             line-height: 30px;
             border: 1px solid #cccccc;
            margin-left: -1px;
            margin-top: -1px;
            padding-left: 20px;
         }
        .itemListdetail dd span{
            display: inline-block;
            width: 778px;
            line-height: 30px;
            border: 1px solid #cccccc;
            margin-left: -1px;
            margin-top: -1px;
            padding-left: 20px;
        }
        .detail{
            font-size: 10px;
        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var goodsInfoId = ${id};
    </script>
    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="height: auto;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span><#if topShow>代客下单<#else>商品详情</#if></span><abbr></abbr>
    </div>
<div class="itemListHead">
        <div class="HeadLeft">
            <abbr class="showImg">
                <img src="${goodInfo.goodsInfoImgId}" width="100" height="99" />
            </abbr>
            <abbr class="imglist">
                <i class="changeLeft"></i>
                <s>
            <span>
                <#list goodInfo.getImages() as img>
                    <img src="${img.imageBigName}" width="100" height="99" />
                </#list>

            </span>
                </s>
                <i class="changeRight"></i>
            </abbr>
        </div>
        <div class="HeadRight">
            <ul>
                <li title="${goodInfo.goodsInfoName}" class="itemName">${goodInfo.goodsInfoName}</li>
                <li>价格:<span id="itemPrice" class="addCommas">${goodInfo.goodsInfoPreferPrice}</span>邮豆</li>
                <li>温馨提示:<img src="${bath}/static/img/sevenDay.png" width="30" height="30" />七天无理由退换</li>
                <#list goodInfo.specs as spec>
                    <li class="goodsType"> <#if topShow>选择<#else>规格</#if>${spec.name}:
                        <#list spec.details as detail>
                            <span class="detail" value="${detail.detailId}">${detail.value}</span>
                        </#list>
                    </li>
                </#list>
                <#if topShow>
                <li >购买数量:<input id="itemNumReduce" type="button" value="-"><input maxlength="4" name="itemNum" value="1" type="text"><input id="itemNumAdd" type="button" value="+" /> </li>
                <li>已选择&nbsp;<span id="itemColor"></span></li>
                <li><input class="allclickButton" id="itemBuy" type="button" value="立即购买"><input style="margin-left: 20px" class="allseachButton" id="addshop" type="button" value="加入购物车"></li>
                </#if>
            </ul>
        </div>
</div>

<div class="itemListbody">
    <h1></h1>
    <div class="itemListbodyHead">
        <ul>
            <li value="0" class="listOn">商品介绍</li>
            <li value="1">参数规格</li>
        </ul>
    </div>
    <dl class="itemListIntorduce">
        <dt>
            <#list goodInfo.extSpecs as extSpec>
                <abbr>${extSpec.name}:<span>${extSpec.value}</span></abbr>
            </#list>
        </dt>
        <dd>
            <ul>
                <li class="itemName">商品名称:${goodInfo.goodsInfoName} <br/>商品编号:${goodInfo.goodsInfoItemNo}</li>
                <li> ${goodInfo.goodsDetailDesc}</li>
            </ul>


        </dd>
    </dl>

    <dl class="itemListdetail">
        <dt>
            详细参数
        </dt>
        <dd>
            <#list goodInfo.exParams as exParam>
                <abbr>${exParam.name}:</abbr><span>${exParam.value}</span>
            </#list>

        </dd>


    </dl>
</div>







</div>

</body>



<script type="text/javascript" src="${bath}/static/js/itemListDetail.js?version=${VERSION}"></script>


</html>
