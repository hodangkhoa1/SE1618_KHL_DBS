package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.BookingFacade;
import com.khl.gentledentalcare.dbo.BookingServiceFacade;
import com.khl.gentledentalcare.dbo.FeedBackFacade;
import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.dbo.ServiceSlotFacade;
import com.khl.gentledentalcare.dbo.SlotFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.Booking;
import com.khl.gentledentalcare.models.BookingService;
import com.khl.gentledentalcare.models.FeedBack;
import com.khl.gentledentalcare.models.HistoryBooking;
import com.khl.gentledentalcare.models.ServiceSlot;
import com.khl.gentledentalcare.models.Services;
import com.khl.gentledentalcare.models.Slot;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HistoryBookingController extends HttpServlet {

    private static final String BOOKING_LIST = "BOOKING_LIST";
    private static final String TOTAL_BOOKING_LIST = "TOTAL_BOOKING_LIST";
    private static final String COUNT_ALL_BOOKING = "COUNT_ALL_BOOKING";
    private static final String COUNT_PROCESS_BOOKING = "COUNT_PROCESS_BOOKING";
    private static final String COUNT_COMPLETED_BOOKING = "COUNT_COMPLETED_BOOKING";
    private static final String COUNT_CONFIRMED_BOOKING = "COUNT_CONFIRMED_BOOKING";
    private static final String COUNT_CANCEL_BOOKING = "COUNT_CANCEL_BOOKING";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FEEDBACK = "FEEDBACK";
    private static final String LOGIN_USER = "LOGIN_USER";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();
            String userID = "", fullName = "", phoneNumber = "", address = "";

            HttpSession session = request.getSession();
            PrintWriter printWriter = response.getWriter();
            Account getAccount = (Account) session.getAttribute(LOGIN_USER);

            AccountFacade accountFacade = new AccountFacade();
            BookingFacade bookingFacade = new BookingFacade();
            BookingServiceFacade bookingServiceFacade = new BookingServiceFacade();
            ServiceFacade serviceFacade = new ServiceFacade();
            SlotFacade slotFacade = new SlotFacade();
            ServiceSlotFacade serviceSlotFacade = new ServiceSlotFacade();
            Booking booking = new Booking();
            FeedBackFacade feedBackFacade = new FeedBackFacade();

            List<HistoryBooking> historyBookingList = new ArrayList<>();
            List<Booking> bookingList;

            HistoryBooking historyBooking;
            BookingService bookingService;

            booking.setUserID(getAccount.getUserID());

            Account account = accountFacade.checkAccount(getAccount, "GetAccountEmployee");
            if (account != null) {
                userID = account.getUserID();
                fullName = account.getFullName();
                phoneNumber = account.getUserPhone();
                address = account.getUserAddress();
            }

            if (urlServlet.equals("/history-booking-all")) {
                String bookingAmount = request.getParameter("BookingAmount");
                String getBookingID = request.getParameter("BookingID");
                String getUserID = request.getParameter("PatientID");

                if (bookingAmount != null) {
                    int bookingAmountInt = Integer.parseInt(bookingAmount);

                    bookingList = bookingFacade.getAllBooking("GetNext5BookingWithUser", bookingAmountInt, booking);

                    for (int i = 0; i < bookingList.size(); i++) {
                        bookingService = new BookingService(null, null, bookingList.get(i).getBookingID(), null, null);

                        List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");
                        List<Services> servicesList = new ArrayList<>();
                        List<Slot> slotList = new ArrayList<>();
                        List<Date> dateList = new ArrayList<>();

                        for (BookingService getBookingService : bookingServiceList) {
                            dateList.add(getBookingService.getBookingDate());
                            servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));

                            ServiceSlot serviceSlot = serviceSlotFacade.getServiceSlot(getBookingService.getSlotServiceID(), "GetServiceSlotWithId");
                            slotList.add(slotFacade.getSlot(serviceSlot.getSlotID(), "GetSlotWithID"));
                        }

                        historyBooking = new HistoryBooking(bookingList.get(i).getBookingID(), userID, fullName, phoneNumber, address, bookingList.get(i).getBookingNote(), servicesList, slotList, dateList, bookingList.get(i).getBookingStatus(), bookingList.get(i).getBookingCreated());
                        historyBookingList.add(historyBooking);
                    }

                    if (!historyBookingList.isEmpty()) {
                        returnPrintWriter(historyBookingList, printWriter, request);
                    }
                } else if (getBookingID != null && getUserID != null) {
                    Booking updateBooking = new Booking();
                    updateBooking.setBookingID(getBookingID);
                    updateBooking.setUserID(getUserID);
                    updateBooking.setBookingStatus(2);

                    bookingFacade.updateBooking(updateBooking, "UpdateStatusInUser");
                } else {
                    bookingList = bookingFacade.getAllBooking("GetTop5BookingWithUser", null, booking);

                    if (bookingList.isEmpty()) {
                        request.setAttribute(BOOKING_LIST, null);
                    } else {
                        for (int i = 0; i < bookingList.size(); i++) {
                            bookingService = new BookingService(null, null, bookingList.get(i).getBookingID(), null, null);

                            List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");
                            List<Services> servicesList = new ArrayList<>();
                            List<Slot> slotList = new ArrayList<>();
                            List<Date> dateList = new ArrayList<>();

                            for (BookingService getBookingService : bookingServiceList) {
                                dateList.add(getBookingService.getBookingDate());
                                servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));

                                ServiceSlot serviceSlot = serviceSlotFacade.getServiceSlot(getBookingService.getSlotServiceID(), "GetServiceSlotWithId");
                                slotList.add(slotFacade.getSlot(serviceSlot.getSlotID(), "GetSlotWithID"));
                            }

                            historyBooking = new HistoryBooking(bookingList.get(i).getBookingID(), userID, fullName, phoneNumber, address, bookingList.get(i).getBookingNote(), servicesList, slotList, dateList, bookingList.get(i).getBookingStatus(), bookingList.get(i).getBookingCreated());
                            historyBookingList.add(historyBooking);
                        }

                        Collections.sort(historyBookingList, new Comparator<HistoryBooking>() {
                            @Override
                            public int compare(HistoryBooking historyBooking1, HistoryBooking historyBooking2) {
                                return historyBooking1.getBookingCreated().compareTo(historyBooking2.getBookingCreated());
                            }
                        });
                        request.setAttribute(BOOKING_LIST, historyBookingList);
                    }

                    request.setAttribute(COUNT_ALL_BOOKING, bookingFacade.countBooking(null, "CountBookingWithUserID", booking.getUserID()));
                    request.setAttribute(COUNT_PROCESS_BOOKING, bookingFacade.countBooking("0", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(COUNT_CONFIRMED_BOOKING, bookingFacade.countBooking("1", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(COUNT_CANCEL_BOOKING, bookingFacade.countBooking("2", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(COUNT_COMPLETED_BOOKING, bookingFacade.countBooking("3", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(TOTAL_BOOKING_LIST, bookingFacade.countBooking(null, "CountBookingWithUserID", booking.getUserID()));

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/HistoryBooking.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                switch (urlServlet) {
                    case "/history-booking":
                        booking.setBookingStatus(0);
                        break;
                    case "/history-booking-confirmed":
                        booking.setBookingStatus(1);
                        break;
                    case "/history-booking-cancelled":
                        booking.setBookingStatus(2);
                        break;
                    case "/history-booking-completed":
                        String getServiceID = request.getParameter("ServiceID");
                        String getUserID = request.getParameter("UserID");
                        String getValueRate = request.getParameter("ValueRate");
                        String getFeedbackContent = request.getParameter("FeedbackContent");

                        if (getServiceID != null && getUserID != null && getValueRate != null && getFeedbackContent != null) {
                            String feedbackID = FunctionRandom.randomID(10);

                            FeedBack feedBack = new FeedBack();

                            feedBack.setFeedBackID(feedbackID);
                            feedBack.setBookingID(getServiceID);
                            feedBack.setUserID(getUserID);
                            feedBack.setFeedBackContent(getFeedbackContent);
                            feedBack.setNumberRating(Integer.parseInt(getValueRate));

                            feedBackFacade.addFeedBack(feedBack);
                            request.getSession().removeAttribute(FEEDBACK);
                        } else {
                            booking.setBookingStatus(3);
                        }
                        break;
                }

                String bookingAmount = request.getParameter("BookingAmount");
                String getBookingID = request.getParameter("BookingID");
                String getUserID = request.getParameter("UserID");

                if (bookingAmount != null) {
                    int bookingAmountInt = Integer.parseInt(bookingAmount);

                    bookingList = bookingFacade.getAllBooking("GetNext5BookingWithStatus", bookingAmountInt, booking);

                    for (int i = 0; i < bookingList.size(); i++) {
                        bookingService = new BookingService(null, null, bookingList.get(i).getBookingID(), null, null);

                        List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");
                        List<Services> servicesList = new ArrayList<>();
                        List<Slot> slotList = new ArrayList<>();
                        List<Date> dateList = new ArrayList<>();

                        for (BookingService getBookingService : bookingServiceList) {
                            dateList.add(getBookingService.getBookingDate());
                            servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));

                            ServiceSlot serviceSlot = serviceSlotFacade.getServiceSlot(getBookingService.getSlotServiceID(), "GetServiceSlotWithId");
                            slotList.add(slotFacade.getSlot(serviceSlot.getSlotID(), "GetSlotWithID"));
                        }

                        historyBooking = new HistoryBooking(bookingList.get(i).getBookingID(), userID, fullName, phoneNumber, address, bookingList.get(i).getBookingNote(), servicesList, slotList, dateList, bookingList.get(i).getBookingStatus(), bookingList.get(i).getBookingCreated());
                        historyBookingList.add(historyBooking);
                    }

                    if (!historyBookingList.isEmpty()) {
                        returnPrintWriter(historyBookingList, printWriter, request);
                    }
                } else if (getBookingID != null && getUserID != null) {
                    Booking updateBooking = new Booking();
                    updateBooking.setBookingID(getBookingID);
                    updateBooking.setUserID(getUserID);
                    updateBooking.setBookingStatus(2);

                    bookingFacade.updateBooking(updateBooking, "UpdateStatusInUser");
                } else {
                    bookingList = bookingFacade.getAllBooking("GetTop5BookingWithStatus", null, booking);

                    if (bookingList.isEmpty()) {
                        request.setAttribute(BOOKING_LIST, null);
                    } else {
                        for (int i = 0; i < bookingList.size(); i++) {
                            bookingService = new BookingService(null, null, bookingList.get(i).getBookingID(), null, null);

                            List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");
                            List<Services> servicesList = new ArrayList<>();
                            List<Slot> slotList = new ArrayList<>();
                            List<Date> dateList = new ArrayList<>();

                            for (BookingService getBookingService : bookingServiceList) {
                                dateList.add(getBookingService.getBookingDate());
                                servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));

                                ServiceSlot serviceSlot = serviceSlotFacade.getServiceSlot(getBookingService.getSlotServiceID(), "GetServiceSlotWithId");
                                slotList.add(slotFacade.getSlot(serviceSlot.getSlotID(), "GetSlotWithID"));
                            }

                            if (bookingList.get(i).getBookingStatus() == 3) {
                                if (feedBackFacade.getFeedBack(bookingList.get(i).getBookingID(), bookingList.get(i).getUserID()) == null) {
                                    request.setAttribute(FEEDBACK, bookingList.get(i).getBookingID());
                                }
                            }

                            historyBooking = new HistoryBooking(bookingList.get(i).getBookingID(), userID, fullName, phoneNumber, address, bookingList.get(i).getBookingNote(), servicesList, slotList, dateList, bookingList.get(i).getBookingStatus(), bookingList.get(i).getBookingCreated());
                            historyBookingList.add(historyBooking);
                        }

                        request.setAttribute(BOOKING_LIST, historyBookingList);
                    }

                    request.setAttribute(COUNT_ALL_BOOKING, bookingFacade.countBooking(null, "CountBookingWithUserID", booking.getUserID()));
                    request.setAttribute(COUNT_PROCESS_BOOKING, bookingFacade.countBooking("0", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(COUNT_CONFIRMED_BOOKING, bookingFacade.countBooking("1", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(COUNT_CANCEL_BOOKING, bookingFacade.countBooking("2", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(COUNT_COMPLETED_BOOKING, bookingFacade.countBooking("3", "CountBookingWithStatus", booking.getUserID()));
                    request.setAttribute(TOTAL_BOOKING_LIST, bookingFacade.countBooking(null, "CountBookingWithUserID", booking.getUserID()));

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/HistoryBooking.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void returnPrintWriter(List<HistoryBooking> historyBookingList, PrintWriter printWriter, HttpServletRequest request) {
        for (HistoryBooking historyBooking : historyBookingList) {
            printWriter.println("<div class=\"col-12 history__card mb-4\">\n"
                    + "                    <div class=\"card--top\">\n"
                    + "                        <div class=\"top--content\">\n"
                    + "                            <p>Booking ID: <span class=\"maId\">" + historyBooking.getBookingID() + "</span></p>\n"
                    + "                            <p>|</p>\n"
                    + "                            <p>Book Create: <span>" + simpleDateFormat.format(historyBooking.getBookingCreated()) + "</span></p>\n"
                    + "                        </div>\n"
                    + "                        " + returnButton(historyBooking, request) + "\n"
                    + "                    </div>\n"
                    + "\n"
                    + "                    <div class=\"card--bottom\">\n"
                    + "                        <div class=\"cb__img\">\n"
                    + "                            " + returnImage(historyBooking.getServiceList()) + "\n"
                    + "                        </div>\n"
                    + "\n"
                    + "                        <div class=\"cb__content\">\n"
                    + "                            <p>Full Name: <span class=\"name\">" + historyBooking.getFullName() + "</span></p>\n"
                    + "                            <p>Phone Number: <span>" + historyBooking.getPhoneNumber() + "</span></p>\n"
                    + "                            <p>Address: <span>" + historyBooking.getAddress() + "</span></p>\n"
                    + "                            <p>Service: <span>" + returnNameService(historyBooking.getServiceList()) + "</span></p>\n"
                    + "                            <p>Slot: <span>" + returnSlotTime(historyBooking.getSlotList()) + "</span></p>\n"
                    + "                            <p>Date: <span>" + returnBookingDate(historyBooking.getBookingDateList()) + "</span></p>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"status--cover\">\n"
                    + "                            " + returnStatus(historyBooking.getBookingStatus()) + ""
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>");
        }
    }

    private String returnImage(List<Services> serviceList) {
        String template = "";

        for (Services services : serviceList) {
            template = "<img src=\"data:image/png;base64," + services.getImageService() + "\" alt=\"\">";
        }

        return template;
    }

    private String returnNameService(List<Services> serviceList) {
        String stringTMP = "";

        for (Services services : serviceList) {
            stringTMP = services.getServiceName();
        }

        return stringTMP;
    }

    private String returnSlotTime(List<Slot> slotList) {
        String stringTMP = "";

        for (Slot slot : slotList) {
            stringTMP = slot.getSlotStart().toString();
        }

        return stringTMP;
    }

    private String returnBookingDate(List<Date> dateList) {
        String stringTMP = "";

        for (Date date : dateList) {
            stringTMP = simpleDateFormat.format(date);
        }

        return stringTMP;
    }

    private String returnStatus(int bookingStatus) {
        String template = "";

        switch (bookingStatus) {
            case 0:
                template = "<div class=\"status\" style=\"background-color: green;\">\n"
                        + "                                                    <p>Pending</p>\n"
                        + "                                                </div>";
                break;
            case 1:
                template = "<div class=\"status\">\n"
                        + "                                                    <p>Confirm</p>\n"
                        + "                                                </div>";
                break;
            case 2:
                template = "<div class=\"status cancelled\" style=\"background-color: red;\">\n"
                        + "                                                    <p>Cancelled</p>\n"
                        + "                                                </div>";
                break;
        }

        return template;
    }

    private String returnButton(HistoryBooking historyBooking, HttpServletRequest request) {
        String template = "";

        if (historyBooking.getBookingStatus() == 0) {
            template = "<button class=\"btn-delete\" onclick=\"confirmDelete('" + request.getContextPath() + "/history-booking', '" + historyBooking.getBookingID() + "', '" + historyBooking.getUserId() + "')\">\n"
                    + "                                                <i class=\"fa-solid fa-xmark\"></i>\n"
                    + "                                            </button>";
        }

        return template;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
