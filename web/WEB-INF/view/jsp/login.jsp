<%--@elvariable id="loginForm" type="hotel.vo.LoginForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <c:import url="common/style.jsp" />
</head>
<body>
<c:import url="common/navbar.jsp" />
<sf:form method="POST" action="/auth/login" commandName="loginForm">
    <div class="error">
        ${errorMessage}
    </div>
    用户类型:
    <sf:select path="userType">
        <sf:option value="vip" label="vip" />
        <sf:option value="manager" label="管理员" />
    </sf:select><br/>
    用户名: <sf:input path="username" />
    <sf:errors path="username" cssClass="error"/><br/>
    密码  : <sf:password path="password" />
    <sf:errors path="password" cssClass="error"/><br/>
    <input type="submit" value="登录">
</sf:form>
<a href="/auth/register">点我注册VIP</a>
</body>
</html>
