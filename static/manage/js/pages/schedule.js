$(function () {
    $('.trigger_job').click(function () {
        var jobName = $(this).attr('jobName');
        var jobGroup = $(this).attr('jobGroup');
        $.ajax({
            type:'POST',
            dataType:'json',
            url:'/adminManager/job/trigger',
            data: {
                "time":new Date().toString(),
                "jobName":jobName,
                "jobGroup":jobGroup
            },
            success: function (data) {
                if(data.result){
                    layer.msg('执行成功', {icon: 1});
                    window.location.href="/adminManager/job/list.html";
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
    });
    $('.remove_job').click(function () {
        var jobName = $(this).attr('jobName');
        var jobGroup = $(this).attr('jobGroup');
        layer.confirm("是否确定删除该权限？", {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:'POST',
                dataType:'json',
                url:'/adminManager/job/remove',
                data: {
                    "time":new Date().toString(),
                    "jobName":jobName,
                    "jobGroup":jobGroup
                },
                success: function (data) {
                    if(data.result){
                        layer.msg('移除成功', {icon: 1});
                        window.location.href="/adminManager/job/list.html";
                    }else {
                        layer.alert(data.errorMessage);
                    }
                }
            });
        },null);
    });
    $('.resume_job').click(function () {
        var jobName = $(this).attr('jobName');
        var jobGroup = $(this).attr('jobGroup');
        $.ajax({
            type:'POST',
            dataType:'json',
            url:'/adminManager/job/resume',
            data: {
                "time":new Date().toString(),
                "jobName":jobName,
                "jobGroup":jobGroup
            },
            success: function (data) {
                if(data.result){
                    layer.msg('恢复成功', {icon: 1});
                    window.location.href="/adminManager/job/list.html";
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
    });
    $('.pause_job').click(function () {
        var jobName = $(this).attr('jobName');
        var jobGroup = $(this).attr('jobGroup');
        $.ajax({
            type:'POST',
            dataType:'json',
            url:'/adminManager/job/pause',
            data: {
                "time":new Date().toString(),
                "jobName":jobName,
                "jobGroup":jobGroup
            },
            success: function (data) {
                if(data.result){
                    layer.msg('停止成功', {icon: 1});
                    window.location.href="/adminManager/job/list.html";
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
    });
    $('#add_job').click(function () {
        layer.tab({
            area: ['600px', '500px'],
            tab: [{
                title: '新增任务',
                content:
                '<div class="panel panel-default">\n' +
                '            <form id="form" enctype="multipart/form-data">\n' +
                '                <div class="panel-bd">\n' +
                '                    <div class="form-group-col-2">\n' +
                '                        <div class="form-label">任务名称：</div>\n' +
                '                        <div class="form-cont">\n' +
                '                            <input type="text" name="jobName" placeholder="任务名称" class="form-control form-boxed">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="form-group-col-2">\n' +
                '                        <div class="form-label">任务组：</div>\n' +
                '                        <div class="form-cont">\n' +
                '                             <input type="text" name="jobGroup" placeholder="任务组" class="form-control form-boxed">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="form-group-col-2">\n' +
                '                        <div class="form-label">描述：</div>\n' +
                '                        <div class="form-cont">\n' +
                '                            <input type="text"  name="description" placeholder="描述" class="form-control form-boxed">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="form-group-col-2">\n' +
                '                        <div class="form-label">执行类：</div>\n' +
                '                        <div class="form-cont">\n' +
                '                            <input type="text"  name="jobClassName" placeholder="执行类" class="form-control form-boxed">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="form-group-col-2">\n' +
                '                        <div class="form-label">执行时间：</div>\n' +
                '                        <div class="form-cont">\n' +
                '                            <input type="text"  name="cronExpression" placeholder="执行时间" class="form-control form-boxed">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="form-group-col-2">\n' +
                '                        <div class="form-label"></div>\n' +
                '                        <div class="form-cont">\n' +
                '                            <button type="button" class="submit btn btn-primary">保存</button>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </form>\n' +
                '        </div>' +
                '<script>' +
                '$(function () {\n' +
                '            $(\'.submit\').click(function () {\n' +
                '                var formData = new FormData($("#form")[0]);\n' +
                '                $.ajax({\n' +
                '                    type: "POST",\n' +
                '                    url: "/adminManager/job/add",\n' +
                '                    data: formData,\n' +
                '                    async: false,\n' +
                '                    cache: false,\n' +
                '                    contentType: false,\n' +
                '                    processData: false,\n' +
                '                    success: function(data) {\n' +
                '                        if (data.result) {\n' +
                '                            layer.confirm("保存成功，将刷新页面", {\n' +
                '                                btn: [\'确定\'] //按钮\n' +
                '                            }, function () {\n' +
                '                                window.location.href = "/adminManager/job/list.html";\n' +
                '                            }, function () {\n' +
                '                                window.location.reload();\n' +
                '                            });\n' +
                '                        } else {\n' +
                '                            layer.alert(data.errorMessage);\n' +
                '                        }\n' +
                '                    }\n' +
                '                });\n' +
                '            });\n' +
                '        });\n' +
                '<\/script>'
            }]
        });
    });
    $('.update_job').click(function () {
        var jobName = $(this).attr('jobName');
        var jobGroup = $(this).attr('jobGroup');
        $.ajax({
            type:'POST',
            dataType:'json',
            url:'/adminManager/job/update',
            data: {
                "time":new Date().toString(),
                "jobName":jobName,
                "jobGroup":jobGroup
            },
            success: function (data) {
                if(data.result){
                    layer.tab({
                        area: ['600px', '500px'],
                        tab: [{
                            title: '编辑任务',
                            content:
                            '<div class="panel panel-default">\n' +
                            '            <form id="form" enctype="multipart/form-data">\n' +
                            '                <input type="hidden" name="oldJobName" value="'+data.t.jobName+'">\n' +
                            '                <input type="hidden" name="oldJobGroup" value="'+data.t.jobGroup+'">\n' +
                            '                <div class="panel-bd">\n' +
                            '                    <div class="form-group-col-2">\n' +
                            '                        <div class="form-label">任务名称：</div>\n' +
                            '                        <div class="form-cont">\n' +
                            '                            <input type="text" name="jobName" placeholder="任务名称" value="'+data.t.jobName+'" class="form-control form-boxed">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <div class="form-group-col-2">\n' +
                            '                        <div class="form-label">任务组：</div>\n' +
                            '                        <div class="form-cont">\n' +
                            '                             <input type="text" name="jobGroup" placeholder="任务组" value="'+data.t.jobGroup+'"  class="form-control form-boxed">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <div class="form-group-col-2">\n' +
                            '                        <div class="form-label">描述：</div>\n' +
                            '                        <div class="form-cont">\n' +
                            '                            <input type="text"  name="description" placeholder="描述" value="'+data.t.description+'"  class="form-control form-boxed">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <div class="form-group-col-2">\n' +
                            '                        <div class="form-label">执行类：</div>\n' +
                            '                        <div class="form-cont">\n' +
                            '                            <input type="text"  name="jobClassName" placeholder="执行类" value="'+data.t.jobClassName+'"  class="form-control form-boxed">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <div class="form-group-col-2">\n' +
                            '                        <div class="form-label">执行时间：</div>\n' +
                            '                        <div class="form-cont">\n' +
                            '                            <input type="text"  name="cronExpression" placeholder="执行时间" value="'+data.t.cronExpression+'"  class="form-control form-boxed">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <div class="form-group-col-2">\n' +
                            '                        <div class="form-label"></div>\n' +
                            '                        <div class="form-cont">\n' +
                            '                            <button type="button" class="submit btn btn-primary">更新</button>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                </div>\n' +
                            '            </form>\n' +
                            '        </div>' +
                            '<script>' +
                            '$(function () {\n' +
                            '            $(\'.submit\').click(function () {\n' +
                            '                var formData = new FormData($("#form")[0]);\n' +
                            '                $.ajax({\n' +
                            '                    type: "POST",\n' +
                            '                    url: "/adminManager/job/add",\n' +
                            '                    data: formData,\n' +
                            '                    async: false,\n' +
                            '                    cache: false,\n' +
                            '                    contentType: false,\n' +
                            '                    processData: false,\n' +
                            '                    success: function(data) {\n' +
                            '                        if (data.result) {\n' +
                            '                            layer.confirm("编辑成功，将刷新页面", {\n' +
                            '                                btn: [\'确定\'] //按钮\n' +
                            '                            }, function () {\n' +
                            '                                window.location.href = "/adminManager/job/list.html";\n' +
                            '                            }, function () {\n' +
                            '                                window.location.reload();\n' +
                            '                            });\n' +
                            '                        } else {\n' +
                            '                            layer.alert(data.errorMessage);\n' +
                            '                        }\n' +
                            '                    }\n' +
                            '                });\n' +
                            '            });\n' +
                            '        });\n' +
                            '<\/script>'
                        }]
                    });
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
    });
});