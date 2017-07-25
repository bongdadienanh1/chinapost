function ajaxPages(url,contentStr,target,type,perpage,reason,batchId,array,callbackChecked){
    var code = '';//单据编号
    var goodsInfoName = '';
    var goodsInfoItemNo = '';//货品编号
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    var lowPrice = '';
    var highPrice = '';
    var start = '';

    var end = '';
    var status = '';
    var creator = '';
    var billType = '';
    var billStatus = '';
    var idCard = '';
    var enterpriseId = '';
    var arr = '';
    var typeName = "";
    var batchCode = "";

    var onlineShow ="";
    var valetShow ="";
    var goodsInfoType = "";
    var date = "";
    var orderStartTime = "";
    var orderEndTime = "";
    var readyStartTime = "";
    var readyEndTime = "";
    var warehouseStartTime = "";
    var warehouseEndTime = "";

    var billStatuss = "";
    var thirdEnterpriseId = "";
    var otStartTime = "";
    var otEndTime = "";
    var startTime = "";
    var endTime = "";//36

    var overday = "";//到期提醒类型
    var day = "";//到期提醒天数
    var startDate = "";//入库开始时间
    var endDate = "";//入库结束时间
    var endStartDate = "";//到期开始时间
    var endEndDate = "";//到期结束时间


    if( array != undefined ){
        goodsInfoName = array[0];
        goodsInfoItemNo = array[1];
        brandId = array[2];
        goodsInfoAdded = array[3];
        typreId = array[4];
        thirdId = array[5];
        lowPrice = array[6];
        highPrice = array[7];
        start = array[8];
        end = array[9];
        status = array[10];
        code = array[11];
        creator = array[12];
        billType = array[13];
        billStatus = array[14];
        idCard = array[15];
        enterpriseId = array[16];
        typeName = array[17];
        batchCode = array[18];
        onlineShow = array[19];
        valetShow = array[20];
        goodsInfoType = array[21];
        date = array[22];
        orderStartTime = array[23];
        orderEndTime = array[24];
        readyStartTime = array[25];
        readyEndTime = array[26];
        warehouseStartTime = array[27];
        warehouseEndTime = array[28];
        billStatuss = array[29];
        thirdEnterpriseId = array[30];
        otStartTime = array[31];
        otEndTime = array[32];
        startTime = array[33];
        endTime = array[34];

        overday = array[35];
        day = array[36];
        startDate = array[37];
        endDate = array[38];
        endStartDate = array[39];
        endEndDate = array[40];





    }
    $.post(url,{
        "code":code,//单据编号
        "goodsInfoName":goodsInfoName,//货品编号
        "goodsInfoItemNo":goodsInfoItemNo,
        "brandId":brandId,
        "goodsInfoAdded":goodsInfoAdded,
        "typeId":typreId,
        "thirdId":thirdId,
        "batchId":batchId,
        "lowPrice":lowPrice,
        "highPrice":highPrice,
        "start":start,
        "end":end,
        "status":status,
        "creatorName":creator,
        "billStatus":billStatus,
        "billType":billType,
        "idCard":idCard,
        "enterpriseId":enterpriseId,
        "type":typeName,
        "batchCode":batchCode,
        "onlineShow":onlineShow,
        "valetShow":valetShow,
        "goodsInfoType":goodsInfoType,
        "date":date,
        "orderStartTime":orderStartTime,
        "orderEndTime":orderEndTime,
        "readyStartTime":readyStartTime,
        "readyEndTime":readyEndTime,
        "warehouseStartTime":warehouseStartTime,
        "warehouseEndTime":warehouseEndTime,
        "billStatuss":billStatuss,
        "thirdEnterpriseId":thirdEnterpriseId,
        "otStartTime":otStartTime,
        "otEndTime":otEndTime,
        "startTime":startTime,
        "endTime":endTime,

        "overday":overday,
        "day":day,
        "startDate":startDate,
        "endDate":endDate,
        "endStartDate":endStartDate,
        "endEndDate":endEndDate,
        "page":1,
        "size":perpage
    },function(data){
        if( data.response == 'success' ){
            //账单管理额外功能
            if( type == 1 || type == 'bill_second'){
                callbackChecked();
            }

            else if( type == 'ubaosendForm' ){
                callbackChecked(data);
                data = data.data;
            }


            appendHtml(contentStr,type,data.data.content,reason,callbackChecked);
            if(type == "memberBaseDataform"){
                discoverHtml()
                $(".loading").hide()
            }
            if(type == "changeHistory" || type == "supplyDetail" || type=="supplyDetailForm" || type == "deliveryDetail"|| type == "supplyCount"|| type == "memberBaseDataform"){
                var i = 0;
                while (i<$(".InventName").length) {
                    var j = i + 1
                    var num = 1
                    while (j <($(".InventName").length + 1)) {
                        if (j < $(".InventName").length && $(".InventName").eq(i).attr("data-id") == $(".InventName").eq(j).attr("data-id") ) {
                            $(".InventName").eq(j).addClass("removeInvent")
                            num++;
                            j++;
                        } else {
                            $(".InventName").eq(i).prop("rowspan",num)
                            break;
                        }
                    }
                    i = j;
                }
                $(".removeInvent").remove()
            }
            if( type == "supplyDetail"|| type=="supplyDetailForm"|| type == "deliveryDetail"|| type == "memberBaseDataform"){
                var i = 0;
                while (i<$(".InventAddress").length) {
                    var j = i + 1
                    var num = 1
                    while (j <($(".InventAddress").length + 1)) {
                        if (j < $(".InventAddress").length && $(".InventAddress").eq(i).attr("data-id") == $(".InventAddress").eq(j).attr("data-id") ) {
                            $(".InventAddress").eq(j).addClass("removeInvent2")
                            num++;
                            j++;
                        } else {
                            $(".InventAddress").eq(i).prop("rowspan",num)
                            break;
                        }
                    }
                    i = j;
                }
                $(".removeInvent2").remove()
            }
            $("#" + target).pagination(data.data.totalElements, {
                prev_text: "＜",
                next_text: "＞",
                num_edge_entries: 2,
                num_display_entries: 8,
                items_per_page:perpage,
                //回调
                callback: function(page){
                    if(type == "memberBaseDataform"){
                        $(".loading").show()
                        coverHtml()
                    }
                    $.post(url,{
                        "goodsInfoName":array[0],
                        "goodsInfoItemNo":array[1],
                        "brandId":array[2],
                        "goodsInfoAdded":array[3],
                        "typeId":array[4],
                        "thirdId":array[5],
                        "batchId":batchId,
                        "lowPrice":array[6],
                        "highPrice":array[7],
                        "start":array[8],
                        "end":array[9],
                        "status":array[10],
                        "code":array[11],
                        "creatorName":array[12],
                        "billStatus":array[14],
                        "billType":array[13],
                        "idCard":array[15],
                        "enterpriseId":array[16],
                        "type":array[17],
                        "batchCode":array[18],
                        "onlineShow":array[19],
                        "valetShow":array[20],
                        "goodsInfoType":array[21],
                        "date":array[22],
                        "orderStartTime":array[23],
                        "orderEndTime":array[24],
                        "readyStartTime":array[25],
                        "readyEndTime":array[26],
                        "warehouseStartTime":array[27],
                        "warehouseEndTime":array[28],
                        "billStatuss":array[29],
                        "thirdEnterpriseId":array[30],
                        "otStartTime":array[31],
                        "otEndTime":array[32],
                        "startTime":array[33],
                        "endTime":array[34],
                        "overday":overday,
                        "day":day,
                        "startDate":startDate,
                        "endDate":endDate,
                        "endStartDate":endStartDate,
                        "endEndDate":endEndDate,
                        "page":page+1,
                        "size":perpage
                    },function(data){
                        if(data.response == 'success'){
                            if( type == 'ubaosendForm' ){
                                data = data.data;
                            }
                            if( typeof callbackChecked == 'function' && type != 'ubaosendForm'){
                                callbackChecked(page);
                            }
                            appendHtml(contentStr,type,data.data.content,reason,callbackChecked);
                            if(type == "memberBaseDataform"){
                                discoverHtml()
                                $(".loading").hide()
                            }
                            if(type == "changeHistory" || type == "supplyDetail"|| type=="supplyDetailForm"|| type == "deliveryDetail"|| type == "supplyCount"|| type == "memberBaseDataform"){
                                var i = 0;
                                while (i<$(".InventName").length) {
                                    var j = i + 1
                                    var num = 1
                                    while (j <($(".InventName").length + 1)) {
                                        if (j < $(".InventName").length && $(".InventName").eq(i).attr("data-id") == $(".InventName").eq(j).attr("data-id")) {
                                            $(".InventName").eq(j).addClass("removeInvent")
                                            num++;
                                            j++;
                                        } else {
                                            $(".InventName").eq(i).prop("rowspan",num)
                                            break;
                                        }
                                    }
                                    i = j;
                                }
                                $(".removeInvent").remove()
                            }
                            if( type == "supplyDetail"|| type=="supplyDetailForm" || type == "deliveryDetail" || type == "memberBaseDataform"){
                                var i = 0;
                                while (i<$(".InventAddress").length) {
                                    var j = i + 1
                                    var num = 1
                                    while (j <($(".InventAddress").length + 1)) {
                                        if (j < $(".InventAddress").length && $(".InventAddress").eq(i).attr("data-id") == $(".InventAddress").eq(j).attr("data-id") ) {
                                            $(".InventAddress").eq(j).addClass("removeInvent2")
                                            num++;
                                            j++;
                                        } else {
                                            $(".InventAddress").eq(i).prop("rowspan",num)
                                            break;
                                        }
                                    }
                                    i = j;
                                }
                                $(".removeInvent2").remove()
                            }
                        }else{
                            if(type == "memberBaseDataform"){
                                discoverHtml()
                                $(".loading").hide()
                            }
                        }
                    },'json');
                }
            });
        }
        else{
            if(type == "memberBaseDataform"){
                discoverHtml()
                $(".loading").hide()
            }
        }
    },'json');
}

