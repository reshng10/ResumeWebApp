package com.mycompany.resumewebapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "JspFilter", urlPatterns = "*.jsp")
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            response.sendRedirect("ErrorController?msg=Not Found");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
