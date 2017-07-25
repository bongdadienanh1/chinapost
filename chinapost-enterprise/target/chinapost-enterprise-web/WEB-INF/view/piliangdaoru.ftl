<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>

<title>无标题文档</title>
<style type="text/css">
    @media screen and ( max-width: 1400px) {
        .import{
            line-height: 24px!important;
        }
    }
body{
	background:#f7f7f7;
	}

a{
	color:#20abf6;
	cursor:pointer;
}
#notice{
  width:100%;
  height:45px;
  line-height: 45px;
  width: 120%;
  margin-left: 20px;
  margin-top: 12px;
  border-radius: 5px;
  font-size: 13px;
  text-align: left;
  padding-left: 18px;
}

#muban{
	width:100px;
	height:30px;
	background:#999;
	border-style:none;
	border-radius:3px;
	color:#fff;
	position:relative;
	left:60px;
	top:30px;
	cursor:pointer;
	}
form > .file{
    display: inline-block;
    width:100px;
    height:30px;
    text-align: center;
    line-height: 30px;
    background:#24b383;
    border-style:none;
    border-radius:3px;
    color:#fff;
    position:relative;
    left:20px;
    top:30px;
    cursor:pointer;
}
form > .file input{
    opacity: 0;
    position:absolute;
    top:0;
    left:0;
    width:100px;
    height:30px;
}
#list_submit{
    width: 0;
    height: 0;
    display: none;
}
    .select{
        display: inline-block;
        vertical-align: middle;
        width:120px;
        height:32px;
        margin-left:11px;
        background:#f8f8f8;
        text-align: center;
        position: relative;
    }
    .arrow{
        display: inline-block;
        position: absolute;
        right: -5px;
        top: 0px;
        width: 36px;
        height: 32px;
        background:url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat;
    }

.select dd,.select dt{
        width:120px;
        height:32px;
        line-height: 32px;
        border:1px solid #e5e5e5!important;
        background:#f8f8f8;
        margin-top: -1px;
        margin-left:-1px;
        cursor: pointer;
    }
    .select dt{
        border-radius: 32px;
    }
    .select dd{
        display: none;
        overflow: hidden;
    }
    .select dd:hover{
        color: #fff;
        background: #54a6de;
    }
    .import{
        display: inline-block;
        margin-left:20px;
        cursor:pointer;
        text-align: center;
        line-height: 32px;
    }

</style>
</head>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    $(document).ready(function(){
        $(".arrow").click(function(){
            var arr=$(this).val()
            $(this).siblings("dd").slideToggle()
            if(arr==0){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_black_up.png) center no-repeat")
                $(this).val("1")
            }else if(arr==1){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat")
                $(this).val("0")
            }

        })
    })
</script>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:800px;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <#--<span>单个转账</span><a class="leftshanow" href="piliangdaoru">批量导入</a><abbr></abbr>-->
        <span>批量导入</span><a class="leftshanow" href="sendReduceRecode">发放扣减记录</a><abbr></abbr>
    </div>



<div id="notice">通过Excel导入，实现批量发放。具体发放到用户身份证号对应的账户中，会员来网点激活账户，使用邮豆消费。一次最多只能导入两万条</div>
    <form action="list" method="post">
        <div class="import allclickButton" id="send">
            批量发放
        </div><input type="file" id="send_csv" accept=".csv,.xlsx,.xls" style="display:none;" />
        <div class="import allclickButton" id="reduce">
            批量扣减
        </div><input type="file" id="reduce_csv" accept=".csv,.xlsx,.xls" style="display:none;" />
        <input id="key" type="hidden" name="key" />
        <input id="business_type" type="hidden" name="business_type" />
        <input type="submit" id="list_submit" />
    </form>
<#--<form action="list" method="post" class="select">-->
    <#--<input type="file" name="" id="csv" style="display:none;" multiple="multiple"  name="csv" accept=".csv,.xlsx,.xls" />-->
    <#--<dl class="select"> <i value="0" class="arrow"></i>-->
        <#--<dt class="allSelectButton" name="active">导入模板${businessTypes}</dt>-->
    <#--<#if !businessTypes?exists>-->
        <#--<dd class="allSelectButton">无业务类型</dd>-->
    <#--<#else>-->
        <#--<#list businessTypes as object>-->
            <#--<dd data-id="${object.typeId}" class="import allSelectButton">${object.typeName}</dd>-->
        <#--</#list>-->
    <#--</#if>-->
    <#--</dl>-->
    <#--<input id="json_data" type="hidden" name="data" />-->
    <#--<input id="typeId" type="hidden" name="typeId">-->
    <#--<input type="submit" id="list_submit" />-->
<#--</form>-->
<#--<dl class="select"> <i value="0" class="arrow"></i>-->
    <#--<dt class="allSelectButton" name="active">下载模板${businessTypes.size}</dt>-->
    <#--<#if !businessTypes?exists>-->
        <#--<dd class="allSelectButton">无业务类型</dd>-->
    <#--<#else>-->
        <#--<#list businessTypes as object>-->
            <#--<dd data-id="${object.typeId}" class="templateDown allSelectButton">${object.typeName}</dd>-->
        <#--</#list>-->
    <#--</#if>-->
<#--</dl>-->
<script type="text/javascript">
    $(".templateDown").click(function(){
        window.location.href = '${bath}/web/api/exportExcel/templateDown?typeId=' + $(this).data("id");
    });
    $(".import").click(function(){
        $("#csv").attr("data-id",$(this).data("id"));
        $("#csv").trigger("click");

    });

    $(".import").click(function(){
       $(this).next().trigger('click');
    });
</script>
    </div>

<div class="loading">
    <h1>Excel数据正在导入，请耐心等待.......</h1>
    <img width="250" height="150" src="${bath}/static/img/loading3.gif">
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script src="${bath}/static/js/piliangdaoru.js?version=${VERSION}"></script>

</html>
