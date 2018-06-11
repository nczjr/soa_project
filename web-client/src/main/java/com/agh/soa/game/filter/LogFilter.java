package com.agh.soa.game.filter;

import javax.ejb.SessionContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class LogFilter implements Filter {

    private SessionContext context;

    private static Map<String, String> userSessions = new HashMap<>();


    public static Map<String, String> getUserSessions() {
        return userSessions;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Principal userPrincipal = ((HttpServletRequest) servletRequest).getUserPrincipal();
        String jsessionId = ((HttpServletRequest) servletRequest).getSession().getId();
        if(userPrincipal == null){
            ((HttpServletResponse)servletResponse).sendRedirect("/login.xhtml");
        }
        String name = userPrincipal.getName();
        String s = userSessions.get(name);
        if (s == null) {
            userSessions.put(name, jsessionId);
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (s.equals(jsessionId)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletRequest)servletRequest).getSession().invalidate();
            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath()
                    + "/login/error.xhtml?logged=Podany%20uzytkownik%20jest%20juz%20zalogowany");
        }
    }

    @Override
    public void destroy() {

    }
}
