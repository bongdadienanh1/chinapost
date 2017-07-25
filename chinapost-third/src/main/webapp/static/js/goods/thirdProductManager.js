/**
 * 货品管理JS
 * author yuankk
 */
var tCates = new Array(0);
var goodsObj = new Object();
/* 递归 */
function rec(data, pid) {
    for (var i = 0; i < data.length; i++) {
        var cpid = data[i].catParentId;
        if ((cpid + "") == (pid + "")) {
            tCates[tCates.length] = data[i];
        }
        var cateVos = data[i];
        rec(cateVos, pid);
    }
}
$(function () {
    /*当货品编号失去焦点的时候验证货品编号是否已经使用*/
    $(".goodsInfoItemNo").blur(function () {
        if (checkNull($(".goodsInfoItemNo"), $(".goodsInfoItemNo_tip"))) {
            if (checkSpecSymb($(".goodsInfoItemNo"), $(".goodsInfoItemNo_tip"))) {
                if ($(".oldGoodsInfoItemNo").val() != "") {
                    checkExists('checkProductNo', 'productNo', $(this), 'goodsInfoItemNo_tip', 1, "oldGoodsInfoItemNo");
                }
                else {
                    checkExists('checkProductNo', 'productNo', $(this), 'goodsInfoItemNo_tip', 0, null);
                }
            }
        }
    });
    $(document).on('click','.chooseProimg',function () {
        i = 1;
        art.dialog.open($("#basPath").val()+'/queryImageManageByPbAndCidForChoose', {
            lock: true,
            opacity: 0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    });

    /*点击保存的时候*/
    $(".sub_product_form").click(function () {
        checkProductForm();
    });
    /*点击关闭的时候*/
    $(".close_sub_product").click(function () {
        $(".sub_product")[0].reset();
        $(".ui-state-error").removeClass("ui-state-error");
        $(".ui-state-highlight").text("").removeClass("ui-state-highlight text-danger");
        /*移除更新时保存的临时参数*/
        $(".old_param_div").html("");
    });
    /*点击批量上架的时候*/
    $(".batch_upload_product").click(function () {
        var ch_product = $(".ch_product");
        var bool = 0;
        for (var i = 0; i < ch_product.length; i++) {
            if ($(ch_product[i]).prop("checked")) {
                bool = bool - 1 + 2;
            }
        }
        if (bool > 0) {
            $(".list_form").attr("action", "batchUploadThirdProduct.htm").submit();
        } else {
            $(".show_title").text("请至少选择一行进行操作!");
            dia(2);
        }
    });
    /*点击批量下架的时候*/
    $(".batch_down_product").click(function () {
        var ch_product = $(".ch_product");
        var bool = 0;
        for (var i = 0; i < ch_product.length; i++) {
            if ($(ch_product[i]).prop("checked")) {
                bool = bool - 1 + 2;
            }
        }
        if (bool > 0) {
            $(".list_form").attr("action", "batchDownThirdProduct.htm").submit();
        } else {
            $(".show_title").text("请至少选择一行进行操作!");
            $("#refuse-tip").modal('show');
        }
    });

    /*点击修改按钮*/
    $(".modi-goods").click(function () {
        editor2.html('');
        $(".choose_imgs").html('');
        $.get("getGoodsInfoById?id=" + $(this).attr("data-role"), function (data) {
            var catName = "";
            /* 查询所有的一级分类,并添加到页面 */
            $.get("queryCateByCatIdAndName",{catId:0}, function (str) {
                tCates.length = 0;
                rec(str.data, 0);
                var html = "";
                for (var i = 0; i < tCates.length; i++) {
                    if(tCates[i].catId == data.data.catGrandpaId){
                        html += "<li class='cur'><a href='javascript:;' onclick='loadSecondCat(" + tCates[i].catId
                            + ",this);'>" + tCates[i].catName + "</a></li>";
                        loadSecondCat('','',tCates[i].catName,data.data);
                    }else {
                        html += "<li><a href='javascript:;' onclick='loadSecondCat(" + tCates[i].catId
                            + ",this);'>" + tCates[i].catName + "</a></li>";
                    }
                }
                $(".cat_first").html(html);
                $(".cat_second").html("");
                $(".cat_third").html("");
            });
            //加载品牌
            $.post("queryAllBrands",function(str){
                if(str.response == "success"){
                    var options = "";
                    for( var i=0; i<str.data.length; i++){
                        var auth=str.data[i];
                        if(auth.brandId == data.data.brandId){
                            options +=  "<option selected='selected' value='"+auth.brandId+"'>"+auth.brandName+"</option>";
                        }else {
                            options +=  "<option value='"+auth.brandId+"'>"+auth.brandName+"</option>";
                        }
                    }
                    $("#updataGoods_brand").html(options);
                    $("#updataGoods_brand").select2();
                }
            });
            editor2.html(data.data.goodsDetailDesc);
            $("input[name=goodsInfoUnit]").val(data.data.goodsInfoUnit);
            $(".app-content").find("input[name=goodsInfoName]").val(data.data.goodsInfoName);
            $("input[name=goodsInfoPreferPrice]").val(data.data.goodsInfoPreferPrice);
            $("input[name=goodsInfoSettlePrice]").val(data.data.goodsInfoSettlePrice);
            $("input[name=freightPrice]").val(data.data.freightPrice);
            $("input[name=goodsAdded]").each(function(){
               if($(this).val()==data.data.goodsInfoAdded){
                   $(this).attr("checked","checked");
               }
            });
            var html = '';
            for(var i=0;i<data.data.images.length;i++){
                html +='<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + data.data.images[i].imageInName + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + data.data.images[i].imageInName + ' width="100" height="100" /></a></li>';
            }
            $(".choose_imgs").html(html);
            $("#goodsInfoId").val(data.data.goodsInfoId);
            goodsObj.goodsId = data.data.goodsId;

        });
    });
    function del_ts(obj) {
        $(obj).parent().remove();
    }
    $(".step_next").click(
        function () {
            var specsArr = [];
            /* 如果点击的是第一阶段的下一步 */
            if ($(this).attr("data-role") == "step_first") {
                if ($(".ch_third_catid").val() == "-1") {
                    $("#select-tip .show_title").text("请选择商品所属分类！");
                    $("#select-tip").modal('show');
                    return;
                } else {
                    goodsObj.catId = $(".thirdCateId").val();
                    console.log(goodsObj);
                }
            } else if ($(this).attr("data-role") == "step_second") {
                // 验证第二步
                if (!$("#goods_info_form").valid()) {
                    return;
                }
                goodsObj.goodsInfoUnit = $("input[name=goodsInfoUnit]").val();
                goodsObj.brandId = $("#updataGoods_brand").val();

            }
            var cur = $(this).parents(".step_wp");
            cur.next(".step_wp").show();
            cur.hide();
        });

    $(".prev_step").click(function () {
        var cur = $(this).parents(".step_wp");
        cur.prev(".step_wp").show();
        cur.hide();
    });
    /*点击保存货品图片的时候触发*/
    $(".save_img").click(function () {
        $(".modi_third_product_img").submit();
    });

    /*选择图片*/
    $(".choose_image_button").click(function () {
        art.dialog.open('queryImageManageByChoose.htm?size=10000', {
            lock: true,
            width: '800px',
            height: '400px',
            title: '选择图片'
        });
    });

    //重置搜索
    $(".rst_btn").click(function () {
        $(".search_product_form")[0].reset();
        $(".form-control").val("");
    });

});
/*保存商品*/
function up_goods_info() {
    if($(".dinfo_wp").find(".goodsInfoForm").valid()){
        var goodsInfosArr = [];
        var goodsInfosObj = new Object();
        var infoImagesArr = [];
        goodsInfosObj.goodsInfoName = $(".dinfo_wp").find('input[name=goodsInfoName]').val();
        goodsInfosObj.goodsInfoPreferPrice = $(".dinfo_wp").find('input[name=goodsInfoPreferPrice]').val();
        goodsInfosObj.goodsInfoSettlePrice = $(".dinfo_wp").find('input[name=goodsInfoSettlePrice]').val();
        goodsInfosObj.freightPrice = $(".dinfo_wp").find('input[name=freightPrice]').val();
        //goodsObj.goodsAdded =  $("input[name=goodsAdded]:checked").val();
        $(".dinfo_wp").find('.choose_imgs li').each(function(){
            var infoImages = new Object();
            infoImages.imageInName = $(this).find("img").attr('src');
            infoImagesArr.push(infoImages)
        });
        goodsInfosObj.infoImages = infoImagesArr;
        goodsInfosObj.goodsInfoId = $("#goodsInfoId").val();
        goodsInfosObj.goodsInfoAdded =  $("input[name=goodsAdded]:checked").val();
        goodsInfosArr.push(goodsInfosObj);
        goodsObj.goodsInfos = goodsInfosArr;
        console.log(JSON.stringify(goodsObj));
        $.post("updateGoodInfo",
            {
                goodsJson:JSON.stringify(goodsObj),
                goodsDetailDesc:editor2.html()
            },function(data){
                if(data.response == "success"){
                    showConfirmAlertTips("修改成功！","refresh()");
                }else {
                    showTipAlert(data.data);
                }
            })

    }
}

// 保存选择的图片信息
function saveChoooseTrademark (path) {
    if (path.toString().indexOf(",") > -1) {
        var paths = path.toString().split(",");
        for (var i = 0; i < paths.length; i++) {
            var path2 = paths[i];
            $(".dinfo_wp")
                .find(".choose_imgs")
                .append(
                    '<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + path2 + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + path2 + ' width="100" height="100" /></a></li>');
        }
    } else {
        $(".dinfo_wp")
            .find(".choose_imgs")
            .append(
                '<li class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value=' + path + '><a href="javascript:;"><img alt="" class="choose_pro_img" src=' + path + ' width="100" height="100" /></a></li>');
    }
    //showConfirmAlert('要将这些图片应用到其他货品吗?', 'copyImageToOtherSpec(\'' + path + '\')');
}
function refresh(){
    window.location.href="getGoodsInfo?auditStatus=1";
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
/* 点击一级分类的时候加载第二级分类 */
function loadSecondCat(catId, obj,catName,dataObj) {
    var aCatName = "";
    var aCatId;
    $("#parentId1").val(catId);
    /* 添加分类选中的样式 */
    if(obj != ''){
        $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
        aCatName = $(obj).html()
        aCatId = catId;
    }else {
        aCatName = catName;
        aCatId = dataObj.catGrandpaId;
    }
    /* 设置选中的值 */
    //$(".ch_third_catid").val(aCatId);
    $(".ch_third_catid").val('-1');
    $(".firstCate").html(
        "<span class='cateName'>" + aCatName
        + "</span><input type='hidden' class='firstCateId' value='" + aCatId + "'/>");
    $(".secondCate").html("");
    $(".thirdCate").html("");
    $(".fouthCate").html("");
    /* 清空所有的查询 */
    $(".sear_second").val("");
    $(".sear_third").val("");
    $(".sear_fouth").val("");
    /* 设置二级分类的全局变量为空 */
    allSecondThirdCat = null;
    allThirdThirdCat = null;
    allFouthThirdCat = null;
    /* 查询子级分类 */
    $.get("queryCateByCatIdAndName", {catId:aCatId},function (data) {
        tCates.length = 0;
        rec(data.data, aCatId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            if(typeof (dataObj)!='undefined' && tCates[i].catId == dataObj.catParentId){
                html += "<li class='cur'><a href='javascript:;' onclick='loadThirdCat(" + tCates[i].catId
                    + ",this);'>" + tCates[i].catName + "</a></li>";
                loadThirdCat('','',tCates[i].catName,dataObj);
            }else {
                html += "<li><a href='javascript:;' onclick='loadThirdCat(" + tCates[i].catId
                    + ",this);'>" + tCates[i].catName + "</a></li>";
            }
        }
        $(".cat_second").html(html);
        $(".cat_third").html("");
        $(".cat_fourth").html("");
    });
}

/* 点击二级分类的时候加载第三级分类 */
function loadThirdCat(catId, obj,catName,dataObj) {
    var aCatName = "";
    var aCatId;
    $("#parentId2").val(catId);
    /* 添加选中的样式 */
    if(obj != ''){
        $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
        aCatName = $(obj).html();
        aCatId = catId;
    }else {
        aCatName = catName;
        aCatId = dataObj.catParentId;
    }

    /* 设置选中的值 */
    //$(".ch_third_catid").val(aCatId);
    $(".ch_third_catid").val('-1');
    $(".secondCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + aCatName
        + "</span><input type='hidden' class='secondCateId' value='" + aCatId + "'/>");
    $(".thirdCate").html("");
    $(".fouthCate").html("");
    /* 清空第三四级的查询参数并设置临时参数为空 */
    $(".sear_third").val("");
    $(".sear_fouth").val("");
    allThirdThirdCat = null;
    allFouthThirdCat = null;
    /* 加载子级分类 */
    $.get("queryCateByCatIdAndName", {catId:aCatId}, function (data) {
        tCates.length = 0;
        rec(data.data, aCatId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            if(typeof (dataObj)!='undefined' && tCates[i].catId == dataObj.catId){
                html += "<li class='cur'><a href='javascript:;' onclick='loadFourthCat(" + tCates[i].catId
                    + ",this);'>" + tCates[i].catName + "</a></li>";
                loadFourthCat('','',tCates[i].catName,dataObj);
            }else {
                html += "<li><a href='javascript:;' onclick='loadFourthCat(" + tCates[i].catId
                    + ",this);'>" + tCates[i].catName + "</a></li>";
            }
        }
        $(".cat_third").html(html);
        $(".cat_fourth").html("");
    });
}
/* 点击第三级分类的时候 加载第四季分类 */
function loadFourthCat(catId, obj,catName,dataObj) {
    var aCatName='';
    var aCatId;
    /* 添加选中的样式 */
    if(obj != ''){
        $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
        aCatName = $(obj).html()
        aCatId = catId;
    }else {
        aCatName = catName;
        aCatId = dataObj.catId;
    }
    /* 设置选中的值 */
    $(".ch_third_catid").val(aCatId);
    $(".thirdCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + aCatName
        + "</span><input type='hidden' class='thirdCateId' value='" + aCatId + "'/>");
}
/*验证货品表单*/
function checkProductForm() {
    var bValid = true;

    bValid = checkRegexp($(".goodsInfoItemNo"), /^[A-Za-z0-9\-]+$/, "货品编号只能为数字.", $(".goodsInfoItemNo_tip")) && bValid;
    if (checkRegexp($(".goodsInfoItemNo"), /^[A-Za-z0-9\-]+$/, "货品编号只能为数字.", $(".goodsInfoItemNo_tip"))) {
        bValid = checkLength($(".goodsInfoItemNo"), "货品编号", $(".goodsInfoItemNo_tip"), 10, 32) && bValid;
    }
    bValid = checkNull($(".goodsInfoName"), $(".goodsInfoName_tip")) && bValid;
    bValid = checkNull($(".goodsInfoUnit"), $(".goodsInfoUnit_tip")) && bValid;
    bValid = checkSpecSymb($(".goodsInfoSubtitle"), $(".goodsInfoSubtitle_tip")) && bValid;
    bValid = checkSpecSymb($(".goodsInfoStock"), $(".goodsInfoStock_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoStock"), $(".goodsInfoStock_tip"))) {
        bValid = checkRegexp($(".goodsInfoStock"), /^[0-9]+$/, "库存必须为正整数.", $(".goodsInfoStock_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoPreferPrice"), $(".goodsInfoPreferPrice_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoPreferPrice"), $(".goodsInfoPreferPrice_tip"))) {
        bValid = checkRegexp($(".goodsInfoPreferPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "销售价格输入格式不正确.", $(".goodsInfoPreferPrice_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoCostPrice"), $(".goodsInfoCostPrice_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoCostPrice"), $(".goodsInfoCostPrice_tip"))) {
        bValid = checkRegexp($(".goodsInfoCostPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "成本价格输入格式不正确.", $(".goodsInfoCostPrice_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoMarketPrice"), $(".goodsInfoMarketPrice_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoMarketPrice"), $(".goodsInfoMarketPrice_tip"))) {
        bValid = checkRegexp($(".goodsInfoMarketPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "市场价格输入格式不正确.", $(".goodsInfoMarketPrice_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoWeight"), $(".goodsInfoWeight_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoWeight"), $(".goodsInfoWeight_tip"))) {
        bValid = checkRegexp($(".goodsInfoWeight"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "重量输入格式不正确.", $(".goodsInfoWeight_tip")) && bValid;
    }
    if($(".goodsInfoPack").val()!=""){
        bValid = checkSpecSymb($(".goodsInfoPack"), $(".goodsInfoPack_tip")) && bValid;
        if (checkSpecSymb($(".goodsInfoPack"), $(".goodsInfoPack_tip"))) {
            bValid = checkRegexp($(".goodsInfoPack"), /^[1-9][0-9]*$/, "请输入正整数.", $(".goodsInfoPack_tip")) && bValid;
        }
    }

    if ($(".checkExistsFlag").val() == "0") {
        $(".goodsInfoItemNo").addClass("ui-state-error");
        updateTips("名称或编号已经存在!", $(".goodsInfoItemNo_tip"));
        bValid = false;
    }

    selProductParam('update_param_Tips', $(".exec_flag").val());
    if ($(".checkProdcutExists").val() == "0") {
        $(".spec_param_Tips").addClass("ui-state-highlight text-danger");
        updateTips("所选参数已经生成货品,请重新选择!", $(".spec_param_Tips"));
        bValid = false;
    }
    if (bValid) {
        $(".sub_product").submit();
    }
}
/*点击页面上的页码的时候触发*/
function changePageNo(obj) {
    /*获取查询的方式标记*/
    $(".search_product_form").append("<input type='hidden' name='pageNo' value='" + $(obj).attr("data-role") + "' />").submit();
}

/*点击编辑图片的时候  */
function modiImg(productId) {
    $(".group_wp").hide();
    $(".modi_img").show();
    $(".img_productId_hide").val(productId);
    $.get("queryImageListByProductId.htm?productId=" + productId, function (data) {
        var imageListHtml = "";
        for (var i = 0; i < data.length; i++) {
            imageListHtml = imageListHtml + "<li id='imagediv" + i + "'><img alt='' id='image" + i + "' src='" + data[i].imageInName + "' />" + "<div class='img-ops'>"+"<button class='btn btn-primary btn-xs' type='button'onclick=" + "setDefaultImage(" + "'" + data[i].imageInName
            + "',"+data[i].goodsImgId+");" + ">设为默认</button>"+"<button class='btn btn-default btn-xs' type='button' onclick=" + "delImage(" + "'" + data[i].goodsImgId + "',this);"+">删除</button></div></li>";
        }
        $(".edit-images-list").html(imageListHtml);
    });
    $.get("queryProductVoByProductId.htm?productId=" + productId, function (data) {
        $(".default_image").val(data.goodsInfoImgId);
    });
}
/*设置货品默认图片*/
function setDefaultImage(imageName,imgId) { 
    $(".default_image").val(imageName);
    $(".goodsImgId").val(imgId)
}
/*删除图片*/
var del_imageId;
var del_img_btn;
function delImage(imageId, btn) {
    del_img_btn = $(btn);
    del_imageId = imageId;
    $("#delete-tip").modal('show');
}
/*执行删除货品图片*/
function delProductImg() {
    $(".del_image").append("<input type='hidden' name='delImages' value='" + del_imageId + "' />");
    $(del_img_btn).parent().parent().remove();
    cls();
}
//设置修改库存弹窗
function showStock(goodsInfoId,goodsInfoStock){
    $("#goodsInfoId").val(goodsInfoId);
    $("#goodsInfoStock").val(goodsInfoStock);
    $("#stockModal").modal('show');
}

function updateStock(){
    if($(".stockForm").valid()){
        $.post("updateStockByGoodsInfoId",{
            goodsInfoId:$("#goodsInfoId").val(),
            goodsInfoStock:$("#goodsInfoStock").val()
        },function(data){
            if(data.response == 'success'){
                $("#stockModal").modal("hide");
                showConfirmAlertTips("修改成功！","searchData()");
            }
        })
    }
}

