<#include "security.ftl">

<nav class="navbar navbar-expand-md navbar-light bg-light">
    <a class="navbar-brand" href="/">Retrovito</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <#if known>
                <#if isAdmin>
                    <li class="nav-item dropdown ">
                        <span class="navbar-text dropdown-toggle ml-4" data-toggle="dropdown">Администратор</span>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Управление пользователями</a></li>
                            <li><a class="dropdown-item" href="#">Управление объявлениями</a></li>
                            <li><a class="dropdown-item" href="#">Тех. поддержка</a></li>
                        </ul>
                    </li>
                </#if>
                <li class="nav-item dropdown ">
                    <span class="navbar-text dropdown-toggle ml-4" data-toggle="dropdown">${name}</span>
                       <ul class="dropdown-menu">
                           <li><a class="dropdown-item" href="#">Моя страница</a></li>
                           <li><a class="dropdown-item" href="#">Личные сообщения</a></li>
                           <li><a class="dropdown-item" href="#">Мои объявления</a></li>
                           <li><a class="dropdown-item" href="#">Избранное</a></li>
                           <li><a class="dropdown-item" href="#">Тех. поддержка</a></li>
                       </ul>
                </li>
                <li class="nav-item ">
                    <form action="/logout" method="post">
                        <button type="submit" class="btn btn-primary ml-4">Выйти</button>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </form>
                </li>
            </#if>
            <#if !known>
                <li class="nav-item ">
                    <a class="nav-link ml-4" href="/login">Войти</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link ml-4" href="/registration">Регистрация</a>
                </li>
            </#if>
        </ul>
    </div>
</nav>