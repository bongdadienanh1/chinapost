var basePath = $("#basePath").val();

/**
 * 提交编辑form
 */
function submitUpdateForm() {
    var user = $('#thirdUserName').val();
    var password = $('#thirdUserPwd').val();
    if(user == password){
        showTipAlert('账号和密码不能相同！');
        return;
    }
    if(isSimplePwd(password)<2){
        showTipAlert('密码过于简单！');
        return;
    }
    if(isPassword(password)){
        showTipAlert('密码不能连续三位重复字符！');
        return;
    }
    if(!$("#editBrandForm").valid()){
        return;
    }

    $(".modal-footer .sub").attr("onclick","");

    $("#editBrandForm input").each(function () {
        $(this).removeClass("ignore");
    });
    $("#editBrandForm").attr("action",basePath+"/ThirdPlatformAdmin/saveThirdPlatform");
    $("#editBrandForm").attr("target","_self");

    if($(".add_title").html() == "添加"){
        checkThirdUser($("#companyName").val(),$("#storeName").val(),$("#thirdUserName").val());
    }else{
        if($("#companyName").val() == companyNameOld){
            companyNameOld = "";
        }else{
            companyNameOld = $("#companyName").val();
        }
        if($("#storeName").val() == storeNameOld){
            storeNameOld = "";
        }else{
            storeNameOld = $("#storeName").val();
        }
        if($("#thirdUserName").val() == thirdUserNameOld){
            thirdUserNameOld = "";
        }else{
            thirdUserNameOld = $("#thirdUserName").val();
        }
        checkThirdUser(companyNameOld,storeNameOld,thirdUserNameOld);
    }

}

function checkThirdUser(companyName,storeName,thirdUserName){
    $.ajax({
        url:basePath+'/ThirdPlatformAdmin/checkThirdUser',
        data:{companyName:companyName,storeName:storeName,thirdUserName: thirdUserName},
        async: false,
        success:function(data) {
            if(data == '200'){
                $("#editBrandForm").submit();
            }else{
                showTipAlert(data);
                $(".modal-footer .sub").attr("onclick","submitUpdateForm()");
            }
        }
    });
}

/**
 * 弹出显示编辑
 */
var companyNameOld;
var storeNameOld;
var thirdUserNameOld;
function updateThirdPlatfrom(thirdId) {
    if(typeof (thirdId) != 'undefined'){
        $("#pwd").hide();
        $("#thirdUserPwd").attr("name","");
        $("#thirdUserPwd").removeClass("required");
        $("#thirdUserName").attr("disabled",true);
        $(".add_title").html("编辑");
        $.ajax({
            url:basePath+'/ThirdPlatformAdmin/queryById?id='+thirdId,
            dataType:'json',
            success:function(data) {
                $("#id").val(data.id);
                $("#companyName").val(data.companyName);
                $("#storeName").val(data.storeName);
                $("#thirdUserName").val(data.thirdUserName);
                companyNameOld = data.companyName;
                storeNameOld = data.storeName;
                thirdUserNameOld = data.thirdUserName;
                $("input:radio[name='platformType'][value='"+data.platformType+"']").click();
                $("#editThirdPlatfrom").modal("show");
            }
        });
    } else{
        $(".add_title").html("添加");
        $("#pwd").show();
        $("#thirdUserPwd").attr("name","thirdUserPwd");
        $("#thirdUserName").attr("disabled",false);
        $("#thirdUserPwd").addClass("required");
        //根据测试要求在添加时点取消后重新添加信息要保留
        //$("#editBrandForm").find("input").val("");
        //$("input:radio[name='platformType']").eq(0).click();
        $("#editThirdPlatfrom").modal("show");
    }
}

/**
 * 密码重置
 */
function passwordReset(thirdId){
    $("#passwordId").val(thirdId);
    $("#passWordForm").modal("show");
}

function bindingSendUrl(thirdId){
    $("#thirdId").val(thirdId);
    // 根据thirdId拿取单个数据
    $.ajax({
        url:basePath+'/ThirdPlatformAdmin/findThirdPlatformInterfaceData?thirdId='+thirdId,
        dataType:'json',
        success:function (data) {
            if(data != null){
                $("#e_id").val(data.id);
                $("#inventoryQuery").val(data.inventoryQuery);
                $("#paymentNotice").val(data.paymentNotice);
                $("#merchantRefund").val(data.merchantRefund);
                $("#merchantSale").val(data.merchantSale);
                $("#merchantReturnLogistics").val(data.merchantReturnLogistics);
            }
        }
    });
    $("#bindingSendUrl").modal("show");
}

function submitInterfaceForm(){
    $.ajax({
        url:basePath+'/ThirdPlatformAdmin/saveThirdPlatformInterface',
        data:$("#editThirdPlatformInterfaceForm").serialize(),
        success:function (data) {
            if(data != '0'){
                showTipAlert('修改成功！');
            }else{
                showTipAlert('出现错误！');
            }
            $("#bindingSendUrl").modal("hide");
        }
    });
}

function submitPassWordForm(){
    if(isSimplePwd($("#thirdUserNameNew").val() || isSimplePwd($("#oldThirdUserPwd").val()))<2){
        showTipAlert('密码过于简单！');
        return;
    }else{
        if($("#thirdUserNameNew").val() == $("#oldThirdUserPwd").val()){
            $("#editPasswordForm").submit();
        }else{
            showTipAlert("两次输入密码不一致，请重新输入！！！");
        }
    }
}
//密码验证不能连续三位重复
function isPassword(str){
    //var str = $("#testid").val();
    var patrn=/(.)*(.)\2{2}(.)*/g;
    if (patrn.exec(str)){
        //showTipAlert('密码过于简单！');
        return true;
    }
    else{
        return false;
    }
}

//验证密码过于简单
function isSimplePwd(s){
    var ls = 0;
    if(s.match(/([a-z])+/)){
        ls++;
    }
    if(s.match(/([0-9])+/)){
        ls++;
    }
    if(s.match(/([A-Z])+/)){
        ls++;
    }
    if(s.match(/[^a-zA-Z0-9]+/)){
        ls++;
    }
    return ls;
}

function importBrand() {
    $("#importGoodsBrandForm").submit();
}