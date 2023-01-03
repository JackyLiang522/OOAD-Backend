package com.sustech.ooad.service;

import com.sustech.ooad.entity.TransactionRecord;
import org.springframework.stereotype.Service;

@Service
public interface TransactionRecordService {

    void save(TransactionRecord record);
}