function appendHtml(str,type,data,reason,callbackChecked) {
    $("#" + str).empty();
    data.map(function(o){
        console.log(o);
        var html = '';
        var htm = '';
        html += "<dd>";
        htm += '<li>';
        if (type == 0) {
            html += '<abbr class="myBillListCreattime">' + handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</abbr>';
            html += '<abbr class="myBillListAccNum">' + handleUndefined(o.code) + '</abbr>';
            if (reason == '网点订单补贴'||reason == '订单退款返还'){
                html += '<abbr style="display: none;" class="myBillListOrderCode">' + handleUndefined(o.code) + '</abbr>';
                //html += '<abbr style="display: none;" class="myBillListUserName">' + handleUndefined(o.customerInfo.name) + '</abbr>';
                //html += '<abbr style="display: none;" class="myBillListIdcard">' + handleUndefined(o.customerInfo.idCard) + '</abbr>';
            }
            if (reason == '上级财富分配'||reason == '充值列表'){
                html += '<abbr style="display: none;" class="myBillListTarget">' + handleUndefined(o.outName) + '</abbr>';
            }
            html += '<abbr class="myBillListState">已完成</abbr>';
            if (o.fee != undefined) {
                html += '<abbr class="myBillListMoneySum">' + addCommas(o.fee,2).replace("-","") + '</abbr>';
            }
            else {
                html += '<abbr class="myBillListMoneySum">' + addCommas(o.price,2).replace("-","") + '</abbr>';
            }
            if (reason == '邮豆发放'||reason == '邮豆扣减') {
                if (o.ucoinCount !== undefined) {
                    html += '<abbr class="myBillListNumSum">' + o.ucoinCount + '</abbr>';
                } else {
                    html += '<abbr class="myBillListNumSum">1</abbr>';
                }
            } else if (reason == '财富分配') {
                if (o.count !== undefined) {
                    html += '<abbr class="myBillListNumSum">' + o.count + '</abbr>';
                } else {
                    html += '<abbr class="myBillListNumSum">1</abbr>';
                }
            }
            else {
                html += '<abbr class="myBillListNumSum">1</abbr>';
            }
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillListReason">' + reason + '</abbr>';
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillLisBusinessType">' + handleUndefined(o.sendType) + '</abbr>';
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillLisPayTime"></abbr>';
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillListNote">' + handleUndefined(o.remark) + '</abbr>';
            ////if (reason == '邮豆扣减') {
            ////    html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTarget">' + handleUndefined(o.customerInfo.idCard) + '</abbr>';
            ////    html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTargetName">' + handleUndefined(o.customerInfo.name) + '</abbr>';
            ////}
            ////else {
            //    html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTarget">' + handleUndefined(o.inName) + '</abbr>';
            ////}

            html += '<abbr style="width:0px; overflow:hidden;" class="myBillListBatchId">' + o.id + '</abbr>';
            html += '<abbr class="myBillListCheck"><input type="button" value="查看详情" class="detail" /></abbr>';
        }
        else if (type == 1) {
            html += '<abbr>' + o.inName + '</abbr>';
            html += '<abbr>' + addCommas(o.fee,2) + '</abbr>';
        }
        else if (type == 'bill_second') {
            html += '<abbr>' + o.customerInfo.idCard + '</abbr>';
            html += '<abbr>' + handleUndefined(o.customerInfo.name) + '</abbr>';
            html += '<abbr>' + addCommas(o.price,2).replace("-","") + '</abbr>';
        }
        else if (type == 2) {
            html += '<abbr class="invenName">' + 1 + '</abbr>';
            html += '<abbr class="invenType">' + 1 + '</abbr>';
            html += '<abbr class="invenId">' + 1 + '</abbr>';
            html += '<abbr class="invenBrand">' + 1 + '</abbr>';
            html += '<abbr class="invenSeller">' + 1 + '</abbr>';
            html += '<abbr class="invenBrand">' + 1 + '</abbr>';
            html += '<abbr><input type="text" class="inventory" />' + 1 + '</abbr>';
            html += '<abbr style="width:300px;"><input type="button" value="移除" /></abbr></abbr>';
        }
        else if (type == 3) {
            html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" /></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
            html += '<abbr class="invenName">' + o.goodsName + '</abbr>';
            html += '<abbr class="invenType">' + o.specString + '</abbr>';
            html += '<abbr class="invenId">' + o.goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(o.goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller">' + o.goodsMerchants + '</abbr>';
            html += '</abbr>';
        }
        else if (type == 4) {
            var status = handleReqStatus(o.status);
            html += '<input type="hidden" value="' + o.id + '">';
            html += '<abbr class="invenName">' + handleDate_prev(new Date(o.createTime)) + '  ' + handleDate_next(new Date(o.createTime)) + '</abbr>';
            html += '<abbr class="invenType">' + o.code + '</abbr>';
            html += '<abbr class="invenId">' + status + '</abbr>';
            html += '<abbr class="invenNum">' + addCommas(o.fee,2) + '</abbr>';
            html += '<abbr class="invenNumUsed">' + o.remark + '</abbr>';
            html += '<abbr class="invenNumUsed">' + addCommas(o.payFee,2) + '</abbr>';
        }
        else if (type == 5) {
            var status = handleTodoStatus(o.status);
            html += '<input type="hidden" value="' + o.id + '">';
            html += '<abbr style="width:180px;" class="invenName">' + o.enterpriseName + '</abbr>';
            html += '<abbr class="invenType">' + handleDate_prev(new Date(o.createTime)) + '  ' + handleDate_next(new Date(o.createTime)) + '</abbr>';
            html += '<abbr class="invenId">' + o.code + '</abbr>';
            html += '<abbr class="invenNum">' + status + '</abbr>';
            html += '<abbr class="invenNumUsed">' + addCommas(o.fee,2) + '</abbr>';
            html += '<abbr class="invenNumUsed">' + o.remark + '</abbr>';
            if (status == "待审核") {
                html += '<abbr class="invenNumUsed">';
                html += '<select class="myRequestTodoSelect">';
                html += '<option selected="selected" value="0">' + '可选' + '</option>';
                html += '<option value="1">' + '同意' + '</option>';
                html += '<option value="2">' + '拒绝' + '</option>';
                html += '</select>';
                html += '</abbr>';
            } else if (status == "已审核") {
                html += '<abbr class="invenNumUsed">';
                html += '<select class="myRequestTodoSelect">';
                html += '<option selected="selected" value="0">' + '可选' + '</option>';
                html += '<option value="3">' + '支付' + '</option>';
                html += '</select>';
                html += '</abbr>';
            }
        }
        else if (type == 6) {
            var status = handleReqStatus(o.status);
            html += '<input type="hidden" value="' + o.id + '">';
            html += '<abbr class="invenName">' + o.enterpriseName + '</abbr>';
            html += '<abbr class="invenType">' + handleDate_prev(new Date(o.createTime)) + '  ' + handleDate_next(new Date(o.createTime)) + '</abbr>';
            html += '<abbr class="invenId">' + o.code + '</abbr>';
            html += '<abbr class="invenNum">' + status + '</abbr>';
            html += '<abbr class="invenNumUsed">' + addCommas(o.fee,2) + '</abbr>';
            html += '<abbr>' + o.remark + '</abbr>';
            html += '<abbr class="invenNumUsed">' + addCommas(o.payFee,2) + '</abbr>';
        }
        else if (type == 7) {
            var operator = 1;
            var onlineShop = 1;
            var userShop = 1;
            if (typeof(o.goodsInfoAdded) != 'undefined') {
                if (o.goodsInfoAdded == "0") {
                    operator = '否';
                }
                if (o.goodsInfoAdded == "1") {
                    operator = '是'
                }
            }else{
                operator = '否';
            }
            console.log(o.onlineShow)
            if (typeof(o.onlineShow) != 'undefined') {
                if (o.onlineShow) {
                    onlineShop = '是';
                }else{
                    onlineShop = '否';
                }
            }else{
                onlineShop = '否';
            }
            if (typeof(o.valetShow) != 'undefined') {
                if (o.valetShow) {
                    userShop = '是';
                }else{
                    userShop = '否';
                }
            }else{
                userShop = '否';
            }

            var content = '';
            var h = '';
            var array = o.specString.split(",");
            for (var j = 0; j < array.length; j++) {
                var text = /(?:null)/;
                var flag = text.test(array[j]);
                if (!flag) {
                    h = array[j];
                    if (array.length > 1) {
                        h += '...';
                    }
                    break;
                }

            }
            for (var a = 0; a < array.length; a++) {
                var text = /(?:null)/;
                var flag = text.test(array[a]);
                if (!flag) {
                    content += array[a] + '\n';
                }
            }

            html += '<abbr style="width:30px;"><input name="itemsListCheck" type="checkbox" /></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="' + o.goodsInfoImgId + '" width="50" height="50" /></abbr>';
            html += '<abbr title="'+ o.goodsInfoName +'">' + o.goodsInfoName + '</abbr>';
            html += '<abbr style="margin-left:15px;" title="' + content + '">' + h + '</abbr>';
            html += '<abbr title="'+ o.goodsInfoItemNo +'">' + o.goodsInfoItemNo + '</abbr>';
            html += '<abbr title="'+ addCommas2(o.goodsInfoPreferPrice,2) +'">' + addCommas2(o.goodsInfoPreferPrice,2) + '</abbr>';
            html += '<abbr title="'+addCommas2(o.goodsInfoSettlePrice,2)+'">' + addCommas2(o.goodsInfoSettlePrice,2) + '</abbr>';
            html += '<abbr>';
                if(isTop == 1){
                    console.log('aaa');
                        if( operator == "是"){
                            html += '<input style="background:#54a6de" value="' + operator + '" type="button" class="inOut" />';
                        }else{
                            html += '<input style="background:#ff3300" value="' + operator + '" type="button" class="inOut" />';
                        }


                }else{
                    if(operator == "是"){
                        html += '<input style="color:#333;width:25px;height:25px;border:none;margin-left:30px;background:#fff;" value="' + operator + '" type="button" />';
                    }else{
                        html += '<input style="color:#333;width:25px;height:25px;border:none;margin-left:30px;background:#fff;" value="' + operator + '" type="button" />';
                    }

                }


            html += '<input class="itemsInfoId" type="hidden" value="' + o.goodsInfoId + '"/>';
            html += '</abbr>';

            html += '<abbr>';

                if(isTop == 1){
                    console.log('aaa');

                        if(onlineShop == "是"){
                            html += '<input style="background:#54a6de" value="' + onlineShop + '" type="button" class="onlineinOut" />';
                        }else{
                            html += '<input style="background:#ff3300" value="' + onlineShop + '" type="button" class="onlineinOut" />';
                        }

                }else{
                    if(onlineShop == "是"){
                        html += '<input style="color:#333;width:25px;height:25px;border:none;margin-left:30px;background:#fff;" value="' + onlineShop + '" type="button" />';
                    }else{
                        html += '<input style="color:#333;width:25px;height:25px;border:none;margin-left:30px;background:#fff;" value="' + onlineShop + '" type="button" />';
                    }

                }


            html += '<input type="hidden" value="' + o.goodsInfoId + '"/>';
            html += '</abbr>';


            html += '<abbr>';

                if(isTop == 1){
                    console.log('aaa');

                        if( userShop == "是"){
                            html += '<input style="background:#54a6de" value="' + userShop + '" type="button" class="userinOut" />';
                        }else{
                            html += '<input style="background:#ff3300" value="' + userShop + '" type="button" class="userinOut" />';
                        }

                }else{
                    if( userShop == "是"){
                        html += '<input style="color:#333;width:25px;height:25px;border:none;margin-left:30px;background:#fff;" value="' + userShop + '" type="button" />';
                    }else{
                        html += '<input style="color:#333;width:25px;height:25px;border:none;margin-left:30px;background:#fff;" value="' + userShop + '" type="button" />';
                    }

                }


            html += '<input type="hidden" value="' + o.goodsInfoId + '"/>';
            html += '</abbr>';

            html += '<abbr title="'+ handleUndefined( o.goodsInfoTypeName) +'">' + handleUndefined( o.goodsInfoTypeName) + '</abbr>';
            html += '<abbr title="'+ handleUndefined(o.goodsBrand)+'">' + handleUndefined(o.goodsBrand) + '</abbr>';
            html += '<abbr title="'+ handleUndefined(o.thirdName) +'">' + handleUndefined(o.thirdName) + '</abbr>';
            html += '<abbr class="itemsDetail">查看详情</abbr>';
            if (typeof callbackChecked == 'function') {
                var flag = callbackChecked();
            }
        }
        else if (type == 8) {
            var ieEnd = getCurrentEnterpriseInfo().getIsEnd()
            html += '<input type="hidden" class="goodsInfoId" value="' + o.goodsInfoId + '">'
            html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" class="accountNumber"/></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="' + o.goodsImg + '" width="50" height="50" /></abbr>';
            html += '<abbr title="'+ o.goodsInfoName  +'" class="invenName">' + o.goodsInfoName + '</abbr>';

            var content = '';
            var h = '';
            var array = o.specString.split(",");
            for (var j = 0; j < array.length; j++) {
                var text = /(?:null)/;
                var flag = text.test(array[j]);
                if (!flag) {
                    h = array[j];
                    if (array.length > 1) {
                        h += '...';
                    }
                    break;
                }

            }
            for (var a = 0; a < array.length; a++) {
                var text = /(?:null)/;
                var flag = text.test(array[a]);
                if (!flag) {
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
            html += '<abbr class="invenId" title="'+ o.goodsNumber +'">' + o.goodsNumber + '</abbr>';
            if(  o.goodsInfoType == "0"){
                html += '<abbr>集采商品</abbr>';
            }else if( o.goodsInfoType == "1"){
                html += '<abbr>自购商品</abbr>';
            }else{
                html += '<abbr></abbr>';
            }

            html += '<abbr class="invenNum">' + o.inventory + '</abbr>';
            html += '<abbr class="invenNumUsed">' + o.available + '</abbr>';
            html += '<abbr class="warningVal">' + handleUndefined(o.goodsInfoWarning) + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(o.goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller">' + o.goodsMerchants + '</abbr>';
            html += '<abbr style="">';
            html += '<select class="invenListSelect">';
            html += '<option value="0" selected="selected">可选</option>';
            if($('#isTop').val() == 'true'){
                html += '<option class="changeInventory" value="1">修改库存</option>';
            }
            html += '<option value="2">删除</option>';
            html += '<option value="3">预警设置</option>';
            html += '</select></abbr>';
        }
        else if (type == 9) {

            htm += '<abbr class="showImg"><input type="hidden" value="' + o.goodsInfoId + '"><img src="' + o.goodsInfoImgId + '" width="100" height="99" /></abbr>';
            htm += '<abbr class="imglist">';
            htm += '<i class="changeLeft"></i>';
            htm += '<s><span>';
            //

            o.images.map(function (img) {
                htm += '<img src="' + img.imageInName + '" width="100" height="99" />';
                htm += '<input type="hidden" class="imageInName" value="' + img.imageInName + '"> ';
            })

            htm += '</span></s>';
            htm += '<i class="changeRight"></i>';
            htm += '</abbr>';
            htm += '<abbr class="moneyList"><span>' + addCommas(o.goodsInfoPreferPrice,2) + '</span>邮豆<i></i></abbr>';
            htm += '<abbr title="'+ o.goodsInfoName +'">' + o.goodsInfoName + '</abbr>';
        }
        else if (type == 10) {
            //var flag = 0;
            //var length = $("#count input[type='checkbox']").length;
            //if( length > 0 ){
            //    for(var j = 0;j < length; j++){
            //        var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
            //        if( o.goodsId == id ){
            //            flag = 1;
            //        }
            //    }
            //}
            //
            //if( flag ){
            //    html += '<abbr style="width:30px;"><input name="invenListCheck" checked="checked" type="checkbox" value="' + o.goodsId + '"/></abbr>';
            //}
            //else{
            //    html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" value="' + o.goodsId + '"/></abbr>';
            //}

            html += '<abbr style="width:30px;"><input name="invenListCheck_add" type="checkbox" value="' + o.goodsId + '"' + '/></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
            html += '<abbr class="invenName" style="padding-top: 0!important;">' + o.goodsName + '</abbr>';

            var content = '';
            var h = '';
            var array = o.specString.split(",");
            for (var j = 0; j < array.length; j++) {
                var text = /(?:null)/;
                var flag = text.test(array[j]);
                if (!flag) {
                    h = array[j];
                    if (array.length > 1) {
                        h += '...';
                    }
                    break;
                }

            }
            for (var a = 0; a < array.length; a++) {
                var text = /(?:null)/;
                var flag = text.test(array[a]);
                if (!flag) {
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';

            html += '<abbr class="invenId">' + o.goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(o.goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller">' + o.goodsMerchants + '</abbr>';
            html += '</abbr>';
        }
        else if (type == 11) {
            console.log(11)
            var ieEnd = getCurrentEnterpriseInfo().getIsEnd()
            var bill_type = handleInventoryBill(o.billType);
            html += '<input type="hidden" name="billId" class="billId" value="' + o.billId + '">';
            html += '<input type="hidden" name="id" value="' + o.id + '">';
            html += '<input type="hidden" class="vocherState" value="' + bill_type + '">';
            html += '<abbr style="width:20px;"><input name="invenListCheck" type="checkbox" class="accountNumber"/></abbr>';
            html += '<abbr style="width:180px;" title="'+ o.code  +'" class="vocherCode">' + o.code + '</abbr>';//入库单据
            html += '<abbr style="padding-top:0px;"><img src="' + o.goodInfoImgId + '" width="50" height="50" /></abbr>';//图片
            html += '<abbr title="'+ o.goodINfoName  +'" class="invenName">' + o.goodINfoName + '</abbr>';//货品名称

            //var content = '';
            //var h = '';
            //var array = o.specString.split(",");
            //for (var j = 0; j < array.length; j++) {
            //    var text = /(?:null)/;
            //    var flag = text.test(array[j]);
            //    if (!flag) {
            //        h = array[j];
            //        if (array.length > 1) {
            //            h += '...';
            //        }
            //        break;
            //    }
            //
            //}
            //for (var a = 0; a < array.length; a++) {
            //    var text = /(?:null)/;
            //    var flag = text.test(array[a]);
            //    if (!flag) {
            //        content += array[a] + '\n';
            //    }
            //}
            html += '<abbr class="invenType" title="'+ o.remark + '">' + o.remark + '</abbr>';//规格
            html += '<abbr class="invenId" title="'+ o.goodInfoItemNo +'">' + o.goodInfoItemNo + '</abbr>';//货品编号
            //if(  o.goodsInfoType == "0"){
            //    html += '<abbr>集采商品</abbr>';
            //}else if( o.goodsInfoType == "1"){
            //    html += '<abbr>自购商品</abbr>';
            //}else{
            //    html += '<abbr></abbr>';
            //}
            html += '<abbr>' + o.currentAmount + '</abbr>';//入库库存
            html += '<abbr class="invenNum">' + handleDate_prev(new Date(o.createTime)) + '</abbr>';//入库时间
            if(typeof(o.productTime) == 'undefined'){
                html += '<abbr class="list_start_time">' + '' + '</abbr>';//生产日期
            }
            else{
            html += '<abbr class="list_start_time">' + handleDate_prev(new Date(o.productTime)) + '</abbr>';//生产日期
            }
            if(typeof(o.endTime) == 'undefined'){
                html += '<abbr class="list_end_time">' + '' + '</abbr>';//到期日期
            }
            else{
                html += '<abbr class="list_end_time">' + handleDate_prev(new Date(o.endTime)) + '</abbr>';//到期日期
            }
            if(o.endWarnDay == 0){
                html += '<abbr class="list_warn_time">' + '' + '</abbr>';//保质期
            }
            else{
                html += '<abbr class="list_warn_time">' + o.endWarnDay + '</abbr>';//保质期
            }
            //html += '<abbr class="list_over_time">' + DateDiff(handleDate_prev(new Date(o.creatTime)),handleDate_prev(new Date(o.productTime))) +'天后到期' + '</abbr>';//到期提醒
            if(typeof (o.endTime) != 'undefined' && handleDate_prev(new Date(o.endTime)) >= handleDate_prev(new Date())){
                html += '<abbr class="list_over_time">' + daysBetween(handleDate_prev(new Date(o.endTime)),handleDate_prev(new Date())) +'天后到期' + '</abbr>';//到期提醒
            }
            else if(typeof (o.endTime) != 'undefined' && handleDate_prev(new Date(o.endTime)) < handleDate_prev(new Date())){
                html += '<abbr class="list_over_time">'+ '已过期' + daysBetween(handleDate_prev(new Date(o.endTime)),handleDate_prev(new Date()),"1") +'天' + '</abbr>';//到期提醒
            }
            else{
                html += '<abbr class="list_over_time">' + '永久有效' + '</abbr>';//到期提醒
            }
            console.log(handleDate_prev(new Date(o.endTime)))
            //html += '<abbr class="list_start_time">' + handleDate_prev(new Date(2017/05/01)) + '</abbr>';//生产日期
            //html += '<abbr class="list_end_time">' + handleDate_prev(new Date(2017-05-02)) + '</abbr>';//到期日期
            //html += '<abbr class="list_warn_time">' + '2' + '</abbr>';//保质期
            //html += '<abbr class="list_over_time">' + DateDiff(handleDate_prev(new Date(2017-05-01)),handleDate_prev(new Date(2017-05-02))) +'天后到期' + '</abbr>';//到期提醒

            html += '<abbr class="invenSeller">' + o.brandName + '</abbr>';//品牌
            if(typeof (o.thirdName) == 'undefined'){
                html += '<abbr class="invenSeller">' + o.enterpriseName + '</abbr>';//商家
            }
            else{
                html += '<abbr class="invenSeller">' + o.thirdName + '</abbr>';//商家
            }
            if($("#isTop").val()=='true'){
                if($("#statusInput2").val() != "2"){
                    $("#operation").css("display","inline-block");
                    html += '<abbr style="">';
                    html += '<select class="invenListSelect">';
                    html += '<option value="0" selected="selected">可选</option>';

                    html += '<option value="1">删除</option>';
                    html += '<option class="changeInventory" value="2">保质期修改</option>';
                    //html += '<option value="3">预警设置</option>';
                    html += '</select></abbr>';
                }else {
                    $("#operation").css("display","none");
                }
            }else if($("#isEnd").val()=='true'){
                $("#operation").css("display","inline-block");
                html += '<abbr style="">';
                html += '<select class="invenListSelect">';
                html += '<option value="0" selected="selected">可选</option>';

                html += '<option value="1">删除</option>';
                html += '<option class="changeInventory" value="2">保质期修改</option>';
                //html += '<option value="3">预警设置</option>';
                html += '</select></abbr>';
            }else {
                $("#operation").css("display","none");
            }

        }
        else if (type == 'inventory_notHandled') {
            var bill_type = handleInventoryBill(o.billType);
            html += '<input type="hidden" value="' + o.billId + '">';
            html += '<abbr class="vocherState">' + bill_type + '</abbr>';
            html += '<abbr class="vocherCode">' + o.code + '</abbr>';
            try{
                html += '<abbr>' + o.tansferInfo.creatorName + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr>' + handleDate_prev(new Date(o.createTime)) + '</abbr>';
            html += '<abbr>' + handleInventoryStatus(o.billStatus,o.billType) + '</abbr>';
            try{
                html += '<abbr>' + handleUndefined(o.tansferInfo.currentName) + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr style="width: 120px;overflow: hidden">'
            if(typeof (o.reason) != 'undefined'){
                html += o.reason
            }
            html +='</abbr>'
        }
        else if (type == 'inventory_done') {
            var bill_type = handleInventoryBill(o.billType);
            html += '<input type="hidden" value="' + o.billId + '">';
            html += '<abbr class="vocherState">' + bill_type + '</abbr>';
            html += '<abbr class="vocherCode">' + o.code + '</abbr>';
            try{
                html += '<abbr>' + o.tansferInfo.creatorName + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr>' + handleDate_prev(new Date(o.createTime)) + '</abbr>';
            html += '<abbr>' + handleInventoryStatus(o.billStatus,o.billType) + '</abbr>';
            try{
                html += '<abbr>' + handleUndefined(o.tansferInfo.currentName) + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr style="width: 120px;overflow: hidden">'
            if(typeof (o.reason) != 'undefined'){
                html += o.reason
            }
            html +='</abbr>'
        }
        else if (type == 'inventory_myRequest') {
            var bill_type = handleInventoryBill(o.billType);
            html += '<input type="hidden" value="' + o.billId + '">';
            html += '<abbr class="vocherState">' + bill_type + '</abbr>';
            html += '<abbr class="vocherCode">' + o.code
            if( !o.readFlag ){
                html += '<b style="color: #ff3300;margin-left: 10px">NEW</b>'
            }
            html += '</abbr>';
            try{
                html += '<abbr>' + o.tansferInfo.creatorName + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr>' + handleDate_prev(new Date(o.createTime)) + '</abbr>';
            html += '<abbr>' + handleInventoryStatus(o.billStatus,o.billType) + '</abbr>';
            try{
                html += '<abbr>' + handleUndefined(o.tansferInfo.currentName) + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr style="width: 120px;overflow: hidden">'
            if(typeof (o.reason) != 'undefined'){
                html += o.reason
            }
            html +='</abbr>'
        }
        else if (type == 'purchaseOrder') {
            html += '<input type="hidden" value="' + o.purchaseId + '">';
            html += '<abbr class="vocherState">采购订单</abbr>';
            html += '<abbr class="vocherCode">' + o.code + '</abbr>';
            html += '<abbr class="vocherCode">' + handleUndefined(o.thirdName) + '</abbr>';
            html += '<abbr>' + purchaseStu(o.status) + '</abbr>';
            html += '<abbr>'  +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) +'</abbr>';
            html += '<abbr style="width: 120px;overflow: hidden">' + handleUndefined(o.marks) + '</abbr>';
        }
        else if (type == "memberUbaoSendForm") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' +  o.idCard + '</td>';
            trHtml += '<td>' + o.fullname + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.grandAmount) + '</td>';
            trHtml += '<td>' + addCommas(o.marketPrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.salePrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.price,2) + '</td>';
            trHtml += '<td><span class="checkDetail">查看明细</span></td>';
            trHtml += '</tr>';
        }
        else if (type == "selfItems") {
            if (typeof callbackChecked == 'function') {
                var flag = callbackChecked(1);
            }
            var trHtml = '<dd>';
            trHtml += '<abbr style="width:30px;"><input name="selfItemsList" type="checkbox" /></abbr>';
            trHtml += '<abbr style="padding-top:0px;"><img src="' + o.goodsInfoImgId + '" width="50" height="50" /></abbr>';
            trHtml += '<abbr title="'+ o.goodsInfoName +'">' + o.goodsInfoName + '</abbr>';
            trHtml += '<abbr style="margin-left:15px;" title="' + o.specString + '">' + o.specString + '</abbr>';
            trHtml += '<abbr title="'+ o.goodsInfoItemNo +'">' + o.goodsInfoItemNo + '</abbr>';
            trHtml += '<abbr style="margin-left: 20px" title="'+addCommas(o.goodsInfoPreferPrice,2)+'">' + addCommas(o.goodsInfoPreferPrice,2) + '</abbr>';
            trHtml += '<abbr title="'+addCommas(o.goodsInfoSettlePrice,2)+'">' + addCommas(o.goodsInfoSettlePrice,2) + '</abbr>';
            if( o.goodsInfoAdded == "1" ){
                trHtml += '<abbr>是 <input class="itemsInfoId" type="hidden" value="' + o.goodsInfoId + '"/></abbr>';
            }else{
                trHtml += '<abbr>否<input class="itemsInfoId" type="hidden" value="' + o.goodsInfoId + '"/></abbr>';
            }
            trHtml += '<abbr title="'+handleUndefined(o.goodsInfoTypeName)+'">' + handleUndefined(o.goodsInfoTypeName) + '</abbr>';
            trHtml += '<abbr title="'+ handleUndefined(o.goodsBrand) +'">' + handleUndefined(o.goodsBrand) + '</abbr>';
            trHtml += '<abbr title="'+handleUndefined(o.enterpriseName)+'">' + handleUndefined(o.enterpriseName) + '</abbr>';
            trHtml += '<abbr class="itemsDetail">查看详情</abbr>';
            trHtml += '</dd>';
        }
        else if (type == "myItems") {
            var trHtml = '<dd>';
            trHtml += '<abbr style="width:30px;"><input name="myItemsList" type="checkbox" /></abbr>';
            trHtml += '<abbr style="padding-top:0px;"><img src="' + o.goodsInfoImgId + '" width="50" height="50" /></abbr>';
            trHtml += '<abbr title="'+ o.goodsInfoName +'">' + o.goodsInfoName + '</abbr>';
            trHtml += '<abbr style="margin-left:15px;" title="' + o.specString + '">' + o.specString + '</abbr>';
            trHtml += '<abbr title="'+ o.goodsInfoItemNo +'">' + o.goodsInfoItemNo + '</abbr>';
            trHtml += '<abbr style="margin-left: 20px" title="'+addCommas(o.goodsInfoPreferPrice,2)+'">' + addCommas(o.goodsInfoPreferPrice,2) + '</abbr>';
            trHtml += '<abbr title="'+addCommas(o.goodsInfoSettlePrice,2)+'">' + addCommas(o.goodsInfoSettlePrice,2) + '</abbr>';
            if( o.goodsInfoAdded == "1" ){
                trHtml += '<abbr><span style="background: #54a6de" class="addMyItem">是</span><input class="itemsInfoId" type="hidden" value="' + o.goodsInfoId + '"/></abbr>';
            }else{
                trHtml += '<abbr><span style="background: #ff3300" class="addMyItem">否</span><input class="itemsInfoId" type="hidden" value="' + o.goodsInfoId + '"/></abbr>';
            }
            trHtml += '<abbr title="'+handleUndefined(o.goodsInfoTypeName)+'">' + handleUndefined(o.goodsInfoTypeName) + '</abbr>';
            trHtml += '<abbr title="'+handleUndefined(o.goodsBrand)+'">' + handleUndefined(o.goodsBrand) + '</abbr>';
            trHtml += '<abbr title="'+handleUndefined(o.enterpriseName)+'">' + handleUndefined(o.enterpriseName) + '</abbr>';
            trHtml += '<abbr class="showmyitemsDetail"><div class="myitemsDetail">查看详情<i class="arrow2"></i></div><section class="update">修改</section></abbr>';
            trHtml += '</dd>';
        }
        else if (type == "memberUbaoSendFormDetail") {
            var trHtml = '<tr>';
            trHtml += '<td>'+ o.idCard + '</td>'
            trHtml += '<td>' + o.fullname + '</td>'
            trHtml += '<td>' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            trHtml += '<td>' + o.grandEnterprise + '</td>'
            trHtml += '<td>' + addCommas(o.marketPrice,2) + '</td>'
            trHtml += '<td>' + addCommas(o.salePrice,2) + '</td>'
            trHtml += '<td>' + addCommas(o.price,2) +'</td>'
            trHtml += '</tr>'
        }
        else if (type == "accSearchByItem") {
            var trHtml = '<tr>';
            if( o.sumFlag == "合计"){
                trHtml += '<td colspan="3">合计</td>'
            }else{
                trHtml += '<td>'+ handleUndefined(o.goodsInfoName) + '</td>'
                trHtml += '<td>' + handleUndefined(o.goodsSpecString) + '</td>'
                trHtml += '<td>' + handleUndefined(o.goodsInfoItemNo) + '</td>'
            }
            trHtml += '<td>' + addCommas(o.lastMonthInventory,0) + '</td>'
            trHtml += '<td>' + addCommas(o.increaseAmount,0) + '</td>'
            if($("#monthAdd").html() == "-"){
                trHtml += '<td style="display: table-cell" class="monthAdd">' + addCommas(o.instockAmount,0) + '</td>'
                trHtml += '<td style="display: table-cell" class="monthAdd">' + addCommas(o.transferIn,0) + '</td>'
                trHtml += '<td style="display: table-cell" class="monthAdd">' + addCommas(o.orderBack,0) + '</td>'
            }else{
                trHtml += '<td class="monthAdd">' + addCommas(o.instockAmount,0) + '</td>'
                trHtml += '<td class="monthAdd">' + addCommas(o.transferIn,0) + '</td>'
                trHtml += '<td class="monthAdd">' + addCommas(o.orderBack,0) + '</td>'
            }

            trHtml += '<td>' + addCommas(o.consumeAmount,0) + '</td>'

            if($("#monthReduce").html() == "-"){
                trHtml += '<td style="display: table-cell" class="monthReduce">' + addCommas(o.transferOut,0) + '</td>'
                trHtml += '<td style="display: table-cell" class="monthReduce">' + addCommas(o.orderConsume,0) + '</td>'
            }else{
                trHtml += '<td class="monthReduce">' + addCommas(o.transferOut,0) + '</td>'
                trHtml += '<td class="monthReduce">' + addCommas(o.orderConsume,0) + '</td>'
            }

            trHtml += '<td>' + addCommas(o.gainsAmount,0) + '</td>'

            if($("#reimburse").html() == "-"){
                trHtml += '<td style="display: table-cell" class="reimburse">' + addCommas(o.lessReport,0) + '</td>'
                trHtml += '<td style="display: table-cell" class="reimburse">' + addCommas(o.moreReport,0) + '</td>'
            }else{
                trHtml += '<td class="reimburse">' + addCommas(o.lessReport,0) + '</td>'
                trHtml += '<td class="reimburse">' + addCommas(o.moreReport,0) + '</td>'
            }

            trHtml += '<td>' + addCommas(o.inventoryAmount,0) + '</td>'
            trHtml += '</tr>'



        }
        else if (type == "accSearchByInvent") {
            var trHtml = '<tr>';
                trHtml += '<td style="border-right: 1px solid #e5e5e5">'+ o.enterpriseName +'</td>'
                trHtml += '<td>'
                trHtml += '<table class="table_listBaseByInventDetail" cellpadding="0" cellspacing="0" align="right">'
            o.formList.map(function(ob){
                trHtml += '<tr>'
                trHtml += '<td>'+ handleUndefined(ob.goodsInfoName) + '</td>'
                trHtml += '<td>' + handleUndefined(ob.goodsSpecString) + '</td>'
                trHtml += '<td>' + handleUndefined(ob.goodsInfoItemNo) + '</td>'
                trHtml += '<td>' + addCommas(ob.lastMonthInventory,0) + '</td>'
                trHtml += '<td>' + addCommas(ob.increaseAmount,0) + '</td>'
                if($("#monthAddByInvent").html() == "-"){
                    trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(ob.instockAmount,0) + '</td>'
                    trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(ob.transferIn,0) + '</td>'
                    trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(ob.orderBack,0) + '</td>'
                }else{
                    trHtml += '<td class="monthAddByInvent">' + addCommas(ob.instockAmount,0) + '</td>'
                    trHtml += '<td class="monthAddByInvent">' + addCommas(ob.transferIn,0) + '</td>'
                    trHtml += '<td class="monthAddByInvent">' + addCommas(ob.orderBack,0) + '</td>'
                }

                trHtml += '<td>' + addCommas(ob.consumeAmount,0) + '</td>'
                if($("#monthReduceByInvent").html() == "-"){
                    trHtml += '<td style="display: table-cell;"  class="monthReduceByInvent">' + addCommas(ob.transferOut,0) + '</td>'
                    trHtml += '<td style="display: table-cell;"  class="monthReduceByInvent">' + addCommas(ob.orderConsume,0) + '</td>'
                }else{
                    trHtml += '<td class="monthReduceByInvent">' + addCommas(ob.transferOut,0) + '</td>'
                    trHtml += '<td class="monthReduceByInvent">' + addCommas(ob.orderConsume,0) + '</td>'
                }

                trHtml += '<td>' + addCommas(ob.gainsAmount,0) + '</td>'

                if($("#reimburseByInvent").html() == "-"){
                    trHtml += '<td style="display: table-cell;"  class="reimburseByInvent">' + addCommas(ob.lessReport,0) + '</td>'
                    trHtml += '<td style="display: table-cell;"  class="reimburseByInvent">' + addCommas(ob.moreReport,0) + '</td>'
                }else{
                    trHtml += '<td class="reimburseByInvent">' + addCommas(ob.lessReport,0) + '</td>'
                    trHtml += '<td class="reimburseByInvent">' + addCommas(ob.moreReport,0) + '</td>'
                }

                trHtml += '<td>' + addCommas(ob.inventoryAmount,0) + '</td>'
                trHtml += '</tr>'
            })
            trHtml += '<tr>'
            trHtml += '<td colspan="3">合计</td>'
            trHtml += '<td>' + addCommas(o.inventoryHistoryForm.lastMonthInventory,0) + '</td>'
            trHtml += '<td>' + addCommas(o.inventoryHistoryForm.increaseAmount,0) + '</td>'
            if($("#monthAddByInvent").html() == "-"){
                trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(o.inventoryHistoryForm.instockAmount,0) + '</td>'
                trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(o.inventoryHistoryForm.transferIn,0) + '</td>'
                trHtml += '<td style="display: table-cell;" class="monthAddByInvent">' + addCommas(o.inventoryHistoryForm.orderBack,0) + '</td>'
            }else{
                trHtml += '<td class="monthAddByInvent">' + addCommas(o.inventoryHistoryForm.instockAmount,0) + '</td>'
                trHtml += '<td class="monthAddByInvent">' + addCommas(o.inventoryHistoryForm.transferIn,0) + '</td>'
                trHtml += '<td class="monthAddByInvent">' + addCommas(o.inventoryHistoryForm.orderBack,0) + '</td>'
            }
            trHtml += '<td>' + addCommas(o.inventoryHistoryForm.consumeAmount,0) + '</td>'
            if($("#monthReduceByInvent").html() == "-"){
                trHtml += '<td style="display: table-cell;"  class="monthReduceByInvent">' + addCommas(o.inventoryHistoryForm.transferOut,0) + '</td>'
                trHtml += '<td style="display: table-cell;"  class="monthReduceByInvent">' + addCommas(o.inventoryHistoryForm.orderConsume,0) + '</td>'
            }else{
                trHtml += '<td class="monthReduceByInvent">' + addCommas(o.inventoryHistoryForm.transferOut,0) + '</td>'
                trHtml += '<td class="monthReduceByInvent">' + addCommas(o.inventoryHistoryForm.orderConsume,0) + '</td>'
            }

            trHtml += '<td>' + addCommas(o.inventoryHistoryForm.gainsAmount,0) + '</td>'

            if($("#reimburseByInvent").html() == "-"){
                trHtml += '<td style="display: table-cell;"  class="reimburseByInvent">' + addCommas(o.inventoryHistoryForm.lessReport,0) + '</td>'
                trHtml += '<td style="display: table-cell;"  class="reimburseByInvent">' + addCommas(o.inventoryHistoryForm.moreReport,0) + '</td>'
            }else{
                trHtml += '<td class="reimburseByInvent">' + addCommas(o.inventoryHistoryForm.lessReport,0) + '</td>'
                trHtml += '<td class="reimburseByInvent">' + addCommas(o.inventoryHistoryForm.moreReport,0) + '</td>'
            }

            trHtml += '<td>' + addCommas(o.inventoryHistoryForm.inventoryAmount,0) + '</td>'
            trHtml += '</tr>'
            trHtml += '</table>'
            trHtml += '</td>'
            trHtml += '</tr>'

        }
        else if (type == "memberUbaoReduceForm") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' +  o.idCard + '</td>';
            trHtml += '<td>' + o.fullname + '</td>';
            trHtml += '<td>' + addCommas(o.orderAmount)  + '</td>';
            trHtml += '<td>' + addCommas(o.totalConsumePrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.backAmount) + '</td>';
            trHtml += '<td>' + addCommas(o.totalRefundPrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.resePrice,2) + '</td>';
            trHtml += '<td><span class="checkDetailReduce">查看明细</span></td>';
            trHtml += '</tr>';
        }
        else if (type == "memberUbaoReduceFormNew") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' + handleUndefined( o.idCard) + '</td>';
            trHtml += '<td>' + handleUndefined(o.fullname) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.valetAmount) + '</td>';
            trHtml += '<td>' + addCommas(o.totalValetPrice,2)  + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.onlineAmount) + '</td>';
            trHtml += '<td>' + addCommas(o.totalOnlinePrice,2)  + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.backValetAmount) + '</td>';
            trHtml += '<td>' + addCommas(o.totalBackValetPrice,2)  + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.backOnlineAmount) + '</td>';
            trHtml += '<td>' + addCommas(o.totalBackOnlinePrice,2)  + '</td>';
            trHtml += '<td><span class="checkDetailReduce">查看明细</span></td>';
            trHtml += '</tr>';
        }
        else if (type == "memberUbaoReduceFormDetail") {
            var trHtml = '<tr>';
            trHtml += '<td>'+ o.idCard + '</td>'
            trHtml += '<td>' + o.fullname + '</td>'
            if(o.type=="UCOIN_CONSUME"){
                trHtml += '<td>订单</td>'
            }else{
                trHtml += '<td>退单</td>'
            }
            trHtml += '<td>' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            trHtml += '<td>' + handleUndefinedZero(o.orderCode) + '</td>'
            trHtml += '<td>' + addCommas(o.orderPrice,2) + '</td>'
            trHtml += '<td>' + addCommas(o.backPrice,2) + '</td>'
            trHtml += '</tr>'
        }
        else if (type == "memberUbaoReduceFormDetailNew") {
            var trHtml = '<tr>';
            trHtml += '<td>'+ handleUndefined(o.idCard) + '</td>'
            trHtml += '<td>' + handleUndefined(o.fullname) + '</td>'
            trHtml += '<td class="checkType">' + handleUndefined(o.typeNew) + '</td>'
            if(o.createTime == undefined){
                trHtml += '<td>' +  "" + '</td>'
            }else{
                trHtml += '<td>' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            }

            trHtml += '<td>' + handleUndefinedZero(o.billCode) + '</td>'
            trHtml += '<td>' + addCommas(o.valetOnlinePrice,2) + '</td>'
            trHtml += '<td class="checkDetail"><input type="hidden" value="'+o.orderId+'">查看详情</td>'
            trHtml += '</tr>'
        }
        else if (type == "sendReduceDetail") {
            var trHtml = '<tr>';
            trHtml += '<td>'+ handleUndefined(o.accountName) + '</td>'
            trHtml += '<td>'+ handleUndefined(o.idCard) + '</td>'
            trHtml += '<td>' + handleUndefined(o.fullname) + '</td>'
            trHtml += '<td>' + handleUndefined(o.contactPhone) + '</td>'
            trHtml += '<td>' + addCommas(o.marketPrice,2).replace("-","") + '</td>'
            trHtml += '<td>' + addCommas(o.salePrice,2).replace("-","") + '</td>'
            if(o.createTime == undefined){
                trHtml += '<td>' +  "" + '</td>'
            }else{
                trHtml += '<td>' +  handleDate_prev(new Date(o.businessTime)) + "  " + handleDate_next(new Date(o.businessTime)) + '</td>'
            }
            trHtml += '</tr>'
        }
        else if (type == "sendReduce") {
            var trHtml = '<tr>';
            if(o.createTime == undefined){
                trHtml += '<td class="recordData">' +  "" + '</td>'
            }else{
                trHtml += '<td class="recordData">' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            }
            trHtml += '<td class="recordFileName">'+ handleUndefined(o.fileName) + '</td>'
            trHtml += '<td class="recordType">'+ handleUndefined(o.sendType) + '</td>'
            trHtml += '<td class="recordCount">' + handleUndefined(o.countbatch) + '</td>'
            trHtml += '<td class="recordMoney">' + addCommas(o.sumfee,2).replace("-","") + '</td>'
            trHtml += '<td class="recordAccount">' + handleUndefined(o.managerName) + '</td>'
            trHtml += '<td>' + '<abbr class="sendReduceDetail">' + "查看详情" + '</abbr>' + '<input class="batchcode" type="hidden" value="'+ handleUndefined(o.batchcode) +'">' + '</td>'
            trHtml += '</tr>'
        }
        else if (type == "addInvent") {
            var trHtml = '<tr>';
            if(o.createTime == undefined){
                trHtml += '<td class="recordData">' +  "" + '</td>'
            }else{
                trHtml += '<td class="recordData">' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            }
            trHtml += '<td class="recordType">'+ handleUndefined(o.sendType) + '</td>'
            trHtml += '<td class="recordCount">' + handleUndefined(o.countbatch) + '</td>'
            trHtml += '<td class="recordMoney">' + addCommas(o.sumfee,2) + '</td>'
            trHtml += '<td class="recordAccount">' + handleUndefined(o.managerName) + '</td>'
            trHtml += '<td>' + '<abbr class="sendReduceDetail">' + "查看详情" + '</abbr>' + '<input class="batchcode" type="hidden" value="'+ handleUndefined(o.batchcode) +'">' + '</td>'
            trHtml += '</tr>'
        }
        else if (type == "memberBill") {
            var trHtml = '<tr>';
            if(o.createTime == undefined){
                trHtml += '<td>' +  "" + '</td>'
            }else{
                trHtml += '<td>' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            }
            trHtml += '<td>'+ handleUndefined(o.enterpriseInfo.enterpriseName) + '</td>'
            if( o.type == "ENTERPRISE_GRAND"){
                trHtml += '<td class="checkType">' +  "邮豆发放" + '</td>'
                trHtml += '<td>'+ "+" + addCommas(o.price,2) + "邮豆" + '</td>'
            }else if( o.type == "UCOIN_CONSUME" ){
                trHtml += '<td class="checkType">' +  "邮豆消耗" + '</td>'
                trHtml += '<td>' + "-" + addCommas(o.price,2) + "邮豆" + '</td>'
            }else if( o.type == "UCOIN_DEDUCT" ){
                trHtml += '<td class="checkType">' +  "邮豆扣减" + '</td>'
                trHtml += '<td>'  + addCommas(o.price,2) + "邮豆" + '</td>'
            }else if( o.type == "UCOIN_REFUND" ){
                trHtml += '<td class="checkType">' +  "邮豆退款" + '</td>'
                trHtml += '<td>'+ "+" + addCommas(o.price,2) + "邮豆" + '</td>'
            }else if( o.type == "ENTERPRISE_SUBSIDY" ){
                trHtml += '<td class="checkType">' +  "企业补贴" + '</td>'
                trHtml += '<td>'+ "+" + addCommas(o.price,2) + "邮豆" + '</td>'
            }
            trHtml += '<td>'+ addCommas2(o.balancePrice,2)+ "邮豆" + '</td>'
            if(o.orderId!= undefined){
                trHtml += '<td class="checkDetail"><input type="hidden" value="'+o.orderId+'"> 查看详情</td>'
            }else{
                trHtml += '<td></td>'
            }
            trHtml += '</tr>'
        }
        else if (type == "ubaosendForm") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' +  o.enterpriseInfo.enterpriseName + '</td>';
            trHtml += '<td>' + o.customerInfo.idCard + '</td>';
            trHtml += '<td>' + handleUndefined(o.customerInfo.name)  + '</td>';
            trHtml += '<td>' + addCommas(o.marketPrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.salesPrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.price,2) + '</td>';
            trHtml += '<td>' +  handleDate_prev(new Date(o.businessTime)) + '</td>';
            trHtml += '<td>' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            trHtml += '<td>' + handleUndefined(o.typeName) + '</td>';
            try{
                var paramJson = JSON.parse(o.paramJson);
            }
            catch(e){
                //todo
            }
            //修正后台返回数据顺序
            var arr = [];
            $(".dynamicHead").each(function(){
                var head = $(this).html();
                arr[head] = '';
                for( var key in paramJson ){
                    if( key == head ){
                        arr[key] = paramJson[key] ;
                    }
                }
            });
            for( x in arr ){
                if( x != 'remove' ){
                    trHtml += '<td>' + arr[x] + '</td>';
                }
            }
            trHtml += '</tr>';
        }
        else if (type == "baseDataform") {
            var trHtml = '<tr>';
            trHtml += '<td>' +  handleUndefined(o.grandEnterprise) + '</td>';
            trHtml += '<td>' + o.newCustomerAmount + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.expenditure)  + '</td>';
            trHtml += '<td>' + addCommas(o.totalMarketPrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.totalSalePrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.totalPrice,2) + '</td>';
            trHtml += '</tr>';
        }
        else if (type == "textDataform") {
            var trHtml = '<tr>';
            trHtml += '<td>' +  handleUndefined(o.grandEnterprise) + '</td>';
            trHtml += '<td>' + o.newCustomerAmount + '</td>';
            trHtml += '<td>' + handleUndefinedZero(o.expenditure)  + '</td>';
            trHtml += '<td>' + addCommas(o.totalMarketPrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.totalSalePrice,2) + '</td>';
            trHtml += '<td>' + addCommas(o.totalPrice,2) + '</td>';
            trHtml += '</tr>';
        }
        else if (type == "memberBaseDataform") {
            var trHtml = '<tr>';
            trHtml += '<td class="InventName" data-id="' + o.currentId + '">' + handleUndefined(o.parentName) + '</td>';
            trHtml += '<td class="InventAddress" data-id="' + o.currentId + '">' + handleUndefined(o.currentName) + '</td>';
            trHtml += '<td>' + handleUndefined(o.fullName)  + '</td>';
            trHtml += '<td>' + handleUndefined(o.idCardNo)  + '</td>';
            trHtml += '<td>' + handleNewOldCus(o.customerRef)  + '</td>';
            trHtml += '<td>' + addCommas(o.lastPrice,2) + '</td>';
            trHtml += '<td style="border-right: 1px solid #e5e5e5">' + addCommas(o.currentPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefined(o.grandFrequency)  + '</td>';
            trHtml += '<td style="border-right: 1px solid #e5e5e5">' + addCommas(o.grandPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefined(o.decutFrequency)  + '</td>';
            trHtml += '<td style="border-right: 1px solid #e5e5e5">' + addCommas(o.decutPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefined(o.consumeFrequencyOffline)  + '</td>';
            trHtml += '<td>' + addCommas(o.consumePriceOffline,2) + '</td>';
            trHtml += '<td style="border-right: 1px solid #e5e5e5">' + addCommas(o.consumeSubsidyPriceOffline,2) + '</td>';
            trHtml += '<td>' + handleUndefined(o.consumeFrequencyOnline)  + '</td>';
            trHtml += '<td style="border-right: 1px solid #e5e5e5">' + addCommas(o.consumePriceOnline,2) + '</td>';
            trHtml += '<td style="border-right: 1px solid #e5e5e5">' + addCommas(o.consumeSettlePrice,2) + '</td>';
            trHtml += '<td>' + handleUndefined(o.refundFrequency)  + '</td>';
            trHtml += '<td>' + addCommas(o.refundSettlePrice,2) + '</td>';
            trHtml += '</tr>';
        }
        else if (type == "logManager") {
            var trHtml = '<tr>';
            trHtml += '<td>' +  handleUndefined(o.opEnterpriseName) + '</td>';
            trHtml += '<td>' + o.opName + '</td>';
            trHtml += '<td>' + handleUndefined(o.opIp)  + '</td>';
            trHtml += '<td>' +  handleDate_prev(new Date(o.opTime)) + "  " + handleDate_next(new Date(o.opTime)) + '</td>'
            trHtml += '<td>' + handleUndefined(o.opCode) + '</td>';
            trHtml += '</tr>';
        }
            else if (type == "changeHistory") {
            var trHtml = '<tr>';
            trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName">' +  handleUndefined(o.enterpriseName) + '</td>';
            trHtml += '<td>' +  handleDate_prev(new Date(o.changeTime)) + "  " + handleDate_next(new Date(o.changeTime)) + '</td>'
            trHtml += '<td>'+ handleUndefined(o.goodsInfoName) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.goodsSpecString) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.goodsInfoItemNo) +'</td>';
            trHtml += '<td>'+ hundleInventoryChangeType(o.inventoryChangeType) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.code) +'</td>';
            if( o.inventoryChangeType == "GOODSINFO_LESS_REPORT" || o.inventoryChangeType == "GOODSINFO_TRANSFER_OUT" || o.inventoryChangeType == "ORDER_CONSUME" ){
                trHtml += '<td>'+ '-' + handleUndefined(o.inventoryChangeAmount) +'</td>';
            }else{
                trHtml += '<td>'+ handleUndefined(o.inventoryChangeAmount) +'</td>';
            }

            trHtml += '<td>'+ addCommas2(o.inventoryAfterChange,0) +'</td>';
            trHtml += '</tr>';
        }
        else if (type == "supplyDetail") {
            var trHtml = '<tr>';
            trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName">' +  handleUndefined(o.enterpriseName) + '</td>';
            trHtml += '<td>'+ handleUndefined(o.thirdName) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.code) +'</td>';
            if(o.createTime != undefined){
                trHtml += '<td>' +  handleDate_prev(new Date(o.createTime)) + "  " + handleDate_next(new Date(o.createTime)) + '</td>'
            }else{
                trHtml += '<td></td>'
            }
            //if(o.stockTime != undefined){
            //    trHtml += '<td>' +  handleDate_prev(new Date(o.stockTime)) + "  " + handleDate_next(new Date(o.stockTime)) + '</td>'
            //}else{
            //    trHtml += '<td></td>'
            //}
            //if(o.recipientTime != undefined){
            //    trHtml += '<td>' +  handleDate_prev(new Date(o.recipientTime)) + "  " + handleDate_next(new Date(o.recipientTime)) + '</td>'
            //}else{
            //    trHtml += '<td></td>'
            //}
            trHtml += '<td>'+ handleUndefined(o.goodsInfoName) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.goodsInfoItemNo) +'</td>';
            if(  o.goodsInfoType == "0"){
                trHtml += '<td>集采商品</td>';
            }else if( o.goodsInfoType == "1"){
                trHtml += '<td>自购商品</td>';
            }else{
                trHtml += '<td></td>';
            }
            trHtml += '<td>'+ addCommas(o.settlePrice,2) +'</td>';
            trHtml += '<td>'+ handleUndefinedToZero(o.deliveryAmount) +'</td>';
            trHtml += '<td>'+ handleUndefinedToZero(o.receiptAmount) +'</td>';
            trHtml += '<td>'+ addCommas(o.price,2) +'</td>';
            trHtml += '<td>'+ handleInventoryStatus(o.billStatus, o.billType) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.linkMan) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.linkMobile) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.address)  +'</td>';
            //trHtml += '<td data-id="'+ o.enterpriseId + '" class="InventAddress">'+handleUndefined(o.linkMan) + '</br>'+handleUndefined(o.linkMobile) + '</br>'+ handleUndefined(o.address) +'</td>';
            trHtml += '</tr>';
        }
        else if (type == "supplyDetailForm") {
            var trHtml = '<tr>';
            trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName">' +  handleUndefined(o.enterpriseName) + '</td>';
            trHtml += '<td>'+ handleUndefined(o.thirdName) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.code) +'</td>';
            if(o.applyTime != undefined){
                trHtml += '<td>' +  handleDate_prev(new Date(o.applyTime)) + "  " + handleDate_next(new Date(o.applyTime)) + '</td>'
            }else{
                trHtml += '<td></td>'
            }
            if(o.stockTime != undefined){
                trHtml += '<td>' +  handleDate_prev(new Date(o.stockTime)) + "  " + handleDate_next(new Date(o.stockTime)) + '</td>'
            }else{
                trHtml += '<td></td>'
            }
            if(o.recipientTime != undefined){
                trHtml += '<td>' +  handleDate_prev(new Date(o.recipientTime)) + "  " + handleDate_next(new Date(o.recipientTime)) + '</td>'
            }else{
                trHtml += '<td></td>'
            }
            trHtml += '<td>'+ handleUndefined(o.goodsInfoName) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.goodsInfoItemNo) +'</td>';
            if(  o.goodsInfoType == "0"){
                trHtml += '<td>集采商品</td>';
            }else if( o.goodsInfoType == "1"){
                trHtml += '<td>自购商品</td>';
            }else{
                trHtml += '<td></td>';
            }
            trHtml += '<td>'+ handleUndefinedToZero(o.deliveryAmount) +'</td>';
            trHtml += '<td>'+ addCommas(o.settlePrice,2) +'</td>';
            trHtml += '<td>'+ addCommas(o.price,2) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.linkMan) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.linkMobile) +'</td>';
            trHtml += '<td>'+ handleUndefined(o.address)  +'</td>';
            trHtml += '<td class="last">'+ handleInventoryStatus(o.billStatus) +'</td>';
            trHtml += '</tr>';
        }
        else if (type == "supplyCount") {
            var trHtml = '<tr>';
            if( o.sumFlag == undefined ){
                trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName">' +  handleUndefined(o.enterpriseName) + '</br>(合计金额：'+ handleUndefined(o.totalPrice) +')'  + '</td>';
                trHtml += '<td>'+ handleUndefined(o.thirdName) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.price) +'</td>';
            }else{
                trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName">' +  handleUndefined(o.sumFlag) + '</td>';
                trHtml += '<td></td>';
                trHtml += '<td>'+ handleUndefined(o.totalPrice) +'</td>';
            }

        }
        else if (type == "deliveryDetail") {
            var trHtml = '<tr>';
            if( o.sumFlag == undefined ){
                trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName">' +  handleUndefined(o.enterpriseName) + '</br>(合计金额：'+ handleUndefined(o.totalPrice) +')' + '</td>';
                trHtml += '<td>'+ handleUndefined(o.goodsInfoName) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.goodsInfoItemNo) +'</td>';
                if(  o.goodsInfoType == "0"){
                    trHtml += '<td>集采商品</td>';
                }else if( o.goodsInfoType == "1"){
                    trHtml += '<td>自购商品</td>';
                }else{
                    trHtml += '<td></td>';
                }
                trHtml += '<td>'+ handleUndefined(o.deliveryAmount) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.settlePrice) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.price) +'</td>';
                trHtml += '<td data-id="'+ o.enterpriseId + '" class="InventAddress">'+handleUndefined(o.linkMan) + '</br>'+handleUndefined(o.linkMobile) + '</br>'+ handleUndefined(o.address) +'</td>';
            }else{
                trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName" colspan="6">' +  handleUndefined(o.sumFlag) + '</td>';
                trHtml += '<td>'+ handleUndefined(o.totalPrice) +'</td>';
                trHtml += '<td class="InventAddress"></td>';
            }
            trHtml += '</tr>';
        }
        else if (type == "deliveryDetail2") {
            var trHtml = '<tr>';
            if( o.sumFlag == undefined ){
                trHtml += '<td>'+ handleUndefined(o.goodsInfoName) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.goodsInfoItemNo) +'</td>';
                if(  o.goodsInfoType == "0"){
                    trHtml += '<td>集采商品</td>';
                }else if( o.goodsInfoType == "1"){
                    trHtml += '<td>自购商品</td>';
                }else{
                    trHtml += '<td></td>';
                }
                trHtml += '<td>'+ handleUndefined(o.deliveryAmount) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.settlePrice) +'</td>';
                trHtml += '<td>'+ handleUndefined(o.price) +'</td>';
            }else{
                trHtml += '<td data-id="'+ o.enterpriseId +'" class="InventName" colspan="5">' +  handleUndefined(o.sumFlag) + '</td>';
                trHtml += '<td>'+ handleUndefined(o.totalPrice) +'</td>';
                trHtml += '<td class="InventAddress"></td>';
            }
            trHtml += '</tr>';
        }
        else if (type == "recentBillManager") {
            var trHtml = '<dd>';
            trHtml += '<abbr>' +  handleUndefined(o.grandEnterprise) + '</abbr>';
            trHtml += '<abbr>' + o.newCustomerAmount + '</abbr>';
            trHtml += '<abbr>' + handleUndefinedZero(o.expenditure)  + '</abbr>';
            trHtml += '<abbr>' + addCommas(o.totalMarketPrice,2) + '</abbr>';
            trHtml += '<abbr>' + addCommas(o.totalSalePrice,2) + '</abbr>';
            trHtml += '<abbr>' + addCommas(o.totalPrice,2) + '</abbr>';
            trHtml += '</dd>';
        }
        else if (type == "recentRequisition") {
            var trHtml = '<dd>';
            trHtml += '<abbr>' +  handleUndefined(o.grandEnterprise) + '</abbr>';
            trHtml += '<abbr>' + o.newCustomerAmount + '</abbr>';
            trHtml += '<abbr>' + handleUndefinedZero(o.expenditure)  + '</abbr>';
            trHtml += '<abbr>' + addCommas(o.totalMarketPrice,2) + '</abbr>';
            trHtml += '<abbr>' + addCommas(o.totalSalePrice,2) + '</abbr>';
            trHtml += '</dd>';
        }
        html += '</dd>';
        htm += '</li>';
        if (type == 9) {
            $("#" + str).append(htm);
        }
        else if (type == 'memberUbaoSendForm') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'selfItems') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'myItems') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoSendFormDetail') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'accSearchByItem') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'accSearchByInvent') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoReduceForm') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoReduceFormNew') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoReduceFormDetail') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'batchCode') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'sendReduceDetail') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'sendReduce') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoReduceFormDetailNew') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'addInvent') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberBill') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'ubaosendForm') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'baseDataform') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberBaseDataform') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'logManager') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'changeHistory') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'supplyDetail') {
            $("#" + str).append(trHtml);
        }
        else if(type == 'supplyDetailForm'){
            $("#" + str).append(trHtml);
        }
        else if (type == 'supplyCount') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'deliveryDetail') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'deliveryDetail2') {
            $("#" + str).append(trHtml);
        }
        else {
            $("#" + str).append(html);
        }
    });
}

