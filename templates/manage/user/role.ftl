<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">系统角色列表</h2>
                <p class="title-description">
                    本系统所有可为用户授予角色列表。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
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
                            <#if userRole ??>
                                <#if userRole.rid == role.id>
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
                                       <td>已授予该角色</td>
                                   </tr>
                                <#else >
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
                                            <#if role.roleCode == "S01">
                                                无权授予该角色
                                            </#if>
                                            <#if role.roleCode != "S01">
                                                <a title="添加角色" class="mr-5 add_role" rid=${role.id} uid=${userId} >添加角色</a>
                                            </#if>
                                        </td>
                                    </tr>
                                </#if>
                            <#else >
                                <tr class="cen">
                                    <td class="lt">${role.name!} </td>
                                    <td>${role.type!}</td>
                                    <td>${role.roleCode!}</td>
                                    <td>${role.comment!}</td>
                                    <td>
                                        <#if role.roleCode == "S01">
                                                无权授予该角色
                                        </#if>
                                        <#if role.roleCode != "S01">
                                            <a title="添加角色" class="mr-5 add_role" rid=${role.id} uid=${userId} >添加角色</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#if>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">您没有权限来为该用户添加角色</td>
                        </tr>
                    </#if>
                </tbody>
            </table>
        </div>
        <!--开始::结束-->
    </div>
    <script>
        $(function () {
            $('.add_role').click(function () {
                var rid = $(this).attr('rid');
                var uid = $(this).attr('uid');
                layer.confirm("是否确定添加该角色？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/user/addRole',
                        data: {
                            rid:rid,
                            uid:uid

                        },
                        success: function (data) {
                            if(data.result){
                                layer.msg('添加成功', {icon: 1});
                                <#-- 刷新当前页面 -->
                                window.location.reload();
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