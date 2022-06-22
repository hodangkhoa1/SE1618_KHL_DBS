<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="./css/user/Loader.css">
        <link rel="stylesheet" href="./css/ScrollBackToTop.css">
        <link rel="stylesheet" href="./css/user/BoxChat.css">
        <link rel="stylesheet" href="./css/user/NavBar.css">
        <link rel="stylesheet" href="./css/user/HistoryBooking.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
    </head>
    <body>
        <jsp:include page="../../layouts/user/Loader.html"></jsp:include>
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
                <div class="row">
                    <div class="col-12 history__card mb-4">
                        <div class="card--top">
                            <div class="top--content">
                                <p>Mã đặt hẹn: <span class="maId">1000195137</span></p>
                                <p>|</p>
                                <p>Đặt ngày: <span>11/02/2022</span> <span>21:45:35</span></p>
                            </div>
                        </div>

                        <div class="card--bottom">
                            <div class="cb__img">
                                <img src="https://media.hasaki.vn/wysiwyg/spa/trietlong.jpg" alt="">
                            </div>

                            <div class="cb__content">
                                <p>Họ tên: <span class="name">Ngọc Ngân</span></p>
                                <p>Điện thoại: <span>0977125153</span></p>
                                <p>Địa chỉ: <span>Địa chỉ : 94 Lê Văn Việt, P.Hiệp Phú, Q.9, TP.HCM</span></p>
                                <p>Dịch vụ: <span>Khám răng</span></p>
                            </div>
                            <div class="status--cover">
                                <div class="status">
                                    <p>Đang xử lý</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 history__card mb-4">
                        <div class="card--top">
                            <div class="top--content">
                                <p>Mã đặt hẹn: <span class="maId">1000195137</span></p>
                                <p>|</p>
                                <p>Đặt ngày: <span>11/02/2022</span> <span>21:45:35</span></p>
                            </div>
                        </div>

                        <div class="card--bottom">
                            <div class="cb__img">
                                <img src="https://media.hasaki.vn/wysiwyg/spa/trietlong.jpg" alt="">
                            </div>

                            <div class="cb__content">
                                <p>Họ tên: <span class="name">Ngọc Ngân</span></p>
                                <p>Điện thoại: <span>0977125153</span></p>
                                <p>Địa chỉ: <span>Địa chỉ : 94 Lê Văn Việt, P.Hiệp Phú, Q.9, TP.HCM</span></p>
                                <p>Dịch vụ: <span>Khám răng</span></p>
                            </div>
                            <div class="status--cover">
                                <div class="status">
                                    <p>Đang xử lý</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 history__card mb-4">
                        <div class="card--top">
                            <div class="top--content">
                                <p>Mã đặt hẹn: <span class="maId">1000195137</span></p>
                                <p>|</p>
                                <p>Đặt ngày: <span>11/02/2022</span> <span>21:45:35</span></p>
                            </div>
                        </div>

                        <div class="card--bottom">
                            <div class="cb__img">
                                <img src="https://media.hasaki.vn/wysiwyg/spa/trietlong.jpg" alt="">
                            </div>

                            <div class="cb__content">
                                <p>Họ tên: <span class="name">Ngọc Ngân</span></p>
                                <p>Điện thoại: <span>0977125153</span></p>
                                <p>Địa chỉ: <span>Địa chỉ : 94 Lê Văn Việt, P.Hiệp Phú, Q.9, TP.HCM</span></p>
                                <p>Dịch vụ: <span>Khám răng</span></p>
                            </div>
                            <div class="status--cover">
                                <div class="status">
                                    <p>Đang xử lý</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 history__card mb-4">
                        <div class="card--top">
                            <div class="top--content">
                                <p>Mã đặt hẹn: <span class="maId">1000195137</span></p>
                                <p>|</p>
                                <p>Đặt ngày: <span>11/02/2022</span> <span>21:45:35</span></p>
                            </div>
                        </div>

                        <div class="card--bottom">
                            <div class="cb__img">
                                <img src="https://media.hasaki.vn/wysiwyg/spa/trietlong.jpg" alt="">
                            </div>

                            <div class="cb__content">
                                <p>Họ tên: <span class="name">Ngọc Ngân</span></p>
                                <p>Điện thoại: <span>0977125153</span></p>
                                <p>Địa chỉ: <span>Địa chỉ : 94 Lê Văn Việt, P.Hiệp Phú, Q.9, TP.HCM</span></p>
                                <p>Dịch vụ: <span>Khám răng</span></p>
                            </div>
                            <div class="status--cover">
                                <div class="status">
                                    <p>Đang xử lý</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 history__card mb-4">
                        <div class="card--top">
                            <div class="top--content">
                                <p>Mã đặt hẹn: <span class="maId">1000195137</span></p>
                                <p>|</p>
                                <p>Đặt ngày: <span>11/02/2022</span> <span>21:45:35</span></p>
                            </div>
                        </div>

                        <div class="card--bottom">
                            <div class="cb__img">
                                <img src="https://media.hasaki.vn/wysiwyg/spa/trietlong.jpg" alt="">
                            </div>

                            <div class="cb__content">
                                <p>Họ tên: <span class="name">Ngọc Ngân</span></p>
                                <p>Điện thoại: <span>0977125153</span></p>
                                <p>Địa chỉ: <span>Địa chỉ : 94 Lê Văn Việt, P.Hiệp Phú, Q.9, TP.HCM</span></p>
                                <p>Dịch vụ: <span>Khám răng</span></p>
                            </div>
                            <div class="status--cover">
                                <div class="status">
                                    <p>Đang xử lý</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 history__card mb-4">
                        <div class="card--top">
                            <div class="top--content">
                                <p>Mã đặt hẹn: <span class="maId">1000195137</span></p>
                                <p>|</p>
                                <p>Đặt ngày: <span>11/02/2022</span> <span>21:45:35</span></p>
                            </div>
                        </div>

                        <div class="card--bottom">
                            <div class="cb__img">
                                <img src="https://media.hasaki.vn/wysiwyg/spa/trietlong.jpg" alt="">
                            </div>

                            <div class="cb__content">
                                <p>Họ tên: <span class="name">Ngọc Ngân</span></p>
                                <p>Điện thoại: <span>0977125153</span></p>
                                <p>Địa chỉ: <span>Địa chỉ : 94 Lê Văn Việt, P.Hiệp Phú, Q.9, TP.HCM</span></p>
                                <p>Dịch vụ: <span>Khám răng</span></p>
                            </div>
                            <div class="status--cover">
                                <div class="status">
                                    <p>Đang xử lý</p>
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
