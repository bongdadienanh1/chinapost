
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

        <#--<div class="col-lg-20 col-md-19 col-sm-18 main">-->
            <#--<div class="main_cont">-->
            <#--<#include "page/breadcrumbs.ftl"/>-->

                <#--<div class="common_data" style="padding-left:20px;">-->

                <#--</div>-->

            <#--</div>-->
        <#--</div>-->
    </div>
</div>




<div class="page_body container-fluid">
    <div class="row">
    <#--<jsp:include page="../page/left.jsp"></jsp:include>-->
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
            <#--<jsp:include page="../page/breadcrumbs.jsp"></jsp:include>-->

                <h2 class="main_title">审核开关设置</h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 审核开关开启后，可对第三方商家上传货品进行审核上架，请根据实际情况进行调整，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form">
                        <form class="form-horizontal" action="changeAuditAction.htm" id="changeAuditAction">
                            <div class="form-group">
                                <label class="control-label col-sm-4">是否启用：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-10">
                                    <input type="hidden" name="type" value="1">
                                    <label class="radio-inline">
                                        <input  type="radio" name="isThirdAuditUsed" id="actionFlag1" value="0" checked="checked"> 关闭
                                    </label>
                                    <label class="radio-inline">
                                        <input class="vm mr5" type="radio" name="isThirdAuditUsed" id="actionFlag2" value="1" checked="checked"> 开启
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-5 col-sm-10">
                                    <button type="button" class="btn btn-primary" onclick=" $('#dialog-confirm2').modal('show');">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
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
<#--<script src="${bath}/static/js/ThirdPlantManage/PlantGoodsList.js"></script>-->

</body>
</html>









