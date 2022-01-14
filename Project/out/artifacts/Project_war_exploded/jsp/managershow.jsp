<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="managercustom.jsp"%>
<%--            查询所有商品--%>
            <div class="xh-text" >
                <div class="xh-iframe">
                    <div class="one">&nbsp&nbsp&nbsp&nbsp所 有 商 品</div>
                    <div class="two2">
                        <table class="table">
                            <tr class="baoliu" id="showgoods">
                                <td class="table_td">商品图片</td>
                                <td class="table_td4">商品名称</td>
                                <td class="table_td">商品价格</td>
                                <td class="table_td">商品销量</td>
                                <td class="table_td">商品库存</td>
                                <td class="table_td4">是否推荐到首页</td>
                                <td class="table_td4">商品关键字</td>
                                <td class="table_td">创建时间</td>
                                <td class="table_td">商品类型</td>
                                <td class="table_td">操作</td>
                            </tr>

                        </table>

                        <div class="bottom">
                            <div class="b_right">
                                <div class="b_pag" id="first">首页</div>
                                <div class="b_pag" id="prev">上一页</div>
                                <div class="b_pag" id="next">下一页</div>
                                <div class="b_pag" id="last">尾页</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="tang" style="display:none;"></div>
<script src="/Project/js/managershow.js"></script>
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
