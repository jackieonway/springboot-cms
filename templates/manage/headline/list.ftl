<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">轮播图列表</h2>
                <p class="title-description">
                    本系统首页的轮播图的上传,发布,修改,删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/headline/add.html" class="btn btn-primary" role="button">新增轮播图</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>图片</th>
                    <th>名称</th>
                    <th>链接</th>
                    <th>ALT标题</th>
                    <th>发布时间</th>
                    <th>发布状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (headlines ??) && (headlines?size >0)>
                        <#list headlines as headline>
                        <tr class="cen">
                            <td>${headline.sort}</td>
                            <td class="lt"><img src="${BASE_PATH!}${headline.picture}" style="height: 150px;width: 150px"/> </td>
                            <td>${headline.name}</td>
                            <td><a href="${headline.url}" >${headline.url}</a></td>
                            <td>${headline.alt}</td>
                            <td>${headline.gmtPublish?string('yyyy-MM-dd HH:mm')}</td>
                            <#if headline.status??>
                                <#if headline.status == 0>
                                <td>未发布</td>
                                </#if>
                                <#if headline.status == 1 >
                                 <td>已发布</td>
                                </#if>
                            </#if>
                            <td>
                                <a title="编辑" class="mr-5" href="${BASE_PATH!}/adminManager/headline/update.html?id=${headline.id}">编辑</a>
                                <a title="删除" class="del_headline" id=${headline.id} >删除</a>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr class="cen">
                            <td colspan="8" align="center">暂时没有轮播图</td>
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
                layer.confirm("是否确定删除该轮播图？", {
                    btn: ['确定','取消'] //按钮
                },function(){
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:'${BASE_PATH!}/adminManager/headline/deleteHeadline',
                        data: 'id='+id,
                        success: function (data) {
                            if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                window.location.href="/adminManager/headline/list.html";
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