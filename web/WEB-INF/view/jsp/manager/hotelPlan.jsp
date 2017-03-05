<%--@elvariable id="managerInfo" type="hotel.vo.ManagerVO"--%>
<%--@elvariable id="hotelVO" type="hotel.vo.HotelVO"--%>
<%--@elvariable id="plan" type="hotel.vo.PlanVO"--%>
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
            <li class="active"><a href="/sbmanager/hotel-plan">酒店概况</a></li>
            <li><a href="/sbmanager/statistic">统计信息</a></li>
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
            <h1>酒店管理</h1>
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
            <c:forEach items="${managerInfo.hotel.plans}" var="plan">
                <div class="row thumbnail">
                    <c:choose>
                        <c:when test="${plan.roomType == '单人间'}">
                            <img src="/resources/image/single_room.jpg">
                        </c:when>
                        <c:when test="${plan.roomType == '双人间'}">
                            <img src="/resources/image/double_room.jpg">
                        </c:when>
                        <c:otherwise>
                            <img src="/resources/image/triple_room.jpg">
                        </c:otherwise>
                    </c:choose>
                    <div class="caption">
                        <h3>${plan.roomType}</h3>
                        <p>房间价格: ${plan.roomPrice} 元</p>
                        <p>剩余房数：${plan.roomCount} 间</p>
                        <p><a href="#" class="btn btn-primary btn-lg" type="button">预定</a></p>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <div class="widget-box sample-widget">
            <div class="widget-header">
                <h2>Widget Header</h2>
                <i class="fa fa-cog"></i>
            </div>
            <div class="widget-content">
                <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/87118/sample-data-1.png">
            </div>
        </div>
    </div>
</section>
</div>
</body>
</html>
