<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="container-fluid">
        <#if chats??>
            <#list chats as chat>
                <#if chat.userFrom.id == user.id>
                    ${chat.userTo.name}
                <#else>
                    ${chat.userFrom.name}
                </#if><br/>
                ${chat.message}<br/>
                ${chat.date}<br/><br/>
            </#list>
        </#if>
    </div>
</@c.page>