<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="pageUser" type="io.ktor.samples.kweet.model.User" -->

<#import "template.ftl" as layout />

<@layout.mainLayout title="Welcome">
<form class="pure-form-stacked" action="/register" method="post" enctype="application/x-www-form-urlencoded">
    <#if error??>
        <p class="error">${error}</p>
    </#if>

    <label for="userId">Login
        <input type="text" name="userId" id="userId" value="${pageUser.userId}">
    </label>


    <label for="email">ایمیل
        <input type="email" name="email" id="email" value="${pageUser.email}">
    </label>


    <label for="displayName">نام مستعار
        <input type="text" name="displayName" id="displayName" value="${pageUser.displayName}">
    </label>


    <label for="password">رمز
        <input type="password" name="password" id="password">
    </label>


    <input class="pure-button pure-button-primary" type="submit" value="ثبت نام">
</form>
</@layout.mainLayout>
