<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="menu-bar">
    <i class='bx bx-menu toggle-sidebar'></i>
    <form action="#">
        <div class="form-group">
            <input ng-model="${SEARCH}" type="text" placeholder="Search...">
            <i class='bx bx-search icon'></i>
        </div>
    </form>
    <div class="profile">
        <div onclick="ProfileDropdown()" class="profile-image" style="background-color: ${sessionScope.LOGIN_ADMIN.colorAvatar};">
            <c:if test="${sessionScope.LOGIN_ADMIN.imageAvatar != null}">
                <img src="data:image/png;base64,${sessionScope.LOGIN_ADMIN.imageAvatar}" alt="">
            </c:if>
            <c:if test="${sessionScope.LOGIN_ADMIN.imageAvatar == null}">
                <p>${sessionScope.LOGIN_ADMIN.defaultAvatar.toUpperCase()}</p>
            </c:if>
        </div>
        <ul class="profile-link">
            <li>
                <a href="${pageContext.request.contextPath}/admin/info-profile">
                    <i class='bx bxs-user-circle icon'></i> Profile
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/logout">
                    <i class='bx bxs-log-out-circle'></i> Logout
                </a>
            </li>
        </ul>
    </div>
</nav>