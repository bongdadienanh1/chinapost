//function ajaxPages(total,url,contentStr,target,type,perpage){
//    for(var i = 0;i < total;i++){
//        var html = get_html(type);
//        $("#"+contentStr).append(html);
//    }
//    jPagesGet(target,contentStr);
//    $("#"+contentStr).empty();
//    $("a").click(function(){
//        var page = $(this).html();
//        $.post(url,{
//            "page":page,
//            "size":perpage
//        },function(data){
//            if(data.response == 'success'){
//                appendHtml(contentStr,page,total,data.data.content,perpage);
//            }
//        },'json');
//    });
//    $("#holder").children()[1].click();
//}
//
//function get_html(type){
//    var html = '';
//    html = "<dd>";
//    html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
//    html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
//    html += '<abbr class="invenName"></abbr>';
//    html += '<abbr class="invenType"></abbr>';
//    html += '<abbr class="invenId"></abbr>';
//    html += '<abbr class="invenNum"></abbr>';
//    html += '<abbr class="invenNumUsed"></abbr>';
//    html += '<abbr class="invenBrand"></abbr>';
//    html += '<abbr class="invenSeller"></abbr>';
//    html += '<abbr class="invenName"></abbr>';
//    html += '<abbr class="invenId"></abbr>';
//    html += '<abbr style="width:300px;">';
//    html += '<select class="invenListSelect">';
//    html += '<option value="0" selected="selected">可选</option>';
//    html += '<option value="1">修改库存</option>';
//    html += '<option value="2">删除</option>';
//    html += '</select></abbr></dd>';
//    return html;
//}
//function appendHtml(str,pageIndex,totalPage,data,perpage){
//    $("#"+ str).empty();
//    for(var i=0;i<perpage;i++){
//        var html = "<dd>";
//        html += '<input type="hidden" value="' + data[i].goodsId + '">'
//        html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
//        html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
//        html += '<abbr class="invenName">' + data[i].goodsName + '</abbr>';
//
//        var content = '';
//        var h = '';
//        var array = data[i].specString.split(",");
//        for ( var j = 0; j < array.length;j++){
//            var text= /(?:null)/;
//            var flag = text.test(array[j]);
//            if ( !flag ){
//                h = array[j];
//                if( array.length > 1 ){
//                    h += '...';
//                }
//                break;
//            }
//
//        }
//        for(var a = 0;a < array.length;a++){
//            var text= /(?:null)/;
//            var flag = text.test(array[a]);
//            if ( !flag ){
//                content += array[a] + '\n';
//            }
//        }
//        html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
//        html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
//        html += '<abbr class="invenNum">' + data[i].inventory + '</abbr>';
//        html += '<abbr class="invenNumUsed">' + data[i].available + '</abbr>';
//        html += '<abbr class="invenBrand">' + data[i].goodsBrand + '</abbr>';
//        html += '<abbr class="invenSeller">' + data[i].goodsMerchants + '</abbr>';
//        html += '<abbr style="width:300px;">';
//        html += '<select class="invenListSelect">';
//        html += '<option value="0" selected="selected">可选</option>';
//        html += '<option value="1">修改库存</option>';
//        html += '<option value="2">删除</option>';
//        html += '</select></abbr></dd>';
//        $("#"+ str).append(html);
//    }
//
//}
//function checkUndefined(str){
//    if( str == 'undefined' ){
//        str = '';
//    }
//    return str;
//}
//
//
//function jPagesGet(content,target){
//    $("#"+content).jPages({
//        containerID : target
//    });
//
//}


