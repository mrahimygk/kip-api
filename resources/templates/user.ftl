<#-- @ftlvariable name="pageUser" type="pojo.User" -->
<#-- @ftlvariable name="kweets" type="java.util.List<pojo.Kweet>" -->

<#import "template.ftl" as layout />

<@layout.mainLayout title="کاربر ${pageUser.name}">
<h3>کاتوئیت‌های ایشون</h3>

<@layout.kweets_list kweets=kweets></@layout.kweets_list>
</@layout.mainLayout>