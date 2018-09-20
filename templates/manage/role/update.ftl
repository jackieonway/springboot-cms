<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">编辑系统用户</h2>
                <p class="title-description">
                    按照所需信息填写完系统用户信息，编辑完成后，点击保存按钮，保存成功后跳转用户列表列表
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <#if role ??>
                    <input type="hidden" name="id" value="${role.id}"/>
                    <div class="panel-bd">
                        <div class="form-group-col-2">
                            <div class="form-label">角色名：</div>
                            <div class="form-cont txt-width">
                                <input type="text" name="name" placeholder="角色名" value="${role.name!}" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">角色类型：</div>
                            <div class="form-cont txt-width">
                                <#if role.type ??>
                                    <select name="type" class="form-control form-boxed">
                                        <#if role.type  == "1">
                                            <option value="1" selected="selected">系统管理员</option>
                                            <option value="2">管理员</option>
                                            <option value="3">用户</option>
                                        </#if>
                                        <#if role.type == "2">
                                            <option value="1" >系统管理员</option>
                                            <option value="2" selected="selected">管理员</option>
                                            <option value="3">用户</option>
                                        </#if>
                                        <#if role.type == "3">
                                            <option value="1" >系统管理员</option>
                                            <option value="2">管理员</option>
                                            <option value="3" selected="selected">用户</option>
                                        </#if>
                                    </select>
                                <#else >
                                    <select name="type" class="form-control form-boxed">
                                        <option value="1" >系统管理员</option>
                                        <option value="2">管理员</option>
                                        <option value="3" selected="selected">用户</option>
                                    </select>
                                </#if>
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">角色编码：</div>
                            <div class="form-cont txt-width">
                                <input type="text"  name="roleCode" placeholder="角色编码" readonly="readonly" value="${role.roleCode!}" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">角色备注：</div>
                            <div class="form-cont txt-width">
                                <textarea id="comment" name="comment" class="form-control form-boxed" >${role.comment!}</textarea>
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
                </#if>
            </form>
        </div>
        <!--开始::结束-->
    </div>
 <script type="text/javascript">
     $(".reset").click(function () {
         window.location.reload();
     });
     $(function(){
         $(".reset").click(function () {
             $(":text").val("");
             $(":password").val("");
             $(":number").val("");
             $(":email").val("");
         });
         $(".submit").click(function () {
             if($("input[name='name']").val()==""){
                 layer.alert("角色名不能为空！");
                 return false;
             }
             if($("input[name='type']").val()==""){
                 layer.alert("角色类型不能为空！");
                 return false;
             }
             if($("input[name='roleCode']").val()==""){
                 layer.alert("角色编码不能为空！");
                 return false;
             }
             var formData = new FormData($("#form")[0]);
             $.ajax({
                 type: "POST",
                 url: "/adminManager/sysRole/update",
                 data: formData,
                 async: false,
                 cache: false,
                 contentType: false,
                 processData: false,
                 success: function(data) {
                     if (data.result) {
                         layer.confirm("更新成功，将刷新页面", {
                             btn: ['确定'] //按钮
                         }, function () {
                             window.location.href = "/adminManager/sysRole/list.html";
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