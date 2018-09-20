<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">编辑系统权限   ${pname!}</h2>
                <p class="title-description">
                    按照所需信息填写完系统权限信息，编辑完成后，点击保存按钮，保存成功后跳转权限列表
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <#if permission ??>
                    <input type="hidden" name="id" value="${permission.id}"/>
                    <div class="panel-bd">
                        <div class="form-group-col-2">
                            <div class="form-label">权限名：</div>
                            <div class="form-cont txt-width">
                                <input type="text" name="name" value="${permission.name!}" placeholder="权限名" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">权限类型：</div>
                            <div class="form-cont txt-width">
                                <#if permission.permissionType ??>
                                    <select name="permissionType" class="form-control form-boxed">
                                        <#if permission.permissionType  == "0">
                                            <option value="0" selected="selected">目录</option>
                                            <option value="1">页面</option>
                                            <option value="2">按钮</option>
                                        </#if>
                                        <#if permission.permissionType == "1">
                                            <option value="0">目录</option>
                                            <option value="1" selected="selected">页面</option>
                                            <option value="2">按钮</option>
                                        </#if>
                                        <#if permission.permissionType == "2">
                                            <option value="0">目录</option>
                                            <option value="1">页面</option>
                                            <option value="2" selected="selected">按钮</option>
                                        </#if>
                                    </select>
                                <#else >
                                    <select name="permissionType" class="form-control form-boxed">
                                        <option value="0" selected="selected">目录</option>
                                        <option value="1">页面</option>
                                        <option value="2">按钮</option>
                                    </select>
                                </#if>
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">权限编码：</div>
                            <div class="form-cont txt-width">
                                <input type="text" name="permissionCode" value="${permission.permissionCode!}" placeholder="权限编码" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">链接地址：</div>
                            <div class="form-cont txt-width">
                                <input type="text"  name="url" value="${permission.url!}" placeholder="链接地址: 若该权限有链接地址则填写,否则为空" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">描述：</div>
                            <div class="form-cont txt-width">
                                <input type="text" name="description" value="${permission.description!}" placeholder="描述" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label"></div>
                            <div class="form-cont txt-width">
                                <button type="button" class="submit btn btn-primary">更新</button>
                                <button type="button" class="btn btn-disabled reset">重置</button>
                            </div>
                        </div>
                    </div>
                </#if>
            </form>
        </div>
        <!--开始::结束-->
    </div>
 <script type="text/javascript">
     $(function(){
         $(".reset").click(function () {
             window.location.reload();
         });
         $(".submit").click(function () {
             if($("input[name='name']").val()==""){
                 layer.alert("权限名不能为空！");
                 return false;
             }
             if($("input[name='permissionType']").val()==""){
                 layer.alert("权限类型不能为空！");
                 return false;
             }
             if($("input[name='perimissionCode']").val()==""){
                 layer.alert("权限编码不能为空！");
                 return false;
             }
             var formData = new FormData($("#form")[0]);
             $.ajax({
                 type: "POST",
                 url: "/adminManager/sysPermission/update",
                 data: formData,
                 async: false,
                 cache: false,
                 contentType: false,
                 processData: false,
                 success: function(data) {
                     if (data.result) {
                         layer.confirm("保存成功，将刷新页面", {
                             btn: ['确定'] //按钮
                         }, function () {
                             window.location.href = "/adminManager/sysPermission/list.html?parentId=${permission.parentId!}";
                         }, null);
                     } else {
                         layer.alert(data.errorMessage);
                     }
                 }
             });
         });
     });
 </script>
<#include "../include/foot.ftl">