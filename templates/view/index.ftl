<#include "include/header.ftl">
<#include "include/banner.ftl">
<!-- about -->
	<div class="about" id="about">
        <div class="container">
            <#if articles??>
                <#list articles as article>
                    <div class="w3l-grids-about">
                        <div class="col-md-5 w3ls-ab-right">
                            <img src="${VIEW_PATH!}${article.picture}">
                        </div>
                        <div class="col-md-7 w3ls-agile-left">
                            <h2>${article.title}</h2>
                            <p>${article.summary}</p>
                            <a href="${article.url}" class="read">查看详情</a>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
<!-- //about-bottom -->
<#include "include/footer.ftl">