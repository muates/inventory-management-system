package com.muates.inventorymanagementsystem.service.product.cloudinary;

import java.io.IOException;
import java.io.InputStream;

public interface CloudinaryService {
    String uploadPhoto(InputStream inputStream) throws IOException;
}
