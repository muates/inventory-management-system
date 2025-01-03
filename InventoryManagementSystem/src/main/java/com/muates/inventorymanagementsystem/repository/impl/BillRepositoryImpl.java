package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Bill;
import com.muates.inventorymanagementsystem.model.enums.BillStatus;
import com.muates.inventorymanagementsystem.repository.BillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryImpl extends BaseRepositoryImpl<Bill, Integer> implements BillRepository {

    private final Connection connection;

    public BillRepositoryImpl(Connection connection) {
        super(connection, Bill.class);
        this.connection = connection;
    }

    @Override
    public List<Bill> findByUserId(String columnName, Integer userId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bill WHERE " + columnName + " = ? ORDER BY date DESC, id DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                bills.add(mapBill(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    private Bill mapBill(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        bill.setId(resultSet.getInt("id"));
        bill.setSupplierId(resultSet.getInt("supplier_id"));
        bill.setRetailerId(resultSet.getInt("retailer_id"));
        bill.setStatus(BillStatus.valueOf(resultSet.getString("status").toUpperCase()));
        bill.setTotalAmount(resultSet.getBigDecimal("total_amount"));
        bill.setDate(resultSet.getTimestamp("date"));
        return bill;
    }

}
