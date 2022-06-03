// Làm chức năng chọn ngày tháng năm
const yearSelect = document.getElementById("year");
const monthSelect = document.getElementById("month");
const daySelect = document.getElementById("day");
const months = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"];

//Months are always the same
(function populateMonths() {
    for (let i = 0; i < months.length; i++) {
        const option = document.createElement("option");
        option.textContent = months[i];
        monthSelect.appendChild(option);
    }
    monthSelect.value = "1";
})();

let previousDay;

function populateDays(month) {
    //Delete all of the children of the day dropdown
    //if they do exist
    while (daySelect.firstChild) {
        daySelect.removeChild(daySelect.firstChild);
    }
    //Holds the number of days in the month
    let dayNum;
    let year = yearSelect.value;

    if (
            month === "1" ||
            month === "3" ||
            month === "5" ||
            month === "7" ||
            month === "8" ||
            month === "10" ||
            month === "12"
            ) {
        dayNum = 31;
    } else if (
            month === "4" ||
            month === "6" ||
            month === "9" ||
            month === "11"
            ) {
        dayNum = 30;
    } else {
        //Check for a leap year
        if (new Date(year, 1, 29).getMonth() === 1) {
            dayNum = 29;
        } else {
            dayNum = 28;
        }
    }

    //Insert the correct days into the day <select>
    for (let i = 1; i <= dayNum; i++) {
        const option = document.createElement("option");
        option.textContent = i;
        daySelect.appendChild(option);
    }

    if (previousDay) {
        daySelect.value = previousDay;
        if (daySelect.value === "") {
            daySelect.value = previousDay - 1;
        }
        if (daySelect.value === "") {
            daySelect.value = previousDay - 2;
        }
        if (daySelect.value === "") {
            daySelect.value = previousDay - 3;
        }
    }
}

function populateYears() {
    //Get the current year as a number
    let year = new Date().getFullYear();

    //Make the previous 100 years be an option
    for (let i = 0; i < 101; i++) {
        const option = document.createElement("option");
        option.textContent = year - i;
        yearSelect.appendChild(option);
    }
}

populateDays(monthSelect.value);
populateYears();

yearSelect.onchange = function () {
    populateDays(monthSelect.value);
};

monthSelect.onchange = function () {
    populateDays(monthSelect.value);
};

daySelect.onchange = function () {
    previousDay = daySelect.value;
};

//Check Full Name
function CheckFullName() {
    const inputFullName = document.querySelector("#fullName"),
            fullnameIconCheck = document.querySelector("#fullName-icon-check"),
            fullnameIconError = document.querySelector("#fullName-icon-error"),
            fullnameErrorMessage = document.querySelector("#fullName-error");

    if (inputFullName.value === "") {
        inputFullName.style.border = "1px solid red";
        fullnameErrorMessage.innerHTML = "Please enter your full name!";
        fullnameIconError.style.display = "block";
        fullnameIconCheck.style.display = "none";
    } else {
        inputFullName.style.border = "1px solid green";
        fullnameErrorMessage.innerHTML = "";
        fullnameIconError.style.display = "none";
        fullnameIconCheck.style.display = "block";
    }
}

//Check Phone Number
function CheckPhoneNumber() {
    const inputPhoneNumber = document.querySelector("#phoneNumber"),
            phoneNumberIconCheck = document.querySelector("#phoneNumber-icon-check"),
            phoneNumberIconError = document.querySelector("#phoneNumber-icon-error"),
            phoneNumberErrorMessage = document.querySelector("#phoneNumber-error");

    if (inputPhoneNumber.value === "") {
        inputPhoneNumber.style.border = "1px solid red";
        phoneNumberErrorMessage.innerHTML = "Please enter your phone number!";
        phoneNumberIconError.style.display = "block";
        phoneNumberIconCheck.style.display = "none";
    } else if (
            inputPhoneNumber.value.length < 10 ||
            inputPhoneNumber.value.length > 10
            ) {
        inputPhoneNumber.style.border = "1px solid red";
        phoneNumberErrorMessage.innerHTML = "Phone number must have 10 digits!";
        phoneNumberIconError.style.display = "block";
        phoneNumberIconCheck.style.display = "none";
    } else {
        inputPhoneNumber.style.border = "1px solid green";
        phoneNumberErrorMessage.innerHTML = "";
        phoneNumberIconError.style.display = "none";
        phoneNumberIconCheck.style.display = "block";
    }
}

//Check Address
function CheckAddress() {
    const inputAddress = document.querySelector("#address"),
            addressIconCheck = document.querySelector("#address-icon-check"),
            addressIconError = document.querySelector("#address-icon-error"),
            addressErrorMessage = document.querySelector("#address-error");

    if (inputAddress.value === "") {
        inputAddress.style.border = "1px solid red";
        addressErrorMessage.innerHTML = "Please enter your address!";
        addressIconError.style.display = "block";
        addressIconCheck.style.display = "none";
    } else {
        inputAddress.style.border = "1px solid green";
        addressErrorMessage.innerHTML = "";
        addressIconError.style.display = "none";
        addressIconCheck.style.display = "block";
    }
}