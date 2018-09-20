<div class="home-post-list js-postlist">
        <#list articles as article>
            <div class="postlist-item u-clearfix" data-id="1043196">
                <div class="post-img">
                    <a href="${VIEW_PATH!}${article.url}" target="_blank"><img src="${VIEW_PATH!}${article.picture}"></a>
                <#--<div class="post-cat operate"><a href="http://www.woshipm.com/category/operate" rel="category tag">产品运营</a></div>-->
                </div>
                <div class="content">
                    <h2 class="post-title"><a title="${article.title}" href="${VIEW_PATH!}${article.url}" target="_blank">${article.title}</a></h2>
                    <p class="des">${article.summary}...</p>
                    <div class="stream-list-meta">
                    <span class="author" data-id="103150">
                        <#if article.username?? && article.username!= "0">
                             ${article.username}
                        <#else >
                            佚名
                        </#if>
                    </span>
                        <span class="dot"></span>
                        <time>${article.articleTime}</time>
                        <div class="u-floatRight post-meta-items">
                            <span class="post-meta-item"><span class="fa fa-eye"></span>${article.viewCount}</span>
                        <#--<span class="post-meta-item"><span class="fa fa-star"></span>0</span>
                        <span class="post-meta-item"><span class="fa fa-thumbs-up"></span>0</span>-->
                        </div>
                    </div>
                </div>
            </div>
        </#list>
<#--<article class="postlist-item u-clearfix">
<h2 class="topic-suggestions-title">知识体系<span class="dot"></span><span class="des">共47个专题</span><span class="u-floatRight"><a href="/topics" class="des" target="_blank">查看更多<svg class="svgIcon-use" width="22" height="22" viewBox="0 0 19 19">
<path d="M7.6 5.138L12.03 9.5 7.6 13.862l-.554-.554L10.854 9.5 7.046 5.692" fill-rule="evenodd">
</path></svg></a></span></h2>
<div class=" stream-list-topic"><a target="_blank" href="http://www.woshipm.com/topic/%e4%ba%a7%e5%93%81%e7%9a%84%e5%95%86%e4%b8%9a%e6%a8%a1%e5%bc%8f" class="stream-img-pad" style="background-image: url(http://image.woshipm.com/wp-files/2017/08/4YNFkij4kbxxoMUOnVVv.gif!/both/280x180);"><div class="mark">专题</div><div class="info"><div class="title">产品的商业模式</div><div class="desc">什么是产品的商业模式，不同类型的产品在商业模式上有什么区别？</div><div class="stream-topic-meta">21956人已学习<span class="dot"></span>14篇文章</div></div></a><a target="_blank" href="http://www.woshipm.com/topic/dlg" class="stream-img-pad" style="background-image: url(http://image.woshipm.com/wp-files/2017/07/5fb6IlgpWxyRx9bbntew.gif!/both/280x180);"><div class="mark">专题</div><div class="info"><div class="title">节流 ：“变心”了的用户该怎么挽回 ？</div><div class="desc">如何提升用户留存率？——相信这是困扰无数产品和运营的问题。</div><div class="stream-topic-meta">21223人已学习<span class="dot"></span>18篇文章</div></div></a><a target="_blank" href="http://www.woshipm.com/topic/zc" class="stream-img-pad" style="background-image: url(http://image.woshipm.com/wp-files/2017/03/PViXEFfC2jBL4Sysy11f.jpg!/both/280x180);"><div class="mark">专题</div><div class="info"><div class="title">注册登录产品设计指南</div><div class="desc">做了好多年的产品经理，该不会连注册登录功能设计都没整明白吧？</div><div class="stream-topic-meta">27374人已学习<span class="dot"></span>18篇文章</div></div></a></div>
</article>-->
</div>