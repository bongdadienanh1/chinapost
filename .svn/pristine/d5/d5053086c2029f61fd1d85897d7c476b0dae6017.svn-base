<!doctype html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择支付配送方式</title>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/distribution.js?v=201608191022"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
</head>
<body>
<input type="hidden" id="basePath"  value="${basePath}"/>
<input type="hidden" id="flag" value="${flag!''}" name="flag">
<input type='hidden' id="cityCh" name="infoCity"/>
<input type="hidden" id="selfFlag" value="${selfFlag!''}">
<div class="order content-order-confirm">
    <div class="chose-receive-info">
        <div class="list-item flag" style="padding-left:10px;padding-right: 10px;">
            <div class="send-way">
                <h3 class="item-head" style="margin-left: 1.0em;">配送方式</h3>
                <div class="list-value">
                    <p class="btn-grey logisticsBtn selected">物流配送</p>
                    <p class="btn-grey sinceBtn">网点自提</p>
                </div>
            </div>
        </div>
        <div class="order content-order-confirm" id="logistics">
            <input type="hidden" value="1" id="mynum">
            <ul class="chose-receive-info">
                <#if addresses??&&addresses?size!=0>
                    <#list addresses as address>
                        <li>
                            <div class="list-item">
                                <div class="checkaddr">
                                    <i class="select-box <#if address.isDefault=='1'>selected</#if>" onclick="checkone(this)"></i>
                                    <input type="hidden" value="${address.addressId!''}">
                                    <h3>
                                        <span class="name">${address.addressName!''}</span>
                                        <span class="phoneNum">${address.addressMoblie!''}</span>
                                    </h3>
                                    <p class="dress-info">${address.province.provinceName!''}${address.city.cityName!''}${address.district.districtName!''}${address.addressDetail}</p>
                                </div>
                                <span class="edit" onclick="window.location='${basePath}/toupdateAddress?addressId=${address.addressId!''}&flag=${flag!''}'"><i class="edit_icon"></i>编辑</span>
                                <span class="delete" onclick="deleteAddress(${address.addressId!''})"><i class="delete_icon"></i>删除</span>
                            </div>
                        </li>
                    </#list>
                    <#else >
                        <div class="no_tips">
                            <p>没有收货地址哦</p>
                        </div>
                </#if>
            </ul>
            <form id="subForm" method="post">
                <input type="hidden" name="addressId" id="addressId">
                <input type="hidden" name="flag" value="${flag!''}">
            </form>
            <div class="list-item bottom-full">
                <a class="btn btn-full" onclick="mychecknum()" href="javascript:;"><i></i>新增收货地址</a>
            </div>
        </div>
        <#--网点自提-->
        <div class="order content-order-confirm" id="since" style="display: none;">
            <form action="changeDeliveryAddress" id="changeDeliveryAddressForm">
                <input type="hidden" name="enterpriseId" id="enterpriseId" value="${enterpriseId!''}">
                <input type="hidden" name="addressName" id="addressName" value="${addressName!''}">
                <input type="hidden" name="enterpriseName" id="enterpriseName" value="${enterpriseName!''}">
            </form>
            <#--推荐提货网点-->
            <ul class="chose-receive-info sinceSome">
            </ul>
            <div class="list-item bottom-full">
                <a class="btn btn-full choose_area" onclick="delivery()" href="javascript:;"><i></i>更多提货网点</a>
            </div>
        </div>
        <div class="screen area-box-lv2" style="display:none;">
            <div class="screen-header">
                <a class="back" href="javascript:;" onclick="back1();">取消</a>
                请选择市
            </div>
            <div class="current-area">
                已选择：
                <span id="readypro"></span>
            </div>
            <div class="screen-cont">
                <div class="lists city">
                    <dl id="citydl">
                    </dl>
                </div>
            </div>
        </div>

        <div class="screen area-box-lv3" style="display:none;">
            <div class="screen-header">
                <a class="back" href="javascript:;" onclick="back2();"><i class="back-icon"></i></a>
                请选择地区
            </div>
            <div class="current-area">
                已选择：
                <span id="readycity"></span>
            </div>
            <div class="screen-cont">
                <div class="lists district">
                    <dl id="areadl">
                    </dl>
                </div>
            </div>
        </div>

        <div class="screen area-box-lv4" style="display:none;">
            <div class="screen-header">
                <a class="back" href="javascript:;" onclick="back3();"><i class="back-icon"></i></a>
                请选择提货网点
            </div>
            <div class="current-area">
                已选择：
                <span id="readycity1"></span>
            </div>
            <div class="screen-cont">
                <div class="lists district">
                    <dl id="areadl1">
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
<#--<div class="bar-bottom">-->
    <#--<a class="bar-item selected" href="${basePath}/main"><i class="bar-bottom-i home"></i>首页</a>-->
    <#--<a class="bar-item" href="${basePath}/myshoppingmcart"><i class="bar-bottom-i cart"></i>购物车</a>-->
    <#--<a class="bar-item" href="/mobile/customercenter"><i class="bar-bottom-i mine"></i>我的</a>-->
