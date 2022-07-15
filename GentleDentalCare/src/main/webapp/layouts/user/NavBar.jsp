<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-none d-xxl-flex">
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/home" class="logo">
            <img src="images/favicon-100x100.png" alt="logo">
        </a>

        <div class="header__menu">
            <ul class="menu">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/service">Service</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/news">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/booking">Booking</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about">About us</a>
                </li>
            </ul>
        </div>

        <div class="header__tool">
            <div id="searchBox">
                <div class="button-search">
                    <i class="btn-search fa-solid fa-magnifying-glass"></i>
                </div>
                <input oninput="searchName(this)" onfocusout="showLoadMoreButton()" class="search-place" type="text" placeholder="Search...">
                    <button onclick="showMicrophoneBox()" type="button" class="button-mic">
                        <svg class="goxjub" focusable="false" viewBox="0 0 24 24"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill="#4285f4"
                                  d="m12 15c1.66 0 3-1.31 3-2.97v-7.02c0-1.66-1.34-3.01-3-3.01s-3 1.34-3 3.01v7.02c0 1.66 1.34 2.97 3 2.97z">
                            </path>
                            <path fill="#34a853" d="m11 18.08h2v3.92h-2z"></path>
                            <path fill="#fbbc04"
                                  d="m7.05 16.87c-1.27-1.33-2.05-2.83-2.05-4.87h2c0 1.45 0.56 2.42 1.47 3.38v0.32l-1.15 1.18z">
                            </path>
                            <path fill="#ea4335"
                                  d="m12 16.93a4.97 5.25 0 0 1 -3.54 -1.55l-1.41 1.49c1.26 1.34 3.02 2.13 4.95 2.13 3.87 0 6.99-2.92 6.99-7h-1.99c0 2.92-2.24 4.93-5 4.93z">
                            </path>
                        </svg>
                    </button>
            </div>
            <div class="microphone-wrapper" id="microphone-wrapper">
                <div class="microphone-box" role="dialog" tabindex="-1">
                    <div class="microphone-container" tabindex="-1">
                        <div class="microphone-header">
                            <div class="microphone-header__text">
                                <div id="microphone-header__prompt" class="microphone-header__prompt"></div>
                            </div>
                            <div class="microphone-exit__button">
                                <button onclick="hideMicrophoneBox()" class="icon-exit-button">
                                    <i class="fa-solid fa-xmark"></i>
                                </button>
                            </div>
                        </div>
                        <div class="microphone-body">
                            <div id="microphone-body-text" class="microphone-body-text"></div>
                        </div>
                        <div class="microphone-footer-button">
                            <div onclick="activeMicrophone()" class="microphone-footer-container">
                                <div class="microphone-pulse"></div>
                                <div class="microphone-levels"></div>
                                <div class="microphone-circle" role="button" tabindex="0">
                                    <i class="fa-solid fa-microphone-lines"></i>
                                </div>
                            </div>
                            <div id="microphone-footer-label" class="microphone-footer-label"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                
        <div class="header__notify">
            <div class="notification has-notification">
                <div style="position: relative;">
                    <div class="bell" id="bell">
                        <i class="fas fa-bell"></i>
                    </div>
                    <c:if test="${sessionScope.LOGIN_USER != null}">
                        <c:if test="${sessionScope.NOTIFICATION_LIST != null}">
                            <div class="bell-number" id="bell-number">${sessionScope.COUNT_NOTIFICATION_NOT_READ}</div>
                        </c:if>
                    </c:if>
                </div>
                <div class="notification-box">
                    <div class="notification-box-header">
                        <h3>Notification</h3>
                    </div>
                    <c:if test="${sessionScope.LOGIN_USER == null}">
                        <div class="notification-item">
                            <div class="notification-content">
                                <img src="./images/notification.png" alt="Notification" class="notification-img">
                                <div class="notification-info">
                                    <span class="notification-message">Sign in to see notifications</span>
                                </div>
                            </div>
                        </div>
                        <div class="notification-footer">
                            <button type="button"
                                onclick="window.location.href = '${pageContext.request.contextPath}/login';"
                                class="footer-registration">Registration</button>
                            <button type="button"
                                onclick="window.location.href = '${pageContext.request.contextPath}/login';"
                                class="footer-login">Login</button>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.LOGIN_USER != null}">
                        <c:if test="${sessionScope.NOTIFICATION_LIST == null}">
                            <div class="notification-empty">
                                <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_buhby0ug.json" background="transparent" speed="1" loop autoplay class="notification-empty_image"></lottie-player>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.NOTIFICATION_LIST != null}">
                            <div class="notification-box-body">
                                <ul>
                                    <c:forEach items="${sessionScope.NOTIFICATION_LIST}" var="notification">
                                        <c:if test="${notification.notifyType.equals('NewAccount')}">
                                            <li id="notification-box-list">
                                                <div class="notification-logo">
                                                    <img src="./images/favicon-100x100.png" alt="logo">
                                                </div>
                                                <div class="notification-message-wrapper">
                                                    <div>
                                                        Welcome <span class="name">${sessionScope.LOGIN_USER.fullName}</span> to Gentle Dental Care.
                                                        Always be passionate, persistent and pursue your goals
                                                        together ❤️
                                                    </div>
                                                    <div class="notification-created">${sessionScope.TIME_NOTIFICATION}</div>
                                                </div>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="header__action">
            <c:if test="${sessionScope.LOGIN_USER == null}">
                <div class="header__action-login">
                    <a href="${pageContext.request.contextPath}/login" class="btn-login-link">
                        <span class="name-desc">Login</span>
                        <div class="btn-icon login">
                            <i class="fa-solid fa-right-to-bracket"></i>
                        </div>
                    </a>
                </div>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER != null}">
                <div class="header__action-user">
                    <div onclick="menuToggle()" class="user-avatar">
                        <c:if test="${sessionScope.LOGIN_USER.imageAvatar != null}">
                            <div class="img-user">
                                <img src="data:image/png;base64,${sessionScope.LOGIN_USER.imageAvatar}" />
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.LOGIN_USER.imageAvatar == null}">
                            <div class="img-user" style="background: ${sessionScope.LOGIN_USER.colorAvatar}">
                                <p>${sessionScope.LOGIN_USER.defaultAvatar.toUpperCase()}</p>
                            </div>
                        </c:if>
                    </div>
                    <div class="user-menu">
                        <ul>
                            <li>
                                <i class="uil uil-user-circle"></i>
                                <a href="${pageContext.request.contextPath}/info-profile">My Profile</a>
                            </li>
                            <li>
                                <i class="fa-solid fa-clock-rotate-left"></i>
                                <a href="${pageContext.request.contextPath}/history-booking">History Booking</a>
                            </li>
                            <li>
                                <i class="uil uil-edit"></i>
                                <a href="${pageContext.request.contextPath}/edit-profile">Edit Profile</a>
                            </li>
                            <li>
                                <i class="uil uil-padlock"></i>
                                <a href="${pageContext.request.contextPath}/change-password">Change Password</a>
                            </li>
                            <li>
                                <i class="uil uil-signout"></i>
                                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<div class="d-flex d-xxl-none nav__bar-mobile pt-3 pb-3 container-fluid">
    <div class="col-xl-2 col-lg-2 col-md-2 col-sm-3 col-4 nav__bar-mobile-logo">
        <a href="${pageContext.request.contextPath}/home" class="nav__bar-logo-mobile-link">
            <img src="./images/favicon-100x100.png" alt="logo">
        </a>
    </div>
    <div class="col-xl-10 col-lg-10 col-md-9 col-sm-8 col-7 nav__bar-mobile-tool">
        <div class="tool-button">
            <div onclick="showSearchBox()" id="button-icon-search" class="d-lg-none">
                <div class="tool-search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
            </div>
            <div onclick="showMicrophoneBoxMobile()" id="button-icon-mic" class="d-lg-none">
                <div class="tool-mic">
                    <i class="fa-solid fa-microphone"></i>
                </div>
            </div>
            <div class="d-none d-lg-flex search-box">
                <div class="button-search">
                    <i class="btn-search fa-solid fa-magnifying-glass"></i>
                </div>
                <input class="search-place" type="text" placeholder="Search service">
                    <button onclick="showMicrophoneBox()" type="button" class="button-mic">
                        <svg class="goxjub" focusable="false" viewBox="0 0 24 24"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill="#4285f4"
                                  d="m12 15c1.66 0 3-1.31 3-2.97v-7.02c0-1.66-1.34-3.01-3-3.01s-3 1.34-3 3.01v7.02c0 1.66 1.34 2.97 3 2.97z">
                            </path>
                            <path fill="#34a853" d="m11 18.08h2v3.92h-2z"></path>
                            <path fill="#fbbc04"
                                  d="m7.05 16.87c-1.27-1.33-2.05-2.83-2.05-4.87h2c0 1.45 0.56 2.42 1.47 3.38v0.32l-1.15 1.18z">
                            </path>
                            <path fill="#ea4335"
                                  d="m12 16.93a4.97 5.25 0 0 1 -3.54 -1.55l-1.41 1.49c1.26 1.34 3.02 2.13 4.95 2.13 3.87 0 6.99-2.92 6.99-7h-1.99c0 2.92-2.24 4.93-5 4.93z">
                            </path>
                        </svg>
                    </button>
            </div>
            <div id="button-icon-bell">
                <div class="bell" id="bell">
                    <i class="fas fa-bell"></i>
                </div>
                <c:if test="${sessionScope.LOGIN_USER != null}">
                    <c:if test="${sessionScope.NOTIFICATION_LIST != null}">
                        <div class="bell-number" id="bell-number">${sessionScope.COUNT_NOTIFICATION_NOT_READ}</div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <div id="searchBox" class="d-lg-none container-fluid">
            <div class="col-md-1 col-sm-1 col-1 button-search">
                <i class="btn-search fa-solid fa-magnifying-glass"></i>
            </div>
            <input class="col-md-9 col-sm-9 col-9 search-place" type="text" placeholder="Search service">
                <button type="button" onclick="showMicrophoneBoxMobile()"
                        class="col-md-1 col-sm-1 col-1 button-mic">
                    <svg class="goxjub" focusable="false" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path fill="#4285f4"
                              d="m12 15c1.66 0 3-1.31 3-2.97v-7.02c0-1.66-1.34-3.01-3-3.01s-3 1.34-3 3.01v7.02c0 1.66 1.34 2.97 3 2.97z">
                        </path>
                        <path fill="#34a853" d="m11 18.08h2v3.92h-2z"></path>
                        <path fill="#fbbc04"
                              d="m7.05 16.87c-1.27-1.33-2.05-2.83-2.05-4.87h2c0 1.45 0.56 2.42 1.47 3.38v0.32l-1.15 1.18z">
                        </path>
                        <path fill="#ea4335"
                              d="m12 16.93a4.97 5.25 0 0 1 -3.54 -1.55l-1.41 1.49c1.26 1.34 3.02 2.13 4.95 2.13 3.87 0 6.99-2.92 6.99-7h-1.99c0 2.92-2.24 4.93-5 4.93z">
                        </path>
                    </svg>
                </button>
                <button type="button" onclick="showSearchBox()" class="col-md-1 col-sm-1 col-1 button-close">
                    <i class="fa-solid fa-xmark"></i>
                </button>
        </div>
        <div class="mobile__microphone-wrapper" id="mobile__microphone-wrapper">
            <div class="mobile__microphone-box" role="dialog" tabindex="-1">
                <div class="mobile__microphone-container" tabindex="-1">
                    <div class="mobile__microphone-header">
                        <div class="mobile-header__text">
                            <div id="mobile-header__prompt" class="mobile-header__prompt"></div>
                        </div>
                        <div class="mobile-exit__button">
                            <button onclick="hideMicrophoneBoxMobile()" class="mobile-icon-exit-button">
                                <i class="fa-solid fa-xmark"></i>
                            </button>
                        </div>
                    </div>
                    <div class="mobile__microphone-body">
                        <div class="mobile-microphone-body-text" id="mobile-microphone-body-text"></div>
                    </div>
                    <div class="mobile__microphone-footer-button">
                        <div onclick="activeMicrophoneMobile()" class="mobile__microphone-footer-container">
                            <div class="mobile__microphone-pulse"></div>
                            <div class="mobile__microphone-levels"></div>
                            <div class="mobile__microphone-circle" role="button" tabindex="0">
                                <i class="fa-solid fa-microphone-lines"></i>
                            </div>
                        </div>
                        <div class="mobile__microphone-footer-label" id="mobile__microphone-footer-label"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-1 col-lg-1 col-md-1 col-sm-1 col-1 nav__bar-mobile-menu">
        <label for="mobile-menu-checkbox" class="nav__bar-mobile-menu-label">
            <i class="fa-solid fa-bars"></i>
        </label>
    </div>
    <input type="checkbox" onclick="BlockScrollInUserMenu()" hidden id="mobile-menu-checkbox" class="nav__menu-mobile">
        <div class="user__mobile">
            <div class="user__mobile-header">
                <c:if test="${sessionScope.LOGIN_USER != null}">
                    <div class="header__action-user">
                        <div class="user-avatar">
                            <c:if test="${sessionScope.LOGIN_USER.imageAvatar != null}">
                                <div class="img-user">
                                    <img src="data:image/png;base64,${sessionScope.LOGIN_USER.imageAvatar}" />
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.LOGIN_USER.imageAvatar == null}">
                                <div class="img-user" style="background: ${sessionScope.LOGIN_USER.colorAvatar}">
                                    <p>${sessionScope.LOGIN_USER.defaultAvatar.toUpperCase()}</p>
                                </div>
                            </c:if>
                        </div>
                        <div class="user-name">
                            <h4>${sessionScope.LOGIN_USER.fullName}</h4>
                        </div>
                    </div>
                </c:if>

                <label for="mobile-menu-checkbox" class="user__mobile-header-close">
                    <i class="fa-solid fa-xmark" style="color: red;"></i>
                </label>
            </div>

            <ul class="user__mobile-list">
                <c:if test="${sessionScope.LOGIN_USER != null}">
                    <li class="user__mobile-item">
                        <a href="${pageContext.request.contextPath}/info-profile" class="user__mobile-link">
                            <i class="fa-solid fa-user"></i>
                            <span class="user__mobile-name">My Profile</span>
                        </a>
                    </li>
                            
                    <li class="user__mobile-item">
                        <a href="${pageContext.request.contextPath}/history-booking" class="user__mobile-link">
                            <i class="fa-solid fa-clock-rotate-left"></i>
                            <span class="user__mobile-name">History Booking</span>
                        </a>
                    </li>

                    <li class="user__mobile-item">
                        <a href="${pageContext.request.contextPath}/edit-profile" class="user__mobile-link">
                            <i class="fa-solid fa-user-pen"></i>
                            <span class="user__mobile-name">Edit Profile</span>
                        </a>
                    </li>

                    <li class="user__mobile-item item-border">
                        <a href="${pageContext.request.contextPath}/change-password" class="user__mobile-link">
                            <i class="fa-solid fa-lock"></i>
                            <span class="user__mobile-name">Change Password</span>
                        </a>
                    </li>
                </c:if>

                <li class="user__mobile-item">
                    <a href="${pageContext.request.contextPath}/home" class="user__mobile-link">
                        <i class="fa-solid fa-house"></i>
                        <span class="user__mobile-name">Home</span>
                    </a>
                </li>

                <li class="user__mobile-item">
                    <a href="${pageContext.request.contextPath}/service" class="user__mobile-link">
                        <i class="fa-solid fa-wrench"></i>
                        <span class="user__mobile-name">Services</span>
                    </a>
                </li>

                <li class="user__mobile-item">
                    <a href="${pageContext.request.contextPath}/news" class="user__mobile-link">
                        <i class="fa-solid fa-brain"></i>
                        <span class="user__mobile-name">News</span>
                    </a>
                </li>

                <li class="user__mobile-item">
                    <a href="${pageContext.request.contextPath}/booking" class="user__mobile-link">
                        <i class="fa-solid fa-calendar-days"></i>
                        <span class="user__mobile-name">Booking</span>
                    </a>
                </li>

                <li class="user__mobile-item item-border">
                    <a href="${pageContext.request.contextPath}/about" class="user__mobile-link">
                        <i class="fa-solid fa-users"></i>
                        <span class="user__mobile-name">About Us</span>
                    </a>
                </li>

                <c:if test="${sessionScope.LOGIN_USER == null}">
                    <li class="user__mobile-item">
                        <a href="${pageContext.request.contextPath}/login" class="user__mobile-link">
                            <i class="fa-solid fa-arrow-right-to-bracket"></i>
                            <span class="user__mobile-name">Login</span>
                        </a>
                    </li>
                </c:if>
                        
                <c:if test="${sessionScope.LOGIN_USER != null}">
                    <li class="user__mobile-item">
                        <a href="${pageContext.request.contextPath}/logout" class="user__mobile-link">
                            <i class="fa-solid fa-right-from-bracket"></i>
                            <span class="user__mobile-name">Logout</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
</div>