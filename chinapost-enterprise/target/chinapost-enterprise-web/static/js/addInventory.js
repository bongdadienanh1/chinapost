$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    $("#chooseInven").click(function(){
        $.post("../web/api/goodsManager/getGoodsInfo",{
            goodsInfoName:goodsInfoName,
            goodsInfoItemNo:goodsInfoItemNo,
            brandId:brandId,
            thirdId:thirdId,
            page:1,
            size:5
        },function(data){
            if( data.response == 'success' ){
                var total = data.data.totalElements;
                for(var i = 0;i < total;i++){
                    var html = get_html(1);
                    $("#itemContainer").append(html);
                }
                jPagesGet("holder","itemContainer",5);
                //处理多余页
                var showElement = ( $("#holder a").length - 2 ) * 5 ;
                if( showElement >= ( data.data.totalElements + 5 ) ){
                    $( $("#holder a")[$("#holder a").length - 2]).remove();
                }
                $("#holder").find("a").css("display","inline-block");
                $("#holder").find("span").css("display","inline-block");
                $("#holder").find(".jp-hidden").css("display","none");
                $("#itemContainer").empty();

                $("#holder a").unbind("click");
                $("#holder a").click(function(){
                    $(".jp-current").next().css("display","inline-block");
                    $(".jp-current").next().next().css("display","inline-block");
                    $(".jp-current").next().next().css("display","inline-block");
                    var page = '';
                    if( $(this).html() == '＞' ){
                        if( parseInt($(".jp-current").html()) > 0 && parseInt($(".jp-current").html()) < ( $("#holder a").length - 2 ) ){
                            page = parseInt($(".jp-current").html()) + 1;
                        }
                        else{
                            page = parseInt($(".jp-current").html());
                        }
                    }
                    else if( $(this).html() == '＜' ){
                        if( parseInt($(".jp-current").html()) < ( $("#holder a").length - 1 ) ){
                            page = parseInt($(".jp-current").html()) - 1;
                        }
                        else{
                            page = parseInt($(".jp-current").html());
                        }
                    }
                    else{
                        page = $(this).html();
                    }
                    if( !isNaN(page) ){
                        $.post("../web/api/goodsManager/getGoodsInfo",{
                            goodsInfoName:goodsInfoName,
                            goodsInfoItemNo:goodsInfoItemNo,
                            brandId:brandId,
                            thirdId:thirdId,
                            page:page,
                            size:5
                        },function(data){
                            if( data.response == 'success' ){
                                var length = data.data.content.length;
                                $("#itemContainer").empty();
                                for(var i = 0;i < length;i++){
                                    var html = get_html(1);
                                    $("#itemContainer").append(html);
                                }
                                //jPagesGet("holder","itemContainer",5);
                                if( length < 5 ){
                                    console.log(length);
                                    goodsSearch_appendHtml("itemContainer",page,length,data.data.content,5);
                                }
                                else{
                                    goodsSearch_appendHtml("itemContainer",page,data.data.size,data.data.content,5);
                                }
                            }
                        },'json');
                    }
                });
                $("#holder").children()[1].click();
            }
        },'json');
    });

    $(document).on("click","input[name='invenListCheck']",function(){
        if($(this).prop("checked")){
            $("#count").append($(this).parent().parent()[0].outerHTML);
        }
        else{
            $("#count").find("input[value='" + $(this).val() + "']").parent().parent().remove();
        }
    });

    $("#addInvenListConfirm").click(function(){
        //var length = $(".invenList dd").length;
        console.log(1);
        $(".add_invenList").empty();
        console.log($("#itemContainer"))
        console.log($("#count").children().length);
        for( var i = 0;i < $("#count").children().length; i++ ){
            var customerId = $( $("#count").children()[i]).find("input[type='checkbox']").val();
            var src = $( $("#count").children()[i]).find(".invenName").prev("abbr").children("img").attr("src")
            var invenName = $( $("#count").children()[i]).find(".invenName").html();
            var invenType = $( $("#count").children()[i]).find(".invenType").html();
            var invenTypeTitle = $( $("#count").children()[i]).find(".invenType").prop("title");
            var invenId = $( $("#count").children()[i]).find(".invenId").html();
            var invenBrand = $( $("#count").children()[i]).find(".invenBrand").html();
            var invenSeller = $( $("#count").children()[i]).find(".invenSeller").html();
            var html = "<dd>";
            html += '<input type="hidden" value="' + customerId + '"/>';
            html += '<abbr style="padding-top:0px;"><img src="'+ src +'" width="50" height="50" /></abbr>';
            html += '<abbr title="'+invenName+'" class="invenName">' + invenName + '</abbr>';
            html += '<abbr class="invenType" title="' + invenTypeTitle + '">' + invenType + '</abbr>';
            html += '<abbr title="'+ invenId +'" class="invenId">' + invenId + '</abbr>';
            html += '<abbr class="invenBrand">' + invenBrand + '</abbr>';
            html += '<abbr class="invenSeller">' + invenSeller + '</abbr>';
            html += '<abbr><input type="text" class="inventory" /></abbr>';
            html += '<abbr style="width:300px;"><input type="button" value="移除" name="remove"/></abbr></abbr>';
            html += '</dd>';
            if(invenName!==undefined) {
                $(".add_invenList").append(html);
            }
            $(".addInvenList").fadeOut();
        }

    });

    $(document).on("click","input[name='remove']",function(){
        $(this).parent().parent().remove();
        for( var i = 0;i < $("#count dd").length;i++ ){
            var invenName = $( $("#count dd")[i]).find(".invenName").html();
            var thisName = $(this).parent().parent().find(".invenName").html();
            if ( invenName == thisName ){
                $( $("#count dd")[i]).remove();
            }
        }
    });

    $("#addInvenConfirm").click(function(){
        coverHtml()
        var goodsJson = '{';
        var array = $(".invenList").find("dd");
        var flag = true;
        for( var i = 0;i < array.length; i++){
            var reg = /^[0-9]*$/;

            var id = $( array[i] ).find("input[type='hidden']").val();
            var inventory = $( array[i] ).find("input[type='text']").val();
            if( inventory != '' ){
                if( reg.test(inventory) ){
                    goodsJson += '"' + id + '":' + inventory + ',';
                }
                else{
                    flag = 'failInventory';
                }
            }
            else{
                flag = 'nullInventory';

            }
        }
        if( flag == 'nullInventory' ){
            discoverHtml()
            data_type_alert("库存不能为空",'error');
        }
        else if( flag == 'failInventory' ){
            discoverHtml()
            data_type_alert("库存添加失败 ",'error');
        }
        goodsJson = goodsJson.substring(0 , goodsJson.length - 1);
        goodsJson += '}';
        if( flag == true ){
            $.post("../web/api/inventory/addInventoryGoods",{
                goodsJson:goodsJson
            },function(data){
                if( data.response == 'success' ){
                    response_ensure_alert("success","操作成功",function(){
                        discoverHtml()
                        window.location.href = "InventoryManager"
                    },function(){
                        discoverHtml()
                        window.location.href = "InventoryManager"
                    })
                }
                else{
                    response_ensure_alert("success","操作失败,请重试",function(){
                        discoverHtml()
                    },function(){
                        discoverHtml()
                    })
                }
            },'json');
        }
    });

    $("#addInvenListSearch").click(function(){
        goodsInfoName = $("input[name='item_name']").val();
        goodsInfoItemNo = $("input[name='item_no']").val();
        brandId = $("#brand option:selected").val();
        thirdId = $("#brand option:selected").val();
        if( brandId == '全部' ){
            brandId = '';
        }
        if( thirdId == '全部' ){
            thirdId = '';
        }
        $.post("../web/api/goodsManager/getGoodsInfo",{
            goodsInfoName:goodsInfoName,
            goodsInfoItemNo:goodsInfoItemNo,
            brandId:brandId,
            thirdId:thirdId,
            page:1,
            size:5
        },function(data){
            if( data.response == 'success' ){
                var total = data.data.totalElements;
                for(var i = 0;i < total;i++){
                    var html = get_html(1);
                    $("#itemContainer").append(html);
                }
                jPagesGet("holder","itemContainer",5);
                //处理多余页
                var showElement = ( $("#holder a").length - 2 ) * 5 ;
                if( showElement >= ( data.data.totalElements + 5 ) ){
                    $( $("#holder a")[$("#holder a").length - 2]).remove();
                }
                $("#holder").find("a").css("display","inline-block");
                $("#holder").find("span").css("display","inline-block");
                $("#holder").find(".jp-hidden").css("display","none");
                $("#itemContainer").empty();

                $("#holder a").unbind("click");
                $("#holder a").click(function(){
                    $(".jp-current").next().css("display","inline-block");
                    $(".jp-current").next().next().css("display","inline-block");
                    $(".jp-current").next().next().css("display","inline-block");
                    var page = '';
                    if( $(this).html() == '＞' ){
                        if( parseInt($(".jp-current").html()) > 0 && parseInt($(".jp-current").html()) < ( $("#holder a").length - 2 ) ){
                            page = parseInt($(".jp-current").html()) + 1;
                        }
                        else{
                            page = parseInt($(".jp-current").html());
                        }
                    }
                    else if( $(this).html() == '＜' ){
                        if( parseInt($(".jp-current").html()) < ( $("#holder a").length - 1 ) ){
                            page = parseInt($(".jp-current").html()) - 1;
                        }
                        else{
                            page = parseInt($(".jp-current").html());
                        }
                    }
                    else{
                        page = $(this).html();
                    }
                    if( !isNaN(page) ){
                        $.post("../web/api/goodsManager/getGoodsInfo",{
                            goodsInfoName:goodsInfoName,
                            goodsInfoItemNo:goodsInfoItemNo,
                            brandId:brandId,
                            thirdId:thirdId,
                            page:page,
                            size:5
                        },function(data){
                            if( data.response == 'success' ){
                                var length = data.data.content.length;
                                $("#itemContainer").empty();
                                for(var i = 0;i < length;i++){
                                    var html = get_html(1);
                                    $("#itemContainer").append(html);
                                }
                                //jPagesGet("holder","itemContainer",5);
                                if( length < 5 ){
                                    console.log(length);
                                    goodsSearch_appendHtml("itemContainer",page,length,data.data.content,5);
                                }
                                else{
                                    goodsSearch_appendHtml("itemContainer",page,data.data.size,data.data.content,5);
                                }
                            }
                        },'json');
                    }
                });
                $("#holder").children()[1].click();
            }
        },'json');
    });
});






