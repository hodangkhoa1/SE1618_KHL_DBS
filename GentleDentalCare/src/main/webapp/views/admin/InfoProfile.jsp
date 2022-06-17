<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile | ${sessionScope.LOGIN_ADMIN.fullName}</title>
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
        <link href=".././css/admin/AdminRoot.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/NavBar.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/SideBar.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/InfoProfile.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/FooterPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../../layouts/admin/SideBar.jsp"></jsp:include>
        
        <section id="content">
            <jsp:include page="../../layouts/admin/NavBar.jsp"></jsp:include>
            
            <!-- MAIN -->
            <main class="main">
                <h1 class="title">Profile</h1>
                <ul class="breadcrumbs">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/dashboard">Gentle Dental Care</a>
                    </li>
                    <li class="divider">/</li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/info-profile" class="active">Profile</a>
                    </li>
                </ul>

                <!--********************* PROFILE BODY *********************-->
                <div class="container pt-5 pb-5">
                    <div class="row gutters">
                        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12 pb-4 pb-lg-0">
                            <div class="card h-100">
                                <div class="card-body">
                                    <div class="account-profile">
                                        <div class="user-profile">
                                            <div class="user-avatar">
                                                <c:if test="${sessionScope.LOGIN_ADMIN.imageAvatar != null}">
                                                    <img src="data:image/png;base64,${sessionScope.LOGIN_ADMIN.imageAvatar}" alt="${sessionScope.LOGIN_ADMIN.fullName}">
                                                </c:if>
                                                <c:if test="${sessionScope.LOGIN_ADMIN.imageAvatar == null}">
                                                    <div class="avatar-photo" style="background: ${sessionScope.LOGIN_ADMIN.colorAvatar};">
                                                        <p>${sessionScope.LOGIN_ADMIN.defaultAvatar.toUpperCase()}</p>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <h5 class="user-name">${sessionScope.LOGIN_ADMIN.fullName}</h5>
                                            <h6 class="user-email">${sessionScope.LOGIN_ADMIN.userEmail}</h6>
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
                                            <button type="button" class="button-edit">
                                                <i class="fa fa-pen fa-xs edit"></i>
                                            </button>
                                        </div>
                                        <div class="table-users">
                                            <table cellspacing="0">
                                                <tr>
                                                    <th>Full Name</th>
                                                    <td>${sessionScope.LOGIN_ADMIN.fullName}</td>
                                                </tr>

                                                <tr>
                                                    <th>Gender</th>
                                                    <td>${sessionScope.LOGIN_ADMIN.gender}</td>
                                                </tr>

                                                <tr>
                                                    <th>Email</th>
                                                    <td>${sessionScope.LOGIN_ADMIN.userEmail}</td>
                                                </tr>

                                                <tr>
                                                    <th>Date Of Birth</th>
                                                    <td><fmt:formatDate value="${sessionScope.LOGIN_ADMIN.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                                                </tr>

                                                <tr>
                                                    <th>Phone Number</th>
                                                    <td>${sessionScope.LOGIN_ADMIN.userPhone}</td>
                                                </tr>

                                                <tr>
                                                    <th>Address</th>
                                                    <td>${sessionScope.LOGIN_ADMIN.userAddress}</td>
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

            </main>
            <!-- MAIN -->
            
            <jsp:include page="../../layouts/admin/FooterPage.jsp"></jsp:include>
        </section>
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- LINK SCRIPT -->
        <script src=".././js/admin/AdminRoot.js"></script>
        <script src=".././js/admin/NavBar.js"></script>
        <script src=".././js/admin/SideBar.js"></script>
        <script>
            activeSidebarLink();
        </script>
    </body>
</html>
