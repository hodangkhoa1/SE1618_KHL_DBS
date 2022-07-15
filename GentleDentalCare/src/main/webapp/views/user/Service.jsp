<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    int totalNews = 0;
    if (request.getAttribute("TOTAL_SERVICE_LIST") != null) {
        totalNews = (int) request.getAttribute("TOTAL_SERVICE_LIST");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service</title>
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
        <!-- ANIMATE  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
        <!-- Malihu Custom Scrollbar -->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.min.css'>
        <!-- LINK STYLE -->
        <link rel="stylesheet" href="./css/user/UserRoot.css">
        <link rel="stylesheet" href="./css/Loader.css">
        <link rel="stylesheet" href="./css/ScrollBackToTop.css">
        <link rel="stylesheet" href="./css/user/BoxChat.css">
        <link rel="stylesheet" href="./css/user/NavBar.css">
        <link rel="stylesheet" href="./css/user/Service.css">
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
        
        <!-- Hero Start -->
        <div class="container-fluid bg-primary py-5 hero-header mb-5"
            style="background: linear-gradient(rgba(9, 30, 62, .85), rgba(9, 30, 62, .85)), url(./images/carousel-1.jpg) center center no-repeat;">
            <div class="row py-3">
                <div class="col-12 text-center">
                    <h1 class="display-3 text-white animate__animated animate__backInDown">Services</h1>
                </div>
            </div>
        </div>
        <!-- Hero End -->
        
        <!-- Service Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="row g-5">
                    <div class="col-lg-7">
                        <div class="section-title mb-5 animate__animated animate__fadeInUp">
                            <h5 class="position-relative d-inline-block text-primary text-uppercase">Our Services</h5>
                            <h1 class="display-5 mb-0">Cosmetic Dentistry</h1>
                            <p>Cosmetic Dentistry is advanced dentistry aimed at creating positive changes to your teeth and
                                your smile.
                                Sparkle your smile with cosmetic procedures, including teeth whitening, etc…</p>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row g-5" id="return-list">
                            <c:forEach items="${SERVICE_LIST}" var="services">
                                <div class="col-md-4 service-item service-amount" data-aos="fade-up" data-aos-duration="1000">
                                    <a href="${pageContext.request.contextPath}/service-detail?sid=${services.serviceID}">
                                        <div class="rounded-top overflow-hidden service-image">
                                            <img class="img-fluid" src="data:image/png;base64,${services.imageService}" alt="${services.serviceName}">
                                        </div>
                                        <div class="position-relative bg-light rounded-bottom text-center p-4">
                                            <h5 class="m-0">${services.serviceName}</h5>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <c:if test="${NOT_EMPTY == null}">
                            <div class="row g-5">
                                <div class="col-12 pt-4">
                                    <div class="view-more">
                                        <button onclick="loadMore('<%=totalNews%>', '${pageContext.request.contextPath}/news')" class="button-view">Load more</button>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- Service End -->
        
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
        <script src="./js/user/Service.js"></script>
        <script>
            setActiveMenuBar();
        </script>
    </body>
</html>
