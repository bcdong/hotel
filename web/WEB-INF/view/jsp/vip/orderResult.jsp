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
    <h2 class="error">${errorMessage}</h2>
    <h2 class="success-msg">${successMessage}</h2>
</body>
</html>
