
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户充值</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script src="/Project/js/jquery-1.11.1.min.js"></script>
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
                    <div class="wt">
                        <ul>
                            <li><a href="/Project/jsp/usercenter.jsp">个人信息</a></li>
                            <li><a href="/Project/jsp/shez_toux.jsp">设置头像</a></li>
                            <li class="dangq_hongx"><a href="/Project/jsp/operatemoney.jsp">账户充值</a></li>
                        </ul>
                    </div>
                    <form method="post" action="/Project/user">
                        <div class="wd">
                            <div class="user_set">
                                <div class="item_meic">
                                    <span class="label_meic">昵称：</span>
                                    <div class="fl_e">
                                        ${sessionScope.user.like_name}
                                    </div>
                                </div>
                                <div class="item_meic">
                                    <span class="label_meic">充值金额：</span>
                                    <div class="fl_e">
                                        <input type="text" name="user_money" value="">
                                    </div>
                                </div>
                                <div class="item_meic">
                                    <span class="label_meic">账号密码：</span>
                                    <div class="fl_e">
                                        <input type="password" name="pwd" value="">
                                    </div>
                                </div>
                                <div class="item_meic">
                                    <span class="label_meic"></span>
                                    <div class="fl_e">
                                        <input type="hidden" name="type" value="setMoney">
                                        <input type="submit" value="充值" class="savebtn">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<div class="order_top" style="display:none;" >--%>
<%--    <div class="ewm" id="ma"></div>--%>
<%--</div>--%>

<div class="tang" style="display:none;"></div>
<%@ include file="bottom.jsp"%>
<script src="/Project/js/ewm.js"></script>
<script>
    $(function () {
        let error ='${requestScope.error}';
        if (error=="充值成功！"){
            <%--alert(${sessionScope.user.user_money})--%>
            // alert(error);
            $(".tang").text(error).show().delay(1000).hide(function () {
                window.location.href="/Project/jsp/myorder.jsp";
            });
        }else if (error) {
            // alert(error);
            $(".tang").text(error).show().delay(1000).hide(0);
        }
        // let mk = new QRCode(document.getElementById("ma"),{width:200,height:200});
        // mk.makeCode("http://127.0.0.1:8080/Project/user?type=addMoney");

    })

</script>
</body>
</html>
