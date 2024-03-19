(function ($) {
    "use strict";

    // 페이지 로딩 애니메이션
    $(window).on('load', function () {
        $('#js-preloader').addClass('loaded');
    });

    // WOW JS 초기화
    if ($(".wow").length) {
        let wow = new WOW({
            boxClass: 'wow', // Animated element css class (default is wow)
            animateClass: 'animated', // Animation css class (default is animated)
            offset: 20, // Distance to the element when triggering the animation (default is 0)
            mobile: true, // Trigger animations on mobile devices (default is true)
            live: true // Act on asynchronously loaded content (default is true)
        });
        wow.init();
    }

 // 헤더 색상 변경
    $(window).scroll(function () {
    let scroll = $(window).scrollTop();
    let headerHeight = $('header').outerHeight(); // 헤더의 높이를 가져옵니다.

    if (scroll >= headerHeight) { // 스크롤 위치가 헤더의 높이를 넘어갈 때
        $("header").addClass("background-header");
    } else {
        $("header").removeClass("background-header");
    }
});

    // 필터 클릭 이벤트
    $('.filters ul li').click(function () {
        $('.filters ul li').removeClass('active');
        $(this).addClass('active');

        let data = $(this).attr('data-filter');
        $grid.isotope({
            filter: data
        });
    });

    // Isotope 그리드 설정
    let $grid = $(".grid").isotope({
        itemSelector: ".all",
        percentPosition: true,
        masonry: {
            columnWidth: ".all"
        }
    });

// 창 크기 조정 이벤트
$(window).resize(function () {
    let width = $(window).width();
    let headerHeight = $('header').outerHeight();
    if (width > 992 && $('.menu-trigger').hasClass('active')) {
        $('.header-area .nav').slideDown(200);
    } else if (width > 992 && !$('.menu-trigger').hasClass('active')) {
        $('.header-area .nav').css('display', 'block');
    } else if (width < 992 && $(window).width() > 992) {
        location.reload();
    }
});

    // 메뉴 탭 이벤트
    $(document).on("click", ".naccs .menu div", function () {
        let numberIndex = $(this).index();

        if (!$(this).is("active")) {
            $(".naccs .menu div").removeClass("active");
            $(".naccs ul li").removeClass("active");

            $(this).addClass("active");
            $(".naccs ul").find("li:eq(" + numberIndex + ")").addClass("active");

            let listItemHeight = $(".naccs ul").find("li:eq(" + numberIndex + ")").innerHeight();
            $(".naccs ul").height(listItemHeight + "px");
        }
    });

    // 특징 Owl Carousel 설정
    $('.owl-features').owlCarousel({
        items: 3,
        loop: true,
        dots: false,
        nav: true,
        autoplay: true,
        margin: 30,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 2
            },
            1200: {
                items: 3
            },
            1800: {
                items: 3
            }
        }
    });

    // 컬렉션 Owl Carousel 설정
    $('.owl-collection').owlCarousel({
        items: 3,
        loop: true,
        dots: false,
        nav: true,
        autoplay: true,
        margin: 30,
        responsive: {
            0: {
                items: 1
            },
            800: {
                items: 2
            },
            1000: {
                items: 3
            }
        }
    });

    // 배너 Owl Carousel 설정
    $('.owl-banner').owlCarousel({
        items: 1,
        loop: true,
        dots: false,
        nav: true,
        autoplay: true,
        margin: 30,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            1000: {
                items: 1
            }
        }
    });

    // 메뉴 토글
    if ($('.menu-trigger').length) {
        $(".menu-trigger").on('click', function () {
            $(this).toggleClass('active');
            $('.header-area .nav').slideToggle(200);
        });
    }

    // 페이지 내부 링크 클릭 이벤트
    $('.scroll-to-section a[href*=\\#]:not([href=\\#])').on('click', function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            let target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                let width = $(window).width();
                if (width < 991) {
                    $('.menu-trigger').removeClass('active');
                    $('.header-area .nav').slideUp(200);
                }
                $('html,body').animate({
                    scrollTop: (target.offset().top) - 80
                }, 700);
                return false;
            }
        }
    });

    // 페이지 스크롤 이벤트
    $(document).ready(function () {
        $(document).on("scroll", onScroll);

        // 스무스 스크롤
        $('.scroll-to-section a[href^="#"]').on('click', function (e) {
            e.preventDefault();
            $(document).off("scroll");

            $('.scroll-to-section a').each(function () {
                $(this).removeClass('active');
            });
            $(this).addClass('active');

            let target = this.hash;
            let targetElem = $(this.hash);
            $('html, body').stop().animate({
                scrollTop: (targetElem.offset().top) - 79
            }, 500, 'swing', function () {
                window.location.hash = target;
                $(document).on("scroll", onScroll);
            });
        });
    });

    // 스크롤 이벤트 처리 함수
    function onScroll(event) {
        let scrollPos = $(document).scrollTop();
        $('.nav a').each(function () {
            let currLink = $(this);
            let href = currLink.attr("href");
            if (href) {
                let refElement = $(href);
                if (refElement.length > 0) {
                    if (refElement.position() && refElement.position().top <= scrollPos && refElement.position().top + refElement.height() > scrollPos) {
                        $('.nav ul li a').removeClass("active");
                        currLink.addClass("active");
                    } else {
                        currLink.removeClass("active");
                    }
                }
            }
        });

        // 헤더 색상 변경 원복
        let box = $('.header-text').height();
        let header = $('header').height();
        if (scrollPos < box - header) {
            $("header").removeClass("background-header");
        }
    }

    // 페이지 로딩 애니메이션
    $(window).on('load', function () {
        if ($('.cover').length) {
            $('.cover').parallax({
                imageSrc: $('.cover').data('image'),
                zIndex: '1'
            });
        }

        $("#preloader").animate({
            'opacity': '0'
        }, 600, function () {
            setTimeout(function () {
                $("#preloader").css("visibility", "hidden").fadeOut();
            }, 300);
        });
    });

    // 메뉴 드롭다운 설정
    const dropdownOpener = $('.main-nav ul.nav .has-sub > a');
    if (dropdownOpener.length) {
        dropdownOpener.each(function () {
            let _this = $(this);
            _this.on('tap click', function (e) {
                let thisItemParent = _this.parent('li');
                let thisItemParentSiblingsWithDrop = thisItemParent.siblings('.has-sub');
                if (thisItemParent.hasClass('has-sub')) {
                    let submenu = thisItemParent.find('> ul.sub-menu');
                    if (submenu.is(':visible')) {
                        submenu.slideUp(450, 'easeInOutQuad');
                        thisItemParent.removeClass('is-open-sub');
                    } else {
                        thisItemParent.addClass('is-open-sub');
                        if (thisItemParentSiblingsWithDrop.length === 0) {
                            thisItemParent.find('.sub-menu').slideUp(400, 'easeInOutQuad', function () {
                                submenu.slideDown(250, 'easeInOutQuad');
                            });
                        } else {
                            thisItemParent.siblings().removeClass('is-open-sub').find('.sub-menu').slideUp(250, 'easeInOutQuad', function () {
                                submenu.slideDown(250, 'easeInOutQuad');
                            });
                        }
                    }
                }
                e.preventDefault();
            });
        });
    }
})(window.jQuery);