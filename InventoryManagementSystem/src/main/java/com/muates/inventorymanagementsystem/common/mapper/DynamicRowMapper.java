package com.muates.inventorymanagementsystem.common.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DynamicRowMapper {

    public static <TEntity> TEntity mapRow(ResultSet resultSet, Class<TEntity> entityClass) throws SQLException {
        try {
            TEntity entity = createEntityInstance(entityClass);
            mapFieldsToColumns(resultSet, entity);
            return entity;
        } catch (NoSuchMethodException e) {
            throw new SQLException("No default constructor found for entity class: " + entityClass.getName(), e);
        } catch (Exception e) {
            throw new SQLException("Error mapping row to entity of class: " + entityClass.getName(), e);
        }
    }

    private static <TEntity> TEntity createEntityInstance(Class<TEntity> entityClass) throws ReflectiveOperationException {
        Constructor<TEntity> constructor = entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private static <TEntity> void mapFieldsToColumns(ResultSet resultSet, TEntity entity) throws SQLException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String columnName = getColumnName(field);

            try {
                Object value = resultSet.getObject(columnName);
                field.set(entity, value);
            } catch (SQLException e) {
                throw new SQLException("Failed to map column '" + columnName + "' to field '" + field.getName() + "'.", e);
            } catch (IllegalAccessException e) {
                throw new SQLException("Error setting field value for field: " + columnName, e);
            }
        }
    }

    private static String getColumnName(Field field) {
        String fieldName = field.getName();
        StringBuilder columnName = new StringBuilder();

        for (char c : fieldName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                columnName.append("_");
                columnName.append(Character.toLowerCase(c));
            } else {
                columnName.append(c);
            }
        }

        return columnName.toString();
    }
}