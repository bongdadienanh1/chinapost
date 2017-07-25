<!DOCTYPE html>
<html>
<head>
    <#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>个人资料 - 编辑联系地址</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
        <link rel="stylesheet" href="${basePath}/static/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
        <link rel="stylesheet" href="${basePath}/static/css/myUb.css"/>
  <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
  <script src="${basePath}/static/js/idangerous.swiper.js"></script>
</head>
<body>
<input type="hidden" value="${basePath}" id="basePath">

<form action="${basePath}/updateAddress" id="updateAddress" method="post">
<div class="member_form">
    <input type='hidden' id="provinceCh" name="provinceId" value="${customer.provinceId!''}"/>
    <input type='hidden' id="cityCh" name="cityId" value="${customer.cityId!''}"/>
    <input type='hidden' id="countyCh" name="districtId" value="${customer.districtId!''}"/>

  <dl>
    <dt>所在地区</dt>
    <dd>
      <a href="javascript:;" class="choose_area" onclick="loadProvice('${customer.provinceId!''}');">
        <span class="local" style="padding: 5px;">${customer.contactAddr?substring(0,customer.contactAddr?index_of(customer.addr))}</span>
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>详细地址</dt>
    <dd>
      <textarea rows="4" class="text" name="addr" id="addressDetail" style="height: 3.0em;padding: 5px;">${customer.addr!''}</textarea>
    </dd>
  </dl>
</div>
</form>
<div class="p10">
  <div class="col-12">
    <div class="pr15">
      <a href="javascript:;" class="btn btn-full-grey" onClick="javascript :history.back(-1);">取消</a>
    </div>
  </div>
  <div class="col-12">
    <div class="pl15">
      <a href="javascript:void(0)" class="btn btn-full" onclick="checkForm()">保存</a>
    </div>
  </div>
</div>

<div class="screen area-box-lv1" style="display:none;">
  <div class="screen-header">
    <a class="back" href="javascript:;" id="back">取消</a>
    请选择省
  </div>
  <div class="screen-cont">
    <div class="lists province">
      <dl id="prodl">
      </dl>
    </div>
  </div>
</div>

<div class="screen area-box-lv2" style="display:none;">
  <div class="screen-header">
    <a class="back" href="javascript:;" onclick="back1();"><i class="back-icon"></i></a>
    请选择市
  </div>
  <div class="current-area">
    已选择：
    <span id="readypro"></span>
  </div>
  <div class="screen-cont">
    <div class="lists city">
      <dl id="citydl">
      </dl>
    </div>
  </div>
</div>

<div class="screen area-box-lv3" style="display:none;">
  <div class="screen-header">
    <a class="back" href="javascript:;" onclick="back2();"><i class="back-icon"></i></a>
    请选择地区
  </div>
  <div class="current-area">
    已选择：
    <span id="readycity"></span>
  </div>
  <div class="screen-cont">
    <div class="lists district">
      <dl id="areadl">
      </dl>
    </div>
  </div>
</div>
<div class="foot" style="height: 100px;">
</div><!-- /foot -->

<script>
  $(function(){

    /* 选择地区 */
    $('.choose_area').click(function(){
      $('body').append('<div class="opacity-bg-3"></div>');
      $('.area-box-lv1').show();
    });

    $('#back').click(function(){
      $('.opacity-bg-3').remove();
      $('.screen').hide();
      $("#provinceCh").val("");
    });

    /* 滑动选框 */
    $('.checkbox').click(function(){
      $(this).toggleClass('selected');
       if ($('#defaultDiv').hasClass('selected')) {
            $("#isDefault1").val('1');
      }else{
            $("#isDefault1").val('0');
      }
    });
  });
  //验证表单
      function checkForm(){
           var bl=true;
          //详细地址
          if($("#addressDetail").val().trim()==''){
               $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>请填写详细地址</h3></div></div>');
                setTimeout(function(){
                    $('.tip-box').remove();
                },1000);
              bl=false;
          }else if($("#addressDetail").val().trim().length>100){
               $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>详细地址请不要多于100字符</h3></div></div>');
                setTimeout(function(){
                    $('.tip-box').remove();
                },1000);
              bl=false;
          }
          if(bl){
              //添加收件地址
              $("#updateAddress").submit();
          }
      }


      $(".bar-bottom a:eq(4)").addClass("selected");
