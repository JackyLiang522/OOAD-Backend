package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.TransactionRecord;
import com.sustech.ooad.repository.TransactionRecordRepository;
import com.sustech.ooad.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    @Override
    public void save(TransactionRecord record) {
        transactionRecordRepository.save(record);
    }
}
