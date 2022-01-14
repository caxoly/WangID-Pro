
$(function () {
    $("#submit").click(function () {
        let isBuy = confirm("确认支付吗？");
        if (isBuy){
            //调用表单提交事件
            $(this).next().click();
        }
    })

    //ajax异步用动态绑定事件 选取收货人信息
    $(document).on("click",".now_add",function () {
        //给相应点击节点添加样式  去除兄弟节点的样式
        $(this).parent().addClass("cur").siblings().removeClass("cur");
        // alert($(this).parent().filter(".cur").data("id"))

        address();
    })


    //选择支付方式
    $(".pay").click(function () {
        //给相应点击节点添加样式  去除兄弟节点的样式
        $(this).parent().addClass("cur").siblings().removeClass("cur");
        // alert($(this).parent().filter(".cur").data("pay"))
        $("[name='pay_way']").val($(this).parent().filter(".cur").data("pay"));
    })

    //选择收货渠道
    $(".give").click(function () {
        //给相应点击节点添加样式  去除兄弟节点的样式
        $(this).parent().addClass("cur").siblings().removeClass("cur");
        // alert($(this).text())
        $("[name='give_way']").val($(this).text());
    })


})

//显示我所有的收货地址
function findAddress(user_id) {
    $.post("/Project/order",{"type":"showAddress","user_id":user_id},function (data) {
        // console.log(data);
        let html = "";
        data.info.forEach(function (item) {
            html += "<li data-id=\""+item.shop_id+"\"><div class=\"dangq_xuanz_diz now_add\">当前地址</div>";
            html += "<span>"+item.shop_name+"</span>";
            html += "<span>"+(item.province+"\t"+item.city+"\t"+item.district+"\t"+item.address)+"</span>";
            html += "<span>"+item.shop_phone+"</span></li>";
        })
        $("#ressBox").empty().append(html);
        $("#ressBox li").eq(0).addClass("cur");
        $(".shiq_1 ul li").hide().first().show();
        address();
    },"json")
}

//显示收货地址
function address() {
    let address = $("#ressBox>li").filter(".cur").children().eq(2).text();
    $("#address").text("寄送至："+address);
    let name = $("#ressBox>li").filter(".cur").children().eq(1).text();
    let phone = $("#ressBox>li").filter(".cur").children().eq(3).text();
    $("#name").text("收货人："+name+"\t"+phone);
    //默认shop_id为第一个地址
    $("[name='shop_id']").val($("#ressBox>li").filter(".cur").data("id"));
}