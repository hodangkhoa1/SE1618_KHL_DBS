<%-- 
    Document   : newjsp
    Created on : Jun 1, 2022, 1:27:55 AM
    Author     : LamHung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="./css/Home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
    <!-- TẠO ICON TRÊN THANH WEB -->
    <link rel="icon" href="./images/icon.png" type="image/png" sizes="200x138" />
    <!-- LINK BOOTSTRAP 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- LINK FONTAWESOME -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
    <!-- Box Icons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <!-- UN ICONS -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    </head>
    <body>
    <header>
        <nav>
            <div class="navbar">
                <div class="logo">
                    <img src="images/icon.png" alt="">
                </div>

                <div class="header__menu">
                    <ul class="menu">
                        <li class="nav-item active"><a href="#">Home</a></li>
                        <li class="nav-item"><a href="#">Service</a></li>
                        <li class="nav-item"><a href="#">Booking</a></li>
                        <li class="nav-item"><a href="#">Services</a></li>
                        <li class="nav-item"><a href="#">About us</a></li>
                    </ul>
                </div>

                <div class="search-box">
                    <input type="text" placeholder="Search here...">
                    <a href="#"><i class="fas fa-search"></i></a>
                </div>

                <div class="header__action">
                    <!-- <div class="header__action-login">
                        <a href="#" class="btn-login-link">
                            <span class="name-desc">Login</span>
                            <div class="btn-icon login">
                                <i class="fa-solid fa-right-to-bracket"></i>
                            </div>
                        </a>
                    </div> -->
                    <div class="header__action-user">
                        <div onclick="menuToggle()" class="user-avatar">
                            <img src="./images/user.jpg" />
                        </div>
                        <div class="user-menu">
                            <ul>
                                <li>
                                    <i class="uil uil-language"></i>
                                    <a href="#">English</a>
                                </li>
                                <li>
                                    <i class="uil uil-user-circle"></i>
                                    <a href="#">My Profile</a>
                                </li>
                                <li>
                                    <i class="uil uil-edit"></i>
                                    <a href="#">Edit Profile</a>
                                </li>
                                <li>
                                    <i class="uil uil-padlock"></i>
                                    <a href="#">Change Password</a>
                                </li>
                                <li>
                                    <i class="uil uil-signout"></i>
                                    <a href="#">Logout</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <div class="content" style="background-image: url(./images/backgroud.png); background-repeat: no-repeat;
        background-position: center;
        background-size: cover; height: 100vh;">
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
                    <button>About Me</button>
                    <button>Follow Me</button>
                </div>
            </div>
            <div class="girl">
                <img src="images/bgdoctor.png" alt="doctor">
            </div>
        </div>
        <div class="media-icons support-online">
            <a href="" class="support_booking">
                <i class="fa-solid fa-calendar-days"></i>
                <span>Booking</span>
            </a>

            <a href="" target="_blank" class="support_facebook">
                <i class="fa-brands fa-facebook"></i>
                <span>Facebook</span>
            </a>

            <a href="" class="support_phone">
                <div class="animated infinite zoomIn alo-circle"></div>
                <div class="animated infinite pulse alo-circle-fill"></div>
                <div class="support_phone-icon">
                    <svg fill="#fff" height="20px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                        <path
                            d="M497.39 361.8l-112-48a24 24 0 0 0-28 6.9l-49.6 60.6A370.66 370.66 0 0 1 130.6 204.11l60.6-49.6a23.94 23.94 0 0 0 6.9-28l-48-112A24.16 24.16 0 0 0 122.6.61l-104 24A24 24 0 0 0 0 48c0 256.5 207.9 464 464 464a24 24 0 0 0 23.4-18.6l24-104a24.29 24.29 0 0 0-14.01-27.6z">
                        </path>
                    </svg>
                    <span>Gọi điện</span>
                </div>
            </a>
        </div>
    </header>

    <!--******************************************* PROFESSIONALS *******************************************-->
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
                                <p>DentiCare Studio is a well-established dentist in the heart of Budapest providing
                                    excellent dentistry for the whole family. Our priority is making you feel
                                    comfortable and at ease in a safe and welcoming environment.</p>
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

    <!--******************************************* END PROFESSIONALS *******************************************-->



    <!--******************************************* DOCTOR TEAM *******************************************-->
    <section class="doctorTeam">
        <div class="container doctor__list">
            <div class="row doctor__card">
                <div class="col-12 col-lg-4 d-flex justify-content-center">
                    <div class="card my-3">
                        <img src="./images/people-adam-palmer.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h6>Clinic Director</h6>
                            <h2>Adam <span>Palmer</span></h2>
                            <p class="card-text">The qualities of excellent communication and trust form part of our
                                commitment to you and I have carefully chosen a great team of people.</p>
                            <div class="card-social">
                                <div class="social-link">
                                    <a href="" class="card-social__facebook">
                                        <i class="fa-brands fa-facebook-f"></i>
                                    </a>
                                </div>
                                <div class="social-link">
                                    <a href="" class="card-social__twitter">
                                        <i class="fa-brands fa-twitter"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-lg-4 d-flex justify-content-center">
                    <div class="card my-3">
                        <img src="./images/people-xavier-symmonds.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h6>Senior Dental Surgeon</h6>
                            <h2>Xavier <span>Symmonds</span></h2>
                            <p class="card-text">Committed to delivering dentistry of the highest quality, Xavier
                                achieved numerous awards over Europe and United States.</p>
                            <div class="card-social">
                                <div class="social-link">
                                    <a href="" class="card-social__facebook">
                                        <i class="fa-brands fa-facebook-f"></i>
                                    </a>
                                </div>
                                <div class="social-link">
                                    <a href="" class="card-social__twitter">
                                        <i class="fa-brands fa-twitter"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-lg-4 d-flex justify-content-center">
                    <div class="card my-3">
                        <img src="./images/people-kate-seward.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h6>Senior Orthodontist</h6>
                            <h2>Kate <span>Seward</span></h2>
                            <p class="card-text">Emphatic and enjoying treating a wide range of patients, including
                                children, oral hygiene and education is important to me as to my patients.</p>
                            <div class="card-social">
                                <div class="social-link">
                                    <a href="" class="card-social__facebook">
                                        <i class="fa-brands fa-facebook-f"></i>
                                    </a>
                                </div>
                                <div class="social-link">
                                    <a href="" class="card-social__twitter">
                                        <i class="fa-brands fa-twitter"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </section>

    <!--******************************************* END DOCTOR TEAM *******************************************-->



    <!--******************************************* EXCELLENCE *******************************************-->
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
                    <button type="button" class="">
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
                            <p class="card-text">Globally harness multimedia based collaboration and idea haring with backend products.</p>
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
                            <p class="card-text">Dramatically disseminate standardized metrics after resource-leveling processes.</p>
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
                            <p class="card-text">Proactively fabricate one-to-one materials via effective e-business services processes.</p>
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
                            <p class="card-text">Distinctively re-engineer revolutionary meta-services and premium architectures.</p>
                        </div>
                    </div>
                    <div class="card__icon">
                        <img src="./images/teeth.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!--******************************************* END EXCELLENCE *******************************************-->

    <!-- LINK BOOTSTRAP 5 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <!-- JQUERY -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- LINK JS -->
    <script src="./js/home.js"></script>
</body>
</html>
