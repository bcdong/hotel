<%--@elvariable id="managerInfo" type="hotel.vo.ManagerVO"--%>
<%--@elvariable id="hotelVO" type="hotel.vo.HotelVO"--%>
<%--@elvariable id="hotelVOs" type="java.util.List<hotel.vo.HotelVO>"--%>
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
            <li class="active"><a href="/topmanager/check-apply">审查开店请求</a></li>
            <li><a href="/topmanager/check-status">查看入住情况</a></li>
            <li><a href="/topmanager/check-vip">查看会员数据</a></li>
            <li><a href="/topmanager/check-finance">查看财务统计</a></li>
            <li><a href="/topmanager/add-manager">添加管理员</a></li>
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
            <h1>审查开店请求</h1>
            <p>总经理: ${managerInfo.name}
            </p>
        </div>
        <table class="table">
            <th>
                <td>酒店名称</td>
                <td>申请人</td>
                <td>操作</td>
            </th>
            <c:forEach items="${hotelVOs}" var="hotelVO">
                <tr>
                    <td>${hotelVO.name}</td>
                    <td>${hotelVO.managerName}</td>
                    <td><a class="btn btn-success" type="button" href="/topmanager/handle-request?action=prove&id=${hotelVO.id}">
                        批准
                    </a>
                        <a class="btn btn-danger" type="button" href="/topmanager/handle-request?action=reject&id=${hotelVO.id}">
                            拒绝
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</div>
</body>
</html>
