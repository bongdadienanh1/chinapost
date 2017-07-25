$(document).ready(function(){
    //var flag = true;
    //setInterval(function(){
    //    if($("#csv").val()){
    //        flag = UpladFile(this,flag);
    //    }
    //},500);
    //$(".import").click(function(){
    //   flag = true;
    //});
    var reduce_flag = true;
    var send_flag = true;
    setInterval(function(){
        if($("#send_csv").val()){
            $("#cover1").show();
            $(".loading").show();
            send_flag = UpladFile("send_csv",send_flag,true);
        }
    },500);
    setInterval(function(){
        if($("#reduce_csv").val()){
            $("#cover1").show();
            $(".loading").show();
            reduce_flag = UpladFile("reduce_csv",reduce_flag,false);
        }
    },500);
    //$(".import").click(function(){
    //   flag = true;
    //});
});

function UpladFile(str,flag,type) {
    var fileObj = document.getElementById(str).files[0]; // 获取文件对象
    var typeId = $("#" + str).data("id");
    var FileController = "../web/api/ucoingrand/newParseFile";                    // 接收上传文件的后台地址
    var data = [];
    // FormData 对象
    var form = new FormData();// 可以增加表单数据
    form.append("file", fileObj);                           // 文件对象
    form.append("type",type);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", FileController, true);
    // XMLHttpRequest 对象
    if( flag == true ){
        xhr.send(form);
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                var responseText = xhr.responseText;
                var data = JSON.parse(responseText);
                if (data.response == 'success'){
                    $("#key").prop("value",data.data);
                    $("#business_type").prop("value",type);
                    $("#list_submit").trigger("click");
                    $("#" + str).val("");
                }
                else{
                    response_ensure_alert('error',data.data.text,function(){
                        $("#" + str).val("");
                        window.location.href = 'piliangdaoru';
                    });
                }
            }
            else if( xhr.status == 400 ){
                response_ensure_alert("error","导入失败",function(){
                    $("#" + str).val("");
                    window.location.href = 'piliangdaoru';
                });
            }
            else if( xhr.status == 500){
                response_ensure_alert("error","服务器内部错误",function(){
                    $("#" + str).val("");
                    window.location.href = 'piliangdaoru';
                });
            }
        };
        return false;
    }
}
//function UpladFile(content,flag) {
//    var fileObj = document.getElementById("csv").files[0]; // 获取文件对象
//    var typeId = $("#csv").data("id");
//    var FileController = "../web/api/ucoingrand/parseExcel";                    // 接收上传文件的后台地址
//    var data = [];
//    // FormData 对象
//    var form = new FormData();// 可以增加表单数据
//    form.append("file", fileObj);                           // 文件对象
//    form.append("typeId",typeId);
//    var xhr = new XMLHttpRequest();
//    xhr.open("POST", FileController, true);
//    // XMLHttpRequest 对象
//    if( flag == true ){
//        xhr.send(form);
//        xhr.onreadystatechange = function(){
//            if(xhr.readyState == 4 && xhr.status == 200){
//                str = xhr.responseText;
//                data = JSON.parse(str);
//                if (data.response == 'success'){
//                    $("#json_data").prop("value",str);
//                    $("#typeId").prop("value",typeId);
//                    $("#list_submit").trigger("click");
//                    $("#csv").val("");
//                }
//                else{
//                    response_ensure_alert('error',data.data.text,function(){
//                        $("#csv").val("");
//                        window.location.href = 'piliangdaoru';
//                    });
//                }
//            }
//            else if( xhr.status == 400 ){
//                response_ensure_alert("error","导入失败",function(){
//                    $("#csv").val("");
//                    window.location.href = 'piliangdaoru';
//                });
//            }
//        };
//        return false;
//    }
//}