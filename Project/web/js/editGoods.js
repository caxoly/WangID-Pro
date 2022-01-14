
$(function () {
    //富文本
    edit();
    //点击页面图片调用上传图片
    $(document).on("click",".showImg",function ()  {
        $("#fileImg").click();
    })
    $(document).on("change","#fileImg",function () {
        // alert(1)
        //    上传图片
        addUrl();
    })

})
function edit() {
    //初始化富文本编辑器
    let E = window.wangEditor;
    let editor2 = new E('#editDiv');
    //设置本地上传图标及上传的地址
    editor2.config.uploadImgShowBase64=true;
    editor2.config.uploadImgServer = "/Project/upload";
    //本地图片上传后将返回结果插入富文本框
    editor2.config.uploadImgHooks ={
        customInsert:function(insertLinkImg,result,editor){
            insertLinkImg("/Project/images/head/"+result.info);
        }
    }
    //创建富文本编辑器
    editor2.create();
    //点击提交按钮将富文本转HTML给remake
    $("#dot").click(function () {
        $("[name='remake']").val(editor2.txt.html());

    })
    let html = '${requestScope.goods.remake}';
    $("#editDiv").find(".w-e-text").empty().append(html);

}

function addUrl() {
    $.ajaxFileUpload({
        //上传处理程序地址
        url:"/Project/upload",
        dataType:"json",
        type:"POST",
        //文件选择框的id属性，即<input type="file">的id
        fileElementId:"fileImg",
        //是否启用安全提交，默认为false
        secureuri:false,
        success:function (data) {
            let info = "";
            console.log(data);
            if (data.code==200){
                info = data.info;
                // console.log(info.toString());
                // console.log(info[0]);
                // $(".showImg").attr("src","/Project/images/head/"+info[0]);
                let html = "";
                for (let i = 0; i < info.length; i++) {
                    html = "<img class=\"showImg\" width=\"100px\" height=\"100px\" src=\"/Project/images/head/"+info[i]+"\">";
                    $("#showImg").remove();
                    $(".url_img").append(html);
                }
                //将图片路径转以，分割的字符串存储
                let url = "<input type=\"hidden\" name=\"url\" value=\""+info.toString()+"\">";
                $(".url_img").append(url);
            }
        }

    })
}

//表单验证
function check() {
    if ($("[name='goods_name']").val().length<1){
        // alert("商品名不能为空")
        $(".tang").text("商品名不能为空").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='price']").val()<0||isNaN($("[name='price']").val())||$("[name='price']").val().length<1){
        // alert("价格不能小于0或只能是数字或不能为空")
        $(".tang").text("价格不能小于0或只能是数字或不能为空").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='sell_num']").val()<0||$("[name='store_num']").val()<0||$("[name='index']").val()<0){
        // alert("不能小于0")
        $(".tang").text("不能小于0").show().delay(1000).hide(0);
        return false;
    }
    if (isNaN($("[name='sell_num']").val())||isNaN($("[name='store_num']").val())||isNaN($("[name='index']").val())){
        // alert("只能是数字")
        $(".tang").text("只能是数字").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='type_id']").val().length<1||$("[name='type_id']").val()<0){
        // alert("商品类型不能小于0或为空")
        $(".tang").text("商品类型不能小于0或为空").show().delay(1000).hide(0);
        return false;
    }
    if ($("[name='key']").val().length<1){
        // alert("商品关键字不能为空")
        $(".tang").text("商品关键字不能为空").show().delay(1000).hide(0);
        return false;
    }
    return true;
}
