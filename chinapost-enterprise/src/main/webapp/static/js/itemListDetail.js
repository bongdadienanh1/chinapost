$(document).ready(function(){
    //$.post("../web/api/valet/getGoodsInfo",{
    //    goodsInfoId:goodsInfoId
    //},function( data ){
    //    if( data.response == 'success' ){
    //        $("#itemPrice").html( addCommas(data.data.goodsInfoPreferPrice,2) );
    //    }
    //});

    var isEnd = sessionStorage.getItem("isEnd")
    if(isEnd == "1"){
        var intervalTime = 0;
        var intervalFlag = true;
        console.log(11111)
        setInterval(function(){
            if( intervalTime == 300000){
                intervalTime = 0;
                sessionStorage.setItem("ShoppingCart",'');
                window.location.href = 'itemList'
            }
            else{
                if( intervalFlag == false ){
                    intervalTime = 0;
                    intervalFlag = true;
                }
            }
            intervalTime = intervalTime + 1000;
        },1000);
    }

    $("body").click(function(){
        intervalFlag = false;
    });
    $("body").mousemove(function(){
        intervalFlag = false;
    });
    $("body").keydown(function(){
        intervalFlag = false;
    })

    var initType=$(".itemColorOn").html()
    $("#itemColor").html(initType)
    $(".imglist span img").mouseover(function(){
        var src=$(this).attr("src");
        $(this).parent().parent().parent().siblings(".showImg").children("img").prop("src",src);
    })
    $(".changeRight").click(function(){
        var left=parseInt($(this).siblings("s").children("span").css("left"));
        var imgNum=$(this).siblings("s").children("span").children("img").length
        console.log(imgNum)
        if(left>0-(imgNum-3)*60){
            $(this).siblings("s").children("span").animate({"left":left-60},100);
        }
    })
    $(".changeLeft").click(function(){
        var left=parseInt($(this).siblings("s").children("span").css("left"));
        console.log(left)
        if(left<0){
            $(this).siblings("s").children("span").animate({"left":left+60},100);
        }
    })
    var type=""
    $(document).on("click",".detail",function(){
        $(this).addClass("itemColorOn").siblings().removeClass("itemColorOn")
            $(".itemColorOn").each(function(){
                type += '"'+ $(this).html() + '"' + " "
            })
        $("#itemColor").html(type);
        type = ""
    })

    $("#itemNumAdd").click(function(){
        var num=parseInt($(this).siblings("input[name='itemNum']").val())
        $("input[name='itemNum']").val(num+1);
    })
    $("#itemNumReduce").click(function(){
        var num=parseInt($(this).siblings("input[name='itemNum']").val())
        if(num>1){
            $("input[name='itemNum']").val(num-1);
        }
    })
    $(".itemListbodyHead li").click(function(){
        $(this).addClass("listOn").siblings().removeClass("listOn");
        var lival=$(this).val()
        if(lival==0){
            $(".itemListdetail").hide()
            $(".itemListIntorduce").show()
        }else if(lival==1){
            $(".itemListdetail").show()
            $(".itemListIntorduce").hide()
        }
    });
    $("input[name='itemNum']").blur(function(){
        var str = /^[1-9]*[1-9][0-9]*$/;
        var count = $("input[name='itemNum']").val();
        if( !str.test(count) ) {
            response_ensure_alert("error","请输入正确的购买数量",function(){
                $("input[name='itemNum']").val(1)
            });
        }
    })
    $("#itemBuy").click(function(){
        var id = goodsInfoId;
        var str = /^[1-9]*[1-9][0-9]*$/;
        var count = $("input[name='itemNum']").val();
        var idsJson = '{"' + id + '":' + count + '}';
        if( str.test(count) ) {
            console.log(idsJson);
            window.location.href = 'OrderSure?idsJson=' + idsJson;
        }else{
           response_ensure_alert("error","请输入正确的购买数量",function(){
               $("input[name='itemNum']").val(1)
           });
        }
        });

    $("#addshop").click(function(){
        var goodId = goodsInfoId;
        var count = $("input[name='itemNum']").val();
        var name = $(".itemName").html();
        var imgSrc = $(".showImg img").prop("src");
        var price = $("#itemPrice").html().replace(",","").replace(",","").replace(",","");
        insertShoppingCart(goodId,count,name,price,imgSrc);
        var okArr = [];
            okArr[0] = '查看详情';
            okArr[1] = function(){
                window.location.href = 'shopListCart';
            };
        var cancelArr = [];
            cancelArr[0] = '返回';
            cancelArr[1] = function(){
                window.location.href = "itemList"
            };
        selfPick_alert('success',"添加成功",okArr,cancelArr);
    });
});