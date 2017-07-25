/**
 * 第三方商品页面js author yuankk time 2014-5-8 11:05
 */
var goodsObj = new Object();
$(function () {
    $(document).on('click','.chooseProimg',function () {
        i = 1;
        art.dialog.open('queryImageManageByPbAndCidForChoose', {
            lock: true,
            opacity: 0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    })
    /* 查询所有的一级分类,并添加到页面 */
    $.get("goodsManager/queryCateByCatIdAndName",{catId:0}, function (data) {
        tCates.length = 0;
        rec(data.data, 0);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='loadSecondCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_first").html(html);
        $(".cat_second").html("");
        $(".cat_third").html("");
    });

    // 步骤
    $(".step_wp:first").show();
    $(".step_next").click(
        function () {
            $('.m_error').remove();
            var specsArr = [];
            /* 如果点击的是第一阶段的下一步 */
            if ($(this).attr("data-role") == "step_first") {
                if ($(".ch_third_catid").val() == "-1") {
                    $(".show_title").text("请选择商品所属分类！");
                    $("#select-tip").modal('show');
                    return;
                } else {
                    /* 如果选择的分类验证通过,就加载所有签约的分类和签约的品牌 */
                    //加载品牌
                    $.post("goodsManager/queryAllBrands",function(data){
                        if(data.response == "success"){
                            var options = "<option value=''>请选择品牌</option>";
                            for( var i=0; i<data.data.length; i++){
                                var auth=data.data[i];
                                options +=  "<option value='"+auth.brandId+"'>"+auth.brandName+"</option>";
                            }
                            $("#goods_brand").html(options);
                            $("#goods_brand").select2();
                        }
                    });
                    goodsObj.catId = $(".thirdCateId").val();
                    console.log(goodsObj);
                }
            } else if ($(this).attr("data-role") == "step_second") {
                // 验证第二步

                $(".dinfo_tabs li:first-child").click();
                var isPass = false;
                var msg = "验证未通过！";
                var html = '';
                html += '<label class="m_error">不能为空</label>';
                if($("#select2-goods_brand-container").attr('title') == '请选择品牌'){
                    $('.select2-selection--single').append(html);
                    return;
                }
                else{
                    $('.m_error').remove();
                }
                if ($(".goodsSpecInfoName").find('.panel-default-goods').length <= 0) {
                    msg = "请至少选择一个商品规格!";
                } else if (!$("#goods_info_form").valid()) {
                    return;
                } else {
                    isPass = true;
                }
                // 不通过则
                if (!isPass) {
                    $(".show_title").text(msg);
                    $("#select-tip").modal('show');
                    return;
                }
                //循环规格值添加到对象
                var goodsSpec = $(".goodsSpecInfoName").find('.panel-default-goods');
                for(var i=0;i<goodsSpec.length;i++){
                    var specs = new Object();
                    var specsDetailArr = [];
                    var span = goodsSpec.eq(i).find('.panel-body').children("span");
                    specs.specId = span.eq(0).attr('spec_id');
                    specs.specName = goodsSpec.eq(i).find('panel-title').children("span").text();
                    for(var j = 0;j< span.length;j++){
                        var goodsInfos = new Object();
                        goodsInfos.specDetailId = span.eq(j).attr('data-id');
                        goodsInfos.specDetailName = span.eq(j).text();
                        goodsInfos.specId = specs.specId;
                        specsDetailArr.push(goodsInfos);
                    }
                    specs.details = specsDetailArr;
                    specsArr.push(specs);
                }
                console.log(JSON.stringify(specsArr));
                goodsObj.specs = specsArr;
                goodsObj.goodsName = $("input[name=goodsName]").val();
                goodsObj.goodsPrice = $("input[name=goodsPrice]").val();
                goodsObj.goodsInfoUnit = $("input[name=goodsInfoUnit]").val();
                goodsObj.goodsAdded = $("input[name=goodsAdded]:checked").val();
                goodsObj.brandId = $("select[name=brandId]").val();
                goodsObj.goodsDetailDesc = editor2.html();
                var html = '';
                $(".dinfo_wp").html('');
                $.ajax({
                    url:"goodsManager/getCombineSpecDetail",
                    data:{data:JSON.stringify(specsArr)},
                    dataType:"json",
                    async: true,
                    success:function(data){
                        if(data.response == 'success'){
                            var dataArr = data.data.split(',');
                            for(var i= 0;i<dataArr.length;i++){
                                $(".demoHtml").find(".tab-pane").attr('id','');
                                $(".demoHtml").find(".tab-pane").removeClass('active');
                                html+='<li role="presentation" class=""><a href="#tab'+i+'" aria-controls="tab'+i+'" role="tab" data-toggle="tab" aria-expanded="false">'+dataArr[i]+'</a></li>';
                                $(".dinfo_tabs").html(html);
                                $(".demoHtml").find(".tab-pane").attr('id','tab'+i);
                                if(i==0){
                                    $(".demoHtml").find(".tab-pane").addClass("active");
                                }
                                $(".dinfo_wp").append($(".demoHtml")[0].innerHTML);
                            }
                            //循环添加货品名称
                            $(".dinfo_tabs li").each(function(){
                                var dataId = $(this).find("a").attr('aria-controls');
                                var demoData = $("#"+dataId+"");
                                demoData.find("input[name=goodsInfoName]").val($("input[name=goodsName]").val()+'('+$(this).find("a").text()+')');
                            })
                            $(".dinfo_tabs li:first-child").addClass("active");
                        }

                    }
                });

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

    /* 绑定清除最近使用的分类的点击事件 */
    $(".clear_third_cate_cookie").click(function () {
        $.get("clearThirdCateFromCookie.htm", function (data) {
            if (data) {
                $(".show_title").text("清除最近使用的分类成功!");
                dia(1);
            } else {
                $(".show_title").text("清除最近使用的分类异常,请重试!");
                dia(1);
            }
        });
    });

});
/* 树形菜单的点击事件 */
function onClick(event, treeId, treeNode, clickFlag) {
    $(".goodsCatId").val(treeNode.id);
    $(".ch_cat_name").val(treeNode.name);
    $(".menuContent").fadeOut("slow");
    $(".aboutGoodsId").remove();
}
/* 显示树形控件 */
function showMenu() {
    var selObj = $(".goodsCatId");
    var businessOffset = $(".goodsCatId").offset();
    $(".menuContent").css({
        left: businessOffset.left + 800 + "px",
        top: businessOffset.top - selObj.outerHeight() + 300 + "px"
    }).slideDown("fast");
    onBodyDownForArea();
}
/* 隐藏树形控件 */
function onBodyDownForArea() {
    jQuery.fn.isChildAndSelfOf = function (b) {
        return (this.closest(b).length > 0);
    };
    $(document).click(
        function (event) {
            if (!($(event.target).isChildAndSelfOf(".menuContent"))
                && !($(event.target).hasClass("st_choose"))) {
                $(".menuContent").fadeOut("slow");
            }
        });
}
var tCates = new Array(0);
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

/* 点击一级分类的时候加载第二级分类 */
function loadSecondCat(catId, obj) {
    /* 设置选中的值 */
    //$(".ch_third_catid").val(catId);
    $("#parentId1").val(catId);
    /* 添加分类选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".firstCate").html(
        "<span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='firstCateId' value='" + catId + "'/>");
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
    $.get("goodsManager/queryCateByCatIdAndName", {catId:catId},function (data) {
        tCates.length = 0;
        rec(data.data, catId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='loadThirdCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_second").html(html);
        $(".cat_third").html("");
        $(".cat_fourth").html("");
    });
}
/* 点击二级分类的时候加载第三级分类 */
function loadThirdCat(catId, obj) {
    /* 设置选中的值 */
    //$(".ch_third_catid").val(catId);
    $("#parentId2").val(catId);
    /* 添加选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".secondCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='secondCateId' value='" + catId + "'/>");
    $(".thirdCate").html("");
    $(".fouthCate").html("");
    /* 清空第三四级的查询参数并设置临时参数为空 */
    $(".sear_third").val("");
    $(".sear_fouth").val("");
    allThirdThirdCat = null;
    allFouthThirdCat = null;
    /* 加载子级分类 */
    $.get("goodsManager/queryCateByCatIdAndName", {catId:catId}, function (data) {
        tCates.length = 0;
        rec(data.data, catId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='loadFourthCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_third").html(html);
        $(".cat_fourth").html("");
    });
}
/* 点击第三级分类的时候 加载第四季分类 */
function loadFourthCat(catId, obj) {
    /* 设置选中的值 */
    $(".ch_third_catid").val(catId);
    /* 添加选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".thirdCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='thirdCateId' value='" + catId + "'/>");
}
/* 搜索一级分类 */
function searfirst() {
    var obj = $("#firstText");
    $("#cat_first").html('');
    var html = "";
    $.ajax({
        url: "goodsManager/queryCateByCatIdAndName",
        Type: "POST",
        data: {catId: 0, cateName: $(obj).val()},
        success: function (data) {
            for (var i = 0; i < data.data.length; i++) {
                html += "<li><a href='javascript:;' onclick='loadSecondCat(" + data.data[i].catId
                + ",this);'>" + data.data[i].catName + "</a></li>";
            }
            /* 清空三四级分类 */
            $(".cat_second").html("");
            $(".cat_third").html("");
            $("#cat_first").html(html);
        }
    });
}
/* 搜索二级分类 */
function searSecond() {
    var obj = $("#secondText");
    $("#cat_second").html('');
    var parentId = $("#parentId1").val();
    var html = "";
    $.ajax({
        url: "goodsManager/queryCateByCatIdAndName",
        Type: "POST",
        data: {catId: parentId, cateName: $(obj).val()},
        success: function (data) {
            for (var i = 0; i < data.data.length; i++) {
                html += "<li><a href='javascript:;' onclick='loadThirdCat(" + data.data[i].catId
                + ",this);'>" + data.data[i].catName + "</a></li>";
            }
            /* 清空三四级分类 */
            $(".cat_second").html("");
            $(".cat_third").html("");
            $("#cat_second").html(html);
        }
    });
}
/* 搜索三级分类 */
function searThird() {
    var obj = $("#thirdText");
    $("#cat_third").html('');
    var parentId = $("#parentId2").val();
    var html = "";
    $.ajax({
        url: "goodsManager/queryCateByCatIdAndName",
        Type: "POST",
        data: {catId: parentId, cateName: $(obj).val()},
        success: function (data) {
            for (var i = 0; i < data.data.length; i++) {
                html += "<li><a href='javascript:;' onclick='loadFourthCat(" + data.data[i].catId
                + ",this);'>" + data.data[i].catName + "</a></li>";
            }
            /* 清空三四级分类 */
            $(".cat_third").html("");
            $("#cat_third").html(html);
        }
    });
}
//第二步商品品牌做判断
function changeGoodsBrand(){
    var html = '';
    html += '<label class="m_error">不能为空</label>';
    if($('#select2-goods_brand-container').attr('title') != '请选择品牌'){
        $('.m_error').remove();
    }
    else{
        $('.select2-selection--single').append(html);
    }
}