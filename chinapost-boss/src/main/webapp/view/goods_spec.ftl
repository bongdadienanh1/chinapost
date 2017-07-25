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
    <link href="${bath}/static/css/bootstrap-select.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/select2.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<#include "page/header.ftl"/>
<div class="page_body container-fluid">
    <div class="row">
    <#include "page/left.ftl"/>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>货品管理</li>
                    <li>货品规格</li>
                </ol>
                <h2 class="main_title">货品规格<small>(共${pageBean.totalElements!'0'}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" id="searchForm" action="${bath}/GoodsSpecAdmin/ListPage?menuParentId=1">
                            <input type="hidden" id="size" name="size" value="10"/>
                            <input type="hidden" id="page" name="page"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">规格名称</span>
                                    <input type="text" class="form-control" name="specName" value="${goodsSpec.specName}">
                                    <input type="hidden" name="condition" value="1"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="updateGoodsSpec()/*$('#addSpecification').modal('show')*/">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="deleteCheckGoods('batchDeleteSpecForm','specIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="batchDeleteSpecForm" action="${bath}/GoodsSpecAdmin/saveGoodsSpec" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'specIds')"></th>
                            <th>规格名称</th>
                            <th>规格备注</th>
                            <th width="200">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageBean.content as spec>
                        <tr>
                            <td><input type="checkbox" name="specIds" value="${spec.specId }"></td>
                            <td>${spec.specName }</td>
                            <td>${spec.specRemark }</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="querySpecDetail(${spec.specId})">管理规格值</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick='updateGoodsSpec(${spec.specId})'>编辑</a></li>
                                        <li><a href="javascript:;" onclick="checkGoods('${bath}/GoodsSpecAdmin/saveGoodsSpec?specId=${spec.specId}&specDelflag=1',${spec.specId})">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    </form>
                        <div class="table_foot">
                            <#include "page/searchPag.ftl"/>
                        </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addSpecification"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加规格</h4>
            </div>
            <form role="form" class="form-horizontal" action="${bath}/GoodsSpecAdmin/saveGoodsSpec" method="post" id="saveSpecForm">
            <input type="hidden" name="specId" value="" id="specId"/>
            <div class="modal-body">
                <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>规格名称：
                            </label>
                            <div class="col-sm-12">
                                <input type="text"  class="form-control required specstr" name="specName" id="specName" maxlength="4" minlength="2">
                            </div>
                            <div class="col-sm-5">
                                <a role="button" class="guigemingchen help_tips" href="javascript:;">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>规格备注：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required specstr" name="specRemark" id="specRemark" maxlength="10" minlength="2">
                            </div>
                            <div class="col-sm-5">
                                <a role="button" class="guigebeizhu help_tips" href="javascript:;">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                规格别名：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control" name="specNickname" id="specNickname"  maxlength="10">
                            </div>
                            <div class="col-sm-5">

                            </div>
                        </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="saveSpecForm()" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="manageSpecification"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">管理规格值</h4>
            </div>
            <form id="updateSpecDetailForm" action="updateSpecDetail" method="post" enctype="multipart/form-data">
                <input type="hidden" name="specId" id="specId" />
                <div class="modal-body">
                <p>
                    <button type="button" class="btn btn-info" onclick="addOneSpecDetail()">
                        <span class="glyphicon glyphicon-plus-sign"></span> 添加规格值
                    </button>
                    <#--<button type="button" class="btn btn-info" onclick="batchDeleteSpecDetail()" >-->

                        <button type="button" class="btn btn-info"  onclick="deleteCheckGoodsBySpecDetailId('specDetailId')">
                        <span class="glyphicon glyphicon-remove-sign"></span> 删除全部
                    </button>
                </p>

                <table class="table table-striped table-hover" id="spec_detail_table">
                    <thead>
                    <tr>
                        <th width="10"><input type="checkbox" onclick="allunchecked(this,'specDetail')" name="specDetail"></th>
                        <th>规格值名称</th>
                        <th width="150">排序</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>

            </div>
            </form>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveSpec();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
   <div class="modal fade" id="helpGuige"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加规格</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>添加货品规格，点击“添加规格”</p>
              <img src="${bath}/static/images/img_g01.png" alt="">
              <p><em>2.</em>点击保存，规格添加成功，然后管理规格值，点击“管理规格值”</p>
              <img src="${bath}/static/images/img_g02.png" alt="">
              <p>保存成功后，规格添加完成</p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
    
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/summernote.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/summernote-zh-CN.js?version=${VERSION}"></script>
<script src="${bath}/static/js/bootstrap-select.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_checked.js?version=${VERSION}"></script>
<script src="${bath}/static/js/select2.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/goods/goods_spec.js?version=${VERSION}"></script>
<script>

    /**
     * 弹出显示编辑
     */
    var specNameOld;
    function updateGoodsSpec(specId) {
        if(typeof (specId) != 'undefined'){
            $("#addSpecification .modal-title").html("编辑");
            $.ajax({
                url:'${bath}/GoodsSpecAdmin/queryById?specId='+specId,
                dataType:'json',
                success:function(data) {
                    $("#specId").val(data.specId);
                    $("#specName").val(data.specName);
                    $("#specRemark").val(data.specRemark);
                    $("#specNickname").val(data.specNickname);
                    specNameOld = data.specName;
                    $("#addSpecification").modal("show");
                    $("#addSpecification").find("label").each(function() {
                        if($(this).hasClass('error')){
                            $(this).remove();
                        }
                    });
                    $("#addSpecification").find("input").each(function() {
                        if($(this).hasClass('error')){
                            $(this).removeClass('error');
                        }
                    });
                }
            });
        } else{
            $("#addSpecification .modal-title").html("添加");
            $("#addSpecification").find("input").val("");
            $("#addSpecification").modal("show");
            $("#addSpecification").find("label").each(function() {
                if($(this).hasClass('error')){
                    $(this).remove();
                }
            });
            $("#addSpecification").find("input").each(function() {
                if($(this).hasClass('error')){
                    $(this).removeClass('error');
                }
            });
        }
    }

    /**
    *添加规格
     */
    function saveSpecForm(){
        if($("#saveSpecForm").valid()){
            if($("#addSpecification .modal-title").html() == "添加"){
                checkSpecName($("#specName").val());
            }else{
                if($("#specName").val() == specNameOld){
                    specNameOld = "";
                }else{
                    specNameOld = $("#specName").val();
                }
                checkSpecName(specNameOld);
            }
        }
    }

    function checkSpecName(specName){
        $.ajax({
            url:'checkGoodsSpecName',
            data:{specName:specName},
            async: false,
            success:function(data) {
                if(data == '200'){
                    $("#saveSpecForm").submit();
                }else{
                    showTipAlert(data);
                }
            }
        });
    }

    var num = 0;
    function saveSpec(){
        if($("#updateSpecDetailForm").valid()&&num==0){
            if(!$("#spec_detail_table").find('tbody').html()==""){
                num+=1;
                $("#updateSpecDetailForm").submit();
            }else{
                showTipAlert("至少要有一个规格值存在！");
            }
        }
    }
    /**
     * 修改规格值提交按钮事件
     * */
    function bcspec(){
        if($("#updateSpecForm").valid()){
            $("#updateSpecForm").submit();
        }
    }
    /**
     * 规格关联货品的无法删除
     * @param url
     * @param specId
     */
    function checkGoods(url,specId){
        $.ajax({
           url:'countGoodsBySpecId',
           data:{specIds:specId},
           success:function (data) {
               if(data == 200){
                   showDeleteOneConfirmAlert(url);
               }else{
                  showTipAlert(data);
               }
           }
        });
    }

    function deleteCheckGoods(deleteFormId,name){
        var checkboxs = $("input[name="+name+"]");
        var oneSelect = false;
        var specIds = [];
        for(var j = 0; j < checkboxs.length; j++) {
            if($(checkboxs[j]).is(':checked')==true) {
                oneSelect = true;
                specIds.push($(checkboxs[j]).val().toString());
            }
        }

        if(!oneSelect) {
            showTipAlert("请至少选择一条记录！");
            return;
        }

        $.ajax({
            url:'countGoodsBySpecId',
            data:{specIds:specIds.join(",")},
            success:function (data) {
                if(data == 200){
                    showDeleteBatchConfirmAlert(deleteFormId,name);
                }else{
                    showTipAlert(data);
                }
            }
        });
    }


    //查询
    function searchData(pageNumber){
        $("#page").val(pageNumber);
        $("#searchForm").submit();
    }
</script>
</body>
</html>