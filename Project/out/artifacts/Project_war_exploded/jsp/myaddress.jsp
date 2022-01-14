
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的收货地址</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script src="/Project/js/jquery-1.12.4.js" ></script>
</head>
<body>
<%@ include file="onetop.jsp"%>
<%@ include file="fourtop.jsp"%>

<div class="wod_tongc_zhongx">
    <div class="beij_center">
        <div class="myGomeWeb">
            <!--侧边导航-->
            <%@include file="threetop.jsp"%>
            <!--左边内容-->
            <div class="mod_main">
                <div class="jib_xinx_kuang">
                    <div class="shand_piaot">收货地址</div>
                    <div id="myBox" class="shouh_diz_beij">


                    </div>
                    <div class="xinz_shouh_dz_ann"><a href="/Project/jsp/address.jsp" data-reveal-id="myModal">新增收货地址</a></div>
                </div>
            </div>
            <!--左边内容结束-->
        </div>
    </div>
</div>
<div class="tang" style="display:none;"></div>
<%@ include file="bottom.jsp"%>
<script src="/Project/js/myaddress.js"></script>
<script>
    //用户id
    let user_id = "${sessionScope.user.user_id}"
</script>

</body>
</html>
