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
    <script type="text/javascript" src="${basePath}/static/js/common/common_alert.js?version=${VERSION}"></script>
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

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>货品管理</li>
                <li class="active">货品列表</li>
            </ol>
            <div class="group_wp">
                <form class="search_product_form" action="getGoodsInfo" method="post">
                    <input type="hidden" id="page" name="page"/>
                    <input type="hidden" id="size" name="size" value="10"/>
                    <input type="hidden" name="type" value="0"/>
                    <input type="hidden" name="goodsInfoAdded" value="${goodsInfoAdded!''}" id="goodsInfoAdded"/>

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


                <div class="mt20 clearfix">
                </div>
                <ul class="nav nav-tabs">
                    <li class="tb_barterOrder <#if goodsInfoAdded == '1'>active</#if>" onclick="changTbl(1)"><a href="javascript:;">已上架</a></li>
                    <li class="tb_backOrderSH <#if goodsInfoAdded == '0'>active</#if>" onclick="changTbl(0)"><a href="javascript:;">未上架</a></li>
                </ul>
                <div class="cfg-content">
                    <div class="ops-bar"></div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="5%"><input type="checkbox" onclick="selectAll(this,'goodsInfoId')"/></th>
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
                                        <td> ${product.goodsInfoStock?string("0")}</td>
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
                        <div class="ops-left">
                            <button class="btn btn-primary btn-xs batch_down_product" type="button">
                                <#if goodsInfoAdded==1>
                                        批量下架
                                    <#else >
                                        批量上架
                                </#if>
                            </button>
                        </div>
                    <#include "index/searchPag.ftl">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="old_param_div"></div>
    <!-- 修改货品图片信息的弹窗 -->
    <div class="mask"></div>

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

    <#--<script type="text/javascript" src="${basePath}/static/js/navmenu/navmenu.js?v=20151016234"></script>-->
    <script>
        $(function () {
             $(".batch_down_product").click(function(){
                 var stringData = "";
                 var goodsInfoAdded;
                 var checkGoods = $("table input[name=goodsInfoId]:checked");
                 checkGoods.each(function(){
                     stringData+=$(this).val()+',';
                 });
                 if($("#goodsInfoAdded").val()==0){
                     goodsInfoAdded="1";
                 }else {
                     goodsInfoAdded="0";
                 }
                 stringData=stringData.substring(stringData.length-1,0);
                 console.log(stringData);
                 $.post("batchUpdateGoodsInfoAdded",{
                     goodsInfoIds:stringData,
                     goodsInfoAdded:goodsInfoAdded
                 },function(data){
                     if(data.response == "success"){
                         showConfirmAlertTips("操作成功！","window.location.reload()");
                     }
                 })
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
        function changTbl(goodsInfoAdded){
            $("#goodsInfoAdded").val(goodsInfoAdded);
            $("#page").val(1);
            $(".search_product_form").submit();
        }
    </script>
<#--<#import "common/selectindex.ftl" as selectindex>-->
<#--<@selectindex.selectindex "${n!''}" "${l!''}" />-->
</body>
</html>
