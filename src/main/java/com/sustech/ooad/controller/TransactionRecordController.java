package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.TransactionRecord;
import com.sustech.ooad.service.ClientService;
import com.sustech.ooad.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactionRecord")
public class TransactionRecordController {

    @Autowired
    private TransactionRecordService transactionRecordService;

    @Autowired
    private ClientService clientService;


    @GetMapping("")
    @Transactional
    public List<TransactionRecord> list(@RequestParam Long clientId){
        return clientService.getUserById(clientId).getTransactionRecords();
    }

    // 充值
    @PostMapping("/recharge")
    @Transactional
    public void recharge(@RequestParam Long clientId, @RequestParam int change){
        Client client = clientService.getUserById(clientId);
        TransactionRecord record = new TransactionRecord(client.getAccount()+change, change, client, new Date(),null);
        client.setAccount(client.getAccount() + change);
        client.getTransactionRecords().add(record);
        transactionRecordService.save(record);

    }

}
