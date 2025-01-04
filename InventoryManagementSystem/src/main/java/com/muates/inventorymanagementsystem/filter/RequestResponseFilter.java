package com.muates.inventorymanagementsystem.filter;

import com.muates.inventorymanagementsystem.exception.handler.GlobalExceptionHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            GlobalExceptionHandler.setRequestAndResponse(
                    (HttpServletRequest) servletRequest,
                    (HttpServletResponse) servletResponse
            );
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
