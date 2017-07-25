<#assign bath = request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>移动站点信息</title>

    <!-- Bootstrap -->
    <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/subject.css?version=${VERSION}" rel="stylesheet">
    <style type="text/css">
        .current_template,.template_list{margin-bottom:20px;}
        .current_template h4,.template_list h4{border-left:2px solid #0099FF;padding-left:10px;font-size:14px;}
        .current_template .body{position:relative;border:1px solid #ddd;padding:10px;}
        .current_template .body .pre_info{margin-left:20px;}
        .current_template .body .pre_info h5{margin-top:0;font-size:16px;}
        .current_template .body .pre_btns{position:absolute;width:100px;bottom:10px;left:210px;}

        .template_item_mobile{position:relative;float:left;margin:0 10px 10px 0;padding:10px;border:1px solid #ddd;}
        .template_item_mobile .ctrl{position:absolute;left:0;bottom:0;width:100%;height:50px;background:rgba(0,0,0,0.5);padding:10px 0;}
        .template_item_mobile .ctrl p{color:#fff;text-indent:10px;}
        .template_item_mobile .ctrl .ctrl_btns{display:none;}
        .add-template a {display:block; width:180px; height:240px; background:url(<%=basePath%>images/mb_add.png) no-repeat center center;}

    </style>
</head>
<body>

<!-- 引用头 -->
<#include "page/header.ftl"/>

<div class="page_body container-fluid">
    <div class="row">

    <#include "page/left.ftl"/>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
            <#include "page/breadcrumbs.ftl"/>

                <div class="common_data" style="padding-left:20px;">
                    <div class="tab-content">
                        <div class="tab-pane active" id="home">
                            <table class="table tb-type2" id="prompt">
                                <tbody>
                                <tr class="space odd">
                                    <th colspan="12">
                                        <div class="title nomargin">
                                            <h5>操作提示</h5>
                                            <span class="arrow"></span>
                                        </div>
                                    </th>
                                </tr>
                                <tr class="odd">
                                    <td>
                                        <ul>
                                            <li>点击添加专题按钮可以添加新的专题，专题描述可以点击后直接修改</li>
                                            <li>点击编辑按钮对专题内容进行修改</li>
                                            <li>点击删除按钮可以删除整个专题</li>
                                            <li>专题链接格式：http://cp.ylife.cn/mobile/subject?subjectId=1（1代表专题编号，可更改）</li>
                                        </ul>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <!-- 列表 -->
                            <form id="list_form" method="post">
                                <table class="table tb-type2">
                                    <thead>
                                    <tr class="space">
                                        <th colspan="15" class="nobg">列表</th>
                                    </tr>
                                    <tr class="thead">
                                        <th class="w12">&nbsp;</th>
                                        <th>专题编号</th>
                                        <th>专题描述</th>
                                        <th class="w200 align-center"><span>操作</span></th>
                                    </tr>
                                    </thead>
                                    <tbody id="treet1">
                                    <#list homePages as homePage>
                                        <tr class="hover">
                                            <td>&nbsp;</td>
                                            <td style="text-align: center;">${homePage.subjectId}</td>
                                            <td style="text-align: center;"><input type="text" name="${homePage.subjectId}" value="${homePage.title}" title="可编辑"></td>
                                            <td class="nowrap align-center" style="text-align: center"><a href="setMobSubject.htm?merchantId=${merchantId }&subjectId=${homePage.subjectId}&flag=${flag}">编辑</a>&nbsp;|&nbsp; <a href="javascript:;" onclick="delSubject(${homePage.subjectId})">删除</a></td>
                                        </tr>
                                    </#list>
                                    <tr style="background: rgb(255, 255, 255);">
                                        <td colspan="20"><a id="btn_add_mb_special" href="javascript:;" class="btn-add marginleft" onclick="addSubject()">添加专题</a></td>
                                    </tr>
                                    </tbody>
                                    <tfoot>
                                    <!-- <tr class="tfoot">
                                      <td colspan="16"><div class="pagination"> <ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><span>下一页</span></li><li><span>末页</span></li></ul> </div></td>
                                    </tr> -->
                                    </tfoot>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
<#--<script src="<%=basePath%>js/summernote.min.js"></script>-->
<#--<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>-->
<#--<script src="<%=basePath%>js/bootstrap-select.min.js"></script>-->
<#--<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>-->
<#--<script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>-->
<script src="${bath}/static/js/common.js?version=${VERSION}"></script>
<script src="${bath}/static/js/common/common_alert.js?version=${VERSION}"></script>
<script type="text/javascript">
    $(function(){
        $("input").blur(function(){
            var subjectId = $(this).attr("name");
            var title = $(this).val();
            $.post("updateSubjectById.htm",{
                subjectId : subjectId,
                title : title
            },function(data){

            });
        });

        $('.template_item_mobile').mouseenter(function() {
            $(this).find('.ctrl p').hide();
            $(this).find('.ctrl .ctrl_btns').show();
        });
        $('.template_item_mobile').mouseleave(function() {
            $(this).find('.ctrl p').show();
            $(this).find('.ctrl .ctrl_btns').hide();
        });
    });

    function delSubject(homepageId){
        showDeleteOneConfirmAlert('deleteSubject.htm?CSRFToken=${token}&subjectId='+homepageId,'确定要删除此专题模板吗？');
    }

    function addSubject(){
        showAddOneConfirmAlert();
    }

</script>
</body>
</html>
