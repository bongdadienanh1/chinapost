<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/js/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/js/kindeditor/plugins/code/prettify.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jPages.css" />
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${bath}/static/js/kindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript" src="${bath}/static/js/kindeditor/plugins/code/prettify.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400) {
       #chooseOneStep,#chooseTwoStep{
           transform: scale(0.9,0.9);
           transform-origin: left top;
           -webkit-transform: scale(0.9,0.9);
           -webkit-transform-origin: left top;
           -ms-transform: scale(0.9,0.9);
           -ms-transform-origin: left top;
           -moz-transform: scale(0.9,0.9);
           -moz-transform-origin: left top;
       }
        #chooseThreeStep,#chooseThreeStep li dl{
            transform: scale(0.9,0.9);
            transform-origin: left top;
            -webkit-transform: scale(0.9,0.9);
            -webkit-transform-origin: left top;
            -ms-transform: scale(0.9,0.9);
            -ms-transform-origin: left top;
            -moz-transform: scale(0.9,0.9);
            -moz-transform-origin: left top;
        }
        .addImg{
            width: 120px!important;
        }
    }
    #chooseOneStep{
        margin-top: 50px;
        margin-left: 20px;
    }
    #chooseOneStep div{
        float: left;
    }
    #chooseOneStep div img{
        position: relative;
        left: -40px;
        cursor: pointer;
    }
    #chooseOneStep div input[type='text']{
        width: 320px;
        height: 24px;
        font-size: 16px;
        border-radius:24px ;
        border: 1px solid #e5e5e5;
        padding-left: 20px;
    }
    #chooseOneStep div ul{
        width: 360px;
        height: 400px;
        overflow-y: scroll;
        overflow-x: hidden;
    }
    #chooseOneStep div ul li{
        height: 50px;
        line-height: 50px;
        padding-left: 20px;
        cursor: pointer;
    }
    #chooseOneStep div ul li.chooseOneStepFirst, #chooseOneStep div ul li.chooseOneStepTwo, #chooseOneStep div ul li.chooseOneStepThree{
        background: #54a6de;
        color: #fff;
    }
    #chooseOneStep div ul li:hover{
        background: #54a6de;
        color: #fff;
    }
    .selfFoot{
        width: 100%;
        text-align: center;
        display: block;
        margin-top:250px;
    }
    #chooseOneStepRoad{

        width: 100%;
    }
    .selfFoot span{
        padding: 8px;
        border-radius: 24px;
        height: 16px;
        background: #54a6de;
        color: #fff;
        cursor: pointer;
        line-height: 16px;
    }
    .selfFoot abbr{
        padding: 5px;
        border-radius: 24px;
        height: 16px;
        background: #bbbbbb;
        color: #fff;
        cursor: pointer;
        margin-right: 20px;
        line-height: 16px;
    }
    #chooseTwoStep{
        display: none;
        margin-top: 50px;
        margin-left: 20px;
    }
    #chooseTwoStep li{
        width: 100%;
        padding: 10px;
        margin: 10px 0px;
    }
    #chooseTwoStep li input[type='text']{
        border: 1px solid #e5e5e5;
        border-radius: 2px;
        height: 24px;
        padding-left: 24px;
        margin: 5px 0px;
    }
    #chooseTwoStep li select{
        width: 150px;
        height: 32px;
    }
    #chooseTwoStep li input[type='radio'], #chooseTwoStep li input[type='button']{
        margin: 0px 10px;
    }
    #chooseTwoStep li abbr, #chooseTwoStep li div{
        display: inline-block;
        vertical-align: middle;
    }
    #chooseTwoStep li abbr{
        text-align: left;
        width: 200px;
        margin-right: 30px;
    }
    #chooseTwoStep li abbr i{
        color: #ff3300;
    }
    #chooseThreeStep{
        display: none;
        margin-top: 50px;
        margin-left: 20px;
    }
    #chooseThreeStep ul{
        position: relative;
        height: 400px;
        width: 1600px;
    }
    #chooseThreeStep li{
        width: 180px;
        height: 24px;
        text-align: center;
        line-height: 24px;
        float: left;
        margin-right: 20px;
        cursor: pointer;
        position: absolute;
    }
    #chooseThreeStep li span{
        display: inline-block;
        width: 150px;
        height: 24px;
        overflow: hidden;
    }
    #chooseThreeStep li.on{
        border-bottom: 2px solid #54a6de;
    }
    #chooseThreeStep li dl{
        width: 100%;
        height: 400px;
        display: none;
        position: absolute;
        left: 50px;
    }
    #chooseThreeStep li dl dd{
        width: 1000px;
        padding: 10px 0px;
        text-align: left;
        margin-top: 20px;

    }
    #chooseThreeStep li dl dd a{
        margin: 0px 10px;
        color: #54a6de;
    }
    #chooseThreeStep li dl dd span, #chooseThreeStep li dl dd div{
        display: inline-block;
        vertical-align: middle;
    }
    #chooseThreeStep li dl dd i{
        color: #ff3300;
    }
    #chooseThreeStep li dl dd input[type='text']{
        border: 1px solid #e5e5e5;
        border-radius: 2px;
        height: 24px;
        padding-left: 24px;
    }
    #selfTitle{
        margin-top: 20px;
        margin-left: 50px;
        font-size: 18px;
        color: #333;
    }
    #addStandardDetail{
        width:480px;
        height:auto;
        position:fixed;
        left:15%;
        top:15%;
        background:#FFF;
        z-index:99;
        display:none;
    }
    #addStandardDetail h1 i{
        background:url(${bath}/static/img/XX.png) center no-repeat;
    }
    #addStandardDetail div{
        margin-top: 20px;
        margin-left: 20px;
    }
    #addStandardDetail div input[type="text"]{
        border: 1px solid #e5e5e5;
        border-radius: 2px;
        height: 24px;
        padding-left: 24px;
        margin: 5px 0px;
    }
    #addStandardDetail div span i{
        color: #ff3300;
    }
    #addStandardDetail div table{
        margin: 20px;
        margin-left: 0px;
    }
    #addStandardDetail div table ,#addStandardDetail div table tbody,#addStandardDetail div table thead{
        width: 400px;
    }
    #addStandardDetail div table th, #addStandardDetail div table td{
        text-align: left;
    }
    #addStandardVal{
        margin: 20px 0px 0px 20px;
    }
    #addStandardValComfirm{
        margin-left: 170px;
        margin-bottom: 30px;
    }
    #updateStandardValComfirm{
        margin-left: 270px;
        margin-bottom: 30px;
    }
    .standard{
        display: block!important;
        margin-left: 240px;
        margin-top: 30px;
    }
    .standard div{
        width: 600px;
        display: block!important;
        border: 1px solid #e5e5e5;
        border-radius: 10px;
        margin: 20px 0px;
    }
    .standard div li{
        width: 520px!important;
        margin-left: 30px!important;
    }
    .standard div li section{
        display: inline-block;
        width: 400px;
        overflow: hidden;
        margin-left: 90px;
    }
    .standard div li section a{
        display: inline-block;
        padding: 5px;
        margin: 5px;
        border: 1px solid #e5e5e5;
    }
    .standard div li img{
        float: right;
        margin: 0px 10px;
        cursor: pointer;
    }
    .standard div li:nth-child(2){
        position: relative;
    }
    .standard div li span{
        display: inline-block;
        width: 80px;

        margin-right: 20px;
    }
    .standard div li:nth-child(2) span{
        position: absolute;
    }
    .standard div li:first-child{
        border-bottom: 1px solid #e5e5e5;
    }
    .addStandardValTable{
        margin-top: 30px;
        margin-left: 240px;
        width: 600px;
        margin-bottom: 50px;
    }
    .addStandardValTable th,.addStandardValTable td{
        text-align: left;
    }
    #upload{
        display: none;
        width: 400px;
        height: 350px;
        position: fixed;
        left: 10%;
        top:20%;
        z-index: 3;
        background: #fff;
    }
    #accountNum{
        display: none;
        width: 400px;
        position: fixed;
        left: 10%;
        top:20%;
        z-index: 3;
        background: #fff;
    }
    #accountNum li{
        width: 100%;
        margin: 20px 0px;
    }

    #upload li{
        width: 100%;
        height: 20px;
    }
    #upload li:first-child{
        height: 200px;
    }
    .showImgs i{
        display: inline-block;
        width: 16px;
        height: 16px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        position: relative;
        left: -16px;
        top: -13px;
    }
    #itemBrand{
        width: 300px;
        min-height: 32px;
        max-height: 300px;
        overflow-y: scroll;
        position: relative;
        background: #fff;
        z-index: 1;
    }
    #itemBrand dt,#itemBrand dd{
        height: 32px;
        line-height: 32px;
        border: 1px solid #e5e5e5;
        margin-top: -1px;
        padding-left: 20px;
        background: #fff;
    }
    #itemBrand dt:hover,#itemBrand dd:not(#brandSearch):hover{
        color: #fff;
        background: #54a6de;
    }
    #itemBrand dd{
        display: none;
    }
    .bigInt{
        margin-left: 20px;
        font-size: 15px;
        color: #ff3300;
        display: block!important;
    }
    #unitShow{
        margin-left: 20px;
    }
