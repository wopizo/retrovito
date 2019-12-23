<#import "parts/common.ftl" as c>

<@c.page>
    <div class="w-50 mx-auto mt-5">
        <h2>Восстановление аккаунта</h2>
        <form action="/restore" method="post">
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Почта :</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="Введите почтовый адрес"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-info">Восстановить</button>
        </form>
    </div>
</@c.page>