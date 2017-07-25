
$(function (){
	//选择上传文件后触发
	$(".uploadImg").change(function() {
		//$(".up_photo").click();
		var photoName = document.getElementById("uploadFile").value;
        if (photoName != "") {
            var photoType = photoName.substring(photoName.lastIndexOf(".") + 1).toLocaleLowerCase(); //转换成小写
            if (photoType != "jpeg" && photoType != "jpg" && photoType != "bmp" && photoType != "png" && photoType != "gif") {
                setTimeout(function(){
                    $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3><i class="error"></i>上传图片格式不正确,或未选中图片 （图片格式为jpeg、jpg、png、gif、bmp）</h3></div></div>');
                },1000);
                setTimeout(function(){
                    $('body').find('div.tip-box').remove();
                },3000);
                return;
            }
        }else {
            setTimeout(function(){
                $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3><i class="error"></i>上传图片,格式为jpeg、jpg、png、gif、bmp。</h3></div></div>');
            },1000);
            setTimeout(function(){
                $('body').find('div.tip-box').remove();
            },3000);
            return;
        }
        lrz(this.files[0], { width: 400 }, function (results) {
        	i = 0; // 图片上传成功标识改变设置为0
			$("#CustomerImg").attr("value",results.base64);
			//alert(orderGoodsId)
			$("#upload_form").submit();
        });
	});
});


/**
 * 上传文件成功后，触发。
 * @param msg
 */
function callback(msg) {  
	//上传失败
    if(msg=="101"||msg=="102"){
        alertStr("上传图片失败！");
    }else {
        //上传成功
        var imgSrc = msg.substring(0,msg.lastIndexOf("."));
        var lastSrc = msg.substring(msg.lastIndexOf("."));
        var msg = imgSrc+"_160x160"+lastSrc;
        $('.opacity-bg-3').remove();
        $('.choose_photo').hide();
        $(".positionIcon").find("img").attr("src",msg);
    }
}

function alertStr(str){
    $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>'+str+'</h3></div></div>');
    setTimeout(function(){
        $('.tip-box').remove();
    },1000);
}



