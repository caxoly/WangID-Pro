$(function(){
    $("#outImages").click(function () {
        $("#upload").click();
    })

    $(document).on("change","#upload",function(){
        $.ajaxFileUpload({
            url:"/Project/upload",
            dataType:"json",
            type:"POST",
            fileElementId:"upload",
            secureuri:false,
            success:function (data) {
                console.log(data);
                if(data.code==200){
                    $(".showImg").attr("src","/Project/images/head/"+data.info);
                    $("[name='user_url']").val(data.info);
                }
            }
        })
    })
})