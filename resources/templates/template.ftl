<#-- @ftlvariable name="user" type="pojo.User" -->

<#macro mainLayout title="خوش‌آمدید">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>${title} | Kweet</title>
    <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
    <link rel="stylesheet" type="text/css" href="/styles/main.css">
</head>
<body>
<div class="pure-g">
    <div class="sidebar pure-u-1 pure-u-md-1-4">
        <div class="header">
            <div class="brand-title">کاتوئیت</div>
            <nav class="nav">
                <ul class="nav-list">
                    <li class="nav-item"><a class="pure-button" href="/">خانه</a></li>
                    <#if user??>
                        <li class="nav-item"><a class="pure-button" href="/user/${user.userID}">خط زمانی من</a></li>
                        <li class="nav-item"><a class="pure-button" href="/ktweet">کاتوئیت جدید</a></li>
                        <li class="nav-item"><a class="pure-button" href="/logout">خروج
                            [${user.name?has_content?then(user.name, user.userID)}]</a></li>
                    <#else>
                        <li class="nav-item"><a class="pure-button" href="/register">ثبت‌نام</a></li>
                        <li class="nav-item"><a class="pure-button" href="/login">ورود</a></li>
                    </#if>
                </ul>
            </nav>
        </div>
    </div>

    <div class="content pure-u-1 pure-u-md-3-4">
        <h2>${title}</h2>
        <#nested />
    </div>
    <div class="footer">
        Kweet ktor example, ${.now?string("yyyy")}
    </div>
</div>
</body>
</html>
</#macro>

<#macro ktweet_li ktweet>
<#-- @ftlvariable name="ktweet" type="pojo.Kweet" -->
<section class="post">
    <header class="post-header">
        <p class="post-meta">
            <a href="/ktweet/${ktweet.id}">${ktweet.date.toDate()?string("yyyy.MM.dd HH:mm:ss")}</a>
            by <a href="/user/${ktweet.userId}">${ktweet.userId}</a></p>
    </header>
    <div class="post-description">${ktweet.content}</div>
</section>
</#macro>

<#macro ktweets_list ktweets>
<ul>
    <#list ktweets as ktweet>
        <@ktweet_li ktweet=ktweet></@ktweet_li>
    <#else>
        <li>هنوز کاتوئیتی نداریم :(</li>
    </#list>
</ul>
</#macro>