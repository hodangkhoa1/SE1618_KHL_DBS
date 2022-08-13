const showErrorBox = document.querySelector("#error-box");
const bodyTag = document.getElementsByTagName("BODY")[0];

function getValueId() {
    var selectedValue = document.getElementById("selService").value;
    return selectedValue;
}

function getValue() {
    var sel = document.getElementById("selService");
    var text = sel.options[sel.selectedIndex].text;
    return text;
}

function getDate() {
    var getDate = document.getElementById("dateService").value;
    return getDate;
}

function change_button() {
    var selectHospital = document.getElementById("hospital");
    var buttonService = document.getElementById("buttonService");

    if (selectHospital.value === "") {
        buttonService.disabled = true;
    } else {
        buttonService.disabled = false;
    }
}

function change_Book(checkbx, button_id, service_id, date_id) {
    var btn = document.getElementById(button_id);
    let service = document.getElementById(service_id).value;
    let date = document.getElementById(date_id).value;
    if (checkbx.value !== "" && service !== "" && date !== "") {
        btn.removeAttribute("disabled");
    }
    getSlot = checkbx;
}

function getSlotValue() {
    var slotValue = getSlot.value;
    return slotValue;
}

function CheckValueUser(userID, urlServlet, homeUrl) {
    var inputFullName = document.getElementById("fullName");
    var inputEmail = document.getElementById("email");
    var inputPhoneNumber = document.getElementById("phoneNumber");
    var inputAddress = document.getElementById("address");

    if (userID === "") {
        bodyTag.style.overflowY = "hidden";
        bodyTag.style.height = "100%";
        showErrorBox.innerHTML = `
            <div class="error-popup">
                <div class="wrapper">
                    <div class="content">
                        <div class="warn-icon">
                            <span><i class="uil uil-exclamation"></i></span>
                        </div>
                        <h2>No Login Detected!</h2>
                        <p>You are not logged in. Please login to use all website functions.</p>
                        <div class="buttons">
                            <button onclick="window.location.href='${urlServlet}';" id="login-btn">Login</button>
                            <button onclick="window.location.href='${homeUrl}';" id="cancel-btn">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    } else if (inputFullName.value === "" || inputEmail.value === "" || inputPhoneNumber.value === "" || inputAddress.value === "") {
        bodyTag.style.overflowY = "hidden";
        bodyTag.style.height = "100%";
        showErrorBox.innerHTML = `
            <div class="error-popup">
                <div class="wrapper">
                    <div class="content">
                        <div class="warn-icon">
                            <span><i class="uil uil-exclamation"></i></span>
                        </div>
                        <h2>Incomplete information!</h2>
                        <p>You need to fully update your information to make an appointment.</p>
                        <div class="buttons">
                            <button onclick="window.location.href='${urlServlet}';" id="login-btn">Update</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }
}

$(function () {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, "0");
    var mm = String(today.getMonth() + 1).padStart(2, "0");
    var yyyy = today.getFullYear();

    today = yyyy + "-" + mm + "-" + dd;
    $("#dateService").attr("min", today);
    var count = $("body").find(".btn-check").length;
    var todayTime = new Date().toLocaleTimeString("en-GB", {
        hour: "numeric",
        minute: "numeric"
    });

    for (let i = 0; i < count; i++) {
        var timePicked = document.getElementById("success-outlined" + i).value;
        var labelPicked = document.getElementById("slot" + i);
        timeSlot = timePicked.split("-");
        if (getDate() !== null) {
            if (getDate() === today) {
                if (timeSlot[0] < todayTime) {
                    labelPicked.setAttribute("hidden", "hidden");
                }
            }
        }
    }
});

function checkToday() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, "0");
    var mm = String(today.getMonth() + 1).padStart(2, "0");
    var yyyy = today.getFullYear();

    today = yyyy + "-" + mm + "-" + dd;
    if (getDate() < today) {
        return 1;
    }
}

index = 0;
countIndex = 0;
timeService = 0;
checkDate = 0;
const dateArray = [];
function addLi() {
    var txtVal = getValue();
    var txtDate = getDate();
    var txtSlot = getSlotValue();
    const inputBookService = document.getElementById("bookService");
    const slotCatch = document.getElementById("slotCatchQ");

    listNode = document.getElementById('list');
    liNode = document.createElement('li');
    input = document.createElement("input");
    input2 = document.createElement("input");
    input3 = document.createElement("input");

    const countDay = {};

    if (checkToday() === 1) {
        slotCatch.style.display = "block";
        slotCatch.innerHTML = "Please choose another date";
        return;
    }

    if (index > 0) {
        for (let i = 0; i < index; i++) {
            if (document.getElementById('date' + i) !== null || document.getElementById('lot' + i) !== null || document.getElementById('service' + i) !== null) {
                if (getDate() === document.getElementById('date' + i).value) {
                    if (getValueId() === document.getElementById('service' + i).value) {
                        slotCatch.style.display = "block";
                        slotCatch.innerHTML = "Please choose another service";
                        return;
                    } else if (getSlotValue() === document.getElementById('lot' + i).value) {
                        slotCatch.style.display = "block";
                        slotCatch.innerHTML = "Please choose another slot";
                        return;
                    }
                }
            }
        }
    }

    //them ngay vao chuoi
    if (dateArray[checkDate] == null) {
        dateArray[checkDate] = getDate();
        checkDate++;
    }

    for (const element of dateArray) {
        if (countDay[element]) {
            countDay[element] += 1;
        } else {
            countDay[element] = 1;
        }
    }

    if (countDay[getDate()] > 2) {
        dateArray.pop();
        slotCatch.style.display = "block";
        slotCatch.innerHTML = "Only can choose 2 service per day";
        return;
    }

    for (var i = 0; i <= index; i++) {
        liNode.setAttribute("id", i);
        input.setAttribute("id", "service" + i);
        input.setAttribute("name", "service" + i);
        input2.setAttribute("id", "lot" + i);
        input2.setAttribute("name", "slot" + i);
        input3.setAttribute("id", "date" + i);
        input3.setAttribute("name", "date" + i);
        inputBookService.setAttribute("value", i + 1);
    }

    txtNode = document.createTextNode(txtVal + " on " + txtDate + " at " + txtSlot);
    liNode.appendChild(txtNode);

    //create input
    input.setAttribute("type", "hidden");
    input.setAttribute("value", getValueId());

    input2.setAttribute("type", "hidden");
    input2.setAttribute("value", txtSlot);

    input3.setAttribute("type", "hidden");
    input3.setAttribute("value", txtDate);

    //create button
    var button = document.createElement("button");
    button.innerHTML = "&times;";
    button.setAttribute("class", "btn btn-danger deleData");
    button.setAttribute("onclick", "removeLi()");
    button.setAttribute("aria-label", "Close");
    button.setAttribute("style", "float: right");
    button.setAttribute("id", txtDate);
    liNode.appendChild(input2);
    liNode.appendChild(input);
    liNode.appendChild(input3);
    liNode.appendChild(button);

    listNode.appendChild(liNode);
    document.getElementById("hospital").setAttribute("onfocus", "this.setAttribute('data-value', this.value)");
    document.getElementById("hospital").setAttribute("onchange", "this.value = this.getAttribute('data-value');");
    index++;
    countIndex++;
    timeService++;
    slotCatch.style.display = "none";
    slotCatch.innerHTML = "";
    $('#modalForm').modal('toggle');
    removeValue();
}