</style>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:800px;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
    <#--<span>单个转账</span><a class="leftshanow" href="piliangdaoru">批量导入</a><abbr></abbr>-->
        <span>上传自购商品</span><abbr></abbr>
    </div>
    <div id="selfTitle">商品分类</div>
    <div id="chooseOneStep">
    <div>
        <input type="text" placeholder="输入名称查找">
        <img id="chooseOneStepOne" width="16" height="16" src="${bath}/static/img/searchImg.png">
        <ul id="firstContain">

        </ul>
    </div>
    <div>
        <input type="text" placeholder="输入名称查找">
        <img id="chooseOneStepTwo" width="16" height="16" src="${bath}/static/img/searchImg.png">
        <ul id="secondContain">

        </ul>
    </div>
    <div>
        <input type="text" placeholder="输入名称查找">
        <img id="chooseOneStepThree" width="16" height="16" src="${bath}/static/img/searchImg.png">
        <ul id="thirdContain">

        </ul>
    </div>

        <div id="chooseOneStepRoad">当前选择的是:<span></span><span></span><span></span></div>
        <div class="selfFoot">
            <span id="goToStepTwo">确认选择类目并进入下一步</span>
        </div>
    </div>

    <div id="chooseTwoStep">
        <ul>
            <li><abbr><i>*</i>商品标题:</abbr><div><input id="itemName" style="width: 400px" placeholder="商品标题不可为空" type="text" ></div></li>
            <li><abbr><i>*</i>商城价（邮豆）:</abbr><div><input maxlength="9" id="priceA" placeholder="商城价" type="text" ></div></li>
            <li><abbr><i>*</i>结算价（RMB）:</abbr><div><input maxlength="9" id="priceB" placeholder="结算价" type="text" ></div></li>
            <li><abbr><i>*</i>单位:</abbr><div><input id="goodsInfoUnit" placeholder="单位" type="text" ></div></li>
            <li><abbr>装箱数:</abbr><div><input id="goodsInfoPack" placeholder="装箱数" type="text" ><span id="unitShow"></span>/箱</div></li>
            <li><abbr><i>*</i>是否上架:</abbr><div><input checked="checked" name="setUpload" type="radio" value="1">立即上架<input name="setUpload" type="radio" value="0">下架</div></li>
            <li style="z-index: 2"><abbr><i>*</i>商品品牌:</abbr><div>
                <dl id="itemBrand">
                <dt data-id="0"id="brandAppend">请选择</dt>
                <dd id="brandSearch"><input placeholder="输入文字搜索" type="text"> </dd>
                </dl>
            </div>
            </li>
            <li><abbr><i>*</i>规格:</abbr><div><input id="addStandard" class="allseachButton" type="button" value="添加规格" ></div>
                <div class="standard">

                </div>
            </li>
            <li><abbr>详细参数:</abbr><div><input id="addParameterl" class="allseachButton" type="button" value="添加参数" ></div>
                <table class="addStandardValTable">
                    <thead>
                    <tr>
                        <th>参数值名称</th>
                        <th>参数值</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="parameterlContain">

                    </tbody>
                </table>
            </li>
            <li><abbr>商品详情:</abbr>
                <textarea id="itemDetail" name="goods_desc" cols="100" rows="8" style="width: 1200px; height: 200px;"></textarea>
            </li>
        </ul>
        <div class="selfFoot">
            <abbr id="backToStepOne">返回上一步</abbr>
            <span id="goToStepThree">进入下一步</span>
        </div>
    </div>

    <div id="chooseThreeStep">
        <ul class="ThreeStepHtml">
            <li class="on"><span>米黄色</span>*<span>1个</span>
                <dl>
                    <dd><span><i>*</i>货品编号:</span><div><input style="width: 400px" placeholder="货品编号" type="text" ><a id="addAccountNum">机构标识</a><a id="setIdNum">生成编号</a><input id="accountNum" type="hidden"> </div></dd>
                    <dd><span><i>*</i>货品标题:</span><div><input style="width: 400px" placeholder="货品标题" type="text" ></div></dd>
                    <dd><span><i>*</i>货品价格:</span><div>商城价（邮豆）：<input style="width: 80px" type="text" ></div><div>结算价（RMB）：<input style="width: 80px" type="text" ></div></dd>
                    <dd><span><i>*</i>货品图片:</span><div><input class="allclickButton" style="width: 400px" value="编辑货品图片" type="button" ></div></dd>
                    <dd><span><i>*</i>已选图片:</span><div><img width="50" height="50"><i></i></div></dd>
                </dl>
            </li>
            <li><span>米黄色</span>*<span>1个</span>
                <dl>
                    <dd><span><i>*</i>货品编号:</span><div><input style="width: 400px" placeholder="货品编号" type="text" ><a>机构标识</a><a>生成编号</a></div></dd>
                    <dd><span><i>*</i>货品标题:</span><div><input style="width: 400px" placeholder="货品标题" type="text" ></div></dd>
                    <dd><span><i>*</i>货品价格:</span><div>商城价（邮豆）：<input style="width: 80px" type="text" ></div><div>结算价（RMB）：<input style="width: 80px" type="text" ></div></dd>
                    <dd><span><i>*</i>货品图片:</span><div><input class="allclickButton" style="width: 400px" value="编辑货品图片" type="button" ></div></dd>
                    <dd><span><i>*</i>已选图片:</span><div><img width="50" height="50"><i></i></div></dd>
                </dl>
            </li>
            <li><span>米黄色</span>*<span>1个</span>
                <dl>
                    <dd><span><i>*</i>货品编号:</span><div><input style="width: 400px" placeholder="货品编号" type="text" ><a>机构标识</a><a>生成编号</a></div></dd>
                    <dd><span><i>*</i>货品标题:</span><div><input style="width: 400px" placeholder="货品标题" type="text" ></div></dd>
                    <dd><span><i>*</i>货品价格:</span><div>商城价（邮豆）：<input style="width: 80px" type="text" ></div><div>结算价（RMB）：<input style="width: 80px" type="text" ></div></dd>
                    <dd><span><i>*</i>货品图片:</span><div><input class="allclickButton" style="width: 400px" value="编辑货品图片" type="button" ></div></dd>
                    <dd><span><i>*</i>已选图片:</span><div><img width="50" height="50"><i></i></div></dd>
                </dl>
            </li>
        </ul>
        <div class="selfFoot">
            <abbr id="backToStepTwo">返回上一步</abbr>
            <span id="outputItem">发布商品</span>
        </div>
    </div>

    <div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<div>