</script>
</body>
 <script src="${basePath}/static/js/myacountaddress.js"></script>
</html>




<#--<!DOCTYPE html>-->
<#--<html>-->
<#--<head>-->
<#--<#assign basePath=request.contextPath>-->
    <#--<meta charset="UTF-8">-->
    <#--<title>个人资料 - 编辑手机号码</title>-->
    <#--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">-->
    <#--<meta content="telephone=no" name="format-detection">-->
    <#--<link rel="stylesheet" href="${basePath}/static/bootstrap/css/bootstrap.min.css">-->
    <#--<link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>-->
    <#--<link rel="stylesheet" href="${basePath}/static/css/myUb.css"/>-->
    <#--<link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>-->
    <#--<script src="${basePath}/static/js/jquery-1.10.1.js"></script>-->
    <#--<script src="${basePath}/static/js/idangerous.swiper.js"></script>-->
    <#--<script src="${basePath}/static/js/customer/customer_share.js"></script>-->
    <#--<script src="${basePath}/static/js/dialog-min.js"></script>-->
    <#--<style>-->
        <#--.passContainer{-->
            <#--border:none;-->
        <#--}-->
    <#--</style>-->
<#--</head>-->
<#--<body>-->
<#--<input type="hidden" value="${basePath}" id="basePath">-->

<#--<form action="${basePath}/updateAddress" id="updateAddress" method="post">-->
    <#--<div class="member_form">-->
        <#--<input type='hidden' id="provinceCh" name="provinceId" value="${customer.provinceId!''}"/>-->
        <#--<input type='hidden' id="cityCh" name="cityId" value="${customer.cityId!''}"/>-->
        <#--<input type='hidden' id="countyCh" name="districtId" value="${customer.districtId!''}"/>-->

        <#--<dl>-->
            <#--<dt>手机号码</dt>-->
            <#--<dd>-->
                <#--<textarea rows="4" class="text" name="addr" id="phoneInput" style="height: 3.0em;padding: 5px;" placeholder="${customer.phoneNo}">${customer.phoneNo!''}</textarea>-->
            <#--</dd>-->
        <#--</dl>-->
    <#--</div>-->
<#--</form>-->
<#--<div class="p10">-->
<#--&lt;#&ndash;<div class="col-12">&ndash;&gt;-->
<#--&lt;#&ndash;<div class="pr15">&ndash;&gt;-->
<#--&lt;#&ndash;<a href="javascript:;" class="btn btn-full-grey" onClick="javascript :history.back(-1);">取消</a>&ndash;&gt;-->
<#--&lt;#&ndash;</div>&ndash;&gt;-->
<#--&lt;#&ndash;</div>&ndash;&gt;-->
<#--<#if customer.phoneNo == ''>-->
    <#--<div class="col-24">-->
        <#--<div class="pl15">-->
            <#--&lt;#&ndash;<a class="btn btn-full" onclick="checkForm1()">确定</a>&ndash;&gt;-->
            <#--&lt;#&ndash;<button class="btn btn-full" type="button" onclick="checkForm1()">确定</button>&ndash;&gt;-->
                <#--<a href="javascript:void(0)" class="btn btn-full" onclick="checkForm1()">确定</a>-->
        <#--</div>-->
    <#--</div>-->
<#--<#else>-->
    <#--<div class="col-24">-->
        <#--<div class="pl15">-->
            <#--<a href="javascript:void(0)" class="btn btn-full" onclick="checkForm2()">更换手机号</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</#if>-->

<#--</div>-->

<#--<div class="foot" style="height: 100px;">-->
<#--</div><!-- /foot &ndash;&gt;-->

