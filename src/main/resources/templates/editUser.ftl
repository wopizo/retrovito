<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="w-50 mx-auto my-5">
        <h2>Редактировать профиль</h2>
        <#if error?? && error>
                <div style="color: red">
                    Пользователь с такой почтой уже зарегестрирован!
                </div>
        </#if>
        <form action="/user/editUser" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Фото:</label>
                <div class="col-sm-6">
                    <input type="file" name="pic" class="form-control" placeholder="Прикрепите фаше фото"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Имя :</label>
                <div class="col-sm-6">
                    <input type="text" name="name" class="form-control" placeholder="Введите ваше имя" value="${usr.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Фамилия :</label>
                <div class="col-sm-6">
                    <input type="text" name="sname" class="form-control" placeholder="Введите вашу фамилию" <#if usr.sname??>value="${usr.sname}"</#if>/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Отчество :</label>
                <div class="col-sm-6">
                    <input type="text" name="fname" class="form-control" placeholder="Введите ваше отчество" <#if usr.fname??>value="${usr.fname}"</#if>/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Email :</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="Введите адрес вашей электронной почты" <#if usr.email??>value="${usr.email}"</#if>/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Телефон :</label>
                <div class="col-sm-6">
                    <input type="text" name="phone" class="form-control" placeholder="Введите номер вашего телефона" <#if usr.phone??>value="${usr.phone}"</#if>/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Город :</label>
                <div class="col-sm-6">
                    <input type="text" name="city" class="form-control" placeholder="Введите город, в котором живете" <#if usr.city??>value="${usr.city}"</#if>/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Изменить</button>
        </form>
    </div>
</@c.page>