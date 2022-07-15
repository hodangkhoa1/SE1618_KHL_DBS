$("#imageInput").on("change", function () {
    $input = $(this);
    if ($input.val().length > 0) {
        fileReader = new FileReader();
        fileReader.onload = function (data) {
            $(".image-preview").attr("src", data.target.result);
            $(".image-hidden").attr({"value": data.target.result, "name": "newsImage"});
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

function CheckNewsName() {
    const inputNewsName = document.querySelector(".news-name"),
            newsNameErrorMessage = document.querySelector(".news-name-error p");

    if (inputNewsName.value === "") {
        inputNewsName.style.border = "1px solid red";
        newsNameErrorMessage.innerHTML = "Please enter news name!";
    } else {
        inputNewsName.style.border = "1px solid green";
        newsNameErrorMessage.innerHTML = "";
    }
}

function CheckSubtitleNews() {
    const inputSubtitleNews = document.querySelector(".subtitle-news"),
            subtitleNewsErrorMessage = document.querySelector(".subtitle-news-error p");

    if (inputSubtitleNews.value === "") {
        inputSubtitleNews.style.border = "1px solid red";
        subtitleNewsErrorMessage.innerHTML = "Please enter subtitle news!";
    } else {
        inputSubtitleNews.style.border = "1px solid green";
        subtitleNewsErrorMessage.innerHTML = "";
    }
}