function ajaxPages(total,url,contentStr,target,type,perpage){
    for(var i = 0;i < total;i++){
        var html = get_html(type);
        $("#"+contentStr).append(html);
    }
    jPagesGet(target,contentStr);
    //处理多余页
    var showElement = ( $("#" + target + " a").length - 2 ) * perpage ;
    if( showElement >= ( total + perpage ) ){
        $( $("#" + target + " a")[$("#" + target + " a").length - 2]).remove();
    }

    $("#"+contentStr).empty();
    $("a").unbind("click");
    $("a").click(function(){
        var page = '';
        if( $(this).html() == '＜' ){
            if( parseInt($("#" + target + " .jp-current").html()) > 1 ){
                page = parseInt($("#" + target + " .jp-current").html()) - 1;
            }
            else{
                page = parseInt($("#" + target + " .jp-current").html());
            }
        }
        else if( $(this).html() == '＞' ){
            if( parseInt($("#" + target + " .jp-current").html()) < ( $("#" + target + " a").length - 2 ) ){
                page = parseInt($("#" + target + " .jp-current").html()) + 1;
            }
            else{
                page = parseInt($("#" + target + " .jp-current").html());
            }
        }
        else{
            page = $(this).html();
        }

        $.post(url,{
            "page":page,
            "size":perpage
        },function(data){
            if(data.response == 'success'){
                if( type == 0 ){
                    goodsList_appendHtml(contentStr,page,total,data.data.content,5);
                }
                else if( type == 1 ){
                    goodsChoose_appendHtml(contentStr,page,total,data.data.content,5);
                }
            }
        },'json');

    });
    $("#holder").children()[1].click();
}
function get_html(type){
    var html = '';
    if(type == 0){
        html = "<dd>";
        html += '<abbr style="padding-top:0px;"><img  width="50" height="50" /></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr class="invenSeller"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr><input type="text" class="inventory" /></abbr>';
        html += '<abbr style="width:300px;"><input type="button" value="移除" name="remove"/></abbr></abbr>';
        html += "</dd>";
    }
    else if(type == 1){
        html += "<dd>";
        html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" /></abbr>';
        html += '<abbr style="padding-top:0px;"><img width="50" height="50" /></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr class="invenSeller"></abbr>' ;
        html += '</abbr>';
        html += "</dd>";
    }
    return html;
}
function goodsList_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i<perpage;i++){
        var html = "<dd>";
        html += '<abbr class="invenName">' + 1 + '</abbr>';
        html += '<abbr class="invenType">' + 1 + '</abbr>';
        html += '<abbr class="invenId">' + 1 + '</abbr>';
        html += '<abbr class="invenBrand">' + 1 + '</abbr>';
        html += '<abbr class="invenSeller">' + 1 + '</abbr>';
        html += '<abbr class="invenBrand">' + 1 + '</abbr>';
        html += '<abbr><input type="text" class="inventory" />' + 1 + '</abbr>';
        html += '<abbr style="width:300px;"><input type="button" value="移除" name="remove"/></abbr></abbr>';
        html += "</dd>";
        $("#"+ str).append(html);
    }

}
function goodsChoose_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    var allCheckedCount = 0;
    console.log(allCheckedCount,perpage);
    for(var i=0;i<perpage;i++){
        var flag = 0;
        var html = "<dd>";
        var length = $("#count input[type='checkbox']").length;
        if( length > 0 ){
            for(var j = 0;j < length; j++){
                var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
                if( data[i].goodsInfoId == id ){
                    flag = 1;
                }
            }
        }

        if( flag ){
            allCheckedCount++;
            html += '<abbr style="width:30px;"><input name="invenListCheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
        }
        else{
            html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
        }

        html += '<abbr style="padding-top:0px;"><img src="'+data[i].goodsInfoImgId +'" width="50" height="50" /></abbr>';
        html += '<abbr title="'+ data[i].goodsInfoName  +'" class="invenName" style="padding-top: 0!important;">' + data[i].goodsInfoName + '</abbr>';

        var content = '';
        var h = '';
        var array = data[i].specString.split(",");
        for ( var j = 0; j < array.length;j++){
            var text= /(?:null)/;
            var flag = text.test(array[j]);
            if ( !flag ){
                h = array[j];
                if( array.length > 1 ){
                    h += '...';
                }
                break;
            }

        }
        for(var a = 0;a < array.length;a++){
            var text= /(?:null)/;
            var flag = text.test(array[a]);
            if ( !flag ){
                content += array[a] + '\n';
            }
        }
        html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';

        html += '<abbr title="'+ data[i].goodsInfoItemNo +'" class="invenId">' + data[i].goodsInfoItemNo + '</abbr>';
        html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
        html += '<abbr class="invenSeller">' + data[i].thirdName + '</abbr>' ;
        html += '</abbr>';
        html += "</dd>";

        $("#"+ str).append(html);
        if( allCheckedCount == perpage ){
            console.log('success');
            $("dt input[name='invenListCheck']").prop("checked","checked");
        }
        else{
            $("dt input[name='invenListCheck']").prop("checked","");
        }
    }

}
function goodsSearch_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i<perpage;i++){
        var flag = 0;
        var html = "<dd>";
        var length = $("#count input[type='checkbox']").length;
        if( length > 0 ){
            for(var j = 0;j < length; j++){
                var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
                if( data[i].goodsInfoId == id ){
                    flag = 1;
                }
            }
        }

        if( flag ){
            html += '<abbr style="width:30px;"><input name="invenListCheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
        }
        else{
            html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
        }

        html += '<abbr style="padding-top:0px;"><img src="'+data[i].goodsInfoImgId +'" width="50" height="50" /></abbr>';
        html += '<abbr title="'+data[i].goodsInfoName+'" class="invenName" style="padding-top: 0!important;">' + data[i].goodsInfoName + '</abbr>';

        var content = '';
        var h = '';
        var array = data[i].specString.split(",");
        for ( var j = 0; j < array.length;j++){
            var text= /(?:null)/;
            var flag = text.test(array[j]);
            if ( !flag ){
                h = array[j];
                if( array.length > 1 ){
                    h += '...';
                }
                break;
            }

        }
        for(var a = 0;a < array.length;a++){
            var text= /(?:null)/;
            var flag = text.test(array[a]);
            if ( !flag ){
                content += array[a] + '\n';
            }
        }
        html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
        html += '<abbr title="'+ data[i].goodsInfoItemNo +'" class="invenId">' + data[i].goodsInfoItemNo + '</abbr>';
        html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
        html += '<abbr class="invenSeller">' + data[i].thirdName + '</abbr>' ;
        html += '</abbr>';
        html += "</dd>";

        $("#"+ str).append(html);
    }

}

function jPagesGet(content,target){
    $("#"+content).jPages({
        containerID : target,
        perPage:5
    });

}