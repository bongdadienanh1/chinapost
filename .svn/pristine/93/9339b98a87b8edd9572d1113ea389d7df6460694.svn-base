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
    <title>网点基础数据统计表</title>
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
        /*line-height:40px;*/
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
        width: 100px;
    }
    #holder{
        margin: 30px 0px;
    }
    .allheadstyle a,.allheadstyle span{
        width: 9%!important;
        font-size: 15px!important;
    }
    #chooseBillStatus{
        position: absolute;
        left: 100px;
        top: 3px;
        background: #fff;
        z-index: 1;
        cursor: pointer;
    }
    #chooseBillStatus dt{
        white-space: nowrap;
        overflow: hidden;
    }
    #chooseBillStatus dt, #chooseBillStatus dd{
        width: 125px;
        height: 20px;
        line-height: 20px;

        text-align: center;
    }
    #chooseBillStatus dd{
        margin-top: -1px;
        display: none;
    }
    #chooseBillStatus dt:hover,#chooseBillStatus dd:not(:first-child):hover{
        background: #54a6de;
        color: #fff;
    }
    .allinputButton {
        line-height: 26px;
    }
</style>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
    })
</script>
<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);
        $("#chooseGoodsType").mouseover(function(){
            $("#chooseGoodsType dd").show()
        })
        $("#chooseGoodsType").mouseout(function(){
            $("#chooseGoodsType dd").hide()
        })
        $("#chooseGoodsType dd").click(function(){
            var html = $(this).html()
            var dataId = $(this).data("id")
            $("#chooseGoodsType dt").attr("data-id",dataId);
            $("#chooseGoodsType dt").html(html);
            $("#chooseGoodsType").trigger("mouseout")
        })

        $("#chooseBillStatus").mouseover(function(){
            $("#chooseBillStatus dd").show()
        })
        $("#chooseBillStatus").mouseout(function(){
            $("#chooseBillStatus dd").hide()
        })
        $("input[name='billStatesCheckboxAll']").click(function(){
            if( $(this).is(":checked") ){
                $("input[name='billStatesCheckbox']:not(':checked')").trigger("click")
            }else{
                $("input[name='billStatesCheckbox']:checked").trigger("click")
            }
        })
        $("input[name='billStatesCheckbox']").click(function(){
            if(  $("input[name='billStatesCheckbox']:checked").length == 5 ){
                $("#chooseBillStatus dt").html("全部")
                $("input[name='billStatesCheckboxAll']").prop("checked","checked")
            }else{
                $("input[name='billStatesCheckboxAll']").prop("checked","")
                var html = ""
                $("input[name='billStatesCheckbox']:checked").each(function(){
                    html += $(this).next("span").html() + ','
                })
                html = html.substring(0,html.length-1)
                $("#chooseBillStatus dt").html(html)
            }

        })
    })
</script>
<body style="background: #fff">
<div class="allheadstyle"><span style="font-size: 18px!important;font-weight:bold;width: 100%!important;text-align: left;padding-left: 20px">报表中心  -  网点基础数据统计表</span>
</div>

<div class="accountSearch">
    <ul>
        <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
        <li><input class="allinputButton" placeholder="开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
        <li><input class="allinputButton" placeholder="结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
        <li>
            <div style="margin-left:20px;margin-top:0px;text-align: left!important; position: relative;" class="allinputButton"><span style="display:inline-block;margin-left: 20px;color: #999">单据状态</span>
                <dl id="chooseBillStatus">
                    <dt data-name="ALL_PICK">全部</dt>
                    <dd style="text-align: left;margin-left: 10px" data-name="ALL_PICK"><input type="checkbox" name="billStatesCheckboxAll" checked="checked"/>全部</dd>
                    <dd data-name="WAIT_DELIVERY"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待发货</span></dd>
                    <dd data-name="WAIT_RECEIVER"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待收货</span></dd>
                    <dd data-name="WAIT_EDIT"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>待修改</span></dd>
                    <dd data-name="FINISHED"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>已完成</span></dd>
                    <dd data-name="PARTIAL_RECEIPT"><input type="checkbox" name="billStatesCheckbox" checked="checked"/><span>部分收货</span></dd>
                </dl>
        </li>
        <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
    </ul>
</div>


<div style="width: 1600px;">
    <table class="table_listBase" cellpadding="0" cellspacing="0" align="center">
        <thead>
        <tr>
            <th>账号名称</th>
            <th>采购日期</th>
            <th>福临门</th>
            <th>金龙鱼</th>
            <th>优生活</th>
            <th>铁好</th>
            <th>乐维</th>
            <th>远恒</th>
            <th>合计</th>
        </tr>
        </thead>
        <tbody align="center" id="itemContainer">
        <tr>
            <td style="text-align: center" colspan="6"><img src="${bath}/static/img/nodata.png" width="1600px;" height="600"></td>
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
    array[15] = '';//idCard
    array[8] = '';//start
    array[9] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
    //    var todayDate = new Date();//获取今天日期
    //    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
    //    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
    //    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");



    $(document).ready(function(){
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        var lock = false
        $("#accSearch").click(function(){
            if(  $("#datetimepicker_end").val() >= $("#datetimepicker_start").val()  ){
                lock=true
                array[15] = $("input[name='accountUserName']").val();//idCard
                array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                ajaxPages("../web/api/report/getNetDataReport","itemContainer","holder","baseDataform",10,'','',array);
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