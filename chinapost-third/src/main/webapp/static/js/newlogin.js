


/**
 * 用户登陆
 */

$(function(){

    //验证码绑定onclick事件
    $("#checkCodeA").click(
        function(){
            $("#checkCodeImg").click();
        }
    );

});



function login(){
    if(checkInput()){
        var url="checkLogin.htm?userName="+$("#managername").val()+"&password="+$("#managerpassword").val()+'&code='+$("#captcha").val();//+"-"+$("#urlhide").val()
        $.ajax({
            url: url,
            context: document.body,
            success: function(data){
                if(data == '201'){
                    $("#managerpassword").parents().addClass("error");
                    $(".password_tip").html("您输入的账户名和密码不匹配，请重新输入");
                    $(".password_tip").show();
                    refreshCode();//刷新验证码
                }else if(data == '202'){
                    $("#captcha").addClass("error");
                    $(".captcha_tip").html('输入的验证码不正确');
                    $(".captcha_tip").show();
                    refreshCode2();//刷新验证码
                }
                else {
                    //登陆成功 去我的店铺页面
                    window.location.href='goodsManager/getGoodsInfo?type=0&goodsInfoAdded=1';
                }
            }});
    }else{
        $(".code_image").click();
    }

}

/***
 * 登陆信息验证
 * @returns {boolean}
 */
function checkInput(){
    var  flag=true;
    if($.trim($("#managername").val()) == 0){
        $("#managername").parents().addClass("error");
        $(".username_tip").html("请输入邮箱/用户名/已验证手机");
        $(".username_tip").show();
        flag=flag&&false;
    }
    if($.trim($("#managerpassword").val()).length == 0){
        $("#managerpassword").parents().addClass("error");
        $(".password_tip").html("请输入密码");
        $(".password_tip").show();
        flag=flag&&false;
    }

    if($.trim($("#captcha").val()).length == 0){
        $("#captcha").parents().addClass("error");
        $(".captcha_tip").html("请输入验证码");
        $(".captcha_tip").show();
        flag=flag&&false;
    }else{
        flag=flag&&true;
        $(".captcha_tip").hide();
    }

    return flag;
}

//刷新验证码
function refreshCode(){
    $("#captcha").parents().removeClass("error");
    $(".captcha_tip").html("");
    //清空录入的验证码
    $('#captcha').val('');
    //清空验证码提示
    $(".code_image").click()

}
//刷新验证码
function refreshCode2(){
    //$("#captcha").parents().removeClass("error");
    //$(".captcha_tip").html("");
    //清空录入的验证码
    $('#captcha').val('');
    //清空验证码提示
    $(".code_image").click()

}
//获取cookie 中的值
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}