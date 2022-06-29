package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.BookingFacade;
import com.khl.gentledentalcare.dbo.BookingServiceFacade;
import com.khl.gentledentalcare.dbo.HospitalFacade;
import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.Booking;
import com.khl.gentledentalcare.models.BookingService;
import com.khl.gentledentalcare.models.Hospital;
import com.khl.gentledentalcare.models.Services;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingController extends HttpServlet {

    private static final String HOSPITAL_LIST = "HOSPITAL_LIST";
    private static final String SERVICES_LIST = "SERVICES_LIST";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HospitalFacade hospitalFacade = new HospitalFacade();
            ServiceFacade serviceFacade = new ServiceFacade();

            List<Hospital> hospitalList = hospitalFacade.getAllHospital(null, "GetAllHospital", "0");
            List<Services> servicesList = serviceFacade.getServices(null, "GetAllService");

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
            String hospital = request.getParameter("hospital");
            String bookingServiceID = FunctionRandom.randomID(10);
            String service = request.getParameter("service");
            String serviceDate = request.getParameter("serviceDate");
            String serviceSlot = request.getParameter("serviceSlot");

            BookingFacade bookingFacade = new BookingFacade();
            BookingServiceFacade bookingServiceFacade = new BookingServiceFacade();

            Account getAccount = (Account) request.getAttribute("LOGIN_USER");
            Booking booking = new Booking();
            BookingService bookingService = new BookingService();

            booking.setBookingID(bookingID);
            if (getAccount != null) {
                booking.setUserID(getAccount.getUserID());
            }
            booking.setHospitalID(hospital);
            bookingService.setBookingServiceID(bookingServiceID);
            bookingService.setServiceID(service);
            bookingService.setBookingID(bookingID);
            bookingService.setSlotServiceID(serviceSlot);
            if (serviceDate != null) {
                Date convertDateOfBirth = Date.valueOf(serviceDate);
                bookingService.setBookingDate(convertDateOfBirth);
            }

            bookingFacade.addBooking(booking);
            bookingServiceFacade.addBookingService(bookingService);

        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
