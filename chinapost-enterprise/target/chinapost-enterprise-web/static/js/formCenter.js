$(document).ready(function(){
    $.datetimepicker.setLocale('ch')
    $("#date_start").click(function(e){
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#datetimepicker_start").trigger("focus");
    });
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").datetimepicker('destroy');
    });

    $("#date_end").click(function(e){
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d'
        });
        $("#datetimepicker_end").trigger("focus");
    })
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });


    //以下是会员邮豆发放统计报表JS
    $("#userDetailList").draggable();
    $("#userDetailList h1 i").click(function(){
        discoverHtml();
        $("#userDetailList").hide()
    });


    //首页JS
    $(".dateSpan").click(function(){
        var start = '';
        var end = '';
        var today = new Date().getTime();
        var lastWeek = new Date( today - 3600*24*7*1000 ).Format("yyyy-MM-dd");
        var lastMonth = new Date( today - 3600*24*30*1000).Format("yyyy-MM-dd");
        var yestarDay = new Date( today - 3600*24*1000 ).Format("yyyy-MM-dd");
        if( $(this).html() == '最近一周' ){
            start = lastWeek;
            end = yestarDay;
            $.post("../web/api/report/getReportInfo",{
                start:start,
                end:end
            },function(data){
               if( data.response == 'success' ){

                   //var ColumnArray = data.data.dayList.map(function(object){
                   //    return object.sumPrice;
                   //});
                   //var ColumnCategoriesArray = data.data.dayList.map(function(object){
                   //    return object.everyDay;
                   //});

                   //chart1
                   var marketPrice = data.data.marketPrice;
                   var salePrice = data.data.salePrice;

                   var priceByTime = data.data.priceByTime;
                   var priceByYear = data.data.priceByYear;
                   var priceByDay = data.data.priceByDay;
                   //期间发放邮豆
                   $(".duringUcoin").html( ( priceByTime == undefined ) ? 0 : addCommas(priceByTime,2) );
                   //今年发放邮豆
                   $(".allUcoin").html( ( priceByYear == undefined ) ? 0 : addCommas(priceByYear,2) );
                   //今日发放邮豆
                   $(".todayUcoin").html( ( priceByDay == undefined ) ? 0 : addCommas(priceByDay,2) );

                   //chart3
                   var expenditureByDay = data.data.expenditureByDay;
                   var expenditureByYear = data.data.expenditureByYear;
                   var expenditureByTime = data.data.expenditureByTime;
                   //期间发放笔数
                   $(".duringCount").html( ( expenditureByTime == undefined ) ? 0 : expenditureByTime );
                   //今年发放笔数
                   $(".allCount").html( ( expenditureByYear == undefined ) ? 0 : expenditureByYear );
                   //今日发放笔数
                   $(".todayCount").html( ( expenditureByDay == undefined ) ? 0 : expenditureByDay );

                   //chart4
                   var increaseCustomerDay = data.data.increaseCustomerDay;
                   var increaseCustomerTime = data.data.increaseCustomerTime;
                   var increaseCustomerYear = data.data.increaseCustomerYear;
                   //期间新增会员
                   $(".duringAddCustomer").html( ( increaseCustomerTime == undefined ) ? 0 : increaseCustomerTime );
                   //今年新增会员
                   $(".allAddCustomer").html( ( increaseCustomerYear == undefined ) ? 0 : increaseCustomerYear );
                   //今日新增会员
                   $(".todayAddCustomer").html( ( increaseCustomerDay == undefined ) ? 0 : increaseCustomerDay );
                   var ChartData = {
                       Column:{
                           title:'',
                           Ytitle:'',
                           contentArray:data.data.dayList.map(function(object){
                               return object.sumPrice;
                           }),
                           categoriesArray:data.data.dayList.map(function(object){
                               return handleChartDate(object.everyDay);
                           }),
                           callback:function(){
                               $("#chartFirst svg").css("margin-top","40px");
                           }
                       },
                       Circle:{
                           title: '',
                           contentArray: [
                               ['营销邮豆金额 :' + addCommas(marketPrice,2),  handleNaNZero((marketPrice*10000)/((marketPrice + salePrice)*100))],
                               ['促销邮豆金额 :' + addCommas(salePrice,2),  handleNaNZero((salePrice*10000)/((marketPrice + salePrice)*100))]
                           ],
                           seriesName: '占比',
                           callback:  function(){

                           }
                       },
                       Line:{
                           title: '',
                           Ytitle:  '',
                           lineWidth: 2,
                           hoverLineWidth: 3,
                           seriesName:'发放笔数',
                           categoriesArray:data.data.dayList.map(function(object){
                               return handleChartDate(object.everyDay);
                           }),
                           contentArray: data.data.dayList.map(function(object){
                               return object.expenditure;
                           }),
                           callback:  function(){

                           }
                       },
                       LineCustomer:{
                           title: '',
                           Ytitle:  '',
                           lineWidth: 2,
                           hoverLineWidth: 3,
                           seriesName:'新增会员数',
                           categoriesArray:data.data.newCustomerAmountList.map(function(object){
                               return handleChartDate(object.everyDay);
                           }),
                           contentArray: data.data.newCustomerAmountList.map(function(object){
                               return object.newCustomerAmount;
                           }),
                           callback:  function(){

                           }
                       }
                   };
                   ChartColumn.apply($("#chartFirst"),[ChartData]);
                   ChartCircle.apply($("#chartSecond"),[ChartData]);
                   ChartLineCount.apply($("#chartThird"),[ChartData]);
                   ChartLineCustomer.apply($("#chartForth"),[ChartData]);
               }
            });

        }
        else if( $(this).html() == '最近一个月' ){
            $.post("../web/api/report/getReportInfo",{
                start:lastMonth,
                end:yestarDay
            },function(data){
                if( data.response == 'success' ){
                    //chart1
                    var marketPrice = data.data.marketPrice;
                    var salePrice = data.data.salePrice;

                    var priceByTime = data.data.priceByTime;
                    var priceByYear = data.data.priceByYear;
                    var priceByDay = data.data.priceByDay;
                    //期间发放邮豆
                    $(".duringUcoin").html( ( priceByTime == undefined ) ? 0 : addCommas(priceByTime,2) );
                    //今年发放邮豆
                    $(".allUcoin").html( ( priceByYear == undefined ) ? 0 : addCommas(priceByYear,2) );
                    //今日发放邮豆
                    $(".todayUcoin").html( ( priceByDay == undefined ) ? 0 : addCommas(priceByDay,2) );

                    //chart3
                    var expenditureByDay = data.data.expenditureByDay;
                    var expenditureByYear = data.data.expenditureByYear;
                    var expenditureByTime = data.data.expenditureByTime;
                    //期间发放笔数
                    $(".duringCount").html( ( expenditureByTime == undefined ) ? 0 : expenditureByTime );
                    //今年发放笔数
                    $(".allCount").html( ( expenditureByYear == undefined ) ? 0 : expenditureByYear );
                    //今日发放笔数
                    $(".todayCount").html( ( expenditureByDay == undefined ) ? 0 : expenditureByDay );

                    //chart4
                    var increaseCustomerDay = data.data.increaseCustomerDay;
                    var increaseCustomerTime = data.data.increaseCustomerTime;
                    var increaseCustomerYear = data.data.increaseCustomerYear;
                    //期间新增会员
                    $(".duringAddCustomer").html( ( increaseCustomerTime == undefined ) ? 0 : increaseCustomerTime );
                    //今年新增会员
                    $(".allAddCustomer").html( ( increaseCustomerYear == undefined ) ? 0 : increaseCustomerYear );
                    //今日新增会员
                    $(".todayAddCustomer").html( ( increaseCustomerDay == undefined ) ? 0 : increaseCustomerDay );
                    var ChartData = {
                        Column:{
                            title:'',
                            Ytitle:'',
                            contentArray:data.data.dayList.map(function(object){
                                return object.sumPrice;
                            }),
                            categoriesArray:data.data.dayList.map(function(object){
                                return handleChartDate(object.everyDay);
                            }),
                            callback:function(){
                                $("#chartFirst svg").css("margin-top","40px");
                            }
                        },
                        Circle:{
                            title: '',
                            contentArray: [
                                ['营销邮豆金额 :' + addCommas(marketPrice,2),  handleNaNZero((marketPrice*10000)/((marketPrice + salePrice)*100))],
                                ['促销邮豆金额 :' + addCommas(salePrice,2),  handleNaNZero((salePrice*10000)/((marketPrice + salePrice)*100))]
                            ],
                            seriesName: '占比',
                            callback:  function(){

                            }
                        },
                        Line:{
                            title: '',
                            Ytitle:  '',
                            lineWidth: 2,
                            hoverLineWidth: 3,
                            seriesName:'发放笔数',
                            categoriesArray:data.data.dayList.map(function(object){
                                return handleChartDate(object.everyDay);
                            }),
                            contentArray: data.data.dayList.map(function(object){
                                console.log(object.expenditure);
                                return object.expenditure;
                            }),
                            callback:  function(){

                            }
                        },
                        LineCustomer:{
                            title: '',
                            Ytitle:  '',
                            lineWidth: 2,
                            hoverLineWidth: 3,
                            seriesName:'新增会员数',
                            categoriesArray:data.data.newCustomerAmountList.map(function(object){
                                return handleChartDate(object.everyDay);
                            }),
                            contentArray: data.data.newCustomerAmountList.map(function(object){
                                return object.newCustomerAmount;
                            }),
                            callback:  function(){

                            }
                        }
                    };
                    ChartColumn.apply($("#chartFirst"),[ChartData]);
                    ChartCircle.apply($("#chartSecond"),[ChartData]);
                    ChartLineCount.apply($("#chartThird"),[ChartData]);
                    ChartLineCustomer.apply($("#chartForth"),[ChartData]);
                }
            });
        }
        $(this).css({
            "background-color":"#54b3e3",
            "color":"#fff"
        });

        $(this).siblings().css({
            "background-color":"#fff",
            "color":"#b2b2b2"
        });

    });














    //以下是会员邮豆扣减统计报表JS
    $("#userDetailListReduce").draggable();
    $("#userDetailListReduce h1 i").click(function(){
        discoverHtml();
        $("#userDetailListReduce").hide()
    });


    /**
     * data:{
     *   Column:{
     *      title:标题,
     *      Ytitle:  Y轴标题,
     *      categoriesArray:  X轴上的坐标类别,
     *      contentArray: 数据
     *      callback:  回调方法,
     *   },
     *   Circle:{
     *      title:标题,
     *      seriesName: 系数名称,
     *      contentArray: 数据,
     *      callback:  回调方法,
     *   },
     *   Line:{
     *      title:标题,
     *      Ytitle:  Y轴标题,
     *      lineWidth: 折线宽度数值
     *      hoverLineWidth: hover加粗数值,
     *      contentArray: 数据,
     *      callback:  回调方法,
     *   }
     * }
     */
    var ChartColumn = function(data){
        $(this).highcharts({
            chart: {
                type: 'column',
                margin: [ 50, 50, 100, 80]
            },
            title: {
                text: data.Column.title
            },
            xAxis: {
                categories: data.Column.categoriesArray,
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '8px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: data.Column.Ytitle
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: '<b>{point.y:1f}邮豆</b>'
            },
            series: [{
                data: data.Column.contentArray,
                dataLabels: {
                    enabled: false,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 2,
                    y: 10,
                    style: {
                        fontSize: '4px',
                        fontFamily: 'Verdana, sans-serif',
                        textShadow: '0 0 3px black'
                    }
                }
            }]
        });
        data.Column.callback();
    };
    var ChartCircle = function(data){
        $(this).highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            colors:[
                '#54a6de',
                '#f56c73'
            ],
            title: {
                text: data.Circle.title
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: data.Circle.seriesName,
                data: data.Circle.contentArray
            }]
        });
        data.Circle.callback();
    };
    var ChartLineCount = function(data){
        $(this).highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: data.Line.title
            },
            subtitle: {
                text: data.Line.title
            },
            xAxis: {
                categories: data.Line.categoriesArray
            },
            yAxis: {
                title: {
                    text: data.Line.Ytitle
                },
                min: 0,
                minorGridLineWidth: 0,
                gridLineWidth: 0,
                alternateGridColor: null
            },
            plotOptions: {
                spline: {
                    lineWidth: data.Line.lineWidth,
                    states: {
                        hover: {
                            lineWidth: data.Line.hoverLineWidth
                        }
                    },
                    marker: {
                        enabled: false
                    }
                }
            },
            series: [{
                name: data.Line.seriesName,
                data: data.Line.contentArray

            }],
            navigation: {
                menuItemStyle: {
                    fontSize: '10px'
                }
            }
        });
        data.Line.callback();
    };

    var ChartLineCustomer = function(data){
        $(this).highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: data.LineCustomer.title
            },
            subtitle: {
                text: data.LineCustomer.title
            },
            xAxis: {
                categories: data.LineCustomer.categoriesArray
            },
            yAxis: {
                title: {
                    text: data.LineCustomer.Ytitle
                },
                min: 0,
                minorGridLineWidth: 0,
                gridLineWidth: 0,
                alternateGridColor: null
            },
            plotOptions: {
                spline: {
                    lineWidth: data.LineCustomer.lineWidth,
                    states: {
                        hover: {
                            lineWidth: data.LineCustomer.hoverLineWidth
                        }
                    },
                    marker: {
                        enabled: false
                    }
                }
            },
            series: [{
                name: data.LineCustomer.seriesName,
                data: data.LineCustomer.contentArray

            }],
            navigation: {
                menuItemStyle: {
                    fontSize: '10px'
                }
            }
        });
        data.LineCustomer.callback();
    };

    //以下是网点基础数据统计报表JS





    //以下是邮豆发放记录表JS
});

function handleChartDate(str){
    var arr = str.split("-");
    var month = arr[0];
    var day = arr[1];
    var handle = function(str){
        var a = parseInt(str.charAt(0));
        if ( a == 0 ){
            str = str.substring(1);
        }
        return str;
    };
    return ( handle(month) + '.' + handle(day) );
}