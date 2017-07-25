$(function(){
    $("#importGoodsBrandForm").validate();
    $("#saveBrandForm").validate({ ignore:".ignore"});
    $("#editBrandForm").validate({ ignore:".ignore"})
    //添加品牌框里的图片预览
    //$("#logoFile").change(function() {
    //    $("#saveBrandForm input").each(function () {
    //        $(this).addClass("ignore");
    //    });
    //    $("#saveBrandForm").attr("action","../web/uploadPicture");
    //    $("#saveBrandForm").attr("target","uploadFrame");
    //    $("#saveBrandForm").submit();
    //});
    ////编辑品牌框里的图片预览
    //$("#logoFile_update").change(function() {
    //    $("#editBrandForm input").each(function () {
    //        $(this).addClass("ignore");
    //    });
    //    $("#editBrandForm").attr("action","../web/uploadPicture");
    //    $("#editBrandForm").attr("target","uploadFrame");
    //    $("#editBrandForm").submit();
    //});
    $('.brandLogo').popover({
        content : 'jpg,png,gif,建议119*60px',
        trigger : 'hover'
    });
    $("#addBrandName").keyup(function() {
        var brandName = $("#addBrandName").val();
        if (brandName == '') {
            $("#helpTip").hide();
        }
    });
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#preview_image").attr("src",data);
    $("#preview_image_update").attr("src",data);
}

/**
 * 提交添加品牌form
 */
var num=0;
function submitSaveBrandForm() {
    if(!$("#saveBrandForm").valid()){
        return;
    }
    var brandName=$("#addBrandName").val();
    var addBrandSort=$('#addBrandSort').val();
    if(brandName==''){
        $("#helpTip").hide();
    }
    $("#saveBrandForm input").each(function () {
        $(this).removeClass("ignore");
    });
    //$("#saveBrandForm").attr("action","selectByBrandName");
    //$("#saveBrandForm").attr("target","_self");
    if(brandName!='' && addBrandSort!=''&&num==0){
        $.ajax({
            url:'selectByBrandName?brandName='+brandName,
            success:function(data) {
                if(data.data==0) {
                    num+=1;
                    //不重复则提交表单
                    $("#addBrandName").removeClass("error");
                    $("#helpTip").hide();
                    //$("#saveBrandForm").submit();
                    //location.reload();
                    $.ajax({
                        url:'saveGoodsBrand',
                        data:{
                            brandName:$('#addBrandName').val(),
                            brandNickname:$('#addbrandNickname').val(),
                            brandLogo: $('#preview_image').attr('src'),
                            brandSort:$('#addBrandSort').val()
                        },
                        success:function(){
                            editsuccess("添加成功");
                            }
                    })
                }else{
                    //重复错误提示
                    $("#addBrandName").addClass("error");
                    $("#helpTip").show();
                }
            }
        });
    }else{
        $("#addBrandName").removeClass("error");
        $("#helpTip").hide();
        $("#saveBrandForm").submit();
    }
}

/**
 * 弹出显示编辑品牌
 * @param brandId
 */
var bname = '';
function showEditBrandForm(brandId) {
    $('#helpTip2').hide();
    $("#brandName").removeClass("error");
    $("#brandId").val(brandId);
    $('#logoFile_update').val('');
    $.ajax({
        url:'queryBrandById?brandId='+brandId,
        success:function(data) {
            $("#brandName").val(data.data.brandName);
            $("#brandNickname").val(data.data.brandNickname);
            if(typeof (data.data.brandLogo) == 'undefined'){
                $("#preview_image_update").attr("src",'');
            }
            else{
                $("#preview_image_update").attr("src",data.data.brandLogo);
            }
            //$("#logoFile_update").val(data.data.brandLogo);
            $("#brandSort").val(data.data.brandSort);
            $("#promIndex"+data.data.promIndex).click();
            bname =  $("#brandName").val();
            $("#editBrand").modal("show");
        }
    });
}
/**
 * 提交编辑品牌form
 */
function submitUpdateBrandForm() {
    if(!$("#editBrandForm").valid()){
        return;
    }
    else{
    //判断如果修改了品牌名称
   if($('#brandName').val() != bname){
       //判断是否与已有的品牌名重复
        $.ajax({
            url:'selectByBrandName?brandName='+$('#brandName').val()+"&CSRFToken="+$("#CSRFToken").val(),
            success:function(data) {
                if(data.data==0) {
                    //不重复则提交表单
                    $("#brandName").removeClass("error");
                    $("#helpTip2").hide();
                    $("#editBrandForm").attr("action","getGoodsBrand");
                    $("#editBrandForm").attr("target","_self");
                    //$("#editBrandForm").submit();
                    //location.reload();
                    $.ajax({
                        url:'editGoodsBrand',
                        data:{
                            brandId:$('#brandId').val(),
                            brandName:$('#brandName').val(),
                            brandNickname:$('#brandNickname').val(),
                            brandLogo: $('#preview_image_update').attr('src'),
                            brandSort:$('#brandSort').val()
                        },
                        success:function(){
                            editsuccess("修改成功");
                            //showTipAlert("修改成功");
                        }
                    })
                }else{
                    //重复错误提示
                    $("#brandName").addClass("error");
                    $("#helpTip2").show();
                }
            }
        });
   }
    else{
       $("#brandName").removeClass("error");
       $("#helpTip2").hide();
       $("#editBrandForm").attr("action","getGoodsBrand");
       $("#editBrandForm").attr("target","_self");
       //$("#editBrandForm").submit();
       //location.reload();
       $.ajax({
           url:'editGoodsBrand',
           data:{
               brandId:$('#brandId').val(),
               brandName:$('#brandName').val(),
               brandNickname:$('#brandNickname').val(),
               brandLogo: $('#preview_image_update').attr('src'),
               brandSort:$('#brandSort').val()
           },
           success:function(){
               editsuccess("修改成功");
               //showTipAlert("修改成功");
           }
       })
   }
    }
        //else{
        //   $("#editBrandForm input").each(function () {
        //       $(this).removeClass("ignore");
        //   });
        //   $("#editBrandForm").attr("action","getGoodsBrand");
        //   $("#editBrandForm").attr("target","_self");
        //   $("#editBrandForm").submit();
        //   location.reload();
        //}

}

function showImport() {
    $("#importBrand").modal("show");
}

function importBrand() {
    $("#importGoodsBrandForm").submit();
}

function import_callback(result) {
    if(result=="200") {
        showTipAlert("导入成功");
        location.reload();
    }else if(result == "" || result == null){
        showTipAlert("导入失败")
    } else {
        showTipAlert(result);
    }
}