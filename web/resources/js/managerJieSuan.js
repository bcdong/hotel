function jiesuan(hotelId) {
    $.ajax({
        type:'post',
        url:'/topmanager/jiesuan',
        data:{
            hotelId:hotelId
        },
        success:function (incomeVOs) {
            $('tr').remove('.hotelItem');
            var index;
            for (index=0; index<incomeVOs.length;index++) {
                var incomeVO = incomeVOs[index];
                var row = '<tr class="hotelItem">';
                row += '<td>'+incomeVO.name+'</td>';
                row += '<td>'+incomeVO.state+'</td>';
                row += '<td>'+incomeVO.todayIncome+'</td>';
                row += '<td><button type="button" class="btn btn-primary" onclick="jiesuan('+incomeVO.id+')">结算</button></td>';
                row += '</tr>';
                $('.table>tbody').append(row);
            }
        }
    });
}