<#include "../include/header.ftl">
<div class="container u-clearfix u-marginBottom50">
    <div class="u-width850 u-floatLeft">
        <div class="u-backgroundColorWhite u-marginTop20 bigfa-ajax-wrapper u-paddingBottom30">
            <header class="list-header">
                <div class="widget-title"><span>${folder.name!}</span></div>
            </header>
            <#include "../include/articleList.ftl">
            <#include "../include/loadMore.ftl">
        </div>
    </div>
</div>
<#include "../include/footer.ftl">