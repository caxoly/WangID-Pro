$(function () {
    //显示用户所有的订单
    findOrder(user_id);

    $(document).on("click",".wod_dingd_delete",function () {
        // alert(1);
        let order_id = $(this).parent().prev().find("[name='id']").text();
        // alert(order_id)
        let _this = $(this);
        let isDelete = confirm("确认删除吗？");
        if (isDelete){
            $.post("/Project/order",{"type":"setShow","order_id":order_id},function (data){
                // console.log(data);
                _this.parents("tbody").remove();
                $(".ta").text("删除成功").show().delay(1000).hide(0);
            },"json")
        }

    })

    $(document).on("click",".isBuy",function () {
        //订单金额
        let sum_money = parseFloat($(this).parents("tr").children().eq(1).find("[name='sum']").text());
        let order_id = parseInt($(this).parents("tr").prev().find("[name='id']").text());
        // alert(sum_money+" "+user_money+" "+order_id+" "+pwd);

        let isBuy = confirm("确认支付吗？");
        if (isBuy){
            //显示输入密码
            $(".order_top2").show();
            //提交订单
            $("#submit").click(function () {
                //支付密码
                let payPwd = $("[name='payPwd']").val();
                if (payPwd==pwd){
                    // alert(1)
                    if (user_money>=sum_money){
                        pay(user_id,sum_money,order_id);
                    }else if (user_money<sum_money){
                        // alert("用户余额不足，请先充值！");
                        $(".ta").text("用户余额不足，请先充值！").show().delay(1000).hide(function () {
                            window.location.href="/Project/jsp/operatemoney.jsp";
                        });

                    }else if(user_money<=0){
                        // alert("用户余额不足，请先充值！");
                        $(".ta").text("用户余额不足，请先充值！").show().delay(1000).hide(function () {
                            window.location.href="/Project/jsp/operatemoney.jsp";
                        });
                    }
                }else{
                    // alert("密码不正确")
                    $(".ta").text("密码不正确").show().delay(1000).hide(0);
                }
            })
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
        // console.log(data);
        if(data.code==1){
            //修改支付状态
            $.post("/Project/order",{"type":"setState","order_id":order_id},function (data) {
                // console.log(data);
                if (data.code==1){
                    // alert("支付成功！");
                    $(".ta").text("支付成功！").show().delay(1000).hide(function () {
                        window.location.href="/Project/jsp/myorder.jsp";
                    });
                }
            },"json")
        }
    },"json")
}

//操作状态
function showOperate(state) {
    if(state===0){
        return "去付款";
    }else if(state===1||state===2||state===3){
        return "再次购买";
    }
}

//订单状态
function showState(state) {
    if(state===0){
        return "未付款";
    }else if(state===1){
        return "已付款";
    }else if(state===2){
        return "已发货";
    }else if(state===3){
        return "已签收";
    }
}

//支付手段
function showPayWay(payWay) {
    if (payWay===0){
        return "在线支付";
    }else if (payWay===1){
        return "货到付款";
    }
}