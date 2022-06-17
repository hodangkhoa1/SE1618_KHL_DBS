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

/* Search With MicroPhone */
function activeMicrophone() {
    const SpeechRecognition =
            window.SpeechRecognition || window.webkitSpeechRecognition,
            microphoneHeader = document.querySelector("#microphone-header__prompt"),
            microphoneFooterLabel = document.querySelector("#microphone-footer-label"),
            microphoneBodyText = document.querySelector("#microphone-body-text");

    if (SpeechRecognition !== undefined) {
        let recognition = new SpeechRecognition();

        recognition.onstart = () => {
            microphoneHeader.innerHTML = "Listening...";
            microphoneFooterLabel.innerHTML = "";
            microphoneBodyText.innerHTML = "";
        };

        recognition.onspeechend = () => {
            microphoneHeader.innerHTML = "Microphone is off. Please speak again.";
            microphoneFooterLabel.innerHTML = "Tap the microphone to try again";
            recognition.stop();
        };

        recognition.onresult = (result) => {
            microphoneBodyText.innerHTML = `${result.results[0][0].transcript}`;
            console.log(`${result.results[0][0].transcript}`);
        };

        recognition.start();
    }
}

function showMicrophoneBox() {
    const bodyForMicrophoneBox = document.getElementsByTagName("BODY")[0];
    const microphoneWrapper = document.querySelector("#microphone-wrapper");

    microphoneWrapper.classList.add("active");
    bodyForMicrophoneBox.style.overflowY = "hidden";
    activeMicrophone();
}

function hideMicrophoneBox() {
    const bodyForMicrophoneBox = document.getElementsByTagName("BODY")[0];
    const microphoneWrapper = document.querySelector("#microphone-wrapper");

    microphoneWrapper.classList.remove("active");
    bodyForMicrophoneBox.style.overflowY = "scroll";
}
/* End Search With MicroPhone */

/* Search With MicroPhone In Mobile */
function activeMicrophoneMobile() {
    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition,
            microphoneHeader = document.querySelector("#mobile-header__prompt"),
            microphoneFooterLabel = document.querySelector("#mobile__microphone-footer-label"),
            microphoneBodyText = document.querySelector("#mobile-microphone-body-text");

    if (SpeechRecognition !== undefined) {
        let recognition = new SpeechRecognition();

        recognition.onstart = () => {
            microphoneHeader.innerHTML = "Listening...";
            microphoneFooterLabel.innerHTML = "";
            microphoneBodyText.innerHTML = "";
        };

        recognition.onspeechend = () => {
            microphoneHeader.innerHTML = "Microphone is off. Please speak again.";
            microphoneFooterLabel.innerHTML = "Tap the microphone to try again";
            recognition.stop();
        };

        recognition.onresult = (result) => {
            microphoneBodyText.innerHTML = `${result.results[0][0].transcript}`;
            console.log(`${result.results[0][0].transcript}`);
        };

        recognition.start();
    }
}

function showMicrophoneBoxMobile() {
    const bodyForMicrophoneBox = document.getElementsByTagName("BODY")[0];
    const microphoneWrapper = document.querySelector("#mobile__microphone-wrapper");

    microphoneWrapper.classList.add("active");
    bodyForMicrophoneBox.style.overflowY = "hidden";
    activeMicrophoneMobile();
}

function hideMicrophoneBoxMobile() {
    const bodyForMicrophoneBox = document.getElementsByTagName("BODY")[0];
    const microphoneWrapper = document.querySelector("#mobile__microphone-wrapper");

    microphoneWrapper.classList.remove("active");
    bodyForMicrophoneBox.style.overflowY = "scroll";
}

function searchName(valueSearch, urlServlet) {
    const textSearch = valueSearch.value;
    const viewMore = document.querySelector(".view-more");

    $.ajax({
        url: urlServlet,
        type: "get",
        data: {
            search: textSearch
        },
        success: function (data) {
            const newsList = document.querySelector("#return-list");
            newsList.innerHTML = data;
            viewMore.style.display = "none";
        }
    });
}

function showLoadMoreButton() {
    const viewMore = document.querySelector(".view-more");
    viewMore.style.display = "block";
}