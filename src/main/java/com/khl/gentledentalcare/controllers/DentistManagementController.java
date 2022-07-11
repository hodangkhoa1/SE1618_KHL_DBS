package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.DentistFacade;
import com.khl.gentledentalcare.models.Dentist;
import com.khl.gentledentalcare.models.DentistError;
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

public class DentistManagementController extends HttpServlet {

    private static final String DENTIST_LIST = "DENTIST_LIST";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String BUTTON_ACTION = "BUTTON_ACTION";
    private static final String NAME_DENTIST = "NAME_DENTIST";
    private static final String SUBTITLE_DENTIST = "SUBTITLE_DENTIST";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String DENTIST_IMAGE = "DENTIST_IMAGE";
    private static final String DENTIST_DESCRIPTION = "DENTIST_DESCRIPTION";
    private static final String DENTIST_ERROR = "DENTIST_ERROR";
    private static final String ACADEMIC_RANK = "ACADEMIC_RANK";
    private static final String ACTION_URL = "ACTION_URL";
    private static final String SEARCH = "SEARCH";
    private static final String NAV_BAR_PROFILE = "NAV_BAR_PROFILE";
    private static final String NAV_BAR_ICON = "NAV_BAR_ICON";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();

            DentistFacade dentistFacade = new DentistFacade();

