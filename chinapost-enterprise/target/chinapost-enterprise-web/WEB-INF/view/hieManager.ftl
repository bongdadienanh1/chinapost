<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <title>无标题文档</title>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            .accoutTable,.updateAcount,.resetPassword,.deleteAccout{
                transform: scale(0.85,0.85);
                transform-origin: left top;
                -webkit-transform: scale(0.85,0.85);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.85,0.85);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.85,0.85);
                -moz-transform-origin: left top;
            }
            td,th{
                text-align:left;
            }
            #newAccout{
                width: 120px!important;
            }
            #creatAccoutSure,#creatAccoutCancel{
                width: 100px!important;

            }
        }
        .hieSearch{
            height:40px;
            margin-left:20px;
            margin-top:20px;
            position: relative;
        }
        .hieSearch abbr{
            display:inline-block;
            height:40px;
            line-height:40px;
        }
        .hieSearch abbr input{
            width:200px;
            margin-top: -3px;
            margin-left: -1px;
            border-style:none;
            background:#FFF;
            border:1px solid #ccc;
            font-size:16px;
        }
        .hieSearch abbr span{
            display:inline-block;
            width:120px;
            height:40px;
            border:1px solid #ccc;
            text-align:center;
            background:#f1f1f1;
            color: #333;
        }
        #conSearch{
            width:120px;
            margin-left:20px;
            margin-top: -2px;
            border-style:none;
            color:#FFF;
        }

        #newAccout{
            width:120px;
            margin-left:20px;
            border-style:none;
            position: absolute;
            right: 5%;
            color:#FFF;
        }
        .hie_list{
            width:98%;
            margin-left:18px;
            margin-top:20px;
            font-size:15px;
            border:0;
        }
        .hie_list th{
            padding-left: 20px;
            width:120px;
            height:50px;
            color: #666666;
            border-bottom:1px solid #e5e5e5;
            font-weight: normal!important;
        }

        .hie_list td{
            padding-left: 20px;
            border-bottom:1px solid #e5e5e5;
            height:50px;
        }
        .hie_list td select{
            width: 120px;;
            height:30px;
            border-style: none;
            padding-left: 20px;
            padding-top: 5px;
            color: #fff;

        }
        .hie_list td option{
            height:20px;
            padding-top:8px;
            padding-left: 20px;
            background: #fff;
            color: #999;
        }
        .creatAccout{
            width:680px;
            height:auto;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index:99;
            display:none;
        }
        .creatAccout ul{
            width: 650px;
            margin-left: 15px;
            border-top:1px solid #e0e0e0;
            padding-top:50px;
        }
        .creatAccout li{
            height:80px;
        }
        .creatAccout li abbr{
            display:inline-block;
            width:180px;
            text-align:left;
            margin-left: 80px;
        }
        .creatAccout li abbr i{
            display: inline-block;;
            vertical-align: middle;
            margin-right: 10px;
            color:#ff3300;
        }
        .creatAccout li input{
            width:300px;
            height:35px;
            background:#FFF;
            border:1px solid #ccc;
            margin-left:50px;
            border-radius: 3px;
        }
        .creatAccout li input[type='radio']{
            width: 20px;
            height: 20px;
        }
        #creatAccoutSure{
            width:120px;
            cursor:pointer;
            margin-left: 120px;
        }
        #creatAccoutCancel{
            width:120px;
            cursor:pointer;
            margin-left: 200px;
        }
        #xx{
            display:inline-block;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            width:25px;
            height:25px;
            cursor:pointer;
        }
        .updateAcount{
            width:680px;
            height:auto;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index:99;
            display:none;
        }
        .updateAcount ul{
            width: 650px;
            margin-left: 15px;
            border-top:1px solid #e0e0e0;
            padding-top: 40px;
        }
        .updateAcount li{
            height:85px;
        }
        .updateAcount li abbr{
            display:inline-block;
            width:180px;
            text-align:left;
            margin-left: 70px;
        }
        .updateAcount li abbr i{
            color:#ff3300;
        }
        .updateAcount li input{
            width:300px;
            height:32px;
            background:#FFF;
            border:1px solid #ccc;
            margin-left:30px;
        }
        .updateAcount li input[type='text']:focus{
            border-color: #54a6de;
        }
        .updateAcount li input[type='text']:hover{
            border-color: #54a6de;
        }
        .updateAcount li input[type='radio']{
            width: 20px;
            height: 20px;
        }

        #updateAcountSure{
            width:120px;
            margin-left: 80px;
            cursor:pointer;
        }
        #updateAcountCancel{
            width:120px;
            margin-left: 200px;
            cursor:pointer;
        }
        #xx1{
            display:inline-block;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            width:25px;
            height:25px;
            cursor:pointer;
        }
        .deleteAccout{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:99;
            display:none;
        }
        .deleteAccout li{
            height:70px;
            line-height:70px;
            padding-left:20px;
        }
        #xx2{
            display:inline-block;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            width:25px;
            height:25px;
            cursor:pointer;
        }
        #deleteAcountSure{
            width:120px;
            margin-left: 160px;
            cursor:pointer;

        }
        #deleteAcountCancel{
            width:120px;
            margin-left: 110px;
            cursor:pointer;
        }

        .deleteError{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:100;
            display:none;
        }
        .deleteError li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #deleteAcountError{
            width:200px;
            height:50px;
            border:1px solid transparent;
            color:#fff;
            cursor:pointer;

        }

        .accessControl{
            width:680px;
            height:420px;;
            position:fixed;
            left:15%;
            top:20%;
            background:#FFF;
            z-index:99;
            display:none;
        }
        .accessControl li{
            height:80px;
            line-height:80px;
            padding-left:20px;
        }
        #xx3{
            display:inline-block;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            width:25px;
            height:25px;
            cursor:pointer;
        }
        #accessControlSure{
            width:120px;
            margin-left: 90px;
            cursor:pointer;

        }
        #accessControlCancel{
            width:120px;
            margin-left: 100px;
            cursor:pointer;
        }

        .update{
            width:125px;
            height:32px;
            color:#54a6de;
            border:none;
            background: #ffffff;

        }
        .arrow{
            display:inline-block;
            background:url(${bath}/static/img/com_btn_arrow_bg_down.png) center no-repeat;
            width:20px;
            height:38px;
            position:absolute;
            left:122px;
            top:20px;

        }
        .delete{
            width:125px;
            height:32px;
            color:#54a6de;
            border:none;
            background: #ffffff;
            position:absolute;
            left:20px;
            top:55px;
            z-index: 1;
            display: none;
        }
        .reset{
            width:125px;
            height:32px;
            color:#54a6de;
            border:none;
            background: #ffffff;
            position:absolute;
            left:20px;
            top:85px;
            z-index: 1;
            display: none;

        }

        .holder{
            margin-top: 60px;
        }
        .ifisEnd{
            display: none;
        }

        .resetPassword{
            display:block;
            width:600px;
            height:auto;
            position:fixed;
            left:15%;
            top:30%;
            background:#FFF;
            z-index: 2;
            display: none;
        }
        .resetPassword input[type='password']{
            margin-left: 15px;
            border-radius: 3px;
        }
        #xx3{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #resetPasswordConfirm{
            width:120px;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #resetPasswordCancel{
            width:120px;
            position:relative;
            left:210px;
            top:40px;
        }
        .changebutton input[type='button']:hover{
            background: #54a6de;
            color: #fff;
        }

    </style>

    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        $("#resetPasswordCancel,#xx3").click(function(){
            discoverHtml();
            $(".resetPassword").hide();
        })
        $( ".creatAccout" ).draggable();
        $( ".updateAcount" ).draggable();
        $( ".deleteAccout" ).draggable();
        $( ".accessControl" ).draggable();
        $( ".resetPassword" ).draggable();
        enterSearch( $("#keywords"),$("#conSearch"))
    });
        $(document).on("mouseover",".update",function() {
            $(this).css("box-shadow","0px -1px 2px 0px #bbbbbb")
            $(this).siblings(".delete").css("box-shadow","0px 0px 3px 0px #bbbbbb")
            $(this).siblings(".reset").css("box-shadow","0px 0px 3px 0px #bbbbbb")
        })
        $(document).on("mouseout",".update",function() {
            $(this).css("box-shadow","none")
            $(this).siblings(".delete").css("box-shadow","none")
            $(this).siblings(".reset").css("box-shadow","none")
        })
        $(document).on("mouseover",".delete",function() {
            $(this).css("box-shadow","0px -1px 2px 0px #bbbbbb")
            $(this).siblings(".update").css("box-shadow","0px 0px 3px 0px #bbbbbb")
            $(this).siblings(".reset").css("box-shadow","0px 0px 3px 0px #bbbbbb")
        })
        $(document).on("mouseout",".delete",function() {
            $(this).css("box-shadow","none")
            $(this).siblings(".update").css("box-shadow","none")
            $(this).siblings(".reset").css("box-shadow","none")
        })
        $(document).on("mouseover",".reset",function() {
            $(this).css("box-shadow","0px -1px 2px 0px #bbbbbb")
            $(this).siblings(".delete").css("box-shadow","0px 0px 3px 0px #bbbbbb")
            $(this).siblings(".update").css("box-shadow","0px 0px 3px 0px #bbbbbb")
        })
        $(document).on("mouseout",".reset",function() {
            $(this).css("box-shadow","none")
            $(this).siblings(".delete").css("box-shadow","none")
            $(this).siblings(".update").css("box-shadow","none")
        })
        $(document).on("click",".passwordResetChooes",function() {
            var chooesval=$(this).val()
            $(".resetPasswordinput").val("")
            if(chooesval == "0"){
                $(".resetPasswordinput").attr("maxlength","20")
            }else if(chooesval == "1"){
                $(".resetPasswordinput").attr("maxlength","6")
            }
        })
        $(document).on("mouseover",".changebutton",function(){
            $(this).children(".delete,.reset").show()
        })
        $(document).on("mouseout",".changebutton",function(){
            $(this).children(".delete,.reset").hide()
        })
    </script>



