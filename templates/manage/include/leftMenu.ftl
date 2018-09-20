<div class="side-nav">
    <div class="side-logo">
        <div class="logo">
				<span class="logo-ico">
					<i class="i-l-1"></i>
					<i class="i-l-2"></i>
					<i class="i-l-3"></i>
				</span>
            <strong>${siteNameBack!}后台管理系统</strong>
        </div>
    </div>

    <nav class="side-menu content mCustomScrollbar" data-mcs-theme="minimal-dark">
        <h2>
            <a href="${BASE_PATH!}/adminManager/index.html" class="InitialPage"><i class="icon-dashboard"></i>数据概况</a>
        </h2>
        <ul>
            <#if (permissionList??) && (permissionList?size > 0)>
                <#list permissionList as pms>
                    <li>
                        <dl>
                            <dt>
                                <i class="icon-table"></i>${pms["0"].name}<i class="icon-angle-right"></i>
                            </dt>
                            <dd>
                                <a href="${BASE_PATH!}${pms["1"].url}">${pms["1"].name}</a>
                            </dd>
                        </dl>
                    </li>
                </#list>
            </#if>
        </ul>
    </nav>
    <footer class="side-footer">${copyRightBack!}</footer>
</div>