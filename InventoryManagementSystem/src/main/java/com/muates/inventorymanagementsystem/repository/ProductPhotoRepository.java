package com.muates.inventorymanagementsystem.repository;

import com.muates.inventorymanagementsystem.common.repository.BaseRepository;
import com.muates.inventorymanagementsystem.model.entity.ProductPhoto;

import java.util.List;

public interface ProductPhotoRepository extends BaseRepository<ProductPhoto, Integer> {
    List<ProductPhoto> findAllByProductId(Integer productId);
}
