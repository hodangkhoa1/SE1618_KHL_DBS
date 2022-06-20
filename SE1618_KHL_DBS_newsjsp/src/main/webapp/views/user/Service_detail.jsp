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
        <!-- Service css -->
        <link rel="stylesheet" href="./css/user/Service.css">
        <link rel="stylesheet" href="./css/user/Service_bootstrap.css">
        <!-- Bootstrap core CSS -->
        <link href="./css/user/style_servicedetail.css" rel="stylesheet">
        <!-- FontAwesome Icons core CSS -->
        <link href="./css/user/font-awesome.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="./css/user/style.css" rel="stylesheet">
        <!-- Responsive styles for this template -->
        <link href="./css/user/responsive.css" rel="stylesheet">
        <!-- Version Tech CSS for this template -->
        <link href="./css/user/version/tech.css" rel="stylesheet">
        <link href="style_servicedetail.css" rel="stylesheet">

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
                            <h1>Dental Veneer</h1>
                        </div>
                    </div>


                    <div id="wrapper">
                        <header class="tech-header header">
                            <div class="container-fluid">

                            </div><!-- end container-fluid -->
                        </header><!-- end market-header -->

                        <section class="section single-wrapper">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="page-wrapper">
                                            <div class="blog-content">
                                             
                                                <div id="toc_container" class="no_bullets">
                                                    <ul class="toc_list">
                                                        <li><a href="#1_What_are_Veneers">1. What are Veneers?</a></li>
                                                        <li><a href="#2_Who_might_be_suited_to_wearing_Veneers">2. Who might be suited to
                                                                wearing
                                                                Veneers?</a></li>
                                                        <li><a
                                                                href="#3_Check_out_some_of_the_many_advantages_our_patients_at_Elite_Dental_have_found_with_Veneers">3.
                                                                Check out some of the many advantages our patients at Elite Dental have
                                                                found with
                                                                Veneers</a></li>
                                                        <li><a href="#4_Veneers_procedure">4. Veneers procedure</a></li>
                                                        <li><a
                                                                href="#5_Elite_Dental_delivershigh-quality_service_to_bring_you_a_bright_and_long-lasting_smile">5.
                                                                Elite Dental delivers&nbsp;high-quality service to bring you a bright and
                                                                long-lasting
                                                                smile</a></li>
                                                    </ul>
                                                </div>
                                                <div class="pp">
                                                    <p><span style="font-weight: 400;">Dental Veneers are a cosmetic permanent treatment that helps correct
                                                            multiple dental issues, for example chips, cracks, stains or even tiny spaces in teeth in order to
                                                            create a natural smile. Let’s take a closer look at how Veneers work and why Veneers have become a
                                                            popular dentistry practice these days.</span></p>
                                                    <h2 style="font-weight: bold;"><span id="1_What_are_Veneers">1. <span class="span-style"
                                                                                                                          style="font-weight: bold;">What are </span>Veneers?</span></h2>
                                                    <p><span style="font-weight: 400;">Let’s think of Veneers this way: If you have imperfect walls but do not
                                                            want to repaint them, high quality wallpaper can help hide their surface imperfections. Veneers work
                                                            the same way.&nbsp;</span></p>
                                                    <p><span style="font-weight: 400;">Veneers are thin layers of material that are placed on the front surface
                                                            of teeth to cover inadequacies in them and get your beautiful smile back. In addition, the unique
                                                            aspect of this aesthetic treatment is that Veneers are custom-made to match your natural teeth in
                                                            size and shape.</span></p>
                                                    <p><span style="font-weight: 400;">Veneers are either made from porcelain or composite material. They both
                                                            can transform your smile into an optimal result. However, porcelain veneers are slightly more
                                                            popular because of their highly aesthetic material and translucent appearance.</span></p>
                                                    <h2 style="font-weight: bold;"><span id="2_Who_might_be_suited_to_wearing_Veneers">2. <span
                                                                class="span-style" style="font-weight: bold;">Who might be suited </span>to wearing
                                                            Veneers?</span></h2>
                                                    <p><span style="font-weight: 400;">Veneers are typically used to improve the look of teeth rather than to
                                                            repair damage. Veneers are recommended in the following cases:</span></p>
                                                    <p><b>Deformity</b><span style="font-weight: 400;">: Crooked or chipped teeth that may have been caused by
                                                            accidents.</span></p>
                                                    <p><b>Tooth stains or discoloration</b><span style="font-weight: 400;">: happens when you take colored food
                                                            or beverage.</span></p>
                                                    <p><b>Tooth gaps:</b><span style="font-weight: 400;"> mostly seen between front teeth.&nbsp;</span></p>
                                                    <p><span style="font-weight: 400;">It is important to note that Veneers are only applied to healthy and
                                                            strong teeth (If not, patients are recommended for a crown instead). Moreover, Veneers might not be
                                                            suitable for individuals who have a habit of grinding or clenching their teeth because this can
                                                            cause cracks in the veneers.&nbsp;</span></p>
                                                    <h2 style="font-weight: bold;"><span
                                                            id="3_Check_out_some_of_the_many_advantages_our_patients_at_Elite_Dental_have_found_with_Veneers">3.
                                                            Check out <span class="span-style" style="font-weight: bold;">some of the many advantages</span> our
                                                            patients at Elite Dental have found with Veneers</span></h2>
                                                    <ul>
                                                        <li style="font-weight: 400;"><span style="font-weight: 400;">A more instant natural-looking appearance
                                                                than any other restorative dental treatment.</span></li>
                                                        <li style="font-weight: 400;"><span style="font-weight: 400;">Durability, especially porcelain veneers.
                                                                They are long-lasting and may last more than 10 years with good oral hygiene.</span></li>
                                                        <li style="font-weight: 400;"><span style="font-weight: 400;">Stain resistance: Veneers do not stain
                                                                easily, even with dark-coloured food or beverages such as coffee and tea</span></li>
                                                        <li style="font-weight: 400;"><span style="font-weight: 400;">Dramatic improvement of broken, chipped,
                                                                and misshapen teeth.&nbsp;</span></li>
                                                        <li style="font-weight: 400;"><span style="font-weight: 400;">Non-injury of gums or natural tooth
                                                                structure non-irritating of your oral tissues.</span></li>
                                                    </ul>
                                                    <h2 style="font-weight: bold;"><span id="4_Veneers_procedure">4. Veneers <span class="span-style"
                                                                                                                                   style="font-weight: bold;">procedure</span></span></h2>
                                                    <p><span style="font-weight: 400;">“Is it painful to get Veneers?” is the most commonly asked question that
                                                            we receive. The answer is there may be some discomfort during the tooth enamel removal procedure.
                                                            However the treatment is typically pain-free.&nbsp;</span></p>
                                                    <p><span style="font-weight: 400;">Here is the global standard process of Veneers treatment at Elite
                                                            Dental.</span></p>
                                                    <p><span style="font-weight: 400;">First, patients have a consultation with dentists to discuss if they are
                                                            suitable&nbsp; candidates for Veneers. Then Xray images are ordered to ensure the shape, fit and
                                                            color of the Veneers match your&nbsp; natural teeth, bite and facial aesthetics.</span></p>
                                                    <p><span style="font-weight: 400;">Secondly, tooth impressions are created with special 3D mapping
                                                            technology. The mold will be sent to the lab where the custom Veneers will be made.&nbsp;</span></p>
                                                    <p><span style="font-weight: 400;">After that, on the day of the procedure, dentists remove a small amount
                                                            of enamel from teeth to make room for the Veneers. Local anesthetic is used to numb the local area
                                                            so you are comfortable during the treatment. In the meantime, temporary veneers are also prepared
                                                            and applied while patients wait for the permanents (2-4 weeks)</span></p>
                                                    <p><span style="font-weight: 400;">On your next visit, the permanents are affixed and adjusted on the
                                                            surface of the teeth using a special glue.</span></p>
                                                    <p><span style="font-weight: 400;">3-4 days after the previous visit, your dentist may ask you to come back
                                                            so they can check your gum and bite conditions. This step is also to make sure </span><span
                                                            style="font-weight: 400;">that</span><span style="font-weight: 400;"> you are getting used to your
                                                            new Veneers.&nbsp;</span></p>
                                                    <p><span style="font-weight: 400;">At Elite Dental Clinic, our Veneer dental experts are very experienced in
                                                            providing Veneers treatments. They can provide you with everything you need to know and recommend
                                                            the best option for you based on your specific needs. Contact us for a consultation visit!</span>
                                                    </p>
                                                    <h2 style="font-weight: bold;"><span
                                                            id="5_Elite_Dental_delivershigh-quality_service_to_bring_you_a_bright_and_long-lasting_smile">5.
                                                            Elite Dental delivers&nbsp;<span class="span-style" style="font-weight: bold;">high-quality
                                                                service</span> to bring you a bright and long-lasting smile</span></h2>
                                                    <p><span style="font-weight: 400;">You might wonder why you should choose Elite Dental for your Veneers,
                                                            here are our reasons:</span></p>
                                                    <p><b>Firstly</b><span style="font-weight: 400;">, Elite Dental comprises two state-of-the-art clinics. Both
                                                            are located in the center of District 3, HCMC with fully-equipped dental facilities, offering a wide
                                                            range of aesthetic restorations and treatments.&nbsp;</span></p>
                                                    <p><b>Secondly</b><span style="font-weight: 400;">, we have been helping patients for over 10 years, since
                                                            we were an independent clinic within An Sinh hospital (one of the most prominent hospitals in HCMC).
                                                            We are also proud of our highly-skilled dental staff who have many years of experience. Our
                                                            specialized dentists constantly receive training and have a great deal of experience in practical
                                                            aesthetic restorations, such as Veneers. They will help you create custom-made veneers which match
                                                            your face and skin color, creating a natural look.</span></p>
                                                    <p><b>Moreover</b><span style="font-weight: 400;">, at Elite Dental, we exclusively use Emax veneers,
                                                            high-quality porcelain veneers crafted and imported directly from Europe. The material generally
                                                            lasts between 8 to 10 years, even more with appropriate care.We have done our best to provide you
                                                            with the finest care, and we hope to help you maintain your results through a voluntary 5-year
                                                            warranty</span></p>
                                                    <p><span style="font-weight: 400;">We have been proudly ranked high in the client satisfaction survey, which
                                                            is regularly conducted at our clinic and on well-known dentistry associations in Vietnam. If you
                                                            need our assistance on your journey to achieve a healthy and bright smile, please feel free to
                                                            contact us. It is our pleasure to assist you!</span></p>
                                                </div><!-- end pp -->

                                                <img src="images/dental_veneer_1.jpg" alt="" class="img-fluid img-fullwidth">


                                            </div><!-- end content -->


                                            <hr class="invis1">

                                            <div class="custombox clearfix">
                                                <h4 class="small-title">3 Comments</h4>
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="comments-list">
                                                            <div class="media">
                                                                <a class="media-left" href="#">
                                                                    <img src="upload/author.jpg" alt="" class="rounded-circle">
                                                                </a>
                                                                <div class="media-body">
                                                                    <h4 class="media-heading user_name">Amanda Martines <small>5 days
                                                                            ago</small></h4>
                                                                    <p>Exercitation photo booth stumptown tote bag Banksy, elit small
                                                                        batch freegan sed. Craft beer elit seitan exercitation, photo
                                                                        booth et 8-bit kale chips proident chillwave deep v laborum.
                                                                        Aliquip veniam delectus, Marfa eiusmod Pinterest in do umami
                                                                        readymade swag. Selfies iPhone Kickstarter, drinking vinegar
                                                                        jean.</p>
                                                                    <a href="#" class="btn btn-primary btn-sm">Reply</a>
                                                                </div>
                                                            </div>
                                                            <div class="media">
                                                                <a class="media-left" href="#">
                                                                    <img src="upload/author_01.jpg" alt="" class="rounded-circle">
                                                                </a>
                                                                <div class="media-body">

                                                                    <h4 class="media-heading user_name">Baltej Singh <small>5 days
                                                                            ago</small></h4>

                                                                    <p>Drinking vinegar stumptown yr pop-up artisan sunt. Deep v cliche
                                                                        lomo biodiesel Neutra selfies. Shorts fixie consequat
                                                                        flexitarian four loko tempor duis single-origin coffee. Banksy,
                                                                        elit small.</p>

                                                                    <a href="#" class="btn btn-primary btn-sm">Reply</a>
                                                                </div>
                                                            </div>
                                                            <div class="media last-child">
                                                                <a class="media-left" href="#">
                                                                    <img src="upload/author_02.jpg" alt="" class="rounded-circle">
                                                                </a>
                                                                <div class="media-body">

                                                                    <h4 class="media-heading user_name">Marie Johnson <small>5 days
                                                                            ago</small></h4>
                                                                    <p>Kickstarter seitan retro. Drinking vinegar stumptown yr pop-up
                                                                        artisan sunt. Deep v cliche lomo biodiesel Neutra selfies.
                                                                        Shorts fixie consequat flexitarian four loko tempor duis
                                                                        single-origin coffee. Banksy, elit small.</p>

                                                                    <a href="#" class="btn btn-primary btn-sm">Reply</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div><!-- end col -->
                                                </div><!-- end row -->
                                            </div><!-- end custom-box -->

                                            <hr class="invis1">

                                            <div class="custombox clearfix">
                                                <h4 class="small-title">Leave a Reply</h4>
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <form class="form-wrapper">
                                                            <input type="text" class="form-control" placeholder="Your name">
                                                            <input type="text" class="form-control" placeholder="Email address">
                                                            <input type="text" class="form-control" placeholder="Website">
                                                            <textarea class="form-control" placeholder="Your comment"></textarea>
                                                            <button type="submit" class="btn btn-primary">Submit Comment</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- end page-wrapper -->
                                    </div><!-- end col -->

                                    <!-- end col -->
                                </div><!-- end row -->
                            </div><!-- end container -->
                        </section>

                        <!-- end footer -->


                    </div><!-- end wrapper -->

                    <!-- Core JavaScript
                    ================================================== -->

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
