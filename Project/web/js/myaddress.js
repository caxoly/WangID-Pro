
$(function () {
    findAddress(user_id);
    //删除
    $(document).on("click",".delete",function () {
        let _this = $(this);
        let idDelete=confirm("确认删除吗？");
        let shop_id = $(_this).data("id");
        if (idDelete){
            $.post("/Project/order",{"type":"delete","shop_id":shop_id},function (data) {
                if (data.code==1){
                    $(_this).parent().parent().remove();
                    $(".tang").text("删除成功！").show().delay(1000).hide(0);
                }
            },"json")
        }
    })
    //修改
    $(document).on("click",".update",function () {
        let shop_id = $(this).data("id");
        $.post("/Project/order",{"type":"insertid","shop_id":shop_id},function (data) {

        },"json")
    })
})

//显示用户所有的地址
function findAddress(user_id) {
    $.post("/Project/order",{"type":"showAddress","user_id":user_id},function (data) {
        // console.log(data);
        let html = "";
        data.info.forEach(function (item) {
            html += "<div class=\"shouh_diz_kuang shouh_diz_kuang_mor\">";
            html += "<div class=\"item\"><span class=\"labal\">收件人：</span><p>"+item.shop_name+"</p></div>";
            html += "<div class=\"item\"><span class=\"labal\">所在地区：</span><p>"+(item.province+item.city+item.district)+"</p></div>";
            html += "<div class=\"item\"><span class=\"labal\">地址：</span><p>"+item.address+"</p></div>";
            html += "<div class=\"item\"><span class=\"labal\">手机：</span><p>"+item.shop_phone+"</p></div>";
            html += "<div class=\"bianj_yv_shanc\"><a data-id="+item.shop_id+" class=\"update\" href=\"/Project/jsp/Upress.jsp\"  data-reveal-id=\"myModal_1\">编辑</a><a data-id="+item.shop_id+" href=\"javascript:void(0);\" class=\"delete\">删除</a></div></div>";

        })
        $("#myBox").empty().append(html);

    },"json")
}
