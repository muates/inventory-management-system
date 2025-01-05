package com.muates.inventorymanagementsystem.service.product.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.ProductConverter;
import com.muates.inventorymanagementsystem.common.mapper.UpdateMapper;
import com.muates.inventorymanagementsystem.exception.ErrorCode;
import com.muates.inventorymanagementsystem.exception.handler.GlobalExceptionHandler;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductUpdateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.response.ProductResponse;
import com.muates.inventorymanagementsystem.model.entity.Product;
import com.muates.inventorymanagementsystem.model.entity.ProductPhoto;
import com.muates.inventorymanagementsystem.repository.ProductPhotoRepository;
import com.muates.inventorymanagementsystem.repository.ProductRepository;
import com.muates.inventorymanagementsystem.service.product.ProductService;
import com.muates.inventorymanagementsystem.service.product.cloudinary.CloudinaryService;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductPhotoRepository productPhotoRepository;

    @Inject
    private CloudinaryService cloudinaryService;

    @Override
    public ProductResponse save(ProductCreateRequest request) {
        Product product = ProductConverter.toEntity(request);
        productRepository.save(product);

        List<ProductPhoto> productPhotos = new ArrayList<>();
        if (request.getPhotos() != null) {
            for (Part photoPart : request.getPhotos()) {
                if (photoPart != null && photoPart.getSize() > 0) {
                    try (InputStream inputStream = photoPart.getInputStream()) {
                        String url = cloudinaryService.uploadPhoto(inputStream);
                        ProductPhoto photo = new ProductPhoto(product.getId(), url, false);
                        productPhotos.add(photo);
                    } catch (Exception e) {
                        System.err.println("Photo Upload Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

            }

            if (!productPhotos.isEmpty()) {
                productPhotos.get(0).setPrimary(true);
                productPhotoRepository.saveAll(productPhotos);
            }
        }

        return ProductConverter.toDto(product, productPhotos);
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            GlobalExceptionHandler.handle(ErrorCode.PRODUCT_NOT_FOUND);
        }

        List<ProductPhoto> photos = productPhotoRepository.findAllByProductId(product.getId());

        return ProductConverter.toDto(product, photos);
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> existProducts = productRepository.findAll();

        Map<Product, List<ProductPhoto>> productPhotosMap = mapProducts(existProducts);

        return ProductConverter.toDto(productPhotosMap);
    }

    @Override
    public List<ProductResponse> findAllBySupplierId(Integer supplierId) {
        List<Product> existProducts = productRepository.findAllBySupplierId(supplierId);

        Map<Product, List<ProductPhoto>> productPhotosMap = mapProducts(existProducts);

        return ProductConverter.toDto(productPhotosMap);
    }

    @Override
    public void update(Integer id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id);

        if (product == null) {
            GlobalExceptionHandler.handle(ErrorCode.PRODUCT_NOT_FOUND);
        }

        UpdateMapper.updateFields(request, product);

        productRepository.update(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.delete(id);
    }

    private Map<Product, List<ProductPhoto>> mapProducts(List<Product> existProducts) {
        Map<Product, List<ProductPhoto>> productPhotosMap = new HashMap<>();

        if (!existProducts.isEmpty()) {
            for (Product product : existProducts) {
                List<ProductPhoto> photos = productPhotoRepository.findAllByProductId(product.getId());
                productPhotosMap.put(product, photos);
            }
        }

        return productPhotosMap;
    }
}
