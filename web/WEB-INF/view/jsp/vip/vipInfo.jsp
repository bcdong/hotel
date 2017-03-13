<%--@elvariable id="vipInfo" type="hotel.vo.VipVO"--%>
<%--@elvariable id="basicInfo" type="hotel.vo.VipBasicInfo"--%>
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
            <li class="active"><a href="/vip/info">基本信息</a></li>
            <li><a href="/vip/record">预定记录</a></li>
            <li><a href="/vip/account">账户管理</a></li>
            <li><a href="/vip/password">修改密码</a></li>
        </ul>
    </div>
</section>
<section id="content">
    <div class="content">
        <div class="content-header">
            <h1>个人信息</h1>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">不可变更信息</div>
            <div class="panel-body">
                VIP编号：${vipInfo.id}<br/>
                账户余额：${vipInfo.balance}<br/>
                VIP等级：${vipInfo.level}<br/>
                VIP经验：${vipInfo.experience}<br/>
                VIP积分：${vipInfo.score}<br/>
                VIP状态：${vipInfo.state}<br/>
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">可变更信息</div>
            <div class="panel-body">
                <sf:form action="/vip/info" method="POST" commandName="basicInfo">
                    <div class="success-msg">${successMessage}</div>
                    VIP姓名：<sf:input path="name" />
                    <sf:errors path="name" cssClass="error"/><br/>
                    银行卡号：<sf:input path="bankId" />
                    <sf:errors path="bankId" cssClass="error"/><br/>
                    <input type="submit" value="更新">
                </sf:form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
