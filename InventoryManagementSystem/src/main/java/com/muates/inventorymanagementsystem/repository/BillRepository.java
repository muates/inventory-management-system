package com.muates.inventorymanagementsystem.repository;

import com.muates.inventorymanagementsystem.common.repository.BaseRepository;
import com.muates.inventorymanagementsystem.model.entity.Bill;

import java.util.List;

public interface BillRepository extends BaseRepository<Bill, Integer> {
    List<Bill> findByUserId(String columnName, Integer userId);
}
