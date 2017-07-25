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