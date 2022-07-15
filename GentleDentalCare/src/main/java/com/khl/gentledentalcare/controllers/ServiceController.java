package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.models.Services;
import com.khl.gentledentalcare.models.ServicesError;
import com.khl.gentledentalcare.utils.FunctionRandom;
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
    private static final String BUTTON_ACTION = "BUTTON_ACTION";
    private static final String ACTION_URL = "ACTION_URL";
    private static final String SERVICE_NAME = "SERVICE_NAME";
    private static final String SERVICE_PRICE = "SERVICE_PRICE";
    private static final String SERVICE_IMAGE = "SERVICE_IMAGE";
    private static final String SERVICE_DESCRIPTION = "SERVICE_DESCRIPTION";
    private static final String SERVICE_ERROR = "SERVICE_ERROR";
    private static final String SEARCH = "SEARCH";
    private static final String NAV_BAR_PROFILE = "NAV_BAR_PROFILE";
    private static final String NAV_BAR_ICON = "NAV_BAR_ICON";

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();

            PrintWriter printWriter = response.getWriter();
            ServiceFacade serviceFacade = new ServiceFacade();

            List<Services> servicesList;

            if (urlServlet.equals("/service")) {
                String serviceAmount = request.getParameter("serviceExits");
                String searchValue = request.getParameter("search");

                if (serviceAmount != null) {
                    int serviceAmountInt = Integer.parseInt(serviceAmount);
                    servicesList = serviceFacade.getServices(serviceAmountInt, "GetNext6Course");
                    returnPrintWriter(servicesList, printWriter, request);
                } else if (searchValue != null) {
                    servicesList = serviceFacade.getServices(searchValue, "SearchByName");
                    returnPrintWriter(servicesList, printWriter, request);
                } else {
                    servicesList = serviceFacade.getServices(null, "Top6Service");

                    if (servicesList.isEmpty()) {
                        request.setAttribute(SERVICE_LIST, null);
                    } else {
                        request.setAttribute(SERVICE_LIST, servicesList);
                    }
                    
                    request.setAttribute(TOTAL_SERVICE_LIST, serviceFacade.countServices());
                    request.setAttribute(NOT_EMPTY, NOT_EMPTY);

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/Service.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else if (urlServlet.equals("/admin/add-service")) {
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-plus icon\"></i>");
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(BUTTON_ACTION, "Add Service");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-service");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddService.jsp");
                requestDispatcher.forward(request, response);
            } else if (urlServlet.equals("/admin/edit-service")) {
                String serviceID = request.getParameter("sid");

                Services services = serviceFacade.getServicesDetail(serviceID);

                request.setAttribute(SERVICE_NAME, services.getServiceName());
                request.setAttribute(SERVICE_PRICE, services.getServicePrice());
                request.setAttribute(SERVICE_IMAGE, services.getImageService());
                request.setAttribute(SERVICE_DESCRIPTION, services.getDescriptionService());
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-pen-to-square icon\"></i>");
                request.setAttribute(BUTTON_ACTION, "Edit Service");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-service?sid="+ serviceID +"");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddService.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String indexPage = request.getParameter("page");
                String serviceID = request.getParameter("ServiceID");

                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);

                if (serviceID != null) {
                    String actionButton = request.getParameter("Action");
                    
                    Services services = new Services();
                    services.setServiceID(serviceID);
                    
                    if (actionButton.equals("Disable")) {
                        services.setServiceStatus(1);
                    } else {
                        services.setServiceStatus(0);
                    }
                    
                    serviceFacade.updateServices(services, "DeleteServices");
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
                    request.setAttribute(SEARCH, "serviceName");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/ServiceManagement.jsp");
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

            Services services;
            ServiceFacade serviceFacade = new ServiceFacade();

            if (urlServlet.equals("/admin/add-service")) {
                String serviceID = FunctionRandom.randomID(10);
                String getServiceName = request.getParameter("serviceName");
                String getServicePrice = request.getParameter("servicePrice");
                String getServiceImage = request.getParameter("serviceImage");
                String getServiceDescription = request.getParameter("serviceDescription");

                ServicesError servicesError = new ServicesError();
                boolean hasError = false;

                if (getServiceName.equals("") && getServicePrice.equals("") && getServiceImage.equals("") && getServiceDescription.equals("")) {
                    hasError = true;
                    servicesError.setServiceNameError("Please enter service name!");
                    servicesError.setServicePriceError("Please enter service price!");
                    servicesError.setImageServiceError("Please choose image service!");
                    servicesError.setDescriptionServiceError("Please enter service description!");
                } else if (getServiceName.equals("")) {
                    hasError = true;
                    servicesError.setServiceNameError("Please enter service name!");
                } else if (getServicePrice.equals("")) {
                    hasError = true;
                    servicesError.setServicePriceError("Please enter service price!");
                } else if (getServiceImage.equals("")) {
                    hasError = true;
                    servicesError.setImageServiceError("Please choose image service!");
                } else if (getServiceDescription.equals("")) {
                    hasError = true;
                    servicesError.setDescriptionServiceError("Please enter service description!");
                }

                if (hasError) {
                    request.setAttribute(SERVICE_NAME, getServiceName);
                    request.setAttribute(SERVICE_PRICE, getServicePrice);
                    if (getServiceImage != null) {
                        String[] cutCodeImage = getServiceImage.split("\\,");
                        request.setAttribute(SERVICE_IMAGE, cutCodeImage[1]);
                    }
                    request.setAttribute(SERVICE_DESCRIPTION, getServiceDescription);
                    request.setAttribute(SERVICE_ERROR, servicesError);
                    request.setAttribute(BUTTON_ACTION, "Add Service");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-service");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddService.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    services = new Services();
                    services.setServiceID(serviceID);
                    services.setServiceName(getServiceName);
                    services.setServicePrice(Double.parseDouble(getServicePrice));
                    if (getServiceImage != null) {
                        String[] cutCodeImage = getServiceImage.split("\\,");
                        services.setImageService(cutCodeImage[1]);
                    }
                    services.setDescriptionService(getServiceDescription);
                    serviceFacade.addServices(services);
                    response.sendRedirect(request.getContextPath() + "/admin/service-management");
                }
            } else {
                String serviceID = request.getParameter("sid");
                String getServiceName = request.getParameter("serviceName");
                String getServicePrice = request.getParameter("servicePrice");
                String getServiceImage = request.getParameter("serviceImage");
                String getServiceDescription = request.getParameter("serviceDescription");

                ServicesError servicesError = new ServicesError();
                boolean hasError = false;

                if (getServiceName.equals("") && getServicePrice.equals("") && getServiceImage.equals("") && getServiceDescription.equals("")) {
                    hasError = true;
                    servicesError.setServiceNameError("Please enter service name!");
                    servicesError.setServicePriceError("Please enter service price!");
                    servicesError.setImageServiceError("Please choose image service!");
                    servicesError.setDescriptionServiceError("Please enter service description!");
                } else if (getServiceName.equals("")) {
                    hasError = true;
                    servicesError.setServiceNameError("Please enter service name!");
                } else if (getServicePrice.equals("")) {
                    hasError = true;
                    servicesError.setServicePriceError("Please enter service price!");
                } else if (getServiceImage.equals("")) {
                    hasError = true;
                    servicesError.setImageServiceError("Please choose image service!");
                } else if (getServiceDescription.equals("")) {
                    hasError = true;
                    servicesError.setDescriptionServiceError("Please enter service description!");
                }

                if (hasError) {
                    request.setAttribute(SERVICE_NAME, getServiceName);
                    request.setAttribute(SERVICE_PRICE, getServicePrice);
                    if (getServiceImage != null) {
                        String[] cutCodeImage = getServiceImage.split("\\,");
                        request.setAttribute(SERVICE_IMAGE, cutCodeImage[1]);
                    }
                    request.setAttribute(SERVICE_DESCRIPTION, getServiceDescription);
                    request.setAttribute(SERVICE_ERROR, servicesError);
                    request.setAttribute(BUTTON_ACTION, "Edit Service");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-service?sid="+ serviceID +"");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddService.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    services = new Services();
                    services.setServiceID(serviceID);
                    services.setServiceName(getServiceName);
                    services.setServicePrice(Double.parseDouble(getServicePrice));
                    if (getServiceImage != null) {
                        String[] cutCodeImage = getServiceImage.split("\\,");
                        services.setImageService(cutCodeImage[1]);
                    }
                    services.setDescriptionService(getServiceDescription);
                    serviceFacade.updateServices(services, "EditServices");
                    response.sendRedirect(request.getContextPath() + "/admin/service-management");
                }
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
