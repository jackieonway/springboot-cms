<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">新增系统用户</h2>
                <p class="title-description">
                    按照所需信息填写完系统用户信息，添加完成后，点击保存按钮，保存成功后跳转用户列表列表
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <div class="panel-bd">
                    <div class="form-group-col-2">
                        <div class="form-label">用户名：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="username" placeholder="用户名" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">昵称：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="nickName" placeholder="昵称" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">密码：</div>
                        <div class="form-cont txt-width">
                            <input type="password"  name="password" placeholder="密码" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">性别：</div>
                        <div class="form-cont txt-width">
                            <select name="sex">
                                <option value="保密" selected>保密</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">邮箱：</div>
                        <div class="form-cont txt-width">
                            <input type="email" name="email" placeholder="邮箱" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">联系电话：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="phone" placeholder="联系电话" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">身份证号：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="idcard" placeholder="身份证号" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">地址：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="address" placeholder="地址" class="form-control form-boxed">
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
             if($("input[name='username']").val()==""){
                 layer.alert("用户名不能为空！");
                 return false;
             }
             if($("input[name='password']").val()==""){
                 layer.alert("密码不能为空！");
                 return false;
             }
             if($("input[name='nickName']").val()==""){
                 layer.alert("昵称不能为空！");
                 return false;
             }
             if($("input[name='phone']").val()==""){
                 layer.alert("联系方式不能为空！");
                 return false;
             }
             if($("input[name='email']").val()==""){
                 layer.alert("邮箱不能为空！");
                 return false;
             }
             var formData = new FormData($("#form")[0]);
             $.ajax({
                 type: "POST",
                 url: "/adminManager/user/add",
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
                             window.location.href = "/adminManager/user/list.html";
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