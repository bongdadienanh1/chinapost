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
    <script type="text/javascript" src="${bath}/static/js/replenishmentMega.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <title>补货流程</title>
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
    vertical-align: middle;
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
    border-right: none;
    border-bottom: none;
    padding: 0px 1.8px;
    overflow: hidden;
}
    .bodySecond #itemUnit tr:last-child td{
        border-bottom: 1px solid #54a6de;
    }
    .bodySecond #itemUnit tr td:last-child, .bodySecond #itemUnit tr th:last-child{
        border-right: 1px solid #54a6de;
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
            <span style="margin-right: 10px;">补货申请流程</span>-<span style="margin-left: 10px;">
            审核中
        </span>
        </li>
        <li style="float: right;margin-right: 1%;">
            <div style="display: inline-block;" id="repleAgree"><input type="button" value="同意" class="topButton nodoupt allclickButton"><input type="button" value="终止" class="topButton stop allclickButton"></div>
        </li>
    </ul>
</div>

<div class="bodyFirst">
    <h1 style=" width: 200px;margin: auto;margin-top: 50px;margin-bottom: 50px; font-size: 28px; font-weight: border;">补货申请流程</h1>
    <ul>
        <li><span>单据编号</span><abbr>新合并补货单</abbr></li>
        <li><span>申请账号</span><abbr>${enterpriseInfo.enterpriseName}</abbr></li>
        <li>
            <span>申请日期</span>
            <abbr id="time"></abbr>
        </li>
        <li><span>当前状态</span><abbr>审核中</abbr></li>
        <li style="width: 717px;"><span>备注</span><abbr><input maxlength="20" style="" type="text" class="reasonForApply" value=""/> </abbr></li>
    </ul>

</div>
<div class="bodySecond">
    <h1><span id="headbody">汇总清单</span><span style="float: right;margin-right: 1%;">
        <input class="allseachButton" id="change" type="button" value="查看明细">
    </span></h1>
    <table id="itemUnit" cellpadding="0" cellspacing="0">
        <thead id="itemUnitShowHead">
            <tr>
                <th id="changhead">序号</th>
                <th>货品名称</th>
                <th>货品规格</th>
                <th>货品编号</th>
                <th>装箱数</th>
                <th>审核数量</th>
            </tr>
        </thead>
        <tbody id="itemUnitShow">
            <tr>
                <td>1</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

        </tbody>
    </table>
</div>
<input id="list" value="${list}" type="hidden">


