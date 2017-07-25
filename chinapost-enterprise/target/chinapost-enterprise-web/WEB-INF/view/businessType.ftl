<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery-ui.css">
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>无标题文档</title>
</head>

<style type="text/css">
    @media screen and ( max-width: 1400px){
        body{
            zoom:62.5%;
            font-size:10px!important;
        }
        #tableHead{
            width: 1601px!important;
        }
        #tableHead li{
            width: 394px!important;
        }
        .tableBody li{
            width: 396px!important;
        }
        .BusbButton{
            width: 200px!important;
        }
    }
    .BusbButton{
        width: 170px;
        height: 50px;
        background: #54a6de;
        color: #fff;
        border: none;
    }
    .BusbButton:hover{

    }
    #tableHead{
        width: 1598px;
        color: #666666;
    }
    #tableHead li{
        float: left;
        height: 50px;
        line-height: 50px;
        border: 1px solid #bbb;
        margin-right: -1px;
        width: 398px;
        text-align: center;
    }
    .tableBody{
        width: 1598px;
        height:auto;
        overflow: hidden;
        margin-top: -2px;
        border-left: 1px solid #bbbbbb;

    }
    .tableBody li{
        float: left;
        text-align: center;
        border-bottom: 1px solid #bbbbbb;
        width: 398px;

    }
    .tableBody span{
        float:right;
    }
    .tableBody li.busName,.tableBody li.busFormula{
        height: 100%;
    }
    .tableBody li.busName abbr,.tableBody li.busParameter abbr,.tableBody li.busParameterVal abbr,.tableBody li.busFormula textfield{
        color: #444444!important;
    }
    .tableBody li.busFormula{
        border-right: 1px solid #bbbbbb;
    }
    .tableBody li.busParameter{
        border-left:1px solid #bbbbbb;
    }
    .tableBody li.busParameter,.tableBody li.busParameterVal{
        height: auto;
        overflow: hidden;
        border-top: 1px solid #bbbbbb;
    }
    .busParameter dd,.busParameterVal dd{
        width: 100%;
        height: 60px;
        line-height: 60px;
        text-align: center;
        border-right:1px solid #bbbbbb ;
        border-bottom: 1px solid #bbbbbb;
        margin-bottom: -1px;
        margin-left: -1px;
    }
    .busChangeButton{
        width: 30px;
        height: 30px;
        display: inline-block;
        vertical-align: middle;
        background:url(${bath}/static/img/bus_change.png) center no-repeat;
        margin: 0px 2px;
    }
    .busDeleteButton{
        width: 30px;
        height: 30px;
        display: inline-block;
        vertical-align: middle;
        background:url(${bath}/static/img/bus_delete.png) center no-repeat;
        margin: 0px 2px;
    }
    .busMoveButton{
        width: 30px;
        height: 30px;
        display: inline-block;
        vertical-align: middle;
        background:url(${bath}/static/img/bus_move.png) center no-repeat;
        margin: 0px 2px;
    }
    .busMoveButtonPar{
        width: 30px;
        height: 30px;
        display: inline-block;
        vertical-align: middle;
        background:url(${bath}/static/img/bus_move.png) center no-repeat;
        margin: 0px 2px;
    }

    .busPopUp{
        width: 700px;
        height: auto;
        overflow: hidden;
        position: fixed;
        left: 10%;
        top: 30%;
        z-index: 3;
        background: #fff;
    }

    .busPopUp li:nth-child(2){
        height: auto;
        overflow: hidden;
    }
    .busPopUp li:nth-child(2) abbr{
        display: inline-block;
        width: 80%;
        height: 50px;
        margin-left: 100px;
        margin-top: 30px;
    }
    .busPopUp li:nth-child(2) abbr input[type="text"]{
        width: 200px;
        height:30px;
        font-size: 18px;
    }
    .busPopUp li:nth-child(2) abbr b{
        display: inline-block;
        font-weight: normal;
        width: 100px;
    }
    .busPopUp li:nth-child(3){
        height: 100px;
        margin: 10px 0px 20px 0px;
    }
    .busPopUp li:nth-child(3) input[type='button']{
        width: 120px!important;
        height: 32px!important;
        color: #ffffff!important;
        background: #b2b2b2!important;
        border: none!important;
        border-radius: 16px!important;
        font-size: 14px!important;
        margin-top: 30px;
    }
    .busPopUp li:nth-child(3) input[type='button']:first-child{
        margin-left: 180px;
    }
    .busPopUp li:nth-child(3) input[type='button']:nth-child(2){
        background: #54a6de!important;
        margin-left: 20px;
    }
    .busParameterValChoose td{
        border-radius: 3px;
        border: 1px solid #e5e5e5;

    }
    .busParameterValChoose table{
        border: 1px solid #e5e5e5;
    }
    .busParameterValChoose td input[type='text']{
        width: 150px!important;
        text-align: center;
        border: none;
    }

    .addValButton{
        text-align: center;
    }


    #busFormulaSet{
        width: 800px;
        height: auto;
        overflow: hidden;
        position: fixed;
        left: 10%;
        top: 30%;
        z-index: 3;
        background: #fff;
    }
    #busFormulaSet h1{
        width: 100%;
        height: 50px;
        line-height: 50px;
        color: #333;
        margin-left: 20px;
        font-weight: bold;
        font-size: 24px;
        border-bottom: 1px solid #bbbbbb;
    }
    #busFormulaContent{
        margin-left: 20px;
        margin-top: 20px;
        width: 760px;
        height: 400px;
        border: 1px solid #bbbbbb;
    }
    #busFormulaContent li{
        float: left;
    }
    #busFormulaContent li:first-child{
        width: 420px;
        height: 149px;
        border-bottom: 1px solid #bbb;
    }
    #busFormulaContent li:nth-child(2){
        width: 338px;
        height: 400px;
        border-left: 1px solid #bbbbbb;
    }
    #busFormulaContent li:nth-child(2) b {
        display: inline-block;
        vertical-align: middle;
        width: 40px;
        height: 40px;
        line-height: 40px;
        text-align: center;
        border: 1px solid #666;
        margin-top: 30px;
        margin-left: 20px;
        cursor: pointer;
    }
    #busFormulaContent li:nth-child(3){
        width: 169px;
        height: 80px;
        position: relative;
        top: -180px;
        border-right:1px solid #bbb ;
        border-bottom:1px solid #bbb ;
        border-top:1px solid #bbb ;

    }
    #busFormulaContent li:nth-child(5){
        width: 420px;
        height: 70px;
        position: relative;
        top: -333px;
        border-right:1px solid #bbb ;
        border-bottom:1px solid #bbb ;
        border-top:1px solid #bbb ;
    }
    #busFormulaContent li:nth-child(5) h2, #busFormulaContent li:nth-child(5) div{
        text-align: center;
        width: 100%;
        margin: 5px 0px;

    }
    #busFormulaContent li:nth-child(6){
        width: 420px;
        height: 98px;
        position: relative;
        top: -253px;
        border-right:1px solid #bbb ;
        border-bottom:1px solid #bbb ;
        border-top:1px solid #bbb ;
    }
    #busFormulaContent li:nth-child(4){
        width: 250px;
        position: relative;
        top: -180px;
        height: 80px;
        border-top:1px solid #bbb ;
        border-bottom:1px solid #bbb ;
    }
    .busFormulaSetButton{
        width: 100px;
        height: 40px;
        color: #fff;
        border: none;
        border-radius: 3px;
        margin-top: 30px;
    }
    .busFormulaSetButton:first-child{
        margin-left: 20px;
    }
    .busFormulaSetButton:nth-child(2){
        background: #54a6de;
        margin-left: 20px;
    }
    .AllAdd{
        cursor: pointer;
    }
    s{
        text-decoration: none;
    }
    #busParmPlace div{
        display: inline-block;
        vertical-align: middle;
        margin-left: 20px;
        padding: 5px;
        margin-top: 5px;
        font-size: 12px;
        border: 1px solid #666;
        cursor: pointer;
    }
    .busParmBut{
        display: inline-block;
        padding: 2px;
        font-size: 10px;
        border: 1px solid #666;
        margin: 0px 2px;
    }
    .calculatorParam{
        display:inline-block;
    }
    .busParameterValDD abbr.busParameterValAbbr{
        display: inline-block;
        vertical-align: middle;
        text-align: left;
        padding: 2px;
        border: 1px solid #999;
        margin-left:2px;
        margin-top: 2px;
        line-height: normal!important;
    }

    .busParameterValDDTab{
        line-height: normal!important;
    }

