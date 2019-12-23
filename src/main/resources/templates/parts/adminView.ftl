<#include "security.ftl">

<#macro adminlView index list count>
    <h2><a data-toggle="collapse" href="#collapse${index}">
            <#if index == 0>Пользователи
        <#elseif index == 1>Объявления
        <#elseif index == 2>Комментарии
        <#elseif index == 3>Отзывы
        <#elseif index == 4>Сделки
            </#if>(Найдено ${count})</a></h2>
    <div id="collapse${index}" class="collapse">
        <table class="table">
            <thead>
            <tr>
                <th>
                    <#if index == 0>ФИО
                    <#elseif index == 1 || index == 4>Название
                    <#elseif index == 2 || index == 3>Сообщение
                    </#if>
                </th>
                <th>Дата</th>
                <#if index == 0 && id == 1>
                    <th>Дать\забрать Админ права</th>
                </#if>
                <#if index == 0 || index == 1>
                    <th>Заблокировать\разблокировать</th>
                </#if>
                <#if index != 4>
                    <th>Удалить</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#list list as l>
                <tr>
                    <td>
                        <#if index == 0><a
                            href="/user/${l.id}"><#if l.sname??>${l.sname} </#if>${l.name}<#if l.fname??> ${l.fname}</a></#if>
                        <#elseif index == 1><a href="/advert/${l.id}">${l.tittle}</a>
                        <#elseif index == 2><a href="/advert/${l.advert.id}">${l.message}</a>
                        <#elseif index == 3><a href="/user/${l.userTo.id}">${l.message}</a>
                        <#elseif index == 4><a href="/advert/${l.advert.id}">${l.advert.tittle}</a>
                        </#if>
                    </td>
                    <td>
                        ${l.date}
                    </td>
                    <form action="/admin" method="post">
                        <#if index == 0 && id == 1 >
                            <td>
                                <#if !l.isAdmin() && !l.isBlocked()>
                                    <button type="submit" class="btn btn-primary" name="mode" value="giveAdmin">Дать админ права</button>
                                <#elseif l.id != 1 && !l.isBlocked()>
                                    <button type="submit" class="btn btn-secondary" name="mode" value="removeAdmin">Забрать админ права</button>
                                </#if>
                            </td>
                        </#if>
                        <#if index == 0 || index == 1>
                            <td>
                            <#if l.isBlocked()>
                                <button type="submit" class="btn btn-success" name="mode" value="removeBan">Разблокировать</button>
                            <#else>
                                <button type="submit" class="btn btn-warning" name="mode" value="giveBan">Заблокировать</button>
                            </#if>
                            </td>
                        </#if>
                        <#if index != 4>
                            <td><button type="submit" class="btn btn-danger" name="mode" value="delete">Удалить</button></td>
                        </#if>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="id" value="${l.id}">
                        <input type="hidden" name="entity"
                               value="<#if index==0>user<#elseif index==1>advert<#elseif index==2>comment<#elseif index==3>review</#if>">
                    </form>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#macro>