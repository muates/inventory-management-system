package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Retailer;
import com.muates.inventorymanagementsystem.repository.RetailerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetailerRepositoryImpl extends BaseRepositoryImpl<Retailer, Integer> implements RetailerRepository {

    private final Connection connection;

    public RetailerRepositoryImpl(Connection connection) {
        super(connection, Retailer.class);
        this.connection = connection;
    }

    @Override
    public Retailer findByEmailAddress(String emailAddress) {
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

    private Retailer mapSupplier(ResultSet resultSet) throws SQLException {
        Retailer retailer = new Retailer();
        retailer.setId(resultSet.getInt("id"));
        retailer.setName(resultSet.getString("name"));
        retailer.setPhoneNumber(resultSet.getString("phone_number"));
        retailer.setEmailAddress(resultSet.getString("email_address"));
        retailer.setPassword(resultSet.getString("password"));
        retailer.setPhotoUrl(resultSet.getString("photo_url"));
        return retailer;
    }
}
