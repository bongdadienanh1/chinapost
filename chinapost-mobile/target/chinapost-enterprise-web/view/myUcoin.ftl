<!DOCTYPE html>
<!-- saved from url=(0057)http://192.168.2.222:8101/mobile/customer/myintegral.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>UDD商城-我的邮豆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta content="telephone=no" name="format-detection">
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/style.css?v=201606201538">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/myUb.css">
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>

    <style>
        .ion-chevron-right{
            font-size: 1.4286em;
            color: #D6D6D6;
        }
    </style>
</head>
<body>
<#--<div class="mui-appbar">-->
    <#--<h2 class="mui-text-center">我的邮豆</h2>-->
    <#--<a href="javascript:history.go(-1);" class="back-btn">-->
		<#--<i class="glyphicon glyphicon-chevron-left"></i>-->
	<#--</a>-->
<#--</div>-->
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="my_balance member_box">
  <div class="cover">

          <img src="${basePath}/static/img/icon_logo_my_youbao.png">
          <p>我的邮豆</p>
          <#if myUcoinBalance == ''>
            <h2>0</h2>
          <#else>
              <h2>${myUcoinBalance!''}</h2>
          </#if>
          <p><a href="${basePath}/main"><i class="u_mall"></i>购买商品</a></p>
          <#--<p>U宝可在对应的专区店铺使用</p>-->
          <#--<#if myUcoinBalance.subjectUrl??>-->
              <#--<p><a href="${basePath}/${myUcoinBalance.subjectUrl}"><i class="u_mall"></i>进入专区店铺</a></p>-->
          <#--<#else >-->
              <#--<p><a href="javascript:void(0)"><i class="u_mall"></i>进入专区店铺</a></p>-->
          <#--</#if>-->

          <#--<p>有效期：${(myUcoinBalance.ucoinEndTime!'')?string("yyyy-MM-dd HH:mm:ss")}</p>-->
          <#--<#else >-->
              <#--<img src="${myUcoinBalance.enterpriseImgUrl!''}">-->
              <#--<p>${myUcoinBalance.couponSubjectName!''}</p>-->
              <#--<h2>0</h2>-->
              <#--<p>U宝可在对应的专区店铺使用</p>-->
              <#--<#if myUcoinBalance.subjectUrl??>-->
                  <#--<p><a href="${basePath}/${myUcoinBalance.subjectUrl}"><i class="u_mall"></i>进入专区店铺</a></p>-->
              <#--<#else >-->
                  <#--<p><a href="javascript:void(0)"><i class="u_mall"></i>进入专区店铺</a></p>-->
              <#--</#if>-->
              <#--<p></p>-->


  </div>
</div>

