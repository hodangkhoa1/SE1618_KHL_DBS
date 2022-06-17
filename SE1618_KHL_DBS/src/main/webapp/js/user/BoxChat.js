var $messages = $(".messages-content"),
        d,
        h,
        m,
        i = 0;

let msg;
let startChat;

$(".already-login").click(function () {
    $messages.mCustomScrollbar();
    setTimeout(function () {
        welcomeMessage();
    }, 100);
    $(".before-chat").addClass("chat");
    $(".chat").removeClass("before-chat");
    $(".discription").addClass("d-none");
    $(".sub-discription").addClass("d-none");
    $(".online").removeClass("d-none");
    $(".offline").addClass("d-none");
    $(".messages-content").removeClass("content-before");
    $(".avatar").removeClass("bot-avatar");
    $(".bot-name").removeClass("d-none");
    $(".already-login").addClass("d-none");
    $(".text-box").removeClass("d-none");
});

$(".icon-minus").click(function () {
    if ($(".chat-box__click").is(":checked")) {
        $(".chat-box__click").prop("checked", false);
    }
});

$(".icon-exit").click(function () {
    if ($("#chat").hasClass("chat") == false) {
        if ($(".chat-box__click").is(":checked")) {
            $(".chat-box__click").prop("checked", false);
        }
    } else {
        setTimeout(() => {
            if ($(".chat-box__click").is(":checked")) {
                $(".chat-box__click").prop("checked", false);
            }
        }, 1000);
    }
    $(".chat").addClass("before-chat");
    $(".before-chat").removeClass("chat");
    $(".discription").removeClass("d-none");
    $(".sub-discription").removeClass("d-none");
    $(".online").addClass("d-none");
    $(".offline").addClass("d-none");
    $(".messages-content").addClass("content-before");
    $(".avatar").addClass("bot-avatar");
    $(".bot-name").addClass("d-none");
    $(".text-box").addClass("d-none");
    $(".new").remove();
    $(".already-login").removeClass("d-none");
    $(".message-input").attr("disabled", "true");
});

function updateScrollbar() {
    $messages.mCustomScrollbar("update").mCustomScrollbar("scrollTo", "bottom", {
        scrollInertia: 10,
        timeout: 0,
    });
}

