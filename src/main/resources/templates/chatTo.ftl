<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row my-3" id="allChat">
        <div class="col-md-12 col-sm-12 col-xs-12 row chatDiv" id="chat">
            <#if messages??>
                <#list messages as message>
                    <#if message.userFrom.id == toUser.id>
                        <div class="col-md-6 col-sm-6 col-xs-6"></div></#if>
                    <div class="col-md-6 col-sm-6 col-xs-6 p-1 my-1
                        <#if message.userFrom.id == thisUser.id>messageFrom<#else>messageTo</#if>">
                        <p class="authorMessage"><a class="text-dark" href="/user/${message.userFrom.id}">${message.userFrom.name}</a></p>
                        ${message.message}
                        <p class="dateMessage">${message.date}</p>
                    </div>
                    <#if message.userFrom.id == thisUser.id>
                        <div class="col-md-6 col-sm-6 col-xs-6"></div></#if>
                </#list>
            </#if>
        </div>
        <div class="container-fluid">
            <form action="/chat" method="post">
                <div class="row">
                    <div class="col-md-9 col-sm-9 col-xs-9">
                        <input type="text" name="message" class="form-control messageInput"
                               placeholder="<#if error??>${error}<#else>Введите сообщение</#if>"/>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="userTo" value="${toUser.id}">
                    <div class="col-md-3 col-sm-3 col-xs-3">
                        <button type="submit" class="btn btn-dark btn-sm col-sm-3">Отправить</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
    <script type="text/javascript">
        var block = document.getElementById("chat");
        block.scrollTop = block.scrollHeight;
    </script>
</@c.page>