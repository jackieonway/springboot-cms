<#include "../include/head.ftl">
    <script src="${BASE_PATH!}/manage/js/file-upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${BASE_PATH!}/manage/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${BASE_PATH!}/manage/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <link rel="stylesheet" href="${BASE_PATH!}/manage/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">编辑轮播图</h2>
                <p class="title-description">
                    按照格式修改轮播图，修改完成后，点击保存按钮，保存成功后跳转轮播图列表
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" enctype="multipart/form-data">
                <#if headline??>
                    <input type="hidden" name="id" value="${headline.id}">
                    <div class="panel-bd">
                        <div class="form-group-col-2">
                            <div class="form-label">名称：</div>
                            <div class="form-cont txt-width">
                                <input type="text" name="name" placeholder="轮播图名称" value="${headline.name}" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">轮播图：</div>
                            <div class="form-cont txt-width">
                                <input type="file" id="fileUpload" name="file"/>
                                <input id="uploadHidden" type="hidden" value="${headline.picture!}" name="picture">
                                <img id="uploadImg" src="${BASE_PATH!}${headline.picture!}" alt="img04" style="height:120px;">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">链接：</div>
                            <div class="form-cont txt-width">
                                <input type="text" name="url" placeholder="链接" value="${headline.url}" class="form-control form-boxed">
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">ALT标题：</div>
                            <div class="form-cont txt-width">
                                <textarea id="summary" name="alt" class="form-control form-boxed" placeholder="ALT标题"> ${headline.alt}</textarea>
                            </div>
                        </div>
                        <div class="form-group-col-2">
                            <div class="form-label">发布时间：</div>
                            <div class="form-cont txt-width">
                                <div class="input-append date form_datetime">
                                    <input size="16" type="text" readonly id="gmtPublish" name="gmtPublish" value="${headline.gmtPublish?string('yyyy-MM-dd HH:mm')}" class="form-control-half form-boxed">
                                    <span class="add-on"><i class="icon-th"></i></span>
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
                </#if>
            </form>
        </div>
        <!--开始::结束-->
    </div>
 <script type="text/javascript">
     $(function(){
         $("#fileUpload").change(
             function(){
                 $.ajaxFileUpload({
                     url : '/adminManager/fileOperator/upload',//后台请求地址
                     type: 'post',//请求方式  当要提交自定义参数时，这个参数要设置成post
                     secureuri : false,//是否启用安全提交，默认为false。
                     fileElementId : 'fileUpload',// 需要上传的文件域的ID，即<input type="file">的ID。
                     dataType : 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。如果json返回的带pre,这里修改为json即可解决。
                     success : function (data) {//提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                         if(data.result){
                             $('#uploadImg').attr('src',data.t.url);
                             $('#uploadHidden').attr('value',data.t.path);
                         }else {
                             layer.alert(data.errorMessage);
                         }
                     }
                 });
             });
         $(".reset").click(function () {
             window.location.reload();
         });
         $(".submit").click(function () {
             if($("input[name='name']").val()==""){
                 layer.alert("轮播图不能为空！");
                 return false;
             }
             if($("input[name='gmtPublish']").val()==""){
                 layer.alert("文章发布时间不能为空！");
                 return false;
             }
             var formData = new FormData($("#form")[0]);
             $.ajax({
                 type: "POST",
                 url: "/adminManager/headline/updateHeadline",
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
                             window.location.href = "/adminManager/headline/list.html";
                         }, null);
                     } else {
                         layer.alert(data.errorMessage);
                     }
                 }
             });
         });
     });
 </script>
 <script type="text/javascript">
     $(function(){
         $(".form_datetime").datetimepicker({
             format:'yyyy-mm-dd hh:ii',
             language:  'zh-CN',
             weekStart: 1,
             todayBtn:  1,
             todayHighlight: 1,
             startView: 2,
             forceParse: 0,
             keyboardNavigation : true,
             autoclose: true,
             minView: 0
         });
     });
 </script>
<#include "../include/foot.ftl">