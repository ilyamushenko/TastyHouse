$(document).ready(function()
{
    var body_height = $(document.body).height();
    var screen_height = $(window).height();
    if (body_height < screen_height)
        $('footer').addClass("fixed-bottom");
    else
        $('footer').removeClass("fixed-bottom");
});