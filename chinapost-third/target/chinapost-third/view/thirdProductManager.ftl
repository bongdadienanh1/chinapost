<!DOCTYPE html>
<html>
<head>
    <title>商城第三方后台-货品列表</title>
<#assign basePath=request.contextPath />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css?v=20151016234"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/material.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/ripples.css?v=20151016234" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/css/style.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/select2.min.css"/>
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/third.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/goods_vali.js?v=20151016234"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js?v=20151016234"></script>
    <script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js?v=20151016234"></script>
    <script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/ripples.min.js?v=20151016234"></script>
	<script src="${basePath}/static/js/material.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/plugins/iframeTools.js?v=20151016234"></script>
    <script src="${basePath}/static/js/app.js?v=20151016234"></script>
    <script src="${basePath}/static/js/select2.min.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/jquery.validate.js?v=20151016234"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/kindeditor.js"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${basePath}/static/js/kindeditor/plugins/code/prettify.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/goods/thirdProductManager.js?version=${VERSION}"></script>
    <style>
        .none {
            display: none;
        }
        .choose_imgs li .del_ts {position:absolute; top:-3px; right:-5px;}
        .choose_imgs li {float:left; margin:0 20px 20px 0;position:relative;}
        .brandSelect .select2-container{z-index: 100 !important;}
        .select2-container--default .select2-selection--single{background-color: rgba(0, 0, 0, 0)!important;}
    </style>
</head>

