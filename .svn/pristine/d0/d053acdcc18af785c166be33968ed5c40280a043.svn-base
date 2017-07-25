<!DOCTYPE html>
<html lang="en">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>个人资料-联系地址</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628">
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/pageAction.js"></script>
</head>
<body>
<div class="order content-order-confirm">
    <div class="chose-receive-info">

    <#if customer.contactAddr??>
        <input type="hidden" value="${customer.contactAddr!''}" id="mynum">
            <div class="list-item" style="padding-left: 10px;">
                <a href="${basePath}/goupdateAddress">
                    <p class="contactAddr">${customer.contactAddr!''}</p>
                    <i class="edit"></i>
                </a>
            </div>
        <#else>
            <div class="no_tips">
                <p>没有联系地址地址哦</p>
                <div class="list-item bottom-full">
                    <a class="btn btn-full" onclick="mychecknum()" href="javascript:;"><i></i>新增收货地址</a>
                </div>
            </div>
        </#if>
        <form id="subForm" method="post">
            <input type="hidden" name="addressId" id="addressId">
        </form>
    </div>
</div>

</body>
<script>
    $(function(){
        $(".checkaddr").click(function(){
            $("#addressId").val($(this).find(".select-box").next().val());
            $("#subForm").attr("action","${basePath}/changeAddress.htm").submit();
        })
    })

    function mychecknum() {
        var num = $("#mynum").val();
        if (num>=10) {
            $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>收货地址最多10个</h3></div></div>');
            setTimeout(function(){
                $('.tip-box').remove();
            },1000);
        }else
        {
            window.location.href ="${basePath}/toAddAddress.htm?flag=1";
        }

    }

</script>
</html>
