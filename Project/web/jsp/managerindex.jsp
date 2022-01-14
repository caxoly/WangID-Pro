<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="managercustom.jsp"%>
<%--            变动部分--%>
            <div class="xh-text" id="index">
                <div class="xh-iframe">
                    <div class="one">&nbsp&nbsp&nbsp&nbsp状 态 栏</div>
                    <div class="two">
                        <ul class="two_ul">
                            <li>
                                <span class="two_p">商城名称：</span>
                                <span class="two_span">通城购物网</span>
                            </li>
                            <li>
                                <span class="two_p">店铺等级：</span>
                                <span class="two_span">三级</span>
                            </li>
                            <li>
                                <span class="two_p">商城类型：</span>
                                <span class="two_span">电子商务</span>
                            </li>
                            <li>
                                <span class="two_p">销售类型：</span>
                                <span class="two_span">综合</span>
                            </li>
                            <li>
                                <span class="two_p">开通时间：</span>
                                <span class="two_span">2020-12-12</span>
                            </li>
                        </ul>
                        <input class="two_input" type="button" value="进入商城首页">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        //点击调回商城的首页
        $(".two_input").click(function () {
            window.location.href="/Project/index.jsp";
        })

    })

</script>
</body>
</html>
