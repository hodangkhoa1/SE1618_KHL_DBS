$(document).ready(function () {
    const progressPath = document.querySelector(".progress-scroll path");
    const pathLength = progressPath.getTotalLength();

    progressPath.style.transition = progressPath.style.WebkitTransition = "none";
    progressPath.style.strokeDasharray = pathLength + " " + pathLength;
    progressPath.style.strokeDashoffset = pathLength;
    progressPath.getBoundingClientRect();
    progressPath.style.transition = progressPath.style.WebkitTransition =
            "stroke-dashoffset 10ms linear";

    const updateProgress = function () {
        const scroll = $(window).scrollTop();
        const height = $(document).height() - $(window).height();
        progressPath.style.strokeDashoffset =
                pathLength - (scroll * pathLength) / height;
    };

    updateProgress();
    $(window).scroll(updateProgress);

    const offset = 50;
    const duration = 550;

    jQuery(window).on("scroll", function () {
        if (jQuery(this).scrollTop() > offset) {
            jQuery(".progress-scroll").addClass("active-progress");
        } else {
            jQuery(".progress-scroll").removeClass("active-progress");
        }
    });

    jQuery(".progress-scroll").on("click", function (event) {
        event.preventDefault();
        jQuery("html, body").animate({scrollTop: 0}, duration);
        return false;
    });
});