$("#buttonService").on("click", function () {
    const bookCatch = document.getElementById("bookCatch");

    if (countIndex >= 3) {
        bookCatch.style.display = "block";
        bookCatch.innerHTML = "Only can choose 3 service per appointment";
        return;
    }
});

$("#buttonSubmit").on("click", function () {
    if (countIndex >= 3) {
        var element = document.getElementById("modalForm");
        element.setAttribute("id", "modalForm2");
        return;
    }
});

function removeValue() {
    var selectedValue = document.getElementById("selService");
    var getDate = document.getElementById("dateService");
    var buttonSubmit = document.getElementById("buttonSubmit");
    var option = document.querySelector('input[name="options-outlined"]:checked');
    const slotCatch = document.getElementById("slotCatchQ");
    const myNode = document.querySelector(".foo");

    selectedValue.value = "";
    getDate.value = "";

    if (option) {
        option.checked = false;
    }

    buttonSubmit.setAttribute("disabled", "");
    slotCatch.innerHTML = "";
    slotCatch.style.display = "none";
}

$('#dateService').change(function () {
    var buttonService = document.getElementById("buttonSubmit");

    buttonService.disabled = true;
});

$('.container').on('click', function () {
    if ($('#modalForm').hasClass('show') === false) {
        $("#return-list").empty();
    }
});

$('#selService').change(function () {
    $("#return-list").empty();
    
    var getDate = document.getElementById("dateService");
    var buttonService = document.getElementById("buttonSubmit");
    const slotCatch = document.getElementById("slotCatchQ");
    
    getDate.value = "";
    slotCatch.innerHTML = "";
    slotCatch.style.display = "none";
    buttonService.disabled = true;
});

function removeLi() {
    const bookCatch = document.getElementById("bookCatch");
    const inputBookService = document.getElementById("bookService");

    $('body').unbind('click').on('click', '.deleData', function () {
        var a = $(this).attr('id');
        var b = dateArray.indexOf(a);
        dateArray.splice(b, 1);
        $(this).parent().remove();
        countIndex--;

        bookCatch.innerHTML = "";
        bookCatch.style.display = "none";
        inputBookService.setAttribute("value", countIndex);

        var element = document.getElementById('modalForm2');
        if (element !== null) {
            element.setAttribute("id", "modalForm");
        }
    });
}

function getServiceSlot(urlServer) {
    $("#return-list").empty();

    $.ajax({
        url: urlServer,
        type: "get",
        data: {
            DataService: getValueId(),
            DataDate: getDate()
        },
        success: function (data) {
            const newsListSlot = document.querySelector("#return-list");
            newsListSlot.innerHTML += data;
        },
        complete: function () {
            var co = $("#return-list").find("*").length;

            for (var i = 0; i <= co / 2; i++) {
                $("#success-outlined").attr("id", "success-outlined" + i);
                $("#slot").attr("for", "success-outlined" + i);
                $("#slot").attr("id", "slot" + i);
            }

            var today = new Date();
            var dd = String(today.getDate()).padStart(2, "0");
            var mm = String(today.getMonth() + 1).padStart(2, "0");
            var yyyy = today.getFullYear();
            today = yyyy + "-" + mm + "-" + dd;
            var count = $("body").find(".btn-check").length;
            var todayTime = new Date();
            todayTime.setHours(todayTime.getHours() + 5);
            var todayTimeString = todayTime.toLocaleTimeString("en-GB", {
                hour: "numeric",
                minute: "numeric",
                second: "numeric"
            });

            for (let i = 0; i < count; i++) {
                var timePicked = document.getElementById("success-outlined" + i);
                var timePickedValue = document.getElementById("success-outlined" + i).value;

                if (timePicked.disabled === true) {
                    timePicked.removeAttribute("disabled");
                }
                if (getDate() !== null) {
                    if (getDate() === today) {
                        if (timePickedValue < todayTimeString) {
                            timePicked.disabled = true;
                        }
                    }
                }
            }
        }
    });
}