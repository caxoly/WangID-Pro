
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品详情</title>
    <link rel="stylesheet" type="text/css" href="/Project/houl/jquery.fancybox-1.3.4.css">
    <link rel="stylesheet" type="text/css" href="/Project/houl/style.css">
    <script type="text/javascript" src="/Project/js/jquery-1.12.4.js"></script>
</head>
<body>
<%@include file="onetop.jsp"%>
<%@include file="twotop.jsp"%>
<!--轮播图上方导航栏  一栏-->
<div id="navv" class="navv_ziy">
    <div class="focus">
        <div class="focus-a">
            <div class="fouc-font fouc_font_ziy">
                <a href="#">全部商品分类</a>
            </div>
        </div>
        <div class="focus-b">
            <ul>
                <li><a href="/Project/index.jsp#bangong">办公家具</a></li>
                <li><a href="/Project/index.jsp#shouji">手机数码</a></li>
                <li><a href="/Project/index.jsp#fushi">服饰鞋帽</a></li>
                <li><a href="/Project/index.jsp#qiche">汽车用品</a></li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    (function(){
        var $subblock = $(".subpage"), $head=$subblock.find('h2'), $ul = $("#proinfo"), $lis = $ul.find("li"), inter=false;
        $head.mouseover(function(e){
            e.stopPropagation();
            if(!inter){
                $ul.show();
            }else{
                $ul.hide();
            }
            inter=!inter;
        });

        $ul.mouseover(function(event){
            event.stopPropagation();
        });

        $(document).mouseover(function(){
            $ul.hide();
            inter=!inter;
        });
    })();
</script>
<!--商品详情-->
<form action="/Project/goods" method="post">
    <div class="gome_container">
        <!--左-->
        <div class="prd_firstscreen_left">
            <div id="magnifier">
                <div class="small-box">
                    <img width="350px" height="350px" src="/Project/images/head/${requestScope.goodsinfo.urls[0]}" >
                    <span class="hover"></span>
                </div>
                <div class="thumbnail-box">
                    <a href="javascript:void(0);" class="btn btn-prev"></a>
                    <a href="javascript:void(0);" class="btn btn-next"></a>
                    <div class="list">
                        <ul class="wrapper" >
                            <c:forEach items="${requestScope.goodsinfo.urls}" var="item">
                                <li class="item" data-src="/Project/images/head/${item}"><img width="54px" src="/Project/images/head/${item}"></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="big-box">
                    <img width="800px" height="800px" src="/Project/images/head/${requestScope.goodsinfo.urls[0]}" alt="#">
                </div>
            </div>

            <script src="/Project/js/magnifier.js"></script>
            <script>
                $(function () {
                    $('#magnifier').magnifier();
                });
            </script>
            <div class="preview-info">
            </div>
        </div>
        <!--中-->
        <div class="prd_firstscreen_center">
            <div class="hgroup">
                <h1>${requestScope.goodsinfo.goods_name}</h1>
            </div>
            <div class="prd_price_1">
                <div class="unitprice">
                    <p>
                        <label class="prd_price_left">价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label>
                        <span class="price xiangq_yuanj" style="color: red;font-weight:bolder;font-size: 24px"><em>¥</em>${requestScope.goodsinfo.price}</span>
                    </p>
                </div>
                <div class="prd_price_flr">
                    <div class="prd_price_line">|</div>
                    <div class="prd_price_lineright">
                        <p>好评度<em id="haocnt">98%</em></p>
                    </div>
                </div>
            </div>
            <div class="prd_properties_1 hou_jia">
                <label class="prd_price_left">货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源</label>
                <span class="prd_span">库&nbsp;存&nbsp;仅&nbsp;剩&nbsp;${requestScope.goodsinfo.store_num}&nbsp;件</span>
            </div>
            <style type="text/css">
                .hou_jia{
                    padding:0 10px;
                }
            </style>
            <div class="prd_buttons">
                <input type="hidden" name="url" value="${requestScope.goodsinfo.urls[0]}">
                <input type="hidden" name="goods_name" value="${requestScope.goodsinfo.goods_name}">
                <input type="hidden" name="price" value="${requestScope.goodsinfo.price}">
                <input type="hidden" name="goods_id" value="${requestScope.goodsinfo.goods_id}">
                <a href="javascript:void (0);" class="btn-product btn-addcart" id="buyCart">立即购买</a>
                <input type="submit" name="type" value="buyCart" style="display:none;">
                <a href="javascript:void (0);" class="btn-product" id="addCart" data-id="${requestScope.goodsinfo.goods_id}">加入购物车</a>
            </div>
            <div class="prd-tips wenxintishi_wrap clearfix">
                <p>温馨提示</p>
                <ol class="wenxinti">
                    <li>
                        正品保障；
                        支持7天无理由退货
                    </li>
                </ol>
            </div>
            <div class="prd-tips">
                <ol class="shouc">
                    <li>
                        收藏商品（商品人气1500）
                    </li>
                </ol>
            </div>
        </div>
    </div>
