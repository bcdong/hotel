<%--@elvariable id="managerInfo" type="hotel.vo.ManagerVO"--%>
<%--@elvariable id="incomeVO" type="hotel.vo.HotelIncomeVO"--%>
<%--@elvariable id="incomeVOs" type="java.util.List<hotel.vo.HotelIncomeVO>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>酒店管理</title>
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <c:import url="../common/style.jsp" />
    <script src="/resources/js/managerJieSuan.js"></script>
</head>
<body>
<section id="sidebar">
    <div class="white-label">
    </div>
    <div id="sidebar-nav">
        <ul>
            <li><a href="/topmanager/check-apply">审查开店请求</a></li>
            <li class="active"><a href="/topmanager/jiesuan">结算各店收入</a></li>
            <li><a href="/topmanager/check-status">查看入住情况</a></li>
            <li><a href="/topmanager/check-vip">查看会员数据</a></li>
            <li><a href="/topmanager/check-finance">查看财务统计</a></li>
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
            <h1>结算各店收入</h1>
            <p>总经理: ${managerInfo.name}
            </p>
        </div>
        <table class="table">
            <tr>
                <th>酒店名称</th>
                <th>酒店状态</th>
                <th>今日收入</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${incomeVOs}" var="incomeVO">
                <tr class="hotelItem">
                    <td>${incomeVO.name}</td>
                    <td>${incomeVO.state}</td>
                    <td>${incomeVO.todayIncome}</td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick="jiesuan(${incomeVO.id})">结算</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>
