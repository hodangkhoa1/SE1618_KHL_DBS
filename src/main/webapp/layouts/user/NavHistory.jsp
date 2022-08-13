<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="heading_history mb-3">
    <h2 class="mb-4 history__title">Service booking history</h2>
    <div class="history__click d-none d-md-block">
        <a href="${pageContext.request.contextPath}/history-booking-all" class="all active">
            All (<span>${COUNT_ALL_BOOKING != null ? COUNT_ALL_BOOKING : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/history-booking" class="all">
            Booking (<span>${COUNT_PROCESS_BOOKING != null ? COUNT_PROCESS_BOOKING : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/history-booking-confirmed" class="all">
            Booking confirmed (<span>${COUNT_CONFIRMED_BOOKING != null ? COUNT_CONFIRMED_BOOKING : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/history-booking-completed" class="all">
            Booking completed (<span>${COUNT_COMPLETED_BOOKING != null ? COUNT_COMPLETED_BOOKING : 0}</span>)
        </a>

        <a href="${pageContext.request.contextPath}/history-booking-cancelled" class="all">
            Cancel booking (<span>${COUNT_CANCEL_BOOKING != null ? COUNT_CANCEL_BOOKING : 0}</span>)
        </a>
    </div>

    <div class="history__link d-block d-md-none">
        <div class="cover">
            <div class="link--cover">
                <a href="${pageContext.request.contextPath}/history-booking-all">
                    All (<span>${COUNT_ALL_BOOKING != null ? COUNT_ALL_BOOKING : 0}</span>)
                </a>
            </div>

            <div class="link--cover">
                <a href="${pageContext.request.contextPath}/history-booking">
                    Booking (<span>${COUNT_PROCESS_BOOKING != null ? COUNT_PROCESS_BOOKING : 0}</span>)
                </a>
            </div>

            <div class="link--cover">
                <a href="${pageContext.request.contextPath}/history-booking-completed">
                    Booking confirmed (<span>${COUNT_CONFIRMED_BOOKING != null ? COUNT_CONFIRMED_BOOKING : 0}</span>)
                </a>
            </div>
        </div>

        <div class="cover">
            <div class="link--cover">
                <a href="${pageContext.request.contextPath}/history-booking-completed">
                    Booking completed (<span>${COUNT_COMPLETED_BOOKING != null ? COUNT_COMPLETED_BOOKING : 0}</span>)
                </a>
            </div>

            <div class="link--cover">
                <a href="${pageContext.request.contextPath}/history-booking-cancelled">
                    Cancel booking (<span>${COUNT_CANCEL_BOOKING != null ? COUNT_CANCEL_BOOKING : 0}</span>)
                </a>
            </div>
        </div>
    </div>
</div>