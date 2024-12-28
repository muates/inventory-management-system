package com.muates.inventorymanagementsystem.listener;

import com.muates.inventorymanagementsystem.common.database.PostgresDatabaseConnection;
import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.repository.*;
import com.muates.inventorymanagementsystem.repository.impl.*;

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
