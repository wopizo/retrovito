<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row">
        <div class="col-md-3 col-sm-12 col-xs-12 bg-secondary text-light text-center">
            <a href="/create"><button class="btn btn-light m-3">Разместить объявление</button></a>
            <p id="qwe321">Еще тут будут поиск и фильтры</p>
        </div>
        <div class="col-md-9">
            <div class="row">
                <#if adverts??>
                    <#list adverts as advert>
                        <div class="col-md-3 col-sm-6 col-xs-12 card m-4 px-0" style="width: 18rem;">
                            <div class="card-header bg-secondary text-light">
                                <h5 class="card-title"><a class="text-light" href="advert/${advert.id}">${advert.tittle}</a></h5>
                            </div>
                            <#if advert.picture??>
                                <img class="card-img-top"  src="/img/advImages/${advert.picture}">
                            <#else>
                                <img class="card-img-top"  src="/static/img/appImages/Empty.png">
                            </#if>
                            <div class="card-body">
                            <span class="card-text">
                                Цена: ${advert.cost}<br/>
                                Город: ${advert.city}</span>
                            </div>
                            <div class="card-footer text-dark">
                                Разместил: <a href="user/${advert.author.id}"> ${advert.author.name}</a>
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