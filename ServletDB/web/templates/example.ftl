<#macro header>
    HEADER
</#macro>
<#macro page_body>
    <h1>hi!!1!</h1>
</#macro>

<@page_body>
    ....                 //это вызов тип
</@page_body>

<#include "example.ftl">
<head></head>

<body>
    <@page_body/>

    <#macro page_body>

    </#macro>
</body>