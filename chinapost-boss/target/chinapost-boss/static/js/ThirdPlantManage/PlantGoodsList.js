$(function() {
    $("#queryStatus").val($("#good_tabs").find(".active").find("a").attr("data-type"));


    /*收款单筛选交互演示用，无实际意义*/
    $('#good_tabs a').click(function () {
        $that = $(this);
        $that.parent().addClass('active');
        $("#queryStatus").val($that.attr("data-type"));
        $that.parent().siblings().removeClass('active');
        $('#adv_form').submit();

    });
});

function searchData(pageNumber){
    $("#page").val(pageNumber);
    $("#searchForm").submit();
}

function checkAuditStatus(status){
    $("#auditStatus").val(status);
    $("#searchForm").submit();
}

// 审核提示框
function openDialog(goodsInfoId) {
    $('#auditProductAction').attr("action","batchUpdateAuditStatus?goodsInfoId="+goodsInfoId);
    $('#dialog-confirm2').modal('show');
}

// 打开拒绝提示框
function openRefuseDialog(goodsInfoId) {
    $('#refuseAuditProductAction').attr("action","batchUpdateAuditStatus?goodsInfoId="+goodsInfoId)
    $("#refuseReason").attr("disabled",false);
    $("#sub").show();
    $('#dialog-confirm3').modal('show');
}

// 查看拒绝原因
function refuseDialog(refuseReason){
    $("#refuseReason").val(refuseReason);
    $("#refuseReason").attr("disabled",true);
    $("#sub").hide();
    $('#dialog-confirm3').modal('show');
}

// 批量审核
function batchAudit(name){
    if(checkData(name)){
        $("#batchAuditFlag").val("1");
        $("#refuseReason").attr("disabled",false);
        $('#dialog-confirm2').modal('show');
    }
}

// 批量拒绝
function refuseAudit(name){
    if(checkData(name)){
        $("#refuseAuditFlag").val("1");
        $("#sub").show();
        $('#dialog-confirm3').modal('show');
    }
}

function checkData(name){
    var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    var data_audit = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            oneSelect = true;
            if($(checkboxs[j]).attr("data-audit") != 0){
                data_audit = true;
            }
        }
    }
    if(!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return false;
    }

    if(data_audit){
        showTipAlert("请去掉已审核记录！");
        return false;
    }
    return true;
}

function submitAudit(){
    if($("#batchAuditFlag").val() == '1'){
        $("#auditStatusFrom").val("1");
        $("#batchAuditStatusFrom").submit();
    }else{
        $("#auditProductAction").submit();
    }
}

function submitRefuse(){
    if($("#refuseAuditFlag").val() == '1'){
        $("#auditStatusFrom").val("2");
        $("#refuseReasonFrom").val($("#refuseReason").val());
        $("#batchAuditStatusFrom").submit();
    }else{
        $("#refuseAuditProductAction").submit();
    }
}