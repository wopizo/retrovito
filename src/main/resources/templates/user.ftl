<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row container-fluid my-3">
        <div class="col-md-3 col-sm-12 col-xs-12">
            <#if usr.picture??>
                <img class="profilePicture" src="/img/userImages/${usr.picture}">
            <#else>
                <img class="profilePicture" src="/img/appImages/userEmpty.jpg">
            </#if>
        </div>
        <div class="col-md-9 col-sm-12 col-xs-12">
            <h2>${usr.name} <#if usr.sname??>${usr.sname}</#if> <#if usr.fname??>${usr.fname}</#if></h2>
            <h4>О пользователе</h4>
            <p>Город проживания: ${usr.city}</p>
            <p>Дата регистрации: <#if usr.date??>${usr.date}<#else>Неизвестно</#if></p>
            <h4>Контакты</h4>
            <p>Электронная почта: ${usr.email}</p>
            <p>Номер мобильного телефона: ${usr.phone}</p>
            <#if usr.id != user.id><a href="#"><button class="btn btn-success m-3">Написать</button></a></#if>
        </div>
        <div class="col-md-12 col-sm-12 col-xs-12 mt-2">
            <h2>Отзывы</h2>
        </div>
    </div>
</@c.page>