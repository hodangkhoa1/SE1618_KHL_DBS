<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service Management | Page: ${CURRENT_PAGE}</title>
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
        <link href=".././css/admin/ServiceManagement.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/FooterPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body ng-app="myapp" ng-controller="viewCril">
        <jsp:include page="../../layouts/admin/SideBar.jsp"></jsp:include>
        
        <section id="content">
            <jsp:include page="../../layouts/admin/NavBar.jsp"></jsp:include>
            
            <!-- MAIN -->
            <main class="main">
                <h1 class="title">Service Management</h1>
                <ul class="breadcrumbs">
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard">Gentle Dental Care</a></li>
                    <li class="divider">/</li>
                    <li><a href="${pageContext.request.contextPath}/admin/service-management" class="active">Service Management</a></li>
                </ul>

                <div class="management-button">
                    <button ng-click="exportToExcel('#tableToExport')" type="button" class="button-export">
                        <i class="fa-solid fa-file-excel"></i>Export Excel
                    </button>
                    <button onclick="window.location.href='${pageContext.request.contextPath}/admin/add-service'" type="button" class="button-export button-add">
                        <i class="fa-solid fa-user-plus"></i>Add Service
                    </button>
                </div>

                <div class="table-users" id="tableToExport">
                    <table cellspacing="0">
                        <tr>
                            <th>STT</th>
                            <th>Service Name</th>
                            <th>Service Price</th>
                            <th>Service Status</th>
                            <th>Actions</th>
                        </tr>

                        <tr ng-repeat="service in listService | filter: ${SEARCH}">
                            <td>{{$index + 1}}</td>
                            <td>{{service.serviceName}}</td>
                            <td>{{service.servicePrice}}</td>
                            <td>{{service.serviceStatus == 0 ? "Not disabled" : "Disabled"}}</td>
                            <td>
                                <div class="table-action-button">
                                    <a href="${pageContext.request.contextPath}/admin/edit-service?sid={{service.serviceID}}" class="users-control btn btn-primary">Edit</a>
                                    <button ng-click="disable(service.serviceID, service.serviceStatus === 0 ? 'Disable' : 'UnDisable')" type="button" class="users-control btn btn-danger">{{service.serviceStatus == 0 ? "Disable" : "UnDisable"}}</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>

                <!-- User Management Pagination -->
                <c:if test="${END_PAGE > 1}">
                    <div class="table-pagination">
                        <ul>
                            <c:if test="${CURRENT_PAGE > 1}">
                                <li class="pagination-button button-prev">
                                    <a href="${pageContext.request.contextPath}/admin/service-management?page=${CURRENT_PAGE - 1}">
                                        <i class="fas fa-angle-left"></i> Prev
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${END_PAGE}" var="i">
                                <li class="pagination-number ${CURRENT_PAGE == i ? "active" : ""}">
                                    <a href="${pageContext.request.contextPath}/admin/service-management?page=${i}" class="pagination-link">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${CURRENT_PAGE < END_PAGE}">
                                <li class="pagination-button button-next">
                                    <a href="${pageContext.request.contextPath}/admin/service-management?page=${CURRENT_PAGE + 1}">
                                        Next <i class="fas fa-angle-right"></i>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </c:if>
            </main>
            <!-- MAIN -->
            
            <jsp:include page="../../layouts/admin/FooterPage.jsp"></jsp:include>
        </section>
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- JQUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- LINK ANGULAR -->
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
        <!-- LINK Sweet Alert 2 -->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- LINK SCRIPT -->
        <script src=".././js/admin/AdminRoot.js"></script>
        <script src=".././js/admin/NavBar.js"></script>
        <script src=".././js/admin/SideBar.js"></script>
        <script src=".././js/admin/ServiceManagement.js"></script>
        <script>
            ManageServiceAPI("${pageContext.request.contextPath}/admin/service-management", ${SERVICE_LIST});
            activeSidebarLink();
        </script>
    </body>
</html>
