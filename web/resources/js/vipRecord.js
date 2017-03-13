function getOrdersByState(state) {
    $.ajax({
        url:'/vip/order',
        type:'post',
        data:{
            state:state
        },
        success:function (orderVOList) {
            var type='';
            switch (state) {
                case 'BOOK':type='已预订';break;
                case 'IN':type='已入住';break;
                case 'LEAVE':type='已离店';break;
            }
            $('#orderType').html(type+'<span class="caret"></span>');
            $('tr').remove('.orderItem');
            var index;
            for (index in orderVOList) {
                var order = orderVOList[index];
                var row = '<tr class="orderItem">';
                row += '<td>' + order.hotelName + '</td>';
                row += '<td>' + order.fromTime + '</td>';
                row += '<td>' + order.toTime + '</td>';
                row += '<td>' + order.roomType + '</td>';
                row += '<td>' + order.customer + '</td>';
                row += '<td>' + order.costBeforeDiscount + '</td>';
                row += '<td>' + order.payMethod + '</td>';
                row += '<td>' + order.state + '</td>';
                row += '</tr>';
                $('.table>tbody').append(row);
            }
        }
    });
}
