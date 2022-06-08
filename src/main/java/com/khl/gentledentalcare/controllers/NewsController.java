package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.NewsFacade;
import com.khl.gentledentalcare.models.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsController extends HttpServlet {

    private static final String NEWS_LIST = "NEWS_LIST";
    private static final String TOTAL_NEWS_LIST = "TOTAL_NEWS_LIST";
    private static final String NOT_EMPTY = "NOT_EMPTY";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String newstAmount = request.getParameter("newsExits");
            String textSearch = request.getParameter("search");
            PrintWriter printWriter = response.getWriter();

            NewsFacade newsFacade = new NewsFacade();

            List<News> newsList;

            if (newstAmount != null) {
                int newsAmountInt = Integer.parseInt(newstAmount);
                newsList = newsFacade.getNews(newsAmountInt, "GetNext8Course");
                returnPrintWriter(newsList, printWriter, request);
            } else if (textSearch != null) {
                newsList = newsFacade.getNews(textSearch, "SearchByName");
                returnPrintWriter(newsList, printWriter, request);
            } else {
                newsList = newsFacade.getNews("", "Top8News");

                request.setAttribute(TOTAL_NEWS_LIST, newsFacade.countNews());
                request.setAttribute(NEWS_LIST, newsList);
                request.setAttribute(NOT_EMPTY, NOT_EMPTY);

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/News.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void returnPrintWriter(List<News> newsList, PrintWriter printWriter, HttpServletRequest request) {
        if (newsList.isEmpty()) {
            returnEmptyList(printWriter);
        } else {
            for (News news : newsList) {
                printWriter.println("<div class=\"about__keep news-amount\">\n"
                        + "                        <div class=\"container\">\n"
                        + "                            <div class=\"row news_keep align-items-center\">\n"
                        + "                                <div class=\"col-12 col-xl-6 mb-5 mb-xl-0\">\n"
                        + "                                    <div class=\"about__keep--content\">\n"
                        + "                                        <h1 style=\"color: rgba(118, 183, 243, 1)\">"+ news.getNameOfNews() +"</h1>\n"
                        + "                                        <div class=\"desc\">\n"
                        + "                                            <p>"+ news.getSubtitleNews() +"</p>\n"
                        + "                                        </div>\n"
                        + "                                        <div class=\"btn--link\">\n"
                        + "                                            <a class=\"about-btn-link\" href=\"#\">View more</a>\n"
                        + "                                        </div>\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                                <div class=\"col-12 col-xl-6\">\n"
                        + "                                    <div class=\"about__keep--img\">\n"
                        + "                                        <img src=\"data:image/png;base64,"+ news.getImageNews() +"\" alt=\"\">\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
        }
    }

    private void returnEmptyList(PrintWriter printWriter) {
        printWriter.println("<lottie-player src=\"https://assets2.lottiefiles.com/private_files/lf30_cgfdhxgx.json\" background=\"transparent\" speed=\"1\" loop autoplay class=\"empty-product\"></lottie-player>");
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