</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px;width: 100%;height: 1050px;">
<div class="allheadstyle">
	<span>层级管理</span><abbr></abbr>
</div>

<div class="hieSearch">
    <abbr><input class="allinputButton" placeholder="下级账号公司名称" type="text" id="keywords" /></abbr><input class="allseachButton" id="conSearch" type="button" value="搜索"/>
    <input class="allclickButton" onclick="coverHtml()" id="newAccout" type="button" value="新建下级账号"/>
</div>


<div class="accoutTable">
    <table class="hie_list" border="0" cellspacing="0" cellpadding="0">
        <thead align="left">
        <tr>
            <th>下级公司</th>
            <th>登录账号</th>
            <th>联系地址</th>
            <th>联系人</th>
            <th>联系方式</th>
            <th>是否为网点</th>
            <th>机构编号</th>
            <th>网点折扣系数</th>
            <th>代客下单付款免验证</th>
            <th style="padding-left: 50px">操作</th>
        </tr>
        </thead>
        <tbody align="left" id="itemContainer">
        <#list enterprises as son>
            <tr style="height: 80px;">
                <td class="updateName">${son.enterpriseName}</td>
                <td>${son.username}</td>
                <td>${son.address}</td>
                <td>${son.linkman}</td>
                <td>${son.linkMobile}</td>
                <td class="updateId" style="display: none">${son.enterpriseId}</td>
                <#if son.end><td class="updateEnd">是</td><#else><td class="updateEnd">否</td></#if>
                <td class="organizationName">${son.accountName}</td>
                <td class="updatediscountPct">${son.discountPct}</td>
                <#if son.hasPermit><td class="updatehasPermit">是</td><#else><td class="updatehasPermit">否</td></#if>
                <td style="position: relative;">
                    <div class="changebutton">
                        <input class="update" type="button" value="编辑账号" /><a value="0" class="arrow"></a>
                        <input class="delete" type="button" value="删除账号" />
                        <input class="reset" type="button" value="重置密码" />
                        <#--<input class="control" type="button" value="控制权限" />-->
                    </div>
                </td>
                <td class="unPrimary" style="display: none"><#if son.getEnd()>0<#else>1</#if></td>
                <td class="getHasPermit" style="display: none"><#if son.getHasPermit()>0<#else>1</#if></td>

        </tr>
        </#list>
        </tbody>
    </table>
    <div class="holder allcpageTurnButton"></div>
    <div id="itemContainerHidden" style="display:none;">
        <table>
        <#list enterprises as son>
            <tr style="height: 80px;">
                <td class="updateName">${son.enterpriseName}</td>
                <td>${son.username}</td>
                <td>${son.address}</td>
                <td>${son.linkman}</td>
                <td>${son.linkMobile}</td>
                <td class="updateId" style="display: none">${son.enterpriseId}</td>
                <#if son.end><td class="updateEnd">是</td><#else><td class="updateEnd">否</td></#if>
                <td class="organizationName">${son.accountName}</td>
                <td class="updatediscountPct">${son.discountPct}</td>
                <#if son.hasPermit><td class="updatehasPermit">是</td><#else><td class="updatehasPermit">否</td></#if>
                <td style="position: relative;">
                    <div class="changebutton">
                        <input class="update" type="button" value="编辑账号" /><a value="0" class="arrow"></a>
                        <input class="delete" type="button" value="删除账号" />
                        <input class="reset" type="button" value="重置密码" />
                    <#--<input class="control" type="button" value="控制权限" />-->
                    </div>
                </td>
                <td class="unPrimary" style="display: none"><#if son.getEnd()>0<#else>1</#if></td>
                <td class="getHasPermit" style="display: none"><#if son.getHasPermit()>0<#else>1</#if></td>

            </tr>
        </#list>
        </table>
    </div>
