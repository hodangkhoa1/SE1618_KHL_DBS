<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FAQ</title>
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
        <link rel="stylesheet" href="./css/user/FAQ.css">
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
        
        <section class="FAQ">
            <div class="FAQ__Us">
                <div class="FAQ__bg">
                    <div class="FAQ__bg--img">
                        <img src="./images/bg3.webp" alt="">
                    </div>
                    <div class="FAQ__bg--overlay"></div>
                    <div class="FAQ__bg--title">
                        <h1>Frequently asked questions</h1>
                    </div>
                </div>
                <div class="FAQ-question pt-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-12 col-xl-6 my-3 my-lg-0">
                                <div class="FAQ-question--img">
                                    <img src="./images/faq.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-12 col-xl-6 mb-5 mb-xl-0 ">
                                <div class="row" id="accordion">
                                    <div class="card">
                                        <div class="card-header" role="tab">
                                            <h3 class="mb-0"><a type="button" data-bs-toggle="collapse"
                                                    href="#collapseOne">How to to keep my teeth healthy?</a></h3>
                                        </div>
                                        <div class="collapse show" id="collapseOne" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                <p>
                                                    Whether it's nice when it appears or no.
                                                    Hardly a nice ornamentation of a great number of handles, to choose by
                                                    dividing the sheets by the sheets which it has for it. But he was a
                                                    visitor to the Greek forest, since I have named him very much his honey.
                                                    Open your senses to that unknown sea.
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header" role="tab">
                                            <h3 class="mb-0"><a type="button" class="collapsed" data-bs-toggle="collapse"
                                                    href="#collapseTwo">Do you accept my insurance plan?</a></h3>
                                        </div>
                                        <div class="collapse" id="collapseTwo" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                <p>
                                                    Our office accepts nearly all of the major
                                                    insurance plans. Also, we’re well versed in the latest coverage trends,
                                                    and are more than happy to discuss the particulars of your plan with you
                                                    prior to any treatments you may undergo.
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header" role="tab">
                                            <h3 class="mb-0"><a type="button" class="collapsed" data-bs-toggle="collapse"
                                                    href="#collapseThree">What precautions do you take to ensure patient
                                                    safety?</a></h3>
                                        </div>
                                        <div class="collapse" id="collapseThree" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                <p>
                                                    Our entire practice team is well trained in
                                                    state-of-the-art sanitation techniques designed to ensure patient
                                                    safety. This goes beyond wearing gloves and face-masks to prevent
                                                    passing germs and includes a specialized sanitation center and a water
                                                    filtration system guaranteeing a freshwater source for each patient. Our
                                                    clinic utilizes all universal precautions as required by the Federal
                                                    Government of Canada. We also regularly train our staff to ensure all
                                                    policies are followed.
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header" role="tab">
                                            <h3 class="mb-0"><a class="collapsed" data-bs-toggle="collapse"
                                                    href="#collapseFour">What is smile analysis?</a></h3>
                                        </div>
                                        <div class="collapse" id="collapseFour" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                <p>
                                                    A smile analysis determines the proper
                                                    aesthetics with relationship to your facial symmetry, lip line and
                                                    phonetics. Each person has a unique "perfect" smile; one proportional
                                                    and flattering to your face. And with smile analysis, we can show you
                                                    what your best smile can look like.
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header" role="tab">
                                            <h3 class="mb-0"><a class="collapsed" data-bs-toggle="collapse"
                                                    href="#collapseFive">How do I know when it's time to come in for a
                                                    checkup?</a></h3>
                                        </div>
                                        <div class="collapse" id="collapseFive" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                <p>
                                                    An average, healthy adult person typically
                                                    benefits from a professional cleaning and checkup every six months.
                                                    While twice daily brushing and daily flossing go a long way towards
                                                    maintaining a healthy mouth, it is wise to check for plaque that has
                                                    hardened into tartar, requiring professional cleaning to avoid
                                                    gingivitis.
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header" role="tab">
                                            <h3 class="mb-0"><a class="collapsed" data-bs-toggle="collapse"
                                                    href="#collapseSix">What do I do if I have an emergency when the office
                                                    is closed?</a></h3>
                                        </div>
                                        <div class="collapse" id="collapseSix" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                <p>
                                                    In case of a true dental emergency, please
                                                    contact our Emergency Care line at 1-867-873-1250.
                                                </p>
                                            </div>
                                        </div>
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
