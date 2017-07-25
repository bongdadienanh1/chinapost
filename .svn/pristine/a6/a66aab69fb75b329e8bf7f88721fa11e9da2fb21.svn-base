$(document).ready(function(){
    ajaxPages(total_elements,'../web/api/customer/getCustomers','itemContainer','holder',0,5);
    $("#newMenberConfirm").click(function(){

        var idCard = $("input[name='newMemId']").val();
        var name = $("input[name='newMemName']").val();
        var linkPhone = $("input[name='newMemTel']").val();
        var provinceId = $("input[name='newMemTel']").val();
        var cityId = $("input[name='newMemTel']").val();
        var districtId = $("input[name='newMemTel']").val();
        var addr = $("input[name='newMemTel']").val();
        var managerNo = $("input[name='newMemTel']").val();
        var remark = $("input[name='newMemTel']").val();
    });
});

function ajaxPages(total,url,contentStr,target,type,perpage){
    for(var i = 0;i < total;i++){
        var html = get_html(type);
        $("#"+contentStr).append(html);
    }
    jPagesGet(target,contentStr);
    $("#"+contentStr).empty();
    $("a").click(function(){
        var page = $(this).html();
        $.post(url,{
            "page":page,
            "perpage":perpage
        },function(data){
            if(data.response == 'success'){
                appendHtml(contentStr,page,total,data.data.content,10);
            }
        },'json');
    });
    $("#holder").children()[1].click();
}

function get_html(type){
    var html = '';
    html = "<dd>";
    html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
    html += '<abbr style="width:150px;" class="memId"></abbr>';
    html += '<abbr style="width:150px;" class="memfullname"></abbr>';
    html += '<abbr class="memSex"></abbr>';
    html += '<abbr class="memTel"></abbr>';
    html += '<abbr class="memTelBinding"></abbr>';
    html += '<abbr class="memUcoin"></abbr>';
    html += '<abbr class="memActive"></abbr>';
    html += '<abbr class="memManagerId"></abbr>';
    html += '<abbr style="width:200px;" class="memTab"></abbr>';
    html += '<abbr class="memLastTime"></abbr>';
    html += '<abbr style="width:300px;">';
    html += '<select class="memSelects">';
    html += '<option value="0" selected="selected">可选</option>';
    html += '<option value="1">查看详情</option>';
    html += '<option value="2">激活账户</option>';
    html += '</select></abbr></dd>';
    return html;
}
function appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i<perpage;i++){
        var idcardNo = '';
        var fullname = '';
        var male = '';
        var phoneNo = '';
        var mobileChecked = '';
        var nonDisabled = '';
        var managerNo = '';
        var tag = '';
        if(typeof(data[i].idcardNo) != 'undefined'){
            idcardNo = data[i].idcardNo;
        }
        if(typeof(data[i].fullname) != 'undefined'){
            fullname = data[i].fullname;
        }
        if(typeof(data[i].male) != 'undefined'){
            male = data[i].male;
            console.log(male);
            if( male == true ){
                male = '男';
            }
            else if( male == false ){
                male = '女';
            }
            console.log(male);
        }
        if(typeof(data[i].phoneNo) != 'undefined'){
            phoneNo = data[i].phoneNo;
        }
        if(typeof(data[i].mobileChecked)!= undefined){
            mobileChecked = data[i].mobileChecked;
            if( mobileChecked == true ){
                mobileChecked = '已绑定';
            }
            else if( mobileChecked == false ){
                mobileChecked = '未绑定';
            }
        }
        if(typeof(data[i].nonDisabled) != 'undefined'){
            nonDisabled = data[i].nonDisabled;
            console.log(nonDisabled);
            if( nonDisabled == true ){
                nonDisabled = '已激活';
            }
            else if( nonDisabled == false ){
                nonDisabled = '未激活';
            }
            console.log(nonDisabled);
        }
        if(typeof(data[i].managerNo) != 'undefined'){
            managerNo = data[i].managerNo;
        }
        if(typeof(data[i].tag) != 'undefined'){
            tag = data[i].tag;
        }
        var html = "<dd>";
        html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
        html += '<abbr style="width:150px;" class="memId">' + idcardNo + '</abbr>';
        html += '<abbr style="width:115px;" class="memfullname">' + fullname + '</abbr>';
        html += '<abbr class="memSex">' + male + '</abbr>';
        html += '<abbr class="memTel">' + phoneNo + '</abbr>';
        html += '<abbr class="memTelBinding">' + mobileChecked + '</abbr>';
        html += '<abbr class="memUcoin">' + 3 + '</abbr>';
        html += '<abbr class="memActive">' + nonDisabled + '</abbr>';
        html += '<abbr class="memManagerId">' + managerNo + '</abbr>';
        html += '<abbr style="width:200px;" class="memTab">' + tag + '</abbr>';
        html += '<abbr class="memLastTime">' + 7 + '</abbr>';
        html += '<abbr style="width:300px;">';
        html += '<select class="memSelects">';
        html += '<option value="0" selected="selected">可选</option>';
        html += '<option value="1">查看详情</option>';
        html += '<option value="2">激活账户</option>';
        html += '</select></abbr></dd>';
        $("#"+ str).append(html);
    }

}
function checkUndefined(str){
    if( str == 'undefined' ){
        str = '';
    }
    return str;
}


function jPagesGet(content,target){
    $("#"+content).jPages({
        containerID : target
    });

}