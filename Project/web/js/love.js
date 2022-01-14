
$(function () {
    //显示收藏
    findLove();

    //全选按钮
    $("#allCheck").click(function () {
        // alert(1)
        $("[name='love_id']").prop("checked",$("#allCheck").prop("checked"))
    })
    $(document).on("click","[name='love_id']",function(){
        //如果选中了就true
        let isCheck = true;
        //循环 如果有一个取消则取消
        $("[name='love_id']").each(function(index,item){
            if(item.checked === false){
                isCheck = false;
            }
        })
        //如果取消了就取消选择
        $("#allCheck").prop('checked',isCheck);
    })

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

    //取消收藏
    $(document).on("click",".delete",function() {
        let isDelete=confirm("确认取消收藏吗？")
        if(isDelete){
            let love_id = $(this).data("id");
            // alert(love_id)
            let _this=this;
            $.post("/Project/goods",{"type":"notLove","love_id":love_id},function (data) {
                // console.log(data);
                if(data.code==1){
                    $(_this).parents("li").remove();
                }
            },"json")
        }

    })
})
//显示收藏
function findLove() {
    $.post("/Project/goods",{"type":"findLove"},function (data) {
        // console.log(data);
        let html = "";
        data.info.forEach(function (item) {
            html += "<li><label><input type=\"checkbox\" class=\"checkbox\" name=\"love_id\" value=\""+item.love_id+"\"></label><div class=\"overflow\">";
            html += "<div class=\"shouc_img\"><a href=\"/Project/goods?type=getGoodsById&goods_id="+item.goods_id+"\"><img src=\"/Project/images/head/"+item.url+"\"></a></div></div><div class=\"col280\">";
            html += "<h2><a href=\"#\" target=\"_blank\">"+item.goods_name+"</a></h2><div class=\"price_box\">";
            html += "<span>￥"+item.price+"</span></div><div class=\"price_box\"><span class=\"font_aide\">收藏时间："+item.love_time+"</span></div>";
            html += "<div class=\"price_box\"><a class=\"button_grey addMyCart\" data-id=\""+item.goods_id+"\">加入购物车</a>";
            html += "<a class=\"button_grey delete\" data-id=\""+item.love_id+"\">取消收藏</a></div></div></li>";

        })
        $("#showLove").append(html);
    },"json")
}