</style>

<script type="text/javascript">
    function triggerClick(action){
        if( action == 'click' ){
            $("#addChange").trigger("click");
        }
    }
    sessionStorage.setItem("ShoppingCart",'');
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
</script>
<body style="background: #edf3f8" onload="triggerClick('${action}')">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>系统设置</span><abbr></abbr>
    </div>


<div style="margin-left: 20px; margin-top: 20px;">
    <input style="width: 180px!important;" type="button" id="addChange" value="添加/编辑业务类型" class="BusbButton allseachButton" >
    <input style="display: none;width: 180px!important;" value="退出编辑模式" type="button" id="complete" class="BusbButton allseachButton" >
    <input style="width: 210px;margin-left: 1210px;" type="button" id="changeHistory" value="业务类型变动记录" class="BusbButton allclickButton" >
</div>

<div style="color:#b2b2b2!important;margin-left: 20px; margin-top: 30px;">
    <ul id="tableHead">
        <li>业务类型</li>
        <li>参数</li>
        <li>参数值</li>
        <li>邮豆金额</li>
    </ul>
    <div id="sortableBusinessType">
    <#list allType as object>
        <ul class="tableBody" draggable="false">
            <input type="hidden" value="${object.typeId}">
        <#if object.getEnabled()>
            <li class="busName">
                <#if object.getDeletable()>
                    <input class="busNamedeleteAble" type="hidden" value="true">
                <#else>
                    <input class="busNamedeleteAble" type="hidden" value="false">
                </#if>
                <#if object.getEnabled()>
                    <input class="busNameEnabled" type="hidden" value="true">
                <#else>
                    <input class="busNameEnabled" type="hidden" value="false">
                </#if>
                <abbr>${object.typeName}</abbr> (已启用)<span class="buttonAppendPlace"></span> </li>
            <#else>
                <li class="busName">
                    <#if object.getDeletable()>
                        <input class="busNamedeleteAble" type="hidden" value="true">
                    <#else>
                        <input class="busNamedeleteAble" type="hidden" value="false">
                    </#if>
                    <#if object.getEnabled()>
                        <input class="busNameEnabled" type="hidden" value="true">
                    <#else>
                        <input class="busNameEnabled" type="hidden" value="false">
                    </#if>
                    <abbr>${object.typeName}</abbr> <span class="buttonAppendPlace"></span> </li>
        </#if>
            <li class="busParameter">
                <dl>
                    <div class="sortableBusinessPam">
                        <#if (object.getParams()?size > 0 )>
                            <#list object.getParams() as parm>
                                <#if parm.getEnabled()>
                                <dd class="busParameterDD">
                                    <#if parm.getDeletable()>
                                        <input class="busParameterdeleteAble" type="hidden" value="true">
                                    <#else>
                                        <input class="busParameterdeleteAble" type="hidden" value="false">
                                    </#if>
                                    <#if parm.getEnabled()>
                                        <input class="busParameterEnabled" type="hidden" value="true">
                                    <#else>
                                        <input class="busParameterEnabled" type="hidden" value="false">
                                    </#if>
                                    <input name="softBusParam" type="hidden" value="${parm.paramId},${parm.paramType},${parm.getRequired()}"><abbr>${parm.paramName}</abbr>(已启用) <span class="buttonAppendPlace"></span></dd>
                                <#else>
                                    <dd class="busParameterDD">
                                        <#if parm.getDeletable()>
                                            <input class="busParameterdeleteAble" type="hidden" value="true">
                                        <#else>
                                            <input class="busParameterdeleteAble" type="hidden" value="false">
                                        </#if>
                                        <#if parm.getEnabled()>
                                            <input class="busParameterEnabled" type="hidden" value="true">
                                        <#else>
                                            <input class="busParameterEnabled" type="hidden" value="false">
                                        </#if>
                                        <input name="softBusParam" type="hidden" value="${parm.paramId},${parm.paramType},${parm.getRequired()}"><abbr>${parm.paramName}</abbr> <span class="buttonAppendPlace"></span></dd>
                                </#if>

                            </#list>
                        <#else>
                            <dd class="busParameterDD">
                                <#if parm.getDeletable()>
                                    <input class="busParameterdeleteAble" type="hidden" value="true">
                                <#else>
                                    <input class="busParameterdeleteAble" type="hidden" value="false">
                                </#if>
                                <#if parm.getEnabled()>
                                    <input class="busParameterEnabled" type="hidden" value="true">
                                <#else>
                                    <input class="busParameterEnabled" type="hidden" value="false">
                                </#if>
                                <abbr>- -</abbr><span class="buttonAppendPlace"></span></dd>
                        </#if>
                    </div>
                </dl>
            </li>
            <li class="busParameterVal">
                <dl>
                    <#if (object.getParams()?size > 0)>
                        <#list object.getParams() as parm>
                            <#if parm.paramType.name() == 'TYPE_FLOAT'>
                                <dd class="busParameterValDD" data-id="${parm.paramId}">
                                    <input type="hidden" value="${parm.required}" class="required">
                                    <abbr>浮点类型</abbr>
                                    <span class="buttonAppendPlace"></span>
                                </dd>
                            <#elseif parm.paramType.name() == 'TYPE_TXT'>
                                <dd class="busParameterValDD" data-id="${parm.paramId}">
                                    <input type="hidden" value="${parm.required}" class="required">
                                    <abbr>文本类型</abbr>
                                    <span class="buttonAppendPlace"></span>
                                </dd>
                            <#else>
                                <dd class="busParameterValDD busParameterValDDTab" data-id="${parm.paramId}">
                                    <input type="hidden" value="${parm.required}" class="required">
                                    <#list parm.getParamValues() as parmValues>
                                        <#if parmValues.paramValueId != ''>
                                            <abbr class="busParameterValAbbr" data-value="${parmValues.paramValueId},${parmValues.paramValue},<#if parmValues.getDeletable()>true<#else>false</#if>,<#if parmValues.getEnabled()>true<#else>false</#if>,${parm.paramId}">${parmValues.paramValueName}</abbr></span>
                                        <#else>
                                            <abbr>- -</abbr>
                                        </#if>
                                    </#list>
                                    <span class="buttonAppendPlace"></span>
                                </dd>
                            </#if>
                        </#list>
                    <#else>
                        <dd class="busParameterValDD" data-id="${parm.paramId}">
                            <input type="hidden" value="${parm.required}" class="required">
                            <abbr>- -</abbr>
                            <span class="buttonAppendPlace"></span>
                        </dd>
                    </#if>
                </dl>
            </li>
            <#if object.expression != ''>
                <li class="busFormula"><TextField>${object.expression}</TextField> <span class="buttonAppendPlace"></span></li>
            <#else>
                <li class="busFormula"><TextField>- -</TextField> <span class="buttonAppendPlace"></span></li>
            </#if>

        </ul>
    </#list>

    </div>
