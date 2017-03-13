<%--@elvariable id="orderVO" type="hotel.vo.OrderVO"--%>
<%--@elvariable id="vipInfo" type="hotel.vo.VipVO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>确认订单</title>
    <c:import url="../common/style.jsp" />
</head>
<body>
<c:import url="../common/navbar.jsp" />
<div class="panel panel-info">
    <div class="panel-heading">${orderVO.hotelName}酒店订单</div>
    <div class="panel-body">
        VIP：   ${vipInfo.name}(${vipInfo.level}级会员)<br/>
        酒店名称：${orderVO.hotelName}<br/>
        房间类型：${orderVO.roomType}<br/>
        入住时间：${orderVO.fromTime}<br/>
        离店时间：${orderVO.toTime}<br/>
        入住人员：${orderVO.customer}<br/>
        折前金额：${orderVO.costBeforeDiscount}<br/>
        会员折扣：${orderVO.discount}<br/>
        订单金额：${orderVO.costBeforeDiscount-orderVO.discount}<br/>
        <form action="/vip/confirm-order" method="post">
            <input type="hidden" name="hotelId" value="${orderVO.hotelId}">
            <input type="hidden" name="hotelName" value="${orderVO.hotelName}">
            <input type="hidden" name="roomType" value="${orderVO.roomType}">
            <input type="hidden" name="fromTime" value="${orderVO.fromTime}">
            <input type="hidden" name="toTime" value="${orderVO.toTime}">
            <input type="hidden" name="customer" value="${orderVO.customer}">
            <input type="hidden" name="costBeforeDiscount" value="${orderVO.costBeforeDiscount}">
            <input type="hidden" name="discount" value="${orderVO.discount}">
            请选择付款方式：
            <select name="payMethod">
                <option value="VIP_CARD" selected>会员卡</option>
                <option value="CASH">现金</option>
            </select><br/>
            <input type="submit" value="确认订单">
        </form>
    </div>
</div>
</body>
</html>
