<#include "../include/head.ftl">
    <script src="${BASE_PATH!}/manage/js/file-upload/ajaxfileupload.js"></script>
    <script src="${BASE_PATH!}/manage/js/pages/config.js"></script>
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">系统配置修改</h2>
                <p class="title-description">
                    本系统的系统配置,修改,删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <form id="form" method="post">
                <div class="form-group-col-2" >
                    <div class="form-label">LOGO(前台)：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="logoUpload" name="file"/>
                        <input id="logoHidden" type="hidden" value="${logo!}" name="logo">
                        <img id="logoImg" src="${BASE_PATH!}${logo!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">LOGO(后台)：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="logoBackUpload" name="file"/>
                        <input id="logoBackHidden" type="hidden" value="${logoBack!}" name="logoBack">
                        <img id="logoBackImg" src="${BASE_PATH!}${logoBack!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">图标(前)：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="faviconUpload" name="file"/>
                        <input id="favicon" type="hidden" value="${favicon!}" name="favicon">
                        <img id="faviconImg" src="${BASE_PATH!}${favicon!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">图标(后)：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="faviconBackUpload" name="file"/>
                        <input id="faviconBackHidden" type="hidden" value="${faviconBack!}" name="faviconBack">
                        <img id="faviconBackImg" src="${BASE_PATH!}${faviconBack!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">QQ：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="qq" value="${qq !}" class="form-control form-boxed"/>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">QQ群：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="qqgroupUpload" name="file"/>
                        <input id="qqgroupHidden" type="hidden" value="${qqgroup!}" name="qqgroup">
                        <img id="qqgroupImg" src="${BASE_PATH!}${qqgroup!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">微信号：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="wechat" value="${wechat !}" class="form-control form-boxed"/>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">微信二维码：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="wechatQRCodeUpload" name="file"/>
                        <input id="wechatQRCodeHidden" type="hidden" value="${wechatQRCode!}" name="wechatQRCode">
                        <img id="wechatQRCodeImg" src="${BASE_PATH!}${wechatQRCode!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2" >
                    <div class="form-label">微信公众号：</div>
                    <div class="form-cont txt-width">
                        <input type="file" id="QRCodeUpload" name="file"/>
                        <input id="QRCodeHidden" type="hidden" value="${QRCode!}" name="QRCode">
                        <img id="QRCodeImg" src="${BASE_PATH!}${QRCode!}" alt="img04" style="height:120px;">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">新浪微博地址：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="weibo" value="${weibo !}" class="form-control form-boxed"/>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">地址：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="address" value="${address !}" class="form-control form-boxed"/>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">版权信息(前)：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="copyRight" value="${copyRight !}" class="form-control form-boxed"/>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">版权信息(后)：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="copyRightBack" value="${copyRightBack !}" class="form-control form-boxed"/>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">网站名称(前)：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="siteName" value="${siteName !}" class="form-control form-boxed">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">网站名称(后)：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="siteNameBack" value="${siteNameBack !}" class="form-control form-boxed">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">联系电话：</div>
                    <div class="form-cont txt-width">
                        <input type="text" name="telephone" value="${telephone!}" class="form-control form-boxed">
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">备案号：</div>
                    <div class="form-cont txt-width">
                        <textarea id="recordNumber" name="recordNumber" class="form-control form-boxed">${recordNumber!}</textarea>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label">关于我们：</div>
                    <div class="form-cont txt-width">
                        <textarea id="aboutUs" name="aboutUs" class="form-control form-boxed">${aboutUs!}</textarea>
                    </div>
                </div>
                <div class="form-group-col-2">
                    <div class="form-label"></div>
                    <div class="form-cont txt-width">
                        <button type="button" class="submit btn btn-primary">更新</button>
                    </div>
                </div>
            </form>
        </div>
        <!--开始::结束-->
    </div>
<#include "../include/foot.ftl">