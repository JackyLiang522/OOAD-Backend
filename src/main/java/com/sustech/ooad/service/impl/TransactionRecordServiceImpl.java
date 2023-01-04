package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.TransactionRecord;
import com.sustech.ooad.repository.TransactionRecordRepository;
import com.sustech.ooad.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    @Override
    public void save(TransactionRecord record) {
        transactionRecordRepository.save(record);
    }

    @Override
    public List<TransactionRecord> getByClient(Client client) {
        return transactionRecordRepository.findTransactionRecordsByClientOrderByDateDesc(client);
    }
}
