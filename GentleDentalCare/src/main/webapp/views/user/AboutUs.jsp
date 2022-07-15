<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>About Us | Gentle Dental Care</title>
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
        <link rel="stylesheet" href="./css/user/AboutUs.css">
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
        
        <section class="aboutUs">
            <div class="about__Us">
                <div class="aboutUs__bg">
                    <div class="aboutUs__bg--img">
                        <img src="./images/bg3.webp" alt="">
                    </div>
                    <div class="aboutUs__bg--overlay"></div>
                    <div class="aboutUs__bg--title">
                        <h4>ABOUT US</h4>
                        <h1>About Gentle Dental Care</h1>
                    </div>
                </div>

                <div class="about__keep">
                    <div class="container">
                        <div class="row">
                            <div class="col-12 col-xl-6 mb-5 mb-xl-0">
                                <div class="about__keep--content">
                                    <h5 class="sub-title">SCIENCE 2005</h5>
                                    <h2 class="title">Keep your <span>Teeth Clean</span> &amp; Shine</h2>
                                    <div class="desc">
                                        <p>Dental care is the maintenance of healthy teeth and the practice of keeping the
                                            mouth and teeth clean pur sue pleasure rationally encounter consequences that
                                            are extremely painful. Nor again is there anyone</p>
                                    </div>
                                    <h4 class="note-info">You need to brush your teeth everyday for healty teeth and simle
                                    </h4>
                                    <div class="btn--link">
                                        <a class="about-btn-link" href="${pageContext.request.contextPath}/booking">Book now</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-xl-6">
                                <div class="about__keep--img">
                                    <img src="./images/about2.webp" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="about__teeth">
                    <div class="container">
                        <div class="row">
                            <div class="col-6 col-lg-4 mb-4">
                                <div class="service-list-item">
                                    <div class="icon-box">
                                        <img src="./images/fun1.webp" width="62" height="79" alt="Image-HasTech">
                                    </div>
                                    <div class="content">
                                        <h2 class="price-number"><span>$</span><span class="counter">65</span></h2>
                                        <h6 class="title">Dental Implant</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-4 mb-4">
                                <div class="service-list-item">
                                    <div class="icon-box">
                                        <img src="./images/fun2.webp" width="74" height="79" alt="Image-HasTech">
                                    </div>
                                    <div class="content">
                                        <h2 class="price-number"><span>$</span><span class="counter">135</span></h2>
                                        <h6 class="title">Teeths Whitening</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-4 mb-4">
                                <div class="service-list-item">
                                    <div class="icon-box">
                                        <img src="./images/fun3.webp" width="89" height="78" alt="Image-HasTech">
                                    </div>
                                    <div class="content">
                                        <h2 class="price-number"><span>$</span><span class="counter">230</span></h2>
                                        <h6 class="title">Dental Crown</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

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

                <div class="about__meet">
                    <div class="container">
                        <div class="text-center about__keep--content">
                            <h5 class="sub-title">DENTIST</h5>
                            <h2 class="title">Meet our <span>Dentist qulified</span> staff</h2>
                        </div>

                        <div class="row">
                            <div class="col-12 col-sm-6 mb-4 col-xl-3 card-doctor">
                                <!--== Start Team Item ==-->
                                <div class="team-item">
                                    <div class="inner-content">
                                        <div class="thumb">
                                            <a href="team-details.html"><img src="./images/1 (1).webp" width="270"
                                                    height="233" alt="Image-HasTech"></a>
                                        </div>
                                        <div class="content">
                                            <div class="staff-info">
                                                <h4 class="title"><a href="team-details.html">Dr. David Costa</a></h4>
                                                <h5 class="sub-title">General Dentist</h5>
                                            </div>
                                            <div class="content-footer">
                                                <div class="meta-rating">
                                                    <i class="fa fa-heart"></i>
                                                    <span>5.0</span>
                                                </div>
                                                <div class="meta-time">
                                                    <i class="fa-solid fa-clock"></i>
                                                    <span>09 am - 12 pm</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--== End Team Item ==-->
                            </div>
                            <div class="col-12 col-sm-6 mb-4 col-xl-3 card-doctor">
                                <!--== Start Team Item ==-->
                                <div class="team-item">
                                    <div class="inner-content">
                                        <div class="thumb">
                                            <a href="team-details.html"><img src="./images/2 (2).webp" width="270"
                                                    height="233" alt="Image-HasTech"></a>
                                        </div>
                                        <div class="content">
                                            <div class="staff-info">
                                                <h4 class="title"><a href="team-details.html">Dr. Julia Smith</a></h4>
                                                <h5 class="sub-title">Orthodontist</h5>
                                            </div>
                                            <div class="content-footer">
                                                <div class="meta-rating">
                                                    <i class="fa fa-heart"></i>
                                                    <span>4.9</span>
                                                </div>
                                                <div class="meta-time">
                                                    <i class="fa-solid fa-clock"></i>
                                                    <span>10 am - 04 pm</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--== End Team Item ==-->
                            </div>
                            <div class="col-12 col-sm-6 mb-4 col-xl-3 card-doctor">
                                <!--== Start Team Item ==-->
                                <div class="team-item">
                                    <div class="inner-content">
                                        <div class="thumb">
                                            <a href="team-details.html"><img src="./images/3 (1).webp" width="270"
                                                    height="233" alt="Image-HasTech"></a>
                                        </div>
                                        <div class="content">
                                            <div class="staff-info">
                                                <h4 class="title"><a href="team-details.html">Dr. Thomas Albart</a></h4>
                                                <h5 class="sub-title">Dental Sergeon</h5>
                                            </div>
                                            <div class="content-footer">
                                                <div class="meta-rating">
                                                    <i class="fa fa-heart"></i>
                                                    <span>4.8</span>
                                                </div>
                                                <div class="meta-time">
                                                    <i class="fa-solid fa-clock"></i>
                                                    <span>09 am - 12 pm</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--== End Team Item ==-->
                            </div>
                            <div class="col-12 col-sm-6 mb-4 col-xl-3 card-doctor">
                                <!--== Start Team Item ==-->
                                <div class="team-item">
                                    <div class="inner-content">
                                        <div class="thumb">
                                            <a href="team-details.html"><img src="./images/4 (1).webp" width="270"
                                                    height="233" alt="Image-HasTech"></a>
                                        </div>
                                        <div class="content">
                                            <div class="staff-info">
                                                <h4 class="title"><a href="team-details.html">Dr. Rebeca Smith</a></h4>
                                                <h5 class="sub-title">Cosmetic Dentist</h5>
                                            </div>
                                            <div class="content-footer">
                                                <div class="meta-rating">
                                                    <i class="fa fa-heart"></i>
                                                    <span>5.0</span>
                                                </div>
                                                <div class="meta-time">
                                                    <i class="fa-solid fa-clock"></i>
                                                    <span>12 pm - 05 pm</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--== End Team Item ==-->
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
        <script>
            setActiveMenuBar();
        </script>
    </body>
</html>
