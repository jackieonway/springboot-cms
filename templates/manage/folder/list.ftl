<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">目录管理</h2>
                <p class="title-description">
                    管理属于您的目录。
                </p>
            </header>
            <hr>
        </section>
        <ul class="flex flex-wrap flex-col-2">
            <#if updateFolder??>
                <li class="box-child">
                    <form id="updateform" action="/adminManager/folder/update" method="post">
                        <div class="panel panel-primary mr-10">
                            <input type="hidden" name="id" value="${updateFolder.id}"/>
                            <div class="panel-hd">修改目录</div>
                            <div class="panel-bd">
                                <div class="form-group-col-2">
                                    <div class="form-label">目录名：</div>
                                    <div class="form-cont">
                                        <input type="text" name="name" placeholder="目录名..." class="form-control form-boxed" value="${updateFolder.name}">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">英文名：</div>
                                    <div class="form-cont">
                                        <input type="text" name="ename" placeholder="英文名..." class="form-control form-boxed" value="${updateFolder.ename}">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">所属目录：</div>
                                    <div class="form-cont">
                                        <select name="fatherId" class="form-control form-boxed">
                                            <#if folders??>
                                                <#if folderId??>
                                                    <#list folders as folder>
                                                        <#if folderId == folder.id>
                                                            <option selected="selected" value="${folder.id}">${folder.name}</option>
                                                        </#if>
                                                    </#list>
                                                <#else>
                                                    <option value="0">根目录</option>
                                                </#if>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">标题：</div>
                                    <div class="form-cont">
                                        <input type="text" name="title" placeholder="标题..." class="form-control form-boxed" value="${updateFolder.title}">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">关键字：</div>
                                    <div class="form-cont">
                                        <input type="text" name="keywords" placeholder="关键字..." class="form-control form-boxed" value="${updateFolder.keywords}">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">描述：</div>
                                    <div class="form-cont">
                                        <textarea class="form-control form-boxed" name="description" placeholder="简要描述目录的情况...">${updateFolder.description}</textarea>
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label"></div>
                                    <div class="form-cont">
                                        <button id="folderupdate" type="button" class="btn btn-primary" >更新</button>
                                        <input type="reset" class="btn btn-disabled" value="重置" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </li>
                <script type="text/javascript">
                    $(function () {
                        $('#folderupdate').click(function () {
                            $.ajax({
                                type:'POST',
                                dataType:'json',
                                url:$('#updateform').attr("action"),
                                async: false,
                                cache: false,
                                contentType: false,
                                processData: false,
                                data:new FormData($('#updateform')[0]),
                                success: function (data) {
                                    if(data.result){
                                        layer.confirm("更新成功，将刷新页面", {
                                            btn: ['确定'] //按钮
                                        },function(){
                                            <#if id??>
                                                window.location.href="/adminManager/folder/list.html?id=${id}";
                                            <#else >
                                                 window.location.href="/adminManager/folder/list.html";
                                            </#if>
                                        },null);
                                    }else {
                                        layer.alert(data.errorMessage);
                                    }
                                }
                            });
                        });
                    });
                </script>
            <#else >
                <li class="box-child">
                    <form id="addform" action="/adminManager/folder/add" method="post">
                        <div class="panel panel-primary mr-10">
                            <div class="panel-hd">新增目录</div>
                            <div class="panel-bd">
                                <div class="form-group-col-2">
                                    <div class="form-label">目录名：</div>
                                    <div class="form-cont">
                                        <input type="text" name="name" placeholder="目录名..." class="form-control form-boxed">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">英文名：</div>
                                    <div class="form-cont">
                                        <input type="text" name="ename" placeholder="英文名..." class="form-control form-boxed">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">所属目录：</div>
                                    <div class="form-cont">
                                        <select name="fatherId" class="form-control form-boxed">
                                            <option value="0">根目录</option>
                                            <#if folders??>
                                                <#list folders as folder>
                                                    <#if parentId??>
                                                        <#if parentId == folder.id>
                                                            <option selected="selected" value="${folder.id}">${folder.name}</option>
                                                        <#else>
                                                            <option value="${folder.id}">${folder.name}</option>
                                                        </#if>
                                                    <#else>
                                                        <option value="${folder.id}">${folder.name}</option>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">标题：</div>
                                    <div class="form-cont">
                                        <input type="text" name="title" placeholder="标题..." class="form-control form-boxed">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">关键字：</div>
                                    <div class="form-cont">
                                        <input type="text" name="keywords" placeholder="关键字..." class="form-control form-boxed">
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label">描述：</div>
                                    <div class="form-cont">
                                        <textarea class="form-control form-boxed" name="description" placeholder="简要描述目录的情况..."></textarea>
                                    </div>
                                </div>
                                <div class="form-group-col-2">
                                    <div class="form-label"></div>
                                    <div class="form-cont">
                                        <button id="folderadd" type="button" class="btn btn-primary" >保存</button>
                                        <input type="reset" class="btn btn-disabled" value="重置" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </li>
                <script type="text/javascript">
                    $(function () {
                        $('#folderadd').click(function () {
                            $.ajax({
                                type:'POST',
                                dataType:'json',
                                url:$('#addform').attr("action"),
                                async: false,
                                cache: false,
                                contentType: false,
                                processData: false,
                                data:new FormData($('#addform')[0]),
                                success: function (data) {
                                    if(data.result){
                                        layer.confirm("保存成功，将刷新页面", {
                                            btn: ['确定'] //按钮
                                        },function(){
                                            <#if id??>
                                                window.location.href="/adminManager/folder/list.html?id=${id}";
                                            <#else >
                                                 window.location.href="/adminManager/folder/list.html";
                                            </#if>
                                        },null);
                                    }else {
                                        layer.alert(data.errorMessage);
                                    }
                                }
                            });
                        });
                    });
                </script>
            </#if>
            <li class="box-child">
                <div class="panel panel-primary">
                    <div class="panel-hd">目录列表</div>
                    <div class="panel-bd">
                        <table class="table table-bordered table-striped mb-15">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>目录名称</th>
                                <th>英文名</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if folderAll??>
                                <#list folderAll as f>
                                    <#if f.level == 1>
                                        <tr class="cen">
                                            <td><input type="number" min="-1" max="128" step="1" name="sort"
                                                       class="form-control sort form-boxed input-sort js_folder_sort"
                                                       folderId="${f.id}"
                                                       value="${f.sort}"/></td>
                                            <td class="lt"><a href="/adminManager/folder/list.html?id=${f.id}">${f.name}</a></td>
                                            <td class="lt">${f.ename}</td>
                                            <td>
                                                <#if f.status??>
                                                    <select name="status" folderId="${f.id}" class="form-control form-boxed update-status">
                                                        <#if f.status == 0>
                                                            <option value="0" selected="selected">显示</option>
                                                            <option value="1">隐藏</option>
                                                        </#if>
                                                        <#if f.status == 1>
                                                            <option value="0">显示</option>
                                                            <option value="1" selected="selected">隐藏</option>
                                                        </#if>
                                                    </select>
                                                </#if>
                                            </td>
                                            <td>
                                                <a title="编辑" href="/adminManager/folder/list.html?id=${f.id}&type=update" class="mr-5">编辑</a>
                                                <#if f.id != 1>
                                                    <a title="删除" class="del_folder" folderid="${f.id}" href="javascript:void(0);">删除</a>
                                                </#if>
                                            </td>
                                        </tr>
                                    <#elseif f.level == 2>
                                        <tr class="cen">
                                            <td><input type="number" min="-1" max="128" step="1" name="sort"
                                                       class="form-control sort form-boxed input-sort js_folder_sort"
                                                       folderId="${f.id}"
                                                       value="${f.sort}"/></td>
                                            <td class="lt">${f.name}</td>
                                            <td class="lt">${f.ename}</td>
                                            <td>
                                                <#if f.status??>
                                                    <select name="status" folderId="${f.id}" class="form-control form-boxed update-status">
                                                        <#if f.status == 0>
                                                            <option value="0" selected="selected">显示</option>
                                                            <option value="1">隐藏</option>
                                                        </#if>
                                                        <#if f.status == 1>
                                                            <option value="0">显示</option>
                                                            <option value="1" selected="selected">隐藏</option>
                                                        </#if>
                                                    </select>
                                                </#if>
                                            </td>
                                            <td>
                                                <a title="编辑" href="/adminManager/folder/list.html?id=${f.id}&type=update" class="mr-5">编辑</a>
                                                <a title="删除" class="del_folder" folderid="${f.id}" href="javascript:void(0);">删除</a>
                                            </td>
                                        </tr>
                                    </#if>

                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <button class="btn btn-info js_update_sort" type="button">
                            <i class="icon-refresh"></i> 更新排序
                        </button>
                    </div>
                </div>
            </li>
        </ul>
        <!--开始::结束-->
    </div>
