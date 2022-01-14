
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script type="text/javascript" src="/Project/js/jquery1.42.min.js"></script>
</head>
<body>
<%@ include file="onetop.jsp"%>

<div class="yiny yiny_gouwc">
    <div class="beij_center">
        <div class="dengl_logo">
            <a href="/Project/index.jsp"><img src="/Project/images/logo_1.png"></a>
            <h1>购物车</h1>
        </div>
    </div>
</div>
<div class="container">
    <div class="cart-login-tip fl" id="idnotlogin" act-not-login-info="" style="display: none;">
        您还没有登录！登录后购物车的商品将保存到您账号中。
        <a class="cart-login-btn" href="dengl.html">
            立即登录
        </a>
    </div>
</div>

<form method="post" action="/Project/order">
    <div class="beij_center">
        <div class="cart-main-header clearfix">
            <div class="cart-col-1">
                <input type="checkbox" class="jdcheckbox" id="allCheck" >
            </div>
            <div class="cart-col-2">全选</div>
            <div class="cart-col-3">商品信息</div>
            <div class="cart-col-4">
                <div class="cart-good-real-price">单价</div>
            </div>
            <div class="cart-col-5">数量</div>
            <div class="cart-col-6">
                <div class="cart-good-amount">小计</div>
            </div>
            <div class="cart-col-7">操作</div>
        </div>
    </div>
    <div class="container">
        <div class="cart-shop-header"></div>
        <c:forEach items="${requestScope.list}" var="item">
            <div class="cart-shop-goods dangq_honh">
                <div class="cart-shop-good" name="trDelete">
                    <div class="cart-col-1">
                        <input type="checkbox" name="cart_id" class="jdcheckbox" value="${item.cart_id}" >
                    </div>
                    <div class="cart-col-2" style="height: 82px;">
                        <a href="" target="_blank" class="g-img"><img src="/Project/images/head/${item.url}"></a>
                    </div>
                    <div class="fl houj_c">
                        <div class="cart-dfsg"><div class="cart-good-name"><a href="#" target="_blank">${item.goods_name}</a></div></div>
                        <div class="cart-good-pro"></div>
                        <div class="cart-col-4"><div class="cart-good-real-price ">¥&nbsp;${item.price}</div><div class="red"></div></div>
                        <div class="cart-col-5" name="five">
                            <div class="gui-count cart-count" >
                                <span class="gui-count-btn gui-count-sub gui-count-disabled del" data-price="${item.price}">-</span>
                                <span class="gui-count-btn gui-count-add gui-count-disabled add" data-price="${item.price}">+</span>
                                <div class="gui-count-input">
                                    <input class="num" type="text" value="${item.num}">
                                </div>
                            </div>
                        </div>
                        <div class="cart-col-6" name="six">
                            <div class="cart-good-amount">¥&nbsp;${item.num*item.price}</div>
                        </div>
                    <div class="cart-col-7">
                        <div class="cart-good-fun delfixed delete" data-id="${item.cart_id}">删除</div>
                    </div>
                </div>
            </div>
            </div>
        </c:forEach>
        <input type="submit" id="toSubmit" style="display: none">
        <input type="hidden" name="sumMoney" value="">
        <input type="hidden" name="type" value="getOrder">
    </div>
</form>
<div class="jies_beij">
    <div class="beij_center over_dis">
        <div class="jies_ann_bei">
            <p>已选 <em id="num">1</em> 件商品 总计（不含运费）：<span id="sumMoney">￥0.00</span></p>
            <a id="submit" href="javascript:void(0);" class="order_btn">去结算</a>
        </div>
    </div>
</div>
<div class="beij_center">
    <div class="investment_f">
        <div class="investment_title">
            <div class="ds_dg on_d">最近预览推荐</div>
        </div>
        <div class="investment_con">

            <div class="picScroll_left_s">
                <div class="hd">
                    <ul></ul>
                </div>
                <div class="bd">
                    <ul id="recentLook" class="picList">

                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="tang" style="display:none;"></div>
<%@ include file="bottom.jsp"%>

<script src="/Project/js/cart.js"></script>
<script>
    let user_type = parseInt('${sessionScope.user.user_type}');
    // alert(user_type);
</script>

</body>
</html>
