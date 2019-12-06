<#import "parts/common.ftl" as c>
<#import "parts/dealView.ftl" as d>
<#include "parts/security.ftl">

<@c.page>
    <@d.dealView byAuthors 0></@d.dealView>

    <@d.dealView byBuyers 1></@d.dealView>
</@c.page>