</div>

<div style="display: none;" id="busTypeAddChange" class="busPopUp allpop">
    <h1>业务类型</h1>
    <ul>
        <li></li>
        <li>

            <abbr><i style="color: #ff3300">*</i><b>业务类型:</b><input id="busTypeName" type="text"></abbr>
            <abbr style="display: none;" class="busTypeActive"><i style="color: #ff3300">*</i><b>是否启用:</b><input checked="checked" value="1" name="busTypeActive" type="radio">是<input value="0" name="busTypeActive" style="margin-left: 50px;" type="radio">否</abbr>
            <#--<abbr><i style="color: #ff3300">*</i><b>排序:</b><input id="busTypeSort" maxlength="2" style="width: 50px" type="text"></abbr>-->
        <#--<abbr><i style="color: #ff3300">*</i><b>是否有参数:</b><input checked="checked" value="0" name="busParameterRid" type="radio">有<input value="1" name="busParameterRid" style="margin-left: 50px;" type="radio">无</abbr>-->
        </li>
        <li><input class="cancel" type="button" value="关闭" /><input class="confirm" value="确定" type="button" id="businessType"/> </li>
    </ul>
</div>


<div style="display: none;" id="busParameterValAddChange" class="busPopUp allpop">
    <h1>参数</h1>
    <ul>
        <li></li>
        <li>
            <abbr class="hasbusParameter"><i style="color: #ff3300">*</i><b>参数名:</b><input id="busParameterName" type="text"></abbr>
            <abbr style="display:none;font-size: 24px;color: #333" class="bussinessParNameEMUE">参数值类型:选项</abbr>
            <#--<abbr class="hasbusParameter"><i style="color: #ff3300">*</i><b>排序:</b><input id="busParameterNameTurn" style="width: 50px" type="text"></abbr>-->
            <abbr class="busParameterValType"><i style="color: #ff3300">*</i><b>参数值类型:</b><input checked="checked" value="0" name="busParameterValRid" type="radio">选项<input value="1" name="busParameterValRid" style="margin-left: 50px;" type="radio">浮点数字<input value="2" name="busParameterValRid" style="margin-left: 50px;" type="radio">文本</abbr>
            <abbr style="display: none;" class="busParameterValActive"><i style="color: #ff3300">*</i><b>是否启用:</b><input checked="checked" value="1" name="busParameterPrimaryActive" type="radio">是<input value="0" name="busParameterPrimaryActive" style="margin-left: 50px;" type="radio">否</abbr>
            <abbr style="display: none;" class="busParameterValPri"><i style="color: #ff3300">*</i><b>是否必填:</b><input checked="checked" value="1" name="busParameterPrimary" type="radio">是<input value="0" name="busParameterPrimary" style="margin-left: 50px;" type="radio">否</abbr>
            <#--<abbr style="display: none;" class="busParameterCountPri"><i style="color: #ff3300">*</i><b>是否为统计业务类型的一句:</b><input checked="checked" value="1" name="busCountPrimary" type="radio">是<input value="0" name="busCountPrimary" style="margin-left: 50px;" type="radio">否</abbr>-->
            <abbr class="busParameterValChoose" style="height: auto; margin-left: 10px;margin-bottom: 50px">
                <table align="center" cellspacing="5" cellpadding="2" border="0">
                    <thead style="border:1px solid #e5e5e5;">
                    <tr align="center">
                        <th>参数</th>
                        <th>系数</th>
                        <th>是否启用</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody align="center" class="newAdd">
                    <tr>
                        <td><input maxlength="8" type="text" class="firstTrInput"></td>
                        <td><input type="text" class="firstTrInput xishushuru"></td>
                        <td><input style="text-align: center;height: 100%;" readonly="readonly" value="已启用" type="text" class="valActive"></td>
                        <td><input style="width: 100px;display: none;" type="button" class="updatBtn" value="编辑"> <input style="width: 100px" type="button" class="deleteBtn allclickButton" value="删除"></td>
                    </tr>
                    <tr>
                        <td style="cursor: pointer; padding: 5px 0px" id="NewAddButton" colspan="4">+添加</td>
                    </tr>
                    </tbody>
                </table>
            </abbr>

        </li>
        <li><input class="cancel" type="button" value="取消" /><input class="confirm" value="确定" type="button"/> </li>
    </ul>
