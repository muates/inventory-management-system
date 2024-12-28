package com.muates.inventorymanagementsystem.common.database.schema;

public class PostgresDatabaseSchema {

    public static final String RETAILER_TABLE = "CREATE TABLE IF NOT EXISTS retailer ("
            + "id SERIAL PRIMARY KEY, "
            + "name VARCHAR(255) NOT NULL, "
            + "phone_number VARCHAR(15) NOT NULL UNIQUE, "
            + "email_address VARCHAR(255) NOT NULL UNIQUE, "
            + "password VARCHAR(255) NOT NULL, "
            + "photo_url TEXT"
            + ");";

    public static final String SUPPLIER_TABLE = "CREATE TABLE IF NOT EXISTS supplier ("
            + "id SERIAL PRIMARY KEY, "
            + "name VARCHAR(255) NOT NULL, "
            + "phone_number VARCHAR(15) NOT NULL UNIQUE, "
            + "email_address VARCHAR(255) NOT NULL UNIQUE, "
            + "password VARCHAR(255) NOT NULL, "
            + "photo_url TEXT"
            + ");";

    public static final String PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS product ("
            + "id SERIAL PRIMARY KEY, "
            + "name VARCHAR(255) NOT NULL, "
            + "stock_quantity INT DEFAULT 0, "
            + "price DECIMAL(10, 2) NOT NULL, "
            + "discount DECIMAL(5, 2) DEFAULT 0.00"
            + ");";

    public static final String PRODUCT_PHOTO_TABLE = "CREATE TABLE IF NOT EXISTS product_photo ("
            + "id SERIAL PRIMARY KEY, "
            + "product_id INT NOT NULL, "
            + "photo_url TEXT NOT NULL, "
            + "is_primary BOOLEAN DEFAULT FALSE, "
            + "FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE"
            + ");";

    public static final String BILL_STATUS_ENUM = "CREATE TYPE bill_status AS ENUM ('pending', 'approved', 'completed', 'rejected');";

    public static final String BILL_TABLE = "CREATE TABLE IF NOT EXISTS bill ("
            + "id SERIAL PRIMARY KEY, "
            + "supplier_id INT NOT NULL, "
            + "retailer_id INT NOT NULL, "
            + "status bill_status DEFAULT 'pending', "
            + "total_amount DECIMAL(15, 2) DEFAULT 0.00, "
            + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (supplier_id) REFERENCES supplier(id) ON DELETE CASCADE, "
            + "FOREIGN KEY (retailer_id) REFERENCES retailer(id) ON DELETE CASCADE"
            + ");";

    public static final String BILL_DETAIL_TABLE = "CREATE TABLE IF NOT EXISTS bill_detail ("
            + "id SERIAL PRIMARY KEY, "
            + "bill_id INT NOT NULL, "
            + "product_id INT NOT NULL, "
            + "quantity INT NOT NULL, "
            + "unit_price DECIMAL(10, 2) NOT NULL, "
            + "total_price DECIMAL(15, 2) NOT NULL, "
            + "FOREIGN KEY (bill_id) REFERENCES bill(id) ON DELETE CASCADE, "
            + "FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE"
            + ");";


    public static String[] getTables() {
        return new String[]{RETAILER_TABLE, SUPPLIER_TABLE, PRODUCT_TABLE, PRODUCT_PHOTO_TABLE, BILL_STATUS_ENUM, BILL_TABLE, BILL_DETAIL_TABLE};
    }
}
