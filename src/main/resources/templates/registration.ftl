<#import "parts/common.ftl" as c>
<@c.page>
    Add new user
    ${message?ifExists}
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Name : <input type="text" name="name"/> </label></div>
        <div><label> Second name : <input type="text" name="sname"/> </label></div>
        <div><label> Father name : <input type="text" name="fname"/> </label></div>
        <div><label> Email : <input type="text" name="email"/> </label></div>
        <div><label> Phone : <input type="text" name="phone"/> </label></div>
        <div><label> City : <input type="text" name="city"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Sign In"/></div>
    </form>
</@c.page>