<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="managercustom.jsp"%>
<%--            变动部分--%>
            <div class="xh-text">
                <div class="xh-iframe">
                    <div class="one">&nbsp&nbsp&nbsp&nbsp修 改 商 品</div>
                    <div class="two3">
                        <script src="/Project/js/ajaxfileupload.js"></script>
                        <div class="top">
                            <div class="top_center">
                                <form method="post" action="/Project/user" onsubmit="return check()">
                                    <ul id="ul" class="top_style">
                                        <li>
                                            <span class="url">商品图片:</span>
                                            <div class="url_img">
                                                <c:forEach items="${requestScope.goods.url}" var="item">
                                                    <img class="showImg" id="showImg" width="100px" height="100px" src="/Project/images/head/${item}">
                                                </c:forEach>
                                                <input type="file" id="fileImg" multiple="multiple" name="fileImg" style="display: none"/>
                                            </div>
                                        </li>

                                        <li>
                                            <span>商品名称:</span>
                                            <input type="text" name="goods_name" value="${requestScope.goods.goods_name}">
                                        </li>
                                        <li>
                                            <span>商品价格:</span>
                                            <input type="text" name="price" value="${requestScope.goods.price}">
                                        </li>
                                        <li>
                                            <span>商品销量:</span>
                                            <input type="text" name="sell_num" value="${requestScope.goods.sell_num}">
                                        </li>
                                        <li>
                                            <span>商品库存:</span>
                                            <input type="text" name="store_num" value="${requestScope.goods.store_num}">
                                        </li>
                                        <li>
                                            <span>是否推荐到首页:</span>
                                            <input type="text" name="index" value="${requestScope.goods.index}">
                                        </li>
                                        <li>
                                            <span>商品关键字:</span>
                                            <input type="text" name="key" value="${requestScope.goods.key}">
                                        </li>
                                        <li id="goodstype">
                                            <span>商品类型:</span>
                                            <input type="hidden" name="type_id" value="${requestScope.goods.type_id}">
                                            ${requestScope.goods.type_name}
                                        </li>

                                        <li class="baoliu">
                                            <span>商品详情:</span>
                                            <div id="editDiv" style="width: 650px;float: right" ></div>
                                            <input type="hidden" name="remake" value="">
                                        </li>
                                        <li  class="baoliu" style="clear: both">
                                            <input type="hidden" name="type" value="updateGoods">
                                            <input type="hidden" name="goods_id" value="${requestScope.goods.goods_id}">
                                            <input class="no" id="dot" type="submit" value="提交修改">
                                        </li>
                                    </ul>
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
<script src="/Project/js/wangEditor.js"></script>
<script src="/Project/js/editGoods.js"></script>
</body>
</html>