<#--<script>-->
    <#--$(function(){-->

    <#--});-->


    <#--//    验证手机号码格式-->
    <#--function checkMobile(str) {-->
        <#--if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|177)[0-9]{8}$/.test(str))) {-->
            <#--alertStr("手机号码格式错误，请重新输入！");-->
            <#--return false;-->
        <#--}-->
        <#--else {-->
            <#--return true;-->
        <#--}-->
    <#--}-->
    <#--//新增绑定的手机号-->
    <#--function checkForm1(){-->
        <#--checkMobile($('#phoneInput').val());-->
        <#--if(checkMobile($('#phoneInput').val()) == true){-->
            <#--$.post('${basePath}/checkExistsPayPassWord', {}, function (tt) {-->
                <#--if (tt.response == "success") {-->
                    <#--var zhifumima = dialog({-->
                        <#--width: 260,-->
                        <#--content: '<p class="tc">输入支付密码</p><p class="tc"><div class="passContainer"> <div class="input-box"> <input type="number" value="" class="passwordInput" maxlength="6"/> </div> <div class="passItem-box"> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div></div> </div></p> ',-->
                        <#--okValue: '确定',-->
                        <#--cancelValue: '取消',-->
                        <#--ok: function () {-->
                            <#--$.ajax({-->
                                <#--type: "POST",-->
                                <#--url: "goPhoneNo",-->
                                <#--data: {-->
                                    <#--paykey:$(".passwordInput").val()-->
                                <#--},-->
                                <#--dataType: "json",-->
                                <#--traditional: true,-->
                                <#--success: function(data){-->
                                    <#--if(data.response == "success"){-->
                                        <#--$.post('updateMaleAndPhone',{-->
                                                    <#--phontNo:$("#phoneInput").val()-->
                                                <#--},-->
                                                <#--function(data){-->
                                                    <#--if(data.response == "success"){-->
<#--//                                                        window.reload();-->
                                                        <#--window.location.href = "${basePath}/account";-->
                                                    <#--}else {-->
                                                        <#--alertStr(data.data.text);-->
                                                    <#--}-->
                                                <#--},'json');-->
                                    <#--}else {-->
                                        <#--var text = data.data.text;-->
                                        <#--alertStr(text);-->
                                        <#--setTimeout(function(){-->
                                            <#--window.location.href = "${basePath}/main";-->
                                        <#--},1000);-->

                                    <#--window.location.href = "${basePath}/account"-->
                                    <#--}-->
                                <#--}-->
                            <#--});-->
                        <#--},-->
                        <#--cancel: function () {-->
                            <#--//停留在当前页面-->
                            <#--return true;-->
                        <#--}-->
                    <#--});-->

                    <#--zhifumima.showModal();-->

                    <#--$('.passContainer').click(function () {-->
                        <#--$('.passwordInput').focus();-->
                    <#--});-->

                    <#--$('.passwordInput').bind("input propertychange change", function (event) {-->
                        <#--if (event.type != "propertychange" || event.originalEvent.propertyName == "value") {-->
                            <#--if ($('.passwordInput').val().length > 6) {-->
                                <#--$('.passwordInput').val($('.passwordInput').val().substr(0, 6));-->
                            <#--}-->

                            <#--$('.passContainer .passItem-box .passItem').html("");-->
                            <#--for (var i = 0; i < $('.passwordInput').val().length; i++) {-->
                                <#--$('.passContainer .passItem-box .passItem').eq(i).html("*");-->
                            <#--}-->
                        <#--}-->
                    <#--});-->


                    <#--$('.passwordInput').focus();-->

                <#--} else {-->
                    <#--var shezhimima = dialog({-->
                        <#--width: 260,-->
                        <#--content: '<p class="tc">去设置支付密码</p> ',-->
                        <#--okValue: '去设置支付密码',-->
                        <#--cancelValue: '取消',-->
                        <#--ok: function () {-->
                        <#--window.location.href = '${basePath}/account';-->
                        <#--},-->
                        <#--cancel: function () {-->
                            <#--//停留在当前页面-->
                            <#--return true;-->
                        <#--}-->
                    <#--});-->
                    <#--shezhimima.showModal();-->
                <#--}-->
            <#--});-->



<#--//            $.post('updateMaleAndPhone',{-->
<#--//                        phoneNo:$('#phoneInput')-->
<#--//                    },-->
<#--//                    function(data){-->
<#--//                        if(data.response == "success"){-->
<#--//                            window.reload();-->
<#--//                        }else {-->
<#--//                            alertStr(data.data.text);-->
<#--//                        }-->
<#--//            },'json');-->
        <#--}-->
    <#--}-->

    <#--//重新绑定的手机号-->
    <#--function checkForm2(){-->
        <#--checkMobile($('#phoneInput').val());-->
        <#--if(checkMobile($('#phoneInput').val()) == true){-->
            <#--$.post('${basePath}/checkExistsPayPassWord', {}, function (tt) {-->
                <#--if (tt.response == "success") {-->
                    <#--var zhifumima = dialog({-->
                        <#--width: 260,-->
                        <#--content: '<p class="tc">输入支付密码</p><p class="tc"><div class="passContainer"> <div class="input-box"> <input type="number" value="" class="passwordInput" maxlength="6"/> </div> <div class="passItem-box"> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div> <div class="passItem"></div></div> </div></p> ',-->
                        <#--okValue: '确定',-->
                        <#--cancelValue: '取消',-->
                        <#--ok: function () {-->
                            <#--$.ajax({-->
                                <#--type: "POST",-->
                                <#--url: "goPhoneNo",-->
                                <#--data: {-->
                                    <#--paykey:$(".passwordInput").val(),-->
