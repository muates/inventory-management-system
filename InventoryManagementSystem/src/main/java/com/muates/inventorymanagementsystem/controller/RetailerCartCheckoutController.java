package com.muates.inventorymanagementsystem.controller;

import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;
import com.muates.inventorymanagementsystem.model.dto.cart.request.CartItem;
import com.muates.inventorymanagementsystem.model.dto.cart.request.CartRequest;
import com.muates.inventorymanagementsystem.service.cart.CartService;
import com.muates.inventorymanagementsystem.session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart/checkout")
public class RetailerCartCheckoutController extends HttpServlet {

    @Inject
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        DependencyManager.getContainer().injectDependencies(this);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer retailerId = SessionManager.getUserId(req);

        List<CartItem> cartItems = new ArrayList<>();

        int i = 0;
        while (true) {
            String productId = req.getParameter("items[" + i + "].productId");
            if (productId == null) {
                break;
            }

            CartItem item = new CartItem();
            item.setProductId(Integer.valueOf(productId));
            item.setQuantity(Integer.valueOf(req.getParameter("items[" + i + "].quantity")));
            item.setUnitPrice(new BigDecimal(req.getParameter("items[" + i + "].unitPrice")));
            item.setDiscount(new BigDecimal(req.getParameter("items[" + i + "].discount")));
            item.setSupplierId(Integer.valueOf(req.getParameter("items[" + i + "].supplierId")));
            cartItems.add(item);

            i++;
        }

        CartRequest cartRequest = new CartRequest();
        cartRequest.setRetailerId(retailerId);
        cartRequest.setItems(cartItems);

        List<BillResponse> billResponses = cartService.processCart(cartRequest);

        req.setAttribute("bills", billResponses);
        req.getRequestDispatcher("/views/retailer/retailer.jsp").forward(req, resp);
    }

}
