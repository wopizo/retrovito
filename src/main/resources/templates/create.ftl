<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="w-50 mx-auto my-5">
<#--    Позже создать макрос для инпутов-->
    <h2>Разместить объявление</h2>
    <form action="/create" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Изображение:</label>
            <div class="col-sm-6">
                <input type="file" name="picture" class="form-control" placeholder="Прикрепите изображение товара"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Заголовок :</label>
            <div class="col-sm-6">
                <input type="text" name="tittle" class="form-control" placeholder="Введите заголовок"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Цена :</label>
            <div class="col-sm-6">
                <input type="text" name="cost" class="form-control" placeholder="Введите цену"/>
            </div>
        </div>
<#--        После поменять на значения из энамов-->
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Тип :</label>
            <div class="col-sm-6">
                <input type="text" name="type" class="form-control" placeholder="Введите тип товара"/>
            </div>
        </div>
<#--        Тоже поменять-->
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Компания :</label>
            <div class="col-sm-6">
                <input type="text" name="company" class="form-control" placeholder="Введите компанию консоли"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Город :</label>
            <div class="col-sm-6">
                <input type="text" name="city" class="form-control" placeholder="Введите город, в котором находится товар"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Описание :</label>
            <div class="col-sm-6">
                <input type="text" name="description" class="form-control" placeholder="Опишите товар"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-dark">Отправить</button>
    </form>
    </div>
</@c.page>