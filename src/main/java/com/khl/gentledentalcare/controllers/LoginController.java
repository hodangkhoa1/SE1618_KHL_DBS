package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.NotificationFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.AccountError;
import com.khl.gentledentalcare.models.Notification;
import com.khl.gentledentalcare.utils.CalculatorTime;
import com.khl.gentledentalcare.utils.Encrypt;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {

    private static final String LOGIN_ADMIN = "LOGIN_ADMIN";
    private static final String LOGIN_USER = "LOGIN_USER";
    private static final String LOGIN_ACCOUNT_ERROR = "LOGIN_ACCOUNT_ERROR";
    private static final String NOTIFICATION_LIST = "NOTIFICATION_LIST";
    private static final String TIME_NOTIFICATION = "TIME_NOTIFICATION";
    private static final String COUNT_NOTIFICATION_NOT_READ = "COUNT_NOTIFICATION_NOT_READ";
    private static final String REMEMBER_USER = "USER_GDC";
    private static final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
    private static final int TIME_COOKIE = 60 * 60 * 24;
    private static final String EMAIL = "EMAIL";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String getEmail = request.getParameter("email");
            String getPassword = request.getParameter("password");
            String hashPassword = DigestUtils.md5Hex(getPassword);
            String rememberMeString = request.getParameter("rememberMe");
            boolean rememberMe = "Y".equals(rememberMeString);

            Account account = null;
            AccountError accountError = new AccountError();
            boolean hasError = false;
            Cookie cookieUserName;

            if (getEmail.equals("") && getPassword.equals("")) {
                hasError = true;
                accountError.setEmailError("Please enter your email!");
                accountError.setPasswordError("Please enter your password!");
            } else if (getEmail.equals("")) {
                hasError = true;
                accountError.setEmailError("Please enter your email!");
            } else if (getPassword.equals("")) {
                hasError = true;
                accountError.setPasswordError("Please enter your password!");
            } else {
                AccountFacade checkLogin = new AccountFacade();
                account = checkLogin.checkAccount(getEmail);
                if (account == null) {
                    hasError = true;
                    accountError.setEmailError("Account does not exist!");
                } else if (account.getUserStatus()== 0) {
                    hasError = true;
                    accountError.setEmailError("Account not activated!");
                } else if (account.getUserStatus() == 1) {
                    hasError = true;
                    accountError.setEmailError("Your account has been locked!");
                } else if (!hashPassword.equals(account.getUserPassword())) {
                    hasError = true;
                    accountError.setPasswordError("Wrong password, please try again!");
                }
            }

            if (hasError) {
                request.setAttribute(LOGIN_ACCOUNT_ERROR, accountError);
                request.setAttribute(EMAIL, getEmail);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
                requestDispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();

                if (rememberMe) {
                    String encryptedUsername = Encrypt.encrypt(getEmail, SECRET_KEY);
                    cookieUserName = new Cookie(REMEMBER_USER, encryptedUsername);
                    cookieUserName.setMaxAge(TIME_COOKIE);
                } else {
                    cookieUserName = new Cookie(REMEMBER_USER, null);
                    cookieUserName.setMaxAge(0);
                }
                response.addCookie(cookieUserName);

                switch (account.getUserRole()) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        NotificationFacade checkNotification = new NotificationFacade();
                        List<Notification> notificationList = checkNotification.getAllNotification(account.getUserID());

                        if (notificationList.isEmpty()) {
                            session.setAttribute(NOTIFICATION_LIST, null);
                        } else {
                            NotificationFacade notificationFacade = new NotificationFacade();
                            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                            String currentDateString = dateFormatter.format(Calendar.getInstance().getTime());
                            Date currentDate = dateFormatter.parse(currentDateString);
                            String pastTimeInSecond = dateFormatter.format(account.getAccountCreated());
                            Date pastDate = dateFormatter.parse(pastTimeInSecond);
                            int totalNotification = notificationFacade.countNotification(account.getUserID());
                            session.setAttribute(TIME_NOTIFICATION, CalculatorTime.timeAgo(currentDate, pastDate));
                            session.setAttribute(NOTIFICATION_LIST, notificationList);
                            if (totalNotification == 0) {
                                session.setAttribute(COUNT_NOTIFICATION_NOT_READ, null);
                            } else {
                                session.setAttribute(COUNT_NOTIFICATION_NOT_READ, totalNotification);
                            }
                        }

                        session.setAttribute(LOGIN_USER, account);
                        session.setMaxInactiveInterval(500);
                        String uri = (String) session.getAttribute("uri");
                        if (uri != null) {
                            response.sendRedirect(uri);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/home");
                        }
                        break;
                }
            }

        } catch (IOException | SQLException | ParseException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

}
