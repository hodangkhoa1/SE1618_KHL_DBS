package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.ServiceFacade;
import com.khl.gentledentalcare.models.Services;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceDetailController extends HttpServlet {
    
    private static final String SERVICE_DETAIL = "SERVICE_DETAIL";
    private static final String TOP_SERVICE = "TOP_SERVICE";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String serviceID = request.getParameter("sid");

            if (serviceID != null) {
                ServiceFacade serviceFacade = new ServiceFacade();
                Services services = serviceFacade.getServicesDetail(serviceID);
                List<Services> serviceList = serviceFacade.getServices(null, "Top4ServiceRandom");
                
                request.setAttribute(SERVICE_DETAIL, services);
                request.setAttribute(TOP_SERVICE, serviceList);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/ServiceDetail.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/service");
            }

        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
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
