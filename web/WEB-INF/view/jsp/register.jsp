<%--@elvariable id="vipVO" type="hotel.vo.VipVO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册VIP</title>
    <c:import url="common/style.jsp" />
</head>
<body>
<c:import url="common/navbar.jsp" />
<h2>注册VIP</h2>
<sf:form method="POST" action="/auth/register" commandName="vipVO">
    <div class="error">
            ${errorMessage}
    </div>
    用户名: <sf:input path="username" />
    <sf:errors path="username" cssClass="error"/><br/>
    密码: <sf:password path="password" />
    <sf:errors path="password" cssClass="error"/><br/>
    姓名: <sf:input path="name" />
    <sf:errors path="name" cssClass="error"/><br/>
    银行卡号: <sf:input path="bankId" />
    <sf:errors path="bankId" cssClass="error" /><br/>
    <input type="submit" value="注册">
</sf:form>
</body>
</html>
