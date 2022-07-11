package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.AccountError;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class EditProfileController extends HttpServlet {
    
    private static final String EDIT_PROFILE_ERROR = "EDIT_PROFILE_ERROR";
    private static final String FULLNAME = "FULLNAME";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String ADDRESS = "ADDRESS";
    private static final String IMAGE_AVATAR = "IMAGE_AVATAR";
    private static final String LOGIN_USER = "LOGIN_USER";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/EditProfile.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String sex = request.getParameter("userSex");
            String day = request.getParameter("day");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String imageAvatar = request.getParameter("imageAvatar");

            Account account;
            AccountFacade accountFacade = new AccountFacade();
            AccountError accountError = new AccountError();
            boolean hasError = false;

            if (fullName.equals("") && phoneNumber.equals("") && address.equals("")) {
                hasError = true;
                accountError.setFullNameError("Please enter your full name!");
                accountError.setPhoneNumberError("Please enter your phone number!");
                accountError.setAddressError("Please enter your address!");
            } else if (fullName.equals("")) {
                hasError = true;
                accountError.setFullNameError("Please enter your full name!");
            } else if (phoneNumber.equals("")) {
                hasError = true;
                accountError.setPhoneNumberError("Please enter your phone number!");
            } else if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
                hasError = true;
                accountError.setPhoneNumberError("Phone number must have 10 digits!");
            } else if (address.equals("")) {
                hasError = true;
                accountError.setAddressError("Please enter your address!");
            }

            if (hasError) {
                request.setAttribute(FULLNAME, fullName);
                request.setAttribute(PHONE_NUMBER, phoneNumber);
                request.setAttribute(ADDRESS, address);
                if (imageAvatar != null) {
                    String[] cutCodeImage = imageAvatar.split("\\,");
                    request.setAttribute(IMAGE_AVATAR, cutCodeImage[1]);
                }
                request.setAttribute(EDIT_PROFILE_ERROR, accountError);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/EditProfile.jsp");
                requestDispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                String gender;

                if (sex.equals("Male")) {
                    gender = "M";
                } else {
                    gender = "F";
                }

                String dateOfBirth = year + "-" + month + "-" + day;
                Date convertDateOfBirth = Date.valueOf(dateOfBirth);

                account = new Account();
                account.setFullName(fullName);
                account.setUserEmail(email);
                account.setGender(gender);
                account.setDateOfBirth(convertDateOfBirth);
                account.setUserPhone(phoneNumber);
                if (imageAvatar != null) {
                    String[] cutCodeImage = imageAvatar.split("\\,");
                    account.setImageAvatar(cutCodeImage[1]);
                }
                account.setUserAddress(address);

                boolean checkAddProduct = accountFacade.updateAccount(account, "EditProfile");
                if (checkAddProduct) {
                    session.setAttribute(LOGIN_USER, accountFacade.checkAccount(account, "Login"));
                }
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/info-profile");
                requestDispatcher.forward(request, response);
            }

        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
