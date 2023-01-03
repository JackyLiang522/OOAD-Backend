package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Quiz;
import com.sustech.ooad.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {

}
