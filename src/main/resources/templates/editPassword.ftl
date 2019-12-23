<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="w-50 mx-auto my-5">
        <h2>Изменение пароля</h2>
        <#if error?? && error>
            <div style="color: red">
                Поля ввода нового пароля не совпадают или пусты
            </div>
        </#if>
        <form action="/user/editPassword" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Новый пароль :</label>
                <div class="col-sm-6">
                    <input type="password" name="new1" class="form-control" placeholder="Введите новый пароль"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Повторите новый :</label>
                <div class="col-sm-6">
                    <input type="password" name="new2" class="form-control" placeholder="Повторите новый"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Изменить</button>
        </form>
    </div>
</@c.page>