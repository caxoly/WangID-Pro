
$(function () {
    //查询所有的商品父类型
    findFtype();
    //选择父类型查询对应的子类型
    $(document).on("change","#c_type",function () {
        let f_id = $("#c_type").val();
        findType(f_id);
    })
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
    //改变子类型选择相应的关键字
    $(document).on("change","#type",function(){
        let id = $(this).val();
        findlevelother(id);
    })
    //选择详情
    $(document).on("click",".checkboxs",function () {
        //所选中的长度
        let len = $(this).siblings().filter(":checked").length;
        // alert(len);
        //单选 如果选中一个则不能在选择
        if(len>0){
            return false;
        }
    })

    $(document).on("click","[name='reset']",function () {
        // alert($("[name='allid']:checked").val())
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

//显示分类详情
function findlevelother(fid) {
    let obj = {};
    obj.type = "findThreeLevel";
    obj.id = fid;
    // console.log(obj);
    //如果存在该参数   则表示需要查询一二级目录
    $.ajax({
        url: "/Project/goods",
        data: obj,
        type: "POST",
        dataType: "json",
        success: function (da) {
            // console.log(da);
            let html="";
            da.info.forEach(function (al) {
                // console.log(al);
                html+="<li><span>"+al.all_name+"</span><div>";
                al.allList.forEach(function (min) {
                    console.log(min);
                    html+="<input class=\"checkboxs\" data-id=\""+min.all_id+"\" type=\"checkbox\" name=\"allid\" value=\""+min.all_id+"\"><div class=\"spans\">"+min.all_name+"</div>";
                })
                html+="</div></li>";
            })
            //查询id为goodstype同级的所有节点
            $("#goodstype").nextAll().each(function () {
                //如果不是保留的则移除
                if(!$(this).hasClass("baoliu")){
                    $(this).remove();
                }
            })
            //在被选元素后插入指定的内容
            $("#goodstype").after(html);
        }
    })

}

function findFtype() {
    $.post("/Project/user",{"type":"findFtype"},function (data) {
        // console.log(data);
        let html = "";
        data.info.forEach(function (item) {
            html += "<option value=\""+item.type_id+"\">"+item.type_name+"</option>";
        })
        $("#c_type").append(html);

    },"json")
}

function findType(f_id) {
    $.post("/Project/user",{"type":"findType","f_id":f_id},function (data) {
        // console.log(data);
        let html = "";
        // console.log(data.info);
        data.info.forEach(function (item) {
            // console.log(item);
            html += "<option value=\""+item.type_id+"\">"+item.type_name+"</option>";
        })
        $("#type").empty().append(html);
        //子类型id
        let id = $("#type").val();
        // alert(id)
        findlevelother(id);
    },"json")
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
