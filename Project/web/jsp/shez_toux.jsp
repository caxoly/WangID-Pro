
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>头像</title>
    <link rel="stylesheet" type="text/css" href="/Project/css/index.css">
    <link rel="stylesheet" type="text/css" href="/Project/css/ziy.css">
    <script src="/Project/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<!--头部-->
<%@include file="onetop.jsp"%>
<script src="/Project/js/ajaxfileupload.js"></script>
<%@ include file="fourtop.jsp"%>
<div class="wod_tongc_zhongx">
    <div class="beij_center">
        <div class="myGomeWeb">
            <!--侧边导航-->
            <%@include file="threetop.jsp"%>
            <!--左边内容-->
            <div class="mod_main">
                <div class="jib_xinx_kuang">
                    <div class="wt">
                        <ul>
                            <li><a href="/Project/jsp/usercenter.jsp">个人信息</a></li>
                            <li class="dangq_hongx"><a href="/Project/jsp/shez_toux.jsp">设置头像</a></li>
                            <li><a href="/Project/jsp/operatemoney.jsp">账户充值</a></li>
                        </ul>
                    </div>
                    <div class="wd">
                        <form method="post" action="/Project/user">
                            <div class="up_avater">
                                <div class="warp_tip">
                                    <div id="outImages" class="avater_btn">+上传头像</div>
                                    <input type="hidden"  value="${sessionScope.user.image_url}">
                                    <input type="file" id="upload" name="upload" style="display:none;"/>
                                    <div class="upload_tip">
                                        <p class="upload_text">仅支持JPG、JPEG、PNG图片文件，且文件小于2M</p>
                                    </div>
                                    <div class="upload_main">
                                        <div class="up-left">
                                            <div class="pic-show">
                                                <div class="pic-wrap ">
                                                    <input type="hidden" name="user_url" value="">
                                                    <c:if test="${empty sessionScope.user.image_url}" var="isOk">
                                                        <img class="showImg" src="/Project/images/DefaultAvatar.jpg">
                                                    </c:if>
                                                    <c:if test="${not isOk}">
                                                        <img class="showImg" src="/Project/images/head/${sessionScope.user.image_url}" />
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="up-right">
                                            <div class="up-right-title">
                                                <h5>效果预览</h5>
                                                <p>你上传的图片会自动生成3种尺寸，请注意小尺寸的图片是否清晰</p>
                                            </div>
                                            <div class="up-top">
                                                <div class="pic-100-box">
                                                    <div class="pic-100 ">
                                                        <c:if test="${empty sessionScope.user.image_url}" var="isOk">
                                                            <img class="showImg" src="/Project/images/DefaultAvatar.jpg">
                                                        </c:if>
                                                        <c:if test="${not isOk}">
                                                            <img class="showImg" src="/Project/images/head/${sessionScope.user.image_url}" />
                                                        </c:if>
                                                    </div>
                                                    <span class="pic-tip">100X100像素</span>
                                                </div>
                                            </div>
                                            <div class="uc_container">
                                                <div class="up-bottom uc-pic-img">
                                                    <div class="pic-60-box">
                                                        <div class="pic-60 ">
                                                            <c:if test="${empty sessionScope.user.image_url}" var="isOk">
                                                                <img class="showImg" src="/Project/images/DefaultAvatar.jpg">
                                                            </c:if>
                                                            <c:if test="${not isOk}">
                                                                <img class="showImg" src="/Project/images/head/${sessionScope.user.image_url}" />
                                                            </c:if>
                                                        </div>
                                                        <span class="pic-tip">60X60像素</span>
                                                    </div>
                                                </div>
                                                <div class="uc-min uc-pic-img">
                                                    <div class="pic-30-box">
                                                        <div class="pic-30 ">
                                                            <c:if test="${empty sessionScope.user.image_url}" var="isOk">
                                                                <img class="showImg" src="/Project/images/DefaultAvatar.jpg">
                                                            </c:if>
                                                            <c:if test="${not isOk}">
                                                                <img class="showImg" src="/Project/images/head/${sessionScope.user.image_url}" />
                                                            </c:if>
                                                        </div>
                                                        <span class="pic-tip">30X30像素</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="upload_btn_box">
                                    <input type="hidden" name="type" value="editUser" />
                                    <input type="submit" class="save-btn" value="保存">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="tang" style="display:none;"></div>
<%@include file="bottom.jsp"%>
<script src="/Project/js/images.js"></script>
<script>
    let error = '${error}';
    if(error){
        // alert(error);
        $(".tang").text(error).show().delay(1000).hide(0);
    }
</script>
</body>
</html>
