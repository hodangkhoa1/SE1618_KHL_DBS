package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class VerifyController extends HttpServlet {

    private static final String VERIFY_STATUS = "VERIFY_STATUS";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String userId = request.getParameter("uid");

            if (userId != null) {
                AccountFacade accountFacade = new AccountFacade();
                Account account = new Account();

                account.setUserEmail(userId);
                account.setUserStatus(1);

                Account checkAccount = accountFacade.checkAccount(account, "Login");

                if (checkAccount != null) {
                    Timestamp currentDate = new Timestamp(System.currentTimeMillis());

                    if ((currentDate.getMinutes() - checkAccount.getAccountCreated().getMinutes()) < 15) {
                        accountFacade.updateAccount(account, "EditStatus");
                        request.setAttribute(VERIFY_STATUS, "Successful");
                    } else {
                        request.setAttribute(VERIFY_STATUS, "Error");
                    }
                }

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Verify.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(VERIFY_STATUS, "Successful");
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Verify.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
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
