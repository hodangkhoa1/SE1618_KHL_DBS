package com.khl.gentledentalcare.controllers;

import java.io.IOException;
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
public class ErrorController extends HttpServlet {

    private static final String URL_PAGE = "URL_PAGE";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            boolean loginUser = (session != null && session.getAttribute("LOGIN_USER") != null);
            boolean loginAdmin = (session != null && session.getAttribute("LOGIN_ADMIN") != null);
            boolean loginEmployee = (session != null && session.getAttribute("LOGIN_EMPLOYEE") != null);

            if (loginUser) {
                request.setAttribute(URL_PAGE, request.getContextPath() + "/home");
            } else if (loginAdmin) {
                request.setAttribute(URL_PAGE, request.getContextPath() + "/admin/dashboard");
            } else if (loginEmployee) {
                request.setAttribute(URL_PAGE, request.getContextPath() + "/employee/appointment");
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/ErrorPage.jsp");
            requestDispatcher.forward(request, response);
            
        } catch (IOException | ServletException e) {
            
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
