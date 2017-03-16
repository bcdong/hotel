<%--@elvariable id="order" type="hotel.vo.OrderVO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>酒店管理</title>
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <c:import url="../common/style.jsp" />
    <script src="/resources/js/hotelOrder.js"></script>
</head>
<body>
<section id="sidebar">
    <div class="white-label">
    </div>
    <div id="sidebar-nav">
        <ul>
            <li class="active"><a href="/sbmanager/orders">住房管理</a></li>
            <li><a href="/sbmanager/hotel-plan">酒店概况</a></li>
            <li><a href="/sbmanager/statistic">统计信息</a></li>
            <li><a href="/sbmanager/open-hotel">开店申请</a></li>
            <li><a href="/sbmanager/add-order">非会员入住</a></li>
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
            <h1>住房管理</h1>
        </div>
        <div>
            订单类型：
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="orderType" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    已预订
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="javascript:getOrdersByState('BOOK')">已预订</a></li>
                    <li><a href="javascript:getOrdersByState('IN')">已入住</a></li>
                    <li><a href="javascript:getOrdersByState('LEAVE')">已离店</a></li>
                </ul>
            </div>
        </div>
        <div>
            <table class="table table-striped">
                <tr>
                    <th>客户名称</th>
                    <th>入住日期</th>
                    <th>离店日期</th>
                    <th>房间类型</th>
                    <th>入住人员</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${orderList}" var="order">
                    <tr class="orderItem">
                        <td>${order.vipName}</td>
                        <td>${order.fromTime}</td>
                        <td>${order.toTime}</td>
                        <td>${order.roomType}</td>
                        <td>${order.customer}</td>
                        <td>
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal" data-orderid="${order.id}" data-vipname="${order.vipName}">安排房间</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">vip姓名</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="roomId" class="control-label">房间号:</label>
                        <input type="text" class="form-control" id="roomId">
                        <input type="hidden" id="orderId">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <span class="error"></span>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="sendRoomId()">确认</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
