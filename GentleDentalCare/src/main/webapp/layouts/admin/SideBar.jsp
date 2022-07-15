<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="sidebar">
    <a href="${pageContext.request.contextPath}/admin/dashboard" class="brand">
        <img src=".././images/favicon-100x100.png" alt="">
        <div>Gentle Dental Care</div>
    </a>
    <ul class="side-menu">
        <hr>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link active">
                <i class='bx bxs-dashboard icon'></i> Dashboard
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/customer-management" class="nav-link">
                <i class="fa-solid fa-user icon"></i> Customers
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/employee-management" class="nav-link">
                <i class="fa-solid fa-user-tie icon"></i> Employee
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/dentist-management" class="nav-link">
                <i class="fa-solid fa-user-doctor icon"></i> Dentist
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/hospital-management" class="nav-link">
                <i class="fa-solid fa-house-medical icon"></i> Hospital
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/service-management" class="nav-link">
                <i class="fa-solid fa-screwdriver icon"></i> Service
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/news-management" class="nav-link">
                <i class="fa-solid fa-newspaper icon"></i> News
            </a>
        </li>
        <c:if test="${NAV_BAR_PROFILE != null}">
            <hr>
            <li class="nav-item">
                <a href="${ACTION_URL}" class="nav-link">
                    ${NAV_BAR_ICON} ${BUTTON_ACTION}
                </a>
            </li>
        </c:if>
    </ul>
</section>