</div>


<div class="creatAccout allpop">
    <h1 style=" height:50px; line-height:50px; padding-left:20px; font-size:20px;">新建下级账号<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul>
        <li><abbr><i>*</i>下级公司名称:</abbr><input id="creatAccoutName" type="text" /></li>
        <li><abbr><i>*</i>登录账号:</abbr><input id="creatAccoutloginName" type="text" /></li>
        <li><abbr><i>*</i>登录密码:</abbr><input id="creatAccoutPassword" maxlength="20" type="password" /></li>
        <li><abbr><i>*</i>确认密码:</abbr><input id="creatAccoutNamePassConfirm" maxlength="20" type="password" /></li>
        <li><abbr><i>*</i>是否为网点:</abbr><input value="0" type="radio" name="unPrimary"/>是<input checked="checked" value="1" type="radio" name="unPrimary"/>否</li>
        <li class="ifisEnd"><abbr><i>*</i>机构编号:</abbr><input id="createOrganizationName" maxlength="20" type="text" /></li>
        <li class="ifisEnd"><abbr><i>*</i>网点折扣系数:</abbr><input id="creatAccoutDiscountPct" type="text" /></li>
        <li class="ifisEnd"><abbr><i>*</i>代客下单付款免验证:</abbr><input value="0" type="radio" name="hasPermit"/>是<input checked="checked" value="1" type="radio" name="hasPermit"/>否</li>
    </ul>
    <div style=" height:80px;"><input class="allseachButton" type="button" value="确定" id="creatAccoutSure"/><input class="allcancelButton" onclick="discoverHtml()" type="button" value="取消" id="creatAccoutCancel" /></div>
