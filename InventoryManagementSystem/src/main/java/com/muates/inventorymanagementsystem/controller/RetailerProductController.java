package com.muates.inventorymanagementsystem.controller;

import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.model.dto.product.response.ProductResponse;
import com.muates.inventorymanagementsystem.service.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/retailer/product")
public class RetailerProductController extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        DependencyManager.getContainer().injectDependencies(this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("product-list".equals(action)) {
            List<ProductResponse> productList = productService.findAll();

            req.setAttribute("productList", productList);
            req.getRequestDispatcher("/views/retailer/product/list-products.jsp").forward(req, resp);
        }
    }

}
