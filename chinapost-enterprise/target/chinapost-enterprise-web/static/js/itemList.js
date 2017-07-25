$(document).ready(function(){
    var goodsInfoName = '';
    var lowPrice = '';
    var highPrice = '';
    var array = [];
    array[0] = '';array[1] = '';array[2] = '';array[3] = '';array[4] = '';array[5] = '';
    ajaxPages("../web/api/valet/getNetWorkGoods","itemContainer","holder",9,10,'','',array);
    $(".a_href").click(function(){
        var str = $(this).html();
        str = str.split('-');
        //if( str.length > 1 ){
        //    $("#input_low").val(str[0]);
        //    $("#input_high").val(str[1]);
        //}
        //else{
        //    $("#input_low").val(str[0].substring(0,7));
        //    $("#input_high").val('');
        //}
        goodsInfoName = $("#itemText").val();
        lowPrice = str[0];
        highPrice = ( str[1] == undefined ) ? '': str[1];
        if(highPrice == "以上"){
            highPrice = ""
        }
        array[0] = goodsInfoName;
        console.log(lowPrice,typeof(lowPrice));
        array[6] = ( isNaN(lowPrice) ) ? 1875000 : lowPrice;
        array[7] = highPrice;
        if($("input[name='selfItemList']").is(":checked")){
            ajaxPages("../web/api/valet/getNetWorkGoods","itemContainer","holder",9,10,'','',array);
        }else{
            ajaxPages("../web/api/valet/getGoods","itemContainer","holder",9,10,'','',array);
        }

    });

    $("#itemSearch").click(function(){
        goodsInfoName = $("#itemText").val();
        var price = $("#searchItemchooseDown").html()
        var arg = price.split("-");

        if( isNaN(arg[0]) ){
            arg[0] =""
        }
        lowPrice = (arg[0] == undefined) ? "" : arg[0];
        highPrice = (arg[1] == undefined) ? "" : arg[1];
        console.log(lowPrice,highPrice)
        if(highPrice == "以上"){
            highPrice =""
        }
        array[0] = goodsInfoName;
        array[6] = lowPrice;
        array[7] = highPrice;
        if($("input[name='selfItemList']").is(":checked")){
            console.log(1111111111111111)
            ajaxPages("../web/api/valet/getNetWorkGoods","itemContainer","holder",9,10,'','',array);
        }else{
            ajaxPages("../web/api/valet/getGoods","itemContainer","holder",9,10,'','',array);
        }

    });
    $("#itemSearch3").click(function(){
        array[0] = "";
        array[6] = "";
        array[7] = "";
        if($("input[name='selfItemList']").is(":checked")){
            console.log(1111111111111111)
            ajaxPages("../web/api/valet/getNetWorkGoods","itemContainer","holder",9,10,'','',array);
        }else{
            ajaxPages("../web/api/valet/getGoods","itemContainer","holder",9,10,'','',array);
        }
    })
    $("#itemSearch2").click(function(){
        goodsInfoName = $("#itemText").val();
        lowPrice = $("#input_low").val();
        highPrice = $("#input_high").val();

        array[0] = goodsInfoName;
        array[6] = lowPrice;
        array[7] = highPrice;
        if($("input[name='selfItemList']").is(":checked")){
            console.log(1111111111111111)
            ajaxPages("../web/api/valet/getNetWorkGoods","itemContainer","holder",9,10,'','',array);
        }else{
            ajaxPages("../web/api/valet/getGoods","itemContainer","holder",9,10,'','',array);
        }

        if(lowPrice == "" && highPrice == ""){
            $("#searchItemchooseDown").html("全部")
        }
        else if(highPrice == ""){
            $("#searchItemchooseDown").html(lowPrice + "-以上")
        }
        else if( lowPrice == ""){
            lowPrice =0
            $("#searchItemchooseDown").html( lowPrice + "-" + highPrice)
        }
        else{
            $("#searchItemchooseDown").html( lowPrice + "-" + highPrice )
        }
    });
    $(document).on("click","input[name='selfItemList']",function(){
        goodsInfoName = $("#itemText").val();
        lowPrice = $("#input_low").val();
        highPrice = $("#input_high").val();

        array[0] = goodsInfoName;
        array[6] = lowPrice;
        array[7] = highPrice;
        if($("input[name='selfItemList']").is(":checked")){
            console.log(1111111111111111)
            ajaxPages("../web/api/valet/getNetWorkGoods","itemContainer","holder",9,10,'','',array);
        }else{
            ajaxPages("../web/api/valet/getGoods","itemContainer","holder",9,10,'','',array);
        }
    });
});