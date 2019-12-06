<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#import "parts/adminView.ftl" as a>

<@c.page>
    <form action="adminShow" method="get">
        <table class="table">
            <tbody>
            <tr class="row">
                <td class="row col-sm-4">
                    <label class="col-sm-3 col-form-label">Поиск:</label>
                    <input type="text" name="search" class="form-control col-sm-9"/>
                </td>
                <td class="row col-sm-3">
                    <label class="col-sm-3 col-form-label">С:</label>
                    <input type="date" name="from" class="form-control col-sm-9"/>
                </td>
                <td class="row col-sm-3">
                    <label class="col-sm-3 col-form-label">По:</label>
                    <input type="date" name="to" class="form-control col-sm-9"/>
                </td>
                <td class="row col-sm-2">
                    <button type="submit" class="btn btn-primary ml-4">Применить</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <@a.adminlView 0 users userCount></@a.adminlView>
    <@a.adminlView 1 adverts advertCount></@a.adminlView>
    <@a.adminlView 2 comments commentCount></@a.adminlView>
    <@a.adminlView 3 reviews reviewCount></@a.adminlView>
    <@a.adminlView 4 deals dealCount></@a.adminlView>
</@c.page>