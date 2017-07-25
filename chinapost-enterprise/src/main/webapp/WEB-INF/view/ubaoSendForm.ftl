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
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <title>邮豆发放记录表</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        /*.table_listSendFrom{*/
            /*transform: scale(0.8,0.8);*/
            /*transform-origin: left top;*/
            /*-webkit-transform: scale(0.8,0.8);*/
            /*-webkit-transform-origin: left top;*/
            /*-ms-transform: scale(0.8,0.8);*/
            /*-ms-transform-origin: left top;*/
            /*-moz-transform: scale(0.8,0.8);*/
            /*-moz-transform-origin: left top;*/
        /*}*/
        /*.accountSearch{*/
            /*width: 1300px!important;*/
            /*height: 80px!important;*/
            /*transform: scale(0.95,0.95);*/
            /*transform-origin: left top;*/
            /*-webkit-transform: scale(0.95,0.95);*/
            /*-webkit-transform-origin: left top;*/
            /*-ms-transform: scale(0.95,0.95);*/
            /*-ms-transform-origin: left top;*/
            /*-moz-transform: scale(0.95,0.95);*/
            /*-moz-transform-origin: left top;*/
        /*}*/

    }
    .accountSearch{
        width:1800px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        width:300px;
        height:32px;
        line-height:40px;
        float:left;
        margin:10px 0px;
    }
    .accountSearch li input[type='text']{

        background:#FFF;
    }
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        height:24px;
        background-color: #f2f2f2;
        margin-left: -45px;
        background: url("${bath}/static/img/date_img.png") no-repeat center;

    }
    .select{
        width: 120px;
        position: absolute;
        right: 30px;
        top: 2px;
        text-indent: 0px;
    }
    .select dd{
        display: none;
    }
    .table_listSendFrom{
        width: 98%;
        margin-left: 20px;
    }
    .table_listSendFrom span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listSendFrom th, .table_listSendFrom td{
        height: 40px;
        border-bottom: 1px solid #e5e5e5;
        overflow: hidden;
        text-align: left;
    }
    .table_listSendFrom th:nth-child(2), .table_listSendFrom td:nth-child(2){
        width: 240px;
    }
    #holder{
        margin: 30px 0px;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
    }
</style>
<script type="text/javascript">
    $(document).ready(function(){
        sessionStorage.setItem("ShoppingCart",'');
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);

        $(".select").mouseover(function(){
            $(".select dd").show()
        })
        $(".select").mouseout(function(){
            $(".select dd").hide()
        })
        $(".select dd").click(function(){
            var secval=$(this).html();
            var typeId=$(this).data("id")
            $(this).hide().siblings("dd").hide();
            $(this).siblings("dt").children("span").html(secval);
            $("#typeId").val(typeId)
        })
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
    })
</script>
<body style="background: #fff">
    <div class="allheadstyle">
        <span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  邮豆发放记录表</span>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li style="display: none; width: 250px;text-align: left;position: relative;text-indent: 30px;line-height: 20px;top: 4px" class="allinputButton">
                <span>业务类型</span>
                <dl class="select"></i>
                    <dt style="width: 130px;height: 20px;line-height: 20px;white-space: nowrap;overflow: hidden;" class="allSelectButton" name="phone"><span>市局统一发放</span><input type="hidden" id="typeId" value="-100" ></dt>
                    <#--<#list businessTypes as object>-->
                        <#--<dd style="width: 130px;height: 30px;line-height: 30px;white-space: nowrap;overflow: hidden;"  data-id="${object.typeId}" class="import allSelectButton">${object.typeName}</dd>-->
                    <#--</#list>-->
                </dl>
            </li>
            <li style="margin-left: 20px;"><input class="allinputButton" placeholder="发放开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="发放结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>

        </ul>
    </div>

    <div style="width: 98%">
        <table style="table-layout:fixed;" class="table_listSendFrom" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>网点名称</th>
                <th>身份证号</th>
                <th>姓名</th>
                <th>营销邮豆金额</th>
                <th>促销邮豆金额</th>
                <th>邮豆总金额</th>
                <th>业务日期</th>
                <th>邮豆发放时间</th>
                <th>业务类型</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
            <tr>
                <td style="text-align: center" colspan="9"><img src="${bath}/static/img/nodata.png" width="98%" height="600"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>


    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
    <div class="chooeseDepot allpop"></div>
    <div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script type="text/javascript">
    var array = [];
    array[4] = '';//typeid
    array[8] = '';//start
    array[9] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
    $(document).ready(function(){
        $("#accSearch").click(function(){
            if( $("#typeId").val() != "" && $("#datetimepicker_end").val() >= $("#datetimepicker_start").val() ){
                array[15] = $("input[name='accountUserName']").val();//idCard
                array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                array[4] = $("#typeId").val();
                ajaxPages("../web/api/report/getGrandHistory","itemContainer","holder","ubaosendForm",10,'','',array,function(data){
                    $(".dynamicHead").remove();
                    var paramJson = data.data.thead;
                    if( paramJson != '' ){
                        paramJson.map(function(a){
                            var html = '<th class="dynamicHead">' + a + '</th>';
                            $("thead tr").append(html);
                        });
                    }
                });
            }else{
                data_type_alert("业务类型必填且结束时间必须大于开始时间","error")
            }

        });

        $("#accExport").click(function(){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
            }
            if( array[4] != undefined ){
                outputStr += '&typeId=' + array[4];
            }


            if( array[8] != undefined ){
                outputStr += '&start=' + array[8];
            }
            if( array[9] != undefined ){
                outputStr += '&end=' + array[9];
            }
            if( array[8] == undefined || array[8] == '' || array[9] == undefined || array[9] == ''){
                response_ensure_alert("warning","搜索时间不能为空");
            }
            else{
                window.location.href = '../web/api/exportExcel/newGrandHistory?' + outputStr;
            }
        });
    });
</script>