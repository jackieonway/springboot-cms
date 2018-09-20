
$(function(){
	'use strict';
	//左侧导航菜单效果
	$('.side-menu li dt').click(function(){
		$(this).parents('li').addClass('open');
		$(this).parents('.side-menu').find('.InitialPage').removeClass('active');
		$(this).parents('li').siblings().removeClass('open');
		
	});
	$('.side-menu dd a').click(function(){
		$(this).parents('li').addClass('open');
		$(this).parents('li').siblings().removeClass('open')
	});
	$('.side-menu li').each(function(){
		//判断链接相当（当前页面导航样式）
		if($(this).find('a').attr('href') == window.location.pathname){
			$(this).addClass('open');
			$(this).siblings().find('.InitialPage').removeClass('active');
			$('.InitialPage').removeClass('active');
		}else if($('.side-menu h2 a').attr('href') == window.location.pathname){
			$('.InitialPage').addClass('active');
		}   
	});
    //Tab选项卡.
    $('.tab-nav li').click(function(){
    	var liIndex = $('.tab-nav li').index(this);
    	$(this).addClass('active').siblings().removeClass('active');
    	$('.tab-cont').eq(liIndex).fadeIn().siblings('.tab-cont').hide();
    });
    //Button下拉菜单
    $('.btn-drop-group .btn').click(function(e){
    	$(this).siblings('.drop-list').show();
    	$(this).parent().siblings().find('.drop-list').hide();
    	$(document).one('click', function(){
	        $('.btn-drop-group .drop-list').hide();
	    });
	    e.stopPropagation();
    });
    	//点击list将当前值复制于button
	    $('.btn-drop-group .drop-list li').click(function(){
	    	$(this).parent().parent().hide();
	    });
	//左侧菜单收缩
	$('.top-hd .hd-lt').click(function(){
		$('.side-nav').toggleClass('hide');
		$('.top-hd,.main-cont,.btm-ft').toggleClass('switchMenu');
		$('.top-hd .hd-lt a').toggleClass('icon-exchange');
		localStorage.setItem('setLayOut1','hide');
		localStorage.setItem('setLayOut2','switchMenu');
		localStorage.setItem('setLayOut3','icon-exchange');
		if(!$('.side-nav').hasClass('hide')){
			localStorage.removeItem('setLayOut1');
			localStorage.removeItem('setLayOut2');
			localStorage.removeItem('setLayOut3');
		}
	});
	$('.side-nav').addClass(localStorage.getItem('setLayOut1'));
	$('.top-hd,.main-cont,.btm-ft').addClass(localStorage.getItem('setLayOut2'));
	$('.top-hd .hd-lt a').addClass(localStorage.getItem('setLayOut3'));
	

	//弹出层基础效果（确认/取消/关闭）
	$('.JyesBtn').click(function(){
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display','block')){
			$('.mask').hide();
		}
	});
	$('.JnoBtn,.JclosePanel').click(function(){
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display','block')){
			$('.mask').hide();
		}
	});
	//基础弹出窗
	$('.JopenPanel').click(function(){
		$('.dialog').show();
		$('.dialog').css('box-shadow','0 0 30px #d2d2d2');
	});
	//带遮罩
	$('.JopenMaskPanel').click(function(){
		$('.dialog,.mask').show();
		$('.dialog').css('box-shadow','none');
	});
	$('.mask').click(function(){
		$(this).hide();
		$('.dialog').hide();
	});
	//自动关闭消息窗口
	$('.Jmsg').click(function(){
		$('dialog').show().delay(5000).hide(0);
	});	
	//安全退出
	$('#JsSignOut').click(function(){
		layer.confirm('确定登出管理系统？', {
		  title:'系统提示',
		  btn: ['确定','取消']
		}, function(){
			$.ajax({
                type:'POST',
                dataType:'json',
                url:"/adminManager/logout",
                success: function (data) {
                    if(data.result){
                        location.href = '/adminManager/login.html';
                    }else {
                        layer.alert(data.errorMessage);
                    }
                }
			})
		});
	});
});
