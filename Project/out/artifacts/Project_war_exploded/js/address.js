
$(function () {
    //获取省份
    getProvince();

    //动态绑定省份改变事件
    $(document).on("change","#province",function () {
        // alert($("[name='province']").val());
        //查询所有的城市
        let Pid = $("[name='province']").val();
        getCity(Pid);
        //将所选中的文本值赋给name=‘p_name’
        $("[name='p_name']").val($("#province option:selected").text());
    })

    //动态绑定城市改变事件
    $(document).on("change","#city",function () {
        // alert($("[name='city']").val());
        //查询所有的城市
        let Pid = $("[name='city']").val();
        getDistinct(Pid);
    })
    //动态绑定区县改变事件
    $(document).on("change","#district",function () {
        //将所选中的文本值赋给name=‘d_name’
        $("[name='d_name']").val($("#district option:selected").text());
    })

})

function getProvince() {
    //查询所有的省
    $.post("/Project/order",{"type":"findProvince"},function (data) {
        // console.log(data);
        let html = "";
        // console.log(data.info);
        data.info.forEach(function (item) {
            // console.log(item)
            html += "<option value=\""+item.id+"\">"+item.name+"</option>";
        })
        //将所有循环加入页面
        $("#province").empty().append(html);
        let Pid = $("[name='province']").val();
        getCity(Pid);
        //将当前省份名称给到对应隐藏域
        // $("#id option:selected").text();  //获取选中的option的文本值
        $("[name='p_name']").val($("#province option:selected").text());
    },"json")
}
function getCity(Pid) {
    $.post("/Project/order",{"type":"findCity","Pid":Pid},function (data) {
        // console.log(data);
        let html = "";
        // console.log(data.info);
        data.info.forEach(function (item) {
            html += "<option value=\""+item.id+"\">"+item.name+"</option>";
        })
        $("#city").empty().append(html);
        let Pid = $("[name='city']").val();
        getDistinct(Pid)
        //将当前省份名称给到对应隐藏域
        // $("#id option:selected").text();  //获取选中的option的文本值
        $("[name='c_name']").val($("#city option:selected").text());
    },"json")
}
function getDistinct(Pid) {
    $.post("/Project/order",{"type":"findCity","Pid":Pid},function (data) {
        // console.log(data);
        let html = "";
        //如果区县的值不为空就默认第一个  为空为显示默认区县
        if(data.info.length>0){
            // console.log(data.info);
            data.info.forEach(function (item) {
                html += "<option value=\""+item.id+"\">"+item.name+"</option>";
            })

        }else{
            html="<option value=\"0\">默认区县</option>";
        }
        $("#district").empty().append(html);
        //将当前省份名称给到对应隐藏域
        // $("#id option:selected").text();  //获取选中的option的文本值
        $("[name='d_name']").val($("#district option:selected").text());
    },"json")
}

//表单验证
function check() {
    if ($("[name='shop_name']").val().length<1){
        $(".tang").text("收货人姓名不能为空").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='shop_phone']").val().length<1||isNaN($("[name='shop_phone']").val())||$("[name='shop_phone']").val().length>11){
        $(".tang").text("收货人号码请输入数字并且为11位号码").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='province']").val().length<1||$("[name='city']").val().length<1||$("[name='district']").val().length<1||$("[name='address']").val().length<1){
        $(".tang").text("地址不能为空").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='district']").val()==0){
        $(".tang").text("请选择区县").show().delay(1000).hide(0);
        return false;
    }
    return true;
}