<#--</div>-->
<script type="text/javascript">
    $(function(){
        var enterprisId = $('#enterpriseId').val();
        var addressName =$("#addressName").val();
        var enterprisName =$("#enterpriseName").val();
//        if(enterprisId != null && enterprisId != ""){
//            getSuggestEnterprise(addressName,enterprisId,enterprisName);
//        }
        //判断从个人中心里进入
        var flag = $("#flag").val();
        if(flag == 1 && flag !=""){
            $(".flag").hide();
            $("#since").hide();
        };
        var selfFlag = $("#selfFlag").val();
        if(selfFlag != null && selfFlag != ''){
            $("#since").show();
            $("#logistics").hide();
            getSuggestEnterprise(addressName,enterprisId,enterprisName);
            $(".logisticsBtn").removeClass('selected');
            $(".sinceBtn").addClass('selected');
        }else {
            $("#logistics").show();
            $("#since").hide();
            $(".logisticsBtn").addClass('selected');
            $(".sinceBtn").removeClass('selected');
        }
        //点击提交表单
        $(".checkaddr").click(function(){
            $("#addressId").val($(this).find(".select-box").next().val());
            $("#subForm").attr("action","changeAddress").submit();
        });
        //点击切换自提，物流
        $(".list-value").find("p").click(function(){
            var enterprisId = $('#enterpriseId').val();
            var addressName =$("#addressName").val();
            $(this).addClass("selected").siblings().removeClass("selected");
            if($(this).text() == "物流配送"){
                window.location.href ="distribution?enterprisId="+enterprisId+"addressName="+addressName;
            }else{
                $("#logistics").css("display","none");
            }
            if($(this).text() == "网点自提"){
                getSuggestEnterprise(addressName,enterprisId);
            }else{
                $("#since").css("display","none");
            }
        });
    })
    //购物车商品的选中
    function checkone(ojb) {
        var selected = $(ojb).parent().find(".selected");
        if (selected.length == 0) {
            $(ojb).addClass("selected");
        } else {
            $(ojb).removeClass("selected");
        }
//        lastPrice();
    }
    //选择更多提货网点
    function delivery(){
        $("#readypro").html("江苏省");
        $.post("${basePath}/api/address/cities",{provinceId:"10"},function(data){
            $(".citydl").html("");
            if(data.length != 0){
                var options = "";
                for(var i = 0; i < data.data.length; i++){
                    var city = data.data[i];
                    options += "<dd id='ci"+city.cityId+"' onclick='chooseD(" + city.cityId + ");'>"+ city.cityName + "</dd>";
                }
                $("#citydl").append(options);
            }
        });
    };
    function deleteAddress(addressId){
        var deleteAddress = dialog({
            width:200,
            title:"提示",
            content:'<p class="tc">确认删除收货地址！！</p>',
            okValue: '确定',
            cancelValue: '取消',
            ok:function(){
                window.location.href = "${basePath}/delAddress?addressId="+addressId+"&flag="+$("#flag").val();
            },
            cancel: function () {
                //停留在当前页面
                return true;
            }
        })
        deleteAddress.showModal();
    }
</script>
</body>
</html>