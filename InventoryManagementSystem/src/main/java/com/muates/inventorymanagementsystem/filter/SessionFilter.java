package com.muates.inventorymanagementsystem.filter;

import com.muates.inventorymanagementsystem.session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        if (!requestURI.equals("/auth") && !requestURI.equals("/logout")) {
            if (SessionManager.getUserId(httpRequest) == null || SessionManager.isSupplier(httpRequest) == null) {
                httpResponse.sendRedirect("/auth");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
