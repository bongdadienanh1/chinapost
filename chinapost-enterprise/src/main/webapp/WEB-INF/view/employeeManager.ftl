    <#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            .employeeDetail{
                width:1400px!important;
                height:600px!important;
                overflow:hidden!important;
                font-size: 14px!important;
            }
            .employeeDetail dt{
                width:1400px!important;
            }
            .employeeDetail dt abbr{
                width:200px!important;
            }
            .employeeDetail dt span{
                width:60px!important;
            }

            .employeeDetail dd{
                width:1400px!important;
            }
            .employeeDetail dd span{
                width:60px!important;
            }
            .employeeDetail dd abbr{
                width:200px!important;
            }
            .updateEmployee{
                transform: scale(0.8,0.8);
                transform-origin: left top;
                -webkit-transform: scale(0.8,0.8);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.8,0.8);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.8,0.8);
                -moz-transform-origin: left top;
            }
            #updateEmployeeDetail li select{
                font-size: 18px!important;
            }
        }
        input[type="text"],input[type="password"]{
            border: 1px solid #cccccc;
            border-radius: 3px;
            background: #fff;
        }
        .employeeFunction{
            height:80px;
            margin-left:20px;
            margin-top:25px;
        }
        .employeeFunction li{
            height:30px;
            margin:10px 0px;
            float: left;
            width: 500px;
        }
        .employeeFunction li span{
            display:inline-block;
            width:91px;
            height:32px;
            text-align:center;
            line-height:32px;
            color:white;
            border:none;
        }
        .employeeFunction li input[name="employeeSearch"]{
            width:140px;
            background:#fff;
            border:1px solid #ccc;
            margin-left:-1px;
            margin-top:-1px;
            font-size: 16px;
        }

        #employeeSearch{
            width:120px;
            margin-left:45px;
            color:#fff;
            border:none;
            margin-top:-1px;
            cursor:pointer;
            font-size: 16px;
        }

        #newemployee{
            width:120px;
            color:#fff;
            border:none;
            cursor:pointer;
        }
        #deleteemployee{
            width:120px;
            margin-left:65px;
            color:#fff;
            border:none;
            cursor:pointer;
        }
        .employeeDetail{
            width:1600px;
            height:800px;
            overflow-y:auto;
            margin-left:20px;
            margin-top:35px;
            font-size: 16px;
        }
        .employeeDetail dt{
            width:1600px;
            height:42px;
            color:#666666;
            border-bottom:1px solid #e5e5e5;
        }
        .employeeDetail dt span{
            display:inline-block;
            width:100px;
            height:42px;
            text-align:center;
            padding:12px 0px;
        }
        .employeeDetail dt abbr{
            display:inline-block;
            width:230px;
            height:42px;
            text-align:left;
            padding-left: 10px;
        }


        .employeeDetail dd{
            width:1600px;
            height:60px;
            background:#fff;
            border-bottom:1px solid #e5e5e5;
        }
        .employeeDetail dd span{
            display:inline-block;
            vertical-align: middle;
            width:100px;
            height:120px;
            text-align:center;
            padding:25px 0px;
        }
        .employeeDetail dd abbr{
            display:inline-block;
            vertical-align: middle;
            width:230px;
            height:120px;
            text-align:left;
            padding-left: 10px;
        }

        .createemployee{
            width:600px;
            height:500px;
            position:fixed;
            left:15%;
            top:15%;  C;
            background:#FFF;
            z-index:2;
            display:none;
            font-size: 16px;

        }
        #creatEmployeeConfirm{
            width:120px;
            height:32px;
            position:absolute;
            left:180px;
            top:30px;
            cursor:pointer;

        }
        #creatEmployeeCancel{
            width:120px;
            height:32px;
            position:absolute;
            left:320px;
            top:30px;
            cursor:pointer;
        }
        #employeeDetail{
            height:350px;
            padding-left:50px;
            padding-top:20px;
        }
        #employeeDetail li{
            height:50px;
            margin:5px 0px;
        }

        #employeeDetail li span{
            display:inline-block;
            width:100px;
            text-align:right;
        }
        #employeeDetail li input{
            width:250px;
            height:30px;
            margin-left:30px;
        }
        #employeeDetail li select{
            width:200px;
            height:30px;
            margin-left:30px;
        }


        .updateEmployee{
            width:600px;
            height:460px;
            position:fixed;
            left:15%;
            top:15%;  C;
            background:#FFF;
            z-index:2;
            display:none;

        }
        #updateEmployeeConfirm{
            width:120px;
            height:32px;
            border-style:none;
            position:absolute;
            left:180px;
            top:30px;
            cursor:pointer;

        }
        #updateEmployeeCancel{
            width:120px;
            position:absolute;
            left:320px;
            top:30px;
            cursor:pointer;
        }
        #updateEmployeeDetail{
            height:260px;
            padding-left:120px;
            padding-top:30px;
        }
        #updateEmployeeDetail li{
            height:60px;
            margin:5px 0px;
        }

        #updateEmployeeDetail li span{
            display:inline-block;
            width:100px;
            text-align:right;
        }
        #updateEmployeeDetail li input{
            width:250px;
            height:30px;
            margin-left:30px;
        }
        #updateEmployeeDetail li select{
            width:200px;
            height:30px;
            margin-left:30px;
        }
        .employeeDetail dd abbr ul{
            display: inline-block;
            vertical-align: middle;
            text-align: center;
            position: relative;
            left: -50px;
            top: -3px;
        }
        .employeeDetail dd abbr ul.select2 section{
            width: 125px;
            height: 30px;
            display: none;
            line-height: 30px;
            cursor: pointer;
        }
        .employeeDetail dd abbr ul.select2 li.checkDateil{
            width: 125px;
            height: 30px;
            line-height: 30px;
            cursor: pointer;
        }
        .arrow2{
            display: inline-block;
            position: absolute;
            right: 0px;
            top:0px;
            width: 20px;
            height: 30px;
            background:url(${bath}/static/img/com_btn_arrow_bg_down.png) center no-repeat;
            cursor: pointer;
        }

        .holder{
            margin-top: 20px;
        }



        .updatePassword{
            width:600px;
            height:auto;
            position:fixed;
            left:15%;
            top:15%;  C;
            background:#FFF;
            z-index:2;
            display:none;

        }
        #updatePasswordConfirm{
            width:120px;
            height:32px;
            position:absolute;
            left:320px;
            top:30px;
            color:#fff;
            cursor:pointer;

        }
        #updatePasswordCancel{
            width:120px;
            height:32px;
            position:absolute;
            left:180px;
            top:30px;
            cursor:pointer;
        }
        #updatePasswordDetail{
            height:160px;
            padding-left:60px;
            padding-top:30px;
        }
        #updatePasswordDetail li{
            height:60px;
            margin:5px 0px;
        }

        #updatePasswordDetail li span{
            display:inline-block;
            width:180px;
            text-align:right;
        }
        #updatePasswordDetail li input{
            width:250px;
            height:30px;
            margin-left:30px;
        }
        #updatePasswordDetail li select{
            width:200px;
            height:30px;
            margin-left:30px;
        }

    </style>
    <script type="text/ecmascript">
        $(document).ready(function(e) {
            //禁止后退键 作用于Firefox、Opera
            document.onkeypress = forbidBackSpace;
            //禁止后退键  作用于IE、Chrome
            document.onkeydown = forbidBackSpace;
            sessionStorage.setItem("ShoppingCart",'');
            $( ".createemployee" ).draggable();
            $( ".updateEmployee" ).draggable();
            $( ".updatePassword" ).draggable();
            enterSearch( $("#employeeSearch_input"),$("#employeeSearch"))
            $(".employeeDetail dt input[name='employeeDetailCheck']").click(function(){
                if($(this).is(':checked')){
                    $("#itemContainer input[name='employeeDetailCheck']:not(:checked)").trigger("click")
                }else{

                    $("#itemContainer input[name='employeeDetailCheck']:checked").trigger("click")
                }

            })

            $(document).on("mouseover",".select2",function() {
                $(this).css("box-shadow","0px 0px 3px 0px #bbbbbb")
                $(this).children("li,section").css("box-shadow","0px 0px 3px 0px #bbbbbb")
            })

            $(document).on("mouseout",".select2",function() {
                $(this).css("box-shadow","none")
                $(this).children("li,section").css("box-shadow","none")
            })


            $("input[type='text'],input[type='password']").focus(function(){
                $(this).css("background","#f1f1f1");
            })
            $("input[type='text'],input[type='password']").blur(function(){
                $(this).css("background","#fff");
            })
            $(document).on("mouseover",".select2",function(){
                $(this).css("z-index","1")
                $(this).children("section").show()
            })
            $(document).on("mouseout",".select2",function(){
                $(this).css("z-index","0")
                $(this).children("section").hide()
            })
            $("#creatEmployeeCancel,#xx").click(function(){
                $(".createemployee").fadeOut(500);
            })
            $("#newemployee").click(function(){
                $(".createemployee").fadeIn(500);
            })
            $("#updateEmployeeCancel,#xx2").click(function(){
                $(".updateEmployee").fadeOut(500);
            })
            $("#updatePasswordCancel,#xx4").click(function(){
                $(".updatePassword").fadeOut(500);
            })
            $(document).on("click",".checkDateil",function(){
                $(".updateEmployee").fadeIn(500);
                var managerId = $(this).parent().parent().siblings("[type='hidden']").val();
                var upadteName=$(this).parent().parent().siblings(".empName").html();
                var upadteTel=$(this).parent().parent().siblings(".empTel").html();
                var upadteRole=$(this).parent().parent().siblings(".empRole").html();
                var upadteFreeze=$(this).parent().parent().siblings(".empFreeze").html();
                var AuthorityId=$(this).parent().parent().siblings(".AuthorityId").val();
                $("input[name='empDatialName']").val(upadteName);
                $("input[name='empDatialTel']").val(upadteTel);
                $("input[name='managerId']").val(managerId);
                $("select[name='empDatialRole'] option[value='" + AuthorityId + "']").prop("selected","selected");
                if(upadteRole=="测试"){
                    $("select[name='empDatialName']").val(1)
                }
                else if(upadteRole=="订单管理员"){
                    $("select[name='empDatialRole']").val(2)
                }
                if(upadteFreeze=="是"){
                    $("#empDatialFreeze").prop("checked","checked");
                }else{
                    $("#empDatialFreezeNO").prop("checked","checked");
                }
            })

        });

    </script>
    <script src="${bath}/static/js/jPages.js"></script>
    <title>无标题文档</title>
