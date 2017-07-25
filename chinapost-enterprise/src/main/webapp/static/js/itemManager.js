$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '1';
    var typreId = '';
    var thirdId = '';
    var array = [];
    var onlineShow =""
    var valetShow =""
    var enterpriseId =""
    var checked_array = {};
    var outport_array = {};

    checked_array.length = 0;
    var itemIds = '[';

    var selfchecked_array = {};
    var selfoutport_array = {};

    selfchecked_array.length = 0;
    var selfitemIds = '[';

    var mychecked_array = {};
    var myoutport_array = {};

    mychecked_array.length = 0;
    var myitemIds = '[';

    array[0] = goodsInfoName;
    array[1] = goodsInfoItemNo;
    array[2] = brandId;
    array[3] = goodsInfoAdded;
    array[4] = typreId;
    array[5] = thirdId;
    array[19] = onlineShow;
    array[20] = valetShow;
    ajaxPages("../web/api/goodsManager/getGoodsInfo","itemContainer","holder",7,5,'','',array,function(pageId){
        console.log(pageId)
        if( checked_array[pageId] != undefined ) {
            $(".inOut").next().each(function () {
                var _this = this;
                checked_array[pageId].map(function(itId){
                    if($(_this).val() == itId){
                        $(_this).parent().siblings().children("input[name='itemsListCheck']").prop("checked","checked");
                    }
                });
            });
        }else{
            console.log(111111111213123123123)
        }
        return isTop;
    });

    $(document).on("click",".itemsList dd input[name='itemsListCheck']",function(){
        var pageIndex=$("#holder span.current").html();
        var itemID=$(this).parent().siblings().children(".itemsInfoId").val();
        if( checked_array[pageIndex] == undefined ){
            checked_array[pageIndex] = new Array();
            checked_array.length++;
            outport_array[checked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            checked_array[pageIndex].push(itemID);
            outport_array[checked_array.length-1].push(itemID);
            console.log(pageIndex)
            console.log(checked_array[pageIndex],outport_array[checked_array.length-1]);
        }
        else{
            checked_array[pageIndex].map(function(id){
                if( id == itemID ){
                    checked_array[pageIndex] = checked_array[pageIndex].remove(id);
                    outport_array[pageIndex-1] = outport_array[pageIndex-1].remove(id);
                }
            })
        }
        console.log(checked_array)
    });


    $(document).on("click",".selfItemsList dd input[name='selfItemsList']",function(){
        var selfpageIndex=$("#selfHolder span.current").html();
        var selfitemID=$(this).parent().siblings().children(".itemsInfoId").val();
        if( selfchecked_array[selfpageIndex] == undefined ){
            selfchecked_array[selfpageIndex] = new Array();
            selfchecked_array.length++;
            selfoutport_array[selfchecked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            selfchecked_array[selfpageIndex].push(selfitemID);
            selfoutport_array[selfchecked_array.length-1].push(selfitemID);
            console.log(selfpageIndex)
            console.log(selfchecked_array[selfpageIndex],selfoutport_array[selfchecked_array.length-1]);
        }
        else{
            selfchecked_array[selfpageIndex].map(function(selfid){
                if( selfid == selfitemID ){
                    selfchecked_array[selfpageIndex] =  selfchecked_array[selfpageIndex].remove(selfid);
                    selfoutport_array[selfpageIndex-1] =  selfoutport_array[selfpageIndex-1].remove(selfid);
                }
            })
        }
        console.log(selfchecked_array)
    });

    $(document).on("click",".myItemsList dd input[name='myItemsList']",function(){
        var mypageIndex=$("#myHolder span.current").html();
        var myitemID=$(this).parent().siblings().children(".itemsInfoId").val();
        if( mychecked_array[mypageIndex] == undefined ){
            mychecked_array[mypageIndex] = new Array();
            mychecked_array.length++;
            myoutport_array[mychecked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            mychecked_array[mypageIndex].push(myitemID);
            myoutport_array[mychecked_array.length-1].push(myitemID);
            console.log(mypageIndex)
            console.log(mychecked_array[mypageIndex],myoutport_array[mychecked_array.length-1]);
        }
        else{
            mychecked_array[mypageIndex].map(function(myid){
                if( myid == myitemID ){
                    mychecked_array[mypageIndex] = mychecked_array[mypageIndex].remove(myid);
                    myoutport_array[mypageIndex-1] = myoutport_array[mypageIndex-1].remove(myid);
                }
            })
        }
        console.log(mychecked_array)
    });

    $(document).on("click",".inOut",function(){
        var goodsInfoId = $(this).next().val();
        var _this = this
        if( isTop ){
            if( $(this).val() == '否' )
                $.post("../web/api/goodsManager/shelves",{
                    goodsInfoId:goodsInfoId
                },function(data){
                    if ( data.response == 'success' ){
                        response_ensure_alert('success','操作成功',function(){
                           $(_this).val("是").css("background","#54a6de")
                        });
                    }
                    else{
                        response_ensure_alert('error','操作失败',function(){
                           window.location.href = "itemManager"
                        });
                    }
                });
            else if( $(this).val() == '是' ){
                $.post("../web/api/goodsManager/unshelves",{
                    goodsInfoId:goodsInfoId
                },function(data){
                    if ( data.response == 'success' ){
                        response_ensure_alert('success','操作成功',function(){
                            $(_this).val("否").css("background","#ff3300")
                        });
                    }
                    else{
                        response_ensure_alert('error','操作失败',function(){
                            window.location.href = "itemManager"
                        });
                    }
                });
            }
        }
        else{

        }

    });


    $(document).on("click",".onlineinOut",function(){
        var goodsInfoId = $(this).next().val();
        var _this = this
        var onlineinOut = $(this).val();
        if( isTop ) {
            if (onlineinOut == '是') {
                $.post("../web/api/goodsManager/updateShow", {
                    goodsInfoId: goodsInfoId,
                    onlineShow: false
                }, function (data) {
                    if (data.response == 'success') {
                        response_ensure_alert('success', '操作成功', function () {
                            $(_this).val("否").css("background", "#ff3300")
                        });
                    }
                    else {
                        response_ensure_alert('error', '操作失败', function () {
                            window.location.href = "itemManager"
                        });
                    }
                });
            } else {
                $.post("../web/api/goodsManager/updateShow", {
                    goodsInfoId: goodsInfoId,
                    onlineShow: true
                }, function (data) {
                    if (data.response == 'success') {
                        response_ensure_alert('success', '操作成功', function () {
                            $(_this).val("是").css("background", "#54a6de")
                        });
                    }
                    else {
                        response_ensure_alert('error', '操作失败', function () {
                            window.location.href = "itemManager"
                        });
                    }
                });
            }


        }else{

        }

    });

    $(document).on("click",".userinOut",function(){
        var goodsInfoId = $(this).next().val();
        var _this = this
        var userinout = $(this).val();
        if( isTop ){
            if( userinout == "否"){
                $.post("../web/api/goodsManager/updateShow",{
                    goodsInfoId:goodsInfoId,
                    valetShow:true
                },function(data){
                    if ( data.response == 'success' ){
                        response_ensure_alert('success','操作成功',function(){
                            $(_this).val("是").css("background","#54a6de")
                        });
                    }
                    else{
                        response_ensure_alert('error','操作失败',function(){
                            window.location.href = "itemManager"
                        });
                    }
                });
            }else{
                $.post("../web/api/goodsManager/updateShow",{
                    goodsInfoId:goodsInfoId,
                    valetShow:false
                },function(data){
                    if ( data.response == 'success' ){
                        response_ensure_alert('success','操作成功',function(){
                            $(_this).val("否").css("background","#ff3300")
                        });
                    }
                    else{
                        response_ensure_alert('error','操作失败',function(){
                            window.location.href = "itemManager"
                        });
                    }
                });
            }


        }
        else{

        }

    });

    $("#itemsSearch").click(function(){
        var check = $(".itemsTable li.on").val()
        if( check == "0"){
            goodsInfoName = $("input[name='itemManager_Name']").val();
            goodsInfoItemNo = $("input[name='itemManager_Number']").val();
            if( $("select[name='itemManager_Brand'] option:selected").val() == '全部' ){
                brandId = '';
            }
            else{
                brandId = $("select[name='itemManager_Brand'] option:selected").val();
            }
            if( $("select[name='itemManager_Shelves'] option:selected").val() == '全部' ){
                goodsInfoAdded = '';

            }
            else{
                goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
            }
            if( $("select[name='itemManager_Type'] option:selected").val() == '全部' ){
                typreId = '';
            }
            else{
                typreId = $("select[name='itemManager_Brand'] option:selected").val();
            }
            if( $("select[name='itemManager_ThirdName'] option:selected").val() == '全部' ){
                thirdId = '';
            }
            else{
                thirdId = $("select[name='itemManager_ThirdName'] option:selected").val();
            }
            if( $("select[name='itemManager_onlineShow'] option:selected").val() == '全部' ){
                onlineShow = '';
            }
            else{
                onlineShow = $("select[name='itemManager_onlineShow'] option:selected").val();
            }
            if( $("select[name='itemManager_valetShow'] option:selected").val() == '全部' ){
                valetShow = '';
            }
            else{
                valetShow = $("select[name='itemManager_valetShow'] option:selected").val();
            }
            if(onlineShow == "1"){
                onlineShow = "true"
            }else if(onlineShow == "0"){
                onlineShow = "false"
            }
            if(valetShow == "1"){
                valetShow = "true"
            }else if(valetShow == "0"){
                valetShow = "false"
            }
            array[0] = goodsInfoName;
            array[1] = goodsInfoItemNo;
            array[2] = brandId;
            array[3] = goodsInfoAdded;
            array[4] = typreId;
            array[5] = thirdId;
            array[19] = onlineShow;
            array[20] = valetShow;
            ajaxPages("../web/api/goodsManager/getGoodsInfo","itemContainer","holder",7,5,'','',array,function(pageId){
                if( checked_array[pageId] != undefined ) {
                    $(".inOut").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(itId){
                            if($(_this).val() == itId){
                                $(_this).parent().siblings().children("input[name='itemsListCheck']").prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
        }
        if( check == "1"){
            $("#NewEnterpriseIdChoosen").val($(".enterpriseIdChoosen").val())
            enterpriseId = $("#NewEnterpriseIdChoosen").val()
            goodsInfoName = $("input[name='itemManager_Name']").val();
            goodsInfoItemNo = $("input[name='itemManager_Number']").val();
            if( $("select[name='itemManager_Brand'] option:selected").val() == '全部' ){
                brandId = '';
            }
            else{
                brandId = $("select[name='itemManager_Brand'] option:selected").val();
            }
            if( $("select[name='itemManager_Shelves'] option:selected").val() == '全部' ){
                goodsInfoAdded = '';

            }
            else{
                goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
            }
            if( $("select[name='itemManager_Type'] option:selected").val() == '全部' ){
                typreId = '';
            }
            else{
                typreId = $("select[name='itemManager_Brand'] option:selected").val();
            }
            if( $("select[name='itemManager_ThirdName'] option:selected").val() == '全部' ){
                thirdId = '';
            }
            else{
                thirdId = $("select[name='itemManager_ThirdName'] option:selected").val();
            }

            array[0] = goodsInfoName;
            array[1] = goodsInfoItemNo;
            array[2] = brandId;
            array[3] = goodsInfoAdded;
            array[4] = typreId;
            array[5] = thirdId;
            array[16] = enterpriseId;
            ajaxPages("../web/api/goodsManager/getSettleGoodsByEnterpriseId","selfItemContainer","selfHolder","selfItems",5,'','',array)
        }
        if( check == "2"){
            goodsInfoName = $("input[name='itemManager_Name']").val();
            goodsInfoItemNo = $("input[name='itemManager_Number']").val();
            if( $("select[name='itemManager_Shelves'] option:selected").val() == '全部' ){
                goodsInfoAdded = '';
            }
            else{
                goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
            }
            array[0] = goodsInfoName;
            array[1] = goodsInfoItemNo;
            array[3] = goodsInfoAdded;

            ajaxPages("../web/api/goodsManager/getSettleGoods","myItemContainer","myHolder","myItems",5,'','',array)
        }
    });

    $("#outputAll").click(function(){
      var val = $(".itemsTable li.on").val()
        if( val == "0"){
            var inventoryName = $("input[name='itemManager_Name']").val();
            var inventoryNumber = $("input[name='itemManager_Number']").val();
            var inOut = $("select[name='itemManager_Shelves'] option:selected").val();
            if( inOut == '全部'){
                inOut = '';
            }
            var onlineShow = $("select[name='itemManager_onlineShow']").val()
            if( onlineShow == "1"){
                onlineShow = true
            }else if(onlineShow == "0"){
                onlineShow = false
            }else{
                onlineShow =""
            }
            var valetShow = $("select[name='itemManager_valetShow']").val()
            if( valetShow == "1"){
                valetShow = true
            }else if(valetShow == "0"){
                valetShow = false
            }else{
                valetShow =""
            }
            window.location.href = '../web/api/exportExcel/goodsManagerDown?goodsInfoId=' + 'all&goodsInfoName=' + inventoryName + "&goodsInfoItemNo=" + inventoryNumber + "&goodsInfoAdded=" + inOut + "&onlineShow=" + onlineShow + "&valetShow=" + valetShow;
        }else if(val == "1"){
            var inventoryName = $("input[name='itemManager_Name']").val();
            var inventoryNumber = $("input[name='itemManager_Number']").val();
            var inOut = $("select[name='itemManager_Shelves'] option:selected").val();
            var enterPriseId = $("#NewEnterpriseIdChoosen").val()
            if( inOut == '全部'){
                inOut = '';
            }
            window.location.href = '../web/api/exportExcel/SettleGoodsDown?goodsInfoId=' + 'all&goodsInfoName=' + inventoryName + "&goodsInfoItemNo=" + inventoryNumber + "&goodsInfoAdded=" + inOut +"&enterpriseId=" + enterPriseId;
        }
        else if(val == "2"){
            var inventoryName = $("input[name='itemManager_Name']").val();
            var inventoryNumber = $("input[name='itemManager_Number']").val();
            var inOut = $("select[name='itemManager_Shelves'] option:selected").val();
            if( inOut == '全部'){
                inOut = '';
            }
            window.location.href = '../web/api/exportExcel/mySettleGoodsDown?goodsInfoId=' + 'all&goodsInfoName=' + inventoryName + "&goodsInfoItemNo=" + inventoryNumber + "&goodsInfoAdded=" + inOut;
        }
    });
    $("#outputPart").click(function(){
        var val = $(".itemsTable li.on").val()
       if(val == "0"){
           var itemIds = '[';
           for(var i = 0;i < checked_array.length;i++){
               for(var j = 0;j < outport_array[i].length;j++){
                   itemIds += '"' + outport_array[i][j] + '",';
               }
           }
           itemIds = itemIds.substring(0,itemIds.length - 1);
           itemIds += ']';
           console.log(itemIds)
           if(itemIds.length > 2){
               window.location.href = '../web/api/exportExcel/goodsManagerDown?goodsInfoId=' + itemIds;
           }
       }else if(val == "1"){
           var selfitemIds = '[';
           for(var i = 0;i < selfchecked_array.length;i++){
               for(var j = 0;j < selfoutport_array[i].length;j++){
                   selfitemIds += '"' + selfoutport_array[i][j] + '",';
               }
           }
           selfitemIds = selfitemIds.substring(0,selfitemIds.length - 1);
           selfitemIds += ']';
           if(selfitemIds.length > 2){
               window.location.href = '../web/api/exportExcel/SettleGoodsDown?goodsInfoId=' + selfitemIds;
           }
       }
       else if(val == "2"){
           var myitemIds = '[';
           for(var i = 0;i < mychecked_array.length;i++){
               for(var j = 0;j < myoutport_array[i].length;j++){
                   myitemIds += '"' + myoutport_array[i][j] + '",';
               }
           }
            myitemIds = myitemIds.substring(0,myitemIds.length - 1);
            myitemIds += ']';
           if(myitemIds.length > 2){
               window.location.href = '../web/api/exportExcel/mySettleGoodsDown?goodsInfoId=' + myitemIds;
           }
       }
    });
    $(document).on("click",".itemsTable ul li",function(){
        $(this).addClass("on").siblings().removeClass("on")
        $(".itemsTable dl").hide()
        var value = $(this).val()
        if(value == "0"){
            $("#selfPickItem").hide()
            $(".itemsList").show()
            console.log(11111111)
            $(".checkplace").hide()
            $(".checkplace2").show()
            var goodsInfoName = $("input[name='itemManager_Name']").val();
            var goodsInfoItemNo = $("input[name='itemManager_Number']").val();
            var brandId = '';
            var goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
            var typreId = '';
            var thirdId = '';
            var array = [];
            var onlineShow = $("select[name='itemManager_onlineShow']").val();
            var valetShow = $("select[name='itemManager_valetShow']").val();
            if( goodsInfoAdded == '全部'){
                goodsInfoAdded = '';
            }
            if( onlineShow == "1"){
                onlineShow = true;
            }else if(onlineShow == "0"){
                onlineShow = false;
            }else{
                onlineShow ="";
            }
            if( valetShow == "1"){
                valetShow = true;
            }else if(valetShow == "0"){
                valetShow = false;
            }else{
                valetShow ="";
            }
            array[0] = goodsInfoName;
            array[1] = goodsInfoItemNo;
            array[2] = brandId;
            array[3] = goodsInfoAdded;
            array[4] = typreId;
            array[5] = thirdId;
            array[19] = onlineShow;
            array[20] = valetShow;
            ajaxPages("../web/api/goodsManager/getGoodsInfo","itemContainer","holder",7,5,'','',array,function(pageId){
                console.log(pageId)
                if( checked_array[pageId] != undefined ) {
                    $(".itemsInfoId").each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(itId){

                            if($(_this).val() == itId){

                                $(_this).parent().siblings().children("input[name='itemsListCheck']").prop("checked","checked");
                            }
                        });
                    });
                }
                return isTop;
            });
        }
        else if(value == "1"){
            $("#selfPickItem").hide()
            $(".selfItemsList").show()
            $(".checkplace2").hide()
            var enterpriseId = $("#NewEnterpriseIdChoosen").val();
            var goodsInfoName = $("input[name='itemManager_Name']").val();
            var goodsInfoItemNo = $("input[name='itemManager_Number']").val();
            var goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
            if( goodsInfoAdded == '全部'){
                goodsInfoAdded = '';
            }
            var brandId = '';
            var typreId = '';
            var thirdId = '';
            var array = [];
            array[0] = goodsInfoName;
            array[1] = goodsInfoItemNo;
            array[2] = brandId;
            array[3] = goodsInfoAdded;
            array[4] = typreId;
            array[5] = thirdId;
            array[16] = enterpriseId
            ajaxPages("../web/api/goodsManager/getSettleGoodsByEnterpriseId","selfItemContainer","selfHolder","selfItems",5,'','',array,function(pageId){
                console.log(pageId)
                if( checked_array[pageId] != undefined ) {
                    $(".inOut").next().each(function () {
                        var _this = this;
                        checked_array[pageId].map(function(itId){
                            if($(_this).val() == itId){
                                $(_this).parent().siblings().children("input[name='itemsListCheck']").prop("checked","checked");
                            }
                        });
                    });
                }else{
                }
                return isTop;
            })
        }else if(value == "2"){
            $(".myItemsList").show()
            $("#selfPickItem").show()
            $(".checkplace").hide()
            $(".checkplace2").hide()
            var goodsInfoName = $("input[name='itemManager_Name']").val();
            var goodsInfoItemNo = $("input[name='itemManager_Number']").val();
            var goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
            if( goodsInfoAdded == '全部'){
                goodsInfoAdded = '';
            }
            var brandId = '';
            var typreId = '';
            var thirdId = '';
            var array = [];
            array[0] = goodsInfoName;
            array[1] = goodsInfoItemNo;
            array[2] = brandId;
            array[3] = goodsInfoAdded;
            array[4] = typreId;
            array[5] = thirdId;
            ajaxPages("../web/api/goodsManager/getSettleGoods","myItemContainer","myHolder","myItems",5,'','',array)
        }
    })
    var time =  setInterval(show,200)
    var selftime =  setInterval(selfshow,200)
    var mytime =  setInterval(myshow,200)
    function show(){
        $(".itemsInfoId").each(function(){
            var pageIndex=$("#holder span.current").html();
            var _this = this;
            if( checked_array[pageIndex] != undefined ) {
                checked_array[pageIndex].map(function (itId) {
                    if ($(_this).val() == itId) {
                        $(_this).parent().siblings().children("input[name='itemsListCheck']").prop("checked", "checked");
                    }
                });
            }
        })
    }
    function selfshow(){
        $(".itemsInfoId").each(function(){
            var selfpageIndex=$("#selfHolder span.current").html();
            var _this = this;
            if( selfchecked_array[selfpageIndex] != undefined ) {
                selfchecked_array[selfpageIndex].map(function (selfitId) {
                    if ($(_this).val() == selfitId) {
                        $(_this).parent().siblings().children("input[name='selfItemsList']").prop("checked", "checked");
                    }
                });
            }
        })
    }
    function myshow(){
        $(".itemsInfoId").each(function(){
            var mypageIndex=$("#holder span.current").html();
            var _this = this;
            if( mychecked_array[mypageIndex] != undefined ) {
                mychecked_array[mypageIndex].map(function (myitId) {
                    if ($(_this).val() == myitId) {
                        $(_this).parent().siblings().children("input[name='myItemsList']").prop("checked", "checked");
                    }
                });
            }
        })
    }
});

