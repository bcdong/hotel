$(document).ready(function () {
    $.ajax({
        type:'get',
        url:'/topmanager/get-hotel-income',
        success:function (income) {
            draw(income);
        }
    });
});

function draw(income) {
    var data = [];
    var index;
    for (index=0;index<income.length;index++) {
        data.push({
            name:income[index][0],
            y:income[index][1]
        });
    }
    if (data.length > 0) {
        data[0]['sliced']=true;
        data[0]['selected']=true;
    }

    Highcharts.chart('financeContainer', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '各分店营业额分布'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.y:.1f} 元</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                },
                showInLegend: true
            }
        },
        series: [{
            name: '营业额',
            colorByPoint: true,
            data: data
        }]
    });
}