$(function(){
	$('#entry').click(function(){
		if($('#username').val()==''){
			$('.mask,.dialog').show();
			$('.dialog .dialog-bd p').html('请输入账号');
		}else if($('#password').val()==''){
			$('.mask,.dialog').show();
			$('.dialog .dialog-bd p').html('请输入密码');
		}else if($('#verifyCode').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入验证码');
        }else{
			$('.mask,.dialog').hide();
			$.ajax({
                type: "POST",
                url: "/adminManager/login",
                data: {'username':$('#username').val(),
                    'password':$('#password').val(),
                    'verifyCode':$('#verifyCode').val()},
                success: function(data) {
                    if (data.result) {
                        $('#checkImg').attr('src','/adminManager/checkCode.jpg');
                        window.location.href="/adminManager/index.html";
                    } else {
                        $('#checkImg').attr('src','/adminManager/checkCode.jpg');
                        $('.mask,.dialog').show();
                        $('.dialog .dialog-bd p').html(data.errorMessage);
                    }
                }
			});
		}
	});
	$('#jsCheck').click(function () {
		$('#checkImg').attr('src','/adminManager/checkCode.jpg');
    });
    $('#forget').click(function(){
        if($('#username').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入账号');
        }else if($('#email').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入邮箱');
        }else if($('#verifyCode').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入验证码');
        }else{
            $('.mask,.dialog').hide();
            $.ajax({
                type: "POST",
                url: "/adminManager/forgetInfo",
                data: {'username':$('#username').val(),
                    'email':$('#email').val(),
                    'verifyCode':$('#verifyCode').val()},
                success: function(data) {
                    if (data.result) {
                        $('#checkImg').attr('src','/adminManager/checkCode.jpg');
                        window.location.href="/adminManager/inputPwd.html";
                    } else {
                        $('#checkImg').attr('src','/adminManager/checkCode.jpg');
                        $('.mask,.dialog').show();
                        $('.dialog .dialog-bd p').html(data.errorMessage);
                    }
                }
            });
        }
    });

    $('#inputPwd').click(function(){
        if($('#password').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入密码');
        }else if($('#rePassword').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入确认密码');
        }else if($('#password').val()!=$('#rePassword').val()){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('两次密码不一致');
        }else if($('#verifyCode').val()==''){
            $('.mask,.dialog').show();
            $('.dialog .dialog-bd p').html('请输入邮箱验证码');
        }else{
            $('.mask,.dialog').hide();
            $.ajax({
                type: "POST",
                url: "/adminManager/inputPwdInfo",
                data: {'password':$('#password').val(),
                    'rePassword':$('#rePassword').val(),
                    'verifyCode':$('#verifyCode').val()},
                success: function(data) {
                    if (data.result) {
                        $('.mask,.dialog').show();
                        $('.dialog .dialog-bd p').html(data.message);
                        window.location.href="/adminManager/login.html";
                    } else {
                        $('.mask,.dialog').show();
                        $('.dialog .dialog-bd p').html(data.errorMessage);
                    }
                }
            });
        }
    });
});
