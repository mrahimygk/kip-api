<#-- @ftlvariable name="date" type="java.lang.Long" -->
<#-- @ftlvariable name="code" type="java.lang.String" -->
<#-- @ftlvariable name="ktweet" type="pojo.Kweet" -->
<#import "template.ftl" as layout />

<@layout.mainLayout title="کاتوئیت‌تون">
<section class="post">
    <header class="post-header">
        <p class="post-meta">
            <a href="/ktweet/${ktweet.id}">${ktweet.date.toDate()?string("yyyy.MM.dd HH:mm:ss")}</a>
            توسط <a href="/user/${ktweet.userID}">${ktweet.userID}</a></p>
    </header>
    <div class="post-description">${ktweet.content}</div>
</section>
<#if user??>
<p>
    <a href="javascript:void(0)" onclick="document.getElementById('deleteForm').submit()">حذف</a>
</p>

<form id="deleteForm" method="post" action="/ktweet/${ktweet.id}/delete" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="date" value="${date?c}">
    <input type="hidden" name="code" value="${code}">
</form>
</#if>

</@layout.mainLayout>