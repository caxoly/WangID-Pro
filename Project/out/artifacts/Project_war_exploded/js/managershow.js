
$(function () {
    //显示第一页的商品
    showGoods(1);

    //点击分页按钮获取当前的页面数
    $(".b_right").children().click(function () {
        let index = $(this).data("index");
        showGoods(index);
    })
    //删除商品
    $(document).on("click",".delete",function () {
        // alert(1);
        let goods_id = $(this).data("id");
        let idDelete=confirm("确认删除吗？")
        let _this = $(this);
        if(idDelete){
            $.post("/Project/goods",{"type":"deleteGoods","goods_id":goods_id},function (data) {
                // console.log(data);
                if (data.code==200){
                    // alert("删除成功！")
                    $(".tang").text("删除成功！").show().delay(1000).hide(0);
                    $(_this).parents("tr").remove();
                }
            })
        }
    })

})

//显示商品
function showGoods(index) {
    $.post("/Project/goods",{"type":"allGoods","index":index},function (data) {
        // console.log(data);
        let html = "";
        data.info.list.forEach(function (item) {
            html += "<tr><td class=\"table_td2\"><img width=\"90px\" height=\"80px\" src=\"/Project/images/head/"+item.url+"\"></td>";
            html += "<td class=\"table_td3\">"+item.goods_name+"</td><td class=\"table_td2\"><span>￥"+item.price+"</span></td>";
            html += "<td class=\"table_td2\">"+item.sell_num+"</td><td class=\"table_td2\">"+item.store_num+"</td>";
            html += "<td class=\"table_td2\">"+item.index+"</td><td class=\"table_td3\">"+item.key+"</td>";
            html += "<td class=\"table_td2\">"+item.create_time+"</td><td class=\"table_td3\">"+item.type_id+"</td><td class=\"table_td2\">";
            html += "<a class=\"t_ope update\" href=\"/Project/goods?type=updateGoods&goods_id="+item.goods_id+"\">修改</a><div class=\"t_ope delete\" data-id=\""+item.goods_id+"\">删除</div></td></tr>";

        })
        //查询id为showgoods同级的所有节点
        $("#showgoods").nextAll().each(function () {
            //如果不是保留的则移除
            if(!$(this).hasClass("baoliu")){
                $(this).remove();
            }
        })
        $("#showgoods").after(html);

        $("#first").data("index",1);
        $("#prev").data("index",(data.info.index==1?1:data.info.index-1));
        $("#next").data("index",(data.info.index==data.info.pageCount?data.info.pageCount:data.info.index+1));
        $("#last").data("index",data.info.pageCount==0?1:data.info.pageCount);
    },"json")
}