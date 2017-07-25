/**
 * 删除单个记录的确认框
 * @param deleteUrl 删除链接。
 */
function showDeleteOneConfirmAlert(deleteUrl,tips) {
    $("#modalDialog").remove();
    var confirmDialog =
    '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
    '    <div class="modal-dialog">'+
    '        <div class="modal-content">'+
    '            <div class="modal-header">'+
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '               <h4 class="modal-title">系统提示</h4>'+
    '           </div>'+
    '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne(\''+deleteUrl+'\');">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}
function showDeleteOneConfirmAlert2(deleteUrl,tips) {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">系统提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne2(\''+deleteUrl+'\');">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

/**
 * 添加单条记录的确认框
 * */
function showAddOneConfirmAlert() {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">添加专题</h4>'+
        '           </div>'+
        '           <div class="modal-body">'+
        '<form id="add_form" method="post" action="setMobSubject.htm" onsubmit="return verify()">'+
        '<table class="table tb-type2">'+
        '<tbody>'+
        '<tr class="noborder" style="background: rgb(255, 255, 255);">'+
        '<td colspan="2" class="required"><label class="validation" for="special_desc">专题描述：</label><span id="vError" style="color:red"></span></td>'+
        '</tr>'+
        '<tr class="noborder" style="background: rgb(255, 255, 255);">'+
        '<td class="vatop rowform">'+
        '<input type="text" value="" name="title" class="txt">'+
        '<input type="hidden" value="-1" name="merchantId">'+
        '</td>'+
        '<td class="vatop tips">专题描述，最多20个字</td>'+
        '</tr>'+
        '</tbody>'+
        '</table>'+
        '</form>'+
        '</div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" onclick="setMobSubject()">确定</button>'+
        '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

/**
 * 批量删除记录谈出框，以表单的形式
 * @param deleteFormId 表单id
 * @param name checkbox的name
 */
function showDeleteBatchConfirmAlert(deleteFormId,name,tips) {
    var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            oneSelect = true;
        }
    }
    if(!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return;
    }
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">删除提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary" onclick="doDeleteBatch(\''+deleteFormId+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function showDeleteBatchConfirmAlert2(deleteFormId,name,tips) {
    var brandIds = '';
    $('input:checkbox[name="brandIds"]:checked').each(function(){
            brandIds += $(this).val() +',';
    });
    brandIds = brandIds.substring(0,brandIds.length - 1)
    console.log(brandIds);
    var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            oneSelect = true;
        }
    }
    if(!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return;
    }
    
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">删除提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary" onclick="doDeleteBatch2(\''+brandIds+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

/**
 * 删除单个记录的确认框
 * @param deleteUrl 删除链接。
 */
function showNoDeleteConfirmAlert(tips) {
    $("#modalDialog").remove();
    var confirmDialog =
    '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
    '    <div class="modal-dialog">'+
    '        <div class="modal-content">'+
    '            <div class="modal-header">'+
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '               <h4 class="modal-title">系统提示</h4>'+
    '           </div>'+
    '           <div class="modal-body">'+tips+
    '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function doDeleteOne(deleteUrl) {
    location.href=deleteUrl;
}
function doDeleteOne(deleteUrl) {
    location.href=deleteUrl;
}

/**
 * 批量删除记录，以表单的形式
 * @param deleteFormId 表单id
 */
function doDeleteBatch(deleteFormId) {
    $("#"+deleteFormId).submit();

}
function doDeleteBatch2(brandIds) {
    $.ajax({
        url:'deleteGoodsBrand',
        data:{
            brandIds:brandIds
        },
        success: function (data) {
            //location.reload();
            console.log(data);
            if(data.response == 'error'){
                showTipAlert(data.data.text);
            }
            else{
                showTipAlert2('删除成功');
            }
        }
    });

}
/**
 * 提示框
 * @param tip 提示内容
 */
function showTipAlert(tip) {
    $("#modalDialog").remove();
    var dialogHtml =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body" style="text-align: center;">'+
        tip+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">确定</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(dialogHtml);
    $('#modalDialog').modal('show');
}
/**
 * 提示框
 * @param tip 提示内容
 */
