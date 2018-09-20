<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if folder?? >${folder.title!} -${siteName!}<#elseif article ??>${article.title!} -${siteName}<#elseif search??>${siteName}搜索-${search!} -${siteName!}</#if></title>
    <meta name="description" content="<#if folder?? >${folder.description!}<#elseif article ??>${article.summary!}<#elseif search??>${siteName!}网站内部关键字检索，方便用户在网站内根据文章的关键字，自动检索与之相关的文章。</#if>"/>
    <meta name="keywords" content="<#if folder?? >${folder.keywords!}<#elseif article ??>${article.keywords!}<#elseif search??>关键字检索,${siteName!}关键字检索,文章关键字</#if>"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta name="author" content="order by ipengzu.com"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta name="full-screen" content="yes">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <link href="${V_PATH}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${V_PATH}/css/misc.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${V_PATH}/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link rel="stylesheet" href="${V_PATH}/css/chocolat.css" type="text/css" media="screen">
    <link href="${V_PATH}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link rel="icon" href="${VIEW_PATH!}${favicon}" type="image/x-icon">
    <!--fonts-->
    <link href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <!-- js -->
    <script type="text/javascript" src="${V_PATH}/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${V_PATH}/js/event.js"></script>
    <!--//fonts-->
    <script type="text/javascript" src="${V_PATH}/js/bootstrap-3.1.1.min.js"></script>
</head>
<body>
<!--Header-->
<div class="header" id="home">
    <!--Top-Bar-->
    <div class="top-bar">
        <div class="container-fluid">
            <div class="header-nav">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <h1><a class="navbar-brand" href="${VIEW_PATH!}/"><img style="width: 150px;height: 56px;" src="${VIEW_PATH!}${logo}"></a></h1>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                        <nav class="cl-effect-15 header-nav" id="cl-effect-15">
                            <ul class="subnav-ul layoutSingleColumn layoutSingleColumn--wide">
                                <#if menus??>
                                    <#list menus as menu>
                                        <#if clazz??>
                                            <#if clazz == menu.ename>
                                                <li class="third menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-155939">
                                                    <a href="${VIEW_PATH!}/<#if menu.id != 1>${menu.ename}</#if>" class="active chk-hrf" >${menu.name}</a>
                                                    <#if  (menu.folders?size > 0)>
                                                        <span class="fa fa-angle-down"></span>
                                                        <ul class="sub-menu">
                                                            <#list menu.folders as secondFolder>
                                                                <#if second??>
                                                                    <#if second == secondFolder.ename>
                                                                        <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                            <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}" class="chk-hrf">${secondFolder.name}</a>
                                                                        </li>
                                                                    <#else >
                                                                        <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                            <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}">${secondFolder.name}</a>
                                                                        </li>
                                                                    </#if>
                                                                <#else >
                                                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                        <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}">${secondFolder.name}</a>
                                                                    </li>
                                                                </#if>
                                                            </#list>
                                                        </ul>
                                                    </#if>
                                                </li>
                                            <#else>
                                                <li class="third menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-155939">
                                                    <a href="${VIEW_PATH!}/<#if menu.id != 1>${menu.ename}</#if>" class="active" >${menu.name}</a>
                                                    <#if  (menu.folders?size > 0)>
                                                        <span class="fa fa-angle-down"></span>
                                                        <ul class="sub-menu">
                                                            <#list menu.folders as secondFolder>
                                                                <#if second??>
                                                                    <#if second == secondFolder.ename>
                                                                        <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                            <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}" class="chk-hrf">${secondFolder.name}</a>
                                                                        </li>
                                                                    <#else >
                                                                        <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                            <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}">${secondFolder.name}</a>
                                                                        </li>
                                                                    </#if>
                                                                <#else >
                                                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                        <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}">${secondFolder.name}</a>
                                                                    </li>
                                                                </#if>
                                                            </#list>
                                                        </ul>
                                                    </#if>
                                                </li>
                                            </#if>
                                        <#else >
                                            <li class="third menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-155939"><a href="${VIEW_PATH!}/<#if menu.id != 1>${menu.ename}</#if>" class="active" >${menu.name}</a>
                                                <#if  (menu.folders?size > 0)>
                                                    <span class="fa fa-angle-down"></span>
                                                    <ul class="sub-menu">
                                                        <#list menu.folders as secondFolder>
                                                            <#if second??>
                                                                <#if second == secondFolder.ename>
                                                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                        <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}" class="chk-hrf">${secondFolder.name}</a>
                                                                    </li>
                                                                <#else >
                                                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                        <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}">${secondFolder.name}</a>
                                                                    </li>
                                                                </#if>
                                                            <#else >
                                                                <li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-155928">
                                                                    <a href="${VIEW_PATH!}/${menu.ename}/${secondFolder.ename}">${secondFolder.name}</a>
                                                                </li>
                                                            </#if>
                                                        </#list>
                                                    </ul>
                                                </#if>
                                            </li>
                                        </#if>
                                    </#list>
                                <#else>
                                    <li class="third menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-155939">
                                        <a href="${VIEW_PATH!}/" class="active" >首页</a>
                                    </li>
                                </#if>
                            </ul>
                        </nav>
                    </div>
                </nav>
                <div class="w3ls_search">
                    <div class="cd-main-header">
                        <ul class="cd-header-buttons">
                            <li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
                        </ul> <!-- cd-header-buttons -->
                    </div>
                    <div id="cd-search" class="cd-search">
                        <form action="/search.html" method="get">
                            <input name="search" type="search" placeholder="搜索...">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--//Top-Bar-->
</div>
<!--//Header-->