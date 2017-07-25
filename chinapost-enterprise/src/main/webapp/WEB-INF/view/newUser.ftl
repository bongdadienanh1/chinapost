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
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
<title>无标题文档</title>
<style>
body{
	background:#f7f7f7;
	}

.newUser{
	width:800px;
	height:auto;
    overflow: hidden;
	background:#FFF;
	border:1px solid #ccc;
	margin-top:50px;
	margin-left:150px;
	box-shadow:0px 0px 2px 1px #CCC;
}
.newUser ul{
	width:500px;
	margin:auto;
	margin-top:30px;
	position:relative;

}
.newUser ul input{
	margin:15px 0px 15px 20px;
	width:300px;
	height:40px;
	border:1px solid #CCC;
	border-radius:5px;
	background:#FFF;
}
.newUser ul select{
	margin:15px 0px 15px 20px;
	width:300px;
	height:40px;
	border:1px solid #CCC;
	border-radius:5px;
	background:#FFF;
	font-size:18px;
	padding-left:20px;
}

.newUser ul li{
	color:#000;
	font-size:20px;
}
.newUser ul li abbr {
    display: inline-block;
    vertical-align: middle;
    width: 130px;
    text-align: right;
}
.newUser ul li i{
	color:#ff3300;
	position:relative;
	top:0px;
}
#cancel{
	width:120px;
	height:40px;
	background:#bbbbbb;
	border-radius:5px;
	color:#FFF;
	position:absolute;
	left:109px;
	cursor:pointer;
	
}
#userSub{
	width:120px;
	height:40px;
	background:#24b35f;
	border-radius:5px;
	color:#FFF;
	position:absolute;
	left:259px;
	cursor:pointer;

}
.success{
	width:600px;
	height:450px;
	background:#FFF;
	border:1px solid #ccc;
	box-shadow:0px 0px 3px 3px #CCC;
	position:fixed;
	left:15%;
	top:30%;
	z-index:2;
	display:none;
	
}
.success span{
	display:inline-block;
	height:40px;
	position:relative;
	left:200px;
	top:150px;
	color:#000;
	font-size:20px;
}
.success span img{
	margin-right:30px;
}
#detail{
	display:inline-block;
	width:100px;
	height:30px;
	line-height:35px;
	color:#FFF;
	font-size:14px;
	border-radius:3px;
	text-align:center;
	background:#bbbbbb;
	position:relative;
	top:300px;
}
#complete{
	display:inline-block;
	width:100px;
	height:30px;
	line-height:35px;
	font-size:14px;
	color:#FFF;
	border-radius:3px;
	text-align:center;
	background:#24b383;
	position:relative;
	top:300px;
	left:50px;

}
#complete:hover{
    box-shadow: 0px 1px 3px 0px #333;
}
#complete:active{
    background: #009966;
}
input{
    padding-left: 20px;
}
</style>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    var enterpriseId = "${enterpriseId}";
    var enterprisePId = "${enterpriseInfo.getProvinceId()}";
    var enterpriseCId = "${enterpriseInfo.getCityId()}";
    var enterpriseDId = "${enterpriseInfo.getDistrictId()}";
    $(document).ready(function(){
        $.get("${bath}/web/api/common/provincies",function(data){
            if( data.response == 'success' ){
                data.data.map(function( object ){
                    var html = '';
                    html += '<option value="' + object.provinceId +'">';
                    html += object.provinceName;
                    html += '</option>';
                    $(".newAddressP").append(html);
                });

                if( window.localStorage ){
                    var PID = localStorage['UbaoSend_provinceId'];
                    var CID = localStorage['UbaoSend_cityId'];
                    var DID = localStorage['UbaoSend_districtId'];
                    if( PID == '' || PID == undefined){
                        PID = enterprisePId;
                    }
                    if( CID == '' || CID == undefined){
                        CID = enterpriseCId;
                    }
                    if( DID == '' || PID == undefined){
                        DID = enterpriseDId;
                    }
                }
                else{
                    var PID = enterprisePId;
                    var CID = enterpriseCId;
                    var DID = enterpriseDId;
                }
                $(".newAddressP option[value='" + PID + "']").prop("selected","selected");
                if(PID){
                    UpdateMemAddressP(function(){
                        $(".newAddressC option[value='" + CID + "']").prop("selected","selected");
                    },PID);
                }
                if(CID){
                    UpdateMemAddressC(function(){
                        $(".newAddressD option[value='" + DID + "']").prop("selected","selected");
                    },CID);
                }

            }
        },'json');

        $(".newAddressP").change(function(){
            $(".newAddressC option[value!='0'],.newAddressD option[value!='0'],.newAddressR option[value!='0']").remove()
            $.post("../web/api/common/cities",{
                "provinceId":$(this).val()
            },function(data){
                if( data.response == 'success' ){
                    data.data.map(function(object){
                        var html = '';
                        html += '<option value="' + object.cityId +'">';
                        html += object.cityName;
                        html += '</option>';

                        $(".newAddressC").append(html);
                    });
                }
            },'json');
        });
        $(".newAddressC").change(function(){
            $(".newAddressD option[value!='0'],.newAddressR option[value!='0']").remove()
            $.post("../web/api/common/districts",{
                "cityId":$(this).val()
            },function(data){
                if( data.response == 'success' ){
                    data.data.map(function(object){
                        var html = '';
                        html += '<option value="' + object.districtId +'">';
                        html += object.districtName;
                        html += '</option>';
                        $(".newAddressD").append(html);
                    });
                }
            },'json');
        });
        $(".newAddressD").change(function(){
            $(".newAddressR option[value!='0']").remove()
            $.post("../web/api/common/streets",{
                "districtId":$(this).val()
            },function(data){
                if( data.response == 'success' ){
                    data.data.map(function(object){
                        var html = '';
                        html += '<option value="' + object.streetId +'">';
                        html += object.streetName;
                        html += '</option>';
                        $(".newAddressR").append(html);
                    });
                }
            },'json');
        });
    })
