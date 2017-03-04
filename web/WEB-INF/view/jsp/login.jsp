<%--@elvariable id="loginForm" type="hotel.vo.LoginForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <c:import url="common/style.jsp" />
</head>
<body>
<sf:form method="POST" action="/auth/login" commandName="loginForm">
    <div class="error">
        ${errorMessage}
    </div>
    用户类型:
    <sf:select path="userType">
        <sf:option value="vip" label="vip" />
        <sf:option value="manager" label="管理员" />
    </sf:select><br/>
    <sf:label path="username">用户名</sf:label>
    <sf:input path="username" cssErrorClass="error"/>
    <sf:errors path="username" cssClass="error"/><br/>
    <sf:label path="password">密码</sf:label>
    <sf:password path="password" cssErrorClass="error"/>
    <sf:errors path="password" cssClass="error"/><br/>
    <input type="submit" value="登录">
</sf:form>
</body>
</html>
