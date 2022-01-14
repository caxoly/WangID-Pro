
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>一层</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script src="/Project/js/jquery-1.11.3.min.js" ></script>
    <script type="text/javascript" src="/Project/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/Project/js/jquery.SuperSlide.2.1.1.source.js"></script>
    <script type="text/javascript" src="/Project/houl/jquery.fancybox-1.3.4.js"></script>
</head>
<body>
<div id="header">
    <div class="header-box">
        <h3 class="huany">WangID本地购物商城欢迎您的到来！</h3>
        <ul class="header-right">
            <li class="denglu"><a href="/Project/jsp/login.jsp">账号切换</a></li>
            <li class="shu"></li>
            <c:if test="${empty sessionScope.user}" var="login">
                <li class="denglu">Hi~<a class="red" href="/Project/jsp/login.jsp">请登录!</a> <a href="/Project/jsp/register.jsp">[免费注册]</a></li>
            </c:if>
            <c:if test="${not login}">
                <li class="denglu">Hi~<a class="red" href="javascript:void (0);">${sessionScope.user.like_name}</a></li>
            </c:if>
            <li class="shu"></li>
            <li class="denglu"><a class="ing_ps" href="/Project/jsp/love.jsp">我的收藏</a></li>
            <li class="shu"></li>
            <li class="denglu"><a class="ing_ps ps1" href="/Project/jsp/usercenter.jsp">我的个人中心</a></li>
            <li class="shu"></li>
            <li class="denglu"><a href="/Project/jsp/myorder.jsp">我的订单</a></li>
            <li class="shu"></li>
        </ul>
    </div>
</div>
</body>
</html>
