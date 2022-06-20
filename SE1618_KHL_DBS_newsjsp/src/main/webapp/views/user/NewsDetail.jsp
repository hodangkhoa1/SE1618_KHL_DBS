<%@page import="java.util.ArrayList"%>
<%@page import="com.khl.gentledentalcare.models.NewsDetail"%>
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
                            <h4>NEWS</h4>
                            <h1>EVENT-PROMOTION</h1>
                        </div>
                    </div>
                <% NewsDetail newsDetail = (NewsDetail) request.getAttribute("newsDetail");
                    String[] detailcontentlist = newsDetail.getNewsContent().split("$",2);
                    String[] img = newsDetail.getImgPath().split("-");
                %>
                <div class="about__keep">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-8  aligncenter">
                                <h1><%= newsDetail.getNewsTitle()%></h1>
                                <small><%= newsDetail.getPostDate()%></small>
                                <%
                                  for (int i = 0; i < detailcontentlist.length; i++) {
                                %>
                                 <img src="<%= img[i]%>" class="d-block w-50 aligncenter">
                                <p><%= detailcontentlist[i]%></p>
                                
                               
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
                <hr style="color: rgba(255, 117, 249, .33);">
                <div class="about__why">
                    <div class="container">
                        <div class="row">
                            <div class="col-12 col-xl-5 img-carousel">
                                <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                                    <div class="carousel-indicators">
                                        <button type="button" data-bs-target="#carouselExampleIndicators"
                                                data-bs-slide-to="0" class="active" aria-current="true"
                                                aria-label="Slide 1"></button>
                                        <button type="button" data-bs-target="#carouselExampleIndicators"
                                                data-bs-slide-to="1" aria-label="Slide 2"></button>
                                    </div>
                                    <div class="carousel-inner">
                                        <div class="carousel-item about__why--img active">
                                            <img src="./images/fea1.webp" class="d-block w-100" alt="...">
                                        </div>
                                        <div class="carousel-item about__why--img">
                                            <img src="./images/fea2.webp" class="d-block w-100" alt="...">
                                        </div>
                                    </div>
                                    <button class="carousel-control-prev" type="button"
                                            data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button"
                                            data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </button>
                                </div>
                            </div>

                            <div class="col-12 col-xl-7 mt-5 mt-xl-0">
                                <div class="divider-content about__keep--content">
                                    <div class="section-title mb--0 position-relative">
                                        <h5 class="sub-title mt-2">WHY WE BEST</h5>
                                        <h2 class="title">People <span>Choose Us</span> because...</h2>
                                        <div class="desc">
                                            <p class="mb-5">Dental care is the maintenance of healthy teeth and the practice
                                                of keeping
                                                the teeth clean </p>
                                        </div>
                                        <div class="section-title mb--0-shape"></div>
                                    </div>
                                    <div class="feature-box-wrap">
                                        <!--== Start Feature Item ==-->
                                        <div class="feature-box">
                                            <div class="inner-content">
                                                <div class="icon">
                                                    <img class="icon-img" src="./images/f1.webp" alt="Image-HasTech">
                                                </div>
                                                <div class="content">
                                                    <h4 class="title-icon">Expert Dentist</h4>
                                                    <p>Dental care is the maintenance of healthy teeth and smile</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--== End Feature Item ==-->

                                        <!--== Start Feature Item ==-->
                                        <div class="feature-box">
                                            <div class="inner-content">
                                                <div class="icon">
                                                    <img class="icon-img" src="./images/f2.webp" alt="Image-HasTech">
                                                </div>
                                                <div class="content">
                                                    <h4 class="title-icon">24/7 Advance Care</h4>
                                                    <p>Dental care is the maintenance of healthy teeth and smile</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--== End Feature Item ==-->

                                        <!--== Start Feature Item ==-->
                                        <div class="feature-box">
                                            <div class="inner-content">
                                                <div class="icon">
                                                    <img class="icon-img" src="./images/f3.webp" alt="Image-HasTech">
                                                </div>
                                                <div class="content">
                                                    <h4 class="title-icon">Available Products</h4>
                                                    <p>Dental care is the maintenance of healthy teeth and smile</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--== End Feature Item ==-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



                <div class="about__logo my-5">
                    <div class="brand-logo-area brand-logo-about-area">
                        <div class="container">
                            <div class="row">
                                <div class="col-12 brand-logo-content">

                                    <div class="brand-logo-item">
                                        <img src="./images/1.webp" width="97" height="103" alt="Image-HasTech">
                                    </div>

                                    <div class="brand-logo-item">
                                        <img src="./images/2.webp" width="82" height="102" alt="Image-HasTech">
                                    </div>

                                    <div class="brand-logo-item">
                                        <img src="./images/3.webp" width="80" height="103" alt="Image-HasTech">
                                    </div>

                                    <div class="brand-logo-item">
                                        <img src="./images/4.webp" width="70" height="103" alt="Image-HasTech">
                                    </div>

                                    <div class="brand-logo-item">
                                        <img src="./images/5.webp" width="77" height="103" alt="Image-HasTech">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
        <!-- LINK JS -->
        <script src="./js/user/UserRoot.js"></script>
        <script src="./js/ScrollBackToTop.js"></script>
        <script src="./js/user/BoxChat.js"></script>
        <script src="./js/user/NavBar.js"></script>
    </body>
</html>