</script>
</head>

<body>

<div style="width:100%; height:100px; border-bottom:2px solid #dcdcdc; margin-left:20px;">
	<span style="font:24px '黑体'; color:#000; display:inline-block; margin-top:15px; margin-bottom:20px;">邮豆发放
    </span>
   	<br  />
    单个转账
</div>

<div class="newUser">
	<ul>
    	<li><abbr>身份证号：</abbr><span style="display:inline-block;margin:15px 0px 15px 30px; width:300px; height:40px;" id="idCard">${idCard}</span></li>
        <li><abbr>姓名：</abbr><input type="text" name="userName" id="userName"/></li>
        <li style="width: 650px; margin-left: -10px"><i>*</i>联系地址：
            <select style="width: 100px;padding: 0px" class="newAddressP">
                <option value="0">请选择</option>
            </select>
            <select style="width: 100px;padding: 0px"  class="newAddressC">
                <option value="0">请选择</option>
            </select>
            <select style="width: 100px;padding: 0px"  class="newAddressD">
                <option value="0">请选择</option>
            </select>
            <#--<select style="width: 100px;padding: 0px"  class="newAddressR">-->
                <#--<option value="0">请选择</option>-->
            <#--</select>-->

        </li>
        <li><input style="margin-left: 140px" type="text" name="address" id="newAddressDetail"/></li>
        <#--<li><abbr><i>*</i>金额：</abbr><input maxlength="8" type="text" name="UbaoName" id="UCoin"/></li>-->
        <li><abbr><i>*</i>手机号：</abbr><input type="text" name="userPhone" id="contactPhone"maxlength="11"/></li>
        <li><abbr><i>*</i>业务类型：</abbr><select id="businessType">
            <option selected="selected" >可选择</option>
            <#list businessType as businessType>
                <option value="${businessType.typeId}">${businessType.typeName}</option>
            </#list>
        </select></li>
        <li><abbr>营销邮豆金额:</abbr><input style="width: 300px;border: none;" readonly="readonly" type="text"  id="ucoinMarketing" /></li>
        <li><abbr>促销邮豆金额:</abbr><input style="width: 300px;" type="text"  id="ucoinSale" /></li>
        <li><abbr>备注：</abbr><input type="text" name="Note" id="remark"/></li>
        <li><abbr><i>*</i>支付密码：</abbr><input type="password" name="userPassword" id="Password"/></li>
        <li style="height: 80px;"> <input style="padding: 0px;" type="button" id="cancel" value="取消" />
        <input style="padding: 0px;" type="button" id="userSub" value="确定发放" />
        </li>
    </ul>
	
</div>


<div class="success">
	<span><img src="${bath}/static/img/ok.png" width="40" height="40" />发放成功</span>
	<a id="detail">查看发放详情</a>
    <a id="complete">完成</a>
    0
</div>


<script src="${bath}/static/js/newUser.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
</html>
