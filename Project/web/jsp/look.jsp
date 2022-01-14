<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2021/1/4
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>最近浏览</title>
</head>
<body>
<%@ include file="onetop.jsp"%>
<%@ include file="fourtop.jsp"%>
<div class="wod_tongc_zhongx">
    <div class="beij_center">
        <div class="myGomeWeb">
            <!--侧边导航-->
            <%@ include file="threetop.jsp"%>
            <!--左边内容-->
            <div class="mod_main">
                <div class="jib_xinx_kuang">
                    <div class="shand_piaot">用户浏览足迹</div>
                    <div class="slideTxtBox_shouc">
                        <div class="bd">
                            <ul>
                                <div class="uc_overdueTable">
                                    <form method="post" action="/Project/goods">
                                        <div class="diyig_s">
                                            <ol id="showLook">

                                            </ol>
                                        </div>
                                    </form>
                                </div>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--左边内容结束-->
        </div>
    </div>
</div>
<div class="tang" style="display:none;"></div>
<%@include file="bottom.jsp"%>
<script src="/Project/js/look.js"></script>
<script>
    //如果存在error，则弹出错误提示
    let error = '${sessionScope.error}';
    if(error){
        // alert(error);
        $(".tang").text(error).show().delay(1000).hide(0);
    }
</script>
</body>
</html>
