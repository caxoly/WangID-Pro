
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>待付款</title>
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
                    <div class="shand_piaot">我的订单</div>
                    <table id="showOrder" class="order-tb order-tb_1">
                        <thead>
                        <tr>
                            <th>订单详情</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>收货人</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="order_top" style="display: none">
    <div class="top_one">
        <p class="top_p">昵称：<span class="top_span">${sessionScope.user.like_name}</span></p>
        <p class="top_p">请输入支付密码：<input class="top_input" type="password" name="payPwd" value=""></p>
        <input class="to" id="submit" type="submit" value="确认支付">
    </div>
</div>
<div class="tang" style="display:none;"></div>
<%@ include file="bottom.jsp"%>
<script src="/Project/js/myorder.js"></script>
<script>
    //用户id
    let user_id = "${sessionScope.user.user_id}";
    // console.log(user_id);
    //用户余额
    let user_money = parseFloat(${sessionScope.user.user_money});
    // alert(user_money);
    let pwd = ${sessionScope.user.pwd};

    //显示用户所有的订单
    function findOrder(user_id) {
        $.post("/Project/order",{"type":"showOneOrder","user_id":user_id,"state":"0"},function (data) {
            // console.log(data);
            let html = "";
            data.info.forEach(function (item) {
                html += "<tbody><tr class=\"sep-row\"><td colspan=\"4\"></td></tr><tr class=\"tr-th\"><td colspan=\"5\">";
                html += "<span class=\"gap\"></span><span class=\"dealtime span_30\" title=\"2015-1-19 10:30:42\">"+item.create_time+"</span>";
                html += "<span class=\"number span_30\">订单号：<a target=\"_blank\" name=\"id\">"+item.order_id+"</a></span>";
                html += "<span class=\"wod_sc_delete_beij span_30\"><a class=\"wod_dingd_delete\"></a></span></td></tr>";
                html += "<tr class=\"tr-bd\"><td rowspan=\"1\"><div class=\"goods-item\"><div class=\"p-img\"><a target=\"_blank\">";
                html += "<img src=\"/Project/images/head/"+item.goods_url+"\"></a></div><div class=\"p-msg\"><div class=\"p-name\">";
                html += "<a target=\"_blank\">"+item.goods_name+"</a></div></div></div>";
                html += "<div class=\"goods-number\">x"+item.goods_num+"</div></td><td rowspan=\"1\"><div class=\"zhif_jine\">";
                html += "<p>总额￥<span name=\"sum\">"+item.sum_money+"</span></p><span class=\"pay\">"+showPayWay(item.pay_way)+"</span></div></td><td rowspan=\"1\"><div class=\"operate\">";
                html += "<p class=\"yiwanc_hui state\">"+showState(item.state)+"</p></div></td><td rowspan=\"1\"><div class=\"txt_ren\">";
                html += "<span>"+item.shop_name+"</span><p class=\"ren_tub\"></p></div></td><td rowspan=\"1\"><div class=\"operate\">";
                html += "<a target=\"_blank\" class=\"btn-def isBuy\">"+showOperate(item.state)+"</a></div></td></tr></tbody>";

            })
            $("#showOrder").append(html);
            showPayWay();
        },"json")
    }


</script>

</body>
</html>
