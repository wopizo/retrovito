<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th>Изображение</th>
            <th>Название</th>
            <th>Стоимость</th>
            <th>Подтвердить покупку</th>
        </tr>
        </thead>
        <tbody>
        <#if goods??>
            <#list goods as good>
                <#if good.active>
                    <tr>
                        <td><#if good.picture??>
                                <img class="messagePic" src="/img/advImages/${good.picture}">
                            <#else>
                                <img class="messagePic" src="/static/img/appImages/Empty.png">
                            </#if>
                        </td>
                        <td><a href="/advert/${good.id}">${good.tittle}</a></td>
                        <td>${good.cost}</td>
                        <td>
                            <form action="addDeal" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="hidden" name="advert" value="${good.id}">
                                <button type="submit" class="btn btn-primary">Подтвердить</button>
                            </form>
                        </td>
                    </tr>
                </#if>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>