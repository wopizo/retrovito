<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getName()
        id = user.getId()
        isAdmin = user.isAdmin()
        isBlocked = user.isBlocked()
    >
    <#else>
    <#assign
        name = "unknown"
        isAdmin = false
        id = -100
        isBlocked = true
    >
</#if>
