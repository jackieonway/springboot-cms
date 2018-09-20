$(function(){
    $("#logoUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'logoUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#logoImg').attr('src',data.t.url);
                    $('#logoHidden').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $("#logoBackUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'logoBackUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#logoBackImg').attr('src',data.t.url);
                    $('#logoBackHidden').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $("#faviconUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'faviconUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#faviconImg').attr('src',data.t.url);
                    $('#favicon').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $("#qqgroupUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'qqgroupUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#qqgroupImg').attr('src',data.t.url);
                    $('#qqgroupHidden').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $("#wechatQRCodeUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'wechatQRCodeUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#wechatQRCodeImg').attr('src',data.t.url);
                    $('#wechatQRCodeHidden').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $("#faviconBackUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'faviconBackUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#faviconBackImg').attr('src',data.t.url);
                    $('#faviconBackHidden').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $("#QRCodeUpload").change(
        function(){
            $.ajaxFileUpload({
                url : '/adminManager/fileOperator/upload',
            type: 'post',
            secureuri : false,
            fileElementId : 'QRCodeUpload',
            dataType : 'json',
            success : function (data) {
                if(data.result){
                    $('#QRCodeImg').attr('src',data.t.url);
                    $('#QRCodeHidden').attr('value',data.t.path);
                }else {
                    layer.alert(data.errorMessage);
                }
            }
        });
        });
    $(".submit").click(function () {
        var formData = new FormData($("#form")[0]);
        $.ajax({
            type: "POST",
            url: "/adminManager/config/updateConfig",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data) {
                if (data.result) {
                    layer.confirm("更新成功，将刷新页面", {
                        btn: ['确定']
                }, function () {
                        window.location.href = "/adminManager/config/config.html";
                    }, null);
                } else {
                    layer.alert(data.errorMessage);
                }
            }
        });
    });
});