</head>


<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:auto;width: 100%;overflow-x: scroll;">
<div class="allheadstyle">
    <a href="RoleManager">角色管理</a><span>员工管理</span><abbr></abbr>
</div>

<div class="employeeFunction">
    <ul>
        <li><input placeholder="员工姓名" type="search" class="allinputButton" id="employeeSearch_input" name="employeeSearch"/><input class="allseachButton" type="button" id="employeeSearch" value="搜索" /></li>
        <li style="float: right;"><input class="allclickButton" onclick="coverHtml()" type="button" id="newemployee" value="添加员工" /><input class="allclickButton" type="button" id="deleteemployee" value="删除" /></li>
    </ul>
</div>
<div class="employeeDetail">
    <dl>
        <dt>
            <span><input type="checkbox" name="employeeDetailCheck" id="checkbox_dt" /><label for="checkbox_dt"></label></span>
            <abbr>账号</abbr>
            <abbr>员工姓名</abbr>
            <abbr>联系电话</abbr>
            <abbr>角色</abbr>
            <abbr>是否冻结</abbr>
            <abbr>操作</abbr>
        </dt>
        <div id="itemContainer">
            <#list managerAuthorityResults as object>
                <#--<#if>-->
                    <dd>
                        <span><input type="checkbox" name="employeeDetailCheck" /></span>
                        <input type="hidden" value="${object.id}" name="detail_id"/>
                        <abbr>${object.username}</abbr>
                        <abbr class="empName">${object.staffname}</abbr>
                        <abbr class="empTel">${object.cellphone}</abbr>
                        <abbr class="empRole">${object.authorityname}</abbr>
                        <abbr class="empFreeze"><#if object.nonDisabled=="0">是</#if><#if object.nonDisabled=="1">否</#if></abbr>
                        <abbr>
                            <ul class="select2"> <i value="0" class="arrow2"></i>
                                <li onclick="coverHtml()" class="checkDateil allSelectButton">编辑</li>
                                <section class="delete allSelectButton">删除</section>
                                <section class="updPassword allSelectButton">修改密码</section>
                            </ul>
                        </abbr>
                        <input type="hidden" class="AuthorityId" value="${object.getAuthorityId()}">
                    </dd>
                <#--</#if>-->
            </#list>
        </div>
        <div id="hiddenContainer" style="display:none;">
        <#list managerAuthorityResults as object>
        <#--<#if>-->
            <dd>
                <span><input type="checkbox" name="employeeDetailCheck" /></span>
                <input type="hidden" value="${object.id}" name="detail_id"/>
                <abbr>${object.username}</abbr>
                <abbr class="empName">${object.staffname}</abbr>
                <abbr class="empTel">${object.cellphone}</abbr>
                <abbr class="empRole">${object.authorityname}</abbr>
                <abbr class="empFreeze"><#if object.nonDisabled=="0">是</#if><#if object.nonDisabled=="1">否</#if></abbr>
                <abbr>
                    <ul class="select2"> <i value="0" class="arrow2"></i>
                        <li onclick="coverHtml()" class="checkDateil">编辑</li>
                        <section class="delete">删除</section>
                        <section class="updPassword">修改密码</section>
                    </ul>
                </abbr>
                <input type="hidden" class="AuthorityId" value="${object.getAuthorityId()}">
            </dd>
        <#--</#if>-->
        </#list>
        </div>
        <div class="holder allcpageTurnButton"></div>

    </dl>
