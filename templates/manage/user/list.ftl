<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">用户列表</h2>
                <p class="title-description">
                    本系统用户的新增、编辑、删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/user/add.html" class="btn btn-primary" role="button">新增用户</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>邮箱</th>
                    <th>联系方式</th>
                    <th>性别</th>
                    <th>地址</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (users ??) && (users?size >0)>
                        <#list users as user>
                        <tr class="cen">
                            <td class="lt">${user.username!} </td>
                            <td>${user.nickName!}</td>
                            <td>${user.email!}</td>
                            <td>${user.phone!}</td>
                            <td>${user.sex!}</td>
                            <td>${user.address!}</td>
                            <td>
                                <#-- 系统超级管理员不能删除,不能修改角色 -->
                                <#if user.username == "adminManager">
                                    <#if "adminManager" == sysuser.username>
                                        <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/user/update.html?id=${user.id}">编辑</a>
                                    <#else >
                                        无权编辑该用户信息
                                    </#if>
                                </#if>
                                <#if user.username != "adminManager">
                                    <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/user/update.html?id=${user.id}">编辑</a>
                                    <#-- 当前登录管理员不能修改自己的角色 -->
                                    <#if sysuser.id != user.id>
                                        <a title="角色" class="mr-5" href="${BASE_PATH!}/adminManager/user/role.html?id=${user.id}">角色</a>
                                    </#if>
                                    <#if user.username != sysuser.username>
                                         <a title="删除" class="del_headline" id=${user.id} >删除</a>
                                    </#if>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有用户</td>
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
            $('.del_headline').click(function () {
                var id = $(this).attr('id');
                layer.confirm("是否确定删除该用户？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/user/delete',
                        data: 'id='+id,
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/user/list.html";
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