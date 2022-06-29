function CheckHospitalName() {
    const inputHospitalName = document.querySelector(".hospital-name"),
            hospitalNameErrorMessage = document.querySelector(".hospital-name-error p");

    if (inputHospitalName.value === "") {
        inputHospitalName.style.border = "1px solid red";
        hospitalNameErrorMessage.innerHTML = "Please enter hospital name!";
    } else {
        inputHospitalName.style.border = "1px solid green";
        hospitalNameErrorMessage.innerHTML = "";
    }
}

function CheckHospitalPhone() {
    const inputHospitalPhone = document.querySelector(".hospital-phone"),
            hospitalPhoneErrorMessage = document.querySelector(".hospital-phone-error p");

    if (inputHospitalPhone.value === "") {
        inputHospitalPhone.style.border = "1px solid red";
        hospitalPhoneErrorMessage.innerHTML = "Please enter hospital phone!";
    } else if (
            inputHospitalPhone.value.length < 10 ||
            inputHospitalPhone.value.length > 10
            ) {
        inputHospitalPhone.style.border = "1px solid red";
        hospitalPhoneErrorMessage.innerHTML = "Phone number must have 10 digits!";
    } else {
        inputHospitalPhone.style.border = "1px solid green";
        hospitalPhoneErrorMessage.innerHTML = "";
    }
}

function CheckHospitalAddress() {
    const inputHospitalAddress = document.querySelector(".hospital-address"),
            hospitalAddressErrorMessage = document.querySelector(".hospital-address-error p");

    if (inputHospitalAddress.value === "") {
        inputHospitalAddress.style.border = "1px solid red";
        hospitalAddressErrorMessage.innerHTML = "Please enter hospital address!";
    } else {
        inputHospitalAddress.style.border = "1px solid green";
        hospitalAddressErrorMessage.innerHTML = "";
    }
}