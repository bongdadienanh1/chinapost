


<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link rel="stylesheet" href="${bath}/static/css/font-awesome.min.css?version=${VERSION}">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/summernote.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/bootstrap/css/bootstrap-select.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/select2.min.css?version=${VERSION}" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<#--<jsp:include page="../page/header.jsp"></jsp:include>-->

<!-- 引用头 -->
<#include "page/header.ftl"/>

<div class="page_body container-fluid">
    <div class="row">
    <#include "page/left.ftl"/>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
            <#--<jsp:include page="../page/breadcrumbs.jsp"></jsp:include>-->
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>货品管理</li>
                    <li>货品分类</li>
                </ol>
                <h2 class="main_title">货品分类</h2>

                <div class="cate_set container-fluid mt20">

                    <div class="row">
                        <div class="col-xs-8 cate_set_column">
                            <div class="cate_set_item">
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="parentId0" value="0"/>
                                        <input type="hidden" id="parentId1"/>
                                        <input type="text" id="search_name1" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findFirstGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="cate_list1">
                                        <#--<a class="add_cart_btn" href="javascript:;" onclick="showAddCate()"><i class="glyphicon glyphicon-plus"></i> 添加一级分类</a>
                                        <#list cateList as cate>
                                            <div class="cate_item" id="cate_item${cate.catId}" onclick="loadSecondCate(this,${cate.catId})">
                                                <h4>${cate.catName}</h4>
                                                <div class="c_btns">
                                                    <a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditCate(1,${cate.catId})"></span></a>
                                                    <a href="javascript:;" onclick="deleteGoodsCate(${cate.catId})"><span class="glyphicon glyphicon-trash"></span></a>
                                                </div>
                                            </div>
                                        </#list>-->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-8 cate_set_column">
                            <div class="cate_set_item">
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="parentId2"/>
                                        <input type="text" id="search_name2" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findSecondGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="cate_list2">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-8 cate_set_column">
                            <div class="cate_set_item">
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="parentId3"/>
                                        <input type="text" id="search_name3" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findThirdGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="cate_list3">

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>


<!-- 添加分类 -->
<div class="modal fade" id="cateAdd"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加分类</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" action="addGoodsCate" method="post" id="saveGoodsCateForm">
                        <input type="hidden" id="cur_level" />
                        <input type="hidden" id="update_catId" name="catId" />
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类名称：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control required" maxlength="10" id="update_cate_name" name="catName" data-name="" onblur="checkCatNameExist(this)">
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>上级分类：
                            </label>
                            <div class="col-sm-16">
                                <select class="w100" data-live-search="true" id="parentCate_edit" name="catParentId">

                                </select>
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类排序：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control w100 required digits" name="catSort" id="update_cate_sort">
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitSaveGoodsCateForm()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/summernote.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/summernote-zh-CN.js?version=${VERSION}"></script>
<script src="${bath}/static/bootstrap/js/bootstrap-select.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_checked.js?version=${VERSION}"></script>
<script src="${bath}/static/js/ThirdPlantManage/GoodsCate.js?version=${VERSION}"></script>
<script src="${bath}/static/js/select2.min.js?version=${VERSION}"></script>

</body>
</html>








