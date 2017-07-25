<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery-ui.css">
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${bath}/static/js/purchaseOrder.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <title>采购订单</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1400px){

    }
.reTop{
    width: 100%;
    height: 50px;
    line-height: 50px;
    background: #fff;
}
    .topButton{
        margin: 0px 10px;
    }
.topButton:hover{
}
.topButton:active{
    background: #54a6de;
    color: #fff;
}
    .bodyFirst{
        width: 100%;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul{
        width: 1110px;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul li{
        width: 350px;
        height: 40px;
        line-height: 40px;
        border: 1px solid #54a6de;
        margin-left: 15px;
        margin-bottom: 15px;
        float: left;
    }
.bodyFirst ul li span{
    display: inline-block;
    height: 100%;
    width: 110px;
    text-align: center;
    background: #54a6de;
    color: #fff;
    border-right:1px solid #54a6de;
}
.bodyFirst ul li abbr{
    display: inline-block;
    height: 100%;
    width: 65%;
    padding-left: 5px;

}
.bodyFirst ul li abbr input{
    width: 100%;
    height: 80%;
}
    .bodySecond{
        width: 1283px;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
.bodySecond h1{
    width: 100%;
    height: 50px;
    line-height: 50px;
    padding-left: 5px;
    font-size: 24px;
}
    .bodySecond table{
        table-layout: fixed;
    }
.bodySecond #itemUnit{
    width: 100%;
    height: auto;
    overflow: hidden;
    margin: auto;
}
.bodySecond #itemUnit tr{
    width: 100%;
    height: 50px;
    line-height: 50px;
    white-space: nowrap;

}
    .bodySecond #itemUnit td{
        margin-top: -1px;
    }

    .bodySecond #itemUnit th{
        border-bottom: 1px solid #54a6de;
    }
.bodySecond #itemUnit td,.bodySecond #itemUnit th{
    text-align: center;
    border: 1px solid #54a6de;
    border-left: none;
    border-bottom: none;
    padding: 0px 1.8px;
    overflow: hidden;
}
    .bodySecond #itemUnit tr:last-child td{
        border-bottom: 1px solid #54a6de;
    }
    .bodySecond #itemUnit tr td:first-child, .bodySecond #itemUnit tr th:first-child{
        border-left: 1px solid #54a6de;
    }
    .importLog2{
        width:680px;
        height:auto;
        position:fixed;
        left:15%;
        top:15%;
        background:#FFF;
        box-shadow: 5px 5px 5px 5px #999;
        z-index:99;
        display:none;
    }
    .importLog2 ul{
        width: 650px;
        height: 400px;
        margin-left: 15px;
        border-top:1px solid #e0e0e0;
        padding-top:50px;
        overflow-x: hidden;
        overflow-y: scroll;
    }
    .importLog2 li{
        margin-top: 14px;
    }
    .importLog2 li div{
        font-size: 14px;
        text-align:left;
        margin-left: 80px;
    }
    .InventName{
        border-bottom: 1px solid #54a6de!important;
    }
    .AddressName{
        border-bottom: 1px solid #54a6de!important;
    }
    .LinkName{
        border-bottom: 1px solid #54a6de!important;
    }
    .purchaseNumInput{
        width: 80%;
    }
    .afterShow{
        display: none;
    }
</style>
<body>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    sessionStorage.setItem("ShoppingCart",'');
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;

</script>
<input type="hidden" value="${enterpriseInfo.enterpriseId}" id="enterpriseId"/>
<input type="hidden" id="billId" value="${id}">
<div class="reTop">
    <ul>
        <li style="float: left;margin-left: 1%; color: #54a6de;font-weight: bold">
            <span style="margin-right: 10px;">采购订单</span>-<span class="purseStatue" style="margin-left: 10px;">

        </span>
        </li>
        <li style="float: right;margin-right: 1%;">
            <input style="display: none" type="button" value="导出采购订单" class="topButton export allclickButton">
            <div style="display: inline-block;" id="repleAgree">
                <input style="display: none" type="button" value="提交" class="topButton commit allclickButton">
                <input style="display: none" type="button" value="生成入库单" class="topButton creatBill allclickButton">
                <input style="display: none" type="button" value="结算" class="topButton check allclickButton">
                <input style="display: none" type="button" value="确认" class="topButton nodoupt allclickButton">
                <input style="display: none" type="button" value="终止" class="topButton stop allclickButton">
            </div>
        </li>
    </ul>
</div>

<div class="bodyFirst">
    <h1 style=" width: 200px;margin: auto;margin-top: 50px;margin-bottom: 50px; font-size: 28px; font-weight: border;">采购订单</h1>
    <ul>
        <li><span>单据编号</span><abbr id="code">新采购订单</abbr></li>
        <li><span>申请账号</span><abbr>${enterpriseInfo.enterpriseName}</abbr></li>
        <li>
            <span>申请日期</span>
            <abbr id="time"></abbr>
        </li>
        <li><span>当前状态</span><abbr class="purseStatue"></abbr></li>
        <li style="width: 717px;"><span>备注</span><abbr id="remark"><input maxlength="20" style="" type="text" class="reasonForApply" value=""/> </abbr></li>
        <li><span>供应商</span><abbr id="third">&nbsp;</abbr></li>
        <li><span>采购总数</span><abbr id="purchaseNum">&nbsp;</abbr></li>
        <li><span>采购总金额</span><abbr id="purchaseSum">&nbsp;</abbr></li>
        <li class="afterShow"><span>入库总数</span><abbr id="recepidNum">&nbsp;</abbr></li>
        <li class="afterShow"><span>入库总金额</span><abbr id="recepidSum">&nbsp;</abbr></li>
    </ul>

