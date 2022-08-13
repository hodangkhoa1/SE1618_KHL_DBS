$(document).ready(function () {
    $(window).bind("scroll", function () {
        var gap = 50;
        if ($(window).scrollTop() > gap) {
            $("header").addClass("active");
        } else {
            $("header").removeClass("active");
        }
    });
});

function setActiveMenuBar() {
    const navLink = document.querySelectorAll(".nav-link");
    document.querySelectorAll(".nav-item").forEach((item) => {
        item.classList.remove("active");
    });
    navLink.forEach((item) => {
        if (item.getAttribute("href") === window.location.pathname) {
            item.parentElement.classList.add("active");
        }
    });
}

function menuToggle() {
    const toggleMenu = document.querySelector(".user-menu");
    toggleMenu.classList.toggle("active");
}

function showSearchBox() {
    const toggleSearch = document.querySelector(".nav__bar-mobile-tool");
    toggleSearch.classList.toggle("active");
}

/* Mobile Menu */
function BlockScrollInUserMenu() {
    const bodyForUserMenu = document.getElementsByTagName("BODY")[0];
    const mobileMenuCheckbox = document.querySelector("#mobile-menu-checkbox");
    if (mobileMenuCheckbox.checked === true) {
        bodyForUserMenu.style.overflowY = "hidden";
    } else {
        bodyForUserMenu.style.overflowY = "scroll";
    }
}