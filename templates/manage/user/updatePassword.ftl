<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">修改面</h2>
                <p class="title-description">
                   编辑完成后，点击修改按钮，修改成功后跳转管理系统首页
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <#if user ??>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <div class="panel-bd">
                        <div class="form-group-col-2">
                            <div class="form-label">验证码：</div>
                            <div class="form-cont txt-width">
                                <input type="text" style="float: left;margin-right: 7px;width: 200px;" name="code" placeholder="验证码" class="form-control form-boxed">
                                <input type="button" id="js_email_code" style="width: 150px;" onclick="sendemail()" value="获取验证码" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">密码：</div>
                            <div class="form-cont txt-width">
                                <input type="password"  name="password" placeholder="密码" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">确认密码：</div>
                            <div class="form-cont txt-width">
                                <input type="password"  name="repeatPassword" placeholder="确认密码" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label"></div>
                            <div class="form-cont txt-width">
                                <button type="button" class="submit btn btn-primary">修改</button>
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
    var countdown=60;
    function sendemail(){
        var obj = $("#js_email_code");
        settime(obj);
        $.ajax({
            type: "POST",
            url: "/adminManager/user/sendEmail",
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data) {
                if (data.result) {
                    layer.alert(data.message);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
    }
    function settime(obj) { //发送验证码倒计时
        if (countdown == 0) {
            obj.attr('disabled',false);
            //obj.removeattr("disabled");
            obj.val("获取验证码");
            countdown = 60;
            return;
        } else {
            obj.attr('disabled',true);
            obj.val("重新发送(" + countdown + "s)");
            countdown--;
        }
        setTimeout(function() {
                    settime(obj) }
                ,1000)
    }
</script>
 <script type="text/javascript">
     $(function(){
         $(".reset").click(function () {
             window.location.reload();
         });
         $(".submit").click(function () {
             if($("input[name='password']").val()==""){
                 layer.alert("密码不能为空！");
                 return false;
             }
             if($("input[name='repeatPassword']").val()==""){
                 layer.alert("确认密码不能为空！");
                 return false;
             }
             if($("input[name='password']").val()!=$("input[name='repeatPassword']").val()){
                 layer.alert("两次密码不一致！");
                 return false;
             }
             var formData = new FormData($("#form")[0]);
             $.ajax({
                 type: "POST",
                 url: "/adminManager/user/updatePassword",
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
                             window.location.href = "/adminManager/index.html";
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