
$(function () {
    //阻止搜索表单提交
    $("#keyButton").click(function () {
        findGoods(1);
        return false;
    })

    //隐藏首页的全部商品分类
    $(".st_d").hide();
    $(".dd-inner").hide();

    $("#pag>a").click(function () {
        //获取点击对象的index
        let index = $(this).data("index");
        findGoods(index);
    })

    $(document).on("click",".chan",function () {
        let level = $(this).data("type");
        //如果level=level1则显示二级目录
        if(level==="level1"){
            //查找二级目录
            let id = $(this).data("id");
            findlevel2(id);
        //如果level=level2则显示二级目录下的子目录
        }else if(level==="level2"){
            //查找其他目录
            let id = $(this).data("id");
            findlevelother(id);
        }
        //点击这个字段添加一个select样式其他的兄弟节点移除这个样式数
        $(this).addClass("select").siblings().removeClass("select");
        findGoods(1);
    })
    //添加用户浏览记录
    $(document).on("click",".images",function () {
        let goods_id = $(this).data("id");
        // alert(goods_id);
        $.post("/Project/user",{"type":"look","goods_id":goods_id},function (data){
            console.log(data);
        },"json")
    })

})

function findGoods(index) {
    let obj = {};
    obj.type ="findByKey";
    obj.index= index;

    obj.key = $("[name='key']").val();
    //获取被点击的一级目录的type_id
    obj.fid=$("#level1").find(".select").data("id");
    //点击一级目录进来 再通过点二级目录 获取被选中字段的type_id
    obj.type_id=$("#level2").find(".select").data("id");
    //点击首页二级目录获取的type_id
    obj.typeid = type_id;
    //获取二级目录下子目录的all_id
    obj.allid="";
    //所有有select样式的allid必须运行以下each函数
    $(".allid").find(".select").each(function (index) {
        if(index==$(".allid").find(".select").length-1){
            obj.allid+=$(this).data("id");
        }else{
            obj.allid+=$(this).data("id")+",";
        }
    })
    // console.log(obj);
    $.ajax({
        url:"/Project/goods",
        data:obj,
        type:"POST",
        dataType:"json",
        success:function (da) {
            // console.log(da);
            let html = "";
            let url = "";
            if (da.info.list.length>0){
                da.info.list.forEach(function (de) {
                    url = de.url.split(",");
                    // console.log(url[0]);
                    html+="<li>";
                    html+="<div class=\"lieb_neir_kuang\">";
                    html+="<div class=\"lieb_img\">";
                    html+="<a class=\"images\" data-id=\""+de.goods_id+"\" href=\"/Project/goods?type=getGoodsById&goods_id="+de.goods_id+"\"><img width=\"276px\" height=\"209px\" src=\"/Project/images/head/"+url[0]+"\"></a>";
                    html+="<div class=\"p_focus\"><a class=\"J_focus\" href=\"#\"><i></i>关注</a></div>";
                    html+="</div>";
                    html+="<div class=\"lieb_text\">";
                    html+="<div class=\"p_price\">";
                    html+="<strong class=\"J_price\"><em>¥</em><i>"+de.price+"</i></strong>";
                    html+="</div>";
                    html+="</div>";
                    html+="<div class=\"shangp_biaot_\"><a href=\"#\">"+de.goods_name+"</a></div>";
                    html+="</div>";
                    html+="</li>";
                })
            }
            //把ajax添加到html id=zoul的下面
            $("#zoul").empty().append(html);
            //上一页
            $("#prev").data("index",(da.info.index==1?1:da.info.index-1));
            //下一页
            $("#next").data("index",(da.info.index==da.info.pageCount?da.info.pageCount:da.info.index+1));
        }
    })
}
//查找一级目录
function findOne() {
    let obj = {};
    obj.type = "findOneLevel";
    // console.log(obj);
    //如果存在该参数   则表示需要查询一二级目录
    $.ajax({
        url: "/Project/goods",
        data: obj,
        type: "POST",
        dataType: "json",
        success: function (da) {
            let html = "<div id='level1'  class=\"sl-wrap\"> <div class=\"sl-key\"><span>商品分类:</span></div><div class=\"sl-value\">";
            html+="<div class=\"sl-v-list\"><ul class=\"J_valueList\">";
            da.info.forEach(function (item) {
                html+="<li class=\"chan\" data-type='level1' data-id='"+item.type_id+"'><a href=\"javascript:void(0)\" rel='nofollow' class=\"la\" data-id=\""+item.type_id+"\">"+item.type_name+"</a></li>";
            })
            html+="</ul></div></div></div>";
            if($("#level1").length>0){
                //移除 被选元素之后的所有同级元素 恢复被移除元素 插入到html里面。
                $("#level1").nextAll().remove().end().after(html);
            }else{
                $(".J_selectorFold").append(html);
            }
            //如果有type_id和level表示点击1级目录进入  应该选中对应的分类
            if(level&&fid){
                $("[data-type='level1']").filter("[data-id='"+fid+"']").click();
            }
        }
    })

}

//根据id获取二级目录
function findlevel2(fid) {
    let obj = {};
    obj.type = "findTwoLevel";
    obj.id = fid;
    // console.log(obj);
    //如果存在该参数   则表示需要查询一二级目录
    $.ajax({
        url: "/Project/goods",
        data: obj,
        type: "POST",
        dataType: "json",
        success: function (da) {
            let html = "<div id='level2' class=\"sl-wrap\"> <div class=\"sl-key\"><span>二级分类:</span></div><div class=\"sl-value\">";
            html+="<div class=\"sl-v-list\"><ul id='level2' class=\"J_valueList\">";
            let temph = "";
            da.info.forEach(function (item) {
                temph+="<li class=\"chan\" data-type='level2' data-id='"+item.type_id+"'><a href=\"javascript:void(0)\" rel='nofollow' class=\"la\" data-id=\""+item.type_id+"\">"+item.type_name+"</a></li>";
            })

            if($("#level2").length>0){
                // 移除 此节点被选元素之后的所有同级元素
                $("#level2").nextAll().remove();
                $("#level2").remove();
            }
            html+=temph;
            html+="</ul></div></div></div>";
            $(".J_selectorFold").append(html);
        }
    })
}
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
            let html="";
            da.info.forEach(function (al) {
                html+="<div class=\"sl-wrap allid\"> <div class=\"sl-key\"><span>"+al.all_name+"</span>";
                html+="</div><div class=\"sl-value\"><div class=\"sl-v-list\">";
                html+="<ul class=\"J_valueList\">";
                al.allList.forEach(function (min) {
                    html+="<li class=\"chan\" data-id=\""+min.all_id+"\">";
                    html+="<a href=\"javascript:void(0)\" rel='nofollow' class=\"la\" data-id=\""+min.all_id+"\">"+min.all_name+"</a></li>";
                })
                html+="</ul></div></div></div>";
            })
            if(!level){
                //如果level没有值 则显示以上内容
                $(".J_selectorFold").append(html);
            }else{
                //如果level有值 则显示完二级目录，根据二级目录再显示此目录
                $("#level2").nextAll().remove().end().after(html);
            }

        }
    })
}
