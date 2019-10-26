<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row">
        <div class="col-md-3 mt-3" style="background-color: bisque">
            <a href="/create"><button class="btn btn-danger m-3">Разместить объявление</button></a>
            <span id="qwe321">Еще тут будут поиск и фильтры</span>
        </div>
        <div class="col-md-9">
            <div class="row">
                <#if adverts??>
                    <#list adverts as advert>
                        <div class="col-md-3 adv m-3">
                            <div>
                                <#if advert.picture??>
                                    <img src="/img/${advert.picture}">
                                    <#else>
                                        <b>Изображение не найдено</b>
                                </#if>
                            </div>
                            <div>
                            <p><h3>${advert.tittle}</h3></p>
                            <p>Цена: ${advert.cost}</p>
                            <p>Город: ${advert.city}</p>
                            <p>Разместил: ${advert.author.getName()}</p>
                            </div>
                        </div>
                    </#list>
                <#else>
                    Объявлений не найдено
                </#if>
            </div>
        </div>
    </div>
</@c.page>