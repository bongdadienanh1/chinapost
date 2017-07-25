<#assign basePath=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <!-- Bootstrap -->
    <link href="${basePath}/static/css/bootstrap.min.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/material.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/ripples.css?v=20151016234" rel="stylesheet">
    <link href="${basePath}/static/css/style.css?version=${VERSION}" rel="stylesheet">
    <link href="${basePath}/static/css/third.css?version=${VERSION}" rel="stylesheet">
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js?v=20151016234"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/jquery.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/bootstrap.min.js?v=20151016234"></script>
    <script src="${basePath}/static/js/ripples.min.js?v=20151016234"></script>
	<script src="${basePath}/static/js/material.min.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/static/js/artDialog4.1.7/plugins/iframeTools.js?v=20151016234"></script>
    <script type="text/javascript" src="${basePath}/static/js/common/common_alert.js?version=${VERSION}"></script>
    <style type="text/css">
        .upload_body{padding:10px;}
        .table_foot .table_ctrl{width:220px;}
    </style>
</head>
<body>
<input id="size" type="hidden" value="${(size)!''}"/>

<div role="tabpanel" class="upload_body">
    <ul class="nav nav-tabs mb20" role="tablist">
        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab"
                                                  data-toggle="tab">上传本地图片</a></li>
    </ul>
    <div class="tab-content">
        <!-- 上传 -->
        <div role="tabpanel" class="tab-pane active" id="tab1">
            <form id="uploadForm" action="uploadFileOneForManage.htm" method="post" enctype="multipart/form-data"
                  target="hidden_frame">
                <input type="hidden" id="size" value="${size}"/>
                <p>
                    <button type="button" class="btn btn-info" id="image">选择图片</button>
                </p>
                <input type="file" id="imgUpload" name="file" style="display: none"/>
            </form>
            <ul class="imgs_list clearfix">

            </ul>
            <input id="fileName" type="hidden" name="fileName"/>
            <iframe name="hidden_frame" style="display:none"></iframe>
        </div>
    </div>
</div>
<script>
    //选择图片
    $(function () {
        $.material.init();
        var parent = art.dialog.parent,				// 父页面window对象
                api = art.dialog.open.api;	// 			art.dialog.open扩展方法
        if (!api) return;
        // 操作对话框
        api.title('选择图片')
                // 自定义按钮
                .button(
                        {
                            name: '保存',
                            callback: function () {
                                var win = art.dialog.open.origin;//来源页面
                                //验证是否有上传文件
                                var size = $("#size").val() > 0 ? $("#size").val() : 1;
                                var url = '';
                                switch ($("li.active a").attr("href")) {
                                    case '#tab1':
                                        var filePaths = $('#fileName').val();
                                        if (filePaths) {
                                            if ((filePaths + "").split(",").length > size) {
                                                art.dialog.alert('上传的图片大于' + size + '张！');
                                            } else {
                                                url = filePaths;
                                            }
                                        } else {
                                            art.dialog.alert('请上传图片！');
                                        }
                                        break;
                                    case '#tab2':
                                        var resoult = checkSelected('imageManageId', size);

                                        //-1：未选中，0：选中的大于可选择的，1：成功
                                        if (resoult == 1) {
                                            url = selectImgUrl("imageManageId");
                                        } else if (resoult == 0) {
                                            art.dialog.alert('选择的图片大于' + size + '张！');
                                        } else {
                                            art.dialog.alert('请选择图片！');
                                        }

                                        break;
                                    case '#tab3':

                                        var urls = $('#imgUrl').val();
                                        if (urls) {
                                            if ((urls + "").split(",").length > size) {
                                                art.dialog.alert('设置的网络图片大于' + size + '张！');
                                            } else {
                                                url = urls;
                                            }
                                        } else {
                                            art.dialog.alert('请设置网络图片！');
                                        }

                                        break;
                                }


                                if (url) {
                                    win.window.saveChoooseTrademark(url, api.config.id);
                                    art.dialog.close();
                                } else {
                                    return false;
                                }
                            },
                            focus: true
                        },
                        {
                            name: '取消',
                            callback: function () {
                                art.dialog.close();
                            }
                        }
                );

        $("#image").click(function () {
            $("#imgUpload").click();
        });
        $("#imgUpload").change(function () {
            var fileObj = document.getElementById("imgUpload").files[0]; // 获取文件对象
            var FileController = "web/uploadPicture";                    // 接收上传文件的后台地址
            var data = [];
            // FormData 对象
            var form = new FormData();// 可以增加表单数据
            form.append("file", fileObj);                           // 文件对象
            var xhr = new XMLHttpRequest();
            xhr.open("POST", FileController, true);
            // XMLHttpRequest 对象
            xhr.send(form);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    str = xhr.responseText;
                    data = JSON.parse(str);
                    if (data.response == 'success') {
                        $(".imgs_list").append('<li><img src="' + data.data + '" style="max-width: 230px; width: auto; height: 230px;"></li>');
                        if ($("#fileName").val() != '') {
                            $("#fileName").val($("#fileName").val() + ',' + data.data);
                        } else {
                            $("#fileName").val(data.data);
                        }
                        return true;
                    }
                    else {
                        $("#imgUpload").val("");
                        showTipAlert(data.data.text);
                        return false;
                    }
                }
                else if (xhr.status == 400) {
                    $("#imgUpload").val("");
                    showTipAlert("上传失败");
                }
            };
        });
    })

</script>
</body>
</html>