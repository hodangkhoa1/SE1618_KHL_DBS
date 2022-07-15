package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.dbo.EmployeeFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.models.AccountError;
import com.khl.gentledentalcare.models.Employee;
import com.khl.gentledentalcare.models.EmployeeError;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;

public class EmployeeManagementController extends HttpServlet {

    private static final String EMPLOYEE_LIST = "EMPLOYEE_LIST";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String BUTTON_ACTION = "BUTTON_ACTION";
    private static final String ACTION_URL = "ACTION_URL";
    private static final String FULL_NAME = "FULL_NAME";
    private static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
    private static final String EMAIL = "EMAIL";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String ADDRESS = "ADDRESS";
    private static final String EMPLOYEE_IMAGE = "EMPLOYEE_IMAGE";
    private static final String SALARY = "SALARY";
    private static final String INSURANCE = "INSURANCE";
    private static final String NEW_PASSWORD = "123456789";
    private static final String ACCOUNT_ERROR = "ACCOUNT_ERROR";
    private static final String EMPLOYEE_ERROR = "EMPLOYEE_ERROR";
    private static final String SEARCH = "SEARCH";
    private static final String NAV_BAR_PROFILE = "NAV_BAR_PROFILE";
    private static final String NAV_BAR_ICON = "NAV_BAR_ICON";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();

            AccountFacade accountFacade = new AccountFacade();

