package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;
import com.muates.inventorymanagementsystem.repository.BillDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailRepositoryImpl extends BaseRepositoryImpl<BillDetail, Integer> implements BillDetailRepository {

    private final Connection connection;

    public BillDetailRepositoryImpl(Connection connection) {
        super(connection, BillDetail.class);
        this.connection = connection;
    }

    @Override
    public List<BillDetail> findByBillId(Integer billId) {
        List<BillDetail> billDetails = new ArrayList<>();
        String query = "SELECT * FROM bill_detail WHERE bill_id = ? ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, billId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                billDetails.add(mapBillDetail(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billDetails;
    }

    private BillDetail mapBillDetail(ResultSet resultSet) throws SQLException {
        BillDetail billDetail = new BillDetail();
        billDetail.setId(resultSet.getInt("id"));
        billDetail.setBillId(resultSet.getInt("bill_id"));
        billDetail.setProductId(resultSet.getInt("product_id"));
        billDetail.setQuantity(resultSet.getInt("quantity"));
        billDetail.setUnitPrice(resultSet.getBigDecimal("unit_price"));
        billDetail.setTotalPrice(resultSet.getBigDecimal("total_price"));
        return billDetail;
    }

}
