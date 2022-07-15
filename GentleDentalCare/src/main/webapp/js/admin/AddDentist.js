$("#imageInput").on("change", function () {
    $input = $(this);
    if ($input.val().length > 0) {
        fileReader = new FileReader();
        fileReader.onload = function (data) {
            $(".image-preview").attr("src", data.target.result);
            $(".image-hidden").attr({"value": data.target.result, "name": "dentistImage"});
        };
        fileReader.readAsDataURL($input.prop("files")[0]);
        $(".image-button").css("display", "none");
        $(".image-preview").css("display", "block");
        $(".image-preview").css("width", "100%");
        $(".image-preview").css("border-radius", "20px");
        $(".change-image").css("display", "block");
    }
});

$(".change-image").on("click", function () {
    $control = $(this);
    $("#imageInput").val("");
    $preview = $(".image-preview");
    $preview.attr("src", "");
    $preview.css("display", "none");
    $control.css("display", "none");
    $(".image-button").css("display", "block");
});

function CheckFullName() {
    const inputFullName = document.querySelector(".full-name"),
            fullNameErrorMessage = document.querySelector(".full-name-error p");

    if (inputFullName.value === "") {
        inputFullName.style.border = "1px solid red";
        fullNameErrorMessage.innerHTML = "Please enter full name!";
    } else {
        inputFullName.style.border = "1px solid green";
        fullNameErrorMessage.innerHTML = "";
    }
}

function CheckSubtitleDentist() {
    const inputSubtitleDentist = document.querySelector(".subtitle-dentist"),
            subtitleDentistErrorMessage = document.querySelector(".subtitle-dentist-error p");

    if (inputSubtitleDentist.value === "") {
        inputSubtitleDentist.style.border = "1px solid red";
        subtitleDentistErrorMessage.innerHTML = "Please enter subtitle dentist!";
    } else {
        inputSubtitleDentist.style.border = "1px solid green";
        subtitleDentistErrorMessage.innerHTML = "";
    }
}

function CheckPhoneNumber() {
    const inputPhoneNumber = document.querySelector(".phone-number"),
            phoneNumberErrorMessage = document.querySelector(".phone-number-error p");

    if (inputPhoneNumber.value === "") {
        inputPhoneNumber.style.border = "1px solid red";
        phoneNumberErrorMessage.innerHTML = "Please enter your phone number!";
    } else if (
            inputPhoneNumber.value.length < 10 ||
            inputPhoneNumber.value.length > 10
            ) {
        inputPhoneNumber.style.border = "1px solid red";
        phoneNumberErrorMessage.innerHTML = "Phone number must have 10 digits!";
    } else {
        inputPhoneNumber.style.border = "1px solid green";
        phoneNumberErrorMessage.innerHTML = "";
    }
}

function CheckAcademicRank() {
    const inputAcademicRank = document.querySelector(".academic-rank"),
            academicRankErrorMessage = document.querySelector(".academic-rank-error p");

    if (inputAcademicRank.value === "") {
        inputAcademicRank.style.border = "1px solid red";
        academicRankErrorMessage.innerHTML = "Please enter academic rank!";
    } else {
        inputAcademicRank.style.border = "1px solid green";
        academicRankErrorMessage.innerHTML = "";
    }
}
