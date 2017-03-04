<%--@elvariable id="vipInfo" type="hotel.vo.VipVO"--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Mr Hotel</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/hotel">酒店预定</a></li>
                <li><a href="/vip/info">会员中心</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.containsKey('vipInfo')}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" id="user-name-field" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${vipInfo.name} <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/hotel">退出</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/vip/login">登录</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
