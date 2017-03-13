<%--@elvariable id="vipInfo" type="hotel.vo.VipVO"--%>
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
            <li class="active"><a href="/vip/account">账户管理</a></li>
            <li><a href="/vip/password">修改密码</a></li>
        </ul>
    </div>
</section>
<section id="content">
    <div class="content">
        <div class="content-header">
            <h1>账户管理</h1>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">账户充值</div>
            <div class="panel-body">
                当前余额：${vipInfo.balance}<br/>
                <div class="error">${chargeError}</div>
                <div class="success-msg">${chargeSuccess}</div>
                请输入充值金额：<br/>
                <form class="form-inline" action="/vip/charge" method="post">
                    <div class="form-group">
                        <label class="sr-only" for="money">Amount (in dollars)</label>
                        <div class="input-group">
                            <div class="input-group-addon">￥</div>
                            <input type="text" name="money" class="form-control" id="money" placeholder="Amount">
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">充值</button>
                </form>
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">积分兑换</div>
            <div class="panel-body">
                <p>当前积分：${vipInfo.score}</p>
                <p><small><em>每100积分可兑换1元，兑换积分必须为100的整数倍</em></small></p>
                <div class="error">${scoreError}</div>
                <div class="success-msg">${scoreSuccess}</div>
                请输入兑换积分数：<br/>
                <form class="form-inline" action="/vip/score" method="post">
                    <div class="form-group">
                        <label class="sr-only" for="score">score</label>
                        <div class="input-group">
                            <div class="input-group-addon">积分</div>
                            <input type="text" name="score" class="form-control" id="score" placeholder="Amount">
                            <div class="input-group-addon">00</div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">兑换</button>
                </form>
            </div>
        </div>
        <div class="panel panel-danger">
            <div class="panel-heading">取消会员</div>
            <div class="panel-body">
                <div class="alert alert-danger" role="alert">
                    <strong>注意：取消会员资格后账户将不可用，请谨慎操作！</strong>
                </div>
                <a class="btn btn-danger" role="button" href="/vip/delete-vip">确认取消</a>
            </div>
        </div>
    </div>
</section>
</body>
</html>
