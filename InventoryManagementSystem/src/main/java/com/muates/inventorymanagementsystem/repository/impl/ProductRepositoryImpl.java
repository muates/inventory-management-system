package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Product;
import com.muates.inventorymanagementsystem.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepositoryImpl extends BaseRepositoryImpl<Product, Integer> implements ProductRepository {

    private final Connection connection;

    public ProductRepositoryImpl(Connection connection) {
        super(connection, Product.class);
        this.connection = connection;
    }

    @Override
    public List<Product> findAllBySupplierId(Integer supplierId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE supplier_id = ? ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, supplierId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> findAllByIds(List<Integer> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<Product> products = new ArrayList<>();

        String placeholders = String.join(",", Collections.nCopies(productIds.size(), "?"));
        String query = "SELECT * FROM product WHERE id IN (" + placeholders + ") ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < productIds.size(); i++) {
                stmt.setInt(i + 1, productIds.get(i));
            }

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private Product mapProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setStockQuantity(resultSet.getInt("stock_quantity"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setDiscount(resultSet.getBigDecimal("discount"));
        product.setSupplierId(resultSet.getInt("supplier_id"));
        return product;
    }

}
