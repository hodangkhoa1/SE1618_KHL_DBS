package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.DentistFacade;
import com.khl.gentledentalcare.models.Dentist;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class DentistManagementController extends HttpServlet {

    private static final String DENTIST_LIST = "DENTIST_LIST";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String indexPage = request.getParameter("page");

            DentistFacade dentistFacade = new DentistFacade();

            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            List<Dentist> dentistList;

            int countDentist = dentistFacade.countDentist();
            int endPage = countDentist / 5;
            if (countDentist % 5 != 0) {
                endPage++;
            }

            dentistList = dentistFacade.getAllDentist(index, "PagingDentist");
            if (dentistList.isEmpty()) {
                request.setAttribute(DENTIST_LIST, null);
            } else {
                JSONArray jsArray = new JSONArray(dentistList);
                request.setAttribute(DENTIST_LIST, jsArray.toString());
            }

            request.setAttribute(END_PAGE, endPage);
            request.setAttribute(CURRENT_PAGE, index);

            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/DentistManagement.jsp");
            requestDispatcher.forward(request, response);
            
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
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
