function CheckSystem(domName,str,alert){
    var domName = domName;
    var str = str;
    var alert = alert;
    var count = 1;
    var _this = this;
    var a = function(){
        var txt = $(this).val();
        if(!(str.test(txt))){
            var html = '';
            html = '<div class="inputTestError">';
            html += alert;
            html += '</div>';
            if( count == 1 ){
                count = 0;
                $(this).after(html);
            }
        }
        else{
            count == 1;
            $(this).next().remove();
        }
    };

    //默认是blur校验
    var MainFunction = function(fun){
        if(typeof(fun) != 'function'){
            $(domName).blur(function(){
                a.call(this);
            });
        }
        else{
            fun();
        }
    };
    var overRideMain = function(NewFunction){
        if( typeof(NewFunction) === 'function' ){
            a = NewFunction;
        }
    };
    return {
        domName:domName,
        str:str,
        alert:alert,
        doCheck:MainFunction,
        overRideMain:overRideMain
    }
}

$(document).ready(function(){
    //手机校验
    var Phone_Check = new CheckSystem(".check-phone",/^1[3|4|5|7|8]\d{9}$/,'手机号未通过校验');
    Phone_Check.doCheck();

    //系数验证
    var coefficient_Check = new CheckSystem(".check-coefficient",/^0$|^[1-9][0-9]*$|^[1-9][0-9]*\.?[0-9]{2}$/,'系数输入非法');
    coefficient_Check.doCheck();

    //小数点后两位



});