<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="w-50 mx-auto my-5">
        <h2>Разместить объявление</h2>
        <#if errors??>
            <div style="color: red">
                <#list errors as error>
                    <p>${error}</p>
                </#list>
            </div>
        </#if>
        <form action="/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Изображение:</label>
                <div class="col-sm-6">
                    <input type="file" name="pic" class="form-control" placeholder="Прикрепите изображение товара"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Заголовок :</label>
                <div class="col-sm-6">
                    <input type="text" name="tittle" class="form-control ${(tittleError??)?string('is-invalid', '')}"
                           value="<#if advert??>${advert.tittle}</#if>" placeholder="Введите заголовок"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Цена :</label>
                <div class="col-sm-6">
                    <input type="number" name="cost" class="form-control ${(costError??)?string('is-invalid', '')}"
                           value="<#if advert?? && advert.cost??>${advert.cost}</#if>" placeholder="Введите цену"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Тип :</label>
                <div class="col-sm-6">
                    <select name="type">
                        <option value=""></option>
                        <#list types as type>
                            <option <#if advert?? && advert.type == type>selected</#if> value="${type}">
                                ${type}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Компания :</label>
                <div class="col-sm-6">
                    <select name="company">
                        <option value=""></option>
                        <#list companies as company>
                            <option <#if advert?? && advert.company == company>selected</#if> value="${company}">
                                ${company}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Город :</label>
                <div class="col-sm-6">
                    <input type="text" name="city" class="form-control ${(cityError??)?string('is-invalid', '')}"
                           value="<#if advert??>${advert.city}</#if>" placeholder="Введите город, в котором находится товар"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Описание :</label>
                <div class="col-sm-6">
                    <input type="text" name="description" maxlength="2000" class="form-control" placeholder="Опишите товар"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Отправить</button>
        </form>
    </div>
</@c.page>