package com.khl.gentledentalcare.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilterUser implements Filter {

    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;

    private static final String[] loginRequiredURLs = {
        "/info-profile", "/edit-profile", "/change-password", "/history-booking"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();

        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;
        httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        boolean isLoggedIn = (session != null && session.getAttribute("LOGIN_USER") != null);
        String loginURI = httpRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        if (path.startsWith("/admin/")) {
            chain.doFilter(request, response);
            return;
        } else if (path.startsWith("/employee/")) {
            chain.doFilter(request, response);
            return;
        } else {
            if (isLoggedIn && isLoginRequest) {
                httpRequest.getRequestDispatcher("/").forward(request, response);
            } else if (!isLoggedIn && isLoginRequired()) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

}
