<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="pageUser" type="pojo.User" -->

<#import "template.ftl" as layout />

<@layout.mainLayout title="به به">
<form class="pure-form-stacked" action="/register" method="post" enctype="application/x-www-form-urlencoded">
    <#if error??>
        <p class="error">${error}</p>
    </#if>

    <label for="userID">نام کاربری
        <input type="text" name="userID" id="userID" value="${pageUser.userID}">
    </label>


    <label for="email">ایمیل
        <input type="email" name="email" id="email" value="${pageUser.email}">
    </label>


    <label for="displayName">نام مستعار
        <input type="text" name="name" id="name" value="${pageUser.name}">
    </label>


    <label for="hash">رمز
        <input type="password" name="hash" id="hash">
    </label>


    <input class="pure-button pure-button-primary" type="submit" value="ثبت نام">
</form>
</@layout.mainLayout>
