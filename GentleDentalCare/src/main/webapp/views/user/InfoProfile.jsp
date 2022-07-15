<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile | ${sessionScope.LOGIN_USER.fullName}</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
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
        <link rel="stylesheet" href="./css/user/InfoProfile.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        <jsp:include page="../../layouts/user/BoxChat.jsp"></jsp:include>
        
        <header class="header-background">
            <jsp:include page="../../layouts/user/NavBar.jsp"></jsp:include>
        </header>
        
        <!--********************* PROFILE BODY *********************-->
        <div class="container pt-5 pb-5">
            <div class="row gutters">
                <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12 pb-4 pb-lg-0">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="account-profile">
                                <div class="user-profile">
                                    <div class="user-avatar">
                                        <c:if test="${sessionScope.LOGIN_USER.imageAvatar != null}">
                                            <img src="data:image/png;base64,${sessionScope.LOGIN_USER.imageAvatar}" alt="">
                                        </c:if>
                                        <c:if test="${sessionScope.LOGIN_USER.imageAvatar == null}">
                                            <div class="avatar-photo" style="background: ${sessionScope.LOGIN_USER.colorAvatar}">
                                                <p>${sessionScope.LOGIN_USER.defaultAvatar.toUpperCase()}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                    <h5 class="user-name">${sessionScope.LOGIN_USER.fullName}</h5>
                                    <h6 class="user-email">${sessionScope.LOGIN_USER.userEmail}</h6>
                                </div>
                                <div class="user-sidenav">
                                    <div class="user-sidenav__url">
                                        <button onclick="DeleteAccount('${pageContext.request.contextPath}/info-profile', '${sessionScope.LOGIN_USER.userEmail}')" type="button" class="sidenav-url__title">Delete Account</button>
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
                                <div
                                    class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 d-flex align-items-center justify-content-end">
                                    <button onclick="window.location.href = '${pageContext.request.contextPath}/edit-profile'" type="button" class="button-edit">
                                        <i class="fa fa-pen fa-xs edit"></i>
                                    </button>
                                </div>
                                <div class="table-users">
                                    <table cellspacing="0">
                                        <tr>
                                            <th>Full Name</th>
                                            <td>${sessionScope.LOGIN_USER.fullName}</td>
                                        </tr>

                                        <tr>
                                            <th>Gender</th>
                                            <td>${sessionScope.LOGIN_USER.gender}</td>
                                        </tr>

                                        <tr>
                                            <th>Email</th>
                                            <td>${sessionScope.LOGIN_USER.userEmail}</td>
                                        </tr>

                                        <tr>
                                            <th>Date Of Birth</th>
                                            <td><fmt:formatDate value="${sessionScope.LOGIN_USER.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                                        </tr>

                                        <tr>
                                            <th>Phone Number</th>
                                            <td>${sessionScope.LOGIN_USER.userPhone}</td>
                                        </tr>

                                        <tr>
                                            <th>Address</th>
                                            <td>${sessionScope.LOGIN_USER.userAddress}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--********************* END PROFILE BODY *****************-->
        
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
        <!-- SWEETALERT2 -->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- LINK JS -->
        <script src="./js/user/UserRoot.js"></script>
        <script src="./js/ScrollBackToTop.js"></script>
        <script src="./js/user/BoxChat.js"></script>
        <script src="./js/user/NavBar.js"></script>
        <script src="./js/user/InfoProfile.js"></script>
    </body>
</html>
