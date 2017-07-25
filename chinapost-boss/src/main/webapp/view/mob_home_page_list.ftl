<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>移动站点信息</title>

    <!-- Bootstrap -->
    <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
	<style type="text/css">
	.current_template,.template_list{margin-bottom:20px;}
.current_template h4,.template_list h4{border-left:2px solid #0099FF;padding-left:10px;font-size:14px;}
.current_template .body{position:relative;border:1px solid #ddd;padding:10px;}
.current_template .body .pre_info{margin-left:20px;}
.current_template .body .pre_info h5{margin-top:0;font-size:16px;}
.current_template .body .pre_btns{position:absolute;width:100px;bottom:10px;left:210px;}

.template_item_mobile{position:relative;float:left;margin:0 10px 10px 0;padding:10px;border:1px solid #ddd;}
.template_item_mobile .ctrl{position:absolute;left:0;bottom:0;width:100%;height:50px;background:rgba(0,0,0,0.5);padding:10px 0;}
.template_item_mobile .ctrl p{color:#fff;text-indent:10px;}
.template_item_mobile .ctrl .ctrl_btns{display:none;}
.add-template a {display:block; width:180px; height:240px; background:url(${bath}/static/images/mb_add.png) no-repeat center center;}
	
	</style>
  </head>
  <body>
   
     <!-- 引用头 -->
     <#include "page/header.ftl"/>
   
    <div class="page_body container-fluid">
      <div class="row">
      <#include "page/left.ftl"/>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
          <#include "page/breadcrumbs.ftl"/>

            <div class="common_data" style="padding-left:20px;">


		 <div class="tab-content">
            <div class="tab-pane active" id="home">
              <div class="current_template">
                <h4>当前主页模板</h4>
                <div class="body">
                  <div class="pre_img pull-left">
                    <img width="180" height="240" alt="" src="${currHomePage.homeImg}">
                  </div>
                  <div class="pre_info pull-left">
                    <h5>${currHomePage.title}</h5>
                    <p><span class="text-muted">作者：</span>${currHomePage.author}</p>
                    <p><span class="text-muted">简介：</span>${currHomePage.homeDesc}</p>
                  </div>
                  <div class="clr"></div>
                  <div class="pre_btns">
                  	<a href="setMobHomePage.htm?merchantId=${currHomePage.merchantId }&homePageId=${currHomePage.homepageId}&flag=${flag}"
                  		class="btn btn-primary btn-sm btn-block">编辑</a>
                    <!-- <button class="btn btn-primary btn-sm btn-block">编辑</button> -->
                  </div>
                </div>
              </div>

              <div class="template_list">
                <h4>店铺主页模板</h4>
                <div class="body">
                <#list homePages as homePage>
                  <div class="template_item_mobile">
                    <img width="180" height="240" alt="" src="${homePage.homeImg}">
                    <div class="ctrl">
                      <p>${homePage.title}</p>
                      <div class="ctrl_btns container-fluid">
                        <div class="row">
                          <div class="col-xs-8">
                            <a href="openHomePage.htm?merchantId=${merchantId }&homePageId=${homePage.homepageId}" class="btn btn-primary btn-sm btn-block">启用</a>
                            <!-- <button class="btn btn-primary btn-sm btn-block">启用</button> -->
                          </div>
                          <div class="col-xs-8">

                          	<a href="setMobHomePage.htm?merchantId=${merchantId }&homePageId=${homePage.homepageId}&flag=${flag}"
                  		class="btn btn-default btn-sm btn-block">编辑</a>
                            <!-- <button class="btn btn-default btn-sm btn-block">编辑</button> -->
                          </div>

                          <div class="col-xs-8">
                            <a id="delHomePage"  class="btn btn-default btn-sm btn-block"onclick="delHomePage(${homePage.homepageId})">删除</a>
                            <!-- <button class="btn btn-primary btn-sm btn-block">启用</button> -->
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </#list>
                  <div class="template_item_mobile add-template">
                  	<a href="setMobHomePage.htm?merchantId=${merchantId }&flag=${flag}">

                  	</a>
                      </div>
                    </div>
                  </div>


                  <div class="clr"></div>

                </div>
              </div>

            </div>

          </div>
        </div>
      </div>
    </div>







    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
    <#--<script src="${bath}/static/js/summernote.min.js"></script>-->
    <#--<script src="${bath}/static/js/language/summernote-zh-CN.js"></script>-->
    <#--<script src="${bath}/static/js/bootstrap-select.min.js"></script>-->
    <#--<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>-->
    <#--<script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>-->
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
   <script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
    <script type="text/javascript">
  	$(function(){
  	    
  		$('.template_item_mobile').mouseenter(function() {
			$(this).find('.ctrl p').hide();
			$(this).find('.ctrl .ctrl_btns').show();
		});
		$('.template_item_mobile').mouseleave(function() {
			$(this).find('.ctrl p').show();
			$(this).find('.ctrl .ctrl_btns').hide();
		});
  	});
  	
  	function delHomePage(homepageId){
  	   showDeleteOneConfirmAlert('deleteHomePage.htm?CSRFToken=${token}&homePageId='+homepageId,'确定要删除此模板吗？');  
  	}
  
    </script>
  </body>
</html>