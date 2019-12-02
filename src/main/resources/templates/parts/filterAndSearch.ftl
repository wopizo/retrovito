<#include "security.ftl">
<div class="row">
        <a class="col-sm-12 text-center" href="/create">
            <button class="btn btn-light my-2 mt-4">Разместить объявление</button>
        </a>
    <form action="/main" method="get">
        <div class="col-sm-12 row my-2">
            <label class="col-sm-3 col-form-label">Поиск:</label>
            <div class="col-sm-9">
                <input type="text" name="search" class="form-control searchField" placeholder="Поиск по названию"/>
            </div>
        </div>
        <div class="col-sm-12 row my-2">
            <label class="col-sm-3 col-form-label">От:</label>
            <div class="col-sm-9">
                <input type="number" name="froom" class="form-control numberField" placeholder="Цена от"/>
            </div>
        </div>
        <div class="col-sm-12 row my-2">
            <label class="col-sm-3 col-form-label">До:</label>
            <div class="col-sm-9">
                <input type="number" name="too" class="form-control  numberField" placeholder="Цена до"/>
            </div>
        </div>
        <div class="col-sm-12 row my-2">
            <label class="col-sm-3 col-form-label">Тип:</label>
            <div class="col-sm-9">
                <select name="type">
                    <option value=""></option>
                    <#list types as type>
                        <option value="${type}">
                            ${type}
                        </option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="col-sm-12 row my-2">
            <label class="col-sm-3 col-form-label">Компания:</label>
            <div class="col-sm-9">
                <select name="company">
                    <option value=""></option>
                    <#list companies as company>
                        <option value="${company}">
                            ${company}
                        </option>
                    </#list>
                </select>
            </div>
        </div>
        <div class=" text-center">
            <button type="submit" class="btn btn-primary my-2">Применить</button>
        </div>
    </form>
</div>