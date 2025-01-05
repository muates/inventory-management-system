package com.muates.inventorymanagementsystem.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;

public class CloudinaryConfig {

    private static final Cloudinary cloudinary;

    static {
        Dotenv dotenv = Dotenv.configure().directory("C:\\Users\\W10\\Desktop\\Projects\\java\\inventory-management-system\\InventoryManagementSystem\\.env").load();
        String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

        cloudinary = new Cloudinary(cloudinaryUrl);
    }

    public static Cloudinary getInstance() {
        return cloudinary;
    }
}
