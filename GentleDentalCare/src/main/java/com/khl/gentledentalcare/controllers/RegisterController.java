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
    private static final String LOGIN_USER = "LOGIN_USER";
    private static final String SIGN_UP_ACCOUNT_ERROR = "SIGN_UP_ACCOUNT_ERROR";
    private static final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
    private static final String USERNAME_REGISTER = "USERNAME_REGISTER";
    private static final String EMAIL_REGISTER = "EMAIL_REGISTER";
    private static final String VALUE_LOGIN = "VALUE_LOGIN";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String getFullName = request.getParameter("fullname");
            String getEmail = request.getParameter("email");
            String getPassword = request.getParameter("password");
            String getConfirmPassword = request.getParameter("confirmpassword");
            String userID = FunctionRandom.randomID(10);
            
            Account account = new Account();
            Notification notification;
            AccountError accountError = new AccountError();
            AccountFacade accountFacade = new AccountFacade();
            HttpSession session = request.getSession();
            boolean hasError = false;
            
            if (getFullName.equals("") && getEmail.equals("") && getPassword.equals("") && getConfirmPassword.equals("")) {
                hasError = true;
                accountError.setFullNameError("Please enter your full name!");
                accountError.setEmailError("Please enter your email!");
                accountError.setPasswordError("Please enter your password!");
                accountError.setConfirmPasswordError("Please enter your confirm password!");
            } else if(getFullName.equals("")) {
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
                String newPassword = DigestUtils.md5Hex(getPassword);
                char getFirstCharacter = getFullName.charAt(0);
                
                account.setFullName(getFullName);
                account.setUserEmail(getEmail);
                account.setUserPassword(newPassword);
                account.setUserID(userID);
                account.setDefaultAvatar(Character.toString(getFirstCharacter));
                account.setColorAvatar(colorAvatar);

                Account checkAccount = accountFacade.checkAccount(account, "Login");
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
                session.setAttribute(VALUE_LOGIN, "VALUE_REGISTER");
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
                    session.setAttribute(INTRO_USER, encryptedUsername);
                    session.setAttribute(WELCOME_USER, encryptedUsername);
                    session.setAttribute(LOGIN_USER, account);
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
