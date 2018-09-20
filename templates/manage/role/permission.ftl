<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">权限列表  ${pName!}</h2>
                <p class="title-description">
                    本系统用户所有能赋予的权限都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>权限名</th>
                    <th>权限类型</th>
                    <th>权限编码</th>
                    <th>描述</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (permissions ??) && (permissions?size >0)>
                        <#list permissions as permission>
                            <tr class="cen">
                                <td class="lt"><a href="/adminManager/sysRole/permission.html?id=${id!}&parentId=${permission.id!}&pName=${permission.name!}" >${permission.name!}</a> </td>
                                <td>
                                        <#if permission.permissionType ??>
                                            <#if permission.permissionType == "0">
                                                目录
                                            </#if>
                                            <#if permission.permissionType == "1">
                                                页面
                                            </#if>
                                            <#if permission.permissionType == "2">
                                                按钮
                                            </#if>
                                        </#if>
                                </td>
                                <td>${permission.permissionCode!}</td>
                                <td>${permission.description!}</td>
                                <td>
                                    <#-- 如果包含有权限id则显示删除权限,否则显示可赋予权限 -->
                                    <#if rolePermissions?index_of(permission.id!) != -1>
                                        <a title="删除权限" class="del_permission" pid=${permission.id!} rid=${id! }>删除权限</a>
                                    <#else >
                                        <a title="赋予权限" class="add_permission" pid=${permission.id!} rid=${id! }>赋予权限</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有可赋予的权限</td>
                        </tr>
                    </#if>
                </tbody>
            </table>
        </div>
        <!--开始::结束-->
    </div>
    <script>
        $(function () {
            $('.add_permission').click(function () {
                var pid = $(this).attr('pid');
                var rid = $(this).attr('rid');
                layer.confirm("是否添加该权限？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/sysRole/addPermission',
                        data: {'pid':pid,"rid":rid},
                        success: function (data) {
                            if(data.result){
                                layer.msg('添加成功', {icon: 1});
                                window.location.href="/adminManager/sysRole/permission.html?id=${id!}&parentId=${parentId!}&pName=${pName!}";
                            }else {
                                layer.alert(data.errorMessage);
                            }
                        }
                    });
                },null);
            });
            $('.del_permission').click(function () {
                var pid = $(this).attr('pid');
                var rid = $(this).attr('rid');
                layer.confirm("是否删除该权限？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/sysRole/delPermission',
                        data: {'pid':pid,"rid":rid},
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/sysRole/permission.html?id=${id!}&parentId=${parentId!}&pName=${pName!}";
                            }else {
                                layer.alert(data.errorMessage);
                            }
                        }
                    });
                },null);
            });
        });
    </script>
<#include "../include/foot.ftl">