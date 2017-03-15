<%--@elvariable id="managerInfo" type="hotel.vo.ManagerVO"--%>
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
            <li><a href="/sbmanager/orders">住房管理</a></li>
            <li><a href="/sbmanager/hotel-plan">酒店概况</a></li>
            <li class="active"><a href="/sbmanager/statistic">统计信息</a></li>
            <li><a href="/sbmanager/open-hotel">开店申请</a></li>
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
            <h1>统计数据</h1>
            <p>经理: ${managerInfo.name}             酒店名称:
                <c:choose>
                    <c:when test="${managerInfo.hotel == null}">
                        暂无酒店
                    </c:when>
                    <c:otherwise>
                        ${managerInfo.hotel.name}    酒店状态: ${managerInfo.hotel.state}
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
        <c:if test="${managerInfo.hotel != null}">
            总订单数：${totalOrders}<br/>
            今日收入：${todayIncome}<br/>
            历史收入：${totalIncome}<br/>
        </c:if>
    </div>
</section>
</body>
</html>
