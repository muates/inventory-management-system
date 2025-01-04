package com.muates.inventorymanagementsystem.controller;

import com.muates.inventorymanagementsystem.model.dto.cart.response.CartItemResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/retailer/cart")
public class RetailerCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItemResponse> cart = getCartFromSession(request.getSession());
        BigDecimal totalPrice = calculateTotalPrice(cart);

        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("cart", cart);

        forwardToCartPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CartItemResponse newItem = createCartItemFromRequest(request);
        HttpSession session = request.getSession();
        List<CartItemResponse> cart = getCartFromSession(session);

        cart.add(newItem);
        session.setAttribute("cart", cart);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private List<CartItemResponse> getCartFromSession(HttpSession session) {
        List<CartItemResponse> cart = (List<CartItemResponse>) session.getAttribute("cart");
        return (cart != null) ? cart : new ArrayList<>();
    }

    private BigDecimal calculateTotalPrice(List<CartItemResponse> cart) {
        return cart.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void forwardToCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/retailer/cart/cart.jsp").forward(request, response);
    }

    private CartItemResponse createCartItemFromRequest(HttpServletRequest request) {
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal unitPrice = new BigDecimal(request.getParameter("unitPrice"));

        String discountParam = request.getParameter("discount");
        Double discount = (discountParam != null && !discountParam.isEmpty()) ? Double.parseDouble(discountParam) : 0.0;

        Integer supplierId = Integer.parseInt(request.getParameter("supplierId"));

        return new CartItemResponse(productId, productName, quantity, unitPrice, discount, supplierId);
    }
}


