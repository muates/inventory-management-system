package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.ProductPhoto;
import com.muates.inventorymanagementsystem.repository.ProductPhotoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPhotoRepositoryImpl extends BaseRepositoryImpl<ProductPhoto, Integer> implements ProductPhotoRepository {

    private final Connection connection;

    public ProductPhotoRepositoryImpl(Connection connection) {
        super(connection, ProductPhoto.class);
        this.connection = connection;
    }

    @Override
    public List<ProductPhoto> findAllByProductId(Integer productId) {
        List<ProductPhoto> productPhotos = new ArrayList<>();
        String query = "SELECT * FROM product_photo WHERE product_id = ? ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                productPhotos.add(mapProductPhoto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productPhotos;
    }

    private ProductPhoto mapProductPhoto(ResultSet resultSet) throws SQLException {
        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setId(resultSet.getInt("id"));
        productPhoto.setProductId(resultSet.getInt("product_id"));
        productPhoto.setPhotoUrl(resultSet.getString("photo_url"));
        productPhoto.setPrimary(resultSet.getBoolean("is_primary"));
        return productPhoto;
    }
}
