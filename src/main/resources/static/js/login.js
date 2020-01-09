$(function () {

    $("#codeImg").click(function () {
        $("#codeImg").attr("src","/code.jpg"+ new Date() + Math.floor(Math.random()*24));
    });

    $(".change").click(function () {
        $("#codeImg").attr("src","/code.jpg"+ new Date() + Math.floor(Math.random()*24));
    });

    //此方法防止空账号、空密码、空验证码提交
    $("#button").bind("click", function (event) {
        var id = $("#id").val().trim();
        var password = $("#password").val().trim();
        var code =$("#code").val().trim();
        if(id==""){
            $("#error_id").text("账号不能为空！");
            return false;
        }
        if(password==""){
            $("#error_password").text("密码不能为空！");
            return false;
        }
        if(code==""){
            $("#error_code").text("验证码不能为空！");
            return false;
        }
    })

    $("#button").click(function () {
        var id = $("#id").val().trim();
        var password = $("#password").val().trim();
        var code = $("#code").val().trim();
        var remeber = $("#remeber").is(':checked');
        alert(remeber);
        $.ajax({
            url: "/login?id="+id+"&password="+password+"&code="+code+"&remeber="+remeber,
            type: "post",
            dataType: "text",
            async: true,
            success: function (data) {
                if(data=="accountError"){
                    $("#error_id").text("账号不存在！");
                }else if(data=="passwordError"){
                    $("#error_password").text("密码输入有误！");
                }else if(data=="accountOrPasswordError"){
                    $("#error_password").text("账号密码输入有误！");
                }else if(data=="powerError"){
                    $("#error_code").text("验证码不能为空！");
                }else {
                    window.location.href=data;
                }
            },
            error: function (data) {
                window.location.href="404.html";
            }
        })
    })
    //账号框失焦判断
    $("#id").blur(function () {
        var id = $("#id").val().trim();
        if(id==""){
            $("#error_id").text("账号不能为空！");
        }else{
            $("#error_id").text("");
        }
    })
    //密码框失焦判断
    $("#password").blur(function () {
        var password = $("#password").val().trim();
        if(password==""){
            $("#error_password").text("密码不能为空！");
        }else{
            $("#error_password").text("");
        }
    })
    //验证码框失焦判断
    $("#code").blur(function () {
        var code = $("#code").val().trim();
        if(code==""){
            $("#error_code").text("验证码不能为空！");
        }else{
            $("#error_code").text("");
        }
    })
})