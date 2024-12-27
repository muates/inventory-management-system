package com.muates.inventorymanagementsystem.common.database;

import com.muates.inventorymanagementsystem.common.database.schema.PostgresDatabaseSchema;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDatabaseSetup {

    public static void main(String[] args) {
        try (Connection connection = PostgresDatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String[] tables = PostgresDatabaseSchema.getTables();
            for (String tableSQL : tables) {
                statement.executeUpdate(tableSQL);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PostgresDatabaseConnection.closeConnection();
        }
    }
}
