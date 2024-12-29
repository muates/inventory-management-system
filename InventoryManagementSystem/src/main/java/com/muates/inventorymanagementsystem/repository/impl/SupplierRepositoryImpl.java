package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Supplier;
import com.muates.inventorymanagementsystem.repository.SupplierRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImpl extends BaseRepositoryImpl<Supplier, Integer> implements SupplierRepository {

    private final Connection connection;

    public SupplierRepositoryImpl(Connection connection) {
        super(connection, Supplier.class);
        this.connection = connection;
    }

    @Override
    public Supplier findByEmailAddress(String emailAddress) {
        String query = "SELECT * FROM supplier WHERE email_address = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, emailAddress);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapSupplier(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Supplier mapSupplier(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(resultSet.getInt("id"));
        supplier.setName(resultSet.getString("name"));
        supplier.setPhoneNumber(resultSet.getString("phone_number"));
        supplier.setEmailAddress(resultSet.getString("email_address"));
        supplier.setPassword(resultSet.getString("password"));
        supplier.setPhotoUrl(resultSet.getString("photo_url"));
        return supplier;
    }
}
