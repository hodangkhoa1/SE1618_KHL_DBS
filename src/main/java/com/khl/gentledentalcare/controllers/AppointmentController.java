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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointmentController extends HttpServlet {

    private static final String APPOINTMENT_LIST = "APPOINTMENT_LIST";
    private static final String TOTAL_APPOINTMENT = "TOTAL_APPOINTMENT";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String bookingID = request.getParameter("BookingID");

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

            List<Appointment> appointmentList = new ArrayList<>();
            List<BookingService> bookingServiceList;

            if (bookingID != null) {
                String action = request.getParameter("Action");
                Booking booking = new Booking();
                booking.setBookingID(bookingID);

                if (action.equals("Accept")) {
                    booking.setBookingStatus(1);
                } else {
                    booking.setBookingStatus(2);
                }

                bookingFacade.updateBooking(booking);

            } else {
                List<Booking> bookingList = bookingFacade.getAllBooking("GetTop5Booking", null, null);

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
                    request.setAttribute(APPOINTMENT_LIST, appointmentList);
                }

                request.setAttribute(TOTAL_APPOINTMENT, bookingFacade.countBooking(null, "CountAllBooking"));

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/employee/Appointment.jsp");
                requestDispatcher.forward(request, response);
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