</form>
<!--商品信息结束-->
<!--详情-->
<div class="shangp_xiangq_neir_beij">
    <!--right-->
    <div class="shangp_xiangq_right">
        <div class="slideTxtBox_1">
            <div class="hd">
                <ul class="hd_ul">
                    <li>
                        <h2>猜你喜欢</h2>
                    </li>
                    <li>
                        <h2 class="xiang">商品详情介绍</h2>
                    </li>
                </ul>
            </div>
            <!--left-->
            <div class="shangp_xiangq_left">
                <!--猜你喜欢-->
                <div class="stores-infos">
                    <div class="ly-stores">
                        <ul class="bend_shangp_lieb" id="max" data-id="${requestScope.goodsinfo.key}">

                        </ul>
                    </div>
                </div>
            </div>
            <div class="bd">${requestScope.goodsinfo.remake}</div>
        </div>
    </div>
</div>
<div class="tang" style="display:none;"></div>
<%@include file="bottom.jsp"%>
<script src="/Project/js/goodsinfo.js"></script>
<script>
    let info = '${requestScope.info}';
    if (info){
        // alert(info);
        $(".tang").text(info).show().delay(1000).hide(0);
    }
    // 获取地址栏中的商品id
    let goods_id = ${param.goods_id};
    $(function () {
        //加入购物车
        $("#addCart").click(function () {
            let goods_id = $(this).data("id");
            //判断用户是否登录
            $.post("/Project/goods",{"type":"isLogin"},function (data) {
                // console.log(data);
                if (data.code==400){
                    //如果用户没有登录 跳转到登录页面 登录成功再返回此页面
                    <%
                        String goods_id = request.getParameter("goods_id");
                        request.getSession().setAttribute("path","/goods?type=getGoodsById&goods_id="+goods_id);
                    %>
                    window.location.href="/Project/jsp/login.jsp";
                } else {
                    //添加购物车
                    $.post("/Project/goods", {"type":"addCart","goods_id":goods_id},function (info) {
                        // console.log(info)
                        if (info.code==0){
                            //添加到购物车失败
                            $(".tang").text("添加到购物车失败").show().delay(1000).hide(0);
                        }else if (info.code==1){
                            $(".tang").text("添加数量成功").show().delay(1000).hide(0);
                        }else if (info.code==2) {
                            //添加到购物车成功
                            $(".tang").text("添加到购物车成功").show().delay(1000).hide(0);
                            $("#cartNum").text(parseInt($("#cartNum").text())+1)
                        }
                    },"json")
                }
            },"json")
        })

        //立即购买
        $("#buyCart").click(function () {
            let _this = this;
            //判断用户是否登录
            $.post("/Project/goods",{"type":"isLogin"},function (data) {
                // console.log(data);
                if (data.code==400){
                    //如果用户没有登录 跳转到登录页面 登录成功再返回此页面
                    <%
                        goods_id = request.getParameter("goods_id");
                        request.getSession().setAttribute("path","/goods?type=getGoodsById&goods_id="+goods_id);
                    %>
                    window.location.href="/Project/jsp/login.jsp";
                } else {
                    //表单提交立即购买
                    $(_this).next().click();
                }
            },"json")
        })

    })
</script>
</body>
</html>

