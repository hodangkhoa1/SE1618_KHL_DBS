package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.GoogleAccount;
import com.khl.gentledentalcare.services.RestGoogle;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginGoogleController extends HttpServlet {

    private static final String LOGIN_USER = "LOGIN_USER";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String googleCode = request.getParameter("code");
            String accessToken = RestGoogle.getGoogleToken(googleCode);
            GoogleAccount googleAccount = RestGoogle.getGoogleUserInfo(accessToken);

            AccountFacade accountFacade = new AccountFacade();
            HttpSession session = request.getSession();

            Account checkLearnerAccount = accountFacade.checkAccount(googleAccount.getEmail());

            if (checkLearnerAccount == null) {
                Account account = new Account();

                account.setUserID(FunctionRandom.randomID(10));
                account.setUserEmail(googleAccount.getEmail());
                accountFacade.registerAccount(account);

                session.setAttribute(LOGIN_USER, account);
            } else {
                session.setAttribute(LOGIN_USER, checkLearnerAccount);
            }

            response.sendRedirect(request.getContextPath() + "/home");

        } catch (IOException | SQLException e) {
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
