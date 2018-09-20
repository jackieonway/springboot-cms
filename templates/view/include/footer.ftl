<!--footer-->
<div class="footer">
    <div class="footer-content-agile">
        <div class="col-md-3 about-us-agile">
            <h4>关于我们</h4>
            <div class="aboutus-right-wthree">
                <p>${aboutUs}</p>

            </div>
        </div>
        <#--<div class="col-md-3 news-letter-agileits-w3layouts">
            <h4></h4>
            <p>signup for our weekly newsletter and get the latest updates</p>
            <form action="#" method="post">
                <input type="email" name="Email" placeholder="Enter your Email" required="" />
                <input type="submit" value="Subscribe" />
            </form>
        </div>-->
        <div class="col-md-3 links-w3layouts">
            <h4>友情链接</h4>
            <#assign blogrollvar=1>
            <ul style="width: 50%; float: left;">
            <#list blogrolls as blogroll >
                <#assign blogrollvar=blogrollvar+1>
                <#if blogrollvar / 5 == 0>
                     </ul>
                     <ul style="width: 50%; float: left;">
                        <li><a target="_blank" href="${blogroll.blogrollUrl}"><span>${blogroll.blogrollName}</span></a></li>
                <#else >
                    <li><a target="_blank" href="${blogroll.blogrollUrl}"><span>${blogroll.blogrollName}</span></a></li>
                </#if>
            </#list>
            </ul>
        </div>
        <div class="col-md-3 footer-social-icons-agileinfo">
            <h4>社交平台</h4>
            <ul>
                <#if qq??>
                    <li><a ><span><i class="fa fa-qq" aria-hidden="true"></i> </span>
                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq! }&site=qq&menu=yes">${qq! }</a>
                    </a>
                    </li>
                </#if>
                <#if wechat??>
                    <li><a href="#" data-toggle="modal" data-target="#myModal1" ><span><i class="fa fa-weixin" aria-hidden="true"></i></span>${wechat!}</a></li>
                </#if>
                <#if QRCode?? || qqgroup??>
                    <li><a data-toggle="modal" data-target="#myModal" ><span><i class="fa fa-qrcode" aria-hidden="true"></i></span> 扫码关注</a></li>
                </#if>
                <#if weibo??>
                    <li><a href="${weibo!}" target="_blank"><span><i class="fa fa-weibo" aria-hidden="true"></i></span>新浪微博</a></li>
                </#if>
                <#if telephone??>
                    <li><a href="tel:${telephone! }"><span><i class="fa fa-phone" aria-hidden="true"></i> </span>${telephone! }</a></li>
                </#if>
                <#if address??>
                    <li><a ><span><i class="fa fa-location-arrow" aria-hidden="true"></i> </span>${address !}</a></li>
                </#if>
            </ul>
        </div>
        <div class="clearfix"> </div>
        <div class="copy">
            <p>${copyRight} ${recordNumber }</p>
        </div>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    扫码关注
                </h4>
            </div>
            <div class="modal-body">
                <div class="pl-20 pr-20">
                    <table style="border: none" class="table table-bordered table-striped mt-10">'
                        <tr style="border: none" >
                            <td style="border: none" ><img src="${VIEW_PATH!}${QRCode}" style="width:auto;max-width:100%;height:120px;"/></td>
                            <td style="border: none" ><img src="${VIEW_PATH!}${qqgroup}" style="width:auto;max-width:100%;height:120px;"/></td>
                        </tr>
                        <tr class="cen" style="border: none" >
                            <td style="border: none"  class="text-primary">微信公众号</td>
                            <td style="border: none"  class="text-primary">QQ群</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <#--<button type="button" class="btn btn-primary">
                    提交更改
                </button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    微信二维码
                </h4>
            </div>
            <div class="modal-body">
                <div class="pl-20 pr-20">
                    <table style="border: none" class="table table-bordered table-striped mt-10">'
                        <tr style="border: none" >
                            <td style="border: none" ><img src="${VIEW_PATH!}${wechatQRCode!}" style="width:auto;max-width:100%;height:120px;"/></td>
                        </tr>
                        <tr class="cen" style="border: none" >
                            <td style="border: none"  class="text-primary">微信二维码</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            <#--<button type="button" class="btn btn-primary">
                提交更改
            </button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--/footer -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="${V_PATH}/js/move-top.js"></script>
<script type="text/javascript" src="${V_PATH}/js/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!-- start-smoth-scrolling -->
<!--light-box-files -->
<script src="${V_PATH}/js/jquery.chocolat.js"></script>
<!--//light-box-files -->
		<script type="text/javascript">
            $(function() {
                $('.gallery a').Chocolat();
            });
        </script>
<!-- //js -->
<script src="${V_PATH}/js/responsiveslides.min.js"></script>
		<script>
            $(function () {
                $("#slider").responsiveSlides({
                    auto: true,
                    pager: true,
                    nav: true,
                    speed: 1000,
                    namespace: "callbacks",
                    before: function () {
                        $('.events').append("<li>before event fired.</li>");
                    },
                    after: function () {
                        $('.events').append("<li>after event fired.</li>");
                    }
                });
            });
        </script>

		<!--search-bar-->
		<script src="${V_PATH}/js/main.js"></script>
<!--//search-bar-->
<!-- smooth scrolling -->
	<script type="text/javascript">
        $(document).ready(function() {
            $().UItoTop({ easingType: 'easeOutQuart' });
        });
    </script>


	<a href="#home" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

</body>
</html>