/**
 * Created by suibian on 2016/5/13.
 */
$(function(){
    /**
     * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
     */
    $("#infoProvince").change(function() {
        loadCity($(this).val());
    });
    /**
     * 城市改变时 触发此事件 开始加载下一级 --区县
     */
    $("#infoCity").change(function() {
        loadDistrict($(this).val());
    });

    /**
     * 区县改变时 触发此事件 开始加载下一级 --街道
     */
    $("#infoCounty").change(function() {
        loadStreet($(this).val());
    });
})



/**
 * 选中 省市区街道
 *
 * @param pid
 *            省编号
 * @param cid
 *            城市编号
 * @param did
 *            区县编号
 * @param sid
 *            街道编号
 * @param po
 *            infoProvince
 * @param co
 *            infoCity
 * @param dio
 *            infoCounty
 * @param so
 *            infoStreet
 */
function selectLocationOption(pid, cid, did, sid, po, co, dio, so) {
    if (pid == "") {
        return;
    }
    $("#" + po + " option[value='" + pid + "']").prop("selected", "selected"); // 选中省份
    loadCity(pid);
    $("#" + co + " option[value='" + cid + "']").prop("selected", "selected"); // 选中城市
    loadDistrict(cid);
    $("#" + dio + " option[value='" + did + "']").prop("selected", "selected");// 选中区县
    loadStreet(did);
    $("#" + so + " option[value='" + sid + "']").prop("selected", "selected"); // 选中街道
};

/**
 * 加载区县
 *
 * @param pid
 *            省份编号
 */
function loadDistrict(pid) {
    //获取区县
    //var district_map = $('.district_map').val();
    var paramStr = "cityId=" + pid;
    var basePath=$("#basePath").val();
    $.ajax({
        type : 'post',
        url : basePath+'/api/address/districts',
        data : paramStr,
        async : false,
        success : function(data) {
            if (data.length != 0) {
                var options = "";
                for (var i = 0; i < data.data.length; i++) {
                    var district = data.data[i];
                    options += "<dd id='ar"+district.districtId+"' onclick='chooseA(" + district.districtId + ");'>"+ district.districtName + "</dd>";
                    //options += "<option selected='selected' value='" + district.districtId + "'>"+ district.districtName + "</option>";
                    //options += "<option value='" + district.districtId + "'>"+ district.districtName + "</option>";
                }
                $('#areadl').html(options);
            } else {
                $("#areadl").html("");
            }
        }
    });
    //loadStreet($("#infoCounty").val());
}
/**
 * 加载区县
 *
 * @param pid
 *            省份编号
 */
function loadDistrict(pid) {
    //获取区县
    //var district_map = $('.district_map').val();
    var paramStr = "cityId=" + pid;
    var basePath=$("#basePath").val();
    $.ajax({
        type : 'post',
        url : basePath+'/api/address/districts',
        data : paramStr,
        async : false,
        success : function(data) {
            if (data.length != 0) {
                var options = "";
                for (var i = 0; i < data.data.length; i++) {
                    var district = data.data[i];
                    options += "<dd id='ar"+district.districtId+"' onclick='chooseE(" + district.districtId +");'>"+ district.districtName + "</dd>";
                    //options += "<option selected='selected' value='" + district.districtId + "'>"+ district.districtName + "</option>";
                    //options += "<option value='" + district.districtId + "'>"+ district.districtName + "</option>";
                }
                $('#areadl').html(options);
            } else {
                $("#areadl").html("");
            }
        }
    });
    //loadStreet($("#infoCounty").val());
};

var choosedp="";
var choosedc="";
var chooseda="";
/**
 * 点击市加载区
 */
function chooseD(cid){
    $('.area-box-lv2').hide();
    $('.area-box-lv3').show();
    choosedp = $("#ci"+cid).text();
    $("#readycity").html("");
    $("#readycity").html("江苏省"+' '+choosedp+' '+choosedc);
    loadDistrict(cid);
    $("#cityCh").val(cid);
}

/**
 * 点击区
 */
function chooseE(aid){
    var basePath = $("#basePath").val();
    $('.area-box-lv3').hide();
    $('.area-box-lv4').show();
    choosedc = $("#ar"+aid).text();
    $("#areadl1").html('');
    $("#readycity1").html("");
    $("#readycity1").html("江苏省"+' '+choosedp+' '+choosedc);
    $.post(basePath+"/getEnterpriseByDistrictId",{districtId:aid},function(data){
        if(data.data.length != "0"){
            var options = "";
            for(var i = 0;i<data.data.length;i++){
                var district = data.data[i];
                if(typeof(district.address)!='undefined') {
                    options += "<dd id='ar" + district.enterpriseId + "' onclick='chooseF(" + district.enterpriseId + "," + "\"" + district.address + "\"" + "," + "\"" + district.enterpriseName + "\"" + ")'>" + district.enterpriseName + "</dd>"
                }
            }
            $("#areadl1").html(options);
        };
    });
}
//点击退后按钮
function back1(){
    $('.opacity-bg-3').remove();
    $("#readypro").html('');
    $('.screen').hide();
    $("#provinceCh").val("");
}
function back2(){
    $("#readycity").html("");
    choosedp='';
    $("#readypro").html("江苏省"+' '+choosedp+' '+choosedc);
    $('.area-box-lv3').hide();
    $('.area-box-lv2').show();
}
function back3(){
    $("#readycity1").html("");
    choosedc = '';
    $("#readycity").html("江苏省"+' '+choosedp+' '+choosedc);
    $('.area-box-lv4').hide();
    $('.area-box-lv3').show();
}
//点击区提交表单
function chooseF(enterpriseId,addressName,enterpriseName){
    $('#enterpriseId').val(enterpriseId);
    $('#addressName').val(addressName);
    $('#enterpriseName').val(enterpriseName);
    $('#changeDeliveryAddressForm').submit();
}

