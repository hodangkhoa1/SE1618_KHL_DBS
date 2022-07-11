<%@page import="com.khl.gentledentalcare.models.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    AccountError accountError = (AccountError) request.getAttribute("EDIT_PROFILE_ERROR");
    if(accountError == null) {
        accountError = new AccountError();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Profile | ${sessionScope.LOGIN_USER.fullName}</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK FONTAWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- Box Icons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- UN ICONS -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <!-- AOS  -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <!-- Malihu Custom Scrollbar -->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.min.css'>
        <!-- LINK STYLE -->
        <link rel="stylesheet" href="./css/user/UserRoot.css">
        <link rel="stylesheet" href="./css/Loader.css">
        <link rel="stylesheet" href="./css/ScrollBackToTop.css">
        <link rel="stylesheet" href="./css/user/BoxChat.css">
        <link rel="stylesheet" href="./css/user/NavBar.css">
        <link rel="stylesheet" href="./css/user/EditProfile.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
        <link rel="stylesheet" href="./css/UploadImagePopup.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        <jsp:include page="../../layouts/UploadImagePopup.html"></jsp:include>
        <jsp:include page="../../layouts/user/BoxChat.jsp"></jsp:include>
        
        <header class="header-background">
            <jsp:include page="../../layouts/user/NavBar.jsp"></jsp:include>
        </header>
        
        <!--********************* EDIT PROFILE BODY *********************-->
        <div class="container pt-5 pb-5">
            <form action="${pageContext.request.contextPath}/edit-profile" method="post">
                <div class="row gutters">
                    <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                        <div class="card h-100">
                            <div class="card-body">
                                <div class="account-settings">
                                    <div class="user-profile">
                                        <div class="user-avatar">
                                            <div class="setting-avatar_photo" style="background: ${sessionScope.LOGIN_USER.imageAvatar == null ? sessionScope.LOGIN_USER.colorAvatar : ""}">
                                                <c:choose>
                                                    <c:when test = "${sessionScope.LOGIN_USER.imageAvatar == null}">
                                                       <p>${sessionScope.LOGIN_USER.defaultAvatar.toUpperCase()}</p>
                                                    </c:when>
                                                       
                                                    <c:when test = "${sessionScope.LOGIN_USER.imageAvatar != null}">
                                                       <img src="data:image/png;base64,${sessionScope.LOGIN_USER.imageAvatar}" />
                                                    </c:when>
                                                       
                                                    <c:when test = "${IMAGE_AVATAR != null}">
                                                        <img src="${fileURL}" alt="" >
                                                        <input type="hidden" value="${fileURL}" name="imageAvatar">
                                                    </c:when>
                                                    <c:when test = "${IMAGE_AVATAR == null}">
                                                        <p>${sessionScope.LOGIN_USER.defaultAvatar.toUpperCase()}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <h5 class="user-name">${sessionScope.LOGIN_USER.fullName}</h5>
                                        <h6 class="user-email">${sessionScope.LOGIN_USER.userEmail}</h6>
                                    </div>
                                    <div class="button-action row">
                                        <div
                                            class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 d-flex justify-content-lg-center justify-content-center">
                                            <button type="button" class="change-avatar">Change Avatar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                        <div class="card h-100">
                            <div class="card-body">
                                <div class="row gutters">
                                    <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <label for="fullName" class="setting-form_label">Full Name</label>
                                            <div class="setting-form_input">
                                                <input type="text" oninput="CheckFullName()" onblur="CheckFullName()"
                                                    name="fullName" id="fullName" value="${FULLNAME != null ? FULLNAME : sessionScope.LOGIN_USER.fullName}">
                                                <i class='bx bx-check-circle' id="fullName-icon-check"></i>
                                                <i class='bx bx-error-circle' id="fullName-icon-error"></i>
                                            </div>
                                            <div class="message">
                                                <span class="error-message" id="fullName-error"><%=accountError.getFullNameError()%></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <label for="email" class="setting-form_label">Email</label>
                                            <div class="setting-form_input">
                                                <input type="email" name="email" id="email" value="${sessionScope.LOGIN_USER.userEmail}"
                                                    placeholder="Enter Email" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <p class="title">Gender</p>
                                            <div class="radio-box">
                                                <label class="radio-container">Male
                                                    <input type="radio" checked name="userSex" value="Male">
                                                    <span class="checkmark"></span>
                                                </label>
                                                <label class="radio-container">Female
                                                    <input type="radio" name="userSex" value="Female">
                                                    <span class="checkmark"></span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <p class="title">Birth Day</p>
                                            <div class="select-date">
                                                <div class="select-box">
                                                    <label for="day" class="setting-form_label">Day</label>
                                                    <select class="select-content" name="day" id="day"></select>
                                                </div>
                                                <div class="select-box">
                                                    <label for="month" class="setting-form_label">Month</label>
                                                    <select class="select-content" name="month" id="month"></select>
                                                </div>
                                                <div class="select-box">
                                                    <label for="year" class="setting-form_label">Year</label>
                                                    <select class="select-content" name="year" id="year"></select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <label for="phoneNumber" class="setting-form_label">Phone Number</label>
                                            <div class="setting-form_input">
                                                <input type="text" oninput="CheckPhoneNumber()" onblur="CheckPhoneNumber()"
                                                    name="phoneNumber" id="phoneNumber" value="${PHONE_NUMBER != null ? PHONE_NUMBER : sessionScope.LOGIN_USER.userPhone}">
                                                <i class='bx bx-check-circle' id="phoneNumber-icon-check"></i>
                                                <i class='bx bx-error-circle' id="phoneNumber-icon-error"></i>
                                            </div>
                                            <div class="message">
                                                <span class="error-message" id="phoneNumber-error"><%=accountError.getPhoneNumberError()%></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <label for="address" class="setting-form_label">Address</label>
                                            <div class="setting-form_input">
                                                <input type="text" oninput="CheckAddress()" onblur="CheckAddress()"
                                                    name="address" id="address" value="${ADDRESS != null ? ADDRESS : sessionScope.LOGIN_USER.userAddress}">
                                                <i class='bx bx-check-circle' id="address-icon-check"></i>
                                                <i class='bx bx-error-circle' id="address-icon-error"></i>
                                            </div>
                                            <div class="message">
                                                <span class="error-message" id="address-error"><%=accountError.getAddressError()%></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row gutters">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 pt-3">
                                        <div class="text-right">
                                            <button type="submit" id="submit" name="submit"
                                                class="btn btn-primary">Update</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!--********************* END EDIT PROFILE BODY *****************-->
        
        <jsp:include page="../../layouts/user/FooterPage.jsp"></jsp:include>
        <jsp:include page="../../layouts/user/SupportOnline.jsp"></jsp:include>
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- JQUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- AOS  -->
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <!-- Malihu Custom Scrollbar -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.concat.min.js'></script>
        <!-- LINK JS -->
        <script src="./js/user/UserRoot.js"></script>
        <script src="./js/ScrollBackToTop.js"></script>
        <script src="./js/user/BoxChat.js"></script>
        <script src="./js/user/NavBar.js"></script>
        <script src="./js/user/EditProfile.js"></script>
        <script src="./js/user/UploadImagePopup.js"></script>
    </body>
</html>
