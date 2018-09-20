<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">友情链接列表</h2>
                <p class="title-description">
                    本系统的友情链接配置,修改,删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/blogroll/add.html" class="btn btn-primary" role="button">新增友情链接</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>友情链接名</th>
                    <th>友情链接地址</th>
                    <th>友情链接描述</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (blogrolls ??) && (blogrolls?size >0)>
                        <#list blogrolls as blogroll>
                        <tr class="cen">
                            <td>${blogroll.blogrollName}</td>
                            <td>${blogroll.blogrollUrl}</td>
                            <td>${blogroll.description}</td>
                            <td>
                                <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/blogroll/update.html?id=${blogroll.id}">编辑</a>
                                <a title="删除" class="del_blogroll" id=${blogroll.id} >删除</a>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有友情链接</td>
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
            $('.del_blogroll').click(function () {
                var id = $(this).attr('id');
                layer.confirm("是否确定删除该友情链接？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/blogroll/deleteBlogroll',
                        data: 'id='+id,
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/blogroll/list.html";
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