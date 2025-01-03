package com.muates.inventorymanagementsystem.repository;

import com.muates.inventorymanagementsystem.common.repository.BaseRepository;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;

import java.util.List;

public interface BillDetailRepository extends BaseRepository<BillDetail, Integer> {
    List<BillDetail> findByBillId(Integer billId);
}
