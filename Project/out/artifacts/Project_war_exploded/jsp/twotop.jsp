
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>二层</title>
</head>
<body>
<!--侧边-->
<div class="jdm-toolbar-wrap J-wrap">
    <div class="jdm-toolbar J-toolbar">
        <div class="jdm-toolbar-panels J-panel"></div>
        <div class="jdm-toolbar-tabs J-tab">
            <div data-type="bar" class="J-trigger jdm-toolbar-tab jdm-tbar-tab-ger">
                <i class="tab-ico"></i>
                <a class="tab-text" href="/Project/jsp/usercenter.jsp">个人中心</a>
            </div>
            <div data-type="bar" class="J-trigger jdm-toolbar-tab jdm-tbar-tab-cart">
                <i class="tab-ico"></i>
                <a class="tab-text" href="/Project/goods?type=getCart">购物车</a>
            </div>
            <div data-type="bar" clstag="h|keycount|cebianlan_h_follow|btn" class="J-trigger jdm-toolbar-tab jdm-tbar-tab-follow" data-name="follow" data-login="true">
                <i class="tab-ico"></i>
                <a class="tab-text" href="/Project/jsp/love.jsp">我的收藏</a>
            </div>
        </div>
        <div class="J-trigger jdm-toolbar-tab jdm-tbar-tab-message" data-name="message"><a target="_blank" href="#">
            <i class="tab-ico"></i>
            <em class="tab-text">我的消息
            </em></a>
        </div>
    </div>
    <div class="jdm-toolbar-footer">
    </div>
    <div class="jdm-toolbar-mini"></div>
    <div id="J-toolbar-load-hook" clstag="h|keycount|cebianlan_h|load"></div>
</div>
<!--搜索栏-->
<div class="toub_beij">
    <div class="logo"><a href="/Project/index.jsp"><img src="/Project/images/logo.png"></a></div>
    <form action="/Project/jsp/classify.jsp" method="get" >
        <div class="search" id="keyform">
            <input type="text" placeholder="家电一折抢" class="text" name="key">
            <input type="hidden"  class="text" name="level" value="1">
            <input type="submit" class="button" value="搜索" id="keyButton">
        </div>
    </form>
    <div class="right">
        <i class="gw-left"></i>
        <div class="sc">
            <i class="gw-count" id="cartNum"></i>
            <i class="sd"></i>
        </div>
        <a href="/Project/goods?type=getCart">我的购物车</a>
    </div>
    <div class="hotwords">
        <a class="biaoti">热门搜索：</a>
        <a href="/Project/jsp/classify.jsp?key=deli&level=1" class="rem">deli</a>
        <a href="/Project/jsp/classify.jsp?key=炊大皇&level=1" class="rem">炊大皇</a>
        <a href="/Project/jsp/classify.jsp?key=芝华仕&level=1" class="rem">芝华仕</a>
        <a href="/Project/jsp/classify.jsp?key=HUAWEI&level=1" class="rem">HUAWEI</a>
        <a href="/Project/jsp/classify.jsp?key=贵妃&level=1" class="rem">贵妃</a>
        <a href="/Project/jsp/classify.jsp?key=馨梦园&level=1" class="rem">馨梦园</a>
        <a href="/Project/jsp/classify.jsp?key=世达&level=1" class="rem">世达</a>
    </div>

</div>
</body>
<script>
    $(function () {
        $(".rem").click(function () {
            //点击热门搜索等同于点击搜索，效果一致
            $("#keyButton").trigger('click');
        })

        $.post("/Project/user",{"type":"findCartNum"},function (data) {
            // console.log(data)
            $("#cartNum").text(data.info);
        },"json")
    })
</script>
</html>
