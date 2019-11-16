<#import "parts/common.ftl" as c>
<@c.page>
    <div class="w-50 mx-auto my-5">
    <h2>Регистрация</h2>
    ${message?ifExists}
    <form action="/registration" method="post">
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
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Имя :</label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" placeholder="Введите ваше имя"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Фамилия :</label>
            <div class="col-sm-6">
                <input type="text" name="sname" class="form-control" placeholder="Введите вашу фамилию"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Отчество :</label>
            <div class="col-sm-6">
                <input type="text" name="fname" class="form-control" placeholder="Введите ваше отчество"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Email :</label>
            <div class="col-sm-6">
                <input type="text" name="email" class="form-control" placeholder="Введите адрес вашей электронной почты"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Телефон :</label>
            <div class="col-sm-6">
                <input type="text" name="phone" class="form-control" placeholder="Введите номер вашего телефона"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Город :</label>
            <div class="col-sm-6">
                <input type="text" name="city" class="form-control" placeholder="Введите город, в котором живете"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Отправить</button>
    </form>
    </div>
</@c.page>