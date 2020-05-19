/**
 * Created by admin on 2020/5/19.
 */
$(document).ready(function () {
    console.log(ctx);
    refresh_kaptcha();
});
//刷新验证码
function refresh_kaptcha() {
    var path = ctx + "kaptcha/kaptcha?p=" + Math.random();
    $("#kaptcha").attr("src", path);
}

function login() {
    showLoading();
    if($("#username").val() == undefined || $("#username").val() == ""){
        $("#username_div").addClass("has-error");
        showFail("请输入用户名");
        return;
    }
    if($("#password").val() == undefined || $("#password").val() == ""){
        $("#password_div").addClass("has-error");
        showFail("请输入密码");
        return;
    }

    if($("#captcha").val() == undefined || $("#captcha").val() == ""){
        $("#captcha_div").addClass("has-error");
        showFail("请输入验证码");
        return;
    }

    $.ajax({
        type: "POST",
        url: ctx+"login",
        data: {
            "username": $("#username").val(),
            "password": $("#password").val(),
            "captcha": $("#captcha").val()
        },
        success: function (r) {
            if (r.status == 0) {
                showSuccess("登录成功");
                parent.location.href = '/index';
            } else {
                console.log(r.msg);
                showFail(r.msg);
            }
        }
    });
}


function onUserNameChange() {
    if($("#username").val() == undefined || $("#username").val() == ""){
        $("#username_div").addClass("has-error");
        return;
    }else{
        $("#username_div").removeClass("has-error");
        return;
    }
}


function onPasswordChange() {
    if($("#password").val() == undefined || $("#password").val() == ""){
        $("#password_div").addClass("has-error");
        return;
    }else{
        $("#password_div").removeClass("has-error");
        return;
    }
}


function onCaptchaChange() {
    if($("#captcha").val() == undefined || $("#captcha").val() == ""){
        $("#captcha_div").addClass("has-error");
        return;
    }else{
        $("#captcha_div").removeClass("has-error");
        return;
    }
}