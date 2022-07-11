package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.AccountError;
import com.khl.gentledentalcare.utils.FunctionRandom;
import com.khl.gentledentalcare.utils.FunctionSendEmail;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

public class ForgotPasswordController extends HttpServlet {

    private static final String FORGOT_ACCOUNT_ERROR = "FORGOT_ACCOUNT_ERROR";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String CHANGE_PAGE_PASSWORD = "CHANGE_PAGE_PASSWORD";
    private static final String CHANGE_PAGE_VERIFY = "CHANGE_PAGE_VERIFY";
    private static final String EMAIL = "EMAIL";
    private String codeVerify;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute(CURRENT_PAGE, CURRENT_PAGE);
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/ForgotPassword.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String email = request.getParameter("email");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String verifySMS = request.getParameter("verifySMS");

            Account account = new Account();
            AccountError accountError = new AccountError();
            AccountFacade accountFacade = new AccountFacade();
            boolean hasError = false;

            if (email != null) {
                if (email.equals("")) {
                    hasError = true;
                    accountError.setEmailError("Please enter your email!");
                } else {
                    account.setUserEmail(email);
                    Account checkAccount = accountFacade.checkAccount(account, "Login");

                    if (checkAccount == null) {
                        hasError = true;
                        accountError.setEmailError("Account does not exist!");
                    } else {
                        account.setFullName(checkAccount.getFullName());
                    }
                }
            } else if (newPassword != null && confirmPassword != null) {
                if (newPassword.equals("")) {
                    hasError = true;
                    accountError.setNewPasswordError("Please enter new password!");
                } else if (confirmPassword.equals("")) {
                    hasError = true;
                    accountError.setConfirmPasswordError("Please enter confirm password!");
                } else if (!newPassword.equals(confirmPassword)) {
                    hasError = true;
                    accountError.setConfirmPasswordError("Password does not match!");
                } else {
                    String hashNewPassword = DigestUtils.md5Hex(newPassword);
                    account.setUserPassword(hashNewPassword);
                }
            } else {
                if (verifySMS.equals("")) {
                    hasError = true;
                    accountError.setVerifySMSError("Please enter code verify!");
                } else if (!verifySMS.equals(codeVerify)) {
                    hasError = true;
                    accountError.setVerifySMSError("Verification code is not correct!");
                }
            }

            if (hasError) {
                if (email != null) {
                    request.setAttribute(CURRENT_PAGE, CURRENT_PAGE);
                    request.setAttribute(EMAIL, email);
                } else if (newPassword != null && confirmPassword != null) {
                    request.removeAttribute(CURRENT_PAGE);
                    request.setAttribute(CHANGE_PAGE_PASSWORD, CHANGE_PAGE_PASSWORD);
                } else if (verifySMS != null) {
                    request.removeAttribute(CHANGE_PAGE_PASSWORD);
                    request.setAttribute(CHANGE_PAGE_VERIFY, CHANGE_PAGE_VERIFY);
                }
                request.setAttribute(FORGOT_ACCOUNT_ERROR, accountError);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/ForgotPassword.jsp");
                requestDispatcher.forward(request, response);
            } else {
                if (email != null) {
                    if (account.getUserStatus() == 1) {
                        request.setAttribute(CHANGE_PAGE_PASSWORD, CHANGE_PAGE_PASSWORD);
                    }
                    
                    request.removeAttribute(CURRENT_PAGE);
                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/ForgotPassword.jsp");
                    requestDispatcher.forward(request, response);
                } else if (newPassword != null && confirmPassword != null) {
                    request.setAttribute(CHANGE_PAGE_VERIFY, CHANGE_PAGE_VERIFY);
                    request.removeAttribute(CHANGE_PAGE_PASSWORD);
                    codeVerify = FunctionRandom.randomVerifyCode(4);
                    FunctionSendEmail functionSendEmail = new FunctionSendEmail(account, "Verification code", codeVerify);
                    functionSendEmail.sendMailChangePassword();

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/ForgotPassword.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    if (verifySMS.equals(codeVerify)) {
                        boolean checkUpdate = accountFacade.updateAccount(account, "ChangePassword");
                        if (checkUpdate) {
                            response.sendRedirect(request.getContextPath() + "/login");
                        }
                    }
                }
            }
        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
