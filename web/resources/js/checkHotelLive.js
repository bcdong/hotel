$(document).ready(function () {
    $.ajax({
        type:'get',
        url:'/topmanager/get-hotel-count',
        success:function (liveCount) {
            draw(liveCount);
        }
    });
});

function draw(liveCount) {
    var x = [];
    var y = [];
    var index = 0;
    for (;index<liveCount.length;index++) {
        x.push(liveCount[index][0]);
        y.push(liveCount[index][1]);
    }
    Highcharts.chart('container', {
        chart: {
            type: 'bar'
        },
        title: {
            text: '各酒店当前入住人数统计'
        },
        xAxis: {
            categories: x,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' 人'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 80,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '酒店当前入住人数',
            data: y
        }]
    });
}