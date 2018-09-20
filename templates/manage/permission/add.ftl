<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">新增系统权限   ${pname!}</h2>
                <p class="title-description">
                    按照所需信息填写完系统权限信息，添加完成后，点击保存按钮，保存成功后跳转用户列表列表
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <input type="hidden" name="parentId" value="${parentId !}">
                <div class="panel-bd">
                    <div class="form-group-col-2">
                        <div class="form-label">权限名：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="name" placeholder="权限名" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">权限类型：</div>
                        <div class="form-cont txt-width">
                            <select name="permissionType" class="form-control form-boxed">
                                <option value="0" selected="selected">目录</option>
                                <option value="1">页面</option>
                                <option value="2">按钮</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">权限编码：</div>
                        <div class="form-cont txt-width">
                            <input type="text"  name="permissionCode" placeholder="权限编码" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">链接地址：</div>
                        <div class="form-cont txt-width">
                            <input type="text"  name="url" placeholder="链接地址: 若该权限有链接地址则填写,否则为空" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">描述：</div>
                        <div class="form-cont txt-width">
                            <input type="text"  name="description" placeholder="描述" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label"></div>
                        <div class="form-cont txt-width">
                            <button type="button" class="submit btn btn-primary">保存</button>
                            <button type="button" class="btn btn-disabled reset">重置</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!--开始::结束-->
    </div>
 <script type="text/javascript">
     $(function(){
         $(".reset").click(function () {
             $(":text").val("");
             $(":password").val("");
             $(":number").val("");
             $(":email").val("");
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
                 url: "/adminManager/sysPermission/add",
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
                             window.location.href = "/adminManager/sysPermission/list.html?parentId=${parentId!}";
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