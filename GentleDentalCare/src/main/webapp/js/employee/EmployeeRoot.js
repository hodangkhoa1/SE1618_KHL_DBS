PrintCopyRight();

function PrintCopyRight() {
    console.log("%cHello! \ud83d\ude4b", "color: #29c4a9;font-size: 16px;font-weight: 600;"),
            console.log("%cGentle Dental Care front-end was built with HTML, CSS, and lots of love. \n\nGentle Dental Care back-end was built with SQL Server, Java Web and lots of love. \n\n\ud83d\udc49 Want to learn with us? Check out ".concat(window.location.origin, "/GentleDentalCare/home"), "color: #29c4a9;font-size: 14px;");
}

const loader = document.querySelector("#preloader");
window.addEventListener("load", function () {
    loader.style.display = "none";
});