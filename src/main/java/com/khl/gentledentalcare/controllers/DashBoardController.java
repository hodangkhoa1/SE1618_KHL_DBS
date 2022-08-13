package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.BookingFacade;
import com.khl.gentledentalcare.dbo.FeedBackFacade;
import com.khl.gentledentalcare.dbo.ViewerFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class DashBoardController extends HttpServlet {
    
    private static final String TOTAL_VIEWER = "TOTAL_VIEWER";
    private static final String TOTAL_FEEDBACK = "TOTAL_FEEDBACK";
    private static final String TOTAL_BOOKING = "TOTAL_BOOKING";
    private static final String TOTAL_ACCOUNT = "TOTAL_ACCOUNT";
    private static final String STATISTIC_BOOKING = "STATISTIC_BOOKING";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            ViewerFacade viewerFacade = new ViewerFacade();
            FeedBackFacade feedBackFacade = new FeedBackFacade();
            BookingFacade bookingFacade = new BookingFacade();
            AccountFacade accountFacade = new AccountFacade();
            Date date = new Date();
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("yyyy");
            String format = simpleDateFormat.format(date);
            
            HashMap<Integer, Integer> statisticBookingList = bookingFacade.statisticBooking(format);
            int[] statistic = new int[12];
            List<Integer> statisticList = new ArrayList<>();

            for (Integer integer : statisticBookingList.keySet()) {
                statistic[integer - 1] = statisticBookingList.get(integer);
            }

            for (int i : statistic) {
                statisticList.add(i);
            }
            
            JSONArray jsArray = new JSONArray(statisticList);
            request.setAttribute(STATISTIC_BOOKING, jsArray.toString());
            request.setAttribute(TOTAL_VIEWER, viewerFacade.getViewer());
            request.setAttribute(TOTAL_FEEDBACK, feedBackFacade.countFeedBack());
            request.setAttribute(TOTAL_BOOKING, bookingFacade.countBooking(null, "CountAllBooking", null));
            request.setAttribute(TOTAL_ACCOUNT, accountFacade.countAccount("0", "GetTotalAccountWithRole"));
            
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/DashBoard.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | SQLException | ServletException e) {
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