function setDate() {
    d = new Date();
    if (d.getHours() < 10) {
        if (d.getMinutes() < 10) {
            $(
                    '<div class="timestamp">' +
                    "0" +
                    d.getHours() +
                    ":" +
                    "0" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        } else {
            $(
                    '<div class="timestamp">' +
                    "0" +
                    d.getHours() +
                    ":" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        }
    } else {
        if (d.getMinutes() < 10) {
            $(
                    '<div class="timestamp">' +
                    d.getHours() +
                    ":" +
                    "0" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        } else {
            $(
                    '<div class="timestamp">' +
                    d.getHours() +
                    ":" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        }
    }
}

function personalDate() {
    d = new Date();
    if (d.getHours() < 10) {
        if (d.getMinutes() < 10) {
            $(
                    '<div class="timestamp-personal-message">' +
                    "0" +
                    d.getHours() +
                    ":" +
                    "0" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        } else {
            $(
                    '<div class="timestamp-personal-message">' +
                    "0" +
                    d.getHours() +
                    ":" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        }
    } else {
        if (d.getMinutes() < 10) {
            $(
                    '<div class="timestamp-personal-message">' +
                    d.getHours() +
                    ":" +
                    "0" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        } else {
            $(
                    '<div class="timestamp-personal-message">' +
                    d.getHours() +
                    ":" +
                    d.getMinutes() +
                    "</div>"
                    ).appendTo($(".message:last"));
        }
    }
}

function insertMessage() {
    msg = $(".message-input").val();
    if ($.trim(msg) == "") {
        return false;
    }
    $('<div class="message message-personal">' + msg + "</div>")
            .appendTo($(".mCSB_container"))
            .addClass("new");
    personalDate();
    $(".message-input").val(null);
    updateScrollbar();
    setTimeout(function () {
        fakeMessage();
    }, 300 + Math.random() * 20 * 100);
}

$(".message-submit").click(function () {
    insertMessage();
});

$(window).on("keydown", function (e) {
    if (e.which == 13) {
        insertMessage();
        return false;
    }
});

var botAnswer = [
    {
        input: ["tư vấn"],
        output: [
            "Tôi sẽ đề xuất cho bạn một số tư vấn sau bạn có thể tham khảo nếu bạn muốn",
            "Tư vấn viện phí",
            "Tư vấn bác sĩ khám",
            "Tư vấn về dịch vụ khám",
        ],
    },
    {
        input: ["tư vấn viện phí"],
        output: ["Tư vấn viện phí", "Tiếp tục tư vấn", "Dừng tư vấn"],
    },
    {
        input: ["tư vấn bác sĩ khám"],
        output: ["Tư vấn bác sĩ khám", "Tiếp tục tư vấn", "Dừng tư vấn"],
    },
    {
        input: ["tư vấn về dịch vụ khám"],
        output: ["Tư vấn về dịch vụ khám", "Tiếp tục tư vấn", "Dừng tư vấn"],
    },
    {
        input: ["liên hệ"],
        output: [
            "Bạn muốn gọi hotline hay chat trực tiếp với nhân viên",
            "Gọi Hotline",
            "Chat trực tiếp",
        ],
    },
    {
        input: ["hotline"],
        output: [
            "Đây là số hotline của trung tâm 19001070",
            "Tiếp tục tư vấn",
            "Dừng tư vấn",
        ],
    },
    {
        input: ["chat trực tiếp"],
        output: [
            "Tôi đang kết nối đến nhân viên. Nhân viên sẽ kết nối trong vài phút",
        ],
    },
    {
        input: ["dung-tu-van"],
        output: ["Dừng tư vấn"],
    },
    {
        input: ["tiep-tuc-tu-van"],
        output: ["Tiếp tục tư vấn"],
    },
];

function welcomeMessage() {
    if ($(".message-input").val() != "") {
        return false;
    }
    $(
            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
            ).appendTo($(".mCSB_container"));
    let deleteOne = document.querySelectorAll(".loading");
    if (deleteOne.length > 1) {
        deleteOne[1].remove();
    }
    updateScrollbar();
    setTimeout(function () {
        $(".message.loading").remove();
        $(
                '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>Chào bạn! Bạn cần gì nào?</div>'
                )
                .appendTo($(".mCSB_container"))
                .addClass("new");

        setTimeout(function () {
            $(
                    `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('tư vấn')">Tư Vấn Nhanh</button>`
                    )
                    .appendTo($(".mCSB_container"))
                    .addClass("new");
        }, 500);
        setTimeout(function () {
            $(
                    `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('liên hệ')">Liên hệ tư vấn trực tiếp</button>`
                    )
                    .appendTo($(".mCSB_container"))
                    .addClass("new");
        }, 1000);
        let deleteOne = document.querySelectorAll(".new");
        if (deleteOne.length > 1) {
            deleteOne[1].remove();
        }
        setDate();
        updateScrollbar();
        i++;
    }, 300 + Math.random() * 20 * 100);
}

function newMessage(className) {
    if ($(".message-input").val() != "") {
        return false;
    }
    $(".message-button").remove();
    botAnswer.forEach((message) => {
        message.input.forEach((input) => {
            if (className === input) {
                if (input === "tư vấn") {
                    $(
                            '<div class="message message-personal">' +
                            input.toUpperCase() +
                            "</div>"
                            )
                            .appendTo($(".mCSB_container"))
                            .addClass("new");
                    personalDate();
                    updateScrollbar();
                    $(
                            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
                            ).appendTo($(".mCSB_container"));
                    updateScrollbar();
                    setTimeout(function () {
                        $(".message.loading").remove();
                        let count = 0;
                        message.output.forEach((output, i) => {
                            if (i == 0) {
                                setTimeout(function () {
                                    $(
                                            '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>' +
                                            output +
                                            "</div>"
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                    setDate();
                                }, 100 + count);
                            } else {
                                if (i == 1) {
                                    let className = "tư vấn viện phí";
                                    setTimeout(function () {
                                        $(
                                                `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                                )
                                                .appendTo($(".mCSB_container"))
                                                .addClass("new");
                                    }, 100 + count);
                                } else if (i == 2) {
                                    let className = "tư vấn bác sĩ khám";
                                    setTimeout(function () {
                                        $(
                                                `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                                )
                                                .appendTo($(".mCSB_container"))
                                                .addClass("new");
                                    }, 100 + count);
                                } else if (i == 3) {
                                    let className = "tư vấn về dịch vụ khám";
                                    setTimeout(function () {
                                        $(
                                                `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                                )
                                                .appendTo($(".mCSB_container"))
                                                .addClass("new");
                                    }, 100 + count);
                                }
                            }
                            count += 700;
                            updateScrollbar();
                        });
                    }, 300 + Math.random() * 20 * 100);
                } else if (input === "liên hệ") {
                    $(
                            '<div class="message message-personal">' +
                            input.toUpperCase() +
                            "</div>"
                            )
                            .appendTo($(".mCSB_container"))
                            .addClass("new");
                    personalDate();
                    updateScrollbar();
                    $(
                            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
                            ).appendTo($(".mCSB_container"));
                    updateScrollbar();
                    setTimeout(function () {
                        $(".message.loading").remove();
                        let count = 0;
                        message.output.forEach((output, i) => {
                            if (i == 0) {
                                setTimeout(function () {
                                    $(
                                            '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>' +
                                            output +
                                            "</div>"
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                    setDate();
                                }, 100 + count);
                            } else {
                                if (i == 2) {
                                    let className = "chat trực tiếp";
                                    setTimeout(function () {
                                        $(
                                                `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                                )
                                                .appendTo($(".mCSB_container"))
                                                .addClass("new");
                                    }, 100 + count);
                                } else if (i == 1) {
                                    let className = "hotline";
                                    setTimeout(function () {
                                        $(
                                                `<button type="submit" class="btn message-button button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                                )
                                                .appendTo($(".mCSB_container"))
                                                .addClass("new");
                                    }, 100 + count);
                                }
                            }
                            count += 700;
                            updateScrollbar();
                        });
                    }, 300 + Math.random() * 20 * 100);
                } else if (input === "hotline") {
                    let count = 300;
                    $(
                            '<div class="message message-personal">' +
                            input.toUpperCase() +
                            "</div>"
                            )
                            .appendTo($(".mCSB_container"))
                            .addClass("new");
                    personalDate();
                    updateScrollbar();
                    $(
                            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
                            ).appendTo($(".mCSB_container"));
                    updateScrollbar();
                    setTimeout(function () {
                        $(".message.loading").remove();
                        message.output.forEach((output, i) => {
                            if (i == 0) {
                                setTimeout(function () {
                                    $(
                                            '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>' +
                                            output +
                                            "</div>"
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                    setDate();
                                }, 100 + count);
                            } else if (i == 2) {
                                let className = "dung-tu-van";
                                setTimeout(function () {
                                    $(
                                            `<button type="submit" class="btn message-button ${className} button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                }, 100 + count);
                            } else if (i == 1) {
                                let className = "tiep-tuc-tu-van";
                                setTimeout(function () {
                                    $(
                                            `<button type="submit" class="btn message-button ${className} button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                }, 100 + count);
                            }
                            count += 700;
                            updateScrollbar();
                        });
                    }, 300 + Math.random() * 20 * 100);
                } else if (input === "chat trực tiếp") {
                    $(".message-input").removeAttr("disabled");
                    $(
                            '<div class="message message-personal">' +
                            input.toUpperCase() +
                            "</div>"
                            )
                            .appendTo($(".mCSB_container"))
                            .addClass("new");
                    personalDate();
                    updateScrollbar();
                    $(
                            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
                            ).appendTo($(".mCSB_container"));
                    setTimeout(function () {
                        $(".message.loading").remove();
                        $(
                                '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>' +
                                message.output +
                                "</div>"
                                )
                                .appendTo($(".mCSB_container"))
                                .addClass("new");
                        setDate();
                        updateScrollbar();
                    }, 300 + Math.random() * 20 * 100);
                } else if (input === "dung-tu-van") {
                    $(".chat").addClass("before-chat");
                    $(".before-chat").removeClass("chat");
                    $(".discription").removeClass("d-none");
                    $(".sub-discription").removeClass("d-none");
                    $(".online").addClass("d-none");
                    $(".offline").addClass("d-none");
                    $(".messages-content").addClass("content-before");
                    $(".avatar").addClass("bot-avatar");
                    $(".bot-name").addClass("d-none");
                    $(".text-box").addClass("d-none");
                    $(".new").remove();
                    $(".already-login").removeClass("d-none");
                    $(".message-input").attr("disabled", "true");
                    return;
                } else if (input === "tiep-tuc-tu-van") {
                    $(".new").remove();
                    setTimeout(function () {
                        return welcomeMessage();
                    }, 100);
                } else if (input === className) {
                    $(
                            '<div class="message message-personal">' +
                            input.toUpperCase() +
                            "</div>"
                            )
                            .appendTo($(".mCSB_container"))
                            .addClass("new");
                    personalDate();
                    updateScrollbar();
                    $(
                            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
                            ).appendTo($(".mCSB_container"));
                    setTimeout(function () {
                        $(".message.loading").remove();
                        let count = 0;
                        message.output.forEach((output, i) => {
                            if (i == 0) {
                                setTimeout(function () {
                                    $(
                                            '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>' +
                                            output +
                                            "</div>"
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                    setDate();
                                }, 100 + count);
                            } else if (i == 2) {
                                let className = "dung-tu-van";
                                setTimeout(function () {
                                    $(
                                            `<button type="submit" class="btn message-button ${className} button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                }, 100 + count);
                            } else if (i == 1) {
                                let className = "tiep-tuc-tu-van";
                                setTimeout(function () {
                                    $(
                                            `<button type="submit" class="btn message-button ${className} button btnFloat btnBlueGreen new" onclick="newMessage('${className}')">${output}</button>`
                                            )
                                            .appendTo($(".mCSB_container"))
                                            .addClass("new");
                                }, 100 + count);
                            }
                            count += 700;
                            updateScrollbar();
                        });
                    }, 300 + Math.random() * 20 * 100);
                } else {
                    $(
                            '<div class="message message-personal">' +
                            input.toUpperCase() +
                            "</div>"
                            )
                            .appendTo($(".mCSB_container"))
                            .addClass("new");
                    personalDate();
                    updateScrollbar();
                    $(
                            '<div class="message loading new"><figure class="avatar"><img src="./images/bot.webp" /></figure><span></span></div>'
                            ).appendTo($(".mCSB_container"));
                    setTimeout(function () {
                        $(".message.loading").remove();
                        $(
                                '<div class="message new"><figure class="avatar"><img src="./images/bot.webp" /></figure>' +
                                message.output +
                                "</div>"
                                )
                                .appendTo($(".mCSB_container"))
                                .addClass("new");
                        setDate();
                        updateScrollbar();
                    }, 300 + Math.random() * 20 * 100);
                }
            }
        });
    });
}