<div class="income_details member_box">
  <h2 style="font-size: 1.2em;">收支明细</h2>
	<div class="ocoupon_list">
        <#list customerUcoinHistoryList.content as u>
            <#--<#if u.type !='UCOIN_DEDUCT'>-->
                <div class="coupon_item ">
                    <#--<a href="${basePath}/billDeta?ucoinHistoryId=${u.id!''}">-->
                        <a href="${basePath}/billDeta?customerId=${u.customerId}&batchId=${u.batchId!''}&type=${u.type}&orderId=${u.orderId!''}">
                        <h4>
                        <#if u.type=='UCOIN_CONSUME'>
                            订单号-${u.orderCode!''}-购物消费
                           <#-- 消费购物-->
                        <#elseif u.type=="UCOIN_REFUND">
                            退单号-${u.backCode!''}-邮豆退款
                        <#elseif u.type=="ENTERPRISE_GRAND">
                            <#list u.enterpriseInfo as b>${b.enterpriseName!''}-邮豆发放</#list>
                        <#elseif u.type =='UCOIN_DEDUCT'>
                            <#list u.enterpriseInfo as a>${a.enterpriseName!''}-邮豆扣减</#list>
                        </#if>
                            <small class="light">
                               ${u.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            </small>
                        </h4>
                        <div class="time red" style="right: 1.5em; top: 0em">
                            <#if u.type=='UCOIN_REFUND' || u.type=='ENTERPRISE_GRAND'>
                                <p>+${u.price!''}</p>
                            <#elseif u.type=='UCOIN_CONSUME'>
                                <p>-${u.price!''}</p>
                            <#elseif u.type =='UCOIN_DEDUCT'>
                                <p>${u.price!''}</p>
                            </#if>
                        </div>
                        <#--<br/>-->
                        <#--<div class="time red">-->
                            <#--<p style=" position:absolute;right: 1.5em; bottom: -3.5em; font-size: 75%;">${u.balancePrice}</p>-->
                        <#--</div>-->
                        <i class="ion-chevron-right" style="position:absolute; top: 0.8em; right: 0.5em;"></i>
                    </a>
                </div>
            <#--</#if>-->
        </#list>
	</div>

    <div class="list-loading" style="display:none" id="showmore">
        <img alt="" src="${basePath}/static/img/loading.gif">
        <span>加载中……</span>
    </div>
</div>
<script type="text/javascript">
    var pageNo=1;
    var basePath = $("#basePath").val();
    $(window).scroll(function () {
        if ($(this).scrollTop() >= ($('body').height() - $(window).height())) {
            pageNo++;
            $.ajax({
                url: basePath + '/myUcoinPage?pageNumber=' + pageNo,
                type: 'GET',
                dataType: 'text',
                async: 'false',
                beforeSend: showLoadingImg,
                success: function (data) {
                    var returnjson = eval("("+data+")");
                    var dataUcoin = returnjson.data.customerUcoinHistoryList.content;
                    var nextpagehtml = '';
                    if (dataUcoin != null && dataUcoin.length > 0) {
                        for (var i = 0; i < dataUcoin.length; i++) {
                            nextpagehtml += '<div class="coupon_item ">';
                            <#--nextpagehtml += '<a href="${basePath}/billDeta?ucoinHistoryId=' + dataUcoin[i].id + '">';-->
                            <#--if( typeof (dataUcoin[i].orderId) == 'undefined'){-->
                                <#--nextpagehtml += '<a href="${basePath}/billDeta?customerId=' + dataUcoin[i].customerId + '&batchId='+ dataUcoin[i].batchId +'&type='+ dataUcoin[i].type +'&orderId= ">';-->
                            <#--}-->
                            <#--else{-->
                                nextpagehtml += '<a href="${basePath}/billDeta?customerId=' + dataUcoin[i].customerId + '&batchId='+ checkEmpty(dataUcoin[i].batchId) +'&type='+ dataUcoin[i].type +'&orderId='+ checkEmpty(dataUcoin[i].orderId) +'">';
//                            }
                            nextpagehtml += '<h4>';
                            if (dataUcoin[i].type == 'UCOIN_CONSUME') {
                              /* nextpagehtml += '消费购物';*/
                                /* dataUcoin[i].enterpriseInfo.enterpriseName*/
                             nextpagehtml += '<p>订单号-'+ dataUcoin[i].orderCode + '-购物消费</p>';
                            } else if (dataUcoin[i].type == "UCOIN_REFUND") {
                                nextpagehtml += '<p>退单号-'+dataUcoin[i].backCode + '-邮豆退款</p>';
                            } else if (dataUcoin[i].type == "ENTERPRISE_GRAND") {
                                nextpagehtml += dataUcoin[i].enterpriseInfo.enterpriseName;
                                nextpagehtml +='-邮豆发放';
                            } else if(dataUcoin[i].type =='UCOIN_DEDUCT'){
                                nextpagehtml += dataUcoin[i].enterpriseInfo.enterpriseName;
                                nextpagehtml +='-邮豆扣减';
                            };
                            nextpagehtml += '<small class="light">';
                            nextpagehtml += new Date(dataUcoin[i].createTime).datePattern("yyyy-MM-dd HH:mm:ss");
                            nextpagehtml += '</small></h4>';
                            nextpagehtml += '<div class="time red" style="right: 1.5em; top: 0em">';

                            if (dataUcoin[i].type == 'UCOIN_REFUND' || dataUcoin[i].type == 'ENTERPRISE_GRAND') {
                                nextpagehtml += '<p>+' + dataUcoin[i].price + '</p>';
                            } else if (dataUcoin[i].type == 'UCOIN_CONSUME' ) {
                                nextpagehtml += '<p>-' + dataUcoin[i].price + '</p>';
                            } else {
                                nextpagehtml += dataUcoin[i].price;
                            }
                            nextpagehtml += '</div>';
//                            nextpagehtml += '<br/>';
//                            nextpagehtml +='<div class="time red">';
//                            nextpagehtml +='<p style=" position:absolute;right: 1.5em; bottom: -3.5em; font-size: 75%;">'+ dataUcoin[i].balancePrice +'</p>';
//                            nextpagehtml +='</div>';
                            nextpagehtml +='<i class="ion-chevron-right" style="position:absolute; top: 0.8em; right: 0.5em;"></i>';
                            nextpagehtml += '</a>';
                            nextpagehtml += '</div>';
                        };
                        if(nextpagehtml!=null&&nextpagehtml!=''){
                                $('.ocoupon_list').append(nextpagehtml );
                            $newElems =$("#ocoupon_list .coupon_item");
                            // 渐显新的内容
                            $newElems.animate({ opacity: 1 });
                            if(returnjson.data.customerUcoinHistoryList.totalPages==pageNo){
                                $('#showmore').hide();
                            }else{
                                $('#showmore').html(' <img src='+basePath+'/static/img/loading.gif /><span>加载中……</span>');
                                $('#showmore').show();
                            }
                        }else {
                            $('#showmore').html('<a class="handle" href="javascript:show()">已无更多结果</a>');
                        }
                    }else{
                        $('#showmore').html('<a class="handle" href="javascript:show()">已无更多结果</a>');
                    };
                }
            });
        }
    });
    function showLoadingImg(){
        $('#showmore').html(' <img src='+basePath+'/static/img/loading.gif /><span>加载中……</span>');
        $('#showmore').show();
    }
    function checkEmpty(str){
        if(typeof (str) == 'undefined'){
            return '';
        }
        else{
            return str;
        }
    }

  $(function(){
      //时间格式化
      Date.prototype.datePattern=function(fmt) {
          var o = {
              "M+" : this.getMonth()+1, //月份
              "d+" : this.getDate(), //日
              "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
              "H+" : this.getHours(), //小时
              "m+" : this.getMinutes(), //分
              "s+" : this.getSeconds(), //秒
              "q+" : Math.floor((this.getMonth()+3)/3), //季度
              "S" : this.getMilliseconds() //毫秒
          };
          var week = {
              "0" : "/u65e5",
              "1" : "/u4e00",
              "2" : "/u4e8c",
              "3" : "/u4e09",
              "4" : "/u56db",
              "5" : "/u4e94",
              "6" : "/u516d"
          };
          if(/(y+)/.test(fmt)){
              fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
          }
          if(/(E+)/.test(fmt)){
              fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
          }
          for(var k in o){
              if(new RegExp("("+ k +")").test(fmt)){
                  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
              }
          }
          return fmt;
      }
  })
</script>
</body>
</html>