</div>
<div class="bodySecond">
    <h1><span id="bodyhead">采购明细</span><span style="float: right;margin-right: 1%;">
        <input class="allseachButton" id="changeToTotle" type="button" value="查看汇总">
          <input style="display: none" class="allseachButton" id="changeToDetail" type="button" value="查看明细">
          <input style="display: none" class="allseachButton" id="deleteTotle" type="button" value="删除">
          <input style="display: none" class="allseachButton" id="deleteDetail" type="button" value="删除">
        <input class="allseachButton" id="inOut" type="button" value="导入采购明细">
        <input style="display: none" type="file" id="file">
    </span></h1>
    <table id="itemUnit" cellpadding="0" cellspacing="0">
        <thead id="itemUnitShowHead">
            <tr>
                <th id="enterprise">仓库名称</th>
                <th id="enteraddress" style="display: none;width: 100px;">网点地址</th>
                <th id="enterperson" style="display: none;width: 110px;">联系人</th>
                <th id="enterprise2">序号</th>
                <th>货品名称</th>
                <th>货品规格</th>
                <th>货品编号</th>
                <th>采购单价</th>
                <th>采购数量</th>
                <th>采购金额</th>
                <th class="afterShow">入库数量</th>
                <th class="afterShow">入库金额</th>
            </tr>
        </thead>
        <tbody id="itemUnitShow">
        <tr>
            <td></td>
            <td>1</td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="loading">
    <h1>Excel数据正在导入，请耐心等待.......</h1>
    <img width="250" height="150" src="${bath}/static/img/loading3.gif">
</div>

<div class="importLog2 allpop">
    <h1>错误<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul></ul>
    <div style="height: 80px">
        <input id="backP" class="allseachButton" style="margin-top: 20px; margin-left: 270px;" type="button" value="确定">
    </div>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<input type="hidden" value="${purchaseId}" id="purchaseId">
