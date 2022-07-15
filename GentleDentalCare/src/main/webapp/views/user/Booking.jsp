<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Booking | ${sessionScope.LOGIN_USER.fullName}</title>
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
        <link rel="stylesheet" href="./css/user/Booking.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
    </head>
    <body onload="CheckValueUser('${sessionScope.LOGIN_USER.userID}', '${pageContext.request.contextPath}/${sessionScope.LOGIN_USER != null ? "edit-profile" : "login"}', '${pageContext.request.contextPath}/home')">
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        <jsp:include page="../../layouts/user/BoxChat.jsp"></jsp:include>
        
        <div class="modal fade" role="dialog" id="modalForm">
            <div class="modal-dialog modal-lg" role="content">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Service Details</h4>
                        <button type="button" onclick="removeValue()" class="close btn btn-danger" data-bs-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <span class="form-label">Service</span>
                                <select class="form-control" id="selService">
                                    <option value='' selected disabled>Please choose service</option>
                                    <c:forEach items="${SERVICES_LIST}" var="service">
                                        <option value="${service.serviceID}">${service.serviceName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <span class="form-label">Date</span>
                                <input class="form-control" onchange="getServiceSlot()" id="dateService" type="date">
                            </div>
                            <div class="mb-3">
                                <span class="form-label d-block">Slot</span>
                                <div class="foo" id="return-list"></div>
                            </div>
                            <div id="slotCatchQ" class="bg-danger text-white mb-2 pl-2"></div>
                            <div class="modal-footer">
                                <button disabled id="buttonSubmit" type="button" class="btn btn-primary" onclick="addLi()">Book Now</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="error-box"></div>
        
        <header class="header-background">
            <jsp:include page="../../layouts/user/NavBar.jsp"></jsp:include>
        </header>
                                
        <div class='container pt-2 pb-5'>
            <div class='window'>
                <div class='order-info'>
                    <div class='order-info-content'>
                        <div class="booking-form">
                            <div class="form-header">
                                <h3>Book a appointment</h3>
                            </div>
                            <form action="${pageContext.request.contextPath}/booking" method="post">
                                <div class="form-group">
                                    <span class="form-label">Full Name</span>
                                    <input type="text" class="form-control" id="fullName" name="fullName" readonly value="${sessionScope.LOGIN_USER.fullName}">
                                </div>
                                <div class="form-group">
                                    <span class="form-label">Email</span>
                                    <input type="email" class="form-control" id="email" name="email" readonly value="${sessionScope.LOGIN_USER.userEmail}">
                                </div>
                                <div class="form-group">
                                    <span class="form-label">Phone Number</span>
                                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" readonly value="${sessionScope.LOGIN_USER.userPhone}">
                                </div>
                                <div class="form-group">
                                    <span class="form-label">Address</span>
                                    <input type="text" class="form-control" id="address" name="address" readonly value="${sessionScope.LOGIN_USER.userAddress}">
                                </div>
                                <div class="form-group">
                                    <span class="form-label">Hospital</span>
                                    <select class="form-control Hospital" name="hospital" id="hospital" onchange="change_button()" onblur="change_button()">
                                        <option value="" selected disabled>Please choose your Hospital</option>
                                        <c:forEach items="${HOSPITAL_LIST}" var="hospital">
                                            <option value="${hospital.hospitalID}">${hospital.hospitalName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <span class="form-label">Add your service</span>
                                    <div class="input-group-append">
                                        <button class="btn btn-success" id="buttonService" type="button" data-bs-toggle="modal" data-bs-target="#modalForm" disabled>
                                            Add more service
                                        </button>
                                    </div>
                                    <hr style="height: 2px; border: 10px; color: gray; background-color: red">
                                    <ul class="ul-list" id="list"></ul>
                                    <div id="bookCatch" class="bg-danger text-white mb-2 pl-2"></div>
                                    <input type="text" hidden value="" name="CountBookService" id="bookService">
                                </div>
                                <div class="form-btn">
                                    <button class="submit-btn">Book Now</button>
                                </div>
                            </form>
                        </div>
                        <div class='line-total'></div>
                    </div>
                </div>
            </div>
        </div>
        
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
        <script src="./js/user/Booking.js"></script>
        <script>
            setActiveMenuBar();
        </script>
    </body>
</html>
