
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
<#include "page/header.ftl"/>

<div class="page_body container-fluid">
    <div class="row">
    <#include "page/left.ftl"/>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>第三方管理</li>
                    <li>平台管理</li>
                    <li>平台货品管理</li>
                </ol>
                <h2 class="main_title">平台货品列表<small>(共${pageBean.totalElements!'0'}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" id="searchForm" action="ListPage">
                            <input type="hidden" id="size" name="size" value="10"/>
                            <input type="hidden" id="page" name="page"/>
                            <input type="hidden" id="auditStatus" name="auditStatus" value="${goodsProduct.auditStatus}"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">货品名称</span>
                                    <input type="text" class="form-control" name="goodsInfoName" value="${goodsProduct.goodsInfoName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">货品编号</span>
                                    <input type="text" class="form-control" name="goodsInfoItemNo" value="${goodsProduct.goodsInfoItemNo}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">所属商家</span>
                                    <select   class="cate_selector w150"  name="thirdId" data-live-search="true">
                                        <option value="">--请选择--</option>
                                        <#list platformList as store>
                                            <option value="${store.storeId}" <#if goodsProduct.thirdId == store.storeId >selected ="selected"</#if>>${store.storeName}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">是否上架</span>
                                    <select name="goodsInfoAdded" class="form-control">
                                        <option value="">--请选择--</option>
                                        <option value="1" <#if goodsProduct.goodsInfoAdded == '1' >selected ="selected"</#if>>是</option>
                                        <option value="0" <#if goodsProduct.goodsInfoAdded == '0' >selected ="selected"</#if>>否</option>

                                    </select>
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
                            <#if goodsProduct.auditStatus == '0'>
                                <button type="button" class="btn btn-info" onclick="batchAudit('goodsInfoIds')">
                                    <i class="glyphicon glyphicon-ok"></i> 批量审核
                                </button>
                                <button type="button" class="btn btn-info" onclick="refuseAudit('goodsInfoIds')">
                                    <i class="glyphicon glyphicon-ok"></i> 批量拒绝
                                </button>
                            </#if>
                            <#--<button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleBrandForm','brandIds')">-->
                                <#--<i class="glyphicon glyphicon-trash"></i> 删除-->
                            <#--</button>-->
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="table_tabs" id="good_tabs">
                        <ul>
                            <li class="<#if goodsProduct.auditStatus == ''>active</#if>">
                                <a href="javascript:;" data-type="" onclick="checkAuditStatus('')">全部</a>
                            </li>
                            <li class="<#if goodsProduct.auditStatus == '0'>active</#if>">
                                <a href="javascript:;" data-type="0" onclick="checkAuditStatus(0)">待审核</a>
                            </li>
                            <li class="<#if goodsProduct.auditStatus == '1'>active</#if>">
                                <a href="javascript:;" data-type="1" onclick="checkAuditStatus(1)">审核通过</a>
                            </li>
                            <li class="<#if goodsProduct.auditStatus == '2'>active</#if>">
                                <a href="javascript:;" data-type="2" onclick="checkAuditStatus(2)">拒绝</a>
                            </li>
                        </ul>
                    </div>
                    <form id="batchAuditStatusFrom" action="batchUpdateAuditStatus" method="post">
                        <input type="hidden" id="auditStatusFrom" name="auditStatus"/>
                        <input type="hidden" id="refuseReasonFrom" name="refuseReason"/>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="10"><input type="checkbox" onclick="allunchecked(this,'goodsInfoIds')"></th>
                                <th>图片</th>
                                <th>货品名称</th>
                                <th>货品编号</th>
                                <th>货品规格</th>
                                <th>销售价（邮豆）</th>
                                <th>结算价（RMB）</th>
                                <th>库存</th>
                                <th>是否上架</th>
                                <th>所属商家</th>
                                <#--<th>邮费</th>-->
                                <th>品牌</th>
                            <#--<#if goodsProduct.auditStatus != '1'><th width="150">操作</th></#if>-->
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list pageBean.content as product>
                                <tr>
                                    <td><input type="checkbox" name="goodsInfoIds" value="${product.goodsInfoId }" data-audit="${product.auditStatus}"/></td>
                                    <td><a href="http://youzheng.ylife.cn/mobile/productdetail?productId=${product.goodsInfoId }"><img  height="50px" src="${product.goodsInfoImgId }"/></a></td>
                                    <#--<td><img  height="50px" src="${product.goodsInfoImgId }" href="'http://cp.ylife.cn/mobile/productdetail?productId='+ ${product.goodsInfoId }"/></td>-->
                                    <td>${product.goodsInfoName}</td>
                                    <td>${product.goodsInfoItemNo }</td>
                                    <td><p title="
                                         ${product.specString }
                                        ">
                                                ${product.specString }
                                        </p> </td>
                                    <td> ${product.goodsInfoPreferPrice}</td>
                                    <td> ${product.goodsInfoSettlePrice}</td>
                                    <td> ${product.goodsInfoStock}</td>
                                    <td>
                                        <#if product.goodsInfoAdded == 1>
                                            <a href="javascript:;" class="label label-success">是</a>
                                        <#else>
                                            <a href="javascript:;" class="label label-default">否</a>
                                        </#if>
                                    </td>

                                    <td>
                                        <span class="text-muted">${product.thirdName}</span>
                                    </td>
                                    <#--<td>-->
                                        <#--<span class="text-muted">${product.freightPrice}</span>-->
                                    <#--</td>-->
                                    <td>
                                        <span class="text-muted">${product.goodsBrand}</span>
                                    </td>
                                    <#--<#if goodsProduct.auditStatus != '1'>-->
                                        <td>
                                            <#if product.auditStatus == '0'>
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default" onclick="openDialog(${product.goodsInfoId})">审核</button>
                                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li><a href="javascript:;" onclick="openRefuseDialog(${product.goodsInfoId})">拒绝</a></li>
                                                    </ul>
                                                </div>
                                            <#elseif product.auditStatus == '1'>
                                                审核通过
                                            <#elseif product.auditStatus == '2'>
                                                <button type="button" class="btn btn-default" onclick="refuseDialog('${product.refuseReason}')">拒绝原因</button>
                                            </#if>

                                        </td>
                                    <#--</#if>-->

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


<!-- 审核Modal -->
<div class="modal fade" id="dialog-confirm2"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" method="post" id="auditProductAction">
                <input type="hidden" id="batchAuditFlag">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    你确定审核通过吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitAudit()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 拒绝Modal -->
<div class="modal fade" id="dialog-confirm3"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal"  method="post" id="refuseAuditProductAction">
                <input type="hidden" id="refuseAuditFlag">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div style="text-align:center;">
                        <div class="form-horizenal">
                            <div class="form-group">
                                <label class="control-label col-sm-5">拒绝原因:</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" id="refuseReason" maxlength="200" name="refuseReason"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="sub" onclick="submitRefuse()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/summernote.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/summernote-zh-CN.js?version=${VERSION}"></script>
<script src="${bath}/static/bootstrap/js/bootstrap-select.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_checked.js?version=${VERSION}"></script>
<script src="${bath}/static/js/select2.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/ThirdPlantManage/PlantGoodsList.js?version=${VERSION}"></script>
<script type="application/javascript">
    $(function(){
        $('select[data-live-search="true"]').select2();
    })
</script>
</body>
</html>









