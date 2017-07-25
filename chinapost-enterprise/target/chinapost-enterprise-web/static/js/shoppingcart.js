$(document).ready(function(){
   if( sessionStorage.getItem("ShoppingCart") != undefined && sessionStorage.getItem("ShoppingCart")!= '' ){
       var sc = JSON.parse(sessionStorage.getItem("ShoppingCart"));
       sc.map(function(obj){
           var html = '<dd>';
           html += '<abbr class="itemDetail">';
           console.log(obj.isChecked);
           if( obj.isChecked ){
               html += '<input type="checkbox" checked="checked" name="chooseList">';
           }
           else{
               html += '<input type="checkbox" name="chooseList">';
           }
           html += '<img width="80" height="80" src="' + obj.imgSrc + '">';
           html += '<span title="'+ obj.goodName +'">' + obj.goodName + '</span></abbr>';
           html += '<abbr class="itemPrice"><span>' + addCommas(obj.price.replace(",","").replace(",","").replace(",",""),2) + '</span>邮豆</abbr>';
           html += '<abbr class="checkItemNum">';
           html += '<input class="itemReduce" type="button" value="-">';
           html += '<input value="' + obj.count + '" maxlength="4" data-goodId="' + obj.goodId + '"class="itemNumber" type="text">';
           html += '<input class="itemAdd" type="button" value="+"></abbr>';
           html += '<abbr class="sumNumber"><span style="color: #ff3300;">' + addCommas(parseInt(obj.count) * parseInt(obj.price.replace(",","").replace(",","").replace(",","")),2) + '</span>邮豆</abbr></dd>'

           $("#listDetail dl").append(html);
       });
   }

    $(document).on("click",".itemReduce",function(){
        $(this).next().trigger("change");
    });
    $(document).on("click",".itemAdd",function(){
        $(this).prev().trigger("change");
    });


    $(document).on("change",".itemNumber",function(){
        var val = parseInt($(this).val());
        if( isNaN(val) ){

        }
        else{
            var goodId = $(this).data("goodid");
            updateShoppingCart(goodId,val);
        }
    });

    $(document).on("change","input[name='chooseList']",function(){
        var goodId = $(this).parent().siblings(".checkItemNum").find(".itemNumber").data("goodid");
        var isChecked = $(this).prop("checked");
        updateShoppingCart(goodId,'',isChecked);

    });

    $("#itemClear").click(function(){
        var goodsInfo = getGoodsJsonFromSS();
        sessionStorage.setItem("goodsInfo",goodsInfo);
        if( goodsInfo == '' ){
            response_ensure_alert("error","请先选择商品");
        }
        else{
            window.location.href = 'OrderSure?idsJson=' + goodsInfo;
        }
    });
});

