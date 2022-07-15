package com.khl.gentledentalcare.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LogoutController extends HttpServlet {
    
    private static final String SUCCESS = "home";
    private static final String ERROR = "error";
    private static final String REMEMBER_USER = "USER_GDC";
    private static final String REMEMBER_PASSWORD = "USER_P_GDC";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            HttpSession session = request.getSession(true);
            Cookie cookieUserName, cookiePassword;

            if (session != null) {
                cookieUserName = new Cookie(REMEMBER_USER, null);
                cookiePassword = new Cookie(REMEMBER_PASSWORD, null);
                cookieUserName.setMaxAge(0);
                cookiePassword.setMaxAge(0);
                response.addCookie(cookieUserName);
                response.addCookie(cookiePassword);
                session.invalidate();
                url = SUCCESS;
            }
        } catch (Exception e) {
            url = ERROR;
        } finally {
            response.sendRedirect(url);
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