function editsuccess(tip) {
    $("#modalDialog").remove();
    var dialogHtml =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body" style="text-align: center;">'+
        tip+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\'),location.reload();">确定</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(dialogHtml);
    $('#modalDialog').modal('show');
}

/**
 * ajax删除确认框，删除后，会刷新当前页面
 * @param deleteUrl 删除链接
 */
function showAjaxDeleteConfirmAlert(deleteUrl) {
    showDeleteOneConfirmAlert(deleteUrl);
}
function doAjaxDeleteOne(deleteUrl) {
    if(deleteUrl!=null){
        deleteUrl = deleteUrl+"&searchText="+$('input[name="searchText"]').val();
    }
    $.ajax({
        url:deleteUrl,
        success: function (data) {
            location.reload();
        }
    });
}
function doAjaxDeleteOne2(deleteUrl) {
    if(deleteUrl!=null){
        deleteUrl = deleteUrl+"&searchText="+$('input[name="searchText"]').val();
    }
    $.ajax({
        url:deleteUrl,
        success: function (data) {
            //location.reload();
            console.log(data);
            if(data.response == 'error'){
                showTipAlert(data.data.text);
            }
            else{
                showTipAlert2('删除成功');
            }
        }
        //error:function(){
        //    console.log('error11212')
        //}
    });
}
function showTipAlert2(tip) {
    $("#modalDialog").remove();
    var dialogHtml =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body" style="text-align: center;">'+
        tip+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\'),reloadWindow();">确定</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(dialogHtml);
    $('#modalDialog').modal('show');
}
function setMobSubject(){
    $("#add_form").submit();
}

function verify(){
    if($(".txt").val()==''){
        $("#vError").html('专题描述不能为空');
        return false;
    }
    if($(".txt").val().length>20){
        $("#vError").html('专题描述最多20个字');
        return false;
    }
    return true;
}

function doCallBackAjaxDelete(deleteUrl) {
    $.ajax({
        url:deleteUrl,
        success: function (data) {
            $("#modalDialog").modal("hide");
            delteCallback();
        }
    });
}


/**
 * 批量删除记录谈出框，以表单的形式
 * @param deleteFormId 表单id
 * @param name checkbox的name
 */
function showAjaxDeleteBatchConfirmAlert(deleteFormId,name,tips) {
    var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            oneSelect = true;
        }
    }
    if(!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return;
    }
    $("#modalDialog").remove();
    var url = $("#"+deleteFormId).attr("action")+"?"+$("#"+deleteFormId).serialize();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne(\''+url+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}


/**
 * 删除单个记录的确认框,只删除，删除后不做任何事
 * @param deleteUrl 删除链接。
 */
function showOnlyDeleteConfirmAlert(deleteUrl,tips) {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">系统提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-primary" onclick="doCallBackAjaxDelete(\''+deleteUrl+'\');">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function checkselect(name,modifyFlag){
	var checkedList = new Array();
	var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
        	checkedList.push($(checkboxs[j]).val());
            oneSelect = true;
        }
    }
    if(modifyFlag!=0){
    	if(checkedList.length ==1){
    		return true;
    	}else{
    		showTipAlert("请先选择一条记录！");
    		return false;
    	}
    }
    if(!oneSelect) {
        showTipAlert("请先选择一条记录！");
        return false;
    }else{
       return true;
    }
}

/**
 * 简单的确认框，返回true或false
 */
function showConfirmAlert(tips,functionName) {
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
        '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}


/**
 * 提示框
 * @param tip 提示内容
 */
function showTipAlertAndReloadWindow(tip) {
    $("#modalDialog").remove();
    var dialogHtml =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
        '    <div class="modal-dialog dialog-width">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body" style="text-align: center;">'+
        tip+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="reloadWindow()">确定</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(dialogHtml);
    $('#modalDialog').modal('show');
}

function reloadWindow(){
    $("#modalDialog").modal("hide");
    window.location.reload();
}