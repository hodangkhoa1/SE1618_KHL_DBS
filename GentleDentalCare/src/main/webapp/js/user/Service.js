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