package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class CustomerManagementController extends HttpServlet {

    private static final String ACCOUNT_LIST = "ACCOUNT_LIST";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String SEARCH = "SEARCH";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String indexPage = request.getParameter("page");
            String userID = request.getParameter("userID");

            Account account = new Account();
            AccountFacade accountFacade = new AccountFacade();

            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            List<Account> accountList;

            if (userID != null) {
                String actionButton = request.getParameter("Action");
                account.setUserID(userID);

                if (actionButton.equals("Active") || actionButton.equals("UnDisabled")) {
                    account.setUserStatus(1);
                } else if (actionButton.equals("Disabled")) {
                    account.setUserStatus(2);
                }
                
                accountFacade.updateAccount(account, "EditStatus");
            } else {
                int countAccount = accountFacade.countAccount("0");
                int endPage = countAccount / 5;
                if (countAccount % 5 != 0) {
                    endPage++;
                }

                accountList = accountFacade.getAccount(index, "PagingAccount", "0");
                if (accountList.isEmpty()) {
                    request.setAttribute(ACCOUNT_LIST, null);
                } else {
                    JSONArray jsArray = new JSONArray(accountList);
                    request.setAttribute(ACCOUNT_LIST, jsArray.toString());
                }

                request.setAttribute(END_PAGE, endPage);
                request.setAttribute(CURRENT_PAGE, index);
                request.setAttribute(SEARCH, "fullName");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/CustomerManagement.jsp");
                requestDispatcher.forward(request, response);
            }

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
