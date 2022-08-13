package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.BookingFacade;
import com.khl.gentledentalcare.dbo.BookingServiceFacade;
import com.khl.gentledentalcare.dbo.HospitalFacade;
import com.khl.gentledentalcare.dbo.NotificationFacade;
import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.dbo.ServiceSlotFacade;
import com.khl.gentledentalcare.dbo.SlotFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.Booking;
import com.khl.gentledentalcare.models.BookingError;
import com.khl.gentledentalcare.models.BookingService;
import com.khl.gentledentalcare.models.Hospital;
import com.khl.gentledentalcare.models.Notification;
import com.khl.gentledentalcare.models.ServiceSlot;
import com.khl.gentledentalcare.models.Services;
import com.khl.gentledentalcare.models.Slot;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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

public class BookingController extends HttpServlet {

    private static final String HOSPITAL_LIST = "HOSPITAL_LIST";
    private static final String SERVICES_LIST = "SERVICES_LIST";
    private static final String BOOKING_ERROR = "BOOKING_ERROR";

    private void returnPrintWriter(List<Slot> slotList, PrintWriter printWriter) {
        for (Slot slot : slotList) {
            printWriter.println("<input type=\"radio\" class=\"btn-check\" name=\"options-outlined\" id=\"success-outlined\" value=\"" + slot.getSlotStart() + "\" onchange=\"change_Book(this,'buttonSubmit','selService','dateService')\">\n"
                    + "                                <label id=\"slot\" class=\"btn btn-outline-primary\" for=\"success-outlined\">" + slot.getSlotStart() + "</label>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String dataService = request.getParameter("DataService");
            String dataDate = request.getParameter("DataDate");

            HospitalFacade hospitalFacade = new HospitalFacade();
            ServiceFacade serviceFacade = new ServiceFacade();
            BookingServiceFacade bookingServiceFacade = new BookingServiceFacade();
            ServiceSlotFacade serviceSlotFacade = new ServiceSlotFacade();
            SlotFacade slotFacade = new SlotFacade();
            PrintWriter printWriter = response.getWriter();

            if (dataService != null && dataDate != null) {
                BookingService bookingService = new BookingService();
                ServiceSlot serviceSlot = new ServiceSlot();
                List<Slot> slotList = new ArrayList<>();

                bookingService.setServiceID(dataService);
                Date convertDataDate = Date.valueOf(dataDate);
                bookingService.setBookingDate(convertDataDate);

                List<BookingService> bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetSlotWithServiceDate");

                if (bookingServiceList.isEmpty()) {
                    serviceSlot.setServiceID(dataService);
                    List<ServiceSlot> serviceSlotList = serviceSlotFacade.getAllServiceSlot(serviceSlot, "GetAllServiceSlot");

                    for (ServiceSlot getServiceSlot : serviceSlotList) {
                        slotList.add(slotFacade.getSlot(getServiceSlot.getSlotID(), "GetSlotWithID"));
                    }

                    Collections.sort(slotList, new Comparator<Slot>() {
                        @Override
                        public int compare(Slot slot1, Slot slot2) {
                            return slot1.getSlotStart().compareTo(slot2.getSlotStart());
                        }
                    });
                    returnPrintWriter(slotList, printWriter);
                } else {
                    for (BookingService getBookingService : bookingServiceList) {
                        serviceSlot.setServiceID(getBookingService.getServiceID());
                    }

                    List<ServiceSlot> serviceSlotList = serviceSlotFacade.getAllServiceSlot(serviceSlot, "GetServiceSlotEmpty");

                    for (ServiceSlot getServiceSlot : serviceSlotList) {
                        slotList.add(slotFacade.getSlot(getServiceSlot.getSlotID(), "GetSlotWithID"));
                    }

                    Collections.sort(slotList, new Comparator<Slot>() {
                        @Override
                        public int compare(Slot slot1, Slot slot2) {
                            return slot1.getSlotStart().compareTo(slot2.getSlotStart());
                        }
                    });
                    returnPrintWriter(slotList, printWriter);
                }

            } else {
                List<Hospital> hospitalList = hospitalFacade.getAllHospital(null, "GetAllHospital", "0");
                List<Services> servicesList = serviceFacade.getServices("0", "GetAllService");

                if (hospitalList.isEmpty()) {
                    request.setAttribute(HOSPITAL_LIST, null);
                } else {
                    request.setAttribute(HOSPITAL_LIST, hospitalList);
                }

                if (servicesList.isEmpty()) {
                    request.setAttribute(SERVICES_LIST, null);
                } else {
                    request.setAttribute(SERVICES_LIST, servicesList);
                }

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/Booking.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String bookingID = FunctionRandom.randomID(10);
            String countBookService = request.getParameter("CountBookService");
            int indexBookService;

            if (countBookService.equals("")) {
                indexBookService = 0;
            } else {
                indexBookService = Integer.parseInt(countBookService);
            }

            String hospital = request.getParameter("hospital");
            HttpSession session = request.getSession();
            Account getAccount = (Account) session.getAttribute("LOGIN_USER");

            BookingFacade bookingFacade = new BookingFacade();
            BookingServiceFacade bookingServiceFacade = new BookingServiceFacade();
            Booking booking = new Booking();
            BookingError bookingError = new BookingError();
            BookingService bookingService = new BookingService();
            NotificationFacade notificationFacade = new NotificationFacade();
            Notification notification = new Notification();
            SlotFacade slotFacade = new SlotFacade();
            ServiceSlotFacade serviceSlotFacade = new ServiceSlotFacade();
            HospitalFacade hospitalFacade = new HospitalFacade();
            ServiceFacade serviceFacade = new ServiceFacade();
            boolean hasError = false;

            if (hospital == null) {
                hasError = true;
                bookingError.setHospitalError("Please select hospital!");
            } else if (indexBookService == 0) {
                hasError = true;
                bookingError.setServiceSlotError("Please select service slot!");
            } else {
                for (int i = 0; i < indexBookService; i++) {
                    if (request.getParameter("service" + i) == null && request.getParameter("slot" + i) == null && request.getParameter("date" + i) == null) {
                        hasError = true;
                        bookingError.setServiceSlotError("Please select service slot!");
                    }
                }
            }

            if (hasError) {
                request.setAttribute(BOOKING_ERROR, bookingError);
                List<Hospital> hospitalList = hospitalFacade.getAllHospital(null, "GetAllHospital", "0");
                List<Services> servicesList = serviceFacade.getServices("0", "GetAllService");

                if (hospitalList.isEmpty()) {
                    request.setAttribute(HOSPITAL_LIST, null);
                } else {
                    request.setAttribute(HOSPITAL_LIST, hospitalList);
                }

                if (servicesList.isEmpty()) {
                    request.setAttribute(SERVICES_LIST, null);
                } else {
                    request.setAttribute(SERVICES_LIST, servicesList);
                }
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/Booking.jsp");
                requestDispatcher.forward(request, response);
            } else {
                booking.setBookingID(bookingID);
                if (getAccount != null) {
                    booking.setUserID(getAccount.getUserID());
                }
                booking.setHospitalID(hospital);
                bookingFacade.addBooking(booking);

                bookingService.setBookingID(bookingID);

                for (int i = 0; i < indexBookService; i++) {
                    bookingService.setBookingServiceID(FunctionRandom.randomID(10));
                    if (request.getParameter("service" + i) != null) {
                        bookingService.setServiceID(request.getParameter("service" + i));
                    }
                    if (request.getParameter("slot" + i) != null) {
                        Slot slot = slotFacade.getSlot(request.getParameter("slot" + i), "GetSlotWithTime");
                        ServiceSlot serviceSlot = serviceSlotFacade.getServiceSlot(slot.getSlotID(), "GetServiceSlotWithSlotID");
                        bookingService.setSlotServiceID(serviceSlot.getSlotServiceID());
                    }
                    if (request.getParameter("date" + i) != null) {
                        String dateSlot = request.getParameter("date" + i);
                        Date convertDateSlot = Date.valueOf(dateSlot);
                        bookingService.setBookingDate(convertDateSlot);
                    }

                    bookingServiceFacade.addBookingService(bookingService);
                }

//                if (checkAddBooking) {
//                    notification.setNotifyID(FunctionRandom.randomID(10));
//                    notification.setUserID(booking.getUserID());
//                    notification.setNotifyType("BookingSuccessful");
//                    notificationFacade.addNotification(notification);
//                }
                response.sendRedirect(request.getContextPath() + "/history-booking");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
