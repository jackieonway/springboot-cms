<#include "../include/head.ftl">
    <script src="${BASE_PATH!}/manage/js/pages/schedule.js"></script>
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">任务管理</h2>
                <p class="title-description">
                    本系统定时任务的新增、编辑、暂停、恢复等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="javascript:void(0)" id="add_job" class="btn btn-primary" role="button">新增任务</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>任务名称</th>
                    <th>任务组</th>
                    <th>描述</th>
                    <th>执行类</th>
                    <th>执行时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if (jobs ??) && (jobs?size >0)>
                        <#list jobs as job>
                        <tr class="cen">
                            <td class="lt">${job.jobName!}</td>
                            <td>${job.jobGroup !}</td>
                            <td>${job.description!}</td>
                            <td>${job.jobClassName!}</td>
                            <td>${job.cronExpression!}</td>
                            <td>
                                <a title="执行" class="trigger_job"  jobName="${job.jobName!}" jobGroup="${job.jobGroup!}" >执行</a>
                                <a title="编辑" class="update_job"  jobName="${job.jobName!}" jobGroup="${job.jobGroup!}" >编辑</a>
                                <a title="移除" class="remove_job"  jobName="${job.jobName!}" jobGroup="${job.jobGroup!}" >移除</a>
                                <#if job.triggerState == "PAUSED">
                                    <a title="恢复" class="resume_job" jobName="${job.jobName!}" jobGroup="${job.jobGroup!}" >恢复</a>
                                <#else >
                                    <a title="停止" class="pause_job"  jobName="${job.jobName!}" jobGroup="${job.jobGroup!}" >停止</a>
                                </#if>
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
    </script>
<#include "../include/foot.ftl">