<#macro scripts_and_styles title>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link rel="icon" href="/favicon.ico?v1" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/css/libs/fonts/font.css"/>
    <link rel="stylesheet" href="/css/libs/bootstrap_css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/libs/fontawesome_css/css/all.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/waiter_page.css"/>
    <#nested>
</head>
</#macro>

<#macro nav_and_footer>
<body>
<nav class="navbar navbar-expand">
    <div class="container">
        <img src="/img/tasty-house-logo.png" class="tasty-house">
        <font class="title">Официант</font>
        <div>
            <a><u class="user">Victor</u></a>
            <div class="dropdown float-right ml-2">
                <span style="color: white">
                    <i class="fas fa-bars menu" data-toggle="dropdown"></i>
                </span>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="#">Link 1</a>
                    <a class="dropdown-item" href="#">Link 2</a>
                    <a class="dropdown-item" href="#">Link 3</a>
                </div>
            </div>
        </div>
    </div>
</nav>

<#nested>

<footer class="footer fixed-bottom">
    <div class="container">
        <font class="footer-content">@2018 TastyHouse</font>
    </div>
</footer>
<script src="/js/libs/jquery_js/jquery-3.3.1.min.js"></script>
<script src="/js/libs/popper_js/popper.min.js"></script>
<script src="/js/libs/bootstrap_js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/change_footer_location.js"></script>
</body>
</html>
</#macro>