<script type="text/javascript">
    $(function () {
        $('.js_update_sort').click(function () {
            var folderSort = new Array();
            var flag = true;
            $('.js_folder_sort').each(function(i, element) {
                var folder = {};
                folder.folderId = $(element).attr('folderId');
                folder.sort = $(element).val();
                if($(element).val() == ""){
                    layer.alert("序号不能为空");
                    flag = false;
                }
                if($(element).val() > 128){
                    layer.alert("序号不能大于128");
                    flag = false;
                }
                folderSort.push(folder);
            });
            if(flag){
                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:'${BASE_PATH!}/adminManager/folder/sort',
                    data: 'sort='+JSON.stringify(folderSort),
                    success: function (data) {
                        if(data.result){
                            layer.msg('更新成功', {icon: 1});
                            <#if id??>
                                window.location.href="/adminManager/folder/list.html?id=${id}";
                            <#else >
                                window.location.href="/adminManager/folder/list.html";
                            </#if>

                        }else {
                            layer.alert(data.errorMessage);
                        }
                    }
                });
            }else {
                return false;
            }

        });
        
        $('.del_folder').click(function () {
            var folderId = $(this).attr('folderid');
            layer.confirm("是否确定删除该目录？", {
                btn: ['确定','取消'] //按钮
            },function(){
                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:'${BASE_PATH!}/adminManager/folder/delete',
                    data: 'id='+folderId,
                    success: function (data) {
                        if(data.result){
                                layer.msg('删除成功', {icon: 1});
                                 <#if id??>
                                    window.location.href="/adminManager/folder/list.html?id=${id}";
                                 <#else >
                                    window.location.href="/adminManager/folder/list.html";
                                 </#if>

                        }else {
                            layer.alert(data.errorMessage);
                        }
                    }
                });
            },null);
        });
        $('.update-status').change(function () {
            var folderId = $(this).attr('folderId');
            var status = $(this).find("option:selected").val();
            layer.confirm("是否确定更新该目录状态？", {
                btn: ['确定','取消'] //按钮
            },function(){
                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:'${BASE_PATH!}/adminManager/folder/updateFolderStatus',
                    data: {
                        "id":folderId,
                        "status":status
                    },
                    success: function (data) {
                        if(data.result){
                            layer.msg('更新成功', {icon: 1});
                            window.location.href="/adminManager/folder/list.html";
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