<body>
<!-- 验证存在的标记变量  start-->
<input type="hidden" class="checkExistsFlag" value="1"/>
<!-- 验证规格参数是否可用的标记变量  start-->
<input type="hidden" class="checkProdcutExists" value="1"/>
<!-- end -->
<input type="hidden" value="${basePath}" id="basPath">
<!-- end -->
<#--引入头部-->
<#include "index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>货品管理</li>
                <li class="active">货品审核列表</li>
            </ol>
            <div class="group_wp">
                <form class="search_product_form" action="getGoodsInfo" method="post">
                    <input type="hidden" id="page" name="page"/>
                    <input type="hidden" id="size" name="size" value="10"/>
                    <input type="hidden" id="auditStatus" name="auditStatus" value="${auditStatus}"/>

                    <div class="search-block">
                        <div class="filter-groups">
                            <div class="control-group">
                                <label class="control-label">货品名称：</label>

                                <div class="controls">
                                    <input class="form-control" type="text"
                                           value="${goodsInfoName!''}"
                                           name="goodsInfoName"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">货品编码：</label>

                                <div class="controls">
                                    <input class="form-control" type="text"
                                           value="${goodsInfoItemNo!''}"
                                           name="goodsInfoItemNo"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">是否上架：</label>

                                <div class="controls">
                                    <select class="form-control" name="goodsInfoAdded">
                                        <option value=""></option>
                                        <option value="0" <#if goodsInfoAdded==0>selected="selected"</#if>>否</option>
                                        <option value="1" <#if goodsInfoAdded==1>selected="selected"</#if>>是</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">货品品牌：
                                </label>
                                <div class="controls brandSelect">
                                    <input type="hidden" value="${brandId!''}" id="brandIdInput">
                                    <select class="form-control required" data-live-search="true"
                                            name="brandId"
                                            id="goods_brand" style="width: 160px;">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="search-operation">
                            <button class="btn btn-primary btn-sm query_btn" onclick="searchData()">查询<i
                                    class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </form>
                <div class="mt20 clearfix"></div>
                <ul class="nav nav-tabs">
                    <li class="tb_barterOrder <#if auditStatus == '1'>active</#if>" onclick="changTbl(1)"><a href="javascript:;">已审核</a></li>
                    <li class="tb_backOrderSH <#if auditStatus == '0'>active</#if>" onclick="changTbl(0)"><a href="javascript:;">未审核</a></li>
                    <li class="tb_backOrderSH <#if auditStatus == '2'>active</#if>" onclick="changTbl(2)"><a href="javascript:;">审核失败</a></li>
                </ul>
                <div class="cfg-content">
                    <div class="ops-bar"></div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="5%"><input type="checkbox" onclick="selectAll(this,'productId')"/></th>
                            <th>图片</th>
                            <th width="12%">货品名称</th>
                            <th width="10%">货品编号</th>
                            <th>销售价（邮豆）</th>
                            <th>结算价（RMB）</th>
                            <th>库存</th>
                            <th>是否上架</th>
                            <th>所属商家</th>
                            <th>邮费</th>
                            <th width="10%">品牌</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                    <#if (pageBean.content?size) &gt; 0 >
                        <form class="list_form" method="post">
                            <input type="hidden" name="goodsId" value="${map.goodsId}">
                            <tbody>
                                <#list pageBean.content as product>
                                    <#if product??>
                                    <tr>
                                        <td><input type="checkbox" class="ch_product" name="goodsInfoId"
                                                   value="${product.goodsInfoId}"/></td>
                                        <td><a href="javascript:;"><img alt="" width="30px" height="30px"
                                                                        src="<#if product.goodsInfoImgId??>${product.goodsInfoImgId}</#if>"/></a>
                                        </td>
                                        <td style="text-align:left;"><a href="http://youzheng.ylife.cn/mobile/productdetail?productId=${product.goodsInfoId}"
                                                                        style="color:#2177d4; line-height:180%;">
                                            <#if product.goodsInfoName??>
                                                <#if product.goodsInfoName?length   gt   8  >
                                                    <p title="${product.goodsInfoName}">${product.goodsInfoName?substring(0,8)}
                                                        ...</p>
                                                <#else>
                                                ${product.goodsInfoName}
                                                </#if>
                                            </#if>
                                        </a></td>
                                        <td>
                                            <#if product.goodsInfoItemNo?length   gt   10   >
                                                <p title="${product.goodsInfoItemNo}">${product.goodsInfoItemNo?substring(0,10)}
                                                    ...</p>

                                            <#else>
                                            ${product.goodsInfoItemNo}
                                            </#if>
                                        </td>
                                        <td><#if product.goodsInfoPreferPrice??>
                                            ${product.goodsInfoPreferPrice?string("0.00")}邮豆</#if></td>
                                        <td><#if product.goodsInfoSettlePrice??>
                                            ¥${product.goodsInfoSettlePrice?string("0.00")}</#if></td>
                                        <td>
                                            ${product.goodsInfoStock?string("0")}</td>
                                        <td>
                                            <#if product.goodsInfoAdded??>
                                                <#if product.goodsInfoAdded == 1>
                                                    <span class="label label-primary">是</span>
                                                <#else >
                                                    <span class="label label-default">否</span>
                                                </#if>
                                            </#if>
                                        </td>
                                        <td>${product.thirdName!''}</td>
                                        <td><#if product.freightPrice??>
                                        ${product.freightPrice?string("0.00")}邮豆</#if></td>
                                        <td>${product.goodsBrand!''}</td>
                                        <td>
                                            <div class="btn-group">
                                                <a class="btn modi-goods btn-primary btn-sm"
                                                   data-role="${product.goodsInfoId}" href="javascript:;"><i
                                                        class="icon"></i>编辑货品</a>
                                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                                                        data-toggle="dropdown" aria-expanded="false"
                                                        onclick="menu_btn(this)">
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="javascript:;"
                                                           onclick="showStock('${product.goodsInfoId}','${product.goodsInfoStock}');">修改库存</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    </#if>
                                </#list>
                            </tbody>
                        </form>
                    <#else>
                        <td colspan="12">
                            <center>暂无相关货品信息</center>
                        </td>
                    </#if>
                    </table>
                    <!--/pr_tb-->
                    <div class="footer-operation">
                        <#include "index/searchPag.ftl">
                    </div>
                </div>
            </div>
            <div class="app-content none">
                <div class="cfg-content step_wp">
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
                        <a class="btn btn-default" onclick="updateCancel()">取消</a>
                        <a class="btn btn-primary step_next" data-role="step_first">下一步</a>
                    <#--</#if>-->
                    <#if haveFreight=="0">
                        <a class="btn btn-primary " data-role="step_first">下一步</a>
                        你还没有添加一个默认模板，请先添加默认<a href="freighttemplatelistthird.htm?n=2&l=92">运费模板</a>
                    </#if>
                    </div>
                </div>
                <div class="cfg-content form-container step_wp none">
                    <h3 class="fm-tit">基本信息</h3>
                    <input type="hidden" class="ch_third_catid" value="">
                    <input type="hidden" id="goodsInfoId" value="">
                    <div class="form-horizontal">
                        <form class="form-horizontal" id="goods_info_form" action="" method="post">

                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>货品品牌：

                                </label>

                                <div class="controls col-xs-8 brandSelect">
                                    <select class="form-control required" data-live-search="true"
                                            name="brandId"
                                            id="updataGoods_brand" style="width: 160px;">
                                    </select>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>单位：</label>

                                <div class="controls col-xs-2">
                                    <div class="input-group">
                                        <input type="text" name="goodsInfoUnit"
                                               class="form-control sm-input required proUnit"
                                               onblur="$('.proUnit').val($(this).val())" onchange="getInputVal(this)" maxlength="4"/>
                                    </div>
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
                <div class="detail_info form-container step_wp none">
                    <div class="tab-content dinfo_wp">
                        <form class="goodsInfoForm">
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
                                    <input type="text" name="goodsInfoPreferPrice"
                                           class="form-control sm-input pro_price required floatingDecimal"/>
                                </div>
                            </div>

                            <div class="form-group" style="overflow: hidden">
                                <label class="control-label col-xs-3"><b>*</b>结算价（RMB）:</label>

                                <div class="controls col-xs-7">
                                    <input name="goodsInfoSettlePrice" type="text"
                                           class="form-control sm-input goodsInfoSettlePrice required floatingDecimal"/>
                                </div>
                            </div>

                            <#--<div class="form-group" style="overflow: hidden">-->
                                <#--<label class="control-label col-xs-3">运费：</label>-->

                                <#--<div class="controls col-xs-7">-->
                                    <#--<div class="input-group">-->
                                        <#--<input name="freightPrice" type="text"-->
                                               <#--class="form-control sm-input freightPrice required floatingDecimal"/>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->

                            <div class="form-group" style="overflow: hidden">
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
                        </form>
                    </div>
                    <!--/dinfo_wp-->

                    <div class="add-sorts-operation">
                        <a class="btn btn-default prev_step"
                           href="javascript:;">上一步
                        </a>
                        <a class="btn btn-primary info_sub" onclick="up_goods_info()" href="javascript:;">发布货品</a>
                    </div>
                </div>
            </div>
            <div class="modi_img mt10 none">
                <form class="modi_third_product_img" enctype="multipart/form-data" action="updateThirdProductImage.htm"
                      method="post">
                    <div class="app-content">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="javascript:;">编辑图片</a>
                            </li>
                        </ul>
                        <input type="hidden" name="goodsId" class="img_goodsId_hide" value="${map.goodsId}"/>
                        <input class="img_productId_hide" type="hidden" name="productId" value=""/>
                        <input class="default_image" name="defaultImage" type="hidden" value=""/>
                        <input class="goodsImgId" name="goodsImgId" type="hidden" value=""/>
                        <div class="del_image">
                        </div>
                        <button class="btn btn-primary choose_image_button" type="button">选择图片</button>

                        <ul class="edit-images-list">
                        </ul>

                        <div class="edit-operation">
                            <button class="btn btn-primary save_img" type="button">保存</button>
                            <button class="btn btn-default close_img" type="button">取消</button>
                        </div>
                </form>
            </div>
        </div>
    </div>
    <div class="old_param_div"></div>
    <!-- 修改货品图片信息的弹窗 -->
    <div class="mask"></div>