</div>

<div class="createemployee allpop">
    <h1>添加员工
        <i onclick="discoverHtml()" id="xx" style="background:url(${bath}/static/img/XX.png) center no-repeat; display:inline-block; width:25px; height:25px;"></i>
    </h1>
    <ul id="employeeDetail">
        <li><span><i style="color:#ff3300;">*</i>用户名：</span><input type="text" /></li>
        <li><span><i style="color:#ff3300;">*</i>密码：</span><input maxlength="20" type="password" /></li>
        <li><span><i style="color:#ff3300;">*</i>确认密码：</span><input maxlength="20" type="password" /></li>
        <li><span><i style="color:#ff3300;">*</i>员工姓名：</span><input maxlength="8" type="text" /></li>
        <li><span><i style="color:#ff3300;">*</i>联系电话：</span><input maxlength="11" type="text" /></li>
        <li><span><i style="color:#ff3300;">*</i>角色：</span>
            <select>
                <#list authWithMAmountResults as object>
                    <option value="${object.id}">${object.name}</option>
                </#list>
            </select>
        </li>
    </ul>
    <div style="height:80px; border-top:1px solid #CCC; position:relative;">
        <input class="allseachButton" type="button" value="确定" id="creatEmployeeConfirm" />
        <input class="allcancelButton" onclick="discoverHtml()" type="button" value="取消" id="creatEmployeeCancel" />
    </div>
