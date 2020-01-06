<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="userId" type="java.lang.String" -->

<#import "template.ftl" as layout />

<@layout.mainLayout title="خوش‌آمدید">
<form class="pure-form-stacked" action="/login" method="post" enctype="application/x-www-form-urlencoded">
    <#if error??>
        <p class="error">${error}</p>
    </#if>

    <label for="userId">نام کاربری
        <input type="text" name="userId" id="userId" value="${userId}">
    </label>

    <label for="password">رمز عبور
        <input type="password" name="password" id="password">
    </label>

    <input class="pure-button pure-button-primary" type="submit" value="ورود">
</form>
</@layout.mainLayout>