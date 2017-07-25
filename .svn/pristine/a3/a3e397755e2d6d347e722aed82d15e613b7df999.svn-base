<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1333/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/pouchdb.min.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
<!-- 主要样式 -->
<link rel="stylesheet" href="${bath}/static/css/style.css">
<style type="text/css">
    @media screen and ( max-width: 1400px){
        #memberBill{
            transform: scale(0.85,0.85);
            transform-origin:top;
            -webkit-transform: scale(0.85,0.85);
            -webkit-transform-origin:top;
            -ms-transform: scale(0.85,0.85);
            -ms-transform-origin:top;
            -moz-transform: scale(0.85,0.85);
            -moz-transform-origin:top;
            font-size: 16px!important;
        }
    }
    #countDetail{
        width: 300px;
        height: 200px;
        background: #fff;
        position: absolute;
        right: 0px;
        top: 30px;
        box-shadow: 0px 1px 3px 0px #ccc;
        display: none;

    }
    body{
        width: 100%;
        background: #fff;
    }
    #countDetail li{
        margin-top: 20px;
    }
    #countDetail li abbr{
        display:inline-block;
        vertical-align: top;
        margin-top: 10px;
        width: 150px;
        color: #333;
        height: 20px;
    }
    #countDetail li abbr:nth-child(3){
        position: relative;
        left: 125px;
        top: -60px;
    }

    #countDetail li img{
        margin-left: 20px;
        box-shadow: 0px 0px 3px 0px #ccc;
    }
    #logout{
        width: 120px;
        height: 35px;
        background: #54a6de;
        color: #fff;
        margin-left: 90px;
        margin-top: -10px;
        border:1px solid transparent;
    }
    #cardBody{
        width: 500px;
        margin: auto;
        margin-top: 200px;
    }
    #idCard{
        width: 350px;
        height: 50px;
        font-size: 24px;
        border-radius: 3px;
        border: 1px solid #e5e5e5;
        color: #333;
    }
    #confirm{
        width: 120px;
        height: 50px;
        border-radius: 3px;
        border: 1px solid #54a6de;
        color: #fff;
        background: #54a6de;
        margin-left: 20px;
    }
    ::-webkit-input-placeholder { /* WebKit browsers */
        color: #333;
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color: #333;
    }
    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color: #333;
    }
    :-ms-input-placeholder { /* Internet Explorer 10+ */
        color: #333;
    }
    #memberBill{
        font-size: 12px;
        width:1200px;
        height: 97%;
        margin: auto;
        z-index:2;
        background:#FFF;
        color: #333;
    }
    #memberBill div{
        width: 80%;
        margin-left: 100px;
    }
    #memberBill div li{
        margin: 10px 0px;
        height: 30px;
    }
    #memberBill div li abbr:nth-child(1){
        float: left;
    }
    #memberBill div li abbr:nth-child(2){
        float: right;
    }
    #memberBill h1 i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
    }

    .memberBillDetail{
        /*margin-left: 100px;*/
        margin-bottom: 20px;
        width: 1200px;
        color: #333;
        margin-top: 50px;
    }
    .memberBillDetail th, .memberBillDetail td{
        height: 40px;
        text-align: left;
        border-bottom: 1px solid #e5e5e5;
        color: #333;
        padding:15px;
    }
    .memberBillDetail th:nth-child(5), .memberBillDetail td:nth-child(5),.memberBillDetail th:nth-child(4), .memberBillDetail td:nth-child(4), .memberBillDetail th:nth-child(6),.memberBillDetail td:nth-child(6){
        text-align: right;
        padding-right: 30px;
    }
    .memberBillDetail th{
        font-weight: normal;
    }
    .userId{
        font-size: 26px;
        font-weight: bold;
    }
    .userMoney{
        font-size: 26px;
        font-weight: bold;
        color: #ff3300;
    }
    .checkDetail{
        color: #54a6de!important;
        cursor: pointer;
    }
    #itemDetail{
        width: 800px;
        height: 600px;
        position: fixed;
        left: 10%;
        top: 10%;
        z-index: 3;
        background: #fff;
        display: none;
    }
    #itemDetail th, #itemDetail td{
        font-weight:normal ;
        border-bottom: 1px solid #e5e5e5;
        height: 50px;
    }
</style>

<title>邮豆</title>
</head>

<body style="overflow-y: hidden;">


<div class="topNews">
    <span style="display: inline-block; vertical-align: middle;width: 200px; height: 54px; line-height: 54px; font-size: 24px;margin-left: 50px;">邮豆系统 V ${VERSION}