            if (urlServlet.equals("/admin/add-dentist")) {
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-plus icon\"></i>");
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(BUTTON_ACTION, "Add Dentist");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-dentist");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddDentist.jsp");
                requestDispatcher.forward(request, response);
            } else if (urlServlet.equals("/admin/edit-dentist")) {
                String dentistID = request.getParameter("did");

                Dentist dentist = dentistFacade.getDentistDetail(dentistID);

                request.setAttribute(NAME_DENTIST, dentist.getNameDentist());
                request.setAttribute(SUBTITLE_DENTIST, dentist.getSubtitleDentist());
                request.setAttribute(PHONE_NUMBER, dentist.getNumberPhoneDentist());
                request.setAttribute(DENTIST_IMAGE, dentist.getImageDentist());
                request.setAttribute(DENTIST_DESCRIPTION, dentist.getDentistDescription());
                request.setAttribute(ACADEMIC_RANK, dentist.getAcademicRank());
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-pen-to-square icon\"></i>");
                request.setAttribute(BUTTON_ACTION, "Edit Dentist");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-dentist?did=" + dentistID + "");
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddDentist.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String indexPage = request.getParameter("page");

                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                List<Dentist> dentistList;

                int countDentist = dentistFacade.countDentist();
                int endPage = countDentist / 5;
                if (countDentist % 5 != 0) {
                    endPage++;
                }

                dentistList = dentistFacade.getAllDentist(index, "PagingDentist");
                if (dentistList.isEmpty()) {
                    request.setAttribute(DENTIST_LIST, null);
                } else {
                    JSONArray jsArray = new JSONArray(dentistList);
                    request.setAttribute(DENTIST_LIST, jsArray.toString());
                }

                request.setAttribute(END_PAGE, endPage);
                request.setAttribute(CURRENT_PAGE, index);
                request.setAttribute(SEARCH, "nameDentist");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/DentistManagement.jsp");
                requestDispatcher.forward(request, response);
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

            Dentist dentist;
            DentistFacade dentistFacade = new DentistFacade();

            if (urlServlet.equals("/admin/add-dentist")) {
                String getFullName = request.getParameter("fullName");
                String getSubtitleDentist = request.getParameter("subtitleDentist");
                String getPhoneNumber = request.getParameter("phoneNumber");
                String getDentistImage = request.getParameter("dentistImage");
                String getAcademicRank = request.getParameter("academicRank");
                String getDentistDescription = request.getParameter("dentistDescription");
                String dentistID = FunctionRandom.randomID(10);

                DentistError dentistError = new DentistError();
                boolean hasError = false;

                if (getFullName.equals("") && getSubtitleDentist.equals("") && getPhoneNumber.equals("") && getDentistImage.equals("") && getAcademicRank.equals("") && getDentistDescription.equals("")) {
                    hasError = true;
                    dentistError.setNameDentistError("Please enter full name!");
                    dentistError.setSubtitleDentist("Please enter subtitle dentist!");
                    dentistError.setNumberPhoneDentistError("Please enter phone number!");
                    dentistError.setImageDentistError("Please choose image avatar!");
                    dentistError.setAcademicRankError("Please enter academic rank!");
                    dentistError.setDentistDescriptionError("Please enter dentist description!");
                } else if (getFullName.equals("")) {
                    hasError = true;
                    dentistError.setNameDentistError("Please enter full name!");
                } else if (getSubtitleDentist.equals("")) {
                    hasError = true;
                    dentistError.setSubtitleDentist("Please enter subtitle dentist!");
                } else if (getPhoneNumber.equals("")) {
                    hasError = true;
                    dentistError.setNumberPhoneDentistError("Please enter phone number!");
                } else if (getDentistImage.equals("")) {
                    hasError = true;
                    dentistError.setImageDentistError("Please choose image avatar!");
                } else if (getAcademicRank.equals("")) {
                    hasError = true;
                    dentistError.setAcademicRankError("Please enter academic rank!");
                } else if (getDentistDescription.equals("")) {
                    hasError = true;
                    dentistError.setDentistDescriptionError("Please enter dentist description!");
                }

                if (hasError) {
                    request.setAttribute(NAME_DENTIST, getFullName);
                    request.setAttribute(SUBTITLE_DENTIST, getSubtitleDentist);
                    request.setAttribute(PHONE_NUMBER, getPhoneNumber);
                    if (getDentistImage != null) {
                        String[] cutCodeImage = getDentistImage.split("\\,");
                        request.setAttribute(DENTIST_IMAGE, cutCodeImage[1]);
                    }
                    request.setAttribute(DENTIST_DESCRIPTION, getDentistDescription);
                    request.setAttribute(ACADEMIC_RANK, getAcademicRank);
                    request.setAttribute(DENTIST_ERROR, dentistError);
                    request.setAttribute(BUTTON_ACTION, "Add Dentist");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-dentist");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddDentist.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    dentist = new Dentist();
                    dentist.setDentistID(dentistID);
                    dentist.setNameDentist(getFullName);
                    dentist.setSubtitleDentist(getSubtitleDentist);
                    dentist.setNumberPhoneDentist(getPhoneNumber);
                    if (getDentistImage != null) {
                        String[] cutCodeImage = getDentistImage.split("\\,");
                        dentist.setImageDentist(cutCodeImage[1]);
                    }
                    dentist.setDentistDescription(getDentistDescription);
                    dentist.setAcademicRank(getAcademicRank);
                    dentistFacade.addDentist(dentist);
                    response.sendRedirect(request.getContextPath() + "/admin/dentist-management");
                }
            } else {
                String dentistID = request.getParameter("did");
                String getFullName = request.getParameter("fullName");
                String getSubtitleDentist = request.getParameter("subtitleDentist");
                String getPhoneNumber = request.getParameter("phoneNumber");
                String getDentistImage = request.getParameter("dentistImage");
                String getAcademicRank = request.getParameter("academicRank");
                String getDentistDescription = request.getParameter("dentistDescription");

                DentistError dentistError = new DentistError();
                boolean hasError = false;

                if (getFullName.equals("") && getSubtitleDentist.equals("") && getPhoneNumber.equals("") && getDentistImage.equals("") && getAcademicRank.equals("") && getDentistDescription.equals("")) {
                    hasError = true;
                    dentistError.setNameDentistError("Please enter full name!");
                    dentistError.setSubtitleDentist("Please enter subtitle dentist!");
                    dentistError.setNumberPhoneDentistError("Please enter phone number!");
                    dentistError.setImageDentistError("Please choose image avatar!");
                    dentistError.setAcademicRankError("Please enter academic rank!");
                    dentistError.setDentistDescriptionError("Please enter dentist description!");
                } else if (getFullName.equals("")) {
                    hasError = true;
                    dentistError.setNameDentistError("Please enter full name!");
                } else if (getSubtitleDentist.equals("")) {
                    hasError = true;
                    dentistError.setSubtitleDentist("Please enter subtitle dentist!");
                } else if (getPhoneNumber.equals("")) {
                    hasError = true;
                    dentistError.setNumberPhoneDentistError("Please enter phone number!");
                } else if (getDentistImage.equals("")) {
                    hasError = true;
                    dentistError.setImageDentistError("Please choose image avatar!");
                } else if (getAcademicRank.equals("")) {
                    hasError = true;
                    dentistError.setAcademicRankError("Please enter academic rank!");
                } else if (getDentistDescription.equals("")) {
                    hasError = true;
                    dentistError.setDentistDescriptionError("Please enter dentist description!");
                }

                if (hasError) {
                    request.setAttribute(NAME_DENTIST, getFullName);
                    request.setAttribute(SUBTITLE_DENTIST, getSubtitleDentist);
                    request.setAttribute(PHONE_NUMBER, getPhoneNumber);
                    if (getDentistImage != null) {
                        String[] cutCodeImage = getDentistImage.split("\\,");
                        request.setAttribute(DENTIST_IMAGE, cutCodeImage[1]);
                    }
                    request.setAttribute(DENTIST_DESCRIPTION, getDentistDescription);
                    request.setAttribute(ACADEMIC_RANK, getAcademicRank);
                    request.setAttribute(DENTIST_ERROR, dentistError);
                    request.setAttribute(BUTTON_ACTION, "Edit Dentist");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-dentist?did=" + dentistID + "");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddDentist.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    dentist = new Dentist();
                    dentist.setDentistID(dentistID);
                    dentist.setNameDentist(getFullName);
                    dentist.setSubtitleDentist(getSubtitleDentist);
                    dentist.setNumberPhoneDentist(getPhoneNumber);
                    if (getDentistImage != null) {
                        String[] cutCodeImage = getDentistImage.split("\\,");
                        dentist.setImageDentist(cutCodeImage[1]);
                    }
                    dentist.setDentistDescription(getDentistDescription);
                    dentist.setAcademicRank(getAcademicRank);
                    dentistFacade.updateDentist(dentist);
                    response.sendRedirect(request.getContextPath() + "/admin/dentist-management");
                }
            }
        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
