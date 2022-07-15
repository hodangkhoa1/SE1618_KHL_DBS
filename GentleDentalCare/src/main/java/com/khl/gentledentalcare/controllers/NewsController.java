package com.khl.gentledentalcare.controllers;

import com.khl.gentledentalcare.dbo.NewsFacade;
import com.khl.gentledentalcare.models.News;
import com.khl.gentledentalcare.models.NewsError;
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

public class NewsController extends HttpServlet {

    private static final String NEWS_LIST = "NEWS_LIST";
    private static final String NEWS_LATEST_LIST = "NEWS_LATEST_LIST";
    private static final String TOTAL_NEWS_LIST = "TOTAL_NEWS_LIST";
    private static final String NOT_EMPTY = "NOT_EMPTY";
    private static final String END_PAGE = "END_PAGE";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String BUTTON_ACTION = "BUTTON_ACTION";
    private static final String ACTION_URL = "ACTION_URL";
    private static final String NAME_NEWS = "NAME_NEWS";
    private static final String IMAGE_NEWS = "IMAGE_NEWS";
    private static final String SUBTITLE_NEWS = "SUBTITLE_NEWS";
    private static final String DETAIL_NEWS = "DETAIL_NEWS";
    private static final String NEWS_ERROR = "NEWS_ERROR";
    private static final String SEARCH = "SEARCH";
    private static final String NAV_BAR_PROFILE = "NAV_BAR_PROFILE";
    private static final String NAV_BAR_ICON = "NAV_BAR_ICON";

    private void returnPrintWriter(List<News> newsList, PrintWriter printWriter, HttpServletRequest request) {
        if (newsList.isEmpty()) {
            returnEmptyList(printWriter);
        } else {
            for (News news : newsList) {
                printWriter.println("<div class=\"col-sm-4 mb-5 mb-sm-2 news-amount\">\n"
                        + "                                        <a href=\"\" class=\"popular-news-link\">\n"
                        + "                                            <div class=\"position-relative image-hover\">\n"
                        + "                                                <img src=\"data:image/png;base64,${news.imageNews}\" class=\"img-fluid\" alt=\"${news.nameOfNews}\"/>\n"
                        + "                                                <span class=\"thumb-title\">NEWS</span>\n"
                        + "                                            </div>\n"
                        + "                                            <h5 class=\"font-weight-600 my-3\">${news.nameOfNews}</h5>\n"
                        + "                                        </a>\n"
                        + "                                    </div>");
            }
        }
    }