<#--图片确认删除提示框-->
    <div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item error">
                        <label class="control-label">确认删除吗？</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn"
                            onclick="delProductImg();">确定
                    </button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
<#--货品确认删除提示框-->
    <div class="modal fade" id="delete-tip1" role="dialog" aria-hidden="true">
        <form action="delThirdProduct.htm" method="post" id="mform" name="mform">
            <input type="hidden" id="productId" name="productId" value=""/>
            <input type="hidden" name="goodsId" value="${map.goodsId}"/>

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">操作提示</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-item error">
                            <label style="width:100%;" id="errorT">确认删除吗？</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                        <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn"
                                onclick="delmar();">确定
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

<#--拒绝原因-->
    <div class="modal fade" id="refuse-tip" role="dialog" aria-hidden="true">
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
                    <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

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

<#--修改库存-->
    <div class="modal fade" id="stockModal" role="dialog" style="z-index: 99999999">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改库存</h4>
                </div>
                <div class="modal-body">
                    <form class="stockForm">
                        <input type="hidden" id="goodsInfoId">
                        <div class="form-group" style="overflow: hidden">
                            <label class="control-label col-xs-3"><b>*</b>库存:</label>
                            <div class="controls col-xs-7">
                                <input name="goodsInfoStock" type="text" id="goodsInfoStock"
                                       class="form-control sm-input required"
                                       onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                       onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" type="button" onclick="updateStock()">确定</button>
                </div>
            </div>
        </div>
    </div>

    <#--<script type="text/javascript" src="${basePath}/static/js/navmenu/navmenu.js?v=20151016234"></script>-->
    <script>
        var editor2;
        $(function () {
            $.material.init();
            KindEditor.ready(function (K) {
                editor2 = K.create('textarea[name="mobile_desc"]',
                        {
                            width: "80%",
                            height: "425px",
                            cssPath: 'static/js/kindeditor/plugins/code/prettify.css', uploadJson: '${basePath}/web/imgUpload',
                            fileManagerJson: 'static/js/kindeditor/jsp/file_manager_json.jsp', allowFileManager: true,allowImageRemote : true,
                            afterCreate: function () {
                                var self = this;
                                K.ctrl(document, 13, function () {
                                    self.sync();
                                    document.forms['example'].submit();
                                });
                                K.ctrl(self.edit.doc, 13, function () {
                                    self.sync();
                                    document.forms['example'].submit();
                                });
                            }
                        });
                prettyPrint();
            });
            $.post("queryAllBrands",function(data){
                if(data.response == "success"){
                    var options = "<option value=''>请选择品牌</option>";
                    for( var i=0; i<data.data.length; i++){
                        var auth=data.data[i];
                        if(auth.brandId == $("#brandIdInput").val()){
                            options +=  "<option selected='selected' value='"+auth.brandId+"'>"+auth.brandName+"</option>";
                        }else {
                            options +=  "<option value='"+auth.brandId+"'>"+auth.brandName+"</option>";
                        }
                    }
                    $("#goods_brand").html(options);
                    $("#goods_brand").select2();
                }
            });

            $(".add-goods").click(function () {
                $(".sub_product").attr("action", "tNewUploadProduct.htm");
                $(".exec_flag").val("0");
                $(".productTitle").html("添加货品");
                $(".group_wp").hide();
                $(".app-content").show();
            });
            $(".modi-goods").click(function () {
                $(".productTitle").html("修改货品");
                $(".group_wp").hide();
                $(".app-content").show();
            });
            $(".cls_gp").click(function () {
                $(".group_wp").show();
                $(".app-content").hide();
            });

            $(".close_img").click(function () {
                $(".group_wp").show();
                $(".modi_img").hide();
                $(".img_productId_hide").val("");
                $(".defaule_image").val("");
                $(".del_image").html("");
            });

        });
        function del(id) {
            $("#productId").val(id);
            var str="确定要删除此货品吗？";
            $.ajax({
                type : 'post',
                url : 'queryGoodsMarket.htm?goodsInfoId='+id,
                async : false,
                success : function(data) {
                    if(data.length>0){
                      str="此货品已参加过促销活动，确定要删除此货品吗？";

                    }
                }
            })
            $("#errorT").html(str);
            $("#delete-tip1").modal('show');
        }
        function delmar() {
            $("#mform").submit();
        }

        function refuseReason(refuseRen) {
            $(".show_title").val(refuseRen);
            $("#refuse-tip").modal('show');
        }


        /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
        function show(){
            $(".show_text").fadeOut(1000).fadeIn(1000);
        }
        setTimeout("show()",1000);

        function getInputVal(obj){
            $("#basic-proPack").html($(obj).val()+"/箱");
        }

        //搜索、分页
        function searchData(pageNumber){
            $("#page").val(pageNumber);
            $(".search_product_form").submit();
        }
        //tab切换
        function changTbl(auditStatus){
            $("#auditStatus").val(auditStatus);
            $("#page").val(1);
            $(".search_product_form").submit();
        }
        function updateCancel(){
            $(".app-content").hide();
            $(".group_wp").show();
        }
    </script>
<#--<#import "common/selectindex.ftl" as selectindex>-->
<#--<@selectindex.selectindex "${n!''}" "${l!''}" />-->
</body>
</html>