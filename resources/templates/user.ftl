<#-- @ftlvariable name="pageUser" type="pojo.User" -->
<#-- @ftlvariable name="userKtweets" type="java.util.List<pojo.Kweet>" -->

<#import "template.ftl" as layout />

<@layout.mainLayout title="کاربر ${pageUser.name}">
<h3>کاتوئیت‌های ایشون</h3>

<@layout.ktweets_list ktweets=userKtweets></@layout.ktweets_list>
</@layout.mainLayout>