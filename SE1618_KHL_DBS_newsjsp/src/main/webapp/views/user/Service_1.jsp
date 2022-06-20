<%@page import="com.khl.gentledentalcare.dbo.NewsDao"%>
<%@page import="com.khl.gentledentalcare.models.NewsDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.khl.gentledentalcare.models.News"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>News | Gentle Dental Care</title>
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
        <link rel="stylesheet" href="./css/user/Loader.css">
        <link rel="stylesheet" href="./css/ScrollBackToTop.css">
        <link rel="stylesheet" href="./css/user/BoxChat.css">
        <link rel="stylesheet" href="./css/user/NavBar.css">
        <link rel="stylesheet" href="./css/user/AboutUs.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">   
        <link rel="stylesheet" href="./css/user/Service.css">
        <link rel="stylesheet" href="./css/user/Service_bootstrap.css">

    </head>
    <body>
        <jsp:include page="../../layouts/user/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        <jsp:include page="../../layouts/user/BoxChat.jsp"></jsp:include>

            <header class="header-top header-background">
            <jsp:include page="../../layouts/user/NavBar.jsp"></jsp:include>
            </header>

            <section class="aboutUs">
                <div class="about__Us">
                    <div class="aboutUs__bg">
                        <div class="aboutUs__bg--img">
                            <img src="./images/bg3.webp" alt="">
                        </div>
                        <div class="aboutUs__bg--overlay"></div>
                        <div class="aboutUs__bg--title">
                            <h1>SERVICES</h1>
                            <h5 class="text-center">Awesome promotions and events are constantly updated here.
                                <br>
                                Our team of experienced and qualified dental experts is here to bring out the best experience to clients.</h5>

                        </div>
                    </div>


                    <!-- Service Start -->
                    <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="container">
                            <div class="row g-5 mb-5">
                                <div class="col-lg-5 wow zoomIn" data-wow-delay="0.3s" style="min-height: 400px;">
                                    <div class="twentytwenty-container position-relative h-100 rounded overflow-hidden">
                                        <img class="position-absolute w-100 h-100" src="images/before.jpg" style="object-fit: cover;">
                                        <img class="position-absolute w-100 h-100" src="images/after.jpg" style="object-fit: cover;">
                                    </div>
                                </div>
                                <div class="col-lg-7">
                                    <div class="section-title mb-5">
                                        <h5 class="position-relative d-inline-block text-primary text-uppercase">Our Services</h5>
                                        <h1 class="display-5 mb-0">Cosmetic Dentistry</h1>
                                        <p>Cosmetic Dentistry is advanced dentistry aimed at creating positive changes to your teeth and your smile. 
                                            Sparkle your smile with cosmetic procedures, including teeth whitening, etc…</p>
                                    </div>
                                    <div class="row g-5">
                                        <div class="col-md-6 service-item wow zoomIn" data-wow-delay="0.6s">
                                            <div class="rounded-top overflow-hidden">
                                                <img class="img-fluid" src="images/service-1.jpg" alt="">
                                            </div>

                                            <div class="position-relative bg-light rounded-bottom text-center p-4">         
                                                <a href="${pageContext.request.contextPath}/ServiceDetail"><h5 class="m-0">Dental Veneer</h5></a>
                                        </div>
                                    </div>
                                    <div class="col-md-6 service-item wow zoomIn" data-wow-delay="0.9s">
                                        <div class="rounded-top overflow-hidden">
                                            <img class="img-fluid" src="images/service-2.jpg" alt="">
                                        </div>
                                        <div class="position-relative bg-light rounded-bottom text-center p-4">
                                            <h5 class="m-0">Dental Crowns/Ceramic</h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row g-5 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="col-lg-7">
                                <div class="row g-5">
                                    <div class="col-md-6 service-item wow zoomIn" data-wow-delay="0.3s">
                                        <div class="rounded-top overflow-hidden">
                                            <img class="img-fluid" src="images/service-3.jpg" alt="">
                                        </div>
                                        <div class="position-relative bg-light rounded-bottom text-center p-4">
                                            <h5 class="m-0">CAD/CAM Dentistry</h5>
                                        </div>
                                    </div>
                                    <div class="col-md-6 service-item wow zoomIn" data-wow-delay="0.6s">
                                        <div class="rounded-top overflow-hidden">
                                            <img class="img-fluid" src="images/service-4.jpg" alt="">
                                        </div>
                                        <div class="position-relative bg-light rounded-bottom text-center p-4">
                                            <h5 class="m-0">Teeth Whitening</h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-5 service-item wow zoomIn" data-wow-delay="0.9s">
                                <a href="#"  data-wpel-link="internal"></a>
                                <div class="position-relative bg-primary rounded h-100 d-flex flex-column align-items-center justify-content-center text-center p-4">                       
                                    <h3 class="text-white mb-3">Make Appointment</h3>
                                    <p class="text-white mb-3">Clita ipsum magna kasd rebum at ipsum amet dolor justo dolor est magna stet eirmod</p>
                                    <h2 class="text-white mb-0">+00000000000</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Service End -->





                <!-- Back to Top -->
                <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded back-to-top"><i class="bi bi-arrow-up"></i></a>

                <jsp:include page="../../layouts/user/FooterPage.jsp"></jsp:include>
                <jsp:include page="../../layouts/user/SupportOnline.jsp"></jsp:include>
                <!-- JavaScript Libraries -->
                <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="lib/wow/wow.min.js"></script>
                <script src="lib/easing/easing.min.js"></script>
                <script src="lib/waypoints/waypoints.min.js"></script>
                <script src="lib/owlcarousel/owl.carousel.min.js"></script>
                <script src="lib/tempusdominus/js/moment.min.js"></script>
                <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
                <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
                <script src="lib/twentytwenty/jquery.event.move.js"></script>
                <script src="lib/twentytwenty/jquery.twentytwenty.js"></script>
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
                <script src="./js/user/Service_main.js"></script>
                </body>
                </html>
