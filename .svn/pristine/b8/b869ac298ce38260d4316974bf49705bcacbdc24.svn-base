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
      <#if myUcoinBalance??>
          <img src="${basePath}/static/img/icon_logo_my_youbao.png">
          <p>我的邮豆</p>
          <h2>${myUcoinBalance!''}</h2>
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
      </#if>

  </div>
</div>

<div class="income_details member_box">
  <h2 style="font-size: 1.2em;">收支明细</h2>
	<div class="ocoupon_list">
        <#list customerUcoinHistoryList.content as u>
            <#if u.type !='UCOIN_DEDUCT'>
                <div class="coupon_item ">
                    <a href="${basePath}/billDeta?ucoinHistoryId=${u.id!''}">
                        <h4>
                        <#if u.type=='UCOIN_CONSUME'>
                            消费购物
                        <#elseif u.type=="UCOIN_REFUND">
                            邮豆退款
                        <#elseif u.type=="ENTERPRISE_GRAND">
                            企业发放
                        <#else >
                            ${u.sendType!''}
                        </#if>
                            <small class="light">
                               ${u.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            </small>
                        </h4>
                        <div class="time red">
                            <#if u.type=='UCOIN_REFUND' || u.type=='ENTERPRISE_GRAND'>
                                <p>+${u.price!''}</p>
                            <#elseif u.type=='UCOIN_CONSUME'>
                                <p>-${u.price!''}</p>
                            </#if>
                        </div>
                    </a>
                </div>
            </#if>
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
                            nextpagehtml += '<a href="${basePath}/billDeta?ucoinHistoryId="' + dataUcoin[i].id + '>';
                            nextpagehtml += '<h4>';
                            if (dataUcoin[i].type == 'UCOIN_CONSUME') {
                                nextpagehtml += '消费购物';
                            } else if (dataUcoin[i].type == "UCOIN_REFUND") {
                                nextpagehtml += '邮豆退款';
                            } else if (dataUcoin[i].type == "ENTERPRISE_GRAND") {
                                nextpagehtml += '企业发放';
                            } else if(dataUcoin[i].type =='UCOIN_DEDUCT'){
                                nextpagehtml += '邮宝扣减';
                            };
                            nextpagehtml += '<small class="light">';
                            nextpagehtml += new Date(dataUcoin[i].createTime).datePattern("yyyy-MM-dd HH:mm:ss");
                            nextpagehtml += '</small></h4>';
                            nextpagehtml += '<div class="time red">';
                            if (dataUcoin[i].type == 'UCOIN_REFUND' || dataUcoin[i].type == 'ENTERPRISE_GRAND') {
                                nextpagehtml += '<p>+' + dataUcoin[i].price + '</p>';
                            } else if (dataUcoin[i].type == 'UCOIN_CONSUME') {
                                nextpagehtml += '<p>-' + dataUcoin[i].price + '</p>';
                            }
                            nextpagehtml += '</div>';
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