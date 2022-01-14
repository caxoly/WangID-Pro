<%@ page import="po.AllType" %>
<%@ page import="service.ClassifyService" %>
<%@ page import="po.GoodsType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>分类查询</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <style>
        .select{
            color:#e10601 ;
            background: #dfbfba;
        }

    </style>
</head>
<body>

<!--头部-->
<%@include file="onetop.jsp"%>
<%@include file="twotop.jsp"%>

<div class="shangp_lieb_jvz">
    <div class="selector">
        <div class="s-title">
            <h3><em>商品筛选</em></h3>
        </div>
        <!--分类查询，循环显示数据-->
        <div class="J_selectorLine s-line J_selectorFold" >
        </div>
    </div>

    <script type="text/javascript">
        function xians()
        {
            var ddd=document.getElementById('yingc');
            if(ddd.style.display=='block')
            {
                ddd.style.display='none';
            }
            else
            {
                ddd.style.display='block';
            }
        }
    </script>
    <!--列表-->
    <div class="shangp_lieb_yi">
        <div class="filter_yi">
            <div class="f_line">
                <div class="f_sort">

                </div>
                <div class="f_pager" id="J_topPage">

                </div>
                <div class="lieb_anniu_kuang">

                </div>
            </div>
        </div>
    </div>
    <div class="shnagp_list_v1">
        <ul id="zoul">

        </ul>
        <div class="page clearfix">
            <div class="p-wrap" id="J_bottomPage">
                <span class="p-num" id="pag">
                    <a class="pn-prev disabled" href="javascript:void(0);" id="prev"><i>&lt;</i><em>上一页</em></a>
                    <a class="pn-next" href="javascript:void(0);" id="next">下一页<i>&gt;</i></a>
                </span>
            </div>
        </div>
    </div>
</div>
</div>

<!--底部-->
<%@include file="bottom.jsp"%>
<script src="/Project/js/classify.js"></script>
<script>
    //获取index页面传过来二级目录的type_id值
    let type_id='${param.type_id}';
    //获取index页面传过来的level值
    let level= '${param.level}';
    //获取index页面传过来一级目录的type_id值
    let fid='${param.fid}';
    if(level){
        //如果搜索框有值则根据搜索框的值来显示数据
        if($("[name='key']").val('${param.key}')!=null){
            findGoods(1);
        }
        //如果level存在则显示一级分类
        findOne();
    }else{
        //如果level不存在则显示二级分类的子分类
        findlevelother(type_id);
        findGoods(1);
    }
    //获取搜索框传过来的key值
    $("[name='key']").val('${param.key}');
</script>
</body>
</html>
