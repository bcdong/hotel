<%--@elvariable id="hotels" type="java.util.List"--%>
<%--@elvariable id="hotel" type="hotel.vo.HotelVO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mr Hotel</title>
    <c:import url="../common/style.jsp" />
</head>
<body>
<c:import url="../common/navbar.jsp" />
<c:forEach items="${hotels}" var="hotel">
    <div class="panel panel-info">
        <div class="panel-heading">${hotel.state}</div>
        <div class="panel-body">
            <h2><a href="/hotel/${hotel.id}">${hotel.name}</a></h2>
            <p>地址: ${hotel.address}</p>
        </div>
    </div>
</c:forEach>
</body>
</html>
