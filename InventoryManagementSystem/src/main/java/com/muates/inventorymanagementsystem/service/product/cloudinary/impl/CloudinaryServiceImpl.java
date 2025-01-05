package com.muates.inventorymanagementsystem.service.product.cloudinary.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.muates.inventorymanagementsystem.config.CloudinaryConfig;
import com.muates.inventorymanagementsystem.service.product.cloudinary.CloudinaryService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl() {
        this.cloudinary = CloudinaryConfig.getInstance();
    }

    @Override
    public String uploadPhoto(InputStream inputStream) throws IOException {
        File tempFile = File.createTempFile("upload-", ".tmp");
        try {
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");

        } finally {
            tempFile.delete();
        }
    }
}
