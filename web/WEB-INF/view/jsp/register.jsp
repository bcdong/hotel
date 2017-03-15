<%--@elvariable id="regForm" type="hotel.vo.RegisterForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <c:import url="common/style.jsp" />
</head>
<body>
<c:import url="common/navbar.jsp" />
<h2>注册</h2>
<sf:form method="POST" action="/auth/register" commandName="regForm">
    <div class="error">
            ${errorMessage}
    </div>
    账号类型:
    <sf:select path="userType">
        <sf:option value="vip" label="vip" />
        <sf:option value="manager" label="管理员" />
    </sf:select><br/>
    用户名: <sf:input path="username" />
    <sf:errors path="username" cssClass="error"/><br/>
    密码: <sf:password path="password" />
    <sf:errors path="password" cssClass="error"/><br/>
    姓名: <sf:input path="name" />
    <sf:errors path="name" cssClass="error"/><br/>
    <input type="submit" value="注册">
</sf:form>
<a href="/auth/login">点我登录</a>
</body>
</html>