$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    var array = [];
    var checked_array = {};
    var outport_array = {};
    var goodsCheckedMap = new Map();

    checked_array.length = 0;
    array[0] = goodsInfoName;
    array[1] = goodsInfoItemNo;
    array[2] = brandId;
    array[3] = goodsInfoAdded;
    array[4] = typreId;
    array[5] = thirdId;


    //ajaxPages(total_elements,'../web/api/inventory/getInventoryGoods','itemContainer','holder',0,5);
    ajaxPages('../web/api/inventory/getInventoryGoods','itemContainer','holder',8,5,'','',array,function(pageId){
        var thisType = $(this).parent().parent().parent().attr("data-type");
        var thisCheckedArray = goodsCheckedMap.get(thisType);
        if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
            $(".accountNumber").each(function () {
                var _this = this;
                thisCheckedArray[pageId].map(function(orderCode){
                    if($(_this).parent().prev().val() == orderCode){
                        $(_this).prop("checked","checked");
                    }
                });
            });
        }
    });
    var time =  setInterval(show,200)
    function show(){
        $(".goodsInfoId").each(function(){
            var pageIndex=$("#holder span.current").html();
            var _this = this;
            var thisType = $(this).parent().parent().attr("data-type");
            var thisCheckedArray = goodsCheckedMap.get(thisType);
            if( typeof (thisCheckedArray) !="undefined" && thisCheckedArray[pageIndex] != undefined ) {
                thisCheckedArray[pageIndex].map(function (itId) {
                    if ($(_this).val() == itId) {
                        $(_this).siblings().children(".accountNumber").prop("checked", "checked");
                    }
                });
            }
        })
    }
    $(document).on("click",".accountNumber",function(){
        var pageIndex=$("#holder span.current").html();
        var orderCode=$(this).parent().prev().val();
        var goodsche=$(this).parent().parent().parent().attr("data-type");
        var newChecked = [];
        var checkedIndex = goodsCheckedMap.get(goodsche);
        if(typeof (checkedIndex) == "undefined"){
            newChecked[pageIndex] = new Array();
            if($(this).is(":checked")){
                newChecked[pageIndex].push(orderCode);
                goodsCheckedMap.put(goodsche,newChecked);
                console.log(goodsCheckedMap.get(goodsche)[pageIndex]);
            }
            else{
                checkedIndex[pageIndex].map(function(code){
                    if( code == orderCode ){
                        checkedIndex[pageIndex] = checkedIndex[pageIndex].remove(code);
                    }
                })
            }
        }else {
            if(checkedIndex[pageIndex] == undefined){
                newChecked[pageIndex] = new Array();
                if($(this).is(":checked")){
                    newChecked[pageIndex].push(orderCode);
                    checkedIndex.push(newChecked[pageIndex]);
                    goodsCheckedMap.put(goodsche,checkedIndex);
                    console.log(goodsCheckedMap.get(goodsche)[pageIndex]);
                }
                else{
                    newChecked[pageIndex].map(function(code){
                        if( code == orderCode ){
                            newChecked[pageIndex] = newChecked[pageIndex].remove(code);
                        }
                    })
                }
            }else {
                if($(this).is(":checked")){
                    checkedIndex[pageIndex].push(orderCode);
                    goodsCheckedMap.put(goodsche,checkedIndex);
                    console.log(goodsCheckedMap.get(goodsche)[pageIndex]);
                }
                else{
                    checkedIndex[pageIndex].map(function(code){
                        if( code == orderCode ){
                            checkedIndex[pageIndex] = checkedIndex[pageIndex].remove(code);
                        }
                    })
                }
            }

        }

    });

    $("#updateInvenNumConfirm").click(function(){
        var inventory = ( $("input[name='editInventory']").val() != '' ) ? $("input[name='editInventory']").val() : 'a';
        var goodsId = $("#goodsId").val();
        console.log(inventory)
            if(/^[0-9]*[1-9][0-9]*$/.test(inventory)){
            $.post("../web/api/inventory/editInventory",{
                goodsInfoId:goodsId,
                inventory:inventory
            },function(data){
                if( data.response == 'success' ){
                    discoverHtml();
                    var lival=$(".accOn").val()
                    if(lival=="0") {
                        ensure_alert( 'InventoryManager')
                    }else if(lival=="1"){
                        data_type_alert("修改库存成功","success")
                        $(".updateInvenNum").fadeOut(100);
                        $(".accountTable li[value='1']").trigger("click")
                    }
                }
                else{
                    window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
                }
            },'json');
            }else{
                data_type_alert("库存输入错误","error")
            }

    });

    $("#deleteComConfirm").click(function(){
        var goodsId = $("#goodsId").val();
        $.post("../web/api/inventory/deleteInventory",{
            goodsInfoId:goodsId
        },function(data){
            if( data.response == 'success' ){
                discoverHtml()
                ensure_alert('InventoryManager');
            }
            else{
                window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
            }
        },'json');
    });

    $("#itemContainer").click(function(){
        if( $(".allOpenShow").children("span").html() == "关闭搜索项"){
            $(".allOpenShow").children("span").html("展开搜索项")
            $(".allOpenShow").children("b").html("∨")
            $(".allOpenShow").parent("div").css("height","50px")
            $(".allOpenShow").parent("div").css("box-shadow","none")
            $(".allOpenShow").siblings().fadeOut(800)
        }
    })

    $("#search").click(function(){
        if($(this).parent().parent().siblings(".allOpenShow").children("span").html() == "关闭搜索项"){
            $(this).parent().parent().siblings(".allOpenShow").children("span").html("展开搜索项")
            $(this).parent().parent().siblings(".allOpenShow").children("b").html("∨")
            $(this).parent().parent().parent("div").css("height","50px")
            $(this).parent().parent().parent("div").css("box-shadow","none")
            $(this).parent().parent("ul").fadeOut(800)
        }
        checked_array = {};
        outport_array = {};
        checked_array.length = 0;

        goodsInfoName = $("input[name='memberNum']").val();
        goodsInfoItemNo = $("input[name='memberCust']").val();
        if ($("#brand_select option:selected").val() == '全部') {
            brandId = '';
        }
        else {
            brandId = $("#brand_select option:selected").val();
        }
        if ($("#thirdname_select option:selected").val() == '全部') {
            thirdId = '';
        }
        else {
            thirdId = $("#thirdname_select option:selected").val();
        }
        var goodsInfoType = $(".goodsInfoType").val()
        if(goodsInfoType == "全部"){
            goodsInfoType =""
        }
        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[2] = brandId;
        array[3] = goodsInfoAdded;
        array[4] = typreId;
        array[5] = thirdId;
        array[21] = goodsInfoType
        ajaxPages("../web/api/inventory/getInventoryGoods", "itemContainer", "holder", 8, 5, '', '', array,function(pageId){
            var thisType = $(this).attr("data-type");
            var thisCheckedArray = goodsCheckedMap.get(thisType);
            if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                $(".accountNumber").each(function () {
                    var _this = this;
                    thisCheckedArray[pageId].map(function(orderCode){
                        if($(_this).parent().prev().val() == orderCode){
                            $(_this).prop("checked","checked");
                        }
                    });
                });
            }
        });


    });
    $("#search2").click(function(){
        checked_array = {};
        outport_array = {};
        checked_array.length = 0;
        var goodsInfoType = $(".goodsInfoType").val()
        if(goodsInfoType == "全部"){
            goodsInfoType =""
        }
        goodsInfoName = $("input[name='memberNum']").val();
        goodsInfoItemNo = $("input[name='memberCust']").val();
        if ($("#brand_select option:selected").val() == '全部') {
            brandId = '';
        }
        else {
            brandId = $("#brand_select option:selected").val();
        }
        if ($("#thirdname_select option:selected").val() == '全部') {
            thirdId = '';
        }
        else {
            thirdId = $("#thirdname_select option:selected").val();
        }


        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[2] = brandId;
        array[3] = goodsInfoAdded;
        array[4] = typreId;
        array[5] = thirdId;
        array[21] = goodsInfoType
        ajaxPages("../web/api/inventory/getWarningGoods", "itemContainer_warning", "holder_warning", 8, 5, '', '', array,function(pageId){
            var thisType = $(this).attr("data-type");
            var thisCheckedArray = goodsCheckedMap.get(thisType);
            if(thisCheckedArray!=undefined && thisCheckedArray[pageId] != undefined ) {
                $(".accountNumber").each(function () {
                    var _this = this;
                    thisCheckedArray[pageId].map(function(orderCode){
                        if($(_this).parent().prev().val() == orderCode){
                            $(_this).prop("checked","checked");
                        }
                    });
                });
            }
        });

    });

    $("#outputAll").click(function(){
        var goodsInfoName = $("input[name='memberNum']").val();
        var goodsNumber = $("input[name='memberCust']").val();
        var goodsInfoType = $(".goodsInfoType").val()
        if(goodsInfoType == "全部"){
            goodsInfoType =""
        }
        var flag = $(".accOn").html();

        if( flag == '全部货品' ){
            window.location.href = '../web/api/exportExcel/inventoryGoodsDown?goodsInfoId=all' + '&goodsNumber=' + goodsNumber + '&goodsInfoName=' + goodsInfoName + '&goodsInfoType=' + goodsInfoType;
        }
        else{
            window.location.href = '../web/api/exportExcel/inventoryWarningGoodsDown?goodsInfoId=all' + '&goodsNumber=' + goodsNumber + '&goodsInfoName=' + goodsInfoName + '&goodsInfoType=' + goodsInfoType;
        }
    });

    $("#outputPart").click(function(){
        var flag = $(".accOn").html();
        var outType = $(".accOn").attr("value");

        var outArr = goodsCheckedMap.get(outType);
        var itemIds = '[';
        //for(var i = 0;i < outArr.length;i++){
        //    for(var j = 0;j < outport_array[i].length;j++){
        //        itemIds += '"' + outport_array[i][j] + '",';
        //    }
        //}
        for(var i=1;i<outArr.length;i++){
            for(var j=0; j < outArr[i].length;j++){
                itemIds += '"' + outArr[i][j] + '",';
            }
        }
        itemIds = itemIds.substring(0,itemIds.length - 1);
        itemIds += ']';

        console.log(itemIds);

        if(itemIds.length > 2){
            if( flag == '全部货品' ){
                window.location.href = '../web/api/exportExcel/inventoryGoodsDown?goodsInfoId=' + itemIds;
            }
            else{
                window.location.href = '../web/api/exportExcel/inventoryWarningGoodsDown?goodsInfoId=' + itemIds;
            }

        }
    });
});

