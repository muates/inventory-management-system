package com.muates.inventorymanagementsystem.listener;

import com.muates.inventorymanagementsystem.common.database.PostgresDatabaseConnection;
import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.repository.*;
import com.muates.inventorymanagementsystem.repository.impl.*;
import com.muates.inventorymanagementsystem.service.auth.AuthService;
import com.muates.inventorymanagementsystem.service.auth.impl.AuthServiceImpl;
import com.muates.inventorymanagementsystem.service.auth.strategy.AuthStrategy;
import com.muates.inventorymanagementsystem.service.auth.strategy.impl.RetailerAuthStrategy;
import com.muates.inventorymanagementsystem.service.auth.strategy.impl.SupplierAuthStrategy;
import com.muates.inventorymanagementsystem.service.cart.CartService;
import com.muates.inventorymanagementsystem.service.cart.impl.CartServiceImpl;
import com.muates.inventorymanagementsystem.service.order.OrderService;
import com.muates.inventorymanagementsystem.service.order.impl.OrderServiceImpl;
import com.muates.inventorymanagementsystem.service.product.ProductService;
import com.muates.inventorymanagementsystem.service.product.impl.ProductServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DependencyManager container = DependencyManager.getContainer();

        registerRepositories(container);
        registerServices(container);

        container.injectAllDependencies();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PostgresDatabaseConnection.closeConnection();
    }

    private void registerServices(DependencyManager container) {
        try {
            // Auth
            container.register(AuthService.class, new AuthServiceImpl());
            container.register(AuthStrategy.class, "supplier", new SupplierAuthStrategy());
            container.register(AuthStrategy.class, "retailer", new RetailerAuthStrategy());

            // Product
            container.register(ProductService.class, new ProductServiceImpl());

            // Cart
            container.register(CartService.class, new CartServiceImpl());

            // Order
            container.register(OrderService.class, new OrderServiceImpl());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void registerRepositories(DependencyManager container) {
        try {
            Connection postgresConnection = PostgresDatabaseConnection.getConnection();

            if (postgresConnection != null) {

                container.register(SupplierRepository.class, new SupplierRepositoryImpl(postgresConnection));
                container.register(RetailerRepository.class, new RetailerRepositoryImpl(postgresConnection));
                container.register(ProductRepository.class, new ProductRepositoryImpl(postgresConnection));
                container.register(ProductPhotoRepository.class, new ProductPhotoRepositoryImpl(postgresConnection));
                container.register(BillRepository.class, new BillRepositoryImpl(postgresConnection));
                container.register(BillDetailRepository.class, new BillDetailRepositoryImpl(postgresConnection));

            } else {
                throw new RuntimeException("Postgres connection is null");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while registering CategoryRepository", e);
        }
    }
}
