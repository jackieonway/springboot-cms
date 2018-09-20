<#include "include/header.ftl">

<div class="container u-clearfix u-marginBottom50">
    <div class="u-width850 u-floatLeft">
        <div class="u-backgroundColorWhite u-marginTop20 bigfa-ajax-wrapper u-paddingBottom30">
            <header class="tag-header">
                <div class="tag-hero-title">
                    <span class="fa fa-tag"></span> "${search}"相关的文章
                </div>
            </header>
            <#include "include/articleList.ftl">
            <#include "include/loadMore.ftl">
        </div>
    </div>
</div>
<#include "include/footer.ftl">