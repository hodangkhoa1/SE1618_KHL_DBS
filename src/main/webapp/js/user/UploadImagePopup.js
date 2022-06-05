const formUpload = document.querySelector("#upload-form"),
        fileInput = formUpload.querySelector(".file-input"),
        uploadedArea = document.querySelector(".uploaded-area"),
        dragText = formUpload.querySelector("p"),
        buttonChange = document.querySelector(".change-avatar"),
        uploadPopup = document.querySelector(".upload-popup"),
        buttonChangeConfirm = document.querySelector(".button-change"),
        buttonCancelConfirm = document.querySelector(".button-cancel"),
        settingAvatarPhoto = document.querySelector(".setting-avatar_photo");
let fileImage;

buttonChange.onclick = () => {
    uploadPopup.classList.toggle("show");
    disableButton();
};

buttonCancelConfirm.onclick = () => {
    buttonChange.click();
};

formUpload.addEventListener("click", () => {
    fileInput.click();
});

formUpload.addEventListener("dragover", (event) => {
    event.preventDefault();
    enableButton();
});

formUpload.addEventListener("dragleave", () => {
    disableButton();
});

function enableButton() {
    formUpload.classList.add("active");
    dragText.textContent = "Release to Upload file";
    buttonChangeConfirm.style.border = '1px solid rgba(209, 213, 219, 1)';
    buttonChangeConfirm.style.backgroundColor = 'rgb(99 102 241)';
    buttonChangeConfirm.style.color = '#fff';
    buttonChangeConfirm.style.cursor = 'pointer';
    buttonChangeConfirm.style.pointerEvents = "all";
}

function disableButton() {
    formUpload.classList.remove("active");
    dragText.textContent = "Browse Image to Upload";
    buttonChangeConfirm.style.border = "1px solid #999999";
    buttonChangeConfirm.style.backgroundColor = "#cccccc";
    buttonChangeConfirm.style.color = "#666666";
    buttonChangeConfirm.style.pointerEvents = "none";
    formUpload.innerHTML = `<input type="file" class="file-input" hidden>
            <i class="uil uil-image-upload"></i>
            <p>Browse Image to Upload</p>`;
    uploadedArea.innerHTML = "";
}

formUpload.addEventListener("drop", (event) => {
    event.preventDefault();
    fileImage = event.dataTransfer.files[0];
    showImage();
});

fileInput.onchange = ({target}) => {
    fileImage = target.files[0];
    if (fileImage) {
        showImage();
        enableButton();
}
};

function showImage() {
    let fileType = fileImage.type;
    let validExtensions = ["image/jpeg", "image/png", "image/jpg"];
    if (validExtensions.includes(fileType)) {
        let fileReader = new FileReader();
        fileReader.onload = () => {
            let fileURL = fileReader.result;
            formUpload.innerHTML = `<img src="${fileURL}" alt="" >`;
        };
        fileReader.readAsDataURL(fileImage);
        let fileName = fileImage.name;
        if (fileName.length >= 12) {
            let splitName = fileName.split('.');
            fileName = splitName[0].substring(0, 12) + "... ." + splitName[1];
        }

        uploadedArea.innerHTML = `<li class="area-row">
                            <div class="content">
                                <i class="uil uil-file"></i>
                                <div class="details">
                                    <span class="name">${fileName} . Uploaded</span>
                                    <span class="size">70 KB</span>
                                </div>
                            </div>
                            <i class="uil uil-check-circle"></i>
                        </li>`;

        buttonChangeConfirm.addEventListener("click", () => {
            let fileURL = fileReader.result;
            settingAvatarPhoto.innerHTML = `<img src="${fileURL}" alt="" >
                                        <input type="hidden" value="${fileURL}" name="imageAvatar">`;
            buttonChange.click();
            fileImage = "";
            formUpload.innerHTML = "";
        });
    } else {
        disableButton();
    }
}