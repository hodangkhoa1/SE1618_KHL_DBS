package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
public class InfoProfileController extends HttpServlet {

    private static final String REMEMBER_USER = "USER_GDC";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String userEmail = request.getParameter("userEmail");

            HttpSession session = request.getSession(true);
            Cookie cookieUserName;
            AccountFacade accountFacade = new AccountFacade();
            Account account = new Account();

            if (userEmail != null) {
                account.setUserEmail(userEmail);
                boolean checkDeleteAccount = accountFacade.updateAccount(account, "DeleteAccount");
                if (checkDeleteAccount) {
                    if (session != null) {
                        cookieUserName = new Cookie(REMEMBER_USER, null);
                        cookieUserName.setMaxAge(0);
                        response.addCookie(cookieUserName);
                        session.invalidate();
                    }
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } else {
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/InfoProfile.jsp");
                requestDispatcher.forward(request, response);
            }
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
