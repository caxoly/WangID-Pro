
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
</head>
<body>
<section>
    <div class="beij_center">
        <div class="dengl_logo">
            <a href="/Project/index.jsp"><img src="/Project/images/logo_1.png"></a>
            <h1>欢迎登录</h1>
        </div>
    </div>
    <div class="dengl_beij">
        <div class="banner_xin">
            <img src="/Project/images/ss.jpg">
        </div>
        <div class="beij_center dengl_jvz">
            <form method="post" action=/Project/user onsubmit="return check()">
                <div class="login_form">
                    <div class="login_tab">
                        <h2>欢迎登录</h2>
                    </div>
                    <div class="kengl_kuang">
                        <div class="txt_kuang">
                            <input type="text" class="itxt" name="user_name"  placeholder="用户名">
                            <input type="password" class="itxt" name="pwd"  placeholder="密码">
                        </div>
                        <div class="remember"></div>
                        <input type="hidden" name="type" value="login">
                        <input type="submit" tabindex="5" value="登 录" class="btnnuw">
                    </div>
                    <div class="corp_login">
                        <div class="regist_link"><a href="/Project/jsp/register.jsp"><b></b>立即注册</a></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="jianj_dib">
        <div class="beia_hao">
            <p>京ICP备：14012449号 黔ICP证：B2-20140009号  </p>
            <p class="gonga_bei">京公网安备：11010602030054号</p>
        </div>
    </div>
</section>
<div class="tang" style="display:none;"></div>
<script src="/Project/js/jquery-1.11.1.min.js"></script>
<script>
    let error = '${requestScope.error}';
    if(error){
        alert(error);
    }
    function check() {
        if($("[name='user_name']").val().length<2){
            // alert("请填写正确用户名");
            $(".tang").text("请填写正确用户名").show().delay(1000).hide(0);
            return false;
        }
        if($("[name='pwd']").val().length<6){
            // alert("请填写密码");
            $(".tang").text("请填写密码").show().delay(1000).hide(0);
            return false;
        }
        return true;
    }
</script>
</body>
</html>
