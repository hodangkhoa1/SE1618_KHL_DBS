function getValue() {
    var selectedValue = document.getElementById("selService").value;
    return selectedValue;
}

function getDate() {
    var getDate = document.getElementById("dateService").value;
    return getDate;
}

function change_button(checkbx, button_id) {
    var btn = document.getElementById(button_id);
    if (checkbx.value === "") {
        btn.disabled = true;
    } else {
        btn.disabled = false;
    }
}

function change_Book(checkbx, button_id, service_id, date_id) {
    var btn = document.getElementById(button_id);
    let service = document.getElementById(service_id).value;
    let date = document.getElementById(date_id).value;
    if (checkbx.value !== "" && service !== "" && date !== "") {
        btn.removeAttribute("disabled");
    }
    getSlotID = checkbx;
}

function getSlot() {
    var slotValue = getSlotID.value;
    return slotValue;
}

function checkDulicate(service) {
    alert("nênne");
    for (let index = 0; index < lenghtLi; index++) {
        if (service.value === document.getElementById(index).value)
            alert("giong nhau ne");
    }
}
index = 0;
timeService = 0;
function addLi() {
    var txtVal = getValue();
    var txtDate = getDate();
    var txtSlot = getSlot();
    (listNode = document.getElementById("list")),
            (liNode = document.createElement("li"));
    var lenghtLi = document
            .getElementById("list")
            .getElementsByTagName("li").length;
    var input = document.createElement("input");
    var input2 = document.createElement("input");
    var input3 = document.createElement("input");

    if (timeService >= 2) {
        document.getElementById("slotCatch").innerHTML =
                "Only can choose 2 service per appointment";
        return;
    }

    if (index > 0) {
        for (var i = 0; i < lenghtLi; i++) {
            if (getDate() === document.getElementById("date" + i).value) {
                if (getValue() === document.getElementById("service" + i).value) {
                    document.getElementById("slotCatch").innerHTML =
                            "Please choose another service";
                    return;
                } else if (getSlot() === document.getElementById("slot" + i).value) {
                    document.getElementById("slotCatch").innerHTML =
                            "Please choose another slot";
                    return;
                }
            }
        }
    }

    for (var i = 0; i <= lenghtLi; i++) {
        liNode.setAttribute("id", i);
        input.setAttribute("id", "service" + i);
        input2.setAttribute("id", "slot" + i);
        input3.setAttribute("id", "date" + i);
    }

    txtNode = document.createTextNode(
            txtVal + " on " + txtDate + " at " + txtSlot
            );
    liNode.appendChild(txtNode);

    //create input
    input.setAttribute("type", "hidden");
    input.setAttribute("value", txtVal);
    input.setAttribute("name", "service");

    input2.setAttribute("type", "hidden");
    input2.setAttribute("value", txtSlot);
    input2.setAttribute("name", "serviceSlot");

    input3.setAttribute("type", "hidden");
    input3.setAttribute("value", txtDate);
    input3.setAttribute("name", "serviceDate");

    //create button
    var button = document.createElement("button");
    button.innerHTML = "&times;";
    button.setAttribute("class", "btn btn-danger deleData");
    button.setAttribute("onclick", "removeLi()");
    button.setAttribute("aria-label", "Close");
    button.setAttribute("style", "float: right");
    liNode.appendChild(input);
    liNode.appendChild(input2);
    liNode.appendChild(input3);
    liNode.appendChild(button);

    listNode.appendChild(liNode);
    document
            .getElementById("hospital")
            .setAttribute("onfocus", "this.setAttribute('data-value', this.value)");
    document
            .getElementById("hospital")
            .setAttribute("onchange", "this.value = this.getAttribute('data-value');");
    index++;
    timeService++;
    document.getElementById("slotCatch").innerHTML = "";
    $("#modalForm").modal("toggle");
}

function removeLi() {
    $("body").on("click", ".deleData", function () {
        $(this).parent().remove();
    });
}

function getServiceSlot(urlServer) {
    $.ajax({
        URL: urlServer,
        type: "get",
        data: {
            dataService: getValue(),
            dataDate: getDate()
        },
        success: function (data) {
            const newsListSlot = document.querySelector("#return-list");
            newsListSlot.innerHTML += data;
        }
    });
}