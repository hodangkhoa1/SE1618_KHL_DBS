package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.NotificationFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.AccountError;
import com.khl.gentledentalcare.models.Notification;
import com.khl.gentledentalcare.utils.Encrypt;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ASUS
 */
public class RegisterController extends HttpServlet {

    private static final String INTRO_USER = "INTRO_USER";
    private static final String WELCOME_USER = "WELCOME_USER";
    private static final String SIGN_UP_ACCOUNT_ERROR = "SIGN_UP_ACCOUNT_ERROR";
    private static final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
    private static final String USERNAME_REGISTER = "USERNAME_REGISTER";
    private static final String EMAIL_REGISTER = "USERNAME_REGISTER";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String getFullName = request.getParameter("signupUsername");
            String getEmail = request.getParameter("signupEmail");
            String getPassword = request.getParameter("signupPassword");
            String getConfirmPassword = request.getParameter("confirmPassword");
            String newPassword = DigestUtils.md5Hex(getPassword);
            String userID = FunctionRandom.randomID(10);
            
            Account account;
            Notification notification;
            AccountError accountError = new AccountError();
            AccountFacade accountFacade = new AccountFacade();
            boolean hasError = false;
            
            if(getFullName.equals("")) {
                hasError = true;
                accountError.setFullNameError("Please enter your full name!");
            } else if(getEmail.equals("")) {
                hasError = true;
                accountError.setEmailError("Please enter your email!");
            } else if(getPassword.equals("")) {
                hasError = true;
                accountError.setPasswordError("Please enter your password!");
            } else if(getConfirmPassword.equals("")) {
                hasError = true;
                accountError.setConfirmPasswordError("Please enter your confirm password!");
            } else if(!getPassword.equals(getConfirmPassword)) {
                hasError = true;
                accountError.setConfirmPasswordError("Password does not match!");
            } else {
                String colorAvatar = FunctionRandom.colorAvatar();
                char getFirstCharacter = getFullName.charAt(0);
                
                account = new Account();
                account.setFullName(getFullName);
                account.setUserEmail(getEmail);
                account.setUserPassword(newPassword);
                account.setUserID(userID);
                account.setDefaultAvatar(Character.toString(getFirstCharacter));
                account.setColorAvatar(colorAvatar);

                Account checkAccount = accountFacade.checkAccount(getEmail);
                if(checkAccount == null) {
                    String message = accountFacade.registerAccount(account);
                    if(!message.equals("success")) {
                        hasError = true;
                        accountError.setFullNameError("System error please try again!");
                    }
                } else {
                    hasError = true;
                    accountError.setEmailError("Account already exists!");
                }
            }
            
            if(hasError) {
                request.setAttribute(SIGN_UP_ACCOUNT_ERROR, accountError);
                request.setAttribute(USERNAME_REGISTER, getFullName);
                request.setAttribute(EMAIL_REGISTER, getEmail);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
                requestDispatcher.forward(request, response);
            } else {
                notification = new Notification();
                notification.setNotifyID(FunctionRandom.randomID(10));
                notification.setUserID(userID);
                notification.setNotifyType("NewAccount");
                NotificationFacade checkNotification = new NotificationFacade();
                boolean addNotificationDone = checkNotification.addNotification(notification);
                if(addNotificationDone) {
                    String encryptedUsername = Encrypt.encrypt(getFullName, SECRET_KEY);
                    HttpSession session = request.getSession();
                    session.setAttribute(INTRO_USER, encryptedUsername);
                    session.setAttribute(WELCOME_USER, encryptedUsername);
                    session.setMaxInactiveInterval(500);
                    response.sendRedirect(request.getContextPath() + "/verify");
                }
            }
            
        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
