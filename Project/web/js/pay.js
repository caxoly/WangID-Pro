
$(function () {
    //提交订单
    $("#paySubmit").click(function () {
        //支付密码
        let payPwd = $("[name='payPwd']").val();
        if (payPwd==pwd){
            // alert(1)
            //获取订单编号
            let order_id = "${param.order_id}";
            let sum_money = "${param.sum}"
            // alert(user_money+" "+sum_money)
            // alert(order_id)
            if (user_money>=sum_money){
                pay(user_id,sum_money,order_id);
            }else if (user_money<sum_money){
                // alert("用户余额不足，请先充值！");
                alert(1)
                $(".tan").text("用户余额不足，请先充值！").show().delay(1000).hide(function () {
                    window.location.href="/Project/jsp/operatemoney.jsp";
                });
            }else if(user_money<=0){
                // alert("用户余额不足，请先充值！");
                $(".tan").text("用户余额不足，请先充值！").show().delay(1000).hide(function () {
                    window.location.href="/Project/jsp/operatemoney.jsp";
                });
            }
        }else{
            // alert("密码不正确")
            $(".tan").text("密码不正确").show().delay(1000).hide(0);
        }
    })


})
//支付并修改状态
function pay(user_id,sum_money,order_id) {
    let obj = {};
    obj.user_id = user_id;
    obj.type = "setUserMoney";
    obj.sum_money = sum_money;
    //支付金钱
    $.post("/Project/order",obj,function (data) {
        console.log(data);
        if(data.code==1){
            //修改支付状态
            $.post("/Project/order",{"type":"setState","order_id":order_id},function (data) {
                // console.log(data);
                if (data.code==1){
                    // alert("支付成功！");
                    $(".tan").text("支付成功！").show().delay(1000).hide(function () {
                        window.location.href="/Project/jsp/myorder.jsp";
                    });
                }
            },"json")
        }
    },"json")
}