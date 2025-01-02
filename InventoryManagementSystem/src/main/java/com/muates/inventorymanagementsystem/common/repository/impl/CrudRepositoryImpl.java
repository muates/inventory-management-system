package com.muates.inventorymanagementsystem.common.repository.impl;

import com.muates.inventorymanagementsystem.common.mapper.DynamicRowMapper;
import com.muates.inventorymanagementsystem.common.repository.CrudRepository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudRepositoryImpl<TEntity, TID> implements CrudRepository<TEntity, TID> {

    private final Connection connection;
    private final Class<TEntity> entityClass;

    public CrudRepositoryImpl(Connection connection, Class<TEntity> entityClass) {
        this.connection = connection;
        this.entityClass = entityClass;
    }

    @Override
    public TEntity save(TEntity entity) {
        Class<?> clazz = entity.getClass();
        String query = buildInsertQuery(entity, clazz);
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatementSaveParams(stmt, entity);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                setId(entity, generatedKeys.getObject(1));
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public TEntity findById(TID id) {
        String query = "SELECT * FROM " + getEntityClass().getSimpleName() + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return DynamicRowMapper.mapRow(resultSet, getEntityClass());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TEntity> findAll() {
        String query = "SELECT * FROM " + getEntityClass().getSimpleName() + " ORDER BY id";
        return executeQuery(query);
    }

    @Override
    public void update(TEntity entity) {
        String query = buildUpdateQuery(entity);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setPreparedStatementUpdateParams(stmt, entity);

            stmt.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TID id) {
        String query = "DELETE FROM " + getEntityClass().getSimpleName() + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TEntity> saveAll(List<TEntity> entities) {
        List<TEntity> savedEntities = new ArrayList<>();
        String query = buildInsertQuery(entities.get(0), getEntityClass());

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (TEntity entity : entities) {
                setPreparedStatementSaveParams(stmt, entity);
                stmt.addBatch();
            }

            stmt.executeBatch();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            for (TEntity entity : entities) {
                if (generatedKeys.next()) {
                    setId(entity, generatedKeys.getObject(1));
                }
            }

            savedEntities.addAll(entities);

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return savedEntities;
    }

    private String buildInsertQuery(TEntity entity, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder columnNames = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                if (columnNames.length() > 0) {
                    columnNames.append(", ");
                    placeholders.append(", ");
                }
                columnNames.append(toSnakeCase(field.getName()));
                placeholders.append("?");
            }
        }

        return "INSERT INTO " + clazz.getSimpleName() + " (" + columnNames + ") VALUES (" + placeholders + ")";
    }

    private String buildUpdateQuery(TEntity entity) {
        Class<?> clazz = getEntityClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder updateFields = new StringBuilder();

        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                String columnName = toSnakeCase(field.getName());
                updateFields.append(columnName).append(" = ?, ");
            }
        }

        updateFields.setLength(updateFields.length() - 2); // Remove the last comma
        return "UPDATE " + clazz.getSimpleName() + " SET " + updateFields + " WHERE id = ?";
    }


    private void setPreparedStatementSaveParams(PreparedStatement stmt, TEntity entity) throws SQLException, IllegalAccessException {
        Field[] fields = getEntityClass().getDeclaredFields();
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equalsIgnoreCase("id")) {
                Object value = field.get(entity);
                if (value == null) {
                    stmt.setObject(index++, null);
                } else {
                    stmt.setObject(index++, value);
                }
            }
        }
    }

    private void setPreparedStatementUpdateParams(PreparedStatement stmt, TEntity entity) throws SQLException, IllegalAccessException {
        Field[] fields = getEntityClass().getDeclaredFields();
        int index = 1;

        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equalsIgnoreCase("id")) {
                Object value = field.get(entity);
                if (value == null) {
                    stmt.setObject(index++, null);
                } else {
                    stmt.setObject(index++, value);
                }
            }
        }

        stmt.setObject(index, getIdValue(entity));
    }

    private Object getIdValue(TEntity entity) throws IllegalAccessException {
        Field[] fields = getEntityClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                return field.get(entity);
            }
        }
        return null;
    }

    private int getFieldCount(TEntity entity) {
        return getEntityClass().getDeclaredFields().length;
    }

    private void setId(TEntity entity, Object idValue) throws IllegalAccessException {
        Field[] fields = getEntityClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                field.set(entity, idValue);
            }
        }
    }

    private List<TEntity> executeQuery(String query) {
        List<TEntity> resultList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                resultList.add(DynamicRowMapper.mapRow(resultSet, getEntityClass()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private String toSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    protected Class<TEntity> getEntityClass() {
        return entityClass;
    }
}
