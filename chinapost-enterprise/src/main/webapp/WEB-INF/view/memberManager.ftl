<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/enterpriseInfo.js"></script>
    <script src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1400px){
            .memberDetail,.memberList{
                transform: scale(0.75,0.75);
                transform-origin: left top;
                -webkit-transform: scale(0.75,0.75);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.75,0.75);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.75,0.75);
                -moz-transform-origin: left top;
            }
            #userDetailListReduce,#memberBill,.resetPassword{
                transform: scale(0.9,0.9);
                transform-origin: left top;
                -webkit-transform: scale(0.9,0.9);
                -webkit-transform-origin: left top;
                -ms-transform: scale(0.9,0.9);
                -ms-transform-origin: left top;
                -moz-transform: scale(0.9,0.9);
                -moz-transform-origin: left top;
                font-size: 16px!important;
            }
            #memberOut{
                width: 120px!important;
                line-height: 24px!important;
            }
            .select2 .allSelectButton {
                font-size: 20px!important;
            }
        }
        a{
            color: #54a6de;
        }
        .memberSearch{
            width:1220px;
            height:150px;
            margin-left:20px;
        }
        .memberSearch li{
            width:250px;
            height:26px;
            float:left;
            color: #999;
            margin-top: 20px;
            border-radius: 20px!important;
        }
        .memberSearch li input{
            width:120px;
            height:32px;
            margin-left: -6px;
            margin-top: -1px;
        }
        .memberSearch li dl{
            display: inline-block;
            vertical-align: middle;
            width:120px;
            height:24px;
            margin-left:5px;
            text-align: center;
            position: relative;
        }
        .arrow{
            display: inline-block;
            position: absolute;
            right: 0px;
            top: -5px;
            width: 136px;
            height: 32px;
            background:url(${bath}/static/img/com_btn_arrow_black_down.png) right center no-repeat;
        }

        .memberSearch li dl dd,.memberSearch li dl dt{
            width:120px;
            height:22px;
            margin-left:-1px;
            cursor: pointer;
        }
        .memberSearch li dl dd{
            display: none;
        }
        .memberSearch li dl dd:hover{
            color: #54a6de;
            color: #fff;
        }
        .memberSearch li dl dd:active{
            color: #fff;
            background: #54a6de;
        }
        .memberSearch li select{
            height: 38px;
            background: #f8f8f8;
        }

        #accSearch{
            width:120px;
            margin-left: 10px;
        }

        #accExport{
            width:110px;
            height:37px;
            color:#FFF;
            font-size: 15px;  39e;
            margin-left:30px;
        }

        .memberList{
            width:1600px;
            height:800px;
            overflow-y:auto;
            margin-left:20px;
            margin-top:20px;
            color: #666666;
        }
        .memberList dt{
            width:1600px;
            height:36px;
            border-bottom:1px solid #e5e5e5;
            color:#666666;
        }
        .memberList dt abbr{
            display:inline-block;
            width:90px;
            height:36px;
            line-height: 36px;
            text-align:left;
        }
        .memberList dd{
            width:1600px;
            height:79px;
            background:#fff;
            border-bottom:1px solid #e5e5e5;
        }
        .memberList dd abbr{
            display:inline-block;
            text-align:left;
            width:90px;
            height:80px;
            line-height: 80px;
            vertical-align:middle;
            white-space: nowrap;
            overflow: hidden;

        }
        .memberList dd abbr ul{
            display: inline-block;
            vertical-align: middle;
            width: 116px;
            text-align: center;
            position: relative;
            top: 23px;
        }

        .arrow2{
            display: inline-block;
            position: absolute;
            right: -10px;
            top:0px;
            width: 20px;
            height: 30px;
            background:url(${bath}/static/img/com_btn_arrow_bg_down.png) center no-repeat;
            cursor: pointer;
        }
        .memberList dd abbr ul.select2 section{
            width: 125px;
            height: 30px;
            line-height: 30px;
            cursor: pointer;
            display: none;
            z-index: 3;
        }
        .memberList dd abbr ul.select2 li.checkDateil{
            width: 125px;
            height: 30px;
            line-height: 30px;
            cursor: pointer;
        }
        .memSelects{
            width: 125px;
            height: 32px;
            color: #fff;
            margin-left: 60px;
            margin-top: -10px;
        }
        .memSelects option{
            background: #fff;
            color: #333;
        }
        .newMember{
            font-size: 12px;
            display:none;
            width:700px;
            position:fixed;
            left:15%;
            top:15%;
            z-index:2;
            background:#FFF;
        }
        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }

        #newMenberConfirm{
            width:160px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#54a6de;
            margin-left: 120px;
            color:#FFF;
        }

        #newMenberCancel{
            width:160px;
            height:30px;
            border-style:none;
            border:1px solid #999;
            margin-left: 120px;
            color: #999;
            background: #fff;
        }

        .newMemberDetail{
            width:700px;
        }
        .newMemberDetail li{
            width:650px;
            margin:20px 0px 20px 20px;
        }
        .newMemberDetail li span{
            display:inline-block;
            width:100px;
            text-align:right;
        }
        .newMemberDetail li span i{
            color:#ff3300;
        }
        .newMemberDetail li input{
            width:200px;
            height:30px;
            margin-left:40px;
            border-radius: 5px;
            background: #fff;
        }
        .newMemberDetail li select{
            width:120px;
            height:32px;
            margin-left:5px;
            border-radius: 5px;
            background: #fff;
        }
        .newMemberDetail li textarea{
            border: 1px solid #ccc;
            border-radius: 5px;
            background: #fff;
        }



        .memberDetail{
            width:900px;
            height:auto;
            position:fixed;
            left:15%;
            top:15%;
            z-index:2;
            background:#FFF;
            display:none;
        }
        .memberDetail input{
            border-style:none;
            background:#FFF;
        }

        #xx1{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        .memberDetail dl{
            width:800px;
            height:auto;
            overflow:hidden;
            margin-left:50px;
            margin-top:20px;
            margin-bottom:50px;
        }
        .memberDetail dl dt{
            width:800px;
            height:50px;
            color:#000;
            border-bottom:1px solid #f2f2f2;
            background:#FFF;
            font-weight: bold;
        }
        .memberDetail dl dd{
            height:auto;
            overflow:hidden;
            border-style:none;
        }
        .memberDetail dl dd ul{
            width:600px;
        }

        .memberDetail dl dd ul li{
            width:280px;
            height:30px;
            margin:5px 0px;
        }
        .memberDetail dl dd ul li span{
            display:inline-block;
            width:90px;
            white-space: nowrap;
        }
        .memberDetail dl dd ul li input{
            width:180px;
            margin-left:10px;
        }
        #accoutDetail{
            width:700px;
            padding-left:20px;
            padding-top:20px;
        }
        #accoutDetail li{
            width:350px;
        }
        #accoutDetail li span{
            display: inline-block;
            vertical-align: middle;
            width:120px;
        }
        #accoutDetail li input{
            width:200px;
        }
        .memberDetail dl dd ul.receiverDetail{
            margin-left:20px;
            margin-top:20px;
        }
        .memberDetail dl dd ul.receiverDetail li{
            width:300px;
            float:left;
        }
        .memberDetail dl dd ul.receiverDetail input{
            width:200px;
        }
        #checkRecever{
            display:inline-block;
            height:30px;
            position:relative;
            left:300px;
            top:10px;
            cursor:pointer;
        }
        #updateMember{
            width:60px;
            height:32px;
            background:#54a6de;
            color:#FFF;
            position:relative;
            left:600px;
            cursor:pointer;
        }

        #updateUcoin{
            background:#54a6de;
            width: 100px;
            height: 32px;
            color:#FFF;
            position:relative;
            left:50px;
            cursor:pointer;
        }

        #UcoinSend{
            background:#54a6de;
            width: 100px;
            height: 32px;
            color:#FFF;
            position:relative;
            left:50px;
            cursor:pointer;
        }

        .updateMember{
            font-size: 12px;
            width:700px;
            position:fixed;
            left:15%;
            top:15%;
            z-index:2;
            background:#FFF;
            display:none;
        }
        #xx2{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        #updateMemberConfirm{
            width:160px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#54a6de;
            margin-left: 120px;
            color:#FFF;
        }

        #updateMemberCancel{
            width:160px;
            height:30px;
            border-style:none;
            border:1px solid #999;
            margin-left: 120px;
            color: #999;
            background: #fff;
        }
        .updateMemberDetail{
            width:700px;
        }
        .updateMemberDetail li{
            width:650px;
            margin:20px 0px 20px 20px;
        }
        .updateMemberDetail li span{
            display:inline-block;
            width:100px;
            text-align:right;
        }
        .updateMemberDetail li span i{
            color:#ff3300;
        }
        .updateMemberDetail li input{
            width:200px;
            height:30px;
            margin-left:40px;
            border:1px solid #CCC;
            border-radius: 5px;
            background: #fff;
        }
        .updateMemberDetail li textarea{
            border:1px solid #CCC;
            border-radius: 5px;
            background: #fff;
        }
        .updateMemberDetail li textarea:hover {  88e;

        }
        .updateMemberDetail li input:hover {  88e;

        }
        .updateMemberDetail li select{
            width:120px;
            height:32px;
            margin-left:5px;
            border:1px solid #CCC;
            border-radius: 5px;
            background: #fff;
        }

        .resetPassword{
            display:block;
            width:600px;
            height:290px;
            position:fixed;
            left:15%;
            top:30%;
            background:#FFF;
            z-index: 2;
            display: none;

        }
        #xx3{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #resetPasswordConfirm{
            position:relative;
            left:180px;
            top:20px;
        }
        #resetPasswordCancel{
            position:relative;
            left:210px;
            top:20px;
        }


        .activeAccout{
            display:none;
            width:600px;
            height:350px;
            position:fixed;
            left:15%;
            top:30%;  CC;
            background:#FFF;
            z-index: 2;
        }
        #xx4{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #activeAccoutConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#54a6de;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #activeAccoutCancel{
            width:80px;
            height:30px;
            border-style:none;
            color: #999;
            border:1px solid #999;
            background:#f2f2f2;
            position:relative;
            left:210px;
            top:40px;
        }



        .success{
            width:600px;
            height:450px;
            background:#FFF;
            position:fixed;
            left:15%;
            top:30%;
            z-index:2;
            text-align:center;
            line-height:200px;
            display:none;

        }
        .success span{
            display:inline-block;
            height:32px;
            color:#000;
            font-size:20px;
            margin-top:50px;
        }
        .success span img{
            margin-right:30px;
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
            background:#2087fc;
            margin-top:200px;
            z-index: 2;
        }

        .ucoinReduce{
            width: 680px;
            height: 670px;
            background:#fff;
            position: absolute;
            left: 15%;
            top: 30%;
            z-index: 40;
            display: none;
        }
        .ucoinReduce ul {
            margin-top: 15px;

        }
        .ucoinReduce ul i{
            color: #ff3300;
        }
        .ucoinReduce li{
            width: 650px;
            height: 80px;
            margin-left: 15px;
        }
        .ucoinReduce li input{
            width: 420px;
            height: 50px;
            border:1px solid #333;
            border-radius: 5px;
            background: #fff;
        }

        .ucoinReduce li span{
            display: inline-block;
            vertical-align: middle;
            width: 120px;
            height: 80px;
            line-height: 80px;
        }
        .ucoinReduce li:first-child{
            height: 200px;
            position: relative;
        }
        .ucoinReduce li:first-child span{
            height: 200px;
        }
        .ucoinReduce h1{
            width: 680px;
            height: 70px;
            line-height: 70px;
            font-size: 24px;
            border-bottom: 1px solid #ccc;
        }
        #UCleft{
            width: 160px;
            height: 200px;
            position: absolute;
            left: 120px;
            top: 0px;
        }
        #UCright{
            width: 370px;
            height: 200px;
            position: absolute;
            left: 280px;
            top: 0px;
            font-size: 12px!important;
        }
        .ucoinReduce input{
            border-style: none;
        }
        #UCright abbr{
            display: inline-block;
            vertical-align: middle;
            width: 370px;
            height: 35px;
        }
        #UCright abbr section{
            display: inline-block;
            vertical-align: middle;
        }
        #UCright abbr input{
            width: 220px;
            border-style: none;
            height: 20px;
        }
        #xx5{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #ucoinReduceConfrim{
            margin-left: 135px;
            margin-top: 20px;
        }
        #ucoinReduceCancel{
            margin-left: 100px;
            margin-top: 20px;

        }
        #holder{
            margin-top: 20px;

        }


        .tagLiDetail{
            width:200px!important;
            height:230px!important;
            position: absolute;
            right: 80px;
            top: 5px;
        }
        .tag{
            width:20px!important;
        }
        .memTag{
            width:200px!important;
            padding-top:20px!important;
            line-height: 20px!important;
            overflow: visible;
            padding-left:0px!important;
            white-space: normal!important;
        }
        .memTab{
            width:auto!important;
            height:10px!important;
            font-size:12px;
            line-height: 20px!important;
            padding-left:0px!important;
            display: inline!important;
            white-space: normal!important;
        }
        .tagSpan{
            width: 230px!important;
            vertical-align: middle;
            text-align: left!important;
        }
        .infosShow{
            overflow-y: scroll;
            overflow-x: hidden;
            min-height: 150px;
            max-height: 600px;
            margin-top: 30px;
        }
        #userDetailListReduce{
            font-size: 12px;
            width:1200px;
            position:fixed;
            left:15%;
            top:15%;
            z-index:2;
            background:#FFF;
            display: none;
        }
        #userDetailListReduce h1 i{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        #memberBill{
            font-size: 12px;
            width:1200px;
            position:fixed;
            left:15%;
            top:15%;
            z-index:2;
            background:#FFF;
            display: none;
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
        #holder3{
            margin-left: 100px;
            margin-bottom: 20px;
            width: 1000px;
        }
        .memberBillDetail{
            margin-left: 100px;
            margin-bottom: 20px;
            width: 1000px;
        }
        .memberBillDetail th, .memberBillDetail td{
            height: 40px;
            text-align: left;
            border-bottom: 1px solid #e5e5e5;
        }
        #holder2{
            margin-left: 100px;
            margin-bottom: 20px;
            width: 1000px;
        }
        .table_listReduceDetail{
            margin-left: 100px;
            margin-bottom: 20px;
            width: 1000px;
        }
        .table_listReduceDetail th, .table_listReduceDetail td{
            height: 40px;
            text-align: left;
            border-bottom: 1px solid #e5e5e5;
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
        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            height:24px;
            background-color: #f2f2f2;
            margin-left: -45px;
            background: url("${bath}/static/img/date_img.png") no-repeat center;

        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var enterprise = getCurrentEnterpriseInfo();
        var isEnd = ( ${isEnd} == 1 ) ? true : false;
        var isTop = false;
        var flag = ( ${isTop} == 1 ) ? true : false;
        var eid = enterprise.getEnterpriseId();
        var ename = enterprise.getEnterpriseName();
        sessionStorage.setItem("ShoppingCart",'');
        var tagList = '';
        var array1 = [];
        array1[15] = '';//idCard
        array1[8] = '';//start
        array1[9] = '';//end
        array1[16] = ""
        var array2 = [];
        array2[15] = '';//idCard
        array2[16] = ""
        var excel_idCard = "";
        $(document).ready(function(e) {
            $( ".newMember" ).draggable();
            $( ".memberDetail" ).draggable();
            $( ".updateMember" ).draggable();
            $( ".ucoinReduce" ).draggable();
            $( ".activeAccout" ).draggable();
            $( ".resetPassword" ).draggable();
            $("#userDetailListReduce").draggable();
            $("#memberBill").draggable();
            $("#itemDetail").draggable()
            enterSearch( $("input[name='memberNum'],input[name='memberName'],input[name='memberPhone'],input[name='memberUserName']"),$("#accSearch"))
            //仓库列表
            inventoryList(eid,ename);
            $("#userDetailListReduce h1 i").click(function(){
                discoverHtml();
                $("#userDetailListReduce").hide()
            });
            $("#memberBill h1 i").click(function(){
                discoverHtml();
                $("#memberBill").hide()
            });

            $(document).on("click",".orderHistory",function(){
                coverHtml()
                var idCard = $(this).parent().parent().siblings(".memId").html();
                var start =""
                var end = ""
                var enterpriseId = $(".enterpriseIdChoosen").val();
                console.log(idCard)
                array1[15] = idCard;
                excel_idCard = idCard;
                array1[16] = enterpriseId
                ajaxPages("../web/api/report/getDetailConsumeNew","itemContainer2","holder2","memberUbaoReduceFormDetailNew",10,'','',array1);
                $("#userDetailListReduce").fadeIn(500);
            });

            $(document).on("click",".memberOrder",function(){
                coverHtml()
                var idCard = $(this).parent().parent().siblings(".memId").html();
                var enterpriseId = $(".enterpriseIdChoosen").val();
                var userName = $(this).parent().parent().siblings(".memfullname").html();
                var userPhone = $(this).parent().parent().siblings(".memTel").html();
                var userMoney = $(this).parent().parent().siblings(".enterpriseUcoin").html();
                array2[15] = idCard;
                array2[16] = enterpriseId
                $(".userName").html( userName + "  " +  userPhone)
                $(".userId").html(idCard)
                $(".userMoney").html(userMoney)
                ajaxPages("../web/api/customer/getCustomerBill","itemContainer3","holder3","memberBill",5,'','',array2);
                $("#memberBill").fadeIn(500);
            });

            $("#updateUcoin").click(function(){
                $(".memberDetail").fadeOut();
                $(".ucoinReduce").fadeIn(500);
                var menberId=$("input[name='menberId']").val()
                var menberName=$("input[name='menberName']").val()
                var menberTel=$("input[name='menberTel']").val()
                var menberUcoin=$("input[name='menberUcoin']").val()
                var menberAddress=$("input[name='menberAddress']").val()
                var menberManId=$("input[name='menberManId']").val()
                var EnterpriseMenberUcoin=$("input[name='EnterpriseMenberUcoin']").val()
                $("input[name='ucoinReduceId']").val(menberId);
                $("input[name='ucoinReduceName']").val(menberName);
                $("input[name='ucoinReduceTel']").val(menberTel);
                $("input[name='ucoinReduceUcNum']").val(menberUcoin);
                $("input[name='ucoinReduceAdd']").val(menberAddress);
                $("input[name='ucoinReduceManId']").val(menberManId);
                $("input[name='EnterpriseMenberUcoin2']").val(EnterpriseMenberUcoin);


            })
            $("#ucoinReduceCancel,#xx5").click(function(){
                $(".ucoinReduce").fadeOut(500);
                $(".memberDetail").fadeIn();
            })

            $("#newMenberCancel,#xx").click(function(){
                $(".newMember").fadeOut(500);
            })
            $("#resetPasswordCancel,#xx3").click(function(){
                $(".resetPassword").fadeOut(500);
            })
            $("#activeAccoutCancel,#xx4").click(function(){
                $(".activeAccout").fadeOut(500);
            });
            $("#accExport").click(function(){
                $("#newImg").prop("src","http://112.22.26.30:10000/M00/00/06/wKgCCVfGSmeARHxOAAATM2_HyMQ637.jpg")
                var enterprisePId = $("#provinceId").val();
                var enterpriseCId = $("#cityId").val();
                var enterpriseDId = $("#districtId").val();
                $(".newMember").fadeIn(500);
                var detailAddress="detailAddress";
                $.get("${bath}/web/api/common/provincies",function(data){
                    if( data.response == 'success' ){
                        data.data.map(function( object ){
                            var html = '';
                            html += '<option value="' + object.provinceId +'">';
                            html += object.provinceName;
                            html += '</option>';
                            $("#newMemAddressP").append(html);
                        });

                        if( window.localStorage ){
                            var PID = localStorage['memberManager_provinceId'];
                            var CID = localStorage['memberManager_cityId'];
                            var DID = localStorage['memberManager_districtId'];
                            if( PID == '' || PID == undefined ){
                                PID = enterprisePId;
                            }
                            if( CID == '' || CID == undefined ){
                                CID = enterpriseCId;
                            }
                            if( DID == '' || DID == undefined){
                                DID = enterpriseDId;
                            }
                        }
                        else{
                            var PID = enterprisePId;
                            var CID = enterpriseCId;
                            var DID = enterpriseDId;
                        }
                        console.log(PID,CID,DID);
                        var PSTR = 'newMemAddressP option[value="' + PID + '"]';
                        var CSTR = 'newMemAddressC option[value="' + CID + '"]';
                        var DSTR = 'newMemAddressD option[value="' + DID + '"]';
                        $('#' + PSTR).prop("selected","selected");
                        if(PID){
                            newMemAddressP(function(){
                                $("#newMemAddressC option[value='" + CID + "']").prop("selected","selected");
                            },PID);
                        }
                        if(CID){
                            newMemAddressC(function(){
                                $('#' + DSTR).prop("selected","selected");
                            },CID);
                        }

                    }
                },'json');

            });
            $("#updateMember").click(function(){
                $(".memberDetail").fadeOut();
                var pid = $("#provinceId").val();
                var cid = $("#cityId").val();
                var did = $("#dirsctId").val();
                var rid = $("#streetId").val();
                var detailAddress = $("#detailAddress").val();
                $.get("${bath}/web/api/common/provincies",function(data){
                    if( data.response == 'success' ){
                        data.data.map(function( object ){
                            var html = '';
                            html += '<option value="' + object.provinceId +'">';
                            html += object.provinceName;
                            html += '</option>';
                            $("#updateMemAddressP").append(html);
                        });

                        $("#updateMemAddressP option[value='" + pid + "']").prop("selected","selected");

                        if(pid){
                            UpdateMemAddressP(function(){
                                $("#updateMemAddressC option[value='" + cid + "']").prop("selected","selected");
                            },pid);
                        }
                        if(cid){
                            UpdateMemAddressC(function(){
                                $("#updateMemAddressD option[value='" + did + "']").prop("selected","selected");
                            },cid);
                        }

                    }
                },'json');
            });
            $("#complete").click(function(){
                $(".success").fadeOut(500);
                $(".activeAccout").fadeOut(500);
                window.location.href = 'memberManager';
            })
            $("#xx1").click(function(){
                $(".memberDetail").fadeOut(500);
            })
            $("#updateMemberCancel,#xx2").click(function(){
                $(".memberDetail").fadeIn();
                $(".updateMember").fadeOut(500);
            });
            $(document).on("click",".checkDateil",function() {

                $(".memberDetail").fadeIn(500);
                var memId = $(this).parent().parent().siblings(".memId").html();

                var memCreator = $(this).parent().parent().siblings(".memCreator").val();
                var memCreateTime = $(this).parent().parent().siblings(".memCreateTime").val();
                var lastLoginTime = $(this).parent().parent().siblings(".lastLoginTime").val();

                var memName = $(this).parent().parent().siblings(".memfullname").html();
                var memSex = $(this).parent().parent().siblings(".memSex").html();
                var memTel = $(this).parent().parent().siblings(".memTel").html();
                var memTelBinding = $(this).parent().parent().siblings(".memTelBinding").html();
                var memUcoin = $(this).parent().parent().siblings(".memUcoin").html();
                var memActive = $(this).parent().parent().siblings(".memActive").html();
                var memManagerId = $(this).parent().parent().siblings(".memManagerId").html();
                var memTab = $(this).parent().parent().siblings(".memTab").html();
                var provinceId = $(this).parent().parent().siblings(".memPId").val();
                var cityId = $(this).parent().parent().siblings(".memCId").val();
                var dirsctId = $(this).parent().parent().siblings(".memDId").val();
                var streetId = $(this).parent().parent().siblings(".memRId").val();
                var enterpriseUcoin = $(this).parent().parent().siblings(".enterpriseUcoin").html();
                var detailAddress = $(this).parent().parent().siblings(".memDetailAddress").val();
                var deliverInfos = JSON.parse($(this).parent().parent().siblings(".memDeliver").val());
                var imgurl = $(this).parent().parent().siblings(".img").val();
                var detailaddr =  $(this).parent().parent().siblings(".detailaddr").val();
                var remark =  $(this).parent().parent().siblings(".remark").val();
                var memTag = $(this).parent().parent().siblings(".memTag").html();
                tagList = $(this).parent().parent().parent().find(".memTab");
                $("input[name='menberId']").val(handleUndefined(memId));
                $("input[name='detailaddr']").val(handleUndefined(detailaddr));
                $("input[name='menberCreatName']").val(handleUndefined(memCreator));
                $("input[name='menberCreatTime']").val(handleUndefined(memCreateTime));
                $("input[name='menberLastTime']").val(handleUndefined(lastLoginTime));
                $("input[name='menberTel']").val(handleUndefined(memTel));
                $("input[name='menberName']").val(handleUndefined(memName));
                $("input[name='menberSex']").val(handleUndefined(memSex));
                $("input[name='menberManId']").val(handleUndefined(memManagerId));
                $("input[name='menberTab']").val(handleUndefined(memTab));
                $("input[name='menberUcoin']").val(handleUndefined(memUcoin));
                $("input[name='menberTelBind']").val(handleUndefined(memTelBinding));
                $("input[name='menberAcctive']").val(handleUndefined(memActive));
                $("input[name='menberAddress']").val(handleUndefined(detailAddress));
                $("input[name='EnterpriseMenberUcoin']").val(handleUndefined(enterpriseUcoin));
                $("input[name='menberNote']").val(handleUndefined(remark));
                $("#imgurl").prop("src",imgurl);
                $("#UCleft img").prop("src",imgurl);
                $("#provinceId").val(provinceId);
                $("#cityId").val(cityId);
                $("#dirsctId").val(dirsctId);
                $("#streetId").val(streetId);
                $("#detailAddress").val(detailAddress);
                $(".tagLiDetail .tagSpan").html(memTag);
                //tag赋值
                var tags = $(".tagLiDetail input[type='checkbox']");
                $(tags).prop("checked","");
                for(var i = 0;i < tagList.length;i++){
                    var tagListId = $( tagList[i]).attr("value");
                    for( var j = 0;j < tags.length;j++ ){
                        var tagId = $( tags[j]).val();
                        if( tagId == tagListId ){
                            $(tags[j]).prop("checked","checked");
                        }
                    }
                }
                //id赋值
                var id = $(this).parent().parent().parent().children()[0].value;
                $("#userId").val(id);

                //收货信息

                var deliverCount = 1;
                $(".deliverInfo").remove();
                if(deliverInfos.length<1){
                    $(".infosShow").hide()
                }else{
                    $(".infosShow").show()
                }
                $("#checkRecever").html("查看全部收货信息")
                deliverInfos.map(function(object){
                    var address = object.provinceName + object.cityName + object.districtName + object.addressDetail;
                    if( deliverCount == 1 ){
                        var html = '<dd class="deliverInfo">';
                        deliverCount = 2;
                    }
                    else{
                        var html = '<dd class="deliverInfo" style="display: none;">';
                    }
                    html += '<ul class="receiverDetail">';
                    html += '<li><span>收货人:</span><input type="text" name="menberReceiverName" readonly="readonly" value="' + object.deliverName + '"></li>';
                    html += '<li><span>手机号:</span><input type="text" name="menberReceiverTel" readonly="readonly" value="' + object.addressMobile + '"></li>';
                    html += '<li style="width:auto;"><span>收货地址:</span><input type="text" style="width:auto;" name="menberReceiverAddress" readonly="readonly" value="' + address + '"></li>';
                    html += '</ul>';
                    html += '</dd>';
                    $("#checkRecever").before(html);
                });

            });

            $("#checkRecever").click(function(){
                if($(this).html() == '查看全部收货信息'){
                    $(".deliverInfo").css("display","block");
                    $(this).html("收起");
                    $(this).css("margin-left","50px");
                }
                else if($(this).html() == '收起'){
                    $(".deliverInfo").first().siblings(".deliverInfo").css("display","none");
                    $(this).html("查看全部收货信息");
                    $(this).css("margin-left","0px");
                }
            });

            $(document).on("click",".activeAcc",function() {
                $(".activeAccout").fadeIn(500);
                var id = $(this).parent().parent().parent().children()[0].value;
                $("#userId").val(id);
                $("#activepassword").val("")
                $("#active_passwordConfirm").val("")
            })
            var customerId=""
            $(document).on("click",".resetPword",function() {
                $(".resetPassword").fadeIn(500);
                $(".resetPasswordinput").val("")
                customerId=$(this).parent().parent().siblings("input").val();
            })
            $(document).on("click",".passwordResetChooes",function() {
                var chooesval=$(this).val()
                $(".resetPasswordinput").val("")
                if(chooesval == "0"){
                    $(".resetPasswordinput").attr("maxlength","16")
                }else if(chooesval == "1"){
                    $(".resetPasswordinput").attr("maxlength","6")
                }
            })
            $(document).on("click","#resetPasswordConfirm",function() {
                var passwordchoose=$(".passwordResetChooes:checked").val()
                if(passwordchoose == "0"){
                    if($("input[name='password']").val() == $("input[name='passwordConfirm']").val()){
                        var password=$("input[name='passwordConfirm']").val();
                        var check = checkPassword(password)
                        if(password.length>7&&password.length<21 && check){
                        $.post("${bath}/web/api/customer/editPassword",{
                            customerId:customerId,
                            password:password
                        },function(data){
                            if(data.response == "success"){
                                discoverHtml()
                                $(".resetPassword").fadeOut(500);
                                data_type_alert("修改成功","success")
                            }else{
                                data_type_alert(data.data.text,"error")
                            }
                        },'json')
                        }else{
                            data_type_alert("密码为8-20位的数字和字母组合","error")
                        }
                    }else{
                        data_type_alert("两次密码不一致","error")
                    }
                }else if(passwordchoose == "1"){
                    if($("input[name='password']").val() == $("input[name='passwordConfirm']").val()) {
                        var password = $("input[name='passwordConfirm']").val();
                        console.log(password.length)
                        if(/^[0-9]*$/.test(password) && password.length == 6) {
                            $.post("${bath}/web/api/customer/editPaykey", {
                                customerId: customerId,
                                paykey: password
                            }, function (data) {
                                if (data.response == "success") {
                                    $(".resetPassword").fadeOut(500);
                                    discoverHtml()
                                    data_type_alert("修改成功", "success")
                                }
                            }, 'json')
                        }else{
                            data_type_alert("支付密码必须是纯数字且必须为6位","error")
                        }
                    }else{
                        data_type_alert("两次密码不正确","error")
                    }
                }else{
                   alert("请选择修改密码的类型")
                }
            })
            $("#updateMember").click(function(){
                $(".updateMember").fadeIn(500);
                var updName=$("input[name='menberName']").val();
                var updId=$("input[name='menberId']").val();
                var updSex=$("input[name='menberSex']").val();
                var updTel=$("input[name='menberTel']").val();
                var updManId=$("input[name='menberManId']").val();
                var updTab=$("input[name='menberTab']").val();
                var updateDetailAddress = $("input[name='menberAddress']").val();
                var imgurl=$("#imgurl").prop("src");
                var detailaddr = $("input[name='detailaddr']").val()
                var remark = $("input[name='menberNote']").val()
                $("#imgShow").prop("src",imgurl);
                $("input[name='updateMemberId']").val(updId);
                $("input[name='updateMemberTel']").val(updTel);
                $("input[name='updateMemberName']").val(updName);
                $("input[name='updateMemberSex']").val(updSex);
                $("input[name='updateMemberManName']").val(updManId);
                $("input[name='updateMemberTab']").val(updTab);
                $("textarea[name='updateMemberNote']").val(remark);
                $("input[name='updateMemberRoadDetail']").val(detailaddr);
                try{
                $("#imgShow").prop("src",imgUrl);
                }catch(e){

                }
                //tag赋值
                var tags = $(".tagLi input[type='checkbox']");
                $( tags).prop("checked","");
                for(var i = 0;i < tagList.length;i++){
                    var tagListId = $( tagList[i]).attr("value");
                    for( var j = 0;j < tags.length;j++ ){
                        var tagId = $( tags[j]).val();
                        if( tagId == tagListId ){
                            $( tags[j]).prop("checked","checked");
                        }
                    }
                }

            })

              $(".updateMemberDetail li input").focus(function(){
                  $(this).css("background","#f5f5f5");
                  $(this).css("border","1px solid #666");
              })
            $(".updateMemberDetail li input").blur(function(){
                $(this).css("background","#fff");
                $(this).css("border","1px solid #ccc");
            })
            $(".newMemberDetail li input").focus(function(){
                $(this).css("background","#f5f5f5");
                $(this).css("border","1px solid #666");
            })
            $(".newMemberDetail li input").blur(function(){
                $(this).css("background","#fff");
                $(this).css("border","1px solid #ccc");
            })
            if(isEnd){
                $(".checkdopet").hide()
            }
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
        });


    </script>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
<div class="allheadstyle">
    <span>会员列表</span><abbr></abbr>
</div>
<input type="hidden" id="provinceId" value="${enterpriseInfo.getProvinceId()}">
<input type="hidden" id="cityId" value="${enterpriseInfo.getCityId()}">
<input type="hidden" id="districtId" value="${enterpriseInfo.getDistrictId()}">
<div class="memberSearch">
    <ul style="position: relative">
        <li class="checkdopet" style="width: 300px;"><input class="allinputButton" placeholder="查看范围" value="${enterpriseInfo.enterpriseName}" style="width: 250px; color:#999;" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterpriseInfo.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose" style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
        <li style="width: 310px;">
            <input class="allinputButton" placeholder="身份证号" maxlength="50" style="width: 280px;" type="text" name="memberNum" />
        </li>
        <li style="width: 150px;">
            <input placeholder="姓名" class="allinputButton"  type="text" name="memberName" />
        </li>
        <li>
            <input class="allinputButton" style="width: 200px" placeholder="联系电话"  maxlength="11" type="text" name="memberPhone" />
        </li>
        <li style="" class="allinputButton">
            <span>是否激活</span>
            <dl class="select"> <i value="0" class="arrow"></i>
                <dt class="allSelectButton" name="active">全部</dt>
                <dd class="allSelectButton">全部</dd>
                <dd class="allSelectButton">是</dd>
                <dd class="allSelectButton">否</dd>
            </dl>
        </li>
        <li style="margin-left: 20px;" class="allinputButton">
            <span>手机绑定</span>
            <dl class="select"> <i value="0" class="arrow"></i>
                <dt class="allSelectButton" name="phone">全部</dt>
                <dd class="allSelectButton">全部</dd>
                <dd class="allSelectButton">是</dd>
                <dd class="allSelectButton">否</dd>
            </dl>
        </li>
        <#--<li style="width: 150px;margin-left: 30px;">-->
            <#--<input class="allinputButton" placeholder="客户经理号" type="text" name="memberUserName" />-->
        <#--</li>-->

        <#--<li style="height: 32px!important;" class="allinputButton">-->
            <#--<span>标签</span>-->
            <#--<dl class="select"> <i value="0" class="arrow"></i>-->
                <#--<dt class="allSelectButton" name="tag" >全部</dt>-->
                <#--<dd class="allSelectButton">全部</dd>-->
                <#--<#list tagList as object>-->
                    <#--<dd class="allSelectButton" value="${object.tagId}" title="${object.tagDesc}">${object.tagName}</dd>-->
                <#--</#list>-->
            <#--</dl>-->

        <#--</li>-->
        <li> <input class="allseachButton" type="button" value="搜索" id="accSearch" /></li>
        <li style="width:500px;position: absolute;right: -35%;">
            <#--<#if isEnd == "1"><input class="allclickButton" onclick="coverHtml()" type="button" id="accExport" value="开通新会员" /></#if>-->
            <input class="allclickButton" style=" " type="button" id="memberOut" value="导出会员信息" />
        </li>
    </ul>
</div>

<div class="memberList">
    <script type="text/javascript">
        var total_elements = ${total_elements_memberManager};
    </script>
    <dl>
        <dt><abbr style="width:280px;">身份证号</abbr><abbr style="width: 115px;">姓名</abbr><abbr style="width: 180px">联系电话</abbr><abbr style="width: 180px">手机绑定</abbr><#if isEnd == "1"><abbr style="width: 240px;">本网点邮豆余额</abbr><#else><abbr style="width: 240px;">邮豆总余额</abbr></#if><abbr style="width: 180px">是否激活</abbr><abbr style="padding-left: 38px">操作</abbr>
        </dt>
        <div id="itemContainer"></div>
        <div id="holder" class="allcpageTurnButton"></div>
    </dl>
</div>


<div class="newMember allpop">
    <h1>开通新会员<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul>
        <li>
            <ul class="newMemberDetail">
                <li><span><i>*</i>身份证号:</span><input maxlength="18" name="newMemId" type="text" /><label style="margin-left: 15px;color:red;font-size: 12px;"></label></li>
                <li><span><i>*</i>姓名:</span><input maxlength="8" name="newMemName" type="text" /></li>
                <li><span>性别:</span>	<input name="newMemSex" type="text" style="border:none; background:#fff;" readonly="readonly" /></li>
                <li style="height:80px;"><span>上传头像:</span><img id="newImg" style="margin-left:40px;" src="${bath}/static/img/look.png" width="50" height="50" />
                    <input style="width:75px; height:30px; border-style:none; background:#fefefe;" id="newMemPhoto" name="newMemPhoto" type="file" /></li>
                <li><span><i>*</i>联系电话:</span>	<input maxlength="11" name="newMemTel" type="text" /></li>
                <li><span><i>*</i>联系地址:</span>
                    <select style="margin-left:40px;" name="newMemAddressP" id="newMemAddressP">
                        <option selected="selected">选择省份</option>
                    </select>
                    <select name="newMemAddressC" id="newMemAddressC">
                        <option value="0" selected="selected">选择城市</option>
                    </select>
                    <select name="newMemAddressD" id="newMemAddressD">
                        <option value="0" selected="selected">选择区县</option>
                    </select>
                    <#--<select name="newMemAddressR" id="newMemAddressR">-->
                        <#--<option value="0" selected="selected">选择街道</option>-->
                    <#--</select>-->
                    <input style="margin-left:143px; margin-top:10px; width:500px;" type="text" name="newMemberRoadDetail" placeholder="详细地址" />
                </li>
                <li><span>客户经理号:</span>	<input name="newMemManName" type="text" /></li>
                <li class="tagList">
                    <span>标签:</span>
                    <span style="width: 430px!important;" class="tagSpan">
                        <#list tagList as object>
                            <input style="width:20px;" type="checkbox" value="${object.tagId}" title="${object.tagDesc}">${object.tagName}</input>
                        </#list>
                    </span>
                </li>
                <li><span style="position:relative; top:-40px;">备注:</span>
                	<textarea style="margin-left:40px;" cols="45" rows="2" name="newMemNote"></textarea>
                </li>
            </ul>
        </li>
        <li style="width:700px; height:80px; line-height:80px;border-top:1px solid #ccc;">

            <input class="allseachButton" type="button" id="newMenberConfirm" value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="newMenberCancel" value="取消" /></li>
    </ul>
</div>

<div class="memberDetail allpop">
    <h1 style="height:60px; line-height:60px; font-size:20px; padding-left:20px; border-bottom:1px solid #CCC;">会员详情<i onclick="discoverHtml()" id="xx1"></i></h1>
    <dl>
        <dt>基本信息
            <#if isEnd == "1"><input id="updateMember" type="button" value="编辑" /></#if>
        </dt>
        <dd style="position:relative;">
            <div style="width:150px; height:150px; position:absolute; left:10px; top:10px;"><img id="imgurl" src="${bath}/static/img/look.png" width="150" height="150" /></div>
            <ul style=" margin-left:180px; margin-top:10px;">
                <input type="hidden" id="provinceId" />
                <input type="hidden" id="cityId" />
                <input type="hidden" id="dirsctId" />
                <input type="hidden" id="streetId" />
                <input type="hidden" id="detailAddress" />
                <li><span>身份证号:</span><input type="hidden" id="userId" /><input type="text" name="menberId" readonly="readonly" /></li>
                <li><span>联系电话:</span><input type="text" name="menberTel" readonly="readonly" /></li>
                <li><span>姓名:</span><input type="text" name="menberName" readonly="readonly" /></li>
                <li><span>联系地址:</span><input type="text" name="menberAddress" readonly="readonly" /></li>
                <li><span>性别:</span><input type="text" name="menberSex" readonly="readonly" /></li>
                <li><span>客户经理号:</span><input type="text" name="menberManId" readonly="readonly" /></li>
                <li class="tagLiDetail"><span>标签:</span>
                    <span style=" white-space: normal!important;" class="tagSpan">

                    </span>
                </li>
                <li><span>备注:</span><input type="text" name="menberNote" readonly="readonly" /></li>
            </ul>
        </dd>
        <dt>财富信息</dt>
        <dd style="margin:20px 0px; padding-left:20px;">
            <#if isEnd == "1">
                <span>本网点邮豆余额:</span> <input style="width: 120px" type="text" name="EnterpriseMenberUcoin" readonly="readonly" />(总余额：<input style="width: 120px" type="text" name="menberUcoin" readonly="readonly" />)
                <#else>
                <span>邮豆总余额:</span><input style="width: 120px" type="text" name="menberUcoin" readonly="readonly" />
                <input type="hidden" name="EnterpriseMenberUcoin" readonly="readonly" />
            </#if>

            <#if isEnd == "1">
                <#--<input id="UcoinSend" type="button" value="邮豆发放" />-->
                <input id="updateUcoin" type="button" value="邮豆扣减" />
            </#if>
        </dd>
        <dt>账户信息
        </dt>
        <dd>
            <ul id="accoutDetail">
                <li><span>账号创建者:</span><input type="text" name="menberCreatName" readonly="readonly" /></li>
                <li><span>创建时间:</span><input type="text" name="menberCreatTime" readonly="readonly" /></li>
                <li><span>是否激活:</span><input type="text" name="menberAcctive" readonly="readonly" /></li>
                <li><span>最后登录时间:</span><input type="text" name="menberLastTime" readonly="readonly" /></li>
                <li><span>手机绑定:</span><input type="text" name="menberTelBind" readonly="readonly" /></li>
                <li><span>绑定手机号:</span><input type="text" name="menberBindTel" readonly="readonly" /></li>
                <input name="detailaddr" type="hidden" />
            </ul>
        </dd>
        <div class="infosShow">
        <dt class="deliverInfos">收货信息
        </dt>
        <a class="deliverInfos" id="checkRecever">查看全部收货信息</a>
        </div>
    </dl>
</div>

<div class="updateMember allpop">
    <h1>编辑会员<i id="xx2"></i></h1>
    <ul>
        <li>
            <ul class="updateMemberDetail">
                <li><span><i>*</i>身份证号:</span><input name="updateMemberId" type="text" readonly="readonly" /></li>
                <li><span><i>*</i>姓名:</span><input name="updateMemberName" type="text" /></li>
                <li><span>性别:</span>	<input name="updateMemberSex" type="text" style="border:none; background:#fff;" readonly="readonly" /></li>
                <li style="height:80px;"><span>上传头像:</span><img style="margin-left:40px;" id="imgShow" src="${bath}/static/img/look.png" width="50" height="50" />
                    <input style="width:80px; height:30px; border-style:none; background:#fefefe;" id="updateMemberPhoto" name="updateMemberPhoto" type="file" /></li>
                <li><span><i>*</i>联系电话:</span>	<input maxlength="11" name="updateMemberTel" type="text" /></li>
                <li><span><i>*</i>联系地址:</span>
                    <select style="margin-left:40px;" name="updateMemberAddressP" id="updateMemAddressP">
                        <option selected="selected">选择省份</option>
                    </select>
                    <select name="updateMemberAddressC" id="updateMemAddressC">
                        <option value="0" selected="selected">选择城市</option>
                    </select>
                    <select name="updateMemberAddressD" id="updateMemAddressD">
                        <option value="0" selected="selected">选择区县</option>
                    </select>
                    <#--<select name="updateMemberAddressR" id="updateMemAddressR">-->
                        <#--<option value="0" selected="selected">选择街道</option>-->
                    <#--</select>-->
                    <input style="margin-left:143px; margin-top:10px; width:500px;" type="text" name="updateMemberRoadDetail" placeholder="详细地址" />
                </li>
                <li><span>客户经理号:</span>	<input name="updateMemberManName" type="text" maxlength="10"/></li>
                <li class="tagLi"><span>标签:</span>
                    <span style="width: 450px!important;" class="tagSpan">
                        <#list tagList as object>
                            <input class="tag"type="checkbox" value="${object.tagId}" title="${object.tagDesc}">${object.tagName}</input>
                        </#list>
                    </span>
                </li>
                <li><span style="position:relative; top:-40px;">备注:</span>
                	<textarea style="margin-left:40px;" cols="40" rows="2" name="updateMemberNote"></textarea>
                </li>
            </ul>
        </li>
        <li style="width:700px; height:80px; line-height:80px;border-top:1px solid #ccc;">
            <input class="allseachButton" type="button" id="updateMemberConfirm" value="确定" />
            <input class="allcancelButton" type="button" id="updateMemberCancel" value="取消" /></li>
    </ul>
</div>

<div class="resetPassword allpop">
    <h1>重置密码<i onclick="discoverHtml()" id="xx3"></i></h1>
    <ul>
        <li style="height:150px; line-height:50px; padding-left:50px;">
            <span style="padding-left:100px;">密码类型:
                <input checked="checked" class="passwordResetChooes" type="radio" value="0" name="type">登录密码</input>
                <input class="passwordResetChooes" type="radio" value="1" name="type">支付密码</input>
            </span><br />
            <span style="padding-left:100px;">输入密码:<input style="margin-left: 20px;border-radius: 3px" class="resetPasswordinput" type="password" name="password" /><label style="color:red;margin-left: 10px;"></label></span><br />
            <span style="padding-left:100px;">确认密码:<input style="margin-left: 20px;border-radius: 3px" class="resetPasswordinput" type="password" name="passwordConfirm"/><label style="color:red;margin-left: 10px;"></label></span>
        </li>
        <li style="height:60px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="resetPasswordConfirm"  value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="resetPasswordCancel" value="取消" />
        </li>
    </ul>
</div>

<div class="activeAccout allpop">
    <h1>激活账号<i onclick="discoverHtml()" id="xx4"></i></h1>
    <ul>
        <li style="height:150px; line-height:50px; padding-left:50px;">
            请设置用户登录密码<br />
            <span style="padding-left:100px;">登录密码:<input maxlength="20" type="password" name="password" id="activepassword"/><label style="color:red;margin-left: 10px;font-size: 12px"></label></span><br />
            <span style="padding-left:100px;">确认密码:<input maxlength="20" type="password" name="passwordConfirm" id="active_passwordConfirm"/><label style="color:red;margin-left: 10px;font-size: 12px"></label></span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="activeAccoutConfirm" value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="activeAccoutCancel" value="取消" />
        </li>
    </ul>
</div>


<div class="success">
    <span style="left:110px;" ><img src="${bath}/static/img/ok.png" width="40" height="40" />账号激活成功</span><br />
    <a onclick="discoverHtml()" id="complete" style="left:-150px;" >完成</a>
</div>

<div class="ucoinReduce allpop">
    <h1>邮豆扣减<i id="xx5"></i></h1>
    <ul>
        <li><span>扣减账户:</span>
            <div id="UCleft"><img width="140px" height="160px" src="${bath}/static/img/look.png" ></div>
            <div id="UCright">
                <abbr><input style="width: 300px;" type="text" readonly="readonly" name="ucoinReduceId"></abbr>
                <abbr><input style="width: 80px" type="text" readonly="readonly" name="ucoinReduceName"><input type="text" readonly="readonly" name="ucoinReduceTel"></abbr>
                <abbr><section>本网点邮豆余额:</section><input type="text" readonly="readonly" name="EnterpriseMenberUcoin2"></abbr>
                <abbr>总余额:<input style="margin-left: 10px" type="text" readonly="readonly" name="ucoinReduceUcNum"></abbr>
                <abbr><section>联系地址:</section><input type="text" readonly="readonly" name="ucoinReduceAdd"></abbr>
            </div>
        </li>
        <li><span><i>*</i>扣减金额:</span><input type="text" id="ucoin_reduce_amount"> </li>
        <li><span><i>*</i>业务日期:</span><input class="" type="text" style="" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
        <li><span>备注:</span><input type="text" id="ucoin_reduce_remark"> </li>
        <li><span><i>*</i>支付密码:</span><input type="password" id="ucoin_reduce_paykey"> </li>
        <input class="allseachButton" type="button" id="ucoinReduceConfrim" value="确定"><input class="allcancelButton" type="button" id="ucoinReduceCancel" value="取消">
    </ul>
</div>



    <div class="allpop" id="userDetailListReduce">
        <h1>会员订单记录<i></i></h1>
        <div style="margin-left: 20px; margin-top: 20px;height: 50px"><input style="float: right;margin-right: 70px"  type="button" id="outExcel" value="导出表格" class="allclickButton"> </div>
        <table class="table_listReduceDetail" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>身份证号</th>
                <th>姓名</th>
                <th>单据类型</th>
                <th>时间</th>
                <th>订单/退单号</th>
                <th>消耗/退款邮豆</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer2">
            <tr>
                <td>身份证号</td>
                <td>姓名</td>
                <td>单据类型</td>
                <td>时间</td>
                <td>单据编号</td>
                <td>订单金额</td>
                <td>退单金额</td>
            </tr>
            </tbody>
        </table>
        <div id="holder2" class="allcpageTurnButton"></div>
    </div>


    <div class="allpop" id="memberBill">
        <h1>会员账单<i></i></h1>
        <div style=" margin-top: 20px;">
            <ul>
                <li><abbr class="userId"></abbr><abbr>本网点邮豆余额</abbr></li>
                <li><abbr class="userName">张三 11111111</abbr><abbr><span class="userMoney"></span>邮豆</abbr></li>
            </ul>
        </div>
        <table class="memberBillDetail" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>交易时间</th>
                <th>交易对象</th>
                <th>交易理由</th>
                <th>交易金额</th>
                <th>邮豆余额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody align="center" id="itemContainer3">
            <tr>
                <td>身份证号</td>
                <td>姓名</td>
                <td>单据类型</td>
                <td>时间</td>
            </tr>
            </tbody>
        </table>
        <div id="holder3" class="allcpageTurnButton"></div>
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
<script type="text/javascript">
    $(document).ready(function(){
        $("#outExcel").click(function(){
            var outputStr = '';
            if( excel_idCard != undefined ){
                outputStr += '&idCard=' + excel_idCard;
            }
//            if( array[8] == undefined || array[8] == '' || array[9] == undefined || array[9] == ''){
//                response_ensure_alert("warning","搜索时间不能为空");
//            }
//            else{
            window.location.href = '../web/api/exportExcel/consumeDetailDownNew?' + outputStr;
//            }
        });
        $(document).on("mouseover",".select2",function(){
            $(this).css("z-index","1")
            $(this).children("section").show()
        })
        $(document).on("mouseout",".select2",function(){
            $(this).css("z-index","0")
            $(this).children("section").hide()
        })

        $(".arrow").click(function(){
            var arr=$(this).val();
            $(this).siblings("dd").slideToggle();
            if(arr==0){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_black_up.png) right center no-repeat");
                $(this).val("1");
            }else if(arr==1){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_black_down.png) right center no-repeat");
                $(this).val("0")
            }

        });
        $(".select dd").click(function(){
            var secval=$(this).html();
            $(this).hide().siblings("dd").hide();
            $(this).siblings("dt").html(secval);
            $(this).siblings(".arrow").css("background","url(${bath}/static/img/com_btn_arrow_black_down.png) right center no-repeat")
        })
        $("#itemDetail h1 i").click(function(){
            $("#itemDetail").hide()
        })
        $(document).on("click",".checkDetail",function(){
            coverHtml()
            var orderId = $(this).children("input").val()
            $("#itemDetail").show()
            if( $(this).siblings(".checkType").html() == "线下订单" || $(this).siblings(".checkType").html() == "线上订单"){
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
            }else if($(this).siblings(".checkType").html() == "线下退单" || $(this).siblings(".checkType").html() == "线上退单"){
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
    })

</script>
<div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
<script src="${bath}/static/js/memberManager.js?version=${VERSION}"></script>
</body>
</html>