<div class="allpop" id="addStandardDetail">
    <h1>添加规格<i></i></h1>
    <div><span><i>*</i>规格名称:</span><input id="StandardName" type="text"></div>
    <input type="button" id="addStandardVal" class="allseachButton" value="添加规格值">
    <div>
        <table>
            <thead>
                <tr>
                    <th>规格值名称</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody class="StandardValContain">
                <tr>
                    <td><input type="text" class="StandardVal"></td>
                    <td><a class="deleteStandardVal">删除</a></td>
                </tr>
            </tbody>
        </table>
    </div>
    <input id="addStandardValComfirm" type="button" class="allseachButton" value="确定"/>
    <input id="updateStandardValComfirm" type="button" class="allseachButton" value="确定"/>
</div>


    <div class="allpop" id="upload">
        <h1>上传商品图片</h1>
        <ul>
            <li style="margin-top: 20px;margin-left: 20px"><img src="" width="150px" height="150px" id="companyLogo"><input id="LogoUpdate" type="file" style="margin-left:20px;width: 80px; height: 30px;"> </li>
            <li><input id="companyLogoCancel" style="background: #bbbbbb; color: #333;margin-left: 80px;" class="button allcancelButton" type="button" value="取消" /><input id="companyLogoConfirm" disabled="disabled" style="margin-left: 20px" class="button allseachButton" value="确定" type="button"/></li>
        </ul>
    </div>


    <div class="allpop" id="accountNum">
        <h1>设置机构标识</h1>
        <ul>
            <li style="margin-top: 20px;margin-left: 20px"><input value="机构标识" type="text" id="accountNumVal"> </li>
            <li><input id="accountNumCancel" style="background: #bbbbbb; color: #333;margin-left: 80px;" class="button allcancelButton" type="button" value="取消" /><input id="accountNumConfirm" style="margin-left: 20px" class="button allseachButton" value="确定" type="button"/></li>
        </ul>
    </div>
</body>
<script src="${bath}/static/js/selfPickItmUpload.js?version=${VERSION}"></script>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">

</script>