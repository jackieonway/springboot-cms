		<header class="top-hd">
			<div class="hd-lt">
				<a class="icon-reorder"></a>
			</div>
			<div class="hd-rt">
				<ul>
					<li>
						<a href="${BASE_PATH!}/" target="_blank"><i class="icon-home"></i>前台访问</a>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i>管理员:<em>${sysuser.nickName}</em></a>
						<ul class="dropdown-menu">
                            <li>
                                <a href="/adminManager/user/updateUser.html?id=${sysuser.id}"><i class="icon-bell-alt"></i>编辑个人信息</a>
                            </li>
                            <li>
                                <a href="/adminManager/user/updatePassword.html?id=${sysuser.id}"><i class="icon-bell-alt"></i>修改密码</a>
                            </li>
						</ul>
					</li>
					<#--<li>
						<a><i class="icon-bell-alt"></i>系统消息</a>
					</li>-->
					<li>
						<a href="javascript:void(0)" id="JsSignOut"><i class="icon-signout"></i>安全退出</a>
					</li>
				</ul>
			</div>
		</header>