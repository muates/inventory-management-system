package com.muates.inventorymanagementsystem.controller;

import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.model.dto.auth.request.LoginRequest;
import com.muates.inventorymanagementsystem.model.dto.retailer.request.RetailerCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.supplier.request.SupplierCreateRequest;
import com.muates.inventorymanagementsystem.service.auth.AuthService;
import com.muates.inventorymanagementsystem.common.mapper.RequestMapper;
import com.muates.inventorymanagementsystem.controller.params.RequestParams;
import com.muates.inventorymanagementsystem.session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

    @Inject
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        DependencyManager.getContainer().injectDependencies(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        boolean isSupplier = Boolean.parseBoolean(req.getParameter("isSupplier"));

        if ("register".equals(action)) {
            if (isSupplier) {
                SupplierCreateRequest supplierCreateRequest =
                        RequestMapper.toRequest(req, SupplierCreateRequest.class, RequestParams.SUPPLIER_CREATE_REQUEST_PARAMS);

                authService.register(supplierCreateRequest);
                resp.getWriter().write("Supplier registration successful");
            } else {
                RetailerCreateRequest retailerCreateRequest =
                        RequestMapper.toRequest(req, RetailerCreateRequest.class, RequestParams.RETAILER_CREATE_REQUEST_PARAMS);

                authService.register(retailerCreateRequest);
                resp.getWriter().write("Retailer registration successful");
            }
        } else if ("login".equals(action)) {
            LoginRequest loginRequest = RequestMapper.toRequest(req, LoginRequest.class, RequestParams.AUTH_LOGIN_REQUEST);

            Integer userId = authService.login(loginRequest, isSupplier);

            if (userId != null) {

                SessionManager.createSession(req, userId, isSupplier);

                if (isSupplier) {
                    resp.sendRedirect("/supplier");
                } else {
                    resp.sendRedirect("/retailer");
                }
            } else {
                resp.getWriter().write("Invalid login credentials");
            }
        } else {
            resp.getWriter().write("Invalid action");
        }
    }
}