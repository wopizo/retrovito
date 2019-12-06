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
                <th>Отредактировать</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody>
            <#list list as l>
                <tr>
                    <td>
                        <#if index == 0><a href="/user/${l.id}"><#if l.sname??>${l.sname} </#if>${l.name}<#if l.fname??> ${l.fname}</a></#if>
                        <#elseif index == 1><a href="/advert/${l.id}">${l.tittle}</a>
                        <#elseif index == 2><a href="/advert/${l.advert.id}">${l.message}</a>
                        <#elseif index == 3><a href="/user/${l.userTo.id}">${l.message}</a>
                        <#elseif index == 4><a href="/advert/${l.advert.id}">${l.advert.tittle}</a>
                        </#if>
                    </td>
                    <td>
                        ${l.date}
                    </td>
                    <td>
                        <a href="#"><button class="btn btn-primary">Редактировать</button></a>
                    </td>
                    <td>
                        <a href="#"><button class="btn btn-danger">Удалить</button></a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#macro>