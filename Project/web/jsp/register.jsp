
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script src="/Project/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<!--dengl-->
<div class="yiny">
    <div class="beij_center">
        <div class="dengl_logo">
            <a href="/Project/index.jsp">
                <img src="/Project/images/logo_1.png">
            </a>
            <h1>欢迎注册</h1>
        </div>
    </div>
</div>
<div class="beij_center">
    <form method="post" action="/Project/user" onsubmit="return check()">
        <div class="ger_zhuc_beij">
            <div class="ger_zhuc_biaot">
                <ul>
                    <li class="ger_border_color"><a href="/Project/jsp/register.jsp">个人注册</a></li>
                    <p>我已经注册，现在就<a class="ftx-05 ml10" href="/Project/jsp/login.jsp">登录</a></p>
                </ul>
            </div>
            <div class="zhuc_biaod">
                <div class="reg-items" >
                    <span class="reg-label">
                        <%--@declare id="j_name"--%><label for="J_Name">用&nbsp;户&nbsp;名：</label>
                    </span>
                    <input  class="i-text" name="user_name" type="text">
                    <!--备注————display使用 inline-block-->
                    <div class="msg-box">
                        <div class="msg-box" style="display: none;">
                            <div class="msg-weak" style="display: inline-block;"> <i></i>
                                <div class="msg-cont">4-20个字符，支持汉字、字母、数字及“-”、“_”组合</div>
                            </div>
                        </div>
                        <div class="msg-weak err-tips"  style="display: inline-block;"><div>请输入用户名</div></div>
                    </div>
                    <span class="suc-icon"></span>
                </div>
                <div class="zhuc_biaod">
                    <div class="reg-items" >
                        <span class="reg-label">
                            <%--@declare id="j_name"--%><label for="J_Name">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
                        </span>
                        <input   class="i-text" name="like_name" type="text">
                        <!--备注————display使用 inline-block-->
                        <div class="msg-box">
                            <div class="msg-box" style="display: none;">
                                <div class="msg-weak" style="display: inline-block;"> <i></i>
                                    <div class="msg-cont">4-20个字符，支持汉字、字母、数字及“-”、“_”组合</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="reg-items" >
                        <span class="reg-label">
                            <label for="J_Name">设置密码：</label>
                        </span>
                        <input   class="i-text" name="pwd" type="text">
                        <!--备注————display使用 inline-block-->
                        <div class="msg-box">
                            <div class="msg-box" style="display: none;">
                                <div class="msg-weak" style="display: inline-block;"> <i></i>
                                    <div class="msg-cont">键盘大写锁定已打开，请注意大小写!</div>
                                </div>
                            </div>
                            <div class="msg-weak err-tips"  style="display:none;"><div>请输入密码</div></div>
                        </div>
                    </div>
                    <div class="reg-items" >
                        <span class="reg-label">
                            <label for="J_Name">确认密码：</label>
                        </span>
                        <input   class="i-text" name="pwdAgain" type="text">
                        <!--备注————display使用 inline-block-->
                        <div class="msg-box">
                            <div class="msg-weak err-tips"  style="display: none;"><div>密码不一样</div></div>
                        </div>
                    </div>
                    <div class="reg-items" >
                        <span class="reg-label">
                            <label for="J_Name">充值金额：</label>
                        </span>
                        <input class="i-text" name="user_money" type="text">
                    </div>
                    <div class="reg-items" >
                        <span class="reg-label">
                            <label for="J_Name"> </label>
                        </span>
                    </div>
                    <div class="reg-items" >
                        <span class="reg-label">
                            <label for="J_Name"> </label>
                        </span>
                        <input type="hidden" name="type" value="register">
                        <input class="reg-btn" value="立即注册" type="submit">
                    </div>
                </div>
                <div class="xiaogg">
                    <img src="/Project/images/cdsgfd.jpg">
                </div>
            </div>
        </div>
    </form>
</div>


<div class="jianj_dib jianj_dib_1">
    <div class="beia_hao">
        <p>京ICP备：14012449号 黔ICP证：B2-20140009号  </p>
        <p class="gonga_bei">京公网安备：11010602030054号</p>
    </div>
</div>

<div class="tang" style="display:none;"></div>
<script>
    let error ='${requestScope.error}';
    if (error){
        // alert(error);
        $(".tang").text(error).show().delay(1000).hide(0);
    }
    function check() {
        if (isNaN($("[name='user_money']").val())||$("[name='user_money']").val()<0){
            // alert("请输入正确的金额")
            $(".tang").text("请输入正确的金额").show().delay(1000).hide(0);
            return false;
        }
        return true;
    }
</script>
</body>
</html>
