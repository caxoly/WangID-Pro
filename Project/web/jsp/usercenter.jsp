
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
<%@include file="onetop.jsp"%>
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
                            <li class="dangq_hongx"><a href="/Project/jsp/usercenter.jsp">个人信息</a></li>
                            <li><a href="/Project/jsp/shez_toux.jsp">设置头像</a></li>
                            <li><a href="/Project/jsp/operatemoney.jsp">账户充值</a></li>
                        </ul>
                    </div>
                    <div class="wd">
                        <div class="user_set">
                            <form method="post" action="/Project/user">
                                <div class="wd">
                                    <div class="user_set">
                                        <div class="item_meic">
                                            <span class="label_meic"><em>*</em> 用户名：</span>
                                            <div class="fl_e">
                                                <div><strong>${sessionScope.user.user_name}</strong></div>
                                            </div>
                                        </div>
                                        <div class="item_meic">
                                            <span class="label_meic"><em>*</em> 昵称：</span>
                                            <div class="fl_e">
                                                <input type="text" class="f_input" maxlength="20" id="nickName" name="like_name" value="${sessionScope.user.like_name}">
                                            </div>
                                        </div>
                                        <div class="item_meic">
                                            <span class="label_meic">性别：</span>
                                            <div class="fl_e">
                                                <input type="radio" name="sex" class="jdradio" value="0" id="man">
                                                <label class="mr10">男</label>
                                                <input type="radio" name="sex" class="jdradio" value="1" id="wonan">
                                                <label class="mr10">女</label>
                                            </div>
                                        </div>
                                        <div class="item_meic">
                                            <span class="label_meic">年龄：</span>
                                            <input type="text" class="f_input" value="${sessionScope.user.age}" name="age">
                                        </div>
                                        <div class="item_meic">
                                            <span class="label_meic"> </span>
                                            <div class="fl_e">
                                                <input type="hidden" name="user_id" value="${sessionScope.user.user_id}">
                                                <input type="hidden" name="type" value="updateXXi">
                                                <input type="submit" value="保存" class="savebtn" id="but">
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
    </div>
</div>
</div>
<div class="tang" style="display:none;"></div>
<!--底部-->
<%@include file="bottom.jsp"%>
<script>
    $(function (){
        //获取从数据库sex值
        let sex = ${sessionScope.user.sex};
        let error = '${error}';
        if (error){
            // alert(error);
            $(".tang").text(error).show().delay(1000).hide(0);
        }

        if(sex==0){
            //如果sex=0则性别为男的radio被选中
            $("input:radio[value='0']").prop('checked', true);
        }else {
            //如果sex=1则性别为女的radio被选中
            $("input:radio[value='1']").prop('checked', true);
        }
    })
</script>
</body>
</html>
