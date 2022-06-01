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

function menuToggle() {
  const toggleMenu = document.querySelector(".user-menu");
  toggleMenu.classList.toggle("active");
}