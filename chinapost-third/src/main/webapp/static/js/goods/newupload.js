product_image = null;
var editor1;
var editor2;
$(function () {
    $("#dialog-about").hide();

    /* 加载第三方授权分类 */
    //getAllGrandCateForThird();
    /* 加载品牌 */
    //addGoodsBrand();
    /*绑定事件*/
    bindEvent();

    KindEditor.ready(function (K) {
        editor1 = K.create('textarea[name="goods_desc"]',
            {
                width: "80%",
                height: "425px",
                cssPath: 'js/kindeditor/plugins/code/prettify.css', uploadJson: 'js/kindeditor/jsp/upload_json.jsp',
                fileManagerJson: 'js/kindeditor/jsp/file_manager_json.jsp', allowFileManager: true,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
        prettyPrint();
    });


    KindEditor.ready(function (K) {
        editor2 = K.create('textarea[name="mobile_desc"]',
            {
                width: "80%",
                height: "425px",
                cssPath: 'static/js/kindeditor/plugins/code/prettify.css', uploadJson: 'web/imgUpload',
                fileManagerJson: 'static/js/kindeditor/jsp/file_manager_json.jsp', allowFileManager: true,allowImageRemote : true,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
        prettyPrint();
    });
});


/*保存商品*/
function up_goods_info() {
    //判断商品编号是否重复
    //var arr = new Array();
    //$(".dinfo_wp").find("input[name='goodsInfoItemNo']").each(function(){
    //    arr.push($(this).val());
    //    var nary=arr.sort();
    //    for(var i=0;i<arr.length;i++){
    //        if (nary[i]==nary[i+1]){
    //            $(".show_title").text('商品编号重复，请重新填写');
    //            $("#select-tip").modal('show');
    //        }
    //    }
    //})
    var flag = true;;
    $(".dinfo_wp").find(".goodsInfoForm").each(function(){
        if(!$(this).valid()){
            $(".show_title").text('请填写完整货品信息');
            $("#select-tip").modal('show');
            flag = false
            return false;
        }
        if($(this).find(".choose_imgs").html() == ""){
            $(".show_title").text('缺少货品图片');
            $("#select-tip").modal('show');
            flag = false;
            return false;
        };
    });
    if(flag){
        var goodsInfosArr = [];
        $(".dinfo_wp").find('.tab-pane').each(function(){
            var goodsInfosObj = new Object();
            var infoImagesArr = [];
            goodsInfosObj.goodsInfoItemNo = $(this).find('input[name=goodsInfoItemNo]').val();
            goodsInfosObj.goodsInfoName = $(this).find('input[name=goodsInfoName]').val();
            goodsInfosObj.goodsInfoPreferPrice = $(this).find('input[name=goodsInfoPreferPrice]').val();
            goodsInfosObj.goodsInfoSettlePrice = $(this).find('input[name=goodsInfoSettlePrice]').val();
            goodsInfosObj.freightPrice = $(this).find('input[name=freightPrice]').val();
            $(this).find('.choose_imgs li').each(function(){
                var infoImages = new Object();
                infoImages.imageInName = $(this).find("img").attr('src');
                infoImagesArr.push(infoImages)
            });
            goodsInfosObj.infoImages = infoImagesArr;
            goodsInfosObj.goodsInfoStock = $(this).find('input[name=goodsInfoStock]').val();
            goodsInfosArr.push(goodsInfosObj);
        })
        goodsObj.goodsInfos = goodsInfosArr;
        console.log(JSON.stringify(goodsObj));
        $.post("goodsManager/newUploadGood",
            {
                goodsJson:JSON.stringify(goodsObj),
                goodsDetailDesc:editor2.html()
            },function(data){
                if(data.response == 'error'){
                    $(".show_title").text(data.data.text);
                    $("#select-tip").modal('show');
                }
                else{
                    showConfirmAlertTips("添加成功！","refresh()");
                }
            })

    }
}

function refresh(){
    window.location.href="goodsManager/getGoodsInfo?auditStatus=0";
}

function call_save_goods(data) {
    if (parseFloat(data) > 0) {
        /* 保存完基本信息之后保存详细介绍,完成之后保存货品信息 */
        $(".new_goods_id").val(data);
        $(".save_goods_desc").submit();
    } else {
        showNoDeleteConfirmAlert('出现异常,请重试!');
    }
}

/**
 * @param data : 商品ID
 * @param isThirdAuditUs: 平台商家商品审核开关
 */
function call_save_desc(data) {
    savePro(data);
}

/* 绑定事件 */
function bindEvent() {
    $(".qf_box:first").show();
    $(".spec_list li").each(function () {
    });
    $(".spec_list li").each(function (n) {
        var _this = $(this);
        _this.find("input[type='checkbox']").change(function () {
            if ($(this).prop("checked") == true) {
                $(".spec_box:eq(" + n + ")").show();
            } else {
                $(".spec_box:eq(" + n + ")").hide();
                $(".openSpecValue_" + $(this).val()).prop("checked", false);
                $(".spec_img_form_" + $(this).val()).remove();
            }
            ;
        });
    });
    $(".dw_box a").click(function () {
    });
    $(".dw_box a").click(function () {
        var _this = $(this);
        _this.parent().prev("input[type='text']").val(_this.text());
    });

    // $(".qf_box:first").show();
    // $(".prev_step").click(function(){});
    $(".prev_step").click(function () {
        /*
         * $("body,html").animate({ scrollTop: 0 },0);
         * $(".dinfo_tabs").html(""); $(".dinfo_wp").html("");
         * $(".qf_box:last").hide(); $(".qf_box:first").show();
         * $(".scg_dl").show();
         */
    });
    $(".sb_wp input[type='checkbox']").change(function () {
    });
    $(".sb_wp input[type='checkbox']").change(function () {
        $(".spec_box").each(function () {
            var _this = $(this);
            if (_this.find("input:checkbox[checked]").length > 0) {
                _this.attr("name", "ok");
            } else {
                _this.attr("name", "no");
            }
            ;
        });
    });
    $(".sb_wp input[type='checkbox']").change(function () {
    });
    $(".sb_wp input[type='checkbox']").change(function () {
        $(".new_box").each(function () {
            var _this = $(this);
            if (_this.find("input:checked").length > 0) {
                _this.attr("name", "ok");
            } else {
                _this.attr("name", "no");
            }
            ;
        });
    });
    // $("#create_gds").click(function(){});
    //$("#create_gds").click(function () {
    //    $("body,html").animate({
    //        scrollTop: 0
    //    }, 0);
    //    // if(checkBase()){
    //    /*$(".name_input").val("");
    //     $(".des_input").val("");*/
    //    $(".dinfo_tabs").html("");
    //    $(".dinfo_wp").html("");
    //    test();
    //    $(".qf_box:first").hide();
    //    $(".scg_dl").hide();
    //    $(".qf_box:last").show();
    //    // }
    //});

    /* 当选择规格图片的按钮发生改变时触发 */
    $(".spec_img").change(function () {
    });
    $(".spec_img").change(function () {
        $(".specValue_" + $(this).attr("spec_val_id")).submit();
    });

    /* 当选择货品图片的按钮发生改变时触发 */
    $(".up_pro_img").change(function () {
    });
    $(".up_pro_img").change(function () {
        product_image = $(this);
        $(product_image).parent().submit();
    });

    /* 选择图片 */
//	$(".choose_img_btn").click(function() {
//	});
    $(".choose_img_btn").click(function () {
        art.dialog.open('queryImageManageByChoose.htm?size=10000', {
            lock: true,
            width: '800px',
            height: '400px',
            title: '选择图片'
        });
    });

}

// 保存选择的图片信息
function saveChoooseTrademark(path) {
    if (path.toString().indexOf(",") > -1) {
        var paths = path.toString().split(",");
        for (var i = 0; i < paths.length; i++) {
            var path2 = paths[i];
            $(".dinfo_wp")
                .find(".active")
                .find(".choose_imgs")
                .append(
                    '<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + path2 + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + path2 + ' width="100" height="100" /></a></li>');
        }
    } else {
        $(".dinfo_wp")
            .find(".active")
            .find(".choose_imgs")
            .append(
                '<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + path + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + path + ' width="100" height="100" /></a></li>');
    }
    showConfirmAlert('要将这些图片应用到其他货品吗?', 'copyImageToOtherSpec(\'' + path + '\')');
}

function del_ts(obj) {
    $(obj).parent().remove();
}

/* 点击复制到全部名称 */
function copyAllSubTitle(obj) {
    $(".des_input").val($(obj).parent().find(".des_input").val());
}

function cp_all(t) {
    $(".choose_img_btn").unbind('click');
    var _t = 0;
    for (var i = 1; i < $(".dinfo_wp .dinfo_box").length; i++) {
        _t = $(t).parents(".dinfo_box").find("dl").clone();
        _t.find(".nameWp input[type='radio']").attr("name", "_rad" + i);
        _t.find(".customerDiscount input[type='radio']").attr("name", "_discount" + i);
        _t.find(".p_code").val("");
        $(".dinfo_wp .dinfo_box:eq(" + i + ") dl").remove();
        $(".dinfo_wp .dinfo_box:eq(" + i + ")").prepend(_t);
        $(t).parents(".dinfo_box").find(".sec_des").click();
    }
    ;
    $(".choose_img_btn").click(function () {
        art.dialog.open('queryImageManageByChoose.htm?size=10000', {
            lock: true,
            width: '800px',
            height: '400px',
            title: '选择图片'
        });
    });
}

// 点击切换
function ctabs(t1, t2, t3) {
    $("." + t1).find("li:first").addClass("cur");
    $("." + t2).find("." + t3 + ":first").show().addClass("show");
    $("." + t1 + " li").each(function (n) {
        var current = $(this);
        $(this).find("a").click(function () {
            var cur = $(this);
            $("." + t1).find("li.cur").removeClass("cur");
            $("." + t2).find("." + t3 + ".show").hide().removeClass("show");
            current.addClass("cur");
            $("." + t2 + " ." + t3 + ":eq(" + n + ")").show().addClass("show");
        });
    });
};

///* 获取第三方授权分类 */
//function getAllGrandCateForThird() {
//    if ($(".fir_list li").length <= 0) {
//        $.get("queryAllGrandCateForThird.htm", function (data) {
//            if (null != data && data.length > 0) {
//                $(".fir_list").html("");
//                for (var i = 0; i < data.length; i++) {
//                    $(".fir_list").append(
//                        " <li><a onclick='loadTypeParam(this);' role-id=" + data[i].catId
//                        + " href='javascript:;'>" + data[i].catName + "</a></li>");
//                }
//            }
//        });
//    }
//}
///* 加载第二级分类 */
//function loadSecCate(catId, obj) {
//    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
//    $(".sec_search").val("");
//    $(".thi_search").val("");
//    $.get("querySonCateByCatId.htm?catId=" + catId + "&CSRFToken=" + $(".token_val").val(),
//        function (data) {
//            if (null != data && data.length > 0) {
//                $(".sec_list").html("");
//                $(".thi_list").html("");
//                for (var i = 0; i < data.length; i++) {
//                    $(".sec_list").append(
//                        " <li><a role-id=" + data[i].catId + " onclick='loadThiCate("
//                        + data[i].catId + ",this)' href='javascript:;'>"
//                        + data[i].catName + "</a></li>");
//                }
//            }
//        });
//}
///* 加载第三级分类 */
//function loadThiCate(catId, obj) {
//    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
//    $(".thi_search").val("");
//    allThirdThirdCat = null;
//    $.get("querySonCateByCatId.htm?catId=0&CSRFToken=" + $(".token_val").val(), function (data) {
//        if (null != data && data.length > 0) {
//            $(".thi_list").html("");
//            for (var i = 0; i < data.length; i++) {
//                /* 点击事件写在goods_list.js中 */
//                $(".thi_list").append(
//                    " <li><a onclick='loadTypeParam(this);' role-id=" + data[i].catId
//                    + " href='javascript:;'>" + data[i].catName + "</a></li>");
//            }
//        }
//    });
//}
//
///* 搜索类目 */
//var allFirstThirdCat = new Array();
//function searchCate() {
//    /* 如果一级分类的全局变量为空 */
//    if (null == allFirstThirdCat || allFirstThirdCat.length <= 0) {
//        allFirstThirdCat = $(".fir_list li");
//    }
//    /* 循环去匹配记录 */
//    if (null != allFirstThirdCat && allFirstThirdCat.length > 0) {
//        var searchList = new Array();
//        for (var i = 0; i < allFirstThirdCat.length; i++) {
//            var second = $(allFirstThirdCat[i]);
//            if ($($(second).find("a")).html().indexOf($.trim($(".fir_search").val())) >= 0) {
//                searchList.push($(second));
//            }
//        }
//        /* 如果查询参数是空,就显示全部 */
//        if ($.trim($(".fir_search").val()) == "" || $(".fir_search").val() == null) {
//            searchList = allFirstThirdCat;
//        }
//        /* 清空分类 */
//        $(".fir_list").html("");
//        /* 如果搜索到的记录不为空,就添加到页面 */
//        for (var i = 0; i < searchList.length; i++) {
//            $(".fir_list").append(
//                " <li><a onclick='loadTypeParam(this)' role-id=" + $(searchList[i]).find("a").attr("role-id") + " href='javascript:;'>" + $(searchList[i]).find("a").html()
//                + "</a></li>");
//        }
//    }
//}

///* 根据选中的第三级分类加载类型参数 */
//function loadTypeParam(obj) {
//
//}
/* 点击开启规格值的时候 */
//function checkSpecValue(obj) {
//    if ($(obj).prop("checked")) {
//        $(obj)
//            .parent().parent()
//            .append(
//                "<form action='uploadImgSingle.htm' method='post' target='hidden_frame'  enctype='multipart/form-data'  class='specValue_"
//                + $(obj).attr("spec_value_id")
//                + " spec_img_form_"
//                + $(obj).attr("spec_id")
//                + "'>"
//                + "<div class='spec_value_img'>"
//                + "<a href='javascript:;' class='add_img'></a>"
//                + "<input type='hidden' name='specValId' value="
//                + $(obj).attr('spec_value_id')
//                + ">"
//                + "<input type='hidden' class='up_spec_img_src up_spec_img_src_"
//                + $(obj).attr("spec_value_id")
//                + "' value=''>"
//                + "<input type='file' name='specImg' spec_val_id="
//                + $(obj).attr("spec_value_id")
//                + " class='spec_img spec_img_"
//                + $(obj).attr("spec_value_id") + "' />"
//                + "</div>"
//                + "</form>"
//            );
//        bindEvent();
//    } else {
//        $(".up_spec_img_" + $(obj).attr("spec_value_id")).remove();
//        $(".specValue_" + $(obj).attr("spec_value_id")).remove();
//    }
//}


/* 上传规格图片回调 */
//function specImgSucc(msg) {
//    if (msg == "111") {
//        openDialog("不支持的图片格式");
//    } else {
//        var id = msg.substring(msg.indexOf("-") + 1, msg.length);
//        var url = msg.substring(0, msg.indexOf("-"));
//        // 如果该规格已经上传过图片就替换已经上传的图片
//        if ($(".up_spec_img_" + id)[0]) {
//            $(".up_spec_img_" + id).attr("src", url);
//        } else {
//            $(".specValue_" + id).before(
//                "<img src=" + url + " class='value_img up_spec_img_" + id
//                + "'/>");
//        }
//        $(".up_spec_img_src_" + id).val(url);
//    }
//}
///* 上传货品图片SUCC */
//function productImgSucc(msg) {
//    if (msg == "111") {
//        openDialog("不支持的图片格式");
//    } else {
//        var params = msg.split("-");
//        $(product_image)
//            .parent()
//            .before(
//                "<img class='vm up_pro_img' src="
//                + params[1]
//                + "  width='50' height='50' /><input type='hidden' class='product_up_img' value="
//                + params[0] + ">");
//    }
//}


/**
 * 添加商品成功 使用
 * @param addUrl 继续添加
 */
function saveGoodsOk(addUrl, listUrl, tips) {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">' +
        '    <div class="modal-dialog">' +
        '        <div class="modal-content">' +
        '            <div class="modal-header">' +
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
        '               <h4 class="modal-title">系统提示</h4>' +
        '           </div>' +
        '           <div class="modal-body">';
    if (tips != '' && tips != undefined) {
        confirmDialog += tips;
    } else {
        confirmDialog += '添加成功，继续添加商品吗？';
    }
    confirmDialog += '           </div>' +
        '           <div class="modal-footer">' +
        '               <button type="button" class="btn btn-primary" onclick="jumpUrl(\'' + addUrl + '\');">继续添加</button>' +
        '               <button type="button" class="btn btn-default" onclick="jumpUrl(\'' + listUrl + '\');">商品列表</button>' +
        '           </div>' +
        '       </div>' +
        '   </div>' +
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function jumpUrl(url) {
    window.location.href = url;
}

/**
 * 验证添加商品基本信息
 */
function checkBase() {
    var bool = true;
    if (!$(".ch_goods_cate").val() > 0) {
        openDialog("请选择商品分类!");
        bool = false;
    }
    /*
     * bool = checkLengthForJQ($(".dw_input"),$(".dw_input_Tips"),"计价单位不能为空!") &&
     * bool;
     * if(checkLengthForJQ($(".dw_input"),$(".dw_input_Tips"),"计价单位不能为空!")){
     * bool = checkSymbForJQ($(".dw_input"),$(".dw_input_Tips")) && bool; }
     */
    /* 如果选择过了分类就验证是否选择规格 */
    if ($(".ch_goods_cate").val() > 0) {
        if ($(".type_spec:checked").length <= 0 || $(".check_spec:checked").length <= 0) {
            openDialog("请至少选择一个商品规格!");
            bool = false;
        } else {
            var spe_text = $(".spe_text");
            for (var i = 0; i < spe_text.length; i++) {
                bool = checkLengthForJQ($(spe_text[i]), $(".spec_remark_input_Tips"), "规格值不能为空!")
                    && bool;
                if (checkLengthForJQ($(spe_text[i]), $(".spec_remark_input_Tips"), "规格值不能为空!")) {
                    bool = checkSymbForJQ($(spe_text[i]), $(".spec_remark_input_Tips")) && bool;
                }
            }
        }
    }
    return bool;
}


/* 验证货品编号是否已经存在 */
function checkProNo(obj) {
    var isValid = true;
    if ($(obj).val() != '' && $(obj).val() != undefined) {
        $(obj).removeClass('error');
        $(obj).next('.error').remove();
    } else {
        $(obj).addClass('error');
        $(obj).next('.error').remove();
        $(obj).after('<label class="error">不能为空</label>');
        isValid = false;
        return isValid;
    }
    var numandletterReg = /^[0-9a-zA-Z]+$/;
    if ($(obj).val() != '' && (numandletterReg.test($(obj).val()))) {
        $(obj).removeClass('error');
        $(obj).next('.error').remove();
    } else {
        $(obj).addClass('error');
        $(obj).next('.error').remove();
        $(obj).after('<label class="error">请输入字母或数字</label>');
        isValid = false;
        return isValid;
    }
    /*验证货品编号是否已经存在*/
    if ($(obj).val() != undefined && $(obj).val().trim().length > 0) {
        //var param = "checkProductNo.htm?productNo=" + $(obj).val() + "&time=" + Math.random();
        //$.ajax({
        //    type: 'post',
        //    url: param,
        //    async: false,
        //    success: function (data) {
        //        if (!data) {
        //            $(obj).addClass('error');
        //            $(obj).next('.error').remove();
        //            $(obj).after('<label class="error">编号已经存在</label>');
        //            $(obj).parent().find(".exist_flag").val("1");
        //            isValid = false;
        //        } else {
        //            /*验证当前输入的是否有重复*/
        //            var inputs = $(".dinfo_wp .no_input");
        //            var count = 0;
        //            for (var i = 0; i < inputs.length; i++) {
        //                var no = $(inputs[i]).val();
        //                if ($(obj).val() == no) {
        //                    count = parseInt(count) + parseInt(1);
        //                }
        //            }
        //            /*如果数量大于1,其中有一个是自身*/
        //            if (parseInt(count) > 1) {
        //                $(obj).addClass('error');
        //                $(obj).next('.error').remove();
        //                $(obj).after('<label class="error">编号已经使用</label>');
        //                $(obj).parent().find(".exist_flag").val("1");
        //                isValid = false;
        //            } else {
        //                $(obj).removeClass('error');
        //                $(obj).next('.error').remove();
        //                $(obj).parent().find(".exist_flag").val("0");
        //            }
        //        }
        //    }
        //});
    }
    return isValid;
}
/* 设置库房默认价格 */
function price_default(obj) {
    if (/^[0-9]+[.]{0,1}[0-9]{0,2}$/.test($(obj).val())) {
        $(obj).parent().parent().find(".ware_price").val($(obj).val());
    }
}

//检查js特殊字符 “&”
function stripscript(s) {
    var regexp = new RegExp("[&#]");
    if (regexp.test(s)) {
        return false;
    } else {
        return true;
    }

}

/* 开启弹出框 */
function openDialog(tipValue) {
    $(".dia_tip").text(tipValue);
    $("#dialog-tip").dialog({
        resizable: false,
        height: 150,
        width: 270,
        modal: true,
        autoOpen: true,
        buttons: {
            "确定": function () {
                $(this).dialog("close");
            }
        }
    });
    $("#dialog-tip").dialog("open");
}

/* 根据传递的tab对象显示对应的tab窗体 */
function show_tab(tab_box) {
    // $(".dinfo_wp").find(".dinfo_box").hide();
    $(tab_box).show();
    var _n = tab_box.index() - 1;
    $(".dinfo_tabs li").removeClass("cur");
    $(".dinfo_tabs li:eq(" + _n + ")").addClass("cur");
}

function changeValueId(obj) {
    $(obj.parentNode.firstChild).val($(obj).val());
}

//function tx() {
//    $(".spec_cont input[type='checkbox']").each(function () {
//        var _this = $(this);
//        var _v = _this.next("span").text();
//        var _text = '<input class="spe_text" onchange="changeValueId(this)"  type="text" value="' + _v + '" />';
//        _this.change(function () {
//            if (_this.prop("checked") == true) {
//                _this.next("span").hide();
//                _this.after(_text);
//            } else {
//                _this.next().next("span").show().text(_this.next("input").val());
//                _this.next("input").remove();
//
//            }
//        });
//    });
//}

function choose_img(c) {
    if ($(c).hasClass("choose")) {
        $(c).removeClass("choose");
    } else {
        $(c).addClass("choose");
    }
};

/*-------------------------------------------------------------------*/
// 根据jq对象验证特殊字符，将调试显示到页面中
function checkSymbForJQ(inputobj, Tipobj) {
    var regexp = new RegExp("[''\\[\\]<>?!]");
    if (regexp.test($(inputobj).val())) {
        $(inputobj).addClass("ui-state-error");
        updateTips("输入的内容包含特殊字符!", $(Tipobj));
        return false;
    } else {
        $(inputobj).removeClass("ui-state-error");
        updateTipsSucc(null, $(Tipobj));
        return true;
    }
}

/*--------------------------------------------------------------------------------------------------------*/

function addGoodsBrand() {
    $.get("queryGrandBrandByThirdId.htm", function (data) {
        if (data != null && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                $("#goods_brand").append(
                    "<option value='" + data[i].brandId + "'>" + data[i].brandName
                    + "</option>");
            }
            //初始化样式
            /*$("#goods_brand").chosen();*/
        }
    });
}

//反选
function unSelectAll(obj) {
    var checkboxs = document.getElementsByName(obj);
    for (var i = 0; i < checkboxs.length; i++) {
        var e = checkboxs[i];
        e.checked = !e.checked;
    }
    for (var j = 0; j < checkboxs.length; j++) {
        if (checkboxs[j].checked) {
            $(checkboxs[j]).parent().parent().addClass("trbcak");
        } else {
            $(checkboxs[j]).parent().parent().removeClass("trbcak");
        }
    }
}

// 全选
function selectAlls(obj) {
    var checkboxs = document.getElementsByName(obj);
    for (var i = 0; i < checkboxs.length; i++) {
        var e = checkboxs[i];
        e.checked = true;
        $(e).parent().parent().addClass("trbcak");
    }
}

function showAddGoodsRelModal() {
    queryGoodsByParam(1);
    $("#addGoodsRelModal").modal("show");
}

function queryGoodsByParam(pageNo) {
    var goodsName = $("#goods_name").val();
    var goodsNo = $("#goods_no").val();
    var goodsBrandId = $("#goodsBrandId").val();
    /*根据选中分类加载相关商品*/
    $.get("queryThirdGoodsByParamAjax.htm?goodsCateId=" + $('#parentId3').val() + "&pageNo=" + pageNo + "&goodsNo=" + goodsNo + "&goodsName=" + goodsName + "&goodsBrandId=" + goodsBrandId + "&showFlag=1", function (data) {
        var aboutGoodsHtml = "";
        var list = data.list;
        if (list.length > 0) {
            for (var i = 0; i < list.length; i++) {
                aboutGoodsHtml = aboutGoodsHtml + "<tr><td><input type='checkbox' goods_name='" + list[i].goodsName + "' goods_no='" + list[i].goodsNo + "' goods_img='" + list[i].goodsImg + "' class='form-control ch_about' name='aboutGoodsIdSelect' value='" + list[i].goodsId + "'/></td>" + "<td><img width='50' height='50' src=" + list[i].goodsImg + " /></td><td title='" + list[i].goodsNo + "'>" + list[i].goodsNo + "</td><td>" + list[i].goodsName + "</td>";
                aboutGoodsHtml = aboutGoodsHtml + "<td>" + list[i].goodsType.typeName + "</td><td>" + list[i].goodsBrand.brandName + "</td></tr>";
            }
        }
        else {
            aboutGoodsHtml = aboutGoodsHtml + "<tr><td colspan='6'>选择的分类下暂时还没有商品</td></tr>";
        }
        $("#aboutGoodsList tbody").html(aboutGoodsHtml);


        /*添加页脚*/
        $(".pagination").html("");
        var foot = "";
        if (data.pageNo == 1) {
            foot += '<li class="disabled"> <a href="javascript:;" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li> ';
        } else {
            foot += "<li><a href='javascript:;' onclick='queryGoodsByParam(" + data.prePageNo + ")'><span aria-hidden='true'>&laquo;</span></a></li>";
        }
        var i = 1;
        for (var l = data.startNo; l <= data.endNo; l++) {
            if ((i - 1 + data.startNo) == data.pageNo) {
                foot = foot + '<li class="active"><a href="javascript:;"> ' + data.pageNo + ' </a></li>';
            }
            else {
                foot = foot + "<li><a onclick='queryGoodsByParam(" + (i - 1 + data.startNo) + ")' href='javascript:;'>" + (i - 1 + data.startNo) + "</a></li>";
            }
            i++;
        }
        foot = foot + "<li><a onclick='queryGoodsByParam(" + data.nextPageNo + ")' href='javascript:;'><span aria-hidden='true'>&raquo;</span></a></li>";
        /*添加tfoot分页信息*/
        $(".pagination").html(foot);
    });

}

function saveRelGoods() {
    var html = '';
    var selecteOne = false;
    $("input[name=aboutGoodsIdSelect]:checkbox").each(function () {
        if ($(this).is(':checked') == true) {
            selecteOne = true;
            if ($("#selectedGoods" + $(this).val()) == undefined || $("#selectedGoods" + $(this).val()).length <= 0) {
                html += '<tr id="rel_goods_tr' + $(this).val() + '">' +
                    '   <td><img src="' + $(this).attr("goods_img") + '" width="50px"></td>' +
                    '   <td><input type="hidden" name="aboutGoodsId" value="' + $(this).val() + '"/><input type="hidden" id="selectedGoods' + $(this).val() + '" value="' + $(this).val() + '"/>' +
                    '       <span>' + $(this).attr("goods_no") + '</span>' +
                    '   </td>' +
                    '   <td><span>' + $(this).attr("goods_name") + '</span></td>' +
                    '   <td><span><a href="javascript:;" onclick="$(\'#rel_goods_tr' + $(this).val() + '\').remove();">删除</a></span></td>' +
                    '</tr>'
            }
        }
    });
    if (!selecteOne) {
        showTipAlert("至少选择一个商品，才能保存！");
        return;
    }
    $("#select_rel_goods tbody").append(html);
    $("#select_rel_goods thead").show();
    $("#addGoodsRelModal").modal("hide");
}

/**
 * 全选反选
 * @param obj
 * @param name
 */
function allunchecked(obj, name) {
    if (obj.checked) {
        $("input[name='" + name + "']").each(function () {
            this.checked = true;
        });
    } else {
        $("input[name='" + name + "']").each(function () {
            this.checked = false;
        });
    }
}

/**
 * 生成编号
 * @param obj
 */
function generateProNo(obj) {
    //var proNo = parseInt(new Date().format("yyyyMMddhhmmss") + "1");
    //var proNoInput = $(".dinfo_wp").find(".active").find(".p_code");
    //$(proNoInput).val(proNo);
    //showConfirmAlert("要生成其他货品的编号吗?", "generateOtherProNo(" + proNo + ")");
    //checkProNo($(proNoInput));
    $.post("goodsManager/createGenerator",{count:$(".dinfo_tabs").find("li").length},function(data){
        for(var i =0 ;i<data.data.length;i++){
            $(".dinfo_wp").find("input[name=goodsInfoItemNo]").eq(i).val(data.data[i]);
        }
    })
}

function generateOtherProNo(proNo) {
    var proNoInput = $(".dinfo_wp").find(".tab-pane").not(".active").find(".p_code");
    for (var i = 0; i < proNoInput.length; i++) {
        $(proNoInput[i]).val(proNo + (i + 1));
    }
    $("#modalDialog").modal("hide");
}

function displayBatchSetStock(obj) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_stock" + spe_id).css("display", "inline-block");
    $(obj).hide();
    $("#do_batch_set_stock_ctrl" + spe_id).show();
    $("#cancel_batch_set_stock_ctrl" + spe_id).show();
}

function displayBatchSetPrice(obj) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_price" + spe_id).css("display", "inline-block");
    $(obj).hide();
    $("#do_batch_set_price_ctrl" + spe_id).show();
    $("#cancel_batch_set_price_ctrl" + spe_id).show();
}

function hideBatchSetStock(obj) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_stock" + spe_id).hide();
    $("#batch_set_stock_ctrl" + spe_id).show();

    $("#do_batch_set_stock_ctrl" + spe_id).hide();
    $("#cancel_batch_set_stock_ctrl" + spe_id).hide();
}

function hideBatchSetPrice(obj) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_price" + spe_id).hide();
    $("#batch_set_price_ctrl" + spe_id).show();

    $("#do_batch_set_price_ctrl" + spe_id).hide();
    $("#cancel_batch_set_price_ctrl" + spe_id).hide();
}

function doBatchSetStock(obj) {
    var spe_id = $(obj).attr("spe_id");
    var stock = $("#batc_set_stock" + spe_id).val();
    var digitsReg = /^[0-9]+$/;
    if (stock != '' && (digitsReg.test(stock))) {
        $(obj).removeClass('error');
        $(obj).prev('.error').remove();
    } else {
        $(obj).addClass('error');
        $(obj).prev('.error').remove();
        $(obj).before('<label class="error">请输入整数</label>');
        return;
    }
    $("#tab" + spe_id).find(".ware_stock").each(function () {
        $(this).val(stock);
    })
    checkProductForm();
    hideBatchSetStock(obj);
}

function doBatchSetPrice(obj) {
    var spe_id = $(obj).attr("spe_id");
    var price = $("#batc_set_price" + spe_id).val();
    var numberReg = /^[0-9]+[.]{0,1}[0-9]{0,2}$/;
    if (price != '' && (numberReg.test(price))) {
        $(obj).removeClass('error');
        $(obj).prev('.error').remove();
    } else {
        $(obj).addClass('error');
        $(obj).prev('.error').remove();
        $(obj).before('<label class="error">请输入数字</label>');
        return;
    }
    $("#tab" + spe_id).find(".ware_price").each(function () {
        $(this).val(price);
    })
    checkProductForm();
    hideBatchSetPrice(obj);
}

function copyWareToOtherSpec(obj) {
    var spe_id = $(obj).attr("spe_id");
    var from_pro = $("#tab" + spe_id);
    var pro_box = $(".dinfo_wp .tab-pane");
    if (null != pro_box && pro_box.length > 0) {
        for (var j = 0; j < pro_box.length; j++) {
            var _pro = $(pro_box[j]);
            var wares = _pro.find(".ware_id");
            if (null != wares && wares.length > 0) {
                for (var i = 0; i < wares.length; i++) {
                    $(_pro.find(".ware_stock")[i]).val(from_pro.find(".ware_stock")[i].value);
                    $(_pro.find(".ware_price")[i]).val(from_pro.find(".ware_price")[i].value);
                }
            }
        }
    }
    showTipAlert("操作成功，已将此设置应用到其他货品！");
}

function showTipAlert(tip) {
    $("#modalDialog").remove();
    var dialogHtml =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">' +
        '    <div class="modal-dialog">' +
        '        <div class="modal-content">' +
        '            <div class="modal-header">' +
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
        '               <h4 class="modal-title">操作提示</h4>' +
        '           </div>' +
        '           <div class="modal-body" style="text-align: center;">' +
        tip +
        '           </div>' +
        '           <div class="modal-footer">' +
        '               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">确定</button>' +
        '           </div>' +
        '       </div>' +
        '   </div>' +
        '</div>';
    $(document.body).append(dialogHtml);
    $('#modalDialog').modal('show');
}

/**
 * 简单的确认框，返回true或false
 */
function showConfirmAlertTips(tips,functionName) {
    $('#modalDialog').remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">确认提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">'
        +tips+
        '           </div>'+
        '           <div class="modal-footer">'+
        '             <button type="button" class="btn btn-primary" onclick="'+functionName+'">确定</button>'+
        //'               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

//function addEvent() {
//    $(".chooseProimg").click(function () {
//        i = 1;
//        art.dialog.open('queryImageManageByChoose.htm?size=10000', {
//            lock: true,
//            opacity: 0.3,
//            width: '900px',
//            height: '620px',
//            title: '选择图片'
//        });
//    });
//
//}


function defaultActive() {
    $(".dinfo_tabs li:first-child").addClass("active");
    $(".dinfo_wp .tab-pane:first-child").addClass("active");
}

function checkProductForm() {
    var f = true;
    var forms = $(".dinfo_wp .form-horizontal");
    for (var i = 0; i < forms.length; i++) {
        var requireds = $(forms[i]).find(".required");
        for (var j = 0; j < requireds.length; j++) {
            if (requireds[j].value == '') {
                $(requireds[j]).addClass('error');
                $(requireds[j]).next('.error').remove();
                $(requireds[j]).after('<label class="error">不能为空</label>');
                f = f && false;
            } else {
                $(requireds[j]).removeClass('error');
                $(requireds[j]).next('.error').remove();
            }
        }
        var numbers = $(forms[i]).find(".number");
        var numberReg = /^[0-9]+[.]{0,1}[0-9]{0,2}$/;
        for (var j = 0; j < numbers.length; j++) {
            if (numbers[j].value != '' && (numberReg.test(numbers[j].value))) {
                $(numbers[j]).removeClass('error');
                $(numbers[j]).next('.error').remove();
            } else {
                $(numbers[j]).addClass('error');
                $(numbers[j]).next('.error').remove();
                $(numbers[j]).after('<label class="error">请输入合法的数字</label>');
                f = f && false;
            }
        }


        var digits = $(forms[i]).find(".digits");
        var digitsReg = /^[0-9]+$/;
        for (var j = 0; j < digits.length; j++) {
            if (digits[j].value != '' && (digitsReg.test(digits[j].value))) {
                $(digits[j]).removeClass('error');
                $(digits[j]).next('.error').remove();
            } else {
                $(digits[j]).addClass('error');
                $(digits[j]).next('.error').remove();
                $(digits[j]).after('<label class="error">请输入整数</label>');
                f = f && false;
            }
        }

        var exist_flag = $(forms[i]).find(".exist_flag").val();
        if (exist_flag == '0') {
            $(exist_flag).removeClass('error');
            $(exist_flag).next('.error').remove();
        } else {
            $(exist_flag).addClass('error');
            $(exist_flag).next('.error').remove();
            $(exist_flag).after('<label class="error">商品编号不正确</label>');
            f = f && false;
        }

    }
    return f;
}

function changeAllPrice(obj,thisClass) {
    var _thisVal = $(obj).val();
    if($("#modalDialog").css("display") != "block"){
        showConfirmAlert('要将该价格应用到其他货品么？', 'firmAllPrice(\'' + _thisVal + '\',\''+thisClass+'\')');
    }
}

function firmAllPrice(_thisVal,thisClass){
    if (_thisVal != '' && /^([0-9][0-9]*(\.[0-9]{1,2})?|0\.(?!0+$)[0-9]{1,2})$/.test(_thisVal)) {
        var panes = $(".dinfo_wp").find(".tab-pane");
        var thisClass = '.'+thisClass;
        var cprice = _thisVal;
        if (panes.length != 0) {
            panes.find(thisClass).each(function () {
                $(this).val(cprice);
            });
        }
        $("#modalDialog").modal("hide");
    } else {
        showTipAlert('请输入正确的价格！');
    }
}

function changeAllStock(obj,thisClass) {
    var _thisVal = $(obj).val();
    if($("#modalDialog").css("display") != "block"){
        showConfirmAlert('要将该库存应用到其他货品么？', 'firmAllStock(\'' + _thisVal + '\',\''+thisClass+'\')');
    }
}

function firmAllStock(_thisVal,thisClass){
    var panes = $(".dinfo_wp").find(".tab-pane");
    var thisClass = '.'+thisClass;
    var cprice = _thisVal;
    if (panes.length != 0) {
        panes.find(thisClass).each(function () {
            $(this).val(cprice);
        });
    }
    $("#modalDialog").modal("hide");
}

function copyImageToOtherSpec(path) {
    if (path.toString().indexOf(",") > -1) {
        var paths = path.toString().split(",");
        for (var i = 0; i < paths.length; i++) {
            var path2 = paths[i];
            $(".dinfo_wp").find(".tab-pane").not(".active").find(".choose_imgs").append('<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + path2 + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + path2 + ' width="100" height="100" /></a></li>');
        }
    } else {
        $(".dinfo_wp").find(".tab-pane").not(".active").find(".choose_imgs").append('<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + path + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + path + ' width="100" height="100" /></a></li>');
    }
    $("#modalDialog").modal("hide");
}

function checkImage() {
    var f = true;
    var forms = $(".tab-pane");
    for (var i = 0; i < forms.length - 1; i++) {
        var s = $(forms[i]).find(".choose_imgs").html();
        if (s == '') {
            showTipAlert('请添加对应货品的图片！');
            f = f && false;
        }

    }
    return f;
}

/**
 * 点击商品规格
 * */
function getSpecShowModle(_spec){
    //加载规格名称
    $.post("goodsManager/queryAllSpec",function(data){
        if(data.response == "success"){
            var options = "<option value=''>请选择规格</option>";
            if(_spec){
                options="";
                $(".specTitle").html('修改规格值');
            }else {
                $(".specTitle").html('添加规格值');
                $(".goodsSpeaInfo").html("")
            }
            for( var i=0; i<data.data.length; i++){
                var auth=data.data[i];
                if(auth.specId == _spec){
                    options +=  "<option selected='selected' value='"+auth.specId+"'>"+auth.specName+"</option>";
                }else {
                    options +=  "<option value='"+auth.specId+"'>"+auth.specName+"</option>";
                }
            }
            $("#goodsSpecName").html(options);
            $("#goodsSpecName").select2();
        }
    });
    $("#goodSpecModal").modal("show");
}

/**
 * 获取规格属性
 * */
function getSpecInfo(obj,_spec,_specInfo){
    /* 加载类型中的参数 */
    var url = "";
    if(obj == ''){
        url = 'goodsManager/queryAllSpecDetail?specId='+_spec;
    }else {
        url = 'goodsManager/queryAllSpecDetail?specId='+$(obj).val();
    }
    $.get(url,
        function (data) {
            var specs = data.data;
            /* 加载规格信息 */
            if (specs.length > 0) {
                var specHtml = "";
                specHtml += "<div class='spec_set_details' id='emp'>";
                specHtml += "<table class='table'>";
                specHtml += "<tbody>";
                for (var i = 0; i < specs.length; i++) {
                    var check = "";
                    if (i % 3 == 0) {
                        specHtml += "<tr>";
                    }
                    specHtml += '<td><label style="" class="choose-label">';
                    if(_specInfo){
                        for(var j=0;j<_specInfo.length;j++){
                            if(_specInfo[j] == specs[i].specDetailId){
                                check = 'checked="checked"';
                            }
                        }
                    }
                    specHtml += '<input type="checkbox" '+check+' name="checkSpec" class="check_spec openSpecValue_' + specs[i].specId + '" spec_id="' + specs[i].specId + '" spec_value_id="' + specs[i].specDetailId + '" value="' + specs[i].specDetailName + '" >';
                    specHtml += '<input class="form-control" readonly type="text" value="' + specs[i].specDetailName + '" onchange="changeValueId(this)"/>';
                    specHtml += '</label></td>';
                    if (specs.length == i) {
                        specHtml += ' </tr>';
                    } else if (specs.length >= 3) {
                        if ((i + 1) % 3 == 0) {
                            specHtml += ' </tr>';
                        }
                    }
                }
                specHtml += ' </tbody></table></div>';
                $(".type_spec").html(specHtml);
                $(".spec_set_details").show();
            } else {
                $(".type_spec").html("<p class='no_spec'>没有规格</p>");
            }
            $('.spec_set input').change(function () {
                if ($(this).is(':checked')) {
                    $(this).parent().parent().next().attr('name', 'ok');
                    $(this).parent().parent().next().slideDown('fast');
                }
                else {
                    $(this).parent().parent().next().attr('name', 'no');
                    $(this).parent().parent().next().slideUp('fast');
                }
            });
            /* 绑定事件 */
            //bindEvent();
            //tx();
        });
}

function addsGoodsSpecInfo(){
    var flag = true;
    var dataId = '[data-id="'+$("#goodsSpecName").val()+'"]';
    var thisGoods = $(".panel-title").find(dataId).parent().parent().parent();
    if($(".specTitle").text() != '修改规格值'){
        $(".panel-default-goods").each(function(){
            var specId = $(this).find(".panel-body").children(":first").attr("spec_id");
            if(specId == $("#goodsSpecName").val()){
                $(".show_title").text('规格名称已添加过！');
                $(".type_spec").html('');
                $("#goodSpecModal").modal('hide');
                $("#select-tip").modal('show');
                return flag = false;
            }
        });
    }
    if(flag){
        var check = $('input[name=checkSpec]:checked');
        if(check.length == 0){
            $(".show_title").html("请选择规格值！");
            $("#select-tip").modal('show');
            return false;
        }else if($("#goodsSpecName").val()==""){
            $(".show_title").html("请选择规格名称！");
            $("#select-tip").modal('show');
            return false;
        }else {
            var html = '<div class="panel panel-default col-xs-10 panel-default-goods"><div class="panel-heading">';
            html+= '<h3 class="panel-title" style="overflow: hidden"><span style="float: left" data-id="'+$("#goodsSpecName").find("option:selected").val()+'">'+$("#goodsSpecName").find("option:selected").text()+'</span><a style="float: right;margin: 0;" class="btn-group btn-group-sm" onclick="delSpac(this)"><span class="glyphicon glyphicon-trash"></span></a><a style="float: right;margin: 0 20px;" class="btn-group btn-group-sm" onclick="updateSpac(this)"><span class="glyphicon glyphicon-share"></span></a></h3></div><div class="panel-body">';
            check.each(function(){
                html+= '<span data-id="'+$(this).attr("spec_value_id")+'" spec_id="'+$(this).attr("spec_id")+'" style="display: inline-block;margin-left: 10px;padding:20px;background-color: #f2faf7;text-align: center">'+$(this).val()+'</span>';

            });
            html+='</div>';
            if($(".specTitle").text() != '修改规格值'){
                $(".goodsSpecInfoName").append(html);
            }else {
                thisGoods.replaceWith(html);
            }
            $(".type_spec").html('');
            $("#goodSpecModal").modal('hide');
        }
    }
}

function delSpac(_this){
    $(_this).parent().parent().parent().remove();
}

function updateSpac(_this){
    var specInfoArr = [];
    var eleSpan = $(_this).parent().parent().next().find("span");
    $.each(eleSpan,function(){
        specInfoArr.push($(this).attr("data-id"));
    });
    getSpecShowModle($(_this).prev().prev().attr('data-id'));
    getSpecInfo('',$(_this).prev().prev().attr('data-id'),specInfoArr);
}



