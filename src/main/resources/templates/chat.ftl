<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row my-3" id="allChat">
        <#if chats??>
            <#list chats as chat>
                <#if chat.userFrom.id == user.id>
                    <#assign
                    this = chat.userFrom
                    that = chat.userTo
                    >
                <#else>
                    <#assign
                    this = chat.userTo
                    that = chat.userFrom
                    >
                </#if>
                <div class="col-md-12 col-sm-12 col-xs-12 my-1 chat">
                    <#if that.picture??>
                        <img class="chatImg" src="/img/userImages/${that.picture}">
                    <#else>
                        <img class="chatImg" src="/img/appImages/UserEmpty.jpg">
                    </#if>
                    <a href="/user/${that.id}">${that.name}</a>
                    <div class="viewMessage">
                    ${chat.userFrom.name}:
                    ${chat.message}
                    </div>
                    (${chat.date})
                    <a href="/chat/${that.id}">К диалогу</a>
                </div>
            </#list>
        </#if>
    </div>
</@c.page>