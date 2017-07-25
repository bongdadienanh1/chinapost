$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    $.datetimepicker.setLocale('ch');
    $("#chooseInven").click(function(){
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
        $.post("../web/api/goodsManager/getSettleGoodsByEnterpriseId",{
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
                        $.post("../web/api/goodsManager/getSettleGoodsByEnterpriseId",{
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
            html += '<abbr title="'+ invenName +'" class="invenName">' + invenName + '</abbr>';
            html += '<abbr class="invenType" title="' + invenTypeTitle + '">' + invenType + '</abbr>';
            html += '<abbr title="'+ invenId +'" class="invenId">' + invenId + '</abbr>';
            html += '<abbr title="'+invenBrand+'" class="invenBrand">' + invenBrand + '</abbr>';
            html += '<abbr title="'+ invenSeller +'" class="invenSeller">' + invenSeller + '</abbr>';
            html += '<abbr style="position: relative"><input type="text" class="inventory" /></abbr>';
            html += '<abbr style="position: relative"><input type="text" class="start-time datetimepicker_start" placeholder="生产时间" onkeyup="$(this).val(\'\')"/><a href="#"class="date_button date_start"/></a></abbr>';
            html += '<abbr style="position: relative"><input type="text" class="end-time datetimepicker_end" placeholder="到期时间" id="" onkeyup="$(this).val(\'\')"/><a href="#"class="date_button date_end"/></a></abbr>';
            html += '<abbr style="position: relative"><input type="text" class="warning-time"onkeyup="onlyNum(this)"></abbr>';
            html += '<abbr style="width:90px;"><input type="button" value="移除" name="remove"/></abbr></abbr>';
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
    //当填了生产日期和到期日期，自动填写保质期
    $(document).on('change','.end-time',function(){
            var stime = $(this).parent().prev().children('.start-time').val();
            var etime = $(this).val();
            var wtime = $(this).parent().next().children('.warning-time');
            if(stime != '' && etime != '') {
                wtime.val(DateDiff(stime, etime) + 1);
            }else if(etime!='' && wtime.val()!='' && stime==''){
                $(this).parent().prev().children('.start-time').val((getNewDay(etime, Number(wtime.val()) - 1,'start')));
            }
    })
    //当填写了到期日期和保质期，自动填写生产日期
    $(document).on('change','.start-time',function(){
        var etime = $(this).parent().next().children('.end-time').val();
        var stime = $(this).val();
        var wtime = $(this).parent().next().next().children('.warning-time');
        if(stime != '' && etime != '') {
            wtime.val(DateDiff(stime, etime) + 1);
        }else if(stime !='' && wtime.val()!='' && etime==''){
            $(this).parent().next().children('.end-time').val((getNewDay(stime, Number(wtime.val()) - 1)));
        }
    })

    $(document).on('change','.warning-time',function(){
        var etime = $(this).parent().prev().children('.end-time');
        var wtime = $(this);
        var stime = $(this).parent().prev().prev().children('.start-time');
        if(wtime.val() != '' && etime.val() =='' && stime.val() != ''){
            etime.val(getNewDay(stime.val(), Number(wtime.val()) - 1));
        }else if(wtime.val() != '' && stime.val() ==''&& etime.val() !=''){
            stime.val(getNewDay(etime.val(), Number(wtime.val()) - 1,'start'));
        }
    })



    $("#addInvenConfirm").click(function(){
        coverHtml()
        var goodsJson = '';
        var array = $(".invenList").find("dd");
        var flag = true;
        var type1 = true;
        var type2 = true;
        var type3 = true;
        var type4 = true;
        $('.warn-info').remove();
        goodsJson += '[';
        for( var i = 0;i < array.length; i++){
            //var reg = /^[1-9]*$/;
            var reg =  /^\+?[1-9]\d*$/;
            var id = $( array[i] ).find("input[type='hidden']").val();
            var inventory = $( array[i] ).find("input[type='text']");
            var story = array.eq(i).find(".inventory");
            var stime = array.eq(i).find(".datetimepicker_start");
            var etime = array.eq(i).find(".datetimepicker_end");
            var wtime = array.eq(i).find(".warning-time");
            goodsJson += '{';
            //生产日期小于到期日期报错
            if(stime.val() != '' && etime.val() != '' && stime.val() > etime.val() ){
                //var htm = '';
                stime.css('border-color','#f56b6b');
                type1 = false;
                break;
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 18px;left: 0px">请重新填写</span>';
                //stime.parent().append(htm);
            }
            //只填写了保质期报错,报没有填写生产日期或到期日期的错
            if( wtime.val() != '' && stime.val() == '' && etime.val() == ''){
                //var htm = '';
                stime.css('border-color','#f56b6b');
                type2 = false;
                break;
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 18px;left: 0px">请填写生产日期或到期日期！</span>';
                //stime.parent().append(htm);
            }
            //开始日期不能大于当前时间
            if(stime.val()!='' && (stime.val() > new Date().Format("yyyy/MM/dd"))){
                //var htm = '';
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 8px;left: 16px">生产日期不能大于当前时间</span>';
                //stime.parent().append(htm);
                stime.css('border-color','#f56b6b');
                type3 = false;
                break;
            }
            if(stime.val() != '' && etime.val() != '' && (DateDiff(stime.val(), etime.val()) + 1) != wtime.val()){
                //var htm = '';
                //htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 8px;left: 16px">生产日期不能大于当前时间</span>';
                //stime.parent().append(htm);
                wtime.css('border-color','#f56b6b');
                type4 = false;
                break;
            }
            if( stime.val() != ''){
                goodsJson += '"productTime":' + '"' +mydate(stime.val()) + '"' +',';
            }
            if( wtime.val() != ''){
                goodsJson += '"endWarnDay":' + wtime.val() +',';
            }
            if( etime.val() != ''){
                goodsJson += '"endTime":' + '"' + mydate(etime.val()) + '"' +',';
           }


            if( inventory.val() != '' ){
                if( reg.test(inventory.val()) ){
                    //goodsJson += '"' + id + '":' + inventory + ',';
                    goodsJson += '"amount":'+ inventory.val() +',';
                    goodsJson += '"goodsInfoId":'+ id +',';

                }
                else{
                    flag = 'failInventory';
                }
            }
            else{
                var htm = '';
                htm += '<span class="warn-info" style="color: red;position: absolute;font-size: 12px;top: 58px;left: 10px">库存不能为空</span>';
                story.parent().append(htm);
                flag = 'nullInventory';
            }
            goodsJson = goodsJson.substring(0 , goodsJson.length - 1);
            goodsJson += '},';
        }
        //if( flag == 'nullInventory' ){
        //    discoverHtml()
        //    data_type_alert("库存不能为空",'error');
        //}
        //else if( flag == 'failInventory' ){
        //    discoverHtml()
        //    data_type_alert("库存添加失败 ",'error');
        //}
        goodsJson = goodsJson.substring(0 , goodsJson.length - 1);
        goodsJson += ']';
        if( flag == 'nullInventory' ){
            discoverHtml()
            data_type_alert("库存不能为空",'error');
        }
        else if( flag == 'failInventory' ){
            discoverHtml()
            data_type_alert("库存添加失败 ",'error');
        }else if(!type1){
            discoverHtml();
            data_type_alert("生产日期应该小于到期日期",'error');
        }else if(!type2){
            discoverHtml();
            data_type_alert("请填写生产日期或者到期日期",'error');
        }else if(!type3){
            discoverHtml();
            data_type_alert("生产日期不能大于当前时间",'error');
        }else if(!type4){
            discoverHtml();
            data_type_alert("保质期不合法",'error');
        }else if( flag == true ){
            $.post("../web/api/inventory/addGoods",{
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
        $.post("../web/api/goodsManager/getSettleGoodsByEnterpriseId",{
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
                        $.post("../web/api/goodsManager/getSettleGoodsByEnterpriseId",{
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

    //日历控件
    $(document).on('click','.date_start',function(e){
        $(this).prev().datetimepicker({
            step:5,
            lang:'ch',
            format:'Y/m/d',
            timepicker:false
        });
        $(this).prev().trigger("focus");
    });
    $(document).on('blur','.datetimepicker_start',function(){
        $(this).datetimepicker('destroy');
    });

    $(document).on('click','.date_end',function(e){
        $(this).prev().datetimepicker({
            step:5,
            lang:'ch',
            format:'Y/m/d',
            timepicker:false
        });
        $(this).prev().trigger("focus");
    })
    $(document).on('blur','.datetimepicker_end',function(){
        $(this).datetimepicker('destroy');
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

        html += '<abbr class="invenId">' + data[i].goodsInfoItemNo + '</abbr>';
        html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
        html += '<abbr class="invenSeller">' + handleUndefined(data[i].thirdName) + '</abbr>' ;
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
        html += '<abbr title="' + data[i].goodsInfoItemNo + '" class="invenId">' + data[i].goodsInfoItemNo + '</abbr>';
        html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
        html += '<abbr class="invenSeller">' + handleUndefined(data[i].enterpriseName) + '</abbr>' ;
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

//将时间格式yyyy/mm/dd转换为yyyy-mm-dd 00:00:00
function mydate(str)
{
    var n = str.replace(/\//g,'-');
    n += ' 00:00:00';
    return n;
}
//将时间格式yyyy/mm/dd转换为yyyy-mm-dd
//function mydate2(str)
//{
//    var n = str.replace(/\//g,'-');
//    return n;
//}

function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006/12/18格式
    var  aDate,  oDate1,  oDate2,  iDays
    aDate  =  sDate1.split("/")
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
    aDate  =  sDate2.split("/")
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
    return  iDays
}

//日期加上天数得到新的日期
//dateTemp 需要参加计算的日期，days要添加的天数，返回新的日期，日期格式：YYYY-MM-DD
function getNewDay(dateTemp, days,type) {
    var dateTemp = dateTemp.split("/");
    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式
    var millSeconds;
    if(type == 'start'){
        millSeconds = Math.abs(nDate) - (days * 24 * 60 * 60 * 1000);
    }else {
        millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
    }
    var rDate = new Date(millSeconds);
    var year = rDate.getFullYear();
    var month = rDate.getMonth() + 1;
    if (month < 10) month = "0" + month;
    var date = rDate.getDate();
    if (date < 10) date = "0" + date;
    return (year + "/" + month + "/" + date);
}
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
