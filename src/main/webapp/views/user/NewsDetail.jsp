<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>News</title>
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
        <link rel="stylesheet" href="./css/user/NewsDetail.css">
        <link rel="stylesheet" href="./css/user/FooterPage.css">
        <link rel="stylesheet" href="./css/user/SupportOnline.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        <jsp:include page="../../layouts/ScrollBackToTop.html"></jsp:include>
        <jsp:include page="../../layouts/user/BoxChat.jsp"></jsp:include>
        
        <header class="header-top header-background">
            <jsp:include page="../../layouts/user/NavBar.jsp"></jsp:include>
        </header>
        
        <section class="section py-2">
            <div class="container">
                <div class="popular-news">
                    <div class="row">
                        <div class="col-lg-9">
                            <div class="news-content">
                                <h1 class="news-content-title">${NEWS_DETAIL.nameOfNews}</h1>
                                <div>
                                    <small class="news-content-author">
                                        <i class="fa-solid fa-user"></i>Gentle Dental Care
                                    </small>
                                    <small class="news-content-time">
                                        <i class="fa-solid fa-clock"></i><fmt:formatDate value="${NEWS_DETAIL.postDate}" pattern="dd-MM-yyyy"/>
                                    </small>
                                </div>
                                <div class="news-content-body">
                                    <div class="news-content-image">
                                        <img src="data:image/png;base64,${NEWS_DETAIL.imageNews}" alt="">
                                    </div>
                                    <div class="my-5">
                                        ${NEWS_DETAIL.newsDetailContent}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="news-latest">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="d-flex position-relative float-left">
                                            <h3 class="section-title">Latest News</h3>
                                        </div>
                                    </div>
                                    <c:forEach items="${TOP_NEWS}" var="newsLatest">
                                        <div class="col-sm-12">
                                            <a href="${pageContext.request.contextPath}/news-detail?nid=${newsLatest.newsID}" class="news-latest-link border-bottom pb-3">
                                                <h5 class="font-weight-600 mt-0 mb-0">${newsLatest.nameOfNews}</h5>
                                                <p class="text-color mb-0 mt-2 d-flex align-items-center">
                                                    <i class="fa-solid fa-bookmark"></i>
                                                    <span><fmt:formatDate value="${newsLatest.postDate}" pattern="dd-MM-yyyy"/></span>
                                                </p>
                                            </a>
                                        </div>
                                    </c:forEach>
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
