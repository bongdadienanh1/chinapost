$(document).ready(function(){

    $("div.holder").jPages({
        containerID : "itemContainer",
        perPage : 6
    });
    var Passwordreset="";
    var lengthLock = true;
    //$("input[name='Passwordreset']").blur(function(){
    //    if( $(this).val().length < 8 || $(this).val().length > 16 ){
    //        var focus = function(){
    //            lengthLock = true;
    //        };
    //        if( lengthLock == true ){
    //            lengthLock = false;
    //            response_ensure_alert("error","修改密码长度不得小于6位或大于16位",focus(),focus());
    //        }
    //    }
    //    else{
    //        Passwordreset=  $("input[name='Passwordreset']").val();
    //    }
    //
    //});
    $("input[name='PasswordresetConfirm']").blur(function(){
        var Passwordreset=  $("input[name='Passwordreset']").val();
        var PasswordresetConfirm=$("input[name='PasswordresetConfirm']").val();
        if( Passwordreset!=PasswordresetConfirm ){
            $(this).next("span").show()
        }else{
            $(this).next("span").hide()
        }
    })
    var managerId=""
    $(document).on("click",".updPassword",function(){
        coverHtml()
        managerId=$(this).parent().parent().siblings("input[name='detail_id']").val()
        $(".updatePassword").fadeIn(500);
        $("input[name='Passwordreset']").val("")
        $("input[name='PasswordresetConfirm']").val("")
    })
    $(document).on("click","#updatePasswordConfirm",function(){
        var password1=$("input[name='Passwordreset']").val();
        var password2=$("input[name='PasswordresetConfirm']").val();
        if(password1==password2){
            var check = checkPassword(password2)
            if(check && password2.length>7 && password2.length <21 ){
                $.post("../web/api/rolemanager/editmanagerpassword",{
                    managerId:managerId,
                    password:password2
                },function(data){
                    if(data.response=="success"){
                        data_type_alert("修改成功","success")
                        discoverHtml()
                        $(".updatePassword").fadeOut(500);
                    }else{
                        response_ensure_alert("error",data.data.text)
                    }
                },"json")
            }else{
                data_type_alert("密码为8-20位的数字和字母组合","error")
            }

        }else{
            data_error_alert("两次密码输入不一致")
        }
    })
    $(document).on("click","#employeeSearch",function(){
        var keywords = $.trim($("input[name='employeeSearch']").val());
        if( keywords != '' ){
            var length = $("#hiddenContainer dd").length;
            var html = '';
            for(var i = 0;i < length; i++){
                var str = $( $("#hiddenContainer dd")[i]).find("abbr")[1].innerHTML;
                if( str.match( keywords ) ){
                    html += $("#hiddenContainer dd")[i].outerHTML;
                }
            }
            $("#itemContainer").empty();
            $("#itemContainer").append(html);

            $("div.holder").jPages({
                containerID : "itemContainer",
                perPage : "6"
            });
        }
        else{
            window.location.href = 'employeeManager';
        }
    })


    $("#creatEmployeeConfirm").click(function(){
        var username = $("#employeeDetail").children()[0].childNodes[1].value;
        var password = $("#employeeDetail").children()[1].childNodes[1].value;
        var ensure_password = $("#employeeDetail").children()[2].childNodes[1].value;
        var staffname = $("#employeeDetail").children()[3].childNodes[1].value;
        var cellphone = $("#employeeDetail").children()[4].childNodes[1].value;
        var authorityId = $("#employeeDetail").children()[5].childNodes[2].value;
        if(!(/^1[3|4|5|7|8]\d{9}$/.test(cellphone))){
            data_type_alert("手机号号码不正确","error")
        }else{
        if(password==ensure_password){
            var check = checkPassword(ensure_password)
            if( authorityId != ""){
                if( check && ensure_password.length>7&&ensure_password.length<21){
                    $.post("../web/api/rolemanager/addNewmanager",{
                        "username":username,
                        "password":password,
                        "staffname":staffname,
                        "cellphone":cellphone,
                        "authorityId":authorityId
                    },function(data){
                        if( data.response == 'success' ){
                            discoverHtml()
                            ensure_alert("employeeManager")

                        }
                        else{
                            data_type_alert(data.data.text,"error")
                        }
                    },'json');
                }else{
                    data_type_alert("密码为8-20位的数字和字母组合","error")
                }
            }else{
                data_type_alert("没有角色无法创建","error")
            }

        }else{
            data_type_alert("两次密码输入不一致","error")
        }
        }
    });
    $(document).on("click","#deleteemployee",function(){
        var length = $("#itemContainer input[type='checkbox']").length;
        var Ids = "[";
        for(var i = 0;i < length;i++){
            if( $( $("#itemContainer input[type='checkbox']")[i]).prop("checked") ){
                Ids += '"' + $("input[name='detail_id']")[i].value + '",';
            }
        }
        Ids = Ids.substring( 0, Ids.length-1 );
        Ids += "]";
        if(Ids=="]"){
            data_type_alert("请勾选想要删除的员工","error")
        }
        $.post("../web/api/rolemanager/deletemanager",{
            "managerIds":Ids
        },function(data){
            if(data.response == 'success'){
                ensure_alert('employeeManager')
                window.location.reload()
            }
            else{

            }
        },'json');

    })

    $("#updateEmployeeConfirm").click(function(){
        var managerId = $("input[name='managerId']").val();
        var staffname = $("input[name='empDatialName']").val();
        var cellphone = $("input[name='empDatialTel']").val();
        var nonDisabled = $("input[type='radio']:checked").val();
        if(nonDisabled=="1"){
            nonDisabled = 'true';
        }else{
            nonDisabled = 'false';
        }
        var authorityId = $("select[name='empDatialRole'] option:selected").val();
        console.log(staffname,cellphone,nonDisabled,authorityId);
        $.post("../web/api/rolemanager/editManagerBuff",{
            "managerId":managerId,
            "staffname":staffname,
            "cellphone":cellphone,
            "nonDisabled":nonDisabled,
            "authorityId":authorityId
        },function(data){
            if(data.response == 'success'){
                discoverHtml()
                ensure_alert('employeeManager');
                window.parent.frames['mainleft'].window.location.href = 'mainleft';
            }
            else{
               data_type_alert(data.data.text,"error")

            }
        },'json');
    });
    $(document).on("click",".delete",function(){
        var Ids = '["' + $(this).parent().parent().parent().children()[1].value + '"]';
            $.post("../web/api/rolemanager/deletemanager", {
                "managerIds": Ids
            }, function (data) {
                if (data.response == 'success') {
                    discoverHtml()
                    ensure_alert('employeeManager');
                }
                else {
                    response_ensure_alert("error",data.data.text,function(){})
                }
            }, 'json');
    })

});

function search(buttonName, keywords , colName){
    $("#"+buttonName).click(function(){
        //var keywords = $.trim($("#keywords").val());
        var length = $(".updateName").length;
        var html = '';
        $("#itemContainer").children().css("display","none");
        for(var i = 0;i < length; i++){
            var name = $.trim($(".updateName")[i].innerHTML);
            console.log(name,keywords);
            if( keywords == name ){
                console.log(name,$(".updateName")[i].parentNode);
                $(".updateName")[i].parentNode.style.display = '';
            }
        }
        if( keywords == '' ){
            $("#itemContainer").children().css("display","");
        }
    });
}