</div>


<div class="updateEmployee allpop">
    <h1 >编辑员工
        <i onclick="discoverHtml()" id="xx2" style="background:url(${bath}/static/img/XX.png) center no-repeat; display:inline-block; width:25px; height:25px;"></i>
    </h1>
    <ul id="updateEmployeeDetail">
        <input type="hidden" name="managerId" />
        <li><span><i style="color:#ff3300;">*</i>员工姓名：</span><input type="text" name="empDatialName" /></li>
        <li><span><i style="color:#ff3300;">*</i>联系电话：</span><input maxlength="11" type="text" name="empDatialTel"/></li>
        <li><span><i style="color:#ff3300;">*</i>是否冻结：</span><input id="empDatialFreeze" style="width:10px; margin:0px 15px;" type="radio" name="freeze" value="1"/>是<input id="empDatialFreezeNO" style="width:10px; margin:0px 15px;" type="radio" checked="checked" name="freeze" value="0"/>否</li>
        <li><span><i style="color:#ff3300;">*</i>角色：</span>
            <select name="empDatialRole">
                <#list authWithMAmountResults as object>
                    <option value="${object.id}">${object.name}</option>
                </#list>
            </select>
        </li>
    </ul>
    <div style="height:80px; border-top:1px solid #CCC; position:relative;">
        <input class="allseachButton" type="button" value="确定" id="updateEmployeeConfirm" />
        <input class="allcancelButton" onclick="discoverHtml()" type="button" value="取消" id="updateEmployeeCancel" />
    </div>
</div>



<div class="updatePassword allpop">
    <h1>修改登录密码
        <i onclick="discoverHtml()" id="xx4" style="background:url(${bath}/static/img/XX.png) center no-repeat; display:inline-block; width:25px; height:25px;"></i>
    </h1>
    <ul id="updatePasswordDetail">
        <li><span><i style="color:#ff3300;">*</i>输入登录密码：</span><input maxlength="20" type="password" name="Passwordreset" /></li>
        <li><span><i style="color:#ff3300;">*</i>再次输入登录密码：</span><input maxlength="20" type="password" name="PasswordresetConfirm"/><span style="position:relative; left:141px;color: #ff3300;display: none;">两次密码不一样</span></li>
    </ul>
    <div style="height:80px; border-top:1px solid #CCC; position:relative;">
        <input class="allseachButton" type="button" value="确定" id="updatePasswordConfirm" />
        <input class="allcancelButton" onclick="discoverHtml()" type="button" value="取消" id="updatePasswordCancel" />
    </div>
</div>


<script src="${bath}/static/js/employeeManager.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
</html>
