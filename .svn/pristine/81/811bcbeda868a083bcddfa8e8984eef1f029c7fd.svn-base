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
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        /*.table_listBase{*/
            /*transform: scale(0.75,0.75);*/
            /*transform-origin: left top;*/
            /*-webkit-transform: scale(0.75,0.75);*/
            /*-webkit-transform-origin: left top;*/
            /*-ms-transform: scale(0.75,0.75);*/
            /*-ms-transform-origin: left top;*/
            /*-moz-transform: scale(0.75,0.75);*/
            /*-moz-transform-origin: left top;*/
        /*}*/
        /*.accountSearch{*/
            /*transform: scale(0.86,0.86);*/
            /*transform-origin: left top;*/
            /*-webkit-transform: scale(0.86,0.86);*/
            /*-webkit-transform-origin: left top;*/
            /*-ms-transform: scale(0.86,0.86);*/
            /*-ms-transform-origin: left top;*/
            /*-moz-transform: scale(0.86,0.86);*/
            /*-moz-transform-origin: left top;*/
        }
    }
    .accountSearch{
        width:1800px;
        height:130px;
        margin-left:20px;
        font-size: 12px;
    }
    .accountSearch li{
        height:40px;
        line-height:40px;
        float:left;
        margin:10px;
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
    .table_listBase{
        margin-left: 20px;
        width: 98%;
    }
    .table_listBase span.checkDetailReduce{
        color: #54a6de;
        cursor: pointer;
    }
    .table_listBase th, .table_listBase td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
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
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        $.datetimepicker.setLocale('ch')
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
    })
</script>
<body style="background: #edf3f8">
<div class="allOutShow" style="height:1000px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle"><span>日志管理</span><abbr></abbr>
    </div>

    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li style=" margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" />
                <#--<input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" />-->
            </li>
        </ul>
    </div>


    <div style="width: 98%">
        <table class="table_listBase" cellpadding="0" cellspacing="0" align="center">
            <thead>
            <tr>
                <th>机构名称</th>
                <th>操作者账号</th>
                <th>操作者IP</th>
                <th>操作时间</th>
                <th>操作事件</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer">
              <tr>
                 <td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="98%" height="600"></td>
              </tr>
            </tbody>
        </table>
    </div>
    <div id="holder" class="allcpageTurnButton"></div>











    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>

<script type="text/javascript">
    var array = [];
    array[31] = '';//start
    array[32] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
//    var todayDate = new Date();//获取今天日期
//    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
//    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
//    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");



    $(document).ready(function(){
        $("#date_start").click(function(e){
            console.log(1);
            $("#datetimepicker_start").datetimepicker({
                step:5,
                lang:'ch',
                formatTime:'H:i',
                formatDate:'d.m.Y'
            });
            $("#datetimepicker_start").trigger("focus");
        })
        $("#datetimepicker_start").blur(function(){
            $("#datetimepicker_start").datetimepicker('destroy');
        });

        $("#date_end").click(function(e){
            $("#datetimepicker_end").datetimepicker({
                step:5,
                lang:'ch',
                formatTime:'H:i',
                formatDate:'d.m.Y'
            });
            $("#datetimepicker_end").trigger("focus");
        })
        $("#datetimepicker_end").blur(function(){
            $("#datetimepicker_end").datetimepicker('destroy');
        });
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  ){
                lock=true
                array[31] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[32] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                ajaxPages("../web/logManager/getOperationLog","itemContainer","holder","logManager",10,'','',array);
            }else{
                data_type_alert("结束时间必须大于开始时间","error")
            }

        });


        $("#accExport").click(function(){
            if(lock){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
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
                window.location.href = '../web/api/exportExcel/netDataDown?' + outputStr;
            }
            }else{
                data_type_alert("请先搜索后再导出","error")
            }
        });
    });
</script>