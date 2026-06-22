package com.codeline.library.repository;

import com.codeline.library.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    long countByResourceIdAndActiveTrue(Long resourceId);
}