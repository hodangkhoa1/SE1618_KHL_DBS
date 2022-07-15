<%@page import="com.khl.gentledentalcare.models.NewsError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    NewsError newsError = (NewsError) request.getAttribute("NEWS_ERROR");
    if(newsError == null) {
        newsError = new NewsError();
    }
    
    String imageService = (String) request.getAttribute("IMAGE_NEWS");
    String concatStringImage = "";
    if (imageService != null) {
        concatStringImage = "data:image/png;base64," + imageService;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${BUTTON_ACTION}</title>
        <!-- Favicon icon -->
        <link rel="icon" href=".././images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- Iconscout CSS -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONTAWESOME -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
        <!-- Box Icons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- LINK STYLE -->
        <link href=".././css/admin/AdminRoot.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/NavBar.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/SideBar.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/AddNews.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/FooterPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../../layouts/admin/SideBar.jsp"></jsp:include>
        
        <section id="content">
            <jsp:include page="../../layouts/admin/NavBar.jsp"></jsp:include>
            
            <!-- MAIN -->
            <main class="main">
                <h1 class="title">${BUTTON_ACTION}</h1>
                <ul class="breadcrumbs">
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard">Gentle Dental Care</a></li>
                    <li class="divider">/</li>
                    <li><a href="${pageContext.request.contextPath}/admin/add-service" class="active">${BUTTON_ACTION}</a></li>
                </ul>

                <div class="form_wrapper">
                    <div class="form_container">
                        <div class="title_container">
                            <h2>${BUTTON_ACTION}</h2>
                        </div>
                        <div class="row clearfix">
                            <div>
                                <form action="${ACTION_URL}" method="post">
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-font"></i>
                                        </span>
                                        <input type="text" class="news-name" name="newsName" value="${NAME_NEWS != null ? NAME_NEWS : ""}" placeholder="News Name" oninput="CheckNewsName()" onblur="CheckNewsName()" />
                                    </div>
                                    <div class="text-danger news-name-error">
                                        <p><%=newsError.getNameOfNewsError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-font"></i>
                                        </span>
                                        <input type="text" class="subtitle-news" name="subtitleNews" value="${SUBTITLE_NEWS != null ? SUBTITLE_NEWS : ""}" placeholder="Subtitle News" oninput="CheckSubtitleNews()" onblur="CheckSubtitleNews()" />
                                    </div>
                                    <div class="text-danger subtitle-news-error">
                                        <p><%=newsError.getSubtitleNewsError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <div class="image-input">
                                            <input type="file" accept="image/*" id="imageInput">
                                            <img src="<%=concatStringImage%>" class="image-preview image-edit">
                                            <input type="hidden" class="image-hidden" name="${IMAGE_NEWS != null ? "newsImage" : ""}" value="<%=concatStringImage%>">
                                            <label for="imageInput" class="image-button add-button">
                                                <i class="fa-solid fa-image"></i>News Picture
                                            </label>
                                            <span class="change-image">Change Picture</span>
                                        </div>
                                    </div>
                                    <div class="text-danger news-image-error">
                                        <p><%=newsError.getImageNewsError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <textarea name="newsDetailContent" class="news-detail-content" required id="editor">${DETAIL_NEWS != null ? DETAIL_NEWS : ""}</textarea>
                                    </div>
                                    <div class="text-danger news-detail-error">
                                        <p><%=newsError.getNewsDetailContentError()%></p>
                                    </div>
                                    <input class="button add-service" type="submit" value="${BUTTON_ACTION}" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
            
            <jsp:include page="../../layouts/admin/FooterPage.jsp"></jsp:include>
        </section>
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- JQUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- LINK CKEDITOR -->
        <script src="//cdn.ckeditor.com/4.19.0/full/ckeditor.js"></script>
        <!-- LINK SCRIPT -->
        <script src=".././js/admin/AdminRoot.js"></script>
        <script src=".././js/admin/NavBar.js"></script>
        <script src=".././js/admin/SideBar.js"></script>
        <script src=".././js/admin/AddNews.js"></script>
        <script>
            CKEDITOR.replace('editor', {
                editorplaceholder: 'Start typing here...'
            });
            
            activeSidebarLink();
        </script>
    </body>
</html>
