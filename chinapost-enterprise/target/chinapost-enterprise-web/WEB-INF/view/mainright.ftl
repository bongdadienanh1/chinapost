<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/mainright.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/pouchdb.min.js"></script>
<style type="text/css">
    @media screen and ( max-width: 1360px){
        body{
            zoom:92.5%;
            font-size:10px!important;
        }
    }
    body{
        width: 100%;
        height: 100%;
        background: #f7f7f7;
    }
    .background{
        width: 1593px;
        height: 896px;
        background:url(../static/img/welcome2.png) no-repeat center;
        position: relative;
    }
    #logShow{
        position: absolute;
        right: 185px;
        top: 250px;
        font-size: 12px;
        color: #666;
        z-index: 2;
    }
    #logShow marquee{
    }
    #logShow li{
        margin-top: 5px;
        width: 200px;
        word-break: break-all;
    }
    #logShow li abbr{
        display: inline-block;
        width: 180px;
        vertical-align: top;
    }
    #logShow li span{
        display: inline-block;
        vertical-align: middle;
        margin-top: 4px;
        margin-right: 3px;
    }
    #logShow2{
        position: relative;
    }
    #show{
        height: 150px;
        overflow: hidden;
    }
</style>
</head>
<script type="text/javascript">
    $(document).ready(function(){
        $.post("${bath}/web/deliveryInform/getDeliveryInformByManagerId", {

        }, function (data) {
            if(data.response=="success"){
                var deliveryInform = data.data.deliveryInform
                var logVal = deliveryInform.split("^_^")
                var html = ""
                for(i = 0; i < logVal.length ;i++){
                    html += ' <li><span class="logNum">'+ (i+1)+ '.' +'</span><abbr class="logVal">'+ logVal[i] +'</abbr></li>'
                }
                $("#logShow2").append(html)
                var height=$("#logShow2").height()
                console.log(height)
                if(height < 150){

                }else{
                    while(i<100){
                        i++
                        $("#logShow2").delay(1000).animate({top:-(height)},height*70,"linear",function(){
                            $("#logShow2").css("top",150)
                        })
                    }
                }


            }else{

            }

        }, "json")
        $("#show").mouseover(function(){
            $("#logShow2").stop()
        })

    })
</script>
<body style="background: #edf3f8">

<div style="margin: 20px" class="background">
    <div id="logShow">
        <ul>
            <li style="margin-bottom: 20px"><span style="font-weight: bold; font-size: 14px">新功能简介</span></li>
            <div id="show">
                <div id="logShow2">

                </div>
            </div>
        </ul>
    </div>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script type="text/javascript">
    var enterpriseId = '${enterpriseId}';
    var enterpriseName = '${enterpriseName}';
    var accountName = '${accountName}';
    var isTop = '${isTop}';
    var isEnd = '${isEnd}';
    sessionStorage.setItem("ShoppingCart",'');
    if( window.sessionStorage ){
        put('enterpriseId',enterpriseId);
        put('enterpriseName',enterpriseName);
        put('accountName',accountName);
        put('isTop',isTop);
        put('isEnd',isEnd);
    }


    function put(key,value){
        sessionStorage.setItem(key,value);
    }

</script>
<script src="${bath}/static/js/enterpriseInfo.js"></script>
<script type="text/javascript">
    var enterprise = getCurrentEnterpriseInfo();
</script>
</body>
</html>
