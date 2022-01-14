
$(function () {
    //收藏
    $(".shouc").click(function () {
        // alert(1);
        $.post("/Project/goods",{"type":"loveGoods","goods_id":goods_id},function (data) {
            // console.log(data);
            if (data.code==0){
                // alert(data.info);
                $(".tang").text(data.info).show().delay(1000).hide(0);
            }else if (data.code==1){
                // alert(data.info);
                $(".tang").text(data.info).show().delay(1000).hide(0);
            }
            if (data.code==400){
                window.location.href="/Project/jsp/login.jsp";
            }
        },"json")
    })

    //猜你喜欢
    let key = $("#max").data("id");
    love(key);

})
function love(key) {
    $.post("/Project/goods",{"type":"getGoodsKeyId","key":key},function (data) {
        // console.log(data);
        let html = "";
        let url = "";
        if (data.info.length>0){
            data.info.forEach(function (item) {
                url = item.url.split(",");
                html+="<li>";
                html+="<a href=\"/Project/goods?type=getGoodsById&goods_id="+item.goods_id+"\">";
                html+="<img src=\"/Project/images/head/"+url[0]+"\">";
                html+="<p>"+item.goods_name+"</p>";
                html+="</a>";
                html+="</li>"

            })
        }
        $("#max").empty().append(html);
    },"json")

}