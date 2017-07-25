<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>平台列表</title>

    <!-- Bootstrap -->
    <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
</head>
<body>

<!-- 引用头 -->
<#include "page/header.ftl"/>
<input type="hidden" id="basePath" value="${bath}">
<div class="page_body container-fluid">
    <div class="row">
    <#include "page/left.ftl"/>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>平台管理</li>
                    <li>平台账号管理</li>
                </ol>
                <h2 class="main_title">平台账号管理<small>(共${pageBean.totalElements!'0'}条)</small></h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <form  id="search_form" method="post" action="${bath}/ThirdPlatformAdmin/ListPage?menuParentId=1" role="form" class="form-inline">
                            <input type="hidden" id="size" name="size" value="10"/>
                            <input type="hidden" id="page" name="page"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">平台登录账号</span>
                                    <input type="text" class="form-control"name="thirdUserName" value="${thirdPlatformEntity.thirdUserName }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">公司名称</span>
                                    <input type="text" class="form-control"name="companyName" value="${thirdPlatformEntity.companyName }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">店铺名称</span>
                                    <input type="text" class="form-control"name="storeName" value="${thirdPlatformEntity.storeName }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="search-button">搜索</button>
                            </div>
                        </form>
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="updateThirdPlatfrom()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleThirdForm','ids')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="deleThirdForm" action="${bath}/ThirdPlatformAdmin/saveThirdPlatform" method="post">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" name="ids" value="-1" onclick="allunchecked(this,'ids');"></th>
                                <th>公司名称</th>
                                <th>店铺名称</th>
                                <th>用户账号</th>
                                <th>是否抛单</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list pageBean.content as cs>
                                <tr>
                                    <td><input type="checkbox" name="ids" value="${cs.id }"></td>
                                    <td>${cs.companyName!'' }</td>
                                    <td>${cs.storeName!'' }</td>
                                    <td>${cs.thirdUserName!'' }</td>
                                    <td>
                                        <#if cs.platformType == 1>
                                            <a href="saveThirdPlatform?id=${cs.id }&platformType=0" class="label label-success" >是</a>
                                        <#else>
                                            <a href="saveThirdPlatform?id=${cs.id }&platformType=1" class="label label-default">否</a>
                                        </#if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="passwordReset(${cs.id})">密码重置</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#" onclick="showDeleteOneConfirmAlert('${bath}/ThirdPlatformAdmin/saveThirdPlatform?id=${cs.id}&deleteFlag=1','确认要删除平台${cs.thirdUserName}吗')">删除</a></li>
                                                <li><a href="#" onclick="bindingSendUrl(${cs.id})">绑定推送路径</a></li>
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
<!-- 编辑 -->
<div class="modal fade" id="editThirdPlatfrom"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title add_title">编辑平台</h4>
            </div>
            <form role="form" class="form-horizontal" id="editBrandForm" action="" method="post">
                <input type="hidden" name="id" id="id"/>
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>公司名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr required" name="companyName" id="companyName" maxlength="25">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>店铺名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="storeName" id="storeName" maxlength="25">
                                <span style='color:#a94442;font-weight: bold;display: none' id='helpTip' >店铺名称重复，请重新输入</span>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>账号：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr required" name="thirdUserName" id="thirdUserName" >
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>

                        <div class="form-group" id="pwd">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>密码：
                            </label>
                            <div class="col-sm-13">
                                <input type="password" class="form-control required " name="thirdUserPwd" id="thirdUserPwd" minlength="8" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-6">是否抛单：</label>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="platformType" value="0"> 否
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="platformType" value="1"> 是
                                </label>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary sub" onclick="submitUpdateForm();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 密码重置 -->
<div class="modal fade" id="passWordForm"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title add_title">重置密码</h4>
            </div>
            <form role="form" class="form-horizontal" id="editPasswordForm" action="${bath}/ThirdPlatformAdmin/saveThirdPlatform" method="post">
                <input type="hidden" name="id" id="passwordId"/>
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>设置密码：
                            </label>
                            <div class="col-sm-13">
                                <input type="password" class="form-control specstr required" name="thirdUserPwd" id="thirdUserNameNew" minlength="8" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group" id="pwd">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>确认密码：
                            </label>
                            <div class="col-sm-13">
                                <input type="password" class="form-control required " id="oldThirdUserPwd" minlength="8" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitPassWordForm();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 绑定消息推送路径 -->
<div class="modal fade" id="bindingSendUrl"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title add_title">推送路径绑定</h4>
            </div>
            <form role="form" class="form-horizontal" id="editThirdPlatformInterfaceForm" method="post">
                <input type="hidden" name="id" id="e_id"/>
                <input type="hidden" name="thirdId" id="thirdId"/>
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>商户库存查询接口：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="inventoryQuery" id="inventoryQuery" maxlength="60">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>支付完成通知接口：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="paymentNotice" id="paymentNotice" maxlength="60">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>商户退款接口：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="merchantRefund" id="merchantRefund" maxlength="60">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>商户申请退货退款接口：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="merchantSale" id="merchantSale" maxlength="60">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>商户退货物流接口：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="merchantReturnLogistics" id="merchantReturnLogistics" maxlength="60">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitInterfaceForm();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_checked.js?version=${VERSION}"></script>
<script src="${bath}/static/js/ThirdPlantManage/PlantAccountManagement.js?version=${VERSION}"></script>
<script type="application/javascript">
    //查询
    function searchData(pageNumber){
        $("#page").val(pageNumber);
        $("#search_form").submit();
    }
</script>
</body>
</html>