            if (urlServlet.equals("/admin/add-employee")) {
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-plus icon\"></i>");
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(BUTTON_ACTION, "Add Employee");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-employee");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddEmployee.jsp");
                requestDispatcher.forward(request, response);
            } else if (urlServlet.equals("/admin/edit-employee")) {
                String employeeID = request.getParameter("eid");

                EmployeeFacade employeeFacade = new EmployeeFacade();
                Account account = new Account();
                account.setUserID(employeeID);

                Account accountEmployee = accountFacade.checkAccount(account, "GetAccountEmployee");
                Employee employee = employeeFacade.getEmployee(employeeID);

                request.setAttribute(FULL_NAME, accountEmployee.getFullName());
                request.setAttribute(DATE_OF_BIRTH, accountEmployee.getDateOfBirth());
                request.setAttribute(EMAIL, accountEmployee.getUserEmail());
                request.setAttribute(PHONE_NUMBER, accountEmployee.getUserPhone());
                request.setAttribute(ADDRESS, accountEmployee.getUserAddress());
                request.setAttribute(EMPLOYEE_IMAGE, accountEmployee.getImageAvatar());
                request.setAttribute(SALARY, employee.getSalary());
                request.setAttribute(INSURANCE, employee.getInsurance());
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-pen-to-square icon\"></i>");
                request.setAttribute(BUTTON_ACTION, "Edit Employee");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-employee?eid="+ employeeID +"");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddEmployee.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String indexPage = request.getParameter("page");
                String employeeID = request.getParameter("EmployeeID");

                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                List<Account> accountList;

                if (employeeID != null) {
                    String actionButton = request.getParameter("Action");
                    Account account = new Account();
                    account.setUserID(employeeID);

                    if (actionButton.equals("Disable")) {
                        account.setUserStatus(2);
                    } else {
                        account.setUserStatus(1);
                    }

                    accountFacade.updateAccount(account, "EditStatus");
                } else {
                    int countAccount = accountFacade.countAccount("2");
                    int endPage = countAccount / 5;
                    if (countAccount % 5 != 0) {
                        endPage++;
                    }

                    accountList = accountFacade.getAccount(index, "PagingAccount", "2");
                    if (accountList.isEmpty()) {
                        request.setAttribute(EMPLOYEE_LIST, null);
                    } else {
                        JSONArray jsArray = new JSONArray(accountList);
                        request.setAttribute(EMPLOYEE_LIST, jsArray.toString());
                    }

                    request.setAttribute(END_PAGE, endPage);
                    request.setAttribute(CURRENT_PAGE, index);
                    request.setAttribute(SEARCH, "fullName");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/EmployeeManagement.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String urlServlet = request.getServletPath();

            Account account;
            Employee employee;
            AccountFacade accountFacade = new AccountFacade();
            EmployeeFacade employeeFacade = new EmployeeFacade();

            if (urlServlet.equals("/admin/add-employee")) {
                String employeeID = FunctionRandom.randomID(10);
                String getFullName = request.getParameter("fullName");
                String getGender = request.getParameter("gender");
                String getEmail = request.getParameter("email");
                String getDateOfBirth = request.getParameter("dateOfBirth");
                String getPhoneNumber = request.getParameter("phoneNumber");
                String getEmployeeImage = request.getParameter("employeeImage");
                String getAddress = request.getParameter("address");
                String getSalary = request.getParameter("salary");
                String getInsurance = request.getParameter("insurance");

                AccountError accountError = new AccountError();
                EmployeeError employeeError = new EmployeeError();
                boolean hasError = false;

                if (getFullName.equals("") && getGender.equals("") && getEmail.equals("") && getDateOfBirth.equals("") && getPhoneNumber.equals("") && getEmployeeImage.equals("") && getAddress.equals("") && getSalary.equals("") && getInsurance.equals("")) {
                    hasError = true;
                    accountError.setFullNameError("Please enter full name!");
                    accountError.setGenderError("Please choose gender!");
                    accountError.setEmailError("Please enter email!");
                    accountError.setDateOfBirthError("Please choose date of birth!");
                    accountError.setPhoneNumberError("Please enter phone number!");
                    accountError.setImageAvatarError("Please choose image avatar!");
                    accountError.setAddressError("Please enter address!");
                    employeeError.setSalaryError("Please enter salary!");
                    employeeError.setInsuranceError("Please enter insurance!");
                } else if (getFullName.equals("")) {
                    hasError = true;
                    accountError.setFullNameError("Please enter full name!");
                } else if (getGender.equals("")) {
                    hasError = true;
                    accountError.setGenderError("Please choose gender!");
                } else if (getEmail.equals("")) {
                    hasError = true;
                    accountError.setEmailError("Please enter email!");
                } else if (getDateOfBirth.equals("")) {
                    hasError = true;
                    accountError.setDateOfBirthError("Please choose date of birth!");
                } else if (getPhoneNumber.equals("")) {
                    hasError = true;
                    accountError.setPhoneNumberError("Please enter phone number!");
                } else if (getEmployeeImage.equals("")) {
                    hasError = true;
                    accountError.setImageAvatarError("Please choose image avatar!");
                } else if (getAddress.equals("")) {
                    hasError = true;
                    accountError.setAddressError("Please enter address!");
                } else if (getSalary.equals("")) {
                    hasError = true;
                    employeeError.setSalaryError("Please enter salary!");
                } else if (getInsurance.equals("")) {
                    hasError = true;
                    employeeError.setInsuranceError("Please enter insurance!");
                }

                if (hasError) {
                    request.setAttribute(FULL_NAME, getFullName);
                    request.setAttribute(DATE_OF_BIRTH, getDateOfBirth);
                    request.setAttribute(EMAIL, getEmail);
                    request.setAttribute(PHONE_NUMBER, getPhoneNumber);
                    request.setAttribute(ADDRESS, getAddress);
                    if (getEmployeeImage != null) {
                        String[] cutCodeImage = getEmployeeImage.split("\\,");
                        request.setAttribute(EMPLOYEE_IMAGE, cutCodeImage[1]);
                    }
                    request.setAttribute(SALARY, getSalary);
                    request.setAttribute(INSURANCE, getInsurance);
                    request.setAttribute(ACCOUNT_ERROR, accountError);
                    request.setAttribute(EMPLOYEE_ERROR, employeeError);
                    request.setAttribute(BUTTON_ACTION, "Add Employee");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-employee");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddEmployee.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    String newPassword = DigestUtils.md5Hex(NEW_PASSWORD);

                    account = new Account();
                    employee = new Employee();
                    account.setUserID(employeeID);
                    account.setFullName(getFullName);
                    account.setUserPassword(newPassword);
                    account.setUserEmail(getEmail);
                    account.setUserAddress(getAddress);
                    account.setUserPhone(getPhoneNumber);
                    account.setGender(getGender);
                    if (getEmployeeImage != null) {
                        String[] cutCodeImage = getEmployeeImage.split("\\,");
                        account.setImageAvatar(cutCodeImage[1]);
                    }
                    Date convertDateOfBirth = Date.valueOf(getDateOfBirth);
                    account.setDateOfBirth(convertDateOfBirth);
                    account.setUserRole(2);
                    account.setUserStatus(1);
                    employee.setEmployeeID(employeeID);
                    employee.setSalary(Integer.parseInt(getSalary));
                    employee.setInsurance(Integer.parseInt(getInsurance));
                    accountFacade.addAccount(account, "AddAccountEmployee");
                    employeeFacade.addEmployee(employee);
                    response.sendRedirect(request.getContextPath() + "/admin/employee-management");
                }
            } else {
                String employeeID = request.getParameter("eid");
                String getFullName = request.getParameter("fullName");
                String getGender = request.getParameter("gender");
                String getEmail = request.getParameter("email");
                String getDateOfBirth = request.getParameter("dateOfBirth");
                String getPhoneNumber = request.getParameter("phoneNumber");
                String getEmployeeImage = request.getParameter("employeeImage");
                String getAddress = request.getParameter("address");
                String getSalary = request.getParameter("salary");
                String getInsurance = request.getParameter("insurance");

                AccountError accountError = new AccountError();
                EmployeeError employeeError = new EmployeeError();
                boolean hasError = false;

                if (getFullName.equals("") && getGender.equals("") && getEmail.equals("") && getDateOfBirth.equals("") && getPhoneNumber.equals("") && getEmployeeImage.equals("") && getAddress.equals("") && getSalary.equals("") && getInsurance.equals("")) {
                    hasError = true;
                    accountError.setFullNameError("Please enter full name!");
                    accountError.setGenderError("Please choose gender!");
                    accountError.setEmailError("Please enter email!");
                    accountError.setDateOfBirthError("Please choose date of birth!");
                    accountError.setPhoneNumberError("Please enter phone number!");
                    accountError.setImageAvatarError("Please choose image avatar!");
                    accountError.setAddressError("Please enter address!");
                    employeeError.setSalaryError("Please enter salary!");
                    employeeError.setInsuranceError("Please enter insurance!");
                } else if (getFullName.equals("")) {
                    hasError = true;
                    accountError.setFullNameError("Please enter full name!");
                } else if (getGender.equals("")) {
                    hasError = true;
                    accountError.setGenderError("Please choose gender!");
                } else if (getEmail.equals("")) {
                    hasError = true;
                    accountError.setEmailError("Please enter email!");
                } else if (getDateOfBirth.equals("")) {
                    hasError = true;
                    accountError.setDateOfBirthError("Please choose date of birth!");
                } else if (getPhoneNumber.equals("")) {
                    hasError = true;
                    accountError.setPhoneNumberError("Please enter phone number!");
                } else if (getEmployeeImage.equals("")) {
                    hasError = true;
                    accountError.setImageAvatarError("Please choose image avatar!");
                } else if (getAddress.equals("")) {
                    hasError = true;
                    accountError.setAddressError("Please enter address!");
                } else if (getSalary.equals("")) {
                    hasError = true;
                    employeeError.setSalaryError("Please enter salary!");
                } else if (getInsurance.equals("")) {
                    hasError = true;
                    employeeError.setInsuranceError("Please enter insurance!");
                }

                if (hasError) {
                    request.setAttribute(FULL_NAME, getFullName);
                    request.setAttribute(DATE_OF_BIRTH, getDateOfBirth);
                    request.setAttribute(EMAIL, getEmail);
                    request.setAttribute(PHONE_NUMBER, getPhoneNumber);
                    request.setAttribute(ADDRESS, getAddress);
                    if (getEmployeeImage != null) {
                        String[] cutCodeImage = getEmployeeImage.split("\\,");
                        request.setAttribute(EMPLOYEE_IMAGE, cutCodeImage[1]);
                    }
                    request.setAttribute(SALARY, getSalary);
                    request.setAttribute(INSURANCE, getInsurance);
                    request.setAttribute(ACCOUNT_ERROR, accountError);
                    request.setAttribute(EMPLOYEE_ERROR, employeeError);
                    request.setAttribute(BUTTON_ACTION, "Edit Employee");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-employee?eid="+ employeeID +"");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddEmployee.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    account = new Account();
                    employee = new Employee();
                    account.setUserID(employeeID);
                    account.setFullName(getFullName);
                    account.setUserEmail(getEmail);
                    account.setUserAddress(getAddress);
                    account.setUserPhone(getPhoneNumber);
                    account.setGender(getGender);
                    if (getEmployeeImage != null) {
                        String[] cutCodeImage = getEmployeeImage.split("\\,");
                        account.setImageAvatar(cutCodeImage[1]);
                    }
                    Date convertDateOfBirth = Date.valueOf(getDateOfBirth);
                    account.setDateOfBirth(convertDateOfBirth);
                    employee.setEmployeeID(employeeID);
                    employee.setSalary(Integer.parseInt(getSalary));
                    employee.setInsurance(Integer.parseInt(getInsurance));
                    accountFacade.updateAccount(account, "EditProfileEmployee");
                    employeeFacade.updateEmployee(employee);
                    response.sendRedirect(request.getContextPath() + "/admin/employee-management");
                }
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
