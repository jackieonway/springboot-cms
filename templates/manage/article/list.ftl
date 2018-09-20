<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">文章列表</h2>
                <p class="title-description">
                    本系统所有文章都在此作查询、编辑、修改或删除等操作。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/article/add.html" class="btn btn-primary" role="button">写文章</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>关键字</th>
                    <th>摘要</th>
                    <th>文章时间</th>
                    <th>审核</th>
                    <th>状态</th>
                    <th>浏览量</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (articles ??) && (articles?size >0)>
                        <#list articles as article>
                        <tr class="cen">
                            <td class="lt"><a  href="${BASE_PATH!}/adminManager/article/update.html?id=${article.id}">${article.title}</a></td>
                            <td>${article.keywords}</td>
                            <td>${article.summary}</td>
                            <td>${article.gmtArticle?string('yyyy-MM-dd HH:mm')}</td>
                            <td>
                                <#if article.check??>
                                    <select name="check" articleId="${article.id}" class="form-control form-boxed check-status">
                                    <#if article.check == 0>
                                        <option value="0" selected="selected">已审核</option>
                                        <option value="1">未审核</option>
                                        <option value="2">审核中</option>
                                        <option value="3">审核失败</option>
                                    </#if>
                                    <#if article.check == 1>
                                        <option value="0">已审核</option>
                                        <option value="1" selected="selected">未审核</option>
                                        <option value="2">审核中</option>
                                        <option value="3">审核失败</option>
                                    </#if>
                                    <#if article.check == 2>
                                        <option value="0">已审核</option>
                                        <option value="1">未审核</option>
                                        <option value="2" selected="selected">审核中</option>
                                        <option value="3">审核失败</option>
                                    </#if>
                                    <#if article.check == 3>
                                        <option value="0">已审核</option>
                                        <option value="1">未审核</option>
                                        <option value="2">审核中</option>
                                        <option value="3" selected="selected">审核失败</option>
                                    </#if>
                                    </select>
                                </#if>
                            </td>
                            <td>
                                <#if article.status??>
                                    <select name="status" articleId="${article.id}" class="form-control form-boxed update-status">
                                    <#if article.status == 0>
                                        <option value="0" selected="selected">显示</option>
                                        <option value="1">隐藏</option>
                                    </#if>
                                    <#if article.status == 1>
                                        <option value="0">显示</option>
                                        <option value="1" selected="selected">隐藏</option>
                                    </#if>
                                    </select>
                                </#if>
                            </td>
                            <td>${article.viewCount}</td>
                            <td>
                                <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/article/update.html?id=${article.id}">编辑</a>
                                <a title="删除" class="del_article" id=${article.id} >删除</a>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有文章</td>
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
            pageSize : ${pageSize},
            total : ${total},
            pageCount: Math.ceil(${total} / ${pageSize} ),
            current:${pageNum},
            backFn:function(p){
                console.log(p);
            }
        });
        $(function () {
            $('.del_article').click(function () {
                var id = $(this).attr('id');
                layer.confirm("是否确定删除该文章？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/article/deleteArticle',
                        data: 'id='+id,
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/article/list.html";
                            }else {
                                layer.alert(data.errorMessage);
                            }
                        }
                    });
                },null);
            });
            $('.check-status').change(function () {
                var id = $(this).attr('articleId');
                var status = $(this).find("option:selected").val();
                layer.confirm("是否审核该文章？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/article/updateArticleCheckStatus',
                        data: {
                            "id":id,
                            "check":status
                        },
                        success: function (data) {
                            if(data.result){
                                layer.msg('审核成功', {icon: 1});
                                window.location.href="/adminManager/article/list.html";
                            }else {
                                layer.alert(data.errorMessage);
                            }
                        }
                    });
                },null);
            });
            $('.update-status').change(function () {
                var id = $(this).attr('articleId');
                var status = $(this).find("option:selected").val();
                layer.confirm("是否确定更新该文章状态？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/article/updateArticleStatus',
                        data: {
                            "id":id,
                            "status":status
                        },
                        success: function (data) {
                            if(data.result){
                                layer.msg('更新成功', {icon: 1});
                                window.location.href="/adminManager/article/list.html";
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