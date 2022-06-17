function DeleteAccount(urlServlet, userEmail) {
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: urlServlet,
                type: "get",
                data: {
                    userEmail: userEmail
                },
                success: function () {
                    location.reload();
                }
            });
        }
    });
}