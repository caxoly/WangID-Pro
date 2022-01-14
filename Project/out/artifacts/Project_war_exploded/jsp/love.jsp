
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收藏</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script type="text/javascript" src="/Project/js/jquery-1.11.1.min.js"></script>
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
                    <div class="shand_piaot">我的收藏</div>
                    <div class="slideTxtBox_shouc">
                        <div class="bd">
                            <ul>
                                <div class="uc_overdueTable">
                                    <div class="list_thead">
                                        <div class="col432">
                                            <label class="label"><input class="checkbox" type="checkbox" id="allCheck">全选</label>
                                        </div>
                                    </div>

                                    <form method="post" action="/Project/goods">
                                        <div class="diyig_s">
                                            <ol id="showLove">

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
<script src="/Project/js/love.js"></script>
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
