<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">权限列表   ${pname!}</h2>
                <p class="title-description">
                    本系统用户权限的新增、编辑、删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/sysPermission/add.html?parentId=${parentId!}&pname=${pname!}" class="btn btn-primary" role="button">新增权限</a></div>
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
                            <td class="lt"><a href="/adminManager/sysPermission/list.html?parentId=${permission.id!}&pname=${permission.name!}" >${permission.name!}</a> </td>
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
                                <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/sysPermission/update.html?permissionCode=${permission.permissionCode!}&pname=${permission.name!}">编辑</a>
                                <a title="删除" class="del_permission" permissionCode=${permission.permissionCode!} >删除</a>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有权限</td>
                        </tr>
                    </#if>
                </tbody>
            </table>
        </div>
        <input type="hidden" name="total" value="${total!}">
        <input type="hidden" name="pageNum" value="${pageNum!}">
        <input type="hidden" name="pageSize" value="${pageSize!}">
        <div class="panel panel-default">
            <div class="panel-bd">
                <div class="pagination"></div>
            </div>
        </div>
        <!--开始::结束-->
    </div>
    <script>
        $(".pagination").createPage({
            pageSize : ${pageSize!""},
            total : ${total!""},
            pageCount: Math.ceil(${total!""} / ${pageSize!""} ),
            current:${pageNum!""},
            backFn:function(p){
                console.log(p);
            }
        });
        $(function () {
            $('.del_permission').click(function () {
                var permissionCode = $(this).attr('permissionCode');
                layer.confirm("是否确定删除该权限？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/sysPermission/delete',
                        data: 'permissionCode='+permissionCode,
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/sysPermission/list.html?parentId=${parentId!}&pname=${pname!}";
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