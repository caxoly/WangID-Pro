<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="managercustom.jsp"%>
<%--            变动部分--%>
            <div class="xh-text">
                <div class="xh-iframe">
                    <div class="one">&nbsp&nbsp&nbsp&nbsp添 加 商 品</div>
                    <div class="two3">
                        <script src="/Project/js/ajaxfileupload.js"></script>
                        <div class="top">
                            <div class="top_center">
                                <form method="post" action="/Project/user" onsubmit="return check()">
                                    <ul id="ul" class="top_style">
                                        <li>
                                            <span class="url">商品图片:</span>
                                            <div class="url_img">
                                                <img class="showImg" id="showImg" width="100px" height="100px" src="/Project/images/big_1.jpg">
                                                <%--                        <input type="hidden" name="url" value="">--%>
                                                <%--                        一次性上传多个文件  <input type="file" name="file" multiple="multiple"/>  --%>
                                                <input type="file" id="fileImg" multiple="multiple" name="fileImg" style="display: none"/>
                                            </div>
                                        </li>

                                        <li>
                                            <span>商品名称:</span>
                                            <input type="text" name="goods_name" value="">
                                        </li>
                                        <li>
                                            <span>商品价格:</span>
                                            <input type="text" name="price" value="">
                                        </li>
                                        <li>
                                            <span>商品销量:</span>
                                            <input type="text" name="sell_num" value="0">
                                        </li>
                                        <li>
                                            <span>商品库存:</span>
                                            <input type="text" name="store_num" value="0">
                                        </li>
                                        <li>
                                            <span>是否推荐到首页:</span>
                                            <input type="text" name="index" value="0">
                                        </li>
                                        <li>
                                            <span>商品关键字:</span>
                                            <input type="text" name="key" value="">
                                        </li>
                                        <li id="goodstype">
                                            <span>商品类型:</span>
                                            <select class="select" id="c_type" name="f_id">
                                                <option>请选择商品父类型</option>
                                            </select>
                                            <select class="select" id="type" name="type_id">
                                                <option>请选择商品子类型</option>
                                            </select>
                                        </li>

                                        <li class="baoliu">
                                            <span>商品详情:</span>
                                            <div id="editDiv" style="width: 650px;float: right" ></div>
                                            <input type="hidden" name="remake" value="">
                                        </li>
                                        <li  class="baoliu" style="clear: both">
                                            <input type="hidden" name="type" value="addGoods">
                                            <input class="no" name="reset" type="reset" value="重置">
                                            <input class="no" id="dot" type="submit" value="新品上架">
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
<script src="/Project/js/manager.js"></script>
<script>
    //显示错误信息
    let info = "${sessionScope.info}";
    if (info){
        // alert(info);
        $(".tang").text(info).show().delay(1000).hide(0);
    }

</script>
</body>
</html>
