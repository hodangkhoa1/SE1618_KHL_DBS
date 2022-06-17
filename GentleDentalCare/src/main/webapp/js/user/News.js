const viewMore = document.querySelector(".view-more");

function loadMore(totalCourses, urlServlet) {
    const newsAmount = document.querySelectorAll(".news-amount").length;
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
            newsExits: newsAmount
        },
        success: function (data) {
            const newsList = document.querySelector("#return-list");
            newsList.innerHTML += data;
        }
    }).done(() => {
        if (totalCourses <= (newsAmount + 9)) {
            viewMore.style.display = "none";
        } else {
            viewMore.innerHTML = `<button onclick="loadMore('${totalCourses}', '${urlServlet}')" class="button-view">Load more</button>`;
        }
    });
}