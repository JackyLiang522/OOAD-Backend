package com.sustech.ooad.repository;


import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
   
    List<TransactionRecord> findTransactionRecordsByClientOrderByDateDesc(Client client);
}
