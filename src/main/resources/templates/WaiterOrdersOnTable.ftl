<#import "parts/Common.ftl" as c>

<@c.scripts_and_styles title="Заказы у столика">
    <link rel="stylesheet" type="text/css" href="/css/waiter_page_orders.css"/>
</@c.scripts_and_styles>

<@c.nav_and_footer>
<header class="header">
    <div class="overlay">
        <div class="container">
            <div class="table mt-5">
                <div class="table_title text-center">
                    <font class="table_name">Столик ${order.tableNumber}</font>
                </div>
                <#list dishes as key, value>
                    <div class="row align-items-center ml-2 mt-4 mr-2">
                    <#list value as dish>
                        <div class="col-2">
                            <img src="${dish.imgUrl}" class="img-fluid">
                        </div>
                        <div class="col-4">
                            <p>
                                <b>${dish.name}</b>
                                <br><font class="status-of-dish">Статус: ${order.dishesFromOrder[dish?index].status}</font>
                                <#switch order.dishesFromOrder[dish?index].status>
                                    <#case "В ожидании">
                                        <i class="far fa-clock"></i>
                                        <#break>
                                    <#case "Готовится">
                                        <i class="fab fa-gripfire"></i>
                                        <#break>
                                    <#case "Готово">
                                        <i class="fas fa-check"></i>
                                        <#break>
                                </#switch>
                            </p>
                            <div class="form-check bring_dish">
                                <input class="form-check-input increase_size" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label ml-2" for="defaultCheck1">Отнесено</label>
                            </div>
                        </div>
                    </#list>
                    </div>
                </#list>
                <div class="row ml-2 mt-4 mr-2">
                    <div class="col">
                        <div class="progress time">
                            <div class="progress-bar" role="progressbar" style="width: ${percentOfReady}%; background: #3F9384;" aria-valuenow="${percentOfReady}" aria-valuemin="0" aria-valuemax="100">
                                <b>${percentOfReady}%</b>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row ml-2 mt-4 mr-2">
                    <div class="col">
                        <font class="order_info">Дата: ${order.dateOrders?date?string["dd.MM.yyyy"]}</font>
                    </div>
                    <div class="col">
                        <font class="order_info">Время: ${order.dateOrders?time?string["HH:mm"]}</font>
                    </div>
                    <div class="col">
                        <font class="order_info">Итого: ${totalPrice} ₽</font>
                    </div>
                    <div class="col">
                        <font class="order_info">Оплата: ${order.typePayment.title}</font>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
</@c.nav_and_footer>