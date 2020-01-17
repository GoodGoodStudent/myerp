$(function () {

    //点击换一张刷新验证码
    $(".change").click(function () {
        $("#codeImg").click();
    });

    //点击验证码刷新验证码
    $("#codeImg").click(function () {
        $("#codeImg").attr("src","/code.jpg?id="+ new Date() + Math.floor(Math.random()*24));
    });

    //回车事件
    $("body").bind("keydown",function (event) {
        if (event.keyCode == "13") {
            $("#button").click();
        }
    })
    //form表单的相关判断
    $("#button").bind("click", function (event) {
        //获取账号，密码，验证码和请记住我
        var id = $("#id").val().trim();
        var password = $("#password").val().trim();
        var code =$("#code").val().trim();
        var remeber = $("#remeber").is(':checked');

        if(id==""){//防止空账号、空密码、空验证码提交
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

        $.ajax({//登陆传值（账号，密码，验证码，请记住我）
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
                    $("#error_password").text("权限不足！");
                }else if(data=="codeError"){
                    $("#error_code").text("验证码错误！");
                    $("#codeImg").attr("src","/code.jpg?id="+ new Date() + Math.floor(Math.random()*24));
                }else {
                    alert("请求成功")
                    location.href=data;
                }
            },
            error: function (data) {
                alert("请求失败")
                location.href="404.html";
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