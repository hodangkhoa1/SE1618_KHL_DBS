package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.BookingFacade;
import com.khl.gentledentalcare.dbo.BookingServiceFacade;
import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.Booking;
import com.khl.gentledentalcare.models.BookingService;
import com.khl.gentledentalcare.models.HistoryBooking;
import com.khl.gentledentalcare.models.Services;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HistoryBookingController extends HttpServlet {

    private static final String HISTORY_BOOKING_LIST = "HISTORY_BOOKING_LIST";
    private static final String TOTAL_HISTORY_BOOKING = "TOTAL_HISTORY_BOOKING";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String bookingAmount = request.getParameter("BookingAmount");
            String userID = "", fullName = "", phoneNumber = "", address = "", bookingID;
            int bookingStatus;
            Date bookingDate = null;

            HttpSession session = request.getSession();
            Account getAccount = (Account) session.getAttribute("LOGIN_USER");

            PrintWriter printWriter = response.getWriter();
            AccountFacade accountFacade = new AccountFacade();
            BookingFacade bookingFacade = new BookingFacade();
            BookingServiceFacade bookingServiceFacade = new BookingServiceFacade();
            ServiceFacade serviceFacade = new ServiceFacade();
            HistoryBooking historyBooking;
            BookingService bookingService;
            Booking booking = new Booking();
            booking.setUserID(getAccount.getUserID());

            List<Booking> bookingList;
            List<HistoryBooking> historyBookingList = new ArrayList<>();

            Account account = accountFacade.checkAccount(getAccount, "GetAccountEmployee");
            if (account != null) {
                userID = account.getUserID();
                fullName = account.getFullName();
                phoneNumber = account.getUserPhone();
                address = account.getUserAddress();
            }

            if (bookingAmount != null) {
                int bookingAmountInt = Integer.parseInt(bookingAmount);

                bookingList = bookingFacade.getAllBooking("GetNext5BookingWithUser", bookingAmountInt, booking);

                for (int i = 0; i < bookingList.size(); i++) {
                    bookingID = bookingList.get(i).getBookingID();
                    bookingStatus = bookingList.get(i).getBookingStatus();

                    bookingService = new BookingService(null, null, bookingList.get(i).getBookingID(), null, null);

                    List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");
                    List<Services> servicesList = new ArrayList<>();
                    
                    for (BookingService getBookingService : bookingServiceList) {
                        bookingDate = getBookingService.getBookingDate();
                        servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));
                    }
                    
                    historyBooking = new HistoryBooking(bookingID, userID, fullName, phoneNumber, address, servicesList, bookingStatus, bookingDate);
                    historyBookingList.add(historyBooking);
                }
                
                returnPrintWriter(historyBookingList, printWriter, request);
            } else {
                bookingList = bookingFacade.getAllBooking("GetTop5BookingWithUser", null, booking);

                if (bookingList.isEmpty()) {
                    request.setAttribute(HISTORY_BOOKING_LIST, null);
                } else {
                    for (int i = 0; i < bookingList.size(); i++) {
                        bookingID = bookingList.get(i).getBookingID();
                        bookingStatus = bookingList.get(i).getBookingStatus();

                        bookingService = new BookingService(null, null, bookingList.get(i).getBookingID(), null, null);

                        List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");
                        List<Services> servicesList = new ArrayList<>();
                        
                        for (BookingService getBookingService : bookingServiceList) {
                            bookingDate = getBookingService.getBookingDate();
                            servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));
                        }
                        
                        historyBooking = new HistoryBooking(bookingID, userID, fullName, phoneNumber, address, servicesList, bookingStatus, bookingDate);
                        historyBookingList.add(historyBooking);
                    }

                    request.setAttribute(TOTAL_HISTORY_BOOKING, bookingFacade.countBooking(userID, "CountBookingWithUserID"));
                    request.setAttribute(HISTORY_BOOKING_LIST, historyBookingList);
                }

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/HistoryBooking.jsp");
                requestDispatcher.forward(request, response);
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
                    + "                            <p>Book Date: <span>" + simpleDateFormat.format(historyBooking.getBookingDate()) + "</span></p>\n"
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
