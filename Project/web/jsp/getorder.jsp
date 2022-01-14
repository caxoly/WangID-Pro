
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script src="/Project/js/jquery-1.11.3.min.js" ></script>
</head>
<body>
<!--头部-->
<%@ include file="onetop.jsp"%>
<!--提交订单——结算页-->
<div class="beij_center">
    <div class="dengl_logo">
        <div>
            <a href="/Project/index.jsp"><img src="/Project/images/logo_1.png"></a>
            <h1>结算页</h1>
        </div>
        <div class="stepflex stepflex_2">
            <dl class="normal done ">
                <dt class="s-num">1</dt>
                <dd class="s-text">1.我的购物车<s></s><b></b></dd>
            </dl>
            <dl class="normal doing">
                <dt class="s-num">2</dt>
                <dd class="s-text">2.填写核对订单信息<s></s><b></b></dd>
            </dl>
            <dl class="normal ">
                <dt class="s-num">3</dt>
                <dd class="s-text">3.成功提交订单<s></s><b></b></dd>
            </dl>
        </div>
    </div>
</div>
<form method="post" action="/Project/order">
    <div class="beij_center">
        <div class="checkout-tit">
            <span class="tit-txt">填写并核对订单信息</span>
        </div>
        <div class="checkout_steps">
            <div class="step-tit">
                <h3>收货人信息</h3>
            </div>
            <div class="jies_y_shouh_diz shiq_1">
                <input type="hidden" name="shop_id" value=""/>
                <ul id="ressBox">

                </ul>
                <div class="addr-switch cur_e">
                    <p><span>更多地址</span><b></b></p>
                    <p class="jiant_xiangs"><span>收起更多</span><b></b></p>
                </div>
            </div>
            <div class="jies_y_shouh_diz shiq_2">
                <input type="hidden" name="pay_way" value="0"/>
                <ul>
                    <li class="zhif_fangs cur" data-pay="0">
                        <div class="dangq_xuanz_diz pay">在线支付</div>
                    </li>
                    <li class="zhif_fangs" data-pay="1">
                        <div class="dangq_xuanz_diz pay">货到付款</div>
                    </li>
                    <div class="addr-switch addr_switch_1 cur_e_1">
                        <p><span>更多 >></span></p>
                        <p><span>收起 <<</span></p>
                    </div>
                </ul>
            </div>
            <div class="step-tit">
                <h3>送货清单</h3>
            </div>
            <div class="shopping_list">
                <div class="dis_modes">
                    <div class="mode_item_tit">
                        <h4>配送方式</h4>
                    </div>
                    <div>
                        <div class="jies_y_shouh_diz jies_y_shouh_diz_kuaid">
                            <input type="hidden" name="give_way" value="申通快递"/>
                            <ul>
                                <li class="zhif_fangs cur">
                                    <div class="dangq_xuanz_diz give">申通快递</div>
                                </li>
                                <li class="zhif_fangs">
                                    <div class="dangq_xuanz_diz give">圆通快递</div>
                                </li>
                                <li class="zhif_fangs">
                                    <div class="dangq_xuanz_diz give">百世快递</div>
                                </li>
                                <li class="zhif_fangs">
                                    <div class="dangq_xuanz_diz give">中通快递</div>
                                </li>
                                <li class="zhif_fangs">
                                    <div class="dangq_xuanz_diz give">邮政快递</div>
                                </li>
                                <li class="zhif_fangs">
                                    <div class="dangq_xuanz_diz give">韵达快递</div>
                                </li>
                                <li class="zhif_fangs">
                                    <div class="dangq_xuanz_diz give">京东快递</div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="goods_list">
                    <c:forEach items="${requestScope.list}" var="list">
                        <input type="hidden" name="cart_id" value="${list.cart_id}"/>
                        <div class="goods_list_neik">
                            <div class="goods_item">
                                <div class="p_img"><a href="#"><img width="100px" height="76px" src="/Project/images/head/${list.url}"></a></div>
                                <div class="goods_msg">
                                    <div class="p_name">
                                        <a href="#">${list.goods_name}</a>
                                    </div>
                                    <div class="p_price">
                                        <span class="jq_price">￥ ${list.price}</span>
                                        <span>x${list.num}</span>
                                        <span>有货</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
        <div class="trade_foot_detail_com">
            <div class="dsgs">
                <div class="qianq_mx">
                    <div class="jiaq_meih">
                        <span class="xiangq_leib"> 应付总额：</span>
                        <em class="goum_zongj zhongf_jine">￥<span id="sum">${param.sumMoney}</span></em>
                    </div>
                </div>
            </div>
            <div class="zuiz_diz">
                <span id="address"></span>
                <span id="name"></span>
            </div>
        </div>
        <div class="tij_dingd_ann">
            <a href="javascript:void(0);" id="submit">提交订单</a>
            <input type="submit" name="type" value="createOrder" style="display: none"/>
        </div>
    </div>
</form>
<%@ include file="bottom.jsp"%>
<script src="/Project/js/getorder.js"></script>
<script type="text/javascript">
    //获取用户的id
    let user_id = "${sessionScope.user.user_id}";
    // alert(user_id)
    //订单金额
    let sum_money = parseFloat($("#sum").text());
    // alert(sum_money)
    findAddress(user_id);
    //显示收起地址
    $(".cur_e p").hide().first().show()
    $(".cur_e p:first").click(
        function(){
            $(".shiq_1 ul li").show()
            $(".cur_e p").hide().last().show()
        }
    )
    $(".cur_e p:last").click(
        function(){
            $(".shiq_1 ul li").hide().first().show()
            $(".cur_e p").hide().first().show()
        }
    )
    //支付方式
    $(".shiq_2 ul li").hide().first().show()
    $(".cur_e_1 p").hide().first().show()
    $(".cur_e_1 p:first").click(
        function(){
            $(".shiq_2 ul li").show()
            $(".cur_e_1 p").hide().last().show()
        }
    )
    $(".cur_e_1 p:last").click(
        function(){
            $(".shiq_2 ul li").hide().first().show()
            $(".cur_e_1 p").hide().first().show()
        }
    )
</script>

</body>
</html>
