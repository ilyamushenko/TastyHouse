<#import "parts/Common.ftl" as c>

<@c.scripts_and_styles title="Столики для официанта">
    <link rel="stylesheet" type="text/css" href="/css/waiter_page_tables.css"/>
</@c.scripts_and_styles>

<@c.nav_and_footer>
<header class="header">
<div class="overlay">
    <div class="container">
        <div class="row mb-4">
            <div class="col text-center">
                <font class="choose_table">Cтолики:</font>
            </div>
        </div>
        <#list orders as key, value>
            <div class="row mb-4">
                <div class="col-1"></div>
                <#list value as order>
                    <#if order?is_first>
                        <div class="rounded-circle col-1 ${order.statuses.title} text-center" onclick="window.location.href = '/orders/${order.tableNumber}'">
                            <font class="number_of_table">${order.tableNumber}</font>
                        </div>
                    <#else>
                        <div class="rounded-circle col-1 ${order.statuses.title} offset-2 text-center" onclick="window.location.href = '/orders/${order.tableNumber}'">
                            <font class="number_of_table">${order.tableNumber}</font>
                        </div>
                    </#if>
                </#list>
            </div>
        </#list>
    </div>
</div>
</header>
</@c.nav_and_footer>