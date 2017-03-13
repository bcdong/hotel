<%--@elvariable id="bookForm" type="hotel.vo.BookHotelForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${bookForm.hotelName}</title>
    <c:import url="../common/style.jsp" />
</head>
<body>
<c:import url="../common/navbar.jsp" />
<h2 class="bg-info">预订酒店</h2>
酒店名称：${bookForm.hotelName}<br/>
酒店地址：${bookForm.hotelAddress}<br/>
房间类型：${bookForm.roomType}<br/>
房间单价：${bookForm.roomPrice}<br/>
<form action="/vip/book-hotel" method="post">
    <input type="hidden" name="hotelId" value="${bookForm.hotelId}">
    <input type="hidden" name="hotelName" value="${bookForm.hotelName}">
    <input type="hidden" name="roomType" value="${bookForm.roomType}">
    <input type="hidden" name="roomPrice" value="${bookForm.roomPrice}">
    <input type="hidden" name="hotelAddress" value="${bookForm.hotelAddress}">
    <input type="hidden" name="today" value="${bookForm.today}">
    入住日期：<input type="date" name="fromTime" value="${bookForm.fromTime}" min="${bookForm.today}" /><br/>
    退房日期：<input type="date" name="toTime" value="${bookForm.toTime}"/><br/>
    入住人员：<input type="text" name="customer" value="${bookForm.customer}"><br/>
    <div class="error">${errorMessage}</div>
    <input type="submit" value="提交">
</form>

</body>
</html>
