<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row my-3" id="allChats">
        <#if chats??>
            <#list chats as chat>
                <#if techChat?? && techChat>
                    <#if chat.userFrom.id == -1>
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
                <#else>
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
                </#if>
                <div class="col-md-12 col-sm-12 col-xs-12 my-1 chat">
                    <#if that.picture??>
                        <img class="chatImg" src="/img/userImages/${that.picture}">
                    <#else>
                        <img class="chatImg" src="/img/appImages/UserEmpty.jpg">
                    </#if>
                    <a href="/user/${that.id}">${that.name}</a>
                    <div class="viewMessage <#if !chat.checked && chat.userTo.id == user.id>bg-secondary</#if>">
                        ${chat.userFrom.name}:
                        ${chat.message}
                    </div>
                    (${chat.date})
                    <#if techChat?? && techChat>
                        <a href="/techChat/${that.id}">К диалогу</a>
                    <#else>
                        <a href="/chat/${that.id}">К диалогу</a>
                    </#if>
                </div>
            </#list>
        </#if>
    </div>
</@c.page>