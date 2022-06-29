$("#imageInput").on("change", function () {
    $input = $(this);
    if ($input.val().length > 0) {
        fileReader = new FileReader();
        fileReader.onload = function (data) {
            $(".image-preview").attr("src", data.target.result);
            $(".image-hidden").attr({"value": data.target.result, "name": "serviceImage"});
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

function CheckServiceName() {
    const inputServiceName = document.querySelector(".service-name"),
            serviceNameErrorMessage = document.querySelector(".service-name-error p");

    if (inputServiceName.value === "") {
        inputServiceName.style.border = "1px solid red";
        serviceNameErrorMessage.innerHTML = "Please enter service name!";
    } else {
        inputServiceName.style.border = "1px solid green";
        serviceNameErrorMessage.innerHTML = "";
    }
}

function CheckServicePrice() {
    const inputServicePrice = document.querySelector(".service-price"),
            servicePriceErrorMessage = document.querySelector(".service-price-error p");

    if (inputServicePrice.value === "") {
        inputServicePrice.style.border = "1px solid red";
        servicePriceErrorMessage.innerHTML = "Please enter service price!";
    } else {
        inputServicePrice.style.border = "1px solid green";
        servicePriceErrorMessage.innerHTML = "";
    }
}
