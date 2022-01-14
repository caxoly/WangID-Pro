
$(function () {
    //显示浏览记录
    findLook();

    //加入购物车
    $(document).on("click",".addMyCart",function(){
        let goods_id = $(this).data("id");
        // alert(goods_id);
        let _this=this;
        $.post("/Project/goods",{"type":"addMyCart","goods_id":goods_id},function (data) {
            // console.log(data);
            if(data.code==1){
                // alert("添加成功！")
                $(".tang").text("添加成功！").show().delay(1000).hide(0);
            }
        },"json")
    })

})
//显示浏览记录
function findLook() {
    $.post("/Project/goods",{"type":"recentLook","cartLook":"1"},function (data) {
        // console.log(data);
        let html = "";
        data.info.forEach(function (item) {
            html += "<li><label></label><div class=\"overflow\">";
            html += "<div class=\"shouc_img\"><a href=\"/Project/goods?type=getGoodsById&goods_id="+item.goods_id+"label \"><img src=\"/Project/images/head/"+item.url+"\"></a></div></div><div class=\"col280\">";
            html += "<h2><a href=\"#\" target=\"_blank\">"+item.goods_name+"</a></h2><div class=\"price_box\">";
            html += "<span>￥"+item.price+"</span></div>";
            html += "<div class=\"price_box\"><a class=\"button_grey addMyCart\" data-id=\""+item.goods_id+"\">加入购物车</a>";

        })
        $("#showLook").append(html);
    },"json")
}
