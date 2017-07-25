/**
 * Created by Administrator on 2016-06-14.
 */
$(document).ready(function(){
    var current_type = '';
    var enterpriseId = '';
    var goodsInfoName = '';
    var goodsNumber = '';
    var brandId = '';
    var thirdId = '';
    var size = 35;
    var pageNumber = 2;
    var lastEnterpriseId = '';

    //查询按货品
    $("#invenSearchItem").click(function(){
        //更改当前状态为查询按货品
        current_type = 'byGoods';
        //滚动分页起始页初始化
        pageNumber = 2;
        $("#invenSearchItem").css("font-weight","bold").css("background","#0584c5")
        $("#invenSearchDepot").removeAttr("style")
        enterpriseId = $("#Dopet").next().val();
        goodsInfoName = $("input[name='itemManager_Name']").val();
        goodsNumber = $("input[name='itemManager_Number']").val();
        brandId = '';
        thirdId = '';
        $.post("../web/api/inventory/getByGoodsInfo",{
            enterpriseId:enterpriseId,
            goodsInfoName:goodsInfoName,
            goodsNumber:goodsNumber,
            brandId:brandId,
            thirdId:thirdId,
            page:1,
            size:size
        },function(data){
            if( data.response == 'success' ){
                var html = '';
                try{
                    data.data.content.map(function(object){
                        var specString = handleSpecString(object.specString);
                        html += '<tr style="height: 40px;">';
                        html += '<td>' + handleUndefined(object.goodsInfoName) + '</td>'
                        html += '<td title="' + specString[1] + '">' + specString[0] + '</td>';
                        html += '<td>' + handleUndefined(object.goodsNumber) + '</td>';
                        if(  object.goodsInfoType == "0"){
                            html += '<td>集采商品</td>';
                        }else if( object.goodsInfoType == "1"){
                            html += '<td>自购商品</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '<td>' + handleUndefined(object.sumInventory) + '</td>'
                        html += '<td>' + handleUndefined(object.sumAvailable) + '</td>';
                        html += '<td>' + handleUndefined(object.goodsBrand) + '</td>';
                        html += '<td>' + handleUndefined(object.goodsMerchants) + '</td>';
                        html += '</tr>';
                    });
                    $(".seachTableItem table tbody").empty().append(html);
                }
                catch(e){
                    console.log(e);
                }
            }
        },'json')
    });

    //查询按仓库
    $("#invenSearchDepot").click(function(){
        //更改查询状态
        current_type = 'byInventory';
        //滚动分页起始页初始化
        pageNumber = 2;
        //上一页最后一条数据的ID
        $("#invenSearchDepot").css("font-weight","bold").css("background","#0584c5")
        $("#invenSearchItem").removeAttr("style")

        enterpriseId = $("#Dopet").next().val();
        goodsInfoName = $("input[name='itemManager_Name']").val();
        goodsNumber = $("input[name='itemManager_Number']").val();
        brandId = '';
        thirdId = '';
        $.post("../web/api/inventory/getByInventory",{
            enterpriseId:enterpriseId,
            goodsInfoName:goodsInfoName,
            goodsNumber:goodsNumber,
            brandId:brandId,
            thirdId:thirdId,
            page:1,
            size:size
        },function(data){
            if( data.response == 'success' ){
                if( data.data.content[0] == ""){
                    $(".seachTableDepot .seachTableDepotContent .tbodyContent").empty()
                }else{
                    var html = '';
                    var tempId = '';
                    data.data.content.map(function(object){
                        var i = 0;
                        var accountName = '';
                        var innerHtml = '';
                        innerHtml += '<table style="table-layout:fixed;" class="insidetable2" width="100%" border="0" align="center" cellspacing="0">';
                        innerHtml += '<tbody align="center">';
                        object.map(function(object){
                            i++;
                            if( i == 1 ){
                                accountName = object.enterpriseName;
                                tempId = object.enterpriseId;
                            }
                            var specString = handleSpecString(object.specString);


                            innerHtml += '<tr style="height: 50px;" align="center">';
                            innerHtml += '<td>' + handleUndefined(object.goodsInfoName) + '</td>'
                            innerHtml += '<td title="' + specString[1] + '">' + specString[0] + '</td>';
                            innerHtml += '<td>' + handleUndefined(object.goodsNumber) + '</td>';

                            if(  object.goodsInfoType == "0"){
                                innerHtml += '<td>集采商品</td>';
                            }else if( object.goodsInfoType == "1"){
                                innerHtml += '<td>自购商品</td>';
                            }else{
                                innerHtml += '<td></td>';
                            }
                            innerHtml += '<td>' + handleUndefined(object.inventory) + '</td>'
                            innerHtml += '<td>' + handleUndefined(object.available) + '</td>';
                            innerHtml += '<td>' + handleUndefined(object.goodsBrand) + '</td>';
                            innerHtml += '<td>' + handleUndefined(object.goodsMerchants) + '</td>';
                            innerHtml += '</tr>';

                        });
                        innerHtml += '</tbody>';
                        innerHtml += '</table>';
                        var outerHtml = '';
                        outerHtml += '<tr style="border-bottom:1px solid #e5e5e5;" id="' + tempId + '">';
                        outerHtml += '<td style="width: 200px;border-right: 1px solid #e5e5e5;">' + handleUndefined(accountName) + '</td>';
                        outerHtml += '<td>';
                        html += outerHtml + innerHtml;
                        html += '</td>';
                        html += '</tr>';
                    });
                    $(".seachTableDepot .seachTableDepotContent .tbodyContent").empty().append(html);
                    if( lastEnterpriseId != tempId ){

                        lastEnterpriseId = tempId;
                        var tableHeightPX = $(".insidetable2").css("height");
                        var tableHeight = tableHeightPX.substring(0,tableHeightPX.length-2);
                        var besideHeightPX = $(".insidetable2").parent().prev().css("height");
                        var besideHeight = besideHeightPX.substring(0,besideHeightPX.length-2);
                        var parentHeightPX = $(".insidetable2").parent().css("height");
                        var parentHeight = parentHeightPX.substring(0,parentHeightPX.length-2);
                        console.log(besideHeight,tableHeight * $(".insidetable2").length);
                        if( besideHeight > tableHeight * ($(".insidetable2").length + 1) ){
                            var height = besideHeight / $(".insidetable2").length -1;
                            $(".insidetable2").css("height",height);
                        }
                    }
                }

            }
        },'json');
    });

    //导出
    $("#invenOut").click(function(){
        var searchStr = '';
        if( enterpriseId != '' ){
            searchStr += 'enterpriseId=' + enterpriseId + '&';
        }
        if( goodsInfoName != ''){
            searchStr += 'goodsInfoName=' + goodsInfoName + '&';
        }
        if( goodsNumber != '' ){
            searchStr += 'goodsNumber=' + goodsNumber + '&';
        }
        if( enterpriseId != '' ){
            if( current_type == 'byGoods' ){
                window.location.href = '../web/api/exportExcel/downByGoodsInfo?' + searchStr;
            }
            else if( current_type == 'byInventory' ){
                window.location.href = '../web/api/exportExcel/downByInventory?' + searchStr;
            }
        }else{
            data_type_alert("请先查询库存，然后导出查询结果","error")
        }

    });

    //滚动分页
    var isActive = true;
    var flag = true;
    var url = '';
    $(window.document).scroll(function(){
        if( current_type == 'byGoods' ){
            url = '../web/api/inventory/getByGoodsInfo';
        }
        else if( current_type == 'byInventory' ){
            url = '../web/api/inventory/getByInventory';
        }
        if($(window.document).scrollTop() > ($(window.document).height() - $(window.window).height()-60) ){
            if( isActive == true ){
                isActive = 'loading';
                $.post(url,{
                    'enterpriseId': enterpriseId,
                    'goodsInfoName':goodsInfoName,
                    'goodsNumber':goodsNumber,
                    'brandId':brandId,
                    'thirdId':thirdId,
                    'page':pageNumber,
                    'size':size
                },function(data){
                    if( data.response == 'success' || flag == true ){
                        if( isActive == 'loading' ){
                            if( data.data.content != '' ){
                                pageNumber = pageNumber + 1;
                                var html = '';
                                data.data.content.map(function(object){
                                    var i = 0;
                                    var tempId = '';
                                    if( current_type == 'byGoods' ){
                                        var specString = handleSpecString(object.specString);
                                        html += '<tr style="height: 40px;">';
                                        html += '<td>' + handleUndefined(object.goodsInfoName) + '</td>'
                                        html += '<td title="' + handleUndefined(specString[1]) + '">' + handleUndefined(specString[0]) + '</td>';
                                        html += '<td>' + handleUndefined(object.goodsNumber) + '</td>';
                                        if(  object.goodsInfoType == "0"){
                                            html += '<td>集采商品</td>';
                                        }else if( object.goodsInfoType == "1"){
                                            html += '<td>自购商品</td>';
                                        }else{
                                            html += '<td></td>';
                                        }
                                        html += '<td>' + handleUndefined(object.sumInventory) + '</td>'
                                        html += '<td>' + handleUndefined(object.sumAvailable) + '</td>';
                                        html += '<td>' + handleUndefined(object.goodsBrand) + '</td>';
                                        html += '<td>' + handleUndefined(object.goodsMerchants) + '</td>';
                                        html += '</tr>';
                                    }
                                    else if( current_type == 'byInventory' ){
                                        var outerHtml = '';
                                        var innerHtml = '';
                                        var sameHtml = '';
                                        object.map(function(object){
                                            tempId = object.enterpriseId;
                                            i++;
                                            if( i == 1 ){
                                                innerHtml += '<td style="width: 200px;border-right: 1px solid #e5e5e5;">' + handleUndefined(object.enterpriseName) + '</td>';
                                            }
                                        });
                                        if( lastEnterpriseId == tempId ){
                                            sameHtml += '<table style="table-layout:fixed;" class="insidetable2" width="100%" border="0" align="center" cellspacing="0">';
                                            sameHtml += '<tbody align="center">';
                                            object.map(function(object){
                                                var specString = handleSpecString(object.specString);
                                                sameHtml += '<tr style="height: 50px;" align="center">';
                                                sameHtml += '<td>' + handleUndefined(object.goodsInfoName) + '</td>'
                                                sameHtml += '<td title="' + specString[1] + '">' + specString[0] + '</td>';
                                                sameHtml += '<td>' + handleUndefined(object.goodsNumber) + '</td>';
                                                if(  object.goodsInfoType == "0"){
                                                    sameHtml += '<td>集采商品</td>';
                                                }else if( object.goodsInfoType == "1"){
                                                    sameHtml += '<td>自购商品</td>';
                                                }else{
                                                    sameHtml += '<td></td>';
                                                }
                                                sameHtml += '<td>' + handleUndefined(object.inventory) + '</td>'
                                                sameHtml += '<td>' + handleUndefined(object.available) + '</td>';
                                                sameHtml += '<td>' + handleUndefined(object.goodsBrand) + '</td>';
                                                sameHtml += '<td>' + handleUndefined(object.goodsMerchants) + '</td>';
                                                sameHtml += '</tr>';

                                            });
                                            sameHtml += '</tbody>';
                                            sameHtml += '</table>';
                                            $("#" + tempId).children().last().append(sameHtml);
                                        }
                                        else{
                                            outerHtml = '<tr style="border-bottom:1px solid #e5e5e5;" id="' + tempId + '">';
                                            html += outerHtml + innerHtml + '<td>';
                                            html += '<table style="table-layout:fixed;" class="insidetable2" width="100%" border="0" align="center" cellspacing="0">';
                                            html += '<tbody align="center">';
                                            object.map(function(object){
                                                var specString = handleSpecString(object.specString);

                                                html += '<tr style="height: 50px;" align="center">';
                                                html += '<td>' + handleUndefined(object.goodsInfoName) + '</td>'
                                                html += '<td title="' + specString[1] + '">' + specString[0] + '</td>';
                                                html += '<td>' + handleUndefined(object.goodsNumber) + '</td>';
                                                if(  object.goodsInfoType == "0"){
                                                    html += '<td>集采商品</td>';
                                                }else if( object.goodsInfoType == "1"){
                                                    html += '<td>自购商品</td>';
                                                }else{
                                                    html += '<td></td>';
                                                }
                                                html += '<td>' + handleUndefined(object.inventory) + '</td>'
                                                html += '<td>' + handleUndefined(object.available) + '</td>';
                                                html += '<td>' + handleUndefined(object.goodsBrand) + '</td>';
                                                html += '<td>' + handleUndefined(object.goodsMerchants) + '</td>';
                                                html += '</tr>';

                                            });
                                            html += '</tbody>';
                                            html += '</table>';
                                            html += '</td></tr>';
                                        }
                                    }
                                });
                                if( current_type == 'byGoods' ){
                                    $(".seachTableItem table tbody").append(html);
                                }
                                else if( current_type == 'byInventory' ){
                                    $(".tbodyContent").append(html);
                                }
                            }else{
                                flag = false;
                            }
                        }
                        isActive = true;
                    }
                    else{
                        $(".loading").html("加载失败");
                    }
                },'json');
            }
        }
        else{

        }
    });

    //查询按货品
    $("#invenSearchItem").click(function(){
        $(".seachTableItem").show();
        $(".seachTableDepot").hide();
    });

    //查询按仓库
    $("#invenSearchDepot").click(function(){
        $(".seachTableDepot").show();
        $(".seachTableItem").hide();
    });

});