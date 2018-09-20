<#include "../include/header.ftl">
<div class="container u-clearfix single-wrapper u-marginBottom30">
    <div class="u-width840 u-floatLeft">
        <div class="u-marginTop30">
            <article class="article-wrapper u-backgroundColorWhite u-rounded">
                <header class="article-header">
                    <h2 class="article-title">${article.title}</h2>
                </header>
                <div class="postMetaLockup--authorWithBio u-flex">
                    <#--<div class="u-flex0">
                        <a href="http://www.woshipm.com/u/689420" target="_blank"><img src="https://static.woshipm.com/WX_U_201805_20180524105727_8613.jpg?imageView2/1/w/42/h/42/q/100" alt="" height="42" width="42" class="avatar"></a>
                    </div>-->
                    <div class="u-flex1 u-paddingLeft15 u-overflowHidden">
                        <#--<div class="">
                            <a href="http://www.woshipm.com/u/689420" target="_blank">木纹</a><button class="button button--toggle button--primary button--follow" data-action="showLoginForm" data-id="689420"><span class="is-default">订阅专栏</span><span class="is-active">取消订阅</span></button>                            </div>-->
                         <#if article.username?? && article.username!= "0">
                             <div class="">${article.username}</div>
                         <#else >
                            <div class="">佚名</div>
                         </#if>
                        <div class="des"></div>
                        <div class="postMetaInline postMetaInlineSupplemental post-meta-items">
                            <span class="post-meta-item"><span class="fa fa-clock-o"></span>${article.articleTime}</span>
                            <span class="post-meta-item"><span class="fa fa-eye"></span>${article.viewCount}</span>
                            <#--<span class="post-meta-item"><span class="fa fa-star"></span>16</span>
                            <span class="post-meta-item"><span class="fa fa-thumbs-up"></span>5</span>-->
                        </div>

                    </div>
                </div>
                <div class="article-topAd">
                    ${article.summary}
                </div>
                <div class="grap">
                    <#--<blockquote><p><span style="font-size: 16px;">虽然O2O现在基本偃旗息鼓，但东家这个商超O2O产品或多或少，对于做商业地产相关的朋友还是有一定价值的。</span></p></blockquote>-->
                    <p><img data-action="zoom" class="size-full wp-image-1037738 aligncenter" src="${VIEW_PATH!}${article.picture}" alt="" width="800" height="450"></p>
                    ${article.content}
                </div>
                <#--<div class="article-bottomAd">
                    <a href="https://ke.qq.com/huodong/pmqidian19_pc/index.html#tuin=woshipm" target="_blank"><img src="http://image.woshipm.com/wp-files/2018/05/SZpvtmmrvQJYjSwu0LFe.jpg" alt=""></a>                    </div>-->
                <div class="taglist">
                    <#list article.searchs as key>
                        <a href="${VIEW_PATH!}/search.html?search=${key}&pageNum=1&pageSize=10" rel="tag">${key}</a>
                    </#list>
                    <#--<a href="http://www.woshipm.com/tag/3%e5%b9%b4" rel="tag">3年</a>
                    <a href="http://www.woshipm.com/tag/o2o" rel="tag">O2O</a>
                    <a href="http://www.woshipm.com/tag/%e4%b8%ad%e7%ba%a7" rel="tag">中级</a>-->
                </div>

                <#--<div class="support-author"><div class="support-title">赞赏是对原创者的最大认可</div><button class="button--pay" data-post-id="1036606" data-author="689420" data-avatar="https://static.woshipm.com/WX_U_201805_20180524105727_8613.jpg"><svg width="13" height="16" class="svgIcon--use" viewBox="0 0 13 16"><path d="M9.113,4.571 C9.951,3.771 10.895,2.742 10.685,2.057 C10.475,1.485 10.056,0.799 9.427,0.571 C8.903,0.342 8.379,0.456 7.750,0.799 C7.540,0.342 7.016,0.114 6.596,-0.001 C5.863,-0.001 5.234,0.228 4.814,0.914 C4.080,0.571 3.451,0.685 2.927,1.028 C2.613,1.256 2.298,1.713 2.298,2.628 C2.298,3.542 3.137,4.228 3.766,4.685 C2.508,5.599 -0.218,7.885 -0.008,12.228 C-0.218,15.656 2.613,15.999 2.613,15.999 L10.371,15.999 C11.314,15.885 12.991,14.971 12.991,12.571 L12.991,12.228 C13.201,7.771 10.371,5.371 9.113,4.571 L9.113,4.571 ZM8.932,11.835 L6.940,11.835 L6.940,13.207 C6.940,13.435 6.731,13.549 6.521,13.549 C6.311,13.549 6.102,13.435 6.102,13.207 L6.102,11.835 L4.110,11.835 C3.900,11.835 3.795,11.606 3.795,11.378 C3.795,11.149 3.900,10.921 4.110,10.921 L6.102,10.921 L6.102,10.121 L4.949,10.121 C4.739,10.121 4.634,9.892 4.634,9.664 C4.634,9.435 4.739,9.206 4.949,9.206 L5.892,9.206 L4.739,7.950 C4.634,7.835 4.739,7.606 4.949,7.492 C5.158,7.378 5.368,7.264 5.473,7.378 L6.521,8.635 L7.674,7.264 C7.779,7.149 7.989,7.264 8.198,7.378 C8.408,7.492 8.408,7.721 8.408,7.835 L7.150,9.321 L8.094,9.321 C8.303,9.321 8.408,9.549 8.408,9.778 C8.408,10.007 8.303,10.235 8.094,10.235 L6.940,10.235 L6.940,11.035 L8.932,11.035 C9.142,11.035 9.247,11.264 9.247,11.493 C9.247,11.606 9.037,11.835 8.932,11.835 L8.932,11.835 Z"></path></svg>
                赞赏</button><div class="pay-num">6人打赏</div><div class="donation-list"><div class="donation-item"><img class="avatar" src="https://static.woshipm.com/TTW_USER_R_201706_20170602183235_2374.jpg?imageView2/1/w/80" height="32" width="32"></div><div class="donation-item"><img class="avatar" src="https://static.woshipm.com/TTW_USER_R_201706_20170602175046_5795.jpg?imageView2/1/w/80" height="32" width="32"></div><div class="donation-item"><img class="avatar" src="https://static.woshipm.com/TTW_USER_R_201706_20170602174830_3697.jpg?imageView2/1/w/80" height="32" width="32"></div><div class="donation-item"><img class="avatar" src="https://static.woshipm.com/TTW_USER_R_201706_20170602175342_1640.jpg?imageView2/1/w/80" height="32" width="32"></div><div class="donation-item"><img class="avatar" src="https://static.woshipm.com/TTW_USER_R201706_20170602174600_4061.jpg?imageView2/1/w/80" height="32" width="32"></div><div class="donation-item"><img class="avatar" src="https://static.woshipm.com/TTW_USER_R_201706_20170602182730_3345.jpg?imageView2/1/w/80" height="32" width="32"></div></div></div>                                        <div class="post-actions u-clearfix">
                <div class="u-floatLeft share-actions">
                    <button class="button button--gray button--circle button--wechat"><span class="fa fa-wechat"></span><span class="qrcode-wrapper"><img src="http://qr.liantu.com/api.php?text=http://www.woshipm.com/pd/1036606.html"></span></button>
                    <a class="button button--gray button--circle button--weibo" target="_blank" href="http://service.weibo.com/share/share.php?appkey=2775287854&amp;title=实战案例：上海大悦城APP产品设计 Review&amp;url=http://www.woshipm.com/pd/1036606.html&amp;pic=http://image.woshipm.com/wp-files/2018/05/nmlhpLoeyOYE2KhEBEXa.jpg" "=""><span class="fa fa-weibo"></span></a>
                </div>-->
                <#--<div class="u-floatRight">
                    <button title="收藏" class="button button--gray button--toggle button--recommend button-post-1036606 " data-id="1036606" data-action="popLogin"><span class="iconfont icon-heart"></span><span class="button-label is-default">收藏</span><span class="button-label is-active">已收藏</span> | <span class="count">16</span></button><button class="button button--toggle button--gray button--postlike" data-action="uplike" data-id="1036606"><span class="button-label is-default">赞</span><span class="button-label is-active">已赞</span> | <span class="count">5</span></button>                        </div>
            </div>-->
            </article>
            <#include "../include/share-common.ftl"/>
            <#--<div class="postFooterInfo u-marginTop30 u-backgroundColorWhite u-rounded">
                <div class="u-flex">
                    <div class="u-flex0">
                        <a href="http://www.woshipm.com/u/689420" target="_blank"><img src="https://static.woshipm.com/WX_U_201805_20180524105727_8613.jpg?imageView2/1/w/60/h/60/q/100" alt="" height="60" width="60" class="avatar"></a>
                    </div>
                    <div class="u-flex1 u-paddingLeft15 u-overflowHidden">
                        <div class=""><span class="title"><a href="http://www.woshipm.com/u/689420" target="_blank">木纹</a></span><button class="button button--toggle button--primary button--follow" data-action="showLoginForm" data-id="689420"><span class="is-default">订阅专栏</span><span class="is-active">取消订阅</span></button></div>
                        <div class="des"></div>
                    </div>
                    <div class="u-flex0">
                        <div class="authorInfo-item"><i>1篇</i>作品</div>
                        <div class="authorInfo-item"><i>3701</i>总阅读量</div>
                    </div>
                </div>
                </div>-->
            <#--<h2 class="comments-title">您可能感兴趣的文章</h2>
                <div class="promote-posts">
                    <div class="promote-post-item">
                        <a target="_blank" href="http://www.woshipm.com/evaluating/1044813.html" style="background-image: url(http://image.woshipm.com/wp-files/2018/05/JpW1iNWT3cufXsGw6LLU.jpg)" class="promote-post-image">
                        </a>
                        <div class="promote-post-title"><a target="_blank" href="http://www.woshipm.com/evaluating/1044813.html">养老行业门户网站可行性分析报告</a></div>
                    </div>
                    <div class="promote-post-item">
                        <a target="_blank" href="http://www.woshipm.com/operate/1044421.html" style="background-image: url(http://image.woshipm.com/wp-files/2018/05/kV1wMCV8uYS9fIOeQRpp.jpg)" class="promote-post-image">
                        </a>
                        <div class="promote-post-title"><a target="_blank" href="http://www.woshipm.com/operate/1044421.html">如何利用漏斗地图，让转化分析更精准</a></div>
                    </div>
                    <div class="promote-post-item">
                        <a target="_blank" href="http://www.woshipm.com/it/1043670.html" style="background-image: url(http://image.woshipm.com/wp-files/2018/05/kxkWeN9ypueb4RLPYtbj.jpg)" class="promote-post-image">
                        </a>
                        <div class="promote-post-title"><a target="_blank" href="http://www.woshipm.com/it/1043670.html">GDPR 来了：欧盟没有科技巨头，但是有法律</a></div>
                    </div>
                    <div class="promote-post-item">
                        <a target="_blank" href="http://y0.cn/2IbUV" style="background-image: url(http://image.woshipm.com/wp-files/2017/03/Il8v4dI18IcRXTVeoLit.jpg)" class="promote-post-image">
                        </a>
                        <div class="promote-post-title"><a target="_blank" href="http://y0.cn/2IbUV">每天3块钱，500门产品、运营课程随便学，做个有竞争力互联网人</a></div>
                    </div>
                    <div class="promote-post-item">
                        <a target="_blank" href="http://www.woshipm.com/active/1044892.html" style="background-image: url(http://image.woshipm.com/wp-files/2018/05/j0t0wcy5taQpm0nQfgKz.jpg)" class="promote-post-image">
                        </a>
                        <div class="promote-post-title"><a target="_blank" href="http://www.woshipm.com/active/1044892.html">产品新人：要是入行前知道这些，就不会走那么多弯路了！</a></div>
                    </div>
                    <div class="promote-post-item">
                        <a target="_blank" href="http://www.woshipm.com/marketing/1045023.html" style="background-image: url(http://image.woshipm.com/wp-files/2018/05/cLOr8iwvFLYC9CrxeJ0t.png)" class="promote-post-image">
                        </a>
                        <div class="promote-post-title"><a target="_blank" href="http://www.woshipm.com/marketing/1045023.html">有哪些看过后觉得非常好的广告（中）</a></div>
                    </div>
                </div>-->
            </div>
        </div>
    </div>
<#include "../include/footer.ftl">