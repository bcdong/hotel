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
            <li><a href="/sbmanager/statistic">统计信息</a></li>
            <li><a href="/sbmanager/open-hotel">开店申请</a></li>
            <li class="active"><a href="/sbmanager/add-order">非会员入住</a></li>
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
        <c:if test="${managerInfo.hotel != null && (managerInfo.hotel.state == '营业中')}">
            <form action="/sbmanager/add-order" method="post">
                入住日期：<input type="date" name="fromTime"><br/>
                离店日期：<input type="date" name="toTime"><br/>
                房间类型：<select name="roomType">
                <option value="单人间" selected="selected">单人间</option>
                <option value="双人间">双人间</option>
                <option value="三人间">三人间</option>
            </select><br/>
                价格：<input type="text" name="costBeforeDiscount"><br/>
                房间号：<input type="text" name="roomId"><br/>
                入住人员：<input type="text" name="customer"><br/>
                <input type="submit" value="提交">
            </form>
        </c:if>
    </div>
</section>
</body>
</html>
