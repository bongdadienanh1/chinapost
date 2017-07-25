$(document).ready(function(){
    var editor1;
    KindEditor.ready(function (K) {
        editor1 = K.create('textarea[name="goods_desc"]',
            {
                width: "100%",
                height: "425px",
                cssPath: '../static/js/kindeditor/plugins/code/prettify.css', uploadJson: '../web/imgUpload',allowImageUpload : true,
                fileManagerJson: '../static/js/kindeditor/jsp/file_manager_json.jsp', allowFileManager: true,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
        prettyPrint();
    });
    $.post("../web/api/current/getInfo",{
    },function(data){
        if( data.response == 'success' ){
            if(data.data.identification != undefined){
                $("#accountNumVal").val(data.data.identification)
            }else{
                $("#accountNumVal").val("机构标识")
            }
        }
        else{
            response_ensure_alert('error',data.data.text,function(){

            });
        }
    },'json');
    $.post("../web/api/goodsManager/queryCateByCatIdAndName",{
        catId:0
    },function(data){
        if( data.response == 'success' ){
            var html = ""
            data.data.map(function(object){
                html += '<li><span>'+ object.catName +'</span><input type="hidden" value="' + object.catId + '"></li>'
            })
            $("#firstContain").empty().append(html)
        }
        else{
            response_ensure_alert('error',data.data.text,function(){

            });
        }
    },'json');
    $("#itemName").blur(function(){
        if($(this).val() != ""){
            $(this).parent().siblings("b").remove()
            if($(this).val().length <3) {
                var html= "<b style='color: #ff3300;'>最少三位数</b>"
                $(this).parent().siblings("b").remove()
                $(this).parent().after(html);
                $("#itemName").focus()
            }else{
                $(this).parent().siblings("b").remove()
            }
        }else{
            var html= "<b style='color: #ff3300;'>不可为空</b>"
            $(this).parent().siblings("b").remove()
            $(this).parent().after(html);
            $("#itemName").focus()
        }
    })
    $("#priceA").blur(function(){
        var val = $(this).val()
        var bigInt = changeNum(val)
        var html = '<div class="bigInt">'+ bigInt +'</div>'
        if( val == ""){
            $(this).siblings(".bigInt").remove()
        }else{
            $(this).siblings(".bigInt").remove()
            $(this).after(html)
        }
        if($(this).val() != ""){
           var check = checkNum($(this).val())
            if( check ){
                if(!isNaN($(this).val())){
                    $(this).parent().siblings("b").remove()
                    var val = $(this).val().split(".")
                    if(val[1] == undefined){
                        val[1] = ""
                    }
                    if( val[1].length > 2) {
                        response_ensure_alert("error","小数点后最多2位数",function(){
                            $("#priceA").focus()
                        })
                    }
                }else{
                    var html= "<b style='color: #ff3300;'>请输入数字</b>"
                    $(this).parent().siblings("b").remove()
                    $(this).parent().after(html);
                    $("#priceA").focus()
                }

            }else{
                var html= "<b style='color: #ff3300;'>数字不规范</b>"
                $(this).parent().siblings("b").remove()
                $(this).parent().after(html);
                $("#priceA").focus()
            }

        }else{
            var html= "<b style='color: #ff3300;'>不可为空</b>"
            $(this).parent().siblings("b").remove()
            $(this).parent().after(html);
            $("#priceA").focus()
        }
    })
    $("#priceB").blur(function(){
        var val = $(this).val()
        var bigInt = changeNum(val)
        var html = '<div class="bigInt">'+ bigInt +'</div>'
        if( val == ""){
            $(this).siblings(".bigInt").remove()
        }else{
            $(this).siblings(".bigInt").remove()
            $(this).after(html)
        }
       if($(this).val()!=""){
           var check = checkNum($(this).val())
           if( check ){
           if(!isNaN($(this).val())) {
               $(this).parent().siblings("b").remove()
               var val = $(this).val().split(".")
               if (val[1] == undefined) {
                   val[1] = ""
               }
               if (val[1].length > 2) {
                   response_ensure_alert("error", "小数点后最多2位数", function () {
                       $("#priceB").focus()
                   })
               }
               if(parseFloat($(this).val()) > 9999){
                   var html= "<b style='color: #ff3300;'>结算价应小于10000</b>"
                   $(this).parent().siblings("b").remove()
                   $(this).parent().after(html);
                   $("#priceB").focus()
               }
           }else{
               var html= "<b style='color: #ff3300;'>数字不规范</b>"
               $(this).parent().siblings("b").remove()
               $(this).parent().after(html);
               $("#priceA").focus()
           }
           }else{
               var html= "<b style='color: #ff3300;'>请输入数字</b>"
               $(this).parent().siblings("b").remove()
               $(this).parent().after(html);
               $("#priceB").focus()
           }

       }else{
           var html= "<b style='color: #ff3300;'>不可为空</b>"
           $(this).parent().siblings("b").remove()
           $(this).parent().after(html);
           $("#priceB").focus()
       }
    })
    $("#addStandardDetail").draggable()
    $("#chooseOneStepOne").click(function(){
        var catName = $(this).siblings("input").val()
        $.post("../web/api/goodsManager/queryCateByCatIdAndName",{
            cateName:catName
        },function(data){
            if( data.response == 'success' ){
                var html = ""
                data.data.map(function(object){
                    html += '<li><span>'+ object.catName +'</span><input type="hidden" value="' + object.catId + '"></li>'
                })
                $("#firstContain").empty().append(html)
                $("#thirdContain").empty()
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
    })

    $("#chooseOneStepTwo").click(function(){
        var catId = $("#firstContain li.chooseOneStepFirst input").val()
        console.log(catId)
        var catName = $(this).siblings("input").val()
        if(catId == undefined){
            response_ensure_alert("error","请先选择上一级物品")
        }else{
        $.post("../web/api/goodsManager/queryCateByCatIdAndName",{
            catId:catId,
            cateName:catName
        },function(data){
            if( data.response == 'success' ){
                var html = ""
                data.data.map(function(object){
                    html += '<li><span>'+ object.catName +'</span><input type="hidden" value="' + object.catId + '"></li>'
                })
                $("#secondContain").empty().append(html)
                $("#thirdContain").empty()
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
        }
    })

    $("#chooseOneStepThree").click(function(){
        var catId = $("#secondContain li.chooseOneStepTwo input").val()
        var catName = $(this).siblings("input").val()
        if(catId == undefined){
            response_ensure_alert("error","请先选择上一级物品")
        }else{
            $.post("../web/api/goodsManager/queryCateByCatIdAndName",{
                catId:catId,
                cateName:catName
            },function(data){
                if( data.response == 'success' ){
                    var html = ""
                    data.data.map(function(object){
                        html += '<li><span>'+ object.catName +'</span><input type="hidden" value="' + object.catId + '"></li>'
                    })
                    $("#thirdContain").empty().append(html)
                }
                else{
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');
        }
    })

    $(document).on("click","#chooseOneStep div:nth-child(1) li",function(){
        $(this).addClass("chooseOneStepFirst").siblings().removeClass("chooseOneStepFirst")
        $("#chooseOneStepRoad span:nth-child(1)").html($(this).html() + " > ")
        $("#chooseOneStepRoad span:nth-child(2),#chooseOneStepRoad span:nth-child(3)").html("")
        $.post("../web/api/goodsManager/queryCateByCatIdAndName",{
            catId:$(this).children("input").val()
        },function(data){
            if( data.response == 'success' ){
                var html = ""
                data.data.map(function(object){
                    html += '<li><span>'+ object.catName +'</span><input type="hidden" value="' + object.catId + '"></li>'
                })
                $("#secondContain").empty().append(html)
                $("#thirdContain").empty()
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
    })
    $(document).on("click","#chooseOneStep div:nth-child(2) li",function(){
        $(this).addClass("chooseOneStepTwo").siblings().removeClass("chooseOneStepTwo")
        $("#chooseOneStepRoad span:nth-child(2)").html($(this).html() + " > ")
        $("#chooseOneStepRoad span:nth-child(3)").html("")
        $.post("../web/api/goodsManager/queryCateByCatIdAndName",{
            catId:$(this).children("input").val()
        },function(data){
            if( data.response == 'success' ){
                var html = ""
                data.data.map(function(object){
                    html += '<li><span>'+ object.catName +'</span><input type="hidden" value="' + object.catId + '"></li>'
                })
                $("#thirdContain").empty().append(html)
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
    })
    $(document).on("click","#chooseOneStep div:nth-child(3) li",function(){
        $(this).addClass("chooseOneStepThree").siblings().removeClass("chooseOneStepThree")
        $("#chooseOneStepRoad span:nth-child(3)").html($(this).html() )
    })

    $("#goToStepTwo").click(function(){
        var catId = $("#thirdContain li.chooseOneStepThree input").val()
        var cateName = $("#thirdContain li.chooseOneStepThree span").html()
        if(catId != undefined && cateName != undefined){
            $("#chooseOneStep").hide()
            $("#chooseTwoStep").show()
            $("#chooseThreeStep").hide()
            $("#selfTitle").html("基本信息")
            $.post("../web/api/goodsManager/queryAllBrands",{
                catId:0
            },function(data){
                if( data.response == 'success' ){
                    var html = ""
                    data.data.map(function(object){
                        html += '<dd data-id="'+ object.brandId +'">'+ object.brandName +'</dd>'
                    })
                    $("#itemBrand").append(html)
                }
                else{
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');

        }else{
            response_ensure_alert("error","请选择商品分类")
        }

    })
    $("#backToStepOne").click(function(){
        $("#chooseOneStep").show()
        $("#chooseTwoStep").hide()
        $("#chooseThreeStep").hide()
        $("#selfTitle").html("商品分类")
    })
    $("#backToStepTwo").click(function(){
        $("#chooseOneStep").hide()
        $("#chooseTwoStep").show()
        $("#chooseThreeStep").hide()
        $("#selfTitle").html("基本信息")
    })

    $("#goToStepThree").click(function(){
        var itemName = $("#itemName").val()
        var priceA = $("#priceA").val()
        var priceB = $("#priceB").val()
        var length = $("#chooseTwoStep li div.standard").length
        var accountNumVal = $("#accountNumVal").val()
        var goodsInfoUnit = $("#goodsInfoUnit").val()
        var goodsInfoPack = $("#goodsInfoPack").val()
        var html = ""
        var showName= ""
        var check = true;
        var dataId = $("#itemBrand dt").attr("data-id")
        console.log(dataId)
        $(".parameterValName").each(function(){
            if($(this).val() == ""){
                check = false
               $(this).parent().siblings(".errorShow").remove()
                var html = '<td class="errorShow" style="color: #ff3300;font-size: 14px">请输入内容</td>'
                $(this).parent().next().next().after(html)
            }else{
                $(".parameterVal").each(function(){
                    if($(this).val() == ""){
                        check = false
                        $(this).parent().siblings(".errorShow").remove()
                        var html = '<td class="errorShow" style="color: #ff3300;font-size: 14px">请输入内容</td>'
                        $(this).parent().next().after(html)
                    }
                })
            }
        })

        if(check ){
            if( itemName != ""){
                if(priceA != ""){
                    if( priceB != "" ){
                       if(goodsInfoUnit != ""){
                           if( dataId != "0" ){
                               if( $(".standard div").length < 5){
                                   if(   $(".standard div").length != 0){
                                       $("#chooseThreeStep").show();
                                       $("#chooseOneStep").hide();
                                       $("#chooseTwoStep").hide();
                                       $("#selfTitle").html("详细信息")
                                       $(".standard div:nth-child(1) a").each(function(){
                                           var nameA = $(this).html()
                                           if( $(".standard div").length == "1" ){
                                               html += getHtml(itemName,nameA,priceA,priceB,accountNumVal)
                                           }else if( $(".standard div").length == "2"){
                                               $(".standard div:nth-child(2) a").each(function(){
                                                   var nameB = $(this).html()
                                                   showName = nameA + '*' + nameB
                                                   html += getHtml(itemName,showName,priceA,priceB,accountNumVal)
                                               })
                                           }else if( $(".standard div").length == "3"){
                                               $(".standard div:nth-child(2) a").each(function(){
                                                   var nameB = $(this).html()
                                                   showName = nameA + '*' + nameB
                                                   $(".standard div:nth-child(3) a").each(function(){
                                                       var namec = $(this).html()
                                                       showName = nameA + '*' + nameB + '*' +namec
                                                       html += getHtml(itemName,showName,priceA,priceB,accountNumVal)
                                                   })
                                               })
                                           }else if( $(".standard div").length == "4"){
                                               $(".standard div:nth-child(2) a").each(function(){
                                                   var nameB = $(this).html()
                                                   showName = nameA + '*' + nameB
                                                   $(".standard div:nth-child(3) a").each(function(){
                                                       var namec = $(this).html()
                                                       showName = nameA + '*' + nameB + '*' +namec
                                                       $(".standard div:nth-child(4) a").each(function(){
                                                           var named = $(this).html()
                                                           showName = nameA + '*' + nameB + '*' +namec + '*'+ named
                                                           html += getHtml(itemName,showName,priceA,priceB,accountNumVal)
                                                       })
                                                   })
                                               })
                                           }

                                       })
                                       $(".ThreeStepHtml").empty().append(html)
                                       $("#chooseThreeStep ul li:nth-child(1)").addClass("on")
                                       $("#chooseThreeStep ul li.on dl").show()
                                       var j = 0
                                       var z = 0
                                       for(var i = 1 ; i <= $(".ThreeStepHtml li").length ; i++){
                                           if( i % 7 == 0 ){
                                               j++
                                           }
                                           z++
                                           if(i % 7 == 0 ){
                                               z = 0
                                           }
                                           $(".ThreeStepHtml li").eq(i).css("left",(i-j*7)*200).css("top",j*24)
                                           $(".ThreeStepHtml li dl").eq(i).css("left",(0-((z)*150+((z-1)*50))))
                                       }
                                       var width = document.body.offsetWidth
                                       if(width < 1360){
                                           $("#chooseThreeStep li dl").css("top",10 + ((j+1)*24))


                                       }else{
                                           $("#chooseThreeStep li dl").css("top",20 + ((j+1)*24))


                                       }

                                   }else{
                                       response_ensure_alert("error","请添加商品规格")
                                   }
                               }else{
                                   response_ensure_alert("error","商品规格最多添加4种")
                               }
                           }else{
                               var html= "<b style='color: #ff3300;'>请选择商品品牌</b>"
                               $("#itemBrand").siblings("b").remove()
                               $("#itemBrand").after(html);
                           }
                       }else{
                           response_ensure_alert("error","请填写单位")
                       }
                    }else{
                        var html= "<b style='color: #ff3300;'>不可为空</b>"
                        $("#priceB").parent().siblings("b").remove()
                        $("#priceB").parent().after(html);
                        $("#priceB").focus()
                    }
                }else{
                    var html= "<b style='color: #ff3300;'>不可为空</b>"
                    $("#priceA").parent().siblings("b").remove()
                    $("#priceA").parent().after(html);
                    $("#priceA").focus()
                }
            }else{
                var html= "<b style='color: #ff3300;'>不可为空</b>"
                $("#itemName").parent().siblings("b").remove()
                $("#itemName").parent().after(html);
                $("#itemName").focus()
            }
        }else{

        }

    })
    $(document).on("click","#chooseThreeStep li",function(){
        $(this).addClass("on").siblings().removeClass("on")
        $("#chooseThreeStep li dl").hide()
        $("#chooseThreeStep li.on dl").show()
    })
    var editor;
    KindEditor.ready(function(K) {
        <!-- window.editor = K.create('#content'); -->
        editor = K.create('#itemDetail', {
            filterMode:false
        });
    });
    $("#addStandardDetail h1 i").click(function(){
        discoverHtml()
        $("#addStandardDetail").hide()
    })
    $("#addStandard").click(function(){
        $(".StandardValContain").empty()
        var html =  '<tr><td><input type="text" class="StandardVal"></td> <td><a class="deleteStandardVal">删除</a></td> </tr>'
        $(".StandardValContain").append(html)
        $("#StandardName").val("")
        $("#updateStandardValComfirm").hide()
        $("#addStandardValComfirm").show()
        coverHtml()
        $("#addStandardDetail").show()
    })
    $("#addStandardVal").click(function(){
        var html =  '<tr><td><input type="text" class="StandardVal"></td> <td><a class="deleteStandardVal">删除</a></td> </tr>'
        $(".StandardValContain").append(html)
    })
    $(document).on("click",".deleteStandardVal",function(){
        if($(".StandardValContain").children("tr").length > 1){
            $(this).parent().parent("tr").remove()
        }else{
            response_ensure_alert("error","至少必须有一个参数值")
        }
    })
    $("#addStandardValComfirm").click(function(){

        var aHtml =""
        var check = true
        var StandardName = $("#StandardName").val()
        $(".StandardVal").each(function(){
            if($(this).val() == ""){
                check = false
            }
            aHtml += '<a>' + $(this).val() + '</a>'
        })
        aHtml = aHtml.substr(0,aHtml.length - 1)
        var html=getStandardHtml(StandardName,aHtml)
        if(check && StandardName !="" ){
            discoverHtml()
            $("#addStandardDetail").hide()
            $(".standard").append(html)
        }else{
            response_ensure_alert("error","请检查参数是否为空")
        }

    })

    $(document).on("click",".standardDel",function(){
        $(this).parent().parent().parent("div").remove()
    })
    var which =""
    $(document).on("click",".standardUpdate",function(){
        coverHtml()
        which = $(this).parent().parent().parent("div").index()
        console.log(which)
        $("#addStandardDetail").show()
        $("#updateStandardValComfirm").show()
        $("#addStandardValComfirm").hide()
        var _this = this
        var html =""
        var StandardName = $(this).siblings("abbr").html()
        $("#StandardName").val(StandardName)
        $(this).parent().siblings().children().children("a").each(function(){
             html += getStandardValHtml($(this).html())
        })
        $(".StandardValContain").empty().append(html)

    })
    $("#updateStandardValComfirm").click(function(){

        var aHtml =""
        var check = true
        var StandardName = $("#StandardName").val()
        $(".StandardVal").each(function(){
            if($(this).val() == ""){
                check = false
            }
            aHtml += '<a>' + $(this).val() + '</a>'
        })
        aHtml = aHtml.substr(0,aHtml.length - 1)
        var html=getStandardHtml(StandardName,aHtml)
        if(check  && StandardName !=""  ){
            discoverHtml()
            $("#addStandardDetail").hide()
            $(".standard div").eq(which).before(html).remove()

        }else{
            response_ensure_alert("error","请检查参数值有没有空")
        }

    })
    $("#addParameterl").click(function(){
        var html =  '<tr><td><input type="text" class="parameterValName"></td> <td><input type="text" class="parameterVal"></td><td><a class="deleteparameterVal">删除</a></td> </tr>'
        $(".parameterlContain").append(html);
        $(document).on('blur','.parameterValName',function(){
            if($(this).parent().next().find('.parameterVal').val() != ''&&$(this).val()!=''){
                $(this).parent().parent().find(".errorShow").remove();
            }
        });
        $(document).on('blur','.parameterVal',function(){
            if($(this).parent().prev().find('.parameterValName').val() != ''&&$(this).val()!=''){
                $(this).parent().parent().find(".errorShow").remove();
            }
        })
    })
    $(document).on("click",".deleteparameterVal",function(){
            $(this).parent().parent("tr").remove()
    })
    $(document).on("click",".addImg",function(){
        coverHtml()
        $("#upload").show()
        $("#companyLogo").prop("src","")
        $("#companyLogoConfirm").prop("disabled","disabled")
    })
    $(document).on("click","#upload h1 i",function(){
        discoverHtml()
        $("#upload").hide()
    })
    $("#companyLogoCancel").click(function(){
        discoverHtml();
        $("#upload").hide();
    });
    $("#companyLogoConfirm").click(function(){
        var imgUrl=$("#companyLogo").prop("src");
        if(imgUrl != ""){
            var okArr = [];
            okArr[0] = '给所有添加';
            okArr[1] = function(){
                discoverHtml();
                $("#upload").hide();
                $(".ThreeStepHtml li dl dd div.showImgs").each(function(){
                    var html = '<img width="50" height="50" src="'+ imgUrl +'"><i></i>'
                    $(this).append(html)
                })
            };
            var cancelArr = [];
            cancelArr[0] = '当前规格添加';
            cancelArr[1] = function(){
                discoverHtml();
                $("#upload").hide();
                var html = '<img width="50" height="50" src="'+ imgUrl +'"><i></i>'
                $(".ThreeStepHtml li.on dl dd div.showImgs").append(html)
            };
            selfPick_alert('success',"是否将该图片添加给所有规格？",okArr,cancelArr);

        }else{
            data_type_alert("error","请选择上传文件")
        }
    });
    $(document).on("click",".ThreeStepHtml li.on dl dd div.showImgs i",function(){
        $(this).prev("img").remove()
        $(this).remove()
    })
    $(document).on("click",".addAccountNum",function(){
        coverHtml()
        $("#accountNum").show()
    })
    $(document).on("click","#accountNumCancel",function(){
        discoverHtml()
        $("#accountNum").hide()
    })
    $(document).on("click","#accountNumConfirm",function(){
        var accountNum = $("#accountNumVal").val()
        if(accountNum != ""){
            $.post("../web/api/accountcenter/editIdentification",{
                identification:accountNum
            },function(data){
                if( data.response == 'success' ){
                    $(".accountNum").each(function(){
                        $(this).html(accountNum)
                        discoverHtml()
                        $("#accountNum").hide()
                    })
                }
                else{
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');

        }else{
            response_ensure_alert("error","请输入机构标示")
        }
    })
    $("#LogoUpdate").click(function(){
        var updateInterval = false;
        var domId = "LogoUpdate";
        var _this = this;
        var disableId = "companyLogoConfirm"
        var imgUploader = setInterval(function(){
            if( updateInterval == false && $(_this).val() != '' ){
                if( $("#LogoUpdate").val() ){
                    updateInterval = UpladFile(imgUploader,domId,disableId);
                }
            }
        },500);
    });
    $(document).on("input","#brandSearch input[type='text']",function(){
        var brandName = $(this).val()
        $.post("../web/api/goodsManager/queryBrandsByName",{
            brandName:brandName
        },function(data){
            if( data.response == 'success' ){
                $("#brandSearch").siblings("dd").remove()
                var html = ""
                data.data.map(function(object){
                    html += '<dd style="display:block" data-id="'+ object.brandId +'">'+ object.brandName +'</dd>'
                })
                $("#brandSearch").after(html)
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
    })
    $(document).on("click","#itemBrand dt",function(){
        $(this).siblings("dd").slideToggle()
    })
    $(document).on("blur","#goodsInfoUnit",function(){
        $("#unitShow").html($(this).val())
    })
    $(document).on("blur","#goodsInfoPack",function(){
            if(!/^[0-9]*[1-9][0-9]*$/.test($(this).val()) && $(this).val()!=""){
                response_ensure_alert("error","请输入正整数")
                $(this).val("")
            }
    })
    $(document).on("click","#itemBrand dd:not(#brandSearch)",function(){
        var id=$(this).data("id")
        var name = $(this).html()
        console.log(id,name)
        $(this).siblings("dt").attr("data-id",id)
        $(this).siblings("dt").html(name)
        $("#itemBrand dd").slideUp()
        $(this).parent().siblings("b").remove()
    })
    $("#outputItem").click(function(){
        var itemsJson = "{"
        var catId =$("#thirdContain li.chooseOneStepThree input").val()
        $('.errorShow').each(function(){
            $(this).remove();
        })
        if ( catId != undefined){
            itemsJson += '"catId":"' + catId + '",'
        }
        var goodsName = $("#itemName").val()
        if(goodsName != ""){
            itemsJson += '"goodsName":"' + goodsName + '",'
        }else{
            response_ensure_alert("error","商品标题未填，请检查")
        }
        var goodsPrice = $("#priceA").val()
        if(goodsPrice != ""){
            itemsJson += '"goodsPrice":"' + goodsPrice + '",'
        }else{
            response_ensure_alert("error","商城价未填，请检查")
        }

        var goodsInfoUnit = $("#goodsInfoUnit").val()
        if(goodsInfoUnit != ""){
            itemsJson += '"goodsInfoUnit":"' + goodsInfoUnit + '",'
        }else{
            response_ensure_alert("error","单位未填，请检查")
        }

        var goodsInfoPack = $("#goodsInfoPack").val()
        if(goodsInfoPack != ""){
            itemsJson += '"goodsInfoPack":"' + goodsInfoPack + '",'
        }

        var goodsAdded = $("input[name='setUpload']:checked").val()
        itemsJson += '"goodsAdded":"' + goodsAdded + '",'

        var brandId = $("#itemBrand dt").data("id")
        itemsJson += '"brandId":"' + brandId + '",'

        itemsJson += '"specs":['
        $(".standard div").each(function(){
            itemsJson += '{"specName":' + '"'  + $(this).children().children().children("abbr").html() + '",'
            itemsJson += '"details":['
            $(this).children().children().children().children("a").each(function(){
                itemsJson += '{"specDetailName":"'+ $(this).html() + '"},'
            })
            itemsJson = itemsJson.substr(0,itemsJson.length - 1)
            itemsJson += "]"
            itemsJson += "},"
        })
        itemsJson = itemsJson.substr(0,itemsJson.length - 1)
        itemsJson += "],"

        itemsJson += '"typeSpecs":['
        if( $(".parameterlContain tr").length != 0){
            $(".parameterlContain tr").each(function(){
                itemsJson += '{"paramName":"'+ $(this).children().children(".parameterValName").val() + '","paramValue":"' + $(this).children().children(".parameterVal").val() + '"},'
            })
            itemsJson = itemsJson.substr(0,itemsJson.length - 1)
        }
        itemsJson += "],"
        var goodsDetailDesc=editor1.html()

        itemsJson += '"goodsInfos":['
        $(".ThreeStepHtml li").each(function(){
            itemsJson += '{"goodsInfoItemNo":"' + $(this).children().children().children().children(".accountNum").html() + $(this).children().children().children().children(".goodNumber").val() + '",'
            itemsJson += '"goodsInfoName":"' + $(this).children().children().children().children(".goodsName").val() + '",'
            itemsJson += '"goodsInfoPreferPrice":"' + $(this).children().children().children().children(".goodsPriceA").val() + '",'
            itemsJson += '"goodsInfoSettlePrice":"' + $(this).children().children().children().children(".goodsPriceB").val() + '",'
            itemsJson += '"infoImages":['
            $(this).children().children().children(".showImgs").children("img").each(function(){
                itemsJson += '{"imageInName":"'+ $(this).prop("src")+ '"' + "},"
            })
            itemsJson = itemsJson.substr(0,itemsJson.length - 1)
            itemsJson += "]"
            itemsJson += "},"
        })

        itemsJson = itemsJson.substr(0,itemsJson.length - 1)
        itemsJson += ']'

        itemsJson += "}"

        var check = true
        var type = 1;
        $(".accountNum").each(function(){
            if($(this).html()== "机构标识"){
                check = false
            }
        })
        $(".goodNumber").each(function(){
            if($(this).val()== ""){
                check = false
            }
        })
        $(".goodsName").each(function(){
            if($(this).val()== ""){
                check = false
            }
        })
        $(".goodsPriceA").each(function(){
            if($(this).val()== ""){
                check = false
            }
        })
        $(".goodsPriceB").each(function(){
            if($(this).val()== ""){
                check = false
            }
            else if(parseInt($(this).val()) > 9999){
                check = false;
                type = 0;
                $(this).after("<span class='errorShow' style='color: red;width: 200px'>结算价应小于10000！</span>");
            }
        })
        $(".showImgs").each(function(){
            if($(this).children("img").length == 0){
                check = false
            }
        })
        console.log(itemsJson)
        if(check){
            coverHtml()
            $.post("../web/api/goodsManager/newUploadGood",{
                goodsJson:itemsJson,
                goodsDetailDesc:goodsDetailDesc
            },function(data){
                if( data.response == 'success' ){
                    discoverHtml()
                    response_ensure_alert('success',"商品上架成功",function(){
                        window.location.href = "itemManager"
                    });
                }
                else{
                    discoverHtml()
                    response_ensure_alert('error',data.data.text,function(){

                    });
                }
            },'json');

        }else{
            var text = '';
            if(type == 0){
                text = "结算价不能大于10000";
            }
            else{
                text = "请检查必填项是否为空";
            }
            response_ensure_alert("error",text);
        }

    })
    $(document).on("click",".setIdNum",function(){
        if($(this).siblings(".accountNum").html() != "机构标识"){
        var count = $(".ThreeStepHtml li").length
        $.post("../web/api/common/createGenerator",{
            count:count
        },function(data){
            if( data.response == 'success' ){
                for(var i = 0 ; i <= count ; i++){
                    $(".ThreeStepHtml .goodNumber").eq(i).val(data.data[i])
                }
            }
            else{
                response_ensure_alert('error',data.data.text,function(){

                });
            }
        },'json');
        }else{
            response_ensure_alert("error","请先设置机构标识")
        }
    })
})
function UpladFile(interval,domId,disableId) {
    var fileObj = document.getElementById(domId).files[0]; // 获取文件对象
    var FileController = "../web/uploadPicture";                    // 接收上传文件的后台地址
    var data = [];
    // FormData 对象
    var form = new FormData();// 可以增加表单数据
    form.append("file", fileObj);                           // 文件对象
    var xhr = new XMLHttpRequest();
    xhr.open("POST", FileController, true);
    // XMLHttpRequest 对象
    xhr.send(form);
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            str = xhr.responseText;
            data = JSON.parse(str);
            if (data.response == 'success'){
                $("#" + domId).prev().prop("src",data.data);
                $("#" + domId).val("");
                $("#" + disableId).prop("disabled","")
                return true;
            }
            else{
                response_ensure_alert('error',data.data.text,function(){
                    console.log("上传失败" + consoleNowTime());
                    $("#" + domId).val("");
                    $("#" + disableId).prop("disabled","disabled")
                });
                return false;
            }
        }
        else if( xhr.status == 400 ){
            response_ensure_alert("error","上传失败",function(){
                console.log("上传失败" + consoleNowTime());
                $("#" + domId).val("");
                return false;
            });
        }
        clearInterval(interval);
    };
}


function getHtml(itemName,nameA,priceA,priceB,val){
        var html = ""
        html += '<li><span title="'+ nameA +'">'+ nameA  +'</span>';
        html += '<dl>'
        html += '<dd><span><i>*</i>货品编号:</span><div><abbr style="padding:0px 5px;margin-left: 10px;margin-right: 10px; color:#999;" class="accountNum">'+ val +'</abbr> - <input class="goodNumber" style="width: 400px;margin-left: 10px" placeholder="货品编号" type="text" ><a class="addAccountNum">机构标识</a><a class="setIdNum">生成编号</a></div></dd>'
        html += '<dd><span><i>*</i>货品标题:</span><div><input class="goodsName" style="width: 400px" placeholder="货品标题" type="text" value="' + itemName + '(' + nameA + ')' +'" ></div></dd>'
        html += '<dd><span><i>*</i>货品价格:</span><div>商城价（邮豆）：<input maxlength="9" class="goodsPriceA" style="width: 80px" type="text" value="'+ priceA +'" ></div><div>结算价（RMB）：<input maxlength="9" class="goodsPriceB" style="width: 80px" type="text" value="'+ priceB +'" ></div></dd>'
        html += '<dd><span><i>*</i>货品图片:</span><div><input class="allclickButton addImg"  value="编辑货品图片" type="button" ></div></dd>'
        html += '<dd><span><i>*</i>已选图片:</span><div class="showImgs"></div></dd>'
        html += '</dl>'
        return html
}

function getStandardHtml(StandardName,aHtml){
    var html = "";
    html +=   '<div>'
    html += '<ul>'
    html += '<li><span>规格名称</span><abbr>'+ StandardName +'</abbr><img width="20" class="standardDel" height="20" src="../static/img/standardDEL.png"><img class="standardUpdate" width="20" height="20" src="../static/img/standardUpdate.png"></li>'
    html += '<li>'
    html += '<span>规格值</span>'
    html += '<section>'
    html += aHtml
    html += '</section>'
    html += '</li>'
    html += '</ul>'
    html += '</div>'
    return html
}


function getStandardValHtml(StandardVal){
    var html = "";
    html +=  '<tr><td><input type="text" class="StandardVal" value="'+ StandardVal +'"></td> <td><a class="deleteStandardVal">删除</a></td> </tr>'
    return html
}

function checkNum(num){
    var lock = true
    var reg = /^(([1-9]\d*)|)$/;
    if( num!="0" ){
        if(!/^-?((([1-9]+(\d+)?)(\.\d+)?)|(0\.\d+))$/.test(num)){
            lock = false
        }
    }

    if(parseFloat(num) < 0){
        lock = false
    }

    return lock
}