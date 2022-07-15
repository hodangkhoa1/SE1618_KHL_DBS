<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Appointment</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href=".././images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONTAWESOME -->
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
        <link rel="stylesheet" href=".././css/employee/Appointment.css">
    </head>
    <body>
        <jsp:include page="../../layouts/Loader.html"></jsp:include>
        
        <!-- View Appointment Start -->
        <div class="modal fade" id="viewappointment" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel1">Appointment Detail</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <div class="d-flex align-items-center">
                            <img src="" id="modal-image" class="avatar avatar-small rounded-pill" alt="">
                            <h5 id="modal-name" class="mb-0 ms-3 title-name"></h5>
                        </div>
                        <ul class="list-unstyled mb-0 d-md-flex justify-content-between mt-4">
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Hospital:</h6>
                                        <p id="modal-hospital" class="text-muted ms-2"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Service:</h6>
                                        <p id="modal-service" class="text-muted ms-2" id="modal-service"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Slot:</h6>
                                        <p class="text-muted ms-2" id="modal-slot"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Date:</h6>
                                        <p id="modal-date" class="text-muted ms-2"></p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- View Appointment End -->

        <!-- Accept Appointment Start -->
        <div class="modal fade" id="acceptappointment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body py-5">
                        <div class="text-center">
                            <div class="icon d-flex align-items-center justify-content-center bg-soft-success rounded-circle mx-auto">
                                <span class="mb-0">
                                    <i class="uil uil-check-circle"></i>
                                </span>
                            </div>
                            <div class="mt-4">
                                <h4 class="modal-body-title">Accept Appointment</h4>
                                <p class="mx-auto text-muted mb-0">Great doctor if you need your family member
                                    to get immediate assistance, emergency treatment.</p>
                                <div class="mt-4">
                                    <button onclick="AcceptAppointment('${pageContext.request.contextPath}/employee/appointment')" class="btn btn-soft-success">Accept</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Accept Appointment End -->

        <!-- Cancel Appointment Start -->
        <div class="modal fade" id="cancelappointment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body py-5">
                        <div class="text-center">
                            <div class="icon d-flex align-items-center justify-content-center bg-soft-danger rounded-circle mx-auto">
                                <span class="mb-0">
                                    <i class="uil uil-times-circle h1"></i>
                                </span>
                            </div>
                            <div class="mt-4">
                                <h4 class="modal-body-title">Cancel Appointment</h4>
                                <p class="mx-auto text-muted mb-0">Great doctor if you need your family member
                                    to get immediate assistance, emergency treatment.</p>
                                <div class="mt-4">
                                    <button onclick="CancelAppointment('${pageContext.request.contextPath}/employee/appointment')" class="btn btn-soft-danger">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cancel Appointment End -->
        
        <header class="header-background">
            <jsp:include page="../../layouts/employee/NavBar.jsp"></jsp:include>
        </header>
        
        <section class="bg-dashboard">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 mt-12 pt-12 mt-sm-12 pt-sm-12">
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded" id="tableToExport">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" style="min-width: 50px;">#</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Name</th>
                                                <th class="border-bottom p-3">Hospital</th>
                                                <th class="border-bottom p-3">Service</th>
                                                <th class="border-bottom p-3">Slot</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Date</th>
                                                <th class="border-bottom p-3">Status</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${APPOINTMENT_LIST}" var="appointment" varStatus="loop">
                                                <tr>
                                                    <th class="p-3">${loop.index + 1}</th>
                                                    <td class="p-3">
                                                        <div class="text-dark">
                                                            <div class="d-flex align-items-center">
                                                                <c:if test="${appointment.imageAvatar != null}">
                                                                    <img src="data:image/png;base64,${appointment.imageAvatar}" class="avatar avatar-md-sm rounded-circle shadow" alt="">
                                                                </c:if>
                                                                <span class="ms-2">${appointment.fullName}</span>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="p-3">${appointment.hospitalName}</td>
                                                    <td class="p-3">
                                                        <span>
                                                            <c:forEach items="${appointment.serviceList}" var="service">${service.serviceName} </c:forEach>
                                                        </span>
                                                    </td>
                                                    <td class="p-3">
                                                        <span>
                                                            <c:forEach items="${appointment.slotList}" var="slot"><fmt:formatDate type="time" value="${slot.slotStart}"/> </c:forEach>
                                                        </span>
                                                    </td>
                                                    <td class="p-3">${appointment.bookingDate}</td>
                                                    <td class="p-3">${appointment.bookingStatus == 0 ? "Pending" : (appointment.bookingStatus == 1 ? "Done" : "Cancel")}</td>
                                                    <td class="text-end p-3">
                                                        <button onclick="ViewAppointment('${appointment.imageAvatar}', '${appointment.fullName}', '${appointment.hospitalName}', '<c:forEach items="${appointment.serviceList}" var="service">${service.serviceName} </c:forEach>', '<c:forEach items="${appointment.slotList}" var="slot"><fmt:formatDate type="time" value="${slot.slotStart}"/> </c:forEach>', '${appointment.bookingDate}');" class="btn btn-icon btn-pills btn-soft-primary" data-bs-toggle="modal" data-bs-target="#viewappointment">
                                                            <i class="uil uil-eye"></i>
                                                        </button>
                                                        <c:if test="${appointment.bookingStatus == 0}">
                                                            <button onclick="GetAppointmentID('${appointment.bookingID}')" class="btn btn-icon btn-pills btn-soft-success" data-bs-toggle="modal" data-bs-target="#acceptappointment">
                                                                <i class="uil uil-check-circle"></i>
                                                            </button>
                                                            <button onclick="GetAppointmentID('${appointment.bookingID}')" class="btn btn-icon btn-pills btn-soft-danger" data-bs-toggle="modal" data-bs-target="#cancelappointment">
                                                                <i class="uil uil-times-circle"></i>
                                                            </button>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="row text-center">
                            <div class="col-12 mt-4">
                                <c:if test="${CURRENT_PAGE > 1}">
                                    <div class="table-pagination">
                                        <ul>
                                            <c:if test="${CURRENT_PAGE > 1}">
                                                <li class="pagination-button button-prev">
                                                    <a href="${pageContext.request.contextPath}/admin/dentist-management?page=${CURRENT_PAGE - 1}">
                                                        <i class="fas fa-angle-left"></i> Prev
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${END_PAGE}" var="i">
                                                <li class="pagination-number ${CURRENT_PAGE == i ? "active" : ""}">
                                                    <a href="${pageContext.request.contextPath}/admin/dentist-management?page=${i}" class="pagination-link">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${CURRENT_PAGE < END_PAGE}">
                                                <li class="pagination-button button-next">
                                                    <a href="${pageContext.request.contextPath}/admin/dentist-management?page=${CURRENT_PAGE + 1}">
                                                        Next <i class="fas fa-angle-right"></i>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </c:if>
                            </div>
                        </div>
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
        <script src=".././js/employee/Appointment.js"></script>
    </body>
</html>
