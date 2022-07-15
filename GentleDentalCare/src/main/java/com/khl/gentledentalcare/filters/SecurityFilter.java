package com.khl.gentledentalcare.filters;

import com.khl.gentledentalcare.dbo.AccountFacade;
import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.utils.Encrypt;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    private static final String REMEMBER_USER = "USER_GDC";
    private static final String REMEMBER_PASSWORD = "USER_P_GDC";
    private static final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
    private static final String LOGIN_USER = "LOGIN_USER";
    private static final String LOGIN_ADMIN = "LOGIN_ADMIN";
    private static final String LOGIN_EMPLOYEE = "LOGIN_EMPLOYEE";

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SecurityFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Account account = new Account();
        AccountFacade accountFacade = new AccountFacade();

        Throwable problem = null;
        try {
            String url = req.getRequestURI();
            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(REMEMBER_USER)) {
                        account.setUserEmail(Encrypt.decrypt(cookie.getValue(), SECRET_KEY));
                    }

                    if (cookie.getName().equals(REMEMBER_PASSWORD)) {
                        account.setUserPassword(cookie.getValue());
                    }
                }
            }

            Account checkAccount = accountFacade.checkAccount(account, "LoginWithCookie");

            if (url.startsWith("/admin")) {
                Account adminAccount = (Account) req.getSession(false).getAttribute(LOGIN_ADMIN);

                if (adminAccount != null) {
                    if ((adminAccount.getUserRole() == 1 && adminAccount.getUserStatus() == 1) || (checkAccount.getUserRole() == 1 && checkAccount.getUserStatus() == 1)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(req.getContextPath() + "/home");
                    }
                } else {
                    res.sendRedirect(req.getContextPath() + "/home");
                }
            } else if (url.startsWith("/employee")) {
                Account employeeAccount = (Account) req.getSession(false).getAttribute(LOGIN_EMPLOYEE);

                if (employeeAccount != null) {
                    if ((employeeAccount.getUserRole() == 2 && employeeAccount.getUserStatus() == 1) || (checkAccount.getUserRole() == 2 && checkAccount.getUserStatus() == 1)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(req.getContextPath() + "/home");
                    }
                } else {
                    res.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                chain.doFilter(request, response);
            }
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals(REMEMBER_USER)) {
//                        account.setUserEmail(Encrypt.decrypt(cookie.getValue(), SECRET_KEY));
//                    }
//                    if (cookie.getName().equals(REMEMBER_PASSWORD)) {
//                        account.setUserPassword(cookie.getValue());
//                    }
//                }
//
//                if (account.getUserEmail() != null && account.getUserPassword() != null) {
//                    Account checkAccount = accountFacade.checkAccount(account, "LoginWithCookie");
//
//                    if (checkAccount.getUserRole() == 0 && checkAccount.getUserStatus() == 1) {
//                        session.setAttribute(LOGIN_USER, account);
//                        session.setMaxInactiveInterval(500);
//                        res.sendRedirect(req.getContextPath() + "/home");
//                    } else if (checkAccount.getUserRole() == 1 && checkAccount.getUserStatus() == 1) {
//                        session.setAttribute(LOGIN_ADMIN, account);
//                        session.setMaxInactiveInterval(500);
//                        res.sendRedirect(req.getContextPath() + "/admin/dashboard");
//                    }
//                } else {
//                    res.sendRedirect(req.getContextPath() + req.getServletPath());
//                }
//            }

            chain.doFilter(request, response);

        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("SecurityFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityFilter()");
        }
        StringBuffer sb = new StringBuffer("SecurityFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
