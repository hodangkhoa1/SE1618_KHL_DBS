var appointmentID = "";

function ViewAppointment(imageAvatar, fullName, hospitalName, serviceName, slotStart, bookingDate) {
    document.querySelector('#modal-image').src = `data:image/png;base64,${imageAvatar}`;
    document.querySelector('#modal-name').innerHTML = fullName;
    document.querySelector('#modal-hospital').innerHTML = hospitalName;
    document.querySelector('#modal-service').innerHTML = serviceName + " ";
    document.querySelector('#modal-slot').innerHTML = slotStart + " ";
    document.querySelector('#modal-date').innerHTML = bookingDate;
}

function GetAppointmentID(getAppointmentID) {
    appointmentID = getAppointmentID;
}

function AcceptAppointment(urlServlet) {
    $.ajax({
        url: urlServlet,
        type: "get",
        data: {
            BookingID: appointmentID,
            Action: "Accept"
        },
        success: function () {
            location.reload();
        }
    });
}

function CancelAppointment(urlServlet) {
    $.ajax({
        url: urlServlet,
        type: "get",
        data: {
            BookingID: appointmentID,
            Action: "Cancel"
        },
        success: function () {
            location.reload();
        }
    });
}