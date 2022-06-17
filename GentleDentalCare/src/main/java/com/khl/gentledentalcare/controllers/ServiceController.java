package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.HospitalFacade;
import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.models.Hospital;
import com.khl.gentledentalcare.models.Services;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class ServiceController extends HttpServlet {

    private static final String SERVICE_LIST = "SERVICE_LIST";
    private static final String TOTAL_SERVICE_LIST = "TOTAL_SERVICE_LIST";
    private static final String NOT_EMPTY = "NOT_EMPTY";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String serviceAmount = request.getParameter("serviceExits");
            String urlServlet = request.getServletPath();
            PrintWriter printWriter = response.getWriter();

            ServiceFacade serviceFacade = new ServiceFacade();

            List<Services> servicesList;

            if (urlServlet.equals("/service")) {
                if (serviceAmount != null) {
                    int serviceAmountInt = Integer.parseInt(serviceAmount);
                    servicesList = serviceFacade.getServices(serviceAmountInt, "GetNext6Course");
                    returnPrintWriter(servicesList, printWriter, request);
                } else {
                    servicesList = serviceFacade.getServices("", "Top6News");

                    request.setAttribute(TOTAL_SERVICE_LIST, serviceFacade.countServices());
                    request.setAttribute(SERVICE_LIST, servicesList);
                    request.setAttribute(NOT_EMPTY, NOT_EMPTY);

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/Service.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                String indexPage = request.getParameter("page");
                String serviceID = request.getParameter("serviceID");

                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);

                if (serviceID != null) {

                } else {
                    int countService = serviceFacade.countServices();
                    int endPage = countService / 5;
                    if (countService % 5 != 0) {
                        endPage++;
                    }

                    servicesList = serviceFacade.getServices(index, "PagingService");
                    if (servicesList.isEmpty()) {
                        request.setAttribute(SERVICE_LIST, null);
                    } else {
                        JSONArray jsArray = new JSONArray(servicesList);
                        request.setAttribute(SERVICE_LIST, jsArray.toString());
                    }

                    request.setAttribute(END_PAGE, endPage);
                    request.setAttribute(CURRENT_PAGE, index);

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/ServiceManagement.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void returnPrintWriter(List<Services> servicesList, PrintWriter printWriter, HttpServletRequest request) {
        for (Services services : servicesList) {
            printWriter.println("<div class=\"col-md-4 service-item\" data-aos=\"fade-up\" data-aos-duration=\"1000\">\n"
                    + "                                <a href=\"" + request.getContextPath() + "/service-detail?sid=" + services.getServiceID() + "\">\n"
                    + "                                    <div class=\"rounded-top overflow-hidden service-image\">\n"
                    + "                                        <img class=\"img-fluid\" src=\"data:image/png;base64," + services.getImageService() + "\" alt=\"" + services.getServiceName() + "\">\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"position-relative bg-light rounded-bottom text-center p-4\">\n"
                    + "                                        <h5 class=\"m-0\">" + services.getServiceName() + "</h5>\n"
                    + "                                    </div>\n"
                    + "                                </a>\n"
                    + "                            </div>");
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
