<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile | ${sessionScope.LOGIN_EMPLOYEE.fullName}</title>
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
        <link rel="stylesheet" href=".././css/employee/InfoProfile.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        
        <header class="header-background">
            <jsp:include page="../../layouts/employee/NavBar.jsp"></jsp:include>
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
                                        <img src="data:image/png;base64,${sessionScope.LOGIN_EMPLOYEE.imageAvatar}" alt="">
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
                                <div class="table-users">
                                    <table cellspacing="0">
                                        <tr>
                                            <th>Full Name</th>
                                            <td>${sessionScope.LOGIN_EMPLOYEE.fullName}</td>
                                        </tr>

                                        <tr>
                                            <th>Gender</th>
                                            <td>${sessionScope.LOGIN_EMPLOYEE.gender}</td>
                                        </tr>

                                        <tr>
                                            <th>Email</th>
                                            <td>${sessionScope.LOGIN_EMPLOYEE.userEmail}</td>
                                        </tr>

                                        <tr>
                                            <th>Date Of Birth</th>
                                            <td><fmt:formatDate value="${sessionScope.LOGIN_EMPLOYEE.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                                        </tr>

                                        <tr>
                                            <th>Phone Number</th>
                                            <td>${sessionScope.LOGIN_EMPLOYEE.userPhone}</td>
                                        </tr>

                                        <tr>
                                            <th>Address</th>
                                            <td>${sessionScope.LOGIN_EMPLOYEE.userAddress}</td>
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
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- J QUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- LINK JAVA SCRIPT -->
        <script src=".././js/employee/EmployeeRoot.js"></script>
        <script src=".././js/employee/NavBar.js"></script>
        <script src=".././js/ScrollBackToTop.js"></script>
    </body>
</html>