$(function(){
    /* 选择地区 */
    $('.choose_area').click(function(){
        $('body').append('<div class="opacity-bg-3"></div>');
        $('.area-box-lv2').show();
    });

    $('#back').click(function(){
        $('.opacity-bg-3').remove();
        $('.screen').hide();
        $("#provinceCh").val("");
    });

    /* 滑动选框 */
    $('.checkbox').click(function(){
        $(this).toggleClass('selected');
        if ($('#defaultDiv').hasClass('selected')) {
            $("#isDefault1").val('1');
        }else{
            $("#isDefault1").val('0');
        }
    });
});

//添加自提点
function getSuggestEnterprise(addressName,enterprisId,enterpriseName){
    var sinceSome ="";
    var basePath = $("#basePath").val();
    var id = enterprisId;
    //点击网点自提加载推荐自提点
    $.post(basePath+"/getSuggestEnterprise",{},function(data){
        $(".sinceSome").html("");
        if(data.response == "success"){
            if(data.data.length !="0"){
                for(var i = 0;i<data.data.length;i++){
                    if(typeof(data.data[i].address)!='undefined') {
                        //判断是否添加选中状态
                        if(data.data[i].enterpriseId != id){
                            sinceSome += "<li><div class='list-item'> <div class='checkaddr chooseFrom' enterpriseId = '" + data.data[i].enterpriseId + "'> <i class='select-box' onclick='checkone(this)'></i><p class='dress-info' id='denterprise' style='color: #666'>" + data.data[i].enterpriseName + "</p> <p class='dress-info'>" + data.data[i].address + "</p></div></div></li>";
                        }else {
                            sinceSome += "<li><div class='list-item'> <div class='checkaddr chooseFrom' enterpriseId = '" + data.data[i].enterpriseId + "'> <i class='select-box selected' onclick='checkone(this)'></i><p class='dress-info' id='denterprise' style='color: #999'>" + data.data[i].enterpriseName + "</p> <p class='dress-info'>" + data.data[i].address + "</p></div></div></li>";
                        }
                    }
                }
                $(".sinceSome").append(sinceSome);
            }else {
                if(enterprisId== null || enterprisId == "") {
                    $(".sinceSome").append(
                        "<li value='' >" + "请选择" + "</li>");
                };
            };

            if(enterprisId!= null && enterprisId != ""){
                //显示先添加的自提点
                var sinceSomeHtml = ""
                var sinceLi = $(".sinceSome").find("li");
                var arr = [];
                //显示新添加自提点
                for (var i = 0; i < sinceLi.length; i++) {
                    arr.push(sinceLi.eq(i).find(".chooseFrom").attr("enterpriseId"));
                }
                //判断列表中是否存在该自提点
                var intOf = arr.indexOf(id);
                if (intOf < 0) {
                    sinceSomeHtml += "<li><div class='list-item'> <div class='checkaddr chooseFrom' style='color: #999' enterpriseId = '" + enterprisId + "'> <i class='select-box selected' onclick='checkone(this)'></i><p class='dress-info' id='denterprise'>"+enterpriseName+"</p><p class='dress-info'>" + addressName + "</p></div></div></li>";
                    $(".sinceSome").prepend(sinceSomeHtml);
                }
            };
            //选择推荐自提点
            $(".chooseFrom").click(function(){
                var enterpriseName =$(this).find("#denterprise").text();
                var addressName =$(this).find("#denterprise").next().text();
                var enterpriseId = $(this).attr("enterpriseId");
                chooseF(enterpriseId,addressName,enterpriseName);
            });
        }
    });
    $("#since").css("display","block");
    $("#logistics").css("display","none");
    $(".logisticsBtn").removeClass("selected");
    $(".sinceBtn").addClass("selected");
}


//添加收货地址
function mychecknum() {
    var num = $("#mynum").val();
    var flag = $("#flag").val();
    if (num>=10) {
        $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>收货地址最多10个</h3></div></div>');
        setTimeout(function(){
            $('.tip-box').remove();
        },1000);
    }else
    {
        window.location.href ="myaddress?flag="+flag;
    };
}