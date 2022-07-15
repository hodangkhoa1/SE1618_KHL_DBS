const viewMore = document.querySelector(".view-more");

function loadMore(totalService, urlServlet) {
    const serviceAmount = document.querySelectorAll(".service-amount").length;
    viewMore.innerHTML = `
                    <div class="spinner-loader">
                        <div class="spinner-border text-primary" role="status">
                            <span class="visually-hidden">Loading...</span>
                        </div>
                        <p class="spinner-title">Please Wait ...</p>
                    </div>
                `;

    $.ajax({
        url: urlServlet,
        type: "get",
        data: {
            serviceExits: serviceAmount
        },
        success: function (data) {
            const serviceList = document.querySelector("#return-list");
            serviceList.innerHTML += data;
        }
    }).done(() => {
        if (totalService <= (serviceAmount + 9)) {
            viewMore.style.display = "none";
        } else {
            viewMore.innerHTML = `<button onclick="loadMore('${totalService}', '${urlServlet}')" class="button-view">Load more</button>`;
        }
    });
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

            $.ajax({
                url: "http://localhost:8080/GentleDentalCare/service",
                type: "get",
                data: {
                    search: `${result.results[0][0].transcript}`
                },
                success: function (data) {
                    const returnList = document.querySelector("#return-list");
                    returnList.innerHTML = data;
                    if (viewMore !== null) {
                        viewMore.style.display = "none";
                    }
                    hideMicrophoneBox();
                }
            });
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

            $.ajax({
                url: "http://localhost:8080/GentleDentalCare/service",
                type: "get",
                data: {
                    search: `${result.results[0][0].transcript}`
                },
                success: function (data) {
                    const returnList = document.querySelector("#return-list");
                    returnList.innerHTML = data;
                    if (viewMore !== null) {
                        viewMore.style.display = "none";
                    }
                    hideMicrophoneBoxMobile();
                }
            });
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

function searchName(valueSearch) {
    const textSearch = valueSearch.value;
    const viewMore = document.querySelector(".view-more");

    $.ajax({
        url: "http://localhost:8080/GentleDentalCare/service",
        type: "get",
        data: {
            search: textSearch
        },
        success: function (data) {
            const newsList = document.querySelector("#return-list");
            newsList.innerHTML = data;
            if (viewMore !== null) {
                viewMore.style.display = "none";
            }
        }
    });
}

function showLoadMoreButton() {
    const viewMore = document.querySelector(".view-more");

    if (viewMore !== null) {
        viewMore.style.display = "block";
    }
}