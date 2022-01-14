
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改收货地址</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/address.css">
    <script src="/Project/js/jquery-1.12.4.js" ></script>
</head>
<body>
<div class="beij_center">
    <div class="qieh_cs_beij">
        <div class="dengl_logo">
            <a href="/Project/index.jsp"><img src="/Project/images/logo_1.png"></a>
            <h1>修改收货地址</h1>
        </div>
    </div>
</div>

<div class="beij_center top">
    <form method="post" action="/Project/order"  onsubmit="return check()">
        <div class="div">
            <div class="div_div">
                <span>收货人姓名:</span>
                <input type="text" name="shop_name" value="">
            </div>
            <div class="div_div">
                <span>收货人电话:</span>
                <input type="text" name="shop_phone" value="">
            </div>
        </div>
        <div class="div">
            <div class="div_div">
                <span>请选择省/市:</span>
                <select name="province" id="province">
                </select>
                <input type="hidden" name="p_name" value="">
            </div>
            <div class="div_div">
                <span>请选择城市:</span>
                <select name="city" id="city">
                    <option>城市</option>
                </select>
                <input type="hidden" name="c_name" value="">
            </div>
            <div class="div_div">
                <span>请选择区县:</span>
                <select name="district" id="district">
                    <option value="0">区县</option>
                </select>
                <input type="hidden" name="d_name" value="">
            </div>
        </div>
        <div class="div">
            <div class="div_div2">
                <span class="t_span">请输入详细地址:</span>
                <input class="t_input" type="text" name="address" maxlength="20" value="">
            </div>
        </div>
        <div class="div2">
            <div class="div_div3">
                <input class="no" type="submit" value="确认修改">
                <input type="hidden" name="user_id" value="${sessionScope.user.user_id}"/>
                <input type="hidden" name="type" value="update">
            </div>

        </div>
    </form>
</div>
<script src="/Project/js/address.js"></script>
</body>
</html>
