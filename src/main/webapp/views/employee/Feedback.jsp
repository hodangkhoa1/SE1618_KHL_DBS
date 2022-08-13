<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    int totalNews = 0;
    if (request.getAttribute("TOTAL_FEEDBACK") != null) {
        totalNews = (int) request.getAttribute("TOTAL_FEEDBACK");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FeedBack</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href=".././images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
        <!-- Box Icons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- UN ICONS -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <!-- LINK STYLE -->
        <link rel="stylesheet" href=".././css/Loader.css">
        <link rel="stylesheet" href=".././css/employee/EmployeeRoot.css">
        <link rel="stylesheet" href=".././css/employee/NavBar.css">
        <link rel="stylesheet" href=".././css/employee/Feedback.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        
        <header class="header-background">
            <jsp:include page="../../layouts/employee/NavBar.jsp"></jsp:include>
        </header>
        
        <section class="feedbackMana mt-5">
            <div class="container-fluid">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header mb-4">
                            <p class="title">Feedback Details</p>
                            <span onclick="CloseModal()" class="close">&times;</span>
                        </div>

                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-md-12 cover mb-3">
                                    <p>Full Name:
                                        <input type="text" id="fullName" readonly value="">
                                    </p>
                                </div>

                                <div class="col-12 col-md-3 cover mb-3">
                                    <p>Gender:
                                        <input type="text" id="gender" readonly value="">
                                    </p>
                                </div>

                                <div class="col-12 col-md-4 cover mb-3">
                                    <p>Date Of Birth:
                                        <input type="text" id="dateOfBirth" readonly value="">
                                    </p>
                                </div>

                                <div class="col-12 col-md-5 cover mb-3">
                                    <p>Phone Number:
                                        <input type="text" id="phoneNumber" readonly value="">
                                    </p>
                                </div>

                                <div class="col-12 col-md-12 cover mb-3">
                                    <p>Email:
                                        <input type="text" id="email" readonly value="">
                                    </p>
                                </div>

                                <div class="col-12 col-md-12 cover mb-3">
                                    <p>Address:
                                        <textarea name="" id="address" readonly rows="1"></textarea>
                                    </p>
                                </div>

                                <div class="col-12 cover mb-3">
                                    <p class="mb-2 textFeedback">Feedback message:
                                        <textarea class="p-2" name="" readonly id="feedback" rows="5"></textarea>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="heading_feedback mb-3">
                    <h2 class="mb-4 feedback__title">Feedback</h2>
                </div>

                <div class="pb-3 mb-2">
                    <div class="row" id="return-list">
                        <c:forEach items="${FEEDBACK_LIST}" var="feedBack">
                            <div class="col-12 my-2 feedback-amount">
                                <div onclick="ViewInformation('${feedBack.fullName}', '${feedBack.gender}', '<fmt:formatDate value="${feedBack.dateOfBirth}" pattern="dd-MM-yyyy"/>', '${feedBack.phoneNumber}', '${feedBack.email}', '${feedBack.address}', '${feedBack.feedBackContent}')" class="card-feedback">
                                    <div class="feedback--top">
                                        <div class="feedback--id ps-2">
                                            <p>FeedBack code: <span class="id">${feedBack.feedBackID}</span></p>
                                            <p>|</p>
                                            <p>FeedBack date: <span><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${feedBack.feedBackCreated}"/></span></p>
                                        </div>

                                            <button onclick="ViewInformation('${feedBack.fullName}', '${feedBack.gender}', '<fmt:formatDate value="${feedBack.dateOfBirth}" pattern="dd-MM-yyyy"/>', '${feedBack.phoneNumber}', '${feedBack.email}', '${feedBack.address}', '${feedBack.feedBackContent}')" style="border: none" class="btn-eye">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>
                                    </div>

                                    <div class="feedback--bottom p-3">
                                        <div class="bottom-img">
                                            <div class="feedback--imgUser" style="background-color: ${feedBack.imageAvatar != null ? "transparent" : feedBack.colorAvatar};">
                                                <c:if test="${feedBack.imageAvatar != null}">
                                                    <img src="data:image/png;base64,${feedBack.imageAvatar}" alt="">
                                                </c:if>
                                                <c:if test="${feedBack.imageAvatar == null}">
                                                    <p>${feedBack.defaultAvatar}</p>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="bottom-content">
                                            <p>Name: <span class="name-feedback">${feedBack.fullName}</span></p>
                                            <p>Phone: <span class="phone">${feedBack.phoneNumber}</span></p>
                                            <p>
                                                Service: <span class="service">
                                                    <c:forEach items="${feedBack.serviceList}" var="service">${service.serviceName}, </c:forEach>
                                                </span>
                                            </p>
                                            <p>
                                                Hospital: <span class="service">${feedBack.hospitalName}</span>
                                            </p>
                                            <div class="reviewStart d-flex d-md-none">
                                                <p>Service review:</p>
                                                <div class="review pending">
                                                    <div class="content">
                                                        <div class="stars">
                                                            <c:choose>
                                                                <c:when test = "${feedBack.numberRating == 5}">
                                                                    <label for="star-1" class="star-1 fas fa-star"></label>
                                                                    <label for="star-2" class="star-2 fas fa-star"></label>
                                                                    <label for="star-3" class="star-3 fas fa-star"></label>
                                                                    <label for="star-4" class="star-4 fas fa-star"></label>
                                                                    <label for="star-5" class="star-5 fas fa-star"></label>
                                                                </c:when>

                                                                <c:when test = "${feedBack.numberRating == 4}">
                                                                    <label for="star-1" class="star-1 fas fa-star"></label>
                                                                    <label for="star-2" class="star-2 fas fa-star"></label>
                                                                    <label for="star-3" class="star-3 fas fa-star"></label>
                                                                    <label for="star-4" class="star-4 fas fa-star"></label>
                                                                </c:when>

                                                                <c:when test = "${feedBack.numberRating == 3}">
                                                                    <label for="star-1" class="star-1 fas fa-star"></label>
                                                                    <label for="star-2" class="star-2 fas fa-star"></label>
                                                                    <label for="star-3" class="star-3 fas fa-star"></label>
                                                                </c:when>

                                                                <c:when test = "${feedBack.numberRating == 2}">
                                                                    <label for="star-1" class="star-1 fas fa-star"></label>
                                                                    <label for="star-2" class="star-2 fas fa-star"></label>
                                                                </c:when>

                                                                <c:when test = "${feedBack.numberRating == 1}">
                                                                    <label for="star-1" class="star-1 fas fa-star"></label>
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="d-none d-md-flex status pending">
                                        <div class="content">
                                            <div class="stars">
                                                <c:choose>
                                                    <c:when test = "${feedBack.numberRating == 5}">
                                                        <label for="star-1" class="star-1 fas fa-star"></label>
                                                        <label for="star-2" class="star-2 fas fa-star"></label>
                                                        <label for="star-3" class="star-3 fas fa-star"></label>
                                                        <label for="star-4" class="star-4 fas fa-star"></label>
                                                        <label for="star-5" class="star-5 fas fa-star"></label>
                                                    </c:when>

                                                    <c:when test = "${feedBack.numberRating == 4}">
                                                        <label for="star-1" class="star-1 fas fa-star"></label>
                                                        <label for="star-2" class="star-2 fas fa-star"></label>
                                                        <label for="star-3" class="star-3 fas fa-star"></label>
                                                        <label for="star-4" class="star-4 fas fa-star"></label>
                                                    </c:when>
                                                        
                                                    <c:when test = "${feedBack.numberRating == 3}">
                                                        <label for="star-1" class="star-1 fas fa-star"></label>
                                                        <label for="star-2" class="star-2 fas fa-star"></label>
                                                        <label for="star-3" class="star-3 fas fa-star"></label>
                                                    </c:when>
                                                        
                                                    <c:when test = "${feedBack.numberRating == 2}">
                                                        <label for="star-1" class="star-1 fas fa-star"></label>
                                                        <label for="star-2" class="star-2 fas fa-star"></label>
                                                    </c:when>
                                                    
                                                    <c:when test = "${feedBack.numberRating == 1}">
                                                        <label for="star-1" class="star-1 fas fa-star"></label>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${NOT_EMPTY == null}">
                            <div class="row g-5">
                                <div class="col-12 pt-4">
                                    <div class="view-more">
                                        <button onclick="loadMore('<%=totalNews%>', '${pageContext.request.contextPath}/news')" class="button-view">Load more</button>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- LINK J QUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- LINK JAVA SCRIPT -->
        <script src=".././js/employee/EmployeeRoot.js"></script>
        <script src=".././js/employee/NavBar.js"></script>
        <script src=".././js/employee/Feedback.js"></script>
        <script>
            setActiveMenuBar();
        </script>
    </body>
</html>
