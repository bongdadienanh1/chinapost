

<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>对账表</title>

    <!-- Bootstrap -->
    <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/bootstrap-select.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/select2.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/bootstrap-datetimepicker.min.css?version=${VERSION}" rel="stylesheet">
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
                    <li>统计信息</li>
                    <li>对账表</li>
                </ol>
                <h2 class="main_title">对账表<small>(共${pageBean.totalElements!'0'}条)</small></h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <form  id="search_form" method="post" action="reconciliationList" role="form" class="form-inline">
                            <input type="hidden" id="size" name="size" value="10"/>
                            <input type="hidden" id="page" name="page"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">订单编号</span>
                                    <input type="text" class="form-control"name="orderCode" value="${payOrderEntity.orderCode}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">供应商</span>
                                    <select   class="cate_selector w150" name="thirdId" data-live-search="true">
                                        <option selected="selected" value="">---请选择---</option>
                                        <#list platformList as store>
                                            <option value="${store.storeId}" <#if payOrderEntity.thirdId == store.storeId >selected ="selected"</#if>>${store.storeName}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">网点</span>
                                    <select   class="cate_selector w150"  name="enterpriseId" data-live-search="true">
                                        <option value="">---请选择---</option>
                                            <#list enterpriseInfoList as enterprise>
                                                <option value="${enterprise.enterpriseId}" <#if payOrderEntity.enterpriseId == enterprise.enterpriseId >selected ="selected"</#if>>${enterprise.enterpriseName}</option>
                                            </#list>
                                        </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">下单开始时间</span>
                                    <input class="form-control" type="text" id="payTimeStart"
                                           value="${payOrderEntity.orderPayStartTime}" name="orderPayStartTime" readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_datetime w300">
                                    <span class="input-group-addon">下单结束时间</span>
                                    <input class="form-control" type="text" value="${payOrderEntity.orderPayEndTime}" id="payTimeEnd" name="orderPayEndTime"
                                           readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>


                        </form>
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-primary" id="search">搜索</button>

                            <button type="button" class="btn btn-primary" id="exportReconciliation">对账单导出</button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="deleThirdForm" method="post">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <#--<th><input type="checkbox" name="ids" value="-1" onclick="allunchecked(this,'ids');"></th>-->
                                <th>订单编号</th>
                                <th>订单状态</th>
                                <th>下单时间</th>
                                <#--<th>付款时间</th>-->
                                <th>供应商</th>
                                <th>网点</th>
                                <th>网点结算费用（元）</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list pageBean.content as payOrder>
                            <tr>
                                <#--<td><input type="checkbox" name="ids" value="${cs.id }"></td>-->
                                <td>${payOrder.orderCode}</td>
                                <td>${payOrder.orderStatus}</td>
                                <td>${payOrder.orderCreateTime}</td>
                                <#--<td>${payOrder.payTime}</td>-->
                                <td>${payOrder.thirdName}</td>
                                <td>${payOrder.enterpriseName}</td>
                                <td>${payOrder.settlePrice?string("0.00")}</td>
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

<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_checked.js?version=${VERSION}"></script>
<script src="${bath}/static/js/select2.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/bootstrap-datetimepicker.min.js?version=${VERSION}"></script>
<script src="${bath}/static/js/language/bootstrap-datetimepicker.zh-CN.js?version=${VERSION}"></script>
<script src="${bath}/static/js/bootstrap-select.min.js?version=${VERSION}"></script>

<script type="application/javascript">
    $(function(){
        $('select[data-live-search="true"]').select2();
        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            weekStart : 1,
            autoclose : true,
            language : 'zh-CN',
            pickerPosition : 'bottom-left',
            todayBtn : true,
            viewSelect : 'hour'
        });
        $('.form_date').datetimepicker({
            format : 'yyyy-mm-dd',
            weekStart : 1,
            autoclose : true,
            language : 'zh-CN',
            pickerPosition : 'bottom-left',
            minView : 2,
            todayBtn : true
        });
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function(){
            var formItem = $(this);
            if(!$('.form_btns').is(':visible')) {
                formItem.parent().addClass('form_open');
                $('.form_btns').show();
                $('.form_btns').css({
                    'left': formItem.next().offset().left + 70 + 'px',
                    'top': formItem.next().offset().top - 30 + 'px'
                });
                $('.form_sure,.form_cancel').click(function () {
                    $('.form_btns').hide();
                    formItem.parent().removeClass('form_open');
                });
            }
        });

        $("#search").click(function(){
            if(checkEndTime($("#payTimeStart").val(),$("#payTimeEnd").val())){
                $("#search_form").submit();
            }else {
                showTipAlert("开始时间不能大于结束时间！");
            }
        });

        $("#exportReconciliation").click(function(){
            $("#search_form").attr("action","exportPayOrderExcel");
            $("#search_form").submit();
            $("#search_form").attr("action","reconciliationList");
        });
    });
    //查询
    function searchData(pageNumber){
        $("#page").val(pageNumber);
        $("#search").click();
    }

</script>
</body>
</html>
