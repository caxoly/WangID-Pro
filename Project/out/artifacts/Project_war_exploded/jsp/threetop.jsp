
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>三层</title>
</head>
<body>
<!--侧边导航-->
<div class="tod_tongc_zuoc">
    <div class="zuoc_toux">
        <div class="toux_kuang">
            <div class="userImage">
                <div class="myGome_userPhoto">
                    <c:if test="${empty sessionScope.user.image_url}" var="ind">
                        <img  src="/Project/images/DefaultAvatar.jpg" />
                    </c:if>
                    <c:if test="${not ind}">
                        <img  src="/Project/images/head/${sessionScope.user.image_url}" />
                    </c:if>
                    <a class="edit_photo_bitton" href="profile" target="_blank">编辑</a>
                </div>
            </div>
            <div class="user_name_Level">
                <p class="user_name">${sessionScope.user.user_name}</p>
                <p class="userLevel">账户余额：<span id="user_money"></span></p>
            </div>
        </div>
        <div class="user_counts">
            <ul>
                <li>
                    <div class="count_item">
                        <a href="/Project/jsp/notpayfor.jsp">
                            <i class="count_icon count_icon01"></i> 待付款
                        </a>
                    </div>
                </li>
                <li>
                    <div class="count_item">
                        <a href="/Project/jsp/waitgoods.jsp">
                            <i class="count_icon count_icon02"></i> 待发货
                        </a>
                    </div>
                </li>
                <li>
                    <div class="count_item">
                        <a href="/Project/jsp/w_a_goods.jsp">
                            <i class="count_icon count_icon03"></i> 待收货
                        </a>
                    </div>
                </li>
                <li>
                    <div class="count_item">
                        <a href="/Project/jsp/receivegoods.jsp">
                            <i class="count_icon count_icon04"></i> 已签收
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="wod_tongc_daoh_lieb">
        <div class="diy_top">
            <ul>
                <h3>订单中心</h3>
                <li><a href="/Project/jsp/myorder.jsp">我的订单</a></li>
            </ul>
            <ul>
                <h3>管理中心</h3>
                <li><a href="/Project/jsp/love.jsp">我的收藏</a></li>
                <li><a href="/Project/jsp/operatemoney.jsp">账户充值</a></li>
                <li><a href="/Project/jsp/look.jsp">最近浏览</a></li>
            </ul>
        </div>
        <div class="diy_top">
            <ul>
                <h3>账户设置</h3>
                <li><a href="/Project/jsp/usercenter.jsp">基本资料</a></li>
                <li><a href="/Project/jsp/shez_toux.jsp">头像更换</a></li>
                <li><a href="/Project/jsp/myaddress.jsp">收货地址</a></li>
            </ul>
        </div>
    </div>
</div>
<script>
    let sum = parseFloat('${sessionScope.user.user_money}');
    // alert(sum.toFixed(2))
    $("#user_money").text(sum.toFixed(2));
</script>
</body>
</html>