</div>

<div class="updateAcount allpop">
    <h1 style=" height:70px; line-height:70px; padding-left:20px; font-size:20px;">编辑账号<i onclick="discoverHtml()" id="xx1"></i></h1>
    <ul>
        <li><abbr><i>*</i>下级公司名称:</abbr><input type="text" id="updateAcountName" /></li>
        <li class="ifisEnd2"><abbr><i>*</i>网点折扣系数:</abbr><input type="text" id="updateAcountSale" /></li>
        <li style="display: none;"><input type="hidden" id="enterpriseId"/></li>
        <li style="display: none;"><input type="hidden" id="ifisEND"/></li>
        <li class="ifisEnd2"><abbr><i>*</i>机构编号:</abbr><input id="updateOrganizationName" maxlength="20" type="text" /></li>
        <li class="ifisEnd2"><abbr><i>*</i>代客下单付款免验证:</abbr><input value="0" type="radio" name="updateHasPermit"/>是<input checked="checked" value="1" type="radio" name="updateHasPermit"/>否</li>
    </ul>
    <div style=" height:80px;margin-top: 20px;"><input class="allseachButton" type="button" value="确定" id="updateAcountSure"/><input class="allcancelButton" onclick="discoverHtml()" type="button" value="取消" id="updateAcountCancel" /></div>
