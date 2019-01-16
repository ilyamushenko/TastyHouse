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
        <#list orders?keys as key>
            <div class="row mb-4">
                <div class="col-1"></div>
                <#list orders?api.get(key) as order>
                    <#if order?is_first>
                        <div class="rounded-circle col-1 no_one_here text-center">
                            <font class="number_of_table">${order.tableNumber}</font>
                        </div>
                    <#else>
                        <div class="rounded-circle col-1 no_one_here offset-2 text-center">
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

<!-- <@c.nav_and_footer>
<header class="header">
<div class="overlay">
    <div class="container">
        <div class="row mb-4">
            <div class="col text-center">
                <font class="choose_table">Cтолики:</font>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-1"></div>
            <div class="rounded-circle col-1 no_one_here text-center">
                <font class="number_of_table">1</font>
            </div>
            <div class="rounded-circle col-1 dish_is_ready offset-2 text-center">
                <font class="number_of_table">2</font>
            </div>
            <div class="rounded-circle col-1 in_process_of_cooking offset-2 text-center">
                <font class="number_of_table">3</font>
            </div>
            <div class="rounded-circle col-1 in_process_of_cooking offset-2 text-center">
                <font class="number_of_table">4</font>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-1"></div>
            <div class="rounded-circle col-1 dish_is_ready text-center">
                <font class="number_of_table">5</font>
            </div>
            <div class="rounded-circle col-1 no_one_here offset-2 text-center">
                <font class="number_of_table">6</font>
            </div>
            <div class="rounded-circle col-1 in_process_of_cooking offset-2 text-center">
                <font class="number_of_table">7</font>
            </div>
            <div class="rounded-circle col-1 dish_is_ready offset-2 text-center">
                <font class="number_of_table">8</font>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-1"></div>
            <div class="rounded-circle col-1 in_process_of_cooking text-center">
                <font class="number_of_table">9</font>
            </div>
            <div class="rounded-circle col-1 in_process_of_cooking offset-2 text-center">
                <font class="number_of_table">10</font>
            </div>
            <div class="rounded-circle col-1 dish_is_ready offset-2 text-center">
                <font class="number_of_table">11</font>
            </div>
            <div class="rounded-circle col-1 no_one_here offset-2 text-center">
                <font class="number_of_table">12</font>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-1"></div>
            <div class="rounded-circle col-1 in_process_of_cooking text-center">
                <font class="number_of_table">13</font>
            </div>
            <div class="rounded-circle col-1 in_process_of_cooking offset-2 text-center">
                <font class="number_of_table">14</font>
            </div>
            <div class="rounded-circle col-1 dish_is_ready offset-2 text-center">
                <font class="number_of_table">15</font>
            </div>
            <div class="rounded-circle col-1 in_process_of_cooking offset-2 text-center">
                <font class="number_of_table">16</font>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-1"></div>
            <div class="rounded-circle col-1 in_process_of_cooking text-center">
                <font class="number_of_table">17</font>
            </div>
            <div class="rounded-circle col-1 no_one_here offset-2 text-center">
                <font class="number_of_table">18</font>
            </div>
        </div>
    </div>
</div>
</header>
</@c.nav_and_footer> -->