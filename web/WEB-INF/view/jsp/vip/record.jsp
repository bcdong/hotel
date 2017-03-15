<%--@elvariable id="vipInfo" type="hotel.vo.VipVO"--%>
<%--@elvariable id="bookOrders" type="java.util.List<hotel.vo.OrderVO>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>VIP</title>
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <c:import url="../common/style.jsp" />
    <script src="/resources/js/vipRecord.js"></script>
</head>
<body>
<c:import url="../common/navbar.jsp" />
<section id="sidebar">
    <div id="sidebar-nav">
        <ul>
            <li><a href="/vip/info">基本信息</a></li>
            <li class="active"><a href="/vip/record">预定记录</a></li>
            <li><a href="/vip/account">账户管理</a></li>
            <li><a href="/vip/password">修改密码</a></li>
        </ul>
    </div>
</section>
<section id="content">
    <div class="content">
        <div class="content-header">
            <h1>预订记录</h1>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">统计数据</div>
            <div class="panel-body">
                您总共订过 <strong>${orderCount}</strong> 间房间，共消费 <strong>${totalCost}</strong> 元。
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">历史订单</div>
            <div class="panel-body">
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
                            <th>酒店名称</th>
                            <th>入住日期</th>
                            <th>离店日期</th>
                            <th>房间类型</th>
                            <th>入住人员</th>
                            <th>支付金额</th>
                            <th>支付方式</th>
                            <th>订单状态</th>
                            <th>操作</th>
                        </tr>
                        <%--这里添加通过js添加订单--%>
                        <c:forEach items="${bookOrders}" var="order">
                            <tr class="orderItem">
                                <td>${order.hotelName}</td>
                                <td>${order.fromTime}</td>
                                <td>${order.toTime}</td>
                                <td>${order.roomType}</td>
                                <td>${order.customer}</td>
                                <td>${order.costBeforeDiscount}</td>
                                <td>${order.payMethod}</td>
                                <td>${order.state}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" onclick="cancel(${order.id})">退订</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