</body>
<script type="text/javascript">
    $(document).ready(function() {
        var data = ""
        var type = "detail"
        $("#backP").click(function(){
            $(".importLog2").hide()
        })
        $("#inOut").click(function(){
            var inout = setInterval(function(){
                if($("#file").val()!=""){
                    var formData = new FormData();
                    var name = $("#file").val();
                    var fileObj = document.getElementById("file").files[0];

                    formData.append("file",fileObj);
                    formData.append("name",name);
                    $.ajax({
                        url : "../web/api/inventory/parsePurchaseExcel",
                        type : 'POST',
                        data : formData,
                        // 告诉jQuery不要去处理发送的数据
                        processData : false,
                        // 告诉jQuery不要去设置Content-Type请求头
                        contentType : false,
                        beforeSend:function(){
                            coverHtml();
                            $(".loading").show()
                        },
                        success : function(responseStr) {
                            discoverHtml()
                            $(".loading").hide()
                            $("#file").val("")
                            clearInterval(inout)
                            if(responseStr.response == "success"){
                                data = responseStr.data
                                $.post('../web/api/inventory/getExcelData',{
                                    key:data
                                },function(data){
                                    if(data.response == "success"){
                                        $("#enterprise").show()

                                        $("#enterprise2").css("border-left","none")
                                        $("#changeToTotle").show()
                                        $("#changeToDetail").hide()
                                        $("#inOut").val("重新导入")
                                        $("#third").html(handleUndefined(data.data.thirdName))
                                        if( data.data.error.length == 0){
                                            var html = ""
                                            var ob1
                                            var ob2
                                            var i = 1
                                            for( ob1 in data.data.content) {
                                                for( ob2 in data.data.content[ob1]) {
                                                    html += '<tr>'
                                                    html += '<td class="InventName" data-id="' + data.data.content[ob1][ob2].enterpriseId + '">' + data.data.content[ob1][ob2].enterpriseName + '</td>'
                                                    html += '<td>' + i + '</td>'
                                                    html += '<td>' + data.data.content[ob1][ob2].goodsInfoName + '</td>'
                                                    html += '<td>' + handleUndefined(data.data.content[ob1][ob2].goodsInfoSpec) + '</td>'
                                                    html += '<td>' + data.data.content[ob1][ob2].goodsInfoNumber + '</td>'
                                                    html += '<td>' + data.data.content[ob1][ob2].purchasePrice + '</td>'
                                                    html += '<td class="purchaseNum">' + data.data.content[ob1][ob2].purchaseAmount + '</td>'
                                                    html += '<td class="purchaseSum">' + addCommas(data.data.content[ob1][ob2].purchaseMoney, 2) + '</td>'
                                                    html += '</tr>'
                                                    i++
                                                }
                                            }
                                            $("#itemUnitShow").empty().append(html)
                                            var i = 0;
                                            while (i<$(".InventName").length) {
                                                var j = i + 1
                                                var num = 1
                                                while (j <($(".InventName").length + 1)) {
                                                    if (j < $(".InventName").length && $(".InventName").eq(i).attr("data-id") == $(".InventName").eq(j).attr("data-id")) {
                                                        $(".InventName").eq(j).addClass("removeInvent")
                                                        num++;
                                                        j++;
                                                    } else {
                                                        $(".InventName").eq(i).prop("rowspan",num)
                                                        break;
                                                    }
                                                }
                                                i = j;
                                            }

                                            $(".removeInvent").remove()
                                            var purchaseNum = 0
                                            $(".purchaseNum").each(function(){
                                                purchaseNum = purchaseNum + parseInt($(this).html())
                                            })
                                            $("#purchaseNum").html(purchaseNum)
                                            var purchaseSum = 0
                                            $(".purchaseSum").each(function(){
                                                purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                                            })
                                            $("#purchaseSum").html(addCommas(purchaseSum,2))
                                        }else{
                                            var html = ""
                                            data.data.error.map(function(arr){
                                                html += '<li>';
                                                html += '<div>'
                                                arr.map(function(arr2){
                                                    html += arr2 + "</br>"
                                                })
                                                html += '</div>';
                                                html += '</li>';
                                            })
                                            $(".importLog2 ul").empty().append(html)
                                            $(".importLog2").show()
                                        }

                                    }else{
                                        response_ensure_alert("error",data.data.text)
                                    }
                                },'json');

                            }else{
                                response_ensure_alert("error","文件不匹配")
                            }
                        },
                        error : function(responseStr) {
                            $("#file").val("")
                            discoverHtml()
                            $(".loading").hide()
                            if(responseStr.status===500){
                                response_ensure_alert("error","服务器500错误，请检查文件",function(){

                                })
                            }

                        }
                    });
                }
            },4000)
            $("#file").trigger("click")
        })



        var purchaseId = $("#purchaseId").val()
        if(purchaseId ==""){
            var date = new Date();
            var year = date.getFullYear()
            var month = date.getMonth() + 1
            var day = date.getDate()
            if(month < 10 ){
                month = '0' + month
            }
            if(day < 10){
                day = "0"+day
            }
            $("#time").html(year + '-' + month + '-' + day)
            $(".purseStatue").html("创建")
            $(".commit").show()
            $(".nodoupt").hide()
            $(".stop").hide()
        }else{
            $(".commit").hide()
            $("#inOut").hide()
            $("#changeToTotle").show()
            $("#enterprise").show()
            $("#enterprise2").css("border-left","none")
            $("#changeToDetail").hide()
            $.post('../web/api/inventory/getPurchaseDetailByKey',{
                purchaseId:purchaseId
            },function(data){
                if(data.response == "success"){
                    $('#enteraddress').show()
                    $('#enterperson').show()
                    $(".purseStatue").html(purchaseStu(data.data.status))
                    $("#third").html(data.data.thirdName)
                    if(data.data.status != "TO_CONFIRM"){
                        if(data.data.marks == undefined ||data.data.marks ==""){
                            $("#remark").html("&nbsp;")
                        }else{
                            $("#remark").html(data.data.marks)
                        }
                    }else{
                        $(".reasonForApply").val(data.data.marks)
                    }
                    $("#code").html(data.data.code)
                    if(data.data.status == "TO_CONFIRM"){
                        $(".nodoupt").show()
                        $(".stop").show()
                        $(".export").show()
                        $("#deleteDetail").show()
                    }else if(data.data.status == "FINISHED"){
                        $(".export").show()
                        $(".stop").show()
                        $(".creatBill").show()
                    }
                    else if(data.data.status == "COMMODITY_STORAGE"||data.data.status == "FOR_SETTLEMENT"){
                        $(".export").show()
                        $(".check").show()
                        $(".afterShow").show()
                    }
                    else if( data.data.status == "COMPLETED"){
                        $(".export").show()
                        $(".afterShow").show()
                    }
                    $("#time").html(handleDate_prev(new Date(data.data.createTime)))

                    var html = ""
                    var i = 1
                    data.data.items.map(function(object){
                        html += '<tr>'
                        html += '<td class="InventName" data-id="' + object.enterpriseId + '">'+ object.enterpriseName +'</td>'
                        html += '<td class="AddressName" data-id="' + object.enterpriseId + '"title="'+ handleUndefined(object.address) +'">'
                        if(typeof (object.address) != 'undefined'){
                        html += object.address
                        }
                        html +='</td>'
                        html += '<td class="LinkName" data-id="' + object.enterpriseId + '"title="'+ handleUndefined(object.linkman) +'">'
                        if(typeof (object.linkman) != 'undefined') {
                             html += object.linkman
                        }
                        if(typeof (object.linkMobile) != 'undefined'){
                            html+= '<br/>'+object.linkMobile
                        }
                        html += '</td>'
                        if(data.data.status == "TO_CONFIRM") {
                            html += '<td><input type="checkbox" name="deleteDetail" data-id="' + object.itemId + '">' + i + '</td>'
                        }else{
                            html += '<td>' + i + '</td>'
                        }
                        html += '<td title="'+ object.info.goodsInfoName +'">' + object.info.goodsInfoName + '</td>'
                        html += '<td title="'+ handleUndefined(object.info.specString) +'">' + handleUndefined(object.info.specString) + '</td>'
                        html += '<td title="'+ object.info.goodsInfoItemNo +'">' + object.info.goodsInfoItemNo + '</td>'
                        html += '<td class="price">' + object.settlePrice + '</td>'
                        if(data.data.status == "TO_CONFIRM"){
                            html += '<td class="purchaseNum"><input type="text" class="purchaseNumInput" value="'+ handleUndefinedToZero(object.sumAmount) + '"></td>'
                        }else{
                            html += '<td class="purchaseNum">'+ handleUndefinedToZero(object.sumAmount) + '</td>'
                        }
                        html += '<td class="purchaseSum">' + addCommas(object.totalPrice,2) + '</td>'
                        if( data.data.status == "COMMODITY_STORAGE" || data.data.status == "FOR_SETTLEMENT"|| data.data.status == "COMPLETED"){
                            html += '<td class="respiedNum">' + handleUndefinedToZero(object.receiptAmount) + '</td>'
                            html += '<td class="respiedSum">' + addCommas(parseFloat(handleUndefinedToZero(object.receiptAmount)) * handleUndefinedToZero(object.settlePrice),2) + '</td>'
                        }else{

                        }
                        html += '</tr>'
                        i++
                    })
                    $("#itemUnitShow").empty().append(html)
                    var i = 0;
                        while (i<$(".InventName").length) {
                            var j = i + 1
                            var num = 1
                            while (j < ($(".InventName").length + 1)) {
                                if (j < $(".InventName").length && $(".InventName").eq(i).attr("data-id") == $(".InventName").eq(j).attr("data-id")) {
                                    $(".InventName").eq(j).addClass("removeInvent")
                                    num++;
                                    j++;
                                } else {
                                    $(".InventName").eq(i).prop("rowspan", num)
                                    break;
                                }
                            }
                            i = j;
                        }
                    var n = 0;
                    while (n<$(".AddressName").length) {
                        var m = n + 1
                        var num = 1
                        while (m < ($(".AddressName").length + 1)) {
                            if (m < $(".AddressName").length && $(".AddressName").eq(n).attr("data-id") == $(".AddressName").eq(m).attr("data-id")) {
                                $(".AddressName").eq(m).addClass("removeInvent")
                                num++;
                                m++;
                            } else {
                                $(".AddressName").eq(n).prop("rowspan", num)
                                break;
                            }
                        }
                        n = m;
                    }
                    var x = 0;
                    while (x<$(".LinkName").length) {
                        var y = x + 1
                        var num = 1
                        while (y < ($(".LinkName").length + 1)) {
                            if (y < $(".LinkName").length && $(".LinkName").eq(x).attr("data-id") == $(".LinkName").eq(y).attr("data-id")) {
                                $(".LinkName").eq(y).addClass("removeInvent")
                                num++;
                                y++;
                            } else {
                                $(".LinkName").eq(x).prop("rowspan", num)
                                break;
                            }
                        }
                        x = y;
                    }
                        $(".removeInvent").remove()
                        if(data.data.status == "TO_CONFIRM"){
                            var purchaseNum = 0
                            $(".purchaseNum").each(function(){
                                purchaseNum = purchaseNum + parseInt($(this).children("input").val())
                            })
                        }else{
                            var purchaseNum = 0
                            $(".purchaseNum").each(function(){
                                purchaseNum = purchaseNum + parseInt($(this).html())
                            })
                        }
                        $("#purchaseNum").html(purchaseNum)
                        var purchaseSum = 0
                        $(".purchaseSum").each(function(){
                            purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        $("#purchaseSum").html(addCommas(purchaseSum,2))
                    if( data.data.status != "TO_CONFIRM" || data.data.status != "FINISHED"|| data.data.status != "TERMINATED"){
                        var receptNum = 0
                        $(".respiedNum").each(function(){
                            receptNum = receptNum + parseInt($(this).html())
                        })
                        $("#recepidNum").html(receptNum)
                        var receptSum = 0
                        $(".respiedSum").each(function(){
                            receptSum = receptSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        $("#recepidSum").html(addCommas(receptSum,2))
                    }
                }else{
                    response_ensure_alert("error",data.data.text)
                }
            },'json')
        }





        $("#changeToTotle").click(function(){
            if( data != "" && purchaseId == "" ){//创建导入excel切换汇总明细
                $.post('../web/api/inventory/getMergeBillByExce',{
                    key:data
                },function(data){
                    if(data.response == "success"){
                        type="totle"
                        $("#changeToDetail").show()
                        $("#changeToTotle").hide()
                        $('#enteraddress').hide()
                        $('#enterperson').hide
                        $("#bodyhead").html("汇总清单")
                        var i = 1
                        var html = ""
                        data.data.map(function(object){
                                html += '<tr>'
                                html += '<td>' + i + '</td>'
                                html += '<td>' + object.info.goodsInfoName + '</td>'
                                html += '<td>' + handleUndefined(object.info.specString) + '</td>'
                                html += '<td>' + object.info.goodsInfoItemNo + '</td>'
                                html += '<td>' + object.info.settlePrice + '</td>'
                                html += '<td class="purchaseNum">' + object.amount + '</td>'
                                html += '<td class="purchaseSum">' + addCommas( parseFloat(object.info.settlePrice) * parseInt(object.amount),2)  + '</td>'
                                html += '</tr>'
                                i++
                        })
                        $("#enterprise").hide()
                        $("#enterprise2").css("border-left","1px solid #54a6de")
                        $("#itemUnitShow").empty().append(html)
                        var purchaseNum = 0
                        $(".purchaseNum").each(function(){
                            purchaseNum = purchaseNum + parseInt($(this).html())
                        })
                        $("#purchaseNum").html(purchaseNum)
                        var purchaseSum = 0
                        $(".purchaseSum").each(function(){
                            purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        $("#purchaseSum").html(addCommas(purchaseSum,2))
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }else if( purchaseId != "" && data ==""){//列表进入 切换汇总明细
                $.post('../web/api/inventory/getSumDetailByPrimaryKey',{
                    purchaseId:purchaseId
                },function(data){
                    if(data.response == "success"){
                        $("#bodyhead").html("汇总清单")
                        type="totle"
                        $(".purseStatue").html(purchaseStu(data.data.status))
                        $("#third").html(data.data.thirdName)
                        $("#enterprise").hide()
                        $("#enterprise2").css("border-left","1px solid #54a6de")
                        $("#changeToDetail").show()
                        $("#changeToTotle").hide()
                        $('#enteraddress').hide()
                        $('#enterperson').hide()
                        if(data.data.status == "TO_CONFIRM"){
                            $("#deleteTotle").show()
                            $("#deleteDetail").hide()
                        }

                        var html = ""
                        var i = 1
                        data.data.items.map(function(object){
                            html += '<tr>'

                            if(data.data.status == "TO_CONFIRM") {
                                html += '<td><input type="checkbox" name="deleteTotle" data-id="' + object.info.goodsInfoId + '">' + i + '</td>'
                            }else{
                                html += '<td>' + i + '</td>'
                            }
                            html += '<td title="'+object.info.goodsInfoName +'">' + object.info.goodsInfoName + '</td>'
                            html += '<td title="'+ handleUndefined(object.info.specString) +'">' + handleUndefined(object.info.specString) + '</td>'
                            html += '<td title="'+object.info.goodsInfoItemNo+'">' + object.info.goodsInfoItemNo + '</td>'
                            if(data.data.status == "TO_CONFIRM"){
                                html += '<td><input class="settlePrice" type="text" value="' + object.settlePrice + '"></td>'
                            }else{
                                html += '<td>' + object.settlePrice + '</td>'
                            }

                            html += '<td class="purchaseNum">' + handleUndefinedToZero(object.sumAmount) + '</td>'
                            html += '<td class="purchaseSum">' + parseFloat(object.settlePrice) * handleUndefinedToZero(object.sumAmount) + '</td>'
                            if( data.data.status == "COMMODITY_STORAGE" || data.data.status == "FOR_SETTLEMENT"|| data.data.status == "COMPLETED"){
                                html += '<td class="respiedNum">' + handleUndefinedToZero(object.receiptAmount) + '</td>'
                                html += '<td class="respiedSum">' + addCommas(parseFloat(handleUndefinedToZero(object.receiptAmount)) * handleUndefinedToZero(object.settlePrice),2) + '</td>'
                            }else{

                            }
                            html += '</tr>'
                            i++
                        })
                        $("#itemUnitShow").empty().append(html)
                        var purchaseNum = 0
                        $(".purchaseNum").each(function(){
                            purchaseNum = purchaseNum + parseInt($(this).html())
                        })
                        $("#purchaseNum").html(purchaseNum)
                        var purchaseSum = 0
                        $(".purchaseSum").each(function(){
                            purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        $("#purchaseSum").html(addCommas(purchaseSum,2))
                        if( data.data.status != "TO_CONFIRM" || data.data.status != "FINISHED"|| data.data.status != "TERMINATED"){
                            var receptNum = 0
                            $(".respiedNum").each(function(){
                                receptNum = receptNum + parseInt($(this).html())
                            })
                            $("#recepidNum").html(receptNum)
                            var receptSum = 0
                            $(".respiedSum").each(function(){
                                receptSum = receptSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                            })
                            $("#recepidSum").html(addCommas2(receptSum,2))
                        }
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }
            else if(purchaseId =="" && data == ""){
                response_ensure_alert("error","请先导入Excel")
            }
        })



        $("#changeToDetail").click(function(){
            if( data != "" && purchaseId == "" ){//创建导入excel切换汇总明细

                $("#enterprise").show()
                $("#enterprise2").css("border-left","none")
                $("#changeToTotle").show()
                $("#changeToDetail").hide()
                $.post('../web/api/inventory/getExcelData',{
                    key:data
                },function(data){
                    if(data.response == "success"){
                        type="detail"
                        $("#bodyhead").html("采购明细")
                        $("#enterprise").show()
                        $("#changeToTotle").show()
                        $("#changeToDetail").hide()
                        $("#enterprise2").css("border-left","none")
                        $("#inOut").html("重新导入")
                        if( data.data.error.length == 0){
                            var html = ""
                            var ob1
                            var ob2
                            var i = 1
                            for( ob1 in data.data.content) {
                                for( ob2 in data.data.content[ob1]) {
                                    html += '<tr>'
                                    html += '<td class="InventName" data-id="' + data.data.content[ob1][ob2].enterpriseId + '">' + data.data.content[ob1][ob2].enterpriseName + '</td>'
                                    html += '<td>' + i + '</td>'
                                    html += '<td title="'+data.data.content[ob1][ob2].goodsInfoName+'">' + data.data.content[ob1][ob2].goodsInfoName + '</td>'
                                    html += '<td title="'+handleUndefined(data.data.content[ob1][ob2].goodsInfoSpec)+'">' + handleUndefined(data.data.content[ob1][ob2].goodsInfoSpec) + '</td>'
                                    html += '<td title="'+data.data.content[ob1][ob2].goodsInfoNumber+'">' + data.data.content[ob1][ob2].goodsInfoNumber + '</td>'
                                    html += '<td class="price">' + data.data.content[ob1][ob2].purchasePrice + '</td>'
                                    html += '<td class="purchaseNum">' + data.data.content[ob1][ob2].purchaseAmount + '</td>'
                                    html += '<td class="purchaseSum">' + addCommas(data.data.content[ob1][ob2].purchaseMoney, 2) + '</td>'
                                    html += '</tr>'
                                    i++
                                }
                            }
                            $("#itemUnitShow").empty().append(html)
                            var i = 0;
                            while (i<$(".InventName").length) {
                                var j = i + 1
                                var num = 1
                                while (j <($(".InventName").length + 1)) {
                                    if (j < $(".InventName").length && $(".InventName").eq(i).attr("data-id") == $(".InventName").eq(j).attr("data-id")) {
                                        $(".InventName").eq(j).addClass("removeInvent")
                                        num++;
                                        j++;
                                    } else {
                                        $(".InventName").eq(i).prop("rowspan",num)
                                        break;
                                    }
                                }
                                i = j;
                            }
                            $(".removeInvent").remove()
                            var purchaseNum = 0
                            $(".purchaseNum").each(function(){
                                purchaseNum = purchaseNum + parseInt($(this).html())
                            })
                            $("#purchaseNum").html(purchaseNum)
                            var purchaseSum = 0
                            $(".purchaseSum").each(function(){
                                purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                            })
                            $("#purchaseSum").html(addCommas(purchaseSum,2))
                        }else{
                            var html = ""
                            data.data.error.map(function(arr){
                                html += '<li>';
                                html += '<div>'+ arr + '</div>';
                                html += '</li>';
                            })
                            $(".importLog2 ul").empty().append(html)
                            $(".importLog2").show()
                        }

                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json');

            }else if( purchaseId != "" && data ==""){//列表进入 切换汇总明细
                $.post('../web/api/inventory/getPurchaseDetailByKey',{
                    purchaseId:purchaseId
                },function(data){
                    if(data.response == "success"){
                        type="detail"
                        $("#bodyhead").html("采购明细")
                        $(".purseStatue").html(purchaseStu(data.data.status))
                        $("#third").html(data.data.thirdName)
                        $("#enterprise").show()
                        $("#enterprise2").css("border-left","none")
                        $("#changeToTotle").show()
                        $('#enteraddress').show();
                        $('#enterperson').show()
                        $("#changeToDetail").hide()
                        if(data.data.status == "TO_CONFIRM") {
                            $("#deleteTotle").hide()
                            $("#deleteDetail").show()
                        }
                        var html = ""
                        var i = 1
                        data.data.items.map(function(object){
                            html += '<tr>'
                            html += '<td class="InventName" data-id="' + object.enterpriseId + '">'+ object.enterpriseName +'</td>'
                            html += '<td class="AddressName" data-id="' + object.enterpriseId + '"title="'+ handleUndefined(object.address) +'">'
                            if(typeof (object.address) != 'undefined'){
                            html += object.address
                            }
                            html +='</td>'
                            html += '<td class="LinkName" data-id="' + object.enterpriseId + '"title="'+ handleUndefined(object.linkman) +'">'
                            if(typeof (object.linkman) != 'undefined') {
                                html += object.linkman
                            }
                            if(typeof (object.linkMobile) != 'undefined'){
                                html+= '<br/>'+object.linkMobile
                            }
                            html += '</td>'

                            if(data.data.status == "TO_CONFIRM") {
                                html += '<td><input type="checkbox" name="deleteDetail" data-id="' + object.itemId + '">' + i + '</td>'
                            }else{
                                html += '<td>' + i + '</td>'
                            }
                            html += '<td title="'+ object.info.goodsInfoName +'">' + object.info.goodsInfoName + '</td>'
                            html += '<td title="'+ handleUndefined(object.info.specString) +'">' + handleUndefined(object.info.specString) + '</td>'
                            html += '<td title="'+ object.info.goodsInfoItemNo +'">' + object.info.goodsInfoItemNo + '</td>'
                            html += '<td class="price">' + object.settlePrice + '</td>'
                            if(data.data.status == "TO_CONFIRM"){
                                html += '<td class="purchaseNum"><input type="text" class="purchaseNumInput" value="'+ handleUndefinedToZero(object.sumAmount) + '"></td>'
                            }else{
                                html += '<td class="purchaseNum">'+ handleUndefinedToZero(object.sumAmount) + '</td>'
                            }
                            html += '<td class="purchaseSum">' + addCommas(object.totalPrice,2) + '</td>'
                            if( data.data.status == "COMMODITY_STORAGE" || data.data.status == "FOR_SETTLEMENT"|| data.data.status == "COMPLETED"){
                                html += '<td class="respiedNum">' + handleUndefinedToZero(object.receiptAmount) + '</td>'
                                html += '<td class="respiedSum">' + addCommas(parseFloat(handleUndefinedToZero(object.receiptAmount)) * handleUndefinedToZero(object.settlePrice),2) + '</td>'
                            }else{

                            }
                            html += '</tr>'
                            i++
                        })
                        $("#itemUnitShow").empty().append(html)
                        var i = 0;
                        while (i<$(".InventName").length) {
                            var j = i + 1
                            var num = 1
                            while (j < ($(".InventName").length + 1)) {
                                if (j < $(".InventName").length && $(".InventName").eq(i).attr("data-id") == $(".InventName").eq(j).attr("data-id")) {
                                    $(".InventName").eq(j).addClass("removeInvent")
                                    num++;
                                    j++;
                                } else {
                                    $(".InventName").eq(i).prop("rowspan", num)
                                    break;
                                }
                            }
                            i = j;
                        }
                        var n = 0;
                        while (n<$(".AddressName").length) {
                            var m = n + 1
                            var num = 1
                            while (m < ($(".AddressName").length + 1)) {
                                if (m < $(".AddressName").length && $(".AddressName").eq(n).attr("data-id") == $(".AddressName").eq(m).attr("data-id")) {
                                    $(".AddressName").eq(m).addClass("removeInvent")
                                    num++;
                                    m++;
                                } else {
                                    $(".AddressName").eq(n).prop("rowspan", num)
                                    break;
                                }
                            }
                            n = m;
                        }
                        var x = 0;
                        while (x<$(".LinkName").length) {
                            var y = x + 1
                            var num = 1
                            while (y < ($(".LinkName").length + 1)) {
                                if (y < $(".LinkName").length && $(".LinkName").eq(x).attr("data-id") == $(".LinkName").eq(y).attr("data-id")) {
                                    $(".LinkName").eq(y).addClass("removeInvent")
                                    num++;
                                    y++;
                                } else {
                                    $(".LinkName").eq(x).prop("rowspan", num)
                                    break;
                                }
                            }
                            x = y;
                        }
                        $(".removeInvent").remove()
                        if(data.data.status == "TO_CONFIRM"){
                            var purchaseNum = 0
                            $(".purchaseNum").each(function(){
                                purchaseNum = purchaseNum + parseInt($(this).children("input").val())
                            })
                        }else{
                            var purchaseNum = 0
                            $(".purchaseNum").each(function(){
                                purchaseNum = purchaseNum + parseInt($(this).html())
                            })
                        }
                        $("#purchaseNum").html(purchaseNum)
                        var purchaseSum = 0
                        $(".purchaseSum").each(function(){
                            purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        $("#purchaseSum").html(addCommas(purchaseSum,2))
                        if( data.data.status != "TO_CONFIRM" || data.data.status != "FINISHED"|| data.data.status != "TERMINATED"){
                            var receptNum = 0
                            $(".respiedNum").each(function(){
                                receptNum = receptNum + parseInt($(this).html())
                            })
                            $("#recepidNum").html(receptNum)
                            var receptSum = 0
                            $(".respiedSum").each(function(){
                                receptSum = receptSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                            })
                            $("#recepidSum").html(addCommas(receptSum,2))
                        }
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }
            else if(purchaseId =="" && data == ""){
                response_ensure_alert("error","请先导入Excel")
            }
        })

        $(document).on("blur",".purchaseNumInput",function(){
            var val = $(this).val()
            var purchase = $(this).parent().siblings(".price").html()
            var _this =this
            var itemId = $(this).parent().siblings().children("input").attr("data-id")
            if(/^[0-9]*[1-9][0-9]*$/.test(val)){
                $.post('../web/api/inventory/updateByItemId',{
                    itemId:itemId,
                    amount:val
                },function(data){
                    if(data.response == "success"){
                        $(_this).parent().siblings(".purchaseSum").html(addCommas(purchase * val,2))
                        var purchaseNum = 0
                        $(".purchaseNum").each(function(){
                            purchaseNum = purchaseNum + parseInt($(this).children("input").val())
                        })
                        $("#purchaseNum").html(purchaseNum)
                        var purchaseSum = 0
                        $(".purchaseSum").each(function(){
                            purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        console.log(purchaseSum)
                        $("#purchaseSum").html(addCommas(purchaseSum,2))
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }else{
                $(".purchaseNumInput").attr("readonly","readonly")
                response_ensure_alert("error","请输入正整数",function(){
                    $(".purchaseNumInput").attr("readonly",null)
                    $(_this).focus()
                    $(_this).val("")
                })


            }
        })
        $(document).on("blur",".settlePrice",function(){
            var val = $(this).val()
            var purchaseN = $(this).parent().siblings(".purchaseNum").html()
            var _this =this
            var itemId = $(this).parent().siblings().children("input").attr("data-id")
            if(/^[+]?[\d]+(([\.]{1}[\d]+)|([\d]*))$/.test(val)){
                $.post('../web/api/inventory/updateSettlePrice',{
                    purchaseId:purchaseId,
                    goodsInfoId:itemId,
                    settlePrice:val
                },function(data){
                    if(data.response == "success"){
                        $(_this).parent().siblings(".purchaseSum").html(addCommas(purchaseN * val,2))
                        var purchaseNum = 0
                        $(".purchaseNum").each(function(){
                            purchaseNum = purchaseNum + parseInt($(this).html())
                        })
                        $("#purchaseNum").html(purchaseNum)
                        var purchaseSum = 0
                        $(".purchaseSum").each(function(){
                            purchaseSum = purchaseSum + parseFloat($(this).html().replace(",","").replace(",","").replace(",","").replace(",",""))
                        })
                        $("#purchaseSum").html(addCommas(purchaseSum,2))
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }else{
                $(".settlePrice").attr("readonly","readonly")
                response_ensure_alert("error","请输入正数",function(){
                    $(".settlePrice").attr("readonly",null)
                    $(_this).focus()
                    $(_this).val("")
                })
            }
        })

        $("#deleteDetail").click(function(){
            if($("input[name='deleteDetail']:checked").length != 0 && $("input[name='deleteDetail']:not(:checked)").length != 0){
                response_OkCancel_alert("warning","是否确认删除",function(){
                    var goodsInfoIds = "["
                    $("input[name='deleteDetail']:checked").each(function(){
                        goodsInfoIds += $(this).attr("data-id") + ','
                    })
                    goodsInfoIds = goodsInfoIds.substring(0,goodsInfoIds.length -1)
                    goodsInfoIds += "]"
                    $.post('../web/api/inventory/deleteByGoodsInfoId',{
                        purchaseId:purchaseId,
                        itemIds:goodsInfoIds
                    },function(data){
                        if(data.response == "success"){
                           $("#changeToDetail").trigger("click")
                        }else{
                            response_ensure_alert("error",data.data.text)
                        }
                    },'json')
                },function(){})

            }else{
                response_ensure_alert("error","必须要有删除对象且不允许全部删除")
            }
        })




        $("#deleteTotle").click(function(){
            if($("input[name='deleteTotle']:checked").length != 0 && $("input[name='deleteTotle']:not(:checked)").length != 0 ){
                response_OkCancel_alert("warning","是否确认删除",function(){
                    var goodsInfoIds = "["
                    $("input[name='deleteTotle']:checked").each(function(){
                        goodsInfoIds += $(this).attr("data-id") + ','
                    })
                    goodsInfoIds = goodsInfoIds.substring(0,goodsInfoIds.length -1)
                    goodsInfoIds += "]"
                    $.post('../web/api/inventory/deleteByGoodsInfoId',{
                        purchaseId:purchaseId,
                        goodsInfoIds:goodsInfoIds
                    },function(data){
                        if(data.response == "success"){
                            $("#changeToTotle").trigger("click")
                        }else{
                            response_ensure_alert("error",data.data.text)
                        }
                    },'json')
                },function(){})

            }else{
                response_ensure_alert("error","必须要有删除对象且不允许全部删除")
            }
        })


        $(".export").click(function(){
            var state = $(".purseStatue").html()
            if(state == "待确认" && type == "detail"){
                window.location.href = '../web/api/exportExcel/exportPurchaseDetailByUnconfirmed?purchaseId=' + purchaseId;
            }
            if( state == "待确认" && type == "totle" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseSumByUnconfirmed?purchaseId=' + purchaseId;
            }
            if( state == "已确认" && type == "detail" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseDetailByUnconfirmed?purchaseId=' + purchaseId;
            }
            if( state == "已确认" && type == "totle" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseSumByUnconfirmed?purchaseId=' + purchaseId;
            }
            if( state == "待商品入库" && type == "detail" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseDetailByConfirm?purchaseId=' + purchaseId;
            }
            if( state == "待商品入库" && type == "totle" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseSumByConfirm?purchaseId=' + purchaseId;
            }
            if( state == "待结算" && type == "detail" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseDetailByConfirm?purchaseId=' + purchaseId;
            }
            if( state == "待结算" && type == "totle" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseSumByConfirm?purchaseId=' + purchaseId;
            }
            if( state == "已完成" && type == "detail" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseDetailByConfirm?purchaseId=' + purchaseId;
            }
            if( state == "已完成" && type == "totle" ){
                window.location.href = '../web/api/exportExcel/exportPurchaseSumByConfirm?purchaseId=' + purchaseId;
            }
        })


        $(".commit").click(function(){
            if(data != ""){
                var marks = $(".reasonForApply").val()
                $.post('../web/api/inventory/createPurchaseBIllByExcel',{
                    key:data,
                    marks:marks
                },function(data){
                    if(data.response == "success"){
                        localStorage['systemAction'] = 'refresh';
                        response_ensure_alert("success","创建采购单成功",function(){

                            window.close()
                        })
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json')
            }else{
                response_ensure_alert("error","请先导入表格")
            }
        })
    })




</script>