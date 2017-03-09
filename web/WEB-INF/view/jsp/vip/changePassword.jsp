<%--@elvariable id="vipInfo" type="hotel.vo.VipVO"--%>
<%--@elvariable id="passwordForm" type="hotel.vo.PasswordForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>VIP</title>
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <c:import url="../common/style.jsp" />
</head>
<body>
<c:import url="../common/navbar.jsp" />
<section id="sidebar">
    <div id="sidebar-nav">
        <ul>
            <li><a href="/vip/info">基本信息</a></li>
            <li><a href="/vip/record">预定记录</a></li>
            <li><a href="/vip/account">账户管理</a></li>
            <li class="active"><a href="/vip/password">修改密码</a></li>
        </ul>
    </div>
</section>
<section id="content">
    <div class="content">
        <div class="content-header">
            <h1>更改密码</h1>
        </div>
        <div class="error">${errorMessage}</div>
        <div class="success-msg">${successMessage}</div>
        <sf:form action="/vip/password" method="POST" commandName="passwordForm">
            旧密码：<sf:password path="oldPassword" />
            <sf:errors path="oldPassword" cssClass="error"/><br/>
            新密码：<sf:password path="newPassword" />
            <sf:errors path="newPassword" cssClass="error"/><br/>
            <input type="submit" value="更新">
        </sf:form>
    </div>
</section>
</div>
</body>
</html>