function Map(){
    this.elements = new Array();

    //获取Map元素个数
    this.size = function() {
        return this.elements.length;
    },

        //判断Map是否为空
        this.isEmpty = function() {
            return (this.elements.length < 1);
        },

        //删除Map所有元素
        this.clear = function() {
            this.elements = new Array();
        },

        //向Map中增加元素（key, value)
        this.put = function(_key, _value) {
            if (this.containsKey(_key) == true) {
                if(this.containsValue(_value)){
                    if(this.remove(_key) == true){
                        this.elements.push( {
                            key : _key,
                            value : _value
                        });
                    }
                }else{
                    this.elements.push( {
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push( {
                    key : _key,
                    value : _value
                });
            }
        },

        //删除指定key的元素，成功返回true，失败返回false
        this.remove = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key){
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            }catch(e){
                bln = false;
            }
            return bln;
        },

        //获取指定key的元素值value，失败返回null
        this.get = function(_key) {
            try{
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        return this.elements[i].value;
                    }
                }
            }catch(e) {
                return null;
            }
        },

        //获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null
        this.element = function(_index) {
            if (_index < 0 || _index >= this.elements.length){
                return null;
            }
            return this.elements[_index];
        },

        //判断Map中是否含有指定key的元素
        this.containsKey = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key){
                        bln = true;
                    }
                }
            }catch(e) {
                bln = false;
            }
            return bln;
        },

        //判断Map中是否含有指定value的元素
        this.containsValue = function(_value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].value == _value){
                        bln = true;
                    }
                }
            }catch(e) {
                bln = false;
            }
            return bln;
        },

        //获取Map中所有key的数组（array）
        this.keys = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].key);
            }
            return arr;
        },

        //获取Map中所有value的数组（array）
        this.values = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].value);
            }
            return arr;
        };
};