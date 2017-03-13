<%--@elvariable id="hotel" type="hotel.vo.HotelVO"--%>
<%--@elvariable id="plan" type="hotel.vo.PlanVO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${hotel.name}</title>
    <c:import url="../common/style.jsp" />
</head>
<body>
<c:import url="../common/navbar.jsp" />
<h1 class="bg-info">${hotel.name} <span class="label label-warning">${hotel.state}</span></h1>
<p>酒店地址：${hotel.address}</p>
<c:forEach items="${hotel.plans}" var="plan">
    <div class="row" style="margin-bottom:50px">
        <c:choose>
            <c:when test="${plan.roomType == '单人间'}">
                <img src="/resources/image/single_room.jpg">
            </c:when>
            <c:when test="${plan.roomType == '双人间'}">
                <img src="/resources/image/double_room.jpg">
            </c:when>
            <c:otherwise>
                <img src="/resources/image/triple_room.jpg">
            </c:otherwise>
        </c:choose>
        <div class="caption" style="display: inline-block">
            <h3>${plan.roomType}</h3>
            <p>房间价格: ${plan.roomPrice} 元</p>
            <p>剩余房数：${plan.roomCount} 间</p>
            <p><a href="/vip/book-hotel?hotelId=${hotel.id}&roomType=${plan.roomType}" class="btn btn-primary btn-lg" type="button">预定</a></p>
        </div>
    </div>
</c:forEach>
</body>
</html>
