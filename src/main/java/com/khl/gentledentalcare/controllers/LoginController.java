package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.NotificationFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.AccountError;
import com.khl.gentledentalcare.models.GoogleAccount;
import com.khl.gentledentalcare.models.Notification;
import com.khl.gentledentalcare.services.RestGoogle;
import com.khl.gentledentalcare.utils.CalculatorTime;
import com.khl.gentledentalcare.utils.Encrypt;
import com.khl.gentledentalcare.utils.FunctionRandom;
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
    private static final String LOGIN_EMPLOYEE = "LOGIN_EMPLOYEE";
    private static final String LOGIN_ACCOUNT_ERROR = "LOGIN_ACCOUNT_ERROR";
    private static final String NOTIFICATION_LIST = "NOTIFICATION_LIST";
    private static final String TIME_NOTIFICATION = "TIME_NOTIFICATION";
    private static final String COUNT_NOTIFICATION_NOT_READ = "COUNT_NOTIFICATION_NOT_READ";
    private static final String REMEMBER_USER = "USER_GDC";
    private static final String REMEMBER_PASSWORD = "USER_P_GDC";
    private static final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
    private static final int TIME_COOKIE = 60 * 60 * 24;
    private static final String EMAIL = "EMAIL";
    private static final String VALUE_LOGIN = "VALUE_LOGIN";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            String googleCode = request.getParameter("code");

            if (googleCode != null) {
                String accessToken = RestGoogle.getGoogleToken(googleCode);
                GoogleAccount googleAccount = RestGoogle.getGoogleUserInfo(accessToken);
                Account account = new Account();
                
                account.setUserID(FunctionRandom.randomID(10));
                account.setUserEmail(googleAccount.getEmail());

                AccountFacade accountFacade = new AccountFacade();
                Account checkLearnerAccount = accountFacade.checkAccount(account, "Login");

                if (checkLearnerAccount == null) {
                    accountFacade.registerAccount(account);
                    session.setAttribute(LOGIN_USER, account);
                } else {
                    session.setAttribute(LOGIN_USER, checkLearnerAccount);
                }

                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                session.setAttribute(VALUE_LOGIN, "VALUE_LOGIN");
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (IOException | SQLException | ServletException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String getEmail = request.getParameter("email");
            String getPassword = request.getParameter("password");
            String rememberMeString = request.getParameter("rememberMe");
            String hashPassword = DigestUtils.md5Hex(getPassword);
            boolean rememberMe = "Y".equals(rememberMeString);
            int countBlock = 0;

            Account account = null;
            AccountError accountError = new AccountError();
            HttpSession session = request.getSession();
            boolean hasError = false;
            Cookie cookieUserName, cookiePassword;

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
                Account accountTMP = new Account();
                accountTMP.setUserEmail(getEmail);
                
                account = checkLogin.checkAccount(accountTMP, "Login");
                if (account == null) {
                    hasError = true;
                    accountError.setEmailError("Account does not exist!");
                } else if (account.getUserStatus() == 0) {
                    hasError = true;
                    accountError.setEmailError("Account not activated!");
                } else if (account.getUserStatus() == 2) {
                    hasError = true;
                    accountError.setEmailError("Your account has been locked!");
                } else if (!hashPassword.equals(account.getUserPassword())) {
                    countBlock++;
                    hasError = true;
                    accountError.setPasswordError("Wrong password, please try again!");
                } else if (countBlock == 5) {
                    hasError = true;
                    accountError.setEmailError("You entered the wrong password more than 5 times. Please re-enter after 30 seconds!");
                }
            }

            if (hasError) {
                request.setAttribute(LOGIN_ACCOUNT_ERROR, accountError);
                request.setAttribute(EMAIL, getEmail);
                session.setAttribute(VALUE_LOGIN, "VALUE_LOGIN");
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
                requestDispatcher.forward(request, response);
            } else {
                if (rememberMe) {
                    String encryptedUsername = Encrypt.encrypt(getEmail, SECRET_KEY);
                    cookieUserName = new Cookie(REMEMBER_USER, encryptedUsername);
                    cookiePassword = new Cookie(REMEMBER_PASSWORD, hashPassword);
                    cookieUserName.setMaxAge(TIME_COOKIE);
                    cookiePassword.setMaxAge(TIME_COOKIE);
                } else {
                    cookieUserName = new Cookie(REMEMBER_USER, null);
                    cookiePassword = new Cookie(REMEMBER_PASSWORD, null);
                    cookieUserName.setMaxAge(0);
                    cookiePassword.setMaxAge(0);
                }
                response.addCookie(cookieUserName);
                response.addCookie(cookiePassword);

                switch (account.getUserRole()) {
                    case 0:
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
                        session.removeAttribute(VALUE_LOGIN);
                        session.setMaxInactiveInterval(500);
                        String uri = (String) session.getAttribute("uri");
                        if (uri != null) {
                            response.sendRedirect(uri);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/home");
                        }
                        break;
                    case 1:
                        session.setAttribute(LOGIN_ADMIN, account);
                        session.setMaxInactiveInterval(500);
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        break;
                    case 2:
                        session.setAttribute(LOGIN_EMPLOYEE, account);
                        session.setMaxInactiveInterval(500);
                        response.sendRedirect(request.getContextPath() + "/employee/appointment");
                        break;
                }
            }

        } catch (IOException | SQLException | ParseException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

}
