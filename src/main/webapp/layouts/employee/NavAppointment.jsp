<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="heading_history mb-0 mb-lg-3">
    <h2 class="mb-4 history__title">List of dental appointments</h2>
    <div class="history__click d-none d-md-block">
        <a href="${pageContext.request.contextPath}/employee/appointment/all" class="all active">
            All (<span>${COUNT_ALL_APPOINTMENT != null ? COUNT_ALL_APPOINTMENT : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/employee/appointment/pending" class="all">
            Pending (<span>${COUNT_TOTAL_PENDING_APPOINTMENT != null ? COUNT_TOTAL_PENDING_APPOINTMENT : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/employee/appointment/confirm" class="all">
            Confirm (<span>${COUNT_TOTAL_CONFIRM_APPOINTMENT != null ? COUNT_TOTAL_CONFIRM_APPOINTMENT : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/employee/appointment/completed" class="all">
            Completed (<span>${COUNT_TOTAL_COMPLETED_APPOINTMENT != null ? COUNT_TOTAL_COMPLETED_APPOINTMENT : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/employee/appointment/cancel" class="all">
            Cancel (<span>${COUNT_TOTAL_CANCEL_APPOINTMENT != null ? COUNT_TOTAL_CANCEL_APPOINTMENT : 0}</span>)
        </a>
    </div>

    <div class="history__link d-block d-md-none pb-2">
        <div class="cover">
            <a href="${pageContext.request.contextPath}/employee/appointment/all" class="active">
                All (<span>${COUNT_ALL_APPOINTMENT != null ? COUNT_ALL_APPOINTMENT : 0}</span>)
            </a>

            <a href="${pageContext.request.contextPath}/employee/appointment/pending">
                Pending (<span>${COUNT_TOTAL_PENDING_APPOINTMENT != null ? COUNT_TOTAL_PENDING_APPOINTMENT : 0}</span>)
            </a>

            <a href="${pageContext.request.contextPath}/employee/appointment/confirm">
                Confirm (<span>${COUNT_TOTAL_CONFIRM_APPOINTMENT != null ? COUNT_TOTAL_CONFIRM_APPOINTMENT : 0}</span>)
            </a>

        </div>

        <div class="cover">
            <a href="${pageContext.request.contextPath}/employee/appointment/completed">
                Completed (<span>${COUNT_TOTAL_COMPLETED_APPOINTMENT != null ? COUNT_TOTAL_COMPLETED_APPOINTMENT : 0}</span>)
            </a>

            <a href="${pageContext.request.contextPath}/employee/appointment/cancel">
                Cancel (<span>${COUNT_TOTAL_CANCEL_APPOINTMENT != null ? COUNT_TOTAL_CANCEL_APPOINTMENT : 0}</span>)
            </a>
        </div>
    </div>
</div>