package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.TransactionRecord;
import com.sustech.ooad.service.ClientService;
import com.sustech.ooad.service.TransactionRecordService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactionRecord")
public class TransactionRecordController {

    @Autowired
    private TransactionRecordService transactionRecordService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/list")
    @Transactional
    public List<FrontRecord> list(@RequestParam Long clientId){
        Client client = clientService.getUserById(clientId);
        List<TransactionRecord> transactionRecords = transactionRecordService.getByClient(client);;
        List<FrontRecord> frontRecords = new ArrayList<>();
        for (TransactionRecord transactionRecord : transactionRecords){
            FrontRecord frontRecord = new FrontRecord();
            frontRecord.setChange(transactionRecord.getChange());
            frontRecord.setRemain(transactionRecord.getRemain());
            frontRecord.setCourseName(transactionRecord.getCourseName());
            frontRecord.setDate(transactionRecord.getDate().toString());
            frontRecords.add(frontRecord);
        }
        return frontRecords;
    }

    @GetMapping("/remain")
    @Transactional
    public int remain(@RequestParam Long clientId){
        return clientService.getUserById(clientId).getAccount();
    }

    // 充值
    @PostMapping("/recharge")
    @Transactional
    public int recharge(@RequestParam Long clientId, @RequestParam int change){
        Client client = clientService.getUserById(clientId);
        TransactionRecord record = new TransactionRecord(client.getAccount()+change, change, client, new Date(),null);
        transactionRecordService.save(record);
        client.setAccount(client.getAccount() + change);
        client.getTransactionRecords().add(record);
        return client.getAccount();

    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class FrontRecord{
    private String date;
    private int change;
    private int remain;
    private String courseName;

}