<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        var date = new Date();
        var year = date.getFullYear()
        var month = date.getMonth() + 1
        var day = date.getDate()
        var type = 0
        if(month < 10 ){
            month = '0' + month
        }
        if(day < 10){
            day = "0"+day
        }
        $("#time").html(year + '-' + month + '-' + day)
        var list = $("#list").val()
        $.post('../web/api/inventory/getMergeBillByBillIds',{
            flag:1,
            billIds:list
        },function(data){
            if(data.response == "success"){
                var html = ""
                var x = 0
                data.data.map(function(object){
                    x =  x + 1
                    html += '<tr>'
                    html += '<td>'+ x +'</td>'
                    html += '<td>' + handleUndefined(object.items[0].info.goodsInfoName) + '</td>'
                    html += '<td>' + handleUndefined(object.items[0].info.specString) + '</td>'
                    html += '<td>' + handleUndefined(object.items[0].info.goodsInfoItemNo) + '</td>'
                    html += '<td>' + handleUndefined(object.items[0].goodsInfoPack) + ' ' + handleUndefined(object.items[0].goodsInfoUnit) + '/箱' + '</td>'
                    html += '<td>' + handleUndefined(object.items[0].checkedAmount) + '</td>'
                    html += '</tr>'
                })
                $("#itemUnitShow").empty().append(html)
            }else{
                response_ensure_alert("error",data.data.text)
            }
        },'json');
        $("#change").click(function(){
            if( type == 0){
                $.post('../web/api/inventory/getMergeBillByBillIds',{
                    flag:0,
                    billIds:list
                },function(data){
                    if(data.response == "success"){
                        $("#change").val("查看汇总")
                        $("#changhead").html("网点名称")
                        $("#headbody").html("货品明细")
                        var html = ""
                        var x = 0
                        data.data.map(function(data){
                            html += '<tr>'
                            html += '<td class="InventName" data-id="' + data.enterpriseId + '">'+ data.enterpriseName +'</td>'
                            html += '<td colspan="5">'
                            html += '<table cellspacing="0" cellpadding="0" id="secTable" style="width: 100%;margin-left: -1px"><tbody>'
                            data.items.map(function(object){
                                html += '<tr>'
                                html += '<td title="'+ handleUndefined(object.info.goodsInfoName) +'">'+ handleUndefined(object.info.goodsInfoName) +'</td>'
                                html += '<td title="'+ handleUndefined(object.info.specString) +'">'+ handleUndefined(object.info.specString) +'</td>'
                                html += '<td title="' +handleUndefined(object.info.goodsInfoItemNo)  + '">'+ handleUndefined(object.info.goodsInfoItemNo) +'</td>'
                                html += '<td>' + handleUndefined(object.goodsInfoPack) + ' ' + handleUndefined(object.goodsInfoUnit) + '/箱' + '</td>'
                                html += '<td>' + handleUndefined(object.checkedAmount) + '</td>'
                                html += '</tr>'
                            })
                            html += '</tbody></table>'
                            html += '</td>'
                            html += '</tr>'
                        })
//                        var html2 = ""
//                        html2 += '<tr>'
//                        html2 += '<th>仓库名称</th>'
//                        html2 += '<th colspan="6">'
//                        html2 += '<table style="width: 100%"><thead>'
//                        html2 += '<tr>'
//                        html2 += '<th>货品名称</th>'
//                        html2 += '<th>货品规格</th>'
//                        html2 += '<th>货品编号</th>'
//                        html2 += '<th>装箱数</th>'
//                        html2 += '<th>库存数量</th>'
//                        html2 += '<th>审核数量</th>'
//                        html2 += '</tr>'
//                        html2 += '</thead></table>'
//                        html2 += '</th>'
//                        html2 += '</tr>'
//                        $("#itemUnitShowHead").empty().append(html2)
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
                        $(".removeInvent").remove()
                        type = 1
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json');
            }else{
                $.post('../web/api/inventory/getMergeBillByBillIds',{
                    flag:1,
                    billIds:list
                },function(data){
                    if(data.response == "success"){
                        $("#change").val("查看明细")
                        $("#changhead").html("序号")
                        $("#headbody").html("汇总清单")
                        var html = ""
                        var x = 0
                        data.data.map(function(object){
                            x =  x + 1
                            html += '<tr>'
                            html += '<td>'+ x +'</td>'
                            html += '<td>' + handleUndefined(object.items[0].info.goodsInfoName) + '</td>'
                            html += '<td>' + handleUndefined(object.items[0].info.specString) + '</td>'
                            html += '<td>' + handleUndefined(object.items[0].info.goodsInfoItemNo) + '</td>'
                            html += '<td>' + handleUndefined(object.items[0].goodsInfoPack) + ' ' + handleUndefined(object.items[0].goodsInfoUnit) + '/箱' + '</td>'
                            html += '<td>' + handleUndefined(object.items[0].checkedAmount) + '</td>'
                            html += '</tr>'
                            html += '</tr>'
                        })
                        $("#itemUnitShow").empty().append(html)
                        type = 0
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json');
            }
        })

        $(".stop").click(function(){
            var reason = $(".reasonForApply").val()
                $.post('../web/api/inventory/createMergeBill',{
                    status:"TERMINATED",
                    reason:reason,
                    billIds:list
                },function(data){
                    if(data.response == "success"){
                        response_ensure_alert("success","终止成功",function(){
                            localStorage['systemAction'] = 'refresh';
                            window.close()
                        })
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json');
        })


        $(".nodoupt").click(function(){
            var reason = $(".reasonForApply").val()
                $.post('../web/api/inventory/createMergeBill',{
                    status:"FINISHED",
                    reason:reason,
                    billIds:list
                },function(data){
                    if(data.response == "success"){
                        var parentId = data.data
                        var okArr = [];
                        okArr[0] = '生成采购订单';
                        okArr[1] = function(){
                            discoverHtml();
                            $.post('../web/api/inventory/createPurchaseBill',{
                                parentId:parentId
                            },function(data){
                                if(data.response == "success"){
                                    response_ensure_alert("success","采购订单已生成，您可在单据管理-采购订单中查看",function(){
                                        localStorage['systemAction'] = 'refresh';
                                        window.close()
                                    })

                                }else{
                                    response_ensure_alert("error",data.data.text)
                                }

                            },'json');

                        };
                        var cancelArr = [];
                        cancelArr[0] = '下次再创建';
                        cancelArr[1] = function(){
                            discoverHtml();
                            localStorage['systemAction'] = 'refresh';
                            window.close();
                        };
                        selfPick_alert('success',"您已审批同意，请及时进行采购操作",okArr,cancelArr);
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },'json');

        })
    })
</script>