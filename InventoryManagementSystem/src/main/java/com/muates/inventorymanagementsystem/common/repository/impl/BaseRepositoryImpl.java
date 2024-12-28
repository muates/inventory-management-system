package com.muates.inventorymanagementsystem.common.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.BaseRepository;

import java.sql.Connection;
import java.util.List;

public class BaseRepositoryImpl<TEntity, TID> implements BaseRepository<TEntity, TID> {

    private final CrudRepositoryImpl<TEntity, TID> crudRepository;

    public BaseRepositoryImpl(Connection connection, Class<TEntity> entityClass) {
        this.crudRepository = new CrudRepositoryImpl<>(connection, entityClass);
    }

    @Override
    public TEntity save(TEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public TEntity findById(TID id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<TEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public void update(TEntity entity) {
        crudRepository.update(entity);
    }

    @Override
    public void delete(TID id) {
        crudRepository.delete(id);
    }
}
