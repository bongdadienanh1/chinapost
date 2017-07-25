<!doctype html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择收货地址</title>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
</head>
<body style="background:#f0f2f5;">
<input type="hidden" id="basePath"  value="${basePath}"/>
<input type='hidden' id="cityCh" name="infoCity"/>
<div class="order content-order-confirm">
    <div class="chose-receive-info">
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
                                <p class="dress-info">${address.addressDetail}</p>
                            </div>
                            <i class="edit" onclick="window.location='${basePath}/toupdateAddress?addressId=${address.addressId!''}'"></i>
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
            </form>
            <div class="list-item bottom-full">
                <a class="btn btn-full" onclick="mychecknum()" href="javascript:;"><i></i>新增收货地址</a>
            </div>
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>