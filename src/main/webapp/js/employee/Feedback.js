function ViewInformation(fullName, gender, dateOfBirth, phoneNumber, email, address, feedBackMessage) {
    var modal = document.getElementById("myModal");

    modal.style.display = "block";

    document.getElementById("fullName").value = fullName;
    document.getElementById("gender").value = gender;
    document.getElementById("dateOfBirth").value = dateOfBirth;
    document.getElementById("phoneNumber").value = phoneNumber;
    document.getElementById("email").value = email;
    document.getElementById("address").value = address;
    document.getElementById("feedback").value = feedBackMessage;
}

function CloseModal() {
    var modal = document.getElementById("myModal");

    modal.style.display = "none";
}

window.onclick = function (event) {
    var modal = document.getElementById("myModal");

    if (event.target === modal) {
        modal.style.display = "none";
    }
};

function loadMore(totalService, urlServlet) {
    const feedbackAmount = document.querySelectorAll(".feedback-amount").length;
    const viewMore = document.querySelector(".view-more");

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
            feedBackExits: feedbackAmount
        },
        success: function (data) {
            const feedBackList = document.querySelector("#return-list");
            feedBackList.innerHTML += data;
        }
    }).done(() => {
        if (totalService <= (feedbackAmount + 9)) {
            viewMore.style.display = "none";
        } else {
            viewMore.innerHTML = `<button onclick="loadMore('${totalService}', '${urlServlet}')" class="button-view">Load more</button>`;
        }
    });
}