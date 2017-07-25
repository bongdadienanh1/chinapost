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
    <script type="text/javascript" src="${bath}/static/js/mageReplishment.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title></title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){
        /*.accountSearch{*/
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
        height:40px;
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
    .table_listBase{
        margin-left: 20px;
        width: 1600px;;
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

    .openDetail{
        display: inline-block;
        width: 16px;
        height: 16px;
        line-height: 16px;
        border: 1px solid #54a6de;
        color: #54a6de;
        border-radius: 16px;
        text-align: center;
        margin: 0px 5px;
    }
    .openDetail:hover{
        background: #54a6de;
        color: #fff;
    }
    .secondClass{
        padding-left: 10px;
    }
    .thirdClass{
        padding-left: 20px;
    }
    .lastClass{
        padding-left:60px;
    }
    .table_listBase input[type="checkbox"]{
        margin-left: 10px;
    }
    .vocherCode {
        color: #54a6de!important;
        cursor: pointer!important;
    }
</style>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');

</script>
<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
    <span>合并补货单</span><abbr></abbr>
    </div>

<div class="accountSearch">
    <ul>
    <#--<li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>-->
        <li><input class="allinputButton" placeholder="单据编号"   type="text" style="width:250px;" id="code"/></li>
        <li><input class="allinputButton" placeholder="申请账号"   type="text" style="width:250px;" id="creatorName"/></li>
        <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
        <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
        <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allseachButton" type="button" value="合并" id="mega" /></li>
    </ul>
</div>


<div style="width: 1600px;">
    <table class="table_listBase" cellpadding="0" cellspacing="0" align="center">
        <thead>
        <tr>
            <th><input type="checkbox" name="dtSelect"></th>
            <th>单据类型</th>
            <th>单据编号</th>
            <th>申请账号</th>
            <th>创建日期</th>
            <th>单据状态</th>
            <th>当前操作者</th>
            <th>申请原因</th>

        </tr>
        </thead>
        <tbody align="center" id="itemContainer">

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


    $(document).ready(function(){

        setInterval(function(){
            var action = localStorage["systemAction"];
            if( action == 'refresh' ){
                localStorage['systemAction'] = 'active';
                $("#accSearch").trigger("click")
            }
        },1000);
        $("#mega").click(function(){
            if( $("input[name='select']:checked").length > 1 ){
                var billIds = "["
                $("input[name='select']:checked").each(function(){
                    billIds += $(this).val() +','
                })
                billIds = billIds.substring(0,billIds.length - 1)
                billIds += ']'
                window.open("replenishmentMega?list=" + billIds)
            }else{
                response_ensure_alert("error","请至少选择两张补货单")
            }
        })
        $.datetimepicker.setLocale('ch')
        $("#date_start").click(function(e){
            $("#datetimepicker_start").datetimepicker({
                step:5,
                lang:'ch',
                timepicker:false,
                format:'Y-m-d',
                formatDate:'Y-m-d'
            });
            $("#datetimepicker_start").trigger("focus");
        });
        $("#datetimepicker_start").blur(function(){
            $("#datetimepicker_start").datetimepicker('destroy');
        });
        $("#date_end").click(function(e){
            $("#datetimepicker_end").datetimepicker({
                step:5,
                lang:'ch',
                timepicker:false,
                format:'Y-m-d',
                formatDate:'Y-m-d'
            });
            $("#datetimepicker_end").trigger("focus");
        });
        $("#datetimepicker_end").blur(function(){
            $("#datetimepicker_end").datetimepicker('destroy');
        });
        $.post('../web/api/inventory/getRepairUnHandleBills',{
            billType:"REPLENISHMENT",
            billStatus:"CHECKING"
        },function(data){

            if(data.response == "success"){
                var html = ""
                data.data.content.map(function(object){
                    html += '<tr>'
                    html += '<td>' + '<input type="checkbox" name="select" value="' + object.billId + '">' + '</td>'
                    html += '<input type="hidden" value="' + object.billId + '">';
                    html += '<td class="vocherState">补货单</td>'
                    html += '<td class="vocherCode">' + handleUndefined(object.code) + '</td>'
                    html += '<td>' + handleUndefined(object.tansferInfo.creatorName) + '</td>'
                    html += '<td>' +  handleDate_prev(new Date(object.createTime)) + '</<td>'
                    html += '<td>审核中</td>'
                    html += '<td>' + handleUndefined(object.tansferInfo.currentName) + '</td>'
                    html += '<td>' + handleUndefined(object.reason) + '</td>'
                    html += '</tr>'
                })
                $("#itemContainer").empty().append(html)
            }
        },'json');
        $("input[name='dtSelect']").click(function(){
            if($(this).is(':checked')){
                $("input[name='select']:not(:checked)").trigger("click")

            }else{
                $("input[name='select']:checked").trigger("click")
            }
        })
        $("#accSearch").click(function(){
            var code = $("#code").val()
            var creatorName = $("#creatorName").val()
            var start = $("#datetimepicker_start").val()
            var end = $("#datetimepicker_end").val()
            if( start <= end ){
                $.post('../web/api/inventory/getRepairUnHandleBills',{
                    code:code,
                    creatorName:creatorName,
                    start:start,
                    end:end,
                    billType:"REPLENISHMENT",
                    billStatus:"CHECKING"
                },function(data){

                    if(data.response == "success"){
                        var html = ""
                        data.data.content.map(function(object){
                            html += '<tr>'
                            html += '<td>' + '<input type="checkbox" name="select" value="' + object.billId + '">' + '</td>'
                            html += '<input type="hidden" value="' + object.billId + '">';
                            html += '<td class="vocherState">补货单</td>'
                            html += '<td class="vocherCode">' + handleUndefined(object.code) + '</td>'
                            html += '<td>' + handleUndefined(object.tansferInfo.creatorName) + '</td>'
                            html += '<td>' +  handleDate_prev(new Date(object.createTime))  + '</<td>'
                            html += '<td>审核中</td>'
                            html += '<td>' + handleUndefined(object.tansferInfo.currentName) + '</td>'
                            html += '<td>' + handleUndefined(object.reason) + '</td>'
                            html += '</tr>'
                        })
                        $("#itemContainer").empty().append(html)
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }else{
                response_ensure_alert("error","结束时间必须大于开始时间")
            }
        })
        $(document).on("click",".vocherCode",function(){
            status = "待办事宜";
            var vocherCode=$(this).siblings("input[type='hidden']").val();
            var vocherState=$(this).siblings(".vocherState").html();
            if(vocherState == "补货单"  ){
                window.open("replenishment?id=" + vocherCode + "&status=" + status) ;
            }
        });
    });
</script>