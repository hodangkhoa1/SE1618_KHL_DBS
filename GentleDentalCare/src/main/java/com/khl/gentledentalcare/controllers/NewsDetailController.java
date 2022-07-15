package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.NewsFacade;
import com.khl.gentledentalcare.models.News;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsDetailController extends HttpServlet {
    
    private static final String NEWS_DETAIL = "NEWS_DETAIL";
    private static final String TOP_NEWS = "TOP_NEWS";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String newsID = request.getParameter("nid");
            NewsFacade newsFacade = new NewsFacade();
            
            if (newsID != null) {
                News news = newsFacade.checkNews(newsID);
                List<News> newsList = newsFacade.getNews(null, "GetNewsLatest");
                
                request.setAttribute(NEWS_DETAIL, news);
                request.setAttribute(TOP_NEWS, newsList);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/NewsDetail.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/news");
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
