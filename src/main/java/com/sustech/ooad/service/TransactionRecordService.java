package com.sustech.ooad.service;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.TransactionRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionRecordService {

    void save(TransactionRecord record);

    List<TransactionRecord> getByClient(Client client);
}
