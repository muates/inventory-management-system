package com.muates.inventorymanagementsystem.controller;

import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.model.dto.order.response.OrderResponse;
import com.muates.inventorymanagementsystem.service.order.OrderService;
import com.muates.inventorymanagementsystem.session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/supplier/order")
public class SupplierOrderController extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        DependencyManager.getContainer().injectDependencies(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer supplierId = SessionManager.getUserId(req);

        boolean isSupplier = Boolean.TRUE.equals(SessionManager.isSupplier(req));

        List<OrderResponse> orders = orderService.findOrders(supplierId, isSupplier);

        req.setAttribute("orders", orders);

        req.getRequestDispatcher("/views/supplier/order/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer billId = Integer.valueOf(req.getParameter("billId"));
        String action = req.getParameter("action");

        if ("approve".equals(action)) {
            orderService.approveOrder(billId);
        } else if ("reject".equals(action)) {
            orderService.rejectOrder(billId);
        }
    }
}
