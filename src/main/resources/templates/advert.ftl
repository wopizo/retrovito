<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="row container-fluid my-3">
        <div class="col-md-3 col-sm-12 col-xs-12">
            <#if advert.picture??>
                <img class="profilePicture" src="/img/advImages/${advert.picture}">
            <#else>
                <img class="profilePicture" src="/img/appImages/Empty.png">
            </#if>
        </div>
        <div class="col-md-9 col-sm-12 col-xs-12">
            <h2>${advert.tittle}</h2>
            <h4>О товаре</h4>
            <p>Цена: ${advert.cost}</p>
            <p>Тип: ${advert.type}</p>
            <p>Компания: ${advert.company}</p>
            <p>Город нахождения товара: ${advert.city}</p>
            <p>Дата публикации: ${advert.date}</p>
            <p>Найден покупатель: <#if advert.buyer??>Да<#else>Еще нет</#if></p>
            <p>Состояние объявления: <#if advert.active>Активно<#else>Закрыто</#if></p>
            <#if advert.description??>
                <h4>Комментарии продавца к товару</h4>
                <p>${advert.description}</p>
            </#if>
            <h4>Контакты</h4>
            <p>Выложил объявление: <a href="/user/${advert.author.id}"> ${advert.author.name} </a></p>
            <p>Номер мобильного телефона продавца: ${advert.author.phone}</p>
            <p>Электронная почта продавца: ${advert.author.email}</p>
            <#if known && advert.author.id != user.id>
                <a href="/chat/${advert.author.id}">
                    <button class="btn btn-success m-3">Написать продавцу</button>
                </a>
            <#elseif known>
                <form action="removeAdvert" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="advertId" value="${advert.id}">
                    <button type="submit" class="btn btn-danger m-3">Удалить</button>
                </form>
            </#if>
        </div>
        <div class="col-md-12 col-sm-12 col-xs-12 mt-2">
            <h2>Комментарии</h2>
            <div class="container-fluid p-1 mt-1" id="chatFooter">
                <#if known && !isBlocked>
                    <form action="addComment" method="post">
                        <div class="row">
                            <div class="col-md-9 col-sm-9 col-xs-9">
                                <input type="text" name="message" maxlength="2000" class="form-control messageInput"
                                       placeholder="Введите сообщение"/>
                            </div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="advert" value="${advert.id}">
                            <div class="col-md-3 col-sm-3 col-xs-3">
                                <button type="submit" class="btn btn-dark btn-sm col-sm-3">Отправить</button>
                            </div>
                        </div>
                    </form>
                <#elseif !known>
                    Авторизируйтесь чтоб оставить комментарий
                <#else>
                    Вы не можете оставляться комментарии. Обратитесь в тех поддержку по поводу вашей блокировки
                </#if>
            </div>
            <table class="table">
                <tbody>
                <#if comments??>
                    <#list comments as comment>
                        <tr>
                            <td>
                                <#if comment.userFrom.picture??>
                                    <img class="messagePic" src="/img/userImages/${comment.userFrom.picture}">
                                <#else>
                                    <img class="messagePic" src="/img/appImages/UserEmpty.jpg">
                                </#if>
                            </td>
                            <td>
                                <a href="/user/${comment.userFrom.id}">${comment.userFrom.name}</a>
                            </td>
                            <td>
                                ${comment.message}<#if comment.edited?? && comment.edited>(Редактировано)</#if>
                            </td>
                            <td>
                                ${comment.date}
                                <#if comment.userFrom.id == user.id>
                                    <br/>
                                    <a data-toggle="collapse" href="#collapse${comment.id}">
                                        <button class="btn btn-info">Редактировать</button>
                                    </a>
                                    <br/>
                                    <form action="removeComment" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <button type="submit" class="btn btn-danger">Удалить</button>
                                    </form>
                                </#if>
                            </td>
                        </tr>
                        <#if comment.userFrom.id == user.id>
                            <tr id="collapse${comment.id}" class="collapse">
                                <form action="editComment" method="post">
                                    <td colspan="3">
                                        <input type="text" name="message" class="form-control"
                                               value="${comment.message}" placeholder="Введите текст комментария"/>
                                    </td>
                                    <td>
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="submit" class="btn btn-info">Сохранить</button>
                                    </td>
                                </form>
                            </tr>
                        </#if>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>