$(document).ready(function(){
    $(".pwd").draggable();
    //确认转账，显示支付页面

    //支付页面点击事件
    //点击X返回上一个页面
    $(".pwd h1 i").click(function(){
        discoverHtml();
        $(".pwd").fadeOut(500);
    });

    //点击确认支付
    $(".re_import").click(function(){
        window.location.href = 'piliangdaoru';
    });

    $("#complete").click(function(){
        discoverHtml();
        window.location.href = 'UbaoSend';
    });

    $("#detail").click(function(){
        discoverHtml();
        //frame操作
        $(window.parent.frames['mainleft'].document).find(".clickOn").removeClass("clickOn");
        $(window.parent.frames['mainleft'].document).find("#billmanager").addClass("clickOn");
        window.location.href="BillManager?action=2";
    });
});
