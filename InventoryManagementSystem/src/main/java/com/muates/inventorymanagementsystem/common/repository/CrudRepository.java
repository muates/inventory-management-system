package com.muates.inventorymanagementsystem.common.repository;

import java.util.List;

public interface CrudRepository<TEntity, TID> {

    TEntity save(TEntity entity);

    TEntity findById(TID id);

    List<TEntity> findAll();

    void update(TEntity entity);

    void delete(TID id);
}