function handleReqStatus(type){
    if( type == 'DENIED' ){
        return '已拒绝';
    }
    if( type == 'PAYED' ){
        return '已支付'
    }
    if( type == 'PASSED' ){
        return '已通过';
    }
    if( type == 'APPLIED' ){
        return '已申请'
    }
}
function handleTodoStatus(type){
    if( type == 'PASSED' ){
        return '已审核';
    }
    else{
        return '待审核';
    }
}
function handleInventoryBill(type){
    if( type == 'REPLENISHMENT' ){
        return '补货单';
    }
    if( type == 'LESS_REPORT' ){
        return '报损单';
    }
    if( type == 'MORE_REPORT' ){
        return '报溢单';
    }
    if( type == 'INVENTORY_TRANSFER' ){
        return '调拨单';
    }
    if( type == 'INVENTORY_INSTOCK' ){
        return '入库单';
    }
    if( type == 'PARTIAL_RECEIPT' ){
        return '调拨单';
    }
    if( type == 'MERGE_REPLENISHMENT' ){
        return '补货单(合并单)';
    }
    if( type == 'PURCHASE_INVENTORY_INSTOCK' ){
        return '采购入库单';
    }
}
function handleInventoryStatus(type,billtype){
    if(billtype == undefined){
        billtype = ""
    }
    if( type == 'CHECKING' ){
        return '审核中';
    }
    if( type == 'CHECKED' ){
        return '已审核';
    }
    if( type == 'TERMINATED' ){
        return '已终止';
    }
    if( type == 'FINISHED' ){
        return '已完成';
    }
    if( type == 'WAIT_DELIVERY' ){
        return '待发货';
    }
    if( type == 'WAIT_RECEIVER' ){
        return '待收货';
    }
    if( type == 'WAIT_EDIT' ){
        return '待修改';
    }
    if( type == 'PARTIAL_RECEIPT'){
        if(billtype == "PURCHASE_INVENTORY_INSTOCK"){
            return '部分入库';
        }else{
            return '部分收货';
        }

    }
    if( type == 'STAY_IN'){
        return '待入库';
    }
    if( type == 'CHECKED_NOT_TRANSFER'){
        return '已同意未调拨';
    }
    if( type == 'CHECKED_NOT_PURCHASE'){
        return '已同意未采购';
    }

}

