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
                    <font class="table_name">Столик ${tableNumber}</font>
                </div>
                <div class="row align-items-center ml-2 mt-4 mr-2">
                    <div class="col-2">
                        <img src="/img/cezar.jpg" class="img-fluid">
                    </div>
                    <div class="col-4">
                        <p>
                            <b>Цезарь с курицей</b>
                            <br><font class="status-of-dish">Статус: В ожидании</font>
                            <i class="far fa-clock"></i>
                        </p>
                        <div class="form-check bring_dish">
                            <input class="form-check-input increase_size" type="checkbox" value="" id="defaultCheck1">
                            <label class="form-check-label ml-2" for="defaultCheck1">Отнесено</label>
                        </div>
                    </div>

                    <div class="col-2">
                        <img src="/img/krevetki.jpg" class="img-fluid">
                    </div>
                    <div class="col-4">
                        <p>
                            <b>Салат с креветками</b>
                            <br><font class="status-of-dish">Статус: Готовится</font>
                            <i class="fab fa-gripfire" aria-hidden="true"></i>
                        </p>
                        <div class="form-check bring_dish">
                            <input class="form-check-input increase_size" type="checkbox" value="" id="defaultCheck2">
                            <label class="form-check-label ml-2" for="defaultCheck2">Отнесено</label>
                        </div>
                    </div>
                </div>
                <div class="row ml-2 mt-4 mr-2">
                    <div class="col">
                        <div class="progress time">
                            <div class="progress-bar" role="progressbar" style="width: 25%; background: #3F9384;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                <b>25%</b>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row ml-2 mt-4 mr-2">
                    <div class="col">
                        <font class="order_info">Дата: 24.09.2016</font>
                    </div>
                    <div class="col">
                        <font class="order_info">Время: 13:25</font>
                    </div>
                    <div class="col">
                        <font class="order_info">Итого: 550 ₽</font>
                    </div>
                    <div class="col">
                        <font class="order_info">Оплата: По карте</font>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
</@c.nav_and_footer>