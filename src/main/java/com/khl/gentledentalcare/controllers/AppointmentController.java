package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.BookingFacade;
import com.khl.gentledentalcare.dbo.BookingServiceFacade;
import com.khl.gentledentalcare.dbo.HospitalFacade;
import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.dbo.ServiceSlotFacade;
import com.khl.gentledentalcare.dbo.SlotFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.Appointment;
import com.khl.gentledentalcare.models.Booking;
import com.khl.gentledentalcare.models.BookingService;
import com.khl.gentledentalcare.models.Hospital;
import com.khl.gentledentalcare.models.ServiceSlot;
import com.khl.gentledentalcare.models.Services;
import com.khl.gentledentalcare.models.Slot;
import java.io.IOException;
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

public class AppointmentController extends HttpServlet {

    private static final String APPOINTMENT_LIST = "APPOINTMENT_LIST";
    private static final String TOTAL_APPOINTMENT = "TOTAL_APPOINTMENT";
    private static final String COUNT_ALL_APPOINTMENT = "COUNT_ALL_APPOINTMENT";
    private static final String COUNT_TOTAL_PENDING_APPOINTMENT = "COUNT_TOTAL_PENDING_APPOINTMENT";
    private static final String COUNT_TOTAL_CONFIRM_APPOINTMENT = "COUNT_TOTAL_CONFIRM_APPOINTMENT";
    private static final String COUNT_TOTAL_COMPLETED_APPOINTMENT = "COUNT_TOTAL_COMPLETED_APPOINTMENT";
    private static final String COUNT_TOTAL_CANCEL_APPOINTMENT = "COUNT_TOTAL_CANCEL_APPOINTMENT";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String LINK_PAGING = "LINK_PAGING";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();

            Appointment appointment;
            Hospital hospital;
            Account getAccount;
            Account account = new Account();
            Date bookingDate = null;

            BookingFacade bookingFacade = new BookingFacade();
            BookingServiceFacade bookingServiceFacade = new BookingServiceFacade();
            AccountFacade accountFacade = new AccountFacade();
            HospitalFacade hospitalFacade = new HospitalFacade();
            ServiceFacade serviceFacade = new ServiceFacade();
            SlotFacade slotFacade = new SlotFacade();
            ServiceSlotFacade serviceSlotFacade = new ServiceSlotFacade();
            BookingService bookingService = new BookingService();
            Booking booking = new Booking();

            List<Appointment> appointmentList = new ArrayList<>();
            List<BookingService> bookingServiceList;

            if (urlServlet.equals("/employee/appointment/all")) {
                String indexPage = request.getParameter("page");
                String bookingID = request.getParameter("BookingID");
                
                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);

