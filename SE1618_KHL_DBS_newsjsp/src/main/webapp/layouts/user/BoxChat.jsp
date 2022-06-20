<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="chat-box">
    <input type="checkbox" id="icon-click" class="chat-box__click" hidden>
    <div class="chat-box__control">
        <label for="icon-click" class="chat-box__label">
            <i class="chat-box__icon-messenger fa-brands fa-facebook-messenger"></i>
            <i class="chat-box__icon-times fa-solid fa-xmark"></i>
        </label>
    </div>
    <div class="before-chat" id="chat">
        <div class="chat-title">
            <h1 class="bot-name d-none">Support</h1>
            <h2 class="online d-none" style="margin-top: 3px;">
                <img src="./images/circle-256.png" alt="" width="10px"> Đang Online
            </h2>
            <h2 class="offline d-none" style="margin-top: 3px;">
                <img src="./images/grey-circle.png" alt="" width="10px"> Đang Offline
            </h2>
            <figure class="avatar bot-avatar">
                <img src="./images/bot.webp" />
            </figure>
            <figure class="avatar icon-minus" style="cursor: pointer; display: flex; justify-content: center; align-items: center;">
                <i class="fas fa-minus text-white"></i>
            </figure>
            <figure class="avatar icon-exit" style="cursor: pointer; display: flex; justify-content: center; align-items: center;">
                <i class="fa-solid fa-xmark text-white"></i>
            </figure>
        </div>
        <div class="messages message-outline">
            <div class="messages-content content-before">
                <h2 class="discription" style="margin-left: 20px !important; margin-top: 15px; font-size: 25px;">
                    Chat với bot
                </h2>
                <h2 class="sub-discription" style="margin-left: 20px !important; font-size: 14px;">
                    Chào bạn! Bot có thể giúp gì cho bạn?
                </h2>
            </div>
        </div>
        <button type="submit" class="message-box message-start-chat already-login">
            Tiếp tục với ai đó
        </button>
        <button type="submit" class="message-box message-start-chat not-logged-in-yet d-none">
            Hãy đăng nhập để có thể tương tác
        </button>
        <div class="message-box text-box d-none">
            <textarea type="text" class="message-input text-white" placeholder='Type message...' disabled></textarea>
            <button type="submit" class="message-submit">Send</button>
        </div>
    </div>
</div>