</div>




<div class="deleteAccout allpop">
    <h1>确认提示<i onclick="discoverHtml()" id="xx2"></i></h1>
    <ul>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">您确定删除该账号吗？</li>
        <input type="hidden" id="deleteAccout_enterpriseId" />
        <li><input class="allseachButton" type="button" id="deleteAcountSure" value="确定" /><input class="allcancelButton" onclick="discoverHtml()" type="button" id="deleteAcountCancel" value="取消" /></li>
    </ul>
</div>

<div class="deleteError">
    <ul>
        <li style="font-size:24px; padding-left:30px;">确认提示</li>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">数据中存在异常，无法删除</li>
        <li style="text-align: center;"><input class="allseachButton" onclick="discoverHtml()" type="button" id="deleteAcountError" value="确定" /></li>
    </ul>
</div>


<div class="accessControl">
    <ul>
        <input type="hidden" id="permission_enterpriseId" />
        <li style="font-size:24px; padding-left:30px;">权限控制<i onclick="discoverHtml()" id="xx3"></i></li>
        <li style="height:250px; width: 640px; margin-left: 20px; border-top:1px solid #CCC; line-height:250px; text-align: center;">代客下单付款免验证：<input style="margin:0px 20px" value="0" type="radio" name="laissez_passer" />是 <input style="margin:0px 20px" type="radio" value="1" name="laissez_passer" checked="checked" />否</li>
        <li><input type="button" class="allseachButton" id="accessControlSure" value="确定" /><input class="allcancelButton" onclick="discoverHtml()" type="button" id="accessControlCancel" value="取消" /></li>
    </ul>
</div>

<div class="resetPassword allpop">
    <input type="hidden" value="" id="resetEnterpriceId">
    <h1>重置密码<i onclick="discoverHtml()" id="xx3"></i></h1>
    <ul>
        <li style="height:150px;margin-top: 20px; line-height:50px; padding-left:50px;">
            <span style="padding-left:100px;">密码类型:
                <input checked="checked" class="passwordResetChooes" type="radio" value="0" name="type">登录密码</input>
                <input class="passwordResetChooes" type="radio" value="1" name="type">支付密码</input>
            </span><br />
            <span style="padding-left:100px;">输入密码:<input class="resetPasswordinput" type="password" name="password" /><label style="color:red;margin-left: 10px;"></label></span><br />
            <span style="padding-left:100px;">确认密码:<input class="resetPasswordinput" type="password" name="passwordConfirm"/><label style="color:red;margin-left: 10px;"></label></span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input type="button" class="allseachButton" id="resetPasswordConfirm" value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="resetPasswordCancel" value="取消" />
        </li>
    </ul>
</div>
<script src="${bath}/static/js/hieManager.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</div>
</body>
</html>
