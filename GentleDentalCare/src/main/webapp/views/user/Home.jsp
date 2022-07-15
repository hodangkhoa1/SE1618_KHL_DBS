<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
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
        <link rel="stylesheet" href="./css/user/Home.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        <jsp:include page="../../layouts/user/BoxChat.jsp"></jsp:include>
        
        <header class="header-top">
            <jsp:include page="../../layouts/user/NavBar.jsp"></jsp:include>
        </header>
        
        <!--************************* HEADER BODY *************************-->
        <div class="body-content" style="background-image: url(./images/backgroud.png);">
            <div class="body-cover">
                <div class="text-content">
                    <div class="text">Hello, We are </div>
                    <div class="name">Gentle Dental Care</div>
                    <div class="job">
                        <div class="job">
                            <span>Caring for </span>
                            <div class="typing-text">
                                <span class="one">YOU &</span>
                                <span class="two">YOUR SMILE.</span>
                            </div>
                        </div>
                    </div>
                    <div class="buttons">
                        <button onclick="window.location.href='${pageContext.request.contextPath}/about'">About Me</button>
                        <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Follow Me</button>
                    </div>
                </div>
                <div class="girl">
                    <img src="./images/bgdoctor.png" alt="doctor">
                </div>
            </div>
        </div>
        <!--*********************** END HEADER BODY ***********************-->
        
        <!--*********************** PROFESSIONALS *************************-->
        <section class="professionals my-5">
            <div class="dental__pro">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12 col-xxl-6">
                            <div class="profess__img">
                                <img src="./images/img-dental-professionals.jpg" alt="">
                            </div>
                        </div>

                        <div class="col-12 col-xxl-6 pro__item">
                            <div class="profess__content">
                                <div class="profess__headline">
                                    <h4>The Dental</h4>
                                    <h1>Professionals</h1>
                                </div>
                                <div class="profess__text py-4">
                                    <p>
                                        DentiCare Studio is a well-established dentist in the heart of Budapest providing
                                        excellent dentistry for the whole family. Our priority is making you feel
                                        comfortable and at ease in a safe and welcoming environment.
                                    </p>
                                </div>
                            </div>

                            <div class="pro__product">
                                <div class="product__title">
                                    <h5>Proud Members</h5>
                                </div>

                                <div class="product__members">
                                    <div class="item--member">
                                        <img src="./images/logo-ada.png" alt="">
                                    </div>

                                    <div class="item--member">
                                        <img src="./images/logo-adea.png" alt="">
                                    </div>

                                    <div class="item--member">
                                        <img src="./images/logo-mouth-healthy.png" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--*********************** END PROFESSIONALS *********************-->
        
        <!--*********************** DOCTOR TEAM ***************************-->
        <section class="doctorTeam">
            <div class="container doctor__list">
                <div class="row doctor__card">
                    <c:forEach items="${DENTIST_LIST}" var="dentist">
                        <div class="col-12 col-lg-4 d-flex justify-content-center">
                            <a href="${pageContext.request.contextPath}/dentist-detail?dd=${dentist.dentistID}" class="card my-3">
                                <img src="data:image/png;base64,${dentist.imageDentist}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h6>${dentist.academicRank}</h6>
                                    <h2>${dentist.nameDentist}</h2>
                                    <p class="card-text">${dentist.subtitleDentist}</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!--*********************** END DOCTOR TEAM ***********************-->
        
        <!--*********************** LOVE SMILE ****************************-->
        <section class="loveSmile py-5" style="background-image: url(./images/about_us_background.jpg);">
            <div class="smile--overlay">
                <div class="container love--smile">
                    <div class="row">
                        <div class="col-12 col-lg-6 profess__headline smile-header">
                            <h4>We Would Love To</h4>
                            <h1>See You Smile</h1>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12 col-xl-4">
                            <p>
                                Distinctively re-engineer revolutionary meta-services and premium architectures.
                                Intrinsically incubate intuitive opportunities and real-time potentialities. Globally
                                revolutionize global sources through interoperable services.
                            </p>
                            <p>
                                Appropriately communicate one-to-one technology after plug-and-play networks and worldwide
                                potentialities.
                            </p>
                        </div>

                        <div class="col-12 col-xl-4">
                            <p>
                                Podcasting operational change management inside of workflows to establish a framework. Taking
                                seamless key performance indicators offline.
                            </p>
                            <p>
                                Keeping your eye on the ball while performing a deep dive on the start-up mentality to derive
                                convergence on cross-platform integration. Dramatically mesh low-risk high-yield alignments
                                before transparent e-tailers.
                            </p>
                        </div>

                        <div class="col-12 col-xl-4">
                            <div class="smile--cover">
                                <div class="progress__content">
                                    <p>Client Satisfaction</p>
                                </div>
                                <div class="progress mb-3">
                                    <div class="progress-bar bg-color" role="progressbar" style="width: 92%;"
                                        aria-valuenow="92" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>

                            <div class="smile--cover">
                                <div class="progress__content">
                                    <p>Dental Success</p>
                                </div>
                                <div class="progress mb-3">
                                    <div class="progress-bar bg-color" role="progressbar" style="width: 95%"
                                        aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>

                            <div class="smile--cover">
                                <div class="progress__content">
                                    <p>Client Referral</p>
                                </div>
                                <div class="progress mb-3">
                                    <div class="progress-bar bg-color" role="progressbar" style="width: 87%"
                                        aria-valuenow="87" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>

                            <div class="smile--cover">
                                <div class="progress__content">
                                    <p>Travel Satisfaction</p>
                                </div>
                                <div class="progress mb-3">
                                    <div class="progress-bar bg-color" role="progressbar" style="width: 90%"
                                        aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--*********************** END LOVE SMILE ************************-->
        
        <!--*********************** EXCELLENCE ****************************-->
        <section class="excellence py-5" style="background-image: url(./images/background-doctor.jpg);">
            <div class="container excellence__commit">
                <div class="row exce__header">
                    <div class="col-12 col-md-7">
                        <div class="profess__headline">
                            <h4>Committed To</h4>
                            <h1>Excellence</h1>
                        </div>
                    </div>

                    <div class="col-12 col-md-5 btn--view">
                        <button onclick="window.location.href='${pageContext.request.contextPath}/service'" type="button" class="">
                            View All Services
                            <i class="fa-solid fa-angle-right"></i>
                        </button>
                    </div>
                </div>

                <div class="row exce__item">
                    <div class="col-12 col-md-6 col-xl-3 card__service py-3">
                        <div class="card">
                            <img src="./images/home-services.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Dental Services</h5>
                                <p class="card-text">
                                    Globally harness multimedia based collaboration and idea haring with backend products.
                                </p>
                            </div>
                        </div>
                        <div class="card__icon">
                            <img src="./images/ser.png" alt="">
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3 card__service py-3">
                        <div class="card">
                            <img src="./images/home-services-2.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Dental Implants</h5>
                                <p class="card-text">
                                    Dramatically disseminate standardized metrics after resource-leveling processes.
                                </p>
                            </div>
                        </div>
                        <div class="card__icon">
                            <img src="./images/implant.png" alt="">
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3 card__service py-3">
                        <div class="card">
                            <img src="./images/home-services-3.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Surgery</h5>
                                <p class="card-text">
                                    Proactively fabricate one-to-one materials via effective e-business services processes.
                                </p>
                            </div>
                        </div>
                        <div class="card__icon">
                            <img src="./images/surgery.png" alt="">
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3 card__service py-3">
                        <div class="card">
                            <img src="./images/home-services-4.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Teeth Whitening</h5>
                                <p class="card-text">
                                    Distinctively re-engineer revolutionary meta-services and premium architectures.
                                </p>
                            </div>
                        </div>
                        <div class="card__icon">
                            <img src="./images/teeth.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--*********************** END EXCELLENCE ************************-->
        
        <!--*********************** NUMBER SERVICE ************************-->
        <section class="number__service py-5" style="background-image: url(./images/background-2.png);">
            <div class="dental__number container">
                <div class="row">
                    <div class="col-12 col-xl-7">
                        <div class="profess__headline">
                            <h4>Our Dental Service</h4>
                            <h1>In Numbers</h1>
                            <p class="my-4">
                                Distinctively exploit optimal alignments for intuitive bandwidth. Quickly
                                coordinate e-business applications through revolutionary catalysts for change. Seamlessly
                                underwhelm optimal testing processes.
                            </p>
                        </div>
                    </div>
                </div>

                <div class="row my-4 py-4 number--processes">
                    <div class="processes-content">
                        <div class="cover">
                            <div class="progress progress-first">
                                <span class="title timer" data-from="0" data-to="99" data-speed="1800">
                                    <img src="./images/like.png" alt="">
                                </span>
                                <div class="overlay"></div>
                                <div class="left"></div>
                                <div class="right"></div>
                            </div>
                            <div class="cover--content">
                                <span class="content__title">99%</span>
                                <p>Client Satisfaction</p>
                            </div>
                        </div>

                        <div class="cover">
                            <div>
                                <div class="progress progress-second">
                                    <span class="title timer" data-from="0" data-to="97" data-speed="1500">
                                        <img src="./images/intervention.png" alt="">
                                    </span>
                                    <div class="overlay"></div>
                                    <div class="left"></div>
                                    <div class="right"></div>
                                </div>
                            </div>

                            <div class="cover--content">
                                <span class="content__title">97%</span>
                                <p>Intervention Success</p>
                            </div>
                        </div>

                        <div class="cover">
                            <div class="progress progress-third">
                                <span class="title timer" data-from="0" data-to="100" data-speed="1500">
                                    <img src="./images/happy.png" alt="">
                                </span>
                                <div class="overlay"></div>
                                <div class="left"></div>
                                <div class="right"></div>
                            </div>
                            <div class="cover--content">
                                <span class="content__title">100%</span>
                                <p>Happy with Staff</p>
                            </div>
                        </div>

                        <div class="cover">
                            <div class="progress progress-fourth">
                                <span class="title timer" data-from="0" data-to="95" data-speed="1800">
                                    <img src="./images/recovery.png" alt="">
                                </span>
                                <div class="overlay"></div>
                                <div class="left"></div>
                                <div class="right"></div>
                            </div>
                            <div class="cover--content">
                                <span class="content__title">95%</span>
                                <p>Quick Recovery</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-xl-4 dental__box">
                        <i class="fa-solid fa-quote-left"></i>
                        <h6 class="number__content">
                            DentiCare was founded in the first place to create absolutely beautiful smiles.
                        </h6>
                        <i class="fa-solid fa-quote-right"></i>
                    </div>

                    <div class="col-12 col-xl-4 content--first">
                        <p class="mt-3">
                            Distinctively re-engineer revolutionary meta-services and premium architectures.
                            Intrinsically incubate intuitive opportunities and real-time potentialities.
                        </p>
                        <p>
                            Appropriately communicate one-to-one technology after plug-and-play networks. Quickly aggregate
                            B2B users and worldwide potentialities.
                        </p>
                    </div>

                    <div class="col-12 col-xl-4">
                        <p>
                            Enthusiastically mesh long-term high-impact infrastructures vis-a-vis efficient customer service.
                            Professionally fashion wireless leadership.
                        </p>
                        <p>
                            Energistically myocardinate clicks-and-mortar testing procedures whereas next-generation
                            manufactured products, appropriately implement visionary readiness.
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <!--*********************** END NUMBER SERVICE ********************-->
        
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
