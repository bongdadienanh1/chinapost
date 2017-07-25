<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <script src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script src="${bath}/static/js/enterpriseInfo.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px) {
          /*.reqbutton{*/
                /*transform: scale(0.9,0.9);*/
                /*transform-origin: left top;*/
                /*-webkit-transform: scale(0.9,0.9);*/
                /*-webkit-transform-origin: left top;*/
                /*-ms-transform: scale(0.9,0.9);*/
                /*-ms-transform-origin: left top;*/
                /*-moz-transform: scale(0.9,0.9);*/
                /*-moz-transform-origin: left top;*/
            /*}*/
            .requestTable{
                transform: scale(0.8,0.8);
                transform-origin: left top;
                -webkit-transform: scale(0.8,0.8);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.8,0.8);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.8,0.8);
                -moz-transform-origin: left top;
            }
            .reqbutton li:nth-child(4){
                width: 226px!important;
            }
        }
        .reqbutton{
            width:1250px;
            height:130px;
            margin-left:20px;
        }
        .reqbutton li{
            height:35px;
            float:left;
            margin-left:20px;
            margin-top:20px;
        }
        .reqbutton li abbr{
            display:inline-block;
            vertical-align:middle;
            width:35px;
            height:35px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        .reqbutton li i{
            display:inline-block;
            vertical-align:middle;
            width:35px;
            height:35px;
            background:url(${bath}/static/img/date_img.png) center no-repeat;
        }
        .reqbutton li input[type='button']:not(#reqsearch){
            width:60px;
            height:35px;
            border:1px solid #CCC;
            background:#fff;
            margin-right:10px;
        }


        .reqbutton li input[type='text']{
            width:205px;
            background:#FFF;
            font-size:16px;
        }

        .reqbutton li select{
            width:80px;
            margin-left: 20px;
            border:none;
            background:#FFF;
            color: #999;
            font-size:16px;
        }
        .reqbutton input[type="date"]{
            width:155px;

        }
        .reqbutton li:first-child{
            width:217px;
        }
        .reqbutton li:nth-child(4){
            width:216px;
        }
        .reqbutton li:nth-child(2){
            width:315px;
        }
        .reqbutton li:nth-child(3){
            width:315px;
        }
        .reqbutton li:nth-child(5){
            width:460px;
            border:1px solid transparent;
        }
        #reqsearch{
            margin-left:20px;
        }

        .requestTable{
            width:1600px;
            height:800px;
            margin-left:20px;
            margin-top:20px;
            color: #666666!important;
        }
        .requestTable ul{
            width:1600px;
            height:35px;
        }
        .requestTable ul li{
            width:85px;
            height:34px;
            border-bottom: 1px solid transparent;
            text-align:center;
            line-height:34px;
            margin-left:20px;
            float:left;
            cursor:pointer;
        }
        .requestTable ul li.reqOn{
            color:#54a6de;
            border-bottom: 2px solid #54a6de;
        }
        .myRequest{
            width:1600px;
            height:700px;
            margin-left:20px;
        }
        .myRequest dt{
            width:1600px;
            height:40px;
            border-bottom: 1px solid #e5e5e5;
            border-top: 1px solid #e5e5e5;
        }
        .myRequest dd{
            width:1600px;
            height:45px;
            background:#FFF;
            border-bottom: 1px solid #e5e5e5;
        }


        .myRequest dt abbr{
            display:inline-block;
            width:250px;
            height:40px;
            text-align:left;
            color:#666666;
            line-height:40px;
        }
        .myRequest dd abbr{
            display:inline-block;
            vertical-align: middle;
            width:250px;
            height:45px;
            text-align:left;
            line-height:45px;
            color:#333;
            vertical-align: middle;
            overflow: hidden;
        }

        .myRequestTodo{
            width:1600px;
            height:700px;
            margin-left:20px;
            display:none;
        }
        .myRequestTodo dt{
            width:1600px;
            height:40px;
            border-top: 1px solid #e5e5e5;
            border-bottom: 1px solid #e5e5e5;
        }
        .myRequestTodo dd{
            width:1600px;
            background:#FFF;
            border-bottom: 1px solid #e5e5e5;
        }

        .myRequestTodo dt abbr{
            display:inline-block;
            width:230px;
            text-align:left;
            color:#666666;
            line-height:40px;
        }
        .myRequestTodo dd abbr{
            display:inline-block;
            width:230px;
            height:45px;
            text-align:left;
            line-height:45px;
            color:#333;
            vertical-align: middle;
            overflow: hidden;
        }



        .myRequestDone{
            width:1600px;
            height:700px;
            margin-left:20px;
            display: none;
        }
        .myRequestDone dt{
            width:1600px;
            height:40px;
            border-top: 1px solid #e5e5e5;
            border-bottom: 1px solid #e5e5e5;
        }
        .myRequestDone dd{
            width:1600px;
            height:45px;
            background:#FFF;
            border-bottom: 1px solid #e5e5e5;
        }


        .myRequestDone dt abbr{
            display:inline-block;
            width:220px;
            height:40px;
            text-align:left;
            color:#666666;
            line-height:40px;
        }
        .myRequestDone dt abbr:nth-child(5),.myRequestDone dt abbr:nth-child(7),.myRequestDone dd abbr:nth-child(6),.myRequestDone dd abbr:nth-child(8){
            text-align:right;
            padding-right: 100px;
            width:120px;
        }
        .myRequestTodo dt abbr:nth-child(5),.myRequestTodo dd abbr:nth-child(6){
            text-align:right;
            padding-right: 100px;
            width:120px;
        }
        .myRequest dt abbr:nth-child(4),.myRequest dd abbr:nth-child(5), .myRequest dt abbr:nth-child(6),.myRequest dd abbr:nth-child(7){
            text-align:right;
            padding-right: 100px;
            width:120px;
        }
        .myRequestDone dd abbr{
            display:inline-block;
            width:220px;
            height:45px;
            text-align:left;
            line-height:45px;
            color:#333;
            overflow: hidden;
            vertical-align: middle;
        }
        .myRequestTodoSelect{
            width:100px;
            height:30px;
            background:#f8f8f8;
            border:1px solid #CCC;
            text-align:center;
            margin-top: 8px;
            font-size: 18px;
        }


        .agreeRequest{
            width:600px;
            height:350px;
            position:absolute;
            left:15%;
            top:30%;
            background:#FFF;
            display:none;
            z-index: 3;
        }
        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;

            cursor:pointer;
        }
        #agreeRequestConfirm{
            width:120px;
            position:relative;
            left:180px;
            top:40px;
        }
        #agreeRequestCancel{
            width:120px;
            position:relative;
            left:210px;
            top:40px;
        }


        .disAgreeRequest{
            width:600px;
            height:350px;
            position:absolute;
            left:15%;
            top:30%;
            background:#FFF;
            display:none;
            z-index: 3;
        }
        #xx1{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;

            cursor:pointer;
        }
        #disAgreeRequestConfirm{
            width:120px;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #disAgreeRequestCancel{
            width:120px;
            position:relative;
            left:210px;
            top:40px;
        }




        .AgreeRequestSure{
            width:600px;
            height:350px;
            position:absolute;
            left:15%;
            top:30%;
            background:#FFF;
            display:none;
            z-index:3;
        }
        #xx2{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;

            cursor:pointer;
        }
        #AgreeRequestSureConfirm{
            width:120px;
            position:relative;
            left:160px;
            top:40px;
        }
        #AgreeRequestSureCancel{
            width:120px;
            position:relative;
            left:180px;
            top:40px;
        }


        .payRequest{
            width:500px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;
            background:#FFF;
            display:none;
            z-index: 3;
        }
        #xx3{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #payRequestConfirm{
            position:relative;
            left:120px;
            top:20px;
        }
        #payRequestCancel{
            position:relative;
            left:150px;
            top:20px;
        }

        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            margin-left: 0!important;
            height:24px;
            background-image: url("${bath}/static/img/date_img.png");
            position: relative;
            left: -45px;
        }


        .xdsoft_datetimepicker{
            width:310px!important;
        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var isOrNotTop = ${isTop};
        var total_elements_myRequest = ${total_elements_myRequest};
        var total_elements_myRequest_todo = ${total_elements_myRequest_todo};
        var total_elements_myRequest_done = ${total_elements_myRequest_done};
        var isEnd = getCurrentEnterpriseInfo().getIsEnd();
        $(document).ready(function(e) {
            if(isEnd =="1"){
                $(".isTopShow").hide()
            }else{
                $(".isTopShow").show()
            }
            var staObj = {};
            $(".reqbutton select option").each(function(){
                if($(this).attr("value")!=""){
                    eval("staObj."+$(this).attr("value")+"='"+$(this).html()+"'");
                }

            });
            $(".requestTable li").click(function(){
                var lival=$(this).val();
                console.log(staObj);
                $(this).addClass("reqOn").siblings("li").removeClass("reqOn")
                if(lival==0){
                    $(".reqbutton select option").each(function(){
                        $(this).css("display","block");
                    });
                    $(".myRequest").show().siblings("dl").hide();
                    ajaxPages('../web/api/requisition/myReq','itemContainer','holder',4,5,'','',[]);
                }else if(lival==1){
                    $(".reqbutton select option").each(function (){
                        var staArr = ["PASSED","APPLIED"];
                        showSelect(staArr,staObj,$(this));
                    });
                    $(".myRequestTodo").show().siblings("dl").hide();
                    ajaxPages('../web/api/requisition/myNotHandleReq','item_container_request_todo','request_todo',5,5,'','',[]);
                }else if(lival==2){
                    $(".reqbutton select option").each(function(){
                        var staArr = ["PAYED","DENIED"];
                        showSelect(staArr,staObj,$(this));
                    });
                    $(".myRequestDone").show().siblings("dl").hide();
                    ajaxPages('../web/api/requisition/myHandledReq','item_container_request_done','holder_done',6,5,'','',[]);
                }















            });
            $("#agreeRequestCancel,#xx").click(function(){
                discoverHtml()
                $(".agreeRequest").fadeOut(500);
            })
            $("#disAgreeRequestCancel,#xx1").click(function(){
                discoverHtml()
                $(".disAgreeRequest").fadeOut(500);
            })
            $("#xx2").click(function(){
                discoverHtml()
                $(".AgreeRequestSure").fadeOut(500);
            })
            $("#AgreeRequestSureCancel,#xx2").click(function(){
                discoverHtml()
                window.location.href = "Requisition";
            })
            $("#payRequestCancel,#xx3").click(function(){
                discoverHtml()
                window.location.href = "Requisition";
            })

            $("#AgreeRequestSureConfirm").click(function(){
                $(".payRequest").fadeIn(500);
                $(".AgreeRequestSure").fadeOut(500);
            })
            $( ".agreeRequest" ).draggable();
            $( ".disAgreeRequest" ).draggable();
            $( ".payRequest" ).draggable();
            $( ".AgreeRequestSure" ).draggable();
            $(document).on("change",".myRequestTodoSelect",function(){
                var val=$(this).val();
                if(val==1){
                    coverHtml()
                    $(this).val(0)
                    $(".agreeRequest").fadeIn(500);
                }else if(val==2){
                    coverHtml()
                    $(this).val(0)
                    $(".disAgreeRequest").fadeIn(500);
                }else if(val==3){
                    coverHtml()
                    $(this).val(0)
                    $(".payRequest").fadeIn(500);
                }

            })

        });


        function showSelect(staArr,staObj,obj){
            var html ="";
            html += '<option value="" selected="selected">全部</option>';
            for(var i=0;i<staArr.length;i++){
                if(typeof (EnumaKey(staObj,staArr[i])) != "undefined"){
                    html += '<option value="'+EnumaKey(staObj,staArr[i])+'">'+eval('staObj.'+EnumaKey(staObj,staArr[i]))+'</option>';
                }
            }
            $(".reqbutton select").html(html);
            if( obj.html() == '全部' ){
                console.log(2);
                obj.prop("selected","selected");
            }
        }

        function EnumaKey(obj,sta){
            for(var key in obj){
                if(key == sta){
                    return key;
                }
            }
        }


    </script>

    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>请款单据</span><abbr></abbr>
    </div>
<div class="reqbutton">
    <ul>
        <li><input placeholder="单据编号" class="allinputButton" type="text"  name="reNo"/></li>
        <li>
            <input placeholder="开始时间" class="allinputButton" type="text" style="width:286px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
        </li>
        <li>
            <input placeholder="结束时间" class="allinputButton" type="text" style="width:286px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
        </li>
        <li style="color: #999" class="allinputButton"><span>单据状态</span>
            <select>
                    <option value="" selected="selected">全部</option>
                <#list statusMap?keys as index>
                    <option value="${index}" >${statusMap[index]}</option>
                </#list>
            </select>
        </li>

        <li style="width: 800px">
            <input type="button" value="今天" class="today allinputButton">
            <input type="button" value="本周" class="thisWeed allinputButton">
            <input type="button" value="上周" class="lastWeek allinputButton">
            <input type="button" value="本月" class="thisMonth allinputButton">
            <input type="button" value="上月" class="lastMonth allinputButton">
            <input type="button" value="今年" class="thisYear allinputButton">
            <input class="allseachButton" type="button" id="reqsearch" value="搜索" />
        </li>

    </ul>
</div>


<div class="requestTable">
    <ul>
        <#if isTop == 'true'>
            <li value="1" class="reqOn isTopShow">我的待办</li>
            <script type="javascript"></script>
        </#if>
        <#if isTop == 'false'>
            <li value="0" class="reqOn">我的请求</li>
            <li class="isTopShow" value="1">我的待办</li>
        </#if>
        <li class="isTopShow" value="2">我的已办</li>
    </ul>
    <#if isTop == 'true'>
    </#if>
    <#if isTop == 'false'>
        <dl class="myRequest">
            <dt><abbr>创建时间</abbr><abbr>单据编号</abbr><abbr>单据状态</abbr><abbr>申请金额</abbr><abbr>申请原因</abbr><abbr>支付金额</abbr>
            </dt>
            <div id="itemContainer"></div>
            <div style="margin-top: 30px" class="allcpageTurnButton" id="holder"></div>

        </dl>
    </#if>
    <dl class="myRequestTodo">
        <dt><abbr style="width:180px;">申请人</abbr><abbr>创建时间</abbr><abbr>单据编号</abbr><abbr>单据状态</abbr><abbr>单据金额</abbr><abbr>申请原因</abbr><abbr>操作</abbr>
        </dt>
        <div id="item_container_request_todo"></div>
        <div style="margin-top: 30px" class="allcpageTurnButton" id="request_todo"></div>
    </dl>



    <dl class="myRequestDone">
        <dt><abbr>申请人</abbr><abbr>创建时间</abbr><abbr>单据编号</abbr><abbr>单据状态</abbr><abbr>申请金额</abbr><abbr>申请原因</abbr><abbr>支付金额</abbr>
        </dt>

        <div id="item_container_request_done"></div>
        <div style="margin-top: 30px" class="allcpageTurnButton" id="holder_done"></div>
    </dl>

</div>


<div class="agreeRequest allpop">
    <h1>操作提示<i id="xx"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">您确定审核通过吗？</span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="agreeRequestConfirm" value="确定" />
            <input class="allcancelButton" type="button" id="agreeRequestCancel" value="取消" />
        </li>
    </ul>
</div>
<div class="disAgreeRequest allpop">
    <h1>操作提示<i id="xx1"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">您确定拒绝该请款单吗？</span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="disAgreeRequestConfirm" value="确定" />
            <input class="allcancelButton" type="button" id="disAgreeRequestCancel" value="取消" />
        </li>
    </ul>
</div>
<div class="AgreeRequestSure allpop">
    <h1>系统提示<i id="xx2"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">单据审核通过</span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="AgreeRequestSureConfirm" value="立即支付" />
            <input class="allcancelButton" type="button" id="AgreeRequestSureCancel" value="返回待办列表" />
        </li>
    </ul>
</div>
<div class="payRequest allpop">
    <h1>支付密码<i id="xx3"></i></h1>
    <ul>
        <li style="height:70px; line-height:70px;">
            <span style="padding-left:50px; font-size:16px;"><i style="color:#ff3300;">*</i>支付金额</span><input maxlength="12" id="payMoney" style="width:250px; height:30px; margin-left:20px;" type="text" />
        </li>
        <li style="height:70px; line-height:70px;">
            <span style="padding-left:50px; font-size:16px;"><i style="color:#ff3300;">*</i>支付密码</span><input style="width:250px; height:30px; margin-left:20px;" type="password" />
        </li>
        <li style="height:80px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="payRequestConfirm" value="支付" />
            <input class="allcancelButton" type="button" id="payRequestCancel" value="取消" />
        </li>
    </ul>
</div>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/requisition.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>


<script type="text/javascript">
    sessionStorage.setItem("ShoppingCart",'');
    $(document).ready(function(){
        enterSearch( $("input[name='reNo']"),$("#reqsearch"))
    $(".today").click(function(){
            var date = new Date();
            var seperator1 = "/";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
        if(strDate<10){
            strDate="0"+strDate

        }
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }

            var todaybegin = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + "00" + seperator2 + "00"
                    + seperator2 + "00"
            var todayover = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + "23" + seperator2 + "59"
                + seperator2 + "59"
        $("#datetimepicker_start").val(todaybegin);
        $("#datetimepicker_end").val(todayover);
    })
        $(".thisWeed").click(function() {
                var date = new Date();
                var year=date.getFullYear()
                var seperator1 = "/";
                var seperator2 = ":";
                var month = date.getMonth() + 1;
                var month2=month
                var strDate = date.getDate();

                var d=new Date()
                var day=new Array(7)
                day[0]="7"
                day[1]="1"
                day[2]="2"
                day[3]="3"
                day[4]="4"
                day[5]="5"
                day[6]="6"
                    strDate=strDate-(day[d.getDay()]-1)
                    strDate2=strDate+6
            if(strDate<0){
                newDate=0-strDate
                if( month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                    strDate=30-newDate
                    month=month-1
                }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                    strDate = 29 - newDate
                    month=month-1
                }else if(month=="2"){
                    if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                        strDate = 28 - newDate
                        month=month-1
                    }else{
                        strDate = 27 - newDate
                        month=month-1
                    }
                }else if(month=="1"){
                    year=year-1
                    strDate=30-newDate
                    month=12
                }
            }
            if(strDate2>31){
                if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                    strDate2=strDate2-31
                    month2=month2+1
                }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                    strDate2=strDate2-30
                    month2=month2+1
                }else if(month=="2"){
                    if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                        strDate2=strDate2-29
                        month2=month2+1
                    }else{
                        strDate2=strDate2-28
                        month2=month2+1
                    }
                }
            }
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (month2 >= 1 && month2 <= 9) {
                month2 = "0" + month2;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }

                    if(strDate2<10){
                        strDate2="0"+strDate2
                    }
                    var todaybegin = year + seperator1 + month + seperator1 + strDate
                            + " " + "00" + seperator2 + "00"
                            + seperator2 + "00"
                    var todayover = date.getFullYear() + seperator1 + month2 + seperator1 + strDate2
                            + " " + "23" + seperator2 + "59"
                            + seperator2 + "59"
                    $("#datetimepicker_start").val(todaybegin);
                    $("#datetimepicker_end").val(todayover);
        })


        $(".lastWeek").click(function() {
            var date = new Date();
            var lastWeekStartSecond = date.getTime() - 604800000;
            var date = new Date(lastWeekStartSecond);
            var year=date.getFullYear();
            var seperator1 = "/";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var month2=month
            var strDate = date.getDate();

            var d=new Date()
            var day=new Array(7)
            day[0]="7"
            day[1]="1"
            day[2]="2"
            day[3]="3"
            day[4]="4"
            day[5]="5"
            day[6]="6"
            strDate=strDate-(day[d.getDay()]-1)
            strDate2=strDate+6
            if(strDate<0){
                newDate=0-strDate
                if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                    strDate=30-newDate
                    month=month-1
                }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                    strDate = 29 - newDate
                    month=month-1
                }else if(month=="2"){
                    if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                        strDate = 28 - newDate
                        month=month-1
                    }else{
                        strDate = 27 - newDate
                        month=month-1
                    }
                }
            }
            if(strDate2>31){
                if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                    strDate2=strDate2-31
                    month2=month2+1
                }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                    strDate2=strDate2-30
                    month2=month2+1
                }else if(month=="2"){
                        if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                            strDate2=strDate2-29
                            month2=month2+1
                        }else{
                            strDate2=strDate2-28
                            month2=month2+1
                        }
                }
            }
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (month2 >= 1 && month2 <= 9) {
                month2 = "0" + month2;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            if(strDate2<10){
                strDate2="0"+strDate2
            }
            var todaybegin = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + "00" + seperator2 + "00"
                    + seperator2 + "00"
            var todayover = date.getFullYear() + seperator1 + month2 + seperator1 + strDate2
                    + " " + "23" + seperator2 + "59"
                    + seperator2 + "59"
            $("#datetimepicker_start").val(todaybegin);
            $("#datetimepicker_end").val(todayover);
        })


        $(".thisMonth").click(function() {
            var date = new Date();
            var seperator1 = "/";
            var seperator2 = ":";
            var year=date.getFullYear();
            var month = date.getMonth() + 1;
            if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                strDate=31
            }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                strDate=30
            }else if(month=="2"){
                if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                    strDate=29
                }else{
                    strDate=28
                }
            }
            if(strDate<10){
                strDate="0"+strDate

            }
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }

            var todaybegin = date.getFullYear() + seperator1 + month + seperator1 + "01"
                    + " " + "00" + seperator2 + "00"
                    + seperator2 + "00"
            var todayover = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + "23" + seperator2 + "59"
                    + seperator2 + "59"
            $("#datetimepicker_start").val(todaybegin);
            $("#datetimepicker_end").val(todayover);


        })



        $(".lastMonth").click(function() {
            var date = new Date();
            var seperator1 = "/";
            var seperator2 = ":";
            var year=date.getFullYear();
            var month = date.getMonth();
            if(month=="0"){
                month=12;
                year=year-1
            }
            if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                strDate=31
            }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                strDate=30
            }else if(month=="2"){
                if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                    strDate=29
                }else{
                    strDate=28
                }
            }
            if(strDate<10){
                strDate="0"+strDate

            }
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }

            var todaybegin = year + seperator1 + month + seperator1 + "01"
                    + " " + "00" + seperator2 + "00"
                    + seperator2 + "00"
            var todayover = year + seperator1 + month + seperator1 + strDate
                    + " " + "23" + seperator2 + "59"
                    + seperator2 + "59"
            $("#datetimepicker_start").val(todaybegin);
            $("#datetimepicker_end").val(todayover);


        })
        $(".thisYear").click(function() {
            var date = new Date();
            var seperator1 = "/";
            var seperator2 = ":";
            var year=date.getFullYear();
            var todaybegin = year + seperator1 + "01" + seperator1 + "01"
                    + " " + "00" + seperator2 + "00"
                    + seperator2 + "00"
            var todayover = year + seperator1 + "12" + seperator1 + "31"
                    + " " + "23" + seperator2 + "59"
                    + seperator2 + "59"
            $("#datetimepicker_start").val(todaybegin);
            $("#datetimepicker_end").val(todayover);


        })
    })
</script>
<#if isTop == 'true'>
    <script type="text/javascript">
        $(document).ready(function(){
            console.log($(".requestTable li[value='1']"));
            $(".requestTable li[value='1']").trigger("click");
        })
    </script>
</#if>
<#if isTop == 'false'>

</#if>
</html>
