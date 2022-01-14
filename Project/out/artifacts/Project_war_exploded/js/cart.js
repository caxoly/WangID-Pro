
$(function(){

    //最近浏览
    $.post("/Project/goods",{"type":"recentLook","cartLook":"0"},function (data){
        // console.log(data);
        let html = "";
        data.info.forEach(function (item) {
            html += "<li><div class=\"pic\"><a href=\"/Project/goods?type=getGoodsById&goods_id="+item.goods_id+"\" target=\"_blank\"><img src=\"/Project/images/head/"+item.url+"\"/></a></div>";
            html += "<div class=\"title\" target=\"_blank\"><a href=\"javascript:void(0);\">"+item.goods_name+"</a>";
            html += "<div class=\"jiage_gouw\"><span>¥"+item.price+"</span></div>";
            html += "<a href=\"javascript:void(0);\" id=\"addCart\"data-id=\""+item.goods_id+"\" class=\"cart_scroll_btn\">加入购物车</a></div></li>";

        })
        $("#recentLook").append(html);
    },"json")

    //最近浏览加入购物车
    $(document).on("click","#addCart",function () {
        let goods_id = $(this).data("id");
        ///添加购物车
        $.post("/Project/goods", {"type":"addCart","goods_id":goods_id},function (info) {
            // console.log(info)
            if (info.code==0){
                //添加到购物车失败
                $(".tang").text("添加到购物车失败").show().delay(1000).hide(function () {
                    window.location.reload();
                });
            }else if (info.code==1){
                $(".tang").text("添加数量成功").show().delay(1000).hide(function () {
                    window.location.reload();
                });
            }else if (info.code==2) {
                //添加到购物车成功
                $(".tang").text("添加到购物车成功").show().delay(1000).hide(function () {
                    window.location.reload();
                });
                $("#cartNum").text(parseInt($("#cartNum").text())+1)
            }

        },"json")
    })

    //判断结算时是否已选择商品
    $("#submit").click(function () {
        if($("[name='cart_id']:checked").length==0){
            $(".tang").text("您没有选中任何商品").show().delay(1000).hide(0);
            return;
        }
        if(user_type==1){
            $(".tang").text("管理员不能进行购买").show().delay(1000).hide(0);
            return;
        }
        $("#toSubmit").click()

    })
    operateMoney();
    //所选择商品数量加一
    $(".add").click(function () {
        let _this=this
        let sumPrice = parseFloat($(_this).parents("[name='five']").next().children().text().replace("¥",""));
        // alert(sumPrice)
        let c_id=$(".delete").data("id")
        let price=parseFloat($(_this).data("price"))
        $.post("/Project/goods",{"type":"addCartNum","c_id":c_id},function (data) {
            // console.log(data)
            if(data.code==1){
                $(_this).next().find(".num").val(parseInt($(_this).next().find(".num").val())+1);
                $(_this).parents("[name='five']").next().children().text("¥"+(sumPrice+price).toFixed(2));
                operateMoney();
            }

        },"json")
    })
    //所选择商品数量减一
    $(".del").click(function () {
        let num = parseInt($(".num").val());
        // alert(num);
        if(num==1){
            $(this).parents("[name='five']").next().next().find(".delete").click();
            return false;
        }
        let _this=this
        let sumPrice = parseFloat($(_this).parents("[name='five']").next().children().text().replace("¥",""));
        // alert(sumPrice)
        let c_id=$(".delete").data("id")
        let price=parseFloat($(this).data("price"))
        $.post("/Project/goods",{"type":"delCartNum","c_id":c_id},function (data) {
            // console.log(data)
            if(data.code==1){
                $(_this).next().next().find(".num").val(parseInt($(_this).next().next().find(".num").val())-1);
                $(_this).parents("[name='five']").next().children().text("¥"+(sumPrice-price).toFixed(2));
                operateMoney();
            }

        },"json")
    })

    //删除商品
    $(".delete").click(function () {
        let idDelete=confirm("确认删除吗？")
        let _this = $(this);
        if(idDelete){
            let cart_id=$(this).data("id");
            $.post("/Project/goods",{"type":"deleteCart","cart_id":cart_id},function (data) {
                // console.log(data);
                if(data.code==1){
                    _this.parents("[name='trDelete']").remove();
                    operateMoney();
                }
            },"json")

        }

    })
    //全选
    $("#allCheck").click(function () {
        $("[name='cart_id']").prop("checked",$("#allCheck").prop("checked"));
        operateMoney();
    })
    //取消
    $("[name='cart_id']").click(function(){
        //如果选中了就true
        let isCheck = true;
        //循环 如果有一个取消则取消
        $("[name='cart_id']").each(function(index,item){
            if(item.checked === false){
                isCheck = false;
            }
        })
        //如果取消了就取消选择
        $("#allCheck").prop('checked',isCheck);
        operateMoney();
    })


    /*tab标签切换*/
    function tabs(tabTit,on,tabCon){
        $(tabCon).each(function(){
            $(this).children().eq(0).show();

        });
        $(tabTit).each(function(){
            $(this).children().eq(0).addClass(on);
        });
        $(tabTit).children().click(function(){
            $(this).addClass(on).siblings().removeClass(on);
            var index = $(tabTit).children().index(this);
            $(tabCon).children().eq(index).show().siblings().hide();
        });
    }
    tabs(".investment_title","on_d",".investment_con");

})
//计算选择商品金额
function operateMoney() {
    let sum=0;
    let goodsNum=0;
    //下标 点击按钮的input
    $("[name='cart_id']:checked").each(function (index,item) {
        // console.log(item);
        goodsNum++;
        sum+=parseFloat($(item).parent().next().next().find("[name='six']").children().text().replace("¥",""))
    })
    $("#sumMoney").text("￥"+sum.toFixed(2));
    $("[name='sumMoney']").val(sum.toFixed(2));
    $("#num").text(goodsNum);
}
jQuery(".picScroll_left_s").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:5,trigger:"click"});