                if (bookingID != null) {
                    String action = request.getParameter("Action");

                    booking.setBookingID(bookingID);

                    if (action.equals("Accept")) {
                        booking.setBookingStatus(1);
                    } else if (action.equals("Done")) {
                        booking.setBookingStatus(3);
                    } else {
                        booking.setBookingStatus(2);
                    }
                    bookingFacade.updateBooking(booking, "UpdateStatusInEmployee");
                } else {
                    int countAppointment = bookingFacade.countBooking(booking.getBookingStatus(), "CountBookingWithStatusAppointment", null);
                    int endPage = countAppointment / 5;
                    if (countAppointment % 5 != 0) {
                        endPage++;
                    }
                    
                    List<Booking> bookingList = bookingFacade.getAllBooking("GetPagingBooking", index, null);

                    if (!bookingList.isEmpty()) {
                        for (int i = 0; i < bookingList.size(); i++) {
                            account.setUserID(bookingList.get(i).getUserID());
                            bookingService.setBookingID(bookingList.get(i).getBookingID());

                            List<Services> servicesList = new ArrayList<>();
                            List<ServiceSlot> serviceSlotList = new ArrayList<>();
                            List<Slot> slotList = new ArrayList<>();

                            getAccount = accountFacade.checkAccount(account, "GetAccountEmployee");
                            hospital = hospitalFacade.getHospital(bookingList.get(i).getHospitalID());
                            bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");

                            for (BookingService getBookingService : bookingServiceList) {
                                bookingDate = getBookingService.getBookingDate();

                                servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));
                                serviceSlotList.add(serviceSlotFacade.getServiceSlot(getBookingService.getSlotServiceID(), "GetServiceSlotWithId"));
                            }

                            for (ServiceSlot serviceSlot : serviceSlotList) {
                                slotList.add(slotFacade.getSlot(serviceSlot.getSlotID(), "GetSlotWithID"));
                            }

                            appointment = new Appointment(bookingList.get(i).getBookingID(), getAccount.getUserID(), getAccount.getFullName(), getAccount.getImageAvatar(), hospital.getHospitalName(), servicesList, slotList, bookingDate, bookingList.get(i).getBookingStatus());
                            appointmentList.add(appointment);
                        }
                    }

                    if (appointmentList.isEmpty()) {
                        request.setAttribute(APPOINTMENT_LIST, null);
                    } else {
                        Collections.sort(appointmentList, new Comparator<Appointment>() {
                            @Override
                            public int compare(Appointment appointment1, Appointment appointment2) {
                                return appointment1.getBookingStatus() - appointment2.getBookingStatus();
                            }
                        });
                        request.setAttribute(APPOINTMENT_LIST, appointmentList);
                    }

                    request.setAttribute(LINK_PAGING, "/employee/appointment/all");
                    request.setAttribute(TOTAL_APPOINTMENT, bookingFacade.countBooking(null, "CountAllBooking", null));
                    request.setAttribute(COUNT_ALL_APPOINTMENT, bookingFacade.countBooking(null, "CountAllBooking", null));
                    request.setAttribute(COUNT_TOTAL_PENDING_APPOINTMENT, bookingFacade.countBooking("0", "CountBookingWithStatusAppointment", null));
                    request.setAttribute(COUNT_TOTAL_CONFIRM_APPOINTMENT, bookingFacade.countBooking("1", "CountBookingWithStatusAppointment", null));
                    request.setAttribute(COUNT_TOTAL_CANCEL_APPOINTMENT, bookingFacade.countBooking("2", "CountBookingWithStatusAppointment", null));
                    request.setAttribute(COUNT_TOTAL_COMPLETED_APPOINTMENT, bookingFacade.countBooking("3", "CountBookingWithStatusAppointment", null));

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/employee/Appointment.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                switch (urlServlet) {
                    case "/employee/appointment/pending":
                        booking.setBookingStatus(0);
                        request.setAttribute(LINK_PAGING, "/employee/appointment/pending");
                        break;
                    case "/employee/appointment/confirm":
                        booking.setBookingStatus(1);
                        request.setAttribute(LINK_PAGING, "/employee/appointment/confirm");
                        break;
                    case "/employee/appointment/cancel":
                        booking.setBookingStatus(2);
                        request.setAttribute(LINK_PAGING, "/employee/appointment/cancel");
                        break;
                    case "/employee/appointment/completed":
                        booking.setBookingStatus(3);
                        request.setAttribute(LINK_PAGING, "/employee/appointment/completed");
                        break;
                }

                String indexPage = request.getParameter("page");
                String bookingID = request.getParameter("BookingID");
                
                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);

                if (bookingID != null) {
                    String action = request.getParameter("Action");

                    booking.setBookingID(bookingID);

                    if (action.equals("Accept")) {
                        booking.setBookingStatus(1);
                    } else {
                        booking.setBookingStatus(2);
                    }
                    bookingFacade.updateBooking(booking, "UpdateStatusInEmployee");
                } else {
                    int countAppointment = bookingFacade.countBooking(booking.getBookingStatus(), "CountBookingWithStatusAppointment", null);
                    int endPage = countAppointment / 5;
                    if (countAppointment % 5 != 0) {
                        endPage++;
                    }
                    
                    List<Booking> bookingList = bookingFacade.getAllBooking("GetPagingAppointmentWithStatus", index, booking);

                    if (!bookingList.isEmpty()) {
                        for (int i = 0; i < bookingList.size(); i++) {
                            account.setUserID(bookingList.get(i).getUserID());
                            bookingService.setBookingID(bookingList.get(i).getBookingID());

                            List<Services> servicesList = new ArrayList<>();
                            List<ServiceSlot> serviceSlotList = new ArrayList<>();
                            List<Slot> slotList = new ArrayList<>();

                            getAccount = accountFacade.checkAccount(account, "GetAccountEmployee");
                            hospital = hospitalFacade.getHospital(bookingList.get(i).getHospitalID());
                            bookingServiceList = bookingServiceFacade.getAllBookingService(bookingService, "GetBookingServiceWithID");

                            for (BookingService getBookingService : bookingServiceList) {
                                bookingDate = getBookingService.getBookingDate();

                                servicesList.add(serviceFacade.getServicesDetail(getBookingService.getServiceID()));
                                serviceSlotList.add(serviceSlotFacade.getServiceSlot(getBookingService.getSlotServiceID(), "GetServiceSlotWithId"));
                            }

                            for (ServiceSlot serviceSlot : serviceSlotList) {
                                slotList.add(slotFacade.getSlot(serviceSlot.getSlotID(), "GetSlotWithID"));
                            }

                            appointment = new Appointment(bookingList.get(i).getBookingID(), getAccount.getUserID(), getAccount.getFullName(), getAccount.getImageAvatar(), hospital.getHospitalName(), servicesList, slotList, bookingDate, bookingList.get(i).getBookingStatus());
                            appointmentList.add(appointment);
                        }
                    }

                    if (appointmentList.isEmpty()) {
                        request.setAttribute(APPOINTMENT_LIST, null);
                    } else {
                        Collections.sort(appointmentList, new Comparator<Appointment>() {
                            @Override
                            public int compare(Appointment appointment1, Appointment appointment2) {
                                return appointment1.getBookingStatus() - appointment2.getBookingStatus();
                            }
                        });
                        request.setAttribute(APPOINTMENT_LIST, appointmentList);
                    }

                    request.setAttribute(END_PAGE, endPage);
                    request.setAttribute(CURRENT_PAGE, index);
                    request.setAttribute(TOTAL_APPOINTMENT, bookingFacade.countBooking(null, "CountAllBooking", null));
                    request.setAttribute(COUNT_ALL_APPOINTMENT, bookingFacade.countBooking(null, "CountAllBooking", null));
                    request.setAttribute(COUNT_TOTAL_PENDING_APPOINTMENT, bookingFacade.countBooking("0", "CountBookingWithStatusAppointment", null));
                    request.setAttribute(COUNT_TOTAL_CONFIRM_APPOINTMENT, bookingFacade.countBooking("1", "CountBookingWithStatusAppointment", null));
                    request.setAttribute(COUNT_TOTAL_CANCEL_APPOINTMENT, bookingFacade.countBooking("2", "CountBookingWithStatusAppointment", null));
                    request.setAttribute(COUNT_TOTAL_COMPLETED_APPOINTMENT, bookingFacade.countBooking("3", "CountBookingWithStatusAppointment", null));

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/employee/Appointment.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
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