</span>
    <div class="spanright">

    <#--消息模式-->
	<#--<span class="news"><img style="margin-right:10px; width:25px; height:25px;" src="${bath}/static/img/ring.png" width="20" height="20" />消息<i>1</i>-->
    <#--</span>-->

    <span class="users">会员邮豆查询
    </span>
    </div>
    <div id="memberBill">
        <div style=" margin-top: 30px;">
            <ul>
                <li><abbr class="userId">${idCard}</abbr><abbr>本网点邮豆余额</abbr></li>
                <li><abbr class="userName">张三 11111111</abbr><abbr><span class="userMoney"></span>邮豆</abbr></li>
            </ul>
        </div>
        <table class="memberBillDetail" cellpadding="0" cellspacing="0">
            <thead>
            <tr bgcolor="#edf3f8">
                <th>交易时间</th>
                <th>交易对象</th>
                <th>交易理由</th>
                <th>交易金额</th>
                <th>邮豆余额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer3">

            </tbody>
        </table>
        <div id="holder3" class="allcpageTurnButton"></div>
    </div>
    <input type="hidden" value="${enterpriseInfo.enterpriseId}" class="enterpriseIdChoosen">
    <input type="hidden" value="${idCard}" class="idCard">
</div>
<div id="itemDetail" class="allpop">
    <h1>商品详情<i style="background:url(${bath}/static/img/XX.png) center no-repeat;"></i></h1>
    <div style="margin-top: 20px;height:500px; width: 100%;overflow-y: scroll;overflow-x: hidden">
        <table style="font-size: 16px;width: 100%;text-align: center;">
            <thead>
                <tr>
                    <th>图片</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>数量</th>
                </tr>
            </thead>
            <tbody id="contain">
                <tr>
                    <td>图片</td>
                    <td>商品名称</td>
                    <td>规格</td>
                    <td>数量</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script type="text/javascript">
    $(document).ready(function(){
        var array = []
        var idCard = $(".idCard").val()
        var enterpriseId = $(".enterpriseIdChoosen").val();
        $("#itemDetail").draggable()
        $.post("../web/api/valet/getCustomerInfo",{
            idCard:idCard
        },function(data){
            if(data.response=="success"){
                $(".userName").html(data.data.fullname + "  " + data.data.contactPhone )
                $(".userMoney").html(addCommas(data.data.enterpriseUcoin,2))
            }else{
                data_type_alert(data.data.text,"error")
            }
        },'json')
        array[15] = idCard;
        array[16] = enterpriseId
        ajaxPages("../web/api/customer/getCustomerBill","itemContainer3","holder3","memberBill",5,'','',array);
        $(document).on("click",".checkDetail",function(){
            coverHtml()
            var orderId = $(this).children("input").val()
            $("#itemDetail").show()
            if($(this).siblings(".checkType").html() == "邮豆消耗"){
                $.post("../web/api/ordermanage/getOrderGoods",{
                    orderId:orderId
                },function(data){
                    if(data.response=="success"){
                        var html = ""
                        data.data.orderGoodsList.map(function(o){
                            html += '<tr>'
                            html += '<td><img src="'+ o.goodsImg +'" height="40" width="40"></td>'
                            html += '<td>'+ o.goodsInfoName +'</td>'
                            html += '<td>'
                            o.goodsProductReleSpecVoList.map(function(oo){
                                if(oo.spec != undefined && oo.goodsSpecDetail != undefined){
                                    html +=  oo.spec.specName + ':' + oo.goodsSpecDetail.specDetailName + ","
                                }
                            })
                            if(html.charAt(html.length - 1) == ","){
                                html = html.substring(0,html.length - 1)
                            }
                            html += '</td>'
                            html += '<td>'+ o.goodsInfoNum +'</td>'
                            html += '</tr>'
                        })
                        $("#contain").empty().append(html)
                    }else{
                        data_type_alert(data.data.text,"error")
                    }
                },'json')
            }else if($(this).siblings(".checkType").html() == "邮豆退款"){
                $.post("../web/api/ordermanage/getBackOrderGoods",{
                    orderId:orderId
                },function(data){
                    if(data.response=="success"){
                        var html = ""
                        data.data.map(function(o){
                            html += '<tr>'
                            html += '<td><img src="'+ o.goodsInfoImage +'" height="40" width="40"></td>'
                            html += '<td>'+ o.goodsInfoName +'</td>'
                            html += '<td>'
                            o.goodsProductReleSpecVoList.map(function(oo){
                                if(oo.spec != undefined && oo.goodsSpecDetail != undefined){
                                    html +=  oo.spec.specName + ':' + oo.goodsSpecDetail.specDetailName + ","
                                }
                            })
                            if(html.charAt(html.length - 1) == ","){
                                html = html.substring(0,html.length - 1)
                            }
                            html += '</td>'
                            html += '<td>'+ o.goodsNum +'</td>'
                            html += '</tr>'
                        })
                        $("#contain").empty().append(html)
                    }else{
                        data_type_alert(data.data.text,"error")
                    }
                },'json')
            }
        })
        $("#itemDetail h1 i").click(function(){
            discoverHtml()
            $("#itemDetail").hide()
        })
    })
</script>
</html>
