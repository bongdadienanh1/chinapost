$(document).ready(function(){
    //加载全部
    var start = '';
    var end = '';
    var typeId = '';

    ajaxPages("../web/api/sysmanage/getHistories","itemContainer","holder",20,[]);

    $("#date_start").click(function(e){
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_start").trigger("focus");
    })
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").datetimepicker('destroy');
    });

    $("#date_end").click(function(e){
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_end").trigger("focus");
    })
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });
    $(".arrow").click(function(){
        var arr=$(this).val()
        $(this).siblings("dd").slideToggle()
        if(arr==0){
            $(this).css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat")
            $(this).val("1")
        }else if(arr==1){
            $(this).css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat")
            $(this).val("0")
        }

    })
    $(".select dd").click(function(){
        var sechtml = $(this).html();
        var sechtId = $(this).data("id");
        $("#typeId").val(sechtId);
        $(this).hide().siblings("dd").hide();
        $(this).siblings("dt").children("abbr").html(sechtml);
        $(this).siblings(".arrow").css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat")
    })

    $("#billsearch").click(function(){
        var array = [];
        start = $("#datetimepicker_start").val();
        end = $("#datetimepicker_end").val();
        typeId = $("#typeId").val();
        if( end == '' || start == '' ){
            response_ensure_alert("error","搜索时间段不能为空",function(){},function(){});
        }
        else if( end < start ){
            response_ensure_alert("error","起始时间不得大于结束时间",function(){},function(){});
        }
        else{
            if( typeId != '' ){
                array[0] = typeId;
            }
            array[1] = start;
            array[2] = end;
            ajaxPages("../web/api/sysmanage/getHistories","itemContainer","holder",20,array);
        }
    });

    $("#busChangeOut").click(function(){
        if( start == '' || end == '' ){
            response_ensure_alert("error","选择要导出变动记录的起止时间段，并点击【搜索】按钮后才能导出变动记录");
        }
        else if( start > end ){
            response_ensure_alert("error","起始时间不得大于结束时间");
        }
        else{
            window.location.href = '../web/api/exportExcel/systemChangeHistory?typeId=' + handleUndefined(typeId) + '&start=' + start + '&end=' + end;
        }
    });
})

function ajaxPages(url,contentStr,target,perpage,array){
    var typeId = array[0];
    var start = array[1];
    var end = array[2];

    $.post(url,{
        typeId:typeId,
        start:start,
        end:end,
        page:1,
        size:perpage
    },function(data){
        $("#" + contentStr).empty();
        if(data.response == 'success'){
            var total = data.data.totalElements;
            for(var i = 0;i < total;i++){
                var html = get_html();
                $("#"+contentStr).append(html);
            }
            jPagesGet(target,contentStr,20);
            if( total < perpage ){
                appendHtml(contentStr,data.data.content,total);
            }
            else{
                appendHtml(contentStr,data.data.content,perpage);
            }
            //处理多余页
            //处理多余页
            var pageLength = $("#" + target + " a").length;
            for( var i = 2;i < (pageLength - 2);i++ ){
                var showElement = ( pageLength - 2 ) * perpage ;
                if( showElement >= ( data.data.totalElements + perpage ) ){
                    $( $("#" + target + " a")[pageLength - i]).remove();
                }
            }
            $("#" + target).find("a").css("display","inline-block");
            $("#" + target).find("span").css("display","inline-block");
            $("#" + target).find(".jp-hidden").css("display","none");

            $("#" + target + " a").unbind("click");
            $("#" + target + ' a').click(function(){
                var page = '';
                if( $(this).html() == '＜' ){
                    if( parseInt($(".jp-current").html()) > 1 ){
                        page = parseInt($(".jp-current").html()) - 1;
                    }
                    else{
                        page = parseInt($(".jp-current").html());
                    }
                }
                else if( $(this).html() == '＞' ){
                    if( parseInt($(".jp-current").html()) < ( $("#holder a").length -2 ) ){
                        page = parseInt($(".jp-current").html()) + 1;
                    }
                    else{
                        page = parseInt($(".jp-current").html());
                    }
                }
                else{
                    page = $(this).html();
                }

                var typeId = array[0];
                var start = array[1];
                var end = array[2];
                $.post(url,{
                    typeId:typeId,
                    start:start,
                    end:end,
                    page:page,
                    size:perpage
                },function(data){
                    if(data.response == 'success'){
                        var total = data.data.totalElements;
                        for(var i = 0;i < total;i++){
                            var html = get_html();
                            $("#"+contentStr).append(html);
                        }
                        if( total < perpage ){
                            appendHtml(contentStr,data.data.content,total);
                        }
                        else{
                            appendHtml(contentStr,data.data.content,perpage);
                        }
                    }
                },'json');




                //处理页数
                if( page > 4 ){
                    var length = $("#holder a").length;
                    if( page == length-2 ){
                        $( $("#holder a")[page] ).prev().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().prev().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().prev().prev().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().prev().prev().prev().css("display","inline-block");

                    }
                    else{
                        $( $("#holder a")[page] ).next().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().css("display","inline-block");
                    }

                }
            });
        }
    },'json');

}
function get_html(){
    var html = '<dd></dd>';
    return html;
}
function appendHtml(str,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i < perpage;i++){
        var html = '';
            html += '<tr>';
            html += '<td>' + handleDate_prev(new Date(data[i].date)) + ' ' + handleDate_next(new Date(data[i].date)) + '</td>';
            html += '<td data-id="' + data[i].typeId + '">' + fuck_jiangtao_handleNone(data[i].typeName) + '</td>';
            html += '<td>' + fuck_jiangtao_handleNone(data[i].beforeEx) + '</td>';
            html += '<td>' + fuck_jiangtao_handleNone(data[i].afterEx) + '</td>';
            html += '<td>' + fuck_jiangtao_handleNone(data[i].operation) + '</td>';
            html += '<td>' + fuck_jiangtao_handleNone(data[i].note) + '</td>';
            html += '<td>' + fuck_jiangtao_handleNone(data[i].operator) + '</td></tr>';
        $("#"+ str).append(html);
    }

}

function jPagesGet(content,target,perPage){
    $("#"+content).jPages({
        containerID : target,
        perPage:perPage
    });

}

function fuck_jiangtao_handleNone(str){
    console.log(typeof(str));
    if(str == undefined){
        return '无';
    }
    else{
        return str;
    }
}