<%@ page import="service.ClassifyService" %>
<%@ page import="po.GoodsType" %>
<%@ page import="java.util.List" %>
<%@ page import="po.AllType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>WangID通城</title>
  <script src="/Project/js/jquery-1.11.3.min.js" ></script>
  <script type="text/javascript" src="/Project/js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="/Project/js/jquery.SuperSlide.2.1.1.source.js"></script>
</head>
<body>
<%@ include file="jsp/onetop.jsp"%>
<%@ include file="jsp/twotop.jsp"%>

<div id="navv">
  <div class="focus">
    <div class="focus-a">
      <div class="fouc-font"><a href="">全部商品分类</a> </div>
    </div>
    <div class="focus-b">
      <ul>
        <li><a href="/Project/index.jsp">商城首页</a></li>
        <li><a href="#bangong">办公家具</a></li>
        <li><a href="#shouji">手机数码</a></li>
        <li><a href="#fushi">服饰鞋帽</a></li>
        <li><a href="#qiche">汽车用品</a></li>
      </ul>
    </div>
    <!--左边导航-->
    <%
      request.setCharacterEncoding("UTF-8");
      ClassifyService service = new ClassifyService();
      List<GoodsType> list = service.findTypeName();
      pageContext.setAttribute("list",list);
    %>
    <!--左边导航-->
    <!--全部分类查询-->
    <div class="dd-inner">
      <c:forEach items="${pageScope.list}" var="onk">
        <div class="font-item">
          <div class="item fore1" id="box">
            <h3>
              <a class="da_zhu" href="/Project/jsp/classify.jsp?fid=${onk.type_id}&level=1">${onk.type_name}</a>
              <p>
                <c:forEach items="${onk.list}" var="item">
                  <a href="/Project/jsp/classify.jsp?type_id=${item.type_id}&type_name=${item.type_name}&name=${onk.type_name}">${item.type_name}</a>
                </c:forEach>
              </p>
            </h3>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<!--轮播图-->
<div id="lunbo">
  <ul id="one">
    <li class="banner"><a href="/Project/jsp/classify.jsp?&level=1"><img src="./images/banner.jpg"></a></li>
    <li><a href="/Project/jsp/classify.jsp?&level=1"><img src="./images/banner1.jpg"></a></li>
    <li><a href="/Project/jsp/classify.jsp?&level=1"><img src="./images/banner.jpg"></a></li>
    <li><a href="/Project/jsp/classify.jsp?&level=1"><img src="./images/banner1.jpg"></a></li>
  </ul>

  <ul id="two">
    <li class="on">1</li>
    <li>2</li>
    <li>3</li>
    <li>4</li>
  </ul>
  <!--轮播图左右箭头-->
  <div class="slider-page">
    <a href="javascript:void(0)" id="left"><</a>
    <a href="javascript:void(0)" id="right">></a>
  </div>
</div>

<!--层次-->
<div class="chengc_jvz">
  <div class="slideTxtBox3">
    <div class="hd">
      <h1>
        <a class="a" name="fushi">服饰鞋帽</a>
        <p class="yingw_">Shoes and hats</p>
      </h1>
      <ul><li id="FS" data-id="服饰鞋帽">精品热卖</li></ul>
    </div>
    <div class="bd">
      <ul>
        <div class="louc_tup_qieh">
          <div class="js-silder ws-slider">
            <div class="silder-scroll">
              <div class="silder-main" id="mianFs">

              </div>
            </div>
            <div class="js-silder-ctrl">
              <span class="silder-ctrl-prev"></span>
              <span class="silder-ctrl-next"></span>
            </div>
          </div>
        </div>
        <div class="you_lirb you_lirb_3">
          <div class="shang_buf" id="goodsFu">

          </div>
        </div>
      </ul>
    </div>
  </div>
</div>
<div class="chengc_jvz">
  <div class="slideTxtBox">
    <div class="hd">
      <h1>
        <a class="a" name="bangong">办公家具</a>
        <p class="yingw_">Office furniture</p>
      </h1>
      <ul><li id="CX" data-id="办公家具">精品热卖</li></ul>
    </div>
    <div class="bd">
      <ul>
        <div class="louc_tup_qieh">
          <div class="js-silder ws-slider">
            <div class="silder-scroll">
              <div class="silder-main" id="maincx">

              </div>
            </div>
            <div class="js-silder-ctrl">
              <span class="silder-ctrl-prev"></span>
              <span class="silder-ctrl-next"></span>
            </div>
          </div>
        </div>
        <div class="you_lirb">
          <div class="shang_buf" id="officeFurniture">

          </div>
        </div>
      </ul>
    </div>
  </div>
</div>
<div class="chengc_jvz">
  <div class="slideTxtBox2">
    <div class="hd">
      <h1>
        <a class="a" name="shouji">手机数码</a>
        <p class="yingw_">Mobile phone digital</p>
      </h1>
      <ul><li id="FT" data-id="电脑数码">精品热卖</li></ul>
    </div>
    <div class="bd">
      <ul>
        <div class="louc_tup_qieh">
          <div class="js-silder ws-slider">
            <div class="silder-scroll">
              <div class="silder-main" id="windUlr">

              </div>
            </div>
            <div class="js-silder-ctrl">
              <span class="silder-ctrl-prev"></span>
              <span class="silder-ctrl-next"></span>
            </div>
          </div>
        </div>
        <div class="you_lirb you_lirb_2">
          <div class="shang_buf" id="offFur">

          </div>
        </div>
      </ul>
    </div>
  </div>
</div>
<div class="chengc_jvz">
  <div class="slideTxtBox4">
    <div class="hd">
      <h1>
        <a class="a" name="qiche">汽车用品</a>
        <p class="yingw_">Automobile</p>
      </h1>
      <ul><li id="Car" data-id="汽车用品">精品热卖</li></ul>
    </div>

    <div class="bd">
      <ul>
        <div class="louc_tup_qieh">
          <div class="js-silder ws-slider">
            <div class="silder-scroll">
              <div class="silder-main" id="mainCar">

              </div>
            </div>
            <div class="js-silder-ctrl">
              <span class="silder-ctrl-prev"></span>
              <span class="silder-ctrl-next"></span>
            </div>
          </div>
        </div>
        <div class="you_lirb you_lirb_4">
          <div class="shang_buf" id="goodsCar">

          </div>
        </div>
      </ul>
    </div>
  </div>
</div>

<script type="text/javascript">jQuery(".slideTxtBox").slide();</script>
<script type="text/javascript">jQuery(".slideTxtBox2").slide();</script>
<script type="text/javascript">jQuery(".slideTxtBox3").slide();</script>
<script type="text/javascript">jQuery(".slideTxtBox4").slide();</script>


<div class="guangg_tu">

</div>

<script type="text/javascript">
  jQuery(".picScroll_left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:2 ,trigger:"click"});
</script>

<%@include file="jsp/bottom.jsp"%>
<script src="/Project/js/index.js" ></script>
<script>
  let user = '${sessionScope.user}';
  // console.log(user);
</script>
</body>
</html>

