<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Password | ${sessionScope.LOGIN_EMPLOYEE.fullName}</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href=".././images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
        <!-- Box Icons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- UN ICONS -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <!-- LINK STYLE -->
        <link rel="stylesheet" href=".././css/employee/EmployeeRoot.css">
        <link rel="stylesheet" href=".././css/Loader.css">
        <link rel="stylesheet" href=".././css/ScrollBackToTop.css">
        <link rel="stylesheet" href=".././css/employee/NavBar.css">
        <link rel="stylesheet" href=".././css/employee/ChangePassword.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        
        <header class="header-background">
            <jsp:include page="../../layouts/employee/NavBar.jsp"></jsp:include>
        </header>
        
        <!--********************* Change Password BODY *********************-->
        <div class="container pt-5 pb-5">
            <form action="${pageContext.request.contextPath}/employee/change-password" method="post">
                <div class="row gutters">
                    <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                        <div class="card h-100">
                            <div class="card-body">
                                <div class="account-settings">
                                    <div class="user-profile">
                                        <div class="user-avatar">
                                            <div class="setting-avatar_photo" style="background: ${sessionScope.LOGIN_EMPLOYEE.imageAvatar == null ? sessionScope.LOGIN_EMPLOYEE.colorAvatar : ""}">
                                                <c:choose>
                                                    <c:when test = "${sessionScope.LOGIN_EMPLOYEE.imageAvatar == null}">
                                                       <p>${sessionScope.LOGIN_EMPLOYEE.defaultAvatar.toUpperCase()}</p>
                                                    </c:when>
                                                       
                                                    <c:when test = "${sessionScope.LOGIN_EMPLOYEE.imageAvatar != null}">
                                                       <img src="data:image/png;base64,${sessionScope.LOGIN_EMPLOYEE.imageAvatar}" />
                                                    </c:when>
                                                       
                                                    <c:when test = "${IMAGE_AVATAR != null}">
                                                        <img src="${fileURL}" alt="" >
                                                        <input type="hidden" value="${fileURL}" name="imageAvatar">
                                                    </c:when>
                                                    <c:when test = "${IMAGE_AVATAR == null}">
                                                        <p>${sessionScope.LOGIN_EMPLOYEE.defaultAvatar.toUpperCase()}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <h5 class="user-name">${sessionScope.LOGIN_EMPLOYEE.fullName}</h5>
                                        <h6 class="user-email">${sessionScope.LOGIN_EMPLOYEE.userEmail}</h6>
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
                                            <label for="oldPassword" class="setting-form_label">Old Password</label>
                                            <div class="setting-form_input">
                                                <input type="password" oninput="CheckOldPassword()" onblur="CheckOldPassword()" onkeyup="OldPasswordKeyUp()" name="oldPassword" id="oldPassword" placeholder="Enter Old Password">
                                                <span class="showBtn" id="show-old-password">
                                                    <i class="fas fa-eye-slash" id="show-old-password-icon"></i>
                                                </span>
                                                <i class='bx bx-check-circle' id="oldPassword-icon-check"></i>
                                                <i class='bx bx-error-circle' id="oldPassword-icon-error"></i>
                                            </div>
                                            <div class="message">
                                                <span class="error-message" id="oldPassword-error"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <label for="newPassword" class="setting-form_label">New Password</label>
                                            <div class="setting-form_input">
                                                <input type="password" oninput="CheckNewPassword()" onblur="CheckNewPassword()" onkeyup="NewPasswordKeyUp()" name="newPassword" id="newPassword" placeholder="Enter New Password">
                                                <span class="showBtn" id="show-new-password">
                                                    <i class="fas fa-eye-slash" id="show-new-password-icon"></i>
                                                </span>
                                                <i class='bx bx-check-circle' id="newPassword-icon-check"></i>
                                                <i class='bx bx-error-circle' id="newPassword-icon-error"></i>
                                            </div>
                                            <div class="message">
                                                <span class="error-message" id="newPassword-error"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        <div class="setting-form_content">
                                            <label for="confirmPassword" class="setting-form_label">Confirm Password</label>
                                            <div class="setting-form_input">
                                                <input type="password" oninput="CheckConfirmPassword()" onblur="CheckConfirmPassword()" onkeyup="ConfirmPasswordKeyUp()" name="confirmPassword" id="confirmPassword" placeholder="Enter Confirm Password">
                                                <span class="showBtn" id="show-confirm-password">
                                                    <i class="fas fa-eye-slash" id="show-confirm-password-icon"></i>
                                                </span>
                                                <i class='bx bx-check-circle' id="confirmPassword-icon-check"></i>
                                                <i class='bx bx-error-circle' id="confirmPassword-icon-error"></i>
                                            </div>
                                            <div class="message">
                                                <span class="error-message" id="confirmPassword-error"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row gutters">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 pt-3">
                                        <div class="text-right">
                                            <button type="submit" id="submit" name="submit"
                                                class="btn btn-primary">Change Password</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!--********************* END CHANGE PASSWORD BODY *****************-->
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- J QUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- LINK JAVA SCRIPT -->
        <script src=".././js/employee/EmployeeRoot.js"></script>
        <script src=".././js/employee/NavBar.js"></script>
        <script src=".././js/ScrollBackToTop.js"></script>
        <script src=".././js/employee/ChangePassword.js"></script>
    </body>
</html>