</div>



<div style="display: none;" id="updateenumVal" class="busPopUp allpop">
    <h1>编辑参数值</h1>
    <ul>
        <li></li>
        <li>
            <input type="hidden" id="bussinessParamId">
            <input id="updateenumValId" type="hidden">
            <input id="updateenumenable" type="hidden">
            <abbr><i style="color: #ff3300">*</i><b>参数值:</b><input id="updateenumValName" type="text"></abbr>
            <abbr><i style="color: #ff3300">*</i><b>系数:</b><input id="updateenumValNum" type="text"></abbr>
            <abbr><i style="color: #ff3300">*</i><b>是否启用:</b><input checked="checked" value="1" name="updateenumValActive" type="radio">是<input value="0" name="updateenumValActive" style="margin-left: 50px;" type="radio">否</abbr>
        </li>
        <li><input class="updateenumValcancel" type="button" value="取消" /><input class="confirm" value="确定" type="button" id="updateenumValconfirm"/> </li>
    </ul>
</div>


<div style="display: none;" id="updateenumVal2" class="busPopUp allpop">
    <h1>编辑参数值</h1>
    <ul>
        <li></li>
        <li>
            <input type="hidden" id="bussinessParamId2">
            <input id="updateenumValId2" type="hidden">
            <input id="updateenumenable2" type="hidden">
            <abbr><i style="color: #ff3300">*</i><b>参数值:</b><input id="updateenumValName2" type="text"></abbr>
            <abbr><i style="color: #ff3300">*</i><b>系数:</b><input id="updateenumValNum2" type="text"></abbr>
            <abbr><i style="color: #ff3300">*</i><b>是否启用:</b><input checked="checked" value="1" name="updateenumValActive2" type="radio">是<input value="0" name="updateenumValActive2" style="margin-left: 50px;" type="radio">否</abbr>
        </li>
        <li><input class="updateenumValcancel2" type="button" value="取消" /><input class="confirm" value="确定" type="button" id="updateenumValconfirm2"/> </li>
    </ul>
