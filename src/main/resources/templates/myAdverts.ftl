<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th>Изображение</th>
            <th>Название</th>
            <th>Стоимость</th>
            <th>Покупатель</th>
            <th>Изменить покупателя</th>
        </tr>
        </thead>
        <tbody>
        <#if adverts??>
            <#list adverts as advert>
                <#if advert.active>
                    <tr>
                        <td><#if advert.picture??>
                                <img class="messagePic" src="/img/advImages/${advert.picture}">
                            <#else>
                                <img class="messagePic" src="/static/img/appImages/Empty.png">
                            </#if>
                        </td>
                        <td><a href="/advert/${advert.id}">${advert.tittle}</a></td>
                        <td>${advert.cost}</td>
                        <td>
                            <#if advert.buyer??>
                                <a href="/user/${advert.buyer.id}">${advert.buyer.name}</a>
                            <#else>
                                Покупателя нет
                            </#if>
                        </td>
                        <td>
                            <form action="changeBuyer" method="post">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <select name="buyer">
                                            <option value="-10">Нет покупателя</option>
                                            <#if chats??>
                                                <#list chats as chat>
                                                    <#if !chat.userFrom.isBlocked() && !chat.userTo.isBlocked() && chat.userFrom.id != -1 && chat.userTo.id != -1>
                                                    <#if chat.userFrom.id == user.id>
                                                        <option value="${chat.userTo.id}">
                                                            ${chat.userTo.name}(${chat.date})
                                                        </option>
                                                    <#else>
                                                        <option value="${chat.userFrom.id}">
                                                            ${chat.userFrom.name}(${chat.date})
                                                        </option>
                                                    </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="hidden" name="advert" value="${advert.id}">
                                    <div class="col-sm-4">
                                        <button type="submit" class="btn btn-primary">Применить</button>
                                    </div>
                                </div>
                            </form>
                        </td>
                    </tr>
                </#if>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>