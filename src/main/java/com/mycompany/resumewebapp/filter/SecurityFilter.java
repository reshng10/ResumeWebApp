package com.mycompany.resumewebapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "SecurityFilter", urlPatterns = {"*"})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req =(HttpServletRequest) servletRequest;
        if(req.getRequestURI().contains("/images") || req.getRequestURI().contains("/fonts") || req.getRequestURI().contains("/js") || req.getRequestURI().contains("/vendor") || req.getRequestURI().contains("/css") ){
            filterChain.doFilter(servletRequest,servletResponse);
        }

        if(req.getRequestURI().contains("ErrorController")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        if ((!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedUser")==null)) {
            resp.sendRedirect("login");
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }
}
