package com.muates.inventorymanagementsystem.controller;

import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.common.mapper.RequestMapper;
import com.muates.inventorymanagementsystem.controller.params.RequestParams;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductUpdateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.response.ProductResponse;
import com.muates.inventorymanagementsystem.service.product.ProductService;
import com.muates.inventorymanagementsystem.session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/supplier/product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class SupplierProductController extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        DependencyManager.getContainer().injectDependencies(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add-product".equals(action)) {
            request.getRequestDispatcher("/views/supplier/product/add-product.jsp").forward(request, response);
        }
        else if ("product-list".equals(action)) {
            Integer supplierId = SessionManager.getUserId(request);
            List<ProductResponse> productList = productService.findAllBySupplierId(supplierId);

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/views/supplier/product/list-products.jsp").forward(request, response);
        }
        else if ("edit-product".equals(action)) {
            String productIdStr = request.getParameter("id");
            if (productIdStr != null) {
                Integer productId = Integer.valueOf(productIdStr);
                ProductResponse productResponse = productService.findById(productId);

                request.setAttribute("product", productResponse);
                request.getRequestDispatcher("/views/supplier/product/edit-product.jsp").forward(request, response);
            }
        }
        else if ("delete".equals(action)) {
            String productIdStr = request.getParameter("id");
            if (productIdStr != null) {
                Integer productId = Integer.valueOf(productIdStr);
                productService.deleteById(productId);
            }
            response.sendRedirect("/supplier");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            ProductCreateRequest productCreateRequest =
                    RequestMapper.toRequest(req, ProductCreateRequest.class, RequestParams.PRODUCT_CREATE_REQUEST_PARAMS);

            List<Part> photos = req.getParts().stream()
                    .filter(part -> "photos".equals(part.getName()))
                    .collect(Collectors.toList());

            productCreateRequest.setPhotos(photos);

            Integer supplierId = SessionManager.getUserId(req);
            productCreateRequest.setSupplierId(supplierId);

            productService.save(productCreateRequest);

            resp.sendRedirect("/supplier");
        }
        else if ("update".equals(action)) {
            Integer productId = Integer.valueOf(req.getParameter("id"));
            ProductUpdateRequest productUpdateRequest =
                    RequestMapper.toRequest(req, ProductUpdateRequest.class, RequestParams.PRODUCT_UPDATE_REQUEST_PARAMS);

            productService.update(productId, productUpdateRequest);

            resp.sendRedirect("/supplier");
        } else {
            resp.sendRedirect("/supplier");
        }
    }

}