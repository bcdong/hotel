$(function () {
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var orderId = button.data('orderid');// Extract info from data-* attributes
        var vipName = button.data('vipname');
        var modal = $(this);
        modal.find('.modal-title').text('会员姓名: '+vipName);
        modal.find('#orderId').val(orderId);
    });
});

function getOrdersByState(state) {
    $.ajax({
        url:'/sbmanager/orders',
        type:'post',
        data:{
            state:state
        },
        success:function (orderVOList) {
            $('tr').remove();
            var type='';
            switch (state) {
                case 'BOOK':type='已预订';break;
                case 'IN':type='已入住';break;
                case 'LEAVE':type='已离店';break;
            }
            $('#orderType').html(type+'<span class="caret"></span>');
            var index;
            for (index=0; index<orderVOList.length;index++) {
                var order = orderVOList[index];
                var row = '<tr class="orderItem">';
                row += '<td>' + order.vipName + '</td>';
                row += '<td>' + order.fromTime + '</td>';
                row += '<td>' + order.toTime + '</td>';
                row += '<td>' + order.roomType + '</td>';
                row += '<td>' + order.customer + '</td>';
                if (state == 'BOOK') {
                    row += '<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal" data-orderid="' + order.id+'" data-vipname="' + order.vipName+'">安排房间</button></td>';
                } else if (state == 'IN') {
                    row+='<td><button type="button" class="btn btn-primary" onclick="leave('+order.id+')">确认离店</button></td>';
                } else {
                    row += '<td>无</td>';
                }
                row += '</tr>';
                $('.table>tbody').append(row);
            }
        }
    });
}

function sendRoomId() {
    var orderId = $('#orderId').val();
    var roomId = $('#roomId').val();
    $.ajax({
        url:'/sbmanager/postLiveIn',
        type:'post',
        data:{
            orderId:orderId,
            roomId:roomId
        },
        success:function (result) {
            if (result) {
                $('.error').html('');
                $('#myModal').modal('hide');
                getOrdersByState('BOOK');
            } else {
                $('.error').html('对不起，系统出错，请稍后重试');
            }
        }
    });
}

function leave(orderId) {
    $.ajax({
        url:'/sbmanager/postLeave',
        type:'post',
        data:{
            orderId:orderId
        },
        success:function (result) {
            getOrdersByState('IN');
        }
    });
}

