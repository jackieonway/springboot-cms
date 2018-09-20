<#include "../include/head.ftl">
    <div class="page-wrap">
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">系统配置</h2>
                <p class="title-description">
                    本系统的系统配置,修改,删除等操作都在此目录下。
                </p>
            </header>
            <hr>
        </section>
        <div class="panel panel-default">
            <div class="panel-hd"><a href="/adminManager/config/update.html" class="btn btn-primary" role="button">修改系统配置信息</a></div>
            <div class="form-group-col-2" style="float: left">
                <div class="form-label">LOGO(前台)：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${logo!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2" style="float: left">
                <div class="form-label">LOGO(后台)：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${logoBack!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2"style="float: left">
                <div class="form-label">图标(前)：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${favicon!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">图标(后)：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${faviconBack!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2" style="float: left">
                <div class="form-label">微信二维码：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${wechatQRCode!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2" style="float: left">
                <div class="form-label">微信公众号：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${QRCode!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">QQ群：</div>
                <div class="form-cont">
                    <img src="${BASE_PATH!}${qqgroup!}" alt="img04" style="height:120px;">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">QQ：</div>
                <div class="form-cont">
                    <input type="text" name="qq" readonly="readonly" value="${qq !}" class="form-control form-boxed"/>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">微信号：</div>
                <div class="form-cont">
                    <input type="text" name="wechat" readonly="readonly" value="${wechat !}" class="form-control form-boxed"/>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">新浪微博地址：</div>
                <div class="form-cont">
                    <input type="text" name="weibo" readonly="readonly" value="${weibo !}" class="form-control form-boxed"/>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">地址：</div>
                <div class="form-cont">
                    <input type="text" name="address" readonly="readonly" value="${address !}" class="form-control form-boxed"/>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">版权信息(前)：</div>
                <div class="form-cont">
                    <input type="text" name="copyRight" readonly="readonly" value="${copyRight !}" class="form-control form-boxed"/>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">版权信息(后)：</div>
                <div class="form-cont">
                    <input type="text" name="copyRightBack" readonly="readonly" value="${copyRightBack !}" class="form-control form-boxed"/>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">网站名称(前)：</div>
                <div class="form-cont">
                    <input type="text" name="siteName" readonly="readonly" value="${siteName !}" class="form-control form-boxed">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">网站名称(后)：</div>
                <div class="form-cont">
                    <input type="text" name="siteNameBack" readonly="readonly" value="${siteNameBack !}" class="form-control form-boxed">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">联系电话：</div>
                <div class="form-cont">
                    <input type="text" name="telephone" readonly="readonly" value="${telephone!}" class="form-control form-boxed">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">备案号：</div>
                <div class="form-cont">
                    <textarea id="recordNumber" name="recordNumber" class="form-control form-boxed" readonly="readonly">${recordNumber!}</textarea>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">关于我们：</div>
                <div class="form-cont">
                    <textarea id="aboutUs" name="aboutUs" class="form-control form-boxed" readonly="readonly">${aboutUs!}</textarea>
                </div>
            </div>
        </div>
        <!--开始::结束-->
    </div>
<#include "../include/foot.ftl">