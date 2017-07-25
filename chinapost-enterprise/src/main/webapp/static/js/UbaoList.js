$(document).ready(function(){
    var customerId = $("input[type='hidden']").val();


    //加载第一页数据
    $.post("../web/api/ucoinquery/ucoinhistories",{
        'customerId': customerId,
        'pageNumber':1,
        'pageSize':10
    },function(data){
        if(data.response == 'success'){
            if( data.data.content != [] ){
                data.data.content.map(function(data){
                    var prev_time = handleDate_prev( new Date( data.createTime ) );
                    var next_time = handleDate_next( new Date( data.createTime ) );
                    var coin = '';

                    var type = data.type;
                    if(type == 'ENTERPRISE_GRAND' || type == 'UCOINCODE' || type == 'UCOIN_REFUND'){
                        coin += '+';
                    }
                    else if( type == 'UCOIN_CONSUME' || type == 'UCOIN_DEDUCT' ){
                        coin += '-';
                    }
                    coin += data.price;
                    var html = '<tr>';
                    html += '';
                    html += '<td>' + prev_time + '<br/>' + next_time + '</td>';
                    if(data.type == "UCOIN_CONSUME"||data.type == "UCOIN_REFUND"){
                        html += '<td><img src="../static/img/logo.png"><abbr>邮豆商城</abbr></td>';
                    }else{
                        html += '<td><img src="'+ data.enterpriseInfo.enterpriseImg + '"><abbr>'+data.enterpriseInfo.enterpriseName+'</abbr></td>';
                    }
                    if(data.type=="UCOIN_CONSUME"){
                    html += '<td>购物消费</td>';
                    }else if(data.type=="UCOIN_DEDUCT"){
                        html += '<td>邮豆扣减</td>';
                    }else if(data.type=="UCOIN_REFUND"){
                        html += '<td>购物退款</td>';
                    }else if(data.type=="ENTERPRISE_GRAND"){
                        html += '<td>邮豆发放</td>';
                    }
                    html += '<td>' + coin + '邮豆</td>';
                    html += '</tr>';
                    $("tbody").append(html);
                });

            }
        }
    },'json')





    //滚动分页
    var pageNumber = 2;
    var isActive = true;
    var flag = true;
    $(document).scroll(function(){
        if($(document).scrollTop() > ($(document).height() - $(window).height()-1) ){
            if( isActive == true ){

                isActive = 'loading';
                $.post("../web/api/ucoinquery/ucoinhistories",{
                    'customerId': customerId,
                    'pageNumber':pageNumber,
                    'pageSize':10
                },function(data){
                    var html = '<div class="loading">加载中...</div>';
                    $("table").after(html);
                    if( data.response == 'success' || flag == true ){
                        if( isActive == 'loading' ){
                            if( data.data.content != '' ){
                                pageNumber = pageNumber + 1;
                                data.data.content.map(function(data){
                                    var prev_time = handleDate_prev( new Date( data.createTime ) );
                                    var next_time = handleDate_next( new Date( data.createTime ) );
                                    var coin = '';

                                    var type = data.type;
                                    if(type == 'ENTERPRISE_GRAND' || type == 'UCOINCODE' || type == 'UCOIN_REFUND'){
                                        coin += '+';
                                    }
                                    else if( type == 'UCOIN_CONSUME' || type == 'UCOIN_DEDUCT' ){
                                        coin += '-';
                                    }
                                    coin += data.price;
                                    var html = '<tr>';
                                    html += '';
                                    html += '<td>' + prev_time + '<br/>' + next_time + '</td>';
                                    if(data.type == "UCOIN_CONSUME"||data.type == "UCOIN_REFUND"){
                                        html += '<td><img src="../static/img/logo.png"><abbr>邮豆商城</abbr></td>';
                                    }else{
                                        html += '<td><img src="'+ data.enterpriseInfo.enterpriseImg + '"><abbr>'+data.enterpriseInfo.enterpriseName+'</abbr></td>';
                                    }
                                    if(data.type=="UCOIN_CONSUME"){
                                        html += '<td>购物消费</td>';
                                    }else if(data.type=="UCOIN_DEDUCT"){
                                        html += '<td>邮豆扣减</td>';
                                    }else if(data.type=="UCOIN_REFUND"){
                                        html += '<td>购物退款</td>';
                                    }else if(data.type=="ENTERPRISE_GRAND"){
                                        html += '<td>邮豆发放</td>';
                                    }
                                    html += '<td>' + coin + '邮豆</td>';
                                    html += '</tr>';
                                    $("tbody").append(html);
                                });

                            }else{
                                flag = false;
                            }
                        }
                        $(".loading").remove();
                        isActive = true;
                    }
                    else{
                        $(".loading").html("加载失败");
                    }
                },'json');
            }
        }
        else{

        }
    });


});