function purchaseStu(str){
    if( str == 'TO_CONFIRM' ){
        return '待确认';
    }
    if( str == 'TERMINATED' ){
        return '已终止';
    }
    if( str == 'FINISHED' ){
        return '已确认';
    }
    if( str == 'COMMODITY_STORAGE' ){
        return '待商品入库';
    }
    if( str == 'FOR_SETTLEMENT' ){
        return '待结算';
    }
    if( str == 'COMPLETED' ){
        return '已完成';
    }
}

//计算两天只差
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006/12/18格式
    var  aDate,  oDate1,  oDate2,  iDays
    aDate  =  sDate1.split("/")
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
    aDate  =  sDate2.split("/")
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
    return  iDays
}
/**
 * 根据两个日期，判断相差天数
 * @param sDate1 开始日期 如：2016-11-01
 * @param sDate2 结束日期 如：2016-11-02
 * @returns {number} 返回相差天数
 */
function daysBetween(sDate1,sDate2,type){
    //Date.parse() 解析一个日期时间字符串，并返回1970/1/1 午夜距离该日期时间的毫秒数
    var time1 = Date.parse(new Date(sDate1));
    var time2 = Date.parse(new Date(sDate2));
    var nDays = Math.abs(parseInt((time2 - time1)/1000/3600/24));
    if(type == '1'){
        return  nDays;
    }else {
        return  nDays+1;
    }
};