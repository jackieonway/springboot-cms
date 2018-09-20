<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">角色列表</h2>
                <p class="title-description">
                    本系统所有角色的新增、编辑、删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/sysRole/add.html" class="btn btn-primary" role="button">新增角色</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>角色名</th>
                    <th>角色类型</th>
                    <th>角色编码</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (roles ??) && (roles?size >0)>
                        <#list roles as role>
                        <tr class="cen">
                            <td class="lt">${role.name!} </td>
                            <td>
                                <#if role.type ??>
                                    <#if role.type == "0">
                                        超级管理员
                                    </#if>
                                    <#if role.type == "1">
                                        系统管理员
                                    </#if>
                                    <#if role.type == "2">
                                        管理员
                                    </#if>
                                    <#if role.type == "3">
                                        用户
                                    </#if>
                                </#if>
                            </td>
                            <td>${role.roleCode!}</td>
                            <td>${role.comment!}</td>
                            <td>
                                <#if role.roleCode != "S01">
                                    <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/sysRole/update.html?roleCode=${role.roleCode}">编辑</a>
                                    <a title="权限" class="mr-5" href="${BASE_PATH!}/adminManager/sysRole/permission.html?id=${role.id}">权限</a>
                                    <a title="删除" class="del_role" roleCode=${role.roleCode} >删除</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有角色</td>
                        </tr>
                    </#if>
                </tbody>
            </table>
        </div>
        <input type="hidden" name="total" value="${total}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="pageSize" value="${pageSize}">
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
            $('.del_role').click(function () {
                var roleCode = $(this).attr('roleCode');
                layer.confirm("是否确定删除该角色？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/sysRole/delete',
                        data: 'roleCode='+roleCode,
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/sysRole/list.html";
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