$(document).ready(function () {
    $.ajax({
        type:'get',
        url:'/topmanager/get-vip-statistic',
        success:function (map) {
            drawOrderCount(map.orderCount);
            drawVipCost(map.vipCost);
        }
    });
});

function drawVipCost(vipCost) {
    var x = [];
    var y = [];
    var index = 0;
    for (;index<vipCost.length;index++) {
        x.push(vipCost[index][0]);
        y.push(vipCost[index][1]);
    }
    Highcharts.chart('vipCostContainer', {
        chart: {
            type: 'column'
        },
        title: {
            text: '会员消费情况统计'
        },
        xAxis: {
            categories: x,
            title: {
                text: '消费金额'
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
            valueSuffix: ''
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
            name: '人数',
            data: y
        }]
    });
}

function drawOrderCount(orderCount) {

    var data = orderCount;
    /**
     * Get histogram data out of xy data
     * @param   {Array} data  Array of tuples [x, y]
     * @param   {Number} step Resolution for the histogram
     * @returns {Array}       Histogram data
     */
    function histogram(data, step) {
        var histo = {},
            x,
            i,
            arr = [];

        // Group down
        for (i = 0; i < data.length; i++) {
            x = Math.floor(data[i][0] / step) * step;
            if (!histo[x]) {
                histo[x] = 0;
            }
            histo[x]++;
        }

        // Make the histo group into an array
        for (x in histo) {
            if (histo.hasOwnProperty((x))) {
                arr.push([parseFloat(x), histo[x]]);
            }
        }

        // Finally, sort the array
        arr.sort(function (a, b) {
            return a[0] - b[0];
        });

        return arr;
    }

    Highcharts.chart('orderCountContainer', {
        chart: {
            type: 'column'
        },
        title: {
            text: '不同订单数会员人数统计'
        },
        xAxis: {
            gridLineWidth: 1,
            title: {
                text: '订单数'
            }
        },
        yAxis: [{
            title: {
                text: '人数'
            }
        }, {
            opposite: true,
            title: {
                text: ''
            }
        }],
        series: [{
            name: '人数',
            type: 'column',
            data: histogram(data, 2),
            pointPadding: 0,
            groupPadding: 0,
            pointPlacement: 'between'
        }]
    });

}