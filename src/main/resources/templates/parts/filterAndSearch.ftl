<#include "security.ftl">
<div xmlns="http://www.w3.org/1999/html">
    <table>
        <tbody>
        <tr>
            <td colspan="2" class="text-center">
                <a class="col-sm-12 text-center" href="/create">
                    <button class="btn btn-primary my-4">Разместить объявление</button>
                </a>
            </td>
        </tr>
        <form action="/main" method="get">
            <tr>
                <td><label class="col-form-label">Поиск:</label></td>
                <td><input type="text" name="search" class="form-control" placeholder="Поиск по названию"/></td>
            </tr>
            <tr>
                <td><label class="col-form-label">Сортировать:</label></td>
                <td>
                    <select name="sort">
                        <option selected value="date DESC">по дате(от новых к старым)</option>
                        <option value="date ASC">по дате(от старых к новым)</option>
                        <option value="cost DESC">по цене(от дорогих к дешевым)</option>
                        <option value="cost ASC">по цене(от дешевых к дорогим)</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label class="col-form-label">От:</label></td>
                <td><input type="number" name="froom" class="form-control" placeholder="Цена от"/></td>
            </tr>
            <tr>
                <td><label class="col-form-label">До:</label></td>
                <td><input type="number" name="too" class="form-control" placeholder="Цена до"/></td>
            </tr>
            <tr>
                <td><label class="col-form-label">Тип:</label></td>
                <td>
                    <select name="type">
                        <option value=""></option>
                        <#list types as type>
                            <option value="${type}">
                                ${type}
                            </option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label class="col-form-label">Компания:</label></td>
                <td>
                    <select name="company">
                        <option value=""></option>
                        <#list companies as company>
                            <option value="${company}">
                                ${company}
                            </option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="text-center">
                    <button type="submit" class="btn btn-light mt-2">Применить</button>
                </td>
            </tr>
        </form>
        </tbody>
    </table>
</div>