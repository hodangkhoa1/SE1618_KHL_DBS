package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.HospitalFacade;
import com.khl.gentledentalcare.models.Hospital;
import com.khl.gentledentalcare.models.HospitalError;
import com.khl.gentledentalcare.utils.FunctionRandom;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class HospitalManagementController extends HttpServlet {

    private static final String HOSPITAL_LIST = "HOSPITAL_LIST";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String BUTTON_ACTION = "BUTTON_ACTION";
    private static final String ACTION_URL = "ACTION_URL";
    private static final String HOSPITAL_NAME = "HOSPITAL_NAME";
    private static final String HOSPITAL_PHONE = "HOSPITAL_PHONE";
    private static final String HOSPITAL_ADDRESS = "HOSPITAL_ADDRESS";
    private static final String SEARCH = "SEARCH";
    private static final String NAV_BAR_PROFILE = "NAV_BAR_PROFILE";
    private static final String NAV_BAR_ICON = "NAV_BAR_ICON";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();

            HospitalFacade hospitalFacade = new HospitalFacade();

            if (urlServlet.equals("/admin/add-hospital")) {
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-plus icon\"></i>");
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(BUTTON_ACTION, "Add Hospital");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-hospital");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddHospital.jsp");
                requestDispatcher.forward(request, response);
            } else if (urlServlet.equals("/admin/edit-hospital")) {
                String hospitalID = request.getParameter("hid");
                Hospital hospital = hospitalFacade.getHospital(hospitalID);

                request.setAttribute(HOSPITAL_NAME, hospital.getHospitalName());
                request.setAttribute(HOSPITAL_PHONE, hospital.getHospitalPhone());
                request.setAttribute(HOSPITAL_ADDRESS, hospital.getHospitalAddress());
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-pen-to-square icon\"></i>");
                request.setAttribute(BUTTON_ACTION, "Edit Hospital");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-hospital");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddHospital.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String indexPage = request.getParameter("page");
                String hospitalID = request.getParameter("hospitalID");

                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                List<Hospital> hospitalList;

                if (hospitalID != null) {

                } else {
                    int countHospital = hospitalFacade.countHospital();
                    int endPage = countHospital / 5;
                    if (countHospital % 5 != 0) {
                        endPage++;
                    }

                    hospitalList = hospitalFacade.getAllHospital(index, "PagingHospital", null);
                    if (hospitalList.isEmpty()) {
                        request.setAttribute(HOSPITAL_LIST, null);
                    } else {
                        JSONArray jsArray = new JSONArray(hospitalList);
                        request.setAttribute(HOSPITAL_LIST, jsArray.toString());
                    }

                    request.setAttribute(END_PAGE, endPage);
                    request.setAttribute(CURRENT_PAGE, index);
                    request.setAttribute(SEARCH, "hospitalName");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/HospitalManagement.jsp");
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

            Hospital hospital;
            HospitalFacade hospitalFacade = new HospitalFacade();

            if (urlServlet.equals("/admin/add-hospital")) {
                String getHospitalName = request.getParameter("hospitalName");
                String getHospitalPhone = request.getParameter("hospitalPhone");
                String getHospitalAddress = request.getParameter("hospitalAddress");
                String hospitalID = FunctionRandom.randomID(10);

                HospitalError hospitalError = new HospitalError();
                boolean hasError = false;

                if (getHospitalName.equals("") && getHospitalPhone.equals("") && getHospitalAddress.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalName("Please enter hospital name!");
                    hospitalError.setHospitalPhone("Please enter hospital phone!");
                    hospitalError.setHospitalAddress("Please choose hospital address!");
                } else if (getHospitalName.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalName("Please enter hospital name!");
                } else if (getHospitalPhone.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalPhone("Please enter hospital phone!");
                } else if (getHospitalAddress.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalAddress("Please choose hospital address!");
                }

                if (hasError) {
                    request.setAttribute(HOSPITAL_NAME, getHospitalName);
                    request.setAttribute(HOSPITAL_PHONE, getHospitalPhone);
                    request.setAttribute(HOSPITAL_ADDRESS, getHospitalAddress);
                    request.setAttribute(BUTTON_ACTION, "Add Hospital");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-hospital");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddHospital.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    hospital = new Hospital();
                    hospital.setHospitalID(hospitalID);
                    hospital.setHospitalName(getHospitalName);
                    hospital.setHospitalPhone(getHospitalPhone);
                    hospital.setHospitalAddress(getHospitalAddress);
                    hospitalFacade.addHospital(hospital);
                    response.sendRedirect(request.getContextPath() + "/admin/hospital-management");
                }
            } else {
                String hospitalID = request.getParameter("hid");
                String getHospitalName = request.getParameter("hospitalName");
                String getHospitalPhone = request.getParameter("hospitalPhone");
                String getHospitalAddress = request.getParameter("hospitalAddress");

                HospitalError hospitalError = new HospitalError();
                boolean hasError = false;

                if (getHospitalName.equals("") && getHospitalPhone.equals("") && getHospitalAddress.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalName("Please enter hospital name!");
                    hospitalError.setHospitalPhone("Please enter hospital phone!");
                    hospitalError.setHospitalAddress("Please choose hospital address!");
                } else if (getHospitalName.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalName("Please enter hospital name!");
                } else if (getHospitalPhone.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalPhone("Please enter hospital phone!");
                } else if (getHospitalAddress.equals("")) {
                    hasError = true;
                    hospitalError.setHospitalAddress("Please choose hospital address!");
                }

                if (hasError) {
                    request.setAttribute(HOSPITAL_NAME, getHospitalName);
                    request.setAttribute(HOSPITAL_PHONE, getHospitalPhone);
                    request.setAttribute(HOSPITAL_ADDRESS, getHospitalAddress);
                    request.setAttribute(BUTTON_ACTION, "Edit Hospital");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-hospital");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddHospital.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    hospital = new Hospital();
                    hospital.setHospitalID(hospitalID);
                    hospital.setHospitalName(getHospitalName);
                    hospital.setHospitalPhone(getHospitalPhone);
                    hospital.setHospitalAddress(getHospitalAddress);
                    hospitalFacade.updateHospital(hospital, "EditHospital");
                    response.sendRedirect(request.getContextPath() + "/admin/hospital-management");
                }
            }

        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
