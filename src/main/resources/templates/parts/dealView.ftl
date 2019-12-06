<#macro dealView list index>
    <h2><a data-toggle="collapse" href="#collapse${index}"><#if index == 0>Продажи<#else>Покупки</#if></a></h2>
    <div id="collapse${index}" class="collapse">
        <table class="table">
            <thead>
            <tr>
                <th>Изображение</th>
                <th>Название</th>
                <th>Стоимость</th>
                <th>Дата заключения</th>
            </tr>
            </thead>
            <tbody>
            <#list list as l>
                <tr>
                    <td><#if l.advert.picture??>
                            <img class="messagePic" src="/img/advImages/${l.advert.picture}">
                        <#else>
                            <img class="messagePic" src="/static/img/appImages/Empty.png">
                        </#if>
                    </td>
                    <td><a href="/advert/${l.advert.id}">${l.advert.tittle}</a></td>
                    <td>
                        ${l.advert.cost}
                    </td>
                    <td>
                        ${l.date}
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#macro>