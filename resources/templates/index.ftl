<#-- @ftlvariable name="top" type="java.util.List<pojo.Kweet>" -->
<#-- @ftlvariable name="latest" type="java.util.List<pojo.Kweet>" -->

<#import "template.ftl" as layout />

<@layout.mainLayout title="خوش‌آمدید">
<div class="posts">
    <h3 class="content-subhead">۱۰ تای برتر</h3>
    <@layout.ktweets_list ktweets=top></@layout.ktweets_list>

    <h3 class="content-subhead">۱۰ تای تازه</h3>
    <@layout.ktweets_list ktweets=latest></@layout.ktweets_list>
</div>
</@layout.mainLayout>
