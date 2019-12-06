<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <table class="table">
        <tbody>
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
                <tr class="<#if !chat.checked && chat.userTo.id == this.id>ucheckedBg</#if>">
                    <td>
                        <#if that.picture??>
                            <img class="messagePic" src="/img/userImages/${that.picture}">
                        <#else>
                            <img class="messagePic" src="/img/appImages/UserEmpty.jpg">
                        </#if>
                    </td>
                    <td>
                        <a href="/user/${that.id}">${that.name}</a>
                    </td>
                    <td>
                        ${chat.userFrom.name}:
                        ${chat.message}
                    </td>
                    <td>
                        ${chat.date}
                    </td>
                    <td>
                        <a
                        <#if techChat?? && techChat>
                            href="/techChat/${that.id}"
                        <#else>
                            href="/chat/${that.id}"
                        </#if>
                        >
                            <button class="btn btn-dark">К диалогу</button>
                        </a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>