    private void returnEmptyList(PrintWriter printWriter) {
        printWriter.println("<lottie-player src=\"https://assets2.lottiefiles.com/private_files/lf30_cgfdhxgx.json\" background=\"transparent\" speed=\"1\" loop autoplay class=\"empty-product\"></lottie-player>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String urlServlet = request.getServletPath();

            PrintWriter printWriter = response.getWriter();
            NewsFacade newsFacade = new NewsFacade();

            List<News> newsList;

            if (urlServlet.equals("/news")) {
                String newsAmount = request.getParameter("newsExits");
                String textSearch = request.getParameter("search");

                if (newsAmount != null) {
                    int newsAmountInt = Integer.parseInt(newsAmount);
                    newsList = newsFacade.getNews(newsAmountInt, "GetNext8Course");
                    returnPrintWriter(newsList, printWriter, request);
                } else if (textSearch != null) {
                    newsList = newsFacade.getNews(textSearch, "SearchByName");
                    returnPrintWriter(newsList, printWriter, request);
                } else {
                    newsList = newsFacade.getNews("", "Top8News");
                    List<News> newsLatestList = newsFacade.getNews("", "GetNewsLatest");

                    request.setAttribute(TOTAL_NEWS_LIST, newsFacade.countNews());
                    request.setAttribute(NEWS_LATEST_LIST, newsLatestList);
                    request.setAttribute(NEWS_LIST, newsList);
                    request.setAttribute(NOT_EMPTY, NOT_EMPTY);

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/user/News.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else if (urlServlet.equals("/admin/add-news")) {
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-plus icon\"></i>");
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(BUTTON_ACTION, "Add News");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-news");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddNews.jsp");
                requestDispatcher.forward(request, response);
            } else if (urlServlet.equals("/admin/edit-news")) {
                String newsID = request.getParameter("nid");

                News news = newsFacade.checkNews(newsID);

                request.setAttribute(NAME_NEWS, news.getNameOfNews());
                request.setAttribute(IMAGE_NEWS, news.getImageNews());
                request.setAttribute(SUBTITLE_NEWS, news.getSubtitleNews());
                request.setAttribute(DETAIL_NEWS, news.getNewsDetailContent());
                request.setAttribute(NAV_BAR_PROFILE, NAV_BAR_PROFILE);
                request.setAttribute(NAV_BAR_ICON, "<i class=\"fa-solid fa-pen-to-square icon\"></i>");
                request.setAttribute(BUTTON_ACTION, "Edit News");
                request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-news?nid=" + newsID + "");

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddNews.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String indexPage = request.getParameter("page");
                String newsID = request.getParameter("NewsID");

                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);

                if (newsID != null) {
                    String actionButton = request.getParameter("Action");

                    News news = new News();
                    news.setNewsID(newsID);

                    if (actionButton.equals("EditStatus")) {
                        news.setStatusNews(1);
                    } else {
                        news.setStatusNews(0);
                    }

                    newsFacade.updateNews(news, "EditStatus");
                } else {
                    int countNews = newsFacade.countNews();
                    int endPage = countNews / 5;
                    if (countNews % 5 != 0) {
                        endPage++;
                    }

                    newsList = newsFacade.getNews(index, "PagingNews");
                    if (newsList.isEmpty()) {
                        request.setAttribute(NEWS_LIST, null);
                    } else {
                        JSONArray jsArray = new JSONArray(newsList);
                        request.setAttribute(NEWS_LIST, jsArray.toString());
                    }

                    request.setAttribute(END_PAGE, endPage);
                    request.setAttribute(CURRENT_PAGE, index);
                    request.setAttribute(SEARCH, "nameOfNews");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/NewsManagement.jsp");
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

            News news;
            NewsFacade newsFacade = new NewsFacade();

            if (urlServlet.equals("/admin/add-news")) {
                String newsID = FunctionRandom.randomID(10);
                String getNewsName = request.getParameter("newsName");
                String getSubtitleNews = request.getParameter("subtitleNews");
                String getNewsImage = request.getParameter("newsImage");
                String getNewsDetailContent = request.getParameter("newsDetailContent");

                NewsError newsError = new NewsError();
                boolean hasError = false;

                if (getNewsName.equals("") && getSubtitleNews.equals("") && getNewsImage.equals("") && getNewsDetailContent.equals("")) {
                    hasError = true;
                    newsError.setNameOfNewsError("Please enter news name!");
                    newsError.setSubtitleNewsError("Please enter subtitle news!");
                    newsError.setImageNewsError("Please choose image news!");
                    newsError.setNewsDetailContentError("Please enter news detail!");
                } else if (getNewsName.equals("")) {
                    hasError = true;
                    newsError.setNameOfNewsError("Please enter news name!");
                } else if (getSubtitleNews.equals("")) {
                    hasError = true;
                    newsError.setSubtitleNewsError("Please enter subtitle news!");
                } else if (getNewsImage.equals("")) {
                    hasError = true;
                    newsError.setImageNewsError("Please choose image news!");
                } else if (getNewsDetailContent.equals("")) {
                    hasError = true;
                    newsError.setNewsDetailContentError("Please enter news detail!");
                }

                if (hasError) {
                    request.setAttribute(NAME_NEWS, getNewsName);
                    if (getNewsImage != null) {
                        String[] cutCodeImage = getNewsImage.split("\\,");
                        request.setAttribute(IMAGE_NEWS, cutCodeImage[1]);
                    }
                    request.setAttribute(SUBTITLE_NEWS, getSubtitleNews);
                    request.setAttribute(DETAIL_NEWS, getNewsDetailContent);
                    request.setAttribute(NEWS_ERROR, newsError);
                    request.setAttribute(BUTTON_ACTION, "Add News");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/add-news");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddNews.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    news = new News();
                    news.setNewsID(newsID);
                    news.setNameOfNews(getNewsName);
                    if (getNewsImage != null) {
                        String[] cutCodeImage = getNewsImage.split("\\,");
                        news.setImageNews(cutCodeImage[1]);
                    }
                    news.setSubtitleNews(getSubtitleNews);
                    news.setNewsDetailContent(getNewsDetailContent);
                    newsFacade.addNews(news);
                    response.sendRedirect(request.getContextPath() + "/admin/news-management");
                }
            } else {
                String newsID = request.getParameter("nid");
                String getNewsName = request.getParameter("newsName");
                String getSubtitleNews = request.getParameter("subtitleNews");
                String getNewsImage = request.getParameter("newsImage");
                String getNewsDetailContent = request.getParameter("newsDetailContent");

                NewsError newsError = new NewsError();
                boolean hasError = false;

                if (getNewsName.equals("") && getSubtitleNews.equals("") && getNewsImage.equals("") && getNewsDetailContent.equals("")) {
                    hasError = true;
                    newsError.setNameOfNewsError("Please enter news name!");
                    newsError.setSubtitleNewsError("Please enter subtitle news!");
                    newsError.setImageNewsError("Please choose image news!");
                    newsError.setNewsDetailContentError("Please enter news detail!");
                } else if (getNewsName.equals("")) {
                    hasError = true;
                    newsError.setNameOfNewsError("Please enter news name!");
                } else if (getSubtitleNews.equals("")) {
                    hasError = true;
                    newsError.setSubtitleNewsError("Please enter subtitle news!");
                } else if (getNewsImage.equals("")) {
                    hasError = true;
                    newsError.setImageNewsError("Please choose image news!");
                } else if (getNewsDetailContent.equals("")) {
                    hasError = true;
                    newsError.setNewsDetailContentError("Please enter news detail!");
                }

                if (hasError) {
                    request.setAttribute(NAME_NEWS, getNewsName);
                    if (getNewsImage != null) {
                        String[] cutCodeImage = getNewsImage.split("\\,");
                        request.setAttribute(IMAGE_NEWS, cutCodeImage[1]);
                    }
                    request.setAttribute(SUBTITLE_NEWS, getSubtitleNews);
                    request.setAttribute(DETAIL_NEWS, getNewsDetailContent);
                    request.setAttribute(NEWS_ERROR, newsError);
                    request.setAttribute(BUTTON_ACTION, "Edit News");
                    request.setAttribute(ACTION_URL, "" + request.getContextPath() + "/admin/edit-news?nid=" + newsID + "");

                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/views/admin/AddNews.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    news = new News();
                    news.setNewsID(newsID);
                    news.setNameOfNews(getNewsName);
                    if (getNewsImage != null) {
                        String[] cutCodeImage = getNewsImage.split("\\,");
                        news.setImageNews(cutCodeImage[1]);
                    }
                    news.setSubtitleNews(getSubtitleNews);
                    news.setNewsDetailContent(getNewsDetailContent);
                    newsFacade.updateNews(news, "EditNews");
                    response.sendRedirect(request.getContextPath() + "/admin/news-management");
                }
            }

        } catch (IOException | SQLException | ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
