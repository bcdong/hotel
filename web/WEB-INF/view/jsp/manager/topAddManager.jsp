<%--@elvariable id="managerInfo" type="hotel.vo.ManagerVO"--%>
<%--@elvariable id="managerForm" type="hotel.vo.ManagerForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>酒店管理</title>
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <c:import url="../common/style.jsp" />
</head>
<body>
<section id="sidebar">
    <div class="white-label">
    </div>
    <div id="sidebar-nav">
        <ul>
            <li><a href="/topmanager/check-apply">审查开店请求</a></li>
            <li><a href="/topmanager/check-status">查看入住情况</a></li>
            <li><a href="/topmanager/check-vip">查看会员数据</a></li>
            <li><a href="/topmanager/check-finance">查看财务统计</a></li>
            <li class="active"><a href="/topmanager/add-manager">添加管理员</a></li>
        </ul>
    </div>
</section>
<section id="content">
    <div id="header">
        <div class="header-nav">
            <div class="menu-button">
                <!--<i class="fa fa-navicon"></i>-->
            </div>
            <div class="nav">
                <ul>
                    <li class="nav-profile">
                        <div class="nav-profile-image">
                            <img src="/resources/image/avatar.jpg" alt="profile-img" alt="profile image">
                            <a class="nav-profile-name" href="/auth/logout">登出</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="content-header">
            <h1>添加管理员</h1>
            <p>总经理: ${managerInfo.name}
            </p>
        </div>
        <div class="error">${errorMessage}</div>
        <div class="success">${successMessage}</div>
        <sf:form action="/topmanager/add-manager" method="POST" commandName="managerForm">
            姓名: <sf:input path="name" /> <sf:errors path="name" cssClass="error" /><br/>
            用户名: <sf:input path="username" /><sf:errors path="username" cssClass="error" /><br/>
            密码: <sf:password path="password" /><sf:errors path="password" cssClass="error" /><br/>
            <input type="submit" value="提交">
        </sf:form>
    </div>
</section>
</div>
</body>
</html>
