<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单-申请退货</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/static/css/style.min.css?v=201601091628"/>
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/pageAction.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
</head>
<body>
    <input id="basePath" type="hidden" value="${basePath!''}"/>
<div class="order content-order-aftersale">
 <form id="upload-form" name="upload-form" method="post" enctype="multipart/form-data" target="hidden_frame">
    <input name="isBack"" type="hidden" value="1" />
    <input id="customerId" type="hidden" value="${cusId!''}" name="customerId"/>
    <input id="applyCredentials" type="hidden" value="3" name="applyCredentials"/>
    <input id="backReason" type="hidden" value="" name="backReason"/>
    <div class="order-number">
        <div class="list-item">
            <h3 class="item-head text-them">申请退货</h3> 

            <h3 class="item-head"><label for="">订单号：</label><span>${order.orderCode!''}</span></h3>

            <h3 class="item-head"><label for="">订单总额：</label><span class="text-them">${order.orderPrice!''}邮豆</span></h3>
        </div>
    </div>
    <div class="mt10">
        <div class="order-info">
            <div class="list-body-line">
             <span class="backGoodsContent" style="color:red;"></span>
                <#if order??>
                    <#list order.orderGoodsList as border>
                    <div class="list-item">
                    <div class="pro-item">
                        <div class="propic">
                        <a href="${basePath}/productdetail?productId=${border.goodsInfoId}">
                            <img alt="" src="${border.goodsImg!''}"  width="78" height="78"  title="${(border.goodsInfoName)!''}" alt="${(border.goodsInfoName)!''}" />
                        </div>
                        <div class="prodesc">
                            <h3 class="title">${border.goodsInfoName!''}</h3>

                            <p class="price"><span class="num"> <#if border.goodsInfoPrice??>
                            ${border.goodsInfoPrice!''}
                            </#if></span>邮豆</p>

                            <p class="number">×<span class="num">${(border.goodsInfoNum)!'0'}</span></p>
                        </div>
                        </a>
                    </div>
                    <div class="return-num">
                        <h3>申请数量
                            <div class="buy-num">
                                <span class="reduce disabled" onclick="mit(this)">−</span>
                                <input type="text" class="back_goods_id" placeholder="0" attr-maxnum="${(border.goodsInfoNum)!'0'}" value="0" attr-price="${border.goodsInfoPrice!''}" attr-goods="${(border.goodsInfoId)!''}" />
                                <span class="add" onclick="add(this)">+</span>
                            </div>
                            <span class="numError" style="color: red; "></span>
                        </h3>
                    </div>
                    <input class="goodsId" type="hidden" value="${(border.goodsInfoId)!''}"/>
                </div>
                    </#list>
                </#if>
                
            </div>
        </div>
    </div>
    <div class="mt10">
        <div class="list-item">
            <a href="javascript:;" class="return_reason">
                <h3 class="item-head">
                    <label for="">退货原因</label>
                    <span class="curValue" id="backReasons">请选择</span>
                    <i class="arrow-right"></i>
                    <span class="backReasonContent" style="color:red;"></span>
                </h3>
            </a>
        </div>
        <div class="list-item">
            <h3 class="item-head">申请凭据</h3>
            <div class="list-value">
                <ul class="pingju">
                    <li><div class="check-box checked noevidence"></div>无凭证</li>
                    <li><div class="check-box bill"></div>有发票</li>
                    <li><div class="check-box report"></div>有质检报告</li>
                </ul>
            </div>
        </div>
        <div class="list-item">
            <h3 class="item-head pr0"><label for="">退款金额</label>
                <input type="hidden" class="back_price" value="${order.orderPrice}">
                <span class="curValue">您最多可退款${order.orderPrice-(order.expressPrice?number)}邮豆</span>
            </h3>
            <div class="list-value">
                <div class="shuoming">
                    <input type="text" id="backorderprice" placeholder="0.00" readonly>
                </div>
            </div>
        </div>
        <div class="list-item">
            <h3 class="item-head pr0"><label for="">问题说明</label>
                <span class="curValue">您最多可填写200字</span>
            </h3>
            <div class="list-value">
                <div class="shuoming">
                    <textarea name="backRemark" id="backRemark" cols="30" rows="3" maxlength="200"></textarea>
                     <span id="backRemark_tips"></span>
                     <span class="backRemarkContent" style="color:red;"></span>
                </div>
            </div>
        </div>
        <div class="list-item">
            <h3 class="item-head">商品返回方式</h3>
            <div class="list-value returen-way">
                    <#if order.selfPick=="true">
                        <a class="btn-grey selected" href="javascript:;" backway="2">返回自提点</a>
                    <#else>
                        <a class="btn-grey selected" href="javascript:;" backway="0">快递</a>
                    </#if>

            </div>
        </div>
        <div class="list-item" id="pubevidence" style="display:none">
            <h3 class="item-head">上传凭证</h3>
            <div class="updata-pic">
                <div class="pic-list">
                    <ul id="show_pics${order.orderId}">
                        <li>
                            <div class="uppic">
                                <a href="javascript:;">
                                    <i></i>
                                </a>
                                <input type="file"class="upload_pics" id="uploadDocuments" name="uploadDocument" accept="image/jpg,image/gif,image/png,image/jpeg" order-id="${order.orderId}">
                               <input type="hidden" name="uploadDocuments" class="upload_documents"/>
                            </div>
                        </li>
                    </ul>
                </div>
                <p>最多上传3张，每张不超过5M，支持bmp，gif，jpg， png，jpeg</p>
            </div>
        </div>
    </div>
     	<input type="hidden" name="orderCode" id="orderCode" value="${order.orderCode}"/>
        <input type="hidden" name="orderId" value="${order.orderId}"/>
	    <input type="hidden" name="backPrice" id="backPrice" value="0.00"/>
    <div class="cancel">
        <div class="list-item">
            <a class="btn btn-full" href="javascript:void(0);" id="submitButtongoods"><i></i>提交申请</a>
        </div>
    </div>
  </form>  
   <iframe name="hidden_frame" style="display:none"></iframe>
</div>
<input type="hidden" class="flag_saved" value="0">

</body>
<script src="${basePath}/static/js/shoppingCar/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/static/js/applybackorder.js?v=201601221728"></script>
<script>
    $(function(){

        /* 选择退货原因 */
        $('.return_reason').click(function(){
            var reasonBox = dialog({
                width : 260,
                title : '选择一个退货原因',
                content : '<ul class="fav-list reasons"><li><a href="javascript:void(0);" value="1">不想买了<i class="check-box"></i></a></li><li><a href="javascript:void(0);" value="2">商品质量问题<i class="check-box"></i></a></li><li><a href="javascript:void(0);" value="3">收到商品与描述不符<i class="check-box"></i></a></li><li><a href="javascript:void(0);" value="4">其他<i class="check-box"></i></a></li></ul>',
                okValue : '确定',
                cancelValue : '取消',
                ok : function(){
                    $('.return_reason .curValue').text($('.reasons li.selected a').text());
                    $('#backReason').val($('.reasons li.selected a').attr('value'));
                    return true;
                },
                cancel : function(){
                    return true;
                }
            });
            reasonBox.showModal();
        });
        $('body').on('click','.reasons li',function(){
           $(this).addClass('selected').siblings().removeClass('selected');
        });
    });
</script>
</html>