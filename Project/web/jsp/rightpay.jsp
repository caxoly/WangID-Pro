
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>立即购买</title>
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
                <div class="extra_r">
                    <a href="/Project/jsp/address.jsp" class="ftx-05 J_consignee_global">新增收货地址</a>
                </div>
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
                    <input type="hidden" name="goods_id" value="${requestScope.goods.goods_id}"/>
                    <div class="goods_list_neik">
                        <div class="goods_item">
                            <div class="p_img"><a href="#"><img width="100px" height="76px" src="/Project/images/head/${requestScope.goods.url}"></a></div>
                            <div class="goods_msg">
                                <div class="p_name">
                                    <a href="#">${requestScope.goods.goods_name}</a>
                                </div>
                                <div class="p_price">
                                    <span class="jq_price">￥ ${requestScope.goods.price}</span>
                                    <span>x1</span>
                                    <span>有货</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="trade_foot_detail_com">
            <div class="dsgs">
                <div class="qianq_mx">
                    <div class="jiaq_meih">
                        <span class="xiangq_leib"> 应付总额：</span>
                        <em class="goum_zongj zhongf_jine">￥<span id="sum">${requestScope.goods.price}</span></em>
                        <input type="hidden" name="sum" value="${requestScope.goods.price}">
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
            <input type="submit" name="type" value="rightBuy" style="display: none"/>
        </div>
    </div>
</form>
<%@ include file="bottom.jsp"%>

<script type="text/javascript">
    $(function () {
        //获取用户的id
        let user_id = "${sessionScope.user.user_id}";
        // alert(user_id)

        //订单金额
        let sum_money = parseFloat($("#sum").text());
        // alert(sum_money)

        $("#submit").click(function () {
            let isBuy = confirm("确认支付吗？");
            if (isBuy){
                //调用表单提交事件
                $(this).next().click();
            }
        })

        //ajax异步用动态绑定事件 选取收货人信息
        $(document).on("click",".now_add",function () {
            //给相应点击节点添加样式  去除兄弟节点的样式
            $(this).parent().addClass("cur").siblings().removeClass("cur");
            // alert($(this).parent().filter(".cur").data("id"))

            address();
        })


        findAddress(user_id);

        //选择支付方式
        $(".pay").click(function () {
            //给相应点击节点添加样式  去除兄弟节点的样式
            $(this).parent().addClass("cur").siblings().removeClass("cur");
            // alert($(this).parent().filter(".cur").data("pay"))
            $("[name='pay_way']").val($(this).parent().filter(".cur").data("pay"));
        })

        //选择收货渠道
        $(".give").click(function () {
            //给相应点击节点添加样式  去除兄弟节点的样式
            $(this).parent().addClass("cur").siblings().removeClass("cur");
            // alert($(this).text())
            $("[name='give_way']").val($(this).text());
        })

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

    })

    //显示我所有的收货地址
    function findAddress(user_id) {
        $.post("/Project/order",{"type":"showAddress","user_id":user_id},function (data) {
            // console.log(data);
            let html = "";
            data.info.forEach(function (item) {
                html += "<li data-id=\""+item.shop_id+"\"><div class=\"dangq_xuanz_diz now_add\">当前地址</div>";
                html += "<span>"+item.shop_name+"</span>";
                html += "<span>"+(item.province+"\t"+item.city+"\t"+item.district+"\t"+item.address)+"</span>";
                html += "<span>"+item.shop_phone+"</span></li>";
            })
            $("#ressBox").empty().append(html);
            $("#ressBox li").eq(0).addClass("cur");
            $(".shiq_1 ul li").hide().first().show();
            address();
        },"json")
    }

    //显示收货地址
    function address() {
        let address = $("#ressBox>li").filter(".cur").children().eq(2).text();
        $("#address").text("寄送至："+address);
        let name = $("#ressBox>li").filter(".cur").children().eq(1).text();
        let phone = $("#ressBox>li").filter(".cur").children().eq(3).text();
        $("#name").text("收货人："+name+"\t"+phone);
        //默认shop_id为第一个地址
        $("[name='shop_id']").val($("#ressBox>li").filter(".cur").data("id"));
    }

</script>
</body>
</html>
