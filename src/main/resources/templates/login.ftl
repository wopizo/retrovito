<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Авторизация</h2>
    <form action="/login" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Логин :</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Введите логин"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Пароль :</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Введите пароль"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <a href="/registration">Регистрация</a>
</@c.page>