</div>


<div style="display:none ;" id="deleteWaring" class="busPopUp allpop">
    <h1>系统提示</h1>
    <ul>
        <li></li>
        <li>
            <abbr>确认删除该业务类型吗？</abbr>
        </li>
        <li><input type="button" value="取消" /><input value="确定" type="button"/> </li>
    </ul>
</div>

<div style="display:none ;" id="completeWaring" class="busPopUp allpop">
    <h1>系统提示</h1>
    <ul>
        <li></li>
        <li>
            <abbr>您有未编辑完成的业务类型，请编辑完成后再点击完成按钮，或删除未编辑完成的业务类型。</abbr>
        </li>
        <li><input type="button" value="取消" /><input value="确定" type="button"/> </li>
    </ul>
</div>

<div class="allpop" style="display: none;" id="busFormulaSet">
    <h1>邮豆金额</h1>
    <div id="busFormulaContent">
        <ul>
            <li>
                <div style="width: 100%; text-align: center; font-size: 24px;">参数</div>
                <div id="busParmPlace" style="width: 100%;  height: 80px;margin-top: 30px;"></div>
            </li>
            <li>
                <b class="calculator">7</b>
                <b class="calculator">8</b>
                <b class="calculator">9</b>
                <b class="calculator">+</b>
                <b class="calculator">-</b>
                <b class="calculator">4</b>
                <b class="calculator">5</b>
                <b class="calculator">6</b>
                <b style="font-size: 36px; line-height: 55px;" class="calculator">*</b>
                <b class="calculator">/</b>
                <b class="calculator">1</b>
                <b class="calculator">2</b>
                <b class="calculator">3</b>
                <b class="calculator">(</b>
                <b class="calculator">)</b>
                <b class="calculator">.</b>
                <b class="calculator">0</b>
                <b id="calculatorBack"><img style="margin-top: 8px;" src="${bath}/static/img/busDel.png" width="25" height="25" /></b>
            </li>
            <li>
                <div style="width: 100%; text-align: center; line-height: 50px;">邮豆金额 =</div>
            </li>
            <li>
                <div id="calculatorVal" style="width: 90%; height: 90%;padding: 10px;"></div>
            </li>
            <li><h2>原邮豆计算公式</h2>
            <div id="calculatorBefore"></div>
            </li>
            <li>
                <textarea style="width: 100%;height: 100%" placeholder="备注:" id="expressionNote"></textarea>

            </li>
        </ul>
    </div>
    <div style="width: 100%;"><input id="busFormulaCancel" class="busFormulaSetButton" type="button" value="取消" /><input id="busFormulaConfirm" class="busFormulaSetButton" value="确定" type="button"/></div>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>

<script type="text/javascript" src="${bath}/static/js/businesType.js?version=${VERSION}"></script>