<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    int totalCourses = 0;
    if (request.getAttribute("TOTAL_BOOKING_LIST") != null) {
        totalCourses = (int) request.getAttribute("TOTAL_BOOKING_LIST");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>History Booking | ${sessionScope.LOGIN_USER.fullName}</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONT AWESOME -->
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
        <link rel="stylesheet" href="./css/user/HistoryBooking.css">
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
        
        <section class="history my-5">
            <div class="container">
                <div class="history--title">
                    <h2>Booking | ${sessionScope.LOGIN_USER.fullName}</h2>
                </div>
                <div class="row" id="return-list">
                    <c:if test="${HISTORY_BOOKING_LIST == null}">
                        <lottie-player src="https://assets6.lottiefiles.com/packages/lf20_GlZGOi.json" background="transparent" speed="1" loop autoplay style="width: 30%; position: relative; left: 50%; transform: translateX(-50%);"></lottie-player>
                    </c:if>
                    <c:if test="${HISTORY_BOOKING_LIST != null}">
                        <c:forEach items="${HISTORY_BOOKING_LIST}" var="booking">
                            <div class="col-12 history__card mb-4">
                                <div class="card--top">
                                    <div class="top--content">
                                        <p>Booking ID: <span class="maId">${booking.bookingID}</span></p>
                                        <p>|</p>
                                        <p>Book Date: <span><fmt:formatDate value="${booking.bookingDate}" pattern="dd-MM-yyyy"/></span></p>
                                    </div>
                                    <c:if test="${booking.bookingStatus == 0}">
                                        <button class="btn-delete" onclick="confirmDelete('${pageContext.request.contextPath}/history-booking', '${booking.bookingID}', '${booking.userId}')">
                                            <i class="fa-solid fa-xmark"></i>
                                        </button>
                                    </c:if>
                                </div>

                                <div class="card--bottom">
                                    <div class="cb__img">
                                        <c:forEach items="${booking.serviceList}" var="bookingService">
                                            <img src="data:image/png;base64,${bookingService.imageService}" alt="">
                                        </c:forEach>
                                    </div>

                                    <div class="cb__content">
                                        <p>Full Name: <span class="name">${booking.fullName}</span></p>
                                        <p>Phone Number: <span>${booking.phoneNumber}</span></p>
                                        <p>Address: <span>${booking.address}</span></p>
                                        <p>Service: 
                                            <span>
                                                <c:forEach items="${booking.serviceList}" var="bookingService">
                                                    </br>
                                                    ${bookingService.serviceName},${bookingService.slotStart},
                                                </c:forEach>
                                            </span>
                                        </p>
                                    </div>
                                    <div class="status--cover">
                                        <c:choose>
                                            <c:when test = "${booking.bookingStatus == 0}">
                                                <div class="status" style="background-color: green;">
                                                    <p>Pending</p>
                                                </div>
                                            </c:when>

                                            <c:when test = "${booking.bookingStatus == 1}">
                                                <div class="status">
                                                    <p>Confirm</p>
                                                </div>
                                            </c:when>
                                            <c:when test = "${booking.bookingStatus == 2}">
                                                <div class="status cancelled" style="background-color: red;">
                                                    <p>Cancelled</p>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    
                    <c:if test="<%=totalCourses > 0%>">
                        <div class="btn--loadMore col-12 mt-3" style="display: flex; align-items: center; justify-content: center;">
                            <button type="button" class="btn btn-primary" width="100px" onclick="LoadMoreButton('<%=totalCourses%>', '${pageContext.request.contextPath}/history-booking')">Load More</button>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
        
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
        <!-- Lottie Files -->
        <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
        <!-- Sweet Alert -->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- LINK JS -->
        <script src="./js/user/UserRoot.js"></script>
        <script src="./js/ScrollBackToTop.js"></script>
        <script src="./js/user/BoxChat.js"></script>
        <script src="./js/user/NavBar.js"></script>
        <script src="./js/user/HistoryBooking.js"></script>
        <script>
            setActiveMenuBar();
        </script>
    </body>
</html>
