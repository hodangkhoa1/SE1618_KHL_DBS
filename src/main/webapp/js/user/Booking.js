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

function CheckValueUser(userID, urlServlet) {
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

index = 0;
timeService = 0;
checkDate = 0;
dateArray = [];
function addLi() {
    var txtVal = getValue();
    var txtDate = getDate();
    var txtSlot = getSlotValue();
    const inputBookService = document.getElementById("bookService");

    listNode = document.getElementById('list');
    liNode = document.createElement('li');
    input = document.createElement("input");
    input2 = document.createElement("input");
    input3 = document.createElement("input");

    const countDay = {};
    console.log(getValue());
    if (index > 0) {
        for (let i = 0; i < index; i++) {
            if (document.getElementById('date' + i) !== null || document.getElementById('lot' + i) !== null || document.getElementById('service' + i) !== null) {
                if (getDate() === document.getElementById('date' + i).value) {
                    if (getValue() === document.getElementById('service' + i).value) {
                        document.getElementById('slotCatch').innerHTML = "Please choose another service";
                        return;
                    } else if (getSlotValue() === document.getElementById('lot' + i).value) {
                        document.getElementById('slotCatch').innerHTML = "Please choose another slot";
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
        document.getElementById('slotCatch').innerHTML = "Only can choose 2 service per day";
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
    timeService++;
    document.getElementById('slotCatch').innerHTML = "";
    $('#modalForm').modal('toggle');
    removeValue();
}

$(function () {
    var todayTime = new Date().toLocaleTimeString("en-GB", {
        hour: "numeric",
        minute: "numeric"
    });
});

$("#buttonService").on("click", function () {
    const bookCatch = document.getElementById("bookCatch");

    if (index >= 5) {
        bookCatch.style.display = "block";
        bookCatch.innerHTML = "Only can choose 5 service per appointment";
        return;
    }
});

$("#buttonSubmit").on("click", function () {
    if (index >= 5) {
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
    const slotCatch = document.getElementById("slotCatch");
    const myNode = document.querySelector(".foo");

    selectedValue.value = "";
    getDate.value = "";
    if (option) {
        option.checked = false;
    }
    buttonSubmit.setAttribute("disabled", "");
    slotCatch.style.display = "none";
    slotCatch.innerHTML = "";
    myNode.innerHTML = "";
}

$('.container').on('click', function () {
    if ($('#modalForm').hasClass('show') === false) {
        const myNode = document.getElementById("foo");
        while (myNode.firstElementChild) {
            myNode.removeChild(myNode.lastChild);
        }
    }
});

function removeLi() {
    const bookCatch = document.getElementById("bookCatch");

    $('body').on('click', '.deleData', function () {
        var a = $(this).attr('id');
        var b = dateArray.indexOf(a);
        dateArray.splice(b, 1);
        $(this).parent().remove();
        index--;

        bookCatch.style.display = "none";
        bookCatch.innerHTML = "";

        var element = document.getElementById('modalForm2');
        if (element !== null) {
            element.setAttribute("id", "modalForm");
        }
    });
}

function getServiceSlot(urlServer) {
    $.ajax({
        URL: urlServer,
        type: "get",
        data: {
            DataService: getValue(),
            DataDate: getDate()
        },
        success: function (data) {
            const newsListSlot = document.querySelector("#return-list");
            newsListSlot.innerHTML += data;
        }
    });
}