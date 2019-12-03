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
            <p>Рейтинг: ${rating}</p>
            <#if usr.id != user.id><a href="/chat/${usr.id}">
                    <button class="btn btn-success m-3">Написать</button></a></#if>
        </div>
        <div class="col-md-12 col-sm-12 col-xs-12 mt-2">
            <h2>Отзывы</h2>
            <div class="container-fluid p-1 mt-1" id="chatFooter">
                <form action="addReview" method="post">
                    <div class="row">
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input type="text" name="message" class="form-control messageInput"
                                   placeholder="Введите сообщение"/>
                        </div>
                        <div class="col-md-1 col-sm-1 col-xs-1">
                                <select name="mark">
                                    <option selected value="1">Лайк</option>
                                    <option value="0">Дизлайк</option>
                                </select>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="userTo" value="${usr.id}">
                        <div class="col-md-3 col-sm-3 col-xs-3">
                            <button type="submit" class="btn btn-dark btn-sm col-sm-3">Отправить</button>
                        </div>
                    </div>
                </form>
            </div>
            <#if reviews??>
                <#list reviews as review>
                    <div class="col-md-12 col-sm-12 col-xs-12 my-1 chat">
                        <#if review.userFrom.picture??>
                            <img class="chatImg" src="/img/userImages/${review.userFrom.picture}">
                        <#else>
                            <img class="chatImg" src="/img/appImages/UserEmpty.jpg">
                        </#if>
                        <a href="/user/${review.userFrom.id}">${review.userFrom.name}</a>
                        <div class="viewMessage">
                            ${review.message}
                        </div>
                        <#if review.mark>
                            Лайк
                        <#else>
                            Дизлайк
                        </#if>
                        (${review.date})
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</@c.page>