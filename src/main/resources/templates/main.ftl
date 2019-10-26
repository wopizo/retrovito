<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div>
        Hello, ${name}
        <a href="/login">Sign in</a>
        <a href="/registration">Add new user</a>
    </div>

    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.page>