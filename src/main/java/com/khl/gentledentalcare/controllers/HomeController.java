package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.DentistFacade;
import com.khl.gentledentalcare.dbo.ViewerFacade;
import com.khl.gentledentalcare.models.Dentist;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class HomeController extends HttpServlet {
    
    private static final String DENTIST_LIST = "DENTIST_LIST";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            ViewerFacade viewerFacade = new ViewerFacade();
            DentistFacade dentistFacade = new DentistFacade();
            
            List<Dentist> dentistList = dentistFacade.getAllDentist(null, "GetTopDentist");
            
            if (dentistList.isEmpty()) {
                request.setAttribute(DENTIST_LIST, null);
            } else {
                request.setAttribute(DENTIST_LIST, dentistList);
            }

            if (session.isNew()) {
                viewerFacade.updateViewer();
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/Home.jsp");
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
