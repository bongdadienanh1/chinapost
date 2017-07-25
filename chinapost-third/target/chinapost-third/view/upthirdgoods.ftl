<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商城第三方后台-货品管理</title>
<#assign basePath=request.contextPath />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/third.css?version=${VERSION}"/>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css?v=20151016234"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/material.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/ripples.css?v=20151016234" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/css/style.css?version=${VERSION}"/>
    <link rel="stylesheet" href="${basePath}/static/css/select2.min.css"/>
    <#--<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js?v=20151016234"></script>-->
    <script type="text/javascript" src="${basePath}/static/js/jquery.min.js?v=20151016234"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/ripples.min.js?v=20151016234"></script>
	<script src="${basePath}/static/js/material.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/third.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/goods_vali.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/thirdgoods.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/newupload.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/plugins/iframeTools.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/jquery.slimscroll.min.js?v=20151016234"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/summernote.css?v=20151016234">
    <script type="text/javascript" src="${basePath}/static/js/summernote.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/summernote-zh-CN.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/jquery.validate.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/common/common_alert.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/common/common_date.js?version=${VERSION}"></script>
    <script src="${basePath}/static/js/app.js?v=20151016234"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/goodsAdd.css?version=${VERSION}"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css?v=20151016234"/>
    <link rel="stylesheet" href="${basePath}/static/css/bootstrap-chosen.css?v=20151016234"/>
    <script src="${basePath}/static/js/chosen.jquery.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/angular.min.js?v=20151016234"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/kindeditor.js"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/plugins/code/prettify.js"></script>
    <script src="${basePath}/static/js/select2.min.js"></script>
    <style>
        .m_error{
            color: #a94442;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<#--引入头部-->
<#include "index/indextop.ftl">

<!-- 保存货品的表单 -->
<form class="save_goods_info" action="sathirdgoods.htm" method="post" target="hidden_frame"
      enctype="multipart/form-data">
</form>

<!-- 模拟无刷新上传图片用到的 -->
<iframe name="hidden_frame" style="display:none"></iframe>

<!-- 保存货品详情的表单 -->
<form class="save_goods_desc" action="tNewUploadSaveGoodsDesc.htm" method="post" target="hidden_frame"
      enctype="multipart/form-data">
    <input type="hidden" class="new_goods_id" name="goodsId" value="">
    <input type="hidden" class="goods_desc" name="goodsDetailDesc" value="">
    <input type="hidden" class="goods_mobile_desc" name="goodsMobileDesc" value="">
</form>


<div class="wp">
    <input type="hidden" value="${isThirdAuditUsed!''}" id="isThirdAuditUsed"/>
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>货品管理</li>
                <li class="active" style="color: #07d;">上传货品</li>
            </ol>

            <div class="app-content step_wp step_01">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">添加货品</a>
                    </li>
                </ul>

                <div>
                    <div class="cfg-content">
                        <div class="sorts-wp">
                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="hidden" id="parentId1">
                                    <input type="text" placeholder="输入名称查找" id="firstText"/>
                                    <button type="button"><i class="glyphicon glyphicon-search"
                                                             onclick="searfirst()"></i></button>
                                </div>
                                <div class="cate-list">
                                    <ul class="cate-items cat_first" id="cat_first">
                                    </ul>
                                </div>
                            </div>

                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="hidden" id="parentId2">
                                    <input type="text" placeholder="输入名称查找" id="secondText"/>
                                    <button type="button"><i class="glyphicon glyphicon-search"
                                                             onclick="searSecond();"></i></button>
                                </div>
                                <div class="cate-list">
                                    <ul class="cate-items cat_second" id="cat_second">
                                    </ul>
                                </div>
                            </div>

                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="hidden" name="catId" id="parentId3" class="ch_goods_cate">
                                    <input type="text" placeholder="输入名称查找" id="thirdText"/>
                                    <button type="button"><i class="glyphicon glyphicon-search"
                                                             onclick="searThird();"></i></button>
                                </div>
                                <div class="cate-list">
                                    <ul class="cate-items cat_third" id="cat_third">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <p class="selected-sorts">
                            当前选择的是：
                            <b><span class="firstCate">请选择货品所属分类</span></b>
                            <b><span class="secondCate"></span></b>
                            <b><span class="thirdCate"></span></b>
                            <><span class="fouthCate"></span></

                        </p>
                        <#--<p ><a  href="cateManager.html?n=3&l=47" style="color:blue;font-size: 15px;">添加货品分类</a></p>-->
                        <div class="add-sorts-operation">
                        <#--<#if haveFreight=="1">-->
                            <a class="btn btn-primary step_next" data-role="step_first">下一步</a>
                        <#--</#if>-->
                        <#if haveFreight=="0">
                            <a class="btn btn-primary " data-role="step_first">下一步</a>
                            你还没有添加一个默认模板，请先添加默认<a href="freighttemplatelistthird.htm?n=2&l=92">运费模板</a>
                        </#if>
                        </div>
                    </div>
                </div>
            </div>
            <!--/step_01-->

            <!-- --------------------------------------------------------------------------------------- -->

            <div class="app-content step_wp step_02 none">
                <input type="hidden" name="thirdCateId" class="ch_third_catid" value="-1"/>

                <div class="form-container">
                    <h3 class="fm-tit">基本信息</h3>

                    <div class="form-horizontal">
                        <form class="form-horizontal" id="goods_info_form" action="" method="post">
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>货品标题：</label>

                                <div class="controls col-xs-8">
                                    <input type="text" name="goodsName"
                                           class="form-control name_input required"
                                           maxlength="50" minlength="3" value="<#--${importGoods.goodsName }-->"
                                           onblur="$('.name_input').val($(this).val())"/>
                                </div>
                            </div>
                            <#--<div class="form-group">-->
                                <#--<label class="control-label col-xs-2"><b>*</b>商城价（邮豆）:</label>-->

                                <#--<div class="controls col-xs-2">-->
                                <#--<div class="input-group">-->
                                    <#--<input type="text" name="goodsPrice"-->
                                           <#--class="form-control sm-input pro_price required floatingDecimal"/>-->
                                <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>单位：</label>

                                <div class="controls col-xs-2">
                                    <div class="input-group">
                                        <input type="text" name="goodsInfoUnit"
                                               maxlength="4"
                                               class="form-control sm-input required proUnit"
                                               onblur="$('.proUnit').val($(this).val())" onchange="getInputVal(this)"/>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>是否上架：</label>
                                <div class="controls col-xs-8">
                                    <div class="radio radio-primary">
                                        <label class="choose-label"><input name="goodsAdded" type="radio"
                                                                           class="pro_status" value="0"
                                                                           onclick="$('.up_not').click();"/>下架</label>
                                        <label class="choose-label"><input name="goodsAdded" type="radio"
                                                                           class="pro_status"
                                                                           value="1"
                                                                           checked="checked"
                                                                           onclick="$('.up_yes').click();"/>立即上架</label>
                                    </div>
                                </div>
                            </div>

                            <#--<div class="form-group">-->
                                <#--<label class="control-label col-xs-2"><b>*</b>货品品牌：-->

                                <#--</label>-->

                                <#--<div class="controls col-xs-8 brandSelect">-->
                                        <#--<select class="form-control required" data-live-search="true"-->
                                                <#--name="brandId"-->
                                                <#--id="goods_brand" >-->
                                        <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>货品品牌：</label>

                                <div class="controls col-xs-8 brandSelect">
                                    <div class="input-group">
                                        <select class="form-control " data-live-search="true" name="brandId" id="goods_brand" style="width: 160px;" onchange="changeGoodsBrand()"></select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div style="overflow: hidden">
                                    <label class="control-label col-xs-2"><b>*</b>规格：</label>

                                    <div class="controls col-xs-10">
                                        <input class="btn btn-primary" type="button" value="选择规格" onclick="getSpecShowModle()"/>
                                    </div>
                                </div>
                                <div class="goodsSpecInfo">
                                    <label class="control-label col-xs-2"></label>
                                    <div class="col-xs-10 goodsSpecInfoName"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">移动版详情：</label>
                                        <textarea name="mobile_desc" cols="100" rows="8"
                                                  style="width: 100%; height: 200px; visibility: hidden;"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="add-sorts-operation">
                        <a class="btn btn-default prev_step" type="button"
                           href="javascript:;">上一步
                        </a>
                        <a class="btn btn-primary step_next" type="button"
                           data-role="step_second" href="javascript:;">下一步
                        </a>
                    </div>
                </div>
            </div>

            <div class="app-content step_wp step_03 none">
                <div class="detail_info form-container">
                    <ul class="dinfo_tabs nav nav-tabs" role="tablist">

                    </ul>
                    <!--/dinfo_tabs-->
                    <div class="tab-content dinfo_wp">

                    </div>
                    <!--/dinfo_wp-->
                    <div class="dinfo_box demo_box">
                        <div class="tab-content">
                            <div class="demo_box demoHtml" style="display:none;">
                                <div role="tabpanel" class="tab-pane" id="">
                                    <form class="form-horizontal goodsInfoForm" method="post">
                                        <div>
                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>货品编号：</label>

                                                <div class="controls col-xs-7">
                                                	<div class="input-group">
                                                		<input type="text" class="form-control p_code no_input required"
                                                           value="<#--${importGoods.goodsNo }-->"
                                                           maxlength="32"
                                                           minlength="10" onblur="checkProNo(this);" name="goodsInfoItemNo">
                                                           <a href="javascript:;" class="input-group-addon text-primary"
                                                       onclick="generateProNo(this)">生成编号</a>
                                                	</div>

                                                    <input type="hidden" class="exist_flag" value="0">
                                                </div>
                                            </div>

                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>货品标题：</label>

                                                <div class="controls col-xs-7">
                                                    <input type="text" name="goodsInfoName" class="form-control name_input required"
                                                           maxlength="50" minlength="3"
                                                           value=""
                                                           onblur="checkProductForm()">
                                                </div>
                                            </div>
                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><b>*</b>商城价（邮豆）:</label>

                                                <div class="controls col-xs-7">
                                                    <div class="input-group">
                                                    <input type="text" name="goodsInfoPreferPrice"
                                                           onkeyup="value=value.replace(/\.\d{2,}$/,value.substr(value.indexOf('.'),3))"
                                                           class="form-control sm-input pro_price required floatingDecimal"
                                                           onblur="changeAllPrice(this,'pro_price');"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><b>*</b>结算价（RMB）:</label>

                                                <div class="controls col-xs-7">
                                                    <div class="input-group">
                                                    <input name="goodsInfoSettlePrice" type="text"
                                                           onkeyup="value=value.replace(/\.\d{2,}$/,value.substr(value.indexOf('.'),3))"
                                                           class="form-control sm-input goodsInfoSettlePrice required floatingDecimal"
                                                           onblur="changeAllPrice(this,'goodsInfoSettlePrice');"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><b>*</b>库存:</label>

                                                <div class="controls col-xs-7">
                                                    <div class="input-group">
                                                        <input name="goodsInfoStock" type="text"
                                                               class="form-control goodsInfoStock sm-input required"
                                                               onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                                               onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                                                onblur="changeAllStock(this,'goodsInfoStock')"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <#--<div class="form-group" style="overflow: hidden">-->
                                                <#--<label class="control-label col-xs-3">运费：</label>-->

                                                <#--<div class="controls col-xs-7">-->
                                                    <#--<div class="input-group">-->
                                                        <#--<input name="freightPrice" type="text"-->
                                                               <#--class="form-control sm-input freightPrice required floatingDecimal"-->
                                                               <#--onblur="changeAllPrice(this,'freightPrice');"/>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</div>-->

                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>货品图片：</label>

                                                <div class="controls col-xs-3">
                                                    <button type="button" class="btn btn-primary chooseProimg">
                                                        编辑货品图片
                                                    </button>
                                                </div>
                                                <div class="col-xs-3">
                                                    <a href="javascript:;" class="productsImg help_tips" style="  background: #09e;width: 16px;height: 16px;display: inline-block;border-radius: 50%;text-align: center;color: #fff;margin: 20px;">
                                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true" ></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="form-group" style="overflow: hidden">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>已选择图片：</label>
                                                <div class="controls col-xs-7">
                                                    <ul class="choose_imgs"></ul>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="add_good_sep">
                        <div></div>
                    </div>

                    <div class="add-sorts-operation">
                        <a class="btn btn-default prev_step"
                           href="javascript:;">上一步
                        </a>
                        <a class="btn btn-primary info_sub" onclick="up_goods_info()" href="javascript:;">发布货品</a>
                    </div>
                </div>
                <!--/detail_info-->
            </div>
        </div>
    </div>
</div>
<#--</form>-->
<#include "common/leftfooter.ftl">

<#--没有选中行提示框-->
<div class="modal fade" id="select-tip" role="dialog" style="z-index: 99999999">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="show_title">请至少选择一行！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal 添加规格 -->
<div class="modal fade" id="goodSpecModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title specTitle">添加规格</h4>
            </div>
            <div class="modal-body m-goods-body">
                <div class="form-group" style="overflow: hidden">
                    <label class="control-label col-xs-3"><b>*</b>规格名称：</label>
                    <div class="controls col-xs-7">
                        <select  class="form-control" style="width: 200px;" id="goodsSpecName" name="goodsSpecName" data-live-search="true" onchange="getSpecInfo(this)">
                        </select>
                    </div>
                </div>
                <div class="goodsSpeaInfo type_spec"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary updateExpress2" onclick="addsGoodsSpecInfo()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<#--<script type="text/javascript" src="${basePath}/static/js/navmenu.js?v=20151016234"></script>-->
<script>
    $(function(){
    	$.material.init();
        $(".chosen-select").chosen();
        $(".brandsChosen").chosen();
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

    function getInputVal(obj){
        $("#basic-proPack").html($(obj).val()+"/箱");
    }
</script>
<#--<#import "common/selectindex.ftl" as selectindex>-->
<#--<@selectindex.selectindex "${n!''}" "${l!''}" />-->

</body>
</html>


