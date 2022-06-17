package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.HospitalFacade;
import com.khl.gentledentalcare.models.Hospital;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String indexPage = request.getParameter("page");
            String hospitalID = request.getParameter("hospitalID");

            HospitalFacade hospitalFacade = new HospitalFacade();

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

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/HospitalManagement.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
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
