
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/Project/public/css/xh.css">
    <link rel="stylesheet" type="text/css" href="/Project/public/css/xhico.css">
    <link rel="stylesheet" type="text/css" href="/Project/public/Font/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/Project/public/css/main.css">
    <script src="/Project/public/js/jquery.js" type="text/javascript"></script>
    <script src="/Project/public/js/xh.js?tmp=123" type="text/javascript"></script>
</head>
<body>
<div class="xh-main">
    <div class="xh-header">
        <h1 class="xh-fleft">通城商城管理系统</h1>
        <a id="modal_admin" class="xh-fright"><i class="fa fa-user-circle-o fa-fw"></i> ${sessionScope.user.like_name}</a>
        <a href="javascript:void(0);" class="xh-fright goIndex"><i class="fa fa-home fa-fw"></i> 首页</a>
    </div>
    <div class="xh-content">
        <div class="xh-menu">
            <ul>
                <li class="xh-nav goIndex" xh-nav-id="#child-first">
                    <xh><i class="fa fa-home fa-fw"></i> 首页</xh>
                </li>
                <li class="xh-nav" xh-nav-id="#child-second">
                    <xh><i class="fa fa-codepen fa-fw"></i> 商品管理</xh>
                    <ul id="child-second" class="xh-nav-child">
                        <li class="xh-href" id="all"><i class="fa fa-angle-right fa-fw"></i> 所有商品</li>
                        <li class="xh-href" id="add"><i class="fa fa-angle-right fa-fw"></i> 添加商品</li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="xh-context">
            <div class="xh-text-top">
                <i class="fa fa-list fa-fw xh-pointer" id="xh-isopen" title="展开/收起"></i>
                <!--横向列表菜单-->
                <ul id="xh-menubar">
                    <li class="xh-tactive goIndex"><i class="fa fa-home fa-fw"></i></li>
                </ul>
                <!---->
                <i class="fa fa-circle-o-notch fa-fw" id="xh-refresh" title="刷新页面"></i>
            </div>


<script>
    $(function () {
        //点击调回商城的首页
        $(".two_input").click(function () {
            window.location.href="/Project/index.jsp";
        })
        //点击返回管理首页
        $(".goIndex").click(function () {
            window.location.href="/Project/jsp/managerindex.jsp";
        })
        //点击查看商品
        $("#all").click(function () {
            window.location.href="/Project/jsp/managershow.jsp";
        })
        //点击添加商品
        $("#add").click(function () {
            window.location.href="/Project/jsp/manager.jsp";
        })

    })
</script>