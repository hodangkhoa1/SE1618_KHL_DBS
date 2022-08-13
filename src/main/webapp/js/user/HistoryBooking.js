function confirmDelete(urlServlet, bookingID, patientID) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    });

    swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
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
                    PatientID: patientID
                },
                success: function () {
                    location.reload();
                }
            });
            swalWithBootstrapButtons.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                    );
        }
    });
}

function LoadMoreButton(totalBooking, urlServlet) {
    const viewMore = document.querySelector('.btn--loadMore');
    const bookingAmount = document.querySelectorAll(".booking-amount").length;

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
        if (totalBooking <= (totalBooking + 9)) {
            viewMore.style.display = "none";
        } else {
            viewMore.innerHTML = `<button type="button" class="btn btn-primary" width="100px" onclick="loadMore('${totalBooking}', '${urlServlet}')">Show More</button>`;
        }
    });
}

var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
});

function openPopupFeedback() {
    document.querySelector('.feedback').classList.add('open');
}