<#--//                                    phontNo:$("#phoneInput").val()-->
                                <#--},-->
                                <#--dataType: "json",-->
                                <#--traditional: true,-->
                                <#--success: function(data){-->
                                    <#--if(data.response == "success"){-->
                                        <#--&lt;#&ndash;window.location.href = "${basePath}/account";&ndash;&gt;-->
                                        <#--var reasonBox = dialog({-->
                                            <#--width : 260,-->
                                            <#--title : '请输入需要绑定的手机号',-->
                                            <#--content : '<form id="upcellphone"><input type="text" placeholder="请输入手机号码..." style="width: 60%; margin-left: 20%;" id="cellphoneinput"><input type="hidden" value="" id="cellphonehidden" name="phoneNo"></form>',-->
                                            <#--okValue : '确定',-->
                                            <#--cancelValue : '取消',-->
                                            <#--ok : function(){-->
                                                <#--var cellphone = $('#cellphoneinput').val();-->
                                                <#--if(checkMobile(cellphone)){-->
                                                    <#--$('#cellphonehidden').val(cellphone);-->
                                                    <#--$("#upcellphone").attr("action","${basePath}/updateMaleAndPhone");-->
                                                    <#--$("#upcellphone").submit();-->
                                                    <#--return true;-->
                                                <#--}-->
                                            <#--},-->
                                            <#--cancel : function(){-->
                                                <#--return true;-->
                                            <#--}-->
                                        <#--});-->
                                        <#--reasonBox.showModal();-->
                                    <#--}else {-->
                                        <#--var text = data.data.text;-->
                                        <#--alertStr(text);-->
                                        <#--setTimeout(function(){-->
                                            <#--window.location.href = "${basePath}/account";-->
                                        <#--},1000);-->

                                    <#--}-->
                                <#--}-->
                            <#--});-->
                        <#--},-->
                        <#--cancel: function () {-->
                            <#--//停留在当前页面-->
                            <#--return true;-->
                        <#--}-->
                    <#--});-->

                    <#--zhifumima.showModal();-->

                    <#--$('.passContainer').click(function () {-->
                        <#--$('.passwordInput').focus();-->
                    <#--});-->

                    <#--$('.passwordInput').bind("input propertychange change", function (event) {-->
                        <#--if (event.type != "propertychange" || event.originalEvent.propertyName == "value") {-->
                            <#--if ($('.passwordInput').val().length > 6) {-->
                                <#--$('.passwordInput').val($('.passwordInput').val().substr(0, 6));-->
                            <#--}-->

                            <#--$('.passContainer .passItem-box .passItem').html("");-->
                            <#--for (var i = 0; i < $('.passwordInput').val().length; i++) {-->
                                <#--$('.passContainer .passItem-box .passItem').eq(i).html("*");-->
                            <#--}-->
                        <#--}-->
                    <#--});-->


                    <#--$('.passwordInput').focus();-->

                <#--} else {-->
                    <#--var shezhimima = dialog({-->
                        <#--width: 260,-->
                        <#--content: '<p class="tc">去设置支付密码</p> ',-->
                        <#--okValue: '去设置支付密码',-->
                        <#--cancelValue: '取消',-->
                        <#--ok: function () {-->
                        <#--window.location.href = '${basePath}/account';-->
                        <#--},-->
                        <#--cancel: function () {-->
                            <#--//停留在当前页面-->
                            <#--return true;-->
                        <#--}-->
                    <#--});-->
                    <#--shezhimima.showModal();-->
                <#--}-->
            <#--});-->
        <#--}-->
    <#--}-->


<#--</script>-->
<#--</body>-->
<#--<script src="${basePath}/static/js/myacountaddress.js"></script>-->
<#--</html>-->
