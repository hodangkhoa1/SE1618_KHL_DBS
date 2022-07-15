function LoadMoreButton(totalBooking, urlServlet) {
    const viewMore = document.querySelector('.btn--loadMore');
    const bookingAmount = document.querySelectorAll(".history__card").length;

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
            BookingAmount: bookingAmount
        },
        success: function (data) {
            const bookingList = document.querySelector("#return-list");
            bookingList.innerHTML += data;
        }
    }).done(() => {
        if (totalBooking <= (totalBooking + 5)) {
            viewMore.style.display = "none";
        } else {
            viewMore.innerHTML = `<button type="button" class="btn btn-primary" width="100px" onclick="loadMore('${totalBooking}', '${urlServlet}')">Load More</button>`;
        }
    });
}

function confirmDelete(urlServlet, bookingID, userID) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: true
    });

    swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        text: "Do you want to cancel your appointment?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: urlServlet,
                type: "get",
                data: {
                    BookingID: bookingID,
                    UserID: userID
                },
                success: function () {
                    location.reload();
                }
            });
            swalWithBootstrapButtons.fire(
                    'Deleted!',
                    'Your booking has been cancelled.',
                    'success'
                    );
        }
    });
}