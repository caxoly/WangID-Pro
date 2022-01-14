
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付密码</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <script src="/Project/js/jquery-1.11.3.min.js" ></script>
</head>
<body>

<div class="order_top">
    <div class="top_one">
        <p class="top_p">昵称：<span class="top_span">${sessionScope.user.like_name}</span></p>
        <p class="top_p">请输入支付密码：<input class="top_input" type="password" name="payPwd" value=""></p>
        <input class="to" id="paySubmit" type="submit" value="确认支付">
    </div>
</div>
<div class="tan" style="display:none;"></div>
<script src="/Project/js/pay.js"></script>
<script>
    //获取用户的id
    let user_id = "${sessionScope.user.user_id}";
    //用户余额
    let user_money = parseFloat(${sessionScope.user.user_money});
    let pwd = ${sessionScope.user.pwd};

</script>
</body>
</html>
