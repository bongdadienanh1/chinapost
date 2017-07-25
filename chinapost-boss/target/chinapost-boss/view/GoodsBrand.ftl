


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
    <#--</div>-->
<#--&lt;#&ndash;</div>&ndash;&gt;-->
<#--<div class="page_body container-fluid">-->
    <#--<div class="row">-->
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>货品管理</li>
                    <li>货品品牌</li>
                </ol>

                <h2 class="main_title">货品品牌<small>(共${pageBean.totalElements!'0'}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" value="searchForm" id="formId">
                        <input type="hidden" value="findAllBrand.htm" id="formName">
                        <form role="form" class="form-inline" id="searchForm" action="getGoodsBrand.hml">
                            <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                            <input type="hidden" id="size" name="size" value="10"/>
                            <input type="hidden" id="page" name="page"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">品牌名称</span>
                                    <input type="text" class="form-control" name="brandName" value="${brandName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">品牌别名</span>
                                    <input type="text" class="form-control" name="brandNickname" value="${brandNickname}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="brandsearch">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="doDeleteBatchdata_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addBrand').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert2('deleBrandForm','brandIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>

                            <div class="btn-group">

                            </div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="deleBrandForm" action="deleteGoodsBrand" method="post">
                        <input type="hidden" value="${pb.pageNo}" name="pageNo"/>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="10"><input type="checkbox" onclick="allunchecked(this,'brandIds')"></th>
                                <th>品牌LOGO</th>
                                <th>品牌名称</th>
                                <th>品牌别名</th>
                                <!-- <th>品牌网址</th> -->
                                <th>品牌排序</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#--<c:forEach items="${pb.list}" var="brand">-->
                            <#list pageBean.content as spec>
                            <tr>
                                <td><input type="checkbox" name="brandIds" value="${spec.brandId }"/></td>
                                <td><img alt="" src="${spec.brandLogo}" height="36px"></td>
                                <td>${spec.brandName}</td>
                                <td>${spec.brandNickname}</td>
                                <td>${spec.brandSort}</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="showEditBrandForm(${spec.brandId})">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert2('deleteOneGoodsBrand?CSRFToken=${token}&brandId=${spec.brandId}','确认要删除品牌${spec.brandName}吗')">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                           </#list>
                            <#--</c:forEach>-->
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
<div class="modal fade" id="addBrand"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加品牌</h4>
            </div>
            <form role="form" class="form-horizontal" id="saveBrandForm"  action="saveGoodsBrand?CSRFToken=${token}" method="post">
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌名称：
                            </label>
                            <div class="col-sm-13">
                                    <input type="text" class="form-control required specstr" name="brandName" id="addBrandName" maxlength="10"
                                           onkeyup="ValidateValue(this)" />
                                <span style='color:#a94442;font-weight: bold;display: none' id='helpTip' >品牌名称重复，请重新输入</span>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                品牌别名：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="brandNickname" id="addbrandNickname" maxlength="10" >
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">LOGO图片：</label>
                            <div class="col-sm-12">
                                <p class="pt5">
                                    <input type="file" name="picFile" id="logoFile">
                                </p>
                            <#--<input type="file" id="imgUpload" name="file" style="display: none" multiple />-->
                                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                            </div>
                            <div class="col-sm-3">
                                <a href="javascript:;" class="brandLogo help_tips">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">预览LOGO：</label>
                            <div class="col-sm-12">
                                <div id="pre_img_box">
                                    <img alt="" src="" id="preview_image" width="90px">
                                </div>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="brandSort" id="addBrandSort"
                                       onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                       maxlength="4"
                                />
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <!--<div class="form-group">
                            <label class="control-label col-sm-6">是否推荐到首页：</label>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="promIndex"  value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="promIndex"  value="0" checked> 否
                                </label>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitSaveBrandForm();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- 编辑品牌 -->
<div class="modal fade" id="editBrand"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑品牌</h4>
            </div>
            <form role="form" class="form-horizontal" id="editBrandForm" enctype="multipart/form-data" action="updateBrand.htm?CSRFToken=${token}" method="post">
                <input type="hidden" name="brandId" id="brandId"/>
                <input type="hidden" value="${pb.pageNo}" name="pageNo" id="pageNo"/>
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="brandName" id="brandName" maxlength="10"
                                       onkeyup="ValidateValue(this)"
                                        />
                                <span style='color:#a94442;font-weight: bold;display: none' id='helpTip2' >品牌名称重复，请重新输入</span>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                品牌别名：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="brandNickname" id="brandNickname" maxlength="10">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">LOGO图片：</label>
                            <div class="col-sm-12">
                                <p class="pt5"><input type="file" name="picFile" id="logoFile_update" ></p>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">预览LOGO：</label>
                            <div class="col-sm-12">
                                <div id="pre_img_box_update">
                                    <img alt="" src="" id="preview_image_update" width="90px">
                                </div>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="brandSort" id="brandSort"
                                       onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                       maxlength="4"
                                        />
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitUpdateBrandForm();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
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
<script src="${bath}/static/js/ThirdPlantManage/GoodsBrand.js?version=${VERSION}"></script>
<#--<script src="${bath}/static/js/goods/goods_brand.js"></script>-->
<script src="${bath}/static/js/ThirdPlantManage/PlantGoodsList.js?version=${VERSION}"></script>
<script>
    function ValidateValue(textbox) {
        var IllegalString = "[`~!#$^&*()=|{}':;',\\[\\].<>/?~！#￥……&*（）——|{}【】‘；：”“'。，、？]‘'";
        var textboxvalue = textbox.value;
        var index = textboxvalue.length - 1;

        var s = textbox.value.charAt(index);

        if (IllegalString.indexOf(s) >= 0) {
            s = textboxvalue.substring(0, index);
            textbox.value = s;
        }
    }
    $(function(){
        $("#logoFile").change(function(){
            var  imgg = $(this).val().substring($(this).val().length -3,$(this).val().length);
            if(imgg != 'jpg' && imgg != 'gif' && imgg != 'png'){
                $('#logoFile').val('');
                showTipAlert('上传失败，清选则正确的图片格式')
                return;
            }
            else{
                var fileObj = document.getElementById("logoFile").files[0]; // 获取文件对象
                var FileController = "${bath}/web/uploadPicture";                    // 接收上传文件的后台地址
                var data = [];
                // FormData 对象
                var form = new FormData();// 可以增加表单数据
                form.append("file", fileObj);                           // 文件对象
                var xhr = new XMLHttpRequest();
                xhr.open("POST", FileController, true);
                // XMLHttpRequest 对象
                xhr.send(form);
                xhr.onreadystatechange = function(){
                    if(xhr.readyState == 4 && xhr.status == 200){
                        str = xhr.responseText;
                        data = JSON.parse(str);
                        if (data.response == 'success'){
                            showTipAlert("上传成功");
                            $("#pre_img_box").html('<img src="'+data.data+'"  id="preview_image" style="max-width: 90px; width: auto; height: 90px;">');
                            if($("#fileName").val()!=''){
                                $("#fileName").val($("#fileName").val()+','+data.data);
                            }else {
                                $("#fileName").val(data.data);
                            }
                            return true;
                        }
                        else{
                            $("#logoFile").val("");
                            showTipAlert(data.data.text);
                            return false;
                        }
                    }
                    else if( xhr.status == 400 ){
                        $("#logoFile").val("");
                        showTipAlert("上传失败");
                    }
                };
            }
        });
        <#--$("#logoFile").change(function(){-->
            <#--var fileObj = document.getElementById("logoFile").files[0]; // 获取文件对象-->
            <#--var FileController = "${bath}/web/uploadPicture";                    // 接收上传文件的后台地址-->
            <#--var data = [];-->
            <#--// FormData 对象-->
            <#--var form = new FormData();// 可以增加表单数据-->
            <#--form.append("file", fileObj);                           // 文件对象-->
            <#--var xhr = new XMLHttpRequest();-->
            <#--xhr.open("POST", FileController, true);-->
            <#--// XMLHttpRequest 对象-->
            <#--xhr.send(form);-->
            <#--xhr.onreadystatechange = function(){-->
                <#--if(xhr.readyState == 4 && xhr.status == 200){-->
                    <#--str = xhr.responseText;-->
                    <#--data = JSON.parse(str);-->
                    <#--if (data.response == 'success'){-->
                        <#--$("#pre_img_box").html('<img src="'+data.data+'"  id="preview_image" style="max-width: 90px; width: auto; height: 90px;">');-->
                        <#--if($("#fileName").val()!=''){-->
                            <#--$("#fileName").val($("#fileName").val()+','+data.data);-->
                        <#--}else {-->
                            <#--$("#fileName").val(data.data);-->
                        <#--}-->
                        <#--return true;-->
                    <#--}-->
                    <#--else{-->
                        <#--$("#imgUpload").val("");-->
                        <#--showTipAlert(data.data.text);-->
                        <#--return false;-->
                    <#--}-->
                <#--}-->
                <#--else if( xhr.status == 400 ){-->
                    <#--$("#logoFile").val("");-->
                    <#--showTipAlert("上传失败");-->
                <#--}-->
            <#--};-->
        <#--});-->
        $("#logoFile_update").change(function(){
            var  imgg = $(this).val().substring($(this).val().length -3,$(this).val().length);
            if(imgg != 'jpg' && imgg != 'gif' && imgg != 'png'){
                $('#logoFile_update').val('');
                showTipAlert('上传失败，清选则正确的图片格式')
                return;
            }
            else{
                var fileObj = document.getElementById("logoFile_update").files[0]; // 获取文件对象
                var FileController = "${bath}/web/uploadPicture";                    // 接收上传文件的后台地址
                var data = [];
                // FormData 对象
                var form = new FormData();// 可以增加表单数据
                form.append("file", fileObj);                           // 文件对象
                var xhr = new XMLHttpRequest();
                xhr.open("POST", FileController, true);
                // XMLHttpRequest 对象
                xhr.send(form);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        str = xhr.responseText;
                        data = JSON.parse(str);
                        if (data.response == 'success') {
                            $("#pre_img_box_update").html('<img src="' + data.data + '"  id="preview_image_update" style="max-width: 90px; width: auto; height: 90px;">');
                            if ($("#fileName").val() != '') {
                                $("#fileName").val($("#fileName").val() + ',' + data.data);
                            } else {
                                $("#fileName").val(data.data);
                            }
                            return true;
                        }
                        else {
                            $("#logoFile_update").val("");
                            showTipAlert(data.data.text);
                            return false;
                        }
                    }
                    else if (xhr.status == 400) {
                        $("#logoFile_update").val("");
                        showTipAlert("上传失败");
                    }
                }
            };
        });
    })
</script>
</body>
</html>








