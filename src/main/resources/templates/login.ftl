<#import "parts/common.ftl" as c>

<@c.page>
    <div class="w-50 mx-auto mt-5">
    <h2>Авторизация</h2>
        <#if message??>
            <#if message == 1>
            <div style="color: green">
                Пользователь успешно активирован!
            </div>
            <#elseif message == 2>
                <div style="color: green">
                    Ваш логин и новый пароль отправлен на вашу почту!
                </div>
            <#else>
            <div style="color: red">
                Пользователь не найден!
            </div>
            </#if>
        </#if>
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
        <button type="submit" class="btn btn-dark">Войти</button>
    </form>
        <a href="/restore">Забыли логин или пароль?</a><br/>
        <a href="/registration">Регистрация</a>
    </div>
</@c.page>