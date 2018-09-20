<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">新增友情链接</h2>
                <p class="title-description">
                    按照格式填写友情链接信息，添加完成后，点击保存按钮，保存成功后跳转友情链接信息列表
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${blogroll.id}"/>
                <div class="panel-bd">
                    <div class="form-group-col-2">
                        <div class="form-label">友情链接名：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="blogrollName" value="${blogroll.blogrollName}" placeholder="友情链接名" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">友情链接地址：</div>
                        <div class="form-cont txt-width">
                            <input type="text"  name="blogrollUrl" value="${blogroll.blogrollUrl}" placeholder="友情链接地址" class="form-control form-boxed">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">友情链接描述：</div>
                        <div class="form-cont txt-width">
                            <input type="text" name="description" value="${blogroll.description}" placeholder="友情链接描述" class="form-control form-boxed">
                        </div>
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
        </form>
    </div>
        <!--开始::结束-->
    </div>
 <script type="text/javascript">
     $(function(){
         $(".reset").click(function () {
             $(":text").val("");
         });
         $(".submit").click(function () {
             if($("input[name='blogrollName']").val()==""){
                 layer.alert("友情链接名不能为空！");
                 return false;
             }
             if($("input[name='blogrollUrl']").val()==""){
                 layer.alert("友情链接地址不能为空！");
                 return false;
             }
             var formData = new FormData($("#form")[0]);
             $.ajax({
                 type: "POST",
                 url: "/adminManager/blogroll/updateBlogroll",
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
                             window.location.href = "/adminManager/blogroll/list.html";
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