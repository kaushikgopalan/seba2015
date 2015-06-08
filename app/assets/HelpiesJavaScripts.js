var wrap = $("#wrap");

wrap.on("scroll", function(e) {

    if (this.scrollTop > 147) {
        wrap.addClass("fix-search");
    } else {
        wrap.removeClass("fix-search");
    }

});
$(window).scroll(function (argument) {
    var second_navbar = $('#second-navbar')
    var navbar_top_offset = $(window).scrollTop() - second_navbar.offset().top

    var headerImg=$('#headerImg')

    console.log('------------------------------------------')
    console.log($(window).scrollTop())
    console.log(second_navbar.offset().top)
    console.log(navbar_top_offset)
    console.log('asdf')
    console.log(headerImg.offset().top)

    if ($(window).scrollTop() >= second_navbar.height()) {
        second_navbar.addClass("navbar-fixed-top").addClass('nav-fixed').removeClass("navbar-static-top").removeClass('nav-static')
        headerImg.addClass("header-image-small").removeClass("header-image")
    } else {
        second_navbar.addClass("navbar-static-top").addClass('nav-static').removeClass("navbar-fixed-top").removeClass('nav-fixed')
        headerImg.addClass("header-image").removeClass("header-image-small")
    }
});
